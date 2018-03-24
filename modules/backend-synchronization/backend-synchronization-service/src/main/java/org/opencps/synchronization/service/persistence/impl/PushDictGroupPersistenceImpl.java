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

import org.opencps.synchronization.exception.NoSuchPushDictGroupException;
import org.opencps.synchronization.model.PushDictGroup;
import org.opencps.synchronization.model.impl.PushDictGroupImpl;
import org.opencps.synchronization.model.impl.PushDictGroupModelImpl;
import org.opencps.synchronization.service.persistence.PushDictGroupPersistence;

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
 * The persistence implementation for the push dict group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see PushDictGroupPersistence
 * @see org.opencps.synchronization.service.persistence.PushDictGroupUtil
 * @generated
 */
@ProviderType
public class PushDictGroupPersistenceImpl extends BasePersistenceImpl<PushDictGroup>
	implements PushDictGroupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PushDictGroupUtil} to access the push dict group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PushDictGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED,
			PushDictGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED,
			PushDictGroupImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED,
			PushDictGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED,
			PushDictGroupImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			PushDictGroupModelImpl.UUID_COLUMN_BITMASK |
			PushDictGroupModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the push dict groups where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching push dict groups
	 */
	@Override
	public List<PushDictGroup> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push dict groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of push dict groups
	 * @param end the upper bound of the range of push dict groups (not inclusive)
	 * @return the range of matching push dict groups
	 */
	@Override
	public List<PushDictGroup> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the push dict groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of push dict groups
	 * @param end the upper bound of the range of push dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching push dict groups
	 */
	@Override
	public List<PushDictGroup> findByUuid(String uuid, int start, int end,
		OrderByComparator<PushDictGroup> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push dict groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of push dict groups
	 * @param end the upper bound of the range of push dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching push dict groups
	 */
	@Override
	public List<PushDictGroup> findByUuid(String uuid, int start, int end,
		OrderByComparator<PushDictGroup> orderByComparator,
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

		List<PushDictGroup> list = null;

		if (retrieveFromCache) {
			list = (List<PushDictGroup>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PushDictGroup pushDictGroup : list) {
					if (!Objects.equals(uuid, pushDictGroup.getUuid())) {
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

			query.append(_SQL_SELECT_PUSHDICTGROUP_WHERE);

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
				query.append(PushDictGroupModelImpl.ORDER_BY_JPQL);
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
					list = (List<PushDictGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushDictGroup>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first push dict group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push dict group
	 * @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup findByUuid_First(String uuid,
		OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = fetchByUuid_First(uuid, orderByComparator);

		if (pushDictGroup != null) {
			return pushDictGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushDictGroupException(msg.toString());
	}

	/**
	 * Returns the first push dict group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push dict group, or <code>null</code> if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup fetchByUuid_First(String uuid,
		OrderByComparator<PushDictGroup> orderByComparator) {
		List<PushDictGroup> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last push dict group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push dict group
	 * @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup findByUuid_Last(String uuid,
		OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = fetchByUuid_Last(uuid, orderByComparator);

		if (pushDictGroup != null) {
			return pushDictGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushDictGroupException(msg.toString());
	}

	/**
	 * Returns the last push dict group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push dict group, or <code>null</code> if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup fetchByUuid_Last(String uuid,
		OrderByComparator<PushDictGroup> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PushDictGroup> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the push dict groups before and after the current push dict group in the ordered set where uuid = &#63;.
	 *
	 * @param pushDictGroupId the primary key of the current push dict group
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next push dict group
	 * @throws NoSuchPushDictGroupException if a push dict group with the primary key could not be found
	 */
	@Override
	public PushDictGroup[] findByUuid_PrevAndNext(long pushDictGroupId,
		String uuid, OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = findByPrimaryKey(pushDictGroupId);

		Session session = null;

		try {
			session = openSession();

			PushDictGroup[] array = new PushDictGroupImpl[3];

			array[0] = getByUuid_PrevAndNext(session, pushDictGroup, uuid,
					orderByComparator, true);

			array[1] = pushDictGroup;

			array[2] = getByUuid_PrevAndNext(session, pushDictGroup, uuid,
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

	protected PushDictGroup getByUuid_PrevAndNext(Session session,
		PushDictGroup pushDictGroup, String uuid,
		OrderByComparator<PushDictGroup> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PUSHDICTGROUP_WHERE);

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
			query.append(PushDictGroupModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(pushDictGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PushDictGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the push dict groups where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PushDictGroup pushDictGroup : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(pushDictGroup);
		}
	}

	/**
	 * Returns the number of push dict groups where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching push dict groups
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PUSHDICTGROUP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "pushDictGroup.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "pushDictGroup.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(pushDictGroup.uuid IS NULL OR pushDictGroup.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED,
			PushDictGroupImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PushDictGroupModelImpl.UUID_COLUMN_BITMASK |
			PushDictGroupModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the push dict group where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPushDictGroupException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching push dict group
	 * @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup findByUUID_G(String uuid, long groupId)
		throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = fetchByUUID_G(uuid, groupId);

		if (pushDictGroup == null) {
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

			throw new NoSuchPushDictGroupException(msg.toString());
		}

		return pushDictGroup;
	}

	/**
	 * Returns the push dict group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the push dict group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PushDictGroup) {
			PushDictGroup pushDictGroup = (PushDictGroup)result;

			if (!Objects.equals(uuid, pushDictGroup.getUuid()) ||
					(groupId != pushDictGroup.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PUSHDICTGROUP_WHERE);

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

				List<PushDictGroup> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					PushDictGroup pushDictGroup = list.get(0);

					result = pushDictGroup;

					cacheResult(pushDictGroup);

					if ((pushDictGroup.getUuid() == null) ||
							!pushDictGroup.getUuid().equals(uuid) ||
							(pushDictGroup.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, pushDictGroup);
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
			return (PushDictGroup)result;
		}
	}

	/**
	 * Removes the push dict group where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the push dict group that was removed
	 */
	@Override
	public PushDictGroup removeByUUID_G(String uuid, long groupId)
		throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = findByUUID_G(uuid, groupId);

		return remove(pushDictGroup);
	}

	/**
	 * Returns the number of push dict groups where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching push dict groups
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PUSHDICTGROUP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "pushDictGroup.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "pushDictGroup.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(pushDictGroup.uuid IS NULL OR pushDictGroup.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "pushDictGroup.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED,
			PushDictGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED,
			PushDictGroupImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PushDictGroupModelImpl.UUID_COLUMN_BITMASK |
			PushDictGroupModelImpl.COMPANYID_COLUMN_BITMASK |
			PushDictGroupModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the push dict groups where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching push dict groups
	 */
	@Override
	public List<PushDictGroup> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push dict groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of push dict groups
	 * @param end the upper bound of the range of push dict groups (not inclusive)
	 * @return the range of matching push dict groups
	 */
	@Override
	public List<PushDictGroup> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the push dict groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of push dict groups
	 * @param end the upper bound of the range of push dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching push dict groups
	 */
	@Override
	public List<PushDictGroup> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PushDictGroup> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push dict groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of push dict groups
	 * @param end the upper bound of the range of push dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching push dict groups
	 */
	@Override
	public List<PushDictGroup> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PushDictGroup> orderByComparator,
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

		List<PushDictGroup> list = null;

		if (retrieveFromCache) {
			list = (List<PushDictGroup>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PushDictGroup pushDictGroup : list) {
					if (!Objects.equals(uuid, pushDictGroup.getUuid()) ||
							(companyId != pushDictGroup.getCompanyId())) {
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

			query.append(_SQL_SELECT_PUSHDICTGROUP_WHERE);

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
				query.append(PushDictGroupModelImpl.ORDER_BY_JPQL);
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
					list = (List<PushDictGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushDictGroup>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first push dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push dict group
	 * @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (pushDictGroup != null) {
			return pushDictGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushDictGroupException(msg.toString());
	}

	/**
	 * Returns the first push dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push dict group, or <code>null</code> if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PushDictGroup> orderByComparator) {
		List<PushDictGroup> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last push dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push dict group
	 * @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (pushDictGroup != null) {
			return pushDictGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushDictGroupException(msg.toString());
	}

	/**
	 * Returns the last push dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push dict group, or <code>null</code> if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PushDictGroup> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<PushDictGroup> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the push dict groups before and after the current push dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param pushDictGroupId the primary key of the current push dict group
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next push dict group
	 * @throws NoSuchPushDictGroupException if a push dict group with the primary key could not be found
	 */
	@Override
	public PushDictGroup[] findByUuid_C_PrevAndNext(long pushDictGroupId,
		String uuid, long companyId,
		OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = findByPrimaryKey(pushDictGroupId);

		Session session = null;

		try {
			session = openSession();

			PushDictGroup[] array = new PushDictGroupImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, pushDictGroup, uuid,
					companyId, orderByComparator, true);

			array[1] = pushDictGroup;

			array[2] = getByUuid_C_PrevAndNext(session, pushDictGroup, uuid,
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

	protected PushDictGroup getByUuid_C_PrevAndNext(Session session,
		PushDictGroup pushDictGroup, String uuid, long companyId,
		OrderByComparator<PushDictGroup> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PUSHDICTGROUP_WHERE);

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
			query.append(PushDictGroupModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(pushDictGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PushDictGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the push dict groups where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (PushDictGroup pushDictGroup : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pushDictGroup);
		}
	}

	/**
	 * Returns the number of push dict groups where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching push dict groups
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PUSHDICTGROUP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "pushDictGroup.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "pushDictGroup.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(pushDictGroup.uuid IS NULL OR pushDictGroup.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "pushDictGroup.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_METHOD =
		new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED,
			PushDictGroupImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_collectionCode_groupCode_Method",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			},
			PushDictGroupModelImpl.GROUPID_COLUMN_BITMASK |
			PushDictGroupModelImpl.COLLECTIONCODE_COLUMN_BITMASK |
			PushDictGroupModelImpl.GROUPCODE_COLUMN_BITMASK |
			PushDictGroupModelImpl.METHOD_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_GROUPCODE_METHOD =
		new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_collectionCode_groupCode_Method",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			});

	/**
	 * Returns the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and method = &#63; or throws a {@link NoSuchPushDictGroupException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param groupCode the group code
	 * @param method the method
	 * @return the matching push dict group
	 * @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup findByF_collectionCode_groupCode_Method(long groupId,
		String collectionCode, String groupCode, String method)
		throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = fetchByF_collectionCode_groupCode_Method(groupId,
				collectionCode, groupCode, method);

		if (pushDictGroup == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", collectionCode=");
			msg.append(collectionCode);

			msg.append(", groupCode=");
			msg.append(groupCode);

			msg.append(", method=");
			msg.append(method);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPushDictGroupException(msg.toString());
		}

		return pushDictGroup;
	}

	/**
	 * Returns the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param groupCode the group code
	 * @param method the method
	 * @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup fetchByF_collectionCode_groupCode_Method(
		long groupId, String collectionCode, String groupCode, String method) {
		return fetchByF_collectionCode_groupCode_Method(groupId,
			collectionCode, groupCode, method, true);
	}

	/**
	 * Returns the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param groupCode the group code
	 * @param method the method
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup fetchByF_collectionCode_groupCode_Method(
		long groupId, String collectionCode, String groupCode, String method,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, collectionCode, groupCode, method
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_METHOD,
					finderArgs, this);
		}

		if (result instanceof PushDictGroup) {
			PushDictGroup pushDictGroup = (PushDictGroup)result;

			if ((groupId != pushDictGroup.getGroupId()) ||
					!Objects.equals(collectionCode,
						pushDictGroup.getCollectionCode()) ||
					!Objects.equals(groupCode, pushDictGroup.getGroupCode()) ||
					!Objects.equals(method, pushDictGroup.getMethod())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_PUSHDICTGROUP_WHERE);

			query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_GROUPID_2);

			boolean bindCollectionCode = false;

			if (collectionCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_COLLECTIONCODE_1);
			}
			else if (collectionCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_COLLECTIONCODE_3);
			}
			else {
				bindCollectionCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_COLLECTIONCODE_2);
			}

			boolean bindGroupCode = false;

			if (groupCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_GROUPCODE_1);
			}
			else if (groupCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_GROUPCODE_3);
			}
			else {
				bindGroupCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_GROUPCODE_2);
			}

			boolean bindMethod = false;

			if (method == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_METHOD_1);
			}
			else if (method.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_METHOD_3);
			}
			else {
				bindMethod = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_METHOD_2);
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

				if (bindGroupCode) {
					qPos.add(groupCode);
				}

				if (bindMethod) {
					qPos.add(method);
				}

				List<PushDictGroup> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_METHOD,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"PushDictGroupPersistenceImpl.fetchByF_collectionCode_groupCode_Method(long, String, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PushDictGroup pushDictGroup = list.get(0);

					result = pushDictGroup;

					cacheResult(pushDictGroup);

					if ((pushDictGroup.getGroupId() != groupId) ||
							(pushDictGroup.getCollectionCode() == null) ||
							!pushDictGroup.getCollectionCode()
											  .equals(collectionCode) ||
							(pushDictGroup.getGroupCode() == null) ||
							!pushDictGroup.getGroupCode().equals(groupCode) ||
							(pushDictGroup.getMethod() == null) ||
							!pushDictGroup.getMethod().equals(method)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_METHOD,
							finderArgs, pushDictGroup);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_METHOD,
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
			return (PushDictGroup)result;
		}
	}

	/**
	 * Removes the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and method = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param groupCode the group code
	 * @param method the method
	 * @return the push dict group that was removed
	 */
	@Override
	public PushDictGroup removeByF_collectionCode_groupCode_Method(
		long groupId, String collectionCode, String groupCode, String method)
		throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = findByF_collectionCode_groupCode_Method(groupId,
				collectionCode, groupCode, method);

		return remove(pushDictGroup);
	}

	/**
	 * Returns the number of push dict groups where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and method = &#63;.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param groupCode the group code
	 * @param method the method
	 * @return the number of matching push dict groups
	 */
	@Override
	public int countByF_collectionCode_groupCode_Method(long groupId,
		String collectionCode, String groupCode, String method) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_GROUPCODE_METHOD;

		Object[] finderArgs = new Object[] {
				groupId, collectionCode, groupCode, method
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_PUSHDICTGROUP_WHERE);

			query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_GROUPID_2);

			boolean bindCollectionCode = false;

			if (collectionCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_COLLECTIONCODE_1);
			}
			else if (collectionCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_COLLECTIONCODE_3);
			}
			else {
				bindCollectionCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_COLLECTIONCODE_2);
			}

			boolean bindGroupCode = false;

			if (groupCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_GROUPCODE_1);
			}
			else if (groupCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_GROUPCODE_3);
			}
			else {
				bindGroupCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_GROUPCODE_2);
			}

			boolean bindMethod = false;

			if (method == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_METHOD_1);
			}
			else if (method.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_METHOD_3);
			}
			else {
				bindMethod = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_METHOD_2);
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

				if (bindGroupCode) {
					qPos.add(groupCode);
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

	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_GROUPID_2 =
		"pushDictGroup.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_COLLECTIONCODE_1 =
		"pushDictGroup.collectionCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_COLLECTIONCODE_2 =
		"pushDictGroup.collectionCode = ? AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_COLLECTIONCODE_3 =
		"(pushDictGroup.collectionCode IS NULL OR pushDictGroup.collectionCode = '') AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_GROUPCODE_1 =
		"pushDictGroup.groupCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_GROUPCODE_2 =
		"pushDictGroup.groupCode = ? AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_GROUPCODE_3 =
		"(pushDictGroup.groupCode IS NULL OR pushDictGroup.groupCode = '') AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_METHOD_1 =
		"pushDictGroup.method IS NULL";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_METHOD_2 =
		"pushDictGroup.method = ?";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_METHOD_METHOD_3 =
		"(pushDictGroup.method IS NULL OR pushDictGroup.method = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD =
		new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED,
			PushDictGroupImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_collectionCode_groupCode_itemCode_Method",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName()
			},
			PushDictGroupModelImpl.GROUPID_COLUMN_BITMASK |
			PushDictGroupModelImpl.COLLECTIONCODE_COLUMN_BITMASK |
			PushDictGroupModelImpl.GROUPCODE_COLUMN_BITMASK |
			PushDictGroupModelImpl.ITEMCODE_COLUMN_BITMASK |
			PushDictGroupModelImpl.METHOD_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD =
		new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_collectionCode_groupCode_itemCode_Method",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and itemCode = &#63; and method = &#63; or throws a {@link NoSuchPushDictGroupException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param groupCode the group code
	 * @param itemCode the item code
	 * @param method the method
	 * @return the matching push dict group
	 * @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup findByF_collectionCode_groupCode_itemCode_Method(
		long groupId, String collectionCode, String groupCode, String itemCode,
		String method) throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = fetchByF_collectionCode_groupCode_itemCode_Method(groupId,
				collectionCode, groupCode, itemCode, method);

		if (pushDictGroup == null) {
			StringBundler msg = new StringBundler(12);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", collectionCode=");
			msg.append(collectionCode);

			msg.append(", groupCode=");
			msg.append(groupCode);

			msg.append(", itemCode=");
			msg.append(itemCode);

			msg.append(", method=");
			msg.append(method);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPushDictGroupException(msg.toString());
		}

		return pushDictGroup;
	}

	/**
	 * Returns the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and itemCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param groupCode the group code
	 * @param itemCode the item code
	 * @param method the method
	 * @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup fetchByF_collectionCode_groupCode_itemCode_Method(
		long groupId, String collectionCode, String groupCode, String itemCode,
		String method) {
		return fetchByF_collectionCode_groupCode_itemCode_Method(groupId,
			collectionCode, groupCode, itemCode, method, true);
	}

	/**
	 * Returns the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and itemCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param groupCode the group code
	 * @param itemCode the item code
	 * @param method the method
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup fetchByF_collectionCode_groupCode_itemCode_Method(
		long groupId, String collectionCode, String groupCode, String itemCode,
		String method, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, collectionCode, groupCode, itemCode, method
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD,
					finderArgs, this);
		}

		if (result instanceof PushDictGroup) {
			PushDictGroup pushDictGroup = (PushDictGroup)result;

			if ((groupId != pushDictGroup.getGroupId()) ||
					!Objects.equals(collectionCode,
						pushDictGroup.getCollectionCode()) ||
					!Objects.equals(groupCode, pushDictGroup.getGroupCode()) ||
					!Objects.equals(itemCode, pushDictGroup.getItemCode()) ||
					!Objects.equals(method, pushDictGroup.getMethod())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_SELECT_PUSHDICTGROUP_WHERE);

			query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_GROUPID_2);

			boolean bindCollectionCode = false;

			if (collectionCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_COLLECTIONCODE_1);
			}
			else if (collectionCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_COLLECTIONCODE_3);
			}
			else {
				bindCollectionCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_COLLECTIONCODE_2);
			}

			boolean bindGroupCode = false;

			if (groupCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_GROUPCODE_1);
			}
			else if (groupCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_GROUPCODE_3);
			}
			else {
				bindGroupCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_GROUPCODE_2);
			}

			boolean bindItemCode = false;

			if (itemCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_ITEMCODE_1);
			}
			else if (itemCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_ITEMCODE_3);
			}
			else {
				bindItemCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_ITEMCODE_2);
			}

			boolean bindMethod = false;

			if (method == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_METHOD_1);
			}
			else if (method.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_METHOD_3);
			}
			else {
				bindMethod = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_METHOD_2);
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

				if (bindGroupCode) {
					qPos.add(groupCode);
				}

				if (bindItemCode) {
					qPos.add(itemCode);
				}

				if (bindMethod) {
					qPos.add(method);
				}

				List<PushDictGroup> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"PushDictGroupPersistenceImpl.fetchByF_collectionCode_groupCode_itemCode_Method(long, String, String, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PushDictGroup pushDictGroup = list.get(0);

					result = pushDictGroup;

					cacheResult(pushDictGroup);

					if ((pushDictGroup.getGroupId() != groupId) ||
							(pushDictGroup.getCollectionCode() == null) ||
							!pushDictGroup.getCollectionCode()
											  .equals(collectionCode) ||
							(pushDictGroup.getGroupCode() == null) ||
							!pushDictGroup.getGroupCode().equals(groupCode) ||
							(pushDictGroup.getItemCode() == null) ||
							!pushDictGroup.getItemCode().equals(itemCode) ||
							(pushDictGroup.getMethod() == null) ||
							!pushDictGroup.getMethod().equals(method)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD,
							finderArgs, pushDictGroup);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD,
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
			return (PushDictGroup)result;
		}
	}

	/**
	 * Removes the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and itemCode = &#63; and method = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param groupCode the group code
	 * @param itemCode the item code
	 * @param method the method
	 * @return the push dict group that was removed
	 */
	@Override
	public PushDictGroup removeByF_collectionCode_groupCode_itemCode_Method(
		long groupId, String collectionCode, String groupCode, String itemCode,
		String method) throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = findByF_collectionCode_groupCode_itemCode_Method(groupId,
				collectionCode, groupCode, itemCode, method);

		return remove(pushDictGroup);
	}

	/**
	 * Returns the number of push dict groups where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and itemCode = &#63; and method = &#63;.
	 *
	 * @param groupId the group ID
	 * @param collectionCode the collection code
	 * @param groupCode the group code
	 * @param itemCode the item code
	 * @param method the method
	 * @return the number of matching push dict groups
	 */
	@Override
	public int countByF_collectionCode_groupCode_itemCode_Method(long groupId,
		String collectionCode, String groupCode, String itemCode, String method) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD;

		Object[] finderArgs = new Object[] {
				groupId, collectionCode, groupCode, itemCode, method
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_PUSHDICTGROUP_WHERE);

			query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_GROUPID_2);

			boolean bindCollectionCode = false;

			if (collectionCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_COLLECTIONCODE_1);
			}
			else if (collectionCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_COLLECTIONCODE_3);
			}
			else {
				bindCollectionCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_COLLECTIONCODE_2);
			}

			boolean bindGroupCode = false;

			if (groupCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_GROUPCODE_1);
			}
			else if (groupCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_GROUPCODE_3);
			}
			else {
				bindGroupCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_GROUPCODE_2);
			}

			boolean bindItemCode = false;

			if (itemCode == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_ITEMCODE_1);
			}
			else if (itemCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_ITEMCODE_3);
			}
			else {
				bindItemCode = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_ITEMCODE_2);
			}

			boolean bindMethod = false;

			if (method == null) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_METHOD_1);
			}
			else if (method.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_METHOD_3);
			}
			else {
				bindMethod = true;

				query.append(_FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_METHOD_2);
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

				if (bindGroupCode) {
					qPos.add(groupCode);
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

	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_GROUPID_2 =
		"pushDictGroup.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_COLLECTIONCODE_1 =
		"pushDictGroup.collectionCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_COLLECTIONCODE_2 =
		"pushDictGroup.collectionCode = ? AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_COLLECTIONCODE_3 =
		"(pushDictGroup.collectionCode IS NULL OR pushDictGroup.collectionCode = '') AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_GROUPCODE_1 =
		"pushDictGroup.groupCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_GROUPCODE_2 =
		"pushDictGroup.groupCode = ? AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_GROUPCODE_3 =
		"(pushDictGroup.groupCode IS NULL OR pushDictGroup.groupCode = '') AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_ITEMCODE_1 =
		"pushDictGroup.itemCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_ITEMCODE_2 =
		"pushDictGroup.itemCode = ? AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_ITEMCODE_3 =
		"(pushDictGroup.itemCode IS NULL OR pushDictGroup.itemCode = '') AND ";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_METHOD_1 =
		"pushDictGroup.method IS NULL";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_METHOD_2 =
		"pushDictGroup.method = ?";
	private static final String _FINDER_COLUMN_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD_METHOD_3 =
		"(pushDictGroup.method IS NULL OR pushDictGroup.method = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GROUPID_SERVERNO =
		new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED,
			PushDictGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_groupId_serverNo",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO =
		new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED,
			PushDictGroupImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_groupId_serverNo",
			new String[] { Long.class.getName(), String.class.getName() },
			PushDictGroupModelImpl.GROUPID_COLUMN_BITMASK |
			PushDictGroupModelImpl.SERVERNO_COLUMN_BITMASK |
			PushDictGroupModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO = new FinderPath(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_groupId_serverNo",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the push dict groups where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @return the matching push dict groups
	 */
	@Override
	public List<PushDictGroup> findByF_groupId_serverNo(long groupId,
		String serverNo) {
		return findByF_groupId_serverNo(groupId, serverNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push dict groups where groupId = &#63; and serverNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param start the lower bound of the range of push dict groups
	 * @param end the upper bound of the range of push dict groups (not inclusive)
	 * @return the range of matching push dict groups
	 */
	@Override
	public List<PushDictGroup> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end) {
		return findByF_groupId_serverNo(groupId, serverNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the push dict groups where groupId = &#63; and serverNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param start the lower bound of the range of push dict groups
	 * @param end the upper bound of the range of push dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching push dict groups
	 */
	@Override
	public List<PushDictGroup> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end,
		OrderByComparator<PushDictGroup> orderByComparator) {
		return findByF_groupId_serverNo(groupId, serverNo, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push dict groups where groupId = &#63; and serverNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param start the lower bound of the range of push dict groups
	 * @param end the upper bound of the range of push dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching push dict groups
	 */
	@Override
	public List<PushDictGroup> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end,
		OrderByComparator<PushDictGroup> orderByComparator,
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

		List<PushDictGroup> list = null;

		if (retrieveFromCache) {
			list = (List<PushDictGroup>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PushDictGroup pushDictGroup : list) {
					if ((groupId != pushDictGroup.getGroupId()) ||
							!Objects.equals(serverNo,
								pushDictGroup.getServerNo())) {
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

			query.append(_SQL_SELECT_PUSHDICTGROUP_WHERE);

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
				query.append(PushDictGroupModelImpl.ORDER_BY_JPQL);
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
					list = (List<PushDictGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushDictGroup>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first push dict group in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push dict group
	 * @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup findByF_groupId_serverNo_First(long groupId,
		String serverNo, OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = fetchByF_groupId_serverNo_First(groupId,
				serverNo, orderByComparator);

		if (pushDictGroup != null) {
			return pushDictGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serverNo=");
		msg.append(serverNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushDictGroupException(msg.toString());
	}

	/**
	 * Returns the first push dict group in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push dict group, or <code>null</code> if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup fetchByF_groupId_serverNo_First(long groupId,
		String serverNo, OrderByComparator<PushDictGroup> orderByComparator) {
		List<PushDictGroup> list = findByF_groupId_serverNo(groupId, serverNo,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last push dict group in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push dict group
	 * @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup findByF_groupId_serverNo_Last(long groupId,
		String serverNo, OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = fetchByF_groupId_serverNo_Last(groupId,
				serverNo, orderByComparator);

		if (pushDictGroup != null) {
			return pushDictGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serverNo=");
		msg.append(serverNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPushDictGroupException(msg.toString());
	}

	/**
	 * Returns the last push dict group in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push dict group, or <code>null</code> if a matching push dict group could not be found
	 */
	@Override
	public PushDictGroup fetchByF_groupId_serverNo_Last(long groupId,
		String serverNo, OrderByComparator<PushDictGroup> orderByComparator) {
		int count = countByF_groupId_serverNo(groupId, serverNo);

		if (count == 0) {
			return null;
		}

		List<PushDictGroup> list = findByF_groupId_serverNo(groupId, serverNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the push dict groups before and after the current push dict group in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param pushDictGroupId the primary key of the current push dict group
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next push dict group
	 * @throws NoSuchPushDictGroupException if a push dict group with the primary key could not be found
	 */
	@Override
	public PushDictGroup[] findByF_groupId_serverNo_PrevAndNext(
		long pushDictGroupId, long groupId, String serverNo,
		OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = findByPrimaryKey(pushDictGroupId);

		Session session = null;

		try {
			session = openSession();

			PushDictGroup[] array = new PushDictGroupImpl[3];

			array[0] = getByF_groupId_serverNo_PrevAndNext(session,
					pushDictGroup, groupId, serverNo, orderByComparator, true);

			array[1] = pushDictGroup;

			array[2] = getByF_groupId_serverNo_PrevAndNext(session,
					pushDictGroup, groupId, serverNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PushDictGroup getByF_groupId_serverNo_PrevAndNext(
		Session session, PushDictGroup pushDictGroup, long groupId,
		String serverNo, OrderByComparator<PushDictGroup> orderByComparator,
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

		query.append(_SQL_SELECT_PUSHDICTGROUP_WHERE);

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
			query.append(PushDictGroupModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(pushDictGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PushDictGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the push dict groups where groupId = &#63; and serverNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 */
	@Override
	public void removeByF_groupId_serverNo(long groupId, String serverNo) {
		for (PushDictGroup pushDictGroup : findByF_groupId_serverNo(groupId,
				serverNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pushDictGroup);
		}
	}

	/**
	 * Returns the number of push dict groups where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @return the number of matching push dict groups
	 */
	@Override
	public int countByF_groupId_serverNo(long groupId, String serverNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO;

		Object[] finderArgs = new Object[] { groupId, serverNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PUSHDICTGROUP_WHERE);

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

	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_GROUPID_2 = "pushDictGroup.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_1 = "pushDictGroup.serverNo IS NULL";
	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_2 = "pushDictGroup.serverNo = ?";
	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_3 = "(pushDictGroup.serverNo IS NULL OR pushDictGroup.serverNo = '')";

	public PushDictGroupPersistenceImpl() {
		setModelClass(PushDictGroup.class);
	}

	/**
	 * Caches the push dict group in the entity cache if it is enabled.
	 *
	 * @param pushDictGroup the push dict group
	 */
	@Override
	public void cacheResult(PushDictGroup pushDictGroup) {
		entityCache.putResult(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupImpl.class, pushDictGroup.getPrimaryKey(),
			pushDictGroup);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { pushDictGroup.getUuid(), pushDictGroup.getGroupId() },
			pushDictGroup);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_METHOD,
			new Object[] {
				pushDictGroup.getGroupId(), pushDictGroup.getCollectionCode(),
				pushDictGroup.getGroupCode(), pushDictGroup.getMethod()
			}, pushDictGroup);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD,
			new Object[] {
				pushDictGroup.getGroupId(), pushDictGroup.getCollectionCode(),
				pushDictGroup.getGroupCode(), pushDictGroup.getItemCode(),
				pushDictGroup.getMethod()
			}, pushDictGroup);

		pushDictGroup.resetOriginalValues();
	}

	/**
	 * Caches the push dict groups in the entity cache if it is enabled.
	 *
	 * @param pushDictGroups the push dict groups
	 */
	@Override
	public void cacheResult(List<PushDictGroup> pushDictGroups) {
		for (PushDictGroup pushDictGroup : pushDictGroups) {
			if (entityCache.getResult(
						PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
						PushDictGroupImpl.class, pushDictGroup.getPrimaryKey()) == null) {
				cacheResult(pushDictGroup);
			}
			else {
				pushDictGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all push dict groups.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PushDictGroupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the push dict group.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PushDictGroup pushDictGroup) {
		entityCache.removeResult(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupImpl.class, pushDictGroup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PushDictGroupModelImpl)pushDictGroup, true);
	}

	@Override
	public void clearCache(List<PushDictGroup> pushDictGroups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PushDictGroup pushDictGroup : pushDictGroups) {
			entityCache.removeResult(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
				PushDictGroupImpl.class, pushDictGroup.getPrimaryKey());

			clearUniqueFindersCache((PushDictGroupModelImpl)pushDictGroup, true);
		}
	}

	protected void cacheUniqueFindersCache(
		PushDictGroupModelImpl pushDictGroupModelImpl) {
		Object[] args = new Object[] {
				pushDictGroupModelImpl.getUuid(),
				pushDictGroupModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			pushDictGroupModelImpl, false);

		args = new Object[] {
				pushDictGroupModelImpl.getGroupId(),
				pushDictGroupModelImpl.getCollectionCode(),
				pushDictGroupModelImpl.getGroupCode(),
				pushDictGroupModelImpl.getMethod()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_GROUPCODE_METHOD,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_METHOD,
			args, pushDictGroupModelImpl, false);

		args = new Object[] {
				pushDictGroupModelImpl.getGroupId(),
				pushDictGroupModelImpl.getCollectionCode(),
				pushDictGroupModelImpl.getGroupCode(),
				pushDictGroupModelImpl.getItemCode(),
				pushDictGroupModelImpl.getMethod()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD,
			args, pushDictGroupModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PushDictGroupModelImpl pushDictGroupModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					pushDictGroupModelImpl.getUuid(),
					pushDictGroupModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((pushDictGroupModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					pushDictGroupModelImpl.getOriginalUuid(),
					pushDictGroupModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					pushDictGroupModelImpl.getGroupId(),
					pushDictGroupModelImpl.getCollectionCode(),
					pushDictGroupModelImpl.getGroupCode(),
					pushDictGroupModelImpl.getMethod()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_GROUPCODE_METHOD,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_METHOD,
				args);
		}

		if ((pushDictGroupModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_METHOD.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					pushDictGroupModelImpl.getOriginalGroupId(),
					pushDictGroupModelImpl.getOriginalCollectionCode(),
					pushDictGroupModelImpl.getOriginalGroupCode(),
					pushDictGroupModelImpl.getOriginalMethod()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_GROUPCODE_METHOD,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_METHOD,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					pushDictGroupModelImpl.getGroupId(),
					pushDictGroupModelImpl.getCollectionCode(),
					pushDictGroupModelImpl.getGroupCode(),
					pushDictGroupModelImpl.getItemCode(),
					pushDictGroupModelImpl.getMethod()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD,
				args);
		}

		if ((pushDictGroupModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					pushDictGroupModelImpl.getOriginalGroupId(),
					pushDictGroupModelImpl.getOriginalCollectionCode(),
					pushDictGroupModelImpl.getOriginalGroupCode(),
					pushDictGroupModelImpl.getOriginalItemCode(),
					pushDictGroupModelImpl.getOriginalMethod()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_COLLECTIONCODE_GROUPCODE_ITEMCODE_METHOD,
				args);
		}
	}

	/**
	 * Creates a new push dict group with the primary key. Does not add the push dict group to the database.
	 *
	 * @param pushDictGroupId the primary key for the new push dict group
	 * @return the new push dict group
	 */
	@Override
	public PushDictGroup create(long pushDictGroupId) {
		PushDictGroup pushDictGroup = new PushDictGroupImpl();

		pushDictGroup.setNew(true);
		pushDictGroup.setPrimaryKey(pushDictGroupId);

		String uuid = PortalUUIDUtil.generate();

		pushDictGroup.setUuid(uuid);

		pushDictGroup.setCompanyId(companyProvider.getCompanyId());

		return pushDictGroup;
	}

	/**
	 * Removes the push dict group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pushDictGroupId the primary key of the push dict group
	 * @return the push dict group that was removed
	 * @throws NoSuchPushDictGroupException if a push dict group with the primary key could not be found
	 */
	@Override
	public PushDictGroup remove(long pushDictGroupId)
		throws NoSuchPushDictGroupException {
		return remove((Serializable)pushDictGroupId);
	}

	/**
	 * Removes the push dict group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the push dict group
	 * @return the push dict group that was removed
	 * @throws NoSuchPushDictGroupException if a push dict group with the primary key could not be found
	 */
	@Override
	public PushDictGroup remove(Serializable primaryKey)
		throws NoSuchPushDictGroupException {
		Session session = null;

		try {
			session = openSession();

			PushDictGroup pushDictGroup = (PushDictGroup)session.get(PushDictGroupImpl.class,
					primaryKey);

			if (pushDictGroup == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPushDictGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(pushDictGroup);
		}
		catch (NoSuchPushDictGroupException nsee) {
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
	protected PushDictGroup removeImpl(PushDictGroup pushDictGroup) {
		pushDictGroup = toUnwrappedModel(pushDictGroup);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(pushDictGroup)) {
				pushDictGroup = (PushDictGroup)session.get(PushDictGroupImpl.class,
						pushDictGroup.getPrimaryKeyObj());
			}

			if (pushDictGroup != null) {
				session.delete(pushDictGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (pushDictGroup != null) {
			clearCache(pushDictGroup);
		}

		return pushDictGroup;
	}

	@Override
	public PushDictGroup updateImpl(PushDictGroup pushDictGroup) {
		pushDictGroup = toUnwrappedModel(pushDictGroup);

		boolean isNew = pushDictGroup.isNew();

		PushDictGroupModelImpl pushDictGroupModelImpl = (PushDictGroupModelImpl)pushDictGroup;

		if (Validator.isNull(pushDictGroup.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			pushDictGroup.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (pushDictGroup.getCreateDate() == null)) {
			if (serviceContext == null) {
				pushDictGroup.setCreateDate(now);
			}
			else {
				pushDictGroup.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!pushDictGroupModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				pushDictGroup.setModifiedDate(now);
			}
			else {
				pushDictGroup.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (pushDictGroup.isNew()) {
				session.save(pushDictGroup);

				pushDictGroup.setNew(false);
			}
			else {
				pushDictGroup = (PushDictGroup)session.merge(pushDictGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PushDictGroupModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((pushDictGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pushDictGroupModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { pushDictGroupModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((pushDictGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pushDictGroupModelImpl.getOriginalUuid(),
						pushDictGroupModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						pushDictGroupModelImpl.getUuid(),
						pushDictGroupModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((pushDictGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pushDictGroupModelImpl.getOriginalGroupId(),
						pushDictGroupModelImpl.getOriginalServerNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO,
					args);

				args = new Object[] {
						pushDictGroupModelImpl.getGroupId(),
						pushDictGroupModelImpl.getServerNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO,
					args);
			}
		}

		entityCache.putResult(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
			PushDictGroupImpl.class, pushDictGroup.getPrimaryKey(),
			pushDictGroup, false);

		clearUniqueFindersCache(pushDictGroupModelImpl, false);
		cacheUniqueFindersCache(pushDictGroupModelImpl);

		pushDictGroup.resetOriginalValues();

		return pushDictGroup;
	}

	protected PushDictGroup toUnwrappedModel(PushDictGroup pushDictGroup) {
		if (pushDictGroup instanceof PushDictGroupImpl) {
			return pushDictGroup;
		}

		PushDictGroupImpl pushDictGroupImpl = new PushDictGroupImpl();

		pushDictGroupImpl.setNew(pushDictGroup.isNew());
		pushDictGroupImpl.setPrimaryKey(pushDictGroup.getPrimaryKey());

		pushDictGroupImpl.setUuid(pushDictGroup.getUuid());
		pushDictGroupImpl.setPushDictGroupId(pushDictGroup.getPushDictGroupId());
		pushDictGroupImpl.setGroupId(pushDictGroup.getGroupId());
		pushDictGroupImpl.setCompanyId(pushDictGroup.getCompanyId());
		pushDictGroupImpl.setUserId(pushDictGroup.getUserId());
		pushDictGroupImpl.setUserName(pushDictGroup.getUserName());
		pushDictGroupImpl.setCreateDate(pushDictGroup.getCreateDate());
		pushDictGroupImpl.setModifiedDate(pushDictGroup.getModifiedDate());
		pushDictGroupImpl.setServerNo(pushDictGroup.getServerNo());
		pushDictGroupImpl.setCollectionCode(pushDictGroup.getCollectionCode());
		pushDictGroupImpl.setGroupCode(pushDictGroup.getGroupCode());
		pushDictGroupImpl.setGroupName(pushDictGroup.getGroupName());
		pushDictGroupImpl.setGroupNameEN(pushDictGroup.getGroupNameEN());
		pushDictGroupImpl.setGroupDescription(pushDictGroup.getGroupDescription());
		pushDictGroupImpl.setItemCode(pushDictGroup.getItemCode());
		pushDictGroupImpl.setMethod(pushDictGroup.getMethod());

		return pushDictGroupImpl;
	}

	/**
	 * Returns the push dict group with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the push dict group
	 * @return the push dict group
	 * @throws NoSuchPushDictGroupException if a push dict group with the primary key could not be found
	 */
	@Override
	public PushDictGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPushDictGroupException {
		PushDictGroup pushDictGroup = fetchByPrimaryKey(primaryKey);

		if (pushDictGroup == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPushDictGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return pushDictGroup;
	}

	/**
	 * Returns the push dict group with the primary key or throws a {@link NoSuchPushDictGroupException} if it could not be found.
	 *
	 * @param pushDictGroupId the primary key of the push dict group
	 * @return the push dict group
	 * @throws NoSuchPushDictGroupException if a push dict group with the primary key could not be found
	 */
	@Override
	public PushDictGroup findByPrimaryKey(long pushDictGroupId)
		throws NoSuchPushDictGroupException {
		return findByPrimaryKey((Serializable)pushDictGroupId);
	}

	/**
	 * Returns the push dict group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the push dict group
	 * @return the push dict group, or <code>null</code> if a push dict group with the primary key could not be found
	 */
	@Override
	public PushDictGroup fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
				PushDictGroupImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PushDictGroup pushDictGroup = (PushDictGroup)serializable;

		if (pushDictGroup == null) {
			Session session = null;

			try {
				session = openSession();

				pushDictGroup = (PushDictGroup)session.get(PushDictGroupImpl.class,
						primaryKey);

				if (pushDictGroup != null) {
					cacheResult(pushDictGroup);
				}
				else {
					entityCache.putResult(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
						PushDictGroupImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
					PushDictGroupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return pushDictGroup;
	}

	/**
	 * Returns the push dict group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pushDictGroupId the primary key of the push dict group
	 * @return the push dict group, or <code>null</code> if a push dict group with the primary key could not be found
	 */
	@Override
	public PushDictGroup fetchByPrimaryKey(long pushDictGroupId) {
		return fetchByPrimaryKey((Serializable)pushDictGroupId);
	}

	@Override
	public Map<Serializable, PushDictGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PushDictGroup> map = new HashMap<Serializable, PushDictGroup>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PushDictGroup pushDictGroup = fetchByPrimaryKey(primaryKey);

			if (pushDictGroup != null) {
				map.put(primaryKey, pushDictGroup);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
					PushDictGroupImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PushDictGroup)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PUSHDICTGROUP_WHERE_PKS_IN);

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

			for (PushDictGroup pushDictGroup : (List<PushDictGroup>)q.list()) {
				map.put(pushDictGroup.getPrimaryKeyObj(), pushDictGroup);

				cacheResult(pushDictGroup);

				uncachedPrimaryKeys.remove(pushDictGroup.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PushDictGroupModelImpl.ENTITY_CACHE_ENABLED,
					PushDictGroupImpl.class, primaryKey, nullModel);
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
	 * Returns all the push dict groups.
	 *
	 * @return the push dict groups
	 */
	@Override
	public List<PushDictGroup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push dict groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push dict groups
	 * @param end the upper bound of the range of push dict groups (not inclusive)
	 * @return the range of push dict groups
	 */
	@Override
	public List<PushDictGroup> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the push dict groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push dict groups
	 * @param end the upper bound of the range of push dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of push dict groups
	 */
	@Override
	public List<PushDictGroup> findAll(int start, int end,
		OrderByComparator<PushDictGroup> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push dict groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push dict groups
	 * @param end the upper bound of the range of push dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of push dict groups
	 */
	@Override
	public List<PushDictGroup> findAll(int start, int end,
		OrderByComparator<PushDictGroup> orderByComparator,
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

		List<PushDictGroup> list = null;

		if (retrieveFromCache) {
			list = (List<PushDictGroup>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PUSHDICTGROUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PUSHDICTGROUP;

				if (pagination) {
					sql = sql.concat(PushDictGroupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PushDictGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushDictGroup>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the push dict groups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PushDictGroup pushDictGroup : findAll()) {
			remove(pushDictGroup);
		}
	}

	/**
	 * Returns the number of push dict groups.
	 *
	 * @return the number of push dict groups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PUSHDICTGROUP);

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
		return PushDictGroupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the push dict group persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PushDictGroupImpl.class.getName());
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
	private static final String _SQL_SELECT_PUSHDICTGROUP = "SELECT pushDictGroup FROM PushDictGroup pushDictGroup";
	private static final String _SQL_SELECT_PUSHDICTGROUP_WHERE_PKS_IN = "SELECT pushDictGroup FROM PushDictGroup pushDictGroup WHERE pushDictGroupId IN (";
	private static final String _SQL_SELECT_PUSHDICTGROUP_WHERE = "SELECT pushDictGroup FROM PushDictGroup pushDictGroup WHERE ";
	private static final String _SQL_COUNT_PUSHDICTGROUP = "SELECT COUNT(pushDictGroup) FROM PushDictGroup pushDictGroup";
	private static final String _SQL_COUNT_PUSHDICTGROUP_WHERE = "SELECT COUNT(pushDictGroup) FROM PushDictGroup pushDictGroup WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "pushDictGroup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PushDictGroup exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PushDictGroup exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PushDictGroupPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}