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

package org.opencps.usermgt.service.persistence.impl;

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

import org.opencps.usermgt.exception.NoSuchOfficeSiteException;
import org.opencps.usermgt.model.OfficeSite;
import org.opencps.usermgt.model.impl.OfficeSiteImpl;
import org.opencps.usermgt.model.impl.OfficeSiteModelImpl;
import org.opencps.usermgt.service.persistence.OfficeSitePersistence;

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
 * The persistence implementation for the office site service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see OfficeSitePersistence
 * @see org.opencps.usermgt.service.persistence.OfficeSiteUtil
 * @generated
 */
@ProviderType
public class OfficeSitePersistenceImpl extends BasePersistenceImpl<OfficeSite>
	implements OfficeSitePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OfficeSiteUtil} to access the office site persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OfficeSiteImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteModelImpl.FINDER_CACHE_ENABLED, OfficeSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteModelImpl.FINDER_CACHE_ENABLED, OfficeSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteModelImpl.FINDER_CACHE_ENABLED, OfficeSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteModelImpl.FINDER_CACHE_ENABLED, OfficeSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			OfficeSiteModelImpl.UUID_COLUMN_BITMASK |
			OfficeSiteModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the office sites where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching office sites
	 */
	@Override
	public List<OfficeSite> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the office sites where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of office sites
	 * @param end the upper bound of the range of office sites (not inclusive)
	 * @return the range of matching office sites
	 */
	@Override
	public List<OfficeSite> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the office sites where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of office sites
	 * @param end the upper bound of the range of office sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching office sites
	 */
	@Override
	public List<OfficeSite> findByUuid(String uuid, int start, int end,
		OrderByComparator<OfficeSite> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the office sites where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of office sites
	 * @param end the upper bound of the range of office sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching office sites
	 */
	@Override
	public List<OfficeSite> findByUuid(String uuid, int start, int end,
		OrderByComparator<OfficeSite> orderByComparator,
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

		List<OfficeSite> list = null;

		if (retrieveFromCache) {
			list = (List<OfficeSite>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OfficeSite officeSite : list) {
					if (!Objects.equals(uuid, officeSite.getUuid())) {
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

			query.append(_SQL_SELECT_OFFICESITE_WHERE);

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
				query.append(OfficeSiteModelImpl.ORDER_BY_JPQL);
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
					list = (List<OfficeSite>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OfficeSite>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first office site in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching office site
	 * @throws NoSuchOfficeSiteException if a matching office site could not be found
	 */
	@Override
	public OfficeSite findByUuid_First(String uuid,
		OrderByComparator<OfficeSite> orderByComparator)
		throws NoSuchOfficeSiteException {
		OfficeSite officeSite = fetchByUuid_First(uuid, orderByComparator);

		if (officeSite != null) {
			return officeSite;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOfficeSiteException(msg.toString());
	}

	/**
	 * Returns the first office site in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching office site, or <code>null</code> if a matching office site could not be found
	 */
	@Override
	public OfficeSite fetchByUuid_First(String uuid,
		OrderByComparator<OfficeSite> orderByComparator) {
		List<OfficeSite> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last office site in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching office site
	 * @throws NoSuchOfficeSiteException if a matching office site could not be found
	 */
	@Override
	public OfficeSite findByUuid_Last(String uuid,
		OrderByComparator<OfficeSite> orderByComparator)
		throws NoSuchOfficeSiteException {
		OfficeSite officeSite = fetchByUuid_Last(uuid, orderByComparator);

		if (officeSite != null) {
			return officeSite;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOfficeSiteException(msg.toString());
	}

	/**
	 * Returns the last office site in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching office site, or <code>null</code> if a matching office site could not be found
	 */
	@Override
	public OfficeSite fetchByUuid_Last(String uuid,
		OrderByComparator<OfficeSite> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<OfficeSite> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the office sites before and after the current office site in the ordered set where uuid = &#63;.
	 *
	 * @param officeSiteId the primary key of the current office site
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next office site
	 * @throws NoSuchOfficeSiteException if a office site with the primary key could not be found
	 */
	@Override
	public OfficeSite[] findByUuid_PrevAndNext(long officeSiteId, String uuid,
		OrderByComparator<OfficeSite> orderByComparator)
		throws NoSuchOfficeSiteException {
		OfficeSite officeSite = findByPrimaryKey(officeSiteId);

		Session session = null;

		try {
			session = openSession();

			OfficeSite[] array = new OfficeSiteImpl[3];

			array[0] = getByUuid_PrevAndNext(session, officeSite, uuid,
					orderByComparator, true);

			array[1] = officeSite;

			array[2] = getByUuid_PrevAndNext(session, officeSite, uuid,
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

	protected OfficeSite getByUuid_PrevAndNext(Session session,
		OfficeSite officeSite, String uuid,
		OrderByComparator<OfficeSite> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFICESITE_WHERE);

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
			query.append(OfficeSiteModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(officeSite);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfficeSite> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the office sites where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (OfficeSite officeSite : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(officeSite);
		}
	}

	/**
	 * Returns the number of office sites where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching office sites
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OFFICESITE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "officeSite.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "officeSite.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(officeSite.uuid IS NULL OR officeSite.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteModelImpl.FINDER_CACHE_ENABLED, OfficeSiteImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			OfficeSiteModelImpl.UUID_COLUMN_BITMASK |
			OfficeSiteModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the office site where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOfficeSiteException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching office site
	 * @throws NoSuchOfficeSiteException if a matching office site could not be found
	 */
	@Override
	public OfficeSite findByUUID_G(String uuid, long groupId)
		throws NoSuchOfficeSiteException {
		OfficeSite officeSite = fetchByUUID_G(uuid, groupId);

		if (officeSite == null) {
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

			throw new NoSuchOfficeSiteException(msg.toString());
		}

		return officeSite;
	}

	/**
	 * Returns the office site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching office site, or <code>null</code> if a matching office site could not be found
	 */
	@Override
	public OfficeSite fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the office site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching office site, or <code>null</code> if a matching office site could not be found
	 */
	@Override
	public OfficeSite fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof OfficeSite) {
			OfficeSite officeSite = (OfficeSite)result;

			if (!Objects.equals(uuid, officeSite.getUuid()) ||
					(groupId != officeSite.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OFFICESITE_WHERE);

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

				List<OfficeSite> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					OfficeSite officeSite = list.get(0);

					result = officeSite;

					cacheResult(officeSite);
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
			return (OfficeSite)result;
		}
	}

	/**
	 * Removes the office site where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the office site that was removed
	 */
	@Override
	public OfficeSite removeByUUID_G(String uuid, long groupId)
		throws NoSuchOfficeSiteException {
		OfficeSite officeSite = findByUUID_G(uuid, groupId);

		return remove(officeSite);
	}

	/**
	 * Returns the number of office sites where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching office sites
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OFFICESITE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "officeSite.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "officeSite.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(officeSite.uuid IS NULL OR officeSite.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "officeSite.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteModelImpl.FINDER_CACHE_ENABLED, OfficeSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteModelImpl.FINDER_CACHE_ENABLED, OfficeSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			OfficeSiteModelImpl.UUID_COLUMN_BITMASK |
			OfficeSiteModelImpl.COMPANYID_COLUMN_BITMASK |
			OfficeSiteModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the office sites where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching office sites
	 */
	@Override
	public List<OfficeSite> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the office sites where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of office sites
	 * @param end the upper bound of the range of office sites (not inclusive)
	 * @return the range of matching office sites
	 */
	@Override
	public List<OfficeSite> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the office sites where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of office sites
	 * @param end the upper bound of the range of office sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching office sites
	 */
	@Override
	public List<OfficeSite> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<OfficeSite> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the office sites where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of office sites
	 * @param end the upper bound of the range of office sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching office sites
	 */
	@Override
	public List<OfficeSite> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<OfficeSite> orderByComparator,
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

		List<OfficeSite> list = null;

		if (retrieveFromCache) {
			list = (List<OfficeSite>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OfficeSite officeSite : list) {
					if (!Objects.equals(uuid, officeSite.getUuid()) ||
							(companyId != officeSite.getCompanyId())) {
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

			query.append(_SQL_SELECT_OFFICESITE_WHERE);

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
				query.append(OfficeSiteModelImpl.ORDER_BY_JPQL);
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
					list = (List<OfficeSite>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OfficeSite>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first office site in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching office site
	 * @throws NoSuchOfficeSiteException if a matching office site could not be found
	 */
	@Override
	public OfficeSite findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<OfficeSite> orderByComparator)
		throws NoSuchOfficeSiteException {
		OfficeSite officeSite = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (officeSite != null) {
			return officeSite;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOfficeSiteException(msg.toString());
	}

	/**
	 * Returns the first office site in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching office site, or <code>null</code> if a matching office site could not be found
	 */
	@Override
	public OfficeSite fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<OfficeSite> orderByComparator) {
		List<OfficeSite> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last office site in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching office site
	 * @throws NoSuchOfficeSiteException if a matching office site could not be found
	 */
	@Override
	public OfficeSite findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<OfficeSite> orderByComparator)
		throws NoSuchOfficeSiteException {
		OfficeSite officeSite = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (officeSite != null) {
			return officeSite;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOfficeSiteException(msg.toString());
	}

	/**
	 * Returns the last office site in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching office site, or <code>null</code> if a matching office site could not be found
	 */
	@Override
	public OfficeSite fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<OfficeSite> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<OfficeSite> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the office sites before and after the current office site in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param officeSiteId the primary key of the current office site
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next office site
	 * @throws NoSuchOfficeSiteException if a office site with the primary key could not be found
	 */
	@Override
	public OfficeSite[] findByUuid_C_PrevAndNext(long officeSiteId,
		String uuid, long companyId,
		OrderByComparator<OfficeSite> orderByComparator)
		throws NoSuchOfficeSiteException {
		OfficeSite officeSite = findByPrimaryKey(officeSiteId);

		Session session = null;

		try {
			session = openSession();

			OfficeSite[] array = new OfficeSiteImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, officeSite, uuid,
					companyId, orderByComparator, true);

			array[1] = officeSite;

			array[2] = getByUuid_C_PrevAndNext(session, officeSite, uuid,
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

	protected OfficeSite getByUuid_C_PrevAndNext(Session session,
		OfficeSite officeSite, String uuid, long companyId,
		OrderByComparator<OfficeSite> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_OFFICESITE_WHERE);

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
			query.append(OfficeSiteModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(officeSite);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfficeSite> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the office sites where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (OfficeSite officeSite : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(officeSite);
		}
	}

	/**
	 * Returns the number of office sites where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching office sites
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OFFICESITE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "officeSite.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "officeSite.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(officeSite.uuid IS NULL OR officeSite.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "officeSite.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GROUPID_SITEGROUPID = new FinderPath(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteModelImpl.FINDER_CACHE_ENABLED, OfficeSiteImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_groupId_siteGroupId",
			new String[] { Long.class.getName(), Long.class.getName() },
			OfficeSiteModelImpl.GROUPID_COLUMN_BITMASK |
			OfficeSiteModelImpl.SITEGROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GROUPID_SITEGROUPID = new FinderPath(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_groupId_siteGroupId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the office site where groupId = &#63; and siteGroupId = &#63; or throws a {@link NoSuchOfficeSiteException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param siteGroupId the site group ID
	 * @return the matching office site
	 * @throws NoSuchOfficeSiteException if a matching office site could not be found
	 */
	@Override
	public OfficeSite findByF_groupId_siteGroupId(long groupId, long siteGroupId)
		throws NoSuchOfficeSiteException {
		OfficeSite officeSite = fetchByF_groupId_siteGroupId(groupId,
				siteGroupId);

		if (officeSite == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", siteGroupId=");
			msg.append(siteGroupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchOfficeSiteException(msg.toString());
		}

		return officeSite;
	}

	/**
	 * Returns the office site where groupId = &#63; and siteGroupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param siteGroupId the site group ID
	 * @return the matching office site, or <code>null</code> if a matching office site could not be found
	 */
	@Override
	public OfficeSite fetchByF_groupId_siteGroupId(long groupId,
		long siteGroupId) {
		return fetchByF_groupId_siteGroupId(groupId, siteGroupId, true);
	}

	/**
	 * Returns the office site where groupId = &#63; and siteGroupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param siteGroupId the site group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching office site, or <code>null</code> if a matching office site could not be found
	 */
	@Override
	public OfficeSite fetchByF_groupId_siteGroupId(long groupId,
		long siteGroupId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, siteGroupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GROUPID_SITEGROUPID,
					finderArgs, this);
		}

		if (result instanceof OfficeSite) {
			OfficeSite officeSite = (OfficeSite)result;

			if ((groupId != officeSite.getGroupId()) ||
					(siteGroupId != officeSite.getSiteGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OFFICESITE_WHERE);

			query.append(_FINDER_COLUMN_F_GROUPID_SITEGROUPID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GROUPID_SITEGROUPID_SITEGROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(siteGroupId);

				List<OfficeSite> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GROUPID_SITEGROUPID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OfficeSitePersistenceImpl.fetchByF_groupId_siteGroupId(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OfficeSite officeSite = list.get(0);

					result = officeSite;

					cacheResult(officeSite);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GROUPID_SITEGROUPID,
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
			return (OfficeSite)result;
		}
	}

	/**
	 * Removes the office site where groupId = &#63; and siteGroupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param siteGroupId the site group ID
	 * @return the office site that was removed
	 */
	@Override
	public OfficeSite removeByF_groupId_siteGroupId(long groupId,
		long siteGroupId) throws NoSuchOfficeSiteException {
		OfficeSite officeSite = findByF_groupId_siteGroupId(groupId, siteGroupId);

		return remove(officeSite);
	}

	/**
	 * Returns the number of office sites where groupId = &#63; and siteGroupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param siteGroupId the site group ID
	 * @return the number of matching office sites
	 */
	@Override
	public int countByF_groupId_siteGroupId(long groupId, long siteGroupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GROUPID_SITEGROUPID;

		Object[] finderArgs = new Object[] { groupId, siteGroupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OFFICESITE_WHERE);

			query.append(_FINDER_COLUMN_F_GROUPID_SITEGROUPID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GROUPID_SITEGROUPID_SITEGROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(siteGroupId);

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

	private static final String _FINDER_COLUMN_F_GROUPID_SITEGROUPID_GROUPID_2 = "officeSite.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GROUPID_SITEGROUPID_SITEGROUPID_2 =
		"officeSite.siteGroupId = ?";

	public OfficeSitePersistenceImpl() {
		setModelClass(OfficeSite.class);

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
	 * Caches the office site in the entity cache if it is enabled.
	 *
	 * @param officeSite the office site
	 */
	@Override
	public void cacheResult(OfficeSite officeSite) {
		entityCache.putResult(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteImpl.class, officeSite.getPrimaryKey(), officeSite);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { officeSite.getUuid(), officeSite.getGroupId() },
			officeSite);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GROUPID_SITEGROUPID,
			new Object[] { officeSite.getGroupId(), officeSite.getSiteGroupId() },
			officeSite);

		officeSite.resetOriginalValues();
	}

	/**
	 * Caches the office sites in the entity cache if it is enabled.
	 *
	 * @param officeSites the office sites
	 */
	@Override
	public void cacheResult(List<OfficeSite> officeSites) {
		for (OfficeSite officeSite : officeSites) {
			if (entityCache.getResult(
						OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
						OfficeSiteImpl.class, officeSite.getPrimaryKey()) == null) {
				cacheResult(officeSite);
			}
			else {
				officeSite.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all office sites.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OfficeSiteImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the office site.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OfficeSite officeSite) {
		entityCache.removeResult(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteImpl.class, officeSite.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((OfficeSiteModelImpl)officeSite, true);
	}

	@Override
	public void clearCache(List<OfficeSite> officeSites) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OfficeSite officeSite : officeSites) {
			entityCache.removeResult(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
				OfficeSiteImpl.class, officeSite.getPrimaryKey());

			clearUniqueFindersCache((OfficeSiteModelImpl)officeSite, true);
		}
	}

	protected void cacheUniqueFindersCache(
		OfficeSiteModelImpl officeSiteModelImpl) {
		Object[] args = new Object[] {
				officeSiteModelImpl.getUuid(), officeSiteModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			officeSiteModelImpl, false);

		args = new Object[] {
				officeSiteModelImpl.getGroupId(),
				officeSiteModelImpl.getSiteGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GROUPID_SITEGROUPID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GROUPID_SITEGROUPID, args,
			officeSiteModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OfficeSiteModelImpl officeSiteModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					officeSiteModelImpl.getUuid(),
					officeSiteModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((officeSiteModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					officeSiteModelImpl.getOriginalUuid(),
					officeSiteModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					officeSiteModelImpl.getGroupId(),
					officeSiteModelImpl.getSiteGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID_SITEGROUPID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GROUPID_SITEGROUPID,
				args);
		}

		if ((officeSiteModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GROUPID_SITEGROUPID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					officeSiteModelImpl.getOriginalGroupId(),
					officeSiteModelImpl.getOriginalSiteGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID_SITEGROUPID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GROUPID_SITEGROUPID,
				args);
		}
	}

	/**
	 * Creates a new office site with the primary key. Does not add the office site to the database.
	 *
	 * @param officeSiteId the primary key for the new office site
	 * @return the new office site
	 */
	@Override
	public OfficeSite create(long officeSiteId) {
		OfficeSite officeSite = new OfficeSiteImpl();

		officeSite.setNew(true);
		officeSite.setPrimaryKey(officeSiteId);

		String uuid = PortalUUIDUtil.generate();

		officeSite.setUuid(uuid);

		officeSite.setCompanyId(companyProvider.getCompanyId());

		return officeSite;
	}

	/**
	 * Removes the office site with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param officeSiteId the primary key of the office site
	 * @return the office site that was removed
	 * @throws NoSuchOfficeSiteException if a office site with the primary key could not be found
	 */
	@Override
	public OfficeSite remove(long officeSiteId)
		throws NoSuchOfficeSiteException {
		return remove((Serializable)officeSiteId);
	}

	/**
	 * Removes the office site with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the office site
	 * @return the office site that was removed
	 * @throws NoSuchOfficeSiteException if a office site with the primary key could not be found
	 */
	@Override
	public OfficeSite remove(Serializable primaryKey)
		throws NoSuchOfficeSiteException {
		Session session = null;

		try {
			session = openSession();

			OfficeSite officeSite = (OfficeSite)session.get(OfficeSiteImpl.class,
					primaryKey);

			if (officeSite == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOfficeSiteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(officeSite);
		}
		catch (NoSuchOfficeSiteException nsee) {
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
	protected OfficeSite removeImpl(OfficeSite officeSite) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(officeSite)) {
				officeSite = (OfficeSite)session.get(OfficeSiteImpl.class,
						officeSite.getPrimaryKeyObj());
			}

			if (officeSite != null) {
				session.delete(officeSite);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (officeSite != null) {
			clearCache(officeSite);
		}

		return officeSite;
	}

	@Override
	public OfficeSite updateImpl(OfficeSite officeSite) {
		boolean isNew = officeSite.isNew();

		if (!(officeSite instanceof OfficeSiteModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(officeSite.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(officeSite);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in officeSite proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OfficeSite implementation " +
				officeSite.getClass());
		}

		OfficeSiteModelImpl officeSiteModelImpl = (OfficeSiteModelImpl)officeSite;

		if (Validator.isNull(officeSite.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			officeSite.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (officeSite.getCreateDate() == null)) {
			if (serviceContext == null) {
				officeSite.setCreateDate(now);
			}
			else {
				officeSite.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!officeSiteModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				officeSite.setModifiedDate(now);
			}
			else {
				officeSite.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (officeSite.isNew()) {
				session.save(officeSite);

				officeSite.setNew(false);
			}
			else {
				officeSite = (OfficeSite)session.merge(officeSite);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OfficeSiteModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { officeSiteModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					officeSiteModelImpl.getUuid(),
					officeSiteModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((officeSiteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						officeSiteModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { officeSiteModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((officeSiteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						officeSiteModelImpl.getOriginalUuid(),
						officeSiteModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						officeSiteModelImpl.getUuid(),
						officeSiteModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
			OfficeSiteImpl.class, officeSite.getPrimaryKey(), officeSite, false);

		clearUniqueFindersCache(officeSiteModelImpl, false);
		cacheUniqueFindersCache(officeSiteModelImpl);

		officeSite.resetOriginalValues();

		return officeSite;
	}

	/**
	 * Returns the office site with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the office site
	 * @return the office site
	 * @throws NoSuchOfficeSiteException if a office site with the primary key could not be found
	 */
	@Override
	public OfficeSite findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOfficeSiteException {
		OfficeSite officeSite = fetchByPrimaryKey(primaryKey);

		if (officeSite == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOfficeSiteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return officeSite;
	}

	/**
	 * Returns the office site with the primary key or throws a {@link NoSuchOfficeSiteException} if it could not be found.
	 *
	 * @param officeSiteId the primary key of the office site
	 * @return the office site
	 * @throws NoSuchOfficeSiteException if a office site with the primary key could not be found
	 */
	@Override
	public OfficeSite findByPrimaryKey(long officeSiteId)
		throws NoSuchOfficeSiteException {
		return findByPrimaryKey((Serializable)officeSiteId);
	}

	/**
	 * Returns the office site with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the office site
	 * @return the office site, or <code>null</code> if a office site with the primary key could not be found
	 */
	@Override
	public OfficeSite fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
				OfficeSiteImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OfficeSite officeSite = (OfficeSite)serializable;

		if (officeSite == null) {
			Session session = null;

			try {
				session = openSession();

				officeSite = (OfficeSite)session.get(OfficeSiteImpl.class,
						primaryKey);

				if (officeSite != null) {
					cacheResult(officeSite);
				}
				else {
					entityCache.putResult(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
						OfficeSiteImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
					OfficeSiteImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return officeSite;
	}

	/**
	 * Returns the office site with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param officeSiteId the primary key of the office site
	 * @return the office site, or <code>null</code> if a office site with the primary key could not be found
	 */
	@Override
	public OfficeSite fetchByPrimaryKey(long officeSiteId) {
		return fetchByPrimaryKey((Serializable)officeSiteId);
	}

	@Override
	public Map<Serializable, OfficeSite> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OfficeSite> map = new HashMap<Serializable, OfficeSite>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OfficeSite officeSite = fetchByPrimaryKey(primaryKey);

			if (officeSite != null) {
				map.put(primaryKey, officeSite);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
					OfficeSiteImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OfficeSite)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OFFICESITE_WHERE_PKS_IN);

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

			for (OfficeSite officeSite : (List<OfficeSite>)q.list()) {
				map.put(officeSite.getPrimaryKeyObj(), officeSite);

				cacheResult(officeSite);

				uncachedPrimaryKeys.remove(officeSite.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OfficeSiteModelImpl.ENTITY_CACHE_ENABLED,
					OfficeSiteImpl.class, primaryKey, nullModel);
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
	 * Returns all the office sites.
	 *
	 * @return the office sites
	 */
	@Override
	public List<OfficeSite> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the office sites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of office sites
	 * @param end the upper bound of the range of office sites (not inclusive)
	 * @return the range of office sites
	 */
	@Override
	public List<OfficeSite> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the office sites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of office sites
	 * @param end the upper bound of the range of office sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of office sites
	 */
	@Override
	public List<OfficeSite> findAll(int start, int end,
		OrderByComparator<OfficeSite> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the office sites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of office sites
	 * @param end the upper bound of the range of office sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of office sites
	 */
	@Override
	public List<OfficeSite> findAll(int start, int end,
		OrderByComparator<OfficeSite> orderByComparator,
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

		List<OfficeSite> list = null;

		if (retrieveFromCache) {
			list = (List<OfficeSite>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OFFICESITE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OFFICESITE;

				if (pagination) {
					sql = sql.concat(OfficeSiteModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OfficeSite>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OfficeSite>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the office sites from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OfficeSite officeSite : findAll()) {
			remove(officeSite);
		}
	}

	/**
	 * Returns the number of office sites.
	 *
	 * @return the number of office sites
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OFFICESITE);

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
		return OfficeSiteModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the office site persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OfficeSiteImpl.class.getName());
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
	private static final String _SQL_SELECT_OFFICESITE = "SELECT officeSite FROM OfficeSite officeSite";
	private static final String _SQL_SELECT_OFFICESITE_WHERE_PKS_IN = "SELECT officeSite FROM OfficeSite officeSite WHERE officeSiteId IN (";
	private static final String _SQL_SELECT_OFFICESITE_WHERE = "SELECT officeSite FROM OfficeSite officeSite WHERE ";
	private static final String _SQL_COUNT_OFFICESITE = "SELECT COUNT(officeSite) FROM OfficeSite officeSite";
	private static final String _SQL_COUNT_OFFICESITE_WHERE = "SELECT COUNT(officeSite) FROM OfficeSite officeSite WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "officeSite.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OfficeSite exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OfficeSite exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OfficeSitePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}