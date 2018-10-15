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

import org.opencps.synchronization.exception.NoSuchDictCollectionTempException;
import org.opencps.synchronization.model.DictCollectionTemp;
import org.opencps.synchronization.model.impl.DictCollectionTempImpl;
import org.opencps.synchronization.model.impl.DictCollectionTempModelImpl;
import org.opencps.synchronization.service.persistence.DictCollectionTempPersistence;

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
 * The persistence implementation for the dict collection temp service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see DictCollectionTempPersistence
 * @see org.opencps.synchronization.service.persistence.DictCollectionTempUtil
 * @generated
 */
@ProviderType
public class DictCollectionTempPersistenceImpl extends BasePersistenceImpl<DictCollectionTemp>
	implements DictCollectionTempPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DictCollectionTempUtil} to access the dict collection temp persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DictCollectionTempImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DictCollectionTempModelImpl.UUID_COLUMN_BITMASK |
			DictCollectionTempModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dict collection temps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict collection temps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @return the range of matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict collection temps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByUuid(String uuid, int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict collection temps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByUuid(String uuid, int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator,
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

		List<DictCollectionTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictCollectionTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictCollectionTemp dictCollectionTemp : list) {
					if (!Objects.equals(uuid, dictCollectionTemp.getUuid())) {
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

			query.append(_SQL_SELECT_DICTCOLLECTIONTEMP_WHERE);

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
				query.append(DictCollectionTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictCollectionTemp>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictCollectionTemp>)QueryUtil.list(q,
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
	 * Returns the first dict collection temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection temp
	 * @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp findByUuid_First(String uuid,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = fetchByUuid_First(uuid,
				orderByComparator);

		if (dictCollectionTemp != null) {
			return dictCollectionTemp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDictCollectionTempException(msg.toString());
	}

	/**
	 * Returns the first dict collection temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp fetchByUuid_First(String uuid,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		List<DictCollectionTemp> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict collection temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection temp
	 * @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp findByUuid_Last(String uuid,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = fetchByUuid_Last(uuid,
				orderByComparator);

		if (dictCollectionTemp != null) {
			return dictCollectionTemp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDictCollectionTempException(msg.toString());
	}

	/**
	 * Returns the last dict collection temp in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp fetchByUuid_Last(String uuid,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DictCollectionTemp> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict collection temps before and after the current dict collection temp in the ordered set where uuid = &#63;.
	 *
	 * @param dictCollectionId the primary key of the current dict collection temp
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict collection temp
	 * @throws NoSuchDictCollectionTempException if a dict collection temp with the primary key could not be found
	 */
	@Override
	public DictCollectionTemp[] findByUuid_PrevAndNext(long dictCollectionId,
		String uuid, OrderByComparator<DictCollectionTemp> orderByComparator)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = findByPrimaryKey(dictCollectionId);

		Session session = null;

		try {
			session = openSession();

			DictCollectionTemp[] array = new DictCollectionTempImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dictCollectionTemp, uuid,
					orderByComparator, true);

			array[1] = dictCollectionTemp;

			array[2] = getByUuid_PrevAndNext(session, dictCollectionTemp, uuid,
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

	protected DictCollectionTemp getByUuid_PrevAndNext(Session session,
		DictCollectionTemp dictCollectionTemp, String uuid,
		OrderByComparator<DictCollectionTemp> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DICTCOLLECTIONTEMP_WHERE);

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
			query.append(DictCollectionTempModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictCollectionTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictCollectionTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict collection temps where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DictCollectionTemp dictCollectionTemp : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictCollectionTemp);
		}
	}

	/**
	 * Returns the number of dict collection temps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dict collection temps
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DICTCOLLECTIONTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dictCollectionTemp.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dictCollectionTemp.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dictCollectionTemp.uuid IS NULL OR dictCollectionTemp.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionTempImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DictCollectionTempModelImpl.UUID_COLUMN_BITMASK |
			DictCollectionTempModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict collection temp where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictCollectionTempException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dict collection temp
	 * @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp findByUUID_G(String uuid, long groupId)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = fetchByUUID_G(uuid, groupId);

		if (dictCollectionTemp == null) {
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

			throw new NoSuchDictCollectionTempException(msg.toString());
		}

		return dictCollectionTemp;
	}

	/**
	 * Returns the dict collection temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dict collection temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DictCollectionTemp) {
			DictCollectionTemp dictCollectionTemp = (DictCollectionTemp)result;

			if (!Objects.equals(uuid, dictCollectionTemp.getUuid()) ||
					(groupId != dictCollectionTemp.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTCOLLECTIONTEMP_WHERE);

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

				List<DictCollectionTemp> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DictCollectionTemp dictCollectionTemp = list.get(0);

					result = dictCollectionTemp;

					cacheResult(dictCollectionTemp);
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
			return (DictCollectionTemp)result;
		}
	}

	/**
	 * Removes the dict collection temp where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dict collection temp that was removed
	 */
	@Override
	public DictCollectionTemp removeByUUID_G(String uuid, long groupId)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = findByUUID_G(uuid, groupId);

		return remove(dictCollectionTemp);
	}

	/**
	 * Returns the number of dict collection temps where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dict collection temps
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTCOLLECTIONTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dictCollectionTemp.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dictCollectionTemp.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dictCollectionTemp.uuid IS NULL OR dictCollectionTemp.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dictCollectionTemp.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DictCollectionTempModelImpl.UUID_COLUMN_BITMASK |
			DictCollectionTempModelImpl.COMPANYID_COLUMN_BITMASK |
			DictCollectionTempModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict collection temps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict collection temps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @return the range of matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict collection temps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict collection temps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator,
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

		List<DictCollectionTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictCollectionTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictCollectionTemp dictCollectionTemp : list) {
					if (!Objects.equals(uuid, dictCollectionTemp.getUuid()) ||
							(companyId != dictCollectionTemp.getCompanyId())) {
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

			query.append(_SQL_SELECT_DICTCOLLECTIONTEMP_WHERE);

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
				query.append(DictCollectionTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictCollectionTemp>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictCollectionTemp>)QueryUtil.list(q,
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
	 * Returns the first dict collection temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection temp
	 * @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (dictCollectionTemp != null) {
			return dictCollectionTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDictCollectionTempException(msg.toString());
	}

	/**
	 * Returns the first dict collection temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		List<DictCollectionTemp> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict collection temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection temp
	 * @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (dictCollectionTemp != null) {
			return dictCollectionTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDictCollectionTempException(msg.toString());
	}

	/**
	 * Returns the last dict collection temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DictCollectionTemp> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict collection temps before and after the current dict collection temp in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dictCollectionId the primary key of the current dict collection temp
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict collection temp
	 * @throws NoSuchDictCollectionTempException if a dict collection temp with the primary key could not be found
	 */
	@Override
	public DictCollectionTemp[] findByUuid_C_PrevAndNext(
		long dictCollectionId, String uuid, long companyId,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = findByPrimaryKey(dictCollectionId);

		Session session = null;

		try {
			session = openSession();

			DictCollectionTemp[] array = new DictCollectionTempImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dictCollectionTemp,
					uuid, companyId, orderByComparator, true);

			array[1] = dictCollectionTemp;

			array[2] = getByUuid_C_PrevAndNext(session, dictCollectionTemp,
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

	protected DictCollectionTemp getByUuid_C_PrevAndNext(Session session,
		DictCollectionTemp dictCollectionTemp, String uuid, long companyId,
		OrderByComparator<DictCollectionTemp> orderByComparator,
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

		query.append(_SQL_SELECT_DICTCOLLECTIONTEMP_WHERE);

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
			query.append(DictCollectionTempModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictCollectionTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictCollectionTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict collection temps where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DictCollectionTemp dictCollectionTemp : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictCollectionTemp);
		}
	}

	/**
	 * Returns the number of dict collection temps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dict collection temps
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTCOLLECTIONTEMP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dictCollectionTemp.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dictCollectionTemp.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dictCollectionTemp.uuid IS NULL OR dictCollectionTemp.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dictCollectionTemp.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE = new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionTempImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_dictCollectionCode",
			new String[] { String.class.getName(), Long.class.getName() },
			DictCollectionTempModelImpl.COLLECTIONCODE_COLUMN_BITMASK |
			DictCollectionTempModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONCODE = new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_dictCollectionCode",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dict collection temp where collectionCode = &#63; and groupId = &#63; or throws a {@link NoSuchDictCollectionTempException} if it could not be found.
	 *
	 * @param collectionCode the collection code
	 * @param groupId the group ID
	 * @return the matching dict collection temp
	 * @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp findByF_dictCollectionCode(
		String collectionCode, long groupId)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = fetchByF_dictCollectionCode(collectionCode,
				groupId);

		if (dictCollectionTemp == null) {
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

			throw new NoSuchDictCollectionTempException(msg.toString());
		}

		return dictCollectionTemp;
	}

	/**
	 * Returns the dict collection temp where collectionCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param collectionCode the collection code
	 * @param groupId the group ID
	 * @return the matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp fetchByF_dictCollectionCode(
		String collectionCode, long groupId) {
		return fetchByF_dictCollectionCode(collectionCode, groupId, true);
	}

	/**
	 * Returns the dict collection temp where collectionCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param collectionCode the collection code
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp fetchByF_dictCollectionCode(
		String collectionCode, long groupId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { collectionCode, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE,
					finderArgs, this);
		}

		if (result instanceof DictCollectionTemp) {
			DictCollectionTemp dictCollectionTemp = (DictCollectionTemp)result;

			if (!Objects.equals(collectionCode,
						dictCollectionTemp.getCollectionCode()) ||
					(groupId != dictCollectionTemp.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DICTCOLLECTIONTEMP_WHERE);

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

				List<DictCollectionTemp> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictCollectionTempPersistenceImpl.fetchByF_dictCollectionCode(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictCollectionTemp dictCollectionTemp = list.get(0);

					result = dictCollectionTemp;

					cacheResult(dictCollectionTemp);
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
			return (DictCollectionTemp)result;
		}
	}

	/**
	 * Removes the dict collection temp where collectionCode = &#63; and groupId = &#63; from the database.
	 *
	 * @param collectionCode the collection code
	 * @param groupId the group ID
	 * @return the dict collection temp that was removed
	 */
	@Override
	public DictCollectionTemp removeByF_dictCollectionCode(
		String collectionCode, long groupId)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = findByF_dictCollectionCode(collectionCode,
				groupId);

		return remove(dictCollectionTemp);
	}

	/**
	 * Returns the number of dict collection temps where collectionCode = &#63; and groupId = &#63;.
	 *
	 * @param collectionCode the collection code
	 * @param groupId the group ID
	 * @return the number of matching dict collection temps
	 */
	@Override
	public int countByF_dictCollectionCode(String collectionCode, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONCODE;

		Object[] finderArgs = new Object[] { collectionCode, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTCOLLECTIONTEMP_WHERE);

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
		"dictCollectionTemp.collectionCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONCODE_COLLECTIONCODE_2 =
		"dictCollectionTemp.collectionCode = ? AND ";
	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONCODE_COLLECTIONCODE_3 =
		"(dictCollectionTemp.collectionCode IS NULL OR dictCollectionTemp.collectionCode = '') AND ";
	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONCODE_GROUPID_2 = "dictCollectionTemp.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTCOLLECTIONBYGROUP =
		new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_dictCollectionByGroup",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONBYGROUP =
		new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_dictCollectionByGroup",
			new String[] { Long.class.getName() },
			DictCollectionTempModelImpl.GROUPID_COLUMN_BITMASK |
			DictCollectionTempModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONBYGROUP = new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_dictCollectionByGroup",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dict collection temps where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByF_dictCollectionByGroup(long groupId) {
		return findByF_dictCollectionByGroup(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict collection temps where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @return the range of matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByF_dictCollectionByGroup(
		long groupId, int start, int end) {
		return findByF_dictCollectionByGroup(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict collection temps where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByF_dictCollectionByGroup(
		long groupId, int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		return findByF_dictCollectionByGroup(groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict collection temps where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByF_dictCollectionByGroup(
		long groupId, int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator,
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

		List<DictCollectionTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictCollectionTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictCollectionTemp dictCollectionTemp : list) {
					if ((groupId != dictCollectionTemp.getGroupId())) {
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

			query.append(_SQL_SELECT_DICTCOLLECTIONTEMP_WHERE);

			query.append(_FINDER_COLUMN_F_DICTCOLLECTIONBYGROUP_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictCollectionTempModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<DictCollectionTemp>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictCollectionTemp>)QueryUtil.list(q,
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
	 * Returns the first dict collection temp in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection temp
	 * @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp findByF_dictCollectionByGroup_First(
		long groupId, OrderByComparator<DictCollectionTemp> orderByComparator)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = fetchByF_dictCollectionByGroup_First(groupId,
				orderByComparator);

		if (dictCollectionTemp != null) {
			return dictCollectionTemp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictCollectionTempException(msg.toString());
	}

	/**
	 * Returns the first dict collection temp in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp fetchByF_dictCollectionByGroup_First(
		long groupId, OrderByComparator<DictCollectionTemp> orderByComparator) {
		List<DictCollectionTemp> list = findByF_dictCollectionByGroup(groupId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict collection temp in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection temp
	 * @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp findByF_dictCollectionByGroup_Last(long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = fetchByF_dictCollectionByGroup_Last(groupId,
				orderByComparator);

		if (dictCollectionTemp != null) {
			return dictCollectionTemp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictCollectionTempException(msg.toString());
	}

	/**
	 * Returns the last dict collection temp in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp fetchByF_dictCollectionByGroup_Last(
		long groupId, OrderByComparator<DictCollectionTemp> orderByComparator) {
		int count = countByF_dictCollectionByGroup(groupId);

		if (count == 0) {
			return null;
		}

		List<DictCollectionTemp> list = findByF_dictCollectionByGroup(groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict collection temps before and after the current dict collection temp in the ordered set where groupId = &#63;.
	 *
	 * @param dictCollectionId the primary key of the current dict collection temp
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict collection temp
	 * @throws NoSuchDictCollectionTempException if a dict collection temp with the primary key could not be found
	 */
	@Override
	public DictCollectionTemp[] findByF_dictCollectionByGroup_PrevAndNext(
		long dictCollectionId, long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = findByPrimaryKey(dictCollectionId);

		Session session = null;

		try {
			session = openSession();

			DictCollectionTemp[] array = new DictCollectionTempImpl[3];

			array[0] = getByF_dictCollectionByGroup_PrevAndNext(session,
					dictCollectionTemp, groupId, orderByComparator, true);

			array[1] = dictCollectionTemp;

			array[2] = getByF_dictCollectionByGroup_PrevAndNext(session,
					dictCollectionTemp, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DictCollectionTemp getByF_dictCollectionByGroup_PrevAndNext(
		Session session, DictCollectionTemp dictCollectionTemp, long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DICTCOLLECTIONTEMP_WHERE);

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
			query.append(DictCollectionTempModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictCollectionTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictCollectionTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict collection temps where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_dictCollectionByGroup(long groupId) {
		for (DictCollectionTemp dictCollectionTemp : findByF_dictCollectionByGroup(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictCollectionTemp);
		}
	}

	/**
	 * Returns the number of dict collection temps where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching dict collection temps
	 */
	@Override
	public int countByF_dictCollectionByGroup(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONBYGROUP;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DICTCOLLECTIONTEMP_WHERE);

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
		"dictCollectionTemp.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTCOLLECTIONNEWERTHAN =
		new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED,
			DictCollectionTempImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_dictCollectionNewerThan",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_DICTCOLLECTIONNEWERTHAN =
		new FinderPath(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_dictCollectionNewerThan",
			new String[] { Date.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict collection temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @return the matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId) {
		return findByF_dictCollectionNewerThan(modifiedDate, groupId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict collection temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @return the range of matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId, int start, int end) {
		return findByF_dictCollectionNewerThan(modifiedDate, groupId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the dict collection temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		return findByF_dictCollectionNewerThan(modifiedDate, groupId, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict collection temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DICTCOLLECTIONNEWERTHAN;
		finderArgs = new Object[] {
				_getTime(modifiedDate), groupId,
				
				start, end, orderByComparator
			};

		List<DictCollectionTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictCollectionTemp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictCollectionTemp dictCollectionTemp : list) {
					if ((modifiedDate.getTime() > dictCollectionTemp.getModifiedDate()
																		.getTime()) ||
							(groupId != dictCollectionTemp.getGroupId())) {
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

			query.append(_SQL_SELECT_DICTCOLLECTIONTEMP_WHERE);

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
				query.append(DictCollectionTempModelImpl.ORDER_BY_JPQL);
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
					list = (List<DictCollectionTemp>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictCollectionTemp>)QueryUtil.list(q,
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
	 * Returns the first dict collection temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection temp
	 * @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp findByF_dictCollectionNewerThan_First(
		Date modifiedDate, long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = fetchByF_dictCollectionNewerThan_First(modifiedDate,
				groupId, orderByComparator);

		if (dictCollectionTemp != null) {
			return dictCollectionTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictCollectionTempException(msg.toString());
	}

	/**
	 * Returns the first dict collection temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp fetchByF_dictCollectionNewerThan_First(
		Date modifiedDate, long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		List<DictCollectionTemp> list = findByF_dictCollectionNewerThan(modifiedDate,
				groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict collection temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection temp
	 * @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp findByF_dictCollectionNewerThan_Last(
		Date modifiedDate, long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = fetchByF_dictCollectionNewerThan_Last(modifiedDate,
				groupId, orderByComparator);

		if (dictCollectionTemp != null) {
			return dictCollectionTemp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDictCollectionTempException(msg.toString());
	}

	/**
	 * Returns the last dict collection temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	 */
	@Override
	public DictCollectionTemp fetchByF_dictCollectionNewerThan_Last(
		Date modifiedDate, long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		int count = countByF_dictCollectionNewerThan(modifiedDate, groupId);

		if (count == 0) {
			return null;
		}

		List<DictCollectionTemp> list = findByF_dictCollectionNewerThan(modifiedDate,
				groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict collection temps before and after the current dict collection temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param dictCollectionId the primary key of the current dict collection temp
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict collection temp
	 * @throws NoSuchDictCollectionTempException if a dict collection temp with the primary key could not be found
	 */
	@Override
	public DictCollectionTemp[] findByF_dictCollectionNewerThan_PrevAndNext(
		long dictCollectionId, Date modifiedDate, long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = findByPrimaryKey(dictCollectionId);

		Session session = null;

		try {
			session = openSession();

			DictCollectionTemp[] array = new DictCollectionTempImpl[3];

			array[0] = getByF_dictCollectionNewerThan_PrevAndNext(session,
					dictCollectionTemp, modifiedDate, groupId,
					orderByComparator, true);

			array[1] = dictCollectionTemp;

			array[2] = getByF_dictCollectionNewerThan_PrevAndNext(session,
					dictCollectionTemp, modifiedDate, groupId,
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

	protected DictCollectionTemp getByF_dictCollectionNewerThan_PrevAndNext(
		Session session, DictCollectionTemp dictCollectionTemp,
		Date modifiedDate, long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator,
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

		query.append(_SQL_SELECT_DICTCOLLECTIONTEMP_WHERE);

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
			query.append(DictCollectionTempModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dictCollectionTemp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictCollectionTemp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict collection temps where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_dictCollectionNewerThan(Date modifiedDate,
		long groupId) {
		for (DictCollectionTemp dictCollectionTemp : findByF_dictCollectionNewerThan(
				modifiedDate, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(dictCollectionTemp);
		}
	}

	/**
	 * Returns the number of dict collection temps where modifiedDate &ge; &#63; and groupId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param groupId the group ID
	 * @return the number of matching dict collection temps
	 */
	@Override
	public int countByF_dictCollectionNewerThan(Date modifiedDate, long groupId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_DICTCOLLECTIONNEWERTHAN;

		Object[] finderArgs = new Object[] { _getTime(modifiedDate), groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTCOLLECTIONTEMP_WHERE);

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
		"dictCollectionTemp.modifiedDate IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONNEWERTHAN_MODIFIEDDATE_2 =
		"dictCollectionTemp.modifiedDate >= ? AND ";
	private static final String _FINDER_COLUMN_F_DICTCOLLECTIONNEWERTHAN_GROUPID_2 =
		"dictCollectionTemp.groupId = ?";

	public DictCollectionTempPersistenceImpl() {
		setModelClass(DictCollectionTemp.class);

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
	 * Caches the dict collection temp in the entity cache if it is enabled.
	 *
	 * @param dictCollectionTemp the dict collection temp
	 */
	@Override
	public void cacheResult(DictCollectionTemp dictCollectionTemp) {
		entityCache.putResult(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempImpl.class, dictCollectionTemp.getPrimaryKey(),
			dictCollectionTemp);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				dictCollectionTemp.getUuid(), dictCollectionTemp.getGroupId()
			}, dictCollectionTemp);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE,
			new Object[] {
				dictCollectionTemp.getCollectionCode(),
				dictCollectionTemp.getGroupId()
			}, dictCollectionTemp);

		dictCollectionTemp.resetOriginalValues();
	}

	/**
	 * Caches the dict collection temps in the entity cache if it is enabled.
	 *
	 * @param dictCollectionTemps the dict collection temps
	 */
	@Override
	public void cacheResult(List<DictCollectionTemp> dictCollectionTemps) {
		for (DictCollectionTemp dictCollectionTemp : dictCollectionTemps) {
			if (entityCache.getResult(
						DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
						DictCollectionTempImpl.class,
						dictCollectionTemp.getPrimaryKey()) == null) {
				cacheResult(dictCollectionTemp);
			}
			else {
				dictCollectionTemp.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dict collection temps.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DictCollectionTempImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dict collection temp.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DictCollectionTemp dictCollectionTemp) {
		entityCache.removeResult(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempImpl.class, dictCollectionTemp.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DictCollectionTempModelImpl)dictCollectionTemp,
			true);
	}

	@Override
	public void clearCache(List<DictCollectionTemp> dictCollectionTemps) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DictCollectionTemp dictCollectionTemp : dictCollectionTemps) {
			entityCache.removeResult(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
				DictCollectionTempImpl.class, dictCollectionTemp.getPrimaryKey());

			clearUniqueFindersCache((DictCollectionTempModelImpl)dictCollectionTemp,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		DictCollectionTempModelImpl dictCollectionTempModelImpl) {
		Object[] args = new Object[] {
				dictCollectionTempModelImpl.getUuid(),
				dictCollectionTempModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dictCollectionTempModelImpl, false);

		args = new Object[] {
				dictCollectionTempModelImpl.getCollectionCode(),
				dictCollectionTempModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONCODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE, args,
			dictCollectionTempModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DictCollectionTempModelImpl dictCollectionTempModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dictCollectionTempModelImpl.getUuid(),
					dictCollectionTempModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dictCollectionTempModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictCollectionTempModelImpl.getOriginalUuid(),
					dictCollectionTempModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dictCollectionTempModelImpl.getCollectionCode(),
					dictCollectionTempModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONCODE,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE,
				args);
		}

		if ((dictCollectionTempModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictCollectionTempModelImpl.getOriginalCollectionCode(),
					dictCollectionTempModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONCODE,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DICTCOLLECTIONCODE,
				args);
		}
	}

	/**
	 * Creates a new dict collection temp with the primary key. Does not add the dict collection temp to the database.
	 *
	 * @param dictCollectionId the primary key for the new dict collection temp
	 * @return the new dict collection temp
	 */
	@Override
	public DictCollectionTemp create(long dictCollectionId) {
		DictCollectionTemp dictCollectionTemp = new DictCollectionTempImpl();

		dictCollectionTemp.setNew(true);
		dictCollectionTemp.setPrimaryKey(dictCollectionId);

		String uuid = PortalUUIDUtil.generate();

		dictCollectionTemp.setUuid(uuid);

		dictCollectionTemp.setCompanyId(companyProvider.getCompanyId());

		return dictCollectionTemp;
	}

	/**
	 * Removes the dict collection temp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dictCollectionId the primary key of the dict collection temp
	 * @return the dict collection temp that was removed
	 * @throws NoSuchDictCollectionTempException if a dict collection temp with the primary key could not be found
	 */
	@Override
	public DictCollectionTemp remove(long dictCollectionId)
		throws NoSuchDictCollectionTempException {
		return remove((Serializable)dictCollectionId);
	}

	/**
	 * Removes the dict collection temp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dict collection temp
	 * @return the dict collection temp that was removed
	 * @throws NoSuchDictCollectionTempException if a dict collection temp with the primary key could not be found
	 */
	@Override
	public DictCollectionTemp remove(Serializable primaryKey)
		throws NoSuchDictCollectionTempException {
		Session session = null;

		try {
			session = openSession();

			DictCollectionTemp dictCollectionTemp = (DictCollectionTemp)session.get(DictCollectionTempImpl.class,
					primaryKey);

			if (dictCollectionTemp == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDictCollectionTempException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dictCollectionTemp);
		}
		catch (NoSuchDictCollectionTempException nsee) {
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
	protected DictCollectionTemp removeImpl(
		DictCollectionTemp dictCollectionTemp) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dictCollectionTemp)) {
				dictCollectionTemp = (DictCollectionTemp)session.get(DictCollectionTempImpl.class,
						dictCollectionTemp.getPrimaryKeyObj());
			}

			if (dictCollectionTemp != null) {
				session.delete(dictCollectionTemp);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dictCollectionTemp != null) {
			clearCache(dictCollectionTemp);
		}

		return dictCollectionTemp;
	}

	@Override
	public DictCollectionTemp updateImpl(DictCollectionTemp dictCollectionTemp) {
		boolean isNew = dictCollectionTemp.isNew();

		if (!(dictCollectionTemp instanceof DictCollectionTempModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dictCollectionTemp.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dictCollectionTemp);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dictCollectionTemp proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DictCollectionTemp implementation " +
				dictCollectionTemp.getClass());
		}

		DictCollectionTempModelImpl dictCollectionTempModelImpl = (DictCollectionTempModelImpl)dictCollectionTemp;

		if (Validator.isNull(dictCollectionTemp.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dictCollectionTemp.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dictCollectionTemp.getCreateDate() == null)) {
			if (serviceContext == null) {
				dictCollectionTemp.setCreateDate(now);
			}
			else {
				dictCollectionTemp.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!dictCollectionTempModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dictCollectionTemp.setModifiedDate(now);
			}
			else {
				dictCollectionTemp.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dictCollectionTemp.isNew()) {
				session.save(dictCollectionTemp);

				dictCollectionTemp.setNew(false);
			}
			else {
				dictCollectionTemp = (DictCollectionTemp)session.merge(dictCollectionTemp);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DictCollectionTempModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dictCollectionTempModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					dictCollectionTempModelImpl.getUuid(),
					dictCollectionTempModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { dictCollectionTempModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONBYGROUP,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONBYGROUP,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dictCollectionTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictCollectionTempModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dictCollectionTempModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dictCollectionTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictCollectionTempModelImpl.getOriginalUuid(),
						dictCollectionTempModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dictCollectionTempModelImpl.getUuid(),
						dictCollectionTempModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((dictCollectionTempModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONBYGROUP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictCollectionTempModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONBYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONBYGROUP,
					args);

				args = new Object[] { dictCollectionTempModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DICTCOLLECTIONBYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DICTCOLLECTIONBYGROUP,
					args);
			}
		}

		entityCache.putResult(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
			DictCollectionTempImpl.class, dictCollectionTemp.getPrimaryKey(),
			dictCollectionTemp, false);

		clearUniqueFindersCache(dictCollectionTempModelImpl, false);
		cacheUniqueFindersCache(dictCollectionTempModelImpl);

		dictCollectionTemp.resetOriginalValues();

		return dictCollectionTemp;
	}

	/**
	 * Returns the dict collection temp with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict collection temp
	 * @return the dict collection temp
	 * @throws NoSuchDictCollectionTempException if a dict collection temp with the primary key could not be found
	 */
	@Override
	public DictCollectionTemp findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDictCollectionTempException {
		DictCollectionTemp dictCollectionTemp = fetchByPrimaryKey(primaryKey);

		if (dictCollectionTemp == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDictCollectionTempException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dictCollectionTemp;
	}

	/**
	 * Returns the dict collection temp with the primary key or throws a {@link NoSuchDictCollectionTempException} if it could not be found.
	 *
	 * @param dictCollectionId the primary key of the dict collection temp
	 * @return the dict collection temp
	 * @throws NoSuchDictCollectionTempException if a dict collection temp with the primary key could not be found
	 */
	@Override
	public DictCollectionTemp findByPrimaryKey(long dictCollectionId)
		throws NoSuchDictCollectionTempException {
		return findByPrimaryKey((Serializable)dictCollectionId);
	}

	/**
	 * Returns the dict collection temp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict collection temp
	 * @return the dict collection temp, or <code>null</code> if a dict collection temp with the primary key could not be found
	 */
	@Override
	public DictCollectionTemp fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
				DictCollectionTempImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DictCollectionTemp dictCollectionTemp = (DictCollectionTemp)serializable;

		if (dictCollectionTemp == null) {
			Session session = null;

			try {
				session = openSession();

				dictCollectionTemp = (DictCollectionTemp)session.get(DictCollectionTempImpl.class,
						primaryKey);

				if (dictCollectionTemp != null) {
					cacheResult(dictCollectionTemp);
				}
				else {
					entityCache.putResult(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
						DictCollectionTempImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
					DictCollectionTempImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dictCollectionTemp;
	}

	/**
	 * Returns the dict collection temp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dictCollectionId the primary key of the dict collection temp
	 * @return the dict collection temp, or <code>null</code> if a dict collection temp with the primary key could not be found
	 */
	@Override
	public DictCollectionTemp fetchByPrimaryKey(long dictCollectionId) {
		return fetchByPrimaryKey((Serializable)dictCollectionId);
	}

	@Override
	public Map<Serializable, DictCollectionTemp> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DictCollectionTemp> map = new HashMap<Serializable, DictCollectionTemp>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DictCollectionTemp dictCollectionTemp = fetchByPrimaryKey(primaryKey);

			if (dictCollectionTemp != null) {
				map.put(primaryKey, dictCollectionTemp);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
					DictCollectionTempImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DictCollectionTemp)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DICTCOLLECTIONTEMP_WHERE_PKS_IN);

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

			for (DictCollectionTemp dictCollectionTemp : (List<DictCollectionTemp>)q.list()) {
				map.put(dictCollectionTemp.getPrimaryKeyObj(),
					dictCollectionTemp);

				cacheResult(dictCollectionTemp);

				uncachedPrimaryKeys.remove(dictCollectionTemp.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DictCollectionTempModelImpl.ENTITY_CACHE_ENABLED,
					DictCollectionTempImpl.class, primaryKey, nullModel);
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
	 * Returns all the dict collection temps.
	 *
	 * @return the dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict collection temps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @return the range of dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict collection temps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findAll(int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict collection temps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict collection temps
	 * @param end the upper bound of the range of dict collection temps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dict collection temps
	 */
	@Override
	public List<DictCollectionTemp> findAll(int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator,
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

		List<DictCollectionTemp> list = null;

		if (retrieveFromCache) {
			list = (List<DictCollectionTemp>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DICTCOLLECTIONTEMP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DICTCOLLECTIONTEMP;

				if (pagination) {
					sql = sql.concat(DictCollectionTempModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DictCollectionTemp>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictCollectionTemp>)QueryUtil.list(q,
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
	 * Removes all the dict collection temps from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DictCollectionTemp dictCollectionTemp : findAll()) {
			remove(dictCollectionTemp);
		}
	}

	/**
	 * Returns the number of dict collection temps.
	 *
	 * @return the number of dict collection temps
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DICTCOLLECTIONTEMP);

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
		return DictCollectionTempModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dict collection temp persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DictCollectionTempImpl.class.getName());
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

	private static final String _SQL_SELECT_DICTCOLLECTIONTEMP = "SELECT dictCollectionTemp FROM DictCollectionTemp dictCollectionTemp";
	private static final String _SQL_SELECT_DICTCOLLECTIONTEMP_WHERE_PKS_IN = "SELECT dictCollectionTemp FROM DictCollectionTemp dictCollectionTemp WHERE dictCollectionId IN (";
	private static final String _SQL_SELECT_DICTCOLLECTIONTEMP_WHERE = "SELECT dictCollectionTemp FROM DictCollectionTemp dictCollectionTemp WHERE ";
	private static final String _SQL_COUNT_DICTCOLLECTIONTEMP = "SELECT COUNT(dictCollectionTemp) FROM DictCollectionTemp dictCollectionTemp";
	private static final String _SQL_COUNT_DICTCOLLECTIONTEMP_WHERE = "SELECT COUNT(dictCollectionTemp) FROM DictCollectionTemp dictCollectionTemp WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dictCollectionTemp.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DictCollectionTemp exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DictCollectionTemp exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DictCollectionTempPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}