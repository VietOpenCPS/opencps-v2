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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.synchronization.exception.NoSuchDictGroupTempException;
import org.opencps.synchronization.model.DictGroupTemp;
import org.opencps.synchronization.model.impl.DictGroupTempImpl;
import org.opencps.synchronization.model.impl.DictGroupTempModelImpl;
import org.opencps.synchronization.service.persistence.DictGroupTempPersistence;

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
 * The persistence implementation for the dict group temp service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see DictGroupTempPersistence
 * @see org.opencps.synchronization.service.persistence.DictGroupTempUtil
 * @generated
 */
@ProviderType
public class DictGroupTempPersistenceImpl extends BasePersistenceImpl<DictGroupTemp>
	implements DictGroupTempPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DictGroupTempUtil} to access the dict group temp persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DictGroupTempImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictGroupTempImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictGroupTempImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictGroupTempImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictGroupTempImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			DictGroupTempModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dict group temps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict group temps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @return the range of matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict group temps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByUuid(String uuid, int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict group temps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByUuid(String uuid, int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator,
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

		List<DictGroupTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictGroupTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictGroupTemp dictGroupTemp : list) {
					if (!Objects.equals(uuid, dictGroupTemp.getUuid())) {
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

			query.append(_SQL_SELECT_DICTGROUPTEMP_WHERE);

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
				query.append(DictGroupTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictGroupTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictGroupTemp>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict group temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group temp
	 * @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp findByUuid_First(String uuid,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = fetchByUuid_First(uuid, orderByComparator);

		if (dictGroupTemp != null) {
			return dictGroupTemp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDictGroupTempException(msg.toString());
	}

	/**
	 * Returns the first dict group temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp fetchByUuid_First(String uuid,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		List<DictGroupTemp> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict group temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group temp
	 * @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp findByUuid_Last(String uuid,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = fetchByUuid_Last(uuid, orderByComparator);

		if (dictGroupTemp != null) {
			return dictGroupTemp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDictGroupTempException(msg.toString());
	}

	/**
	 * Returns the last dict group temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp fetchByUuid_Last(String uuid,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DictGroupTemp> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict group temps before and after the current dict group temp in the ordered set where uuid = &#63;.
	 *
	 * @param dictGroupId the primary key of the current dict group temp
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict group temp
	 * @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	 */
	@Override
	public DictGroupTemp[] findByUuid_PrevAndNext(long dictGroupId,
		String uuid, OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = findByPrimaryKey(dictGroupId);

		Session session = null;

		try {
			session = openSession();

			DictGroupTemp[] array = new DictGroupTempImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dictGroupTemp, uuid,
					orderByComparator, true);

			array[1] = dictGroupTemp;

			array[2] = getByUuid_PrevAndNext(session, dictGroupTemp, uuid,
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

	protected DictGroupTemp getByUuid_PrevAndNext(Session session,
		DictGroupTemp dictGroupTemp, String uuid,
		OrderByComparator<DictGroupTemp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DICTGROUPTEMP_WHERE);

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
			query.append(DictGroupTempModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictGroupTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictGroupTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict group temps where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DictGroupTemp dictGroupTemp : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dictGroupTemp);
		}
	}

	/**
	 * Returns the number of dict group temps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dict group temps
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DICTGROUPTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dictGroupTemp.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dictGroupTemp.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dictGroupTemp.uuid IS NULL OR dictGroupTemp.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictGroupTempImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DictGroupTempModelImpl.UUID_COLUMN_BITMASK |
			DictGroupTempModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict group temp where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictGroupTempException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dict group temp
	 * @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp findByUUID_G(String uuid, long groupId)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = fetchByUUID_G(uuid, groupId);

		if (dictGroupTemp == null) {
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

			throw new NoSuchDictGroupTempException(msg.toString());
		}

		return dictGroupTemp;
	}

	/**
	 * Returns the dict group temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dict group temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DictGroupTemp) {
			DictGroupTemp dictGroupTemp = (DictGroupTemp)result;

			if (!Objects.equals(uuid, dictGroupTemp.getUuid()) ||
					(groupId != dictGroupTemp.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTGROUPTEMP_WHERE);

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

				List<DictGroupTemp> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DictGroupTemp dictGroupTemp = list.get(0);

					result = dictGroupTemp;

					cacheResult(dictGroupTemp);
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
			return (DictGroupTemp)result;
		}
	}

	/**
	 * Removes the dict group temp where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dict group temp that was removed
	 */
	@Override
	public DictGroupTemp removeByUUID_G(String uuid, long groupId)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = findByUUID_G(uuid, groupId);

		return remove(dictGroupTemp);
	}

	/**
	 * Returns the number of dict group temps where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dict group temps
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTGROUPTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dictGroupTemp.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dictGroupTemp.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dictGroupTemp.uuid IS NULL OR dictGroupTemp.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dictGroupTemp.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictGroupTempImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictGroupTempImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DictGroupTempModelImpl.UUID_COLUMN_BITMASK |
			DictGroupTempModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict group temps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict group temps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @return the range of matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict group temps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DictGroupTemp> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict group temps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DictGroupTemp> orderByComparator,
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

		List<DictGroupTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictGroupTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictGroupTemp dictGroupTemp : list) {
					if (!Objects.equals(uuid, dictGroupTemp.getUuid()) ||
							(companyId != dictGroupTemp.getCompanyId())) {
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

			query.append(_SQL_SELECT_DICTGROUPTEMP_WHERE);

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
				query.append(DictGroupTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictGroupTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictGroupTemp>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group temp
	 * @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (dictGroupTemp != null) {
			return dictGroupTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDictGroupTempException(msg.toString());
	}

	/**
	 * Returns the first dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		List<DictGroupTemp> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group temp
	 * @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (dictGroupTemp != null) {
			return dictGroupTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDictGroupTempException(msg.toString());
	}

	/**
	 * Returns the last dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DictGroupTemp> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict group temps before and after the current dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dictGroupId the primary key of the current dict group temp
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict group temp
	 * @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	 */
	@Override
	public DictGroupTemp[] findByUuid_C_PrevAndNext(long dictGroupId,
		String uuid, long companyId,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = findByPrimaryKey(dictGroupId);

		Session session = null;

		try {
			session = openSession();

			DictGroupTemp[] array = new DictGroupTempImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dictGroupTemp, uuid,
					companyId, orderByComparator, true);

			array[1] = dictGroupTemp;

			array[2] = getByUuid_C_PrevAndNext(session, dictGroupTemp, uuid,
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

	protected DictGroupTemp getByUuid_C_PrevAndNext(Session session,
		DictGroupTemp dictGroupTemp, String uuid, long companyId,
		OrderByComparator<DictGroupTemp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTGROUPTEMP_WHERE);

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
			query.append(DictGroupTempModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictGroupTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictGroupTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict group temps where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DictGroupTemp dictGroupTemp : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictGroupTemp);
		}
	}

	/**
	 * Returns the number of dict group temps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dict group temps
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTGROUPTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dictGroupTemp.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dictGroupTemp.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dictGroupTemp.uuid IS NULL OR dictGroupTemp.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dictGroupTemp.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GROUPCODE = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictGroupTempImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_groupCode",
			new String[] { String.class.getName(), Long.class.getName() },
			DictGroupTempModelImpl.GROUPCODE_COLUMN_BITMASK |
			DictGroupTempModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GROUPCODE = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_groupCode",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict group temp where groupCode = &#63; and groupId = &#63; or throws a {@link NoSuchDictGroupTempException} if it could not be found.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @return the matching dict group temp
	 * @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp findByF_groupCode(String groupCode, long groupId)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = fetchByF_groupCode(groupCode, groupId);

		if (dictGroupTemp == null) {
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

			throw new NoSuchDictGroupTempException(msg.toString());
		}

		return dictGroupTemp;
	}

	/**
	 * Returns the dict group temp where groupCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp fetchByF_groupCode(String groupCode, long groupId) {
		return fetchByF_groupCode(groupCode, groupId, true);
	}

	/**
	 * Returns the dict group temp where groupCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp fetchByF_groupCode(String groupCode, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupCode, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GROUPCODE,
					finderArgs, this);
		}

		if (result instanceof DictGroupTemp) {
			DictGroupTemp dictGroupTemp = (DictGroupTemp)result;

			if (!Objects.equals(groupCode, dictGroupTemp.getGroupCode()) ||
					(groupId != dictGroupTemp.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTGROUPTEMP_WHERE);

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

				List<DictGroupTemp> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GROUPCODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictGroupTempPersistenceImpl.fetchByF_groupCode(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictGroupTemp dictGroupTemp = list.get(0);

					result = dictGroupTemp;

					cacheResult(dictGroupTemp);
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
			return (DictGroupTemp)result;
		}
	}

	/**
	 * Removes the dict group temp where groupCode = &#63; and groupId = &#63; from the database.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @return the dict group temp that was removed
	 */
	@Override
	public DictGroupTemp removeByF_groupCode(String groupCode, long groupId)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = findByF_groupCode(groupCode, groupId);

		return remove(dictGroupTemp);
	}

	/**
	 * Returns the number of dict group temps where groupCode = &#63; and groupId = &#63;.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @return the number of matching dict group temps
	 */
	@Override
	public int countByF_groupCode(String groupCode, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GROUPCODE;

		Object[] finderArgs = new Object[] { groupCode, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTGROUPTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_F_GROUPCODE_GROUPCODE_1 = "dictGroupTemp.groupCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GROUPCODE_GROUPCODE_2 = "dictGroupTemp.groupCode = ? AND ";
	private static final String _FINDER_COLUMN_F_GROUPCODE_GROUPCODE_3 = "(dictGroupTemp.groupCode IS NULL OR dictGroupTemp.groupCode = '') AND ";
	private static final String _FINDER_COLUMN_F_GROUPCODE_GROUPID_2 = "dictGroupTemp.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_GC_GI_DCI = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictGroupTempImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByGC_GI_DCI",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			DictGroupTempModelImpl.GROUPCODE_COLUMN_BITMASK |
			DictGroupTempModelImpl.GROUPID_COLUMN_BITMASK |
			DictGroupTempModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GC_GI_DCI = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGC_GI_DCI",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns the dict group temp where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; or throws a {@link NoSuchDictGroupTempException} if it could not be found.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @return the matching dict group temp
	 * @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp findByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId) throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = fetchByGC_GI_DCI(groupCode, groupId,
				dictCollectionId);

		if (dictGroupTemp == null) {
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

			throw new NoSuchDictGroupTempException(msg.toString());
		}

		return dictGroupTemp;
	}

	/**
	 * Returns the dict group temp where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp fetchByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId) {
		return fetchByGC_GI_DCI(groupCode, groupId, dictCollectionId, true);
	}

	/**
	 * Returns the dict group temp where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp fetchByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupCode, groupId, dictCollectionId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_GC_GI_DCI,
					finderArgs, this);
		}

		if (result instanceof DictGroupTemp) {
			DictGroupTemp dictGroupTemp = (DictGroupTemp)result;

			if (!Objects.equals(groupCode, dictGroupTemp.getGroupCode()) ||
					(groupId != dictGroupTemp.getGroupId()) ||
					(dictCollectionId != dictGroupTemp.getDictCollectionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DICTGROUPTEMP_WHERE);

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

				List<DictGroupTemp> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_GC_GI_DCI,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictGroupTempPersistenceImpl.fetchByGC_GI_DCI(String, long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictGroupTemp dictGroupTemp = list.get(0);

					result = dictGroupTemp;

					cacheResult(dictGroupTemp);
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
			return (DictGroupTemp)result;
		}
	}

	/**
	 * Removes the dict group temp where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; from the database.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @return the dict group temp that was removed
	 */
	@Override
	public DictGroupTemp removeByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId) throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = findByGC_GI_DCI(groupCode, groupId,
				dictCollectionId);

		return remove(dictGroupTemp);
	}

	/**
	 * Returns the number of dict group temps where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63;.
	 *
	 * @param groupCode the group code
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @return the number of matching dict group temps
	 */
	@Override
	public int countByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GC_GI_DCI;

		Object[] finderArgs = new Object[] { groupCode, groupId, dictCollectionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DICTGROUPTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_GC_GI_DCI_GROUPCODE_1 = "dictGroupTemp.groupCode IS NULL AND ";
	private static final String _FINDER_COLUMN_GC_GI_DCI_GROUPCODE_2 = "dictGroupTemp.groupCode = ? AND ";
	private static final String _FINDER_COLUMN_GC_GI_DCI_GROUPCODE_3 = "(dictGroupTemp.groupCode IS NULL OR dictGroupTemp.groupCode = '') AND ";
	private static final String _FINDER_COLUMN_GC_GI_DCI_GROUPID_2 = "dictGroupTemp.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GC_GI_DCI_DICTCOLLECTIONID_2 = "dictGroupTemp.dictCollectionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_DC = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictGroupTempImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGID_DC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_DC =
		new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictGroupTempImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGID_DC",
			new String[] { Long.class.getName(), Long.class.getName() },
			DictGroupTempModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK |
			DictGroupTempModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_DC = new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGID_DC",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByGID_DC(long dictCollectionId, long groupId) {
		return findByGID_DC(dictCollectionId, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @return the range of matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByGID_DC(long dictCollectionId,
		long groupId, int start, int end) {
		return findByGID_DC(dictCollectionId, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByGID_DC(long dictCollectionId,
		long groupId, int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		return findByGID_DC(dictCollectionId, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByGID_DC(long dictCollectionId,
		long groupId, int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator,
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

		List<DictGroupTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictGroupTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictGroupTemp dictGroupTemp : list) {
					if ((dictCollectionId != dictGroupTemp.getDictCollectionId()) ||
							(groupId != dictGroupTemp.getGroupId())) {
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

			query.append(_SQL_SELECT_DICTGROUPTEMP_WHERE);

			query.append(_FINDER_COLUMN_GID_DC_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_GID_DC_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictGroupTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictGroupTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictGroupTemp>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group temp
	 * @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp findByGID_DC_First(long dictCollectionId,
		long groupId, OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = fetchByGID_DC_First(dictCollectionId,
				groupId, orderByComparator);

		if (dictGroupTemp != null) {
			return dictGroupTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictGroupTempException(msg.toString());
	}

	/**
	 * Returns the first dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp fetchByGID_DC_First(long dictCollectionId,
		long groupId, OrderByComparator<DictGroupTemp> orderByComparator) {
		List<DictGroupTemp> list = findByGID_DC(dictCollectionId, groupId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group temp
	 * @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp findByGID_DC_Last(long dictCollectionId, long groupId,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = fetchByGID_DC_Last(dictCollectionId,
				groupId, orderByComparator);

		if (dictGroupTemp != null) {
			return dictGroupTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictGroupTempException(msg.toString());
	}

	/**
	 * Returns the last dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp fetchByGID_DC_Last(long dictCollectionId,
		long groupId, OrderByComparator<DictGroupTemp> orderByComparator) {
		int count = countByGID_DC(dictCollectionId, groupId);

		if (count == 0) {
			return null;
		}

		List<DictGroupTemp> list = findByGID_DC(dictCollectionId, groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict group temps before and after the current dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictGroupId the primary key of the current dict group temp
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict group temp
	 * @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	 */
	@Override
	public DictGroupTemp[] findByGID_DC_PrevAndNext(long dictGroupId,
		long dictCollectionId, long groupId,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = findByPrimaryKey(dictGroupId);

		Session session = null;

		try {
			session = openSession();

			DictGroupTemp[] array = new DictGroupTempImpl[3];

			array[0] = getByGID_DC_PrevAndNext(session, dictGroupTemp,
					dictCollectionId, groupId, orderByComparator, true);

			array[1] = dictGroupTemp;

			array[2] = getByGID_DC_PrevAndNext(session, dictGroupTemp,
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

	protected DictGroupTemp getByGID_DC_PrevAndNext(Session session,
		DictGroupTemp dictGroupTemp, long dictCollectionId, long groupId,
		OrderByComparator<DictGroupTemp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTGROUPTEMP_WHERE);

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
			query.append(DictGroupTempModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dictCollectionId);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictGroupTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictGroupTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict group temps where dictCollectionId = &#63; and groupId = &#63; from the database.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGID_DC(long dictCollectionId, long groupId) {
		for (DictGroupTemp dictGroupTemp : findByGID_DC(dictCollectionId,
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictGroupTemp);
		}
	}

	/**
	 * Returns the number of dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the number of matching dict group temps
	 */
	@Override
	public int countByGID_DC(long dictCollectionId, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_DC;

		Object[] finderArgs = new Object[] { dictCollectionId, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTGROUPTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_GID_DC_DICTCOLLECTIONID_2 = "dictGroupTemp.dictCollectionId = ? AND ";
	private static final String _FINDER_COLUMN_GID_DC_GROUPID_2 = "dictGroupTemp.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTGROUPNEWERTHAN =
		new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictGroupTempImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_dictGroupNewerThan",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_DICTGROUPNEWERTHAN =
		new FinderPath(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_dictGroupNewerThan",
			new String[] { Date.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @return the matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByF_dictGroupNewerThan(Date modifiedDate,
		long groupId) {
		return findByF_dictGroupNewerThan(modifiedDate, groupId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @return the range of matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByF_dictGroupNewerThan(Date modifiedDate,
		long groupId, int start, int end) {
		return findByF_dictGroupNewerThan(modifiedDate, groupId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByF_dictGroupNewerThan(Date modifiedDate,
		long groupId, int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		return findByF_dictGroupNewerThan(modifiedDate, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict group temps
	 */
	@Override
	public List<DictGroupTemp> findByF_dictGroupNewerThan(Date modifiedDate,
		long groupId, int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTGROUPNEWERTHAN;
		finderArgs = new Object[] {
				_getTime(modifiedDate), groupId,
				
				start, end, orderByComparator
			};

		List<DictGroupTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictGroupTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictGroupTemp dictGroupTemp : list) {
					if ((modifiedDate.getTime() > dictGroupTemp.getModifiedDate()
																   .getTime()) ||
							(groupId != dictGroupTemp.getGroupId())) {
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

			query.append(_SQL_SELECT_DICTGROUPTEMP_WHERE);

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
				query.append(DictGroupTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictGroupTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictGroupTemp>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group temp
	 * @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp findByF_dictGroupNewerThan_First(Date modifiedDate,
		long groupId, OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = fetchByF_dictGroupNewerThan_First(modifiedDate,
				groupId, orderByComparator);

		if (dictGroupTemp != null) {
			return dictGroupTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictGroupTempException(msg.toString());
	}

	/**
	 * Returns the first dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp fetchByF_dictGroupNewerThan_First(Date modifiedDate,
		long groupId, OrderByComparator<DictGroupTemp> orderByComparator) {
		List<DictGroupTemp> list = findByF_dictGroupNewerThan(modifiedDate,
				groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group temp
	 * @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp findByF_dictGroupNewerThan_Last(Date modifiedDate,
		long groupId, OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = fetchByF_dictGroupNewerThan_Last(modifiedDate,
				groupId, orderByComparator);

		if (dictGroupTemp != null) {
			return dictGroupTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictGroupTempException(msg.toString());
	}

	/**
	 * Returns the last dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	 */
	@Override
	public DictGroupTemp fetchByF_dictGroupNewerThan_Last(Date modifiedDate,
		long groupId, OrderByComparator<DictGroupTemp> orderByComparator) {
		int count = countByF_dictGroupNewerThan(modifiedDate, groupId);

		if (count == 0) {
			return null;
		}

		List<DictGroupTemp> list = findByF_dictGroupNewerThan(modifiedDate,
				groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict group temps before and after the current dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param dictGroupId the primary key of the current dict group temp
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict group temp
	 * @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	 */
	@Override
	public DictGroupTemp[] findByF_dictGroupNewerThan_PrevAndNext(
		long dictGroupId, Date modifiedDate, long groupId,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = findByPrimaryKey(dictGroupId);

		Session session = null;

		try {
			session = openSession();

			DictGroupTemp[] array = new DictGroupTempImpl[3];

			array[0] = getByF_dictGroupNewerThan_PrevAndNext(session,
					dictGroupTemp, modifiedDate, groupId, orderByComparator,
					true);

			array[1] = dictGroupTemp;

			array[2] = getByF_dictGroupNewerThan_PrevAndNext(session,
					dictGroupTemp, modifiedDate, groupId, orderByComparator,
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

	protected DictGroupTemp getByF_dictGroupNewerThan_PrevAndNext(
		Session session, DictGroupTemp dictGroupTemp, Date modifiedDate,
		long groupId, OrderByComparator<DictGroupTemp> orderByComparator,
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

		query.append(_SQL_SELECT_DICTGROUPTEMP_WHERE);

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
			query.append(DictGroupTempModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictGroupTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictGroupTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_dictGroupNewerThan(Date modifiedDate, long groupId) {
		for (DictGroupTemp dictGroupTemp : findByF_dictGroupNewerThan(
				modifiedDate, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(dictGroupTemp);
		}
	}

	/**
	 * Returns the number of dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @return the number of matching dict group temps
	 */
	@Override
	public int countByF_dictGroupNewerThan(Date modifiedDate, long groupId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_DICTGROUPNEWERTHAN;

		Object[] finderArgs = new Object[] { _getTime(modifiedDate), groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTGROUPTEMP_WHERE);

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
		"dictGroupTemp.modifiedDate IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DICTGROUPNEWERTHAN_MODIFIEDDATE_2 =
		"dictGroupTemp.modifiedDate >= ? AND ";
	private static final String _FINDER_COLUMN_F_DICTGROUPNEWERTHAN_GROUPID_2 = "dictGroupTemp.groupId = ?";

	public DictGroupTempPersistenceImpl() {
		setModelClass(DictGroupTemp.class);

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
	 * Caches the dict group temp in the entity cache if it is enabled.
	 *
	 * @param dictGroupTemp the dict group temp
	 */
	@Override
	public void cacheResult(DictGroupTemp dictGroupTemp) {
		entityCache.putResult(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempImpl.class, dictGroupTemp.getPrimaryKey(),
			dictGroupTemp);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dictGroupTemp.getUuid(), dictGroupTemp.getGroupId() },
			dictGroupTemp);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GROUPCODE,
			new Object[] {
				dictGroupTemp.getGroupCode(), dictGroupTemp.getGroupId()
			}, dictGroupTemp);

		finderCache.putResult(FINDER_PATH_FETCH_BY_GC_GI_DCI,
			new Object[] {
				dictGroupTemp.getGroupCode(), dictGroupTemp.getGroupId(),
				dictGroupTemp.getDictCollectionId()
			}, dictGroupTemp);

		dictGroupTemp.resetOriginalValues();
	}

	/**
	 * Caches the dict group temps in the entity cache if it is enabled.
	 *
	 * @param dictGroupTemps the dict group temps
	 */
	@Override
	public void cacheResult(List<DictGroupTemp> dictGroupTemps) {
		for (DictGroupTemp dictGroupTemp : dictGroupTemps) {
			if (entityCache.getResult(
						DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
						DictGroupTempImpl.class, dictGroupTemp.getPrimaryKey()) == null) {
				cacheResult(dictGroupTemp);
			}
			else {
				dictGroupTemp.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dict group temps.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DictGroupTempImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dict group temp.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DictGroupTemp dictGroupTemp) {
		entityCache.removeResult(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempImpl.class, dictGroupTemp.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DictGroupTempModelImpl)dictGroupTemp, true);
	}

	@Override
	public void clearCache(List<DictGroupTemp> dictGroupTemps) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DictGroupTemp dictGroupTemp : dictGroupTemps) {
			entityCache.removeResult(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
				DictGroupTempImpl.class, dictGroupTemp.getPrimaryKey());

			clearUniqueFindersCache((DictGroupTempModelImpl)dictGroupTemp, true);
		}
	}

	protected void cacheUniqueFindersCache(
		DictGroupTempModelImpl dictGroupTempModelImpl) {
		Object[] args = new Object[] {
				dictGroupTempModelImpl.getUuid(),
				dictGroupTempModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dictGroupTempModelImpl, false);

		args = new Object[] {
				dictGroupTempModelImpl.getGroupCode(),
				dictGroupTempModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GROUPCODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GROUPCODE, args,
			dictGroupTempModelImpl, false);

		args = new Object[] {
				dictGroupTempModelImpl.getGroupCode(),
				dictGroupTempModelImpl.getGroupId(),
				dictGroupTempModelImpl.getDictCollectionId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_GC_GI_DCI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_GC_GI_DCI, args,
			dictGroupTempModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DictGroupTempModelImpl dictGroupTempModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dictGroupTempModelImpl.getUuid(),
					dictGroupTempModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dictGroupTempModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictGroupTempModelImpl.getOriginalUuid(),
					dictGroupTempModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dictGroupTempModelImpl.getGroupCode(),
					dictGroupTempModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GROUPCODE, args);
		}

		if ((dictGroupTempModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GROUPCODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictGroupTempModelImpl.getOriginalGroupCode(),
					dictGroupTempModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GROUPCODE, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dictGroupTempModelImpl.getGroupCode(),
					dictGroupTempModelImpl.getGroupId(),
					dictGroupTempModelImpl.getDictCollectionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GC_GI_DCI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GC_GI_DCI, args);
		}

		if ((dictGroupTempModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GC_GI_DCI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictGroupTempModelImpl.getOriginalGroupCode(),
					dictGroupTempModelImpl.getOriginalGroupId(),
					dictGroupTempModelImpl.getOriginalDictCollectionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GC_GI_DCI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GC_GI_DCI, args);
		}
	}

	/**
	 * Creates a new dict group temp with the primary key. Does not add the dict group temp to the database.
	 *
	 * @param dictGroupId the primary key for the new dict group temp
	 * @return the new dict group temp
	 */
	@Override
	public DictGroupTemp create(long dictGroupId) {
		DictGroupTemp dictGroupTemp = new DictGroupTempImpl();

		dictGroupTemp.setNew(true);
		dictGroupTemp.setPrimaryKey(dictGroupId);

		String uuid = PortalUUIDUtil.generate();

		dictGroupTemp.setUuid(uuid);

		dictGroupTemp.setCompanyId(companyProvider.getCompanyId());

		return dictGroupTemp;
	}

	/**
	 * Removes the dict group temp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dictGroupId the primary key of the dict group temp
	 * @return the dict group temp that was removed
	 * @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	 */
	@Override
	public DictGroupTemp remove(long dictGroupId)
		throws NoSuchDictGroupTempException {
		return remove((Serializable)dictGroupId);
	}

	/**
	 * Removes the dict group temp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dict group temp
	 * @return the dict group temp that was removed
	 * @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	 */
	@Override
	public DictGroupTemp remove(Serializable primaryKey)
		throws NoSuchDictGroupTempException {
		Session session = null;

		try {
			session = openSession();

			DictGroupTemp dictGroupTemp = (DictGroupTemp)session.get(DictGroupTempImpl.class,
					primaryKey);

			if (dictGroupTemp == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDictGroupTempException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dictGroupTemp);
		}
		catch (NoSuchDictGroupTempException nsee) {
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
	protected DictGroupTemp removeImpl(DictGroupTemp dictGroupTemp) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dictGroupTemp)) {
				dictGroupTemp = (DictGroupTemp)session.get(DictGroupTempImpl.class,
						dictGroupTemp.getPrimaryKeyObj());
			}

			if (dictGroupTemp != null) {
				session.delete(dictGroupTemp);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dictGroupTemp != null) {
			clearCache(dictGroupTemp);
		}

		return dictGroupTemp;
	}

	@Override
	public DictGroupTemp updateImpl(DictGroupTemp dictGroupTemp) {
		boolean isNew = dictGroupTemp.isNew();

		if (!(dictGroupTemp instanceof DictGroupTempModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dictGroupTemp.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dictGroupTemp);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dictGroupTemp proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DictGroupTemp implementation " +
				dictGroupTemp.getClass());
		}

		DictGroupTempModelImpl dictGroupTempModelImpl = (DictGroupTempModelImpl)dictGroupTemp;

		if (Validator.isNull(dictGroupTemp.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dictGroupTemp.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dictGroupTemp.getCreateDate() == null)) {
			if (serviceContext == null) {
				dictGroupTemp.setCreateDate(now);
			}
			else {
				dictGroupTemp.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dictGroupTempModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dictGroupTemp.setModifiedDate(now);
			}
			else {
				dictGroupTemp.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dictGroupTemp.isNew()) {
				session.save(dictGroupTemp);

				dictGroupTemp.setNew(false);
			}
			else {
				dictGroupTemp = (DictGroupTemp)session.merge(dictGroupTemp);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DictGroupTempModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dictGroupTempModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					dictGroupTempModelImpl.getUuid(),
					dictGroupTempModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					dictGroupTempModelImpl.getDictCollectionId(),
					dictGroupTempModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_DC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_DC,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dictGroupTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictGroupTempModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dictGroupTempModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dictGroupTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictGroupTempModelImpl.getOriginalUuid(),
						dictGroupTempModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dictGroupTempModelImpl.getUuid(),
						dictGroupTempModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((dictGroupTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_DC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictGroupTempModelImpl.getOriginalDictCollectionId(),
						dictGroupTempModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_DC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_DC,
					args);

				args = new Object[] {
						dictGroupTempModelImpl.getDictCollectionId(),
						dictGroupTempModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_DC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_DC,
					args);
			}
		}

		entityCache.putResult(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictGroupTempImpl.class, dictGroupTemp.getPrimaryKey(),
			dictGroupTemp, false);

		clearUniqueFindersCache(dictGroupTempModelImpl, false);
		cacheUniqueFindersCache(dictGroupTempModelImpl);

		dictGroupTemp.resetOriginalValues();

		return dictGroupTemp;
	}

	/**
	 * Returns the dict group temp with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict group temp
	 * @return the dict group temp
	 * @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	 */
	@Override
	public DictGroupTemp findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDictGroupTempException {
		DictGroupTemp dictGroupTemp = fetchByPrimaryKey(primaryKey);

		if (dictGroupTemp == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDictGroupTempException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dictGroupTemp;
	}

	/**
	 * Returns the dict group temp with the primary key or throws a {@link NoSuchDictGroupTempException} if it could not be found.
	 *
	 * @param dictGroupId the primary key of the dict group temp
	 * @return the dict group temp
	 * @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	 */
	@Override
	public DictGroupTemp findByPrimaryKey(long dictGroupId)
		throws NoSuchDictGroupTempException {
		return findByPrimaryKey((Serializable)dictGroupId);
	}

	/**
	 * Returns the dict group temp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict group temp
	 * @return the dict group temp, or <code>null</code> if a dict group temp with the primary key could not be found
	 */
	@Override
	public DictGroupTemp fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
				DictGroupTempImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DictGroupTemp dictGroupTemp = (DictGroupTemp)serializable;

		if (dictGroupTemp == null) {
			Session session = null;

			try {
				session = openSession();

				dictGroupTemp = (DictGroupTemp)session.get(DictGroupTempImpl.class,
						primaryKey);

				if (dictGroupTemp != null) {
					cacheResult(dictGroupTemp);
				}
				else {
					entityCache.putResult(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
						DictGroupTempImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
					DictGroupTempImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dictGroupTemp;
	}

	/**
	 * Returns the dict group temp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dictGroupId the primary key of the dict group temp
	 * @return the dict group temp, or <code>null</code> if a dict group temp with the primary key could not be found
	 */
	@Override
	public DictGroupTemp fetchByPrimaryKey(long dictGroupId) {
		return fetchByPrimaryKey((Serializable)dictGroupId);
	}

	@Override
	public Map<Serializable, DictGroupTemp> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DictGroupTemp> map = new HashMap<Serializable, DictGroupTemp>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DictGroupTemp dictGroupTemp = fetchByPrimaryKey(primaryKey);

			if (dictGroupTemp != null) {
				map.put(primaryKey, dictGroupTemp);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
					DictGroupTempImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DictGroupTemp)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DICTGROUPTEMP_WHERE_PKS_IN);

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

			for (DictGroupTemp dictGroupTemp : (List<DictGroupTemp>)q.list()) {
				map.put(dictGroupTemp.getPrimaryKeyObj(), dictGroupTemp);

				cacheResult(dictGroupTemp);

				uncachedPrimaryKeys.remove(dictGroupTemp.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DictGroupTempModelImpl.ENTITY_CACHE_ENABLED,
					DictGroupTempImpl.class, primaryKey, nullModel);
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
	 * Returns all the dict group temps.
	 *
	 * @return the dict group temps
	 */
	@Override
	public List<DictGroupTemp> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict group temps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @return the range of dict group temps
	 */
	@Override
	public List<DictGroupTemp> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict group temps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dict group temps
	 */
	@Override
	public List<DictGroupTemp> findAll(int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict group temps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict group temps
	 * @param end the upper bound of the range of dict group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dict group temps
	 */
	@Override
	public List<DictGroupTemp> findAll(int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator,
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

		List<DictGroupTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictGroupTemp>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DICTGROUPTEMP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DICTGROUPTEMP;

				if (pagination) {
					sql = sql.concat(DictGroupTempModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DictGroupTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictGroupTemp>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the dict group temps from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DictGroupTemp dictGroupTemp : findAll()) {
			remove(dictGroupTemp);
		}
	}

	/**
	 * Returns the number of dict group temps.
	 *
	 * @return the number of dict group temps
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DICTGROUPTEMP);

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
		return DictGroupTempModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dict group temp persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DictGroupTempImpl.class.getName());
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

	private static final String _SQL_SELECT_DICTGROUPTEMP = "SELECT dictGroupTemp FROM DictGroupTemp dictGroupTemp";
	private static final String _SQL_SELECT_DICTGROUPTEMP_WHERE_PKS_IN = "SELECT dictGroupTemp FROM DictGroupTemp dictGroupTemp WHERE dictGroupId IN (";
	private static final String _SQL_SELECT_DICTGROUPTEMP_WHERE = "SELECT dictGroupTemp FROM DictGroupTemp dictGroupTemp WHERE ";
	private static final String _SQL_COUNT_DICTGROUPTEMP = "SELECT COUNT(dictGroupTemp) FROM DictGroupTemp dictGroupTemp";
	private static final String _SQL_COUNT_DICTGROUPTEMP_WHERE = "SELECT COUNT(dictGroupTemp) FROM DictGroupTemp dictGroupTemp WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dictGroupTemp.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DictGroupTemp exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DictGroupTemp exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DictGroupTempPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}