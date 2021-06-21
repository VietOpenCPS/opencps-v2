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

import org.opencps.dossiermgt.exception.NoSuchCsdlDcServiceInfoException;
import org.opencps.dossiermgt.model.CsdlDcServiceInfo;
import org.opencps.dossiermgt.model.impl.CsdlDcServiceInfoImpl;
import org.opencps.dossiermgt.model.impl.CsdlDcServiceInfoModelImpl;
import org.opencps.dossiermgt.service.persistence.CsdlDcServiceInfoPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the csdl dc service info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see CsdlDcServiceInfoPersistence
 * @see org.opencps.dossiermgt.service.persistence.CsdlDcServiceInfoUtil
 * @generated
 */
@ProviderType
public class CsdlDcServiceInfoPersistenceImpl extends BasePersistenceImpl<CsdlDcServiceInfo>
	implements CsdlDcServiceInfoPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CsdlDcServiceInfoUtil} to access the csdl dc service info persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CsdlDcServiceInfoImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoModelImpl.FINDER_CACHE_ENABLED,
			CsdlDcServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoModelImpl.FINDER_CACHE_ENABLED,
			CsdlDcServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoModelImpl.FINDER_CACHE_ENABLED,
			CsdlDcServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoModelImpl.FINDER_CACHE_ENABLED,
			CsdlDcServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CsdlDcServiceInfoModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the csdl dc service infos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching csdl dc service infos
	 */
	@Override
	public List<CsdlDcServiceInfo> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the csdl dc service infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csdl dc service infos
	 * @param end the upper bound of the range of csdl dc service infos (not inclusive)
	 * @return the range of matching csdl dc service infos
	 */
	@Override
	public List<CsdlDcServiceInfo> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the csdl dc service infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csdl dc service infos
	 * @param end the upper bound of the range of csdl dc service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csdl dc service infos
	 */
	@Override
	public List<CsdlDcServiceInfo> findByUuid(String uuid, int start, int end,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the csdl dc service infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csdl dc service infos
	 * @param end the upper bound of the range of csdl dc service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csdl dc service infos
	 */
	@Override
	public List<CsdlDcServiceInfo> findByUuid(String uuid, int start, int end,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator,
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

		List<CsdlDcServiceInfo> list = null;

		if (retrieveFromCache) {
			list = (List<CsdlDcServiceInfo>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CsdlDcServiceInfo csdlDcServiceInfo : list) {
					if (!Objects.equals(uuid, csdlDcServiceInfo.getUuid())) {
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

			query.append(_SQL_SELECT_CSDLDCSERVICEINFO_WHERE);

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
				query.append(CsdlDcServiceInfoModelImpl.ORDER_BY_JPQL);
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
					list = (List<CsdlDcServiceInfo>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CsdlDcServiceInfo>)QueryUtil.list(q,
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
	 * Returns the first csdl dc service info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csdl dc service info
	 * @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	 */
	@Override
	public CsdlDcServiceInfo findByUuid_First(String uuid,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws NoSuchCsdlDcServiceInfoException {
		CsdlDcServiceInfo csdlDcServiceInfo = fetchByUuid_First(uuid,
				orderByComparator);

		if (csdlDcServiceInfo != null) {
			return csdlDcServiceInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCsdlDcServiceInfoException(msg.toString());
	}

	/**
	 * Returns the first csdl dc service info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	 */
	@Override
	public CsdlDcServiceInfo fetchByUuid_First(String uuid,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		List<CsdlDcServiceInfo> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last csdl dc service info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csdl dc service info
	 * @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	 */
	@Override
	public CsdlDcServiceInfo findByUuid_Last(String uuid,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws NoSuchCsdlDcServiceInfoException {
		CsdlDcServiceInfo csdlDcServiceInfo = fetchByUuid_Last(uuid,
				orderByComparator);

		if (csdlDcServiceInfo != null) {
			return csdlDcServiceInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCsdlDcServiceInfoException(msg.toString());
	}

	/**
	 * Returns the last csdl dc service info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	 */
	@Override
	public CsdlDcServiceInfo fetchByUuid_Last(String uuid,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CsdlDcServiceInfo> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the csdl dc service infos before and after the current csdl dc service info in the ordered set where uuid = &#63;.
	 *
	 * @param idDcService the primary key of the current csdl dc service info
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csdl dc service info
	 * @throws NoSuchCsdlDcServiceInfoException if a csdl dc service info with the primary key could not be found
	 */
	@Override
	public CsdlDcServiceInfo[] findByUuid_PrevAndNext(long idDcService,
		String uuid, OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws NoSuchCsdlDcServiceInfoException {
		CsdlDcServiceInfo csdlDcServiceInfo = findByPrimaryKey(idDcService);

		Session session = null;

		try {
			session = openSession();

			CsdlDcServiceInfo[] array = new CsdlDcServiceInfoImpl[3];

			array[0] = getByUuid_PrevAndNext(session, csdlDcServiceInfo, uuid,
					orderByComparator, true);

			array[1] = csdlDcServiceInfo;

			array[2] = getByUuid_PrevAndNext(session, csdlDcServiceInfo, uuid,
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

	protected CsdlDcServiceInfo getByUuid_PrevAndNext(Session session,
		CsdlDcServiceInfo csdlDcServiceInfo, String uuid,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CSDLDCSERVICEINFO_WHERE);

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
			query.append(CsdlDcServiceInfoModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(csdlDcServiceInfo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CsdlDcServiceInfo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the csdl dc service infos where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CsdlDcServiceInfo csdlDcServiceInfo : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(csdlDcServiceInfo);
		}
	}

	/**
	 * Returns the number of csdl dc service infos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching csdl dc service infos
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CSDLDCSERVICEINFO_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "csdlDcServiceInfo.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "csdlDcServiceInfo.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(csdlDcServiceInfo.uuid IS NULL OR csdlDcServiceInfo.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoModelImpl.FINDER_CACHE_ENABLED,
			CsdlDcServiceInfoImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			CsdlDcServiceInfoModelImpl.UUID_COLUMN_BITMASK |
			CsdlDcServiceInfoModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the csdl dc service info where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCsdlDcServiceInfoException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching csdl dc service info
	 * @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	 */
	@Override
	public CsdlDcServiceInfo findByUUID_G(String uuid, long groupId)
		throws NoSuchCsdlDcServiceInfoException {
		CsdlDcServiceInfo csdlDcServiceInfo = fetchByUUID_G(uuid, groupId);

		if (csdlDcServiceInfo == null) {
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

			throw new NoSuchCsdlDcServiceInfoException(msg.toString());
		}

		return csdlDcServiceInfo;
	}

	/**
	 * Returns the csdl dc service info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	 */
	@Override
	public CsdlDcServiceInfo fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the csdl dc service info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	 */
	@Override
	public CsdlDcServiceInfo fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof CsdlDcServiceInfo) {
			CsdlDcServiceInfo csdlDcServiceInfo = (CsdlDcServiceInfo)result;

			if (!Objects.equals(uuid, csdlDcServiceInfo.getUuid()) ||
					(groupId != csdlDcServiceInfo.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CSDLDCSERVICEINFO_WHERE);

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

				List<CsdlDcServiceInfo> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					CsdlDcServiceInfo csdlDcServiceInfo = list.get(0);

					result = csdlDcServiceInfo;

					cacheResult(csdlDcServiceInfo);
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
			return (CsdlDcServiceInfo)result;
		}
	}

	/**
	 * Removes the csdl dc service info where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the csdl dc service info that was removed
	 */
	@Override
	public CsdlDcServiceInfo removeByUUID_G(String uuid, long groupId)
		throws NoSuchCsdlDcServiceInfoException {
		CsdlDcServiceInfo csdlDcServiceInfo = findByUUID_G(uuid, groupId);

		return remove(csdlDcServiceInfo);
	}

	/**
	 * Returns the number of csdl dc service infos where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching csdl dc service infos
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CSDLDCSERVICEINFO_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "csdlDcServiceInfo.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "csdlDcServiceInfo.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(csdlDcServiceInfo.uuid IS NULL OR csdlDcServiceInfo.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "csdlDcServiceInfo.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoModelImpl.FINDER_CACHE_ENABLED,
			CsdlDcServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoModelImpl.FINDER_CACHE_ENABLED,
			CsdlDcServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			CsdlDcServiceInfoModelImpl.UUID_COLUMN_BITMASK |
			CsdlDcServiceInfoModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the csdl dc service infos where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching csdl dc service infos
	 */
	@Override
	public List<CsdlDcServiceInfo> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the csdl dc service infos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of csdl dc service infos
	 * @param end the upper bound of the range of csdl dc service infos (not inclusive)
	 * @return the range of matching csdl dc service infos
	 */
	@Override
	public List<CsdlDcServiceInfo> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the csdl dc service infos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of csdl dc service infos
	 * @param end the upper bound of the range of csdl dc service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csdl dc service infos
	 */
	@Override
	public List<CsdlDcServiceInfo> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the csdl dc service infos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of csdl dc service infos
	 * @param end the upper bound of the range of csdl dc service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csdl dc service infos
	 */
	@Override
	public List<CsdlDcServiceInfo> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator,
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

		List<CsdlDcServiceInfo> list = null;

		if (retrieveFromCache) {
			list = (List<CsdlDcServiceInfo>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CsdlDcServiceInfo csdlDcServiceInfo : list) {
					if (!Objects.equals(uuid, csdlDcServiceInfo.getUuid()) ||
							(companyId != csdlDcServiceInfo.getCompanyId())) {
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

			query.append(_SQL_SELECT_CSDLDCSERVICEINFO_WHERE);

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
				query.append(CsdlDcServiceInfoModelImpl.ORDER_BY_JPQL);
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
					list = (List<CsdlDcServiceInfo>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CsdlDcServiceInfo>)QueryUtil.list(q,
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
	 * Returns the first csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csdl dc service info
	 * @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	 */
	@Override
	public CsdlDcServiceInfo findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws NoSuchCsdlDcServiceInfoException {
		CsdlDcServiceInfo csdlDcServiceInfo = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (csdlDcServiceInfo != null) {
			return csdlDcServiceInfo;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCsdlDcServiceInfoException(msg.toString());
	}

	/**
	 * Returns the first csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	 */
	@Override
	public CsdlDcServiceInfo fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		List<CsdlDcServiceInfo> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csdl dc service info
	 * @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	 */
	@Override
	public CsdlDcServiceInfo findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws NoSuchCsdlDcServiceInfoException {
		CsdlDcServiceInfo csdlDcServiceInfo = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (csdlDcServiceInfo != null) {
			return csdlDcServiceInfo;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCsdlDcServiceInfoException(msg.toString());
	}

	/**
	 * Returns the last csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	 */
	@Override
	public CsdlDcServiceInfo fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CsdlDcServiceInfo> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the csdl dc service infos before and after the current csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param idDcService the primary key of the current csdl dc service info
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csdl dc service info
	 * @throws NoSuchCsdlDcServiceInfoException if a csdl dc service info with the primary key could not be found
	 */
	@Override
	public CsdlDcServiceInfo[] findByUuid_C_PrevAndNext(long idDcService,
		String uuid, long companyId,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws NoSuchCsdlDcServiceInfoException {
		CsdlDcServiceInfo csdlDcServiceInfo = findByPrimaryKey(idDcService);

		Session session = null;

		try {
			session = openSession();

			CsdlDcServiceInfo[] array = new CsdlDcServiceInfoImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, csdlDcServiceInfo,
					uuid, companyId, orderByComparator, true);

			array[1] = csdlDcServiceInfo;

			array[2] = getByUuid_C_PrevAndNext(session, csdlDcServiceInfo,
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

	protected CsdlDcServiceInfo getByUuid_C_PrevAndNext(Session session,
		CsdlDcServiceInfo csdlDcServiceInfo, String uuid, long companyId,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CSDLDCSERVICEINFO_WHERE);

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
			query.append(CsdlDcServiceInfoModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(csdlDcServiceInfo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CsdlDcServiceInfo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the csdl dc service infos where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CsdlDcServiceInfo csdlDcServiceInfo : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(csdlDcServiceInfo);
		}
	}

	/**
	 * Returns the number of csdl dc service infos where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching csdl dc service infos
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CSDLDCSERVICEINFO_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "csdlDcServiceInfo.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "csdlDcServiceInfo.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(csdlDcServiceInfo.uuid IS NULL OR csdlDcServiceInfo.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "csdlDcServiceInfo.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_SERVICECODE_STATUS = new FinderPath(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoModelImpl.FINDER_CACHE_ENABLED,
			CsdlDcServiceInfoImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_SERVICECODE_STATUS",
			new String[] { String.class.getName(), Integer.class.getName() },
			CsdlDcServiceInfoModelImpl.SERVICECODE_COLUMN_BITMASK |
			CsdlDcServiceInfoModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_SERVICECODE_STATUS = new FinderPath(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_SERVICECODE_STATUS",
			new String[] { String.class.getName(), Integer.class.getName() });

	/**
	 * Returns the csdl dc service info where serviceCode = &#63; and status = &#63; or throws a {@link NoSuchCsdlDcServiceInfoException} if it could not be found.
	 *
	 * @param serviceCode the service code
	 * @param status the status
	 * @return the matching csdl dc service info
	 * @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	 */
	@Override
	public CsdlDcServiceInfo findByF_SERVICECODE_STATUS(String serviceCode,
		int status) throws NoSuchCsdlDcServiceInfoException {
		CsdlDcServiceInfo csdlDcServiceInfo = fetchByF_SERVICECODE_STATUS(serviceCode,
				status);

		if (csdlDcServiceInfo == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("serviceCode=");
			msg.append(serviceCode);

			msg.append(", status=");
			msg.append(status);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCsdlDcServiceInfoException(msg.toString());
		}

		return csdlDcServiceInfo;
	}

	/**
	 * Returns the csdl dc service info where serviceCode = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param serviceCode the service code
	 * @param status the status
	 * @return the matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	 */
	@Override
	public CsdlDcServiceInfo fetchByF_SERVICECODE_STATUS(String serviceCode,
		int status) {
		return fetchByF_SERVICECODE_STATUS(serviceCode, status, true);
	}

	/**
	 * Returns the csdl dc service info where serviceCode = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param serviceCode the service code
	 * @param status the status
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	 */
	@Override
	public CsdlDcServiceInfo fetchByF_SERVICECODE_STATUS(String serviceCode,
		int status, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { serviceCode, status };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_SERVICECODE_STATUS,
					finderArgs, this);
		}

		if (result instanceof CsdlDcServiceInfo) {
			CsdlDcServiceInfo csdlDcServiceInfo = (CsdlDcServiceInfo)result;

			if (!Objects.equals(serviceCode, csdlDcServiceInfo.getServiceCode()) ||
					(status != csdlDcServiceInfo.getStatus())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CSDLDCSERVICEINFO_WHERE);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_F_SERVICECODE_STATUS_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_F_SERVICECODE_STATUS_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_F_SERVICECODE_STATUS_SERVICECODE_2);
			}

			query.append(_FINDER_COLUMN_F_SERVICECODE_STATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				qPos.add(status);

				List<CsdlDcServiceInfo> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_SERVICECODE_STATUS,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CsdlDcServiceInfoPersistenceImpl.fetchByF_SERVICECODE_STATUS(String, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CsdlDcServiceInfo csdlDcServiceInfo = list.get(0);

					result = csdlDcServiceInfo;

					cacheResult(csdlDcServiceInfo);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_SERVICECODE_STATUS,
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
			return (CsdlDcServiceInfo)result;
		}
	}

	/**
	 * Removes the csdl dc service info where serviceCode = &#63; and status = &#63; from the database.
	 *
	 * @param serviceCode the service code
	 * @param status the status
	 * @return the csdl dc service info that was removed
	 */
	@Override
	public CsdlDcServiceInfo removeByF_SERVICECODE_STATUS(String serviceCode,
		int status) throws NoSuchCsdlDcServiceInfoException {
		CsdlDcServiceInfo csdlDcServiceInfo = findByF_SERVICECODE_STATUS(serviceCode,
				status);

		return remove(csdlDcServiceInfo);
	}

	/**
	 * Returns the number of csdl dc service infos where serviceCode = &#63; and status = &#63;.
	 *
	 * @param serviceCode the service code
	 * @param status the status
	 * @return the number of matching csdl dc service infos
	 */
	@Override
	public int countByF_SERVICECODE_STATUS(String serviceCode, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_SERVICECODE_STATUS;

		Object[] finderArgs = new Object[] { serviceCode, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CSDLDCSERVICEINFO_WHERE);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_F_SERVICECODE_STATUS_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_F_SERVICECODE_STATUS_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_F_SERVICECODE_STATUS_SERVICECODE_2);
			}

			query.append(_FINDER_COLUMN_F_SERVICECODE_STATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_F_SERVICECODE_STATUS_SERVICECODE_1 =
		"csdlDcServiceInfo.serviceCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_SERVICECODE_STATUS_SERVICECODE_2 =
		"csdlDcServiceInfo.serviceCode = ? AND ";
	private static final String _FINDER_COLUMN_F_SERVICECODE_STATUS_SERVICECODE_3 =
		"(csdlDcServiceInfo.serviceCode IS NULL OR csdlDcServiceInfo.serviceCode = '') AND ";
	private static final String _FINDER_COLUMN_F_SERVICECODE_STATUS_STATUS_2 = "csdlDcServiceInfo.status = ?";

	public CsdlDcServiceInfoPersistenceImpl() {
		setModelClass(CsdlDcServiceInfo.class);

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
	 * Caches the csdl dc service info in the entity cache if it is enabled.
	 *
	 * @param csdlDcServiceInfo the csdl dc service info
	 */
	@Override
	public void cacheResult(CsdlDcServiceInfo csdlDcServiceInfo) {
		entityCache.putResult(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoImpl.class, csdlDcServiceInfo.getPrimaryKey(),
			csdlDcServiceInfo);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				csdlDcServiceInfo.getUuid(), csdlDcServiceInfo.getGroupId()
			}, csdlDcServiceInfo);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_SERVICECODE_STATUS,
			new Object[] {
				csdlDcServiceInfo.getServiceCode(),
				csdlDcServiceInfo.getStatus()
			}, csdlDcServiceInfo);

		csdlDcServiceInfo.resetOriginalValues();
	}

	/**
	 * Caches the csdl dc service infos in the entity cache if it is enabled.
	 *
	 * @param csdlDcServiceInfos the csdl dc service infos
	 */
	@Override
	public void cacheResult(List<CsdlDcServiceInfo> csdlDcServiceInfos) {
		for (CsdlDcServiceInfo csdlDcServiceInfo : csdlDcServiceInfos) {
			if (entityCache.getResult(
						CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
						CsdlDcServiceInfoImpl.class,
						csdlDcServiceInfo.getPrimaryKey()) == null) {
				cacheResult(csdlDcServiceInfo);
			}
			else {
				csdlDcServiceInfo.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all csdl dc service infos.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CsdlDcServiceInfoImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the csdl dc service info.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CsdlDcServiceInfo csdlDcServiceInfo) {
		entityCache.removeResult(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoImpl.class, csdlDcServiceInfo.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CsdlDcServiceInfoModelImpl)csdlDcServiceInfo,
			true);
	}

	@Override
	public void clearCache(List<CsdlDcServiceInfo> csdlDcServiceInfos) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CsdlDcServiceInfo csdlDcServiceInfo : csdlDcServiceInfos) {
			entityCache.removeResult(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
				CsdlDcServiceInfoImpl.class, csdlDcServiceInfo.getPrimaryKey());

			clearUniqueFindersCache((CsdlDcServiceInfoModelImpl)csdlDcServiceInfo,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CsdlDcServiceInfoModelImpl csdlDcServiceInfoModelImpl) {
		Object[] args = new Object[] {
				csdlDcServiceInfoModelImpl.getUuid(),
				csdlDcServiceInfoModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			csdlDcServiceInfoModelImpl, false);

		args = new Object[] {
				csdlDcServiceInfoModelImpl.getServiceCode(),
				csdlDcServiceInfoModelImpl.getStatus()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_SERVICECODE_STATUS, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_SERVICECODE_STATUS, args,
			csdlDcServiceInfoModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CsdlDcServiceInfoModelImpl csdlDcServiceInfoModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					csdlDcServiceInfoModelImpl.getUuid(),
					csdlDcServiceInfoModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((csdlDcServiceInfoModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					csdlDcServiceInfoModelImpl.getOriginalUuid(),
					csdlDcServiceInfoModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					csdlDcServiceInfoModelImpl.getServiceCode(),
					csdlDcServiceInfoModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_SERVICECODE_STATUS,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_SERVICECODE_STATUS,
				args);
		}

		if ((csdlDcServiceInfoModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_SERVICECODE_STATUS.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					csdlDcServiceInfoModelImpl.getOriginalServiceCode(),
					csdlDcServiceInfoModelImpl.getOriginalStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_SERVICECODE_STATUS,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_SERVICECODE_STATUS,
				args);
		}
	}

	/**
	 * Creates a new csdl dc service info with the primary key. Does not add the csdl dc service info to the database.
	 *
	 * @param idDcService the primary key for the new csdl dc service info
	 * @return the new csdl dc service info
	 */
	@Override
	public CsdlDcServiceInfo create(long idDcService) {
		CsdlDcServiceInfo csdlDcServiceInfo = new CsdlDcServiceInfoImpl();

		csdlDcServiceInfo.setNew(true);
		csdlDcServiceInfo.setPrimaryKey(idDcService);

		String uuid = PortalUUIDUtil.generate();

		csdlDcServiceInfo.setUuid(uuid);

		csdlDcServiceInfo.setCompanyId(companyProvider.getCompanyId());

		return csdlDcServiceInfo;
	}

	/**
	 * Removes the csdl dc service info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param idDcService the primary key of the csdl dc service info
	 * @return the csdl dc service info that was removed
	 * @throws NoSuchCsdlDcServiceInfoException if a csdl dc service info with the primary key could not be found
	 */
	@Override
	public CsdlDcServiceInfo remove(long idDcService)
		throws NoSuchCsdlDcServiceInfoException {
		return remove((Serializable)idDcService);
	}

	/**
	 * Removes the csdl dc service info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the csdl dc service info
	 * @return the csdl dc service info that was removed
	 * @throws NoSuchCsdlDcServiceInfoException if a csdl dc service info with the primary key could not be found
	 */
	@Override
	public CsdlDcServiceInfo remove(Serializable primaryKey)
		throws NoSuchCsdlDcServiceInfoException {
		Session session = null;

		try {
			session = openSession();

			CsdlDcServiceInfo csdlDcServiceInfo = (CsdlDcServiceInfo)session.get(CsdlDcServiceInfoImpl.class,
					primaryKey);

			if (csdlDcServiceInfo == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCsdlDcServiceInfoException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(csdlDcServiceInfo);
		}
		catch (NoSuchCsdlDcServiceInfoException nsee) {
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
	protected CsdlDcServiceInfo removeImpl(CsdlDcServiceInfo csdlDcServiceInfo) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(csdlDcServiceInfo)) {
				csdlDcServiceInfo = (CsdlDcServiceInfo)session.get(CsdlDcServiceInfoImpl.class,
						csdlDcServiceInfo.getPrimaryKeyObj());
			}

			if (csdlDcServiceInfo != null) {
				session.delete(csdlDcServiceInfo);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (csdlDcServiceInfo != null) {
			clearCache(csdlDcServiceInfo);
		}

		return csdlDcServiceInfo;
	}

	@Override
	public CsdlDcServiceInfo updateImpl(CsdlDcServiceInfo csdlDcServiceInfo) {
		boolean isNew = csdlDcServiceInfo.isNew();

		if (!(csdlDcServiceInfo instanceof CsdlDcServiceInfoModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(csdlDcServiceInfo.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(csdlDcServiceInfo);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in csdlDcServiceInfo proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CsdlDcServiceInfo implementation " +
				csdlDcServiceInfo.getClass());
		}

		CsdlDcServiceInfoModelImpl csdlDcServiceInfoModelImpl = (CsdlDcServiceInfoModelImpl)csdlDcServiceInfo;

		if (Validator.isNull(csdlDcServiceInfo.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			csdlDcServiceInfo.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (csdlDcServiceInfo.isNew()) {
				session.save(csdlDcServiceInfo);

				csdlDcServiceInfo.setNew(false);
			}
			else {
				csdlDcServiceInfo = (CsdlDcServiceInfo)session.merge(csdlDcServiceInfo);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CsdlDcServiceInfoModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { csdlDcServiceInfoModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					csdlDcServiceInfoModelImpl.getUuid(),
					csdlDcServiceInfoModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((csdlDcServiceInfoModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						csdlDcServiceInfoModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { csdlDcServiceInfoModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((csdlDcServiceInfoModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						csdlDcServiceInfoModelImpl.getOriginalUuid(),
						csdlDcServiceInfoModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						csdlDcServiceInfoModelImpl.getUuid(),
						csdlDcServiceInfoModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcServiceInfoImpl.class, csdlDcServiceInfo.getPrimaryKey(),
			csdlDcServiceInfo, false);

		clearUniqueFindersCache(csdlDcServiceInfoModelImpl, false);
		cacheUniqueFindersCache(csdlDcServiceInfoModelImpl);

		csdlDcServiceInfo.resetOriginalValues();

		return csdlDcServiceInfo;
	}

	/**
	 * Returns the csdl dc service info with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the csdl dc service info
	 * @return the csdl dc service info
	 * @throws NoSuchCsdlDcServiceInfoException if a csdl dc service info with the primary key could not be found
	 */
	@Override
	public CsdlDcServiceInfo findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCsdlDcServiceInfoException {
		CsdlDcServiceInfo csdlDcServiceInfo = fetchByPrimaryKey(primaryKey);

		if (csdlDcServiceInfo == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCsdlDcServiceInfoException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return csdlDcServiceInfo;
	}

	/**
	 * Returns the csdl dc service info with the primary key or throws a {@link NoSuchCsdlDcServiceInfoException} if it could not be found.
	 *
	 * @param idDcService the primary key of the csdl dc service info
	 * @return the csdl dc service info
	 * @throws NoSuchCsdlDcServiceInfoException if a csdl dc service info with the primary key could not be found
	 */
	@Override
	public CsdlDcServiceInfo findByPrimaryKey(long idDcService)
		throws NoSuchCsdlDcServiceInfoException {
		return findByPrimaryKey((Serializable)idDcService);
	}

	/**
	 * Returns the csdl dc service info with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the csdl dc service info
	 * @return the csdl dc service info, or <code>null</code> if a csdl dc service info with the primary key could not be found
	 */
	@Override
	public CsdlDcServiceInfo fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
				CsdlDcServiceInfoImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CsdlDcServiceInfo csdlDcServiceInfo = (CsdlDcServiceInfo)serializable;

		if (csdlDcServiceInfo == null) {
			Session session = null;

			try {
				session = openSession();

				csdlDcServiceInfo = (CsdlDcServiceInfo)session.get(CsdlDcServiceInfoImpl.class,
						primaryKey);

				if (csdlDcServiceInfo != null) {
					cacheResult(csdlDcServiceInfo);
				}
				else {
					entityCache.putResult(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
						CsdlDcServiceInfoImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
					CsdlDcServiceInfoImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return csdlDcServiceInfo;
	}

	/**
	 * Returns the csdl dc service info with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param idDcService the primary key of the csdl dc service info
	 * @return the csdl dc service info, or <code>null</code> if a csdl dc service info with the primary key could not be found
	 */
	@Override
	public CsdlDcServiceInfo fetchByPrimaryKey(long idDcService) {
		return fetchByPrimaryKey((Serializable)idDcService);
	}

	@Override
	public Map<Serializable, CsdlDcServiceInfo> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CsdlDcServiceInfo> map = new HashMap<Serializable, CsdlDcServiceInfo>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CsdlDcServiceInfo csdlDcServiceInfo = fetchByPrimaryKey(primaryKey);

			if (csdlDcServiceInfo != null) {
				map.put(primaryKey, csdlDcServiceInfo);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
					CsdlDcServiceInfoImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CsdlDcServiceInfo)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CSDLDCSERVICEINFO_WHERE_PKS_IN);

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

			for (CsdlDcServiceInfo csdlDcServiceInfo : (List<CsdlDcServiceInfo>)q.list()) {
				map.put(csdlDcServiceInfo.getPrimaryKeyObj(), csdlDcServiceInfo);

				cacheResult(csdlDcServiceInfo);

				uncachedPrimaryKeys.remove(csdlDcServiceInfo.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CsdlDcServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
					CsdlDcServiceInfoImpl.class, primaryKey, nullModel);
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
	 * Returns all the csdl dc service infos.
	 *
	 * @return the csdl dc service infos
	 */
	@Override
	public List<CsdlDcServiceInfo> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the csdl dc service infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csdl dc service infos
	 * @param end the upper bound of the range of csdl dc service infos (not inclusive)
	 * @return the range of csdl dc service infos
	 */
	@Override
	public List<CsdlDcServiceInfo> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the csdl dc service infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csdl dc service infos
	 * @param end the upper bound of the range of csdl dc service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of csdl dc service infos
	 */
	@Override
	public List<CsdlDcServiceInfo> findAll(int start, int end,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the csdl dc service infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csdl dc service infos
	 * @param end the upper bound of the range of csdl dc service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of csdl dc service infos
	 */
	@Override
	public List<CsdlDcServiceInfo> findAll(int start, int end,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator,
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

		List<CsdlDcServiceInfo> list = null;

		if (retrieveFromCache) {
			list = (List<CsdlDcServiceInfo>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CSDLDCSERVICEINFO);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CSDLDCSERVICEINFO;

				if (pagination) {
					sql = sql.concat(CsdlDcServiceInfoModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CsdlDcServiceInfo>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CsdlDcServiceInfo>)QueryUtil.list(q,
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
	 * Removes all the csdl dc service infos from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CsdlDcServiceInfo csdlDcServiceInfo : findAll()) {
			remove(csdlDcServiceInfo);
		}
	}

	/**
	 * Returns the number of csdl dc service infos.
	 *
	 * @return the number of csdl dc service infos
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CSDLDCSERVICEINFO);

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
		return CsdlDcServiceInfoModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the csdl dc service info persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CsdlDcServiceInfoImpl.class.getName());
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
	private static final String _SQL_SELECT_CSDLDCSERVICEINFO = "SELECT csdlDcServiceInfo FROM CsdlDcServiceInfo csdlDcServiceInfo";
	private static final String _SQL_SELECT_CSDLDCSERVICEINFO_WHERE_PKS_IN = "SELECT csdlDcServiceInfo FROM CsdlDcServiceInfo csdlDcServiceInfo WHERE idDcService IN (";
	private static final String _SQL_SELECT_CSDLDCSERVICEINFO_WHERE = "SELECT csdlDcServiceInfo FROM CsdlDcServiceInfo csdlDcServiceInfo WHERE ";
	private static final String _SQL_COUNT_CSDLDCSERVICEINFO = "SELECT COUNT(csdlDcServiceInfo) FROM CsdlDcServiceInfo csdlDcServiceInfo";
	private static final String _SQL_COUNT_CSDLDCSERVICEINFO_WHERE = "SELECT COUNT(csdlDcServiceInfo) FROM CsdlDcServiceInfo csdlDcServiceInfo WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "csdlDcServiceInfo.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CsdlDcServiceInfo exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CsdlDcServiceInfo exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CsdlDcServiceInfoPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}