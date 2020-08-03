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

package org.opencps.dossiermgt.service.persistence.impl;

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

import org.opencps.dossiermgt.exception.NoSuchApplicableInfoException;
import org.opencps.dossiermgt.model.ApplicableInfo;
import org.opencps.dossiermgt.model.impl.ApplicableInfoImpl;
import org.opencps.dossiermgt.model.impl.ApplicableInfoModelImpl;
import org.opencps.dossiermgt.service.persistence.ApplicableInfoPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the applicable info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ApplicableInfoPersistence
 * @see org.opencps.dossiermgt.service.persistence.ApplicableInfoUtil
 * @generated
 */
@ProviderType
public class ApplicableInfoPersistenceImpl extends BasePersistenceImpl<ApplicableInfo>
	implements ApplicableInfoPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ApplicableInfoUtil} to access the applicable info persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ApplicableInfoImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED,
			ApplicableInfoImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED,
			ApplicableInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED,
			ApplicableInfoImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED,
			ApplicableInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ApplicableInfoModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the applicable infos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching applicable infos
	 */
	@Override
	public List<ApplicableInfo> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the applicable infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of applicable infos
	 * @param end the upper bound of the range of applicable infos (not inclusive)
	 * @return the range of matching applicable infos
	 */
	@Override
	public List<ApplicableInfo> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the applicable infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of applicable infos
	 * @param end the upper bound of the range of applicable infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching applicable infos
	 */
	@Override
	public List<ApplicableInfo> findByUuid(String uuid, int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the applicable infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of applicable infos
	 * @param end the upper bound of the range of applicable infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching applicable infos
	 */
	@Override
	public List<ApplicableInfo> findByUuid(String uuid, int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator,
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

		List<ApplicableInfo> list = null;

		if (retrieveFromCache) {
			list = (List<ApplicableInfo>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ApplicableInfo applicableInfo : list) {
					if (!Objects.equals(uuid, applicableInfo.getUuid())) {
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

			query.append(_SQL_SELECT_APPLICABLEINFO_WHERE);

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
				query.append(ApplicableInfoModelImpl.ORDER_BY_JPQL);
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
					list = (List<ApplicableInfo>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ApplicableInfo>)QueryUtil.list(q,
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
	 * Returns the first applicable info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching applicable info
	 * @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo findByUuid_First(String uuid,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException {
		ApplicableInfo applicableInfo = fetchByUuid_First(uuid,
				orderByComparator);

		if (applicableInfo != null) {
			return applicableInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchApplicableInfoException(msg.toString());
	}

	/**
	 * Returns the first applicable info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching applicable info, or <code>null</code> if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo fetchByUuid_First(String uuid,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		List<ApplicableInfo> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last applicable info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching applicable info
	 * @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo findByUuid_Last(String uuid,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException {
		ApplicableInfo applicableInfo = fetchByUuid_Last(uuid, orderByComparator);

		if (applicableInfo != null) {
			return applicableInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchApplicableInfoException(msg.toString());
	}

	/**
	 * Returns the last applicable info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching applicable info, or <code>null</code> if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo fetchByUuid_Last(String uuid,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ApplicableInfo> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the applicable infos before and after the current applicable info in the ordered set where uuid = &#63;.
	 *
	 * @param applicableInfoId the primary key of the current applicable info
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next applicable info
	 * @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	 */
	@Override
	public ApplicableInfo[] findByUuid_PrevAndNext(long applicableInfoId,
		String uuid, OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException {
		ApplicableInfo applicableInfo = findByPrimaryKey(applicableInfoId);

		Session session = null;

		try {
			session = openSession();

			ApplicableInfo[] array = new ApplicableInfoImpl[3];

			array[0] = getByUuid_PrevAndNext(session, applicableInfo, uuid,
					orderByComparator, true);

			array[1] = applicableInfo;

			array[2] = getByUuid_PrevAndNext(session, applicableInfo, uuid,
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

	protected ApplicableInfo getByUuid_PrevAndNext(Session session,
		ApplicableInfo applicableInfo, String uuid,
		OrderByComparator<ApplicableInfo> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPLICABLEINFO_WHERE);

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
			query.append(ApplicableInfoModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(applicableInfo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ApplicableInfo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the applicable infos where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ApplicableInfo applicableInfo : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(applicableInfo);
		}
	}

	/**
	 * Returns the number of applicable infos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching applicable infos
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPLICABLEINFO_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "applicableInfo.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "applicableInfo.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(applicableInfo.uuid IS NULL OR applicableInfo.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED,
			ApplicableInfoImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ApplicableInfoModelImpl.UUID_COLUMN_BITMASK |
			ApplicableInfoModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the applicable info where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchApplicableInfoException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching applicable info
	 * @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo findByUUID_G(String uuid, long groupId)
		throws NoSuchApplicableInfoException {
		ApplicableInfo applicableInfo = fetchByUUID_G(uuid, groupId);

		if (applicableInfo == null) {
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

			throw new NoSuchApplicableInfoException(msg.toString());
		}

		return applicableInfo;
	}

	/**
	 * Returns the applicable info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching applicable info, or <code>null</code> if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the applicable info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching applicable info, or <code>null</code> if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ApplicableInfo) {
			ApplicableInfo applicableInfo = (ApplicableInfo)result;

			if (!Objects.equals(uuid, applicableInfo.getUuid()) ||
					(groupId != applicableInfo.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_APPLICABLEINFO_WHERE);

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

				List<ApplicableInfo> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ApplicableInfo applicableInfo = list.get(0);

					result = applicableInfo;

					cacheResult(applicableInfo);
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
			return (ApplicableInfo)result;
		}
	}

	/**
	 * Removes the applicable info where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the applicable info that was removed
	 */
	@Override
	public ApplicableInfo removeByUUID_G(String uuid, long groupId)
		throws NoSuchApplicableInfoException {
		ApplicableInfo applicableInfo = findByUUID_G(uuid, groupId);

		return remove(applicableInfo);
	}

	/**
	 * Returns the number of applicable infos where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching applicable infos
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPLICABLEINFO_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "applicableInfo.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "applicableInfo.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(applicableInfo.uuid IS NULL OR applicableInfo.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "applicableInfo.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED,
			ApplicableInfoImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED,
			ApplicableInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ApplicableInfoModelImpl.UUID_COLUMN_BITMASK |
			ApplicableInfoModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the applicable infos where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching applicable infos
	 */
	@Override
	public List<ApplicableInfo> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the applicable infos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of applicable infos
	 * @param end the upper bound of the range of applicable infos (not inclusive)
	 * @return the range of matching applicable infos
	 */
	@Override
	public List<ApplicableInfo> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the applicable infos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of applicable infos
	 * @param end the upper bound of the range of applicable infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching applicable infos
	 */
	@Override
	public List<ApplicableInfo> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ApplicableInfo> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the applicable infos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of applicable infos
	 * @param end the upper bound of the range of applicable infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching applicable infos
	 */
	@Override
	public List<ApplicableInfo> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator,
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

		List<ApplicableInfo> list = null;

		if (retrieveFromCache) {
			list = (List<ApplicableInfo>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ApplicableInfo applicableInfo : list) {
					if (!Objects.equals(uuid, applicableInfo.getUuid()) ||
							(companyId != applicableInfo.getCompanyId())) {
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

			query.append(_SQL_SELECT_APPLICABLEINFO_WHERE);

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
				query.append(ApplicableInfoModelImpl.ORDER_BY_JPQL);
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
					list = (List<ApplicableInfo>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ApplicableInfo>)QueryUtil.list(q,
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
	 * Returns the first applicable info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching applicable info
	 * @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException {
		ApplicableInfo applicableInfo = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (applicableInfo != null) {
			return applicableInfo;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchApplicableInfoException(msg.toString());
	}

	/**
	 * Returns the first applicable info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching applicable info, or <code>null</code> if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		List<ApplicableInfo> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last applicable info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching applicable info
	 * @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException {
		ApplicableInfo applicableInfo = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (applicableInfo != null) {
			return applicableInfo;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchApplicableInfoException(msg.toString());
	}

	/**
	 * Returns the last applicable info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching applicable info, or <code>null</code> if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ApplicableInfo> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the applicable infos before and after the current applicable info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param applicableInfoId the primary key of the current applicable info
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next applicable info
	 * @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	 */
	@Override
	public ApplicableInfo[] findByUuid_C_PrevAndNext(long applicableInfoId,
		String uuid, long companyId,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException {
		ApplicableInfo applicableInfo = findByPrimaryKey(applicableInfoId);

		Session session = null;

		try {
			session = openSession();

			ApplicableInfo[] array = new ApplicableInfoImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, applicableInfo, uuid,
					companyId, orderByComparator, true);

			array[1] = applicableInfo;

			array[2] = getByUuid_C_PrevAndNext(session, applicableInfo, uuid,
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

	protected ApplicableInfo getByUuid_C_PrevAndNext(Session session,
		ApplicableInfo applicableInfo, String uuid, long companyId,
		OrderByComparator<ApplicableInfo> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_APPLICABLEINFO_WHERE);

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
			query.append(ApplicableInfoModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(applicableInfo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ApplicableInfo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the applicable infos where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ApplicableInfo applicableInfo : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(applicableInfo);
		}
	}

	/**
	 * Returns the number of applicable infos where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching applicable infos
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPLICABLEINFO_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "applicableInfo.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "applicableInfo.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(applicableInfo.uuid IS NULL OR applicableInfo.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "applicableInfo.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_SC_GC_SL = new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED,
			ApplicableInfoImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_SC_GC_SL",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Integer.class.getName()
			},
			ApplicableInfoModelImpl.GROUPID_COLUMN_BITMASK |
			ApplicableInfoModelImpl.SERVICECODE_COLUMN_BITMASK |
			ApplicableInfoModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			ApplicableInfoModelImpl.SERVICELEVEL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_SC_GC_SL = new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_SC_GC_SL",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns the applicable info where groupId = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and serviceLevel = &#63; or throws a {@link NoSuchApplicableInfoException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param serviceLevel the service level
	 * @return the matching applicable info
	 * @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo findByG_SC_GC_SL(long groupId, String serviceCode,
		String govAgencyCode, int serviceLevel)
		throws NoSuchApplicableInfoException {
		ApplicableInfo applicableInfo = fetchByG_SC_GC_SL(groupId, serviceCode,
				govAgencyCode, serviceLevel);

		if (applicableInfo == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", serviceCode=");
			msg.append(serviceCode);

			msg.append(", govAgencyCode=");
			msg.append(govAgencyCode);

			msg.append(", serviceLevel=");
			msg.append(serviceLevel);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchApplicableInfoException(msg.toString());
		}

		return applicableInfo;
	}

	/**
	 * Returns the applicable info where groupId = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and serviceLevel = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param serviceLevel the service level
	 * @return the matching applicable info, or <code>null</code> if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo fetchByG_SC_GC_SL(long groupId, String serviceCode,
		String govAgencyCode, int serviceLevel) {
		return fetchByG_SC_GC_SL(groupId, serviceCode, govAgencyCode,
			serviceLevel, true);
	}

	/**
	 * Returns the applicable info where groupId = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and serviceLevel = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param serviceLevel the service level
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching applicable info, or <code>null</code> if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo fetchByG_SC_GC_SL(long groupId, String serviceCode,
		String govAgencyCode, int serviceLevel, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, serviceCode, govAgencyCode, serviceLevel
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_SC_GC_SL,
					finderArgs, this);
		}

		if (result instanceof ApplicableInfo) {
			ApplicableInfo applicableInfo = (ApplicableInfo)result;

			if ((groupId != applicableInfo.getGroupId()) ||
					!Objects.equals(serviceCode, applicableInfo.getServiceCode()) ||
					!Objects.equals(govAgencyCode,
						applicableInfo.getGovAgencyCode()) ||
					(serviceLevel != applicableInfo.getServiceLevel())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_APPLICABLEINFO_WHERE);

			query.append(_FINDER_COLUMN_G_SC_GC_SL_GROUPID_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_SC_GC_SL_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_SC_GC_SL_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_SC_GC_SL_SERVICECODE_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_SC_GC_SL_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_SC_GC_SL_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_SC_GC_SL_GOVAGENCYCODE_2);
			}

			query.append(_FINDER_COLUMN_G_SC_GC_SL_SERVICELEVEL_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				qPos.add(serviceLevel);

				List<ApplicableInfo> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_SC_GC_SL,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ApplicableInfoPersistenceImpl.fetchByG_SC_GC_SL(long, String, String, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ApplicableInfo applicableInfo = list.get(0);

					result = applicableInfo;

					cacheResult(applicableInfo);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_SC_GC_SL,
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
			return (ApplicableInfo)result;
		}
	}

	/**
	 * Removes the applicable info where groupId = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and serviceLevel = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param serviceLevel the service level
	 * @return the applicable info that was removed
	 */
	@Override
	public ApplicableInfo removeByG_SC_GC_SL(long groupId, String serviceCode,
		String govAgencyCode, int serviceLevel)
		throws NoSuchApplicableInfoException {
		ApplicableInfo applicableInfo = findByG_SC_GC_SL(groupId, serviceCode,
				govAgencyCode, serviceLevel);

		return remove(applicableInfo);
	}

	/**
	 * Returns the number of applicable infos where groupId = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and serviceLevel = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param serviceLevel the service level
	 * @return the number of matching applicable infos
	 */
	@Override
	public int countByG_SC_GC_SL(long groupId, String serviceCode,
		String govAgencyCode, int serviceLevel) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_SC_GC_SL;

		Object[] finderArgs = new Object[] {
				groupId, serviceCode, govAgencyCode, serviceLevel
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_APPLICABLEINFO_WHERE);

			query.append(_FINDER_COLUMN_G_SC_GC_SL_GROUPID_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_SC_GC_SL_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_SC_GC_SL_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_SC_GC_SL_SERVICECODE_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_SC_GC_SL_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_SC_GC_SL_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_SC_GC_SL_GOVAGENCYCODE_2);
			}

			query.append(_FINDER_COLUMN_G_SC_GC_SL_SERVICELEVEL_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				qPos.add(serviceLevel);

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

	private static final String _FINDER_COLUMN_G_SC_GC_SL_GROUPID_2 = "applicableInfo.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_SC_GC_SL_SERVICECODE_1 = "applicableInfo.serviceCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_SC_GC_SL_SERVICECODE_2 = "applicableInfo.serviceCode = ? AND ";
	private static final String _FINDER_COLUMN_G_SC_GC_SL_SERVICECODE_3 = "(applicableInfo.serviceCode IS NULL OR applicableInfo.serviceCode = '') AND ";
	private static final String _FINDER_COLUMN_G_SC_GC_SL_GOVAGENCYCODE_1 = "applicableInfo.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_SC_GC_SL_GOVAGENCYCODE_2 = "applicableInfo.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_SC_GC_SL_GOVAGENCYCODE_3 = "(applicableInfo.govAgencyCode IS NULL OR applicableInfo.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_SC_GC_SL_SERVICELEVEL_2 = "applicableInfo.serviceLevel = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID =
		new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED,
			ApplicableInfoImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByServiceConfigMappingId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID =
		new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED,
			ApplicableInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByServiceConfigMappingId",
			new String[] { Long.class.getName() },
			ApplicableInfoModelImpl.SERVICECONFIGMAPPINGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SERVICECONFIGMAPPINGID = new FinderPath(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByServiceConfigMappingId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the applicable infos where serviceConfigMappingId = &#63;.
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @return the matching applicable infos
	 */
	@Override
	public List<ApplicableInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId) {
		return findByServiceConfigMappingId(serviceConfigMappingId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the applicable infos where serviceConfigMappingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param start the lower bound of the range of applicable infos
	 * @param end the upper bound of the range of applicable infos (not inclusive)
	 * @return the range of matching applicable infos
	 */
	@Override
	public List<ApplicableInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end) {
		return findByServiceConfigMappingId(serviceConfigMappingId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the applicable infos where serviceConfigMappingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param start the lower bound of the range of applicable infos
	 * @param end the upper bound of the range of applicable infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching applicable infos
	 */
	@Override
	public List<ApplicableInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		return findByServiceConfigMappingId(serviceConfigMappingId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the applicable infos where serviceConfigMappingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param start the lower bound of the range of applicable infos
	 * @param end the upper bound of the range of applicable infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching applicable infos
	 */
	@Override
	public List<ApplicableInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID;
			finderArgs = new Object[] { serviceConfigMappingId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID;
			finderArgs = new Object[] {
					serviceConfigMappingId,
					
					start, end, orderByComparator
				};
		}

		List<ApplicableInfo> list = null;

		if (retrieveFromCache) {
			list = (List<ApplicableInfo>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ApplicableInfo applicableInfo : list) {
					if ((serviceConfigMappingId != applicableInfo.getServiceConfigMappingId())) {
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

			query.append(_SQL_SELECT_APPLICABLEINFO_WHERE);

			query.append(_FINDER_COLUMN_SERVICECONFIGMAPPINGID_SERVICECONFIGMAPPINGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ApplicableInfoModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceConfigMappingId);

				if (!pagination) {
					list = (List<ApplicableInfo>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ApplicableInfo>)QueryUtil.list(q,
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
	 * Returns the first applicable info in the ordered set where serviceConfigMappingId = &#63;.
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching applicable info
	 * @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo findByServiceConfigMappingId_First(
		long serviceConfigMappingId,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException {
		ApplicableInfo applicableInfo = fetchByServiceConfigMappingId_First(serviceConfigMappingId,
				orderByComparator);

		if (applicableInfo != null) {
			return applicableInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("serviceConfigMappingId=");
		msg.append(serviceConfigMappingId);

		msg.append("}");

		throw new NoSuchApplicableInfoException(msg.toString());
	}

	/**
	 * Returns the first applicable info in the ordered set where serviceConfigMappingId = &#63;.
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching applicable info, or <code>null</code> if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo fetchByServiceConfigMappingId_First(
		long serviceConfigMappingId,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		List<ApplicableInfo> list = findByServiceConfigMappingId(serviceConfigMappingId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last applicable info in the ordered set where serviceConfigMappingId = &#63;.
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching applicable info
	 * @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo findByServiceConfigMappingId_Last(
		long serviceConfigMappingId,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException {
		ApplicableInfo applicableInfo = fetchByServiceConfigMappingId_Last(serviceConfigMappingId,
				orderByComparator);

		if (applicableInfo != null) {
			return applicableInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("serviceConfigMappingId=");
		msg.append(serviceConfigMappingId);

		msg.append("}");

		throw new NoSuchApplicableInfoException(msg.toString());
	}

	/**
	 * Returns the last applicable info in the ordered set where serviceConfigMappingId = &#63;.
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching applicable info, or <code>null</code> if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo fetchByServiceConfigMappingId_Last(
		long serviceConfigMappingId,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		int count = countByServiceConfigMappingId(serviceConfigMappingId);

		if (count == 0) {
			return null;
		}

		List<ApplicableInfo> list = findByServiceConfigMappingId(serviceConfigMappingId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the applicable infos before and after the current applicable info in the ordered set where serviceConfigMappingId = &#63;.
	 *
	 * @param applicableInfoId the primary key of the current applicable info
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next applicable info
	 * @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	 */
	@Override
	public ApplicableInfo[] findByServiceConfigMappingId_PrevAndNext(
		long applicableInfoId, long serviceConfigMappingId,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException {
		ApplicableInfo applicableInfo = findByPrimaryKey(applicableInfoId);

		Session session = null;

		try {
			session = openSession();

			ApplicableInfo[] array = new ApplicableInfoImpl[3];

			array[0] = getByServiceConfigMappingId_PrevAndNext(session,
					applicableInfo, serviceConfigMappingId, orderByComparator,
					true);

			array[1] = applicableInfo;

			array[2] = getByServiceConfigMappingId_PrevAndNext(session,
					applicableInfo, serviceConfigMappingId, orderByComparator,
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

	protected ApplicableInfo getByServiceConfigMappingId_PrevAndNext(
		Session session, ApplicableInfo applicableInfo,
		long serviceConfigMappingId,
		OrderByComparator<ApplicableInfo> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPLICABLEINFO_WHERE);

		query.append(_FINDER_COLUMN_SERVICECONFIGMAPPINGID_SERVICECONFIGMAPPINGID_2);

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
			query.append(ApplicableInfoModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(serviceConfigMappingId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(applicableInfo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ApplicableInfo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the applicable infos where serviceConfigMappingId = &#63; from the database.
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 */
	@Override
	public void removeByServiceConfigMappingId(long serviceConfigMappingId) {
		for (ApplicableInfo applicableInfo : findByServiceConfigMappingId(
				serviceConfigMappingId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(applicableInfo);
		}
	}

	/**
	 * Returns the number of applicable infos where serviceConfigMappingId = &#63;.
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @return the number of matching applicable infos
	 */
	@Override
	public int countByServiceConfigMappingId(long serviceConfigMappingId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SERVICECONFIGMAPPINGID;

		Object[] finderArgs = new Object[] { serviceConfigMappingId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPLICABLEINFO_WHERE);

			query.append(_FINDER_COLUMN_SERVICECONFIGMAPPINGID_SERVICECONFIGMAPPINGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceConfigMappingId);

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

	private static final String _FINDER_COLUMN_SERVICECONFIGMAPPINGID_SERVICECONFIGMAPPINGID_2 =
		"applicableInfo.serviceConfigMappingId = ?";

	public ApplicableInfoPersistenceImpl() {
		setModelClass(ApplicableInfo.class);

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
	 * Caches the applicable info in the entity cache if it is enabled.
	 *
	 * @param applicableInfo the applicable info
	 */
	@Override
	public void cacheResult(ApplicableInfo applicableInfo) {
		entityCache.putResult(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoImpl.class, applicableInfo.getPrimaryKey(),
			applicableInfo);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { applicableInfo.getUuid(), applicableInfo.getGroupId() },
			applicableInfo);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_SC_GC_SL,
			new Object[] {
				applicableInfo.getGroupId(), applicableInfo.getServiceCode(),
				applicableInfo.getGovAgencyCode(),
				applicableInfo.getServiceLevel()
			}, applicableInfo);

		applicableInfo.resetOriginalValues();
	}

	/**
	 * Caches the applicable infos in the entity cache if it is enabled.
	 *
	 * @param applicableInfos the applicable infos
	 */
	@Override
	public void cacheResult(List<ApplicableInfo> applicableInfos) {
		for (ApplicableInfo applicableInfo : applicableInfos) {
			if (entityCache.getResult(
						ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
						ApplicableInfoImpl.class, applicableInfo.getPrimaryKey()) == null) {
				cacheResult(applicableInfo);
			}
			else {
				applicableInfo.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all applicable infos.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ApplicableInfoImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the applicable info.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ApplicableInfo applicableInfo) {
		entityCache.removeResult(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoImpl.class, applicableInfo.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ApplicableInfoModelImpl)applicableInfo, true);
	}

	@Override
	public void clearCache(List<ApplicableInfo> applicableInfos) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ApplicableInfo applicableInfo : applicableInfos) {
			entityCache.removeResult(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
				ApplicableInfoImpl.class, applicableInfo.getPrimaryKey());

			clearUniqueFindersCache((ApplicableInfoModelImpl)applicableInfo,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ApplicableInfoModelImpl applicableInfoModelImpl) {
		Object[] args = new Object[] {
				applicableInfoModelImpl.getUuid(),
				applicableInfoModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			applicableInfoModelImpl, false);

		args = new Object[] {
				applicableInfoModelImpl.getGroupId(),
				applicableInfoModelImpl.getServiceCode(),
				applicableInfoModelImpl.getGovAgencyCode(),
				applicableInfoModelImpl.getServiceLevel()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_SC_GC_SL, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_SC_GC_SL, args,
			applicableInfoModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ApplicableInfoModelImpl applicableInfoModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					applicableInfoModelImpl.getUuid(),
					applicableInfoModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((applicableInfoModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					applicableInfoModelImpl.getOriginalUuid(),
					applicableInfoModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					applicableInfoModelImpl.getGroupId(),
					applicableInfoModelImpl.getServiceCode(),
					applicableInfoModelImpl.getGovAgencyCode(),
					applicableInfoModelImpl.getServiceLevel()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SC_GC_SL, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_SC_GC_SL, args);
		}

		if ((applicableInfoModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_SC_GC_SL.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					applicableInfoModelImpl.getOriginalGroupId(),
					applicableInfoModelImpl.getOriginalServiceCode(),
					applicableInfoModelImpl.getOriginalGovAgencyCode(),
					applicableInfoModelImpl.getOriginalServiceLevel()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SC_GC_SL, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_SC_GC_SL, args);
		}
	}

	/**
	 * Creates a new applicable info with the primary key. Does not add the applicable info to the database.
	 *
	 * @param applicableInfoId the primary key for the new applicable info
	 * @return the new applicable info
	 */
	@Override
	public ApplicableInfo create(long applicableInfoId) {
		ApplicableInfo applicableInfo = new ApplicableInfoImpl();

		applicableInfo.setNew(true);
		applicableInfo.setPrimaryKey(applicableInfoId);

		String uuid = PortalUUIDUtil.generate();

		applicableInfo.setUuid(uuid);

		applicableInfo.setCompanyId(companyProvider.getCompanyId());

		return applicableInfo;
	}

	/**
	 * Removes the applicable info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param applicableInfoId the primary key of the applicable info
	 * @return the applicable info that was removed
	 * @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	 */
	@Override
	public ApplicableInfo remove(long applicableInfoId)
		throws NoSuchApplicableInfoException {
		return remove((Serializable)applicableInfoId);
	}

	/**
	 * Removes the applicable info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the applicable info
	 * @return the applicable info that was removed
	 * @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	 */
	@Override
	public ApplicableInfo remove(Serializable primaryKey)
		throws NoSuchApplicableInfoException {
		Session session = null;

		try {
			session = openSession();

			ApplicableInfo applicableInfo = (ApplicableInfo)session.get(ApplicableInfoImpl.class,
					primaryKey);

			if (applicableInfo == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApplicableInfoException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(applicableInfo);
		}
		catch (NoSuchApplicableInfoException nsee) {
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
	protected ApplicableInfo removeImpl(ApplicableInfo applicableInfo) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(applicableInfo)) {
				applicableInfo = (ApplicableInfo)session.get(ApplicableInfoImpl.class,
						applicableInfo.getPrimaryKeyObj());
			}

			if (applicableInfo != null) {
				session.delete(applicableInfo);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (applicableInfo != null) {
			clearCache(applicableInfo);
		}

		return applicableInfo;
	}

	@Override
	public ApplicableInfo updateImpl(ApplicableInfo applicableInfo) {
		boolean isNew = applicableInfo.isNew();

		if (!(applicableInfo instanceof ApplicableInfoModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(applicableInfo.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(applicableInfo);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in applicableInfo proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ApplicableInfo implementation " +
				applicableInfo.getClass());
		}

		ApplicableInfoModelImpl applicableInfoModelImpl = (ApplicableInfoModelImpl)applicableInfo;

		if (Validator.isNull(applicableInfo.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			applicableInfo.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (applicableInfo.getCreateDate() == null)) {
			if (serviceContext == null) {
				applicableInfo.setCreateDate(now);
			}
			else {
				applicableInfo.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!applicableInfoModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				applicableInfo.setModifiedDate(now);
			}
			else {
				applicableInfo.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (applicableInfo.isNew()) {
				session.save(applicableInfo);

				applicableInfo.setNew(false);
			}
			else {
				applicableInfo = (ApplicableInfo)session.merge(applicableInfo);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ApplicableInfoModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { applicableInfoModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					applicableInfoModelImpl.getUuid(),
					applicableInfoModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					applicableInfoModelImpl.getServiceConfigMappingId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SERVICECONFIGMAPPINGID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((applicableInfoModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						applicableInfoModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { applicableInfoModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((applicableInfoModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						applicableInfoModelImpl.getOriginalUuid(),
						applicableInfoModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						applicableInfoModelImpl.getUuid(),
						applicableInfoModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((applicableInfoModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						applicableInfoModelImpl.getOriginalServiceConfigMappingId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SERVICECONFIGMAPPINGID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID,
					args);

				args = new Object[] {
						applicableInfoModelImpl.getServiceConfigMappingId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SERVICECONFIGMAPPINGID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID,
					args);
			}
		}

		entityCache.putResult(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
			ApplicableInfoImpl.class, applicableInfo.getPrimaryKey(),
			applicableInfo, false);

		clearUniqueFindersCache(applicableInfoModelImpl, false);
		cacheUniqueFindersCache(applicableInfoModelImpl);

		applicableInfo.resetOriginalValues();

		return applicableInfo;
	}

	/**
	 * Returns the applicable info with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the applicable info
	 * @return the applicable info
	 * @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	 */
	@Override
	public ApplicableInfo findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApplicableInfoException {
		ApplicableInfo applicableInfo = fetchByPrimaryKey(primaryKey);

		if (applicableInfo == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApplicableInfoException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return applicableInfo;
	}

	/**
	 * Returns the applicable info with the primary key or throws a {@link NoSuchApplicableInfoException} if it could not be found.
	 *
	 * @param applicableInfoId the primary key of the applicable info
	 * @return the applicable info
	 * @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	 */
	@Override
	public ApplicableInfo findByPrimaryKey(long applicableInfoId)
		throws NoSuchApplicableInfoException {
		return findByPrimaryKey((Serializable)applicableInfoId);
	}

	/**
	 * Returns the applicable info with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the applicable info
	 * @return the applicable info, or <code>null</code> if a applicable info with the primary key could not be found
	 */
	@Override
	public ApplicableInfo fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
				ApplicableInfoImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ApplicableInfo applicableInfo = (ApplicableInfo)serializable;

		if (applicableInfo == null) {
			Session session = null;

			try {
				session = openSession();

				applicableInfo = (ApplicableInfo)session.get(ApplicableInfoImpl.class,
						primaryKey);

				if (applicableInfo != null) {
					cacheResult(applicableInfo);
				}
				else {
					entityCache.putResult(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
						ApplicableInfoImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
					ApplicableInfoImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return applicableInfo;
	}

	/**
	 * Returns the applicable info with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param applicableInfoId the primary key of the applicable info
	 * @return the applicable info, or <code>null</code> if a applicable info with the primary key could not be found
	 */
	@Override
	public ApplicableInfo fetchByPrimaryKey(long applicableInfoId) {
		return fetchByPrimaryKey((Serializable)applicableInfoId);
	}

	@Override
	public Map<Serializable, ApplicableInfo> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ApplicableInfo> map = new HashMap<Serializable, ApplicableInfo>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ApplicableInfo applicableInfo = fetchByPrimaryKey(primaryKey);

			if (applicableInfo != null) {
				map.put(primaryKey, applicableInfo);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
					ApplicableInfoImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ApplicableInfo)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_APPLICABLEINFO_WHERE_PKS_IN);

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

			for (ApplicableInfo applicableInfo : (List<ApplicableInfo>)q.list()) {
				map.put(applicableInfo.getPrimaryKeyObj(), applicableInfo);

				cacheResult(applicableInfo);

				uncachedPrimaryKeys.remove(applicableInfo.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ApplicableInfoModelImpl.ENTITY_CACHE_ENABLED,
					ApplicableInfoImpl.class, primaryKey, nullModel);
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
	 * Returns all the applicable infos.
	 *
	 * @return the applicable infos
	 */
	@Override
	public List<ApplicableInfo> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the applicable infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of applicable infos
	 * @param end the upper bound of the range of applicable infos (not inclusive)
	 * @return the range of applicable infos
	 */
	@Override
	public List<ApplicableInfo> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the applicable infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of applicable infos
	 * @param end the upper bound of the range of applicable infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of applicable infos
	 */
	@Override
	public List<ApplicableInfo> findAll(int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the applicable infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of applicable infos
	 * @param end the upper bound of the range of applicable infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of applicable infos
	 */
	@Override
	public List<ApplicableInfo> findAll(int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator,
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

		List<ApplicableInfo> list = null;

		if (retrieveFromCache) {
			list = (List<ApplicableInfo>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_APPLICABLEINFO);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_APPLICABLEINFO;

				if (pagination) {
					sql = sql.concat(ApplicableInfoModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ApplicableInfo>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ApplicableInfo>)QueryUtil.list(q,
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
	 * Removes all the applicable infos from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ApplicableInfo applicableInfo : findAll()) {
			remove(applicableInfo);
		}
	}

	/**
	 * Returns the number of applicable infos.
	 *
	 * @return the number of applicable infos
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_APPLICABLEINFO);

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
		return ApplicableInfoModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the applicable info persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ApplicableInfoImpl.class.getName());
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
	private static final String _SQL_SELECT_APPLICABLEINFO = "SELECT applicableInfo FROM ApplicableInfo applicableInfo";
	private static final String _SQL_SELECT_APPLICABLEINFO_WHERE_PKS_IN = "SELECT applicableInfo FROM ApplicableInfo applicableInfo WHERE applicableInfoId IN (";
	private static final String _SQL_SELECT_APPLICABLEINFO_WHERE = "SELECT applicableInfo FROM ApplicableInfo applicableInfo WHERE ";
	private static final String _SQL_COUNT_APPLICABLEINFO = "SELECT COUNT(applicableInfo) FROM ApplicableInfo applicableInfo";
	private static final String _SQL_COUNT_APPLICABLEINFO_WHERE = "SELECT COUNT(applicableInfo) FROM ApplicableInfo applicableInfo WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "applicableInfo.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ApplicableInfo exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ApplicableInfo exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ApplicableInfoPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}