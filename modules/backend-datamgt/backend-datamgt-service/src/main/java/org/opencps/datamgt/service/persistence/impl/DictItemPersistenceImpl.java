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

import org.opencps.datamgt.exception.NoSuchDictItemException;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.impl.DictItemImpl;
import org.opencps.datamgt.model.impl.DictItemModelImpl;
import org.opencps.datamgt.service.persistence.DictItemPersistence;

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
 * The persistence implementation for the dict item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see DictItemPersistence
 * @see org.opencps.datamgt.service.persistence.DictItemUtil
 * @generated
 */
@ProviderType
public class DictItemPersistenceImpl extends BasePersistenceImpl<DictItem>
	implements DictItemPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DictItemUtil} to access the dict item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DictItemImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DictItemModelImpl.UUID_COLUMN_BITMASK |
			DictItemModelImpl.SIBLING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dict items where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dict items
	 */
	@Override
	public List<DictItem> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict items where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @return the range of matching dict items
	 */
	@Override
	public List<DictItem> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict items where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByUuid(String uuid, int start, int end,
		OrderByComparator<DictItem> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict items where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByUuid(String uuid, int start, int end,
		OrderByComparator<DictItem> orderByComparator, boolean retrieveFromCache) {
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

		List<DictItem> list = null;

		if (retrieveFromCache) {
			list = (List<DictItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItem dictItem : list) {
					if (!Objects.equals(uuid, dictItem.getUuid())) {
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

			query.append(_SQL_SELECT_DICTITEM_WHERE);

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
				query.append(DictItemModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByUuid_First(String uuid,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByUuid_First(uuid, orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the first dict item in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByUuid_First(String uuid,
		OrderByComparator<DictItem> orderByComparator) {
		List<DictItem> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByUuid_Last(String uuid,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByUuid_Last(uuid, orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the last dict item in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByUuid_Last(String uuid,
		OrderByComparator<DictItem> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DictItem> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict items before and after the current dict item in the ordered set where uuid = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item
	 * @throws NoSuchDictItemException if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem[] findByUuid_PrevAndNext(long dictItemId, String uuid,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItem[] array = new DictItemImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dictItem, uuid,
					orderByComparator, true);

			array[1] = dictItem;

			array[2] = getByUuid_PrevAndNext(session, dictItem, uuid,
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

	protected DictItem getByUuid_PrevAndNext(Session session,
		DictItem dictItem, String uuid,
		OrderByComparator<DictItem> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DICTITEM_WHERE);

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
			query.append(DictItemModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict items where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DictItem dictItem : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dictItem);
		}
	}

	/**
	 * Returns the number of dict items where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dict items
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DICTITEM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dictItem.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dictItem.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dictItem.uuid IS NULL OR dictItem.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DictItemModelImpl.UUID_COLUMN_BITMASK |
			DictItemModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict item where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByUUID_G(String uuid, long groupId)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByUUID_G(uuid, groupId);

		if (dictItem == null) {
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

			throw new NoSuchDictItemException(msg.toString());
		}

		return dictItem;
	}

	/**
	 * Returns the dict item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dict item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DictItem) {
			DictItem dictItem = (DictItem)result;

			if (!Objects.equals(uuid, dictItem.getUuid()) ||
					(groupId != dictItem.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTITEM_WHERE);

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

				List<DictItem> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DictItem dictItem = list.get(0);

					result = dictItem;

					cacheResult(dictItem);
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
			return (DictItem)result;
		}
	}

	/**
	 * Removes the dict item where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dict item that was removed
	 */
	@Override
	public DictItem removeByUUID_G(String uuid, long groupId)
		throws NoSuchDictItemException {
		DictItem dictItem = findByUUID_G(uuid, groupId);

		return remove(dictItem);
	}

	/**
	 * Returns the number of dict items where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dict items
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dictItem.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dictItem.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dictItem.uuid IS NULL OR dictItem.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dictItem.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DictItemModelImpl.UUID_COLUMN_BITMASK |
			DictItemModelImpl.COMPANYID_COLUMN_BITMASK |
			DictItemModelImpl.SIBLING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict items where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dict items
	 */
	@Override
	public List<DictItem> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict items where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @return the range of matching dict items
	 */
	@Override
	public List<DictItem> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict items where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<DictItem> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict items where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<DictItem> orderByComparator,
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

		List<DictItem> list = null;

		if (retrieveFromCache) {
			list = (List<DictItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItem dictItem : list) {
					if (!Objects.equals(uuid, dictItem.getUuid()) ||
							(companyId != dictItem.getCompanyId())) {
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

			query.append(_SQL_SELECT_DICTITEM_WHERE);

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
				query.append(DictItemModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the first dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictItem> orderByComparator) {
		List<DictItem> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the last dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictItem> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DictItem> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict items before and after the current dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item
	 * @throws NoSuchDictItemException if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem[] findByUuid_C_PrevAndNext(long dictItemId, String uuid,
		long companyId, OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItem[] array = new DictItemImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dictItem, uuid,
					companyId, orderByComparator, true);

			array[1] = dictItem;

			array[2] = getByUuid_C_PrevAndNext(session, dictItem, uuid,
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

	protected DictItem getByUuid_C_PrevAndNext(Session session,
		DictItem dictItem, String uuid, long companyId,
		OrderByComparator<DictItem> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTITEM_WHERE);

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
			query.append(DictItemModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict items where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DictItem dictItem : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictItem);
		}
	}

	/**
	 * Returns the number of dict items where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dict items
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dictItem.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dictItem.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dictItem.uuid IS NULL OR dictItem.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dictItem.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTCOLLECTIONID =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_dictCollectionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_dictCollectionId", new String[] { Long.class.getName() },
			DictItemModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK |
			DictItemModelImpl.SIBLING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_dictCollectionId", new String[] { Long.class.getName() });

	/**
	 * Returns all the dict items where dictCollectionId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @return the matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictCollectionId(long dictCollectionId) {
		return findByF_dictCollectionId(dictCollectionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict items where dictCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @return the range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictCollectionId(long dictCollectionId,
		int start, int end) {
		return findByF_dictCollectionId(dictCollectionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict items where dictCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictCollectionId(long dictCollectionId,
		int start, int end, OrderByComparator<DictItem> orderByComparator) {
		return findByF_dictCollectionId(dictCollectionId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict items where dictCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictCollectionId(long dictCollectionId,
		int start, int end, OrderByComparator<DictItem> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID;
			finderArgs = new Object[] { dictCollectionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTCOLLECTIONID;
			finderArgs = new Object[] {
					dictCollectionId,
					
					start, end, orderByComparator
				};
		}

		List<DictItem> list = null;

		if (retrieveFromCache) {
			list = (List<DictItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItem dictItem : list) {
					if ((dictCollectionId != dictItem.getDictCollectionId())) {
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

			query.append(_SQL_SELECT_DICTITEM_WHERE);

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONID_DICTCOLLECTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dictCollectionId);

				if (!pagination) {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item in the ordered set where dictCollectionId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_dictCollectionId_First(long dictCollectionId,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_dictCollectionId_First(dictCollectionId,
				orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the first dict item in the ordered set where dictCollectionId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_dictCollectionId_First(long dictCollectionId,
		OrderByComparator<DictItem> orderByComparator) {
		List<DictItem> list = findByF_dictCollectionId(dictCollectionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item in the ordered set where dictCollectionId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_dictCollectionId_Last(long dictCollectionId,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_dictCollectionId_Last(dictCollectionId,
				orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the last dict item in the ordered set where dictCollectionId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_dictCollectionId_Last(long dictCollectionId,
		OrderByComparator<DictItem> orderByComparator) {
		int count = countByF_dictCollectionId(dictCollectionId);

		if (count == 0) {
			return null;
		}

		List<DictItem> list = findByF_dictCollectionId(dictCollectionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict items before and after the current dict item in the ordered set where dictCollectionId = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item
	 * @param dictCollectionId the dict collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item
	 * @throws NoSuchDictItemException if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem[] findByF_dictCollectionId_PrevAndNext(long dictItemId,
		long dictCollectionId, OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItem[] array = new DictItemImpl[3];

			array[0] = getByF_dictCollectionId_PrevAndNext(session, dictItem,
					dictCollectionId, orderByComparator, true);

			array[1] = dictItem;

			array[2] = getByF_dictCollectionId_PrevAndNext(session, dictItem,
					dictCollectionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DictItem getByF_dictCollectionId_PrevAndNext(Session session,
		DictItem dictItem, long dictCollectionId,
		OrderByComparator<DictItem> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DICTITEM_WHERE);

		query.append(_FINDER_COLUMN_F_DICTCOLLECTIONID_DICTCOLLECTIONID_2);

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
			query.append(DictItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dictCollectionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict items where dictCollectionId = &#63; from the database.
	 *
	 * @param dictCollectionId the dict collection ID
	 */
	@Override
	public void removeByF_dictCollectionId(long dictCollectionId) {
		for (DictItem dictItem : findByF_dictCollectionId(dictCollectionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictItem);
		}
	}

	/**
	 * Returns the number of dict items where dictCollectionId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @return the number of matching dict items
	 */
	@Override
	public int countByF_dictCollectionId(long dictCollectionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID;

		Object[] finderArgs = new Object[] { dictCollectionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DICTITEM_WHERE);

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONID_DICTCOLLECTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dictCollectionId);

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

	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONID_DICTCOLLECTIONID_2 =
		"dictItem.dictCollectionId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_DICTITEMCODE = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_dictItemCode",
			new String[] { String.class.getName(), Long.class.getName() },
			DictItemModelImpl.ITEMCODE_COLUMN_BITMASK |
			DictItemModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTITEMCODE = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_dictItemCode",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict item where itemCode = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemException} if it could not be found.
	 *
	 * @param itemCode the item code
	 * @param groupId the group ID
	 * @return the matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_dictItemCode(String itemCode, long groupId)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_dictItemCode(itemCode, groupId);

		if (dictItem == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itemCode=");
			msg.append(itemCode);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDictItemException(msg.toString());
		}

		return dictItem;
	}

	/**
	 * Returns the dict item where itemCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itemCode the item code
	 * @param groupId the group ID
	 * @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_dictItemCode(String itemCode, long groupId) {
		return fetchByF_dictItemCode(itemCode, groupId, true);
	}

	/**
	 * Returns the dict item where itemCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itemCode the item code
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_dictItemCode(String itemCode, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { itemCode, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE,
					finderArgs, this);
		}

		if (result instanceof DictItem) {
			DictItem dictItem = (DictItem)result;

			if (!Objects.equals(itemCode, dictItem.getItemCode()) ||
					(groupId != dictItem.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTITEM_WHERE);

			boolean bindItemCode = false;

			if (itemCode == null) {
				query.append(_FINDER_COLUMN_F_DICTITEMCODE_ITEMCODE_1);
			}
			else if (itemCode.equals("")) {
				query.append(_FINDER_COLUMN_F_DICTITEMCODE_ITEMCODE_3);
			}
			else {
				bindItemCode = true;

				query.append(_FINDER_COLUMN_F_DICTITEMCODE_ITEMCODE_2);
			}

			query.append(_FINDER_COLUMN_F_DICTITEMCODE_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemCode) {
					qPos.add(itemCode);
				}

				qPos.add(groupId);

				List<DictItem> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictItemPersistenceImpl.fetchByF_dictItemCode(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictItem dictItem = list.get(0);

					result = dictItem;

					cacheResult(dictItem);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE,
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
			return (DictItem)result;
		}
	}

	/**
	 * Removes the dict item where itemCode = &#63; and groupId = &#63; from the database.
	 *
	 * @param itemCode the item code
	 * @param groupId the group ID
	 * @return the dict item that was removed
	 */
	@Override
	public DictItem removeByF_dictItemCode(String itemCode, long groupId)
		throws NoSuchDictItemException {
		DictItem dictItem = findByF_dictItemCode(itemCode, groupId);

		return remove(dictItem);
	}

	/**
	 * Returns the number of dict items where itemCode = &#63; and groupId = &#63;.
	 *
	 * @param itemCode the item code
	 * @param groupId the group ID
	 * @return the number of matching dict items
	 */
	@Override
	public int countByF_dictItemCode(String itemCode, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTITEMCODE;

		Object[] finderArgs = new Object[] { itemCode, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEM_WHERE);

			boolean bindItemCode = false;

			if (itemCode == null) {
				query.append(_FINDER_COLUMN_F_DICTITEMCODE_ITEMCODE_1);
			}
			else if (itemCode.equals("")) {
				query.append(_FINDER_COLUMN_F_DICTITEMCODE_ITEMCODE_3);
			}
			else {
				bindItemCode = true;

				query.append(_FINDER_COLUMN_F_DICTITEMCODE_ITEMCODE_2);
			}

			query.append(_FINDER_COLUMN_F_DICTITEMCODE_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemCode) {
					qPos.add(itemCode);
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

	private static final String _FINDER_COLUMN_F_DICTITEMCODE_ITEMCODE_1 = "dictItem.itemCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMCODE_ITEMCODE_2 = "dictItem.itemCode = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMCODE_ITEMCODE_3 = "(dictItem.itemCode IS NULL OR dictItem.itemCode = '') AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMCODE_GROUPID_2 = "dictItem.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTITEMBYGROUP =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_dictItemByGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMBYGROUP =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_dictItemByGroup",
			new String[] { Long.class.getName(), Long.class.getName() },
			DictItemModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK |
			DictItemModelImpl.GROUPID_COLUMN_BITMASK |
			DictItemModelImpl.SIBLING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTITEMBYGROUP = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_dictItemByGroup",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict items where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictItemByGroup(long dictCollectionId,
		long groupId) {
		return findByF_dictItemByGroup(dictCollectionId, groupId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict items where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @return the range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictItemByGroup(long dictCollectionId,
		long groupId, int start, int end) {
		return findByF_dictItemByGroup(dictCollectionId, groupId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the dict items where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictItemByGroup(long dictCollectionId,
		long groupId, int start, int end,
		OrderByComparator<DictItem> orderByComparator) {
		return findByF_dictItemByGroup(dictCollectionId, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict items where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictItemByGroup(long dictCollectionId,
		long groupId, int start, int end,
		OrderByComparator<DictItem> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMBYGROUP;
			finderArgs = new Object[] { dictCollectionId, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTITEMBYGROUP;
			finderArgs = new Object[] {
					dictCollectionId, groupId,
					
					start, end, orderByComparator
				};
		}

		List<DictItem> list = null;

		if (retrieveFromCache) {
			list = (List<DictItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItem dictItem : list) {
					if ((dictCollectionId != dictItem.getDictCollectionId()) ||
							(groupId != dictItem.getGroupId())) {
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

			query.append(_SQL_SELECT_DICTITEM_WHERE);

			query.append(_FINDER_COLUMN_F_DICTITEMBYGROUP_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_F_DICTITEMBYGROUP_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dictCollectionId);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_dictItemByGroup_First(long dictCollectionId,
		long groupId, OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_dictItemByGroup_First(dictCollectionId,
				groupId, orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the first dict item in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_dictItemByGroup_First(long dictCollectionId,
		long groupId, OrderByComparator<DictItem> orderByComparator) {
		List<DictItem> list = findByF_dictItemByGroup(dictCollectionId,
				groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_dictItemByGroup_Last(long dictCollectionId,
		long groupId, OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_dictItemByGroup_Last(dictCollectionId,
				groupId, orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the last dict item in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_dictItemByGroup_Last(long dictCollectionId,
		long groupId, OrderByComparator<DictItem> orderByComparator) {
		int count = countByF_dictItemByGroup(dictCollectionId, groupId);

		if (count == 0) {
			return null;
		}

		List<DictItem> list = findByF_dictItemByGroup(dictCollectionId,
				groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict items before and after the current dict item in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item
	 * @throws NoSuchDictItemException if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem[] findByF_dictItemByGroup_PrevAndNext(long dictItemId,
		long dictCollectionId, long groupId,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItem[] array = new DictItemImpl[3];

			array[0] = getByF_dictItemByGroup_PrevAndNext(session, dictItem,
					dictCollectionId, groupId, orderByComparator, true);

			array[1] = dictItem;

			array[2] = getByF_dictItemByGroup_PrevAndNext(session, dictItem,
					dictCollectionId, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DictItem getByF_dictItemByGroup_PrevAndNext(Session session,
		DictItem dictItem, long dictCollectionId, long groupId,
		OrderByComparator<DictItem> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTITEM_WHERE);

		query.append(_FINDER_COLUMN_F_DICTITEMBYGROUP_DICTCOLLECTIONID_2);

		query.append(_FINDER_COLUMN_F_DICTITEMBYGROUP_GROUPID_2);

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
			query.append(DictItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dictCollectionId);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict items where dictCollectionId = &#63; and groupId = &#63; from the database.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_dictItemByGroup(long dictCollectionId, long groupId) {
		for (DictItem dictItem : findByF_dictItemByGroup(dictCollectionId,
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictItem);
		}
	}

	/**
	 * Returns the number of dict items where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the number of matching dict items
	 */
	@Override
	public int countByF_dictItemByGroup(long dictCollectionId, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTITEMBYGROUP;

		Object[] finderArgs = new Object[] { dictCollectionId, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEM_WHERE);

			query.append(_FINDER_COLUMN_F_DICTITEMBYGROUP_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_F_DICTITEMBYGROUP_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dictCollectionId);

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

	private static final String _FINDER_COLUMN_F_DICTITEMBYGROUP_DICTCOLLECTIONID_2 =
		"dictItem.dictCollectionId = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMBYGROUP_GROUPID_2 = "dictItem.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_dictItemCode_dictCollectionId",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			DictItemModelImpl.ITEMCODE_COLUMN_BITMASK |
			DictItemModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK |
			DictItemModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTITEMCODE_DICTCOLLECTIONID =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_dictItemCode_dictCollectionId",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns the dict item where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemException} if it could not be found.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_dictItemCode_dictCollectionId(String itemCode,
		long dictCollectionId, long groupId) throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_dictItemCode_dictCollectionId(itemCode,
				dictCollectionId, groupId);

		if (dictItem == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itemCode=");
			msg.append(itemCode);

			msg.append(", dictCollectionId=");
			msg.append(dictCollectionId);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDictItemException(msg.toString());
		}

		return dictItem;
	}

	/**
	 * Returns the dict item where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_dictItemCode_dictCollectionId(String itemCode,
		long dictCollectionId, long groupId) {
		return fetchByF_dictItemCode_dictCollectionId(itemCode,
			dictCollectionId, groupId, true);
	}

	/**
	 * Returns the dict item where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_dictItemCode_dictCollectionId(String itemCode,
		long dictCollectionId, long groupId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { itemCode, dictCollectionId, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
					finderArgs, this);
		}

		if (result instanceof DictItem) {
			DictItem dictItem = (DictItem)result;

			if (!Objects.equals(itemCode, dictItem.getItemCode()) ||
					(dictCollectionId != dictItem.getDictCollectionId()) ||
					(groupId != dictItem.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DICTITEM_WHERE);

			boolean bindItemCode = false;

			if (itemCode == null) {
				query.append(_FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_ITEMCODE_1);
			}
			else if (itemCode.equals("")) {
				query.append(_FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_ITEMCODE_3);
			}
			else {
				bindItemCode = true;

				query.append(_FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_ITEMCODE_2);
			}

			query.append(_FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemCode) {
					qPos.add(itemCode);
				}

				qPos.add(dictCollectionId);

				qPos.add(groupId);

				List<DictItem> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictItemPersistenceImpl.fetchByF_dictItemCode_dictCollectionId(String, long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictItem dictItem = list.get(0);

					result = dictItem;

					cacheResult(dictItem);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
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
			return (DictItem)result;
		}
	}

	/**
	 * Removes the dict item where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; from the database.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the dict item that was removed
	 */
	@Override
	public DictItem removeByF_dictItemCode_dictCollectionId(String itemCode,
		long dictCollectionId, long groupId) throws NoSuchDictItemException {
		DictItem dictItem = findByF_dictItemCode_dictCollectionId(itemCode,
				dictCollectionId, groupId);

		return remove(dictItem);
	}

	/**
	 * Returns the number of dict items where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the number of matching dict items
	 */
	@Override
	public int countByF_dictItemCode_dictCollectionId(String itemCode,
		long dictCollectionId, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTITEMCODE_DICTCOLLECTIONID;

		Object[] finderArgs = new Object[] { itemCode, dictCollectionId, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DICTITEM_WHERE);

			boolean bindItemCode = false;

			if (itemCode == null) {
				query.append(_FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_ITEMCODE_1);
			}
			else if (itemCode.equals("")) {
				query.append(_FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_ITEMCODE_3);
			}
			else {
				bindItemCode = true;

				query.append(_FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_ITEMCODE_2);
			}

			query.append(_FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemCode) {
					qPos.add(itemCode);
				}

				qPos.add(dictCollectionId);

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

	private static final String _FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_ITEMCODE_1 =
		"dictItem.itemCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_ITEMCODE_2 =
		"dictItem.itemCode = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_ITEMCODE_3 =
		"(dictItem.itemCode IS NULL OR dictItem.itemCode = '') AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_DICTCOLLECTIONID_2 =
		"dictItem.dictCollectionId = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_GROUPID_2 =
		"dictItem.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_PARENTITEMID =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_parentItemId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_parentItemId",
			new String[] { Long.class.getName() },
			DictItemModelImpl.PARENTITEMID_COLUMN_BITMASK |
			DictItemModelImpl.SIBLING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_PARENTITEMID = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_parentItemId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dict items where parentItemId = &#63;.
	 *
	 * @param parentItemId the parent item ID
	 * @return the matching dict items
	 */
	@Override
	public List<DictItem> findByF_parentItemId(long parentItemId) {
		return findByF_parentItemId(parentItemId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict items where parentItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentItemId the parent item ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @return the range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_parentItemId(long parentItemId, int start,
		int end) {
		return findByF_parentItemId(parentItemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict items where parentItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentItemId the parent item ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_parentItemId(long parentItemId, int start,
		int end, OrderByComparator<DictItem> orderByComparator) {
		return findByF_parentItemId(parentItemId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict items where parentItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentItemId the parent item ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_parentItemId(long parentItemId, int start,
		int end, OrderByComparator<DictItem> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID;
			finderArgs = new Object[] { parentItemId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_PARENTITEMID;
			finderArgs = new Object[] {
					parentItemId,
					
					start, end, orderByComparator
				};
		}

		List<DictItem> list = null;

		if (retrieveFromCache) {
			list = (List<DictItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItem dictItem : list) {
					if ((parentItemId != dictItem.getParentItemId())) {
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

			query.append(_SQL_SELECT_DICTITEM_WHERE);

			query.append(_FINDER_COLUMN_F_PARENTITEMID_PARENTITEMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentItemId);

				if (!pagination) {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item in the ordered set where parentItemId = &#63;.
	 *
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_parentItemId_First(long parentItemId,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_parentItemId_First(parentItemId,
				orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentItemId=");
		msg.append(parentItemId);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the first dict item in the ordered set where parentItemId = &#63;.
	 *
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_parentItemId_First(long parentItemId,
		OrderByComparator<DictItem> orderByComparator) {
		List<DictItem> list = findByF_parentItemId(parentItemId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item in the ordered set where parentItemId = &#63;.
	 *
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_parentItemId_Last(long parentItemId,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_parentItemId_Last(parentItemId,
				orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentItemId=");
		msg.append(parentItemId);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the last dict item in the ordered set where parentItemId = &#63;.
	 *
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_parentItemId_Last(long parentItemId,
		OrderByComparator<DictItem> orderByComparator) {
		int count = countByF_parentItemId(parentItemId);

		if (count == 0) {
			return null;
		}

		List<DictItem> list = findByF_parentItemId(parentItemId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict items before and after the current dict item in the ordered set where parentItemId = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item
	 * @throws NoSuchDictItemException if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem[] findByF_parentItemId_PrevAndNext(long dictItemId,
		long parentItemId, OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItem[] array = new DictItemImpl[3];

			array[0] = getByF_parentItemId_PrevAndNext(session, dictItem,
					parentItemId, orderByComparator, true);

			array[1] = dictItem;

			array[2] = getByF_parentItemId_PrevAndNext(session, dictItem,
					parentItemId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DictItem getByF_parentItemId_PrevAndNext(Session session,
		DictItem dictItem, long parentItemId,
		OrderByComparator<DictItem> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DICTITEM_WHERE);

		query.append(_FINDER_COLUMN_F_PARENTITEMID_PARENTITEMID_2);

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
			query.append(DictItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentItemId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict items where parentItemId = &#63; from the database.
	 *
	 * @param parentItemId the parent item ID
	 */
	@Override
	public void removeByF_parentItemId(long parentItemId) {
		for (DictItem dictItem : findByF_parentItemId(parentItemId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictItem);
		}
	}

	/**
	 * Returns the number of dict items where parentItemId = &#63;.
	 *
	 * @param parentItemId the parent item ID
	 * @return the number of matching dict items
	 */
	@Override
	public int countByF_parentItemId(long parentItemId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_PARENTITEMID;

		Object[] finderArgs = new Object[] { parentItemId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DICTITEM_WHERE);

			query.append(_FINDER_COLUMN_F_PARENTITEMID_PARENTITEMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentItemId);

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

	private static final String _FINDER_COLUMN_F_PARENTITEMID_PARENTITEMID_2 = "dictItem.parentItemId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_PARENTITEMID_LEVEL =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_parentItemId_level",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID_LEVEL =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_parentItemId_level",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			DictItemModelImpl.GROUPID_COLUMN_BITMASK |
			DictItemModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK |
			DictItemModelImpl.PARENTITEMID_COLUMN_BITMASK |
			DictItemModelImpl.LEVEL_COLUMN_BITMASK |
			DictItemModelImpl.SIBLING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_PARENTITEMID_LEVEL = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_parentItemId_level",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the dict items where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @return the matching dict items
	 */
	@Override
	public List<DictItem> findByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level) {
		return findByF_parentItemId_level(groupId, dictCollectionId,
			parentItemId, level, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict items where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @return the range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level, int start, int end) {
		return findByF_parentItemId_level(groupId, dictCollectionId,
			parentItemId, level, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict items where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level, int start,
		int end, OrderByComparator<DictItem> orderByComparator) {
		return findByF_parentItemId_level(groupId, dictCollectionId,
			parentItemId, level, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict items where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level, int start,
		int end, OrderByComparator<DictItem> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID_LEVEL;
			finderArgs = new Object[] {
					groupId, dictCollectionId, parentItemId, level
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_PARENTITEMID_LEVEL;
			finderArgs = new Object[] {
					groupId, dictCollectionId, parentItemId, level,
					
					start, end, orderByComparator
				};
		}

		List<DictItem> list = null;

		if (retrieveFromCache) {
			list = (List<DictItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItem dictItem : list) {
					if ((groupId != dictItem.getGroupId()) ||
							(dictCollectionId != dictItem.getDictCollectionId()) ||
							(parentItemId != dictItem.getParentItemId()) ||
							(level != dictItem.getLevel())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_DICTITEM_WHERE);

			query.append(_FINDER_COLUMN_F_PARENTITEMID_LEVEL_GROUPID_2);

			query.append(_FINDER_COLUMN_F_PARENTITEMID_LEVEL_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_F_PARENTITEMID_LEVEL_PARENTITEMID_2);

			query.append(_FINDER_COLUMN_F_PARENTITEMID_LEVEL_LEVEL_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dictCollectionId);

				qPos.add(parentItemId);

				qPos.add(level);

				if (!pagination) {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_parentItemId_level_First(long groupId,
		long dictCollectionId, long parentItemId, int level,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_parentItemId_level_First(groupId,
				dictCollectionId, parentItemId, level, orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", parentItemId=");
		msg.append(parentItemId);

		msg.append(", level=");
		msg.append(level);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the first dict item in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_parentItemId_level_First(long groupId,
		long dictCollectionId, long parentItemId, int level,
		OrderByComparator<DictItem> orderByComparator) {
		List<DictItem> list = findByF_parentItemId_level(groupId,
				dictCollectionId, parentItemId, level, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_parentItemId_level_Last(long groupId,
		long dictCollectionId, long parentItemId, int level,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_parentItemId_level_Last(groupId,
				dictCollectionId, parentItemId, level, orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", parentItemId=");
		msg.append(parentItemId);

		msg.append(", level=");
		msg.append(level);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the last dict item in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_parentItemId_level_Last(long groupId,
		long dictCollectionId, long parentItemId, int level,
		OrderByComparator<DictItem> orderByComparator) {
		int count = countByF_parentItemId_level(groupId, dictCollectionId,
				parentItemId, level);

		if (count == 0) {
			return null;
		}

		List<DictItem> list = findByF_parentItemId_level(groupId,
				dictCollectionId, parentItemId, level, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict items before and after the current dict item in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item
	 * @throws NoSuchDictItemException if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem[] findByF_parentItemId_level_PrevAndNext(long dictItemId,
		long groupId, long dictCollectionId, long parentItemId, int level,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItem[] array = new DictItemImpl[3];

			array[0] = getByF_parentItemId_level_PrevAndNext(session, dictItem,
					groupId, dictCollectionId, parentItemId, level,
					orderByComparator, true);

			array[1] = dictItem;

			array[2] = getByF_parentItemId_level_PrevAndNext(session, dictItem,
					groupId, dictCollectionId, parentItemId, level,
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

	protected DictItem getByF_parentItemId_level_PrevAndNext(Session session,
		DictItem dictItem, long groupId, long dictCollectionId,
		long parentItemId, int level,
		OrderByComparator<DictItem> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_DICTITEM_WHERE);

		query.append(_FINDER_COLUMN_F_PARENTITEMID_LEVEL_GROUPID_2);

		query.append(_FINDER_COLUMN_F_PARENTITEMID_LEVEL_DICTCOLLECTIONID_2);

		query.append(_FINDER_COLUMN_F_PARENTITEMID_LEVEL_PARENTITEMID_2);

		query.append(_FINDER_COLUMN_F_PARENTITEMID_LEVEL_LEVEL_2);

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
			query.append(DictItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(dictCollectionId);

		qPos.add(parentItemId);

		qPos.add(level);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict items where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 */
	@Override
	public void removeByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level) {
		for (DictItem dictItem : findByF_parentItemId_level(groupId,
				dictCollectionId, parentItemId, level, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dictItem);
		}
	}

	/**
	 * Returns the number of dict items where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @return the number of matching dict items
	 */
	@Override
	public int countByF_parentItemId_level(long groupId, long dictCollectionId,
		long parentItemId, int level) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_PARENTITEMID_LEVEL;

		Object[] finderArgs = new Object[] {
				groupId, dictCollectionId, parentItemId, level
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_DICTITEM_WHERE);

			query.append(_FINDER_COLUMN_F_PARENTITEMID_LEVEL_GROUPID_2);

			query.append(_FINDER_COLUMN_F_PARENTITEMID_LEVEL_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_F_PARENTITEMID_LEVEL_PARENTITEMID_2);

			query.append(_FINDER_COLUMN_F_PARENTITEMID_LEVEL_LEVEL_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dictCollectionId);

				qPos.add(parentItemId);

				qPos.add(level);

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

	private static final String _FINDER_COLUMN_F_PARENTITEMID_LEVEL_GROUPID_2 = "dictItem.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_PARENTITEMID_LEVEL_DICTCOLLECTIONID_2 =
		"dictItem.dictCollectionId = ? AND ";
	private static final String _FINDER_COLUMN_F_PARENTITEMID_LEVEL_PARENTITEMID_2 =
		"dictItem.parentItemId = ? AND ";
	private static final String _FINDER_COLUMN_F_PARENTITEMID_LEVEL_LEVEL_2 = "dictItem.level = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_IC_DCI = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByIC_DCI",
			new String[] { String.class.getName(), Long.class.getName() },
			DictItemModelImpl.ITEMCODE_COLUMN_BITMASK |
			DictItemModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_IC_DCI = new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByIC_DCI",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict item where itemCode = &#63; and dictCollectionId = &#63; or throws a {@link NoSuchDictItemException} if it could not be found.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @return the matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByIC_DCI(String itemCode, long dictCollectionId)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByIC_DCI(itemCode, dictCollectionId);

		if (dictItem == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itemCode=");
			msg.append(itemCode);

			msg.append(", dictCollectionId=");
			msg.append(dictCollectionId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDictItemException(msg.toString());
		}

		return dictItem;
	}

	/**
	 * Returns the dict item where itemCode = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByIC_DCI(String itemCode, long dictCollectionId) {
		return fetchByIC_DCI(itemCode, dictCollectionId, true);
	}

	/**
	 * Returns the dict item where itemCode = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByIC_DCI(String itemCode, long dictCollectionId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { itemCode, dictCollectionId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_IC_DCI,
					finderArgs, this);
		}

		if (result instanceof DictItem) {
			DictItem dictItem = (DictItem)result;

			if (!Objects.equals(itemCode, dictItem.getItemCode()) ||
					(dictCollectionId != dictItem.getDictCollectionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTITEM_WHERE);

			boolean bindItemCode = false;

			if (itemCode == null) {
				query.append(_FINDER_COLUMN_IC_DCI_ITEMCODE_1);
			}
			else if (itemCode.equals("")) {
				query.append(_FINDER_COLUMN_IC_DCI_ITEMCODE_3);
			}
			else {
				bindItemCode = true;

				query.append(_FINDER_COLUMN_IC_DCI_ITEMCODE_2);
			}

			query.append(_FINDER_COLUMN_IC_DCI_DICTCOLLECTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemCode) {
					qPos.add(itemCode);
				}

				qPos.add(dictCollectionId);

				List<DictItem> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_IC_DCI,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictItemPersistenceImpl.fetchByIC_DCI(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictItem dictItem = list.get(0);

					result = dictItem;

					cacheResult(dictItem);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_IC_DCI, finderArgs);

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
			return (DictItem)result;
		}
	}

	/**
	 * Removes the dict item where itemCode = &#63; and dictCollectionId = &#63; from the database.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @return the dict item that was removed
	 */
	@Override
	public DictItem removeByIC_DCI(String itemCode, long dictCollectionId)
		throws NoSuchDictItemException {
		DictItem dictItem = findByIC_DCI(itemCode, dictCollectionId);

		return remove(dictItem);
	}

	/**
	 * Returns the number of dict items where itemCode = &#63; and dictCollectionId = &#63;.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @return the number of matching dict items
	 */
	@Override
	public int countByIC_DCI(String itemCode, long dictCollectionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_IC_DCI;

		Object[] finderArgs = new Object[] { itemCode, dictCollectionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEM_WHERE);

			boolean bindItemCode = false;

			if (itemCode == null) {
				query.append(_FINDER_COLUMN_IC_DCI_ITEMCODE_1);
			}
			else if (itemCode.equals("")) {
				query.append(_FINDER_COLUMN_IC_DCI_ITEMCODE_3);
			}
			else {
				bindItemCode = true;

				query.append(_FINDER_COLUMN_IC_DCI_ITEMCODE_2);
			}

			query.append(_FINDER_COLUMN_IC_DCI_DICTCOLLECTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemCode) {
					qPos.add(itemCode);
				}

				qPos.add(dictCollectionId);

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

	private static final String _FINDER_COLUMN_IC_DCI_ITEMCODE_1 = "dictItem.itemCode IS NULL AND ";
	private static final String _FINDER_COLUMN_IC_DCI_ITEMCODE_2 = "dictItem.itemCode = ? AND ";
	private static final String _FINDER_COLUMN_IC_DCI_ITEMCODE_3 = "(dictItem.itemCode IS NULL OR dictItem.itemCode = '') AND ";
	private static final String _FINDER_COLUMN_IC_DCI_DICTCOLLECTIONID_2 = "dictItem.dictCollectionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_TREEINDEX =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_treeIndex",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_TREEINDEX =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByF_treeIndex",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the dict items where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @return the matching dict items
	 */
	@Override
	public List<DictItem> findByF_treeIndex(long dictCollectionId,
		long parentItemId, String treeIndex) {
		return findByF_treeIndex(dictCollectionId, parentItemId, treeIndex,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict items where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @return the range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_treeIndex(long dictCollectionId,
		long parentItemId, String treeIndex, int start, int end) {
		return findByF_treeIndex(dictCollectionId, parentItemId, treeIndex,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict items where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_treeIndex(long dictCollectionId,
		long parentItemId, String treeIndex, int start, int end,
		OrderByComparator<DictItem> orderByComparator) {
		return findByF_treeIndex(dictCollectionId, parentItemId, treeIndex,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict items where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_treeIndex(long dictCollectionId,
		long parentItemId, String treeIndex, int start, int end,
		OrderByComparator<DictItem> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_TREEINDEX;
		finderArgs = new Object[] {
				dictCollectionId, parentItemId, treeIndex,
				
				start, end, orderByComparator
			};

		List<DictItem> list = null;

		if (retrieveFromCache) {
			list = (List<DictItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItem dictItem : list) {
					if ((dictCollectionId != dictItem.getDictCollectionId()) ||
							(parentItemId != dictItem.getParentItemId()) ||
							!StringUtil.wildcardMatches(
								dictItem.getTreeIndex(), treeIndex, '_', '%',
								'\\', true)) {
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

			query.append(_SQL_SELECT_DICTITEM_WHERE);

			query.append(_FINDER_COLUMN_F_TREEINDEX_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_F_TREEINDEX_PARENTITEMID_2);

			boolean bindTreeIndex = false;

			if (treeIndex == null) {
				query.append(_FINDER_COLUMN_F_TREEINDEX_TREEINDEX_1);
			}
			else if (treeIndex.equals("")) {
				query.append(_FINDER_COLUMN_F_TREEINDEX_TREEINDEX_3);
			}
			else {
				bindTreeIndex = true;

				query.append(_FINDER_COLUMN_F_TREEINDEX_TREEINDEX_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dictCollectionId);

				qPos.add(parentItemId);

				if (bindTreeIndex) {
					qPos.add(treeIndex);
				}

				if (!pagination) {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_treeIndex_First(long dictCollectionId,
		long parentItemId, String treeIndex,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_treeIndex_First(dictCollectionId,
				parentItemId, treeIndex, orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", parentItemId=");
		msg.append(parentItemId);

		msg.append(", treeIndex=");
		msg.append(treeIndex);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the first dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_treeIndex_First(long dictCollectionId,
		long parentItemId, String treeIndex,
		OrderByComparator<DictItem> orderByComparator) {
		List<DictItem> list = findByF_treeIndex(dictCollectionId, parentItemId,
				treeIndex, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_treeIndex_Last(long dictCollectionId,
		long parentItemId, String treeIndex,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_treeIndex_Last(dictCollectionId,
				parentItemId, treeIndex, orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", parentItemId=");
		msg.append(parentItemId);

		msg.append(", treeIndex=");
		msg.append(treeIndex);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the last dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_treeIndex_Last(long dictCollectionId,
		long parentItemId, String treeIndex,
		OrderByComparator<DictItem> orderByComparator) {
		int count = countByF_treeIndex(dictCollectionId, parentItemId, treeIndex);

		if (count == 0) {
			return null;
		}

		List<DictItem> list = findByF_treeIndex(dictCollectionId, parentItemId,
				treeIndex, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict items before and after the current dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item
	 * @throws NoSuchDictItemException if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem[] findByF_treeIndex_PrevAndNext(long dictItemId,
		long dictCollectionId, long parentItemId, String treeIndex,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItem[] array = new DictItemImpl[3];

			array[0] = getByF_treeIndex_PrevAndNext(session, dictItem,
					dictCollectionId, parentItemId, treeIndex,
					orderByComparator, true);

			array[1] = dictItem;

			array[2] = getByF_treeIndex_PrevAndNext(session, dictItem,
					dictCollectionId, parentItemId, treeIndex,
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

	protected DictItem getByF_treeIndex_PrevAndNext(Session session,
		DictItem dictItem, long dictCollectionId, long parentItemId,
		String treeIndex, OrderByComparator<DictItem> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_DICTITEM_WHERE);

		query.append(_FINDER_COLUMN_F_TREEINDEX_DICTCOLLECTIONID_2);

		query.append(_FINDER_COLUMN_F_TREEINDEX_PARENTITEMID_2);

		boolean bindTreeIndex = false;

		if (treeIndex == null) {
			query.append(_FINDER_COLUMN_F_TREEINDEX_TREEINDEX_1);
		}
		else if (treeIndex.equals("")) {
			query.append(_FINDER_COLUMN_F_TREEINDEX_TREEINDEX_3);
		}
		else {
			bindTreeIndex = true;

			query.append(_FINDER_COLUMN_F_TREEINDEX_TREEINDEX_2);
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
			query.append(DictItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dictCollectionId);

		qPos.add(parentItemId);

		if (bindTreeIndex) {
			qPos.add(treeIndex);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict items where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63; from the database.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 */
	@Override
	public void removeByF_treeIndex(long dictCollectionId, long parentItemId,
		String treeIndex) {
		for (DictItem dictItem : findByF_treeIndex(dictCollectionId,
				parentItemId, treeIndex, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(dictItem);
		}
	}

	/**
	 * Returns the number of dict items where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @return the number of matching dict items
	 */
	@Override
	public int countByF_treeIndex(long dictCollectionId, long parentItemId,
		String treeIndex) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_TREEINDEX;

		Object[] finderArgs = new Object[] {
				dictCollectionId, parentItemId, treeIndex
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DICTITEM_WHERE);

			query.append(_FINDER_COLUMN_F_TREEINDEX_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_F_TREEINDEX_PARENTITEMID_2);

			boolean bindTreeIndex = false;

			if (treeIndex == null) {
				query.append(_FINDER_COLUMN_F_TREEINDEX_TREEINDEX_1);
			}
			else if (treeIndex.equals("")) {
				query.append(_FINDER_COLUMN_F_TREEINDEX_TREEINDEX_3);
			}
			else {
				bindTreeIndex = true;

				query.append(_FINDER_COLUMN_F_TREEINDEX_TREEINDEX_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dictCollectionId);

				qPos.add(parentItemId);

				if (bindTreeIndex) {
					qPos.add(treeIndex);
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

	private static final String _FINDER_COLUMN_F_TREEINDEX_DICTCOLLECTIONID_2 = "dictItem.dictCollectionId = ? AND ";
	private static final String _FINDER_COLUMN_F_TREEINDEX_PARENTITEMID_2 = "dictItem.parentItemId = ? AND ";
	private static final String _FINDER_COLUMN_F_TREEINDEX_TREEINDEX_1 = "dictItem.treeIndex IS NULL";
	private static final String _FINDER_COLUMN_F_TREEINDEX_TREEINDEX_2 = "dictItem.treeIndex LIKE ?";
	private static final String _FINDER_COLUMN_F_TREEINDEX_TREEINDEX_3 = "(dictItem.treeIndex IS NULL OR dictItem.treeIndex LIKE '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTCOLLECTIONID_PARENTITEMID =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_dictCollectionId_parentItemId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID_PARENTITEMID =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_dictCollectionId_parentItemId",
			new String[] { Long.class.getName(), Long.class.getName() },
			DictItemModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK |
			DictItemModelImpl.PARENTITEMID_COLUMN_BITMASK |
			DictItemModelImpl.SIBLING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID_PARENTITEMID =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_dictCollectionId_parentItemId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict items where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @return the matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId) {
		return findByF_dictCollectionId_parentItemId(dictCollectionId,
			parentItemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict items where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @return the range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId, int start, int end) {
		return findByF_dictCollectionId_parentItemId(dictCollectionId,
			parentItemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict items where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId, int start, int end,
		OrderByComparator<DictItem> orderByComparator) {
		return findByF_dictCollectionId_parentItemId(dictCollectionId,
			parentItemId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict items where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId, int start, int end,
		OrderByComparator<DictItem> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID_PARENTITEMID;
			finderArgs = new Object[] { dictCollectionId, parentItemId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTCOLLECTIONID_PARENTITEMID;
			finderArgs = new Object[] {
					dictCollectionId, parentItemId,
					
					start, end, orderByComparator
				};
		}

		List<DictItem> list = null;

		if (retrieveFromCache) {
			list = (List<DictItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItem dictItem : list) {
					if ((dictCollectionId != dictItem.getDictCollectionId()) ||
							(parentItemId != dictItem.getParentItemId())) {
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

			query.append(_SQL_SELECT_DICTITEM_WHERE);

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONID_PARENTITEMID_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONID_PARENTITEMID_PARENTITEMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dictCollectionId);

				qPos.add(parentItemId);

				if (!pagination) {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_dictCollectionId_parentItemId_First(
		long dictCollectionId, long parentItemId,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_dictCollectionId_parentItemId_First(dictCollectionId,
				parentItemId, orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", parentItemId=");
		msg.append(parentItemId);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the first dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_dictCollectionId_parentItemId_First(
		long dictCollectionId, long parentItemId,
		OrderByComparator<DictItem> orderByComparator) {
		List<DictItem> list = findByF_dictCollectionId_parentItemId(dictCollectionId,
				parentItemId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_dictCollectionId_parentItemId_Last(
		long dictCollectionId, long parentItemId,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_dictCollectionId_parentItemId_Last(dictCollectionId,
				parentItemId, orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", parentItemId=");
		msg.append(parentItemId);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the last dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_dictCollectionId_parentItemId_Last(
		long dictCollectionId, long parentItemId,
		OrderByComparator<DictItem> orderByComparator) {
		int count = countByF_dictCollectionId_parentItemId(dictCollectionId,
				parentItemId);

		if (count == 0) {
			return null;
		}

		List<DictItem> list = findByF_dictCollectionId_parentItemId(dictCollectionId,
				parentItemId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict items before and after the current dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item
	 * @throws NoSuchDictItemException if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem[] findByF_dictCollectionId_parentItemId_PrevAndNext(
		long dictItemId, long dictCollectionId, long parentItemId,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItem[] array = new DictItemImpl[3];

			array[0] = getByF_dictCollectionId_parentItemId_PrevAndNext(session,
					dictItem, dictCollectionId, parentItemId,
					orderByComparator, true);

			array[1] = dictItem;

			array[2] = getByF_dictCollectionId_parentItemId_PrevAndNext(session,
					dictItem, dictCollectionId, parentItemId,
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

	protected DictItem getByF_dictCollectionId_parentItemId_PrevAndNext(
		Session session, DictItem dictItem, long dictCollectionId,
		long parentItemId, OrderByComparator<DictItem> orderByComparator,
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

		query.append(_SQL_SELECT_DICTITEM_WHERE);

		query.append(_FINDER_COLUMN_F_DICTCOLLECTIONID_PARENTITEMID_DICTCOLLECTIONID_2);

		query.append(_FINDER_COLUMN_F_DICTCOLLECTIONID_PARENTITEMID_PARENTITEMID_2);

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
			query.append(DictItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dictCollectionId);

		qPos.add(parentItemId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict items where dictCollectionId = &#63; and parentItemId = &#63; from the database.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 */
	@Override
	public void removeByF_dictCollectionId_parentItemId(long dictCollectionId,
		long parentItemId) {
		for (DictItem dictItem : findByF_dictCollectionId_parentItemId(
				dictCollectionId, parentItemId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dictItem);
		}
	}

	/**
	 * Returns the number of dict items where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @return the number of matching dict items
	 */
	@Override
	public int countByF_dictCollectionId_parentItemId(long dictCollectionId,
		long parentItemId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID_PARENTITEMID;

		Object[] finderArgs = new Object[] { dictCollectionId, parentItemId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEM_WHERE);

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONID_PARENTITEMID_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONID_PARENTITEMID_PARENTITEMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dictCollectionId);

				qPos.add(parentItemId);

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

	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONID_PARENTITEMID_DICTCOLLECTIONID_2 =
		"dictItem.dictCollectionId = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONID_PARENTITEMID_PARENTITEMID_2 =
		"dictItem.parentItemId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTITEMNEWERTHAN =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, DictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_dictItemNewerThan",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_DICTITEMNEWERTHAN =
		new FinderPath(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_dictItemNewerThan",
			new String[] { Date.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict items where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @return the matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictItemNewerThan(Date modifiedDate,
		long groupId) {
		return findByF_dictItemNewerThan(modifiedDate, groupId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict items where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @return the range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictItemNewerThan(Date modifiedDate,
		long groupId, int start, int end) {
		return findByF_dictItemNewerThan(modifiedDate, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict items where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictItemNewerThan(Date modifiedDate,
		long groupId, int start, int end,
		OrderByComparator<DictItem> orderByComparator) {
		return findByF_dictItemNewerThan(modifiedDate, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict items where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict items
	 */
	@Override
	public List<DictItem> findByF_dictItemNewerThan(Date modifiedDate,
		long groupId, int start, int end,
		OrderByComparator<DictItem> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTITEMNEWERTHAN;
		finderArgs = new Object[] {
				_getTime(modifiedDate), groupId,
				
				start, end, orderByComparator
			};

		List<DictItem> list = null;

		if (retrieveFromCache) {
			list = (List<DictItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItem dictItem : list) {
					if ((modifiedDate.getTime() > dictItem.getModifiedDate()
															  .getTime()) ||
							(groupId != dictItem.getGroupId())) {
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

			query.append(_SQL_SELECT_DICTITEM_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_DICTITEMNEWERTHAN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_DICTITEMNEWERTHAN_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_F_DICTITEMNEWERTHAN_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_dictItemNewerThan_First(Date modifiedDate,
		long groupId, OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_dictItemNewerThan_First(modifiedDate,
				groupId, orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the first dict item in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_dictItemNewerThan_First(Date modifiedDate,
		long groupId, OrderByComparator<DictItem> orderByComparator) {
		List<DictItem> list = findByF_dictItemNewerThan(modifiedDate, groupId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item
	 * @throws NoSuchDictItemException if a matching dict item could not be found
	 */
	@Override
	public DictItem findByF_dictItemNewerThan_Last(Date modifiedDate,
		long groupId, OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByF_dictItemNewerThan_Last(modifiedDate,
				groupId, orderByComparator);

		if (dictItem != null) {
			return dictItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictItemException(msg.toString());
	}

	/**
	 * Returns the last dict item in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	 */
	@Override
	public DictItem fetchByF_dictItemNewerThan_Last(Date modifiedDate,
		long groupId, OrderByComparator<DictItem> orderByComparator) {
		int count = countByF_dictItemNewerThan(modifiedDate, groupId);

		if (count == 0) {
			return null;
		}

		List<DictItem> list = findByF_dictItemNewerThan(modifiedDate, groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict items before and after the current dict item in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item
	 * @throws NoSuchDictItemException if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem[] findByF_dictItemNewerThan_PrevAndNext(long dictItemId,
		Date modifiedDate, long groupId,
		OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException {
		DictItem dictItem = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItem[] array = new DictItemImpl[3];

			array[0] = getByF_dictItemNewerThan_PrevAndNext(session, dictItem,
					modifiedDate, groupId, orderByComparator, true);

			array[1] = dictItem;

			array[2] = getByF_dictItemNewerThan_PrevAndNext(session, dictItem,
					modifiedDate, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DictItem getByF_dictItemNewerThan_PrevAndNext(Session session,
		DictItem dictItem, Date modifiedDate, long groupId,
		OrderByComparator<DictItem> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTITEM_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_F_DICTITEMNEWERTHAN_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_F_DICTITEMNEWERTHAN_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_F_DICTITEMNEWERTHAN_GROUPID_2);

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
			query.append(DictItemModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict items where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_dictItemNewerThan(Date modifiedDate, long groupId) {
		for (DictItem dictItem : findByF_dictItemNewerThan(modifiedDate,
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictItem);
		}
	}

	/**
	 * Returns the number of dict items where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @return the number of matching dict items
	 */
	@Override
	public int countByF_dictItemNewerThan(Date modifiedDate, long groupId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_DICTITEMNEWERTHAN;

		Object[] finderArgs = new Object[] { _getTime(modifiedDate), groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEM_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_DICTITEMNEWERTHAN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_DICTITEMNEWERTHAN_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_F_DICTITEMNEWERTHAN_GROUPID_2);

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

	private static final String _FINDER_COLUMN_F_DICTITEMNEWERTHAN_MODIFIEDDATE_1 =
		"dictItem.modifiedDate IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMNEWERTHAN_MODIFIEDDATE_2 =
		"dictItem.modifiedDate >= ? AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMNEWERTHAN_GROUPID_2 = "dictItem.groupId = ?";

	public DictItemPersistenceImpl() {
		setModelClass(DictItem.class);

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
	 * Caches the dict item in the entity cache if it is enabled.
	 *
	 * @param dictItem the dict item
	 */
	@Override
	public void cacheResult(DictItem dictItem) {
		entityCache.putResult(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemImpl.class, dictItem.getPrimaryKey(), dictItem);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dictItem.getUuid(), dictItem.getGroupId() }, dictItem);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE,
			new Object[] { dictItem.getItemCode(), dictItem.getGroupId() },
			dictItem);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
			new Object[] {
				dictItem.getItemCode(), dictItem.getDictCollectionId(),
				dictItem.getGroupId()
			}, dictItem);

		finderCache.putResult(FINDER_PATH_FETCH_BY_IC_DCI,
			new Object[] { dictItem.getItemCode(), dictItem.getDictCollectionId() },
			dictItem);

		dictItem.resetOriginalValues();
	}

	/**
	 * Caches the dict items in the entity cache if it is enabled.
	 *
	 * @param dictItems the dict items
	 */
	@Override
	public void cacheResult(List<DictItem> dictItems) {
		for (DictItem dictItem : dictItems) {
			if (entityCache.getResult(DictItemModelImpl.ENTITY_CACHE_ENABLED,
						DictItemImpl.class, dictItem.getPrimaryKey()) == null) {
				cacheResult(dictItem);
			}
			else {
				dictItem.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dict items.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DictItemImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dict item.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DictItem dictItem) {
		entityCache.removeResult(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemImpl.class, dictItem.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DictItemModelImpl)dictItem, true);
	}

	@Override
	public void clearCache(List<DictItem> dictItems) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DictItem dictItem : dictItems) {
			entityCache.removeResult(DictItemModelImpl.ENTITY_CACHE_ENABLED,
				DictItemImpl.class, dictItem.getPrimaryKey());

			clearUniqueFindersCache((DictItemModelImpl)dictItem, true);
		}
	}

	protected void cacheUniqueFindersCache(DictItemModelImpl dictItemModelImpl) {
		Object[] args = new Object[] {
				dictItemModelImpl.getUuid(), dictItemModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dictItemModelImpl, false);

		args = new Object[] {
				dictItemModelImpl.getItemCode(), dictItemModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_DICTITEMCODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE, args,
			dictItemModelImpl, false);

		args = new Object[] {
				dictItemModelImpl.getItemCode(),
				dictItemModelImpl.getDictCollectionId(),
				dictItemModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
			args, dictItemModelImpl, false);

		args = new Object[] {
				dictItemModelImpl.getItemCode(),
				dictItemModelImpl.getDictCollectionId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_IC_DCI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_IC_DCI, args,
			dictItemModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DictItemModelImpl dictItemModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dictItemModelImpl.getUuid(), dictItemModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dictItemModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictItemModelImpl.getOriginalUuid(),
					dictItemModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dictItemModelImpl.getItemCode(),
					dictItemModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE, args);
		}

		if ((dictItemModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_DICTITEMCODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictItemModelImpl.getOriginalItemCode(),
					dictItemModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dictItemModelImpl.getItemCode(),
					dictItemModelImpl.getDictCollectionId(),
					dictItemModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
				args);
		}

		if ((dictItemModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictItemModelImpl.getOriginalItemCode(),
					dictItemModelImpl.getOriginalDictCollectionId(),
					dictItemModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dictItemModelImpl.getItemCode(),
					dictItemModelImpl.getDictCollectionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_IC_DCI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_IC_DCI, args);
		}

		if ((dictItemModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_IC_DCI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictItemModelImpl.getOriginalItemCode(),
					dictItemModelImpl.getOriginalDictCollectionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_IC_DCI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_IC_DCI, args);
		}
	}

	/**
	 * Creates a new dict item with the primary key. Does not add the dict item to the database.
	 *
	 * @param dictItemId the primary key for the new dict item
	 * @return the new dict item
	 */
	@Override
	public DictItem create(long dictItemId) {
		DictItem dictItem = new DictItemImpl();

		dictItem.setNew(true);
		dictItem.setPrimaryKey(dictItemId);

		String uuid = PortalUUIDUtil.generate();

		dictItem.setUuid(uuid);

		dictItem.setCompanyId(companyProvider.getCompanyId());

		return dictItem;
	}

	/**
	 * Removes the dict item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dictItemId the primary key of the dict item
	 * @return the dict item that was removed
	 * @throws NoSuchDictItemException if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem remove(long dictItemId) throws NoSuchDictItemException {
		return remove((Serializable)dictItemId);
	}

	/**
	 * Removes the dict item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dict item
	 * @return the dict item that was removed
	 * @throws NoSuchDictItemException if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem remove(Serializable primaryKey)
		throws NoSuchDictItemException {
		Session session = null;

		try {
			session = openSession();

			DictItem dictItem = (DictItem)session.get(DictItemImpl.class,
					primaryKey);

			if (dictItem == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDictItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dictItem);
		}
		catch (NoSuchDictItemException nsee) {
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
	protected DictItem removeImpl(DictItem dictItem) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dictItem)) {
				dictItem = (DictItem)session.get(DictItemImpl.class,
						dictItem.getPrimaryKeyObj());
			}

			if (dictItem != null) {
				session.delete(dictItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dictItem != null) {
			clearCache(dictItem);
		}

		return dictItem;
	}

	@Override
	public DictItem updateImpl(DictItem dictItem) {
		boolean isNew = dictItem.isNew();

		if (!(dictItem instanceof DictItemModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dictItem.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dictItem);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dictItem proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DictItem implementation " +
				dictItem.getClass());
		}

		DictItemModelImpl dictItemModelImpl = (DictItemModelImpl)dictItem;

		if (Validator.isNull(dictItem.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dictItem.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dictItem.getCreateDate() == null)) {
			if (serviceContext == null) {
				dictItem.setCreateDate(now);
			}
			else {
				dictItem.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dictItemModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dictItem.setModifiedDate(now);
			}
			else {
				dictItem.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dictItem.isNew()) {
				session.save(dictItem);

				dictItem.setNew(false);
			}
			else {
				dictItem = (DictItem)session.merge(dictItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DictItemModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dictItemModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					dictItemModelImpl.getUuid(),
					dictItemModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { dictItemModelImpl.getDictCollectionId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID,
				args);

			args = new Object[] {
					dictItemModelImpl.getDictCollectionId(),
					dictItemModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMBYGROUP,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMBYGROUP,
				args);

			args = new Object[] { dictItemModelImpl.getParentItemId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTITEMID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID,
				args);

			args = new Object[] {
					dictItemModelImpl.getGroupId(),
					dictItemModelImpl.getDictCollectionId(),
					dictItemModelImpl.getParentItemId(),
					dictItemModelImpl.getLevel()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTITEMID_LEVEL,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID_LEVEL,
				args);

			args = new Object[] {
					dictItemModelImpl.getDictCollectionId(),
					dictItemModelImpl.getParentItemId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID_PARENTITEMID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID_PARENTITEMID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dictItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { dictItemModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dictItemModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dictItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemModelImpl.getOriginalUuid(),
						dictItemModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dictItemModelImpl.getUuid(),
						dictItemModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((dictItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemModelImpl.getOriginalDictCollectionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID,
					args);

				args = new Object[] { dictItemModelImpl.getDictCollectionId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID,
					args);
			}

			if ((dictItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMBYGROUP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemModelImpl.getOriginalDictCollectionId(),
						dictItemModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMBYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMBYGROUP,
					args);

				args = new Object[] {
						dictItemModelImpl.getDictCollectionId(),
						dictItemModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMBYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMBYGROUP,
					args);
			}

			if ((dictItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemModelImpl.getOriginalParentItemId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTITEMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID,
					args);

				args = new Object[] { dictItemModelImpl.getParentItemId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTITEMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID,
					args);
			}

			if ((dictItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID_LEVEL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemModelImpl.getOriginalGroupId(),
						dictItemModelImpl.getOriginalDictCollectionId(),
						dictItemModelImpl.getOriginalParentItemId(),
						dictItemModelImpl.getOriginalLevel()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTITEMID_LEVEL,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID_LEVEL,
					args);

				args = new Object[] {
						dictItemModelImpl.getGroupId(),
						dictItemModelImpl.getDictCollectionId(),
						dictItemModelImpl.getParentItemId(),
						dictItemModelImpl.getLevel()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTITEMID_LEVEL,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID_LEVEL,
					args);
			}

			if ((dictItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID_PARENTITEMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemModelImpl.getOriginalDictCollectionId(),
						dictItemModelImpl.getOriginalParentItemId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID_PARENTITEMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID_PARENTITEMID,
					args);

				args = new Object[] {
						dictItemModelImpl.getDictCollectionId(),
						dictItemModelImpl.getParentItemId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID_PARENTITEMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID_PARENTITEMID,
					args);
			}
		}

		entityCache.putResult(DictItemModelImpl.ENTITY_CACHE_ENABLED,
			DictItemImpl.class, dictItem.getPrimaryKey(), dictItem, false);

		clearUniqueFindersCache(dictItemModelImpl, false);
		cacheUniqueFindersCache(dictItemModelImpl);

		dictItem.resetOriginalValues();

		return dictItem;
	}

	/**
	 * Returns the dict item with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict item
	 * @return the dict item
	 * @throws NoSuchDictItemException if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDictItemException {
		DictItem dictItem = fetchByPrimaryKey(primaryKey);

		if (dictItem == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDictItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dictItem;
	}

	/**
	 * Returns the dict item with the primary key or throws a {@link NoSuchDictItemException} if it could not be found.
	 *
	 * @param dictItemId the primary key of the dict item
	 * @return the dict item
	 * @throws NoSuchDictItemException if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem findByPrimaryKey(long dictItemId)
		throws NoSuchDictItemException {
		return findByPrimaryKey((Serializable)dictItemId);
	}

	/**
	 * Returns the dict item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict item
	 * @return the dict item, or <code>null</code> if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DictItemModelImpl.ENTITY_CACHE_ENABLED,
				DictItemImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DictItem dictItem = (DictItem)serializable;

		if (dictItem == null) {
			Session session = null;

			try {
				session = openSession();

				dictItem = (DictItem)session.get(DictItemImpl.class, primaryKey);

				if (dictItem != null) {
					cacheResult(dictItem);
				}
				else {
					entityCache.putResult(DictItemModelImpl.ENTITY_CACHE_ENABLED,
						DictItemImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DictItemModelImpl.ENTITY_CACHE_ENABLED,
					DictItemImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dictItem;
	}

	/**
	 * Returns the dict item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dictItemId the primary key of the dict item
	 * @return the dict item, or <code>null</code> if a dict item with the primary key could not be found
	 */
	@Override
	public DictItem fetchByPrimaryKey(long dictItemId) {
		return fetchByPrimaryKey((Serializable)dictItemId);
	}

	@Override
	public Map<Serializable, DictItem> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DictItem> map = new HashMap<Serializable, DictItem>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DictItem dictItem = fetchByPrimaryKey(primaryKey);

			if (dictItem != null) {
				map.put(primaryKey, dictItem);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DictItemModelImpl.ENTITY_CACHE_ENABLED,
					DictItemImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DictItem)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DICTITEM_WHERE_PKS_IN);

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

			for (DictItem dictItem : (List<DictItem>)q.list()) {
				map.put(dictItem.getPrimaryKeyObj(), dictItem);

				cacheResult(dictItem);

				uncachedPrimaryKeys.remove(dictItem.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DictItemModelImpl.ENTITY_CACHE_ENABLED,
					DictItemImpl.class, primaryKey, nullModel);
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
	 * Returns all the dict items.
	 *
	 * @return the dict items
	 */
	@Override
	public List<DictItem> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @return the range of dict items
	 */
	@Override
	public List<DictItem> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dict items
	 */
	@Override
	public List<DictItem> findAll(int start, int end,
		OrderByComparator<DictItem> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict items
	 * @param end the upper bound of the range of dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dict items
	 */
	@Override
	public List<DictItem> findAll(int start, int end,
		OrderByComparator<DictItem> orderByComparator, boolean retrieveFromCache) {
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

		List<DictItem> list = null;

		if (retrieveFromCache) {
			list = (List<DictItem>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DICTITEM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DICTITEM;

				if (pagination) {
					sql = sql.concat(DictItemModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItem>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the dict items from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DictItem dictItem : findAll()) {
			remove(dictItem);
		}
	}

	/**
	 * Returns the number of dict items.
	 *
	 * @return the number of dict items
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DICTITEM);

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
		return DictItemModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dict item persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DictItemImpl.class.getName());
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

	private static final String _SQL_SELECT_DICTITEM = "SELECT dictItem FROM DictItem dictItem";
	private static final String _SQL_SELECT_DICTITEM_WHERE_PKS_IN = "SELECT dictItem FROM DictItem dictItem WHERE dictItemId IN (";
	private static final String _SQL_SELECT_DICTITEM_WHERE = "SELECT dictItem FROM DictItem dictItem WHERE ";
	private static final String _SQL_COUNT_DICTITEM = "SELECT COUNT(dictItem) FROM DictItem dictItem";
	private static final String _SQL_COUNT_DICTITEM_WHERE = "SELECT COUNT(dictItem) FROM DictItem dictItem WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dictItem.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DictItem exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DictItem exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DictItemPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}