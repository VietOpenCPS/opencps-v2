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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchDossierException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.impl.DossierImpl;
import org.opencps.dossiermgt.model.impl.DossierModelImpl;
import org.opencps.dossiermgt.service.persistence.DossierPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

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
 * The persistence implementation for the dossier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierPersistence
 * @see org.opencps.dossiermgt.service.persistence.DossierUtil
 * @generated
 */
@ProviderType
public class DossierPersistenceImpl extends BasePersistenceImpl<Dossier>
	implements DossierPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DossierUtil} to access the dossier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DossierImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DossierModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dossiers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByUuid(String uuid, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByUuid(String uuid, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
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

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if (!Objects.equals(uuid, dossier.getUuid())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

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
				query.append(DossierModelImpl.ORDER_BY_JPQL);
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
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByUuid_First(String uuid,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByUuid_First(uuid, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByUuid_First(String uuid,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByUuid_Last(String uuid,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByUuid_Last(uuid, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByUuid_Last(String uuid,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where uuid = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByUuid_PrevAndNext(long dossierId, String uuid,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dossier, uuid,
					orderByComparator, true);

			array[1] = dossier;

			array[2] = getByUuid_PrevAndNext(session, dossier, uuid,
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

	protected Dossier getByUuid_PrevAndNext(Session session, Dossier dossier,
		String uuid, OrderByComparator<Dossier> orderByComparator,
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

		query.append(_SQL_SELECT_DOSSIER_WHERE);

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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Dossier dossier : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dossier.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dossier.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dossier.uuid IS NULL OR dossier.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierModelImpl.UUID_COLUMN_BITMASK |
			DossierModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierException {
		Dossier dossier = fetchByUUID_G(uuid, groupId);

		if (dossier == null) {
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

			throw new NoSuchDossierException(msg.toString());
		}

		return dossier;
	}

	/**
	 * Returns the dossier where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dossier where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Dossier) {
			Dossier dossier = (Dossier)result;

			if (!Objects.equals(uuid, dossier.getUuid()) ||
					(groupId != dossier.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIER_WHERE);

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

				List<Dossier> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Dossier dossier = list.get(0);

					result = dossier;

					cacheResult(dossier);
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
			return (Dossier)result;
		}
	}

	/**
	 * Removes the dossier where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dossier that was removed
	 */
	@Override
	public Dossier removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierException {
		Dossier dossier = findByUUID_G(uuid, groupId);

		return remove(dossier);
	}

	/**
	 * Returns the number of dossiers where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dossier.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dossier.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dossier.uuid IS NULL OR dossier.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dossier.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierModelImpl.UUID_COLUMN_BITMASK |
			DossierModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dossiers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Dossier> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Dossier> orderByComparator,
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

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if (!Objects.equals(uuid, dossier.getUuid()) ||
							(companyId != dossier.getCompanyId())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

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
				query.append(DossierModelImpl.ORDER_BY_JPQL);
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
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByUuid_C_PrevAndNext(long dossierId, String uuid,
		long companyId, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dossier, uuid,
					companyId, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByUuid_C_PrevAndNext(session, dossier, uuid,
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

	protected Dossier getByUuid_C_PrevAndNext(Session session, Dossier dossier,
		String uuid, long companyId,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Dossier dossier : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dossier.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dossier.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dossier.uuid IS NULL OR dossier.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dossier.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_UID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_UID",
			new String[] { Long.class.getName(), Long.class.getName() },
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_UID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_UID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dossiers where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_UID(long groupId, long userId) {
		return findByG_UID(groupId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_UID(long groupId, long userId, int start,
		int end) {
		return findByG_UID(groupId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_UID(long groupId, long userId, int start,
		int end, OrderByComparator<Dossier> orderByComparator) {
		return findByG_UID(groupId, userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_UID(long groupId, long userId, int start,
		int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID;
			finderArgs = new Object[] { groupId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UID;
			finderArgs = new Object[] {
					groupId, userId,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							(userId != dossier.getUserId())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_UID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_UID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_UID_First(long groupId, long userId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_UID_First(groupId, userId, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_UID_First(long groupId, long userId,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_UID(groupId, userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_UID_Last(long groupId, long userId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_UID_Last(groupId, userId, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_UID_Last(long groupId, long userId,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_UID(groupId, userId);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_UID(groupId, userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_UID_PrevAndNext(long dossierId, long groupId,
		long userId, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_UID_PrevAndNext(session, dossier, groupId,
					userId, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByG_UID_PrevAndNext(session, dossier, groupId,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByG_UID_PrevAndNext(Session session, Dossier dossier,
		long groupId, long userId,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_UID_GROUPID_2);

		query.append(_FINDER_COLUMN_G_UID_USERID_2);

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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByG_UID(long groupId, long userId) {
		for (Dossier dossier : findByG_UID(groupId, userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_UID(long groupId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_UID;

		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_UID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_UID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_G_UID_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_USERID_2 = "dossier.userId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_REF = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_REF",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.REFERENCEUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_REF = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_REF",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the dossier where groupId = &#63; and referenceUid = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @return the matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_REF(long groupId, String referenceUid)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_REF(groupId, referenceUid);

		if (dossier == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", referenceUid=");
			msg.append(referenceUid);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierException(msg.toString());
		}

		return dossier;
	}

	/**
	 * Returns the dossier where groupId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_REF(long groupId, String referenceUid) {
		return fetchByG_REF(groupId, referenceUid, true);
	}

	/**
	 * Returns the dossier where groupId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_REF(long groupId, String referenceUid,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, referenceUid };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_REF,
					finderArgs, this);
		}

		if (result instanceof Dossier) {
			Dossier dossier = (Dossier)result;

			if ((groupId != dossier.getGroupId()) ||
					!Objects.equals(referenceUid, dossier.getReferenceUid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_REF_GROUPID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_G_REF_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_G_REF_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_G_REF_REFERENCEUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
				}

				List<Dossier> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_REF,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierPersistenceImpl.fetchByG_REF(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Dossier dossier = list.get(0);

					result = dossier;

					cacheResult(dossier);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_REF, finderArgs);

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
			return (Dossier)result;
		}
	}

	/**
	 * Removes the dossier where groupId = &#63; and referenceUid = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @return the dossier that was removed
	 */
	@Override
	public Dossier removeByG_REF(long groupId, String referenceUid)
		throws NoSuchDossierException {
		Dossier dossier = findByG_REF(groupId, referenceUid);

		return remove(dossier);
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and referenceUid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_REF(long groupId, String referenceUid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_REF;

		Object[] finderArgs = new Object[] { groupId, referenceUid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_REF_GROUPID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_G_REF_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_G_REF_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_G_REF_REFERENCEUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
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

	private static final String _FINDER_COLUMN_G_REF_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_REF_REFERENCEUID_1 = "dossier.referenceUid IS NULL";
	private static final String _FINDER_COLUMN_G_REF_REFERENCEUID_2 = "dossier.referenceUid = ?";
	private static final String _FINDER_COLUMN_G_REF_REFERENCEUID_3 = "(dossier.referenceUid IS NULL OR dossier.referenceUid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_SC = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_SC",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SC = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_SC",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.SERVICECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_SC = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_SC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossiers where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_SC(long groupId, String serviceCode) {
		return findByG_SC(groupId, serviceCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_SC(long groupId, String serviceCode,
		int start, int end) {
		return findByG_SC(groupId, serviceCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_SC(long groupId, String serviceCode,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByG_SC(groupId, serviceCode, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_SC(long groupId, String serviceCode,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SC;
			finderArgs = new Object[] { groupId, serviceCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_SC;
			finderArgs = new Object[] {
					groupId, serviceCode,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							!Objects.equals(serviceCode,
								dossier.getServiceCode())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_SC_GROUPID_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_SC_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_SC_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_SC_SERVICECODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

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

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_SC_First(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_SC_First(groupId, serviceCode,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_SC_First(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_SC(groupId, serviceCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_SC_Last(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_SC_Last(groupId, serviceCode,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_SC_Last(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_SC(groupId, serviceCode);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_SC(groupId, serviceCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_SC_PrevAndNext(long dossierId, long groupId,
		String serviceCode, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_SC_PrevAndNext(session, dossier, groupId,
					serviceCode, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByG_SC_PrevAndNext(session, dossier, groupId,
					serviceCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByG_SC_PrevAndNext(Session session, Dossier dossier,
		long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_SC_GROUPID_2);

		boolean bindServiceCode = false;

		if (serviceCode == null) {
			query.append(_FINDER_COLUMN_G_SC_SERVICECODE_1);
		}
		else if (serviceCode.equals("")) {
			query.append(_FINDER_COLUMN_G_SC_SERVICECODE_3);
		}
		else {
			bindServiceCode = true;

			query.append(_FINDER_COLUMN_G_SC_SERVICECODE_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindServiceCode) {
			qPos.add(serviceCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and serviceCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 */
	@Override
	public void removeByG_SC(long groupId, String serviceCode) {
		for (Dossier dossier : findByG_SC(groupId, serviceCode,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_SC(long groupId, String serviceCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_SC;

		Object[] finderArgs = new Object[] { groupId, serviceCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_SC_GROUPID_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_SC_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_SC_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_SC_SERVICECODE_2);
			}

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

	private static final String _FINDER_COLUMN_G_SC_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_SC_SERVICECODE_1 = "dossier.serviceCode IS NULL";
	private static final String _FINDER_COLUMN_G_SC_SERVICECODE_2 = "dossier.serviceCode = ?";
	private static final String _FINDER_COLUMN_G_SC_SERVICECODE_3 = "(dossier.serviceCode IS NULL OR dossier.serviceCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_GAC = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_GAC",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_GAC = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_GAC",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.SERVICECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_GAC = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_GAC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossiers where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_GAC(long groupId, String serviceCode) {
		return findByG_GAC(groupId, serviceCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_GAC(long groupId, String serviceCode,
		int start, int end) {
		return findByG_GAC(groupId, serviceCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_GAC(long groupId, String serviceCode,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByG_GAC(groupId, serviceCode, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_GAC(long groupId, String serviceCode,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_GAC;
			finderArgs = new Object[] { groupId, serviceCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_GAC;
			finderArgs = new Object[] {
					groupId, serviceCode,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							!Objects.equals(serviceCode,
								dossier.getServiceCode())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_GAC_GROUPID_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_GAC_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_GAC_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_GAC_SERVICECODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

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

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_GAC_First(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_GAC_First(groupId, serviceCode,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_GAC_First(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_GAC(groupId, serviceCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_GAC_Last(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_GAC_Last(groupId, serviceCode,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_GAC_Last(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_GAC(groupId, serviceCode);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_GAC(groupId, serviceCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_GAC_PrevAndNext(long dossierId, long groupId,
		String serviceCode, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_GAC_PrevAndNext(session, dossier, groupId,
					serviceCode, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByG_GAC_PrevAndNext(session, dossier, groupId,
					serviceCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByG_GAC_PrevAndNext(Session session, Dossier dossier,
		long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_GAC_GROUPID_2);

		boolean bindServiceCode = false;

		if (serviceCode == null) {
			query.append(_FINDER_COLUMN_G_GAC_SERVICECODE_1);
		}
		else if (serviceCode.equals("")) {
			query.append(_FINDER_COLUMN_G_GAC_SERVICECODE_3);
		}
		else {
			bindServiceCode = true;

			query.append(_FINDER_COLUMN_G_GAC_SERVICECODE_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindServiceCode) {
			qPos.add(serviceCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and serviceCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 */
	@Override
	public void removeByG_GAC(long groupId, String serviceCode) {
		for (Dossier dossier : findByG_GAC(groupId, serviceCode,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_GAC(long groupId, String serviceCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_GAC;

		Object[] finderArgs = new Object[] { groupId, serviceCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_GAC_GROUPID_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_GAC_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_GAC_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_GAC_SERVICECODE_2);
			}

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

	private static final String _FINDER_COLUMN_G_GAC_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_GAC_SERVICECODE_1 = "dossier.serviceCode IS NULL";
	private static final String _FINDER_COLUMN_G_GAC_SERVICECODE_2 = "dossier.serviceCode = ?";
	private static final String _FINDER_COLUMN_G_GAC_SERVICECODE_3 = "(dossier.serviceCode IS NULL OR dossier.serviceCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_DTN = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_DTN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DTN = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_DTN",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.DOSSIERTEMPLATENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_DTN = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_DTN",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierTemplateNo the dossier template no
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_DTN(long groupId, String dossierTemplateNo) {
		return findByG_DTN(groupId, dossierTemplateNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierTemplateNo the dossier template no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_DTN(long groupId, String dossierTemplateNo,
		int start, int end) {
		return findByG_DTN(groupId, dossierTemplateNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierTemplateNo the dossier template no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_DTN(long groupId, String dossierTemplateNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByG_DTN(groupId, dossierTemplateNo, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierTemplateNo the dossier template no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_DTN(long groupId, String dossierTemplateNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DTN;
			finderArgs = new Object[] { groupId, dossierTemplateNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_DTN;
			finderArgs = new Object[] {
					groupId, dossierTemplateNo,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							!Objects.equals(dossierTemplateNo,
								dossier.getDossierTemplateNo())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_DTN_GROUPID_2);

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_G_DTN_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_G_DTN_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_G_DTN_DOSSIERTEMPLATENO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_DTN_First(long groupId, String dossierTemplateNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_DTN_First(groupId, dossierTemplateNo,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierTemplateNo=");
		msg.append(dossierTemplateNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_DTN_First(long groupId, String dossierTemplateNo,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_DTN(groupId, dossierTemplateNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_DTN_Last(long groupId, String dossierTemplateNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_DTN_Last(groupId, dossierTemplateNo,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierTemplateNo=");
		msg.append(dossierTemplateNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_DTN_Last(long groupId, String dossierTemplateNo,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_DTN(groupId, dossierTemplateNo);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_DTN(groupId, dossierTemplateNo, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_DTN_PrevAndNext(long dossierId, long groupId,
		String dossierTemplateNo, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_DTN_PrevAndNext(session, dossier, groupId,
					dossierTemplateNo, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByG_DTN_PrevAndNext(session, dossier, groupId,
					dossierTemplateNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByG_DTN_PrevAndNext(Session session, Dossier dossier,
		long groupId, String dossierTemplateNo,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_DTN_GROUPID_2);

		boolean bindDossierTemplateNo = false;

		if (dossierTemplateNo == null) {
			query.append(_FINDER_COLUMN_G_DTN_DOSSIERTEMPLATENO_1);
		}
		else if (dossierTemplateNo.equals("")) {
			query.append(_FINDER_COLUMN_G_DTN_DOSSIERTEMPLATENO_3);
		}
		else {
			bindDossierTemplateNo = true;

			query.append(_FINDER_COLUMN_G_DTN_DOSSIERTEMPLATENO_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindDossierTemplateNo) {
			qPos.add(dossierTemplateNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and dossierTemplateNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierTemplateNo the dossier template no
	 */
	@Override
	public void removeByG_DTN(long groupId, String dossierTemplateNo) {
		for (Dossier dossier : findByG_DTN(groupId, dossierTemplateNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierTemplateNo the dossier template no
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_DTN(long groupId, String dossierTemplateNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_DTN;

		Object[] finderArgs = new Object[] { groupId, dossierTemplateNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_DTN_GROUPID_2);

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_G_DTN_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_G_DTN_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_G_DTN_DOSSIERTEMPLATENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
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

	private static final String _FINDER_COLUMN_G_DTN_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_DTN_DOSSIERTEMPLATENO_1 = "dossier.dossierTemplateNo IS NULL";
	private static final String _FINDER_COLUMN_G_DTN_DOSSIERTEMPLATENO_2 = "dossier.dossierTemplateNo = ?";
	private static final String _FINDER_COLUMN_G_DTN_DOSSIERTEMPLATENO_3 = "(dossier.dossierTemplateNo IS NULL OR dossier.dossierTemplateNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_DID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_DID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_DID",
			new String[] { Long.class.getName(), Long.class.getName() },
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.DOSSIERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_DID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_DID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dossiers where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_DID(long groupId, long dossierId) {
		return findByG_DID(groupId, dossierId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_DID(long groupId, long dossierId, int start,
		int end) {
		return findByG_DID(groupId, dossierId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_DID(long groupId, long dossierId, int start,
		int end, OrderByComparator<Dossier> orderByComparator) {
		return findByG_DID(groupId, dossierId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_DID(long groupId, long dossierId, int start,
		int end, OrderByComparator<Dossier> orderByComparator,
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

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							(dossierId != dossier.getDossierId())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_DID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_DOSSIERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
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
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_DID_First(long groupId, long dossierId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_DID_First(groupId, dossierId,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_DID_First(long groupId, long dossierId,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_DID(groupId, dossierId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_DID_Last(long groupId, long dossierId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_DID_Last(groupId, dossierId,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_DID_Last(long groupId, long dossierId,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_DID(groupId, dossierId);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_DID(groupId, dossierId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and dossierId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 */
	@Override
	public void removeByG_DID(long groupId, long dossierId) {
		for (Dossier dossier : findByG_DID(groupId, dossierId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_DID(long groupId, long dossierId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_DID;

		Object[] finderArgs = new Object[] { groupId, dossierId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

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

	private static final String _FINDER_COLUMN_G_DID_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_DID_DOSSIERID_2 = "dossier.dossierId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NOTO_DS = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByNOTO_DS",
			new String[] {
				Integer.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOTO_DS = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByNOTO_DS",
			new String[] { Integer.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByNOTO_DS(int originality, String dossierStatus) {
		return findByNOTO_DS(originality, dossierStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNOTO_DS(int originality, String dossierStatus,
		int start, int end) {
		return findByNOTO_DS(originality, dossierStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNOTO_DS(int originality, String dossierStatus,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByNOTO_DS(originality, dossierStatus, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNOTO_DS(int originality, String dossierStatus,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NOTO_DS;
		finderArgs = new Object[] {
				originality, dossierStatus,
				
				start, end, orderByComparator
			};

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((originality == dossier.getOriginality()) ||
							!Objects.equals(dossierStatus,
								dossier.getDossierStatus())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_NOTO_DS_ORIGINALITY_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(originality);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByNOTO_DS_First(int originality, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByNOTO_DS_First(originality, dossierStatus,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("originality=");
		msg.append(originality);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByNOTO_DS_First(int originality, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByNOTO_DS(originality, dossierStatus, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByNOTO_DS_Last(int originality, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByNOTO_DS_Last(originality, dossierStatus,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("originality=");
		msg.append(originality);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByNOTO_DS_Last(int originality, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByNOTO_DS(originality, dossierStatus);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByNOTO_DS(originality, dossierStatus,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByNOTO_DS_PrevAndNext(long dossierId, int originality,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByNOTO_DS_PrevAndNext(session, dossier, originality,
					dossierStatus, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByNOTO_DS_PrevAndNext(session, dossier, originality,
					dossierStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByNOTO_DS_PrevAndNext(Session session,
		Dossier dossier, int originality, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_NOTO_DS_ORIGINALITY_2);

		boolean bindDossierStatus = false;

		if (dossierStatus == null) {
			query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_1);
		}
		else if (dossierStatus.equals("")) {
			query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_3);
		}
		else {
			bindDossierStatus = true;

			query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(originality);

		if (bindDossierStatus) {
			qPos.add(dossierStatus);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dossiers where originality &ne; all &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originalities the originalities
	 * @param dossierStatus the dossier status
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByNOTO_DS(int[] originalities, String dossierStatus) {
		return findByNOTO_DS(originalities, dossierStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where originality &ne; all &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originalities the originalities
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNOTO_DS(int[] originalities,
		String dossierStatus, int start, int end) {
		return findByNOTO_DS(originalities, dossierStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where originality &ne; all &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originalities the originalities
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNOTO_DS(int[] originalities,
		String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByNOTO_DS(originalities, dossierStatus, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where originality &ne; &#63; and dossierStatus = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNOTO_DS(int[] originalities,
		String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		if (originalities == null) {
			originalities = new int[0];
		}
		else if (originalities.length > 1) {
			originalities = ArrayUtil.unique(originalities);

			Arrays.sort(originalities);
		}

		if (originalities.length == 1) {
			return findByNOTO_DS(originalities[0], dossierStatus, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(originalities), dossierStatus
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(originalities), dossierStatus,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_NOTO_DS,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if (!ArrayUtil.contains(originalities,
								dossier.getOriginality()) ||
							!Objects.equals(dossierStatus,
								dossier.getDossierStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			if (originalities.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_NOTO_DS_ORIGINALITY_7);

				query.append(StringUtil.merge(originalities));

				query.append(")");

				query.append(")");

				query.append(WHERE_AND);
			}

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_NOTO_DS,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_NOTO_DS,
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
	 * Removes all the dossiers where originality &ne; &#63; and dossierStatus = &#63; from the database.
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 */
	@Override
	public void removeByNOTO_DS(int originality, String dossierStatus) {
		for (Dossier dossier : findByNOTO_DS(originality, dossierStatus,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByNOTO_DS(int originality, String dossierStatus) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOTO_DS;

		Object[] finderArgs = new Object[] { originality, dossierStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_NOTO_DS_ORIGINALITY_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(originality);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
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

	/**
	 * Returns the number of dossiers where originality &ne; all &#63; and dossierStatus = &#63;.
	 *
	 * @param originalities the originalities
	 * @param dossierStatus the dossier status
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByNOTO_DS(int[] originalities, String dossierStatus) {
		if (originalities == null) {
			originalities = new int[0];
		}
		else if (originalities.length > 1) {
			originalities = ArrayUtil.unique(originalities);

			Arrays.sort(originalities);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(originalities), dossierStatus
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOTO_DS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			if (originalities.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_NOTO_DS_ORIGINALITY_7);

				query.append(StringUtil.merge(originalities));

				query.append(")");

				query.append(")");

				query.append(WHERE_AND);
			}

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOTO_DS,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOTO_DS,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_NOTO_DS_ORIGINALITY_2 = "dossier.originality != ? AND ";
	private static final String _FINDER_COLUMN_NOTO_DS_ORIGINALITY_7 = "dossier.originality NOT IN (";
	private static final String _FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_1 = "dossier.dossierStatus IS NULL";
	private static final String _FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_2 = "dossier.dossierStatus = ?";
	private static final String _FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_3 = "(dossier.dossierStatus IS NULL OR dossier.dossierStatus = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_OG_DS = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_OG_DS",
			new String[] {
				Integer.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_OG_DS =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_OG_DS",
			new String[] { Integer.class.getName(), String.class.getName() },
			DossierModelImpl.ORIGINALITY_COLUMN_BITMASK |
			DossierModelImpl.DOSSIERSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_OG_DS = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_OG_DS",
			new String[] { Integer.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossiers where originality = &#63; and dossierStatus = &#63;.
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByF_OG_DS(int originality, String dossierStatus) {
		return findByF_OG_DS(originality, dossierStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where originality = &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByF_OG_DS(int originality, String dossierStatus,
		int start, int end) {
		return findByF_OG_DS(originality, dossierStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where originality = &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByF_OG_DS(int originality, String dossierStatus,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByF_OG_DS(originality, dossierStatus, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where originality = &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByF_OG_DS(int originality, String dossierStatus,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_OG_DS;
			finderArgs = new Object[] { originality, dossierStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_OG_DS;
			finderArgs = new Object[] {
					originality, dossierStatus,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((originality != dossier.getOriginality()) ||
							!Objects.equals(dossierStatus,
								dossier.getDossierStatus())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_F_OG_DS_ORIGINALITY_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_F_OG_DS_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_F_OG_DS_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_F_OG_DS_DOSSIERSTATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(originality);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByF_OG_DS_First(int originality, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByF_OG_DS_First(originality, dossierStatus,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("originality=");
		msg.append(originality);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByF_OG_DS_First(int originality, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByF_OG_DS(originality, dossierStatus, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByF_OG_DS_Last(int originality, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByF_OG_DS_Last(originality, dossierStatus,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("originality=");
		msg.append(originality);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByF_OG_DS_Last(int originality, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByF_OG_DS(originality, dossierStatus);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByF_OG_DS(originality, dossierStatus,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByF_OG_DS_PrevAndNext(long dossierId, int originality,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByF_OG_DS_PrevAndNext(session, dossier, originality,
					dossierStatus, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByF_OG_DS_PrevAndNext(session, dossier, originality,
					dossierStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByF_OG_DS_PrevAndNext(Session session,
		Dossier dossier, int originality, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_F_OG_DS_ORIGINALITY_2);

		boolean bindDossierStatus = false;

		if (dossierStatus == null) {
			query.append(_FINDER_COLUMN_F_OG_DS_DOSSIERSTATUS_1);
		}
		else if (dossierStatus.equals("")) {
			query.append(_FINDER_COLUMN_F_OG_DS_DOSSIERSTATUS_3);
		}
		else {
			bindDossierStatus = true;

			query.append(_FINDER_COLUMN_F_OG_DS_DOSSIERSTATUS_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(originality);

		if (bindDossierStatus) {
			qPos.add(dossierStatus);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where originality = &#63; and dossierStatus = &#63; from the database.
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 */
	@Override
	public void removeByF_OG_DS(int originality, String dossierStatus) {
		for (Dossier dossier : findByF_OG_DS(originality, dossierStatus,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where originality = &#63; and dossierStatus = &#63;.
	 *
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByF_OG_DS(int originality, String dossierStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_OG_DS;

		Object[] finderArgs = new Object[] { originality, dossierStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_F_OG_DS_ORIGINALITY_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_F_OG_DS_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_F_OG_DS_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_F_OG_DS_DOSSIERSTATUS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(originality);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
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

	private static final String _FINDER_COLUMN_F_OG_DS_ORIGINALITY_2 = "dossier.originality = ? AND ";
	private static final String _FINDER_COLUMN_F_OG_DS_DOSSIERSTATUS_1 = "dossier.dossierStatus IS NULL";
	private static final String _FINDER_COLUMN_F_OG_DS_DOSSIERSTATUS_2 = "dossier.dossierStatus = ?";
	private static final String _FINDER_COLUMN_F_OG_DS_DOSSIERSTATUS_3 = "(dossier.dossierStatus IS NULL OR dossier.dossierStatus = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C_GAC_SC_DTNO_NOTDS =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_C_GAC_SC_DTNO_NOTDS",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_C_GAC_SC_DTNO_NOTDS =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByG_C_GAC_SC_DTNO_NOTDS",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			});

	/**
	 * Returns all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus) {
		return findByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int start, int end) {
		return findByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C_GAC_SC_DTNO_NOTDS;
		finderArgs = new Object[] {
				groupId, companyId, govAgencyCode, serviceCode,
				dossierTemplateNo, dossierStatus,
				
				start, end, orderByComparator
			};

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							(companyId != dossier.getCompanyId()) ||
							!Objects.equals(govAgencyCode,
								dossier.getGovAgencyCode()) ||
							!Objects.equals(serviceCode,
								dossier.getServiceCode()) ||
							!Objects.equals(dossierTemplateNo,
								dossier.getDossierTemplateNo()) ||
							Objects.equals(dossierStatus,
								dossier.getDossierStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(8 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(8);
			}

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_COMPANYID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GOVAGENCYCODE_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_SERVICECODE_2);
			}

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERTEMPLATENO_2);
			}

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERSTATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_C_GAC_SC_DTNO_NOTDS_First(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_C_GAC_SC_DTNO_NOTDS_First(groupId,
				companyId, govAgencyCode, serviceCode, dossierTemplateNo,
				dossierStatus, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append(", dossierTemplateNo=");
		msg.append(dossierTemplateNo);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_C_GAC_SC_DTNO_NOTDS_First(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_C_GAC_SC_DTNO_NOTDS_Last(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_C_GAC_SC_DTNO_NOTDS_Last(groupId, companyId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append(", dossierTemplateNo=");
		msg.append(dossierTemplateNo);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_C_GAC_SC_DTNO_NOTDS_Last(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_C_GAC_SC_DTNO_NOTDS_PrevAndNext(long dossierId,
		long groupId, long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_C_GAC_SC_DTNO_NOTDS_PrevAndNext(session, dossier,
					groupId, companyId, govAgencyCode, serviceCode,
					dossierTemplateNo, dossierStatus, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByG_C_GAC_SC_DTNO_NOTDS_PrevAndNext(session, dossier,
					groupId, companyId, govAgencyCode, serviceCode,
					dossierTemplateNo, dossierStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByG_C_GAC_SC_DTNO_NOTDS_PrevAndNext(Session session,
		Dossier dossier, long groupId, long companyId, String govAgencyCode,
		String serviceCode, String dossierTemplateNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(9 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(8);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_COMPANYID_2);

		boolean bindGovAgencyCode = false;

		if (govAgencyCode == null) {
			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GOVAGENCYCODE_1);
		}
		else if (govAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GOVAGENCYCODE_3);
		}
		else {
			bindGovAgencyCode = true;

			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GOVAGENCYCODE_2);
		}

		boolean bindServiceCode = false;

		if (serviceCode == null) {
			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_SERVICECODE_1);
		}
		else if (serviceCode.equals("")) {
			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_SERVICECODE_3);
		}
		else {
			bindServiceCode = true;

			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_SERVICECODE_2);
		}

		boolean bindDossierTemplateNo = false;

		if (dossierTemplateNo == null) {
			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERTEMPLATENO_1);
		}
		else if (dossierTemplateNo.equals("")) {
			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERTEMPLATENO_3);
		}
		else {
			bindDossierTemplateNo = true;

			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERTEMPLATENO_2);
		}

		boolean bindDossierStatus = false;

		if (dossierStatus == null) {
			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERSTATUS_1);
		}
		else if (dossierStatus.equals("")) {
			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERSTATUS_3);
		}
		else {
			bindDossierStatus = true;

			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERSTATUS_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(companyId);

		if (bindGovAgencyCode) {
			qPos.add(govAgencyCode);
		}

		if (bindServiceCode) {
			qPos.add(serviceCode);
		}

		if (bindDossierTemplateNo) {
			qPos.add(dossierTemplateNo);
		}

		if (bindDossierStatus) {
			qPos.add(dossierStatus);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 */
	@Override
	public void removeByG_C_GAC_SC_DTNO_NOTDS(long groupId, long companyId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus) {
		for (Dossier dossier : findByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_C_GAC_SC_DTNO_NOTDS(long groupId, long companyId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_C_GAC_SC_DTNO_NOTDS;

		Object[] finderArgs = new Object[] {
				groupId, companyId, govAgencyCode, serviceCode,
				dossierTemplateNo, dossierStatus
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_COMPANYID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GOVAGENCYCODE_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_SERVICECODE_2);
			}

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERTEMPLATENO_2);
			}

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERSTATUS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
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

	private static final String _FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_COMPANYID_2 =
		"dossier.companyId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GOVAGENCYCODE_1 =
		"dossier.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GOVAGENCYCODE_2 =
		"dossier.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_GOVAGENCYCODE_3 =
		"(dossier.govAgencyCode IS NULL OR dossier.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_SERVICECODE_1 =
		"dossier.serviceCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_SERVICECODE_2 =
		"dossier.serviceCode = ? AND ";
	private static final String _FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_SERVICECODE_3 =
		"(dossier.serviceCode IS NULL OR dossier.serviceCode = '') AND ";
	private static final String _FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERTEMPLATENO_1 =
		"dossier.dossierTemplateNo IS NULL AND ";
	private static final String _FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERTEMPLATENO_2 =
		"dossier.dossierTemplateNo = ? AND ";
	private static final String _FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERTEMPLATENO_3 =
		"(dossier.dossierTemplateNo IS NULL OR dossier.dossierTemplateNo = '') AND ";
	private static final String _FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERSTATUS_1 =
		"dossier.dossierStatus IS NOT NULL";
	private static final String _FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERSTATUS_2 =
		"dossier.dossierStatus != ?";
	private static final String _FINDER_COLUMN_G_C_GAC_SC_DTNO_NOTDS_DOSSIERSTATUS_3 =
		"(dossier.dossierStatus IS NULL OR dossier.dossierStatus != '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_GOV_DID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_GID_GOV_DID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Long.class.getName()
			},
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			DossierModelImpl.SERVICECODE_COLUMN_BITMASK |
			DossierModelImpl.DOSSIERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_GOV_DID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_GOV_DID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the dossier where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierId the dossier ID
	 * @return the matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByF_GID_GOV_DID(long groupId, String govAgencyCode,
		String serviceCode, long dossierId) throws NoSuchDossierException {
		Dossier dossier = fetchByF_GID_GOV_DID(groupId, govAgencyCode,
				serviceCode, dossierId);

		if (dossier == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", govAgencyCode=");
			msg.append(govAgencyCode);

			msg.append(", serviceCode=");
			msg.append(serviceCode);

			msg.append(", dossierId=");
			msg.append(dossierId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierException(msg.toString());
		}

		return dossier;
	}

	/**
	 * Returns the dossier where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierId the dossier ID
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByF_GID_GOV_DID(long groupId, String govAgencyCode,
		String serviceCode, long dossierId) {
		return fetchByF_GID_GOV_DID(groupId, govAgencyCode, serviceCode,
			dossierId, true);
	}

	/**
	 * Returns the dossier where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierId the dossier ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByF_GID_GOV_DID(long groupId, String govAgencyCode,
		String serviceCode, long dossierId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, govAgencyCode, serviceCode, dossierId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_GOV_DID,
					finderArgs, this);
		}

		if (result instanceof Dossier) {
			Dossier dossier = (Dossier)result;

			if ((groupId != dossier.getGroupId()) ||
					!Objects.equals(govAgencyCode, dossier.getGovAgencyCode()) ||
					!Objects.equals(serviceCode, dossier.getServiceCode()) ||
					(dossierId != dossier.getDossierId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_F_GID_GOV_DID_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_F_GID_GOV_DID_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_GOV_DID_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_F_GID_GOV_DID_GOVAGENCYCODE_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_F_GID_GOV_DID_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_GOV_DID_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_F_GID_GOV_DID_SERVICECODE_2);
			}

			query.append(_FINDER_COLUMN_F_GID_GOV_DID_DOSSIERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				qPos.add(dossierId);

				List<Dossier> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_GOV_DID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierPersistenceImpl.fetchByF_GID_GOV_DID(long, String, String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Dossier dossier = list.get(0);

					result = dossier;

					cacheResult(dossier);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_GOV_DID,
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
			return (Dossier)result;
		}
	}

	/**
	 * Removes the dossier where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierId the dossier ID
	 * @return the dossier that was removed
	 */
	@Override
	public Dossier removeByF_GID_GOV_DID(long groupId, String govAgencyCode,
		String serviceCode, long dossierId) throws NoSuchDossierException {
		Dossier dossier = findByF_GID_GOV_DID(groupId, govAgencyCode,
				serviceCode, dossierId);

		return remove(dossier);
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierId the dossier ID
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByF_GID_GOV_DID(long groupId, String govAgencyCode,
		String serviceCode, long dossierId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_GOV_DID;

		Object[] finderArgs = new Object[] {
				groupId, govAgencyCode, serviceCode, dossierId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_F_GID_GOV_DID_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_F_GID_GOV_DID_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_GOV_DID_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_F_GID_GOV_DID_GOVAGENCYCODE_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_F_GID_GOV_DID_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_GOV_DID_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_F_GID_GOV_DID_SERVICECODE_2);
			}

			query.append(_FINDER_COLUMN_F_GID_GOV_DID_DOSSIERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

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

	private static final String _FINDER_COLUMN_F_GID_GOV_DID_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_GOV_DID_GOVAGENCYCODE_1 = "dossier.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_GOV_DID_GOVAGENCYCODE_2 = "dossier.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_GOV_DID_GOVAGENCYCODE_3 = "(dossier.govAgencyCode IS NULL OR dossier.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_F_GID_GOV_DID_SERVICECODE_1 = "dossier.serviceCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_GOV_DID_SERVICECODE_2 = "dossier.serviceCode = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_GOV_DID_SERVICECODE_3 = "(dossier.serviceCode IS NULL OR dossier.serviceCode = '') AND ";
	private static final String _FINDER_COLUMN_F_GID_GOV_DID_DOSSIERID_2 = "dossier.dossierId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTO_DS_SC_GC =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_NOTO_DS_SC_GC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTO_DS_SC_GC =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_NOTO_DS_SC_GC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTO_DS_SC_GC(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode) {
		return findByG_NOTO_DS_SC_GC(groupId, originality, dossierStatus,
			serviceCode, govAgencyCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTO_DS_SC_GC(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode,
		int start, int end) {
		return findByG_NOTO_DS_SC_GC(groupId, originality, dossierStatus,
			serviceCode, govAgencyCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTO_DS_SC_GC(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByG_NOTO_DS_SC_GC(groupId, originality, dossierStatus,
			serviceCode, govAgencyCode, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTO_DS_SC_GC(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTO_DS_SC_GC;
		finderArgs = new Object[] {
				groupId, originality, dossierStatus, serviceCode, govAgencyCode,
				
				start, end, orderByComparator
			};

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							(originality == dossier.getOriginality()) ||
							!Objects.equals(dossierStatus,
								dossier.getDossierStatus()) ||
							!Objects.equals(serviceCode,
								dossier.getServiceCode()) ||
							!Objects.equals(govAgencyCode,
								dossier.getGovAgencyCode())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_GROUPID_2);

			query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_ORIGINALITY_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_DOSSIERSTATUS_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_SERVICECODE_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_GOVAGENCYCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(originality);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_NOTO_DS_SC_GC_First(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_NOTO_DS_SC_GC_First(groupId, originality,
				dossierStatus, serviceCode, govAgencyCode, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", originality=");
		msg.append(originality);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_NOTO_DS_SC_GC_First(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_NOTO_DS_SC_GC(groupId, originality,
				dossierStatus, serviceCode, govAgencyCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_NOTO_DS_SC_GC_Last(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_NOTO_DS_SC_GC_Last(groupId, originality,
				dossierStatus, serviceCode, govAgencyCode, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", originality=");
		msg.append(originality);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_NOTO_DS_SC_GC_Last(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_NOTO_DS_SC_GC(groupId, originality, dossierStatus,
				serviceCode, govAgencyCode);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_NOTO_DS_SC_GC(groupId, originality,
				dossierStatus, serviceCode, govAgencyCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_NOTO_DS_SC_GC_PrevAndNext(long dossierId,
		long groupId, int originality, String dossierStatus,
		String serviceCode, String govAgencyCode,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_NOTO_DS_SC_GC_PrevAndNext(session, dossier,
					groupId, originality, dossierStatus, serviceCode,
					govAgencyCode, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByG_NOTO_DS_SC_GC_PrevAndNext(session, dossier,
					groupId, originality, dossierStatus, serviceCode,
					govAgencyCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByG_NOTO_DS_SC_GC_PrevAndNext(Session session,
		Dossier dossier, long groupId, int originality, String dossierStatus,
		String serviceCode, String govAgencyCode,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(8 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_GROUPID_2);

		query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_ORIGINALITY_2);

		boolean bindDossierStatus = false;

		if (dossierStatus == null) {
			query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_DOSSIERSTATUS_1);
		}
		else if (dossierStatus.equals("")) {
			query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_DOSSIERSTATUS_3);
		}
		else {
			bindDossierStatus = true;

			query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_DOSSIERSTATUS_2);
		}

		boolean bindServiceCode = false;

		if (serviceCode == null) {
			query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_SERVICECODE_1);
		}
		else if (serviceCode.equals("")) {
			query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_SERVICECODE_3);
		}
		else {
			bindServiceCode = true;

			query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_SERVICECODE_2);
		}

		boolean bindGovAgencyCode = false;

		if (govAgencyCode == null) {
			query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_GOVAGENCYCODE_1);
		}
		else if (govAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_GOVAGENCYCODE_3);
		}
		else {
			bindGovAgencyCode = true;

			query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_GOVAGENCYCODE_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(originality);

		if (bindDossierStatus) {
			qPos.add(dossierStatus);
		}

		if (bindServiceCode) {
			qPos.add(serviceCode);
		}

		if (bindGovAgencyCode) {
			qPos.add(govAgencyCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 */
	@Override
	public void removeByG_NOTO_DS_SC_GC(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode) {
		for (Dossier dossier : findByG_NOTO_DS_SC_GC(groupId, originality,
				dossierStatus, serviceCode, govAgencyCode, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originality the originality
	 * @param dossierStatus the dossier status
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_NOTO_DS_SC_GC(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTO_DS_SC_GC;

		Object[] finderArgs = new Object[] {
				groupId, originality, dossierStatus, serviceCode, govAgencyCode
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_GROUPID_2);

			query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_ORIGINALITY_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_DOSSIERSTATUS_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_SERVICECODE_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_NOTO_DS_SC_GC_GOVAGENCYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(originality);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
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

	private static final String _FINDER_COLUMN_G_NOTO_DS_SC_GC_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_NOTO_DS_SC_GC_ORIGINALITY_2 = "dossier.originality != ? AND ";
	private static final String _FINDER_COLUMN_G_NOTO_DS_SC_GC_DOSSIERSTATUS_1 = "dossier.dossierStatus IS NULL AND ";
	private static final String _FINDER_COLUMN_G_NOTO_DS_SC_GC_DOSSIERSTATUS_2 = "dossier.dossierStatus = ? AND ";
	private static final String _FINDER_COLUMN_G_NOTO_DS_SC_GC_DOSSIERSTATUS_3 = "(dossier.dossierStatus IS NULL OR dossier.dossierStatus = '') AND ";
	private static final String _FINDER_COLUMN_G_NOTO_DS_SC_GC_SERVICECODE_1 = "dossier.serviceCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_NOTO_DS_SC_GC_SERVICECODE_2 = "dossier.serviceCode = ? AND ";
	private static final String _FINDER_COLUMN_G_NOTO_DS_SC_GC_SERVICECODE_3 = "(dossier.serviceCode IS NULL OR dossier.serviceCode = '') AND ";
	private static final String _FINDER_COLUMN_G_NOTO_DS_SC_GC_GOVAGENCYCODE_1 = "dossier.govAgencyCode IS NULL";
	private static final String _FINDER_COLUMN_G_NOTO_DS_SC_GC_GOVAGENCYCODE_2 = "dossier.govAgencyCode = ?";
	private static final String _FINDER_COLUMN_G_NOTO_DS_SC_GC_GOVAGENCYCODE_3 = "(dossier.govAgencyCode IS NULL OR dossier.govAgencyCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_GC_SC_DTN_DS_APP_ORI =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGID_GC_SC_DTN_DS_APP_ORI",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_GC_SC_DTN_DS_APP_ORI =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGID_GC_SC_DTN_DS_APP_ORI",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			DossierModelImpl.SERVICECODE_COLUMN_BITMASK |
			DossierModelImpl.DOSSIERTEMPLATENO_COLUMN_BITMASK |
			DossierModelImpl.DOSSIERSTATUS_COLUMN_BITMASK |
			DossierModelImpl.APPLICANTIDTYPE_COLUMN_BITMASK |
			DossierModelImpl.ORIGINALITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_GC_SC_DTN_DS_APP_ORI =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGID_GC_SC_DTN_DS_APP_ORI",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GID_GC_SC_DTN_DS_APP_ORI =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByGID_GC_SC_DTN_DS_APP_ORI",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality) {
		return findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, applicantIdType,
			originality, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality,
		int start, int end) {
		return findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, applicantIdType,
			originality, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, applicantIdType,
			originality, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_GC_SC_DTN_DS_APP_ORI;
			finderArgs = new Object[] {
					groupId, govAgencyCode, serviceCode, dossierTemplateNo,
					dossierStatus, applicantIdType, originality
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_GC_SC_DTN_DS_APP_ORI;
			finderArgs = new Object[] {
					groupId, govAgencyCode, serviceCode, dossierTemplateNo,
					dossierStatus, applicantIdType, originality,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							!Objects.equals(govAgencyCode,
								dossier.getGovAgencyCode()) ||
							!Objects.equals(serviceCode,
								dossier.getServiceCode()) ||
							!Objects.equals(dossierTemplateNo,
								dossier.getDossierTemplateNo()) ||
							!Objects.equals(dossierStatus,
								dossier.getDossierStatus()) ||
							!Objects.equals(applicantIdType,
								dossier.getApplicantIdType()) ||
							(originality != dossier.getOriginality())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(9 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(9);
			}

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_2);
			}

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_2);
			}

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_2);
			}

			boolean bindApplicantIdType = false;

			if (applicantIdType == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_1);
			}
			else if (applicantIdType.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_3);
			}
			else {
				bindApplicantIdType = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_2);
			}

			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_ORIGINALITY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (bindApplicantIdType) {
					qPos.add(applicantIdType);
				}

				qPos.add(originality);

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByGID_GC_SC_DTN_DS_APP_ORI_First(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByGID_GC_SC_DTN_DS_APP_ORI_First(groupId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
				applicantIdType, originality, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(16);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append(", dossierTemplateNo=");
		msg.append(dossierTemplateNo);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", applicantIdType=");
		msg.append(applicantIdType);

		msg.append(", originality=");
		msg.append(originality);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByGID_GC_SC_DTN_DS_APP_ORI_First(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByGID_GC_SC_DTN_DS_APP_ORI(groupId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
				applicantIdType, originality, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByGID_GC_SC_DTN_DS_APP_ORI_Last(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByGID_GC_SC_DTN_DS_APP_ORI_Last(groupId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
				applicantIdType, originality, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(16);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append(", dossierTemplateNo=");
		msg.append(dossierTemplateNo);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", applicantIdType=");
		msg.append(applicantIdType);

		msg.append(", originality=");
		msg.append(originality);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByGID_GC_SC_DTN_DS_APP_ORI_Last(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
				serviceCode, dossierTemplateNo, dossierStatus, applicantIdType,
				originality);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByGID_GC_SC_DTN_DS_APP_ORI(groupId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
				applicantIdType, originality, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByGID_GC_SC_DTN_DS_APP_ORI_PrevAndNext(
		long dossierId, long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, String applicantIdType,
		int originality, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByGID_GC_SC_DTN_DS_APP_ORI_PrevAndNext(session,
					dossier, groupId, govAgencyCode, serviceCode,
					dossierTemplateNo, dossierStatus, applicantIdType,
					originality, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByGID_GC_SC_DTN_DS_APP_ORI_PrevAndNext(session,
					dossier, groupId, govAgencyCode, serviceCode,
					dossierTemplateNo, dossierStatus, applicantIdType,
					originality, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByGID_GC_SC_DTN_DS_APP_ORI_PrevAndNext(
		Session session, Dossier dossier, long groupId, String govAgencyCode,
		String serviceCode, String dossierTemplateNo, String dossierStatus,
		String applicantIdType, int originality,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(10 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(9);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GROUPID_2);

		boolean bindGovAgencyCode = false;

		if (govAgencyCode == null) {
			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_1);
		}
		else if (govAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_3);
		}
		else {
			bindGovAgencyCode = true;

			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_2);
		}

		boolean bindServiceCode = false;

		if (serviceCode == null) {
			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_1);
		}
		else if (serviceCode.equals("")) {
			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_3);
		}
		else {
			bindServiceCode = true;

			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_2);
		}

		boolean bindDossierTemplateNo = false;

		if (dossierTemplateNo == null) {
			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_1);
		}
		else if (dossierTemplateNo.equals("")) {
			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_3);
		}
		else {
			bindDossierTemplateNo = true;

			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_2);
		}

		boolean bindDossierStatus = false;

		if (dossierStatus == null) {
			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_1);
		}
		else if (dossierStatus.equals("")) {
			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_3);
		}
		else {
			bindDossierStatus = true;

			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_2);
		}

		boolean bindApplicantIdType = false;

		if (applicantIdType == null) {
			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_1);
		}
		else if (applicantIdType.equals("")) {
			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_3);
		}
		else {
			bindApplicantIdType = true;

			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_2);
		}

		query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_ORIGINALITY_2);

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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindGovAgencyCode) {
			qPos.add(govAgencyCode);
		}

		if (bindServiceCode) {
			qPos.add(serviceCode);
		}

		if (bindDossierTemplateNo) {
			qPos.add(dossierTemplateNo);
		}

		if (bindDossierStatus) {
			qPos.add(dossierStatus);
		}

		if (bindApplicantIdType) {
			qPos.add(applicantIdType);
		}

		qPos.add(originality);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = any &#63; and applicantIdType = &#63; and originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatuses the dossier statuses
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String[] dossierStatuses, String applicantIdType, int originality) {
		return findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatuses, applicantIdType,
			originality, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = any &#63; and applicantIdType = &#63; and originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatuses the dossier statuses
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String[] dossierStatuses, String applicantIdType, int originality,
		int start, int end) {
		return findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatuses, applicantIdType,
			originality, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = any &#63; and applicantIdType = &#63; and originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatuses the dossier statuses
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String[] dossierStatuses, String applicantIdType, int originality,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatuses, applicantIdType,
			originality, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String[] dossierStatuses, String applicantIdType, int originality,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		if (dossierStatuses == null) {
			dossierStatuses = new String[0];
		}
		else if (dossierStatuses.length > 1) {
			dossierStatuses = ArrayUtil.distinct(dossierStatuses,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(dossierStatuses, NULL_SAFE_STRING_COMPARATOR);
		}

		if (dossierStatuses.length == 1) {
			return findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
				serviceCode, dossierTemplateNo, dossierStatuses[0],
				applicantIdType, originality, start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, govAgencyCode, serviceCode, dossierTemplateNo,
					StringUtil.merge(dossierStatuses), applicantIdType,
					originality
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, govAgencyCode, serviceCode, dossierTemplateNo,
					StringUtil.merge(dossierStatuses), applicantIdType,
					originality,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_GC_SC_DTN_DS_APP_ORI,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							!Objects.equals(govAgencyCode,
								dossier.getGovAgencyCode()) ||
							!Objects.equals(serviceCode,
								dossier.getServiceCode()) ||
							!Objects.equals(dossierTemplateNo,
								dossier.getDossierTemplateNo()) ||
							!ArrayUtil.contains(dossierStatuses,
								dossier.getDossierStatus()) ||
							!Objects.equals(applicantIdType,
								dossier.getApplicantIdType()) ||
							(originality != dossier.getOriginality())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_2);
			}

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_2);
			}

			if (dossierStatuses.length > 0) {
				query.append("(");

				for (int i = 0; i < dossierStatuses.length; i++) {
					String dossierStatus = dossierStatuses[i];

					if (dossierStatus == null) {
						query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_4);
					}
					else if (dossierStatus.equals("")) {
						query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_6);
					}
					else {
						query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_5);
					}

					if ((i + 1) < dossierStatuses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(")");

				query.append(WHERE_AND);
			}

			boolean bindApplicantIdType = false;

			if (applicantIdType == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_1);
			}
			else if (applicantIdType.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_3);
			}
			else {
				bindApplicantIdType = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_2);
			}

			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_ORIGINALITY_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				for (String dossierStatus : dossierStatuses) {
					if ((dossierStatus != null) && !dossierStatus.isEmpty()) {
						qPos.add(dossierStatus);
					}
				}

				if (bindApplicantIdType) {
					qPos.add(applicantIdType);
				}

				qPos.add(originality);

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_GC_SC_DTN_DS_APP_ORI,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_GC_SC_DTN_DS_APP_ORI,
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
	 * Removes all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 */
	@Override
	public void removeByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality) {
		for (Dossier dossier : findByGID_GC_SC_DTN_DS_APP_ORI(groupId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
				applicantIdType, originality, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_GC_SC_DTN_DS_APP_ORI;

		Object[] finderArgs = new Object[] {
				groupId, govAgencyCode, serviceCode, dossierTemplateNo,
				dossierStatus, applicantIdType, originality
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(8);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_2);
			}

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_2);
			}

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_2);
			}

			boolean bindApplicantIdType = false;

			if (applicantIdType == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_1);
			}
			else if (applicantIdType.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_3);
			}
			else {
				bindApplicantIdType = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_2);
			}

			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_ORIGINALITY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (bindApplicantIdType) {
					qPos.add(applicantIdType);
				}

				qPos.add(originality);

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
	 * Returns the number of dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = any &#63; and applicantIdType = &#63; and originality = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatuses the dossier statuses
	 * @param applicantIdType the applicant ID type
	 * @param originality the originality
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String[] dossierStatuses, String applicantIdType, int originality) {
		if (dossierStatuses == null) {
			dossierStatuses = new String[0];
		}
		else if (dossierStatuses.length > 1) {
			dossierStatuses = ArrayUtil.distinct(dossierStatuses,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(dossierStatuses, NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] {
				groupId, govAgencyCode, serviceCode, dossierTemplateNo,
				StringUtil.merge(dossierStatuses), applicantIdType, originality
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GID_GC_SC_DTN_DS_APP_ORI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_2);
			}

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_2);
			}

			if (dossierStatuses.length > 0) {
				query.append("(");

				for (int i = 0; i < dossierStatuses.length; i++) {
					String dossierStatus = dossierStatuses[i];

					if (dossierStatus == null) {
						query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_4);
					}
					else if (dossierStatus.equals("")) {
						query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_6);
					}
					else {
						query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_5);
					}

					if ((i + 1) < dossierStatuses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(")");

				query.append(WHERE_AND);
			}

			boolean bindApplicantIdType = false;

			if (applicantIdType == null) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_1);
			}
			else if (applicantIdType.equals("")) {
				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_3);
			}
			else {
				bindApplicantIdType = true;

				query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_2);
			}

			query.append(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_ORIGINALITY_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				for (String dossierStatus : dossierStatuses) {
					if ((dossierStatus != null) && !dossierStatus.isEmpty()) {
						qPos.add(dossierStatus);
					}
				}

				if (bindApplicantIdType) {
					qPos.add(applicantIdType);
				}

				qPos.add(originality);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GID_GC_SC_DTN_DS_APP_ORI,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GID_GC_SC_DTN_DS_APP_ORI,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GROUPID_2 =
		"dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_1 =
		"dossier.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_2 =
		"dossier.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_GOVAGENCYCODE_3 =
		"(dossier.govAgencyCode IS NULL OR dossier.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_1 =
		"dossier.serviceCode IS NULL AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_2 =
		"dossier.serviceCode = ? AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_SERVICECODE_3 =
		"(dossier.serviceCode IS NULL OR dossier.serviceCode = '') AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_1 =
		"dossier.dossierTemplateNo IS NULL AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_2 =
		"dossier.dossierTemplateNo = ? AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERTEMPLATENO_3 =
		"(dossier.dossierTemplateNo IS NULL OR dossier.dossierTemplateNo = '') AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_1 =
		"dossier.dossierStatus IS NULL AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_2 =
		"dossier.dossierStatus = ? AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_3 =
		"(dossier.dossierStatus IS NULL OR dossier.dossierStatus = '') AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_4 =
		"(" +
		removeConjunction(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_1) +
		")";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_5 =
		"(" +
		removeConjunction(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_2) +
		")";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_6 =
		"(" +
		removeConjunction(_FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_DOSSIERSTATUS_3) +
		")";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_1 =
		"dossier.applicantIdType IS NULL AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_2 =
		"dossier.applicantIdType = ? AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_APPLICANTIDTYPE_3 =
		"(dossier.applicantIdType IS NULL OR dossier.applicantIdType = '') AND ";
	private static final String _FINDER_COLUMN_GID_GC_SC_DTN_DS_APP_ORI_ORIGINALITY_2 =
		"dossier.originality = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_O_DID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_O_DID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_O_DID =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_O_DID",
			new String[] { Long.class.getName(), Long.class.getName() },
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.ORIGINDOSSIERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_O_DID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_O_DID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dossiers where groupId = &#63; and originDossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originDossierId the origin dossier ID
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_O_DID(long groupId, long originDossierId) {
		return findByG_O_DID(groupId, originDossierId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and originDossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param originDossierId the origin dossier ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_O_DID(long groupId, long originDossierId,
		int start, int end) {
		return findByG_O_DID(groupId, originDossierId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and originDossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param originDossierId the origin dossier ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_O_DID(long groupId, long originDossierId,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByG_O_DID(groupId, originDossierId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and originDossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param originDossierId the origin dossier ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_O_DID(long groupId, long originDossierId,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_O_DID;
			finderArgs = new Object[] { groupId, originDossierId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_O_DID;
			finderArgs = new Object[] {
					groupId, originDossierId,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							(originDossierId != dossier.getOriginDossierId())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_O_DID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_O_DID_ORIGINDOSSIERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(originDossierId);

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originDossierId the origin dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_O_DID_First(long groupId, long originDossierId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_O_DID_First(groupId, originDossierId,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", originDossierId=");
		msg.append(originDossierId);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originDossierId the origin dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_O_DID_First(long groupId, long originDossierId,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_O_DID(groupId, originDossierId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originDossierId the origin dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_O_DID_Last(long groupId, long originDossierId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_O_DID_Last(groupId, originDossierId,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", originDossierId=");
		msg.append(originDossierId);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originDossierId the origin dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_O_DID_Last(long groupId, long originDossierId,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_O_DID(groupId, originDossierId);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_O_DID(groupId, originDossierId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param originDossierId the origin dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_O_DID_PrevAndNext(long dossierId, long groupId,
		long originDossierId, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_O_DID_PrevAndNext(session, dossier, groupId,
					originDossierId, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByG_O_DID_PrevAndNext(session, dossier, groupId,
					originDossierId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByG_O_DID_PrevAndNext(Session session,
		Dossier dossier, long groupId, long originDossierId,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_O_DID_GROUPID_2);

		query.append(_FINDER_COLUMN_G_O_DID_ORIGINDOSSIERID_2);

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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(originDossierId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and originDossierId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param originDossierId the origin dossier ID
	 */
	@Override
	public void removeByG_O_DID(long groupId, long originDossierId) {
		for (Dossier dossier : findByG_O_DID(groupId, originDossierId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and originDossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originDossierId the origin dossier ID
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_O_DID(long groupId, long originDossierId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_O_DID;

		Object[] finderArgs = new Object[] { groupId, originDossierId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_O_DID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_O_DID_ORIGINDOSSIERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(originDossierId);

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

	private static final String _FINDER_COLUMN_G_O_DID_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_O_DID_ORIGINDOSSIERID_2 = "dossier.originDossierId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG",
			new String[] { Long.class.getName() },
			DossierModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dossiers where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG(long groupId) {
		return findByG(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG(long groupId, int start, int end) {
		return findByG(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG(long groupId, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByG(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG(long groupId, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_First(long groupId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_First(groupId, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_First(long groupId,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_Last(long groupId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_Last(groupId, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_Last(long groupId,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG(groupId);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_PrevAndNext(long dossierId, long groupId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_PrevAndNext(session, dossier, groupId,
					orderByComparator, true);

			array[1] = dossier;

			array[2] = getByG_PrevAndNext(session, dossier, groupId,
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

	protected Dossier getByG_PrevAndNext(Session session, Dossier dossier,
		long groupId, OrderByComparator<Dossier> orderByComparator,
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

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_GROUPID_2);

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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByG(long groupId) {
		for (Dossier dossier : findByG(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_GROUPID_2);

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

	private static final String _FINDER_COLUMN_G_GROUPID_2 = "dossier.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DN_AN = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDN_AN",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DN_AN = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDN_AN",
			new String[] { String.class.getName(), String.class.getName() },
			DossierModelImpl.DOSSIERNO_COLUMN_BITMASK |
			DossierModelImpl.APPLICANTIDNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DN_AN = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDN_AN",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @param applicantIdNo the applicant ID no
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByDN_AN(String dossierNo, String applicantIdNo) {
		return findByDN_AN(dossierNo, applicantIdNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierNo the dossier no
	 * @param applicantIdNo the applicant ID no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByDN_AN(String dossierNo, String applicantIdNo,
		int start, int end) {
		return findByDN_AN(dossierNo, applicantIdNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierNo the dossier no
	 * @param applicantIdNo the applicant ID no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByDN_AN(String dossierNo, String applicantIdNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByDN_AN(dossierNo, applicantIdNo, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierNo the dossier no
	 * @param applicantIdNo the applicant ID no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByDN_AN(String dossierNo, String applicantIdNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DN_AN;
			finderArgs = new Object[] { dossierNo, applicantIdNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DN_AN;
			finderArgs = new Object[] {
					dossierNo, applicantIdNo,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if (!Objects.equals(dossierNo, dossier.getDossierNo()) ||
							!Objects.equals(applicantIdNo,
								dossier.getApplicantIdNo())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_DN_AN_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_DN_AN_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_DN_AN_DOSSIERNO_2);
			}

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_DN_AN_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_DN_AN_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_DN_AN_APPLICANTIDNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierNo) {
					qPos.add(dossierNo);
				}

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByDN_AN_First(String dossierNo, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByDN_AN_First(dossierNo, applicantIdNo,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierNo=");
		msg.append(dossierNo);

		msg.append(", applicantIdNo=");
		msg.append(applicantIdNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByDN_AN_First(String dossierNo, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByDN_AN(dossierNo, applicantIdNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByDN_AN_Last(String dossierNo, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByDN_AN_Last(dossierNo, applicantIdNo,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierNo=");
		msg.append(dossierNo);

		msg.append(", applicantIdNo=");
		msg.append(applicantIdNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByDN_AN_Last(String dossierNo, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByDN_AN(dossierNo, applicantIdNo);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByDN_AN(dossierNo, applicantIdNo, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param dossierNo the dossier no
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByDN_AN_PrevAndNext(long dossierId, String dossierNo,
		String applicantIdNo, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByDN_AN_PrevAndNext(session, dossier, dossierNo,
					applicantIdNo, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByDN_AN_PrevAndNext(session, dossier, dossierNo,
					applicantIdNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByDN_AN_PrevAndNext(Session session, Dossier dossier,
		String dossierNo, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		boolean bindDossierNo = false;

		if (dossierNo == null) {
			query.append(_FINDER_COLUMN_DN_AN_DOSSIERNO_1);
		}
		else if (dossierNo.equals("")) {
			query.append(_FINDER_COLUMN_DN_AN_DOSSIERNO_3);
		}
		else {
			bindDossierNo = true;

			query.append(_FINDER_COLUMN_DN_AN_DOSSIERNO_2);
		}

		boolean bindApplicantIdNo = false;

		if (applicantIdNo == null) {
			query.append(_FINDER_COLUMN_DN_AN_APPLICANTIDNO_1);
		}
		else if (applicantIdNo.equals("")) {
			query.append(_FINDER_COLUMN_DN_AN_APPLICANTIDNO_3);
		}
		else {
			bindApplicantIdNo = true;

			query.append(_FINDER_COLUMN_DN_AN_APPLICANTIDNO_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindDossierNo) {
			qPos.add(dossierNo);
		}

		if (bindApplicantIdNo) {
			qPos.add(applicantIdNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where dossierNo = &#63; and applicantIdNo = &#63; from the database.
	 *
	 * @param dossierNo the dossier no
	 * @param applicantIdNo the applicant ID no
	 */
	@Override
	public void removeByDN_AN(String dossierNo, String applicantIdNo) {
		for (Dossier dossier : findByDN_AN(dossierNo, applicantIdNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @param applicantIdNo the applicant ID no
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByDN_AN(String dossierNo, String applicantIdNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DN_AN;

		Object[] finderArgs = new Object[] { dossierNo, applicantIdNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_DN_AN_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_DN_AN_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_DN_AN_DOSSIERNO_2);
			}

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_DN_AN_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_DN_AN_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_DN_AN_APPLICANTIDNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierNo) {
					qPos.add(dossierNo);
				}

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
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

	private static final String _FINDER_COLUMN_DN_AN_DOSSIERNO_1 = "dossier.dossierNo IS NULL AND ";
	private static final String _FINDER_COLUMN_DN_AN_DOSSIERNO_2 = "dossier.dossierNo = ? AND ";
	private static final String _FINDER_COLUMN_DN_AN_DOSSIERNO_3 = "(dossier.dossierNo IS NULL OR dossier.dossierNo = '') AND ";
	private static final String _FINDER_COLUMN_DN_AN_APPLICANTIDNO_1 = "dossier.applicantIdNo IS NULL";
	private static final String _FINDER_COLUMN_DN_AN_APPLICANTIDNO_2 = "dossier.applicantIdNo = ?";
	private static final String _FINDER_COLUMN_DN_AN_APPLICANTIDNO_3 = "(dossier.applicantIdNo IS NULL OR dossier.applicantIdNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_VIAPOSTAL =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByVIAPOSTAL",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIAPOSTAL =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByVIAPOSTAL",
			new String[] { Integer.class.getName() },
			DossierModelImpl.VIAPOSTAL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_VIAPOSTAL = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByVIAPOSTAL",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the dossiers where viaPostal = &#63;.
	 *
	 * @param viaPostal the via postal
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByVIAPOSTAL(int viaPostal) {
		return findByVIAPOSTAL(viaPostal, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dossiers where viaPostal = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param viaPostal the via postal
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByVIAPOSTAL(int viaPostal, int start, int end) {
		return findByVIAPOSTAL(viaPostal, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where viaPostal = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param viaPostal the via postal
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByVIAPOSTAL(int viaPostal, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByVIAPOSTAL(viaPostal, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where viaPostal = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param viaPostal the via postal
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByVIAPOSTAL(int viaPostal, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIAPOSTAL;
			finderArgs = new Object[] { viaPostal };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_VIAPOSTAL;
			finderArgs = new Object[] { viaPostal, start, end, orderByComparator };
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((viaPostal != dossier.getViaPostal())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_VIAPOSTAL_VIAPOSTAL_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(viaPostal);

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where viaPostal = &#63;.
	 *
	 * @param viaPostal the via postal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByVIAPOSTAL_First(int viaPostal,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByVIAPOSTAL_First(viaPostal, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("viaPostal=");
		msg.append(viaPostal);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where viaPostal = &#63;.
	 *
	 * @param viaPostal the via postal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByVIAPOSTAL_First(int viaPostal,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByVIAPOSTAL(viaPostal, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where viaPostal = &#63;.
	 *
	 * @param viaPostal the via postal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByVIAPOSTAL_Last(int viaPostal,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByVIAPOSTAL_Last(viaPostal, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("viaPostal=");
		msg.append(viaPostal);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where viaPostal = &#63;.
	 *
	 * @param viaPostal the via postal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByVIAPOSTAL_Last(int viaPostal,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByVIAPOSTAL(viaPostal);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByVIAPOSTAL(viaPostal, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where viaPostal = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param viaPostal the via postal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByVIAPOSTAL_PrevAndNext(long dossierId, int viaPostal,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByVIAPOSTAL_PrevAndNext(session, dossier, viaPostal,
					orderByComparator, true);

			array[1] = dossier;

			array[2] = getByVIAPOSTAL_PrevAndNext(session, dossier, viaPostal,
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

	protected Dossier getByVIAPOSTAL_PrevAndNext(Session session,
		Dossier dossier, int viaPostal,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_VIAPOSTAL_VIAPOSTAL_2);

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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(viaPostal);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where viaPostal = &#63; from the database.
	 *
	 * @param viaPostal the via postal
	 */
	@Override
	public void removeByVIAPOSTAL(int viaPostal) {
		for (Dossier dossier : findByVIAPOSTAL(viaPostal, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where viaPostal = &#63;.
	 *
	 * @param viaPostal the via postal
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByVIAPOSTAL(int viaPostal) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_VIAPOSTAL;

		Object[] finderArgs = new Object[] { viaPostal };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_VIAPOSTAL_VIAPOSTAL_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(viaPostal);

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

	private static final String _FINDER_COLUMN_VIAPOSTAL_VIAPOSTAL_2 = "dossier.viaPostal = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DAI_O =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_G_GAC_SC_DTNO_DAI_O",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), String.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DAI_O =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_G_GAC_SC_DTNO_DAI_O",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), String.class.getName(),
				Long.class.getName(), Integer.class.getName()
			},
			DossierModelImpl.USERID_COLUMN_BITMASK |
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			DossierModelImpl.SERVICECODE_COLUMN_BITMASK |
			DossierModelImpl.DOSSIERACTIONID_COLUMN_BITMASK |
			DossierModelImpl.ORIGINALITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_G_GAC_SC_DTNO_DAI_O = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByU_G_GAC_SC_DTNO_DAI_O",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), String.class.getName(),
				Long.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierActionId the dossier action ID
	 * @param originality the originality
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByU_G_GAC_SC_DTNO_DAI_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality) {
		return findByU_G_GAC_SC_DTNO_DAI_O(userId, groupId, govAgencyCode,
			serviceCode, dossierActionId, originality, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierActionId the dossier action ID
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByU_G_GAC_SC_DTNO_DAI_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality, int start, int end) {
		return findByU_G_GAC_SC_DTNO_DAI_O(userId, groupId, govAgencyCode,
			serviceCode, dossierActionId, originality, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierActionId the dossier action ID
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByU_G_GAC_SC_DTNO_DAI_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByU_G_GAC_SC_DTNO_DAI_O(userId, groupId, govAgencyCode,
			serviceCode, dossierActionId, originality, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierActionId the dossier action ID
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByU_G_GAC_SC_DTNO_DAI_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DAI_O;
			finderArgs = new Object[] {
					userId, groupId, govAgencyCode, serviceCode, dossierActionId,
					originality
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DAI_O;
			finderArgs = new Object[] {
					userId, groupId, govAgencyCode, serviceCode, dossierActionId,
					originality,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((userId != dossier.getUserId()) ||
							(groupId != dossier.getGroupId()) ||
							!Objects.equals(govAgencyCode,
								dossier.getGovAgencyCode()) ||
							!Objects.equals(serviceCode,
								dossier.getServiceCode()) ||
							(dossierActionId != dossier.getDossierActionId()) ||
							(originality != dossier.getOriginality())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(8 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(8);
			}

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_USERID_2);

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GOVAGENCYCODE_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_SERVICECODE_2);
			}

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_DOSSIERACTIONID_2);

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_ORIGINALITY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				qPos.add(dossierActionId);

				qPos.add(originality);

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierActionId the dossier action ID
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByU_G_GAC_SC_DTNO_DAI_O_First(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByU_G_GAC_SC_DTNO_DAI_O_First(userId, groupId,
				govAgencyCode, serviceCode, dossierActionId, originality,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append(", dossierActionId=");
		msg.append(dossierActionId);

		msg.append(", originality=");
		msg.append(originality);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierActionId the dossier action ID
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByU_G_GAC_SC_DTNO_DAI_O_First(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByU_G_GAC_SC_DTNO_DAI_O(userId, groupId,
				govAgencyCode, serviceCode, dossierActionId, originality, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierActionId the dossier action ID
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByU_G_GAC_SC_DTNO_DAI_O_Last(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByU_G_GAC_SC_DTNO_DAI_O_Last(userId, groupId,
				govAgencyCode, serviceCode, dossierActionId, originality,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append(", dossierActionId=");
		msg.append(dossierActionId);

		msg.append(", originality=");
		msg.append(originality);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierActionId the dossier action ID
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByU_G_GAC_SC_DTNO_DAI_O_Last(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality, OrderByComparator<Dossier> orderByComparator) {
		int count = countByU_G_GAC_SC_DTNO_DAI_O(userId, groupId,
				govAgencyCode, serviceCode, dossierActionId, originality);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByU_G_GAC_SC_DTNO_DAI_O(userId, groupId,
				govAgencyCode, serviceCode, dossierActionId, originality,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierActionId the dossier action ID
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByU_G_GAC_SC_DTNO_DAI_O_PrevAndNext(long dossierId,
		long userId, long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByU_G_GAC_SC_DTNO_DAI_O_PrevAndNext(session, dossier,
					userId, groupId, govAgencyCode, serviceCode,
					dossierActionId, originality, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByU_G_GAC_SC_DTNO_DAI_O_PrevAndNext(session, dossier,
					userId, groupId, govAgencyCode, serviceCode,
					dossierActionId, originality, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByU_G_GAC_SC_DTNO_DAI_O_PrevAndNext(Session session,
		Dossier dossier, long userId, long groupId, String govAgencyCode,
		String serviceCode, long dossierActionId, int originality,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(9 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(8);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_USERID_2);

		query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GROUPID_2);

		boolean bindGovAgencyCode = false;

		if (govAgencyCode == null) {
			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GOVAGENCYCODE_1);
		}
		else if (govAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GOVAGENCYCODE_3);
		}
		else {
			bindGovAgencyCode = true;

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GOVAGENCYCODE_2);
		}

		boolean bindServiceCode = false;

		if (serviceCode == null) {
			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_SERVICECODE_1);
		}
		else if (serviceCode.equals("")) {
			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_SERVICECODE_3);
		}
		else {
			bindServiceCode = true;

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_SERVICECODE_2);
		}

		query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_DOSSIERACTIONID_2);

		query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_ORIGINALITY_2);

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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(groupId);

		if (bindGovAgencyCode) {
			qPos.add(govAgencyCode);
		}

		if (bindServiceCode) {
			qPos.add(serviceCode);
		}

		qPos.add(dossierActionId);

		qPos.add(originality);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierActionId the dossier action ID
	 * @param originality the originality
	 */
	@Override
	public void removeByU_G_GAC_SC_DTNO_DAI_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality) {
		for (Dossier dossier : findByU_G_GAC_SC_DTNO_DAI_O(userId, groupId,
				govAgencyCode, serviceCode, dossierActionId, originality,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierActionId the dossier action ID
	 * @param originality the originality
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByU_G_GAC_SC_DTNO_DAI_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_G_GAC_SC_DTNO_DAI_O;

		Object[] finderArgs = new Object[] {
				userId, groupId, govAgencyCode, serviceCode, dossierActionId,
				originality
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_USERID_2);

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GOVAGENCYCODE_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_SERVICECODE_2);
			}

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_DOSSIERACTIONID_2);

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_ORIGINALITY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				qPos.add(dossierActionId);

				qPos.add(originality);

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

	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_USERID_2 = "dossier.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GOVAGENCYCODE_1 =
		"dossier.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GOVAGENCYCODE_2 =
		"dossier.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_GOVAGENCYCODE_3 =
		"(dossier.govAgencyCode IS NULL OR dossier.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_SERVICECODE_1 =
		"dossier.serviceCode IS NULL AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_SERVICECODE_2 =
		"dossier.serviceCode = ? AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_SERVICECODE_3 =
		"(dossier.serviceCode IS NULL OR dossier.serviceCode = '') AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_DOSSIERACTIONID_2 =
		"dossier.dossierActionId = ? AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DAI_O_ORIGINALITY_2 =
		"dossier.originality = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_DN = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_DN",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.DOSSIERNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_DN = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_DN",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the dossier where groupId = &#63; and dossierNo = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @return the matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_DN(long groupId, String dossierNo)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_DN(groupId, dossierNo);

		if (dossier == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", dossierNo=");
			msg.append(dossierNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierException(msg.toString());
		}

		return dossier;
	}

	/**
	 * Returns the dossier where groupId = &#63; and dossierNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_DN(long groupId, String dossierNo) {
		return fetchByG_DN(groupId, dossierNo, true);
	}

	/**
	 * Returns the dossier where groupId = &#63; and dossierNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_DN(long groupId, String dossierNo,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, dossierNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_DN,
					finderArgs, this);
		}

		if (result instanceof Dossier) {
			Dossier dossier = (Dossier)result;

			if ((groupId != dossier.getGroupId()) ||
					!Objects.equals(dossierNo, dossier.getDossierNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_DN_GROUPID_2);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_G_DN_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_G_DN_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_G_DN_DOSSIERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierNo) {
					qPos.add(dossierNo);
				}

				List<Dossier> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_DN,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierPersistenceImpl.fetchByG_DN(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Dossier dossier = list.get(0);

					result = dossier;

					cacheResult(dossier);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DN, finderArgs);

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
			return (Dossier)result;
		}
	}

	/**
	 * Removes the dossier where groupId = &#63; and dossierNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @return the dossier that was removed
	 */
	@Override
	public Dossier removeByG_DN(long groupId, String dossierNo)
		throws NoSuchDossierException {
		Dossier dossier = findByG_DN(groupId, dossierNo);

		return remove(dossier);
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and dossierNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_DN(long groupId, String dossierNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_DN;

		Object[] finderArgs = new Object[] { groupId, dossierNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_DN_GROUPID_2);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_G_DN_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_G_DN_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_G_DN_DOSSIERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierNo) {
					qPos.add(dossierNo);
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

	private static final String _FINDER_COLUMN_G_DN_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_DN_DOSSIERNO_1 = "dossier.dossierNo IS NULL";
	private static final String _FINDER_COLUMN_G_DN_DOSSIERNO_2 = "dossier.dossierNo = ?";
	private static final String _FINDER_COLUMN_G_DN_DOSSIERNO_3 = "(dossier.dossierNo IS NULL OR dossier.dossierNo = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_DO_NO = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDO_NO",
			new String[] { String.class.getName() },
			DossierModelImpl.DOSSIERNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DO_NO = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDO_NO",
			new String[] { String.class.getName() });

	/**
	 * Returns the dossier where dossierNo = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	 *
	 * @param dossierNo the dossier no
	 * @return the matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByDO_NO(String dossierNo) throws NoSuchDossierException {
		Dossier dossier = fetchByDO_NO(dossierNo);

		if (dossier == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossierNo=");
			msg.append(dossierNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierException(msg.toString());
		}

		return dossier;
	}

	/**
	 * Returns the dossier where dossierNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossierNo the dossier no
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByDO_NO(String dossierNo) {
		return fetchByDO_NO(dossierNo, true);
	}

	/**
	 * Returns the dossier where dossierNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossierNo the dossier no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByDO_NO(String dossierNo, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossierNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DO_NO,
					finderArgs, this);
		}

		if (result instanceof Dossier) {
			Dossier dossier = (Dossier)result;

			if (!Objects.equals(dossierNo, dossier.getDossierNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_DO_NO_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_DO_NO_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_DO_NO_DOSSIERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierNo) {
					qPos.add(dossierNo);
				}

				List<Dossier> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DO_NO,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierPersistenceImpl.fetchByDO_NO(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Dossier dossier = list.get(0);

					result = dossier;

					cacheResult(dossier);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DO_NO, finderArgs);

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
			return (Dossier)result;
		}
	}

	/**
	 * Removes the dossier where dossierNo = &#63; from the database.
	 *
	 * @param dossierNo the dossier no
	 * @return the dossier that was removed
	 */
	@Override
	public Dossier removeByDO_NO(String dossierNo)
		throws NoSuchDossierException {
		Dossier dossier = findByDO_NO(dossierNo);

		return remove(dossier);
	}

	/**
	 * Returns the number of dossiers where dossierNo = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByDO_NO(String dossierNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DO_NO;

		Object[] finderArgs = new Object[] { dossierNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_DO_NO_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_DO_NO_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_DO_NO_DOSSIERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierNo) {
					qPos.add(dossierNo);
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

	private static final String _FINDER_COLUMN_DO_NO_DOSSIERNO_1 = "dossier.dossierNo IS NULL";
	private static final String _FINDER_COLUMN_DO_NO_DOSSIERNO_2 = "dossier.dossierNo = ?";
	private static final String _FINDER_COLUMN_DO_NO_DOSSIERNO_3 = "(dossier.dossierNo IS NULL OR dossier.dossierNo = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_DO_NO_GROUP = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDO_NO_GROUP",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierModelImpl.DOSSIERNO_COLUMN_BITMASK |
			DossierModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DO_NO_GROUP = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDO_NO_GROUP",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier where dossierNo = &#63; and groupId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	 *
	 * @param dossierNo the dossier no
	 * @param groupId the group ID
	 * @return the matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByDO_NO_GROUP(String dossierNo, long groupId)
		throws NoSuchDossierException {
		Dossier dossier = fetchByDO_NO_GROUP(dossierNo, groupId);

		if (dossier == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossierNo=");
			msg.append(dossierNo);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierException(msg.toString());
		}

		return dossier;
	}

	/**
	 * Returns the dossier where dossierNo = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossierNo the dossier no
	 * @param groupId the group ID
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByDO_NO_GROUP(String dossierNo, long groupId) {
		return fetchByDO_NO_GROUP(dossierNo, groupId, true);
	}

	/**
	 * Returns the dossier where dossierNo = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossierNo the dossier no
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByDO_NO_GROUP(String dossierNo, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossierNo, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DO_NO_GROUP,
					finderArgs, this);
		}

		if (result instanceof Dossier) {
			Dossier dossier = (Dossier)result;

			if (!Objects.equals(dossierNo, dossier.getDossierNo()) ||
					(groupId != dossier.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_DO_NO_GROUP_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_DO_NO_GROUP_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_DO_NO_GROUP_DOSSIERNO_2);
			}

			query.append(_FINDER_COLUMN_DO_NO_GROUP_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierNo) {
					qPos.add(dossierNo);
				}

				qPos.add(groupId);

				List<Dossier> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DO_NO_GROUP,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierPersistenceImpl.fetchByDO_NO_GROUP(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Dossier dossier = list.get(0);

					result = dossier;

					cacheResult(dossier);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DO_NO_GROUP,
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
			return (Dossier)result;
		}
	}

	/**
	 * Removes the dossier where dossierNo = &#63; and groupId = &#63; from the database.
	 *
	 * @param dossierNo the dossier no
	 * @param groupId the group ID
	 * @return the dossier that was removed
	 */
	@Override
	public Dossier removeByDO_NO_GROUP(String dossierNo, long groupId)
		throws NoSuchDossierException {
		Dossier dossier = findByDO_NO_GROUP(dossierNo, groupId);

		return remove(dossier);
	}

	/**
	 * Returns the number of dossiers where dossierNo = &#63; and groupId = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @param groupId the group ID
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByDO_NO_GROUP(String dossierNo, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DO_NO_GROUP;

		Object[] finderArgs = new Object[] { dossierNo, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_DO_NO_GROUP_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_DO_NO_GROUP_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_DO_NO_GROUP_DOSSIERNO_2);
			}

			query.append(_FINDER_COLUMN_DO_NO_GROUP_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierNo) {
					qPos.add(dossierNo);
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

	private static final String _FINDER_COLUMN_DO_NO_GROUP_DOSSIERNO_1 = "dossier.dossierNo IS NULL AND ";
	private static final String _FINDER_COLUMN_DO_NO_GROUP_DOSSIERNO_2 = "dossier.dossierNo = ? AND ";
	private static final String _FINDER_COLUMN_DO_NO_GROUP_DOSSIERNO_3 = "(dossier.dossierNo IS NULL OR dossier.dossierNo = '') AND ";
	private static final String _FINDER_COLUMN_DO_NO_GROUP_GROUPID_2 = "dossier.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DO_POST_SEND_GROUP = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDO_POST_SEND_GROUP",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierModelImpl.POSTALCODESEND_COLUMN_BITMASK |
			DossierModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DO_POST_SEND_GROUP = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByDO_POST_SEND_GROUP",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier where postalCodeSend = &#63; and groupId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	 *
	 * @param postalCodeSend the postal code send
	 * @param groupId the group ID
	 * @return the matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByDO_POST_SEND_GROUP(String postalCodeSend, long groupId)
		throws NoSuchDossierException {
		Dossier dossier = fetchByDO_POST_SEND_GROUP(postalCodeSend, groupId);

		if (dossier == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("postalCodeSend=");
			msg.append(postalCodeSend);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierException(msg.toString());
		}

		return dossier;
	}

	/**
	 * Returns the dossier where postalCodeSend = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param postalCodeSend the postal code send
	 * @param groupId the group ID
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByDO_POST_SEND_GROUP(String postalCodeSend, long groupId) {
		return fetchByDO_POST_SEND_GROUP(postalCodeSend, groupId, true);
	}

	/**
	 * Returns the dossier where postalCodeSend = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param postalCodeSend the postal code send
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByDO_POST_SEND_GROUP(String postalCodeSend,
		long groupId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { postalCodeSend, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DO_POST_SEND_GROUP,
					finderArgs, this);
		}

		if (result instanceof Dossier) {
			Dossier dossier = (Dossier)result;

			if (!Objects.equals(postalCodeSend, dossier.getPostalCodeSend()) ||
					(groupId != dossier.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			boolean bindPostalCodeSend = false;

			if (postalCodeSend == null) {
				query.append(_FINDER_COLUMN_DO_POST_SEND_GROUP_POSTALCODESEND_1);
			}
			else if (postalCodeSend.equals("")) {
				query.append(_FINDER_COLUMN_DO_POST_SEND_GROUP_POSTALCODESEND_3);
			}
			else {
				bindPostalCodeSend = true;

				query.append(_FINDER_COLUMN_DO_POST_SEND_GROUP_POSTALCODESEND_2);
			}

			query.append(_FINDER_COLUMN_DO_POST_SEND_GROUP_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPostalCodeSend) {
					qPos.add(postalCodeSend);
				}

				qPos.add(groupId);

				List<Dossier> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DO_POST_SEND_GROUP,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierPersistenceImpl.fetchByDO_POST_SEND_GROUP(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Dossier dossier = list.get(0);

					result = dossier;

					cacheResult(dossier);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DO_POST_SEND_GROUP,
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
			return (Dossier)result;
		}
	}

	/**
	 * Removes the dossier where postalCodeSend = &#63; and groupId = &#63; from the database.
	 *
	 * @param postalCodeSend the postal code send
	 * @param groupId the group ID
	 * @return the dossier that was removed
	 */
	@Override
	public Dossier removeByDO_POST_SEND_GROUP(String postalCodeSend,
		long groupId) throws NoSuchDossierException {
		Dossier dossier = findByDO_POST_SEND_GROUP(postalCodeSend, groupId);

		return remove(dossier);
	}

	/**
	 * Returns the number of dossiers where postalCodeSend = &#63; and groupId = &#63;.
	 *
	 * @param postalCodeSend the postal code send
	 * @param groupId the group ID
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByDO_POST_SEND_GROUP(String postalCodeSend, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DO_POST_SEND_GROUP;

		Object[] finderArgs = new Object[] { postalCodeSend, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			boolean bindPostalCodeSend = false;

			if (postalCodeSend == null) {
				query.append(_FINDER_COLUMN_DO_POST_SEND_GROUP_POSTALCODESEND_1);
			}
			else if (postalCodeSend.equals("")) {
				query.append(_FINDER_COLUMN_DO_POST_SEND_GROUP_POSTALCODESEND_3);
			}
			else {
				bindPostalCodeSend = true;

				query.append(_FINDER_COLUMN_DO_POST_SEND_GROUP_POSTALCODESEND_2);
			}

			query.append(_FINDER_COLUMN_DO_POST_SEND_GROUP_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPostalCodeSend) {
					qPos.add(postalCodeSend);
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

	private static final String _FINDER_COLUMN_DO_POST_SEND_GROUP_POSTALCODESEND_1 =
		"dossier.postalCodeSend IS NULL AND ";
	private static final String _FINDER_COLUMN_DO_POST_SEND_GROUP_POSTALCODESEND_2 =
		"dossier.postalCodeSend = ? AND ";
	private static final String _FINDER_COLUMN_DO_POST_SEND_GROUP_POSTALCODESEND_3 =
		"(dossier.postalCodeSend IS NULL OR dossier.postalCodeSend = '') AND ";
	private static final String _FINDER_COLUMN_DO_POST_SEND_GROUP_GROUPID_2 = "dossier.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DO_POST_RECEIVED_GROUP = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDO_POST_RECEIVED_GROUP",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierModelImpl.POSTALCODERECEIVED_COLUMN_BITMASK |
			DossierModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DO_POST_RECEIVED_GROUP = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByDO_POST_RECEIVED_GROUP",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier where postalCodeReceived = &#63; and groupId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	 *
	 * @param postalCodeReceived the postal code received
	 * @param groupId the group ID
	 * @return the matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByDO_POST_RECEIVED_GROUP(String postalCodeReceived,
		long groupId) throws NoSuchDossierException {
		Dossier dossier = fetchByDO_POST_RECEIVED_GROUP(postalCodeReceived,
				groupId);

		if (dossier == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("postalCodeReceived=");
			msg.append(postalCodeReceived);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierException(msg.toString());
		}

		return dossier;
	}

	/**
	 * Returns the dossier where postalCodeReceived = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param postalCodeReceived the postal code received
	 * @param groupId the group ID
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByDO_POST_RECEIVED_GROUP(String postalCodeReceived,
		long groupId) {
		return fetchByDO_POST_RECEIVED_GROUP(postalCodeReceived, groupId, true);
	}

	/**
	 * Returns the dossier where postalCodeReceived = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param postalCodeReceived the postal code received
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByDO_POST_RECEIVED_GROUP(String postalCodeReceived,
		long groupId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { postalCodeReceived, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DO_POST_RECEIVED_GROUP,
					finderArgs, this);
		}

		if (result instanceof Dossier) {
			Dossier dossier = (Dossier)result;

			if (!Objects.equals(postalCodeReceived,
						dossier.getPostalCodeReceived()) ||
					(groupId != dossier.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			boolean bindPostalCodeReceived = false;

			if (postalCodeReceived == null) {
				query.append(_FINDER_COLUMN_DO_POST_RECEIVED_GROUP_POSTALCODERECEIVED_1);
			}
			else if (postalCodeReceived.equals("")) {
				query.append(_FINDER_COLUMN_DO_POST_RECEIVED_GROUP_POSTALCODERECEIVED_3);
			}
			else {
				bindPostalCodeReceived = true;

				query.append(_FINDER_COLUMN_DO_POST_RECEIVED_GROUP_POSTALCODERECEIVED_2);
			}

			query.append(_FINDER_COLUMN_DO_POST_RECEIVED_GROUP_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPostalCodeReceived) {
					qPos.add(postalCodeReceived);
				}

				qPos.add(groupId);

				List<Dossier> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DO_POST_RECEIVED_GROUP,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierPersistenceImpl.fetchByDO_POST_RECEIVED_GROUP(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Dossier dossier = list.get(0);

					result = dossier;

					cacheResult(dossier);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DO_POST_RECEIVED_GROUP,
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
			return (Dossier)result;
		}
	}

	/**
	 * Removes the dossier where postalCodeReceived = &#63; and groupId = &#63; from the database.
	 *
	 * @param postalCodeReceived the postal code received
	 * @param groupId the group ID
	 * @return the dossier that was removed
	 */
	@Override
	public Dossier removeByDO_POST_RECEIVED_GROUP(String postalCodeReceived,
		long groupId) throws NoSuchDossierException {
		Dossier dossier = findByDO_POST_RECEIVED_GROUP(postalCodeReceived,
				groupId);

		return remove(dossier);
	}

	/**
	 * Returns the number of dossiers where postalCodeReceived = &#63; and groupId = &#63;.
	 *
	 * @param postalCodeReceived the postal code received
	 * @param groupId the group ID
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByDO_POST_RECEIVED_GROUP(String postalCodeReceived,
		long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DO_POST_RECEIVED_GROUP;

		Object[] finderArgs = new Object[] { postalCodeReceived, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			boolean bindPostalCodeReceived = false;

			if (postalCodeReceived == null) {
				query.append(_FINDER_COLUMN_DO_POST_RECEIVED_GROUP_POSTALCODERECEIVED_1);
			}
			else if (postalCodeReceived.equals("")) {
				query.append(_FINDER_COLUMN_DO_POST_RECEIVED_GROUP_POSTALCODERECEIVED_3);
			}
			else {
				bindPostalCodeReceived = true;

				query.append(_FINDER_COLUMN_DO_POST_RECEIVED_GROUP_POSTALCODERECEIVED_2);
			}

			query.append(_FINDER_COLUMN_DO_POST_RECEIVED_GROUP_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPostalCodeReceived) {
					qPos.add(postalCodeReceived);
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

	private static final String _FINDER_COLUMN_DO_POST_RECEIVED_GROUP_POSTALCODERECEIVED_1 =
		"dossier.postalCodeReceived IS NULL AND ";
	private static final String _FINDER_COLUMN_DO_POST_RECEIVED_GROUP_POSTALCODERECEIVED_2 =
		"dossier.postalCodeReceived = ? AND ";
	private static final String _FINDER_COLUMN_DO_POST_RECEIVED_GROUP_POSTALCODERECEIVED_3 =
		"(dossier.postalCodeReceived IS NULL OR dossier.postalCodeReceived = '') AND ";
	private static final String _FINDER_COLUMN_DO_POST_RECEIVED_GROUP_GROUPID_2 = "dossier.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_AN = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_AN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_AN = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_AN",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.APPLICANTIDNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_AN = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_AN",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossiers where groupId = &#63; and applicantIdNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_AN(long groupId, String applicantIdNo) {
		return findByG_AN(groupId, applicantIdNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and applicantIdNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_AN(long groupId, String applicantIdNo,
		int start, int end) {
		return findByG_AN(groupId, applicantIdNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and applicantIdNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_AN(long groupId, String applicantIdNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByG_AN(groupId, applicantIdNo, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and applicantIdNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_AN(long groupId, String applicantIdNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_AN;
			finderArgs = new Object[] { groupId, applicantIdNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_AN;
			finderArgs = new Object[] {
					groupId, applicantIdNo,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							!Objects.equals(applicantIdNo,
								dossier.getApplicantIdNo())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_AN_GROUPID_2);

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_G_AN_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_G_AN_APPLICANTIDNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_AN_First(long groupId, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_AN_First(groupId, applicantIdNo,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", applicantIdNo=");
		msg.append(applicantIdNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_AN_First(long groupId, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_AN(groupId, applicantIdNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_AN_Last(long groupId, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_AN_Last(groupId, applicantIdNo,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", applicantIdNo=");
		msg.append(applicantIdNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_AN_Last(long groupId, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_AN(groupId, applicantIdNo);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_AN(groupId, applicantIdNo, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_AN_PrevAndNext(long dossierId, long groupId,
		String applicantIdNo, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_AN_PrevAndNext(session, dossier, groupId,
					applicantIdNo, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByG_AN_PrevAndNext(session, dossier, groupId,
					applicantIdNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByG_AN_PrevAndNext(Session session, Dossier dossier,
		long groupId, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_AN_GROUPID_2);

		boolean bindApplicantIdNo = false;

		if (applicantIdNo == null) {
			query.append(_FINDER_COLUMN_G_AN_APPLICANTIDNO_1);
		}
		else if (applicantIdNo.equals("")) {
			query.append(_FINDER_COLUMN_G_AN_APPLICANTIDNO_3);
		}
		else {
			bindApplicantIdNo = true;

			query.append(_FINDER_COLUMN_G_AN_APPLICANTIDNO_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindApplicantIdNo) {
			qPos.add(applicantIdNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and applicantIdNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 */
	@Override
	public void removeByG_AN(long groupId, String applicantIdNo) {
		for (Dossier dossier : findByG_AN(groupId, applicantIdNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and applicantIdNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_AN(long groupId, String applicantIdNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_AN;

		Object[] finderArgs = new Object[] { groupId, applicantIdNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_AN_GROUPID_2);

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_G_AN_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_G_AN_APPLICANTIDNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
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

	private static final String _FINDER_COLUMN_G_AN_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_AN_APPLICANTIDNO_1 = "dossier.applicantIdNo IS NULL";
	private static final String _FINDER_COLUMN_G_AN_APPLICANTIDNO_2 = "dossier.applicantIdNo = ?";
	private static final String _FINDER_COLUMN_G_AN_APPLICANTIDNO_3 = "(dossier.applicantIdNo IS NULL OR dossier.applicantIdNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_AN_DS =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_GID_AN_DS",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_AN_DS =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_GID_AN_DS",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.APPLICANTIDNO_COLUMN_BITMASK |
			DossierModelImpl.DOSSIERSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_AN_DS = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_AN_DS",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param dossierStatus the dossier status
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByF_GID_AN_DS(long groupId, String applicantIdNo,
		String dossierStatus) {
		return findByF_GID_AN_DS(groupId, applicantIdNo, dossierStatus,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByF_GID_AN_DS(long groupId, String applicantIdNo,
		String dossierStatus, int start, int end) {
		return findByF_GID_AN_DS(groupId, applicantIdNo, dossierStatus, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByF_GID_AN_DS(long groupId, String applicantIdNo,
		String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByF_GID_AN_DS(groupId, applicantIdNo, dossierStatus, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByF_GID_AN_DS(long groupId, String applicantIdNo,
		String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_AN_DS;
			finderArgs = new Object[] { groupId, applicantIdNo, dossierStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_AN_DS;
			finderArgs = new Object[] {
					groupId, applicantIdNo, dossierStatus,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							!Objects.equals(applicantIdNo,
								dossier.getApplicantIdNo()) ||
							!Objects.equals(dossierStatus,
								dossier.getDossierStatus())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_F_GID_AN_DS_GROUPID_2);

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_F_GID_AN_DS_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_AN_DS_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_F_GID_AN_DS_APPLICANTIDNO_2);
			}

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_F_GID_AN_DS_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_AN_DS_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_F_GID_AN_DS_DOSSIERSTATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
				}

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByF_GID_AN_DS_First(long groupId, String applicantIdNo,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByF_GID_AN_DS_First(groupId, applicantIdNo,
				dossierStatus, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", applicantIdNo=");
		msg.append(applicantIdNo);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByF_GID_AN_DS_First(long groupId, String applicantIdNo,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByF_GID_AN_DS(groupId, applicantIdNo,
				dossierStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByF_GID_AN_DS_Last(long groupId, String applicantIdNo,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByF_GID_AN_DS_Last(groupId, applicantIdNo,
				dossierStatus, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", applicantIdNo=");
		msg.append(applicantIdNo);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByF_GID_AN_DS_Last(long groupId, String applicantIdNo,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator) {
		int count = countByF_GID_AN_DS(groupId, applicantIdNo, dossierStatus);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByF_GID_AN_DS(groupId, applicantIdNo,
				dossierStatus, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByF_GID_AN_DS_PrevAndNext(long dossierId,
		long groupId, String applicantIdNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByF_GID_AN_DS_PrevAndNext(session, dossier, groupId,
					applicantIdNo, dossierStatus, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByF_GID_AN_DS_PrevAndNext(session, dossier, groupId,
					applicantIdNo, dossierStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByF_GID_AN_DS_PrevAndNext(Session session,
		Dossier dossier, long groupId, String applicantIdNo,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator,
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

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_F_GID_AN_DS_GROUPID_2);

		boolean bindApplicantIdNo = false;

		if (applicantIdNo == null) {
			query.append(_FINDER_COLUMN_F_GID_AN_DS_APPLICANTIDNO_1);
		}
		else if (applicantIdNo.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_AN_DS_APPLICANTIDNO_3);
		}
		else {
			bindApplicantIdNo = true;

			query.append(_FINDER_COLUMN_F_GID_AN_DS_APPLICANTIDNO_2);
		}

		boolean bindDossierStatus = false;

		if (dossierStatus == null) {
			query.append(_FINDER_COLUMN_F_GID_AN_DS_DOSSIERSTATUS_1);
		}
		else if (dossierStatus.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_AN_DS_DOSSIERSTATUS_3);
		}
		else {
			bindDossierStatus = true;

			query.append(_FINDER_COLUMN_F_GID_AN_DS_DOSSIERSTATUS_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindApplicantIdNo) {
			qPos.add(applicantIdNo);
		}

		if (bindDossierStatus) {
			qPos.add(dossierStatus);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param dossierStatus the dossier status
	 */
	@Override
	public void removeByF_GID_AN_DS(long groupId, String applicantIdNo,
		String dossierStatus) {
		for (Dossier dossier : findByF_GID_AN_DS(groupId, applicantIdNo,
				dossierStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param dossierStatus the dossier status
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByF_GID_AN_DS(long groupId, String applicantIdNo,
		String dossierStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_AN_DS;

		Object[] finderArgs = new Object[] { groupId, applicantIdNo, dossierStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_F_GID_AN_DS_GROUPID_2);

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_F_GID_AN_DS_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_AN_DS_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_F_GID_AN_DS_APPLICANTIDNO_2);
			}

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_F_GID_AN_DS_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_AN_DS_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_F_GID_AN_DS_DOSSIERSTATUS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
				}

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
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

	private static final String _FINDER_COLUMN_F_GID_AN_DS_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_AN_DS_APPLICANTIDNO_1 = "dossier.applicantIdNo IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_AN_DS_APPLICANTIDNO_2 = "dossier.applicantIdNo = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_AN_DS_APPLICANTIDNO_3 = "(dossier.applicantIdNo IS NULL OR dossier.applicantIdNo = '') AND ";
	private static final String _FINDER_COLUMN_F_GID_AN_DS_DOSSIERSTATUS_1 = "dossier.dossierStatus IS NULL";
	private static final String _FINDER_COLUMN_F_GID_AN_DS_DOSSIERSTATUS_2 = "dossier.dossierStatus = ?";
	private static final String _FINDER_COLUMN_F_GID_AN_DS_DOSSIERSTATUS_3 = "(dossier.dossierStatus IS NULL OR dossier.dossierStatus = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_ODID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_AN_SC_GAC_DTNO_ODID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), Long.class.getName()
			},
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.APPLICANTIDNO_COLUMN_BITMASK |
			DossierModelImpl.SERVICECODE_COLUMN_BITMASK |
			DossierModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			DossierModelImpl.DOSSIERTEMPLATENO_COLUMN_BITMASK |
			DossierModelImpl.ORIGINDOSSIERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_AN_SC_GAC_DTNO_ODID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_AN_SC_GAC_DTNO_ODID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param dossierTemplateNo the dossier template no
	 * @param originDossierId the origin dossier ID
	 * @return the matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_AN_SC_GAC_DTNO_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_AN_SC_GAC_DTNO_ODID(groupId, applicantIdNo,
				serviceCode, govAgencyCode, dossierTemplateNo, originDossierId);

		if (dossier == null) {
			StringBundler msg = new StringBundler(14);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", applicantIdNo=");
			msg.append(applicantIdNo);

			msg.append(", serviceCode=");
			msg.append(serviceCode);

			msg.append(", govAgencyCode=");
			msg.append(govAgencyCode);

			msg.append(", dossierTemplateNo=");
			msg.append(dossierTemplateNo);

			msg.append(", originDossierId=");
			msg.append(originDossierId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierException(msg.toString());
		}

		return dossier;
	}

	/**
	 * Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param dossierTemplateNo the dossier template no
	 * @param originDossierId the origin dossier ID
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_AN_SC_GAC_DTNO_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId) {
		return fetchByG_AN_SC_GAC_DTNO_ODID(groupId, applicantIdNo,
			serviceCode, govAgencyCode, dossierTemplateNo, originDossierId, true);
	}

	/**
	 * Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param dossierTemplateNo the dossier template no
	 * @param originDossierId the origin dossier ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_AN_SC_GAC_DTNO_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, applicantIdNo, serviceCode, govAgencyCode,
				dossierTemplateNo, originDossierId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_ODID,
					finderArgs, this);
		}

		if (result instanceof Dossier) {
			Dossier dossier = (Dossier)result;

			if ((groupId != dossier.getGroupId()) ||
					!Objects.equals(applicantIdNo, dossier.getApplicantIdNo()) ||
					!Objects.equals(serviceCode, dossier.getServiceCode()) ||
					!Objects.equals(govAgencyCode, dossier.getGovAgencyCode()) ||
					!Objects.equals(dossierTemplateNo,
						dossier.getDossierTemplateNo()) ||
					(originDossierId != dossier.getOriginDossierId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(8);

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_GROUPID_2);

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_APPLICANTIDNO_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_SERVICECODE_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_GOVAGENCYCODE_2);
			}

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_DOSSIERTEMPLATENO_2);
			}

			query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_ORIGINDOSSIERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				qPos.add(originDossierId);

				List<Dossier> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_ODID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierPersistenceImpl.fetchByG_AN_SC_GAC_DTNO_ODID(long, String, String, String, String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Dossier dossier = list.get(0);

					result = dossier;

					cacheResult(dossier);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_ODID,
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
			return (Dossier)result;
		}
	}

	/**
	 * Removes the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param dossierTemplateNo the dossier template no
	 * @param originDossierId the origin dossier ID
	 * @return the dossier that was removed
	 */
	@Override
	public Dossier removeByG_AN_SC_GAC_DTNO_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId)
		throws NoSuchDossierException {
		Dossier dossier = findByG_AN_SC_GAC_DTNO_ODID(groupId, applicantIdNo,
				serviceCode, govAgencyCode, dossierTemplateNo, originDossierId);

		return remove(dossier);
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param dossierTemplateNo the dossier template no
	 * @param originDossierId the origin dossier ID
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_AN_SC_GAC_DTNO_ODID(long groupId, String applicantIdNo,
		String serviceCode, String govAgencyCode, String dossierTemplateNo,
		long originDossierId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_AN_SC_GAC_DTNO_ODID;

		Object[] finderArgs = new Object[] {
				groupId, applicantIdNo, serviceCode, govAgencyCode,
				dossierTemplateNo, originDossierId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_GROUPID_2);

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_APPLICANTIDNO_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_SERVICECODE_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_GOVAGENCYCODE_2);
			}

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_DOSSIERTEMPLATENO_2);
			}

			query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_ORIGINDOSSIERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				qPos.add(originDossierId);

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

	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_APPLICANTIDNO_1 =
		"dossier.applicantIdNo IS NULL AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_APPLICANTIDNO_2 =
		"dossier.applicantIdNo = ? AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_APPLICANTIDNO_3 =
		"(dossier.applicantIdNo IS NULL OR dossier.applicantIdNo = '') AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_SERVICECODE_1 =
		"dossier.serviceCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_SERVICECODE_2 =
		"dossier.serviceCode = ? AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_SERVICECODE_3 =
		"(dossier.serviceCode IS NULL OR dossier.serviceCode = '') AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_GOVAGENCYCODE_1 =
		"dossier.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_GOVAGENCYCODE_2 =
		"dossier.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_GOVAGENCYCODE_3 =
		"(dossier.govAgencyCode IS NULL OR dossier.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_DOSSIERTEMPLATENO_1 =
		"dossier.dossierTemplateNo IS NULL AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_DOSSIERTEMPLATENO_2 =
		"dossier.dossierTemplateNo = ? AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_DOSSIERTEMPLATENO_3 =
		"(dossier.dossierTemplateNo IS NULL OR dossier.dossierTemplateNo = '') AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_ODID_ORIGINDOSSIERID_2 =
		"dossier.originDossierId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_SN_ODID =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_AN_SC_GAC_DTNO_SN_ODID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.APPLICANTIDNO_COLUMN_BITMASK |
			DossierModelImpl.SERVICECODE_COLUMN_BITMASK |
			DossierModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			DossierModelImpl.DOSSIERTEMPLATENO_COLUMN_BITMASK |
			DossierModelImpl.ORIGINDOSSIERID_COLUMN_BITMASK |
			DossierModelImpl.SERVERNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_AN_SC_GAC_DTNO_SN_ODID =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_AN_SC_GAC_DTNO_SN_ODID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; and serverNo = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param dossierTemplateNo the dossier template no
	 * @param originDossierId the origin dossier ID
	 * @param serverNo the server no
	 * @return the matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_AN_SC_GAC_DTNO_SN_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId, String serverNo)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_AN_SC_GAC_DTNO_SN_ODID(groupId,
				applicantIdNo, serviceCode, govAgencyCode, dossierTemplateNo,
				originDossierId, serverNo);

		if (dossier == null) {
			StringBundler msg = new StringBundler(16);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", applicantIdNo=");
			msg.append(applicantIdNo);

			msg.append(", serviceCode=");
			msg.append(serviceCode);

			msg.append(", govAgencyCode=");
			msg.append(govAgencyCode);

			msg.append(", dossierTemplateNo=");
			msg.append(dossierTemplateNo);

			msg.append(", originDossierId=");
			msg.append(originDossierId);

			msg.append(", serverNo=");
			msg.append(serverNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierException(msg.toString());
		}

		return dossier;
	}

	/**
	 * Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param dossierTemplateNo the dossier template no
	 * @param originDossierId the origin dossier ID
	 * @param serverNo the server no
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_AN_SC_GAC_DTNO_SN_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId, String serverNo) {
		return fetchByG_AN_SC_GAC_DTNO_SN_ODID(groupId, applicantIdNo,
			serviceCode, govAgencyCode, dossierTemplateNo, originDossierId,
			serverNo, true);
	}

	/**
	 * Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param dossierTemplateNo the dossier template no
	 * @param originDossierId the origin dossier ID
	 * @param serverNo the server no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_AN_SC_GAC_DTNO_SN_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId, String serverNo,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, applicantIdNo, serviceCode, govAgencyCode,
				dossierTemplateNo, originDossierId, serverNo
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_SN_ODID,
					finderArgs, this);
		}

		if (result instanceof Dossier) {
			Dossier dossier = (Dossier)result;

			if ((groupId != dossier.getGroupId()) ||
					!Objects.equals(applicantIdNo, dossier.getApplicantIdNo()) ||
					!Objects.equals(serviceCode, dossier.getServiceCode()) ||
					!Objects.equals(govAgencyCode, dossier.getGovAgencyCode()) ||
					!Objects.equals(dossierTemplateNo,
						dossier.getDossierTemplateNo()) ||
					(originDossierId != dossier.getOriginDossierId()) ||
					!Objects.equals(serverNo, dossier.getServerNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(9);

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_GROUPID_2);

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_APPLICANTIDNO_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVICECODE_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_GOVAGENCYCODE_2);
			}

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_DOSSIERTEMPLATENO_2);
			}

			query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_ORIGINDOSSIERID_2);

			boolean bindServerNo = false;

			if (serverNo == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVERNO_1);
			}
			else if (serverNo.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVERNO_3);
			}
			else {
				bindServerNo = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				qPos.add(originDossierId);

				if (bindServerNo) {
					qPos.add(serverNo);
				}

				List<Dossier> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_SN_ODID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierPersistenceImpl.fetchByG_AN_SC_GAC_DTNO_SN_ODID(long, String, String, String, String, long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Dossier dossier = list.get(0);

					result = dossier;

					cacheResult(dossier);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_SN_ODID,
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
			return (Dossier)result;
		}
	}

	/**
	 * Removes the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; and serverNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param dossierTemplateNo the dossier template no
	 * @param originDossierId the origin dossier ID
	 * @param serverNo the server no
	 * @return the dossier that was removed
	 */
	@Override
	public Dossier removeByG_AN_SC_GAC_DTNO_SN_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId, String serverNo)
		throws NoSuchDossierException {
		Dossier dossier = findByG_AN_SC_GAC_DTNO_SN_ODID(groupId,
				applicantIdNo, serviceCode, govAgencyCode, dossierTemplateNo,
				originDossierId, serverNo);

		return remove(dossier);
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param serviceCode the service code
	 * @param govAgencyCode the gov agency code
	 * @param dossierTemplateNo the dossier template no
	 * @param originDossierId the origin dossier ID
	 * @param serverNo the server no
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_AN_SC_GAC_DTNO_SN_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId, String serverNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_AN_SC_GAC_DTNO_SN_ODID;

		Object[] finderArgs = new Object[] {
				groupId, applicantIdNo, serviceCode, govAgencyCode,
				dossierTemplateNo, originDossierId, serverNo
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(8);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_GROUPID_2);

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_APPLICANTIDNO_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVICECODE_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_GOVAGENCYCODE_2);
			}

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_DOSSIERTEMPLATENO_2);
			}

			query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_ORIGINDOSSIERID_2);

			boolean bindServerNo = false;

			if (serverNo == null) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVERNO_1);
			}
			else if (serverNo.equals("")) {
				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVERNO_3);
			}
			else {
				bindServerNo = true;

				query.append(_FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				qPos.add(originDossierId);

				if (bindServerNo) {
					qPos.add(serverNo);
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

	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_GROUPID_2 =
		"dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_APPLICANTIDNO_1 =
		"dossier.applicantIdNo IS NULL AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_APPLICANTIDNO_2 =
		"dossier.applicantIdNo = ? AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_APPLICANTIDNO_3 =
		"(dossier.applicantIdNo IS NULL OR dossier.applicantIdNo = '') AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVICECODE_1 =
		"dossier.serviceCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVICECODE_2 =
		"dossier.serviceCode = ? AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVICECODE_3 =
		"(dossier.serviceCode IS NULL OR dossier.serviceCode = '') AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_GOVAGENCYCODE_1 =
		"dossier.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_GOVAGENCYCODE_2 =
		"dossier.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_GOVAGENCYCODE_3 =
		"(dossier.govAgencyCode IS NULL OR dossier.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_DOSSIERTEMPLATENO_1 =
		"dossier.dossierTemplateNo IS NULL AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_DOSSIERTEMPLATENO_2 =
		"dossier.dossierTemplateNo = ? AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_DOSSIERTEMPLATENO_3 =
		"(dossier.dossierTemplateNo IS NULL OR dossier.dossierTemplateNo = '') AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_ORIGINDOSSIERID_2 =
		"dossier.originDossierId = ? AND ";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVERNO_1 =
		"dossier.serverNo IS NULL";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVERNO_2 =
		"dossier.serverNo = ?";
	private static final String _FINDER_COLUMN_G_AN_SC_GAC_DTNO_SN_ODID_SERVERNO_3 =
		"(dossier.serverNo IS NULL OR dossier.serverNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_O = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByO",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByO",
			new String[] { Integer.class.getName() },
			DossierModelImpl.ORIGINALITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_O = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByO",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the dossiers where originality = &#63;.
	 *
	 * @param originality the originality
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByO(int originality) {
		return findByO(originality, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByO(int originality, int start, int end) {
		return findByO(originality, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByO(int originality, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByO(originality, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByO(int originality, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O;
			finderArgs = new Object[] { originality };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_O;
			finderArgs = new Object[] { originality, start, end, orderByComparator };
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((originality != dossier.getOriginality())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_O_ORIGINALITY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(originality);

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where originality = &#63;.
	 *
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByO_First(int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByO_First(originality, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("originality=");
		msg.append(originality);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where originality = &#63;.
	 *
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByO_First(int originality,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByO(originality, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where originality = &#63;.
	 *
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByO_Last(int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByO_Last(originality, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("originality=");
		msg.append(originality);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where originality = &#63;.
	 *
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByO_Last(int originality,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByO(originality);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByO(originality, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where originality = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByO_PrevAndNext(long dossierId, int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByO_PrevAndNext(session, dossier, originality,
					orderByComparator, true);

			array[1] = dossier;

			array[2] = getByO_PrevAndNext(session, dossier, originality,
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

	protected Dossier getByO_PrevAndNext(Session session, Dossier dossier,
		int originality, OrderByComparator<Dossier> orderByComparator,
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

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_O_ORIGINALITY_2);

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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(originality);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where originality = &#63; from the database.
	 *
	 * @param originality the originality
	 */
	@Override
	public void removeByO(int originality) {
		for (Dossier dossier : findByO(originality, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where originality = &#63;.
	 *
	 * @param originality the originality
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByO(int originality) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_O;

		Object[] finderArgs = new Object[] { originality };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_O_ORIGINALITY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(originality);

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

	private static final String _FINDER_COLUMN_O_ORIGINALITY_2 = "dossier.originality = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_PNO = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGID_PNO",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_PNO =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGID_PNO",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.PROCESSNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_PNO = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGID_PNO",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossiers where groupId = &#63; and processNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processNo the process no
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_PNO(long groupId, String processNo) {
		return findByGID_PNO(groupId, processNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and processNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param processNo the process no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_PNO(long groupId, String processNo,
		int start, int end) {
		return findByGID_PNO(groupId, processNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and processNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param processNo the process no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_PNO(long groupId, String processNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByGID_PNO(groupId, processNo, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and processNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param processNo the process no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_PNO(long groupId, String processNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_PNO;
			finderArgs = new Object[] { groupId, processNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_PNO;
			finderArgs = new Object[] {
					groupId, processNo,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							!Objects.equals(processNo, dossier.getProcessNo())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_GID_PNO_GROUPID_2);

			boolean bindProcessNo = false;

			if (processNo == null) {
				query.append(_FINDER_COLUMN_GID_PNO_PROCESSNO_1);
			}
			else if (processNo.equals("")) {
				query.append(_FINDER_COLUMN_GID_PNO_PROCESSNO_3);
			}
			else {
				bindProcessNo = true;

				query.append(_FINDER_COLUMN_GID_PNO_PROCESSNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindProcessNo) {
					qPos.add(processNo);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processNo the process no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByGID_PNO_First(long groupId, String processNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByGID_PNO_First(groupId, processNo,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", processNo=");
		msg.append(processNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processNo the process no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByGID_PNO_First(long groupId, String processNo,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByGID_PNO(groupId, processNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processNo the process no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByGID_PNO_Last(long groupId, String processNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByGID_PNO_Last(groupId, processNo,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", processNo=");
		msg.append(processNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processNo the process no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByGID_PNO_Last(long groupId, String processNo,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByGID_PNO(groupId, processNo);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByGID_PNO(groupId, processNo, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param processNo the process no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByGID_PNO_PrevAndNext(long dossierId, long groupId,
		String processNo, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByGID_PNO_PrevAndNext(session, dossier, groupId,
					processNo, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByGID_PNO_PrevAndNext(session, dossier, groupId,
					processNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByGID_PNO_PrevAndNext(Session session,
		Dossier dossier, long groupId, String processNo,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_GID_PNO_GROUPID_2);

		boolean bindProcessNo = false;

		if (processNo == null) {
			query.append(_FINDER_COLUMN_GID_PNO_PROCESSNO_1);
		}
		else if (processNo.equals("")) {
			query.append(_FINDER_COLUMN_GID_PNO_PROCESSNO_3);
		}
		else {
			bindProcessNo = true;

			query.append(_FINDER_COLUMN_GID_PNO_PROCESSNO_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindProcessNo) {
			qPos.add(processNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and processNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param processNo the process no
	 */
	@Override
	public void removeByGID_PNO(long groupId, String processNo) {
		for (Dossier dossier : findByGID_PNO(groupId, processNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and processNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processNo the process no
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByGID_PNO(long groupId, String processNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_PNO;

		Object[] finderArgs = new Object[] { groupId, processNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_GID_PNO_GROUPID_2);

			boolean bindProcessNo = false;

			if (processNo == null) {
				query.append(_FINDER_COLUMN_GID_PNO_PROCESSNO_1);
			}
			else if (processNo.equals("")) {
				query.append(_FINDER_COLUMN_GID_PNO_PROCESSNO_3);
			}
			else {
				bindProcessNo = true;

				query.append(_FINDER_COLUMN_GID_PNO_PROCESSNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindProcessNo) {
					qPos.add(processNo);
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

	private static final String _FINDER_COLUMN_GID_PNO_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GID_PNO_PROCESSNO_1 = "dossier.processNo IS NULL";
	private static final String _FINDER_COLUMN_GID_PNO_PROCESSNO_2 = "dossier.processNo = ?";
	private static final String _FINDER_COLUMN_GID_PNO_PROCESSNO_3 = "(dossier.processNo IS NULL OR dossier.processNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NOT_ST_GT_MD =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByNOT_ST_GT_MD",
			new String[] {
				String.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOT_ST_GT_MD =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByNOT_ST_GT_MD",
			new String[] { String.class.getName(), Date.class.getName() });

	/**
	 * Returns all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	 *
	 * @param dossierStatus the dossier status
	 * @param modifiedDate the modified date
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByNOT_ST_GT_MD(String dossierStatus,
		Date modifiedDate) {
		return findByNOT_ST_GT_MD(dossierStatus, modifiedDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierStatus the dossier status
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNOT_ST_GT_MD(String dossierStatus,
		Date modifiedDate, int start, int end) {
		return findByNOT_ST_GT_MD(dossierStatus, modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierStatus the dossier status
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNOT_ST_GT_MD(String dossierStatus,
		Date modifiedDate, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByNOT_ST_GT_MD(dossierStatus, modifiedDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierStatus the dossier status
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNOT_ST_GT_MD(String dossierStatus,
		Date modifiedDate, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NOT_ST_GT_MD;
		finderArgs = new Object[] {
				dossierStatus, _getTime(modifiedDate),
				
				start, end, orderByComparator
			};

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if (Objects.equals(dossierStatus, dossier.getDossierStatus()) ||
							(modifiedDate.getTime() > dossier.getModifiedDate()
																 .getTime())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_2);
			}

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_NOT_ST_GT_MD_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_NOT_ST_GT_MD_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	 *
	 * @param dossierStatus the dossier status
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByNOT_ST_GT_MD_First(String dossierStatus,
		Date modifiedDate, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByNOT_ST_GT_MD_First(dossierStatus,
				modifiedDate, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	 *
	 * @param dossierStatus the dossier status
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByNOT_ST_GT_MD_First(String dossierStatus,
		Date modifiedDate, OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByNOT_ST_GT_MD(dossierStatus, modifiedDate, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	 *
	 * @param dossierStatus the dossier status
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByNOT_ST_GT_MD_Last(String dossierStatus,
		Date modifiedDate, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByNOT_ST_GT_MD_Last(dossierStatus, modifiedDate,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	 *
	 * @param dossierStatus the dossier status
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByNOT_ST_GT_MD_Last(String dossierStatus,
		Date modifiedDate, OrderByComparator<Dossier> orderByComparator) {
		int count = countByNOT_ST_GT_MD(dossierStatus, modifiedDate);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByNOT_ST_GT_MD(dossierStatus, modifiedDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param dossierStatus the dossier status
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByNOT_ST_GT_MD_PrevAndNext(long dossierId,
		String dossierStatus, Date modifiedDate,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByNOT_ST_GT_MD_PrevAndNext(session, dossier,
					dossierStatus, modifiedDate, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByNOT_ST_GT_MD_PrevAndNext(session, dossier,
					dossierStatus, modifiedDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByNOT_ST_GT_MD_PrevAndNext(Session session,
		Dossier dossier, String dossierStatus, Date modifiedDate,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		boolean bindDossierStatus = false;

		if (dossierStatus == null) {
			query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_1);
		}
		else if (dossierStatus.equals("")) {
			query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_3);
		}
		else {
			bindDossierStatus = true;

			query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_2);
		}

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_NOT_ST_GT_MD_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_NOT_ST_GT_MD_MODIFIEDDATE_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindDossierStatus) {
			qPos.add(dossierStatus);
		}

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dossiers where dossierStatus &ne; all &#63; and modifiedDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierStatuses the dossier statuses
	 * @param modifiedDate the modified date
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByNOT_ST_GT_MD(String[] dossierStatuses,
		Date modifiedDate) {
		return findByNOT_ST_GT_MD(dossierStatuses, modifiedDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where dossierStatus &ne; all &#63; and modifiedDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierStatuses the dossier statuses
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNOT_ST_GT_MD(String[] dossierStatuses,
		Date modifiedDate, int start, int end) {
		return findByNOT_ST_GT_MD(dossierStatuses, modifiedDate, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the dossiers where dossierStatus &ne; all &#63; and modifiedDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierStatuses the dossier statuses
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNOT_ST_GT_MD(String[] dossierStatuses,
		Date modifiedDate, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByNOT_ST_GT_MD(dossierStatuses, modifiedDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierStatus the dossier status
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNOT_ST_GT_MD(String[] dossierStatuses,
		Date modifiedDate, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		if (dossierStatuses == null) {
			dossierStatuses = new String[0];
		}
		else if (dossierStatuses.length > 1) {
			dossierStatuses = ArrayUtil.distinct(dossierStatuses,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(dossierStatuses, NULL_SAFE_STRING_COMPARATOR);
		}

		if (dossierStatuses.length == 1) {
			return findByNOT_ST_GT_MD(dossierStatuses[0], modifiedDate, start,
				end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(dossierStatuses), _getTime(modifiedDate)
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(dossierStatuses), _getTime(modifiedDate),
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_NOT_ST_GT_MD,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if (!ArrayUtil.contains(dossierStatuses,
								dossier.getDossierStatus()) ||
							(modifiedDate.getTime() > dossier.getModifiedDate()
																 .getTime())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			if (dossierStatuses.length > 0) {
				query.append("(");

				for (int i = 0; i < dossierStatuses.length; i++) {
					String dossierStatus = dossierStatuses[i];

					if (dossierStatus == null) {
						query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_4);
					}
					else if (dossierStatus.equals("")) {
						query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_6);
					}
					else {
						query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_5);
					}

					if ((i + 1) < dossierStatuses.length) {
						query.append(WHERE_AND);
					}
				}

				query.append(")");

				query.append(WHERE_AND);
			}

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_NOT_ST_GT_MD_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_NOT_ST_GT_MD_MODIFIEDDATE_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				for (String dossierStatus : dossierStatuses) {
					if ((dossierStatus != null) && !dossierStatus.isEmpty()) {
						qPos.add(dossierStatus);
					}
				}

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_NOT_ST_GT_MD,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_NOT_ST_GT_MD,
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
	 * Removes all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63; from the database.
	 *
	 * @param dossierStatus the dossier status
	 * @param modifiedDate the modified date
	 */
	@Override
	public void removeByNOT_ST_GT_MD(String dossierStatus, Date modifiedDate) {
		for (Dossier dossier : findByNOT_ST_GT_MD(dossierStatus, modifiedDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	 *
	 * @param dossierStatus the dossier status
	 * @param modifiedDate the modified date
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByNOT_ST_GT_MD(String dossierStatus, Date modifiedDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOT_ST_GT_MD;

		Object[] finderArgs = new Object[] { dossierStatus, _getTime(modifiedDate) };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_2);
			}

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_NOT_ST_GT_MD_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_NOT_ST_GT_MD_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
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

	/**
	 * Returns the number of dossiers where dossierStatus &ne; all &#63; and modifiedDate &ge; &#63;.
	 *
	 * @param dossierStatuses the dossier statuses
	 * @param modifiedDate the modified date
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByNOT_ST_GT_MD(String[] dossierStatuses, Date modifiedDate) {
		if (dossierStatuses == null) {
			dossierStatuses = new String[0];
		}
		else if (dossierStatuses.length > 1) {
			dossierStatuses = ArrayUtil.distinct(dossierStatuses,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(dossierStatuses, NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(dossierStatuses), _getTime(modifiedDate)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOT_ST_GT_MD,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			if (dossierStatuses.length > 0) {
				query.append("(");

				for (int i = 0; i < dossierStatuses.length; i++) {
					String dossierStatus = dossierStatuses[i];

					if (dossierStatus == null) {
						query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_4);
					}
					else if (dossierStatus.equals("")) {
						query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_6);
					}
					else {
						query.append(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_5);
					}

					if ((i + 1) < dossierStatuses.length) {
						query.append(WHERE_AND);
					}
				}

				query.append(")");

				query.append(WHERE_AND);
			}

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_NOT_ST_GT_MD_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_NOT_ST_GT_MD_MODIFIEDDATE_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				for (String dossierStatus : dossierStatuses) {
					if ((dossierStatus != null) && !dossierStatus.isEmpty()) {
						qPos.add(dossierStatus);
					}
				}

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOT_ST_GT_MD,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOT_ST_GT_MD,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_1 = "dossier.dossierStatus IS NOT NULL AND ";
	private static final String _FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_2 = "dossier.dossierStatus != ? AND ";
	private static final String _FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_3 = "(dossier.dossierStatus IS NULL OR dossier.dossierStatus != '') AND ";
	private static final String _FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_4 = "(" +
		removeConjunction(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_1) + ")";
	private static final String _FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_5 = "(" +
		removeConjunction(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_2) + ")";
	private static final String _FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_6 = "(" +
		removeConjunction(_FINDER_COLUMN_NOT_ST_GT_MD_DOSSIERSTATUS_3) + ")";
	private static final String _FINDER_COLUMN_NOT_ST_GT_MD_MODIFIEDDATE_1 = "dossier.modifiedDate IS NULL";
	private static final String _FINDER_COLUMN_NOT_ST_GT_MD_MODIFIEDDATE_2 = "dossier.modifiedDate >= ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_ORI_NO =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGID_ORI_NO",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_ORI_NO =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGID_ORI_NO",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.ORIGINDOSSIERNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_ORI_NO = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGID_ORI_NO",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossiers where groupId = &#63; and originDossierNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originDossierNo the origin dossier no
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_ORI_NO(long groupId, String originDossierNo) {
		return findByGID_ORI_NO(groupId, originDossierNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and originDossierNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param originDossierNo the origin dossier no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_ORI_NO(long groupId, String originDossierNo,
		int start, int end) {
		return findByGID_ORI_NO(groupId, originDossierNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and originDossierNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param originDossierNo the origin dossier no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_ORI_NO(long groupId, String originDossierNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByGID_ORI_NO(groupId, originDossierNo, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and originDossierNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param originDossierNo the origin dossier no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByGID_ORI_NO(long groupId, String originDossierNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_ORI_NO;
			finderArgs = new Object[] { groupId, originDossierNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_ORI_NO;
			finderArgs = new Object[] {
					groupId, originDossierNo,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							!Objects.equals(originDossierNo,
								dossier.getOriginDossierNo())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_GID_ORI_NO_GROUPID_2);

			boolean bindOriginDossierNo = false;

			if (originDossierNo == null) {
				query.append(_FINDER_COLUMN_GID_ORI_NO_ORIGINDOSSIERNO_1);
			}
			else if (originDossierNo.equals("")) {
				query.append(_FINDER_COLUMN_GID_ORI_NO_ORIGINDOSSIERNO_3);
			}
			else {
				bindOriginDossierNo = true;

				query.append(_FINDER_COLUMN_GID_ORI_NO_ORIGINDOSSIERNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindOriginDossierNo) {
					qPos.add(originDossierNo);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originDossierNo the origin dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByGID_ORI_NO_First(long groupId, String originDossierNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByGID_ORI_NO_First(groupId, originDossierNo,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", originDossierNo=");
		msg.append(originDossierNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originDossierNo the origin dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByGID_ORI_NO_First(long groupId,
		String originDossierNo, OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByGID_ORI_NO(groupId, originDossierNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originDossierNo the origin dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByGID_ORI_NO_Last(long groupId, String originDossierNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByGID_ORI_NO_Last(groupId, originDossierNo,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", originDossierNo=");
		msg.append(originDossierNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originDossierNo the origin dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByGID_ORI_NO_Last(long groupId, String originDossierNo,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByGID_ORI_NO(groupId, originDossierNo);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByGID_ORI_NO(groupId, originDossierNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param originDossierNo the origin dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByGID_ORI_NO_PrevAndNext(long dossierId, long groupId,
		String originDossierNo, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByGID_ORI_NO_PrevAndNext(session, dossier, groupId,
					originDossierNo, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByGID_ORI_NO_PrevAndNext(session, dossier, groupId,
					originDossierNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByGID_ORI_NO_PrevAndNext(Session session,
		Dossier dossier, long groupId, String originDossierNo,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_GID_ORI_NO_GROUPID_2);

		boolean bindOriginDossierNo = false;

		if (originDossierNo == null) {
			query.append(_FINDER_COLUMN_GID_ORI_NO_ORIGINDOSSIERNO_1);
		}
		else if (originDossierNo.equals("")) {
			query.append(_FINDER_COLUMN_GID_ORI_NO_ORIGINDOSSIERNO_3);
		}
		else {
			bindOriginDossierNo = true;

			query.append(_FINDER_COLUMN_GID_ORI_NO_ORIGINDOSSIERNO_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindOriginDossierNo) {
			qPos.add(originDossierNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and originDossierNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param originDossierNo the origin dossier no
	 */
	@Override
	public void removeByGID_ORI_NO(long groupId, String originDossierNo) {
		for (Dossier dossier : findByGID_ORI_NO(groupId, originDossierNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and originDossierNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param originDossierNo the origin dossier no
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByGID_ORI_NO(long groupId, String originDossierNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_ORI_NO;

		Object[] finderArgs = new Object[] { groupId, originDossierNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_GID_ORI_NO_GROUPID_2);

			boolean bindOriginDossierNo = false;

			if (originDossierNo == null) {
				query.append(_FINDER_COLUMN_GID_ORI_NO_ORIGINDOSSIERNO_1);
			}
			else if (originDossierNo.equals("")) {
				query.append(_FINDER_COLUMN_GID_ORI_NO_ORIGINDOSSIERNO_3);
			}
			else {
				bindOriginDossierNo = true;

				query.append(_FINDER_COLUMN_GID_ORI_NO_ORIGINDOSSIERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindOriginDossierNo) {
					qPos.add(originDossierNo);
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

	private static final String _FINDER_COLUMN_GID_ORI_NO_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GID_ORI_NO_ORIGINDOSSIERNO_1 = "dossier.originDossierNo IS NULL";
	private static final String _FINDER_COLUMN_GID_ORI_NO_ORIGINDOSSIERNO_2 = "dossier.originDossierNo = ?";
	private static final String _FINDER_COLUMN_GID_ORI_NO_ORIGINDOSSIERNO_3 = "(dossier.originDossierNo IS NULL OR dossier.originDossierNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ORIGIN_NO =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByORIGIN_NO",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORIGIN_NO =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByORIGIN_NO",
			new String[] { String.class.getName() },
			DossierModelImpl.ORIGINDOSSIERNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ORIGIN_NO = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByORIGIN_NO",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dossiers where originDossierNo = &#63;.
	 *
	 * @param originDossierNo the origin dossier no
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByORIGIN_NO(String originDossierNo) {
		return findByORIGIN_NO(originDossierNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where originDossierNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originDossierNo the origin dossier no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByORIGIN_NO(String originDossierNo, int start,
		int end) {
		return findByORIGIN_NO(originDossierNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where originDossierNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originDossierNo the origin dossier no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByORIGIN_NO(String originDossierNo, int start,
		int end, OrderByComparator<Dossier> orderByComparator) {
		return findByORIGIN_NO(originDossierNo, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the dossiers where originDossierNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originDossierNo the origin dossier no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByORIGIN_NO(String originDossierNo, int start,
		int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORIGIN_NO;
			finderArgs = new Object[] { originDossierNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ORIGIN_NO;
			finderArgs = new Object[] {
					originDossierNo,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if (!Objects.equals(originDossierNo,
								dossier.getOriginDossierNo())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			boolean bindOriginDossierNo = false;

			if (originDossierNo == null) {
				query.append(_FINDER_COLUMN_ORIGIN_NO_ORIGINDOSSIERNO_1);
			}
			else if (originDossierNo.equals("")) {
				query.append(_FINDER_COLUMN_ORIGIN_NO_ORIGINDOSSIERNO_3);
			}
			else {
				bindOriginDossierNo = true;

				query.append(_FINDER_COLUMN_ORIGIN_NO_ORIGINDOSSIERNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindOriginDossierNo) {
					qPos.add(originDossierNo);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where originDossierNo = &#63;.
	 *
	 * @param originDossierNo the origin dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByORIGIN_NO_First(String originDossierNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByORIGIN_NO_First(originDossierNo,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("originDossierNo=");
		msg.append(originDossierNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where originDossierNo = &#63;.
	 *
	 * @param originDossierNo the origin dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByORIGIN_NO_First(String originDossierNo,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByORIGIN_NO(originDossierNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where originDossierNo = &#63;.
	 *
	 * @param originDossierNo the origin dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByORIGIN_NO_Last(String originDossierNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByORIGIN_NO_Last(originDossierNo,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("originDossierNo=");
		msg.append(originDossierNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where originDossierNo = &#63;.
	 *
	 * @param originDossierNo the origin dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByORIGIN_NO_Last(String originDossierNo,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByORIGIN_NO(originDossierNo);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByORIGIN_NO(originDossierNo, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where originDossierNo = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param originDossierNo the origin dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByORIGIN_NO_PrevAndNext(long dossierId,
		String originDossierNo, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByORIGIN_NO_PrevAndNext(session, dossier,
					originDossierNo, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByORIGIN_NO_PrevAndNext(session, dossier,
					originDossierNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByORIGIN_NO_PrevAndNext(Session session,
		Dossier dossier, String originDossierNo,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		boolean bindOriginDossierNo = false;

		if (originDossierNo == null) {
			query.append(_FINDER_COLUMN_ORIGIN_NO_ORIGINDOSSIERNO_1);
		}
		else if (originDossierNo.equals("")) {
			query.append(_FINDER_COLUMN_ORIGIN_NO_ORIGINDOSSIERNO_3);
		}
		else {
			bindOriginDossierNo = true;

			query.append(_FINDER_COLUMN_ORIGIN_NO_ORIGINDOSSIERNO_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindOriginDossierNo) {
			qPos.add(originDossierNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where originDossierNo = &#63; from the database.
	 *
	 * @param originDossierNo the origin dossier no
	 */
	@Override
	public void removeByORIGIN_NO(String originDossierNo) {
		for (Dossier dossier : findByORIGIN_NO(originDossierNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where originDossierNo = &#63;.
	 *
	 * @param originDossierNo the origin dossier no
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByORIGIN_NO(String originDossierNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ORIGIN_NO;

		Object[] finderArgs = new Object[] { originDossierNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			boolean bindOriginDossierNo = false;

			if (originDossierNo == null) {
				query.append(_FINDER_COLUMN_ORIGIN_NO_ORIGINDOSSIERNO_1);
			}
			else if (originDossierNo.equals("")) {
				query.append(_FINDER_COLUMN_ORIGIN_NO_ORIGINDOSSIERNO_3);
			}
			else {
				bindOriginDossierNo = true;

				query.append(_FINDER_COLUMN_ORIGIN_NO_ORIGINDOSSIERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindOriginDossierNo) {
					qPos.add(originDossierNo);
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

	private static final String _FINDER_COLUMN_ORIGIN_NO_ORIGINDOSSIERNO_1 = "dossier.originDossierNo IS NULL";
	private static final String _FINDER_COLUMN_ORIGIN_NO_ORIGINDOSSIERNO_2 = "dossier.originDossierNo = ?";
	private static final String _FINDER_COLUMN_ORIGIN_NO_ORIGINDOSSIERNO_3 = "(dossier.originDossierNo IS NULL OR dossier.originDossierNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_SC =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_NOTS_O_SC",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_SC =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_NOTS_O_SC",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName()
			});

	/**
	 * Returns all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_SC(long groupId, String dossierStatus,
		int originality, String serviceCode) {
		return findByG_NOTS_O_SC(groupId, dossierStatus, originality,
			serviceCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_SC(long groupId, String dossierStatus,
		int originality, String serviceCode, int start, int end) {
		return findByG_NOTS_O_SC(groupId, dossierStatus, originality,
			serviceCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_SC(long groupId, String dossierStatus,
		int originality, String serviceCode, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByG_NOTS_O_SC(groupId, dossierStatus, originality,
			serviceCode, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_SC(long groupId, String dossierStatus,
		int originality, String serviceCode, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_SC;
		finderArgs = new Object[] {
				groupId, dossierStatus, originality, serviceCode,
				
				start, end, orderByComparator
			};

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							Objects.equals(dossierStatus,
								dossier.getDossierStatus()) ||
							(originality > dossier.getOriginality()) ||
							!Objects.equals(serviceCode,
								dossier.getServiceCode())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_NOTS_O_SC_GROUPID_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_2);
			}

			query.append(_FINDER_COLUMN_G_NOTS_O_SC_ORIGINALITY_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				qPos.add(originality);

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_NOTS_O_SC_First(long groupId, String dossierStatus,
		int originality, String serviceCode,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_NOTS_O_SC_First(groupId, dossierStatus,
				originality, serviceCode, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", originality=");
		msg.append(originality);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_NOTS_O_SC_First(long groupId, String dossierStatus,
		int originality, String serviceCode,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_NOTS_O_SC(groupId, dossierStatus,
				originality, serviceCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_NOTS_O_SC_Last(long groupId, String dossierStatus,
		int originality, String serviceCode,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_NOTS_O_SC_Last(groupId, dossierStatus,
				originality, serviceCode, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", originality=");
		msg.append(originality);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_NOTS_O_SC_Last(long groupId, String dossierStatus,
		int originality, String serviceCode,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_NOTS_O_SC(groupId, dossierStatus, originality,
				serviceCode);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_NOTS_O_SC(groupId, dossierStatus,
				originality, serviceCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_NOTS_O_SC_PrevAndNext(long dossierId,
		long groupId, String dossierStatus, int originality,
		String serviceCode, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_NOTS_O_SC_PrevAndNext(session, dossier, groupId,
					dossierStatus, originality, serviceCode, orderByComparator,
					true);

			array[1] = dossier;

			array[2] = getByG_NOTS_O_SC_PrevAndNext(session, dossier, groupId,
					dossierStatus, originality, serviceCode, orderByComparator,
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

	protected Dossier getByG_NOTS_O_SC_PrevAndNext(Session session,
		Dossier dossier, long groupId, String dossierStatus, int originality,
		String serviceCode, OrderByComparator<Dossier> orderByComparator,
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

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_NOTS_O_SC_GROUPID_2);

		boolean bindDossierStatus = false;

		if (dossierStatus == null) {
			query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_1);
		}
		else if (dossierStatus.equals("")) {
			query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_3);
		}
		else {
			bindDossierStatus = true;

			query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_2);
		}

		query.append(_FINDER_COLUMN_G_NOTS_O_SC_ORIGINALITY_2);

		boolean bindServiceCode = false;

		if (serviceCode == null) {
			query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_1);
		}
		else if (serviceCode.equals("")) {
			query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_3);
		}
		else {
			bindServiceCode = true;

			query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindDossierStatus) {
			qPos.add(dossierStatus);
		}

		qPos.add(originality);

		if (bindServiceCode) {
			qPos.add(serviceCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatuses the dossier statuses
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_SC(long groupId,
		String[] dossierStatuses, int originality, String serviceCode) {
		return findByG_NOTS_O_SC(groupId, dossierStatuses, originality,
			serviceCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatuses the dossier statuses
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_SC(long groupId,
		String[] dossierStatuses, int originality, String serviceCode,
		int start, int end) {
		return findByG_NOTS_O_SC(groupId, dossierStatuses, originality,
			serviceCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatuses the dossier statuses
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_SC(long groupId,
		String[] dossierStatuses, int originality, String serviceCode,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByG_NOTS_O_SC(groupId, dossierStatuses, originality,
			serviceCode, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_SC(long groupId,
		String[] dossierStatuses, int originality, String serviceCode,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		if (dossierStatuses == null) {
			dossierStatuses = new String[0];
		}
		else if (dossierStatuses.length > 1) {
			dossierStatuses = ArrayUtil.distinct(dossierStatuses,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(dossierStatuses, NULL_SAFE_STRING_COMPARATOR);
		}

		if (dossierStatuses.length == 1) {
			return findByG_NOTS_O_SC(groupId, dossierStatuses[0], originality,
				serviceCode, start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(dossierStatuses), originality,
					serviceCode
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(dossierStatuses), originality,
					serviceCode,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_SC,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							!ArrayUtil.contains(dossierStatuses,
								dossier.getDossierStatus()) ||
							(originality > dossier.getOriginality()) ||
							!Objects.equals(serviceCode,
								dossier.getServiceCode())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_NOTS_O_SC_GROUPID_2);

			if (dossierStatuses.length > 0) {
				query.append("(");

				for (int i = 0; i < dossierStatuses.length; i++) {
					String dossierStatus = dossierStatuses[i];

					if (dossierStatus == null) {
						query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_4);
					}
					else if (dossierStatus.equals("")) {
						query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_5);
					}

					if ((i + 1) < dossierStatuses.length) {
						query.append(WHERE_AND);
					}
				}

				query.append(")");

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_NOTS_O_SC_ORIGINALITY_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				for (String dossierStatus : dossierStatuses) {
					if ((dossierStatus != null) && !dossierStatus.isEmpty()) {
						qPos.add(dossierStatus);
					}
				}

				qPos.add(originality);

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_SC,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_SC,
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
	 * Removes all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param serviceCode the service code
	 */
	@Override
	public void removeByG_NOTS_O_SC(long groupId, String dossierStatus,
		int originality, String serviceCode) {
		for (Dossier dossier : findByG_NOTS_O_SC(groupId, dossierStatus,
				originality, serviceCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_NOTS_O_SC(long groupId, String dossierStatus,
		int originality, String serviceCode) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_SC;

		Object[] finderArgs = new Object[] {
				groupId, dossierStatus, originality, serviceCode
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_NOTS_O_SC_GROUPID_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_2);
			}

			query.append(_FINDER_COLUMN_G_NOTS_O_SC_ORIGINALITY_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				qPos.add(originality);

				if (bindServiceCode) {
					qPos.add(serviceCode);
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

	/**
	 * Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatuses the dossier statuses
	 * @param originality the originality
	 * @param serviceCode the service code
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_NOTS_O_SC(long groupId, String[] dossierStatuses,
		int originality, String serviceCode) {
		if (dossierStatuses == null) {
			dossierStatuses = new String[0];
		}
		else if (dossierStatuses.length > 1) {
			dossierStatuses = ArrayUtil.distinct(dossierStatuses,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(dossierStatuses, NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(dossierStatuses), originality,
				serviceCode
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_SC,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_NOTS_O_SC_GROUPID_2);

			if (dossierStatuses.length > 0) {
				query.append("(");

				for (int i = 0; i < dossierStatuses.length; i++) {
					String dossierStatus = dossierStatuses[i];

					if (dossierStatus == null) {
						query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_4);
					}
					else if (dossierStatus.equals("")) {
						query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_5);
					}

					if ((i + 1) < dossierStatuses.length) {
						query.append(WHERE_AND);
					}
				}

				query.append(")");

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_NOTS_O_SC_ORIGINALITY_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_2);
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

				for (String dossierStatus : dossierStatuses) {
					if ((dossierStatus != null) && !dossierStatus.isEmpty()) {
						qPos.add(dossierStatus);
					}
				}

				qPos.add(originality);

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_SC,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_SC,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_NOTS_O_SC_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_1 = "dossier.dossierStatus IS NOT NULL AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_2 = "dossier.dossierStatus != ? AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_3 = "(dossier.dossierStatus IS NULL OR dossier.dossierStatus != '') AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_4 = "(" +
		removeConjunction(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_1) + ")";
	private static final String _FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_2) + ")";
	private static final String _FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_6 = "(" +
		removeConjunction(_FINDER_COLUMN_G_NOTS_O_SC_DOSSIERSTATUS_3) + ")";
	private static final String _FINDER_COLUMN_G_NOTS_O_SC_ORIGINALITY_2 = "dossier.originality >= ? AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_1 = "dossier.serviceCode IS NULL";
	private static final String _FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_2 = "dossier.serviceCode = ?";
	private static final String _FINDER_COLUMN_G_NOTS_O_SC_SERVICECODE_3 = "(dossier.serviceCode IS NULL OR dossier.serviceCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_DTN =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_NOTS_O_DTN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_DTN =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_NOTS_O_DTN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName()
			});

	/**
	 * Returns all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_DTN(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo) {
		return findByG_NOTS_O_DTN(groupId, dossierStatus, originality,
			dossierTemplateNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_DTN(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo, int start, int end) {
		return findByG_NOTS_O_DTN(groupId, dossierStatus, originality,
			dossierTemplateNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_DTN(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByG_NOTS_O_DTN(groupId, dossierStatus, originality,
			dossierTemplateNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_DTN(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_DTN;
		finderArgs = new Object[] {
				groupId, dossierStatus, originality, dossierTemplateNo,
				
				start, end, orderByComparator
			};

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							Objects.equals(dossierStatus,
								dossier.getDossierStatus()) ||
							(originality > dossier.getOriginality()) ||
							!Objects.equals(dossierTemplateNo,
								dossier.getDossierTemplateNo())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_NOTS_O_DTN_GROUPID_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_2);
			}

			query.append(_FINDER_COLUMN_G_NOTS_O_DTN_ORIGINALITY_2);

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				qPos.add(originality);

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_NOTS_O_DTN_First(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_NOTS_O_DTN_First(groupId, dossierStatus,
				originality, dossierTemplateNo, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", originality=");
		msg.append(originality);

		msg.append(", dossierTemplateNo=");
		msg.append(dossierTemplateNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_NOTS_O_DTN_First(long groupId,
		String dossierStatus, int originality, String dossierTemplateNo,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_NOTS_O_DTN(groupId, dossierStatus,
				originality, dossierTemplateNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_NOTS_O_DTN_Last(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_NOTS_O_DTN_Last(groupId, dossierStatus,
				originality, dossierTemplateNo, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", originality=");
		msg.append(originality);

		msg.append(", dossierTemplateNo=");
		msg.append(dossierTemplateNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_NOTS_O_DTN_Last(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_NOTS_O_DTN(groupId, dossierStatus, originality,
				dossierTemplateNo);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_NOTS_O_DTN(groupId, dossierStatus,
				originality, dossierTemplateNo, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_NOTS_O_DTN_PrevAndNext(long dossierId,
		long groupId, String dossierStatus, int originality,
		String dossierTemplateNo, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_NOTS_O_DTN_PrevAndNext(session, dossier, groupId,
					dossierStatus, originality, dossierTemplateNo,
					orderByComparator, true);

			array[1] = dossier;

			array[2] = getByG_NOTS_O_DTN_PrevAndNext(session, dossier, groupId,
					dossierStatus, originality, dossierTemplateNo,
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

	protected Dossier getByG_NOTS_O_DTN_PrevAndNext(Session session,
		Dossier dossier, long groupId, String dossierStatus, int originality,
		String dossierTemplateNo, OrderByComparator<Dossier> orderByComparator,
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

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_NOTS_O_DTN_GROUPID_2);

		boolean bindDossierStatus = false;

		if (dossierStatus == null) {
			query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_1);
		}
		else if (dossierStatus.equals("")) {
			query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_3);
		}
		else {
			bindDossierStatus = true;

			query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_2);
		}

		query.append(_FINDER_COLUMN_G_NOTS_O_DTN_ORIGINALITY_2);

		boolean bindDossierTemplateNo = false;

		if (dossierTemplateNo == null) {
			query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_1);
		}
		else if (dossierTemplateNo.equals("")) {
			query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_3);
		}
		else {
			bindDossierTemplateNo = true;

			query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindDossierStatus) {
			qPos.add(dossierStatus);
		}

		qPos.add(originality);

		if (bindDossierTemplateNo) {
			qPos.add(dossierTemplateNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatuses the dossier statuses
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String[] dossierStatuses, int originality, String dossierTemplateNo) {
		return findByG_NOTS_O_DTN(groupId, dossierStatuses, originality,
			dossierTemplateNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatuses the dossier statuses
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String[] dossierStatuses, int originality, String dossierTemplateNo,
		int start, int end) {
		return findByG_NOTS_O_DTN(groupId, dossierStatuses, originality,
			dossierTemplateNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatuses the dossier statuses
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String[] dossierStatuses, int originality, String dossierTemplateNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByG_NOTS_O_DTN(groupId, dossierStatuses, originality,
			dossierTemplateNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String[] dossierStatuses, int originality, String dossierTemplateNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		if (dossierStatuses == null) {
			dossierStatuses = new String[0];
		}
		else if (dossierStatuses.length > 1) {
			dossierStatuses = ArrayUtil.distinct(dossierStatuses,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(dossierStatuses, NULL_SAFE_STRING_COMPARATOR);
		}

		if (dossierStatuses.length == 1) {
			return findByG_NOTS_O_DTN(groupId, dossierStatuses[0], originality,
				dossierTemplateNo, start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(dossierStatuses), originality,
					dossierTemplateNo
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(dossierStatuses), originality,
					dossierTemplateNo,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_DTN,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							!ArrayUtil.contains(dossierStatuses,
								dossier.getDossierStatus()) ||
							(originality > dossier.getOriginality()) ||
							!Objects.equals(dossierTemplateNo,
								dossier.getDossierTemplateNo())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_NOTS_O_DTN_GROUPID_2);

			if (dossierStatuses.length > 0) {
				query.append("(");

				for (int i = 0; i < dossierStatuses.length; i++) {
					String dossierStatus = dossierStatuses[i];

					if (dossierStatus == null) {
						query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_4);
					}
					else if (dossierStatus.equals("")) {
						query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_5);
					}

					if ((i + 1) < dossierStatuses.length) {
						query.append(WHERE_AND);
					}
				}

				query.append(")");

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_NOTS_O_DTN_ORIGINALITY_2);

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				for (String dossierStatus : dossierStatuses) {
					if ((dossierStatus != null) && !dossierStatus.isEmpty()) {
						qPos.add(dossierStatus);
					}
				}

				qPos.add(originality);

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_DTN,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_DTN,
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
	 * Removes all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 */
	@Override
	public void removeByG_NOTS_O_DTN(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo) {
		for (Dossier dossier : findByG_NOTS_O_DTN(groupId, dossierStatus,
				originality, dossierTemplateNo, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_NOTS_O_DTN(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_DTN;

		Object[] finderArgs = new Object[] {
				groupId, dossierStatus, originality, dossierTemplateNo
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_NOTS_O_DTN_GROUPID_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_2);
			}

			query.append(_FINDER_COLUMN_G_NOTS_O_DTN_ORIGINALITY_2);

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				qPos.add(originality);

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
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

	/**
	 * Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatuses the dossier statuses
	 * @param originality the originality
	 * @param dossierTemplateNo the dossier template no
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_NOTS_O_DTN(long groupId, String[] dossierStatuses,
		int originality, String dossierTemplateNo) {
		if (dossierStatuses == null) {
			dossierStatuses = new String[0];
		}
		else if (dossierStatuses.length > 1) {
			dossierStatuses = ArrayUtil.distinct(dossierStatuses,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(dossierStatuses, NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(dossierStatuses), originality,
				dossierTemplateNo
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_DTN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_NOTS_O_DTN_GROUPID_2);

			if (dossierStatuses.length > 0) {
				query.append("(");

				for (int i = 0; i < dossierStatuses.length; i++) {
					String dossierStatus = dossierStatuses[i];

					if (dossierStatus == null) {
						query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_4);
					}
					else if (dossierStatus.equals("")) {
						query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_5);
					}

					if ((i + 1) < dossierStatuses.length) {
						query.append(WHERE_AND);
					}
				}

				query.append(")");

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_NOTS_O_DTN_ORIGINALITY_2);

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_2);
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

				for (String dossierStatus : dossierStatuses) {
					if ((dossierStatus != null) && !dossierStatus.isEmpty()) {
						qPos.add(dossierStatus);
					}
				}

				qPos.add(originality);

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_DTN,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_DTN,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_NOTS_O_DTN_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_1 = "dossier.dossierStatus IS NOT NULL AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_2 = "dossier.dossierStatus != ? AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_3 = "(dossier.dossierStatus IS NULL OR dossier.dossierStatus != '') AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_4 = "(" +
		removeConjunction(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_1) + ")";
	private static final String _FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_2) + ")";
	private static final String _FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_6 = "(" +
		removeConjunction(_FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERSTATUS_3) + ")";
	private static final String _FINDER_COLUMN_G_NOTS_O_DTN_ORIGINALITY_2 = "dossier.originality >= ? AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_1 = "dossier.dossierTemplateNo IS NULL";
	private static final String _FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_2 = "dossier.dossierTemplateNo = ?";
	private static final String _FINDER_COLUMN_G_NOTS_O_DTN_DOSSIERTEMPLATENO_3 = "(dossier.dossierTemplateNo IS NULL OR dossier.dossierTemplateNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_PN =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_NOTS_O_PN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_PN =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_NOTS_O_PN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName()
			});

	/**
	 * Returns all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param processNo the process no
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_PN(long groupId, String dossierStatus,
		int originality, String processNo) {
		return findByG_NOTS_O_PN(groupId, dossierStatus, originality,
			processNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param processNo the process no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_PN(long groupId, String dossierStatus,
		int originality, String processNo, int start, int end) {
		return findByG_NOTS_O_PN(groupId, dossierStatus, originality,
			processNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param processNo the process no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_PN(long groupId, String dossierStatus,
		int originality, String processNo, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByG_NOTS_O_PN(groupId, dossierStatus, originality,
			processNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param processNo the process no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_PN(long groupId, String dossierStatus,
		int originality, String processNo, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_PN;
		finderArgs = new Object[] {
				groupId, dossierStatus, originality, processNo,
				
				start, end, orderByComparator
			};

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							Objects.equals(dossierStatus,
								dossier.getDossierStatus()) ||
							(originality > dossier.getOriginality()) ||
							!Objects.equals(processNo, dossier.getProcessNo())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_NOTS_O_PN_GROUPID_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_2);
			}

			query.append(_FINDER_COLUMN_G_NOTS_O_PN_ORIGINALITY_2);

			boolean bindProcessNo = false;

			if (processNo == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_1);
			}
			else if (processNo.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_3);
			}
			else {
				bindProcessNo = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				qPos.add(originality);

				if (bindProcessNo) {
					qPos.add(processNo);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param processNo the process no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_NOTS_O_PN_First(long groupId, String dossierStatus,
		int originality, String processNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_NOTS_O_PN_First(groupId, dossierStatus,
				originality, processNo, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", originality=");
		msg.append(originality);

		msg.append(", processNo=");
		msg.append(processNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param processNo the process no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_NOTS_O_PN_First(long groupId, String dossierStatus,
		int originality, String processNo,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_NOTS_O_PN(groupId, dossierStatus,
				originality, processNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param processNo the process no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_NOTS_O_PN_Last(long groupId, String dossierStatus,
		int originality, String processNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_NOTS_O_PN_Last(groupId, dossierStatus,
				originality, processNo, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", originality=");
		msg.append(originality);

		msg.append(", processNo=");
		msg.append(processNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param processNo the process no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_NOTS_O_PN_Last(long groupId, String dossierStatus,
		int originality, String processNo,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_NOTS_O_PN(groupId, dossierStatus, originality,
				processNo);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_NOTS_O_PN(groupId, dossierStatus,
				originality, processNo, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param processNo the process no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_NOTS_O_PN_PrevAndNext(long dossierId,
		long groupId, String dossierStatus, int originality, String processNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_NOTS_O_PN_PrevAndNext(session, dossier, groupId,
					dossierStatus, originality, processNo, orderByComparator,
					true);

			array[1] = dossier;

			array[2] = getByG_NOTS_O_PN_PrevAndNext(session, dossier, groupId,
					dossierStatus, originality, processNo, orderByComparator,
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

	protected Dossier getByG_NOTS_O_PN_PrevAndNext(Session session,
		Dossier dossier, long groupId, String dossierStatus, int originality,
		String processNo, OrderByComparator<Dossier> orderByComparator,
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

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_NOTS_O_PN_GROUPID_2);

		boolean bindDossierStatus = false;

		if (dossierStatus == null) {
			query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_1);
		}
		else if (dossierStatus.equals("")) {
			query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_3);
		}
		else {
			bindDossierStatus = true;

			query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_2);
		}

		query.append(_FINDER_COLUMN_G_NOTS_O_PN_ORIGINALITY_2);

		boolean bindProcessNo = false;

		if (processNo == null) {
			query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_1);
		}
		else if (processNo.equals("")) {
			query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_3);
		}
		else {
			bindProcessNo = true;

			query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindDossierStatus) {
			qPos.add(dossierStatus);
		}

		qPos.add(originality);

		if (bindProcessNo) {
			qPos.add(processNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and processNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatuses the dossier statuses
	 * @param originality the originality
	 * @param processNo the process no
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_PN(long groupId,
		String[] dossierStatuses, int originality, String processNo) {
		return findByG_NOTS_O_PN(groupId, dossierStatuses, originality,
			processNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and processNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatuses the dossier statuses
	 * @param originality the originality
	 * @param processNo the process no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_PN(long groupId,
		String[] dossierStatuses, int originality, String processNo, int start,
		int end) {
		return findByG_NOTS_O_PN(groupId, dossierStatuses, originality,
			processNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and processNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatuses the dossier statuses
	 * @param originality the originality
	 * @param processNo the process no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_PN(long groupId,
		String[] dossierStatuses, int originality, String processNo, int start,
		int end, OrderByComparator<Dossier> orderByComparator) {
		return findByG_NOTS_O_PN(groupId, dossierStatuses, originality,
			processNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param processNo the process no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_NOTS_O_PN(long groupId,
		String[] dossierStatuses, int originality, String processNo, int start,
		int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		if (dossierStatuses == null) {
			dossierStatuses = new String[0];
		}
		else if (dossierStatuses.length > 1) {
			dossierStatuses = ArrayUtil.distinct(dossierStatuses,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(dossierStatuses, NULL_SAFE_STRING_COMPARATOR);
		}

		if (dossierStatuses.length == 1) {
			return findByG_NOTS_O_PN(groupId, dossierStatuses[0], originality,
				processNo, start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(dossierStatuses), originality,
					processNo
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(dossierStatuses), originality,
					processNo,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_PN,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							!ArrayUtil.contains(dossierStatuses,
								dossier.getDossierStatus()) ||
							(originality > dossier.getOriginality()) ||
							!Objects.equals(processNo, dossier.getProcessNo())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_NOTS_O_PN_GROUPID_2);

			if (dossierStatuses.length > 0) {
				query.append("(");

				for (int i = 0; i < dossierStatuses.length; i++) {
					String dossierStatus = dossierStatuses[i];

					if (dossierStatus == null) {
						query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_4);
					}
					else if (dossierStatus.equals("")) {
						query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_5);
					}

					if ((i + 1) < dossierStatuses.length) {
						query.append(WHERE_AND);
					}
				}

				query.append(")");

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_NOTS_O_PN_ORIGINALITY_2);

			boolean bindProcessNo = false;

			if (processNo == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_1);
			}
			else if (processNo.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_3);
			}
			else {
				bindProcessNo = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				for (String dossierStatus : dossierStatuses) {
					if ((dossierStatus != null) && !dossierStatus.isEmpty()) {
						qPos.add(dossierStatus);
					}
				}

				qPos.add(originality);

				if (bindProcessNo) {
					qPos.add(processNo);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_PN,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NOTS_O_PN,
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
	 * Removes all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param processNo the process no
	 */
	@Override
	public void removeByG_NOTS_O_PN(long groupId, String dossierStatus,
		int originality, String processNo) {
		for (Dossier dossier : findByG_NOTS_O_PN(groupId, dossierStatus,
				originality, processNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param processNo the process no
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_NOTS_O_PN(long groupId, String dossierStatus,
		int originality, String processNo) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_PN;

		Object[] finderArgs = new Object[] {
				groupId, dossierStatus, originality, processNo
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_NOTS_O_PN_GROUPID_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_2);
			}

			query.append(_FINDER_COLUMN_G_NOTS_O_PN_ORIGINALITY_2);

			boolean bindProcessNo = false;

			if (processNo == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_1);
			}
			else if (processNo.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_3);
			}
			else {
				bindProcessNo = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				qPos.add(originality);

				if (bindProcessNo) {
					qPos.add(processNo);
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

	/**
	 * Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and processNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatuses the dossier statuses
	 * @param originality the originality
	 * @param processNo the process no
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_NOTS_O_PN(long groupId, String[] dossierStatuses,
		int originality, String processNo) {
		if (dossierStatuses == null) {
			dossierStatuses = new String[0];
		}
		else if (dossierStatuses.length > 1) {
			dossierStatuses = ArrayUtil.distinct(dossierStatuses,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(dossierStatuses, NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(dossierStatuses), originality,
				processNo
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_PN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_NOTS_O_PN_GROUPID_2);

			if (dossierStatuses.length > 0) {
				query.append("(");

				for (int i = 0; i < dossierStatuses.length; i++) {
					String dossierStatus = dossierStatuses[i];

					if (dossierStatus == null) {
						query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_4);
					}
					else if (dossierStatus.equals("")) {
						query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_5);
					}

					if ((i + 1) < dossierStatuses.length) {
						query.append(WHERE_AND);
					}
				}

				query.append(")");

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_NOTS_O_PN_ORIGINALITY_2);

			boolean bindProcessNo = false;

			if (processNo == null) {
				query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_1);
			}
			else if (processNo.equals("")) {
				query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_3);
			}
			else {
				bindProcessNo = true;

				query.append(_FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_2);
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

				for (String dossierStatus : dossierStatuses) {
					if ((dossierStatus != null) && !dossierStatus.isEmpty()) {
						qPos.add(dossierStatus);
					}
				}

				qPos.add(originality);

				if (bindProcessNo) {
					qPos.add(processNo);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_PN,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NOTS_O_PN,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_NOTS_O_PN_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_1 = "dossier.dossierStatus IS NOT NULL AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_2 = "dossier.dossierStatus != ? AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_3 = "(dossier.dossierStatus IS NULL OR dossier.dossierStatus != '') AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_4 = "(" +
		removeConjunction(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_1) + ")";
	private static final String _FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_2) + ")";
	private static final String _FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_6 = "(" +
		removeConjunction(_FINDER_COLUMN_G_NOTS_O_PN_DOSSIERSTATUS_3) + ")";
	private static final String _FINDER_COLUMN_G_NOTS_O_PN_ORIGINALITY_2 = "dossier.originality >= ? AND ";
	private static final String _FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_1 = "dossier.processNo IS NULL";
	private static final String _FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_2 = "dossier.processNo = ?";
	private static final String _FINDER_COLUMN_G_NOTS_O_PN_PROCESSNO_3 = "(dossier.processNo IS NULL OR dossier.processNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DS_O =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_G_GAC_SC_DTNO_DS_O",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DS_O =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_G_GAC_SC_DTNO_DS_O",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			DossierModelImpl.USERID_COLUMN_BITMASK |
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			DossierModelImpl.SERVICECODE_COLUMN_BITMASK |
			DossierModelImpl.DOSSIERTEMPLATENO_COLUMN_BITMASK |
			DossierModelImpl.DOSSIERSTATUS_COLUMN_BITMASK |
			DossierModelImpl.ORIGINALITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_G_GAC_SC_DTNO_DS_O = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByU_G_GAC_SC_DTNO_DS_O",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByU_G_GAC_SC_DTNO_DS_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality) {
		return findByU_G_GAC_SC_DTNO_DS_O(userId, groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, originality,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByU_G_GAC_SC_DTNO_DS_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality, int start, int end) {
		return findByU_G_GAC_SC_DTNO_DS_O(userId, groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, originality, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByU_G_GAC_SC_DTNO_DS_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByU_G_GAC_SC_DTNO_DS_O(userId, groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, originality, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByU_G_GAC_SC_DTNO_DS_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DS_O;
			finderArgs = new Object[] {
					userId, groupId, govAgencyCode, serviceCode,
					dossierTemplateNo, dossierStatus, originality
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DS_O;
			finderArgs = new Object[] {
					userId, groupId, govAgencyCode, serviceCode,
					dossierTemplateNo, dossierStatus, originality,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((userId != dossier.getUserId()) ||
							(groupId != dossier.getGroupId()) ||
							!Objects.equals(govAgencyCode,
								dossier.getGovAgencyCode()) ||
							!Objects.equals(serviceCode,
								dossier.getServiceCode()) ||
							!Objects.equals(dossierTemplateNo,
								dossier.getDossierTemplateNo()) ||
							!Objects.equals(dossierStatus,
								dossier.getDossierStatus()) ||
							(originality != dossier.getOriginality())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(9 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(9);
			}

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_USERID_2);

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GOVAGENCYCODE_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_SERVICECODE_2);
			}

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERTEMPLATENO_2);
			}

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERSTATUS_2);
			}

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_ORIGINALITY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				qPos.add(originality);

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByU_G_GAC_SC_DTNO_DS_O_First(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByU_G_GAC_SC_DTNO_DS_O_First(userId, groupId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
				originality, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(16);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append(", dossierTemplateNo=");
		msg.append(dossierTemplateNo);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", originality=");
		msg.append(originality);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByU_G_GAC_SC_DTNO_DS_O_First(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByU_G_GAC_SC_DTNO_DS_O(userId, groupId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
				originality, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByU_G_GAC_SC_DTNO_DS_O_Last(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByU_G_GAC_SC_DTNO_DS_O_Last(userId, groupId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
				originality, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(16);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append(", dossierTemplateNo=");
		msg.append(dossierTemplateNo);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", originality=");
		msg.append(originality);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByU_G_GAC_SC_DTNO_DS_O_Last(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByU_G_GAC_SC_DTNO_DS_O(userId, groupId, govAgencyCode,
				serviceCode, dossierTemplateNo, dossierStatus, originality);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByU_G_GAC_SC_DTNO_DS_O(userId, groupId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
				originality, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByU_G_GAC_SC_DTNO_DS_O_PrevAndNext(long dossierId,
		long userId, long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByU_G_GAC_SC_DTNO_DS_O_PrevAndNext(session, dossier,
					userId, groupId, govAgencyCode, serviceCode,
					dossierTemplateNo, dossierStatus, originality,
					orderByComparator, true);

			array[1] = dossier;

			array[2] = getByU_G_GAC_SC_DTNO_DS_O_PrevAndNext(session, dossier,
					userId, groupId, govAgencyCode, serviceCode,
					dossierTemplateNo, dossierStatus, originality,
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

	protected Dossier getByU_G_GAC_SC_DTNO_DS_O_PrevAndNext(Session session,
		Dossier dossier, long userId, long groupId, String govAgencyCode,
		String serviceCode, String dossierTemplateNo, String dossierStatus,
		int originality, OrderByComparator<Dossier> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(10 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(9);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_USERID_2);

		query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GROUPID_2);

		boolean bindGovAgencyCode = false;

		if (govAgencyCode == null) {
			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GOVAGENCYCODE_1);
		}
		else if (govAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GOVAGENCYCODE_3);
		}
		else {
			bindGovAgencyCode = true;

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GOVAGENCYCODE_2);
		}

		boolean bindServiceCode = false;

		if (serviceCode == null) {
			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_SERVICECODE_1);
		}
		else if (serviceCode.equals("")) {
			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_SERVICECODE_3);
		}
		else {
			bindServiceCode = true;

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_SERVICECODE_2);
		}

		boolean bindDossierTemplateNo = false;

		if (dossierTemplateNo == null) {
			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERTEMPLATENO_1);
		}
		else if (dossierTemplateNo.equals("")) {
			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERTEMPLATENO_3);
		}
		else {
			bindDossierTemplateNo = true;

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERTEMPLATENO_2);
		}

		boolean bindDossierStatus = false;

		if (dossierStatus == null) {
			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERSTATUS_1);
		}
		else if (dossierStatus.equals("")) {
			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERSTATUS_3);
		}
		else {
			bindDossierStatus = true;

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERSTATUS_2);
		}

		query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_ORIGINALITY_2);

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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(groupId);

		if (bindGovAgencyCode) {
			qPos.add(govAgencyCode);
		}

		if (bindServiceCode) {
			qPos.add(serviceCode);
		}

		if (bindDossierTemplateNo) {
			qPos.add(dossierTemplateNo);
		}

		if (bindDossierStatus) {
			qPos.add(dossierStatus);
		}

		qPos.add(originality);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 */
	@Override
	public void removeByU_G_GAC_SC_DTNO_DS_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality) {
		for (Dossier dossier : findByU_G_GAC_SC_DTNO_DS_O(userId, groupId,
				govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
				originality, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param dossierTemplateNo the dossier template no
	 * @param dossierStatus the dossier status
	 * @param originality the originality
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByU_G_GAC_SC_DTNO_DS_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_G_GAC_SC_DTNO_DS_O;

		Object[] finderArgs = new Object[] {
				userId, groupId, govAgencyCode, serviceCode, dossierTemplateNo,
				dossierStatus, originality
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(8);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_USERID_2);

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GOVAGENCYCODE_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_SERVICECODE_2);
			}

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERTEMPLATENO_2);
			}

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERSTATUS_2);
			}

			query.append(_FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_ORIGINALITY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				qPos.add(originality);

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

	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_USERID_2 = "dossier.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GOVAGENCYCODE_1 =
		"dossier.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GOVAGENCYCODE_2 =
		"dossier.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_GOVAGENCYCODE_3 =
		"(dossier.govAgencyCode IS NULL OR dossier.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_SERVICECODE_1 =
		"dossier.serviceCode IS NULL AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_SERVICECODE_2 =
		"dossier.serviceCode = ? AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_SERVICECODE_3 =
		"(dossier.serviceCode IS NULL OR dossier.serviceCode = '') AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERTEMPLATENO_1 =
		"dossier.dossierTemplateNo IS NULL AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERTEMPLATENO_2 =
		"dossier.dossierTemplateNo = ? AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERTEMPLATENO_3 =
		"(dossier.dossierTemplateNo IS NULL OR dossier.dossierTemplateNo = '') AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERSTATUS_1 =
		"dossier.dossierStatus IS NULL AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERSTATUS_2 =
		"dossier.dossierStatus = ? AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_DOSSIERSTATUS_3 =
		"(dossier.dossierStatus IS NULL OR dossier.dossierStatus = '') AND ";
	private static final String _FINDER_COLUMN_U_G_GAC_SC_DTNO_DS_O_ORIGINALITY_2 =
		"dossier.originality = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_GDID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_GDID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_GDID = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_GDID",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossiers where groupId = &#63; and groupDossierId LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param groupDossierId the group dossier ID
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_GDID(long groupId, String groupDossierId) {
		return findByG_GDID(groupId, groupDossierId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and groupDossierId LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param groupDossierId the group dossier ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_GDID(long groupId, String groupDossierId,
		int start, int end) {
		return findByG_GDID(groupId, groupDossierId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and groupDossierId LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param groupDossierId the group dossier ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_GDID(long groupId, String groupDossierId,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByG_GDID(groupId, groupDossierId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and groupDossierId LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param groupDossierId the group dossier ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_GDID(long groupId, String groupDossierId,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_GDID;
		finderArgs = new Object[] {
				groupId, groupDossierId,
				
				start, end, orderByComparator
			};

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							!StringUtil.wildcardMatches(
								dossier.getGroupDossierId(), groupDossierId,
								'_', '%', '\\', true)) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_GDID_GROUPID_2);

			boolean bindGroupDossierId = false;

			if (groupDossierId == null) {
				query.append(_FINDER_COLUMN_G_GDID_GROUPDOSSIERID_1);
			}
			else if (groupDossierId.equals("")) {
				query.append(_FINDER_COLUMN_G_GDID_GROUPDOSSIERID_3);
			}
			else {
				bindGroupDossierId = true;

				query.append(_FINDER_COLUMN_G_GDID_GROUPDOSSIERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGroupDossierId) {
					qPos.add(groupDossierId);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and groupDossierId LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param groupDossierId the group dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_GDID_First(long groupId, String groupDossierId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_GDID_First(groupId, groupDossierId,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", groupDossierId=");
		msg.append(groupDossierId);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and groupDossierId LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param groupDossierId the group dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_GDID_First(long groupId, String groupDossierId,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_GDID(groupId, groupDossierId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and groupDossierId LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param groupDossierId the group dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_GDID_Last(long groupId, String groupDossierId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_GDID_Last(groupId, groupDossierId,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", groupDossierId=");
		msg.append(groupDossierId);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and groupDossierId LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param groupDossierId the group dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_GDID_Last(long groupId, String groupDossierId,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_GDID(groupId, groupDossierId);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_GDID(groupId, groupDossierId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and groupDossierId LIKE &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param groupDossierId the group dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_GDID_PrevAndNext(long dossierId, long groupId,
		String groupDossierId, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_GDID_PrevAndNext(session, dossier, groupId,
					groupDossierId, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByG_GDID_PrevAndNext(session, dossier, groupId,
					groupDossierId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByG_GDID_PrevAndNext(Session session, Dossier dossier,
		long groupId, String groupDossierId,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_GDID_GROUPID_2);

		boolean bindGroupDossierId = false;

		if (groupDossierId == null) {
			query.append(_FINDER_COLUMN_G_GDID_GROUPDOSSIERID_1);
		}
		else if (groupDossierId.equals("")) {
			query.append(_FINDER_COLUMN_G_GDID_GROUPDOSSIERID_3);
		}
		else {
			bindGroupDossierId = true;

			query.append(_FINDER_COLUMN_G_GDID_GROUPDOSSIERID_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindGroupDossierId) {
			qPos.add(groupDossierId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and groupDossierId LIKE &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param groupDossierId the group dossier ID
	 */
	@Override
	public void removeByG_GDID(long groupId, String groupDossierId) {
		for (Dossier dossier : findByG_GDID(groupId, groupDossierId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and groupDossierId LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param groupDossierId the group dossier ID
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_GDID(long groupId, String groupDossierId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_GDID;

		Object[] finderArgs = new Object[] { groupId, groupDossierId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_GDID_GROUPID_2);

			boolean bindGroupDossierId = false;

			if (groupDossierId == null) {
				query.append(_FINDER_COLUMN_G_GDID_GROUPDOSSIERID_1);
			}
			else if (groupDossierId.equals("")) {
				query.append(_FINDER_COLUMN_G_GDID_GROUPDOSSIERID_3);
			}
			else {
				bindGroupDossierId = true;

				query.append(_FINDER_COLUMN_G_GDID_GROUPDOSSIERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGroupDossierId) {
					qPos.add(groupDossierId);
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

	private static final String _FINDER_COLUMN_G_GDID_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_GDID_GROUPDOSSIERID_1 = "dossier.groupDossierId IS NULL";
	private static final String _FINDER_COLUMN_G_GDID_GROUPDOSSIERID_2 = "dossier.groupDossierId LIKE ?";
	private static final String _FINDER_COLUMN_G_GDID_GROUPDOSSIERID_3 = "(dossier.groupDossierId IS NULL OR dossier.groupDossierId LIKE '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UID_DS = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_UID_DS",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_DS =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_UID_DS",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.USERID_COLUMN_BITMASK |
			DossierModelImpl.DOSSIERSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_UID_DS = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_UID_DS",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the dossiers where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param dossierStatus the dossier status
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_UID_DS(long groupId, long userId,
		String dossierStatus) {
		return findByG_UID_DS(groupId, userId, dossierStatus,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_UID_DS(long groupId, long userId,
		String dossierStatus, int start, int end) {
		return findByG_UID_DS(groupId, userId, dossierStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_UID_DS(long groupId, long userId,
		String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByG_UID_DS(groupId, userId, dossierStatus, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param dossierStatus the dossier status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_UID_DS(long groupId, long userId,
		String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_DS;
			finderArgs = new Object[] { groupId, userId, dossierStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UID_DS;
			finderArgs = new Object[] {
					groupId, userId, dossierStatus,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							(userId != dossier.getUserId()) ||
							!Objects.equals(dossierStatus,
								dossier.getDossierStatus())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_UID_DS_GROUPID_2);

			query.append(_FINDER_COLUMN_G_UID_DS_USERID_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_G_UID_DS_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_G_UID_DS_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_G_UID_DS_DOSSIERSTATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_UID_DS_First(long groupId, long userId,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_UID_DS_First(groupId, userId, dossierStatus,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_UID_DS_First(long groupId, long userId,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_UID_DS(groupId, userId, dossierStatus, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_UID_DS_Last(long groupId, long userId,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_UID_DS_Last(groupId, userId, dossierStatus,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_UID_DS_Last(long groupId, long userId,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_UID_DS(groupId, userId, dossierStatus);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_UID_DS(groupId, userId, dossierStatus,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param dossierStatus the dossier status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_UID_DS_PrevAndNext(long dossierId, long groupId,
		long userId, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_UID_DS_PrevAndNext(session, dossier, groupId,
					userId, dossierStatus, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByG_UID_DS_PrevAndNext(session, dossier, groupId,
					userId, dossierStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByG_UID_DS_PrevAndNext(Session session,
		Dossier dossier, long groupId, long userId, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_UID_DS_GROUPID_2);

		query.append(_FINDER_COLUMN_G_UID_DS_USERID_2);

		boolean bindDossierStatus = false;

		if (dossierStatus == null) {
			query.append(_FINDER_COLUMN_G_UID_DS_DOSSIERSTATUS_1);
		}
		else if (dossierStatus.equals("")) {
			query.append(_FINDER_COLUMN_G_UID_DS_DOSSIERSTATUS_3);
		}
		else {
			bindDossierStatus = true;

			query.append(_FINDER_COLUMN_G_UID_DS_DOSSIERSTATUS_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (bindDossierStatus) {
			qPos.add(dossierStatus);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and userId = &#63; and dossierStatus = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param dossierStatus the dossier status
	 */
	@Override
	public void removeByG_UID_DS(long groupId, long userId, String dossierStatus) {
		for (Dossier dossier : findByG_UID_DS(groupId, userId, dossierStatus,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param dossierStatus the dossier status
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_UID_DS(long groupId, long userId, String dossierStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_UID_DS;

		Object[] finderArgs = new Object[] { groupId, userId, dossierStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_UID_DS_GROUPID_2);

			query.append(_FINDER_COLUMN_G_UID_DS_USERID_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_G_UID_DS_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_G_UID_DS_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_G_UID_DS_DOSSIERSTATUS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
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

	private static final String _FINDER_COLUMN_G_UID_DS_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_DS_USERID_2 = "dossier.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_DS_DOSSIERSTATUS_1 = "dossier.dossierStatus IS NULL";
	private static final String _FINDER_COLUMN_G_UID_DS_DOSSIERSTATUS_2 = "dossier.dossierStatus = ?";
	private static final String _FINDER_COLUMN_G_UID_DS_DOSSIERSTATUS_3 = "(dossier.dossierStatus IS NULL OR dossier.dossierStatus = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_VNP_STT =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_VNP_STT",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_VNP_STT =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_VNP_STT",
			new String[] { Long.class.getName(), Integer.class.getName() },
			DossierModelImpl.GROUPID_COLUMN_BITMASK |
			DossierModelImpl.VNPOSTALSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_VNP_STT = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_VNP_STT",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the dossiers where groupId = &#63; and vnpostalStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vnpostalStatus the vnpostal status
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByG_VNP_STT(long groupId, int vnpostalStatus) {
		return findByG_VNP_STT(groupId, vnpostalStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers where groupId = &#63; and vnpostalStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param vnpostalStatus the vnpostal status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_VNP_STT(long groupId, int vnpostalStatus,
		int start, int end) {
		return findByG_VNP_STT(groupId, vnpostalStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and vnpostalStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param vnpostalStatus the vnpostal status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_VNP_STT(long groupId, int vnpostalStatus,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return findByG_VNP_STT(groupId, vnpostalStatus, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where groupId = &#63; and vnpostalStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param vnpostalStatus the vnpostal status
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByG_VNP_STT(long groupId, int vnpostalStatus,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_VNP_STT;
			finderArgs = new Object[] { groupId, vnpostalStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_VNP_STT;
			finderArgs = new Object[] {
					groupId, vnpostalStatus,
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((groupId != dossier.getGroupId()) ||
							(vnpostalStatus != dossier.getVnpostalStatus())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_VNP_STT_GROUPID_2);

			query.append(_FINDER_COLUMN_G_VNP_STT_VNPOSTALSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(vnpostalStatus);

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where groupId = &#63; and vnpostalStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vnpostalStatus the vnpostal status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_VNP_STT_First(long groupId, int vnpostalStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_VNP_STT_First(groupId, vnpostalStatus,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", vnpostalStatus=");
		msg.append(vnpostalStatus);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where groupId = &#63; and vnpostalStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vnpostalStatus the vnpostal status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_VNP_STT_First(long groupId, int vnpostalStatus,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByG_VNP_STT(groupId, vnpostalStatus, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and vnpostalStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vnpostalStatus the vnpostal status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByG_VNP_STT_Last(long groupId, int vnpostalStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByG_VNP_STT_Last(groupId, vnpostalStatus,
				orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", vnpostalStatus=");
		msg.append(vnpostalStatus);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where groupId = &#63; and vnpostalStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vnpostalStatus the vnpostal status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByG_VNP_STT_Last(long groupId, int vnpostalStatus,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByG_VNP_STT(groupId, vnpostalStatus);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByG_VNP_STT(groupId, vnpostalStatus,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and vnpostalStatus = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param groupId the group ID
	 * @param vnpostalStatus the vnpostal status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByG_VNP_STT_PrevAndNext(long dossierId, long groupId,
		int vnpostalStatus, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByG_VNP_STT_PrevAndNext(session, dossier, groupId,
					vnpostalStatus, orderByComparator, true);

			array[1] = dossier;

			array[2] = getByG_VNP_STT_PrevAndNext(session, dossier, groupId,
					vnpostalStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Dossier getByG_VNP_STT_PrevAndNext(Session session,
		Dossier dossier, long groupId, int vnpostalStatus,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		query.append(_FINDER_COLUMN_G_VNP_STT_GROUPID_2);

		query.append(_FINDER_COLUMN_G_VNP_STT_VNPOSTALSTATUS_2);

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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(vnpostalStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where groupId = &#63; and vnpostalStatus = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param vnpostalStatus the vnpostal status
	 */
	@Override
	public void removeByG_VNP_STT(long groupId, int vnpostalStatus) {
		for (Dossier dossier : findByG_VNP_STT(groupId, vnpostalStatus,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where groupId = &#63; and vnpostalStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vnpostalStatus the vnpostal status
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByG_VNP_STT(long groupId, int vnpostalStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_VNP_STT;

		Object[] finderArgs = new Object[] { groupId, vnpostalStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_G_VNP_STT_GROUPID_2);

			query.append(_FINDER_COLUMN_G_VNP_STT_VNPOSTALSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(vnpostalStatus);

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

	private static final String _FINDER_COLUMN_G_VNP_STT_GROUPID_2 = "dossier.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_VNP_STT_VNPOSTALSTATUS_2 = "dossier.vnpostalStatus = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DC = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDC",
			new String[] { String.class.getName() },
			DossierModelImpl.DOSSIERCOUNTER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DC = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDC",
			new String[] { String.class.getName() });

	/**
	 * Returns the dossier where dossierCounter = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	 *
	 * @param dossierCounter the dossier counter
	 * @return the matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByDC(String dossierCounter)
		throws NoSuchDossierException {
		Dossier dossier = fetchByDC(dossierCounter);

		if (dossier == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossierCounter=");
			msg.append(dossierCounter);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierException(msg.toString());
		}

		return dossier;
	}

	/**
	 * Returns the dossier where dossierCounter = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossierCounter the dossier counter
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByDC(String dossierCounter) {
		return fetchByDC(dossierCounter, true);
	}

	/**
	 * Returns the dossier where dossierCounter = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossierCounter the dossier counter
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByDC(String dossierCounter, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossierCounter };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DC, finderArgs,
					this);
		}

		if (result instanceof Dossier) {
			Dossier dossier = (Dossier)result;

			if (!Objects.equals(dossierCounter, dossier.getDossierCounter())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			boolean bindDossierCounter = false;

			if (dossierCounter == null) {
				query.append(_FINDER_COLUMN_DC_DOSSIERCOUNTER_1);
			}
			else if (dossierCounter.equals("")) {
				query.append(_FINDER_COLUMN_DC_DOSSIERCOUNTER_3);
			}
			else {
				bindDossierCounter = true;

				query.append(_FINDER_COLUMN_DC_DOSSIERCOUNTER_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierCounter) {
					qPos.add(dossierCounter);
				}

				List<Dossier> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DC, finderArgs,
						list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierPersistenceImpl.fetchByDC(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Dossier dossier = list.get(0);

					result = dossier;

					cacheResult(dossier);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DC, finderArgs);

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
			return (Dossier)result;
		}
	}

	/**
	 * Removes the dossier where dossierCounter = &#63; from the database.
	 *
	 * @param dossierCounter the dossier counter
	 * @return the dossier that was removed
	 */
	@Override
	public Dossier removeByDC(String dossierCounter)
		throws NoSuchDossierException {
		Dossier dossier = findByDC(dossierCounter);

		return remove(dossier);
	}

	/**
	 * Returns the number of dossiers where dossierCounter = &#63;.
	 *
	 * @param dossierCounter the dossier counter
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByDC(String dossierCounter) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DC;

		Object[] finderArgs = new Object[] { dossierCounter };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			boolean bindDossierCounter = false;

			if (dossierCounter == null) {
				query.append(_FINDER_COLUMN_DC_DOSSIERCOUNTER_1);
			}
			else if (dossierCounter.equals("")) {
				query.append(_FINDER_COLUMN_DC_DOSSIERCOUNTER_3);
			}
			else {
				bindDossierCounter = true;

				query.append(_FINDER_COLUMN_DC_DOSSIERCOUNTER_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierCounter) {
					qPos.add(dossierCounter);
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

	private static final String _FINDER_COLUMN_DC_DOSSIERCOUNTER_1 = "dossier.dossierCounter IS NULL";
	private static final String _FINDER_COLUMN_DC_DOSSIERCOUNTER_2 = "dossier.dossierCounter = ?";
	private static final String _FINDER_COLUMN_DC_DOSSIERCOUNTER_3 = "(dossier.dossierCounter IS NULL OR dossier.dossierCounter = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_D_OR_D = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByD_OR_D",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_OR_D =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByD_OR_D",
			new String[] { Long.class.getName() },
			DossierModelImpl.DOSSIERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_D_OR_D = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByD_OR_D",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_D_OR_D = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByD_OR_D",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dossiers where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByD_OR_D(long dossierId) {
		return findByD_OR_D(dossierId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dossiers where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByD_OR_D(long dossierId, int start, int end) {
		return findByD_OR_D(dossierId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByD_OR_D(long dossierId, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByD_OR_D(dossierId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByD_OR_D(long dossierId, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_OR_D;
			finderArgs = new Object[] { dossierId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_D_OR_D;
			finderArgs = new Object[] { dossierId, start, end, orderByComparator };
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if ((dossierId != dossier.getDossierId())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_D_OR_D_DOSSIERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByD_OR_D_First(long dossierId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByD_OR_D_First(dossierId, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByD_OR_D_First(long dossierId,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByD_OR_D(dossierId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByD_OR_D_Last(long dossierId,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByD_OR_D_Last(dossierId, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByD_OR_D_Last(long dossierId,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByD_OR_D(dossierId);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByD_OR_D(dossierId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns all the dossiers where dossierId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierIds the dossier IDs
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByD_OR_D(long[] dossierIds) {
		return findByD_OR_D(dossierIds, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dossiers where dossierId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierIds the dossier IDs
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByD_OR_D(long[] dossierIds, int start, int end) {
		return findByD_OR_D(dossierIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where dossierId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierIds the dossier IDs
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByD_OR_D(long[] dossierIds, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByD_OR_D(dossierIds, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where dossierId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByD_OR_D(long[] dossierIds, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		if (dossierIds == null) {
			dossierIds = new long[0];
		}
		else if (dossierIds.length > 1) {
			dossierIds = ArrayUtil.unique(dossierIds);

			Arrays.sort(dossierIds);
		}

		if (dossierIds.length == 1) {
			return findByD_OR_D(dossierIds[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(dossierIds) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(dossierIds),
					
					start, end, orderByComparator
				};
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_D_OR_D,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if (!ArrayUtil.contains(dossierIds, dossier.getDossierId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			if (dossierIds.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_D_OR_D_DOSSIERID_7);

				query.append(StringUtil.merge(dossierIds));

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
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_D_OR_D,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_D_OR_D,
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
	 * Removes all the dossiers where dossierId = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 */
	@Override
	public void removeByD_OR_D(long dossierId) {
		for (Dossier dossier : findByD_OR_D(dossierId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByD_OR_D(long dossierId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_D_OR_D;

		Object[] finderArgs = new Object[] { dossierId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			query.append(_FINDER_COLUMN_D_OR_D_DOSSIERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	/**
	 * Returns the number of dossiers where dossierId = any &#63;.
	 *
	 * @param dossierIds the dossier IDs
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByD_OR_D(long[] dossierIds) {
		if (dossierIds == null) {
			dossierIds = new long[0];
		}
		else if (dossierIds.length > 1) {
			dossierIds = ArrayUtil.unique(dossierIds);

			Arrays.sort(dossierIds);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(dossierIds) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_D_OR_D,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			if (dossierIds.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_D_OR_D_DOSSIERID_7);

				query.append(StringUtil.merge(dossierIds));

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

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_D_OR_D,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_D_OR_D,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_D_OR_D_DOSSIERID_2 = "dossier.dossierId = ?";
	private static final String _FINDER_COLUMN_D_OR_D_DOSSIERID_7 = "dossier.dossierId IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NEW_DO_NO =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByNEW_DO_NO",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NEW_DO_NO =
		new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, DossierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByNEW_DO_NO",
			new String[] { String.class.getName() },
			DossierModelImpl.DOSSIERNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NEW_DO_NO = new FinderPath(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByNEW_DO_NO",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dossiers where dossierNo = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @return the matching dossiers
	 */
	@Override
	public List<Dossier> findByNEW_DO_NO(String dossierNo) {
		return findByNEW_DO_NO(dossierNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dossiers where dossierNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierNo the dossier no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNEW_DO_NO(String dossierNo, int start, int end) {
		return findByNEW_DO_NO(dossierNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers where dossierNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierNo the dossier no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNEW_DO_NO(String dossierNo, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findByNEW_DO_NO(dossierNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers where dossierNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierNo the dossier no
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossiers
	 */
	@Override
	public List<Dossier> findByNEW_DO_NO(String dossierNo, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NEW_DO_NO;
			finderArgs = new Object[] { dossierNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NEW_DO_NO;
			finderArgs = new Object[] { dossierNo, start, end, orderByComparator };
		}

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Dossier dossier : list) {
					if (!Objects.equals(dossierNo, dossier.getDossierNo())) {
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

			query.append(_SQL_SELECT_DOSSIER_WHERE);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_NEW_DO_NO_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_NEW_DO_NO_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_NEW_DO_NO_DOSSIERNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierNo) {
					qPos.add(dossierNo);
				}

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier in the ordered set where dossierNo = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByNEW_DO_NO_First(String dossierNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByNEW_DO_NO_First(dossierNo, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierNo=");
		msg.append(dossierNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the first dossier in the ordered set where dossierNo = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByNEW_DO_NO_First(String dossierNo,
		OrderByComparator<Dossier> orderByComparator) {
		List<Dossier> list = findByNEW_DO_NO(dossierNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier in the ordered set where dossierNo = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier
	 * @throws NoSuchDossierException if a matching dossier could not be found
	 */
	@Override
	public Dossier findByNEW_DO_NO_Last(String dossierNo,
		OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = fetchByNEW_DO_NO_Last(dossierNo, orderByComparator);

		if (dossier != null) {
			return dossier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierNo=");
		msg.append(dossierNo);

		msg.append("}");

		throw new NoSuchDossierException(msg.toString());
	}

	/**
	 * Returns the last dossier in the ordered set where dossierNo = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	 */
	@Override
	public Dossier fetchByNEW_DO_NO_Last(String dossierNo,
		OrderByComparator<Dossier> orderByComparator) {
		int count = countByNEW_DO_NO(dossierNo);

		if (count == 0) {
			return null;
		}

		List<Dossier> list = findByNEW_DO_NO(dossierNo, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossiers before and after the current dossier in the ordered set where dossierNo = &#63;.
	 *
	 * @param dossierId the primary key of the current dossier
	 * @param dossierNo the dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier[] findByNEW_DO_NO_PrevAndNext(long dossierId,
		String dossierNo, OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException {
		Dossier dossier = findByPrimaryKey(dossierId);

		Session session = null;

		try {
			session = openSession();

			Dossier[] array = new DossierImpl[3];

			array[0] = getByNEW_DO_NO_PrevAndNext(session, dossier, dossierNo,
					orderByComparator, true);

			array[1] = dossier;

			array[2] = getByNEW_DO_NO_PrevAndNext(session, dossier, dossierNo,
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

	protected Dossier getByNEW_DO_NO_PrevAndNext(Session session,
		Dossier dossier, String dossierNo,
		OrderByComparator<Dossier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIER_WHERE);

		boolean bindDossierNo = false;

		if (dossierNo == null) {
			query.append(_FINDER_COLUMN_NEW_DO_NO_DOSSIERNO_1);
		}
		else if (dossierNo.equals("")) {
			query.append(_FINDER_COLUMN_NEW_DO_NO_DOSSIERNO_3);
		}
		else {
			bindDossierNo = true;

			query.append(_FINDER_COLUMN_NEW_DO_NO_DOSSIERNO_2);
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
			query.append(DossierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindDossierNo) {
			qPos.add(dossierNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Dossier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossiers where dossierNo = &#63; from the database.
	 *
	 * @param dossierNo the dossier no
	 */
	@Override
	public void removeByNEW_DO_NO(String dossierNo) {
		for (Dossier dossier : findByNEW_DO_NO(dossierNo, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers where dossierNo = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @return the number of matching dossiers
	 */
	@Override
	public int countByNEW_DO_NO(String dossierNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NEW_DO_NO;

		Object[] finderArgs = new Object[] { dossierNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIER_WHERE);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_NEW_DO_NO_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_NEW_DO_NO_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_NEW_DO_NO_DOSSIERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierNo) {
					qPos.add(dossierNo);
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

	private static final String _FINDER_COLUMN_NEW_DO_NO_DOSSIERNO_1 = "dossier.dossierNo IS NULL";
	private static final String _FINDER_COLUMN_NEW_DO_NO_DOSSIERNO_2 = "dossier.dossierNo = ?";
	private static final String _FINDER_COLUMN_NEW_DO_NO_DOSSIERNO_3 = "(dossier.dossierNo IS NULL OR dossier.dossierNo = '')";

	public DossierPersistenceImpl() {
		setModelClass(Dossier.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("password", "password_");
			dbColumnNames.put("online", "online_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the dossier in the entity cache if it is enabled.
	 *
	 * @param dossier the dossier
	 */
	@Override
	public void cacheResult(Dossier dossier) {
		entityCache.putResult(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierImpl.class, dossier.getPrimaryKey(), dossier);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dossier.getUuid(), dossier.getGroupId() }, dossier);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_REF,
			new Object[] { dossier.getGroupId(), dossier.getReferenceUid() },
			dossier);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_GOV_DID,
			new Object[] {
				dossier.getGroupId(), dossier.getGovAgencyCode(),
				dossier.getServiceCode(), dossier.getDossierId()
			}, dossier);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_DN,
			new Object[] { dossier.getGroupId(), dossier.getDossierNo() },
			dossier);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DO_NO,
			new Object[] { dossier.getDossierNo() }, dossier);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DO_NO_GROUP,
			new Object[] { dossier.getDossierNo(), dossier.getGroupId() },
			dossier);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DO_POST_SEND_GROUP,
			new Object[] { dossier.getPostalCodeSend(), dossier.getGroupId() },
			dossier);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DO_POST_RECEIVED_GROUP,
			new Object[] { dossier.getPostalCodeReceived(), dossier.getGroupId() },
			dossier);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_ODID,
			new Object[] {
				dossier.getGroupId(), dossier.getApplicantIdNo(),
				dossier.getServiceCode(), dossier.getGovAgencyCode(),
				dossier.getDossierTemplateNo(), dossier.getOriginDossierId()
			}, dossier);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_SN_ODID,
			new Object[] {
				dossier.getGroupId(), dossier.getApplicantIdNo(),
				dossier.getServiceCode(), dossier.getGovAgencyCode(),
				dossier.getDossierTemplateNo(), dossier.getOriginDossierId(),
				dossier.getServerNo()
			}, dossier);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DC,
			new Object[] { dossier.getDossierCounter() }, dossier);

		dossier.resetOriginalValues();
	}

	/**
	 * Caches the dossiers in the entity cache if it is enabled.
	 *
	 * @param dossiers the dossiers
	 */
	@Override
	public void cacheResult(List<Dossier> dossiers) {
		for (Dossier dossier : dossiers) {
			if (entityCache.getResult(DossierModelImpl.ENTITY_CACHE_ENABLED,
						DossierImpl.class, dossier.getPrimaryKey()) == null) {
				cacheResult(dossier);
			}
			else {
				dossier.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dossiers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DossierImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dossier.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Dossier dossier) {
		entityCache.removeResult(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierImpl.class, dossier.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DossierModelImpl)dossier, true);
	}

	@Override
	public void clearCache(List<Dossier> dossiers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Dossier dossier : dossiers) {
			entityCache.removeResult(DossierModelImpl.ENTITY_CACHE_ENABLED,
				DossierImpl.class, dossier.getPrimaryKey());

			clearUniqueFindersCache((DossierModelImpl)dossier, true);
		}
	}

	protected void cacheUniqueFindersCache(DossierModelImpl dossierModelImpl) {
		Object[] args = new Object[] {
				dossierModelImpl.getUuid(), dossierModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dossierModelImpl, false);

		args = new Object[] {
				dossierModelImpl.getGroupId(),
				dossierModelImpl.getReferenceUid()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_REF, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_REF, args,
			dossierModelImpl, false);

		args = new Object[] {
				dossierModelImpl.getGroupId(),
				dossierModelImpl.getGovAgencyCode(),
				dossierModelImpl.getServiceCode(),
				dossierModelImpl.getDossierId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_GOV_DID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_GOV_DID, args,
			dossierModelImpl, false);

		args = new Object[] {
				dossierModelImpl.getGroupId(), dossierModelImpl.getDossierNo()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_DN, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_DN, args,
			dossierModelImpl, false);

		args = new Object[] { dossierModelImpl.getDossierNo() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_DO_NO, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DO_NO, args,
			dossierModelImpl, false);

		args = new Object[] {
				dossierModelImpl.getDossierNo(), dossierModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_DO_NO_GROUP, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DO_NO_GROUP, args,
			dossierModelImpl, false);

		args = new Object[] {
				dossierModelImpl.getPostalCodeSend(),
				dossierModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_DO_POST_SEND_GROUP, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DO_POST_SEND_GROUP, args,
			dossierModelImpl, false);

		args = new Object[] {
				dossierModelImpl.getPostalCodeReceived(),
				dossierModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_DO_POST_RECEIVED_GROUP,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DO_POST_RECEIVED_GROUP,
			args, dossierModelImpl, false);

		args = new Object[] {
				dossierModelImpl.getGroupId(),
				dossierModelImpl.getApplicantIdNo(),
				dossierModelImpl.getServiceCode(),
				dossierModelImpl.getGovAgencyCode(),
				dossierModelImpl.getDossierTemplateNo(),
				dossierModelImpl.getOriginDossierId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_AN_SC_GAC_DTNO_ODID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_ODID, args,
			dossierModelImpl, false);

		args = new Object[] {
				dossierModelImpl.getGroupId(),
				dossierModelImpl.getApplicantIdNo(),
				dossierModelImpl.getServiceCode(),
				dossierModelImpl.getGovAgencyCode(),
				dossierModelImpl.getDossierTemplateNo(),
				dossierModelImpl.getOriginDossierId(),
				dossierModelImpl.getServerNo()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_AN_SC_GAC_DTNO_SN_ODID,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_SN_ODID,
			args, dossierModelImpl, false);

		args = new Object[] { dossierModelImpl.getDossierCounter() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_DC, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DC, args, dossierModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(DossierModelImpl dossierModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierModelImpl.getUuid(), dossierModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dossierModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierModelImpl.getOriginalUuid(),
					dossierModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getReferenceUid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REF, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_REF, args);
		}

		if ((dossierModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_REF.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierModelImpl.getOriginalGroupId(),
					dossierModelImpl.getOriginalReferenceUid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REF, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_REF, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getGovAgencyCode(),
					dossierModelImpl.getServiceCode(),
					dossierModelImpl.getDossierId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_GOV_DID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_GOV_DID, args);
		}

		if ((dossierModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_GOV_DID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierModelImpl.getOriginalGroupId(),
					dossierModelImpl.getOriginalGovAgencyCode(),
					dossierModelImpl.getOriginalServiceCode(),
					dossierModelImpl.getOriginalDossierId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_GOV_DID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_GOV_DID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getDossierNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DN, args);
		}

		if ((dossierModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_DN.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierModelImpl.getOriginalGroupId(),
					dossierModelImpl.getOriginalDossierNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DN, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { dossierModelImpl.getDossierNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DO_NO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DO_NO, args);
		}

		if ((dossierModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DO_NO.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { dossierModelImpl.getOriginalDossierNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DO_NO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DO_NO, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierModelImpl.getDossierNo(),
					dossierModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DO_NO_GROUP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DO_NO_GROUP, args);
		}

		if ((dossierModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DO_NO_GROUP.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierModelImpl.getOriginalDossierNo(),
					dossierModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DO_NO_GROUP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DO_NO_GROUP, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierModelImpl.getPostalCodeSend(),
					dossierModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DO_POST_SEND_GROUP,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DO_POST_SEND_GROUP,
				args);
		}

		if ((dossierModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DO_POST_SEND_GROUP.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierModelImpl.getOriginalPostalCodeSend(),
					dossierModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DO_POST_SEND_GROUP,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DO_POST_SEND_GROUP,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierModelImpl.getPostalCodeReceived(),
					dossierModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DO_POST_RECEIVED_GROUP,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DO_POST_RECEIVED_GROUP,
				args);
		}

		if ((dossierModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DO_POST_RECEIVED_GROUP.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierModelImpl.getOriginalPostalCodeReceived(),
					dossierModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DO_POST_RECEIVED_GROUP,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DO_POST_RECEIVED_GROUP,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getApplicantIdNo(),
					dossierModelImpl.getServiceCode(),
					dossierModelImpl.getGovAgencyCode(),
					dossierModelImpl.getDossierTemplateNo(),
					dossierModelImpl.getOriginDossierId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_AN_SC_GAC_DTNO_ODID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_ODID,
				args);
		}

		if ((dossierModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_ODID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierModelImpl.getOriginalGroupId(),
					dossierModelImpl.getOriginalApplicantIdNo(),
					dossierModelImpl.getOriginalServiceCode(),
					dossierModelImpl.getOriginalGovAgencyCode(),
					dossierModelImpl.getOriginalDossierTemplateNo(),
					dossierModelImpl.getOriginalOriginDossierId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_AN_SC_GAC_DTNO_ODID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_ODID,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getApplicantIdNo(),
					dossierModelImpl.getServiceCode(),
					dossierModelImpl.getGovAgencyCode(),
					dossierModelImpl.getDossierTemplateNo(),
					dossierModelImpl.getOriginDossierId(),
					dossierModelImpl.getServerNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_AN_SC_GAC_DTNO_SN_ODID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_SN_ODID,
				args);
		}

		if ((dossierModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_SN_ODID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierModelImpl.getOriginalGroupId(),
					dossierModelImpl.getOriginalApplicantIdNo(),
					dossierModelImpl.getOriginalServiceCode(),
					dossierModelImpl.getOriginalGovAgencyCode(),
					dossierModelImpl.getOriginalDossierTemplateNo(),
					dossierModelImpl.getOriginalOriginDossierId(),
					dossierModelImpl.getOriginalServerNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_AN_SC_GAC_DTNO_SN_ODID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_AN_SC_GAC_DTNO_SN_ODID,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { dossierModelImpl.getDossierCounter() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DC, args);
		}

		if ((dossierModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DC.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierModelImpl.getOriginalDossierCounter()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DC, args);
		}
	}

	/**
	 * Creates a new dossier with the primary key. Does not add the dossier to the database.
	 *
	 * @param dossierId the primary key for the new dossier
	 * @return the new dossier
	 */
	@Override
	public Dossier create(long dossierId) {
		Dossier dossier = new DossierImpl();

		dossier.setNew(true);
		dossier.setPrimaryKey(dossierId);

		String uuid = PortalUUIDUtil.generate();

		dossier.setUuid(uuid);

		dossier.setCompanyId(companyProvider.getCompanyId());

		return dossier;
	}

	/**
	 * Removes the dossier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dossierId the primary key of the dossier
	 * @return the dossier that was removed
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier remove(long dossierId) throws NoSuchDossierException {
		return remove((Serializable)dossierId);
	}

	/**
	 * Removes the dossier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dossier
	 * @return the dossier that was removed
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier remove(Serializable primaryKey)
		throws NoSuchDossierException {
		Session session = null;

		try {
			session = openSession();

			Dossier dossier = (Dossier)session.get(DossierImpl.class, primaryKey);

			if (dossier == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDossierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dossier);
		}
		catch (NoSuchDossierException nsee) {
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
	protected Dossier removeImpl(Dossier dossier) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dossier)) {
				dossier = (Dossier)session.get(DossierImpl.class,
						dossier.getPrimaryKeyObj());
			}

			if (dossier != null) {
				session.delete(dossier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dossier != null) {
			clearCache(dossier);
		}

		return dossier;
	}

	@Override
	public Dossier updateImpl(Dossier dossier) {
		boolean isNew = dossier.isNew();

		if (!(dossier instanceof DossierModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dossier.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dossier);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dossier proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Dossier implementation " +
				dossier.getClass());
		}

		DossierModelImpl dossierModelImpl = (DossierModelImpl)dossier;

		if (Validator.isNull(dossier.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dossier.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dossier.getCreateDate() == null)) {
			if (serviceContext == null) {
				dossier.setCreateDate(now);
			}
			else {
				dossier.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dossierModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dossier.setModifiedDate(now);
			}
			else {
				dossier.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dossier.isNew()) {
				session.save(dossier);

				dossier.setNew(false);
			}
			else {
				dossier = (Dossier)session.merge(dossier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DossierModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dossierModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					dossierModelImpl.getUuid(), dossierModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					dossierModelImpl.getGroupId(), dossierModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID,
				args);

			args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getServiceCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SC,
				args);

			args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getServiceCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_GAC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_GAC,
				args);

			args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getDossierTemplateNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DTN, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DTN,
				args);

			args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getDossierId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID,
				args);

			args = new Object[] {
					dossierModelImpl.getOriginality(),
					dossierModelImpl.getDossierStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_OG_DS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_OG_DS,
				args);

			args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getGovAgencyCode(),
					dossierModelImpl.getServiceCode(),
					dossierModelImpl.getDossierTemplateNo(),
					dossierModelImpl.getDossierStatus(),
					dossierModelImpl.getApplicantIdType(),
					dossierModelImpl.getOriginality()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_GC_SC_DTN_DS_APP_ORI,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_GC_SC_DTN_DS_APP_ORI,
				args);

			args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getOriginDossierId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_O_DID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_O_DID,
				args);

			args = new Object[] { dossierModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
				args);

			args = new Object[] {
					dossierModelImpl.getDossierNo(),
					dossierModelImpl.getApplicantIdNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DN_AN, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DN_AN,
				args);

			args = new Object[] { dossierModelImpl.getViaPostal() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_VIAPOSTAL, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIAPOSTAL,
				args);

			args = new Object[] {
					dossierModelImpl.getUserId(), dossierModelImpl.getGroupId(),
					dossierModelImpl.getGovAgencyCode(),
					dossierModelImpl.getServiceCode(),
					dossierModelImpl.getDossierActionId(),
					dossierModelImpl.getOriginality()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_G_GAC_SC_DTNO_DAI_O,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DAI_O,
				args);

			args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getApplicantIdNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_AN, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_AN,
				args);

			args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getApplicantIdNo(),
					dossierModelImpl.getDossierStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_AN_DS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_AN_DS,
				args);

			args = new Object[] { dossierModelImpl.getOriginality() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_O, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O,
				args);

			args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getProcessNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_PNO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_PNO,
				args);

			args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getOriginDossierNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_ORI_NO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_ORI_NO,
				args);

			args = new Object[] { dossierModelImpl.getOriginDossierNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ORIGIN_NO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORIGIN_NO,
				args);

			args = new Object[] {
					dossierModelImpl.getUserId(), dossierModelImpl.getGroupId(),
					dossierModelImpl.getGovAgencyCode(),
					dossierModelImpl.getServiceCode(),
					dossierModelImpl.getDossierTemplateNo(),
					dossierModelImpl.getDossierStatus(),
					dossierModelImpl.getOriginality()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_G_GAC_SC_DTNO_DS_O,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DS_O,
				args);

			args = new Object[] {
					dossierModelImpl.getGroupId(), dossierModelImpl.getUserId(),
					dossierModelImpl.getDossierStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_DS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_DS,
				args);

			args = new Object[] {
					dossierModelImpl.getGroupId(),
					dossierModelImpl.getVnpostalStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_VNP_STT, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_VNP_STT,
				args);

			args = new Object[] { dossierModelImpl.getDossierId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_D_OR_D, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_OR_D,
				args);

			args = new Object[] { dossierModelImpl.getDossierNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NEW_DO_NO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NEW_DO_NO,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { dossierModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dossierModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalUuid(),
						dossierModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dossierModelImpl.getUuid(),
						dossierModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID,
					args);

				args = new Object[] {
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalServiceCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SC,
					args);

				args = new Object[] {
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getServiceCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SC,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_GAC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalServiceCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_GAC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_GAC,
					args);

				args = new Object[] {
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getServiceCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_GAC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_GAC,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DTN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalDossierTemplateNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DTN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DTN,
					args);

				args = new Object[] {
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getDossierTemplateNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DTN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DTN,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID,
					args);

				args = new Object[] {
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_OG_DS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalOriginality(),
						dossierModelImpl.getOriginalDossierStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_OG_DS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_OG_DS,
					args);

				args = new Object[] {
						dossierModelImpl.getOriginality(),
						dossierModelImpl.getDossierStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_OG_DS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_OG_DS,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_GC_SC_DTN_DS_APP_ORI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalGovAgencyCode(),
						dossierModelImpl.getOriginalServiceCode(),
						dossierModelImpl.getOriginalDossierTemplateNo(),
						dossierModelImpl.getOriginalDossierStatus(),
						dossierModelImpl.getOriginalApplicantIdType(),
						dossierModelImpl.getOriginalOriginality()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_GC_SC_DTN_DS_APP_ORI,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_GC_SC_DTN_DS_APP_ORI,
					args);

				args = new Object[] {
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getGovAgencyCode(),
						dossierModelImpl.getServiceCode(),
						dossierModelImpl.getDossierTemplateNo(),
						dossierModelImpl.getDossierStatus(),
						dossierModelImpl.getApplicantIdType(),
						dossierModelImpl.getOriginality()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_GC_SC_DTN_DS_APP_ORI,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_GC_SC_DTN_DS_APP_ORI,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_O_DID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalOriginDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_O_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_O_DID,
					args);

				args = new Object[] {
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getOriginDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_O_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_O_DID,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
					args);

				args = new Object[] { dossierModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DN_AN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalDossierNo(),
						dossierModelImpl.getOriginalApplicantIdNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DN_AN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DN_AN,
					args);

				args = new Object[] {
						dossierModelImpl.getDossierNo(),
						dossierModelImpl.getApplicantIdNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DN_AN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DN_AN,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIAPOSTAL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalViaPostal()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_VIAPOSTAL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIAPOSTAL,
					args);

				args = new Object[] { dossierModelImpl.getViaPostal() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_VIAPOSTAL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VIAPOSTAL,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DAI_O.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalUserId(),
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalGovAgencyCode(),
						dossierModelImpl.getOriginalServiceCode(),
						dossierModelImpl.getOriginalDossierActionId(),
						dossierModelImpl.getOriginalOriginality()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_G_GAC_SC_DTNO_DAI_O,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DAI_O,
					args);

				args = new Object[] {
						dossierModelImpl.getUserId(),
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getGovAgencyCode(),
						dossierModelImpl.getServiceCode(),
						dossierModelImpl.getDossierActionId(),
						dossierModelImpl.getOriginality()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_G_GAC_SC_DTNO_DAI_O,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DAI_O,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_AN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalApplicantIdNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_AN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_AN,
					args);

				args = new Object[] {
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getApplicantIdNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_AN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_AN,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_AN_DS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalApplicantIdNo(),
						dossierModelImpl.getOriginalDossierStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_AN_DS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_AN_DS,
					args);

				args = new Object[] {
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getApplicantIdNo(),
						dossierModelImpl.getDossierStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_AN_DS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_AN_DS,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalOriginality()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_O, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O,
					args);

				args = new Object[] { dossierModelImpl.getOriginality() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_O, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_PNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalProcessNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_PNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_PNO,
					args);

				args = new Object[] {
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getProcessNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_PNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_PNO,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_ORI_NO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalOriginDossierNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_ORI_NO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_ORI_NO,
					args);

				args = new Object[] {
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getOriginDossierNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_ORI_NO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_ORI_NO,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORIGIN_NO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalOriginDossierNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ORIGIN_NO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORIGIN_NO,
					args);

				args = new Object[] { dossierModelImpl.getOriginDossierNo() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ORIGIN_NO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORIGIN_NO,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DS_O.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalUserId(),
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalGovAgencyCode(),
						dossierModelImpl.getOriginalServiceCode(),
						dossierModelImpl.getOriginalDossierTemplateNo(),
						dossierModelImpl.getOriginalDossierStatus(),
						dossierModelImpl.getOriginalOriginality()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_G_GAC_SC_DTNO_DS_O,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DS_O,
					args);

				args = new Object[] {
						dossierModelImpl.getUserId(),
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getGovAgencyCode(),
						dossierModelImpl.getServiceCode(),
						dossierModelImpl.getDossierTemplateNo(),
						dossierModelImpl.getDossierStatus(),
						dossierModelImpl.getOriginality()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_G_GAC_SC_DTNO_DS_O,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_G_GAC_SC_DTNO_DS_O,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_DS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalUserId(),
						dossierModelImpl.getOriginalDossierStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_DS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_DS,
					args);

				args = new Object[] {
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getUserId(),
						dossierModelImpl.getDossierStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_DS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_DS,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_VNP_STT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalGroupId(),
						dossierModelImpl.getOriginalVnpostalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_VNP_STT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_VNP_STT,
					args);

				args = new Object[] {
						dossierModelImpl.getGroupId(),
						dossierModelImpl.getVnpostalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_VNP_STT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_VNP_STT,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_OR_D.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_D_OR_D, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_OR_D,
					args);

				args = new Object[] { dossierModelImpl.getDossierId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_D_OR_D, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_OR_D,
					args);
			}

			if ((dossierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NEW_DO_NO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierModelImpl.getOriginalDossierNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_NEW_DO_NO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NEW_DO_NO,
					args);

				args = new Object[] { dossierModelImpl.getDossierNo() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_NEW_DO_NO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NEW_DO_NO,
					args);
			}
		}

		entityCache.putResult(DossierModelImpl.ENTITY_CACHE_ENABLED,
			DossierImpl.class, dossier.getPrimaryKey(), dossier, false);

		clearUniqueFindersCache(dossierModelImpl, false);
		cacheUniqueFindersCache(dossierModelImpl);

		dossier.resetOriginalValues();

		return dossier;
	}

	/**
	 * Returns the dossier with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier
	 * @return the dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDossierException {
		Dossier dossier = fetchByPrimaryKey(primaryKey);

		if (dossier == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDossierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dossier;
	}

	/**
	 * Returns the dossier with the primary key or throws a {@link NoSuchDossierException} if it could not be found.
	 *
	 * @param dossierId the primary key of the dossier
	 * @return the dossier
	 * @throws NoSuchDossierException if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier findByPrimaryKey(long dossierId)
		throws NoSuchDossierException {
		return findByPrimaryKey((Serializable)dossierId);
	}

	/**
	 * Returns the dossier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier
	 * @return the dossier, or <code>null</code> if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DossierModelImpl.ENTITY_CACHE_ENABLED,
				DossierImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Dossier dossier = (Dossier)serializable;

		if (dossier == null) {
			Session session = null;

			try {
				session = openSession();

				dossier = (Dossier)session.get(DossierImpl.class, primaryKey);

				if (dossier != null) {
					cacheResult(dossier);
				}
				else {
					entityCache.putResult(DossierModelImpl.ENTITY_CACHE_ENABLED,
						DossierImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DossierModelImpl.ENTITY_CACHE_ENABLED,
					DossierImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dossier;
	}

	/**
	 * Returns the dossier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dossierId the primary key of the dossier
	 * @return the dossier, or <code>null</code> if a dossier with the primary key could not be found
	 */
	@Override
	public Dossier fetchByPrimaryKey(long dossierId) {
		return fetchByPrimaryKey((Serializable)dossierId);
	}

	@Override
	public Map<Serializable, Dossier> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Dossier> map = new HashMap<Serializable, Dossier>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Dossier dossier = fetchByPrimaryKey(primaryKey);

			if (dossier != null) {
				map.put(primaryKey, dossier);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DossierModelImpl.ENTITY_CACHE_ENABLED,
					DossierImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Dossier)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DOSSIER_WHERE_PKS_IN);

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

			for (Dossier dossier : (List<Dossier>)q.list()) {
				map.put(dossier.getPrimaryKeyObj(), dossier);

				cacheResult(dossier);

				uncachedPrimaryKeys.remove(dossier.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DossierModelImpl.ENTITY_CACHE_ENABLED,
					DossierImpl.class, primaryKey, nullModel);
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
	 * Returns all the dossiers.
	 *
	 * @return the dossiers
	 */
	@Override
	public List<Dossier> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @return the range of dossiers
	 */
	@Override
	public List<Dossier> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dossiers
	 */
	@Override
	public List<Dossier> findAll(int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossiers
	 * @param end the upper bound of the range of dossiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dossiers
	 */
	@Override
	public List<Dossier> findAll(int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
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

		List<Dossier> list = null;

		if (retrieveFromCache) {
			list = (List<Dossier>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOSSIER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOSSIER;

				if (pagination) {
					sql = sql.concat(DossierModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Dossier>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the dossiers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Dossier dossier : findAll()) {
			remove(dossier);
		}
	}

	/**
	 * Returns the number of dossiers.
	 *
	 * @return the number of dossiers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOSSIER);

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
		return DossierModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dossier persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DossierImpl.class.getName());
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

	private static final String _SQL_SELECT_DOSSIER = "SELECT dossier FROM Dossier dossier";
	private static final String _SQL_SELECT_DOSSIER_WHERE_PKS_IN = "SELECT dossier FROM Dossier dossier WHERE dossierId IN (";
	private static final String _SQL_SELECT_DOSSIER_WHERE = "SELECT dossier FROM Dossier dossier WHERE ";
	private static final String _SQL_COUNT_DOSSIER = "SELECT COUNT(dossier) FROM Dossier dossier";
	private static final String _SQL_COUNT_DOSSIER_WHERE = "SELECT COUNT(dossier) FROM Dossier dossier WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dossier.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Dossier exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Dossier exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DossierPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "password", "online"
			});
}