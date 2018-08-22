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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.datamgt.exception.NoSuchDictCollectionException;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.impl.DictCollectionImpl;
import org.opencps.datamgt.model.impl.DictCollectionModelImpl;
import org.opencps.datamgt.service.persistence.DictCollectionPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

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
 * The persistence implementation for the dict collection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see DictCollectionPersistence
 * @see org.opencps.datamgt.service.persistence.DictCollectionUtil
 * @generated
 */
@ProviderType
public class DictCollectionPersistenceImpl extends BasePersistenceImpl<DictCollection>
	implements DictCollectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DictCollectionUtil} to access the dict collection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DictCollectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DictCollectionModelImpl.UUID_COLUMN_BITMASK |
			DictCollectionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dict collections where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dict collections
	 */
	@Override
	public List<DictCollection> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict collections where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @return the range of matching dict collections
	 */
	@Override
	public List<DictCollection> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict collections where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict collections
	 */
	@Override
	public List<DictCollection> findByUuid(String uuid, int start, int end,
		OrderByComparator<DictCollection> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict collections where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict collections
	 */
	@Override
	public List<DictCollection> findByUuid(String uuid, int start, int end,
		OrderByComparator<DictCollection> orderByComparator,
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

		List<DictCollection> list = null;

		if (retrieveFromCache) {
			list = (List<DictCollection>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictCollection dictCollection : list) {
					if (!Objects.equals(uuid, dictCollection.getUuid())) {
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

			query.append(_SQL_SELECT_DICTCOLLECTION_WHERE);

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
				query.append(DictCollectionModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictCollection>)QueryUtil.list(q,
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
	 * Returns the first dict collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection
	 * @throws NoSuchDictCollectionException if a matching dict collection could not be found
	 */
	@Override
	public DictCollection findByUuid_First(String uuid,
		OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = fetchByUuid_First(uuid,
				orderByComparator);

		if (dictCollection != null) {
			return dictCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDictCollectionException(msg.toString());
	}

	/**
	 * Returns the first dict collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection, or <code>null</code> if a matching dict collection could not be found
	 */
	@Override
	public DictCollection fetchByUuid_First(String uuid,
		OrderByComparator<DictCollection> orderByComparator) {
		List<DictCollection> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection
	 * @throws NoSuchDictCollectionException if a matching dict collection could not be found
	 */
	@Override
	public DictCollection findByUuid_Last(String uuid,
		OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = fetchByUuid_Last(uuid, orderByComparator);

		if (dictCollection != null) {
			return dictCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDictCollectionException(msg.toString());
	}

	/**
	 * Returns the last dict collection in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection, or <code>null</code> if a matching dict collection could not be found
	 */
	@Override
	public DictCollection fetchByUuid_Last(String uuid,
		OrderByComparator<DictCollection> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DictCollection> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict collections before and after the current dict collection in the ordered set where uuid = &#63;.
	 *
	 * @param dictCollectionId the primary key of the current dict collection
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict collection
	 * @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	 */
	@Override
	public DictCollection[] findByUuid_PrevAndNext(long dictCollectionId,
		String uuid, OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = findByPrimaryKey(dictCollectionId);

		Session session = null;

		try {
			session = openSession();

			DictCollection[] array = new DictCollectionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dictCollection, uuid,
					orderByComparator, true);

			array[1] = dictCollection;

			array[2] = getByUuid_PrevAndNext(session, dictCollection, uuid,
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

	protected DictCollection getByUuid_PrevAndNext(Session session,
		DictCollection dictCollection, String uuid,
		OrderByComparator<DictCollection> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DICTCOLLECTION_WHERE);

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
			query.append(DictCollectionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict collections where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DictCollection dictCollection : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictCollection);
		}
	}

	/**
	 * Returns the number of dict collections where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dict collections
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DICTCOLLECTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dictCollection.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dictCollection.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dictCollection.uuid IS NULL OR dictCollection.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DictCollectionModelImpl.UUID_COLUMN_BITMASK |
			DictCollectionModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict collection where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictCollectionException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dict collection
	 * @throws NoSuchDictCollectionException if a matching dict collection could not be found
	 */
	@Override
	public DictCollection findByUUID_G(String uuid, long groupId)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = fetchByUUID_G(uuid, groupId);

		if (dictCollection == null) {
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

			throw new NoSuchDictCollectionException(msg.toString());
		}

		return dictCollection;
	}

	/**
	 * Returns the dict collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dict collection, or <code>null</code> if a matching dict collection could not be found
	 */
	@Override
	public DictCollection fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dict collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict collection, or <code>null</code> if a matching dict collection could not be found
	 */
	@Override
	public DictCollection fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DictCollection) {
			DictCollection dictCollection = (DictCollection)result;

			if (!Objects.equals(uuid, dictCollection.getUuid()) ||
					(groupId != dictCollection.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTCOLLECTION_WHERE);

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

				List<DictCollection> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DictCollection dictCollection = list.get(0);

					result = dictCollection;

					cacheResult(dictCollection);
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
			return (DictCollection)result;
		}
	}

	/**
	 * Removes the dict collection where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dict collection that was removed
	 */
	@Override
	public DictCollection removeByUUID_G(String uuid, long groupId)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = findByUUID_G(uuid, groupId);

		return remove(dictCollection);
	}

	/**
	 * Returns the number of dict collections where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dict collections
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTCOLLECTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dictCollection.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dictCollection.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dictCollection.uuid IS NULL OR dictCollection.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dictCollection.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DictCollectionModelImpl.UUID_COLUMN_BITMASK |
			DictCollectionModelImpl.COMPANYID_COLUMN_BITMASK |
			DictCollectionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict collections where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dict collections
	 */
	@Override
	public List<DictCollection> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict collections where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @return the range of matching dict collections
	 */
	@Override
	public List<DictCollection> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict collections where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict collections
	 */
	@Override
	public List<DictCollection> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DictCollection> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict collections where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict collections
	 */
	@Override
	public List<DictCollection> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DictCollection> orderByComparator,
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

		List<DictCollection> list = null;

		if (retrieveFromCache) {
			list = (List<DictCollection>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictCollection dictCollection : list) {
					if (!Objects.equals(uuid, dictCollection.getUuid()) ||
							(companyId != dictCollection.getCompanyId())) {
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

			query.append(_SQL_SELECT_DICTCOLLECTION_WHERE);

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
				query.append(DictCollectionModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictCollection>)QueryUtil.list(q,
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
	 * Returns the first dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection
	 * @throws NoSuchDictCollectionException if a matching dict collection could not be found
	 */
	@Override
	public DictCollection findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (dictCollection != null) {
			return dictCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDictCollectionException(msg.toString());
	}

	/**
	 * Returns the first dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection, or <code>null</code> if a matching dict collection could not be found
	 */
	@Override
	public DictCollection fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictCollection> orderByComparator) {
		List<DictCollection> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection
	 * @throws NoSuchDictCollectionException if a matching dict collection could not be found
	 */
	@Override
	public DictCollection findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (dictCollection != null) {
			return dictCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDictCollectionException(msg.toString());
	}

	/**
	 * Returns the last dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection, or <code>null</code> if a matching dict collection could not be found
	 */
	@Override
	public DictCollection fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictCollection> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DictCollection> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict collections before and after the current dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dictCollectionId the primary key of the current dict collection
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict collection
	 * @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	 */
	@Override
	public DictCollection[] findByUuid_C_PrevAndNext(long dictCollectionId,
		String uuid, long companyId,
		OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = findByPrimaryKey(dictCollectionId);

		Session session = null;

		try {
			session = openSession();

			DictCollection[] array = new DictCollectionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dictCollection, uuid,
					companyId, orderByComparator, true);

			array[1] = dictCollection;

			array[2] = getByUuid_C_PrevAndNext(session, dictCollection, uuid,
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

	protected DictCollection getByUuid_C_PrevAndNext(Session session,
		DictCollection dictCollection, String uuid, long companyId,
		OrderByComparator<DictCollection> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTCOLLECTION_WHERE);

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
			query.append(DictCollectionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict collections where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DictCollection dictCollection : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictCollection);
		}
	}

	/**
	 * Returns the number of dict collections where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dict collections
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTCOLLECTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dictCollection.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dictCollection.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dictCollection.uuid IS NULL OR dictCollection.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dictCollection.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE = new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_dictCollectionCode",
			new String[] { String.class.getName(), Long.class.getName() },
			DictCollectionModelImpl.COLLECTIONCODE_COLUMN_BITMASK |
			DictCollectionModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONCODE = new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_dictCollectionCode",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict collection where collectionCode = &#63; and groupId = &#63; or throws a {@link NoSuchDictCollectionException} if it could not be found.
	 *
	 * @param collectionCode the collection code
	 * @param groupId the group ID
	 * @return the matching dict collection
	 * @throws NoSuchDictCollectionException if a matching dict collection could not be found
	 */
	@Override
	public DictCollection findByF_dictCollectionCode(String collectionCode,
		long groupId) throws NoSuchDictCollectionException {
		DictCollection dictCollection = fetchByF_dictCollectionCode(collectionCode,
				groupId);

		if (dictCollection == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("collectionCode=");
			msg.append(collectionCode);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDictCollectionException(msg.toString());
		}

		return dictCollection;
	}

	/**
	 * Returns the dict collection where collectionCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param collectionCode the collection code
	 * @param groupId the group ID
	 * @return the matching dict collection, or <code>null</code> if a matching dict collection could not be found
	 */
	@Override
	public DictCollection fetchByF_dictCollectionCode(String collectionCode,
		long groupId) {
		return fetchByF_dictCollectionCode(collectionCode, groupId, true);
	}

	/**
	 * Returns the dict collection where collectionCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param collectionCode the collection code
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict collection, or <code>null</code> if a matching dict collection could not be found
	 */
	@Override
	public DictCollection fetchByF_dictCollectionCode(String collectionCode,
		long groupId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { collectionCode, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE,
					finderArgs, this);
		}

		if (result instanceof DictCollection) {
			DictCollection dictCollection = (DictCollection)result;

			if (!Objects.equals(collectionCode,
						dictCollection.getCollectionCode()) ||
					(groupId != dictCollection.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTCOLLECTION_WHERE);

			boolean bindCollectionCode = false;

			if (collectionCode == null) {
				query.append(_FINDER_COLUMN_F_DICTCOLLECTIONCODE_COLLECTIONCODE_1);
			}
			else if (collectionCode.equals("")) {
				query.append(_FINDER_COLUMN_F_DICTCOLLECTIONCODE_COLLECTIONCODE_3);
			}
			else {
				bindCollectionCode = true;

				query.append(_FINDER_COLUMN_F_DICTCOLLECTIONCODE_COLLECTIONCODE_2);
			}

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONCODE_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCollectionCode) {
					qPos.add(collectionCode);
				}

				qPos.add(groupId);

				List<DictCollection> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictCollectionPersistenceImpl.fetchByF_dictCollectionCode(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictCollection dictCollection = list.get(0);

					result = dictCollection;

					cacheResult(dictCollection);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE,
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
			return (DictCollection)result;
		}
	}

	/**
	 * Removes the dict collection where collectionCode = &#63; and groupId = &#63; from the database.
	 *
	 * @param collectionCode the collection code
	 * @param groupId the group ID
	 * @return the dict collection that was removed
	 */
	@Override
	public DictCollection removeByF_dictCollectionCode(String collectionCode,
		long groupId) throws NoSuchDictCollectionException {
		DictCollection dictCollection = findByF_dictCollectionCode(collectionCode,
				groupId);

		return remove(dictCollection);
	}

	/**
	 * Returns the number of dict collections where collectionCode = &#63; and groupId = &#63;.
	 *
	 * @param collectionCode the collection code
	 * @param groupId the group ID
	 * @return the number of matching dict collections
	 */
	@Override
	public int countByF_dictCollectionCode(String collectionCode, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONCODE;

		Object[] finderArgs = new Object[] { collectionCode, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTCOLLECTION_WHERE);

			boolean bindCollectionCode = false;

			if (collectionCode == null) {
				query.append(_FINDER_COLUMN_F_DICTCOLLECTIONCODE_COLLECTIONCODE_1);
			}
			else if (collectionCode.equals("")) {
				query.append(_FINDER_COLUMN_F_DICTCOLLECTIONCODE_COLLECTIONCODE_3);
			}
			else {
				bindCollectionCode = true;

				query.append(_FINDER_COLUMN_F_DICTCOLLECTIONCODE_COLLECTIONCODE_2);
			}

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONCODE_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCollectionCode) {
					qPos.add(collectionCode);
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

	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONCODE_COLLECTIONCODE_1 =
		"dictCollection.collectionCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONCODE_COLLECTIONCODE_2 =
		"dictCollection.collectionCode = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONCODE_COLLECTIONCODE_3 =
		"(dictCollection.collectionCode IS NULL OR dictCollection.collectionCode = '') AND ";
	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONCODE_GROUPID_2 = "dictCollection.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTCOLLECTIONBYGROUP =
		new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_dictCollectionByGroup",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONBYGROUP =
		new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_dictCollectionByGroup",
			new String[] { Long.class.getName() },
			DictCollectionModelImpl.GROUPID_COLUMN_BITMASK |
			DictCollectionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONBYGROUP = new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_dictCollectionByGroup",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dict collections where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching dict collections
	 */
	@Override
	public List<DictCollection> findByF_dictCollectionByGroup(long groupId) {
		return findByF_dictCollectionByGroup(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict collections where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @return the range of matching dict collections
	 */
	@Override
	public List<DictCollection> findByF_dictCollectionByGroup(long groupId,
		int start, int end) {
		return findByF_dictCollectionByGroup(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict collections where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict collections
	 */
	@Override
	public List<DictCollection> findByF_dictCollectionByGroup(long groupId,
		int start, int end, OrderByComparator<DictCollection> orderByComparator) {
		return findByF_dictCollectionByGroup(groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict collections where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict collections
	 */
	@Override
	public List<DictCollection> findByF_dictCollectionByGroup(long groupId,
		int start, int end,
		OrderByComparator<DictCollection> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONBYGROUP;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTCOLLECTIONBYGROUP;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<DictCollection> list = null;

		if (retrieveFromCache) {
			list = (List<DictCollection>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictCollection dictCollection : list) {
					if ((groupId != dictCollection.getGroupId())) {
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

			query.append(_SQL_SELECT_DICTCOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONBYGROUP_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<DictCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictCollection>)QueryUtil.list(q,
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
	 * Returns the first dict collection in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection
	 * @throws NoSuchDictCollectionException if a matching dict collection could not be found
	 */
	@Override
	public DictCollection findByF_dictCollectionByGroup_First(long groupId,
		OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = fetchByF_dictCollectionByGroup_First(groupId,
				orderByComparator);

		if (dictCollection != null) {
			return dictCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictCollectionException(msg.toString());
	}

	/**
	 * Returns the first dict collection in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection, or <code>null</code> if a matching dict collection could not be found
	 */
	@Override
	public DictCollection fetchByF_dictCollectionByGroup_First(long groupId,
		OrderByComparator<DictCollection> orderByComparator) {
		List<DictCollection> list = findByF_dictCollectionByGroup(groupId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict collection in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection
	 * @throws NoSuchDictCollectionException if a matching dict collection could not be found
	 */
	@Override
	public DictCollection findByF_dictCollectionByGroup_Last(long groupId,
		OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = fetchByF_dictCollectionByGroup_Last(groupId,
				orderByComparator);

		if (dictCollection != null) {
			return dictCollection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictCollectionException(msg.toString());
	}

	/**
	 * Returns the last dict collection in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection, or <code>null</code> if a matching dict collection could not be found
	 */
	@Override
	public DictCollection fetchByF_dictCollectionByGroup_Last(long groupId,
		OrderByComparator<DictCollection> orderByComparator) {
		int count = countByF_dictCollectionByGroup(groupId);

		if (count == 0) {
			return null;
		}

		List<DictCollection> list = findByF_dictCollectionByGroup(groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict collections before and after the current dict collection in the ordered set where groupId = &#63;.
	 *
	 * @param dictCollectionId the primary key of the current dict collection
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict collection
	 * @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	 */
	@Override
	public DictCollection[] findByF_dictCollectionByGroup_PrevAndNext(
		long dictCollectionId, long groupId,
		OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = findByPrimaryKey(dictCollectionId);

		Session session = null;

		try {
			session = openSession();

			DictCollection[] array = new DictCollectionImpl[3];

			array[0] = getByF_dictCollectionByGroup_PrevAndNext(session,
					dictCollection, groupId, orderByComparator, true);

			array[1] = dictCollection;

			array[2] = getByF_dictCollectionByGroup_PrevAndNext(session,
					dictCollection, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DictCollection getByF_dictCollectionByGroup_PrevAndNext(
		Session session, DictCollection dictCollection, long groupId,
		OrderByComparator<DictCollection> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DICTCOLLECTION_WHERE);

		query.append(_FINDER_COLUMN_F_DICTCOLLECTIONBYGROUP_GROUPID_2);

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
			query.append(DictCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict collections where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_dictCollectionByGroup(long groupId) {
		for (DictCollection dictCollection : findByF_dictCollectionByGroup(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictCollection);
		}
	}

	/**
	 * Returns the number of dict collections where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching dict collections
	 */
	@Override
	public int countByF_dictCollectionByGroup(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONBYGROUP;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DICTCOLLECTION_WHERE);

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONBYGROUP_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONBYGROUP_GROUPID_2 =
		"dictCollection.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTCOLLECTIONNEWERTHAN =
		new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_dictCollectionNewerThan",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_DICTCOLLECTIONNEWERTHAN =
		new FinderPath(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_dictCollectionNewerThan",
			new String[] { Date.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict collections where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @return the matching dict collections
	 */
	@Override
	public List<DictCollection> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId) {
		return findByF_dictCollectionNewerThan(modifiedDate, groupId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict collections where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @return the range of matching dict collections
	 */
	@Override
	public List<DictCollection> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId, int start, int end) {
		return findByF_dictCollectionNewerThan(modifiedDate, groupId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the dict collections where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict collections
	 */
	@Override
	public List<DictCollection> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		OrderByComparator<DictCollection> orderByComparator) {
		return findByF_dictCollectionNewerThan(modifiedDate, groupId, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict collections where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict collections
	 */
	@Override
	public List<DictCollection> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		OrderByComparator<DictCollection> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTCOLLECTIONNEWERTHAN;
		finderArgs = new Object[] {
				_getTime(modifiedDate), groupId,
				
				start, end, orderByComparator
			};

		List<DictCollection> list = null;

		if (retrieveFromCache) {
			list = (List<DictCollection>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictCollection dictCollection : list) {
					if ((modifiedDate.getTime() > dictCollection.getModifiedDate()
																	.getTime()) ||
							(groupId != dictCollection.getGroupId())) {
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

			query.append(_SQL_SELECT_DICTCOLLECTION_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_DICTCOLLECTIONNEWERTHAN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_DICTCOLLECTIONNEWERTHAN_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONNEWERTHAN_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictCollectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				qPos.add(groupId);

				if (!pagination) {
					list = (List<DictCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictCollection>)QueryUtil.list(q,
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
	 * Returns the first dict collection in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection
	 * @throws NoSuchDictCollectionException if a matching dict collection could not be found
	 */
	@Override
	public DictCollection findByF_dictCollectionNewerThan_First(
		Date modifiedDate, long groupId,
		OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = fetchByF_dictCollectionNewerThan_First(modifiedDate,
				groupId, orderByComparator);

		if (dictCollection != null) {
			return dictCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictCollectionException(msg.toString());
	}

	/**
	 * Returns the first dict collection in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection, or <code>null</code> if a matching dict collection could not be found
	 */
	@Override
	public DictCollection fetchByF_dictCollectionNewerThan_First(
		Date modifiedDate, long groupId,
		OrderByComparator<DictCollection> orderByComparator) {
		List<DictCollection> list = findByF_dictCollectionNewerThan(modifiedDate,
				groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict collection in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection
	 * @throws NoSuchDictCollectionException if a matching dict collection could not be found
	 */
	@Override
	public DictCollection findByF_dictCollectionNewerThan_Last(
		Date modifiedDate, long groupId,
		OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = fetchByF_dictCollectionNewerThan_Last(modifiedDate,
				groupId, orderByComparator);

		if (dictCollection != null) {
			return dictCollection;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictCollectionException(msg.toString());
	}

	/**
	 * Returns the last dict collection in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection, or <code>null</code> if a matching dict collection could not be found
	 */
	@Override
	public DictCollection fetchByF_dictCollectionNewerThan_Last(
		Date modifiedDate, long groupId,
		OrderByComparator<DictCollection> orderByComparator) {
		int count = countByF_dictCollectionNewerThan(modifiedDate, groupId);

		if (count == 0) {
			return null;
		}

		List<DictCollection> list = findByF_dictCollectionNewerThan(modifiedDate,
				groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict collections before and after the current dict collection in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the primary key of the current dict collection
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict collection
	 * @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	 */
	@Override
	public DictCollection[] findByF_dictCollectionNewerThan_PrevAndNext(
		long dictCollectionId, Date modifiedDate, long groupId,
		OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = findByPrimaryKey(dictCollectionId);

		Session session = null;

		try {
			session = openSession();

			DictCollection[] array = new DictCollectionImpl[3];

			array[0] = getByF_dictCollectionNewerThan_PrevAndNext(session,
					dictCollection, modifiedDate, groupId, orderByComparator,
					true);

			array[1] = dictCollection;

			array[2] = getByF_dictCollectionNewerThan_PrevAndNext(session,
					dictCollection, modifiedDate, groupId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DictCollection getByF_dictCollectionNewerThan_PrevAndNext(
		Session session, DictCollection dictCollection, Date modifiedDate,
		long groupId, OrderByComparator<DictCollection> orderByComparator,
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

		query.append(_SQL_SELECT_DICTCOLLECTION_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONNEWERTHAN_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONNEWERTHAN_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_F_DICTCOLLECTIONNEWERTHAN_GROUPID_2);

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
			query.append(DictCollectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictCollection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictCollection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict collections where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_dictCollectionNewerThan(Date modifiedDate,
		long groupId) {
		for (DictCollection dictCollection : findByF_dictCollectionNewerThan(
				modifiedDate, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(dictCollection);
		}
	}

	/**
	 * Returns the number of dict collections where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @return the number of matching dict collections
	 */
	@Override
	public int countByF_dictCollectionNewerThan(Date modifiedDate, long groupId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_DICTCOLLECTIONNEWERTHAN;

		Object[] finderArgs = new Object[] { _getTime(modifiedDate), groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTCOLLECTION_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_DICTCOLLECTIONNEWERTHAN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_DICTCOLLECTIONNEWERTHAN_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONNEWERTHAN_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
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

	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONNEWERTHAN_MODIFIEDDATE_1 =
		"dictCollection.modifiedDate IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONNEWERTHAN_MODIFIEDDATE_2 =
		"dictCollection.modifiedDate >= ? AND ";
	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONNEWERTHAN_GROUPID_2 =
		"dictCollection.groupId = ?";

	public DictCollectionPersistenceImpl() {
		setModelClass(DictCollection.class);

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
	 * Caches the dict collection in the entity cache if it is enabled.
	 *
	 * @param dictCollection the dict collection
	 */
	@Override
	public void cacheResult(DictCollection dictCollection) {
		entityCache.putResult(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionImpl.class, dictCollection.getPrimaryKey(),
			dictCollection);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dictCollection.getUuid(), dictCollection.getGroupId() },
			dictCollection);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE,
			new Object[] {
				dictCollection.getCollectionCode(), dictCollection.getGroupId()
			}, dictCollection);

		dictCollection.resetOriginalValues();
	}

	/**
	 * Caches the dict collections in the entity cache if it is enabled.
	 *
	 * @param dictCollections the dict collections
	 */
	@Override
	public void cacheResult(List<DictCollection> dictCollections) {
		for (DictCollection dictCollection : dictCollections) {
			if (entityCache.getResult(
						DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
						DictCollectionImpl.class, dictCollection.getPrimaryKey()) == null) {
				cacheResult(dictCollection);
			}
			else {
				dictCollection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dict collections.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DictCollectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dict collection.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DictCollection dictCollection) {
		entityCache.removeResult(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionImpl.class, dictCollection.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DictCollectionModelImpl)dictCollection, true);
	}

	@Override
	public void clearCache(List<DictCollection> dictCollections) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DictCollection dictCollection : dictCollections) {
			entityCache.removeResult(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
				DictCollectionImpl.class, dictCollection.getPrimaryKey());

			clearUniqueFindersCache((DictCollectionModelImpl)dictCollection,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		DictCollectionModelImpl dictCollectionModelImpl) {
		Object[] args = new Object[] {
				dictCollectionModelImpl.getUuid(),
				dictCollectionModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dictCollectionModelImpl, false);

		args = new Object[] {
				dictCollectionModelImpl.getCollectionCode(),
				dictCollectionModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONCODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE, args,
			dictCollectionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DictCollectionModelImpl dictCollectionModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dictCollectionModelImpl.getUuid(),
					dictCollectionModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dictCollectionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictCollectionModelImpl.getOriginalUuid(),
					dictCollectionModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dictCollectionModelImpl.getCollectionCode(),
					dictCollectionModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONCODE,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE,
				args);
		}

		if ((dictCollectionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictCollectionModelImpl.getOriginalCollectionCode(),
					dictCollectionModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONCODE,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE,
				args);
		}
	}

	/**
	 * Creates a new dict collection with the primary key. Does not add the dict collection to the database.
	 *
	 * @param dictCollectionId the primary key for the new dict collection
	 * @return the new dict collection
	 */
	@Override
	public DictCollection create(long dictCollectionId) {
		DictCollection dictCollection = new DictCollectionImpl();

		dictCollection.setNew(true);
		dictCollection.setPrimaryKey(dictCollectionId);

		String uuid = PortalUUIDUtil.generate();

		dictCollection.setUuid(uuid);

		dictCollection.setCompanyId(companyProvider.getCompanyId());

		return dictCollection;
	}

	/**
	 * Removes the dict collection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dictCollectionId the primary key of the dict collection
	 * @return the dict collection that was removed
	 * @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	 */
	@Override
	public DictCollection remove(long dictCollectionId)
		throws NoSuchDictCollectionException {
		return remove((Serializable)dictCollectionId);
	}

	/**
	 * Removes the dict collection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dict collection
	 * @return the dict collection that was removed
	 * @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	 */
	@Override
	public DictCollection remove(Serializable primaryKey)
		throws NoSuchDictCollectionException {
		Session session = null;

		try {
			session = openSession();

			DictCollection dictCollection = (DictCollection)session.get(DictCollectionImpl.class,
					primaryKey);

			if (dictCollection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDictCollectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dictCollection);
		}
		catch (NoSuchDictCollectionException nsee) {
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
	protected DictCollection removeImpl(DictCollection dictCollection) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dictCollection)) {
				dictCollection = (DictCollection)session.get(DictCollectionImpl.class,
						dictCollection.getPrimaryKeyObj());
			}

			if (dictCollection != null) {
				session.delete(dictCollection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dictCollection != null) {
			clearCache(dictCollection);
		}

		return dictCollection;
	}

	@Override
	public DictCollection updateImpl(DictCollection dictCollection) {
		boolean isNew = dictCollection.isNew();

		if (!(dictCollection instanceof DictCollectionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dictCollection.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dictCollection);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dictCollection proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DictCollection implementation " +
				dictCollection.getClass());
		}

		DictCollectionModelImpl dictCollectionModelImpl = (DictCollectionModelImpl)dictCollection;

		if (Validator.isNull(dictCollection.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dictCollection.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dictCollection.getCreateDate() == null)) {
			if (serviceContext == null) {
				dictCollection.setCreateDate(now);
			}
			else {
				dictCollection.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dictCollectionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dictCollection.setModifiedDate(now);
			}
			else {
				dictCollection.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dictCollection.isNew()) {
				session.save(dictCollection);

				dictCollection.setNew(false);
			}
			else {
				dictCollection = (DictCollection)session.merge(dictCollection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DictCollectionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dictCollectionModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					dictCollectionModelImpl.getUuid(),
					dictCollectionModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { dictCollectionModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONBYGROUP,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONBYGROUP,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dictCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictCollectionModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dictCollectionModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dictCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictCollectionModelImpl.getOriginalUuid(),
						dictCollectionModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dictCollectionModelImpl.getUuid(),
						dictCollectionModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((dictCollectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONBYGROUP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictCollectionModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONBYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONBYGROUP,
					args);

				args = new Object[] { dictCollectionModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONBYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONBYGROUP,
					args);
			}
		}

		entityCache.putResult(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionImpl.class, dictCollection.getPrimaryKey(),
			dictCollection, false);

		clearUniqueFindersCache(dictCollectionModelImpl, false);
		cacheUniqueFindersCache(dictCollectionModelImpl);

		dictCollection.resetOriginalValues();

		return dictCollection;
	}

	/**
	 * Returns the dict collection with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict collection
	 * @return the dict collection
	 * @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	 */
	@Override
	public DictCollection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDictCollectionException {
		DictCollection dictCollection = fetchByPrimaryKey(primaryKey);

		if (dictCollection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDictCollectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dictCollection;
	}

	/**
	 * Returns the dict collection with the primary key or throws a {@link NoSuchDictCollectionException} if it could not be found.
	 *
	 * @param dictCollectionId the primary key of the dict collection
	 * @return the dict collection
	 * @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	 */
	@Override
	public DictCollection findByPrimaryKey(long dictCollectionId)
		throws NoSuchDictCollectionException {
		return findByPrimaryKey((Serializable)dictCollectionId);
	}

	/**
	 * Returns the dict collection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict collection
	 * @return the dict collection, or <code>null</code> if a dict collection with the primary key could not be found
	 */
	@Override
	public DictCollection fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
				DictCollectionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DictCollection dictCollection = (DictCollection)serializable;

		if (dictCollection == null) {
			Session session = null;

			try {
				session = openSession();

				dictCollection = (DictCollection)session.get(DictCollectionImpl.class,
						primaryKey);

				if (dictCollection != null) {
					cacheResult(dictCollection);
				}
				else {
					entityCache.putResult(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
						DictCollectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
					DictCollectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dictCollection;
	}

	/**
	 * Returns the dict collection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dictCollectionId the primary key of the dict collection
	 * @return the dict collection, or <code>null</code> if a dict collection with the primary key could not be found
	 */
	@Override
	public DictCollection fetchByPrimaryKey(long dictCollectionId) {
		return fetchByPrimaryKey((Serializable)dictCollectionId);
	}

	@Override
	public Map<Serializable, DictCollection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DictCollection> map = new HashMap<Serializable, DictCollection>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DictCollection dictCollection = fetchByPrimaryKey(primaryKey);

			if (dictCollection != null) {
				map.put(primaryKey, dictCollection);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
					DictCollectionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DictCollection)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DICTCOLLECTION_WHERE_PKS_IN);

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

			for (DictCollection dictCollection : (List<DictCollection>)q.list()) {
				map.put(dictCollection.getPrimaryKeyObj(), dictCollection);

				cacheResult(dictCollection);

				uncachedPrimaryKeys.remove(dictCollection.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DictCollectionModelImpl.ENTITY_CACHE_ENABLED,
					DictCollectionImpl.class, primaryKey, nullModel);
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
	 * Returns all the dict collections.
	 *
	 * @return the dict collections
	 */
	@Override
	public List<DictCollection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict collections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @return the range of dict collections
	 */
	@Override
	public List<DictCollection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict collections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dict collections
	 */
	@Override
	public List<DictCollection> findAll(int start, int end,
		OrderByComparator<DictCollection> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict collections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict collections
	 * @param end the upper bound of the range of dict collections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dict collections
	 */
	@Override
	public List<DictCollection> findAll(int start, int end,
		OrderByComparator<DictCollection> orderByComparator,
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

		List<DictCollection> list = null;

		if (retrieveFromCache) {
			list = (List<DictCollection>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DICTCOLLECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DICTCOLLECTION;

				if (pagination) {
					sql = sql.concat(DictCollectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DictCollection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictCollection>)QueryUtil.list(q,
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
	 * Removes all the dict collections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DictCollection dictCollection : findAll()) {
			remove(dictCollection);
		}
	}

	/**
	 * Returns the number of dict collections.
	 *
	 * @return the number of dict collections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DICTCOLLECTION);

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
		return DictCollectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dict collection persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DictCollectionImpl.class.getName());
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

	private Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_DICTCOLLECTION = "SELECT dictCollection FROM DictCollection dictCollection";
	private static final String _SQL_SELECT_DICTCOLLECTION_WHERE_PKS_IN = "SELECT dictCollection FROM DictCollection dictCollection WHERE dictCollectionId IN (";
	private static final String _SQL_SELECT_DICTCOLLECTION_WHERE = "SELECT dictCollection FROM DictCollection dictCollection WHERE ";
	private static final String _SQL_COUNT_DICTCOLLECTION = "SELECT COUNT(dictCollection) FROM DictCollection dictCollection";
	private static final String _SQL_COUNT_DICTCOLLECTION_WHERE = "SELECT COUNT(dictCollection) FROM DictCollection dictCollection WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dictCollection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DictCollection exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DictCollection exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DictCollectionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}