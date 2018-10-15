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

import org.opencps.dossiermgt.exception.NoSuchDossierException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.impl.DossierImpl;
import org.opencps.dossiermgt.model.impl.DossierModelImpl;
import org.opencps.dossiermgt.service.persistence.DossierPersistence;

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

	private static final String _FINDER_COLUMN_NOTO_DS_ORIGINALITY_2 = "dossier.originality != ? AND ";
	private static final String _FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_1 = "dossier.dossierStatus IS NULL";
	private static final String _FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_2 = "dossier.dossierStatus = ?";
	private static final String _FINDER_COLUMN_NOTO_DS_DOSSIERSTATUS_3 = "(dossier.dossierStatus IS NULL OR dossier.dossierStatus = '')";
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