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

import org.opencps.synchronization.exception.NoSuchDictItemTempException;
import org.opencps.synchronization.model.DictItemTemp;
import org.opencps.synchronization.model.impl.DictItemTempImpl;
import org.opencps.synchronization.model.impl.DictItemTempModelImpl;
import org.opencps.synchronization.service.persistence.DictItemTempPersistence;

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
 * The persistence implementation for the dict item temp service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see DictItemTempPersistence
 * @see org.opencps.synchronization.service.persistence.DictItemTempUtil
 * @generated
 */
@ProviderType
public class DictItemTempPersistenceImpl extends BasePersistenceImpl<DictItemTemp>
	implements DictItemTempPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DictItemTempUtil} to access the dict item temp persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DictItemTempImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DictItemTempModelImpl.UUID_COLUMN_BITMASK |
			DictItemTempModelImpl.SIBLING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dict item temps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item temps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @return the range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item temps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByUuid(String uuid, int start, int end,
		OrderByComparator<DictItemTemp> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item temps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByUuid(String uuid, int start, int end,
		OrderByComparator<DictItemTemp> orderByComparator,
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

		List<DictItemTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemTemp dictItemTemp : list) {
					if (!Objects.equals(uuid, dictItemTemp.getUuid())) {
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

			query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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
				query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByUuid_First(String uuid,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByUuid_First(uuid, orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the first dict item temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByUuid_First(String uuid,
		OrderByComparator<DictItemTemp> orderByComparator) {
		List<DictItemTemp> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByUuid_Last(String uuid,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByUuid_Last(uuid, orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the last dict item temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByUuid_Last(String uuid,
		OrderByComparator<DictItemTemp> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DictItemTemp> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item temps before and after the current dict item temp in the ordered set where uuid = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item temp
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item temp
	 * @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp[] findByUuid_PrevAndNext(long dictItemId, String uuid,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItemTemp[] array = new DictItemTempImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dictItemTemp, uuid,
					orderByComparator, true);

			array[1] = dictItemTemp;

			array[2] = getByUuid_PrevAndNext(session, dictItemTemp, uuid,
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

	protected DictItemTemp getByUuid_PrevAndNext(Session session,
		DictItemTemp dictItemTemp, String uuid,
		OrderByComparator<DictItemTemp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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
			query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item temps where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DictItemTemp dictItemTemp : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dictItemTemp);
		}
	}

	/**
	 * Returns the number of dict item temps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dict item temps
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DICTITEMTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dictItemTemp.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dictItemTemp.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dictItemTemp.uuid IS NULL OR dictItemTemp.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DictItemTempModelImpl.UUID_COLUMN_BITMASK |
			DictItemTempModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict item temp where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemTempException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByUUID_G(String uuid, long groupId)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByUUID_G(uuid, groupId);

		if (dictItemTemp == null) {
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

			throw new NoSuchDictItemTempException(msg.toString());
		}

		return dictItemTemp;
	}

	/**
	 * Returns the dict item temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dict item temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DictItemTemp) {
			DictItemTemp dictItemTemp = (DictItemTemp)result;

			if (!Objects.equals(uuid, dictItemTemp.getUuid()) ||
					(groupId != dictItemTemp.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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

				List<DictItemTemp> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DictItemTemp dictItemTemp = list.get(0);

					result = dictItemTemp;

					cacheResult(dictItemTemp);
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
			return (DictItemTemp)result;
		}
	}

	/**
	 * Removes the dict item temp where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dict item temp that was removed
	 */
	@Override
	public DictItemTemp removeByUUID_G(String uuid, long groupId)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = findByUUID_G(uuid, groupId);

		return remove(dictItemTemp);
	}

	/**
	 * Returns the number of dict item temps where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dict item temps
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEMTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dictItemTemp.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dictItemTemp.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dictItemTemp.uuid IS NULL OR dictItemTemp.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dictItemTemp.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DictItemTempModelImpl.UUID_COLUMN_BITMASK |
			DictItemTempModelImpl.COMPANYID_COLUMN_BITMASK |
			DictItemTempModelImpl.SIBLING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict item temps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item temps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @return the range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item temps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DictItemTemp> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item temps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DictItemTemp> orderByComparator,
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

		List<DictItemTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemTemp dictItemTemp : list) {
					if (!Objects.equals(uuid, dictItemTemp.getUuid()) ||
							(companyId != dictItemTemp.getCompanyId())) {
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

			query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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
				query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the first dict item temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictItemTemp> orderByComparator) {
		List<DictItemTemp> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the last dict item temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictItemTemp> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DictItemTemp> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item temps before and after the current dict item temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item temp
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item temp
	 * @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp[] findByUuid_C_PrevAndNext(long dictItemId,
		String uuid, long companyId,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItemTemp[] array = new DictItemTempImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dictItemTemp, uuid,
					companyId, orderByComparator, true);

			array[1] = dictItemTemp;

			array[2] = getByUuid_C_PrevAndNext(session, dictItemTemp, uuid,
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

	protected DictItemTemp getByUuid_C_PrevAndNext(Session session,
		DictItemTemp dictItemTemp, String uuid, long companyId,
		OrderByComparator<DictItemTemp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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
			query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item temps where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DictItemTemp dictItemTemp : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictItemTemp);
		}
	}

	/**
	 * Returns the number of dict item temps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dict item temps
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEMTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dictItemTemp.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dictItemTemp.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dictItemTemp.uuid IS NULL OR dictItemTemp.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dictItemTemp.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTCOLLECTIONID =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_dictCollectionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_dictCollectionId", new String[] { Long.class.getName() },
			DictItemTempModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK |
			DictItemTempModelImpl.SIBLING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_dictCollectionId", new String[] { Long.class.getName() });

	/**
	 * Returns all the dict item temps where dictCollectionId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @return the matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictCollectionId(long dictCollectionId) {
		return findByF_dictCollectionId(dictCollectionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item temps where dictCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @return the range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictCollectionId(long dictCollectionId,
		int start, int end) {
		return findByF_dictCollectionId(dictCollectionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item temps where dictCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictCollectionId(long dictCollectionId,
		int start, int end, OrderByComparator<DictItemTemp> orderByComparator) {
		return findByF_dictCollectionId(dictCollectionId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item temps where dictCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictCollectionId(long dictCollectionId,
		int start, int end, OrderByComparator<DictItemTemp> orderByComparator,
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

		List<DictItemTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemTemp dictItemTemp : list) {
					if ((dictCollectionId != dictItemTemp.getDictCollectionId())) {
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

			query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONID_DICTCOLLECTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dictCollectionId);

				if (!pagination) {
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item temp in the ordered set where dictCollectionId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_dictCollectionId_First(long dictCollectionId,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_dictCollectionId_First(dictCollectionId,
				orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append("}");

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the first dict item temp in the ordered set where dictCollectionId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_dictCollectionId_First(long dictCollectionId,
		OrderByComparator<DictItemTemp> orderByComparator) {
		List<DictItemTemp> list = findByF_dictCollectionId(dictCollectionId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item temp in the ordered set where dictCollectionId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_dictCollectionId_Last(long dictCollectionId,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_dictCollectionId_Last(dictCollectionId,
				orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append("}");

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the last dict item temp in the ordered set where dictCollectionId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_dictCollectionId_Last(long dictCollectionId,
		OrderByComparator<DictItemTemp> orderByComparator) {
		int count = countByF_dictCollectionId(dictCollectionId);

		if (count == 0) {
			return null;
		}

		List<DictItemTemp> list = findByF_dictCollectionId(dictCollectionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item temps before and after the current dict item temp in the ordered set where dictCollectionId = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item temp
	 * @param dictCollectionId the dict collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item temp
	 * @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp[] findByF_dictCollectionId_PrevAndNext(
		long dictItemId, long dictCollectionId,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItemTemp[] array = new DictItemTempImpl[3];

			array[0] = getByF_dictCollectionId_PrevAndNext(session,
					dictItemTemp, dictCollectionId, orderByComparator, true);

			array[1] = dictItemTemp;

			array[2] = getByF_dictCollectionId_PrevAndNext(session,
					dictItemTemp, dictCollectionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DictItemTemp getByF_dictCollectionId_PrevAndNext(
		Session session, DictItemTemp dictItemTemp, long dictCollectionId,
		OrderByComparator<DictItemTemp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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
			query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dictCollectionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item temps where dictCollectionId = &#63; from the database.
	 *
	 * @param dictCollectionId the dict collection ID
	 */
	@Override
	public void removeByF_dictCollectionId(long dictCollectionId) {
		for (DictItemTemp dictItemTemp : findByF_dictCollectionId(
				dictCollectionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictItemTemp);
		}
	}

	/**
	 * Returns the number of dict item temps where dictCollectionId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @return the number of matching dict item temps
	 */
	@Override
	public int countByF_dictCollectionId(long dictCollectionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID;

		Object[] finderArgs = new Object[] { dictCollectionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DICTITEMTEMP_WHERE);

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
		"dictItemTemp.dictCollectionId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_DICTITEMCODE = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_dictItemCode",
			new String[] { String.class.getName(), Long.class.getName() },
			DictItemTempModelImpl.ITEMCODE_COLUMN_BITMASK |
			DictItemTempModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTITEMCODE = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_dictItemCode",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict item temp where itemCode = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemTempException} if it could not be found.
	 *
	 * @param itemCode the item code
	 * @param groupId the group ID
	 * @return the matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_dictItemCode(String itemCode, long groupId)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_dictItemCode(itemCode, groupId);

		if (dictItemTemp == null) {
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

			throw new NoSuchDictItemTempException(msg.toString());
		}

		return dictItemTemp;
	}

	/**
	 * Returns the dict item temp where itemCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itemCode the item code
	 * @param groupId the group ID
	 * @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_dictItemCode(String itemCode, long groupId) {
		return fetchByF_dictItemCode(itemCode, groupId, true);
	}

	/**
	 * Returns the dict item temp where itemCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itemCode the item code
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_dictItemCode(String itemCode, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { itemCode, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE,
					finderArgs, this);
		}

		if (result instanceof DictItemTemp) {
			DictItemTemp dictItemTemp = (DictItemTemp)result;

			if (!Objects.equals(itemCode, dictItemTemp.getItemCode()) ||
					(groupId != dictItemTemp.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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

				List<DictItemTemp> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictItemTempPersistenceImpl.fetchByF_dictItemCode(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictItemTemp dictItemTemp = list.get(0);

					result = dictItemTemp;

					cacheResult(dictItemTemp);
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
			return (DictItemTemp)result;
		}
	}

	/**
	 * Removes the dict item temp where itemCode = &#63; and groupId = &#63; from the database.
	 *
	 * @param itemCode the item code
	 * @param groupId the group ID
	 * @return the dict item temp that was removed
	 */
	@Override
	public DictItemTemp removeByF_dictItemCode(String itemCode, long groupId)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = findByF_dictItemCode(itemCode, groupId);

		return remove(dictItemTemp);
	}

	/**
	 * Returns the number of dict item temps where itemCode = &#63; and groupId = &#63;.
	 *
	 * @param itemCode the item code
	 * @param groupId the group ID
	 * @return the number of matching dict item temps
	 */
	@Override
	public int countByF_dictItemCode(String itemCode, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTITEMCODE;

		Object[] finderArgs = new Object[] { itemCode, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEMTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_F_DICTITEMCODE_ITEMCODE_1 = "dictItemTemp.itemCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMCODE_ITEMCODE_2 = "dictItemTemp.itemCode = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMCODE_ITEMCODE_3 = "(dictItemTemp.itemCode IS NULL OR dictItemTemp.itemCode = '') AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMCODE_GROUPID_2 = "dictItemTemp.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTITEMBYGROUP =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_dictItemByGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMBYGROUP =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_dictItemByGroup",
			new String[] { Long.class.getName(), Long.class.getName() },
			DictItemTempModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK |
			DictItemTempModelImpl.GROUPID_COLUMN_BITMASK |
			DictItemTempModelImpl.SIBLING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTITEMBYGROUP = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_dictItemByGroup",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict item temps where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictItemByGroup(long dictCollectionId,
		long groupId) {
		return findByF_dictItemByGroup(dictCollectionId, groupId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item temps where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @return the range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictItemByGroup(long dictCollectionId,
		long groupId, int start, int end) {
		return findByF_dictItemByGroup(dictCollectionId, groupId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the dict item temps where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictItemByGroup(long dictCollectionId,
		long groupId, int start, int end,
		OrderByComparator<DictItemTemp> orderByComparator) {
		return findByF_dictItemByGroup(dictCollectionId, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item temps where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictItemByGroup(long dictCollectionId,
		long groupId, int start, int end,
		OrderByComparator<DictItemTemp> orderByComparator,
		boolean retrieveFromCache) {
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

		List<DictItemTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemTemp dictItemTemp : list) {
					if ((dictCollectionId != dictItemTemp.getDictCollectionId()) ||
							(groupId != dictItemTemp.getGroupId())) {
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

			query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

			query.append(_FINDER_COLUMN_F_DICTITEMBYGROUP_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_F_DICTITEMBYGROUP_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_dictItemByGroup_First(long dictCollectionId,
		long groupId, OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_dictItemByGroup_First(dictCollectionId,
				groupId, orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the first dict item temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_dictItemByGroup_First(long dictCollectionId,
		long groupId, OrderByComparator<DictItemTemp> orderByComparator) {
		List<DictItemTemp> list = findByF_dictItemByGroup(dictCollectionId,
				groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_dictItemByGroup_Last(long dictCollectionId,
		long groupId, OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_dictItemByGroup_Last(dictCollectionId,
				groupId, orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the last dict item temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_dictItemByGroup_Last(long dictCollectionId,
		long groupId, OrderByComparator<DictItemTemp> orderByComparator) {
		int count = countByF_dictItemByGroup(dictCollectionId, groupId);

		if (count == 0) {
			return null;
		}

		List<DictItemTemp> list = findByF_dictItemByGroup(dictCollectionId,
				groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item temps before and after the current dict item temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item temp
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item temp
	 * @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp[] findByF_dictItemByGroup_PrevAndNext(long dictItemId,
		long dictCollectionId, long groupId,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItemTemp[] array = new DictItemTempImpl[3];

			array[0] = getByF_dictItemByGroup_PrevAndNext(session,
					dictItemTemp, dictCollectionId, groupId, orderByComparator,
					true);

			array[1] = dictItemTemp;

			array[2] = getByF_dictItemByGroup_PrevAndNext(session,
					dictItemTemp, dictCollectionId, groupId, orderByComparator,
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

	protected DictItemTemp getByF_dictItemByGroup_PrevAndNext(Session session,
		DictItemTemp dictItemTemp, long dictCollectionId, long groupId,
		OrderByComparator<DictItemTemp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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
			query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dictCollectionId);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item temps where dictCollectionId = &#63; and groupId = &#63; from the database.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_dictItemByGroup(long dictCollectionId, long groupId) {
		for (DictItemTemp dictItemTemp : findByF_dictItemByGroup(
				dictCollectionId, groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dictItemTemp);
		}
	}

	/**
	 * Returns the number of dict item temps where dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the number of matching dict item temps
	 */
	@Override
	public int countByF_dictItemByGroup(long dictCollectionId, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTITEMBYGROUP;

		Object[] finderArgs = new Object[] { dictCollectionId, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEMTEMP_WHERE);

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
		"dictItemTemp.dictCollectionId = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMBYGROUP_GROUPID_2 = "dictItemTemp.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_dictItemCode_dictCollectionId",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			DictItemTempModelImpl.ITEMCODE_COLUMN_BITMASK |
			DictItemTempModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK |
			DictItemTempModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTITEMCODE_DICTCOLLECTIONID =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_dictItemCode_dictCollectionId",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns the dict item temp where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemTempException} if it could not be found.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_dictItemCode_dictCollectionId(String itemCode,
		long dictCollectionId, long groupId) throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_dictItemCode_dictCollectionId(itemCode,
				dictCollectionId, groupId);

		if (dictItemTemp == null) {
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

			throw new NoSuchDictItemTempException(msg.toString());
		}

		return dictItemTemp;
	}

	/**
	 * Returns the dict item temp where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_dictItemCode_dictCollectionId(
		String itemCode, long dictCollectionId, long groupId) {
		return fetchByF_dictItemCode_dictCollectionId(itemCode,
			dictCollectionId, groupId, true);
	}

	/**
	 * Returns the dict item temp where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_dictItemCode_dictCollectionId(
		String itemCode, long dictCollectionId, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { itemCode, dictCollectionId, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
					finderArgs, this);
		}

		if (result instanceof DictItemTemp) {
			DictItemTemp dictItemTemp = (DictItemTemp)result;

			if (!Objects.equals(itemCode, dictItemTemp.getItemCode()) ||
					(dictCollectionId != dictItemTemp.getDictCollectionId()) ||
					(groupId != dictItemTemp.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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

				List<DictItemTemp> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictItemTempPersistenceImpl.fetchByF_dictItemCode_dictCollectionId(String, long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictItemTemp dictItemTemp = list.get(0);

					result = dictItemTemp;

					cacheResult(dictItemTemp);
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
			return (DictItemTemp)result;
		}
	}

	/**
	 * Removes the dict item temp where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; from the database.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the dict item temp that was removed
	 */
	@Override
	public DictItemTemp removeByF_dictItemCode_dictCollectionId(
		String itemCode, long dictCollectionId, long groupId)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = findByF_dictItemCode_dictCollectionId(itemCode,
				dictCollectionId, groupId);

		return remove(dictItemTemp);
	}

	/**
	 * Returns the number of dict item temps where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63;.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @param groupId the group ID
	 * @return the number of matching dict item temps
	 */
	@Override
	public int countByF_dictItemCode_dictCollectionId(String itemCode,
		long dictCollectionId, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTITEMCODE_DICTCOLLECTIONID;

		Object[] finderArgs = new Object[] { itemCode, dictCollectionId, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DICTITEMTEMP_WHERE);

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
		"dictItemTemp.itemCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_ITEMCODE_2 =
		"dictItemTemp.itemCode = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_ITEMCODE_3 =
		"(dictItemTemp.itemCode IS NULL OR dictItemTemp.itemCode = '') AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_DICTCOLLECTIONID_2 =
		"dictItemTemp.dictCollectionId = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMCODE_DICTCOLLECTIONID_GROUPID_2 =
		"dictItemTemp.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_PARENTITEMID =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_parentItemId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_parentItemId",
			new String[] { Long.class.getName() },
			DictItemTempModelImpl.PARENTITEMID_COLUMN_BITMASK |
			DictItemTempModelImpl.SIBLING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_PARENTITEMID = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_parentItemId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dict item temps where parentItemId = &#63;.
	 *
	 * @param parentItemId the parent item ID
	 * @return the matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_parentItemId(long parentItemId) {
		return findByF_parentItemId(parentItemId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item temps where parentItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentItemId the parent item ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @return the range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_parentItemId(long parentItemId,
		int start, int end) {
		return findByF_parentItemId(parentItemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item temps where parentItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentItemId the parent item ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_parentItemId(long parentItemId,
		int start, int end, OrderByComparator<DictItemTemp> orderByComparator) {
		return findByF_parentItemId(parentItemId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item temps where parentItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentItemId the parent item ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_parentItemId(long parentItemId,
		int start, int end, OrderByComparator<DictItemTemp> orderByComparator,
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

		List<DictItemTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemTemp dictItemTemp : list) {
					if ((parentItemId != dictItemTemp.getParentItemId())) {
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

			query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

			query.append(_FINDER_COLUMN_F_PARENTITEMID_PARENTITEMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentItemId);

				if (!pagination) {
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item temp in the ordered set where parentItemId = &#63;.
	 *
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_parentItemId_First(long parentItemId,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_parentItemId_First(parentItemId,
				orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentItemId=");
		msg.append(parentItemId);

		msg.append("}");

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the first dict item temp in the ordered set where parentItemId = &#63;.
	 *
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_parentItemId_First(long parentItemId,
		OrderByComparator<DictItemTemp> orderByComparator) {
		List<DictItemTemp> list = findByF_parentItemId(parentItemId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item temp in the ordered set where parentItemId = &#63;.
	 *
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_parentItemId_Last(long parentItemId,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_parentItemId_Last(parentItemId,
				orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentItemId=");
		msg.append(parentItemId);

		msg.append("}");

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the last dict item temp in the ordered set where parentItemId = &#63;.
	 *
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_parentItemId_Last(long parentItemId,
		OrderByComparator<DictItemTemp> orderByComparator) {
		int count = countByF_parentItemId(parentItemId);

		if (count == 0) {
			return null;
		}

		List<DictItemTemp> list = findByF_parentItemId(parentItemId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item temps before and after the current dict item temp in the ordered set where parentItemId = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item temp
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item temp
	 * @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp[] findByF_parentItemId_PrevAndNext(long dictItemId,
		long parentItemId, OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItemTemp[] array = new DictItemTempImpl[3];

			array[0] = getByF_parentItemId_PrevAndNext(session, dictItemTemp,
					parentItemId, orderByComparator, true);

			array[1] = dictItemTemp;

			array[2] = getByF_parentItemId_PrevAndNext(session, dictItemTemp,
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

	protected DictItemTemp getByF_parentItemId_PrevAndNext(Session session,
		DictItemTemp dictItemTemp, long parentItemId,
		OrderByComparator<DictItemTemp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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
			query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentItemId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item temps where parentItemId = &#63; from the database.
	 *
	 * @param parentItemId the parent item ID
	 */
	@Override
	public void removeByF_parentItemId(long parentItemId) {
		for (DictItemTemp dictItemTemp : findByF_parentItemId(parentItemId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictItemTemp);
		}
	}

	/**
	 * Returns the number of dict item temps where parentItemId = &#63;.
	 *
	 * @param parentItemId the parent item ID
	 * @return the number of matching dict item temps
	 */
	@Override
	public int countByF_parentItemId(long parentItemId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_PARENTITEMID;

		Object[] finderArgs = new Object[] { parentItemId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DICTITEMTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_F_PARENTITEMID_PARENTITEMID_2 = "dictItemTemp.parentItemId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_PARENTITEMID_LEVEL =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_parentItemId_level",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID_LEVEL =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_parentItemId_level",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			DictItemTempModelImpl.GROUPID_COLUMN_BITMASK |
			DictItemTempModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK |
			DictItemTempModelImpl.PARENTITEMID_COLUMN_BITMASK |
			DictItemTempModelImpl.LEVEL_COLUMN_BITMASK |
			DictItemTempModelImpl.SIBLING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_PARENTITEMID_LEVEL = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_parentItemId_level",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the dict item temps where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @return the matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level) {
		return findByF_parentItemId_level(groupId, dictCollectionId,
			parentItemId, level, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item temps where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @return the range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level, int start, int end) {
		return findByF_parentItemId_level(groupId, dictCollectionId,
			parentItemId, level, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item temps where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level, int start,
		int end, OrderByComparator<DictItemTemp> orderByComparator) {
		return findByF_parentItemId_level(groupId, dictCollectionId,
			parentItemId, level, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item temps where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level, int start,
		int end, OrderByComparator<DictItemTemp> orderByComparator,
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

		List<DictItemTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemTemp dictItemTemp : list) {
					if ((groupId != dictItemTemp.getGroupId()) ||
							(dictCollectionId != dictItemTemp.getDictCollectionId()) ||
							(parentItemId != dictItemTemp.getParentItemId()) ||
							(level != dictItemTemp.getLevel())) {
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

			query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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
				query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item temp in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_parentItemId_level_First(long groupId,
		long dictCollectionId, long parentItemId, int level,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_parentItemId_level_First(groupId,
				dictCollectionId, parentItemId, level, orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
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

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the first dict item temp in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_parentItemId_level_First(long groupId,
		long dictCollectionId, long parentItemId, int level,
		OrderByComparator<DictItemTemp> orderByComparator) {
		List<DictItemTemp> list = findByF_parentItemId_level(groupId,
				dictCollectionId, parentItemId, level, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item temp in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_parentItemId_level_Last(long groupId,
		long dictCollectionId, long parentItemId, int level,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_parentItemId_level_Last(groupId,
				dictCollectionId, parentItemId, level, orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
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

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the last dict item temp in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_parentItemId_level_Last(long groupId,
		long dictCollectionId, long parentItemId, int level,
		OrderByComparator<DictItemTemp> orderByComparator) {
		int count = countByF_parentItemId_level(groupId, dictCollectionId,
				parentItemId, level);

		if (count == 0) {
			return null;
		}

		List<DictItemTemp> list = findByF_parentItemId_level(groupId,
				dictCollectionId, parentItemId, level, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item temps before and after the current dict item temp in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item temp
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item temp
	 * @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp[] findByF_parentItemId_level_PrevAndNext(
		long dictItemId, long groupId, long dictCollectionId,
		long parentItemId, int level,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItemTemp[] array = new DictItemTempImpl[3];

			array[0] = getByF_parentItemId_level_PrevAndNext(session,
					dictItemTemp, groupId, dictCollectionId, parentItemId,
					level, orderByComparator, true);

			array[1] = dictItemTemp;

			array[2] = getByF_parentItemId_level_PrevAndNext(session,
					dictItemTemp, groupId, dictCollectionId, parentItemId,
					level, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DictItemTemp getByF_parentItemId_level_PrevAndNext(
		Session session, DictItemTemp dictItemTemp, long groupId,
		long dictCollectionId, long parentItemId, int level,
		OrderByComparator<DictItemTemp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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
			query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item temps where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 */
	@Override
	public void removeByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level) {
		for (DictItemTemp dictItemTemp : findByF_parentItemId_level(groupId,
				dictCollectionId, parentItemId, level, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dictItemTemp);
		}
	}

	/**
	 * Returns the number of dict item temps where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param level the level
	 * @return the number of matching dict item temps
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

			query.append(_SQL_COUNT_DICTITEMTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_F_PARENTITEMID_LEVEL_GROUPID_2 = "dictItemTemp.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_PARENTITEMID_LEVEL_DICTCOLLECTIONID_2 =
		"dictItemTemp.dictCollectionId = ? AND ";
	private static final String _FINDER_COLUMN_F_PARENTITEMID_LEVEL_PARENTITEMID_2 =
		"dictItemTemp.parentItemId = ? AND ";
	private static final String _FINDER_COLUMN_F_PARENTITEMID_LEVEL_LEVEL_2 = "dictItemTemp.level = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_IC_DCI = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByIC_DCI",
			new String[] { String.class.getName(), Long.class.getName() },
			DictItemTempModelImpl.ITEMCODE_COLUMN_BITMASK |
			DictItemTempModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_IC_DCI = new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByIC_DCI",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict item temp where itemCode = &#63; and dictCollectionId = &#63; or throws a {@link NoSuchDictItemTempException} if it could not be found.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @return the matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByIC_DCI(String itemCode, long dictCollectionId)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByIC_DCI(itemCode, dictCollectionId);

		if (dictItemTemp == null) {
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

			throw new NoSuchDictItemTempException(msg.toString());
		}

		return dictItemTemp;
	}

	/**
	 * Returns the dict item temp where itemCode = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByIC_DCI(String itemCode, long dictCollectionId) {
		return fetchByIC_DCI(itemCode, dictCollectionId, true);
	}

	/**
	 * Returns the dict item temp where itemCode = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByIC_DCI(String itemCode, long dictCollectionId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { itemCode, dictCollectionId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_IC_DCI,
					finderArgs, this);
		}

		if (result instanceof DictItemTemp) {
			DictItemTemp dictItemTemp = (DictItemTemp)result;

			if (!Objects.equals(itemCode, dictItemTemp.getItemCode()) ||
					(dictCollectionId != dictItemTemp.getDictCollectionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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

				List<DictItemTemp> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_IC_DCI,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictItemTempPersistenceImpl.fetchByIC_DCI(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictItemTemp dictItemTemp = list.get(0);

					result = dictItemTemp;

					cacheResult(dictItemTemp);
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
			return (DictItemTemp)result;
		}
	}

	/**
	 * Removes the dict item temp where itemCode = &#63; and dictCollectionId = &#63; from the database.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @return the dict item temp that was removed
	 */
	@Override
	public DictItemTemp removeByIC_DCI(String itemCode, long dictCollectionId)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = findByIC_DCI(itemCode, dictCollectionId);

		return remove(dictItemTemp);
	}

	/**
	 * Returns the number of dict item temps where itemCode = &#63; and dictCollectionId = &#63;.
	 *
	 * @param itemCode the item code
	 * @param dictCollectionId the dict collection ID
	 * @return the number of matching dict item temps
	 */
	@Override
	public int countByIC_DCI(String itemCode, long dictCollectionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_IC_DCI;

		Object[] finderArgs = new Object[] { itemCode, dictCollectionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEMTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_IC_DCI_ITEMCODE_1 = "dictItemTemp.itemCode IS NULL AND ";
	private static final String _FINDER_COLUMN_IC_DCI_ITEMCODE_2 = "dictItemTemp.itemCode = ? AND ";
	private static final String _FINDER_COLUMN_IC_DCI_ITEMCODE_3 = "(dictItemTemp.itemCode IS NULL OR dictItemTemp.itemCode = '') AND ";
	private static final String _FINDER_COLUMN_IC_DCI_DICTCOLLECTIONID_2 = "dictItemTemp.dictCollectionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_TREEINDEX =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_treeIndex",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_TREEINDEX =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByF_treeIndex",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @return the matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_treeIndex(long dictCollectionId,
		long parentItemId, String treeIndex) {
		return findByF_treeIndex(dictCollectionId, parentItemId, treeIndex,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @return the range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_treeIndex(long dictCollectionId,
		long parentItemId, String treeIndex, int start, int end) {
		return findByF_treeIndex(dictCollectionId, parentItemId, treeIndex,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_treeIndex(long dictCollectionId,
		long parentItemId, String treeIndex, int start, int end,
		OrderByComparator<DictItemTemp> orderByComparator) {
		return findByF_treeIndex(dictCollectionId, parentItemId, treeIndex,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_treeIndex(long dictCollectionId,
		long parentItemId, String treeIndex, int start, int end,
		OrderByComparator<DictItemTemp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_TREEINDEX;
		finderArgs = new Object[] {
				dictCollectionId, parentItemId, treeIndex,
				
				start, end, orderByComparator
			};

		List<DictItemTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemTemp dictItemTemp : list) {
					if ((dictCollectionId != dictItemTemp.getDictCollectionId()) ||
							(parentItemId != dictItemTemp.getParentItemId()) ||
							!StringUtil.wildcardMatches(
								dictItemTemp.getTreeIndex(), treeIndex, '_',
								'%', '\\', true)) {
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

			query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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
				query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_treeIndex_First(long dictCollectionId,
		long parentItemId, String treeIndex,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_treeIndex_First(dictCollectionId,
				parentItemId, treeIndex, orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
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

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the first dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_treeIndex_First(long dictCollectionId,
		long parentItemId, String treeIndex,
		OrderByComparator<DictItemTemp> orderByComparator) {
		List<DictItemTemp> list = findByF_treeIndex(dictCollectionId,
				parentItemId, treeIndex, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_treeIndex_Last(long dictCollectionId,
		long parentItemId, String treeIndex,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_treeIndex_Last(dictCollectionId,
				parentItemId, treeIndex, orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
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

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the last dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_treeIndex_Last(long dictCollectionId,
		long parentItemId, String treeIndex,
		OrderByComparator<DictItemTemp> orderByComparator) {
		int count = countByF_treeIndex(dictCollectionId, parentItemId, treeIndex);

		if (count == 0) {
			return null;
		}

		List<DictItemTemp> list = findByF_treeIndex(dictCollectionId,
				parentItemId, treeIndex, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item temps before and after the current dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item temp
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item temp
	 * @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp[] findByF_treeIndex_PrevAndNext(long dictItemId,
		long dictCollectionId, long parentItemId, String treeIndex,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItemTemp[] array = new DictItemTempImpl[3];

			array[0] = getByF_treeIndex_PrevAndNext(session, dictItemTemp,
					dictCollectionId, parentItemId, treeIndex,
					orderByComparator, true);

			array[1] = dictItemTemp;

			array[2] = getByF_treeIndex_PrevAndNext(session, dictItemTemp,
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

	protected DictItemTemp getByF_treeIndex_PrevAndNext(Session session,
		DictItemTemp dictItemTemp, long dictCollectionId, long parentItemId,
		String treeIndex, OrderByComparator<DictItemTemp> orderByComparator,
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

		query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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
			query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63; from the database.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 */
	@Override
	public void removeByF_treeIndex(long dictCollectionId, long parentItemId,
		String treeIndex) {
		for (DictItemTemp dictItemTemp : findByF_treeIndex(dictCollectionId,
				parentItemId, treeIndex, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(dictItemTemp);
		}
	}

	/**
	 * Returns the number of dict item temps where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param treeIndex the tree index
	 * @return the number of matching dict item temps
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

			query.append(_SQL_COUNT_DICTITEMTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_F_TREEINDEX_DICTCOLLECTIONID_2 = "dictItemTemp.dictCollectionId = ? AND ";
	private static final String _FINDER_COLUMN_F_TREEINDEX_PARENTITEMID_2 = "dictItemTemp.parentItemId = ? AND ";
	private static final String _FINDER_COLUMN_F_TREEINDEX_TREEINDEX_1 = "dictItemTemp.treeIndex IS NULL";
	private static final String _FINDER_COLUMN_F_TREEINDEX_TREEINDEX_2 = "dictItemTemp.treeIndex LIKE ?";
	private static final String _FINDER_COLUMN_F_TREEINDEX_TREEINDEX_3 = "(dictItemTemp.treeIndex IS NULL OR dictItemTemp.treeIndex LIKE '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTCOLLECTIONID_PARENTITEMID =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_dictCollectionId_parentItemId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID_PARENTITEMID =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_dictCollectionId_parentItemId",
			new String[] { Long.class.getName(), Long.class.getName() },
			DictItemTempModelImpl.DICTCOLLECTIONID_COLUMN_BITMASK |
			DictItemTempModelImpl.PARENTITEMID_COLUMN_BITMASK |
			DictItemTempModelImpl.SIBLING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID_PARENTITEMID =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_dictCollectionId_parentItemId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @return the matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId) {
		return findByF_dictCollectionId_parentItemId(dictCollectionId,
			parentItemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @return the range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId, int start, int end) {
		return findByF_dictCollectionId_parentItemId(dictCollectionId,
			parentItemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId, int start, int end,
		OrderByComparator<DictItemTemp> orderByComparator) {
		return findByF_dictCollectionId_parentItemId(dictCollectionId,
			parentItemId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId, int start, int end,
		OrderByComparator<DictItemTemp> orderByComparator,
		boolean retrieveFromCache) {
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

		List<DictItemTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemTemp dictItemTemp : list) {
					if ((dictCollectionId != dictItemTemp.getDictCollectionId()) ||
							(parentItemId != dictItemTemp.getParentItemId())) {
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

			query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONID_PARENTITEMID_DICTCOLLECTIONID_2);

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONID_PARENTITEMID_PARENTITEMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_dictCollectionId_parentItemId_First(
		long dictCollectionId, long parentItemId,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_dictCollectionId_parentItemId_First(dictCollectionId,
				parentItemId, orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", parentItemId=");
		msg.append(parentItemId);

		msg.append("}");

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the first dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_dictCollectionId_parentItemId_First(
		long dictCollectionId, long parentItemId,
		OrderByComparator<DictItemTemp> orderByComparator) {
		List<DictItemTemp> list = findByF_dictCollectionId_parentItemId(dictCollectionId,
				parentItemId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_dictCollectionId_parentItemId_Last(
		long dictCollectionId, long parentItemId,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_dictCollectionId_parentItemId_Last(dictCollectionId,
				parentItemId, orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dictCollectionId=");
		msg.append(dictCollectionId);

		msg.append(", parentItemId=");
		msg.append(parentItemId);

		msg.append("}");

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the last dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_dictCollectionId_parentItemId_Last(
		long dictCollectionId, long parentItemId,
		OrderByComparator<DictItemTemp> orderByComparator) {
		int count = countByF_dictCollectionId_parentItemId(dictCollectionId,
				parentItemId);

		if (count == 0) {
			return null;
		}

		List<DictItemTemp> list = findByF_dictCollectionId_parentItemId(dictCollectionId,
				parentItemId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item temps before and after the current dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item temp
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item temp
	 * @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp[] findByF_dictCollectionId_parentItemId_PrevAndNext(
		long dictItemId, long dictCollectionId, long parentItemId,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItemTemp[] array = new DictItemTempImpl[3];

			array[0] = getByF_dictCollectionId_parentItemId_PrevAndNext(session,
					dictItemTemp, dictCollectionId, parentItemId,
					orderByComparator, true);

			array[1] = dictItemTemp;

			array[2] = getByF_dictCollectionId_parentItemId_PrevAndNext(session,
					dictItemTemp, dictCollectionId, parentItemId,
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

	protected DictItemTemp getByF_dictCollectionId_parentItemId_PrevAndNext(
		Session session, DictItemTemp dictItemTemp, long dictCollectionId,
		long parentItemId, OrderByComparator<DictItemTemp> orderByComparator,
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

		query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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
			query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dictCollectionId);

		qPos.add(parentItemId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63; from the database.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 */
	@Override
	public void removeByF_dictCollectionId_parentItemId(long dictCollectionId,
		long parentItemId) {
		for (DictItemTemp dictItemTemp : findByF_dictCollectionId_parentItemId(
				dictCollectionId, parentItemId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dictItemTemp);
		}
	}

	/**
	 * Returns the number of dict item temps where dictCollectionId = &#63; and parentItemId = &#63;.
	 *
	 * @param dictCollectionId the dict collection ID
	 * @param parentItemId the parent item ID
	 * @return the number of matching dict item temps
	 */
	@Override
	public int countByF_dictCollectionId_parentItemId(long dictCollectionId,
		long parentItemId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID_PARENTITEMID;

		Object[] finderArgs = new Object[] { dictCollectionId, parentItemId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEMTEMP_WHERE);

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
		"dictItemTemp.dictCollectionId = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONID_PARENTITEMID_PARENTITEMID_2 =
		"dictItemTemp.parentItemId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTITEMNEWERTHAN =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, DictItemTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_dictItemNewerThan",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_DICTITEMNEWERTHAN =
		new FinderPath(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_dictItemNewerThan",
			new String[] { Date.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict item temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @return the matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictItemNewerThan(Date modifiedDate,
		long groupId) {
		return findByF_dictItemNewerThan(modifiedDate, groupId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @return the range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictItemNewerThan(Date modifiedDate,
		long groupId, int start, int end) {
		return findByF_dictItemNewerThan(modifiedDate, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictItemNewerThan(Date modifiedDate,
		long groupId, int start, int end,
		OrderByComparator<DictItemTemp> orderByComparator) {
		return findByF_dictItemNewerThan(modifiedDate, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item temps
	 */
	@Override
	public List<DictItemTemp> findByF_dictItemNewerThan(Date modifiedDate,
		long groupId, int start, int end,
		OrderByComparator<DictItemTemp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTITEMNEWERTHAN;
		finderArgs = new Object[] {
				_getTime(modifiedDate), groupId,
				
				start, end, orderByComparator
			};

		List<DictItemTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemTemp dictItemTemp : list) {
					if ((modifiedDate.getTime() > dictItemTemp.getModifiedDate()
																  .getTime()) ||
							(groupId != dictItemTemp.getGroupId())) {
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

			query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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
				query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dict item temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_dictItemNewerThan_First(Date modifiedDate,
		long groupId, OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_dictItemNewerThan_First(modifiedDate,
				groupId, orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the first dict item temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_dictItemNewerThan_First(Date modifiedDate,
		long groupId, OrderByComparator<DictItemTemp> orderByComparator) {
		List<DictItemTemp> list = findByF_dictItemNewerThan(modifiedDate,
				groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp
	 * @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp findByF_dictItemNewerThan_Last(Date modifiedDate,
		long groupId, OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByF_dictItemNewerThan_Last(modifiedDate,
				groupId, orderByComparator);

		if (dictItemTemp != null) {
			return dictItemTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictItemTempException(msg.toString());
	}

	/**
	 * Returns the last dict item temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	 */
	@Override
	public DictItemTemp fetchByF_dictItemNewerThan_Last(Date modifiedDate,
		long groupId, OrderByComparator<DictItemTemp> orderByComparator) {
		int count = countByF_dictItemNewerThan(modifiedDate, groupId);

		if (count == 0) {
			return null;
		}

		List<DictItemTemp> list = findByF_dictItemNewerThan(modifiedDate,
				groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item temps before and after the current dict item temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param dictItemId the primary key of the current dict item temp
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item temp
	 * @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp[] findByF_dictItemNewerThan_PrevAndNext(
		long dictItemId, Date modifiedDate, long groupId,
		OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = findByPrimaryKey(dictItemId);

		Session session = null;

		try {
			session = openSession();

			DictItemTemp[] array = new DictItemTempImpl[3];

			array[0] = getByF_dictItemNewerThan_PrevAndNext(session,
					dictItemTemp, modifiedDate, groupId, orderByComparator, true);

			array[1] = dictItemTemp;

			array[2] = getByF_dictItemNewerThan_PrevAndNext(session,
					dictItemTemp, modifiedDate, groupId, orderByComparator,
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

	protected DictItemTemp getByF_dictItemNewerThan_PrevAndNext(
		Session session, DictItemTemp dictItemTemp, Date modifiedDate,
		long groupId, OrderByComparator<DictItemTemp> orderByComparator,
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

		query.append(_SQL_SELECT_DICTITEMTEMP_WHERE);

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
			query.append(DictItemTempModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item temps where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_dictItemNewerThan(Date modifiedDate, long groupId) {
		for (DictItemTemp dictItemTemp : findByF_dictItemNewerThan(
				modifiedDate, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(dictItemTemp);
		}
	}

	/**
	 * Returns the number of dict item temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @return the number of matching dict item temps
	 */
	@Override
	public int countByF_dictItemNewerThan(Date modifiedDate, long groupId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_DICTITEMNEWERTHAN;

		Object[] finderArgs = new Object[] { _getTime(modifiedDate), groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEMTEMP_WHERE);

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
		"dictItemTemp.modifiedDate IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMNEWERTHAN_MODIFIEDDATE_2 =
		"dictItemTemp.modifiedDate >= ? AND ";
	private static final String _FINDER_COLUMN_F_DICTITEMNEWERTHAN_GROUPID_2 = "dictItemTemp.groupId = ?";

	public DictItemTempPersistenceImpl() {
		setModelClass(DictItemTemp.class);

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
	 * Caches the dict item temp in the entity cache if it is enabled.
	 *
	 * @param dictItemTemp the dict item temp
	 */
	@Override
	public void cacheResult(DictItemTemp dictItemTemp) {
		entityCache.putResult(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempImpl.class, dictItemTemp.getPrimaryKey(), dictItemTemp);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dictItemTemp.getUuid(), dictItemTemp.getGroupId() },
			dictItemTemp);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE,
			new Object[] { dictItemTemp.getItemCode(), dictItemTemp.getGroupId() },
			dictItemTemp);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
			new Object[] {
				dictItemTemp.getItemCode(), dictItemTemp.getDictCollectionId(),
				dictItemTemp.getGroupId()
			}, dictItemTemp);

		finderCache.putResult(FINDER_PATH_FETCH_BY_IC_DCI,
			new Object[] {
				dictItemTemp.getItemCode(), dictItemTemp.getDictCollectionId()
			}, dictItemTemp);

		dictItemTemp.resetOriginalValues();
	}

	/**
	 * Caches the dict item temps in the entity cache if it is enabled.
	 *
	 * @param dictItemTemps the dict item temps
	 */
	@Override
	public void cacheResult(List<DictItemTemp> dictItemTemps) {
		for (DictItemTemp dictItemTemp : dictItemTemps) {
			if (entityCache.getResult(
						DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
						DictItemTempImpl.class, dictItemTemp.getPrimaryKey()) == null) {
				cacheResult(dictItemTemp);
			}
			else {
				dictItemTemp.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dict item temps.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DictItemTempImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dict item temp.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DictItemTemp dictItemTemp) {
		entityCache.removeResult(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempImpl.class, dictItemTemp.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DictItemTempModelImpl)dictItemTemp, true);
	}

	@Override
	public void clearCache(List<DictItemTemp> dictItemTemps) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DictItemTemp dictItemTemp : dictItemTemps) {
			entityCache.removeResult(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
				DictItemTempImpl.class, dictItemTemp.getPrimaryKey());

			clearUniqueFindersCache((DictItemTempModelImpl)dictItemTemp, true);
		}
	}

	protected void cacheUniqueFindersCache(
		DictItemTempModelImpl dictItemTempModelImpl) {
		Object[] args = new Object[] {
				dictItemTempModelImpl.getUuid(),
				dictItemTempModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dictItemTempModelImpl, false);

		args = new Object[] {
				dictItemTempModelImpl.getItemCode(),
				dictItemTempModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_DICTITEMCODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE, args,
			dictItemTempModelImpl, false);

		args = new Object[] {
				dictItemTempModelImpl.getItemCode(),
				dictItemTempModelImpl.getDictCollectionId(),
				dictItemTempModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
			args, dictItemTempModelImpl, false);

		args = new Object[] {
				dictItemTempModelImpl.getItemCode(),
				dictItemTempModelImpl.getDictCollectionId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_IC_DCI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_IC_DCI, args,
			dictItemTempModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DictItemTempModelImpl dictItemTempModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dictItemTempModelImpl.getUuid(),
					dictItemTempModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dictItemTempModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictItemTempModelImpl.getOriginalUuid(),
					dictItemTempModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dictItemTempModelImpl.getItemCode(),
					dictItemTempModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE, args);
		}

		if ((dictItemTempModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_DICTITEMCODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictItemTempModelImpl.getOriginalItemCode(),
					dictItemTempModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dictItemTempModelImpl.getItemCode(),
					dictItemTempModelImpl.getDictCollectionId(),
					dictItemTempModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
				args);
		}

		if ((dictItemTempModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictItemTempModelImpl.getOriginalItemCode(),
					dictItemTempModelImpl.getOriginalDictCollectionId(),
					dictItemTempModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTITEMCODE_DICTCOLLECTIONID,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dictItemTempModelImpl.getItemCode(),
					dictItemTempModelImpl.getDictCollectionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_IC_DCI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_IC_DCI, args);
		}

		if ((dictItemTempModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_IC_DCI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictItemTempModelImpl.getOriginalItemCode(),
					dictItemTempModelImpl.getOriginalDictCollectionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_IC_DCI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_IC_DCI, args);
		}
	}

	/**
	 * Creates a new dict item temp with the primary key. Does not add the dict item temp to the database.
	 *
	 * @param dictItemId the primary key for the new dict item temp
	 * @return the new dict item temp
	 */
	@Override
	public DictItemTemp create(long dictItemId) {
		DictItemTemp dictItemTemp = new DictItemTempImpl();

		dictItemTemp.setNew(true);
		dictItemTemp.setPrimaryKey(dictItemId);

		String uuid = PortalUUIDUtil.generate();

		dictItemTemp.setUuid(uuid);

		dictItemTemp.setCompanyId(companyProvider.getCompanyId());

		return dictItemTemp;
	}

	/**
	 * Removes the dict item temp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dictItemId the primary key of the dict item temp
	 * @return the dict item temp that was removed
	 * @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp remove(long dictItemId)
		throws NoSuchDictItemTempException {
		return remove((Serializable)dictItemId);
	}

	/**
	 * Removes the dict item temp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dict item temp
	 * @return the dict item temp that was removed
	 * @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp remove(Serializable primaryKey)
		throws NoSuchDictItemTempException {
		Session session = null;

		try {
			session = openSession();

			DictItemTemp dictItemTemp = (DictItemTemp)session.get(DictItemTempImpl.class,
					primaryKey);

			if (dictItemTemp == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDictItemTempException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dictItemTemp);
		}
		catch (NoSuchDictItemTempException nsee) {
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
	protected DictItemTemp removeImpl(DictItemTemp dictItemTemp) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dictItemTemp)) {
				dictItemTemp = (DictItemTemp)session.get(DictItemTempImpl.class,
						dictItemTemp.getPrimaryKeyObj());
			}

			if (dictItemTemp != null) {
				session.delete(dictItemTemp);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dictItemTemp != null) {
			clearCache(dictItemTemp);
		}

		return dictItemTemp;
	}

	@Override
	public DictItemTemp updateImpl(DictItemTemp dictItemTemp) {
		boolean isNew = dictItemTemp.isNew();

		if (!(dictItemTemp instanceof DictItemTempModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dictItemTemp.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dictItemTemp);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dictItemTemp proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DictItemTemp implementation " +
				dictItemTemp.getClass());
		}

		DictItemTempModelImpl dictItemTempModelImpl = (DictItemTempModelImpl)dictItemTemp;

		if (Validator.isNull(dictItemTemp.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dictItemTemp.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dictItemTemp.getCreateDate() == null)) {
			if (serviceContext == null) {
				dictItemTemp.setCreateDate(now);
			}
			else {
				dictItemTemp.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dictItemTempModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dictItemTemp.setModifiedDate(now);
			}
			else {
				dictItemTemp.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dictItemTemp.isNew()) {
				session.save(dictItemTemp);

				dictItemTemp.setNew(false);
			}
			else {
				dictItemTemp = (DictItemTemp)session.merge(dictItemTemp);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DictItemTempModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dictItemTempModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					dictItemTempModelImpl.getUuid(),
					dictItemTempModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { dictItemTempModelImpl.getDictCollectionId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID,
				args);

			args = new Object[] {
					dictItemTempModelImpl.getDictCollectionId(),
					dictItemTempModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMBYGROUP,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMBYGROUP,
				args);

			args = new Object[] { dictItemTempModelImpl.getParentItemId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTITEMID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID,
				args);

			args = new Object[] {
					dictItemTempModelImpl.getGroupId(),
					dictItemTempModelImpl.getDictCollectionId(),
					dictItemTempModelImpl.getParentItemId(),
					dictItemTempModelImpl.getLevel()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTITEMID_LEVEL,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID_LEVEL,
				args);

			args = new Object[] {
					dictItemTempModelImpl.getDictCollectionId(),
					dictItemTempModelImpl.getParentItemId()
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
			if ((dictItemTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemTempModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dictItemTempModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dictItemTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemTempModelImpl.getOriginalUuid(),
						dictItemTempModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dictItemTempModelImpl.getUuid(),
						dictItemTempModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((dictItemTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemTempModelImpl.getOriginalDictCollectionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID,
					args);

				args = new Object[] { dictItemTempModelImpl.getDictCollectionId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID,
					args);
			}

			if ((dictItemTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMBYGROUP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemTempModelImpl.getOriginalDictCollectionId(),
						dictItemTempModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMBYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMBYGROUP,
					args);

				args = new Object[] {
						dictItemTempModelImpl.getDictCollectionId(),
						dictItemTempModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTITEMBYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTITEMBYGROUP,
					args);
			}

			if ((dictItemTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemTempModelImpl.getOriginalParentItemId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTITEMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID,
					args);

				args = new Object[] { dictItemTempModelImpl.getParentItemId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTITEMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID,
					args);
			}

			if ((dictItemTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID_LEVEL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemTempModelImpl.getOriginalGroupId(),
						dictItemTempModelImpl.getOriginalDictCollectionId(),
						dictItemTempModelImpl.getOriginalParentItemId(),
						dictItemTempModelImpl.getOriginalLevel()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTITEMID_LEVEL,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID_LEVEL,
					args);

				args = new Object[] {
						dictItemTempModelImpl.getGroupId(),
						dictItemTempModelImpl.getDictCollectionId(),
						dictItemTempModelImpl.getParentItemId(),
						dictItemTempModelImpl.getLevel()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTITEMID_LEVEL,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTITEMID_LEVEL,
					args);
			}

			if ((dictItemTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID_PARENTITEMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemTempModelImpl.getOriginalDictCollectionId(),
						dictItemTempModelImpl.getOriginalParentItemId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID_PARENTITEMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID_PARENTITEMID,
					args);

				args = new Object[] {
						dictItemTempModelImpl.getDictCollectionId(),
						dictItemTempModelImpl.getParentItemId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONID_PARENTITEMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONID_PARENTITEMID,
					args);
			}
		}

		entityCache.putResult(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
			DictItemTempImpl.class, dictItemTemp.getPrimaryKey(), dictItemTemp,
			false);

		clearUniqueFindersCache(dictItemTempModelImpl, false);
		cacheUniqueFindersCache(dictItemTempModelImpl);

		dictItemTemp.resetOriginalValues();

		return dictItemTemp;
	}

	/**
	 * Returns the dict item temp with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict item temp
	 * @return the dict item temp
	 * @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDictItemTempException {
		DictItemTemp dictItemTemp = fetchByPrimaryKey(primaryKey);

		if (dictItemTemp == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDictItemTempException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dictItemTemp;
	}

	/**
	 * Returns the dict item temp with the primary key or throws a {@link NoSuchDictItemTempException} if it could not be found.
	 *
	 * @param dictItemId the primary key of the dict item temp
	 * @return the dict item temp
	 * @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp findByPrimaryKey(long dictItemId)
		throws NoSuchDictItemTempException {
		return findByPrimaryKey((Serializable)dictItemId);
	}

	/**
	 * Returns the dict item temp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict item temp
	 * @return the dict item temp, or <code>null</code> if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
				DictItemTempImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DictItemTemp dictItemTemp = (DictItemTemp)serializable;

		if (dictItemTemp == null) {
			Session session = null;

			try {
				session = openSession();

				dictItemTemp = (DictItemTemp)session.get(DictItemTempImpl.class,
						primaryKey);

				if (dictItemTemp != null) {
					cacheResult(dictItemTemp);
				}
				else {
					entityCache.putResult(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
						DictItemTempImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
					DictItemTempImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dictItemTemp;
	}

	/**
	 * Returns the dict item temp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dictItemId the primary key of the dict item temp
	 * @return the dict item temp, or <code>null</code> if a dict item temp with the primary key could not be found
	 */
	@Override
	public DictItemTemp fetchByPrimaryKey(long dictItemId) {
		return fetchByPrimaryKey((Serializable)dictItemId);
	}

	@Override
	public Map<Serializable, DictItemTemp> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DictItemTemp> map = new HashMap<Serializable, DictItemTemp>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DictItemTemp dictItemTemp = fetchByPrimaryKey(primaryKey);

			if (dictItemTemp != null) {
				map.put(primaryKey, dictItemTemp);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
					DictItemTempImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DictItemTemp)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DICTITEMTEMP_WHERE_PKS_IN);

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

			for (DictItemTemp dictItemTemp : (List<DictItemTemp>)q.list()) {
				map.put(dictItemTemp.getPrimaryKeyObj(), dictItemTemp);

				cacheResult(dictItemTemp);

				uncachedPrimaryKeys.remove(dictItemTemp.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DictItemTempModelImpl.ENTITY_CACHE_ENABLED,
					DictItemTempImpl.class, primaryKey, nullModel);
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
	 * Returns all the dict item temps.
	 *
	 * @return the dict item temps
	 */
	@Override
	public List<DictItemTemp> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item temps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @return the range of dict item temps
	 */
	@Override
	public List<DictItemTemp> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item temps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dict item temps
	 */
	@Override
	public List<DictItemTemp> findAll(int start, int end,
		OrderByComparator<DictItemTemp> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item temps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict item temps
	 * @param end the upper bound of the range of dict item temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dict item temps
	 */
	@Override
	public List<DictItemTemp> findAll(int start, int end,
		OrderByComparator<DictItemTemp> orderByComparator,
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

		List<DictItemTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemTemp>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DICTITEMTEMP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DICTITEMTEMP;

				if (pagination) {
					sql = sql.concat(DictItemTempModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemTemp>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the dict item temps from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DictItemTemp dictItemTemp : findAll()) {
			remove(dictItemTemp);
		}
	}

	/**
	 * Returns the number of dict item temps.
	 *
	 * @return the number of dict item temps
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DICTITEMTEMP);

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
		return DictItemTempModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dict item temp persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DictItemTempImpl.class.getName());
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

	private static final String _SQL_SELECT_DICTITEMTEMP = "SELECT dictItemTemp FROM DictItemTemp dictItemTemp";
	private static final String _SQL_SELECT_DICTITEMTEMP_WHERE_PKS_IN = "SELECT dictItemTemp FROM DictItemTemp dictItemTemp WHERE dictItemId IN (";
	private static final String _SQL_SELECT_DICTITEMTEMP_WHERE = "SELECT dictItemTemp FROM DictItemTemp dictItemTemp WHERE ";
	private static final String _SQL_COUNT_DICTITEMTEMP = "SELECT COUNT(dictItemTemp) FROM DictItemTemp dictItemTemp";
	private static final String _SQL_COUNT_DICTITEMTEMP_WHERE = "SELECT COUNT(dictItemTemp) FROM DictItemTemp dictItemTemp WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dictItemTemp.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DictItemTemp exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DictItemTemp exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DictItemTempPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}