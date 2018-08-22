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

import org.opencps.datamgt.exception.NoSuchDictGroupException;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.impl.DictGroupImpl;
import org.opencps.datamgt.model.impl.DictGroupModelImpl;
import org.opencps.datamgt.service.persistence.DictGroupPersistence;

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
 * The persistence implementation for the dict group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see DictGroupPersistence
 * @see org.opencps.datamgt.service.persistence.DictGroupUtil
 * @generated
 */
@ProviderType
public class DictGroupPersistenceImpl extends BasePersistenceImpl<DictGroup>
	implements DictGroupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DictGroupUtil} to access the dict group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DictGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, DictGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, DictGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, DictGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, DictGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DictGroupModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dict groups where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dict groups
	 */
	@Override
	public List<DictGroup> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @return the range of matching dict groups
	 */
	@Override
	public List<DictGroup> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict groups
	 */
	@Override
	public List<DictGroup> findByUuid(String uuid, int start, int end,
		OrderByComparator<DictGroup> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict groups
	 */
	@Override
	public List<DictGroup> findByUuid(String uuid, int start, int end,
		OrderByComparator<DictGroup> orderByComparator,
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

		List<DictGroup> list = null;

		if (retrieveFromCache) {
			list = (List<DictGroup>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictGroup dictGroup : list) {
					if (!Objects.equals(uuid, dictGroup.getUuid())) {
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

			query.append(_SQL_SELECT_DICTGROUP_WHERE);

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
				query.append(DictGroupModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictGroup>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group
	 * @throws NoSuchDictGroupException if a matching dict group could not be found
	 */
	@Override
	public DictGroup findByUuid_First(String uuid,
		OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = fetchByUuid_First(uuid, orderByComparator);

		if (dictGroup != null) {
			return dictGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDictGroupException(msg.toString());
	}

	/**
	 * Returns the first dict group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group, or <code>null</code> if a matching dict group could not be found
	 */
	@Override
	public DictGroup fetchByUuid_First(String uuid,
		OrderByComparator<DictGroup> orderByComparator) {
		List<DictGroup> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group
	 * @throws NoSuchDictGroupException if a matching dict group could not be found
	 */
	@Override
	public DictGroup findByUuid_Last(String uuid,
		OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = fetchByUuid_Last(uuid, orderByComparator);

		if (dictGroup != null) {
			return dictGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDictGroupException(msg.toString());
	}

	/**
	 * Returns the last dict group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group, or <code>null</code> if a matching dict group could not be found
	 */
	@Override
	public DictGroup fetchByUuid_Last(String uuid,
		OrderByComparator<DictGroup> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DictGroup> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict groups before and after the current dict group in the ordered set where uuid = &#63;.
	 *
	 * @param dictGroupId the primary key of the current dict group
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict group
	 * @throws NoSuchDictGroupException if a dict group with the primary key could not be found
	 */
	@Override
	public DictGroup[] findByUuid_PrevAndNext(long dictGroupId, String uuid,
		OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = findByPrimaryKey(dictGroupId);

		Session session = null;

		try {
			session = openSession();

			DictGroup[] array = new DictGroupImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dictGroup, uuid,
					orderByComparator, true);

			array[1] = dictGroup;

			array[2] = getByUuid_PrevAndNext(session, dictGroup, uuid,
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

	protected DictGroup getByUuid_PrevAndNext(Session session,
		DictGroup dictGroup, String uuid,
		OrderByComparator<DictGroup> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DICTGROUP_WHERE);

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
			query.append(DictGroupModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict groups where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DictGroup dictGroup : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dictGroup);
		}
	}

	/**
	 * Returns the number of dict groups where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dict groups
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DICTGROUP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dictGroup.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dictGroup.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dictGroup.uuid IS NULL OR dictGroup.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, DictGroupImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DictGroupModelImpl.UUID_COLUMN_BITMASK |
			DictGroupModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict group where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictGroupException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dict group
	 * @throws NoSuchDictGroupException if a matching dict group could not be found
	 */
	@Override
	public DictGroup findByUUID_G(String uuid, long groupId)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = fetchByUUID_G(uuid, groupId);

		if (dictGroup == null) {
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

			throw new NoSuchDictGroupException(msg.toString());
		}

		return dictGroup;
	}

	/**
	 * Returns the dict group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dict group, or <code>null</code> if a matching dict group could not be found
	 */
	@Override
	public DictGroup fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dict group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict group, or <code>null</code> if a matching dict group could not be found
	 */
	@Override
	public DictGroup fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DictGroup) {
			DictGroup dictGroup = (DictGroup)result;

			if (!Objects.equals(uuid, dictGroup.getUuid()) ||
					(groupId != dictGroup.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTGROUP_WHERE);

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

				List<DictGroup> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DictGroup dictGroup = list.get(0);

					result = dictGroup;

					cacheResult(dictGroup);
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
			return (DictGroup)result;
		}
	}

	/**
	 * Removes the dict group where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dict group that was removed
	 */
	@Override
	public DictGroup removeByUUID_G(String uuid, long groupId)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = findByUUID_G(uuid, groupId);

		return remove(dictGroup);
	}

	/**
	 * Returns the number of dict groups where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dict groups
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTGROUP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dictGroup.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dictGroup.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dictGroup.uuid IS NULL OR dictGroup.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dictGroup.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, DictGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, DictGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DictGroupModelImpl.UUID_COLUMN_BITMASK |
			DictGroupModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict groups where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dict groups
	 */
	@Override
	public List<DictGroup> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @return the range of matching dict groups
	 */
	@Override
	public List<DictGroup> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict groups
	 */
	@Override
	public List<DictGroup> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<DictGroup> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict groups
	 */
	@Override
	public List<DictGroup> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<DictGroup> orderByComparator,
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

		List<DictGroup> list = null;

		if (retrieveFromCache) {
			list = (List<DictGroup>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictGroup dictGroup : list) {
					if (!Objects.equals(uuid, dictGroup.getUuid()) ||
							(companyId != dictGroup.getCompanyId())) {
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

			query.append(_SQL_SELECT_DICTGROUP_WHERE);

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
				query.append(DictGroupModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictGroup>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group
	 * @throws NoSuchDictGroupException if a matching dict group could not be found
	 */
	@Override
	public DictGroup findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (dictGroup != null) {
			return dictGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDictGroupException(msg.toString());
	}

	/**
	 * Returns the first dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group, or <code>null</code> if a matching dict group could not be found
	 */
	@Override
	public DictGroup fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictGroup> orderByComparator) {
		List<DictGroup> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group
	 * @throws NoSuchDictGroupException if a matching dict group could not be found
	 */
	@Override
	public DictGroup findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (dictGroup != null) {
			return dictGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDictGroupException(msg.toString());
	}

	/**
	 * Returns the last dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group, or <code>null</code> if a matching dict group could not be found
	 */
	@Override
	public DictGroup fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictGroup> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DictGroup> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict groups before and after the current dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dictGroupId the primary key of the current dict group
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict group
	 * @throws NoSuchDictGroupException if a dict group with the primary key could not be found
	 */
	@Override
	public DictGroup[] findByUuid_C_PrevAndNext(long dictGroupId, String uuid,
		long companyId, OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = findByPrimaryKey(dictGroupId);

		Session session = null;

		try {
			session = openSession();

			DictGroup[] array = new DictGroupImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dictGroup, uuid,
					companyId, orderByComparator, true);

			array[1] = dictGroup;

			array[2] = getByUuid_C_PrevAndNext(session, dictGroup, uuid,
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

	protected DictGroup getByUuid_C_PrevAndNext(Session session,
		DictGroup dictGroup, String uuid, long companyId,
		OrderByComparator<DictGroup> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTGROUP_WHERE);

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
			query.append(DictGroupModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict groups where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DictGroup dictGroup : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictGroup);
		}
	}

	/**
	 * Returns the number of dict groups where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dict groups
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTGROUP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dictGroup.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dictGroup.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dictGroup.uuid IS NULL OR dictGroup.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dictGroup.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GROUPCODE = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, DictGroupImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_groupCode",
			new String[] { String.class.getName(), Long.class.getName() },
			DictGroupModelImpl.GROUPCODE_COLUMN_BITMASK |
			DictGroupModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GROUPCODE = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_groupCode",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict group where groupCode = &#63; and groupId = &#63; or throws a {@link NoSuchDictGroupException} if it could not be found.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @return the matching dict group
	 * @throws NoSuchDictGroupException if a matching dict group could not be found
	 */
	@Override
	public DictGroup findByF_groupCode(String groupCode, long groupId)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = fetchByF_groupCode(groupCode, groupId);

		if (dictGroup == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupCode=");
			msg.append(groupCode);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDictGroupException(msg.toString());
		}

		return dictGroup;
	}

	/**
	 * Returns the dict group where groupCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @return the matching dict group, or <code>null</code> if a matching dict group could not be found
	 */
	@Override
	public DictGroup fetchByF_groupCode(String groupCode, long groupId) {
		return fetchByF_groupCode(groupCode, groupId, true);
	}

	/**
	 * Returns the dict group where groupCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict group, or <code>null</code> if a matching dict group could not be found
	 */
	@Override
	public DictGroup fetchByF_groupCode(String groupCode, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupCode, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GROUPCODE,
					finderArgs, this);
		}

		if (result instanceof DictGroup) {
			DictGroup dictGroup = (DictGroup)result;

			if (!Objects.equals(groupCode, dictGroup.getGroupCode()) ||
					(groupId != dictGroup.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTGROUP_WHERE);

			boolean bindGroupCode = false;

			if (groupCode == null) {
				query.append(_FINDER_COLUMN_F_GROUPCODE_GROUPCODE_1);
			}
			else if (groupCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GROUPCODE_GROUPCODE_3);
			}
			else {
				bindGroupCode = true;

				query.append(_FINDER_COLUMN_F_GROUPCODE_GROUPCODE_2);
			}

			query.append(_FINDER_COLUMN_F_GROUPCODE_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGroupCode) {
					qPos.add(groupCode);
				}

				qPos.add(groupId);

				List<DictGroup> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GROUPCODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictGroupPersistenceImpl.fetchByF_groupCode(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictGroup dictGroup = list.get(0);

					result = dictGroup;

					cacheResult(dictGroup);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GROUPCODE,
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
			return (DictGroup)result;
		}
	}

	/**
	 * Removes the dict group where groupCode = &#63; and groupId = &#63; from the database.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @return the dict group that was removed
	 */
	@Override
	public DictGroup removeByF_groupCode(String groupCode, long groupId)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = findByF_groupCode(groupCode, groupId);

		return remove(dictGroup);
	}

	/**
	 * Returns the number of dict groups where groupCode = &#63; and groupId = &#63;.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @return the number of matching dict groups
	 */
	@Override
	public int countByF_groupCode(String groupCode, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GROUPCODE;

		Object[] finderArgs = new Object[] { groupCode, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTGROUP_WHERE);

			boolean bindGroupCode = false;

			if (groupCode == null) {
				query.append(_FINDER_COLUMN_F_GROUPCODE_GROUPCODE_1);
			}
			else if (groupCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GROUPCODE_GROUPCODE_3);
			}
			else {
				bindGroupCode = true;

				query.append(_FINDER_COLUMN_F_GROUPCODE_GROUPCODE_2);
			}

			query.append(_FINDER_COLUMN_F_GROUPCODE_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGroupCode) {
					qPos.add(groupCode);
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

	private static final String _FINDER_COLUMN_F_GROUPCODE_GROUPCODE_1 = "dictGroup.groupCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GROUPCODE_GROUPCODE_2 = "dictGroup.groupCode = ? AND ";
	private static final String _FINDER_COLUMN_F_GROUPCODE_GROUPCODE_3 = "(dictGroup.groupCode IS NULL OR dictGroup.groupCode = '') AND ";
	private static final String _FINDER_COLUMN_F_GROUPCODE_GROUPID_2 = "dictGroup.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_GC_GI_DCI = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, DictGroupImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByGC_GI_DCI",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			DictGroupModelImpl.GROUPCODE_COLUMN_BITMASK |
			DictGroupModelImpl.GROUPID_COLUMN_BITMASK |
			DictGroupModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GC_GI_DCI = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGC_GI_DCI",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns the dict group where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; or throws a {@link NoSuchDictGroupException} if it could not be found.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @return the matching dict group
	 * @throws NoSuchDictGroupException if a matching dict group could not be found
	 */
	@Override
	public DictGroup findByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId) throws NoSuchDictGroupException {
		DictGroup dictGroup = fetchByGC_GI_DCI(groupCode, groupId,
				dictCollectionId);

		if (dictGroup == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupCode=");
			msg.append(groupCode);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(", dictCollectionId=");
			msg.append(dictCollectionId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDictGroupException(msg.toString());
		}

		return dictGroup;
	}

	/**
	 * Returns the dict group where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @return the matching dict group, or <code>null</code> if a matching dict group could not be found
	 */
	@Override
	public DictGroup fetchByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId) {
		return fetchByGC_GI_DCI(groupCode, groupId, dictCollectionId, true);
	}

	/**
	 * Returns the dict group where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict group, or <code>null</code> if a matching dict group could not be found
	 */
	@Override
	public DictGroup fetchByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupCode, groupId, dictCollectionId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_GC_GI_DCI,
					finderArgs, this);
		}

		if (result instanceof DictGroup) {
			DictGroup dictGroup = (DictGroup)result;

			if (!Objects.equals(groupCode, dictGroup.getGroupCode()) ||
					(groupId != dictGroup.getGroupId()) ||
					(dictCollectionId != dictGroup.getDictCollectionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DICTGROUP_WHERE);

			boolean bindGroupCode = false;

			if (groupCode == null) {
				query.append(_FINDER_COLUMN_GC_GI_DCI_GROUPCODE_1);
			}
			else if (groupCode.equals("")) {
				query.append(_FINDER_COLUMN_GC_GI_DCI_GROUPCODE_3);
			}
			else {
				bindGroupCode = true;

				query.append(_FINDER_COLUMN_GC_GI_DCI_GROUPCODE_2);
			}

			query.append(_FINDER_COLUMN_GC_GI_DCI_GROUPID_2);

			query.append(_FINDER_COLUMN_GC_GI_DCI_DICTCOLLECTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGroupCode) {
					qPos.add(groupCode);
				}

				qPos.add(groupId);

				qPos.add(dictCollectionId);

				List<DictGroup> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_GC_GI_DCI,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictGroupPersistenceImpl.fetchByGC_GI_DCI(String, long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictGroup dictGroup = list.get(0);

					result = dictGroup;

					cacheResult(dictGroup);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_GC_GI_DCI,
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
			return (DictGroup)result;
		}
	}

	/**
	 * Removes the dict group where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; from the database.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @return the dict group that was removed
	 */
	@Override
	public DictGroup removeByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId) throws NoSuchDictGroupException {
		DictGroup dictGroup = findByGC_GI_DCI(groupCode, groupId,
				dictCollectionId);

		return remove(dictGroup);
	}

	/**
	 * Returns the number of dict groups where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63;.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @return the number of matching dict groups
	 */
	@Override
	public int countByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GC_GI_DCI;

		Object[] finderArgs = new Object[] { groupCode, groupId, dictCollectionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DICTGROUP_WHERE);

			boolean bindGroupCode = false;

			if (groupCode == null) {
				query.append(_FINDER_COLUMN_GC_GI_DCI_GROUPCODE_1);
			}
			else if (groupCode.equals("")) {
				query.append(_FINDER_COLUMN_GC_GI_DCI_GROUPCODE_3);
			}
			else {
				bindGroupCode = true;

				query.append(_FINDER_COLUMN_GC_GI_DCI_GROUPCODE_2);
			}

			query.append(_FINDER_COLUMN_GC_GI_DCI_GROUPID_2);

			query.append(_FINDER_COLUMN_GC_GI_DCI_DICTCOLLECTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGroupCode) {
					qPos.add(groupCode);
				}

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GC_GI_DCI_GROUPCODE_1 = "dictGroup.groupCode IS NULL AND ";
	private static final String _FINDER_COLUMN_GC_GI_DCI_GROUPCODE_2 = "dictGroup.groupCode = ? AND ";
	private static final String _FINDER_COLUMN_GC_GI_DCI_GROUPCODE_3 = "(dictGroup.groupCode IS NULL OR dictGroup.groupCode = '') AND ";
	private static final String _FINDER_COLUMN_GC_GI_DCI_GROUPID_2 = "dictGroup.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GC_GI_DCI_DICTCOLLECTIONID_2 = "dictGroup.dictCollectionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_DC = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, DictGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGID_DC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_DC =
		new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, DictGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGID_DC",
			new String[] { Long.class.getName(), Long.class.getName() },
			DictGroupModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK |
			DictGroupModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_DC = new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGID_DC",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict groups where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the matching dict groups
	 */
	@Override
	public List<DictGroup> findByGID_DC(long dictCollectionId, long groupId) {
		return findByGID_DC(dictCollectionId, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict groups where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @return the range of matching dict groups
	 */
	@Override
	public List<DictGroup> findByGID_DC(long dictCollectionId, long groupId,
		int start, int end) {
		return findByGID_DC(dictCollectionId, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict groups where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict groups
	 */
	@Override
	public List<DictGroup> findByGID_DC(long dictCollectionId, long groupId,
		int start, int end, OrderByComparator<DictGroup> orderByComparator) {
		return findByGID_DC(dictCollectionId, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict groups where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict groups
	 */
	@Override
	public List<DictGroup> findByGID_DC(long dictCollectionId, long groupId,
		int start, int end, OrderByComparator<DictGroup> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_DC;
			finderArgs = new Object[] { dictCollectionId, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_DC;
			finderArgs = new Object[] {
					dictCollectionId, groupId,
					
					start, end, orderByComparator
				};
		}

		List<DictGroup> list = null;

		if (retrieveFromCache) {
			list = (List<DictGroup>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictGroup dictGroup : list) {
					if ((dictCollectionId != dictGroup.getDictCollectionId()) ||
							(groupId != dictGroup.getGroupId())) {
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

			query.append(_SQL_SELECT_DICTGROUP_WHERE);

			query.append(_FINDER_COLUMN_GID_DC_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_GID_DC_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictGroupModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictGroup>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict group in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group
	 * @throws NoSuchDictGroupException if a matching dict group could not be found
	 */
	@Override
	public DictGroup findByGID_DC_First(long dictCollectionId, long groupId,
		OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = fetchByGID_DC_First(dictCollectionId, groupId,
				orderByComparator);

		if (dictGroup != null) {
			return dictGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictGroupException(msg.toString());
	}

	/**
	 * Returns the first dict group in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group, or <code>null</code> if a matching dict group could not be found
	 */
	@Override
	public DictGroup fetchByGID_DC_First(long dictCollectionId, long groupId,
		OrderByComparator<DictGroup> orderByComparator) {
		List<DictGroup> list = findByGID_DC(dictCollectionId, groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict group in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group
	 * @throws NoSuchDictGroupException if a matching dict group could not be found
	 */
	@Override
	public DictGroup findByGID_DC_Last(long dictCollectionId, long groupId,
		OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = fetchByGID_DC_Last(dictCollectionId, groupId,
				orderByComparator);

		if (dictGroup != null) {
			return dictGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictGroupException(msg.toString());
	}

	/**
	 * Returns the last dict group in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group, or <code>null</code> if a matching dict group could not be found
	 */
	@Override
	public DictGroup fetchByGID_DC_Last(long dictCollectionId, long groupId,
		OrderByComparator<DictGroup> orderByComparator) {
		int count = countByGID_DC(dictCollectionId, groupId);

		if (count == 0) {
			return null;
		}

		List<DictGroup> list = findByGID_DC(dictCollectionId, groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict groups before and after the current dict group in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictGroupId the primary key of the current dict group
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict group
	 * @throws NoSuchDictGroupException if a dict group with the primary key could not be found
	 */
	@Override
	public DictGroup[] findByGID_DC_PrevAndNext(long dictGroupId,
		long dictCollectionId, long groupId,
		OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = findByPrimaryKey(dictGroupId);

		Session session = null;

		try {
			session = openSession();

			DictGroup[] array = new DictGroupImpl[3];

			array[0] = getByGID_DC_PrevAndNext(session, dictGroup,
					dictCollectionId, groupId, orderByComparator, true);

			array[1] = dictGroup;

			array[2] = getByGID_DC_PrevAndNext(session, dictGroup,
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

	protected DictGroup getByGID_DC_PrevAndNext(Session session,
		DictGroup dictGroup, long dictCollectionId, long groupId,
		OrderByComparator<DictGroup> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTGROUP_WHERE);

		query.append(_FINDER_COLUMN_GID_DC_DICTCOLLECTIONID_2);

		query.append(_FINDER_COLUMN_GID_DC_GROUPID_2);

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
			query.append(DictGroupModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dictCollectionId);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict groups where dictCollectionId = &#63; and groupId = &#63; from the database.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGID_DC(long dictCollectionId, long groupId) {
		for (DictGroup dictGroup : findByGID_DC(dictCollectionId, groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictGroup);
		}
	}

	/**
	 * Returns the number of dict groups where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the number of matching dict groups
	 */
	@Override
	public int countByGID_DC(long dictCollectionId, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_DC;

		Object[] finderArgs = new Object[] { dictCollectionId, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTGROUP_WHERE);

			query.append(_FINDER_COLUMN_GID_DC_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_GID_DC_GROUPID_2);

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

	private static final String _FINDER_COLUMN_GID_DC_DICTCOLLECTIONID_2 = "dictGroup.dictCollectionId = ? AND ";
	private static final String _FINDER_COLUMN_GID_DC_GROUPID_2 = "dictGroup.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTGROUPNEWERTHAN =
		new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, DictGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_dictGroupNewerThan",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_DICTGROUPNEWERTHAN =
		new FinderPath(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_dictGroupNewerThan",
			new String[] { Date.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict groups where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @return the matching dict groups
	 */
	@Override
	public List<DictGroup> findByF_dictGroupNewerThan(Date modifiedDate,
		long groupId) {
		return findByF_dictGroupNewerThan(modifiedDate, groupId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict groups where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @return the range of matching dict groups
	 */
	@Override
	public List<DictGroup> findByF_dictGroupNewerThan(Date modifiedDate,
		long groupId, int start, int end) {
		return findByF_dictGroupNewerThan(modifiedDate, groupId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the dict groups where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict groups
	 */
	@Override
	public List<DictGroup> findByF_dictGroupNewerThan(Date modifiedDate,
		long groupId, int start, int end,
		OrderByComparator<DictGroup> orderByComparator) {
		return findByF_dictGroupNewerThan(modifiedDate, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict groups where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict groups
	 */
	@Override
	public List<DictGroup> findByF_dictGroupNewerThan(Date modifiedDate,
		long groupId, int start, int end,
		OrderByComparator<DictGroup> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTGROUPNEWERTHAN;
		finderArgs = new Object[] {
				_getTime(modifiedDate), groupId,
				
				start, end, orderByComparator
			};

		List<DictGroup> list = null;

		if (retrieveFromCache) {
			list = (List<DictGroup>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictGroup dictGroup : list) {
					if ((modifiedDate.getTime() > dictGroup.getModifiedDate()
															   .getTime()) ||
							(groupId != dictGroup.getGroupId())) {
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

			query.append(_SQL_SELECT_DICTGROUP_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_DICTGROUPNEWERTHAN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_DICTGROUPNEWERTHAN_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_F_DICTGROUPNEWERTHAN_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictGroupModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictGroup>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict group in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group
	 * @throws NoSuchDictGroupException if a matching dict group could not be found
	 */
	@Override
	public DictGroup findByF_dictGroupNewerThan_First(Date modifiedDate,
		long groupId, OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = fetchByF_dictGroupNewerThan_First(modifiedDate,
				groupId, orderByComparator);

		if (dictGroup != null) {
			return dictGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictGroupException(msg.toString());
	}

	/**
	 * Returns the first dict group in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group, or <code>null</code> if a matching dict group could not be found
	 */
	@Override
	public DictGroup fetchByF_dictGroupNewerThan_First(Date modifiedDate,
		long groupId, OrderByComparator<DictGroup> orderByComparator) {
		List<DictGroup> list = findByF_dictGroupNewerThan(modifiedDate,
				groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict group in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group
	 * @throws NoSuchDictGroupException if a matching dict group could not be found
	 */
	@Override
	public DictGroup findByF_dictGroupNewerThan_Last(Date modifiedDate,
		long groupId, OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = fetchByF_dictGroupNewerThan_Last(modifiedDate,
				groupId, orderByComparator);

		if (dictGroup != null) {
			return dictGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictGroupException(msg.toString());
	}

	/**
	 * Returns the last dict group in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group, or <code>null</code> if a matching dict group could not be found
	 */
	@Override
	public DictGroup fetchByF_dictGroupNewerThan_Last(Date modifiedDate,
		long groupId, OrderByComparator<DictGroup> orderByComparator) {
		int count = countByF_dictGroupNewerThan(modifiedDate, groupId);

		if (count == 0) {
			return null;
		}

		List<DictGroup> list = findByF_dictGroupNewerThan(modifiedDate,
				groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict groups before and after the current dict group in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param dictGroupId the primary key of the current dict group
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict group
	 * @throws NoSuchDictGroupException if a dict group with the primary key could not be found
	 */
	@Override
	public DictGroup[] findByF_dictGroupNewerThan_PrevAndNext(
		long dictGroupId, Date modifiedDate, long groupId,
		OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = findByPrimaryKey(dictGroupId);

		Session session = null;

		try {
			session = openSession();

			DictGroup[] array = new DictGroupImpl[3];

			array[0] = getByF_dictGroupNewerThan_PrevAndNext(session,
					dictGroup, modifiedDate, groupId, orderByComparator, true);

			array[1] = dictGroup;

			array[2] = getByF_dictGroupNewerThan_PrevAndNext(session,
					dictGroup, modifiedDate, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DictGroup getByF_dictGroupNewerThan_PrevAndNext(Session session,
		DictGroup dictGroup, Date modifiedDate, long groupId,
		OrderByComparator<DictGroup> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTGROUP_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_F_DICTGROUPNEWERTHAN_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_F_DICTGROUPNEWERTHAN_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_F_DICTGROUPNEWERTHAN_GROUPID_2);

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
			query.append(DictGroupModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict groups where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_dictGroupNewerThan(Date modifiedDate, long groupId) {
		for (DictGroup dictGroup : findByF_dictGroupNewerThan(modifiedDate,
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictGroup);
		}
	}

	/**
	 * Returns the number of dict groups where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @return the number of matching dict groups
	 */
	@Override
	public int countByF_dictGroupNewerThan(Date modifiedDate, long groupId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_DICTGROUPNEWERTHAN;

		Object[] finderArgs = new Object[] { _getTime(modifiedDate), groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTGROUP_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_DICTGROUPNEWERTHAN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_DICTGROUPNEWERTHAN_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_F_DICTGROUPNEWERTHAN_GROUPID_2);

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

	private static final String _FINDER_COLUMN_F_DICTGROUPNEWERTHAN_MODIFIEDDATE_1 =
		"dictGroup.modifiedDate IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DICTGROUPNEWERTHAN_MODIFIEDDATE_2 =
		"dictGroup.modifiedDate >= ? AND ";
	private static final String _FINDER_COLUMN_F_DICTGROUPNEWERTHAN_GROUPID_2 = "dictGroup.groupId = ?";

	public DictGroupPersistenceImpl() {
		setModelClass(DictGroup.class);

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
	 * Caches the dict group in the entity cache if it is enabled.
	 *
	 * @param dictGroup the dict group
	 */
	@Override
	public void cacheResult(DictGroup dictGroup) {
		entityCache.putResult(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupImpl.class, dictGroup.getPrimaryKey(), dictGroup);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dictGroup.getUuid(), dictGroup.getGroupId() },
			dictGroup);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GROUPCODE,
			new Object[] { dictGroup.getGroupCode(), dictGroup.getGroupId() },
			dictGroup);

		finderCache.putResult(FINDER_PATH_FETCH_BY_GC_GI_DCI,
			new Object[] {
				dictGroup.getGroupCode(), dictGroup.getGroupId(),
				dictGroup.getDictCollectionId()
			}, dictGroup);

		dictGroup.resetOriginalValues();
	}

	/**
	 * Caches the dict groups in the entity cache if it is enabled.
	 *
	 * @param dictGroups the dict groups
	 */
	@Override
	public void cacheResult(List<DictGroup> dictGroups) {
		for (DictGroup dictGroup : dictGroups) {
			if (entityCache.getResult(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
						DictGroupImpl.class, dictGroup.getPrimaryKey()) == null) {
				cacheResult(dictGroup);
			}
			else {
				dictGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dict groups.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DictGroupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dict group.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DictGroup dictGroup) {
		entityCache.removeResult(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupImpl.class, dictGroup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DictGroupModelImpl)dictGroup, true);
	}

	@Override
	public void clearCache(List<DictGroup> dictGroups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DictGroup dictGroup : dictGroups) {
			entityCache.removeResult(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
				DictGroupImpl.class, dictGroup.getPrimaryKey());

			clearUniqueFindersCache((DictGroupModelImpl)dictGroup, true);
		}
	}

	protected void cacheUniqueFindersCache(
		DictGroupModelImpl dictGroupModelImpl) {
		Object[] args = new Object[] {
				dictGroupModelImpl.getUuid(), dictGroupModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dictGroupModelImpl, false);

		args = new Object[] {
				dictGroupModelImpl.getGroupCode(),
				dictGroupModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GROUPCODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GROUPCODE, args,
			dictGroupModelImpl, false);

		args = new Object[] {
				dictGroupModelImpl.getGroupCode(),
				dictGroupModelImpl.getGroupId(),
				dictGroupModelImpl.getDictCollectionId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_GC_GI_DCI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_GC_GI_DCI, args,
			dictGroupModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DictGroupModelImpl dictGroupModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dictGroupModelImpl.getUuid(),
					dictGroupModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dictGroupModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictGroupModelImpl.getOriginalUuid(),
					dictGroupModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dictGroupModelImpl.getGroupCode(),
					dictGroupModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GROUPCODE, args);
		}

		if ((dictGroupModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GROUPCODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictGroupModelImpl.getOriginalGroupCode(),
					dictGroupModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GROUPCODE, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dictGroupModelImpl.getGroupCode(),
					dictGroupModelImpl.getGroupId(),
					dictGroupModelImpl.getDictCollectionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GC_GI_DCI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GC_GI_DCI, args);
		}

		if ((dictGroupModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GC_GI_DCI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictGroupModelImpl.getOriginalGroupCode(),
					dictGroupModelImpl.getOriginalGroupId(),
					dictGroupModelImpl.getOriginalDictCollectionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GC_GI_DCI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GC_GI_DCI, args);
		}
	}

	/**
	 * Creates a new dict group with the primary key. Does not add the dict group to the database.
	 *
	 * @param dictGroupId the primary key for the new dict group
	 * @return the new dict group
	 */
	@Override
	public DictGroup create(long dictGroupId) {
		DictGroup dictGroup = new DictGroupImpl();

		dictGroup.setNew(true);
		dictGroup.setPrimaryKey(dictGroupId);

		String uuid = PortalUUIDUtil.generate();

		dictGroup.setUuid(uuid);

		dictGroup.setCompanyId(companyProvider.getCompanyId());

		return dictGroup;
	}

	/**
	 * Removes the dict group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dictGroupId the primary key of the dict group
	 * @return the dict group that was removed
	 * @throws NoSuchDictGroupException if a dict group with the primary key could not be found
	 */
	@Override
	public DictGroup remove(long dictGroupId) throws NoSuchDictGroupException {
		return remove((Serializable)dictGroupId);
	}

	/**
	 * Removes the dict group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dict group
	 * @return the dict group that was removed
	 * @throws NoSuchDictGroupException if a dict group with the primary key could not be found
	 */
	@Override
	public DictGroup remove(Serializable primaryKey)
		throws NoSuchDictGroupException {
		Session session = null;

		try {
			session = openSession();

			DictGroup dictGroup = (DictGroup)session.get(DictGroupImpl.class,
					primaryKey);

			if (dictGroup == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDictGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dictGroup);
		}
		catch (NoSuchDictGroupException nsee) {
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
	protected DictGroup removeImpl(DictGroup dictGroup) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dictGroup)) {
				dictGroup = (DictGroup)session.get(DictGroupImpl.class,
						dictGroup.getPrimaryKeyObj());
			}

			if (dictGroup != null) {
				session.delete(dictGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dictGroup != null) {
			clearCache(dictGroup);
		}

		return dictGroup;
	}

	@Override
	public DictGroup updateImpl(DictGroup dictGroup) {
		boolean isNew = dictGroup.isNew();

		if (!(dictGroup instanceof DictGroupModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dictGroup.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dictGroup);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dictGroup proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DictGroup implementation " +
				dictGroup.getClass());
		}

		DictGroupModelImpl dictGroupModelImpl = (DictGroupModelImpl)dictGroup;

		if (Validator.isNull(dictGroup.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dictGroup.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dictGroup.getCreateDate() == null)) {
			if (serviceContext == null) {
				dictGroup.setCreateDate(now);
			}
			else {
				dictGroup.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dictGroupModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dictGroup.setModifiedDate(now);
			}
			else {
				dictGroup.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dictGroup.isNew()) {
				session.save(dictGroup);

				dictGroup.setNew(false);
			}
			else {
				dictGroup = (DictGroup)session.merge(dictGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DictGroupModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dictGroupModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					dictGroupModelImpl.getUuid(),
					dictGroupModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					dictGroupModelImpl.getDictCollectionId(),
					dictGroupModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_DC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_DC,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dictGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictGroupModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dictGroupModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dictGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictGroupModelImpl.getOriginalUuid(),
						dictGroupModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dictGroupModelImpl.getUuid(),
						dictGroupModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((dictGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_DC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictGroupModelImpl.getOriginalDictCollectionId(),
						dictGroupModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_DC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_DC,
					args);

				args = new Object[] {
						dictGroupModelImpl.getDictCollectionId(),
						dictGroupModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_DC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_DC,
					args);
			}
		}

		entityCache.putResult(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupImpl.class, dictGroup.getPrimaryKey(), dictGroup, false);

		clearUniqueFindersCache(dictGroupModelImpl, false);
		cacheUniqueFindersCache(dictGroupModelImpl);

		dictGroup.resetOriginalValues();

		return dictGroup;
	}

	/**
	 * Returns the dict group with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict group
	 * @return the dict group
	 * @throws NoSuchDictGroupException if a dict group with the primary key could not be found
	 */
	@Override
	public DictGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDictGroupException {
		DictGroup dictGroup = fetchByPrimaryKey(primaryKey);

		if (dictGroup == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDictGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dictGroup;
	}

	/**
	 * Returns the dict group with the primary key or throws a {@link NoSuchDictGroupException} if it could not be found.
	 *
	 * @param dictGroupId the primary key of the dict group
	 * @return the dict group
	 * @throws NoSuchDictGroupException if a dict group with the primary key could not be found
	 */
	@Override
	public DictGroup findByPrimaryKey(long dictGroupId)
		throws NoSuchDictGroupException {
		return findByPrimaryKey((Serializable)dictGroupId);
	}

	/**
	 * Returns the dict group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict group
	 * @return the dict group, or <code>null</code> if a dict group with the primary key could not be found
	 */
	@Override
	public DictGroup fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
				DictGroupImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DictGroup dictGroup = (DictGroup)serializable;

		if (dictGroup == null) {
			Session session = null;

			try {
				session = openSession();

				dictGroup = (DictGroup)session.get(DictGroupImpl.class,
						primaryKey);

				if (dictGroup != null) {
					cacheResult(dictGroup);
				}
				else {
					entityCache.putResult(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
						DictGroupImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
					DictGroupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dictGroup;
	}

	/**
	 * Returns the dict group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dictGroupId the primary key of the dict group
	 * @return the dict group, or <code>null</code> if a dict group with the primary key could not be found
	 */
	@Override
	public DictGroup fetchByPrimaryKey(long dictGroupId) {
		return fetchByPrimaryKey((Serializable)dictGroupId);
	}

	@Override
	public Map<Serializable, DictGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DictGroup> map = new HashMap<Serializable, DictGroup>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DictGroup dictGroup = fetchByPrimaryKey(primaryKey);

			if (dictGroup != null) {
				map.put(primaryKey, dictGroup);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
					DictGroupImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DictGroup)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DICTGROUP_WHERE_PKS_IN);

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

			for (DictGroup dictGroup : (List<DictGroup>)q.list()) {
				map.put(dictGroup.getPrimaryKeyObj(), dictGroup);

				cacheResult(dictGroup);

				uncachedPrimaryKeys.remove(dictGroup.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DictGroupModelImpl.ENTITY_CACHE_ENABLED,
					DictGroupImpl.class, primaryKey, nullModel);
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
	 * Returns all the dict groups.
	 *
	 * @return the dict groups
	 */
	@Override
	public List<DictGroup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @return the range of dict groups
	 */
	@Override
	public List<DictGroup> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dict groups
	 */
	@Override
	public List<DictGroup> findAll(int start, int end,
		OrderByComparator<DictGroup> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict groups
	 * @param end the upper bound of the range of dict groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dict groups
	 */
	@Override
	public List<DictGroup> findAll(int start, int end,
		OrderByComparator<DictGroup> orderByComparator,
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

		List<DictGroup> list = null;

		if (retrieveFromCache) {
			list = (List<DictGroup>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DICTGROUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DICTGROUP;

				if (pagination) {
					sql = sql.concat(DictGroupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DictGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictGroup>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the dict groups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DictGroup dictGroup : findAll()) {
			remove(dictGroup);
		}
	}

	/**
	 * Returns the number of dict groups.
	 *
	 * @return the number of dict groups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DICTGROUP);

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
		return DictGroupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dict group persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DictGroupImpl.class.getName());
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

	private static final String _SQL_SELECT_DICTGROUP = "SELECT dictGroup FROM DictGroup dictGroup";
	private static final String _SQL_SELECT_DICTGROUP_WHERE_PKS_IN = "SELECT dictGroup FROM DictGroup dictGroup WHERE dictGroupId IN (";
	private static final String _SQL_SELECT_DICTGROUP_WHERE = "SELECT dictGroup FROM DictGroup dictGroup WHERE ";
	private static final String _SQL_COUNT_DICTGROUP = "SELECT COUNT(dictGroup) FROM DictGroup dictGroup";
	private static final String _SQL_COUNT_DICTGROUP_WHERE = "SELECT COUNT(dictGroup) FROM DictGroup dictGroup WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dictGroup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DictGroup exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DictGroup exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DictGroupPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}