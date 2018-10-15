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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchDossierSyncException;
import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.dossiermgt.model.impl.DossierSyncImpl;
import org.opencps.dossiermgt.model.impl.DossierSyncModelImpl;
import org.opencps.dossiermgt.service.persistence.DossierSyncPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Arrays;
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
 * The persistence implementation for the dossier sync service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierSyncPersistence
 * @see org.opencps.dossiermgt.service.persistence.DossierSyncUtil
 * @generated
 */
@ProviderType
public class DossierSyncPersistenceImpl extends BasePersistenceImpl<DossierSync>
	implements DossierSyncPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DossierSyncUtil} to access the dossier sync persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DossierSyncImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, DossierSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, DossierSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, DossierSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, DossierSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DossierSyncModelImpl.UUID_COLUMN_BITMASK |
			DossierSyncModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dossier syncs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier syncs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @return the range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier syncs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier syncs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierSync> orderByComparator,
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

		List<DossierSync> list = null;

		if (retrieveFromCache) {
			list = (List<DossierSync>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierSync dossierSync : list) {
					if (!Objects.equals(uuid, dossierSync.getUuid())) {
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

			query.append(_SQL_SELECT_DOSSIERSYNC_WHERE);

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
				query.append(DossierSyncModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierSync>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierSync>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier sync in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier sync
	 * @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync findByUuid_First(String uuid,
		OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = fetchByUuid_First(uuid, orderByComparator);

		if (dossierSync != null) {
			return dossierSync;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierSyncException(msg.toString());
	}

	/**
	 * Returns the first dossier sync in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync fetchByUuid_First(String uuid,
		OrderByComparator<DossierSync> orderByComparator) {
		List<DossierSync> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier sync in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier sync
	 * @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync findByUuid_Last(String uuid,
		OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = fetchByUuid_Last(uuid, orderByComparator);

		if (dossierSync != null) {
			return dossierSync;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierSyncException(msg.toString());
	}

	/**
	 * Returns the last dossier sync in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync fetchByUuid_Last(String uuid,
		OrderByComparator<DossierSync> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DossierSync> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier syncs before and after the current dossier sync in the ordered set where uuid = &#63;.
	 *
	 * @param DossierSyncId the primary key of the current dossier sync
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier sync
	 * @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	 */
	@Override
	public DossierSync[] findByUuid_PrevAndNext(long DossierSyncId,
		String uuid, OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = findByPrimaryKey(DossierSyncId);

		Session session = null;

		try {
			session = openSession();

			DossierSync[] array = new DossierSyncImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dossierSync, uuid,
					orderByComparator, true);

			array[1] = dossierSync;

			array[2] = getByUuid_PrevAndNext(session, dossierSync, uuid,
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

	protected DossierSync getByUuid_PrevAndNext(Session session,
		DossierSync dossierSync, String uuid,
		OrderByComparator<DossierSync> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERSYNC_WHERE);

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
			query.append(DossierSyncModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierSync);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierSync> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier syncs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DossierSync dossierSync : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossierSync);
		}
	}

	/**
	 * Returns the number of dossier syncs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dossier syncs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERSYNC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dossierSync.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dossierSync.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dossierSync.uuid IS NULL OR dossierSync.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, DossierSyncImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierSyncModelImpl.UUID_COLUMN_BITMASK |
			DossierSyncModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier sync where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierSyncException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier sync
	 * @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = fetchByUUID_G(uuid, groupId);

		if (dossierSync == null) {
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

			throw new NoSuchDossierSyncException(msg.toString());
		}

		return dossierSync;
	}

	/**
	 * Returns the dossier sync where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dossier sync where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DossierSync) {
			DossierSync dossierSync = (DossierSync)result;

			if (!Objects.equals(uuid, dossierSync.getUuid()) ||
					(groupId != dossierSync.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERSYNC_WHERE);

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

				List<DossierSync> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DossierSync dossierSync = list.get(0);

					result = dossierSync;

					cacheResult(dossierSync);
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
			return (DossierSync)result;
		}
	}

	/**
	 * Removes the dossier sync where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dossier sync that was removed
	 */
	@Override
	public DossierSync removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = findByUUID_G(uuid, groupId);

		return remove(dossierSync);
	}

	/**
	 * Returns the number of dossier syncs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dossier syncs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERSYNC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dossierSync.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dossierSync.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dossierSync.uuid IS NULL OR dossierSync.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dossierSync.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ST = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, DossierSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByST",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ST = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, DossierSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByST",
			new String[] { Integer.class.getName() },
			DossierSyncModelImpl.STATE_COLUMN_BITMASK |
			DossierSyncModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ST = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByST",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the dossier syncs where state = &#63;.
	 *
	 * @param state the state
	 * @return the matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByST(int state) {
		return findByST(state, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier syncs where state = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param state the state
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @return the range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByST(int state, int start, int end) {
		return findByST(state, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier syncs where state = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param state the state
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByST(int state, int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return findByST(state, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier syncs where state = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param state the state
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByST(int state, int start, int end,
		OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ST;
			finderArgs = new Object[] { state };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ST;
			finderArgs = new Object[] { state, start, end, orderByComparator };
		}

		List<DossierSync> list = null;

		if (retrieveFromCache) {
			list = (List<DossierSync>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierSync dossierSync : list) {
					if ((state != dossierSync.getState())) {
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

			query.append(_SQL_SELECT_DOSSIERSYNC_WHERE);

			query.append(_FINDER_COLUMN_ST_STATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierSyncModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(state);

				if (!pagination) {
					list = (List<DossierSync>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierSync>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier sync in the ordered set where state = &#63;.
	 *
	 * @param state the state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier sync
	 * @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync findByST_First(int state,
		OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = fetchByST_First(state, orderByComparator);

		if (dossierSync != null) {
			return dossierSync;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("state=");
		msg.append(state);

		msg.append("}");

		throw new NoSuchDossierSyncException(msg.toString());
	}

	/**
	 * Returns the first dossier sync in the ordered set where state = &#63;.
	 *
	 * @param state the state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync fetchByST_First(int state,
		OrderByComparator<DossierSync> orderByComparator) {
		List<DossierSync> list = findByST(state, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier sync in the ordered set where state = &#63;.
	 *
	 * @param state the state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier sync
	 * @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync findByST_Last(int state,
		OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = fetchByST_Last(state, orderByComparator);

		if (dossierSync != null) {
			return dossierSync;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("state=");
		msg.append(state);

		msg.append("}");

		throw new NoSuchDossierSyncException(msg.toString());
	}

	/**
	 * Returns the last dossier sync in the ordered set where state = &#63;.
	 *
	 * @param state the state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync fetchByST_Last(int state,
		OrderByComparator<DossierSync> orderByComparator) {
		int count = countByST(state);

		if (count == 0) {
			return null;
		}

		List<DossierSync> list = findByST(state, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier syncs before and after the current dossier sync in the ordered set where state = &#63;.
	 *
	 * @param DossierSyncId the primary key of the current dossier sync
	 * @param state the state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier sync
	 * @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	 */
	@Override
	public DossierSync[] findByST_PrevAndNext(long DossierSyncId, int state,
		OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = findByPrimaryKey(DossierSyncId);

		Session session = null;

		try {
			session = openSession();

			DossierSync[] array = new DossierSyncImpl[3];

			array[0] = getByST_PrevAndNext(session, dossierSync, state,
					orderByComparator, true);

			array[1] = dossierSync;

			array[2] = getByST_PrevAndNext(session, dossierSync, state,
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

	protected DossierSync getByST_PrevAndNext(Session session,
		DossierSync dossierSync, int state,
		OrderByComparator<DossierSync> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERSYNC_WHERE);

		query.append(_FINDER_COLUMN_ST_STATE_2);

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
			query.append(DossierSyncModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(state);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierSync);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierSync> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier syncs where state = &#63; from the database.
	 *
	 * @param state the state
	 */
	@Override
	public void removeByST(int state) {
		for (DossierSync dossierSync : findByST(state, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossierSync);
		}
	}

	/**
	 * Returns the number of dossier syncs where state = &#63;.
	 *
	 * @param state the state
	 * @return the number of matching dossier syncs
	 */
	@Override
	public int countByST(int state) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ST;

		Object[] finderArgs = new Object[] { state };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERSYNC_WHERE);

			query.append(_FINDER_COLUMN_ST_STATE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(state);

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

	private static final String _FINDER_COLUMN_ST_STATE_2 = "dossierSync.state = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DRID_IT = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, DossierSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDRID_IT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DRID_IT =
		new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, DossierSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDRID_IT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			DossierSyncModelImpl.GROUPID_COLUMN_BITMASK |
			DossierSyncModelImpl.DOSSIERREFUID_COLUMN_BITMASK |
			DossierSyncModelImpl.INFOTYPE_COLUMN_BITMASK |
			DossierSyncModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DRID_IT = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDRID_IT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_DRID_IT = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByDRID_IT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoType the info type
	 * @return the matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByDRID_IT(long groupId, String dossierRefUid,
		int infoType) {
		return findByDRID_IT(groupId, dossierRefUid, infoType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoType the info type
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @return the range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByDRID_IT(long groupId, String dossierRefUid,
		int infoType, int start, int end) {
		return findByDRID_IT(groupId, dossierRefUid, infoType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoType the info type
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByDRID_IT(long groupId, String dossierRefUid,
		int infoType, int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return findByDRID_IT(groupId, dossierRefUid, infoType, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoType the info type
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByDRID_IT(long groupId, String dossierRefUid,
		int infoType, int start, int end,
		OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DRID_IT;
			finderArgs = new Object[] { groupId, dossierRefUid, infoType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DRID_IT;
			finderArgs = new Object[] {
					groupId, dossierRefUid, infoType,
					
					start, end, orderByComparator
				};
		}

		List<DossierSync> list = null;

		if (retrieveFromCache) {
			list = (List<DossierSync>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierSync dossierSync : list) {
					if ((groupId != dossierSync.getGroupId()) ||
							!Objects.equals(dossierRefUid,
								dossierSync.getDossierRefUid()) ||
							(infoType != dossierSync.getInfoType())) {
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

			query.append(_SQL_SELECT_DOSSIERSYNC_WHERE);

			query.append(_FINDER_COLUMN_DRID_IT_GROUPID_2);

			boolean bindDossierRefUid = false;

			if (dossierRefUid == null) {
				query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_1);
			}
			else if (dossierRefUid.equals("")) {
				query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_3);
			}
			else {
				bindDossierRefUid = true;

				query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_2);
			}

			query.append(_FINDER_COLUMN_DRID_IT_INFOTYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierSyncModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierRefUid) {
					qPos.add(dossierRefUid);
				}

				qPos.add(infoType);

				if (!pagination) {
					list = (List<DossierSync>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierSync>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoType the info type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier sync
	 * @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync findByDRID_IT_First(long groupId, String dossierRefUid,
		int infoType, OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = fetchByDRID_IT_First(groupId, dossierRefUid,
				infoType, orderByComparator);

		if (dossierSync != null) {
			return dossierSync;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierRefUid=");
		msg.append(dossierRefUid);

		msg.append(", infoType=");
		msg.append(infoType);

		msg.append("}");

		throw new NoSuchDossierSyncException(msg.toString());
	}

	/**
	 * Returns the first dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoType the info type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync fetchByDRID_IT_First(long groupId, String dossierRefUid,
		int infoType, OrderByComparator<DossierSync> orderByComparator) {
		List<DossierSync> list = findByDRID_IT(groupId, dossierRefUid,
				infoType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoType the info type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier sync
	 * @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync findByDRID_IT_Last(long groupId, String dossierRefUid,
		int infoType, OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = fetchByDRID_IT_Last(groupId, dossierRefUid,
				infoType, orderByComparator);

		if (dossierSync != null) {
			return dossierSync;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierRefUid=");
		msg.append(dossierRefUid);

		msg.append(", infoType=");
		msg.append(infoType);

		msg.append("}");

		throw new NoSuchDossierSyncException(msg.toString());
	}

	/**
	 * Returns the last dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoType the info type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync fetchByDRID_IT_Last(long groupId, String dossierRefUid,
		int infoType, OrderByComparator<DossierSync> orderByComparator) {
		int count = countByDRID_IT(groupId, dossierRefUid, infoType);

		if (count == 0) {
			return null;
		}

		List<DossierSync> list = findByDRID_IT(groupId, dossierRefUid,
				infoType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier syncs before and after the current dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	 *
	 * @param DossierSyncId the primary key of the current dossier sync
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoType the info type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier sync
	 * @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	 */
	@Override
	public DossierSync[] findByDRID_IT_PrevAndNext(long DossierSyncId,
		long groupId, String dossierRefUid, int infoType,
		OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = findByPrimaryKey(DossierSyncId);

		Session session = null;

		try {
			session = openSession();

			DossierSync[] array = new DossierSyncImpl[3];

			array[0] = getByDRID_IT_PrevAndNext(session, dossierSync, groupId,
					dossierRefUid, infoType, orderByComparator, true);

			array[1] = dossierSync;

			array[2] = getByDRID_IT_PrevAndNext(session, dossierSync, groupId,
					dossierRefUid, infoType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierSync getByDRID_IT_PrevAndNext(Session session,
		DossierSync dossierSync, long groupId, String dossierRefUid,
		int infoType, OrderByComparator<DossierSync> orderByComparator,
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

		query.append(_SQL_SELECT_DOSSIERSYNC_WHERE);

		query.append(_FINDER_COLUMN_DRID_IT_GROUPID_2);

		boolean bindDossierRefUid = false;

		if (dossierRefUid == null) {
			query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_1);
		}
		else if (dossierRefUid.equals("")) {
			query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_3);
		}
		else {
			bindDossierRefUid = true;

			query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_2);
		}

		query.append(_FINDER_COLUMN_DRID_IT_INFOTYPE_2);

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
			query.append(DossierSyncModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindDossierRefUid) {
			qPos.add(dossierRefUid);
		}

		qPos.add(infoType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierSync);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierSync> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoTypes the info types
	 * @return the matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByDRID_IT(long groupId, String dossierRefUid,
		int[] infoTypes) {
		return findByDRID_IT(groupId, dossierRefUid, infoTypes,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoTypes the info types
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @return the range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByDRID_IT(long groupId, String dossierRefUid,
		int[] infoTypes, int start, int end) {
		return findByDRID_IT(groupId, dossierRefUid, infoTypes, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoTypes the info types
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByDRID_IT(long groupId, String dossierRefUid,
		int[] infoTypes, int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return findByDRID_IT(groupId, dossierRefUid, infoTypes, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoType the info type
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByDRID_IT(long groupId, String dossierRefUid,
		int[] infoTypes, int start, int end,
		OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache) {
		if (infoTypes == null) {
			infoTypes = new int[0];
		}
		else if (infoTypes.length > 1) {
			infoTypes = ArrayUtil.unique(infoTypes);

			Arrays.sort(infoTypes);
		}

		if (infoTypes.length == 1) {
			return findByDRID_IT(groupId, dossierRefUid, infoTypes[0], start,
				end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, dossierRefUid, StringUtil.merge(infoTypes)
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, dossierRefUid, StringUtil.merge(infoTypes),
					
					start, end, orderByComparator
				};
		}

		List<DossierSync> list = null;

		if (retrieveFromCache) {
			list = (List<DossierSync>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_DRID_IT,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierSync dossierSync : list) {
					if ((groupId != dossierSync.getGroupId()) ||
							!Objects.equals(dossierRefUid,
								dossierSync.getDossierRefUid()) ||
							!ArrayUtil.contains(infoTypes,
								dossierSync.getInfoType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_DOSSIERSYNC_WHERE);

			query.append(_FINDER_COLUMN_DRID_IT_GROUPID_2);

			boolean bindDossierRefUid = false;

			if (dossierRefUid == null) {
				query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_1);
			}
			else if (dossierRefUid.equals("")) {
				query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_3);
			}
			else {
				bindDossierRefUid = true;

				query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_2);
			}

			if (infoTypes.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_DRID_IT_INFOTYPE_7);

				query.append(StringUtil.merge(infoTypes));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierSyncModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierRefUid) {
					qPos.add(dossierRefUid);
				}

				if (!pagination) {
					list = (List<DossierSync>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierSync>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_DRID_IT,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_DRID_IT,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoType the info type
	 */
	@Override
	public void removeByDRID_IT(long groupId, String dossierRefUid, int infoType) {
		for (DossierSync dossierSync : findByDRID_IT(groupId, dossierRefUid,
				infoType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierSync);
		}
	}

	/**
	 * Returns the number of dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoType the info type
	 * @return the number of matching dossier syncs
	 */
	@Override
	public int countByDRID_IT(long groupId, String dossierRefUid, int infoType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DRID_IT;

		Object[] finderArgs = new Object[] { groupId, dossierRefUid, infoType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERSYNC_WHERE);

			query.append(_FINDER_COLUMN_DRID_IT_GROUPID_2);

			boolean bindDossierRefUid = false;

			if (dossierRefUid == null) {
				query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_1);
			}
			else if (dossierRefUid.equals("")) {
				query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_3);
			}
			else {
				bindDossierRefUid = true;

				query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_2);
			}

			query.append(_FINDER_COLUMN_DRID_IT_INFOTYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierRefUid) {
					qPos.add(dossierRefUid);
				}

				qPos.add(infoType);

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

	/**
	 * Returns the number of dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierRefUid the dossier ref uid
	 * @param infoTypes the info types
	 * @return the number of matching dossier syncs
	 */
	@Override
	public int countByDRID_IT(long groupId, String dossierRefUid,
		int[] infoTypes) {
		if (infoTypes == null) {
			infoTypes = new int[0];
		}
		else if (infoTypes.length > 1) {
			infoTypes = ArrayUtil.unique(infoTypes);

			Arrays.sort(infoTypes);
		}

		Object[] finderArgs = new Object[] {
				groupId, dossierRefUid, StringUtil.merge(infoTypes)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_DRID_IT,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_DOSSIERSYNC_WHERE);

			query.append(_FINDER_COLUMN_DRID_IT_GROUPID_2);

			boolean bindDossierRefUid = false;

			if (dossierRefUid == null) {
				query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_1);
			}
			else if (dossierRefUid.equals("")) {
				query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_3);
			}
			else {
				bindDossierRefUid = true;

				query.append(_FINDER_COLUMN_DRID_IT_DOSSIERREFUID_2);
			}

			if (infoTypes.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_DRID_IT_INFOTYPE_7);

				query.append(StringUtil.merge(infoTypes));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierRefUid) {
					qPos.add(dossierRefUid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_DRID_IT,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_DRID_IT,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_DRID_IT_GROUPID_2 = "dossierSync.groupId = ? AND ";
	private static final String _FINDER_COLUMN_DRID_IT_DOSSIERREFUID_1 = "dossierSync.dossierRefUid IS NULL AND ";
	private static final String _FINDER_COLUMN_DRID_IT_DOSSIERREFUID_2 = "dossierSync.dossierRefUid = ? AND ";
	private static final String _FINDER_COLUMN_DRID_IT_DOSSIERREFUID_3 = "(dossierSync.dossierRefUid IS NULL OR dossierSync.dossierRefUid = '') AND ";
	private static final String _FINDER_COLUMN_DRID_IT_INFOTYPE_2 = "dossierSync.infoType = ?";
	private static final String _FINDER_COLUMN_DRID_IT_INFOTYPE_7 = "dossierSync.infoType IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_AC_ST_IT =
		new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, DossierSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_AC_ST_IT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_AC_ST_IT =
		new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, DossierSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_AC_ST_IT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			DossierSyncModelImpl.GROUPID_COLUMN_BITMASK |
			DossierSyncModelImpl.ACTIONCODE_COLUMN_BITMASK |
			DossierSyncModelImpl.SYNCTYPE_COLUMN_BITMASK |
			DossierSyncModelImpl.INFOTYPE_COLUMN_BITMASK |
			DossierSyncModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_AC_ST_IT = new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_AC_ST_IT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_AC_ST_IT =
		new FinderPath(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_AC_ST_IT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoType the info type
	 * @return the matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByG_AC_ST_IT(long groupId, String actionCode,
		int syncType, int infoType) {
		return findByG_AC_ST_IT(groupId, actionCode, syncType, infoType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoType the info type
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @return the range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByG_AC_ST_IT(long groupId, String actionCode,
		int syncType, int infoType, int start, int end) {
		return findByG_AC_ST_IT(groupId, actionCode, syncType, infoType, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoType the info type
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByG_AC_ST_IT(long groupId, String actionCode,
		int syncType, int infoType, int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return findByG_AC_ST_IT(groupId, actionCode, syncType, infoType, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoType the info type
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByG_AC_ST_IT(long groupId, String actionCode,
		int syncType, int infoType, int start, int end,
		OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_AC_ST_IT;
			finderArgs = new Object[] { groupId, actionCode, syncType, infoType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_AC_ST_IT;
			finderArgs = new Object[] {
					groupId, actionCode, syncType, infoType,
					
					start, end, orderByComparator
				};
		}

		List<DossierSync> list = null;

		if (retrieveFromCache) {
			list = (List<DossierSync>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierSync dossierSync : list) {
					if ((groupId != dossierSync.getGroupId()) ||
							!Objects.equals(actionCode,
								dossierSync.getActionCode()) ||
							(syncType != dossierSync.getSyncType()) ||
							(infoType != dossierSync.getInfoType())) {
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

			query.append(_SQL_SELECT_DOSSIERSYNC_WHERE);

			query.append(_FINDER_COLUMN_G_AC_ST_IT_GROUPID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_2);
			}

			query.append(_FINDER_COLUMN_G_AC_ST_IT_SYNCTYPE_2);

			query.append(_FINDER_COLUMN_G_AC_ST_IT_INFOTYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierSyncModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindActionCode) {
					qPos.add(actionCode);
				}

				qPos.add(syncType);

				qPos.add(infoType);

				if (!pagination) {
					list = (List<DossierSync>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierSync>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoType the info type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier sync
	 * @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync findByG_AC_ST_IT_First(long groupId, String actionCode,
		int syncType, int infoType,
		OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = fetchByG_AC_ST_IT_First(groupId, actionCode,
				syncType, infoType, orderByComparator);

		if (dossierSync != null) {
			return dossierSync;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", actionCode=");
		msg.append(actionCode);

		msg.append(", syncType=");
		msg.append(syncType);

		msg.append(", infoType=");
		msg.append(infoType);

		msg.append("}");

		throw new NoSuchDossierSyncException(msg.toString());
	}

	/**
	 * Returns the first dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoType the info type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync fetchByG_AC_ST_IT_First(long groupId, String actionCode,
		int syncType, int infoType,
		OrderByComparator<DossierSync> orderByComparator) {
		List<DossierSync> list = findByG_AC_ST_IT(groupId, actionCode,
				syncType, infoType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoType the info type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier sync
	 * @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync findByG_AC_ST_IT_Last(long groupId, String actionCode,
		int syncType, int infoType,
		OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = fetchByG_AC_ST_IT_Last(groupId, actionCode,
				syncType, infoType, orderByComparator);

		if (dossierSync != null) {
			return dossierSync;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", actionCode=");
		msg.append(actionCode);

		msg.append(", syncType=");
		msg.append(syncType);

		msg.append(", infoType=");
		msg.append(infoType);

		msg.append("}");

		throw new NoSuchDossierSyncException(msg.toString());
	}

	/**
	 * Returns the last dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoType the info type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	 */
	@Override
	public DossierSync fetchByG_AC_ST_IT_Last(long groupId, String actionCode,
		int syncType, int infoType,
		OrderByComparator<DossierSync> orderByComparator) {
		int count = countByG_AC_ST_IT(groupId, actionCode, syncType, infoType);

		if (count == 0) {
			return null;
		}

		List<DossierSync> list = findByG_AC_ST_IT(groupId, actionCode,
				syncType, infoType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier syncs before and after the current dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	 *
	 * @param DossierSyncId the primary key of the current dossier sync
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoType the info type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier sync
	 * @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	 */
	@Override
	public DossierSync[] findByG_AC_ST_IT_PrevAndNext(long DossierSyncId,
		long groupId, String actionCode, int syncType, int infoType,
		OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = findByPrimaryKey(DossierSyncId);

		Session session = null;

		try {
			session = openSession();

			DossierSync[] array = new DossierSyncImpl[3];

			array[0] = getByG_AC_ST_IT_PrevAndNext(session, dossierSync,
					groupId, actionCode, syncType, infoType, orderByComparator,
					true);

			array[1] = dossierSync;

			array[2] = getByG_AC_ST_IT_PrevAndNext(session, dossierSync,
					groupId, actionCode, syncType, infoType, orderByComparator,
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

	protected DossierSync getByG_AC_ST_IT_PrevAndNext(Session session,
		DossierSync dossierSync, long groupId, String actionCode, int syncType,
		int infoType, OrderByComparator<DossierSync> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_DOSSIERSYNC_WHERE);

		query.append(_FINDER_COLUMN_G_AC_ST_IT_GROUPID_2);

		boolean bindActionCode = false;

		if (actionCode == null) {
			query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_1);
		}
		else if (actionCode.equals("")) {
			query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_3);
		}
		else {
			bindActionCode = true;

			query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_2);
		}

		query.append(_FINDER_COLUMN_G_AC_ST_IT_SYNCTYPE_2);

		query.append(_FINDER_COLUMN_G_AC_ST_IT_INFOTYPE_2);

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
			query.append(DossierSyncModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindActionCode) {
			qPos.add(actionCode);
		}

		qPos.add(syncType);

		qPos.add(infoType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierSync);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierSync> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoTypes the info types
	 * @return the matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByG_AC_ST_IT(long groupId, String actionCode,
		int syncType, int[] infoTypes) {
		return findByG_AC_ST_IT(groupId, actionCode, syncType, infoTypes,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoTypes the info types
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @return the range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByG_AC_ST_IT(long groupId, String actionCode,
		int syncType, int[] infoTypes, int start, int end) {
		return findByG_AC_ST_IT(groupId, actionCode, syncType, infoTypes,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoTypes the info types
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByG_AC_ST_IT(long groupId, String actionCode,
		int syncType, int[] infoTypes, int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return findByG_AC_ST_IT(groupId, actionCode, syncType, infoTypes,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoType the info type
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier syncs
	 */
	@Override
	public List<DossierSync> findByG_AC_ST_IT(long groupId, String actionCode,
		int syncType, int[] infoTypes, int start, int end,
		OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache) {
		if (infoTypes == null) {
			infoTypes = new int[0];
		}
		else if (infoTypes.length > 1) {
			infoTypes = ArrayUtil.unique(infoTypes);

			Arrays.sort(infoTypes);
		}

		if (infoTypes.length == 1) {
			return findByG_AC_ST_IT(groupId, actionCode, syncType,
				infoTypes[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, actionCode, syncType, StringUtil.merge(infoTypes)
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, actionCode, syncType, StringUtil.merge(infoTypes),
					
					start, end, orderByComparator
				};
		}

		List<DossierSync> list = null;

		if (retrieveFromCache) {
			list = (List<DossierSync>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_AC_ST_IT,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierSync dossierSync : list) {
					if ((groupId != dossierSync.getGroupId()) ||
							!Objects.equals(actionCode,
								dossierSync.getActionCode()) ||
							(syncType != dossierSync.getSyncType()) ||
							!ArrayUtil.contains(infoTypes,
								dossierSync.getInfoType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_DOSSIERSYNC_WHERE);

			query.append(_FINDER_COLUMN_G_AC_ST_IT_GROUPID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_2);
			}

			query.append(_FINDER_COLUMN_G_AC_ST_IT_SYNCTYPE_2);

			if (infoTypes.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_AC_ST_IT_INFOTYPE_7);

				query.append(StringUtil.merge(infoTypes));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierSyncModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindActionCode) {
					qPos.add(actionCode);
				}

				qPos.add(syncType);

				if (!pagination) {
					list = (List<DossierSync>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierSync>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_AC_ST_IT,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_AC_ST_IT,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoType the info type
	 */
	@Override
	public void removeByG_AC_ST_IT(long groupId, String actionCode,
		int syncType, int infoType) {
		for (DossierSync dossierSync : findByG_AC_ST_IT(groupId, actionCode,
				syncType, infoType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierSync);
		}
	}

	/**
	 * Returns the number of dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoType the info type
	 * @return the number of matching dossier syncs
	 */
	@Override
	public int countByG_AC_ST_IT(long groupId, String actionCode, int syncType,
		int infoType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_AC_ST_IT;

		Object[] finderArgs = new Object[] {
				groupId, actionCode, syncType, infoType
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_DOSSIERSYNC_WHERE);

			query.append(_FINDER_COLUMN_G_AC_ST_IT_GROUPID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_2);
			}

			query.append(_FINDER_COLUMN_G_AC_ST_IT_SYNCTYPE_2);

			query.append(_FINDER_COLUMN_G_AC_ST_IT_INFOTYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindActionCode) {
					qPos.add(actionCode);
				}

				qPos.add(syncType);

				qPos.add(infoType);

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

	/**
	 * Returns the number of dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param syncType the sync type
	 * @param infoTypes the info types
	 * @return the number of matching dossier syncs
	 */
	@Override
	public int countByG_AC_ST_IT(long groupId, String actionCode, int syncType,
		int[] infoTypes) {
		if (infoTypes == null) {
			infoTypes = new int[0];
		}
		else if (infoTypes.length > 1) {
			infoTypes = ArrayUtil.unique(infoTypes);

			Arrays.sort(infoTypes);
		}

		Object[] finderArgs = new Object[] {
				groupId, actionCode, syncType, StringUtil.merge(infoTypes)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_AC_ST_IT,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_DOSSIERSYNC_WHERE);

			query.append(_FINDER_COLUMN_G_AC_ST_IT_GROUPID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_2);
			}

			query.append(_FINDER_COLUMN_G_AC_ST_IT_SYNCTYPE_2);

			if (infoTypes.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_AC_ST_IT_INFOTYPE_7);

				query.append(StringUtil.merge(infoTypes));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindActionCode) {
					qPos.add(actionCode);
				}

				qPos.add(syncType);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_AC_ST_IT,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_AC_ST_IT,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_AC_ST_IT_GROUPID_2 = "dossierSync.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_1 = "dossierSync.actionCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_2 = "dossierSync.actionCode = ? AND ";
	private static final String _FINDER_COLUMN_G_AC_ST_IT_ACTIONCODE_3 = "(dossierSync.actionCode IS NULL OR dossierSync.actionCode = '') AND ";
	private static final String _FINDER_COLUMN_G_AC_ST_IT_SYNCTYPE_2 = "dossierSync.syncType = ? AND ";
	private static final String _FINDER_COLUMN_G_AC_ST_IT_INFOTYPE_2 = "dossierSync.infoType = ?";
	private static final String _FINDER_COLUMN_G_AC_ST_IT_INFOTYPE_7 = "dossierSync.infoType IN (";

	public DossierSyncPersistenceImpl() {
		setModelClass(DossierSync.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("state", "state_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the dossier sync in the entity cache if it is enabled.
	 *
	 * @param dossierSync the dossier sync
	 */
	@Override
	public void cacheResult(DossierSync dossierSync) {
		entityCache.putResult(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncImpl.class, dossierSync.getPrimaryKey(), dossierSync);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dossierSync.getUuid(), dossierSync.getGroupId() },
			dossierSync);

		dossierSync.resetOriginalValues();
	}

	/**
	 * Caches the dossier syncs in the entity cache if it is enabled.
	 *
	 * @param dossierSyncs the dossier syncs
	 */
	@Override
	public void cacheResult(List<DossierSync> dossierSyncs) {
		for (DossierSync dossierSync : dossierSyncs) {
			if (entityCache.getResult(
						DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
						DossierSyncImpl.class, dossierSync.getPrimaryKey()) == null) {
				cacheResult(dossierSync);
			}
			else {
				dossierSync.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dossier syncs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DossierSyncImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dossier sync.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DossierSync dossierSync) {
		entityCache.removeResult(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncImpl.class, dossierSync.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DossierSyncModelImpl)dossierSync, true);
	}

	@Override
	public void clearCache(List<DossierSync> dossierSyncs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DossierSync dossierSync : dossierSyncs) {
			entityCache.removeResult(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
				DossierSyncImpl.class, dossierSync.getPrimaryKey());

			clearUniqueFindersCache((DossierSyncModelImpl)dossierSync, true);
		}
	}

	protected void cacheUniqueFindersCache(
		DossierSyncModelImpl dossierSyncModelImpl) {
		Object[] args = new Object[] {
				dossierSyncModelImpl.getUuid(),
				dossierSyncModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dossierSyncModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DossierSyncModelImpl dossierSyncModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierSyncModelImpl.getUuid(),
					dossierSyncModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dossierSyncModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierSyncModelImpl.getOriginalUuid(),
					dossierSyncModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new dossier sync with the primary key. Does not add the dossier sync to the database.
	 *
	 * @param DossierSyncId the primary key for the new dossier sync
	 * @return the new dossier sync
	 */
	@Override
	public DossierSync create(long DossierSyncId) {
		DossierSync dossierSync = new DossierSyncImpl();

		dossierSync.setNew(true);
		dossierSync.setPrimaryKey(DossierSyncId);

		String uuid = PortalUUIDUtil.generate();

		dossierSync.setUuid(uuid);

		return dossierSync;
	}

	/**
	 * Removes the dossier sync with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param DossierSyncId the primary key of the dossier sync
	 * @return the dossier sync that was removed
	 * @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	 */
	@Override
	public DossierSync remove(long DossierSyncId)
		throws NoSuchDossierSyncException {
		return remove((Serializable)DossierSyncId);
	}

	/**
	 * Removes the dossier sync with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dossier sync
	 * @return the dossier sync that was removed
	 * @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	 */
	@Override
	public DossierSync remove(Serializable primaryKey)
		throws NoSuchDossierSyncException {
		Session session = null;

		try {
			session = openSession();

			DossierSync dossierSync = (DossierSync)session.get(DossierSyncImpl.class,
					primaryKey);

			if (dossierSync == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDossierSyncException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dossierSync);
		}
		catch (NoSuchDossierSyncException nsee) {
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
	protected DossierSync removeImpl(DossierSync dossierSync) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dossierSync)) {
				dossierSync = (DossierSync)session.get(DossierSyncImpl.class,
						dossierSync.getPrimaryKeyObj());
			}

			if (dossierSync != null) {
				session.delete(dossierSync);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dossierSync != null) {
			clearCache(dossierSync);
		}

		return dossierSync;
	}

	@Override
	public DossierSync updateImpl(DossierSync dossierSync) {
		boolean isNew = dossierSync.isNew();

		if (!(dossierSync instanceof DossierSyncModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dossierSync.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dossierSync);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dossierSync proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DossierSync implementation " +
				dossierSync.getClass());
		}

		DossierSyncModelImpl dossierSyncModelImpl = (DossierSyncModelImpl)dossierSync;

		if (Validator.isNull(dossierSync.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dossierSync.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dossierSync.getCreateDate() == null)) {
			if (serviceContext == null) {
				dossierSync.setCreateDate(now);
			}
			else {
				dossierSync.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dossierSyncModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dossierSync.setModifiedDate(now);
			}
			else {
				dossierSync.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dossierSync.isNew()) {
				session.save(dossierSync);

				dossierSync.setNew(false);
			}
			else {
				dossierSync = (DossierSync)session.merge(dossierSync);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DossierSyncModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dossierSyncModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { dossierSyncModelImpl.getState() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ST, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ST,
				args);

			args = new Object[] {
					dossierSyncModelImpl.getGroupId(),
					dossierSyncModelImpl.getDossierRefUid(),
					dossierSyncModelImpl.getInfoType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DRID_IT, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DRID_IT,
				args);

			args = new Object[] {
					dossierSyncModelImpl.getGroupId(),
					dossierSyncModelImpl.getActionCode(),
					dossierSyncModelImpl.getSyncType(),
					dossierSyncModelImpl.getInfoType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_AC_ST_IT, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_AC_ST_IT,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dossierSyncModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierSyncModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dossierSyncModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dossierSyncModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierSyncModelImpl.getOriginalState()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ST,
					args);

				args = new Object[] { dossierSyncModelImpl.getState() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ST,
					args);
			}

			if ((dossierSyncModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DRID_IT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierSyncModelImpl.getOriginalGroupId(),
						dossierSyncModelImpl.getOriginalDossierRefUid(),
						dossierSyncModelImpl.getOriginalInfoType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DRID_IT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DRID_IT,
					args);

				args = new Object[] {
						dossierSyncModelImpl.getGroupId(),
						dossierSyncModelImpl.getDossierRefUid(),
						dossierSyncModelImpl.getInfoType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DRID_IT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DRID_IT,
					args);
			}

			if ((dossierSyncModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_AC_ST_IT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierSyncModelImpl.getOriginalGroupId(),
						dossierSyncModelImpl.getOriginalActionCode(),
						dossierSyncModelImpl.getOriginalSyncType(),
						dossierSyncModelImpl.getOriginalInfoType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_AC_ST_IT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_AC_ST_IT,
					args);

				args = new Object[] {
						dossierSyncModelImpl.getGroupId(),
						dossierSyncModelImpl.getActionCode(),
						dossierSyncModelImpl.getSyncType(),
						dossierSyncModelImpl.getInfoType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_AC_ST_IT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_AC_ST_IT,
					args);
			}
		}

		entityCache.putResult(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierSyncImpl.class, dossierSync.getPrimaryKey(), dossierSync,
			false);

		clearUniqueFindersCache(dossierSyncModelImpl, false);
		cacheUniqueFindersCache(dossierSyncModelImpl);

		dossierSync.resetOriginalValues();

		return dossierSync;
	}

	/**
	 * Returns the dossier sync with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier sync
	 * @return the dossier sync
	 * @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	 */
	@Override
	public DossierSync findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDossierSyncException {
		DossierSync dossierSync = fetchByPrimaryKey(primaryKey);

		if (dossierSync == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDossierSyncException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dossierSync;
	}

	/**
	 * Returns the dossier sync with the primary key or throws a {@link NoSuchDossierSyncException} if it could not be found.
	 *
	 * @param DossierSyncId the primary key of the dossier sync
	 * @return the dossier sync
	 * @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	 */
	@Override
	public DossierSync findByPrimaryKey(long DossierSyncId)
		throws NoSuchDossierSyncException {
		return findByPrimaryKey((Serializable)DossierSyncId);
	}

	/**
	 * Returns the dossier sync with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier sync
	 * @return the dossier sync, or <code>null</code> if a dossier sync with the primary key could not be found
	 */
	@Override
	public DossierSync fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
				DossierSyncImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DossierSync dossierSync = (DossierSync)serializable;

		if (dossierSync == null) {
			Session session = null;

			try {
				session = openSession();

				dossierSync = (DossierSync)session.get(DossierSyncImpl.class,
						primaryKey);

				if (dossierSync != null) {
					cacheResult(dossierSync);
				}
				else {
					entityCache.putResult(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
						DossierSyncImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
					DossierSyncImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dossierSync;
	}

	/**
	 * Returns the dossier sync with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param DossierSyncId the primary key of the dossier sync
	 * @return the dossier sync, or <code>null</code> if a dossier sync with the primary key could not be found
	 */
	@Override
	public DossierSync fetchByPrimaryKey(long DossierSyncId) {
		return fetchByPrimaryKey((Serializable)DossierSyncId);
	}

	@Override
	public Map<Serializable, DossierSync> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DossierSync> map = new HashMap<Serializable, DossierSync>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DossierSync dossierSync = fetchByPrimaryKey(primaryKey);

			if (dossierSync != null) {
				map.put(primaryKey, dossierSync);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
					DossierSyncImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DossierSync)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DOSSIERSYNC_WHERE_PKS_IN);

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

			for (DossierSync dossierSync : (List<DossierSync>)q.list()) {
				map.put(dossierSync.getPrimaryKeyObj(), dossierSync);

				cacheResult(dossierSync);

				uncachedPrimaryKeys.remove(dossierSync.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DossierSyncModelImpl.ENTITY_CACHE_ENABLED,
					DossierSyncImpl.class, primaryKey, nullModel);
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
	 * Returns all the dossier syncs.
	 *
	 * @return the dossier syncs
	 */
	@Override
	public List<DossierSync> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier syncs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @return the range of dossier syncs
	 */
	@Override
	public List<DossierSync> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier syncs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dossier syncs
	 */
	@Override
	public List<DossierSync> findAll(int start, int end,
		OrderByComparator<DossierSync> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier syncs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier syncs
	 * @param end the upper bound of the range of dossier syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dossier syncs
	 */
	@Override
	public List<DossierSync> findAll(int start, int end,
		OrderByComparator<DossierSync> orderByComparator,
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

		List<DossierSync> list = null;

		if (retrieveFromCache) {
			list = (List<DossierSync>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOSSIERSYNC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOSSIERSYNC;

				if (pagination) {
					sql = sql.concat(DossierSyncModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DossierSync>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierSync>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the dossier syncs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DossierSync dossierSync : findAll()) {
			remove(dossierSync);
		}
	}

	/**
	 * Returns the number of dossier syncs.
	 *
	 * @return the number of dossier syncs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOSSIERSYNC);

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
		return DossierSyncModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dossier sync persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DossierSyncImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_DOSSIERSYNC = "SELECT dossierSync FROM DossierSync dossierSync";
	private static final String _SQL_SELECT_DOSSIERSYNC_WHERE_PKS_IN = "SELECT dossierSync FROM DossierSync dossierSync WHERE DossierSyncId IN (";
	private static final String _SQL_SELECT_DOSSIERSYNC_WHERE = "SELECT dossierSync FROM DossierSync dossierSync WHERE ";
	private static final String _SQL_COUNT_DOSSIERSYNC = "SELECT COUNT(dossierSync) FROM DossierSync dossierSync";
	private static final String _SQL_COUNT_DOSSIERSYNC_WHERE = "SELECT COUNT(dossierSync) FROM DossierSync dossierSync WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dossierSync.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DossierSync exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DossierSync exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DossierSyncPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "state"
			});
}