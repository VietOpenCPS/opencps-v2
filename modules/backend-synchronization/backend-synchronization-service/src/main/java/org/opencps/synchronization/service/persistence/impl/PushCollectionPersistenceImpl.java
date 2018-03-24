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

package org.opencps.synchronization.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.synchronization.exception.NoSuchPushCollectionException;
import org.opencps.synchronization.model.PushCollection;
import org.opencps.synchronization.model.impl.PushCollectionImpl;
import org.opencps.synchronization.model.impl.PushCollectionModelImpl;
import org.opencps.synchronization.service.persistence.PushCollectionPersistence;

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
 * The persistence implementation for the push collection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see PushCollectionPersistence
 * @see org.opencps.synchronization.service.persistence.PushCollectionUtil
 * @generated
 */
@ProviderType
public class PushCollectionPersistenceImpl extends BasePersistenceImpl<PushCollection>
	implements PushCollectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PushCollectionUtil} to access the push collection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PushCollectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED,
			PushCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED,
			PushCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED,
			PushCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED,
			PushCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PushCollectionModelImpl.UUID_COLUMN_BITMASK |
			PushCollectionModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the push collections where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching push collections
	 */
	@Override
	public List<PushCollection> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push collections where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of push collections
	 * @param end the upper bound of the range of push collections (not inclusive)
	 * @return the range of matching push collections
	 */
	@Override
	public List<PushCollection> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the push collections where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of push collections
	 * @param end the upper bound of the range of push collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching push collections
	 */
	@Override
	public List<PushCollection> findByUuid(String uuid, int start, int end,
		OrderByComparator<PushCollection> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push collections where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of push collections
	 * @param end the upper bound of the range of push collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching push collections
	 */
	@Override
	public List<PushCollection> findByUuid(String uuid, int start, int end,
		OrderByComparator<PushCollection> orderByComparator,
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

		List<PushCollection> list = null;

		if (retrieveFromCache) {
			list = (List<PushCollection>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PushCollection pushCollection : list) {
					if (!Objects.equals(uuid, pushCollection.getUuid())) {
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

			query.append(_SQL_SELECT_PUSHCOLLECTION_WHERE);

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
				query.append(PushCollectionModelImpl.ORDER_BY_JPQL);
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
					list = (List<PushCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushCollection>)QueryUtil.list(q,
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
	 * Returns the first push collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push collection
	 * @throws NoSuchPushCollectionException if a matching push collection could not be found
	 */
	@Override
	public PushCollection findByUuid_First(String uuid,
		OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException {
		PushCollection pushCollection = fetchByUuid_First(uuid,
				orderByComparator);

		if (pushCollection != null) {
			return pushCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushCollectionException(msg.toString());
	}

	/**
	 * Returns the first push collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push collection, or <code>null</code> if a matching push collection could not be found
	 */
	@Override
	public PushCollection fetchByUuid_First(String uuid,
		OrderByComparator<PushCollection> orderByComparator) {
		List<PushCollection> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last push collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push collection
	 * @throws NoSuchPushCollectionException if a matching push collection could not be found
	 */
	@Override
	public PushCollection findByUuid_Last(String uuid,
		OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException {
		PushCollection pushCollection = fetchByUuid_Last(uuid, orderByComparator);

		if (pushCollection != null) {
			return pushCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushCollectionException(msg.toString());
	}

	/**
	 * Returns the last push collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push collection, or <code>null</code> if a matching push collection could not be found
	 */
	@Override
	public PushCollection fetchByUuid_Last(String uuid,
		OrderByComparator<PushCollection> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PushCollection> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the push collections before and after the current push collection in the ordered set where uuid = &#63;.
	 *
	 * @param pushCollectionId the primary key of the current push collection
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next push collection
	 * @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	 */
	@Override
	public PushCollection[] findByUuid_PrevAndNext(long pushCollectionId,
		String uuid, OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException {
		PushCollection pushCollection = findByPrimaryKey(pushCollectionId);

		Session session = null;

		try {
			session = openSession();

			PushCollection[] array = new PushCollectionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, pushCollection, uuid,
					orderByComparator, true);

			array[1] = pushCollection;

			array[2] = getByUuid_PrevAndNext(session, pushCollection, uuid,
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

	protected PushCollection getByUuid_PrevAndNext(Session session,
		PushCollection pushCollection, String uuid,
		OrderByComparator<PushCollection> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PUSHCOLLECTION_WHERE);

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
			query.append(PushCollectionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(pushCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PushCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the push collections where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PushCollection pushCollection : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pushCollection);
		}
	}

	/**
	 * Returns the number of push collections where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching push collections
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PUSHCOLLECTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "pushCollection.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "pushCollection.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(pushCollection.uuid IS NULL OR pushCollection.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED,
			PushCollectionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PushCollectionModelImpl.UUID_COLUMN_BITMASK |
			PushCollectionModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the push collection where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPushCollectionException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching push collection
	 * @throws NoSuchPushCollectionException if a matching push collection could not be found
	 */
	@Override
	public PushCollection findByUUID_G(String uuid, long groupId)
		throws NoSuchPushCollectionException {
		PushCollection pushCollection = fetchByUUID_G(uuid, groupId);

		if (pushCollection == null) {
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

			throw new NoSuchPushCollectionException(msg.toString());
		}

		return pushCollection;
	}

	/**
	 * Returns the push collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching push collection, or <code>null</code> if a matching push collection could not be found
	 */
	@Override
	public PushCollection fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the push collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching push collection, or <code>null</code> if a matching push collection could not be found
	 */
	@Override
	public PushCollection fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PushCollection) {
			PushCollection pushCollection = (PushCollection)result;

			if (!Objects.equals(uuid, pushCollection.getUuid()) ||
					(groupId != pushCollection.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PUSHCOLLECTION_WHERE);

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

				List<PushCollection> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					PushCollection pushCollection = list.get(0);

					result = pushCollection;

					cacheResult(pushCollection);

					if ((pushCollection.getUuid() == null) ||
							!pushCollection.getUuid().equals(uuid) ||
							(pushCollection.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, pushCollection);
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
			return (PushCollection)result;
		}
	}

	/**
	 * Removes the push collection where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the push collection that was removed
	 */
	@Override
	public PushCollection removeByUUID_G(String uuid, long groupId)
		throws NoSuchPushCollectionException {
		PushCollection pushCollection = findByUUID_G(uuid, groupId);

		return remove(pushCollection);
	}

	/**
	 * Returns the number of push collections where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching push collections
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PUSHCOLLECTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "pushCollection.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "pushCollection.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(pushCollection.uuid IS NULL OR pushCollection.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "pushCollection.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED,
			PushCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED,
			PushCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PushCollectionModelImpl.UUID_COLUMN_BITMASK |
			PushCollectionModelImpl.COMPANYID_COLUMN_BITMASK |
			PushCollectionModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the push collections where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching push collections
	 */
	@Override
	public List<PushCollection> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push collections where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of push collections
	 * @param end the upper bound of the range of push collections (not inclusive)
	 * @return the range of matching push collections
	 */
	@Override
	public List<PushCollection> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the push collections where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of push collections
	 * @param end the upper bound of the range of push collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching push collections
	 */
	@Override
	public List<PushCollection> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PushCollection> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push collections where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of push collections
	 * @param end the upper bound of the range of push collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching push collections
	 */
	@Override
	public List<PushCollection> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<PushCollection> orderByComparator,
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

		List<PushCollection> list = null;

		if (retrieveFromCache) {
			list = (List<PushCollection>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PushCollection pushCollection : list) {
					if (!Objects.equals(uuid, pushCollection.getUuid()) ||
							(companyId != pushCollection.getCompanyId())) {
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

			query.append(_SQL_SELECT_PUSHCOLLECTION_WHERE);

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
				query.append(PushCollectionModelImpl.ORDER_BY_JPQL);
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
					list = (List<PushCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushCollection>)QueryUtil.list(q,
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
	 * Returns the first push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push collection
	 * @throws NoSuchPushCollectionException if a matching push collection could not be found
	 */
	@Override
	public PushCollection findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException {
		PushCollection pushCollection = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (pushCollection != null) {
			return pushCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushCollectionException(msg.toString());
	}

	/**
	 * Returns the first push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push collection, or <code>null</code> if a matching push collection could not be found
	 */
	@Override
	public PushCollection fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PushCollection> orderByComparator) {
		List<PushCollection> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push collection
	 * @throws NoSuchPushCollectionException if a matching push collection could not be found
	 */
	@Override
	public PushCollection findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException {
		PushCollection pushCollection = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (pushCollection != null) {
			return pushCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushCollectionException(msg.toString());
	}

	/**
	 * Returns the last push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push collection, or <code>null</code> if a matching push collection could not be found
	 */
	@Override
	public PushCollection fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PushCollection> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<PushCollection> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the push collections before and after the current push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param pushCollectionId the primary key of the current push collection
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next push collection
	 * @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	 */
	@Override
	public PushCollection[] findByUuid_C_PrevAndNext(long pushCollectionId,
		String uuid, long companyId,
		OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException {
		PushCollection pushCollection = findByPrimaryKey(pushCollectionId);

		Session session = null;

		try {
			session = openSession();

			PushCollection[] array = new PushCollectionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, pushCollection, uuid,
					companyId, orderByComparator, true);

			array[1] = pushCollection;

			array[2] = getByUuid_C_PrevAndNext(session, pushCollection, uuid,
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

	protected PushCollection getByUuid_C_PrevAndNext(Session session,
		PushCollection pushCollection, String uuid, long companyId,
		OrderByComparator<PushCollection> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PUSHCOLLECTION_WHERE);

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
			query.append(PushCollectionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(pushCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PushCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the push collections where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (PushCollection pushCollection : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pushCollection);
		}
	}

	/**
	 * Returns the number of push collections where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching push collections
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PUSHCOLLECTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "pushCollection.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "pushCollection.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(pushCollection.uuid IS NULL OR pushCollection.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "pushCollection.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_METHOD = new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED,
			PushCollectionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_collectionCode_Method",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			PushCollectionModelImpl.GROUPID_COLUMN_BITMASK |
			PushCollectionModelImpl.COLLECTIONCODE_COLUMN_BITMASK |
			PushCollectionModelImpl.METHOD_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_METHOD = new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_collectionCode_Method",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the push collection where groupId = &#63; and collectionCode = &#63; and method = &#63; or throws a {@link NoSuchPushCollectionException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param method the method
	 * @return the matching push collection
	 * @throws NoSuchPushCollectionException if a matching push collection could not be found
	 */
	@Override
	public PushCollection findByF_collectionCode_Method(long groupId,
		String collectionCode, String method)
		throws NoSuchPushCollectionException {
		PushCollection pushCollection = fetchByF_collectionCode_Method(groupId,
				collectionCode, method);

		if (pushCollection == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", collectionCode=");
			msg.append(collectionCode);

			msg.append(", method=");
			msg.append(method);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPushCollectionException(msg.toString());
		}

		return pushCollection;
	}

	/**
	 * Returns the push collection where groupId = &#63; and collectionCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param method the method
	 * @return the matching push collection, or <code>null</code> if a matching push collection could not be found
	 */
	@Override
	public PushCollection fetchByF_collectionCode_Method(long groupId,
		String collectionCode, String method) {
		return fetchByF_collectionCode_Method(groupId, collectionCode, method,
			true);
	}

	/**
	 * Returns the push collection where groupId = &#63; and collectionCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param method the method
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching push collection, or <code>null</code> if a matching push collection could not be found
	 */
	@Override
	public PushCollection fetchByF_collectionCode_Method(long groupId,
		String collectionCode, String method, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, collectionCode, method };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_METHOD,
					finderArgs, this);
		}

		if (result instanceof PushCollection) {
			PushCollection pushCollection = (PushCollection)result;

			if ((groupId != pushCollection.getGroupId()) ||
					!Objects.equals(collectionCode,
						pushCollection.getCollectionCode()) ||
					!Objects.equals(method, pushCollection.getMethod())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_PUSHCOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_F_COLLECTIONCODE_METHOD_GROUPID_2);

			boolean bindCollectionCode = false;

			if (collectionCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_METHOD_COLLECTIONCODE_1);
			}
			else if (collectionCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_METHOD_COLLECTIONCODE_3);
			}
			else {
				bindCollectionCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_METHOD_COLLECTIONCODE_2);
			}

			boolean bindMethod = false;

			if (method == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_METHOD_METHOD_1);
			}
			else if (method.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_METHOD_METHOD_3);
			}
			else {
				bindMethod = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_METHOD_METHOD_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindCollectionCode) {
					qPos.add(collectionCode);
				}

				if (bindMethod) {
					qPos.add(method);
				}

				List<PushCollection> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_METHOD,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"PushCollectionPersistenceImpl.fetchByF_collectionCode_Method(long, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PushCollection pushCollection = list.get(0);

					result = pushCollection;

					cacheResult(pushCollection);

					if ((pushCollection.getGroupId() != groupId) ||
							(pushCollection.getCollectionCode() == null) ||
							!pushCollection.getCollectionCode()
											   .equals(collectionCode) ||
							(pushCollection.getMethod() == null) ||
							!pushCollection.getMethod().equals(method)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_METHOD,
							finderArgs, pushCollection);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_METHOD,
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
			return (PushCollection)result;
		}
	}

	/**
	 * Removes the push collection where groupId = &#63; and collectionCode = &#63; and method = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param method the method
	 * @return the push collection that was removed
	 */
	@Override
	public PushCollection removeByF_collectionCode_Method(long groupId,
		String collectionCode, String method)
		throws NoSuchPushCollectionException {
		PushCollection pushCollection = findByF_collectionCode_Method(groupId,
				collectionCode, method);

		return remove(pushCollection);
	}

	/**
	 * Returns the number of push collections where groupId = &#63; and collectionCode = &#63; and method = &#63;.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param method the method
	 * @return the number of matching push collections
	 */
	@Override
	public int countByF_collectionCode_Method(long groupId,
		String collectionCode, String method) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_METHOD;

		Object[] finderArgs = new Object[] { groupId, collectionCode, method };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PUSHCOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_F_COLLECTIONCODE_METHOD_GROUPID_2);

			boolean bindCollectionCode = false;

			if (collectionCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_METHOD_COLLECTIONCODE_1);
			}
			else if (collectionCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_METHOD_COLLECTIONCODE_3);
			}
			else {
				bindCollectionCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_METHOD_COLLECTIONCODE_2);
			}

			boolean bindMethod = false;

			if (method == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_METHOD_METHOD_1);
			}
			else if (method.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_METHOD_METHOD_3);
			}
			else {
				bindMethod = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_METHOD_METHOD_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindCollectionCode) {
					qPos.add(collectionCode);
				}

				if (bindMethod) {
					qPos.add(method);
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

	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_METHOD_GROUPID_2 =
		"pushCollection.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_METHOD_COLLECTIONCODE_1 =
		"pushCollection.collectionCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_METHOD_COLLECTIONCODE_2 =
		"pushCollection.collectionCode = ? AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_METHOD_COLLECTIONCODE_3 =
		"(pushCollection.collectionCode IS NULL OR pushCollection.collectionCode = '') AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_METHOD_METHOD_1 = "pushCollection.method IS NULL";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_METHOD_METHOD_2 = "pushCollection.method = ?";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_METHOD_METHOD_3 = "(pushCollection.method IS NULL OR pushCollection.method = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GROUPID_SERVERNO =
		new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED,
			PushCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_groupId_serverNo",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO =
		new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED,
			PushCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_groupId_serverNo",
			new String[] { Long.class.getName(), String.class.getName() },
			PushCollectionModelImpl.GROUPID_COLUMN_BITMASK |
			PushCollectionModelImpl.SERVERNO_COLUMN_BITMASK |
			PushCollectionModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO = new FinderPath(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_groupId_serverNo",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the push collections where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @return the matching push collections
	 */
	@Override
	public List<PushCollection> findByF_groupId_serverNo(long groupId,
		String serverNo) {
		return findByF_groupId_serverNo(groupId, serverNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push collections where groupId = &#63; and serverNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param start the lower bound of the range of push collections
	 * @param end the upper bound of the range of push collections (not inclusive)
	 * @return the range of matching push collections
	 */
	@Override
	public List<PushCollection> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end) {
		return findByF_groupId_serverNo(groupId, serverNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the push collections where groupId = &#63; and serverNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param start the lower bound of the range of push collections
	 * @param end the upper bound of the range of push collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching push collections
	 */
	@Override
	public List<PushCollection> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end,
		OrderByComparator<PushCollection> orderByComparator) {
		return findByF_groupId_serverNo(groupId, serverNo, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push collections where groupId = &#63; and serverNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param start the lower bound of the range of push collections
	 * @param end the upper bound of the range of push collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching push collections
	 */
	@Override
	public List<PushCollection> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end,
		OrderByComparator<PushCollection> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO;
			finderArgs = new Object[] { groupId, serverNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GROUPID_SERVERNO;
			finderArgs = new Object[] {
					groupId, serverNo,
					
					start, end, orderByComparator
				};
		}

		List<PushCollection> list = null;

		if (retrieveFromCache) {
			list = (List<PushCollection>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PushCollection pushCollection : list) {
					if ((groupId != pushCollection.getGroupId()) ||
							!Objects.equals(serverNo,
								pushCollection.getServerNo())) {
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

			query.append(_SQL_SELECT_PUSHCOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_GROUPID_2);

			boolean bindServerNo = false;

			if (serverNo == null) {
				query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_1);
			}
			else if (serverNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_3);
			}
			else {
				bindServerNo = true;

				query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PushCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServerNo) {
					qPos.add(serverNo);
				}

				if (!pagination) {
					list = (List<PushCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushCollection>)QueryUtil.list(q,
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
	 * Returns the first push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push collection
	 * @throws NoSuchPushCollectionException if a matching push collection could not be found
	 */
	@Override
	public PushCollection findByF_groupId_serverNo_First(long groupId,
		String serverNo, OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException {
		PushCollection pushCollection = fetchByF_groupId_serverNo_First(groupId,
				serverNo, orderByComparator);

		if (pushCollection != null) {
			return pushCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serverNo=");
		msg.append(serverNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushCollectionException(msg.toString());
	}

	/**
	 * Returns the first push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push collection, or <code>null</code> if a matching push collection could not be found
	 */
	@Override
	public PushCollection fetchByF_groupId_serverNo_First(long groupId,
		String serverNo, OrderByComparator<PushCollection> orderByComparator) {
		List<PushCollection> list = findByF_groupId_serverNo(groupId, serverNo,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push collection
	 * @throws NoSuchPushCollectionException if a matching push collection could not be found
	 */
	@Override
	public PushCollection findByF_groupId_serverNo_Last(long groupId,
		String serverNo, OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException {
		PushCollection pushCollection = fetchByF_groupId_serverNo_Last(groupId,
				serverNo, orderByComparator);

		if (pushCollection != null) {
			return pushCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serverNo=");
		msg.append(serverNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushCollectionException(msg.toString());
	}

	/**
	 * Returns the last push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push collection, or <code>null</code> if a matching push collection could not be found
	 */
	@Override
	public PushCollection fetchByF_groupId_serverNo_Last(long groupId,
		String serverNo, OrderByComparator<PushCollection> orderByComparator) {
		int count = countByF_groupId_serverNo(groupId, serverNo);

		if (count == 0) {
			return null;
		}

		List<PushCollection> list = findByF_groupId_serverNo(groupId, serverNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the push collections before and after the current push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param pushCollectionId the primary key of the current push collection
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next push collection
	 * @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	 */
	@Override
	public PushCollection[] findByF_groupId_serverNo_PrevAndNext(
		long pushCollectionId, long groupId, String serverNo,
		OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException {
		PushCollection pushCollection = findByPrimaryKey(pushCollectionId);

		Session session = null;

		try {
			session = openSession();

			PushCollection[] array = new PushCollectionImpl[3];

			array[0] = getByF_groupId_serverNo_PrevAndNext(session,
					pushCollection, groupId, serverNo, orderByComparator, true);

			array[1] = pushCollection;

			array[2] = getByF_groupId_serverNo_PrevAndNext(session,
					pushCollection, groupId, serverNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PushCollection getByF_groupId_serverNo_PrevAndNext(
		Session session, PushCollection pushCollection, long groupId,
		String serverNo, OrderByComparator<PushCollection> orderByComparator,
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

		query.append(_SQL_SELECT_PUSHCOLLECTION_WHERE);

		query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_GROUPID_2);

		boolean bindServerNo = false;

		if (serverNo == null) {
			query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_1);
		}
		else if (serverNo.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_3);
		}
		else {
			bindServerNo = true;

			query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_2);
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
			query.append(PushCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindServerNo) {
			qPos.add(serverNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(pushCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PushCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the push collections where groupId = &#63; and serverNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 */
	@Override
	public void removeByF_groupId_serverNo(long groupId, String serverNo) {
		for (PushCollection pushCollection : findByF_groupId_serverNo(groupId,
				serverNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pushCollection);
		}
	}

	/**
	 * Returns the number of push collections where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @return the number of matching push collections
	 */
	@Override
	public int countByF_groupId_serverNo(long groupId, String serverNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO;

		Object[] finderArgs = new Object[] { groupId, serverNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PUSHCOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_GROUPID_2);

			boolean bindServerNo = false;

			if (serverNo == null) {
				query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_1);
			}
			else if (serverNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_3);
			}
			else {
				bindServerNo = true;

				query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServerNo) {
					qPos.add(serverNo);
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

	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_GROUPID_2 = "pushCollection.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_1 = "pushCollection.serverNo IS NULL";
	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_2 = "pushCollection.serverNo = ?";
	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_3 = "(pushCollection.serverNo IS NULL OR pushCollection.serverNo = '')";

	public PushCollectionPersistenceImpl() {
		setModelClass(PushCollection.class);
	}

	/**
	 * Caches the push collection in the entity cache if it is enabled.
	 *
	 * @param pushCollection the push collection
	 */
	@Override
	public void cacheResult(PushCollection pushCollection) {
		entityCache.putResult(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionImpl.class, pushCollection.getPrimaryKey(),
			pushCollection);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { pushCollection.getUuid(), pushCollection.getGroupId() },
			pushCollection);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_METHOD,
			new Object[] {
				pushCollection.getGroupId(), pushCollection.getCollectionCode(),
				pushCollection.getMethod()
			}, pushCollection);

		pushCollection.resetOriginalValues();
	}

	/**
	 * Caches the push collections in the entity cache if it is enabled.
	 *
	 * @param pushCollections the push collections
	 */
	@Override
	public void cacheResult(List<PushCollection> pushCollections) {
		for (PushCollection pushCollection : pushCollections) {
			if (entityCache.getResult(
						PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
						PushCollectionImpl.class, pushCollection.getPrimaryKey()) == null) {
				cacheResult(pushCollection);
			}
			else {
				pushCollection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all push collections.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PushCollectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the push collection.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PushCollection pushCollection) {
		entityCache.removeResult(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionImpl.class, pushCollection.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PushCollectionModelImpl)pushCollection, true);
	}

	@Override
	public void clearCache(List<PushCollection> pushCollections) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PushCollection pushCollection : pushCollections) {
			entityCache.removeResult(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
				PushCollectionImpl.class, pushCollection.getPrimaryKey());

			clearUniqueFindersCache((PushCollectionModelImpl)pushCollection,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		PushCollectionModelImpl pushCollectionModelImpl) {
		Object[] args = new Object[] {
				pushCollectionModelImpl.getUuid(),
				pushCollectionModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			pushCollectionModelImpl, false);

		args = new Object[] {
				pushCollectionModelImpl.getGroupId(),
				pushCollectionModelImpl.getCollectionCode(),
				pushCollectionModelImpl.getMethod()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_METHOD,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_METHOD,
			args, pushCollectionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PushCollectionModelImpl pushCollectionModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					pushCollectionModelImpl.getUuid(),
					pushCollectionModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((pushCollectionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					pushCollectionModelImpl.getOriginalUuid(),
					pushCollectionModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					pushCollectionModelImpl.getGroupId(),
					pushCollectionModelImpl.getCollectionCode(),
					pushCollectionModelImpl.getMethod()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_METHOD,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_METHOD,
				args);
		}

		if ((pushCollectionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_METHOD.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					pushCollectionModelImpl.getOriginalGroupId(),
					pushCollectionModelImpl.getOriginalCollectionCode(),
					pushCollectionModelImpl.getOriginalMethod()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_METHOD,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_METHOD,
				args);
		}
	}

	/**
	 * Creates a new push collection with the primary key. Does not add the push collection to the database.
	 *
	 * @param pushCollectionId the primary key for the new push collection
	 * @return the new push collection
	 */
	@Override
	public PushCollection create(long pushCollectionId) {
		PushCollection pushCollection = new PushCollectionImpl();

		pushCollection.setNew(true);
		pushCollection.setPrimaryKey(pushCollectionId);

		String uuid = PortalUUIDUtil.generate();

		pushCollection.setUuid(uuid);

		pushCollection.setCompanyId(companyProvider.getCompanyId());

		return pushCollection;
	}

	/**
	 * Removes the push collection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pushCollectionId the primary key of the push collection
	 * @return the push collection that was removed
	 * @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	 */
	@Override
	public PushCollection remove(long pushCollectionId)
		throws NoSuchPushCollectionException {
		return remove((Serializable)pushCollectionId);
	}

	/**
	 * Removes the push collection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the push collection
	 * @return the push collection that was removed
	 * @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	 */
	@Override
	public PushCollection remove(Serializable primaryKey)
		throws NoSuchPushCollectionException {
		Session session = null;

		try {
			session = openSession();

			PushCollection pushCollection = (PushCollection)session.get(PushCollectionImpl.class,
					primaryKey);

			if (pushCollection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPushCollectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(pushCollection);
		}
		catch (NoSuchPushCollectionException nsee) {
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
	protected PushCollection removeImpl(PushCollection pushCollection) {
		pushCollection = toUnwrappedModel(pushCollection);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(pushCollection)) {
				pushCollection = (PushCollection)session.get(PushCollectionImpl.class,
						pushCollection.getPrimaryKeyObj());
			}

			if (pushCollection != null) {
				session.delete(pushCollection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (pushCollection != null) {
			clearCache(pushCollection);
		}

		return pushCollection;
	}

	@Override
	public PushCollection updateImpl(PushCollection pushCollection) {
		pushCollection = toUnwrappedModel(pushCollection);

		boolean isNew = pushCollection.isNew();

		PushCollectionModelImpl pushCollectionModelImpl = (PushCollectionModelImpl)pushCollection;

		if (Validator.isNull(pushCollection.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			pushCollection.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (pushCollection.getCreateDate() == null)) {
			if (serviceContext == null) {
				pushCollection.setCreateDate(now);
			}
			else {
				pushCollection.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!pushCollectionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				pushCollection.setModifiedDate(now);
			}
			else {
				pushCollection.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (pushCollection.isNew()) {
				session.save(pushCollection);

				pushCollection.setNew(false);
			}
			else {
				pushCollection = (PushCollection)session.merge(pushCollection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PushCollectionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((pushCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pushCollectionModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { pushCollectionModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((pushCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pushCollectionModelImpl.getOriginalUuid(),
						pushCollectionModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						pushCollectionModelImpl.getUuid(),
						pushCollectionModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((pushCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pushCollectionModelImpl.getOriginalGroupId(),
						pushCollectionModelImpl.getOriginalServerNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO,
					args);

				args = new Object[] {
						pushCollectionModelImpl.getGroupId(),
						pushCollectionModelImpl.getServerNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO,
					args);
			}
		}

		entityCache.putResult(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
			PushCollectionImpl.class, pushCollection.getPrimaryKey(),
			pushCollection, false);

		clearUniqueFindersCache(pushCollectionModelImpl, false);
		cacheUniqueFindersCache(pushCollectionModelImpl);

		pushCollection.resetOriginalValues();

		return pushCollection;
	}

	protected PushCollection toUnwrappedModel(PushCollection pushCollection) {
		if (pushCollection instanceof PushCollectionImpl) {
			return pushCollection;
		}

		PushCollectionImpl pushCollectionImpl = new PushCollectionImpl();

		pushCollectionImpl.setNew(pushCollection.isNew());
		pushCollectionImpl.setPrimaryKey(pushCollection.getPrimaryKey());

		pushCollectionImpl.setUuid(pushCollection.getUuid());
		pushCollectionImpl.setPushCollectionId(pushCollection.getPushCollectionId());
		pushCollectionImpl.setGroupId(pushCollection.getGroupId());
		pushCollectionImpl.setCompanyId(pushCollection.getCompanyId());
		pushCollectionImpl.setUserId(pushCollection.getUserId());
		pushCollectionImpl.setUserName(pushCollection.getUserName());
		pushCollectionImpl.setCreateDate(pushCollection.getCreateDate());
		pushCollectionImpl.setModifiedDate(pushCollection.getModifiedDate());
		pushCollectionImpl.setServerNo(pushCollection.getServerNo());
		pushCollectionImpl.setCollectionCode(pushCollection.getCollectionCode());
		pushCollectionImpl.setCollectionName(pushCollection.getCollectionName());
		pushCollectionImpl.setCollectionNameEN(pushCollection.getCollectionNameEN());
		pushCollectionImpl.setDescription(pushCollection.getDescription());
		pushCollectionImpl.setDataForm(pushCollection.getDataForm());
		pushCollectionImpl.setMethod(pushCollection.getMethod());

		return pushCollectionImpl;
	}

	/**
	 * Returns the push collection with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the push collection
	 * @return the push collection
	 * @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	 */
	@Override
	public PushCollection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPushCollectionException {
		PushCollection pushCollection = fetchByPrimaryKey(primaryKey);

		if (pushCollection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPushCollectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return pushCollection;
	}

	/**
	 * Returns the push collection with the primary key or throws a {@link NoSuchPushCollectionException} if it could not be found.
	 *
	 * @param pushCollectionId the primary key of the push collection
	 * @return the push collection
	 * @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	 */
	@Override
	public PushCollection findByPrimaryKey(long pushCollectionId)
		throws NoSuchPushCollectionException {
		return findByPrimaryKey((Serializable)pushCollectionId);
	}

	/**
	 * Returns the push collection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the push collection
	 * @return the push collection, or <code>null</code> if a push collection with the primary key could not be found
	 */
	@Override
	public PushCollection fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
				PushCollectionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PushCollection pushCollection = (PushCollection)serializable;

		if (pushCollection == null) {
			Session session = null;

			try {
				session = openSession();

				pushCollection = (PushCollection)session.get(PushCollectionImpl.class,
						primaryKey);

				if (pushCollection != null) {
					cacheResult(pushCollection);
				}
				else {
					entityCache.putResult(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
						PushCollectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
					PushCollectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return pushCollection;
	}

	/**
	 * Returns the push collection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pushCollectionId the primary key of the push collection
	 * @return the push collection, or <code>null</code> if a push collection with the primary key could not be found
	 */
	@Override
	public PushCollection fetchByPrimaryKey(long pushCollectionId) {
		return fetchByPrimaryKey((Serializable)pushCollectionId);
	}

	@Override
	public Map<Serializable, PushCollection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PushCollection> map = new HashMap<Serializable, PushCollection>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PushCollection pushCollection = fetchByPrimaryKey(primaryKey);

			if (pushCollection != null) {
				map.put(primaryKey, pushCollection);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
					PushCollectionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PushCollection)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PUSHCOLLECTION_WHERE_PKS_IN);

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

			for (PushCollection pushCollection : (List<PushCollection>)q.list()) {
				map.put(pushCollection.getPrimaryKeyObj(), pushCollection);

				cacheResult(pushCollection);

				uncachedPrimaryKeys.remove(pushCollection.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PushCollectionModelImpl.ENTITY_CACHE_ENABLED,
					PushCollectionImpl.class, primaryKey, nullModel);
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
	 * Returns all the push collections.
	 *
	 * @return the push collections
	 */
	@Override
	public List<PushCollection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push collections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push collections
	 * @param end the upper bound of the range of push collections (not inclusive)
	 * @return the range of push collections
	 */
	@Override
	public List<PushCollection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the push collections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push collections
	 * @param end the upper bound of the range of push collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of push collections
	 */
	@Override
	public List<PushCollection> findAll(int start, int end,
		OrderByComparator<PushCollection> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push collections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push collections
	 * @param end the upper bound of the range of push collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of push collections
	 */
	@Override
	public List<PushCollection> findAll(int start, int end,
		OrderByComparator<PushCollection> orderByComparator,
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

		List<PushCollection> list = null;

		if (retrieveFromCache) {
			list = (List<PushCollection>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PUSHCOLLECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PUSHCOLLECTION;

				if (pagination) {
					sql = sql.concat(PushCollectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PushCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushCollection>)QueryUtil.list(q,
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
	 * Removes all the push collections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PushCollection pushCollection : findAll()) {
			remove(pushCollection);
		}
	}

	/**
	 * Returns the number of push collections.
	 *
	 * @return the number of push collections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PUSHCOLLECTION);

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
		return PushCollectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the push collection persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PushCollectionImpl.class.getName());
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
	private static final String _SQL_SELECT_PUSHCOLLECTION = "SELECT pushCollection FROM PushCollection pushCollection";
	private static final String _SQL_SELECT_PUSHCOLLECTION_WHERE_PKS_IN = "SELECT pushCollection FROM PushCollection pushCollection WHERE pushCollectionId IN (";
	private static final String _SQL_SELECT_PUSHCOLLECTION_WHERE = "SELECT pushCollection FROM PushCollection pushCollection WHERE ";
	private static final String _SQL_COUNT_PUSHCOLLECTION = "SELECT COUNT(pushCollection) FROM PushCollection pushCollection";
	private static final String _SQL_COUNT_PUSHCOLLECTION_WHERE = "SELECT COUNT(pushCollection) FROM PushCollection pushCollection WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "pushCollection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PushCollection exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PushCollection exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PushCollectionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}