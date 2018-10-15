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

import org.opencps.dossiermgt.exception.NoSuchDossierMarkException;
import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.model.impl.DossierMarkImpl;
import org.opencps.dossiermgt.model.impl.DossierMarkModelImpl;
import org.opencps.dossiermgt.service.persistence.DossierMarkPersistence;

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
 * The persistence implementation for the dossier mark service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierMarkPersistence
 * @see org.opencps.dossiermgt.service.persistence.DossierMarkUtil
 * @generated
 */
@ProviderType
public class DossierMarkPersistenceImpl extends BasePersistenceImpl<DossierMark>
	implements DossierMarkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DossierMarkUtil} to access the dossier mark persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DossierMarkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, DossierMarkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, DossierMarkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, DossierMarkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, DossierMarkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DossierMarkModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dossier marks where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dossier marks
	 */
	@Override
	public List<DossierMark> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier marks where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @return the range of matching dossier marks
	 */
	@Override
	public List<DossierMark> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier marks where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier marks
	 */
	@Override
	public List<DossierMark> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierMark> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier marks where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier marks
	 */
	@Override
	public List<DossierMark> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierMark> orderByComparator,
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

		List<DossierMark> list = null;

		if (retrieveFromCache) {
			list = (List<DossierMark>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierMark dossierMark : list) {
					if (!Objects.equals(uuid, dossierMark.getUuid())) {
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

			query.append(_SQL_SELECT_DOSSIERMARK_WHERE);

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
				query.append(DossierMarkModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierMark>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierMark>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier mark in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier mark
	 * @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark findByUuid_First(String uuid,
		OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = fetchByUuid_First(uuid, orderByComparator);

		if (dossierMark != null) {
			return dossierMark;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierMarkException(msg.toString());
	}

	/**
	 * Returns the first dossier mark in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark fetchByUuid_First(String uuid,
		OrderByComparator<DossierMark> orderByComparator) {
		List<DossierMark> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier mark in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier mark
	 * @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark findByUuid_Last(String uuid,
		OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = fetchByUuid_Last(uuid, orderByComparator);

		if (dossierMark != null) {
			return dossierMark;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierMarkException(msg.toString());
	}

	/**
	 * Returns the last dossier mark in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark fetchByUuid_Last(String uuid,
		OrderByComparator<DossierMark> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DossierMark> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier marks before and after the current dossier mark in the ordered set where uuid = &#63;.
	 *
	 * @param dossierMarkId the primary key of the current dossier mark
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier mark
	 * @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	 */
	@Override
	public DossierMark[] findByUuid_PrevAndNext(long dossierMarkId,
		String uuid, OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = findByPrimaryKey(dossierMarkId);

		Session session = null;

		try {
			session = openSession();

			DossierMark[] array = new DossierMarkImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dossierMark, uuid,
					orderByComparator, true);

			array[1] = dossierMark;

			array[2] = getByUuid_PrevAndNext(session, dossierMark, uuid,
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

	protected DossierMark getByUuid_PrevAndNext(Session session,
		DossierMark dossierMark, String uuid,
		OrderByComparator<DossierMark> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERMARK_WHERE);

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
			query.append(DossierMarkModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierMark);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierMark> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier marks where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DossierMark dossierMark : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossierMark);
		}
	}

	/**
	 * Returns the number of dossier marks where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dossier marks
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERMARK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dossierMark.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dossierMark.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dossierMark.uuid IS NULL OR dossierMark.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, DossierMarkImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierMarkModelImpl.UUID_COLUMN_BITMASK |
			DossierMarkModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier mark where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierMarkException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier mark
	 * @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = fetchByUUID_G(uuid, groupId);

		if (dossierMark == null) {
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

			throw new NoSuchDossierMarkException(msg.toString());
		}

		return dossierMark;
	}

	/**
	 * Returns the dossier mark where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dossier mark where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DossierMark) {
			DossierMark dossierMark = (DossierMark)result;

			if (!Objects.equals(uuid, dossierMark.getUuid()) ||
					(groupId != dossierMark.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERMARK_WHERE);

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

				List<DossierMark> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DossierMark dossierMark = list.get(0);

					result = dossierMark;

					cacheResult(dossierMark);
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
			return (DossierMark)result;
		}
	}

	/**
	 * Removes the dossier mark where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dossier mark that was removed
	 */
	@Override
	public DossierMark removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = findByUUID_G(uuid, groupId);

		return remove(dossierMark);
	}

	/**
	 * Returns the number of dossier marks where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dossier marks
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERMARK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dossierMark.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dossierMark.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dossierMark.uuid IS NULL OR dossierMark.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dossierMark.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, DossierMarkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, DossierMarkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierMarkModelImpl.UUID_COLUMN_BITMASK |
			DossierMarkModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dossier marks where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dossier marks
	 */
	@Override
	public List<DossierMark> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier marks where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @return the range of matching dossier marks
	 */
	@Override
	public List<DossierMark> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier marks where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier marks
	 */
	@Override
	public List<DossierMark> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierMark> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier marks where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier marks
	 */
	@Override
	public List<DossierMark> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierMark> orderByComparator,
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

		List<DossierMark> list = null;

		if (retrieveFromCache) {
			list = (List<DossierMark>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierMark dossierMark : list) {
					if (!Objects.equals(uuid, dossierMark.getUuid()) ||
							(companyId != dossierMark.getCompanyId())) {
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

			query.append(_SQL_SELECT_DOSSIERMARK_WHERE);

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
				query.append(DossierMarkModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierMark>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierMark>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier mark
	 * @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (dossierMark != null) {
			return dossierMark;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDossierMarkException(msg.toString());
	}

	/**
	 * Returns the first dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierMark> orderByComparator) {
		List<DossierMark> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier mark
	 * @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (dossierMark != null) {
			return dossierMark;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDossierMarkException(msg.toString());
	}

	/**
	 * Returns the last dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierMark> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DossierMark> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier marks before and after the current dossier mark in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dossierMarkId the primary key of the current dossier mark
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier mark
	 * @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	 */
	@Override
	public DossierMark[] findByUuid_C_PrevAndNext(long dossierMarkId,
		String uuid, long companyId,
		OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = findByPrimaryKey(dossierMarkId);

		Session session = null;

		try {
			session = openSession();

			DossierMark[] array = new DossierMarkImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dossierMark, uuid,
					companyId, orderByComparator, true);

			array[1] = dossierMark;

			array[2] = getByUuid_C_PrevAndNext(session, dossierMark, uuid,
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

	protected DossierMark getByUuid_C_PrevAndNext(Session session,
		DossierMark dossierMark, String uuid, long companyId,
		OrderByComparator<DossierMark> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERMARK_WHERE);

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
			query.append(DossierMarkModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierMark);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierMark> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier marks where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DossierMark dossierMark : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierMark);
		}
	}

	/**
	 * Returns the number of dossier marks where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dossier marks
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERMARK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dossierMark.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dossierMark.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dossierMark.uuid IS NULL OR dossierMark.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dossierMark.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_DID_PN = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, DossierMarkImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_DID_PN",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			DossierMarkModelImpl.GROUPID_COLUMN_BITMASK |
			DossierMarkModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierMarkModelImpl.DOSSIERPARTNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_DID_PN = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_DID_PN",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the dossier mark where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63; or throws a {@link NoSuchDossierMarkException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @return the matching dossier mark
	 * @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark findByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo) throws NoSuchDossierMarkException {
		DossierMark dossierMark = fetchByG_DID_PN(groupId, dossierId,
				dossierPartNo);

		if (dossierMark == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", dossierId=");
			msg.append(dossierId);

			msg.append(", dossierPartNo=");
			msg.append(dossierPartNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierMarkException(msg.toString());
		}

		return dossierMark;
	}

	/**
	 * Returns the dossier mark where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @return the matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark fetchByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo) {
		return fetchByG_DID_PN(groupId, dossierId, dossierPartNo, true);
	}

	/**
	 * Returns the dossier mark where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark fetchByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, dossierId, dossierPartNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_DID_PN,
					finderArgs, this);
		}

		if (result instanceof DossierMark) {
			DossierMark dossierMark = (DossierMark)result;

			if ((groupId != dossierMark.getGroupId()) ||
					(dossierId != dossierMark.getDossierId()) ||
					!Objects.equals(dossierPartNo,
						dossierMark.getDossierPartNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DOSSIERMARK_WHERE);

			query.append(_FINDER_COLUMN_G_DID_PN_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_PN_DOSSIERID_2);

			boolean bindDossierPartNo = false;

			if (dossierPartNo == null) {
				query.append(_FINDER_COLUMN_G_DID_PN_DOSSIERPARTNO_1);
			}
			else if (dossierPartNo.equals("")) {
				query.append(_FINDER_COLUMN_G_DID_PN_DOSSIERPARTNO_3);
			}
			else {
				bindDossierPartNo = true;

				query.append(_FINDER_COLUMN_G_DID_PN_DOSSIERPARTNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				if (bindDossierPartNo) {
					qPos.add(dossierPartNo);
				}

				List<DossierMark> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_DID_PN,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierMarkPersistenceImpl.fetchByG_DID_PN(long, long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierMark dossierMark = list.get(0);

					result = dossierMark;

					cacheResult(dossierMark);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DID_PN,
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
			return (DossierMark)result;
		}
	}

	/**
	 * Removes the dossier mark where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @return the dossier mark that was removed
	 */
	@Override
	public DossierMark removeByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo) throws NoSuchDossierMarkException {
		DossierMark dossierMark = findByG_DID_PN(groupId, dossierId,
				dossierPartNo);

		return remove(dossierMark);
	}

	/**
	 * Returns the number of dossier marks where groupId = &#63; and dossierId = &#63; and dossierPartNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @return the number of matching dossier marks
	 */
	@Override
	public int countByG_DID_PN(long groupId, long dossierId,
		String dossierPartNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_DID_PN;

		Object[] finderArgs = new Object[] { groupId, dossierId, dossierPartNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERMARK_WHERE);

			query.append(_FINDER_COLUMN_G_DID_PN_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_PN_DOSSIERID_2);

			boolean bindDossierPartNo = false;

			if (dossierPartNo == null) {
				query.append(_FINDER_COLUMN_G_DID_PN_DOSSIERPARTNO_1);
			}
			else if (dossierPartNo.equals("")) {
				query.append(_FINDER_COLUMN_G_DID_PN_DOSSIERPARTNO_3);
			}
			else {
				bindDossierPartNo = true;

				query.append(_FINDER_COLUMN_G_DID_PN_DOSSIERPARTNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				if (bindDossierPartNo) {
					qPos.add(dossierPartNo);
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

	private static final String _FINDER_COLUMN_G_DID_PN_GROUPID_2 = "dossierMark.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_DID_PN_DOSSIERID_2 = "dossierMark.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_G_DID_PN_DOSSIERPARTNO_1 = "dossierMark.dossierPartNo IS NULL";
	private static final String _FINDER_COLUMN_G_DID_PN_DOSSIERPARTNO_2 = "dossierMark.dossierPartNo = ?";
	private static final String _FINDER_COLUMN_G_DID_PN_DOSSIERPARTNO_3 = "(dossierMark.dossierPartNo IS NULL OR dossierMark.dossierPartNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_DID = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, DossierMarkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_DID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, DossierMarkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_DID",
			new String[] { Long.class.getName(), Long.class.getName() },
			DossierMarkModelImpl.GROUPID_COLUMN_BITMASK |
			DossierMarkModelImpl.DOSSIERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_DID = new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_DID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dossier marks where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @return the matching dossier marks
	 */
	@Override
	public List<DossierMark> findByG_DID(long groupId, long dossierId) {
		return findByG_DID(groupId, dossierId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier marks where groupId = &#63; and dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @return the range of matching dossier marks
	 */
	@Override
	public List<DossierMark> findByG_DID(long groupId, long dossierId,
		int start, int end) {
		return findByG_DID(groupId, dossierId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier marks where groupId = &#63; and dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier marks
	 */
	@Override
	public List<DossierMark> findByG_DID(long groupId, long dossierId,
		int start, int end, OrderByComparator<DossierMark> orderByComparator) {
		return findByG_DID(groupId, dossierId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the dossier marks where groupId = &#63; and dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier marks
	 */
	@Override
	public List<DossierMark> findByG_DID(long groupId, long dossierId,
		int start, int end, OrderByComparator<DossierMark> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID;
			finderArgs = new Object[] { groupId, dossierId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_DID;
			finderArgs = new Object[] {
					groupId, dossierId,
					
					start, end, orderByComparator
				};
		}

		List<DossierMark> list = null;

		if (retrieveFromCache) {
			list = (List<DossierMark>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierMark dossierMark : list) {
					if ((groupId != dossierMark.getGroupId()) ||
							(dossierId != dossierMark.getDossierId())) {
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

			query.append(_SQL_SELECT_DOSSIERMARK_WHERE);

			query.append(_FINDER_COLUMN_G_DID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_DOSSIERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierMarkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				if (!pagination) {
					list = (List<DossierMark>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierMark>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier mark
	 * @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark findByG_DID_First(long groupId, long dossierId,
		OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = fetchByG_DID_First(groupId, dossierId,
				orderByComparator);

		if (dossierMark != null) {
			return dossierMark;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchDossierMarkException(msg.toString());
	}

	/**
	 * Returns the first dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark fetchByG_DID_First(long groupId, long dossierId,
		OrderByComparator<DossierMark> orderByComparator) {
		List<DossierMark> list = findByG_DID(groupId, dossierId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier mark
	 * @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark findByG_DID_Last(long groupId, long dossierId,
		OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = fetchByG_DID_Last(groupId, dossierId,
				orderByComparator);

		if (dossierMark != null) {
			return dossierMark;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchDossierMarkException(msg.toString());
	}

	/**
	 * Returns the last dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark fetchByG_DID_Last(long groupId, long dossierId,
		OrderByComparator<DossierMark> orderByComparator) {
		int count = countByG_DID(groupId, dossierId);

		if (count == 0) {
			return null;
		}

		List<DossierMark> list = findByG_DID(groupId, dossierId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier marks before and after the current dossier mark in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param dossierMarkId the primary key of the current dossier mark
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier mark
	 * @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	 */
	@Override
	public DossierMark[] findByG_DID_PrevAndNext(long dossierMarkId,
		long groupId, long dossierId,
		OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = findByPrimaryKey(dossierMarkId);

		Session session = null;

		try {
			session = openSession();

			DossierMark[] array = new DossierMarkImpl[3];

			array[0] = getByG_DID_PrevAndNext(session, dossierMark, groupId,
					dossierId, orderByComparator, true);

			array[1] = dossierMark;

			array[2] = getByG_DID_PrevAndNext(session, dossierMark, groupId,
					dossierId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierMark getByG_DID_PrevAndNext(Session session,
		DossierMark dossierMark, long groupId, long dossierId,
		OrderByComparator<DossierMark> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERMARK_WHERE);

		query.append(_FINDER_COLUMN_G_DID_GROUPID_2);

		query.append(_FINDER_COLUMN_G_DID_DOSSIERID_2);

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
			query.append(DossierMarkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(dossierId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierMark);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierMark> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier marks where groupId = &#63; and dossierId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 */
	@Override
	public void removeByG_DID(long groupId, long dossierId) {
		for (DossierMark dossierMark : findByG_DID(groupId, dossierId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierMark);
		}
	}

	/**
	 * Returns the number of dossier marks where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @return the number of matching dossier marks
	 */
	@Override
	public int countByG_DID(long groupId, long dossierId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_DID;

		Object[] finderArgs = new Object[] { groupId, dossierId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERMARK_WHERE);

			query.append(_FINDER_COLUMN_G_DID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_DOSSIERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

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

	private static final String _FINDER_COLUMN_G_DID_GROUPID_2 = "dossierMark.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_DID_DOSSIERID_2 = "dossierMark.dossierId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_DID_MARK =
		new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, DossierMarkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_DID_MARK",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_DID_MARK =
		new FinderPath(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_DID_MARK",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fileMark the file mark
	 * @return the matching dossier marks
	 */
	@Override
	public List<DossierMark> findByG_DID_MARK(long groupId, long dossierId,
		int fileMark) {
		return findByG_DID_MARK(groupId, dossierId, fileMark,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fileMark the file mark
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @return the range of matching dossier marks
	 */
	@Override
	public List<DossierMark> findByG_DID_MARK(long groupId, long dossierId,
		int fileMark, int start, int end) {
		return findByG_DID_MARK(groupId, dossierId, fileMark, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fileMark the file mark
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier marks
	 */
	@Override
	public List<DossierMark> findByG_DID_MARK(long groupId, long dossierId,
		int fileMark, int start, int end,
		OrderByComparator<DossierMark> orderByComparator) {
		return findByG_DID_MARK(groupId, dossierId, fileMark, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fileMark the file mark
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier marks
	 */
	@Override
	public List<DossierMark> findByG_DID_MARK(long groupId, long dossierId,
		int fileMark, int start, int end,
		OrderByComparator<DossierMark> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_DID_MARK;
		finderArgs = new Object[] {
				groupId, dossierId, fileMark,
				
				start, end, orderByComparator
			};

		List<DossierMark> list = null;

		if (retrieveFromCache) {
			list = (List<DossierMark>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierMark dossierMark : list) {
					if ((groupId != dossierMark.getGroupId()) ||
							(dossierId != dossierMark.getDossierId()) ||
							(fileMark == dossierMark.getFileMark())) {
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

			query.append(_SQL_SELECT_DOSSIERMARK_WHERE);

			query.append(_FINDER_COLUMN_G_DID_MARK_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_MARK_DOSSIERID_2);

			query.append(_FINDER_COLUMN_G_DID_MARK_FILEMARK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierMarkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				qPos.add(fileMark);

				if (!pagination) {
					list = (List<DossierMark>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierMark>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fileMark the file mark
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier mark
	 * @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark findByG_DID_MARK_First(long groupId, long dossierId,
		int fileMark, OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = fetchByG_DID_MARK_First(groupId, dossierId,
				fileMark, orderByComparator);

		if (dossierMark != null) {
			return dossierMark;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierId=");
		msg.append(dossierId);

		msg.append(", fileMark=");
		msg.append(fileMark);

		msg.append("}");

		throw new NoSuchDossierMarkException(msg.toString());
	}

	/**
	 * Returns the first dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fileMark the file mark
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark fetchByG_DID_MARK_First(long groupId, long dossierId,
		int fileMark, OrderByComparator<DossierMark> orderByComparator) {
		List<DossierMark> list = findByG_DID_MARK(groupId, dossierId, fileMark,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fileMark the file mark
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier mark
	 * @throws NoSuchDossierMarkException if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark findByG_DID_MARK_Last(long groupId, long dossierId,
		int fileMark, OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = fetchByG_DID_MARK_Last(groupId, dossierId,
				fileMark, orderByComparator);

		if (dossierMark != null) {
			return dossierMark;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierId=");
		msg.append(dossierId);

		msg.append(", fileMark=");
		msg.append(fileMark);

		msg.append("}");

		throw new NoSuchDossierMarkException(msg.toString());
	}

	/**
	 * Returns the last dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fileMark the file mark
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	 */
	@Override
	public DossierMark fetchByG_DID_MARK_Last(long groupId, long dossierId,
		int fileMark, OrderByComparator<DossierMark> orderByComparator) {
		int count = countByG_DID_MARK(groupId, dossierId, fileMark);

		if (count == 0) {
			return null;
		}

		List<DossierMark> list = findByG_DID_MARK(groupId, dossierId, fileMark,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier marks before and after the current dossier mark in the ordered set where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	 *
	 * @param dossierMarkId the primary key of the current dossier mark
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fileMark the file mark
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier mark
	 * @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	 */
	@Override
	public DossierMark[] findByG_DID_MARK_PrevAndNext(long dossierMarkId,
		long groupId, long dossierId, int fileMark,
		OrderByComparator<DossierMark> orderByComparator)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = findByPrimaryKey(dossierMarkId);

		Session session = null;

		try {
			session = openSession();

			DossierMark[] array = new DossierMarkImpl[3];

			array[0] = getByG_DID_MARK_PrevAndNext(session, dossierMark,
					groupId, dossierId, fileMark, orderByComparator, true);

			array[1] = dossierMark;

			array[2] = getByG_DID_MARK_PrevAndNext(session, dossierMark,
					groupId, dossierId, fileMark, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierMark getByG_DID_MARK_PrevAndNext(Session session,
		DossierMark dossierMark, long groupId, long dossierId, int fileMark,
		OrderByComparator<DossierMark> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_DOSSIERMARK_WHERE);

		query.append(_FINDER_COLUMN_G_DID_MARK_GROUPID_2);

		query.append(_FINDER_COLUMN_G_DID_MARK_DOSSIERID_2);

		query.append(_FINDER_COLUMN_G_DID_MARK_FILEMARK_2);

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
			query.append(DossierMarkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(dossierId);

		qPos.add(fileMark);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierMark);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierMark> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fileMark the file mark
	 */
	@Override
	public void removeByG_DID_MARK(long groupId, long dossierId, int fileMark) {
		for (DossierMark dossierMark : findByG_DID_MARK(groupId, dossierId,
				fileMark, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierMark);
		}
	}

	/**
	 * Returns the number of dossier marks where groupId = &#63; and dossierId = &#63; and fileMark &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fileMark the file mark
	 * @return the number of matching dossier marks
	 */
	@Override
	public int countByG_DID_MARK(long groupId, long dossierId, int fileMark) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_DID_MARK;

		Object[] finderArgs = new Object[] { groupId, dossierId, fileMark };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERMARK_WHERE);

			query.append(_FINDER_COLUMN_G_DID_MARK_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_MARK_DOSSIERID_2);

			query.append(_FINDER_COLUMN_G_DID_MARK_FILEMARK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				qPos.add(fileMark);

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

	private static final String _FINDER_COLUMN_G_DID_MARK_GROUPID_2 = "dossierMark.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_DID_MARK_DOSSIERID_2 = "dossierMark.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_G_DID_MARK_FILEMARK_2 = "dossierMark.fileMark != ?";

	public DossierMarkPersistenceImpl() {
		setModelClass(DossierMark.class);

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
	 * Caches the dossier mark in the entity cache if it is enabled.
	 *
	 * @param dossierMark the dossier mark
	 */
	@Override
	public void cacheResult(DossierMark dossierMark) {
		entityCache.putResult(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkImpl.class, dossierMark.getPrimaryKey(), dossierMark);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dossierMark.getUuid(), dossierMark.getGroupId() },
			dossierMark);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_DID_PN,
			new Object[] {
				dossierMark.getGroupId(), dossierMark.getDossierId(),
				dossierMark.getDossierPartNo()
			}, dossierMark);

		dossierMark.resetOriginalValues();
	}

	/**
	 * Caches the dossier marks in the entity cache if it is enabled.
	 *
	 * @param dossierMarks the dossier marks
	 */
	@Override
	public void cacheResult(List<DossierMark> dossierMarks) {
		for (DossierMark dossierMark : dossierMarks) {
			if (entityCache.getResult(
						DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
						DossierMarkImpl.class, dossierMark.getPrimaryKey()) == null) {
				cacheResult(dossierMark);
			}
			else {
				dossierMark.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dossier marks.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DossierMarkImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dossier mark.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DossierMark dossierMark) {
		entityCache.removeResult(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkImpl.class, dossierMark.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DossierMarkModelImpl)dossierMark, true);
	}

	@Override
	public void clearCache(List<DossierMark> dossierMarks) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DossierMark dossierMark : dossierMarks) {
			entityCache.removeResult(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
				DossierMarkImpl.class, dossierMark.getPrimaryKey());

			clearUniqueFindersCache((DossierMarkModelImpl)dossierMark, true);
		}
	}

	protected void cacheUniqueFindersCache(
		DossierMarkModelImpl dossierMarkModelImpl) {
		Object[] args = new Object[] {
				dossierMarkModelImpl.getUuid(),
				dossierMarkModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dossierMarkModelImpl, false);

		args = new Object[] {
				dossierMarkModelImpl.getGroupId(),
				dossierMarkModelImpl.getDossierId(),
				dossierMarkModelImpl.getDossierPartNo()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_DID_PN, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_DID_PN, args,
			dossierMarkModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DossierMarkModelImpl dossierMarkModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierMarkModelImpl.getUuid(),
					dossierMarkModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dossierMarkModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierMarkModelImpl.getOriginalUuid(),
					dossierMarkModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierMarkModelImpl.getGroupId(),
					dossierMarkModelImpl.getDossierId(),
					dossierMarkModelImpl.getDossierPartNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID_PN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DID_PN, args);
		}

		if ((dossierMarkModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_DID_PN.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierMarkModelImpl.getOriginalGroupId(),
					dossierMarkModelImpl.getOriginalDossierId(),
					dossierMarkModelImpl.getOriginalDossierPartNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID_PN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DID_PN, args);
		}
	}

	/**
	 * Creates a new dossier mark with the primary key. Does not add the dossier mark to the database.
	 *
	 * @param dossierMarkId the primary key for the new dossier mark
	 * @return the new dossier mark
	 */
	@Override
	public DossierMark create(long dossierMarkId) {
		DossierMark dossierMark = new DossierMarkImpl();

		dossierMark.setNew(true);
		dossierMark.setPrimaryKey(dossierMarkId);

		String uuid = PortalUUIDUtil.generate();

		dossierMark.setUuid(uuid);

		dossierMark.setCompanyId(companyProvider.getCompanyId());

		return dossierMark;
	}

	/**
	 * Removes the dossier mark with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dossierMarkId the primary key of the dossier mark
	 * @return the dossier mark that was removed
	 * @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	 */
	@Override
	public DossierMark remove(long dossierMarkId)
		throws NoSuchDossierMarkException {
		return remove((Serializable)dossierMarkId);
	}

	/**
	 * Removes the dossier mark with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dossier mark
	 * @return the dossier mark that was removed
	 * @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	 */
	@Override
	public DossierMark remove(Serializable primaryKey)
		throws NoSuchDossierMarkException {
		Session session = null;

		try {
			session = openSession();

			DossierMark dossierMark = (DossierMark)session.get(DossierMarkImpl.class,
					primaryKey);

			if (dossierMark == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDossierMarkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dossierMark);
		}
		catch (NoSuchDossierMarkException nsee) {
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
	protected DossierMark removeImpl(DossierMark dossierMark) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dossierMark)) {
				dossierMark = (DossierMark)session.get(DossierMarkImpl.class,
						dossierMark.getPrimaryKeyObj());
			}

			if (dossierMark != null) {
				session.delete(dossierMark);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dossierMark != null) {
			clearCache(dossierMark);
		}

		return dossierMark;
	}

	@Override
	public DossierMark updateImpl(DossierMark dossierMark) {
		boolean isNew = dossierMark.isNew();

		if (!(dossierMark instanceof DossierMarkModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dossierMark.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dossierMark);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dossierMark proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DossierMark implementation " +
				dossierMark.getClass());
		}

		DossierMarkModelImpl dossierMarkModelImpl = (DossierMarkModelImpl)dossierMark;

		if (Validator.isNull(dossierMark.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dossierMark.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dossierMark.getCreateDate() == null)) {
			if (serviceContext == null) {
				dossierMark.setCreateDate(now);
			}
			else {
				dossierMark.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dossierMarkModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dossierMark.setModifiedDate(now);
			}
			else {
				dossierMark.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dossierMark.isNew()) {
				session.save(dossierMark);

				dossierMark.setNew(false);
			}
			else {
				dossierMark = (DossierMark)session.merge(dossierMark);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DossierMarkModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dossierMarkModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					dossierMarkModelImpl.getUuid(),
					dossierMarkModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					dossierMarkModelImpl.getGroupId(),
					dossierMarkModelImpl.getDossierId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dossierMarkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierMarkModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dossierMarkModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dossierMarkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierMarkModelImpl.getOriginalUuid(),
						dossierMarkModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dossierMarkModelImpl.getUuid(),
						dossierMarkModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((dossierMarkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierMarkModelImpl.getOriginalGroupId(),
						dossierMarkModelImpl.getOriginalDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID,
					args);

				args = new Object[] {
						dossierMarkModelImpl.getGroupId(),
						dossierMarkModelImpl.getDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID,
					args);
			}
		}

		entityCache.putResult(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
			DossierMarkImpl.class, dossierMark.getPrimaryKey(), dossierMark,
			false);

		clearUniqueFindersCache(dossierMarkModelImpl, false);
		cacheUniqueFindersCache(dossierMarkModelImpl);

		dossierMark.resetOriginalValues();

		return dossierMark;
	}

	/**
	 * Returns the dossier mark with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier mark
	 * @return the dossier mark
	 * @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	 */
	@Override
	public DossierMark findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDossierMarkException {
		DossierMark dossierMark = fetchByPrimaryKey(primaryKey);

		if (dossierMark == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDossierMarkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dossierMark;
	}

	/**
	 * Returns the dossier mark with the primary key or throws a {@link NoSuchDossierMarkException} if it could not be found.
	 *
	 * @param dossierMarkId the primary key of the dossier mark
	 * @return the dossier mark
	 * @throws NoSuchDossierMarkException if a dossier mark with the primary key could not be found
	 */
	@Override
	public DossierMark findByPrimaryKey(long dossierMarkId)
		throws NoSuchDossierMarkException {
		return findByPrimaryKey((Serializable)dossierMarkId);
	}

	/**
	 * Returns the dossier mark with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier mark
	 * @return the dossier mark, or <code>null</code> if a dossier mark with the primary key could not be found
	 */
	@Override
	public DossierMark fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
				DossierMarkImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DossierMark dossierMark = (DossierMark)serializable;

		if (dossierMark == null) {
			Session session = null;

			try {
				session = openSession();

				dossierMark = (DossierMark)session.get(DossierMarkImpl.class,
						primaryKey);

				if (dossierMark != null) {
					cacheResult(dossierMark);
				}
				else {
					entityCache.putResult(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
						DossierMarkImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
					DossierMarkImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dossierMark;
	}

	/**
	 * Returns the dossier mark with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dossierMarkId the primary key of the dossier mark
	 * @return the dossier mark, or <code>null</code> if a dossier mark with the primary key could not be found
	 */
	@Override
	public DossierMark fetchByPrimaryKey(long dossierMarkId) {
		return fetchByPrimaryKey((Serializable)dossierMarkId);
	}

	@Override
	public Map<Serializable, DossierMark> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DossierMark> map = new HashMap<Serializable, DossierMark>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DossierMark dossierMark = fetchByPrimaryKey(primaryKey);

			if (dossierMark != null) {
				map.put(primaryKey, dossierMark);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
					DossierMarkImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DossierMark)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DOSSIERMARK_WHERE_PKS_IN);

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

			for (DossierMark dossierMark : (List<DossierMark>)q.list()) {
				map.put(dossierMark.getPrimaryKeyObj(), dossierMark);

				cacheResult(dossierMark);

				uncachedPrimaryKeys.remove(dossierMark.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DossierMarkModelImpl.ENTITY_CACHE_ENABLED,
					DossierMarkImpl.class, primaryKey, nullModel);
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
	 * Returns all the dossier marks.
	 *
	 * @return the dossier marks
	 */
	@Override
	public List<DossierMark> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier marks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @return the range of dossier marks
	 */
	@Override
	public List<DossierMark> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier marks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dossier marks
	 */
	@Override
	public List<DossierMark> findAll(int start, int end,
		OrderByComparator<DossierMark> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier marks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier marks
	 * @param end the upper bound of the range of dossier marks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dossier marks
	 */
	@Override
	public List<DossierMark> findAll(int start, int end,
		OrderByComparator<DossierMark> orderByComparator,
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

		List<DossierMark> list = null;

		if (retrieveFromCache) {
			list = (List<DossierMark>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOSSIERMARK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOSSIERMARK;

				if (pagination) {
					sql = sql.concat(DossierMarkModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DossierMark>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierMark>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the dossier marks from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DossierMark dossierMark : findAll()) {
			remove(dossierMark);
		}
	}

	/**
	 * Returns the number of dossier marks.
	 *
	 * @return the number of dossier marks
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOSSIERMARK);

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
		return DossierMarkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dossier mark persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DossierMarkImpl.class.getName());
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
	private static final String _SQL_SELECT_DOSSIERMARK = "SELECT dossierMark FROM DossierMark dossierMark";
	private static final String _SQL_SELECT_DOSSIERMARK_WHERE_PKS_IN = "SELECT dossierMark FROM DossierMark dossierMark WHERE dossierMarkId IN (";
	private static final String _SQL_SELECT_DOSSIERMARK_WHERE = "SELECT dossierMark FROM DossierMark dossierMark WHERE ";
	private static final String _SQL_COUNT_DOSSIERMARK = "SELECT COUNT(dossierMark) FROM DossierMark dossierMark";
	private static final String _SQL_COUNT_DOSSIERMARK_WHERE = "SELECT COUNT(dossierMark) FROM DossierMark dossierMark WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dossierMark.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DossierMark exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DossierMark exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DossierMarkPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}