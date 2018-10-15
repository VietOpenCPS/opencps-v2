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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException;
import org.opencps.dossiermgt.model.DossierActionSync;
import org.opencps.dossiermgt.model.impl.DossierActionSyncImpl;
import org.opencps.dossiermgt.model.impl.DossierActionSyncModelImpl;
import org.opencps.dossiermgt.service.persistence.DossierActionSyncPersistence;

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
 * The persistence implementation for the dossier action sync service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierActionSyncPersistence
 * @see org.opencps.dossiermgt.service.persistence.DossierActionSyncUtil
 * @generated
 */
@ProviderType
public class DossierActionSyncPersistenceImpl extends BasePersistenceImpl<DossierActionSync>
	implements DossierActionSyncPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DossierActionSyncUtil} to access the dossier action sync persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DossierActionSyncImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncModelImpl.FINDER_CACHE_ENABLED,
			DossierActionSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncModelImpl.FINDER_CACHE_ENABLED,
			DossierActionSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncModelImpl.FINDER_CACHE_ENABLED,
			DossierActionSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncModelImpl.FINDER_CACHE_ENABLED,
			DossierActionSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DossierActionSyncModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dossier action syncs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier action syncs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier action syncs
	 * @param end the upper bound of the range of dossier action syncs (not inclusive)
	 * @return the range of matching dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier action syncs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier action syncs
	 * @param end the upper bound of the range of dossier action syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierActionSync> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier action syncs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier action syncs
	 * @param end the upper bound of the range of dossier action syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierActionSync> orderByComparator,
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

		List<DossierActionSync> list = null;

		if (retrieveFromCache) {
			list = (List<DossierActionSync>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierActionSync dossierActionSync : list) {
					if (!Objects.equals(uuid, dossierActionSync.getUuid())) {
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

			query.append(_SQL_SELECT_DOSSIERACTIONSYNC_WHERE);

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
				query.append(DossierActionSyncModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierActionSync>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierActionSync>)QueryUtil.list(q,
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
	 * Returns the first dossier action sync in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action sync
	 * @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync findByUuid_First(String uuid,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException {
		DossierActionSync dossierActionSync = fetchByUuid_First(uuid,
				orderByComparator);

		if (dossierActionSync != null) {
			return dossierActionSync;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierActionSyncException(msg.toString());
	}

	/**
	 * Returns the first dossier action sync in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync fetchByUuid_First(String uuid,
		OrderByComparator<DossierActionSync> orderByComparator) {
		List<DossierActionSync> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action sync in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action sync
	 * @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync findByUuid_Last(String uuid,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException {
		DossierActionSync dossierActionSync = fetchByUuid_Last(uuid,
				orderByComparator);

		if (dossierActionSync != null) {
			return dossierActionSync;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierActionSyncException(msg.toString());
	}

	/**
	 * Returns the last dossier action sync in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync fetchByUuid_Last(String uuid,
		OrderByComparator<DossierActionSync> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DossierActionSync> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier action syncs before and after the current dossier action sync in the ordered set where uuid = &#63;.
	 *
	 * @param dossierActionSyncId the primary key of the current dossier action sync
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action sync
	 * @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	 */
	@Override
	public DossierActionSync[] findByUuid_PrevAndNext(
		long dossierActionSyncId, String uuid,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException {
		DossierActionSync dossierActionSync = findByPrimaryKey(dossierActionSyncId);

		Session session = null;

		try {
			session = openSession();

			DossierActionSync[] array = new DossierActionSyncImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dossierActionSync, uuid,
					orderByComparator, true);

			array[1] = dossierActionSync;

			array[2] = getByUuid_PrevAndNext(session, dossierActionSync, uuid,
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

	protected DossierActionSync getByUuid_PrevAndNext(Session session,
		DossierActionSync dossierActionSync, String uuid,
		OrderByComparator<DossierActionSync> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERACTIONSYNC_WHERE);

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
			query.append(DossierActionSyncModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierActionSync);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierActionSync> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier action syncs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DossierActionSync dossierActionSync : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierActionSync);
		}
	}

	/**
	 * Returns the number of dossier action syncs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dossier action syncs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERACTIONSYNC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dossierActionSync.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dossierActionSync.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dossierActionSync.uuid IS NULL OR dossierActionSync.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncModelImpl.FINDER_CACHE_ENABLED,
			DossierActionSyncImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierActionSyncModelImpl.UUID_COLUMN_BITMASK |
			DossierActionSyncModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier action sync where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierActionSyncException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier action sync
	 * @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierActionSyncException {
		DossierActionSync dossierActionSync = fetchByUUID_G(uuid, groupId);

		if (dossierActionSync == null) {
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

			throw new NoSuchDossierActionSyncException(msg.toString());
		}

		return dossierActionSync;
	}

	/**
	 * Returns the dossier action sync where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dossier action sync where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DossierActionSync) {
			DossierActionSync dossierActionSync = (DossierActionSync)result;

			if (!Objects.equals(uuid, dossierActionSync.getUuid()) ||
					(groupId != dossierActionSync.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERACTIONSYNC_WHERE);

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

				List<DossierActionSync> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DossierActionSync dossierActionSync = list.get(0);

					result = dossierActionSync;

					cacheResult(dossierActionSync);
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
			return (DossierActionSync)result;
		}
	}

	/**
	 * Removes the dossier action sync where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dossier action sync that was removed
	 */
	@Override
	public DossierActionSync removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierActionSyncException {
		DossierActionSync dossierActionSync = findByUUID_G(uuid, groupId);

		return remove(dossierActionSync);
	}

	/**
	 * Returns the number of dossier action syncs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dossier action syncs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTIONSYNC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dossierActionSync.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dossierActionSync.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dossierActionSync.uuid IS NULL OR dossierActionSync.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dossierActionSync.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncModelImpl.FINDER_CACHE_ENABLED,
			DossierActionSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncModelImpl.FINDER_CACHE_ENABLED,
			DossierActionSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierActionSyncModelImpl.UUID_COLUMN_BITMASK |
			DossierActionSyncModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dossier action syncs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier action syncs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier action syncs
	 * @param end the upper bound of the range of dossier action syncs (not inclusive)
	 * @return the range of matching dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier action syncs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier action syncs
	 * @param end the upper bound of the range of dossier action syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DossierActionSync> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier action syncs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier action syncs
	 * @param end the upper bound of the range of dossier action syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DossierActionSync> orderByComparator,
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

		List<DossierActionSync> list = null;

		if (retrieveFromCache) {
			list = (List<DossierActionSync>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierActionSync dossierActionSync : list) {
					if (!Objects.equals(uuid, dossierActionSync.getUuid()) ||
							(companyId != dossierActionSync.getCompanyId())) {
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

			query.append(_SQL_SELECT_DOSSIERACTIONSYNC_WHERE);

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
				query.append(DossierActionSyncModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierActionSync>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierActionSync>)QueryUtil.list(q,
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
	 * Returns the first dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action sync
	 * @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException {
		DossierActionSync dossierActionSync = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (dossierActionSync != null) {
			return dossierActionSync;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDossierActionSyncException(msg.toString());
	}

	/**
	 * Returns the first dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierActionSync> orderByComparator) {
		List<DossierActionSync> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action sync
	 * @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException {
		DossierActionSync dossierActionSync = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (dossierActionSync != null) {
			return dossierActionSync;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDossierActionSyncException(msg.toString());
	}

	/**
	 * Returns the last dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierActionSync> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DossierActionSync> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier action syncs before and after the current dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dossierActionSyncId the primary key of the current dossier action sync
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action sync
	 * @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	 */
	@Override
	public DossierActionSync[] findByUuid_C_PrevAndNext(
		long dossierActionSyncId, String uuid, long companyId,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException {
		DossierActionSync dossierActionSync = findByPrimaryKey(dossierActionSyncId);

		Session session = null;

		try {
			session = openSession();

			DossierActionSync[] array = new DossierActionSyncImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dossierActionSync,
					uuid, companyId, orderByComparator, true);

			array[1] = dossierActionSync;

			array[2] = getByUuid_C_PrevAndNext(session, dossierActionSync,
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

	protected DossierActionSync getByUuid_C_PrevAndNext(Session session,
		DossierActionSync dossierActionSync, String uuid, long companyId,
		OrderByComparator<DossierActionSync> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERACTIONSYNC_WHERE);

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
			query.append(DossierActionSyncModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierActionSync);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierActionSync> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier action syncs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DossierActionSync dossierActionSync : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierActionSync);
		}
	}

	/**
	 * Returns the number of dossier action syncs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dossier action syncs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTIONSYNC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dossierActionSync.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dossierActionSync.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dossierActionSync.uuid IS NULL OR dossierActionSync.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dossierActionSync.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID = new FinderPath(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncModelImpl.FINDER_CACHE_ENABLED,
			DossierActionSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID = new FinderPath(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncModelImpl.FINDER_CACHE_ENABLED,
			DossierActionSyncImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDID",
			new String[] { Long.class.getName() },
			DossierActionSyncModelImpl.DOSSIERACTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID = new FinderPath(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dossier action syncs where dossierActionId = &#63;.
	 *
	 * @param dossierActionId the dossier action ID
	 * @return the matching dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findByDID(long dossierActionId) {
		return findByDID(dossierActionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dossier action syncs where dossierActionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierActionId the dossier action ID
	 * @param start the lower bound of the range of dossier action syncs
	 * @param end the upper bound of the range of dossier action syncs (not inclusive)
	 * @return the range of matching dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findByDID(long dossierActionId, int start,
		int end) {
		return findByDID(dossierActionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier action syncs where dossierActionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierActionId the dossier action ID
	 * @param start the lower bound of the range of dossier action syncs
	 * @param end the upper bound of the range of dossier action syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findByDID(long dossierActionId, int start,
		int end, OrderByComparator<DossierActionSync> orderByComparator) {
		return findByDID(dossierActionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier action syncs where dossierActionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierActionId the dossier action ID
	 * @param start the lower bound of the range of dossier action syncs
	 * @param end the upper bound of the range of dossier action syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findByDID(long dossierActionId, int start,
		int end, OrderByComparator<DossierActionSync> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID;
			finderArgs = new Object[] { dossierActionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID;
			finderArgs = new Object[] {
					dossierActionId,
					
					start, end, orderByComparator
				};
		}

		List<DossierActionSync> list = null;

		if (retrieveFromCache) {
			list = (List<DossierActionSync>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierActionSync dossierActionSync : list) {
					if ((dossierActionId != dossierActionSync.getDossierActionId())) {
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

			query.append(_SQL_SELECT_DOSSIERACTIONSYNC_WHERE);

			query.append(_FINDER_COLUMN_DID_DOSSIERACTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierActionSyncModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierActionId);

				if (!pagination) {
					list = (List<DossierActionSync>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierActionSync>)QueryUtil.list(q,
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
	 * Returns the first dossier action sync in the ordered set where dossierActionId = &#63;.
	 *
	 * @param dossierActionId the dossier action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action sync
	 * @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync findByDID_First(long dossierActionId,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException {
		DossierActionSync dossierActionSync = fetchByDID_First(dossierActionId,
				orderByComparator);

		if (dossierActionSync != null) {
			return dossierActionSync;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierActionId=");
		msg.append(dossierActionId);

		msg.append("}");

		throw new NoSuchDossierActionSyncException(msg.toString());
	}

	/**
	 * Returns the first dossier action sync in the ordered set where dossierActionId = &#63;.
	 *
	 * @param dossierActionId the dossier action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync fetchByDID_First(long dossierActionId,
		OrderByComparator<DossierActionSync> orderByComparator) {
		List<DossierActionSync> list = findByDID(dossierActionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action sync in the ordered set where dossierActionId = &#63;.
	 *
	 * @param dossierActionId the dossier action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action sync
	 * @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync findByDID_Last(long dossierActionId,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException {
		DossierActionSync dossierActionSync = fetchByDID_Last(dossierActionId,
				orderByComparator);

		if (dossierActionSync != null) {
			return dossierActionSync;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierActionId=");
		msg.append(dossierActionId);

		msg.append("}");

		throw new NoSuchDossierActionSyncException(msg.toString());
	}

	/**
	 * Returns the last dossier action sync in the ordered set where dossierActionId = &#63;.
	 *
	 * @param dossierActionId the dossier action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	 */
	@Override
	public DossierActionSync fetchByDID_Last(long dossierActionId,
		OrderByComparator<DossierActionSync> orderByComparator) {
		int count = countByDID(dossierActionId);

		if (count == 0) {
			return null;
		}

		List<DossierActionSync> list = findByDID(dossierActionId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier action syncs before and after the current dossier action sync in the ordered set where dossierActionId = &#63;.
	 *
	 * @param dossierActionSyncId the primary key of the current dossier action sync
	 * @param dossierActionId the dossier action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action sync
	 * @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	 */
	@Override
	public DossierActionSync[] findByDID_PrevAndNext(long dossierActionSyncId,
		long dossierActionId,
		OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException {
		DossierActionSync dossierActionSync = findByPrimaryKey(dossierActionSyncId);

		Session session = null;

		try {
			session = openSession();

			DossierActionSync[] array = new DossierActionSyncImpl[3];

			array[0] = getByDID_PrevAndNext(session, dossierActionSync,
					dossierActionId, orderByComparator, true);

			array[1] = dossierActionSync;

			array[2] = getByDID_PrevAndNext(session, dossierActionSync,
					dossierActionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierActionSync getByDID_PrevAndNext(Session session,
		DossierActionSync dossierActionSync, long dossierActionId,
		OrderByComparator<DossierActionSync> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERACTIONSYNC_WHERE);

		query.append(_FINDER_COLUMN_DID_DOSSIERACTIONID_2);

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
			query.append(DossierActionSyncModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierActionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierActionSync);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierActionSync> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier action syncs where dossierActionId = &#63; from the database.
	 *
	 * @param dossierActionId the dossier action ID
	 */
	@Override
	public void removeByDID(long dossierActionId) {
		for (DossierActionSync dossierActionSync : findByDID(dossierActionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierActionSync);
		}
	}

	/**
	 * Returns the number of dossier action syncs where dossierActionId = &#63;.
	 *
	 * @param dossierActionId the dossier action ID
	 * @return the number of matching dossier action syncs
	 */
	@Override
	public int countByDID(long dossierActionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID;

		Object[] finderArgs = new Object[] { dossierActionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERACTIONSYNC_WHERE);

			query.append(_FINDER_COLUMN_DID_DOSSIERACTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierActionId);

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

	private static final String _FINDER_COLUMN_DID_DOSSIERACTIONID_2 = "dossierActionSync.dossierActionId = ?";

	public DossierActionSyncPersistenceImpl() {
		setModelClass(DossierActionSync.class);

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
	 * Caches the dossier action sync in the entity cache if it is enabled.
	 *
	 * @param dossierActionSync the dossier action sync
	 */
	@Override
	public void cacheResult(DossierActionSync dossierActionSync) {
		entityCache.putResult(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncImpl.class, dossierActionSync.getPrimaryKey(),
			dossierActionSync);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				dossierActionSync.getUuid(), dossierActionSync.getGroupId()
			}, dossierActionSync);

		dossierActionSync.resetOriginalValues();
	}

	/**
	 * Caches the dossier action syncs in the entity cache if it is enabled.
	 *
	 * @param dossierActionSyncs the dossier action syncs
	 */
	@Override
	public void cacheResult(List<DossierActionSync> dossierActionSyncs) {
		for (DossierActionSync dossierActionSync : dossierActionSyncs) {
			if (entityCache.getResult(
						DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
						DossierActionSyncImpl.class,
						dossierActionSync.getPrimaryKey()) == null) {
				cacheResult(dossierActionSync);
			}
			else {
				dossierActionSync.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dossier action syncs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DossierActionSyncImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dossier action sync.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DossierActionSync dossierActionSync) {
		entityCache.removeResult(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncImpl.class, dossierActionSync.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DossierActionSyncModelImpl)dossierActionSync,
			true);
	}

	@Override
	public void clearCache(List<DossierActionSync> dossierActionSyncs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DossierActionSync dossierActionSync : dossierActionSyncs) {
			entityCache.removeResult(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
				DossierActionSyncImpl.class, dossierActionSync.getPrimaryKey());

			clearUniqueFindersCache((DossierActionSyncModelImpl)dossierActionSync,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		DossierActionSyncModelImpl dossierActionSyncModelImpl) {
		Object[] args = new Object[] {
				dossierActionSyncModelImpl.getUuid(),
				dossierActionSyncModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dossierActionSyncModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DossierActionSyncModelImpl dossierActionSyncModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierActionSyncModelImpl.getUuid(),
					dossierActionSyncModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dossierActionSyncModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierActionSyncModelImpl.getOriginalUuid(),
					dossierActionSyncModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new dossier action sync with the primary key. Does not add the dossier action sync to the database.
	 *
	 * @param dossierActionSyncId the primary key for the new dossier action sync
	 * @return the new dossier action sync
	 */
	@Override
	public DossierActionSync create(long dossierActionSyncId) {
		DossierActionSync dossierActionSync = new DossierActionSyncImpl();

		dossierActionSync.setNew(true);
		dossierActionSync.setPrimaryKey(dossierActionSyncId);

		String uuid = PortalUUIDUtil.generate();

		dossierActionSync.setUuid(uuid);

		dossierActionSync.setCompanyId(companyProvider.getCompanyId());

		return dossierActionSync;
	}

	/**
	 * Removes the dossier action sync with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dossierActionSyncId the primary key of the dossier action sync
	 * @return the dossier action sync that was removed
	 * @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	 */
	@Override
	public DossierActionSync remove(long dossierActionSyncId)
		throws NoSuchDossierActionSyncException {
		return remove((Serializable)dossierActionSyncId);
	}

	/**
	 * Removes the dossier action sync with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dossier action sync
	 * @return the dossier action sync that was removed
	 * @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	 */
	@Override
	public DossierActionSync remove(Serializable primaryKey)
		throws NoSuchDossierActionSyncException {
		Session session = null;

		try {
			session = openSession();

			DossierActionSync dossierActionSync = (DossierActionSync)session.get(DossierActionSyncImpl.class,
					primaryKey);

			if (dossierActionSync == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDossierActionSyncException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dossierActionSync);
		}
		catch (NoSuchDossierActionSyncException nsee) {
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
	protected DossierActionSync removeImpl(DossierActionSync dossierActionSync) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dossierActionSync)) {
				dossierActionSync = (DossierActionSync)session.get(DossierActionSyncImpl.class,
						dossierActionSync.getPrimaryKeyObj());
			}

			if (dossierActionSync != null) {
				session.delete(dossierActionSync);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dossierActionSync != null) {
			clearCache(dossierActionSync);
		}

		return dossierActionSync;
	}

	@Override
	public DossierActionSync updateImpl(DossierActionSync dossierActionSync) {
		boolean isNew = dossierActionSync.isNew();

		if (!(dossierActionSync instanceof DossierActionSyncModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dossierActionSync.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dossierActionSync);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dossierActionSync proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DossierActionSync implementation " +
				dossierActionSync.getClass());
		}

		DossierActionSyncModelImpl dossierActionSyncModelImpl = (DossierActionSyncModelImpl)dossierActionSync;

		if (Validator.isNull(dossierActionSync.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dossierActionSync.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dossierActionSync.getCreateDate() == null)) {
			if (serviceContext == null) {
				dossierActionSync.setCreateDate(now);
			}
			else {
				dossierActionSync.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!dossierActionSyncModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dossierActionSync.setModifiedDate(now);
			}
			else {
				dossierActionSync.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dossierActionSync.isNew()) {
				session.save(dossierActionSync);

				dossierActionSync.setNew(false);
			}
			else {
				dossierActionSync = (DossierActionSync)session.merge(dossierActionSync);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DossierActionSyncModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dossierActionSyncModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					dossierActionSyncModelImpl.getUuid(),
					dossierActionSyncModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { dossierActionSyncModelImpl.getDossierActionId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dossierActionSyncModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionSyncModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dossierActionSyncModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dossierActionSyncModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionSyncModelImpl.getOriginalUuid(),
						dossierActionSyncModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dossierActionSyncModelImpl.getUuid(),
						dossierActionSyncModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((dossierActionSyncModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionSyncModelImpl.getOriginalDossierActionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID,
					args);

				args = new Object[] {
						dossierActionSyncModelImpl.getDossierActionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID,
					args);
			}
		}

		entityCache.putResult(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionSyncImpl.class, dossierActionSync.getPrimaryKey(),
			dossierActionSync, false);

		clearUniqueFindersCache(dossierActionSyncModelImpl, false);
		cacheUniqueFindersCache(dossierActionSyncModelImpl);

		dossierActionSync.resetOriginalValues();

		return dossierActionSync;
	}

	/**
	 * Returns the dossier action sync with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier action sync
	 * @return the dossier action sync
	 * @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	 */
	@Override
	public DossierActionSync findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDossierActionSyncException {
		DossierActionSync dossierActionSync = fetchByPrimaryKey(primaryKey);

		if (dossierActionSync == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDossierActionSyncException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dossierActionSync;
	}

	/**
	 * Returns the dossier action sync with the primary key or throws a {@link NoSuchDossierActionSyncException} if it could not be found.
	 *
	 * @param dossierActionSyncId the primary key of the dossier action sync
	 * @return the dossier action sync
	 * @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	 */
	@Override
	public DossierActionSync findByPrimaryKey(long dossierActionSyncId)
		throws NoSuchDossierActionSyncException {
		return findByPrimaryKey((Serializable)dossierActionSyncId);
	}

	/**
	 * Returns the dossier action sync with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier action sync
	 * @return the dossier action sync, or <code>null</code> if a dossier action sync with the primary key could not be found
	 */
	@Override
	public DossierActionSync fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
				DossierActionSyncImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DossierActionSync dossierActionSync = (DossierActionSync)serializable;

		if (dossierActionSync == null) {
			Session session = null;

			try {
				session = openSession();

				dossierActionSync = (DossierActionSync)session.get(DossierActionSyncImpl.class,
						primaryKey);

				if (dossierActionSync != null) {
					cacheResult(dossierActionSync);
				}
				else {
					entityCache.putResult(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
						DossierActionSyncImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
					DossierActionSyncImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dossierActionSync;
	}

	/**
	 * Returns the dossier action sync with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dossierActionSyncId the primary key of the dossier action sync
	 * @return the dossier action sync, or <code>null</code> if a dossier action sync with the primary key could not be found
	 */
	@Override
	public DossierActionSync fetchByPrimaryKey(long dossierActionSyncId) {
		return fetchByPrimaryKey((Serializable)dossierActionSyncId);
	}

	@Override
	public Map<Serializable, DossierActionSync> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DossierActionSync> map = new HashMap<Serializable, DossierActionSync>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DossierActionSync dossierActionSync = fetchByPrimaryKey(primaryKey);

			if (dossierActionSync != null) {
				map.put(primaryKey, dossierActionSync);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
					DossierActionSyncImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DossierActionSync)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DOSSIERACTIONSYNC_WHERE_PKS_IN);

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

			for (DossierActionSync dossierActionSync : (List<DossierActionSync>)q.list()) {
				map.put(dossierActionSync.getPrimaryKeyObj(), dossierActionSync);

				cacheResult(dossierActionSync);

				uncachedPrimaryKeys.remove(dossierActionSync.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DossierActionSyncModelImpl.ENTITY_CACHE_ENABLED,
					DossierActionSyncImpl.class, primaryKey, nullModel);
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
	 * Returns all the dossier action syncs.
	 *
	 * @return the dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier action syncs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier action syncs
	 * @param end the upper bound of the range of dossier action syncs (not inclusive)
	 * @return the range of dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier action syncs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier action syncs
	 * @param end the upper bound of the range of dossier action syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findAll(int start, int end,
		OrderByComparator<DossierActionSync> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier action syncs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier action syncs
	 * @param end the upper bound of the range of dossier action syncs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dossier action syncs
	 */
	@Override
	public List<DossierActionSync> findAll(int start, int end,
		OrderByComparator<DossierActionSync> orderByComparator,
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

		List<DossierActionSync> list = null;

		if (retrieveFromCache) {
			list = (List<DossierActionSync>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOSSIERACTIONSYNC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOSSIERACTIONSYNC;

				if (pagination) {
					sql = sql.concat(DossierActionSyncModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DossierActionSync>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierActionSync>)QueryUtil.list(q,
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
	 * Removes all the dossier action syncs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DossierActionSync dossierActionSync : findAll()) {
			remove(dossierActionSync);
		}
	}

	/**
	 * Returns the number of dossier action syncs.
	 *
	 * @return the number of dossier action syncs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOSSIERACTIONSYNC);

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
		return DossierActionSyncModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dossier action sync persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DossierActionSyncImpl.class.getName());
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
	private static final String _SQL_SELECT_DOSSIERACTIONSYNC = "SELECT dossierActionSync FROM DossierActionSync dossierActionSync";
	private static final String _SQL_SELECT_DOSSIERACTIONSYNC_WHERE_PKS_IN = "SELECT dossierActionSync FROM DossierActionSync dossierActionSync WHERE dossierActionSyncId IN (";
	private static final String _SQL_SELECT_DOSSIERACTIONSYNC_WHERE = "SELECT dossierActionSync FROM DossierActionSync dossierActionSync WHERE ";
	private static final String _SQL_COUNT_DOSSIERACTIONSYNC = "SELECT COUNT(dossierActionSync) FROM DossierActionSync dossierActionSync";
	private static final String _SQL_COUNT_DOSSIERACTIONSYNC_WHERE = "SELECT COUNT(dossierActionSync) FROM DossierActionSync dossierActionSync WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dossierActionSync.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DossierActionSync exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DossierActionSync exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DossierActionSyncPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}