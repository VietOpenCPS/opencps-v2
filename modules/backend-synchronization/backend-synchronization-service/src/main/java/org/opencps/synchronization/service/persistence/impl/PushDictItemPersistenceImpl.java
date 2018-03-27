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

import org.opencps.synchronization.exception.NoSuchPushDictItemException;
import org.opencps.synchronization.model.PushDictItem;
import org.opencps.synchronization.model.impl.PushDictItemImpl;
import org.opencps.synchronization.model.impl.PushDictItemModelImpl;
import org.opencps.synchronization.service.persistence.PushDictItemPersistence;

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
 * The persistence implementation for the push dict item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see PushDictItemPersistence
 * @see org.opencps.synchronization.service.persistence.PushDictItemUtil
 * @generated
 */
@ProviderType
public class PushDictItemPersistenceImpl extends BasePersistenceImpl<PushDictItem>
	implements PushDictItemPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PushDictItemUtil} to access the push dict item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PushDictItemImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, PushDictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, PushDictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, PushDictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, PushDictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PushDictItemModelImpl.UUID_COLUMN_BITMASK |
			PushDictItemModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the push dict items where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching push dict items
	 */
	@Override
	public List<PushDictItem> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push dict items where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of push dict items
	 * @param end the upper bound of the range of push dict items (not inclusive)
	 * @return the range of matching push dict items
	 */
	@Override
	public List<PushDictItem> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the push dict items where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of push dict items
	 * @param end the upper bound of the range of push dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching push dict items
	 */
	@Override
	public List<PushDictItem> findByUuid(String uuid, int start, int end,
		OrderByComparator<PushDictItem> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push dict items where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of push dict items
	 * @param end the upper bound of the range of push dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching push dict items
	 */
	@Override
	public List<PushDictItem> findByUuid(String uuid, int start, int end,
		OrderByComparator<PushDictItem> orderByComparator,
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

		List<PushDictItem> list = null;

		if (retrieveFromCache) {
			list = (List<PushDictItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PushDictItem pushDictItem : list) {
					if (!Objects.equals(uuid, pushDictItem.getUuid())) {
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

			query.append(_SQL_SELECT_PUSHDICTITEM_WHERE);

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
				query.append(PushDictItemModelImpl.ORDER_BY_JPQL);
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
					list = (List<PushDictItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushDictItem>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first push dict item in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push dict item
	 * @throws NoSuchPushDictItemException if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem findByUuid_First(String uuid,
		OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException {
		PushDictItem pushDictItem = fetchByUuid_First(uuid, orderByComparator);

		if (pushDictItem != null) {
			return pushDictItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushDictItemException(msg.toString());
	}

	/**
	 * Returns the first push dict item in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push dict item, or <code>null</code> if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem fetchByUuid_First(String uuid,
		OrderByComparator<PushDictItem> orderByComparator) {
		List<PushDictItem> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last push dict item in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push dict item
	 * @throws NoSuchPushDictItemException if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem findByUuid_Last(String uuid,
		OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException {
		PushDictItem pushDictItem = fetchByUuid_Last(uuid, orderByComparator);

		if (pushDictItem != null) {
			return pushDictItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushDictItemException(msg.toString());
	}

	/**
	 * Returns the last push dict item in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push dict item, or <code>null</code> if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem fetchByUuid_Last(String uuid,
		OrderByComparator<PushDictItem> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PushDictItem> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the push dict items before and after the current push dict item in the ordered set where uuid = &#63;.
	 *
	 * @param pushDictItemId the primary key of the current push dict item
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next push dict item
	 * @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	 */
	@Override
	public PushDictItem[] findByUuid_PrevAndNext(long pushDictItemId,
		String uuid, OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException {
		PushDictItem pushDictItem = findByPrimaryKey(pushDictItemId);

		Session session = null;

		try {
			session = openSession();

			PushDictItem[] array = new PushDictItemImpl[3];

			array[0] = getByUuid_PrevAndNext(session, pushDictItem, uuid,
					orderByComparator, true);

			array[1] = pushDictItem;

			array[2] = getByUuid_PrevAndNext(session, pushDictItem, uuid,
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

	protected PushDictItem getByUuid_PrevAndNext(Session session,
		PushDictItem pushDictItem, String uuid,
		OrderByComparator<PushDictItem> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PUSHDICTITEM_WHERE);

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
			query.append(PushDictItemModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(pushDictItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PushDictItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the push dict items where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PushDictItem pushDictItem : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(pushDictItem);
		}
	}

	/**
	 * Returns the number of push dict items where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching push dict items
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PUSHDICTITEM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "pushDictItem.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "pushDictItem.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(pushDictItem.uuid IS NULL OR pushDictItem.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, PushDictItemImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PushDictItemModelImpl.UUID_COLUMN_BITMASK |
			PushDictItemModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the push dict item where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPushDictItemException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching push dict item
	 * @throws NoSuchPushDictItemException if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem findByUUID_G(String uuid, long groupId)
		throws NoSuchPushDictItemException {
		PushDictItem pushDictItem = fetchByUUID_G(uuid, groupId);

		if (pushDictItem == null) {
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

			throw new NoSuchPushDictItemException(msg.toString());
		}

		return pushDictItem;
	}

	/**
	 * Returns the push dict item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching push dict item, or <code>null</code> if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the push dict item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching push dict item, or <code>null</code> if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PushDictItem) {
			PushDictItem pushDictItem = (PushDictItem)result;

			if (!Objects.equals(uuid, pushDictItem.getUuid()) ||
					(groupId != pushDictItem.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PUSHDICTITEM_WHERE);

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

				List<PushDictItem> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					PushDictItem pushDictItem = list.get(0);

					result = pushDictItem;

					cacheResult(pushDictItem);

					if ((pushDictItem.getUuid() == null) ||
							!pushDictItem.getUuid().equals(uuid) ||
							(pushDictItem.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, pushDictItem);
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
			return (PushDictItem)result;
		}
	}

	/**
	 * Removes the push dict item where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the push dict item that was removed
	 */
	@Override
	public PushDictItem removeByUUID_G(String uuid, long groupId)
		throws NoSuchPushDictItemException {
		PushDictItem pushDictItem = findByUUID_G(uuid, groupId);

		return remove(pushDictItem);
	}

	/**
	 * Returns the number of push dict items where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching push dict items
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PUSHDICTITEM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "pushDictItem.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "pushDictItem.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(pushDictItem.uuid IS NULL OR pushDictItem.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "pushDictItem.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, PushDictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, PushDictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PushDictItemModelImpl.UUID_COLUMN_BITMASK |
			PushDictItemModelImpl.COMPANYID_COLUMN_BITMASK |
			PushDictItemModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the push dict items where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching push dict items
	 */
	@Override
	public List<PushDictItem> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push dict items where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of push dict items
	 * @param end the upper bound of the range of push dict items (not inclusive)
	 * @return the range of matching push dict items
	 */
	@Override
	public List<PushDictItem> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the push dict items where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of push dict items
	 * @param end the upper bound of the range of push dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching push dict items
	 */
	@Override
	public List<PushDictItem> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PushDictItem> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push dict items where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of push dict items
	 * @param end the upper bound of the range of push dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching push dict items
	 */
	@Override
	public List<PushDictItem> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PushDictItem> orderByComparator,
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

		List<PushDictItem> list = null;

		if (retrieveFromCache) {
			list = (List<PushDictItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PushDictItem pushDictItem : list) {
					if (!Objects.equals(uuid, pushDictItem.getUuid()) ||
							(companyId != pushDictItem.getCompanyId())) {
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

			query.append(_SQL_SELECT_PUSHDICTITEM_WHERE);

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
				query.append(PushDictItemModelImpl.ORDER_BY_JPQL);
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
					list = (List<PushDictItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushDictItem>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push dict item
	 * @throws NoSuchPushDictItemException if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException {
		PushDictItem pushDictItem = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (pushDictItem != null) {
			return pushDictItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushDictItemException(msg.toString());
	}

	/**
	 * Returns the first push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push dict item, or <code>null</code> if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PushDictItem> orderByComparator) {
		List<PushDictItem> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push dict item
	 * @throws NoSuchPushDictItemException if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException {
		PushDictItem pushDictItem = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (pushDictItem != null) {
			return pushDictItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushDictItemException(msg.toString());
	}

	/**
	 * Returns the last push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push dict item, or <code>null</code> if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PushDictItem> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<PushDictItem> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the push dict items before and after the current push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param pushDictItemId the primary key of the current push dict item
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next push dict item
	 * @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	 */
	@Override
	public PushDictItem[] findByUuid_C_PrevAndNext(long pushDictItemId,
		String uuid, long companyId,
		OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException {
		PushDictItem pushDictItem = findByPrimaryKey(pushDictItemId);

		Session session = null;

		try {
			session = openSession();

			PushDictItem[] array = new PushDictItemImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, pushDictItem, uuid,
					companyId, orderByComparator, true);

			array[1] = pushDictItem;

			array[2] = getByUuid_C_PrevAndNext(session, pushDictItem, uuid,
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

	protected PushDictItem getByUuid_C_PrevAndNext(Session session,
		PushDictItem pushDictItem, String uuid, long companyId,
		OrderByComparator<PushDictItem> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PUSHDICTITEM_WHERE);

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
			query.append(PushDictItemModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(pushDictItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PushDictItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the push dict items where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (PushDictItem pushDictItem : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pushDictItem);
		}
	}

	/**
	 * Returns the number of push dict items where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching push dict items
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PUSHDICTITEM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "pushDictItem.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "pushDictItem.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(pushDictItem.uuid IS NULL OR pushDictItem.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "pushDictItem.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_ITEMCODE_METHOD =
		new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, PushDictItemImpl.class,
			FINDER_CLASS_NAME_ENTITY,
			"fetchByF_collectionCode_itemCode_Method",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			},
			PushDictItemModelImpl.GROUPID_COLUMN_BITMASK |
			PushDictItemModelImpl.COLLECTIONCODE_COLUMN_BITMASK |
			PushDictItemModelImpl.ITEMCODE_COLUMN_BITMASK |
			PushDictItemModelImpl.METHOD_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_ITEMCODE_METHOD =
		new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_collectionCode_itemCode_Method",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			});

	/**
	 * Returns the push dict item where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63; or throws a {@link NoSuchPushDictItemException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param itemCode the item code
	 * @param method the method
	 * @return the matching push dict item
	 * @throws NoSuchPushDictItemException if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem findByF_collectionCode_itemCode_Method(long groupId,
		String collectionCode, String itemCode, String method)
		throws NoSuchPushDictItemException {
		PushDictItem pushDictItem = fetchByF_collectionCode_itemCode_Method(groupId,
				collectionCode, itemCode, method);

		if (pushDictItem == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", collectionCode=");
			msg.append(collectionCode);

			msg.append(", itemCode=");
			msg.append(itemCode);

			msg.append(", method=");
			msg.append(method);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPushDictItemException(msg.toString());
		}

		return pushDictItem;
	}

	/**
	 * Returns the push dict item where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param itemCode the item code
	 * @param method the method
	 * @return the matching push dict item, or <code>null</code> if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem fetchByF_collectionCode_itemCode_Method(long groupId,
		String collectionCode, String itemCode, String method) {
		return fetchByF_collectionCode_itemCode_Method(groupId, collectionCode,
			itemCode, method, true);
	}

	/**
	 * Returns the push dict item where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param itemCode the item code
	 * @param method the method
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching push dict item, or <code>null</code> if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem fetchByF_collectionCode_itemCode_Method(long groupId,
		String collectionCode, String itemCode, String method,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, collectionCode, itemCode, method
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_ITEMCODE_METHOD,
					finderArgs, this);
		}

		if (result instanceof PushDictItem) {
			PushDictItem pushDictItem = (PushDictItem)result;

			if ((groupId != pushDictItem.getGroupId()) ||
					!Objects.equals(collectionCode,
						pushDictItem.getCollectionCode()) ||
					!Objects.equals(itemCode, pushDictItem.getItemCode()) ||
					!Objects.equals(method, pushDictItem.getMethod())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_PUSHDICTITEM_WHERE);

			query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_GROUPID_2);

			boolean bindCollectionCode = false;

			if (collectionCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_COLLECTIONCODE_1);
			}
			else if (collectionCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_COLLECTIONCODE_3);
			}
			else {
				bindCollectionCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_COLLECTIONCODE_2);
			}

			boolean bindItemCode = false;

			if (itemCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_ITEMCODE_1);
			}
			else if (itemCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_ITEMCODE_3);
			}
			else {
				bindItemCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_ITEMCODE_2);
			}

			boolean bindMethod = false;

			if (method == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_METHOD_1);
			}
			else if (method.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_METHOD_3);
			}
			else {
				bindMethod = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_METHOD_2);
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

				if (bindItemCode) {
					qPos.add(itemCode);
				}

				if (bindMethod) {
					qPos.add(method);
				}

				List<PushDictItem> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_ITEMCODE_METHOD,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"PushDictItemPersistenceImpl.fetchByF_collectionCode_itemCode_Method(long, String, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PushDictItem pushDictItem = list.get(0);

					result = pushDictItem;

					cacheResult(pushDictItem);

					if ((pushDictItem.getGroupId() != groupId) ||
							(pushDictItem.getCollectionCode() == null) ||
							!pushDictItem.getCollectionCode()
											 .equals(collectionCode) ||
							(pushDictItem.getItemCode() == null) ||
							!pushDictItem.getItemCode().equals(itemCode) ||
							(pushDictItem.getMethod() == null) ||
							!pushDictItem.getMethod().equals(method)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_ITEMCODE_METHOD,
							finderArgs, pushDictItem);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_ITEMCODE_METHOD,
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
			return (PushDictItem)result;
		}
	}

	/**
	 * Removes the push dict item where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param itemCode the item code
	 * @param method the method
	 * @return the push dict item that was removed
	 */
	@Override
	public PushDictItem removeByF_collectionCode_itemCode_Method(long groupId,
		String collectionCode, String itemCode, String method)
		throws NoSuchPushDictItemException {
		PushDictItem pushDictItem = findByF_collectionCode_itemCode_Method(groupId,
				collectionCode, itemCode, method);

		return remove(pushDictItem);
	}

	/**
	 * Returns the number of push dict items where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63;.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param itemCode the item code
	 * @param method the method
	 * @return the number of matching push dict items
	 */
	@Override
	public int countByF_collectionCode_itemCode_Method(long groupId,
		String collectionCode, String itemCode, String method) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_ITEMCODE_METHOD;

		Object[] finderArgs = new Object[] {
				groupId, collectionCode, itemCode, method
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_PUSHDICTITEM_WHERE);

			query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_GROUPID_2);

			boolean bindCollectionCode = false;

			if (collectionCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_COLLECTIONCODE_1);
			}
			else if (collectionCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_COLLECTIONCODE_3);
			}
			else {
				bindCollectionCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_COLLECTIONCODE_2);
			}

			boolean bindItemCode = false;

			if (itemCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_ITEMCODE_1);
			}
			else if (itemCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_ITEMCODE_3);
			}
			else {
				bindItemCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_ITEMCODE_2);
			}

			boolean bindMethod = false;

			if (method == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_METHOD_1);
			}
			else if (method.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_METHOD_3);
			}
			else {
				bindMethod = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_METHOD_2);
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

				if (bindItemCode) {
					qPos.add(itemCode);
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

	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_GROUPID_2 =
		"pushDictItem.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_COLLECTIONCODE_1 =
		"pushDictItem.collectionCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_COLLECTIONCODE_2 =
		"pushDictItem.collectionCode = ? AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_COLLECTIONCODE_3 =
		"(pushDictItem.collectionCode IS NULL OR pushDictItem.collectionCode = '') AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_ITEMCODE_1 =
		"pushDictItem.itemCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_ITEMCODE_2 =
		"pushDictItem.itemCode = ? AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_ITEMCODE_3 =
		"(pushDictItem.itemCode IS NULL OR pushDictItem.itemCode = '') AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_METHOD_1 =
		"pushDictItem.method IS NULL";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_METHOD_2 =
		"pushDictItem.method = ?";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_ITEMCODE_METHOD_METHOD_3 =
		"(pushDictItem.method IS NULL OR pushDictItem.method = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GROUPID_SERVERNO =
		new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, PushDictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_groupId_serverNo",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO =
		new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, PushDictItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_groupId_serverNo",
			new String[] { Long.class.getName(), String.class.getName() },
			PushDictItemModelImpl.GROUPID_COLUMN_BITMASK |
			PushDictItemModelImpl.SERVERNO_COLUMN_BITMASK |
			PushDictItemModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO = new FinderPath(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_groupId_serverNo",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the push dict items where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @return the matching push dict items
	 */
	@Override
	public List<PushDictItem> findByF_groupId_serverNo(long groupId,
		String serverNo) {
		return findByF_groupId_serverNo(groupId, serverNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push dict items where groupId = &#63; and serverNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param start the lower bound of the range of push dict items
	 * @param end the upper bound of the range of push dict items (not inclusive)
	 * @return the range of matching push dict items
	 */
	@Override
	public List<PushDictItem> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end) {
		return findByF_groupId_serverNo(groupId, serverNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the push dict items where groupId = &#63; and serverNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param start the lower bound of the range of push dict items
	 * @param end the upper bound of the range of push dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching push dict items
	 */
	@Override
	public List<PushDictItem> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end,
		OrderByComparator<PushDictItem> orderByComparator) {
		return findByF_groupId_serverNo(groupId, serverNo, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push dict items where groupId = &#63; and serverNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param start the lower bound of the range of push dict items
	 * @param end the upper bound of the range of push dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching push dict items
	 */
	@Override
	public List<PushDictItem> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end,
		OrderByComparator<PushDictItem> orderByComparator,
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

		List<PushDictItem> list = null;

		if (retrieveFromCache) {
			list = (List<PushDictItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PushDictItem pushDictItem : list) {
					if ((groupId != pushDictItem.getGroupId()) ||
							!Objects.equals(serverNo, pushDictItem.getServerNo())) {
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

			query.append(_SQL_SELECT_PUSHDICTITEM_WHERE);

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
				query.append(PushDictItemModelImpl.ORDER_BY_JPQL);
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
					list = (List<PushDictItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushDictItem>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push dict item
	 * @throws NoSuchPushDictItemException if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem findByF_groupId_serverNo_First(long groupId,
		String serverNo, OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException {
		PushDictItem pushDictItem = fetchByF_groupId_serverNo_First(groupId,
				serverNo, orderByComparator);

		if (pushDictItem != null) {
			return pushDictItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serverNo=");
		msg.append(serverNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushDictItemException(msg.toString());
	}

	/**
	 * Returns the first push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push dict item, or <code>null</code> if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem fetchByF_groupId_serverNo_First(long groupId,
		String serverNo, OrderByComparator<PushDictItem> orderByComparator) {
		List<PushDictItem> list = findByF_groupId_serverNo(groupId, serverNo,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push dict item
	 * @throws NoSuchPushDictItemException if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem findByF_groupId_serverNo_Last(long groupId,
		String serverNo, OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException {
		PushDictItem pushDictItem = fetchByF_groupId_serverNo_Last(groupId,
				serverNo, orderByComparator);

		if (pushDictItem != null) {
			return pushDictItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serverNo=");
		msg.append(serverNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushDictItemException(msg.toString());
	}

	/**
	 * Returns the last push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push dict item, or <code>null</code> if a matching push dict item could not be found
	 */
	@Override
	public PushDictItem fetchByF_groupId_serverNo_Last(long groupId,
		String serverNo, OrderByComparator<PushDictItem> orderByComparator) {
		int count = countByF_groupId_serverNo(groupId, serverNo);

		if (count == 0) {
			return null;
		}

		List<PushDictItem> list = findByF_groupId_serverNo(groupId, serverNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the push dict items before and after the current push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param pushDictItemId the primary key of the current push dict item
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next push dict item
	 * @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	 */
	@Override
	public PushDictItem[] findByF_groupId_serverNo_PrevAndNext(
		long pushDictItemId, long groupId, String serverNo,
		OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException {
		PushDictItem pushDictItem = findByPrimaryKey(pushDictItemId);

		Session session = null;

		try {
			session = openSession();

			PushDictItem[] array = new PushDictItemImpl[3];

			array[0] = getByF_groupId_serverNo_PrevAndNext(session,
					pushDictItem, groupId, serverNo, orderByComparator, true);

			array[1] = pushDictItem;

			array[2] = getByF_groupId_serverNo_PrevAndNext(session,
					pushDictItem, groupId, serverNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PushDictItem getByF_groupId_serverNo_PrevAndNext(
		Session session, PushDictItem pushDictItem, long groupId,
		String serverNo, OrderByComparator<PushDictItem> orderByComparator,
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

		query.append(_SQL_SELECT_PUSHDICTITEM_WHERE);

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
			query.append(PushDictItemModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(pushDictItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PushDictItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the push dict items where groupId = &#63; and serverNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 */
	@Override
	public void removeByF_groupId_serverNo(long groupId, String serverNo) {
		for (PushDictItem pushDictItem : findByF_groupId_serverNo(groupId,
				serverNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pushDictItem);
		}
	}

	/**
	 * Returns the number of push dict items where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @return the number of matching push dict items
	 */
	@Override
	public int countByF_groupId_serverNo(long groupId, String serverNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO;

		Object[] finderArgs = new Object[] { groupId, serverNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PUSHDICTITEM_WHERE);

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

	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_GROUPID_2 = "pushDictItem.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_1 = "pushDictItem.serverNo IS NULL";
	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_2 = "pushDictItem.serverNo = ?";
	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_3 = "(pushDictItem.serverNo IS NULL OR pushDictItem.serverNo = '')";

	public PushDictItemPersistenceImpl() {
		setModelClass(PushDictItem.class);
	}

	/**
	 * Caches the push dict item in the entity cache if it is enabled.
	 *
	 * @param pushDictItem the push dict item
	 */
	@Override
	public void cacheResult(PushDictItem pushDictItem) {
		entityCache.putResult(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemImpl.class, pushDictItem.getPrimaryKey(), pushDictItem);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { pushDictItem.getUuid(), pushDictItem.getGroupId() },
			pushDictItem);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_ITEMCODE_METHOD,
			new Object[] {
				pushDictItem.getGroupId(), pushDictItem.getCollectionCode(),
				pushDictItem.getItemCode(), pushDictItem.getMethod()
			}, pushDictItem);

		pushDictItem.resetOriginalValues();
	}

	/**
	 * Caches the push dict items in the entity cache if it is enabled.
	 *
	 * @param pushDictItems the push dict items
	 */
	@Override
	public void cacheResult(List<PushDictItem> pushDictItems) {
		for (PushDictItem pushDictItem : pushDictItems) {
			if (entityCache.getResult(
						PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
						PushDictItemImpl.class, pushDictItem.getPrimaryKey()) == null) {
				cacheResult(pushDictItem);
			}
			else {
				pushDictItem.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all push dict items.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PushDictItemImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the push dict item.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PushDictItem pushDictItem) {
		entityCache.removeResult(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemImpl.class, pushDictItem.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PushDictItemModelImpl)pushDictItem, true);
	}

	@Override
	public void clearCache(List<PushDictItem> pushDictItems) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PushDictItem pushDictItem : pushDictItems) {
			entityCache.removeResult(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
				PushDictItemImpl.class, pushDictItem.getPrimaryKey());

			clearUniqueFindersCache((PushDictItemModelImpl)pushDictItem, true);
		}
	}

	protected void cacheUniqueFindersCache(
		PushDictItemModelImpl pushDictItemModelImpl) {
		Object[] args = new Object[] {
				pushDictItemModelImpl.getUuid(),
				pushDictItemModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			pushDictItemModelImpl, false);

		args = new Object[] {
				pushDictItemModelImpl.getGroupId(),
				pushDictItemModelImpl.getCollectionCode(),
				pushDictItemModelImpl.getItemCode(),
				pushDictItemModelImpl.getMethod()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_ITEMCODE_METHOD,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_ITEMCODE_METHOD,
			args, pushDictItemModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PushDictItemModelImpl pushDictItemModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					pushDictItemModelImpl.getUuid(),
					pushDictItemModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((pushDictItemModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					pushDictItemModelImpl.getOriginalUuid(),
					pushDictItemModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					pushDictItemModelImpl.getGroupId(),
					pushDictItemModelImpl.getCollectionCode(),
					pushDictItemModelImpl.getItemCode(),
					pushDictItemModelImpl.getMethod()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_ITEMCODE_METHOD,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_ITEMCODE_METHOD,
				args);
		}

		if ((pushDictItemModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_ITEMCODE_METHOD.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					pushDictItemModelImpl.getOriginalGroupId(),
					pushDictItemModelImpl.getOriginalCollectionCode(),
					pushDictItemModelImpl.getOriginalItemCode(),
					pushDictItemModelImpl.getOriginalMethod()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_ITEMCODE_METHOD,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_ITEMCODE_METHOD,
				args);
		}
	}

	/**
	 * Creates a new push dict item with the primary key. Does not add the push dict item to the database.
	 *
	 * @param pushDictItemId the primary key for the new push dict item
	 * @return the new push dict item
	 */
	@Override
	public PushDictItem create(long pushDictItemId) {
		PushDictItem pushDictItem = new PushDictItemImpl();

		pushDictItem.setNew(true);
		pushDictItem.setPrimaryKey(pushDictItemId);

		String uuid = PortalUUIDUtil.generate();

		pushDictItem.setUuid(uuid);

		pushDictItem.setCompanyId(companyProvider.getCompanyId());

		return pushDictItem;
	}

	/**
	 * Removes the push dict item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pushDictItemId the primary key of the push dict item
	 * @return the push dict item that was removed
	 * @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	 */
	@Override
	public PushDictItem remove(long pushDictItemId)
		throws NoSuchPushDictItemException {
		return remove((Serializable)pushDictItemId);
	}

	/**
	 * Removes the push dict item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the push dict item
	 * @return the push dict item that was removed
	 * @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	 */
	@Override
	public PushDictItem remove(Serializable primaryKey)
		throws NoSuchPushDictItemException {
		Session session = null;

		try {
			session = openSession();

			PushDictItem pushDictItem = (PushDictItem)session.get(PushDictItemImpl.class,
					primaryKey);

			if (pushDictItem == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPushDictItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(pushDictItem);
		}
		catch (NoSuchPushDictItemException nsee) {
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
	protected PushDictItem removeImpl(PushDictItem pushDictItem) {
		pushDictItem = toUnwrappedModel(pushDictItem);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(pushDictItem)) {
				pushDictItem = (PushDictItem)session.get(PushDictItemImpl.class,
						pushDictItem.getPrimaryKeyObj());
			}

			if (pushDictItem != null) {
				session.delete(pushDictItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (pushDictItem != null) {
			clearCache(pushDictItem);
		}

		return pushDictItem;
	}

	@Override
	public PushDictItem updateImpl(PushDictItem pushDictItem) {
		pushDictItem = toUnwrappedModel(pushDictItem);

		boolean isNew = pushDictItem.isNew();

		PushDictItemModelImpl pushDictItemModelImpl = (PushDictItemModelImpl)pushDictItem;

		if (Validator.isNull(pushDictItem.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			pushDictItem.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (pushDictItem.getCreateDate() == null)) {
			if (serviceContext == null) {
				pushDictItem.setCreateDate(now);
			}
			else {
				pushDictItem.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!pushDictItemModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				pushDictItem.setModifiedDate(now);
			}
			else {
				pushDictItem.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (pushDictItem.isNew()) {
				session.save(pushDictItem);

				pushDictItem.setNew(false);
			}
			else {
				pushDictItem = (PushDictItem)session.merge(pushDictItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PushDictItemModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((pushDictItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pushDictItemModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { pushDictItemModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((pushDictItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pushDictItemModelImpl.getOriginalUuid(),
						pushDictItemModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						pushDictItemModelImpl.getUuid(),
						pushDictItemModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((pushDictItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pushDictItemModelImpl.getOriginalGroupId(),
						pushDictItemModelImpl.getOriginalServerNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO,
					args);

				args = new Object[] {
						pushDictItemModelImpl.getGroupId(),
						pushDictItemModelImpl.getServerNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO,
					args);
			}
		}

		entityCache.putResult(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
			PushDictItemImpl.class, pushDictItem.getPrimaryKey(), pushDictItem,
			false);

		clearUniqueFindersCache(pushDictItemModelImpl, false);
		cacheUniqueFindersCache(pushDictItemModelImpl);

		pushDictItem.resetOriginalValues();

		return pushDictItem;
	}

	protected PushDictItem toUnwrappedModel(PushDictItem pushDictItem) {
		if (pushDictItem instanceof PushDictItemImpl) {
			return pushDictItem;
		}

		PushDictItemImpl pushDictItemImpl = new PushDictItemImpl();

		pushDictItemImpl.setNew(pushDictItem.isNew());
		pushDictItemImpl.setPrimaryKey(pushDictItem.getPrimaryKey());

		pushDictItemImpl.setUuid(pushDictItem.getUuid());
		pushDictItemImpl.setPushDictItemId(pushDictItem.getPushDictItemId());
		pushDictItemImpl.setGroupId(pushDictItem.getGroupId());
		pushDictItemImpl.setCompanyId(pushDictItem.getCompanyId());
		pushDictItemImpl.setUserId(pushDictItem.getUserId());
		pushDictItemImpl.setUserName(pushDictItem.getUserName());
		pushDictItemImpl.setCreateDate(pushDictItem.getCreateDate());
		pushDictItemImpl.setModifiedDate(pushDictItem.getModifiedDate());
		pushDictItemImpl.setServerNo(pushDictItem.getServerNo());
		pushDictItemImpl.setCollectionCode(pushDictItem.getCollectionCode());
		pushDictItemImpl.setItemCode(pushDictItem.getItemCode());
		pushDictItemImpl.setItemName(pushDictItem.getItemName());
		pushDictItemImpl.setItemNameEN(pushDictItem.getItemNameEN());
		pushDictItemImpl.setItemDescription(pushDictItem.getItemDescription());
		pushDictItemImpl.setParentItemCode(pushDictItem.getParentItemCode());
		pushDictItemImpl.setSibling(pushDictItem.getSibling());
		pushDictItemImpl.setMethod(pushDictItem.getMethod());
		pushDictItemImpl.setMetaData(pushDictItem.getMetaData());

		return pushDictItemImpl;
	}

	/**
	 * Returns the push dict item with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the push dict item
	 * @return the push dict item
	 * @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	 */
	@Override
	public PushDictItem findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPushDictItemException {
		PushDictItem pushDictItem = fetchByPrimaryKey(primaryKey);

		if (pushDictItem == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPushDictItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return pushDictItem;
	}

	/**
	 * Returns the push dict item with the primary key or throws a {@link NoSuchPushDictItemException} if it could not be found.
	 *
	 * @param pushDictItemId the primary key of the push dict item
	 * @return the push dict item
	 * @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	 */
	@Override
	public PushDictItem findByPrimaryKey(long pushDictItemId)
		throws NoSuchPushDictItemException {
		return findByPrimaryKey((Serializable)pushDictItemId);
	}

	/**
	 * Returns the push dict item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the push dict item
	 * @return the push dict item, or <code>null</code> if a push dict item with the primary key could not be found
	 */
	@Override
	public PushDictItem fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
				PushDictItemImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PushDictItem pushDictItem = (PushDictItem)serializable;

		if (pushDictItem == null) {
			Session session = null;

			try {
				session = openSession();

				pushDictItem = (PushDictItem)session.get(PushDictItemImpl.class,
						primaryKey);

				if (pushDictItem != null) {
					cacheResult(pushDictItem);
				}
				else {
					entityCache.putResult(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
						PushDictItemImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
					PushDictItemImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return pushDictItem;
	}

	/**
	 * Returns the push dict item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pushDictItemId the primary key of the push dict item
	 * @return the push dict item, or <code>null</code> if a push dict item with the primary key could not be found
	 */
	@Override
	public PushDictItem fetchByPrimaryKey(long pushDictItemId) {
		return fetchByPrimaryKey((Serializable)pushDictItemId);
	}

	@Override
	public Map<Serializable, PushDictItem> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PushDictItem> map = new HashMap<Serializable, PushDictItem>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PushDictItem pushDictItem = fetchByPrimaryKey(primaryKey);

			if (pushDictItem != null) {
				map.put(primaryKey, pushDictItem);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
					PushDictItemImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PushDictItem)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PUSHDICTITEM_WHERE_PKS_IN);

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

			for (PushDictItem pushDictItem : (List<PushDictItem>)q.list()) {
				map.put(pushDictItem.getPrimaryKeyObj(), pushDictItem);

				cacheResult(pushDictItem);

				uncachedPrimaryKeys.remove(pushDictItem.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PushDictItemModelImpl.ENTITY_CACHE_ENABLED,
					PushDictItemImpl.class, primaryKey, nullModel);
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
	 * Returns all the push dict items.
	 *
	 * @return the push dict items
	 */
	@Override
	public List<PushDictItem> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push dict items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push dict items
	 * @param end the upper bound of the range of push dict items (not inclusive)
	 * @return the range of push dict items
	 */
	@Override
	public List<PushDictItem> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the push dict items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push dict items
	 * @param end the upper bound of the range of push dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of push dict items
	 */
	@Override
	public List<PushDictItem> findAll(int start, int end,
		OrderByComparator<PushDictItem> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push dict items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push dict items
	 * @param end the upper bound of the range of push dict items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of push dict items
	 */
	@Override
	public List<PushDictItem> findAll(int start, int end,
		OrderByComparator<PushDictItem> orderByComparator,
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

		List<PushDictItem> list = null;

		if (retrieveFromCache) {
			list = (List<PushDictItem>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PUSHDICTITEM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PUSHDICTITEM;

				if (pagination) {
					sql = sql.concat(PushDictItemModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PushDictItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushDictItem>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the push dict items from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PushDictItem pushDictItem : findAll()) {
			remove(pushDictItem);
		}
	}

	/**
	 * Returns the number of push dict items.
	 *
	 * @return the number of push dict items
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PUSHDICTITEM);

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
		return PushDictItemModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the push dict item persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PushDictItemImpl.class.getName());
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
	private static final String _SQL_SELECT_PUSHDICTITEM = "SELECT pushDictItem FROM PushDictItem pushDictItem";
	private static final String _SQL_SELECT_PUSHDICTITEM_WHERE_PKS_IN = "SELECT pushDictItem FROM PushDictItem pushDictItem WHERE pushDictItemId IN (";
	private static final String _SQL_SELECT_PUSHDICTITEM_WHERE = "SELECT pushDictItem FROM PushDictItem pushDictItem WHERE ";
	private static final String _SQL_COUNT_PUSHDICTITEM = "SELECT COUNT(pushDictItem) FROM PushDictItem pushDictItem";
	private static final String _SQL_COUNT_PUSHDICTITEM_WHERE = "SELECT COUNT(pushDictItem) FROM PushDictItem pushDictItem WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "pushDictItem.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PushDictItem exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PushDictItem exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PushDictItemPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}