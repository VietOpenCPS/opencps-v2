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

import org.opencps.synchronization.exception.NoSuchDictItemGroupTempException;
import org.opencps.synchronization.model.DictItemGroupTemp;
import org.opencps.synchronization.model.impl.DictItemGroupTempImpl;
import org.opencps.synchronization.model.impl.DictItemGroupTempModelImpl;
import org.opencps.synchronization.service.persistence.DictItemGroupTempPersistence;

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
 * The persistence implementation for the dict item group temp service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see DictItemGroupTempPersistence
 * @see org.opencps.synchronization.service.persistence.DictItemGroupTempUtil
 * @generated
 */
@ProviderType
public class DictItemGroupTempPersistenceImpl extends BasePersistenceImpl<DictItemGroupTemp>
	implements DictItemGroupTempPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DictItemGroupTempUtil} to access the dict item group temp persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DictItemGroupTempImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictItemGroupTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictItemGroupTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictItemGroupTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictItemGroupTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DictItemGroupTempModelImpl.UUID_COLUMN_BITMASK |
			DictItemGroupTempModelImpl.DICTGROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dict item group temps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item group temps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @return the range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item group temps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByUuid(String uuid, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item group temps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByUuid(String uuid, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator,
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

		List<DictItemGroupTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemGroupTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemGroupTemp dictItemGroupTemp : list) {
					if (!Objects.equals(uuid, dictItemGroupTemp.getUuid())) {
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

			query.append(_SQL_SELECT_DICTITEMGROUPTEMP_WHERE);

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
				query.append(DictItemGroupTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictItemGroupTemp>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemGroupTemp>)QueryUtil.list(q,
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
	 * Returns the first dict item group temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp findByUuid_First(String uuid,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = fetchByUuid_First(uuid,
				orderByComparator);

		if (dictItemGroupTemp != null) {
			return dictItemGroupTemp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDictItemGroupTempException(msg.toString());
	}

	/**
	 * Returns the first dict item group temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByUuid_First(String uuid,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		List<DictItemGroupTemp> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item group temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp findByUuid_Last(String uuid,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = fetchByUuid_Last(uuid,
				orderByComparator);

		if (dictItemGroupTemp != null) {
			return dictItemGroupTemp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDictItemGroupTempException(msg.toString());
	}

	/**
	 * Returns the last dict item group temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByUuid_Last(String uuid,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DictItemGroupTemp> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item group temps before and after the current dict item group temp in the ordered set where uuid = &#63;.
	 *
	 * @param dictItemGroupId the primary key of the current dict item group temp
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	 */
	@Override
	public DictItemGroupTemp[] findByUuid_PrevAndNext(long dictItemGroupId,
		String uuid, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = findByPrimaryKey(dictItemGroupId);

		Session session = null;

		try {
			session = openSession();

			DictItemGroupTemp[] array = new DictItemGroupTempImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dictItemGroupTemp, uuid,
					orderByComparator, true);

			array[1] = dictItemGroupTemp;

			array[2] = getByUuid_PrevAndNext(session, dictItemGroupTemp, uuid,
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

	protected DictItemGroupTemp getByUuid_PrevAndNext(Session session,
		DictItemGroupTemp dictItemGroupTemp, String uuid,
		OrderByComparator<DictItemGroupTemp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DICTITEMGROUPTEMP_WHERE);

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
			query.append(DictItemGroupTempModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemGroupTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemGroupTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item group temps where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DictItemGroupTemp dictItemGroupTemp : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictItemGroupTemp);
		}
	}

	/**
	 * Returns the number of dict item group temps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dict item group temps
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DICTITEMGROUPTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dictItemGroupTemp.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dictItemGroupTemp.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dictItemGroupTemp.uuid IS NULL OR dictItemGroupTemp.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictItemGroupTempImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DictItemGroupTempModelImpl.UUID_COLUMN_BITMASK |
			DictItemGroupTempModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict item group temp where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemGroupTempException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp findByUUID_G(String uuid, long groupId)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = fetchByUUID_G(uuid, groupId);

		if (dictItemGroupTemp == null) {
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

			throw new NoSuchDictItemGroupTempException(msg.toString());
		}

		return dictItemGroupTemp;
	}

	/**
	 * Returns the dict item group temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dict item group temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DictItemGroupTemp) {
			DictItemGroupTemp dictItemGroupTemp = (DictItemGroupTemp)result;

			if (!Objects.equals(uuid, dictItemGroupTemp.getUuid()) ||
					(groupId != dictItemGroupTemp.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTITEMGROUPTEMP_WHERE);

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

				List<DictItemGroupTemp> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DictItemGroupTemp dictItemGroupTemp = list.get(0);

					result = dictItemGroupTemp;

					cacheResult(dictItemGroupTemp);
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
			return (DictItemGroupTemp)result;
		}
	}

	/**
	 * Removes the dict item group temp where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dict item group temp that was removed
	 */
	@Override
	public DictItemGroupTemp removeByUUID_G(String uuid, long groupId)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = findByUUID_G(uuid, groupId);

		return remove(dictItemGroupTemp);
	}

	/**
	 * Returns the number of dict item group temps where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dict item group temps
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEMGROUPTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dictItemGroupTemp.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dictItemGroupTemp.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dictItemGroupTemp.uuid IS NULL OR dictItemGroupTemp.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dictItemGroupTemp.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictItemGroupTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictItemGroupTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DictItemGroupTempModelImpl.UUID_COLUMN_BITMASK |
			DictItemGroupTempModelImpl.COMPANYID_COLUMN_BITMASK |
			DictItemGroupTempModelImpl.DICTGROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict item group temps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item group temps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @return the range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item group temps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item group temps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator,
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

		List<DictItemGroupTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemGroupTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemGroupTemp dictItemGroupTemp : list) {
					if (!Objects.equals(uuid, dictItemGroupTemp.getUuid()) ||
							(companyId != dictItemGroupTemp.getCompanyId())) {
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

			query.append(_SQL_SELECT_DICTITEMGROUPTEMP_WHERE);

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
				query.append(DictItemGroupTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictItemGroupTemp>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemGroupTemp>)QueryUtil.list(q,
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
	 * Returns the first dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (dictItemGroupTemp != null) {
			return dictItemGroupTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDictItemGroupTempException(msg.toString());
	}

	/**
	 * Returns the first dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		List<DictItemGroupTemp> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (dictItemGroupTemp != null) {
			return dictItemGroupTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDictItemGroupTempException(msg.toString());
	}

	/**
	 * Returns the last dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DictItemGroupTemp> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item group temps before and after the current dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dictItemGroupId the primary key of the current dict item group temp
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	 */
	@Override
	public DictItemGroupTemp[] findByUuid_C_PrevAndNext(long dictItemGroupId,
		String uuid, long companyId,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = findByPrimaryKey(dictItemGroupId);

		Session session = null;

		try {
			session = openSession();

			DictItemGroupTemp[] array = new DictItemGroupTempImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dictItemGroupTemp,
					uuid, companyId, orderByComparator, true);

			array[1] = dictItemGroupTemp;

			array[2] = getByUuid_C_PrevAndNext(session, dictItemGroupTemp,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DictItemGroupTemp getByUuid_C_PrevAndNext(Session session,
		DictItemGroupTemp dictItemGroupTemp, String uuid, long companyId,
		OrderByComparator<DictItemGroupTemp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTITEMGROUPTEMP_WHERE);

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
			query.append(DictItemGroupTempModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemGroupTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemGroupTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item group temps where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DictItemGroupTemp dictItemGroupTemp : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictItemGroupTemp);
		}
	}

	/**
	 * Returns the number of dict item group temps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dict item group temps
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEMGROUPTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dictItemGroupTemp.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dictItemGroupTemp.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dictItemGroupTemp.uuid IS NULL OR dictItemGroupTemp.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dictItemGroupTemp.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_DICTITEMID_DICTGROUPID =
		new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictItemGroupTempImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_dictItemId_dictGroupId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			DictItemGroupTempModelImpl.GROUPID_COLUMN_BITMASK |
			DictItemGroupTempModelImpl.DICTGROUPID_COLUMN_BITMASK |
			DictItemGroupTempModelImpl.DICTITEMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTITEMID_DICTGROUPID =
		new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_dictItemId_dictGroupId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the dict item group temp where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; or throws a {@link NoSuchDictItemGroupTempException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @param dictItemId the dict item ID
	 * @return the matching dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp findByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = fetchByF_dictItemId_dictGroupId(groupId,
				dictGroupId, dictItemId);

		if (dictItemGroupTemp == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", dictGroupId=");
			msg.append(dictGroupId);

			msg.append(", dictItemId=");
			msg.append(dictItemId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDictItemGroupTempException(msg.toString());
		}

		return dictItemGroupTemp;
	}

	/**
	 * Returns the dict item group temp where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @param dictItemId the dict item ID
	 * @return the matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId) {
		return fetchByF_dictItemId_dictGroupId(groupId, dictGroupId,
			dictItemId, true);
	}

	/**
	 * Returns the dict item group temp where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @param dictItemId the dict item ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, dictGroupId, dictItemId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_DICTITEMID_DICTGROUPID,
					finderArgs, this);
		}

		if (result instanceof DictItemGroupTemp) {
			DictItemGroupTemp dictItemGroupTemp = (DictItemGroupTemp)result;

			if ((groupId != dictItemGroupTemp.getGroupId()) ||
					(dictGroupId != dictItemGroupTemp.getDictGroupId()) ||
					(dictItemId != dictItemGroupTemp.getDictItemId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DICTITEMGROUPTEMP_WHERE);

			query.append(_FINDER_COLUMN_F_DICTITEMID_DICTGROUPID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_DICTITEMID_DICTGROUPID_DICTGROUPID_2);

			query.append(_FINDER_COLUMN_F_DICTITEMID_DICTGROUPID_DICTITEMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dictGroupId);

				qPos.add(dictItemId);

				List<DictItemGroupTemp> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMID_DICTGROUPID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictItemGroupTempPersistenceImpl.fetchByF_dictItemId_dictGroupId(long, long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictItemGroupTemp dictItemGroupTemp = list.get(0);

					result = dictItemGroupTemp;

					cacheResult(dictItemGroupTemp);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTITEMID_DICTGROUPID,
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
			return (DictItemGroupTemp)result;
		}
	}

	/**
	 * Removes the dict item group temp where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @param dictItemId the dict item ID
	 * @return the dict item group temp that was removed
	 */
	@Override
	public DictItemGroupTemp removeByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = findByF_dictItemId_dictGroupId(groupId,
				dictGroupId, dictItemId);

		return remove(dictItemGroupTemp);
	}

	/**
	 * Returns the number of dict item group temps where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @param dictItemId the dict item ID
	 * @return the number of matching dict item group temps
	 */
	@Override
	public int countByF_dictItemId_dictGroupId(long groupId, long dictGroupId,
		long dictItemId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTITEMID_DICTGROUPID;

		Object[] finderArgs = new Object[] { groupId, dictGroupId, dictItemId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DICTITEMGROUPTEMP_WHERE);

			query.append(_FINDER_COLUMN_F_DICTITEMID_DICTGROUPID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_DICTITEMID_DICTGROUPID_DICTGROUPID_2);

			query.append(_FINDER_COLUMN_F_DICTITEMID_DICTGROUPID_DICTITEMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dictGroupId);

				qPos.add(dictItemId);

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

	private static final String _FINDER_COLUMN_F_DICTITEMID_DICTGROUPID_GROUPID_2 =
		"dictItemGroupTemp.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMID_DICTGROUPID_DICTGROUPID_2 =
		"dictItemGroupTemp.dictGroupId = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMID_DICTGROUPID_DICTITEMID_2 =
		"dictItemGroupTemp.dictItemId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTGROUPID =
		new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictItemGroupTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_dictGroupId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTGROUPID =
		new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictItemGroupTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_dictGroupId",
			new String[] { Long.class.getName(), Long.class.getName() },
			DictItemGroupTempModelImpl.GROUPID_COLUMN_BITMASK |
			DictItemGroupTempModelImpl.DICTGROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTGROUPID = new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_dictGroupId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @return the matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByF_dictGroupId(long groupId,
		long dictGroupId) {
		return findByF_dictGroupId(groupId, dictGroupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @return the range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByF_dictGroupId(long groupId,
		long dictGroupId, int start, int end) {
		return findByF_dictGroupId(groupId, dictGroupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByF_dictGroupId(long groupId,
		long dictGroupId, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return findByF_dictGroupId(groupId, dictGroupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByF_dictGroupId(long groupId,
		long dictGroupId, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTGROUPID;
			finderArgs = new Object[] { groupId, dictGroupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTGROUPID;
			finderArgs = new Object[] {
					groupId, dictGroupId,
					
					start, end, orderByComparator
				};
		}

		List<DictItemGroupTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemGroupTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemGroupTemp dictItemGroupTemp : list) {
					if ((groupId != dictItemGroupTemp.getGroupId()) ||
							(dictGroupId != dictItemGroupTemp.getDictGroupId())) {
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

			query.append(_SQL_SELECT_DICTITEMGROUPTEMP_WHERE);

			query.append(_FINDER_COLUMN_F_DICTGROUPID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_DICTGROUPID_DICTGROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemGroupTempModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dictGroupId);

				if (!pagination) {
					list = (List<DictItemGroupTemp>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemGroupTemp>)QueryUtil.list(q,
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
	 * Returns the first dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp findByF_dictGroupId_First(long groupId,
		long dictGroupId, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = fetchByF_dictGroupId_First(groupId,
				dictGroupId, orderByComparator);

		if (dictItemGroupTemp != null) {
			return dictItemGroupTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dictGroupId=");
		msg.append(dictGroupId);

		msg.append("}");

		throw new NoSuchDictItemGroupTempException(msg.toString());
	}

	/**
	 * Returns the first dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByF_dictGroupId_First(long groupId,
		long dictGroupId, OrderByComparator<DictItemGroupTemp> orderByComparator) {
		List<DictItemGroupTemp> list = findByF_dictGroupId(groupId,
				dictGroupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp findByF_dictGroupId_Last(long groupId,
		long dictGroupId, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = fetchByF_dictGroupId_Last(groupId,
				dictGroupId, orderByComparator);

		if (dictItemGroupTemp != null) {
			return dictItemGroupTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dictGroupId=");
		msg.append(dictGroupId);

		msg.append("}");

		throw new NoSuchDictItemGroupTempException(msg.toString());
	}

	/**
	 * Returns the last dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByF_dictGroupId_Last(long groupId,
		long dictGroupId, OrderByComparator<DictItemGroupTemp> orderByComparator) {
		int count = countByF_dictGroupId(groupId, dictGroupId);

		if (count == 0) {
			return null;
		}

		List<DictItemGroupTemp> list = findByF_dictGroupId(groupId,
				dictGroupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item group temps before and after the current dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	 *
	 * @param dictItemGroupId the primary key of the current dict item group temp
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	 */
	@Override
	public DictItemGroupTemp[] findByF_dictGroupId_PrevAndNext(
		long dictItemGroupId, long groupId, long dictGroupId,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = findByPrimaryKey(dictItemGroupId);

		Session session = null;

		try {
			session = openSession();

			DictItemGroupTemp[] array = new DictItemGroupTempImpl[3];

			array[0] = getByF_dictGroupId_PrevAndNext(session,
					dictItemGroupTemp, groupId, dictGroupId, orderByComparator,
					true);

			array[1] = dictItemGroupTemp;

			array[2] = getByF_dictGroupId_PrevAndNext(session,
					dictItemGroupTemp, groupId, dictGroupId, orderByComparator,
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

	protected DictItemGroupTemp getByF_dictGroupId_PrevAndNext(
		Session session, DictItemGroupTemp dictItemGroupTemp, long groupId,
		long dictGroupId,
		OrderByComparator<DictItemGroupTemp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTITEMGROUPTEMP_WHERE);

		query.append(_FINDER_COLUMN_F_DICTGROUPID_GROUPID_2);

		query.append(_FINDER_COLUMN_F_DICTGROUPID_DICTGROUPID_2);

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
			query.append(DictItemGroupTempModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(dictGroupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemGroupTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemGroupTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item group temps where groupId = &#63; and dictGroupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 */
	@Override
	public void removeByF_dictGroupId(long groupId, long dictGroupId) {
		for (DictItemGroupTemp dictItemGroupTemp : findByF_dictGroupId(
				groupId, dictGroupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictItemGroupTemp);
		}
	}

	/**
	 * Returns the number of dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictGroupId the dict group ID
	 * @return the number of matching dict item group temps
	 */
	@Override
	public int countByF_dictGroupId(long groupId, long dictGroupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTGROUPID;

		Object[] finderArgs = new Object[] { groupId, dictGroupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEMGROUPTEMP_WHERE);

			query.append(_FINDER_COLUMN_F_DICTGROUPID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_DICTGROUPID_DICTGROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dictGroupId);

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

	private static final String _FINDER_COLUMN_F_DICTGROUPID_GROUPID_2 = "dictItemGroupTemp.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTGROUPID_DICTGROUPID_2 = "dictItemGroupTemp.dictGroupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTITEMID =
		new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictItemGroupTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_dictItemId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMID =
		new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictItemGroupTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_dictItemId",
			new String[] { Long.class.getName(), Long.class.getName() },
			DictItemGroupTempModelImpl.GROUPID_COLUMN_BITMASK |
			DictItemGroupTempModelImpl.DICTITEMID_COLUMN_BITMASK |
			DictItemGroupTempModelImpl.DICTGROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTITEMID = new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_dictItemId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict item group temps where groupId = &#63; and dictItemId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictItemId the dict item ID
	 * @return the matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByF_dictItemId(long groupId,
		long dictItemId) {
		return findByF_dictItemId(groupId, dictItemId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item group temps where groupId = &#63; and dictItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dictItemId the dict item ID
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @return the range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByF_dictItemId(long groupId,
		long dictItemId, int start, int end) {
		return findByF_dictItemId(groupId, dictItemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item group temps where groupId = &#63; and dictItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dictItemId the dict item ID
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByF_dictItemId(long groupId,
		long dictItemId, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return findByF_dictItemId(groupId, dictItemId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item group temps where groupId = &#63; and dictItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dictItemId the dict item ID
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByF_dictItemId(long groupId,
		long dictItemId, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMID;
			finderArgs = new Object[] { groupId, dictItemId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTITEMID;
			finderArgs = new Object[] {
					groupId, dictItemId,
					
					start, end, orderByComparator
				};
		}

		List<DictItemGroupTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemGroupTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemGroupTemp dictItemGroupTemp : list) {
					if ((groupId != dictItemGroupTemp.getGroupId()) ||
							(dictItemId != dictItemGroupTemp.getDictItemId())) {
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

			query.append(_SQL_SELECT_DICTITEMGROUPTEMP_WHERE);

			query.append(_FINDER_COLUMN_F_DICTITEMID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_DICTITEMID_DICTITEMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemGroupTempModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dictItemId);

				if (!pagination) {
					list = (List<DictItemGroupTemp>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemGroupTemp>)QueryUtil.list(q,
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
	 * Returns the first dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictItemId the dict item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp findByF_dictItemId_First(long groupId,
		long dictItemId, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = fetchByF_dictItemId_First(groupId,
				dictItemId, orderByComparator);

		if (dictItemGroupTemp != null) {
			return dictItemGroupTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dictItemId=");
		msg.append(dictItemId);

		msg.append("}");

		throw new NoSuchDictItemGroupTempException(msg.toString());
	}

	/**
	 * Returns the first dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictItemId the dict item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByF_dictItemId_First(long groupId,
		long dictItemId, OrderByComparator<DictItemGroupTemp> orderByComparator) {
		List<DictItemGroupTemp> list = findByF_dictItemId(groupId, dictItemId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictItemId the dict item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp findByF_dictItemId_Last(long groupId,
		long dictItemId, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = fetchByF_dictItemId_Last(groupId,
				dictItemId, orderByComparator);

		if (dictItemGroupTemp != null) {
			return dictItemGroupTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dictItemId=");
		msg.append(dictItemId);

		msg.append("}");

		throw new NoSuchDictItemGroupTempException(msg.toString());
	}

	/**
	 * Returns the last dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictItemId the dict item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByF_dictItemId_Last(long groupId,
		long dictItemId, OrderByComparator<DictItemGroupTemp> orderByComparator) {
		int count = countByF_dictItemId(groupId, dictItemId);

		if (count == 0) {
			return null;
		}

		List<DictItemGroupTemp> list = findByF_dictItemId(groupId, dictItemId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item group temps before and after the current dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	 *
	 * @param dictItemGroupId the primary key of the current dict item group temp
	 * @param groupId the group ID
	 * @param dictItemId the dict item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	 */
	@Override
	public DictItemGroupTemp[] findByF_dictItemId_PrevAndNext(
		long dictItemGroupId, long groupId, long dictItemId,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = findByPrimaryKey(dictItemGroupId);

		Session session = null;

		try {
			session = openSession();

			DictItemGroupTemp[] array = new DictItemGroupTempImpl[3];

			array[0] = getByF_dictItemId_PrevAndNext(session,
					dictItemGroupTemp, groupId, dictItemId, orderByComparator,
					true);

			array[1] = dictItemGroupTemp;

			array[2] = getByF_dictItemId_PrevAndNext(session,
					dictItemGroupTemp, groupId, dictItemId, orderByComparator,
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

	protected DictItemGroupTemp getByF_dictItemId_PrevAndNext(Session session,
		DictItemGroupTemp dictItemGroupTemp, long groupId, long dictItemId,
		OrderByComparator<DictItemGroupTemp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTITEMGROUPTEMP_WHERE);

		query.append(_FINDER_COLUMN_F_DICTITEMID_GROUPID_2);

		query.append(_FINDER_COLUMN_F_DICTITEMID_DICTITEMID_2);

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
			query.append(DictItemGroupTempModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(dictItemId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemGroupTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemGroupTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item group temps where groupId = &#63; and dictItemId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dictItemId the dict item ID
	 */
	@Override
	public void removeByF_dictItemId(long groupId, long dictItemId) {
		for (DictItemGroupTemp dictItemGroupTemp : findByF_dictItemId(groupId,
				dictItemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictItemGroupTemp);
		}
	}

	/**
	 * Returns the number of dict item group temps where groupId = &#63; and dictItemId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictItemId the dict item ID
	 * @return the number of matching dict item group temps
	 */
	@Override
	public int countByF_dictItemId(long groupId, long dictItemId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTITEMID;

		Object[] finderArgs = new Object[] { groupId, dictItemId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEMGROUPTEMP_WHERE);

			query.append(_FINDER_COLUMN_F_DICTITEMID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_DICTITEMID_DICTITEMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dictItemId);

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

	private static final String _FINDER_COLUMN_F_DICTITEMID_GROUPID_2 = "dictItemGroupTemp.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMID_DICTITEMID_2 = "dictItemGroupTemp.dictItemId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_NEWERTHAN =
		new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED,
			DictItemGroupTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_newerThan",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_NEWERTHAN =
		new FinderPath(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByF_newerThan",
			new String[] { Date.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @return the matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByF_newerThan(Date modifiedDate,
		long groupId) {
		return findByF_newerThan(modifiedDate, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @return the range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByF_newerThan(Date modifiedDate,
		long groupId, int start, int end) {
		return findByF_newerThan(modifiedDate, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByF_newerThan(Date modifiedDate,
		long groupId, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return findByF_newerThan(modifiedDate, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findByF_newerThan(Date modifiedDate,
		long groupId, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_NEWERTHAN;
		finderArgs = new Object[] {
				_getTime(modifiedDate), groupId,
				
				start, end, orderByComparator
			};

		List<DictItemGroupTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemGroupTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemGroupTemp dictItemGroupTemp : list) {
					if ((modifiedDate.getTime() > dictItemGroupTemp.getModifiedDate()
																	   .getTime()) ||
							(groupId != dictItemGroupTemp.getGroupId())) {
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

			query.append(_SQL_SELECT_DICTITEMGROUPTEMP_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_NEWERTHAN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_NEWERTHAN_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_F_NEWERTHAN_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemGroupTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictItemGroupTemp>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemGroupTemp>)QueryUtil.list(q,
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
	 * Returns the first dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp findByF_newerThan_First(Date modifiedDate,
		long groupId, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = fetchByF_newerThan_First(modifiedDate,
				groupId, orderByComparator);

		if (dictItemGroupTemp != null) {
			return dictItemGroupTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictItemGroupTempException(msg.toString());
	}

	/**
	 * Returns the first dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByF_newerThan_First(Date modifiedDate,
		long groupId, OrderByComparator<DictItemGroupTemp> orderByComparator) {
		List<DictItemGroupTemp> list = findByF_newerThan(modifiedDate, groupId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp findByF_newerThan_Last(Date modifiedDate,
		long groupId, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = fetchByF_newerThan_Last(modifiedDate,
				groupId, orderByComparator);

		if (dictItemGroupTemp != null) {
			return dictItemGroupTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictItemGroupTempException(msg.toString());
	}

	/**
	 * Returns the last dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByF_newerThan_Last(Date modifiedDate,
		long groupId, OrderByComparator<DictItemGroupTemp> orderByComparator) {
		int count = countByF_newerThan(modifiedDate, groupId);

		if (count == 0) {
			return null;
		}

		List<DictItemGroupTemp> list = findByF_newerThan(modifiedDate, groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item group temps before and after the current dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param dictItemGroupId the primary key of the current dict item group temp
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	 */
	@Override
	public DictItemGroupTemp[] findByF_newerThan_PrevAndNext(
		long dictItemGroupId, Date modifiedDate, long groupId,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = findByPrimaryKey(dictItemGroupId);

		Session session = null;

		try {
			session = openSession();

			DictItemGroupTemp[] array = new DictItemGroupTempImpl[3];

			array[0] = getByF_newerThan_PrevAndNext(session, dictItemGroupTemp,
					modifiedDate, groupId, orderByComparator, true);

			array[1] = dictItemGroupTemp;

			array[2] = getByF_newerThan_PrevAndNext(session, dictItemGroupTemp,
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

	protected DictItemGroupTemp getByF_newerThan_PrevAndNext(Session session,
		DictItemGroupTemp dictItemGroupTemp, Date modifiedDate, long groupId,
		OrderByComparator<DictItemGroupTemp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTITEMGROUPTEMP_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_F_NEWERTHAN_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_F_NEWERTHAN_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_F_NEWERTHAN_GROUPID_2);

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
			query.append(DictItemGroupTempModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemGroupTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemGroupTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_newerThan(Date modifiedDate, long groupId) {
		for (DictItemGroupTemp dictItemGroupTemp : findByF_newerThan(
				modifiedDate, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(dictItemGroupTemp);
		}
	}

	/**
	 * Returns the number of dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @return the number of matching dict item group temps
	 */
	@Override
	public int countByF_newerThan(Date modifiedDate, long groupId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_NEWERTHAN;

		Object[] finderArgs = new Object[] { _getTime(modifiedDate), groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEMGROUPTEMP_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_NEWERTHAN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_NEWERTHAN_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_F_NEWERTHAN_GROUPID_2);

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

	private static final String _FINDER_COLUMN_F_NEWERTHAN_MODIFIEDDATE_1 = "dictItemGroupTemp.modifiedDate IS NULL AND ";
	private static final String _FINDER_COLUMN_F_NEWERTHAN_MODIFIEDDATE_2 = "dictItemGroupTemp.modifiedDate >= ? AND ";
	private static final String _FINDER_COLUMN_F_NEWERTHAN_GROUPID_2 = "dictItemGroupTemp.groupId = ?";

	public DictItemGroupTempPersistenceImpl() {
		setModelClass(DictItemGroupTemp.class);

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
	 * Caches the dict item group temp in the entity cache if it is enabled.
	 *
	 * @param dictItemGroupTemp the dict item group temp
	 */
	@Override
	public void cacheResult(DictItemGroupTemp dictItemGroupTemp) {
		entityCache.putResult(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempImpl.class, dictItemGroupTemp.getPrimaryKey(),
			dictItemGroupTemp);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				dictItemGroupTemp.getUuid(), dictItemGroupTemp.getGroupId()
			}, dictItemGroupTemp);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMID_DICTGROUPID,
			new Object[] {
				dictItemGroupTemp.getGroupId(),
				dictItemGroupTemp.getDictGroupId(),
				dictItemGroupTemp.getDictItemId()
			}, dictItemGroupTemp);

		dictItemGroupTemp.resetOriginalValues();
	}

	/**
	 * Caches the dict item group temps in the entity cache if it is enabled.
	 *
	 * @param dictItemGroupTemps the dict item group temps
	 */
	@Override
	public void cacheResult(List<DictItemGroupTemp> dictItemGroupTemps) {
		for (DictItemGroupTemp dictItemGroupTemp : dictItemGroupTemps) {
			if (entityCache.getResult(
						DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
						DictItemGroupTempImpl.class,
						dictItemGroupTemp.getPrimaryKey()) == null) {
				cacheResult(dictItemGroupTemp);
			}
			else {
				dictItemGroupTemp.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dict item group temps.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DictItemGroupTempImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dict item group temp.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DictItemGroupTemp dictItemGroupTemp) {
		entityCache.removeResult(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempImpl.class, dictItemGroupTemp.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DictItemGroupTempModelImpl)dictItemGroupTemp,
			true);
	}

	@Override
	public void clearCache(List<DictItemGroupTemp> dictItemGroupTemps) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DictItemGroupTemp dictItemGroupTemp : dictItemGroupTemps) {
			entityCache.removeResult(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
				DictItemGroupTempImpl.class, dictItemGroupTemp.getPrimaryKey());

			clearUniqueFindersCache((DictItemGroupTempModelImpl)dictItemGroupTemp,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		DictItemGroupTempModelImpl dictItemGroupTempModelImpl) {
		Object[] args = new Object[] {
				dictItemGroupTempModelImpl.getUuid(),
				dictItemGroupTempModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dictItemGroupTempModelImpl, false);

		args = new Object[] {
				dictItemGroupTempModelImpl.getGroupId(),
				dictItemGroupTempModelImpl.getDictGroupId(),
				dictItemGroupTempModelImpl.getDictItemId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_DICTITEMID_DICTGROUPID,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMID_DICTGROUPID,
			args, dictItemGroupTempModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DictItemGroupTempModelImpl dictItemGroupTempModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dictItemGroupTempModelImpl.getUuid(),
					dictItemGroupTempModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dictItemGroupTempModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictItemGroupTempModelImpl.getOriginalUuid(),
					dictItemGroupTempModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dictItemGroupTempModelImpl.getGroupId(),
					dictItemGroupTempModelImpl.getDictGroupId(),
					dictItemGroupTempModelImpl.getDictItemId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMID_DICTGROUPID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTITEMID_DICTGROUPID,
				args);
		}

		if ((dictItemGroupTempModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_DICTITEMID_DICTGROUPID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictItemGroupTempModelImpl.getOriginalGroupId(),
					dictItemGroupTempModelImpl.getOriginalDictGroupId(),
					dictItemGroupTempModelImpl.getOriginalDictItemId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMID_DICTGROUPID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTITEMID_DICTGROUPID,
				args);
		}
	}

	/**
	 * Creates a new dict item group temp with the primary key. Does not add the dict item group temp to the database.
	 *
	 * @param dictItemGroupId the primary key for the new dict item group temp
	 * @return the new dict item group temp
	 */
	@Override
	public DictItemGroupTemp create(long dictItemGroupId) {
		DictItemGroupTemp dictItemGroupTemp = new DictItemGroupTempImpl();

		dictItemGroupTemp.setNew(true);
		dictItemGroupTemp.setPrimaryKey(dictItemGroupId);

		String uuid = PortalUUIDUtil.generate();

		dictItemGroupTemp.setUuid(uuid);

		dictItemGroupTemp.setCompanyId(companyProvider.getCompanyId());

		return dictItemGroupTemp;
	}

	/**
	 * Removes the dict item group temp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dictItemGroupId the primary key of the dict item group temp
	 * @return the dict item group temp that was removed
	 * @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	 */
	@Override
	public DictItemGroupTemp remove(long dictItemGroupId)
		throws NoSuchDictItemGroupTempException {
		return remove((Serializable)dictItemGroupId);
	}

	/**
	 * Removes the dict item group temp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dict item group temp
	 * @return the dict item group temp that was removed
	 * @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	 */
	@Override
	public DictItemGroupTemp remove(Serializable primaryKey)
		throws NoSuchDictItemGroupTempException {
		Session session = null;

		try {
			session = openSession();

			DictItemGroupTemp dictItemGroupTemp = (DictItemGroupTemp)session.get(DictItemGroupTempImpl.class,
					primaryKey);

			if (dictItemGroupTemp == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDictItemGroupTempException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dictItemGroupTemp);
		}
		catch (NoSuchDictItemGroupTempException nsee) {
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
	protected DictItemGroupTemp removeImpl(DictItemGroupTemp dictItemGroupTemp) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dictItemGroupTemp)) {
				dictItemGroupTemp = (DictItemGroupTemp)session.get(DictItemGroupTempImpl.class,
						dictItemGroupTemp.getPrimaryKeyObj());
			}

			if (dictItemGroupTemp != null) {
				session.delete(dictItemGroupTemp);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dictItemGroupTemp != null) {
			clearCache(dictItemGroupTemp);
		}

		return dictItemGroupTemp;
	}

	@Override
	public DictItemGroupTemp updateImpl(DictItemGroupTemp dictItemGroupTemp) {
		boolean isNew = dictItemGroupTemp.isNew();

		if (!(dictItemGroupTemp instanceof DictItemGroupTempModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dictItemGroupTemp.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dictItemGroupTemp);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dictItemGroupTemp proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DictItemGroupTemp implementation " +
				dictItemGroupTemp.getClass());
		}

		DictItemGroupTempModelImpl dictItemGroupTempModelImpl = (DictItemGroupTempModelImpl)dictItemGroupTemp;

		if (Validator.isNull(dictItemGroupTemp.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dictItemGroupTemp.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dictItemGroupTemp.getCreateDate() == null)) {
			if (serviceContext == null) {
				dictItemGroupTemp.setCreateDate(now);
			}
			else {
				dictItemGroupTemp.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!dictItemGroupTempModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dictItemGroupTemp.setModifiedDate(now);
			}
			else {
				dictItemGroupTemp.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dictItemGroupTemp.isNew()) {
				session.save(dictItemGroupTemp);

				dictItemGroupTemp.setNew(false);
			}
			else {
				dictItemGroupTemp = (DictItemGroupTemp)session.merge(dictItemGroupTemp);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DictItemGroupTempModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dictItemGroupTempModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					dictItemGroupTempModelImpl.getUuid(),
					dictItemGroupTempModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					dictItemGroupTempModelImpl.getGroupId(),
					dictItemGroupTempModelImpl.getDictGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTGROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTGROUPID,
				args);

			args = new Object[] {
					dictItemGroupTempModelImpl.getGroupId(),
					dictItemGroupTempModelImpl.getDictItemId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dictItemGroupTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemGroupTempModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dictItemGroupTempModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dictItemGroupTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemGroupTempModelImpl.getOriginalUuid(),
						dictItemGroupTempModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dictItemGroupTempModelImpl.getUuid(),
						dictItemGroupTempModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((dictItemGroupTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTGROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemGroupTempModelImpl.getOriginalGroupId(),
						dictItemGroupTempModelImpl.getOriginalDictGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTGROUPID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTGROUPID,
					args);

				args = new Object[] {
						dictItemGroupTempModelImpl.getGroupId(),
						dictItemGroupTempModelImpl.getDictGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTGROUPID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTGROUPID,
					args);
			}

			if ((dictItemGroupTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemGroupTempModelImpl.getOriginalGroupId(),
						dictItemGroupTempModelImpl.getOriginalDictItemId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMID,
					args);

				args = new Object[] {
						dictItemGroupTempModelImpl.getGroupId(),
						dictItemGroupTempModelImpl.getDictItemId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMID,
					args);
			}
		}

		entityCache.putResult(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemGroupTempImpl.class, dictItemGroupTemp.getPrimaryKey(),
			dictItemGroupTemp, false);

		clearUniqueFindersCache(dictItemGroupTempModelImpl, false);
		cacheUniqueFindersCache(dictItemGroupTempModelImpl);

		dictItemGroupTemp.resetOriginalValues();

		return dictItemGroupTemp;
	}

	/**
	 * Returns the dict item group temp with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict item group temp
	 * @return the dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	 */
	@Override
	public DictItemGroupTemp findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDictItemGroupTempException {
		DictItemGroupTemp dictItemGroupTemp = fetchByPrimaryKey(primaryKey);

		if (dictItemGroupTemp == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDictItemGroupTempException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dictItemGroupTemp;
	}

	/**
	 * Returns the dict item group temp with the primary key or throws a {@link NoSuchDictItemGroupTempException} if it could not be found.
	 *
	 * @param dictItemGroupId the primary key of the dict item group temp
	 * @return the dict item group temp
	 * @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	 */
	@Override
	public DictItemGroupTemp findByPrimaryKey(long dictItemGroupId)
		throws NoSuchDictItemGroupTempException {
		return findByPrimaryKey((Serializable)dictItemGroupId);
	}

	/**
	 * Returns the dict item group temp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict item group temp
	 * @return the dict item group temp, or <code>null</code> if a dict item group temp with the primary key could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
				DictItemGroupTempImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DictItemGroupTemp dictItemGroupTemp = (DictItemGroupTemp)serializable;

		if (dictItemGroupTemp == null) {
			Session session = null;

			try {
				session = openSession();

				dictItemGroupTemp = (DictItemGroupTemp)session.get(DictItemGroupTempImpl.class,
						primaryKey);

				if (dictItemGroupTemp != null) {
					cacheResult(dictItemGroupTemp);
				}
				else {
					entityCache.putResult(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
						DictItemGroupTempImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
					DictItemGroupTempImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dictItemGroupTemp;
	}

	/**
	 * Returns the dict item group temp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dictItemGroupId the primary key of the dict item group temp
	 * @return the dict item group temp, or <code>null</code> if a dict item group temp with the primary key could not be found
	 */
	@Override
	public DictItemGroupTemp fetchByPrimaryKey(long dictItemGroupId) {
		return fetchByPrimaryKey((Serializable)dictItemGroupId);
	}

	@Override
	public Map<Serializable, DictItemGroupTemp> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DictItemGroupTemp> map = new HashMap<Serializable, DictItemGroupTemp>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DictItemGroupTemp dictItemGroupTemp = fetchByPrimaryKey(primaryKey);

			if (dictItemGroupTemp != null) {
				map.put(primaryKey, dictItemGroupTemp);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
					DictItemGroupTempImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DictItemGroupTemp)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DICTITEMGROUPTEMP_WHERE_PKS_IN);

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

			for (DictItemGroupTemp dictItemGroupTemp : (List<DictItemGroupTemp>)q.list()) {
				map.put(dictItemGroupTemp.getPrimaryKeyObj(), dictItemGroupTemp);

				cacheResult(dictItemGroupTemp);

				uncachedPrimaryKeys.remove(dictItemGroupTemp.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DictItemGroupTempModelImpl.ENTITY_CACHE_ENABLED,
					DictItemGroupTempImpl.class, primaryKey, nullModel);
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
	 * Returns all the dict item group temps.
	 *
	 * @return the dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item group temps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @return the range of dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item group temps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findAll(int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item group temps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict item group temps
	 * @param end the upper bound of the range of dict item group temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dict item group temps
	 */
	@Override
	public List<DictItemGroupTemp> findAll(int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator,
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

		List<DictItemGroupTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemGroupTemp>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DICTITEMGROUPTEMP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DICTITEMGROUPTEMP;

				if (pagination) {
					sql = sql.concat(DictItemGroupTempModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DictItemGroupTemp>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemGroupTemp>)QueryUtil.list(q,
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
	 * Removes all the dict item group temps from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DictItemGroupTemp dictItemGroupTemp : findAll()) {
			remove(dictItemGroupTemp);
		}
	}

	/**
	 * Returns the number of dict item group temps.
	 *
	 * @return the number of dict item group temps
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DICTITEMGROUPTEMP);

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
		return DictItemGroupTempModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dict item group temp persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DictItemGroupTempImpl.class.getName());
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

	private static final String _SQL_SELECT_DICTITEMGROUPTEMP = "SELECT dictItemGroupTemp FROM DictItemGroupTemp dictItemGroupTemp";
	private static final String _SQL_SELECT_DICTITEMGROUPTEMP_WHERE_PKS_IN = "SELECT dictItemGroupTemp FROM DictItemGroupTemp dictItemGroupTemp WHERE dictItemGroupId IN (";
	private static final String _SQL_SELECT_DICTITEMGROUPTEMP_WHERE = "SELECT dictItemGroupTemp FROM DictItemGroupTemp dictItemGroupTemp WHERE ";
	private static final String _SQL_COUNT_DICTITEMGROUPTEMP = "SELECT COUNT(dictItemGroupTemp) FROM DictItemGroupTemp dictItemGroupTemp";
	private static final String _SQL_COUNT_DICTITEMGROUPTEMP_WHERE = "SELECT COUNT(dictItemGroupTemp) FROM DictItemGroupTemp dictItemGroupTemp WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dictItemGroupTemp.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DictItemGroupTemp exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DictItemGroupTemp exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DictItemGroupTempPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}