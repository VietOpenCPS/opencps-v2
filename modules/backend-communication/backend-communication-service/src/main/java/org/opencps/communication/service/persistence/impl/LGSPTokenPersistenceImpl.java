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

package org.opencps.communication.service.persistence.impl;

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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.communication.exception.NoSuchLGSPTokenException;
import org.opencps.communication.model.LGSPToken;
import org.opencps.communication.model.impl.LGSPTokenImpl;
import org.opencps.communication.model.impl.LGSPTokenModelImpl;
import org.opencps.communication.service.persistence.LGSPTokenPersistence;

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
 * The persistence implementation for the lgsp token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavd
 * @see LGSPTokenPersistence
 * @see org.opencps.communication.service.persistence.LGSPTokenUtil
 * @generated
 */
@ProviderType
public class LGSPTokenPersistenceImpl extends BasePersistenceImpl<LGSPToken>
	implements LGSPTokenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LGSPTokenUtil} to access the lgsp token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LGSPTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
			LGSPTokenModelImpl.FINDER_CACHE_ENABLED, LGSPTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
			LGSPTokenModelImpl.FINDER_CACHE_ENABLED, LGSPTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
			LGSPTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
			LGSPTokenModelImpl.FINDER_CACHE_ENABLED, LGSPTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
			LGSPTokenModelImpl.FINDER_CACHE_ENABLED, LGSPTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			LGSPTokenModelImpl.UUID_COLUMN_BITMASK |
			LGSPTokenModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
			LGSPTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the lgsp tokens where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching lgsp tokens
	 */
	@Override
	public List<LGSPToken> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lgsp tokens where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of lgsp tokens
	 * @param end the upper bound of the range of lgsp tokens (not inclusive)
	 * @return the range of matching lgsp tokens
	 */
	@Override
	public List<LGSPToken> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lgsp tokens where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of lgsp tokens
	 * @param end the upper bound of the range of lgsp tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lgsp tokens
	 */
	@Override
	public List<LGSPToken> findByUuid(String uuid, int start, int end,
		OrderByComparator<LGSPToken> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the lgsp tokens where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of lgsp tokens
	 * @param end the upper bound of the range of lgsp tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching lgsp tokens
	 */
	@Override
	public List<LGSPToken> findByUuid(String uuid, int start, int end,
		OrderByComparator<LGSPToken> orderByComparator,
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

		List<LGSPToken> list = null;

		if (retrieveFromCache) {
			list = (List<LGSPToken>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LGSPToken lgspToken : list) {
					if (!Objects.equals(uuid, lgspToken.getUuid())) {
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

			query.append(_SQL_SELECT_LGSPTOKEN_WHERE);

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
				query.append(LGSPTokenModelImpl.ORDER_BY_JPQL);
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
					list = (List<LGSPToken>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LGSPToken>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first lgsp token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lgsp token
	 * @throws NoSuchLGSPTokenException if a matching lgsp token could not be found
	 */
	@Override
	public LGSPToken findByUuid_First(String uuid,
		OrderByComparator<LGSPToken> orderByComparator)
		throws NoSuchLGSPTokenException {
		LGSPToken lgspToken = fetchByUuid_First(uuid, orderByComparator);

		if (lgspToken != null) {
			return lgspToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchLGSPTokenException(msg.toString());
	}

	/**
	 * Returns the first lgsp token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lgsp token, or <code>null</code> if a matching lgsp token could not be found
	 */
	@Override
	public LGSPToken fetchByUuid_First(String uuid,
		OrderByComparator<LGSPToken> orderByComparator) {
		List<LGSPToken> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last lgsp token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lgsp token
	 * @throws NoSuchLGSPTokenException if a matching lgsp token could not be found
	 */
	@Override
	public LGSPToken findByUuid_Last(String uuid,
		OrderByComparator<LGSPToken> orderByComparator)
		throws NoSuchLGSPTokenException {
		LGSPToken lgspToken = fetchByUuid_Last(uuid, orderByComparator);

		if (lgspToken != null) {
			return lgspToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchLGSPTokenException(msg.toString());
	}

	/**
	 * Returns the last lgsp token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lgsp token, or <code>null</code> if a matching lgsp token could not be found
	 */
	@Override
	public LGSPToken fetchByUuid_Last(String uuid,
		OrderByComparator<LGSPToken> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<LGSPToken> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lgsp tokens before and after the current lgsp token in the ordered set where uuid = &#63;.
	 *
	 * @param tokenId the primary key of the current lgsp token
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lgsp token
	 * @throws NoSuchLGSPTokenException if a lgsp token with the primary key could not be found
	 */
	@Override
	public LGSPToken[] findByUuid_PrevAndNext(long tokenId, String uuid,
		OrderByComparator<LGSPToken> orderByComparator)
		throws NoSuchLGSPTokenException {
		LGSPToken lgspToken = findByPrimaryKey(tokenId);

		Session session = null;

		try {
			session = openSession();

			LGSPToken[] array = new LGSPTokenImpl[3];

			array[0] = getByUuid_PrevAndNext(session, lgspToken, uuid,
					orderByComparator, true);

			array[1] = lgspToken;

			array[2] = getByUuid_PrevAndNext(session, lgspToken, uuid,
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

	protected LGSPToken getByUuid_PrevAndNext(Session session,
		LGSPToken lgspToken, String uuid,
		OrderByComparator<LGSPToken> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LGSPTOKEN_WHERE);

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
			query.append(LGSPTokenModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(lgspToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LGSPToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lgsp tokens where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (LGSPToken lgspToken : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(lgspToken);
		}
	}

	/**
	 * Returns the number of lgsp tokens where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching lgsp tokens
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LGSPTOKEN_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "lgspToken.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "lgspToken.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(lgspToken.uuid IS NULL OR lgspToken.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_TYPE_EXPIRY =
		new FinderPath(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
			LGSPTokenModelImpl.FINDER_CACHE_ENABLED, LGSPTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_TYPE_EXPIRY",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_TYPE_EXPIRY =
		new FinderPath(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
			LGSPTokenModelImpl.FINDER_CACHE_ENABLED, LGSPTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_TYPE_EXPIRY",
			new String[] { String.class.getName() },
			LGSPTokenModelImpl.TOKENTYPE_COLUMN_BITMASK |
			LGSPTokenModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_TYPE_EXPIRY = new FinderPath(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
			LGSPTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_TYPE_EXPIRY",
			new String[] { String.class.getName() });

	/**
	 * Returns all the lgsp tokens where tokenType = &#63;.
	 *
	 * @param tokenType the token type
	 * @return the matching lgsp tokens
	 */
	@Override
	public List<LGSPToken> findByF_TYPE_EXPIRY(String tokenType) {
		return findByF_TYPE_EXPIRY(tokenType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lgsp tokens where tokenType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param tokenType the token type
	 * @param start the lower bound of the range of lgsp tokens
	 * @param end the upper bound of the range of lgsp tokens (not inclusive)
	 * @return the range of matching lgsp tokens
	 */
	@Override
	public List<LGSPToken> findByF_TYPE_EXPIRY(String tokenType, int start,
		int end) {
		return findByF_TYPE_EXPIRY(tokenType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lgsp tokens where tokenType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param tokenType the token type
	 * @param start the lower bound of the range of lgsp tokens
	 * @param end the upper bound of the range of lgsp tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lgsp tokens
	 */
	@Override
	public List<LGSPToken> findByF_TYPE_EXPIRY(String tokenType, int start,
		int end, OrderByComparator<LGSPToken> orderByComparator) {
		return findByF_TYPE_EXPIRY(tokenType, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the lgsp tokens where tokenType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param tokenType the token type
	 * @param start the lower bound of the range of lgsp tokens
	 * @param end the upper bound of the range of lgsp tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching lgsp tokens
	 */
	@Override
	public List<LGSPToken> findByF_TYPE_EXPIRY(String tokenType, int start,
		int end, OrderByComparator<LGSPToken> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_TYPE_EXPIRY;
			finderArgs = new Object[] { tokenType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_TYPE_EXPIRY;
			finderArgs = new Object[] { tokenType, start, end, orderByComparator };
		}

		List<LGSPToken> list = null;

		if (retrieveFromCache) {
			list = (List<LGSPToken>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LGSPToken lgspToken : list) {
					if (!Objects.equals(tokenType, lgspToken.getTokenType())) {
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

			query.append(_SQL_SELECT_LGSPTOKEN_WHERE);

			boolean bindTokenType = false;

			if (tokenType == null) {
				query.append(_FINDER_COLUMN_F_TYPE_EXPIRY_TOKENTYPE_1);
			}
			else if (tokenType.equals("")) {
				query.append(_FINDER_COLUMN_F_TYPE_EXPIRY_TOKENTYPE_3);
			}
			else {
				bindTokenType = true;

				query.append(_FINDER_COLUMN_F_TYPE_EXPIRY_TOKENTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LGSPTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTokenType) {
					qPos.add(tokenType);
				}

				if (!pagination) {
					list = (List<LGSPToken>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LGSPToken>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first lgsp token in the ordered set where tokenType = &#63;.
	 *
	 * @param tokenType the token type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lgsp token
	 * @throws NoSuchLGSPTokenException if a matching lgsp token could not be found
	 */
	@Override
	public LGSPToken findByF_TYPE_EXPIRY_First(String tokenType,
		OrderByComparator<LGSPToken> orderByComparator)
		throws NoSuchLGSPTokenException {
		LGSPToken lgspToken = fetchByF_TYPE_EXPIRY_First(tokenType,
				orderByComparator);

		if (lgspToken != null) {
			return lgspToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("tokenType=");
		msg.append(tokenType);

		msg.append("}");

		throw new NoSuchLGSPTokenException(msg.toString());
	}

	/**
	 * Returns the first lgsp token in the ordered set where tokenType = &#63;.
	 *
	 * @param tokenType the token type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lgsp token, or <code>null</code> if a matching lgsp token could not be found
	 */
	@Override
	public LGSPToken fetchByF_TYPE_EXPIRY_First(String tokenType,
		OrderByComparator<LGSPToken> orderByComparator) {
		List<LGSPToken> list = findByF_TYPE_EXPIRY(tokenType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last lgsp token in the ordered set where tokenType = &#63;.
	 *
	 * @param tokenType the token type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lgsp token
	 * @throws NoSuchLGSPTokenException if a matching lgsp token could not be found
	 */
	@Override
	public LGSPToken findByF_TYPE_EXPIRY_Last(String tokenType,
		OrderByComparator<LGSPToken> orderByComparator)
		throws NoSuchLGSPTokenException {
		LGSPToken lgspToken = fetchByF_TYPE_EXPIRY_Last(tokenType,
				orderByComparator);

		if (lgspToken != null) {
			return lgspToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("tokenType=");
		msg.append(tokenType);

		msg.append("}");

		throw new NoSuchLGSPTokenException(msg.toString());
	}

	/**
	 * Returns the last lgsp token in the ordered set where tokenType = &#63;.
	 *
	 * @param tokenType the token type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lgsp token, or <code>null</code> if a matching lgsp token could not be found
	 */
	@Override
	public LGSPToken fetchByF_TYPE_EXPIRY_Last(String tokenType,
		OrderByComparator<LGSPToken> orderByComparator) {
		int count = countByF_TYPE_EXPIRY(tokenType);

		if (count == 0) {
			return null;
		}

		List<LGSPToken> list = findByF_TYPE_EXPIRY(tokenType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lgsp tokens before and after the current lgsp token in the ordered set where tokenType = &#63;.
	 *
	 * @param tokenId the primary key of the current lgsp token
	 * @param tokenType the token type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lgsp token
	 * @throws NoSuchLGSPTokenException if a lgsp token with the primary key could not be found
	 */
	@Override
	public LGSPToken[] findByF_TYPE_EXPIRY_PrevAndNext(long tokenId,
		String tokenType, OrderByComparator<LGSPToken> orderByComparator)
		throws NoSuchLGSPTokenException {
		LGSPToken lgspToken = findByPrimaryKey(tokenId);

		Session session = null;

		try {
			session = openSession();

			LGSPToken[] array = new LGSPTokenImpl[3];

			array[0] = getByF_TYPE_EXPIRY_PrevAndNext(session, lgspToken,
					tokenType, orderByComparator, true);

			array[1] = lgspToken;

			array[2] = getByF_TYPE_EXPIRY_PrevAndNext(session, lgspToken,
					tokenType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LGSPToken getByF_TYPE_EXPIRY_PrevAndNext(Session session,
		LGSPToken lgspToken, String tokenType,
		OrderByComparator<LGSPToken> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LGSPTOKEN_WHERE);

		boolean bindTokenType = false;

		if (tokenType == null) {
			query.append(_FINDER_COLUMN_F_TYPE_EXPIRY_TOKENTYPE_1);
		}
		else if (tokenType.equals("")) {
			query.append(_FINDER_COLUMN_F_TYPE_EXPIRY_TOKENTYPE_3);
		}
		else {
			bindTokenType = true;

			query.append(_FINDER_COLUMN_F_TYPE_EXPIRY_TOKENTYPE_2);
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
			query.append(LGSPTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindTokenType) {
			qPos.add(tokenType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lgspToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LGSPToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lgsp tokens where tokenType = &#63; from the database.
	 *
	 * @param tokenType the token type
	 */
	@Override
	public void removeByF_TYPE_EXPIRY(String tokenType) {
		for (LGSPToken lgspToken : findByF_TYPE_EXPIRY(tokenType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lgspToken);
		}
	}

	/**
	 * Returns the number of lgsp tokens where tokenType = &#63;.
	 *
	 * @param tokenType the token type
	 * @return the number of matching lgsp tokens
	 */
	@Override
	public int countByF_TYPE_EXPIRY(String tokenType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_TYPE_EXPIRY;

		Object[] finderArgs = new Object[] { tokenType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LGSPTOKEN_WHERE);

			boolean bindTokenType = false;

			if (tokenType == null) {
				query.append(_FINDER_COLUMN_F_TYPE_EXPIRY_TOKENTYPE_1);
			}
			else if (tokenType.equals("")) {
				query.append(_FINDER_COLUMN_F_TYPE_EXPIRY_TOKENTYPE_3);
			}
			else {
				bindTokenType = true;

				query.append(_FINDER_COLUMN_F_TYPE_EXPIRY_TOKENTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTokenType) {
					qPos.add(tokenType);
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

	private static final String _FINDER_COLUMN_F_TYPE_EXPIRY_TOKENTYPE_1 = "lgspToken.tokenType IS NULL";
	private static final String _FINDER_COLUMN_F_TYPE_EXPIRY_TOKENTYPE_2 = "lgspToken.tokenType = ?";
	private static final String _FINDER_COLUMN_F_TYPE_EXPIRY_TOKENTYPE_3 = "(lgspToken.tokenType IS NULL OR lgspToken.tokenType = '')";

	public LGSPTokenPersistenceImpl() {
		setModelClass(LGSPToken.class);

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
	 * Caches the lgsp token in the entity cache if it is enabled.
	 *
	 * @param lgspToken the lgsp token
	 */
	@Override
	public void cacheResult(LGSPToken lgspToken) {
		entityCache.putResult(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
			LGSPTokenImpl.class, lgspToken.getPrimaryKey(), lgspToken);

		lgspToken.resetOriginalValues();
	}

	/**
	 * Caches the lgsp tokens in the entity cache if it is enabled.
	 *
	 * @param lgspTokens the lgsp tokens
	 */
	@Override
	public void cacheResult(List<LGSPToken> lgspTokens) {
		for (LGSPToken lgspToken : lgspTokens) {
			if (entityCache.getResult(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
						LGSPTokenImpl.class, lgspToken.getPrimaryKey()) == null) {
				cacheResult(lgspToken);
			}
			else {
				lgspToken.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all lgsp tokens.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LGSPTokenImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the lgsp token.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LGSPToken lgspToken) {
		entityCache.removeResult(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
			LGSPTokenImpl.class, lgspToken.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LGSPToken> lgspTokens) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LGSPToken lgspToken : lgspTokens) {
			entityCache.removeResult(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
				LGSPTokenImpl.class, lgspToken.getPrimaryKey());
		}
	}

	/**
	 * Creates a new lgsp token with the primary key. Does not add the lgsp token to the database.
	 *
	 * @param tokenId the primary key for the new lgsp token
	 * @return the new lgsp token
	 */
	@Override
	public LGSPToken create(long tokenId) {
		LGSPToken lgspToken = new LGSPTokenImpl();

		lgspToken.setNew(true);
		lgspToken.setPrimaryKey(tokenId);

		String uuid = PortalUUIDUtil.generate();

		lgspToken.setUuid(uuid);

		return lgspToken;
	}

	/**
	 * Removes the lgsp token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tokenId the primary key of the lgsp token
	 * @return the lgsp token that was removed
	 * @throws NoSuchLGSPTokenException if a lgsp token with the primary key could not be found
	 */
	@Override
	public LGSPToken remove(long tokenId) throws NoSuchLGSPTokenException {
		return remove((Serializable)tokenId);
	}

	/**
	 * Removes the lgsp token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the lgsp token
	 * @return the lgsp token that was removed
	 * @throws NoSuchLGSPTokenException if a lgsp token with the primary key could not be found
	 */
	@Override
	public LGSPToken remove(Serializable primaryKey)
		throws NoSuchLGSPTokenException {
		Session session = null;

		try {
			session = openSession();

			LGSPToken lgspToken = (LGSPToken)session.get(LGSPTokenImpl.class,
					primaryKey);

			if (lgspToken == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLGSPTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lgspToken);
		}
		catch (NoSuchLGSPTokenException nsee) {
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
	protected LGSPToken removeImpl(LGSPToken lgspToken) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lgspToken)) {
				lgspToken = (LGSPToken)session.get(LGSPTokenImpl.class,
						lgspToken.getPrimaryKeyObj());
			}

			if (lgspToken != null) {
				session.delete(lgspToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lgspToken != null) {
			clearCache(lgspToken);
		}

		return lgspToken;
	}

	@Override
	public LGSPToken updateImpl(LGSPToken lgspToken) {
		boolean isNew = lgspToken.isNew();

		if (!(lgspToken instanceof LGSPTokenModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(lgspToken.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(lgspToken);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in lgspToken proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LGSPToken implementation " +
				lgspToken.getClass());
		}

		LGSPTokenModelImpl lgspTokenModelImpl = (LGSPTokenModelImpl)lgspToken;

		if (Validator.isNull(lgspToken.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			lgspToken.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (lgspToken.getCreateDate() == null)) {
			if (serviceContext == null) {
				lgspToken.setCreateDate(now);
			}
			else {
				lgspToken.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!lgspTokenModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				lgspToken.setModifiedDate(now);
			}
			else {
				lgspToken.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (lgspToken.isNew()) {
				session.save(lgspToken);

				lgspToken.setNew(false);
			}
			else {
				lgspToken = (LGSPToken)session.merge(lgspToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LGSPTokenModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { lgspTokenModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { lgspTokenModelImpl.getTokenType() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_TYPE_EXPIRY, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_TYPE_EXPIRY,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((lgspTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lgspTokenModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { lgspTokenModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((lgspTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_TYPE_EXPIRY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lgspTokenModelImpl.getOriginalTokenType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_TYPE_EXPIRY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_TYPE_EXPIRY,
					args);

				args = new Object[] { lgspTokenModelImpl.getTokenType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_TYPE_EXPIRY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_TYPE_EXPIRY,
					args);
			}
		}

		entityCache.putResult(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
			LGSPTokenImpl.class, lgspToken.getPrimaryKey(), lgspToken, false);

		lgspToken.resetOriginalValues();

		return lgspToken;
	}

	/**
	 * Returns the lgsp token with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the lgsp token
	 * @return the lgsp token
	 * @throws NoSuchLGSPTokenException if a lgsp token with the primary key could not be found
	 */
	@Override
	public LGSPToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLGSPTokenException {
		LGSPToken lgspToken = fetchByPrimaryKey(primaryKey);

		if (lgspToken == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLGSPTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lgspToken;
	}

	/**
	 * Returns the lgsp token with the primary key or throws a {@link NoSuchLGSPTokenException} if it could not be found.
	 *
	 * @param tokenId the primary key of the lgsp token
	 * @return the lgsp token
	 * @throws NoSuchLGSPTokenException if a lgsp token with the primary key could not be found
	 */
	@Override
	public LGSPToken findByPrimaryKey(long tokenId)
		throws NoSuchLGSPTokenException {
		return findByPrimaryKey((Serializable)tokenId);
	}

	/**
	 * Returns the lgsp token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the lgsp token
	 * @return the lgsp token, or <code>null</code> if a lgsp token with the primary key could not be found
	 */
	@Override
	public LGSPToken fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
				LGSPTokenImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LGSPToken lgspToken = (LGSPToken)serializable;

		if (lgspToken == null) {
			Session session = null;

			try {
				session = openSession();

				lgspToken = (LGSPToken)session.get(LGSPTokenImpl.class,
						primaryKey);

				if (lgspToken != null) {
					cacheResult(lgspToken);
				}
				else {
					entityCache.putResult(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
						LGSPTokenImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
					LGSPTokenImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lgspToken;
	}

	/**
	 * Returns the lgsp token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param tokenId the primary key of the lgsp token
	 * @return the lgsp token, or <code>null</code> if a lgsp token with the primary key could not be found
	 */
	@Override
	public LGSPToken fetchByPrimaryKey(long tokenId) {
		return fetchByPrimaryKey((Serializable)tokenId);
	}

	@Override
	public Map<Serializable, LGSPToken> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LGSPToken> map = new HashMap<Serializable, LGSPToken>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LGSPToken lgspToken = fetchByPrimaryKey(primaryKey);

			if (lgspToken != null) {
				map.put(primaryKey, lgspToken);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
					LGSPTokenImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LGSPToken)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LGSPTOKEN_WHERE_PKS_IN);

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

			for (LGSPToken lgspToken : (List<LGSPToken>)q.list()) {
				map.put(lgspToken.getPrimaryKeyObj(), lgspToken);

				cacheResult(lgspToken);

				uncachedPrimaryKeys.remove(lgspToken.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LGSPTokenModelImpl.ENTITY_CACHE_ENABLED,
					LGSPTokenImpl.class, primaryKey, nullModel);
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
	 * Returns all the lgsp tokens.
	 *
	 * @return the lgsp tokens
	 */
	@Override
	public List<LGSPToken> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lgsp tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of lgsp tokens
	 * @param end the upper bound of the range of lgsp tokens (not inclusive)
	 * @return the range of lgsp tokens
	 */
	@Override
	public List<LGSPToken> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the lgsp tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of lgsp tokens
	 * @param end the upper bound of the range of lgsp tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of lgsp tokens
	 */
	@Override
	public List<LGSPToken> findAll(int start, int end,
		OrderByComparator<LGSPToken> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the lgsp tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of lgsp tokens
	 * @param end the upper bound of the range of lgsp tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of lgsp tokens
	 */
	@Override
	public List<LGSPToken> findAll(int start, int end,
		OrderByComparator<LGSPToken> orderByComparator,
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

		List<LGSPToken> list = null;

		if (retrieveFromCache) {
			list = (List<LGSPToken>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LGSPTOKEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LGSPTOKEN;

				if (pagination) {
					sql = sql.concat(LGSPTokenModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LGSPToken>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LGSPToken>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the lgsp tokens from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LGSPToken lgspToken : findAll()) {
			remove(lgspToken);
		}
	}

	/**
	 * Returns the number of lgsp tokens.
	 *
	 * @return the number of lgsp tokens
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LGSPTOKEN);

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
		return LGSPTokenModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the lgsp token persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LGSPTokenImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LGSPTOKEN = "SELECT lgspToken FROM LGSPToken lgspToken";
	private static final String _SQL_SELECT_LGSPTOKEN_WHERE_PKS_IN = "SELECT lgspToken FROM LGSPToken lgspToken WHERE tokenId IN (";
	private static final String _SQL_SELECT_LGSPTOKEN_WHERE = "SELECT lgspToken FROM LGSPToken lgspToken WHERE ";
	private static final String _SQL_COUNT_LGSPTOKEN = "SELECT COUNT(lgspToken) FROM LGSPToken lgspToken";
	private static final String _SQL_COUNT_LGSPTOKEN_WHERE = "SELECT COUNT(lgspToken) FROM LGSPToken lgspToken WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lgspToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LGSPToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LGSPToken exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LGSPTokenPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}