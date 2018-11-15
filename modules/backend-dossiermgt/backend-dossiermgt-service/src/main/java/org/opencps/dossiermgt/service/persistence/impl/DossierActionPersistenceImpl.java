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

import org.opencps.dossiermgt.exception.NoSuchDossierActionException;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.impl.DossierActionImpl;
import org.opencps.dossiermgt.model.impl.DossierActionModelImpl;
import org.opencps.dossiermgt.service.persistence.DossierActionPersistence;

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
 * The persistence implementation for the dossier action service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierActionPersistence
 * @see org.opencps.dossiermgt.service.persistence.DossierActionUtil
 * @generated
 */
@ProviderType
public class DossierActionPersistenceImpl extends BasePersistenceImpl<DossierAction>
	implements DossierActionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DossierActionUtil} to access the dossier action persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DossierActionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			DossierActionModelImpl.UUID_COLUMN_BITMASK |
			DossierActionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dossier actions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dossier actions
	 */
	@Override
	public List<DossierAction> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier actions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @return the range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier actions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier actions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
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

		List<DossierAction> list = null;

		if (retrieveFromCache) {
			list = (List<DossierAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierAction dossierAction : list) {
					if (!Objects.equals(uuid, dossierAction.getUuid())) {
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

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

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
				query.append(DossierActionModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier action in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByUuid_First(String uuid,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByUuid_First(uuid, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the first dossier action in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByUuid_First(String uuid,
		OrderByComparator<DossierAction> orderByComparator) {
		List<DossierAction> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByUuid_Last(String uuid,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByUuid_Last(uuid, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the last dossier action in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByUuid_Last(String uuid,
		OrderByComparator<DossierAction> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DossierAction> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier actions before and after the current dossier action in the ordered set where uuid = &#63;.
	 *
	 * @param dossierActionId the primary key of the current dossier action
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction[] findByUuid_PrevAndNext(long dossierActionId,
		String uuid, OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByPrimaryKey(dossierActionId);

		Session session = null;

		try {
			session = openSession();

			DossierAction[] array = new DossierActionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dossierAction, uuid,
					orderByComparator, true);

			array[1] = dossierAction;

			array[2] = getByUuid_PrevAndNext(session, dossierAction, uuid,
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

	protected DossierAction getByUuid_PrevAndNext(Session session,
		DossierAction dossierAction, String uuid,
		OrderByComparator<DossierAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

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
			query.append(DossierActionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier actions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DossierAction dossierAction : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossierAction);
		}
	}

	/**
	 * Returns the number of dossier actions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dossierAction.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dossierAction.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dossierAction.uuid IS NULL OR dossierAction.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierActionModelImpl.UUID_COLUMN_BITMASK |
			DossierActionModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier action where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierActionException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByUUID_G(uuid, groupId);

		if (dossierAction == null) {
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

			throw new NoSuchDossierActionException(msg.toString());
		}

		return dossierAction;
	}

	/**
	 * Returns the dossier action where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dossier action where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DossierAction) {
			DossierAction dossierAction = (DossierAction)result;

			if (!Objects.equals(uuid, dossierAction.getUuid()) ||
					(groupId != dossierAction.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

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

				List<DossierAction> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DossierAction dossierAction = list.get(0);

					result = dossierAction;

					cacheResult(dossierAction);
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
			return (DossierAction)result;
		}
	}

	/**
	 * Removes the dossier action where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dossier action that was removed
	 */
	@Override
	public DossierAction removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByUUID_G(uuid, groupId);

		return remove(dossierAction);
	}

	/**
	 * Returns the number of dossier actions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dossierAction.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dossierAction.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dossierAction.uuid IS NULL OR dossierAction.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dossierAction.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierActionModelImpl.UUID_COLUMN_BITMASK |
			DossierActionModelImpl.COMPANYID_COLUMN_BITMASK |
			DossierActionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dossier actions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dossier actions
	 */
	@Override
	public List<DossierAction> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier actions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @return the range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier actions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierAction> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier actions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierAction> orderByComparator,
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

		List<DossierAction> list = null;

		if (retrieveFromCache) {
			list = (List<DossierAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierAction dossierAction : list) {
					if (!Objects.equals(uuid, dossierAction.getUuid()) ||
							(companyId != dossierAction.getCompanyId())) {
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

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

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
				query.append(DossierActionModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier action in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the first dossier action in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierAction> orderByComparator) {
		List<DossierAction> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the last dossier action in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierAction> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DossierAction> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier actions before and after the current dossier action in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dossierActionId the primary key of the current dossier action
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction[] findByUuid_C_PrevAndNext(long dossierActionId,
		String uuid, long companyId,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByPrimaryKey(dossierActionId);

		Session session = null;

		try {
			session = openSession();

			DossierAction[] array = new DossierActionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dossierAction, uuid,
					companyId, orderByComparator, true);

			array[1] = dossierAction;

			array[2] = getByUuid_C_PrevAndNext(session, dossierAction, uuid,
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

	protected DossierAction getByUuid_C_PrevAndNext(Session session,
		DossierAction dossierAction, String uuid, long companyId,
		OrderByComparator<DossierAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

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
			query.append(DossierActionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier actions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DossierAction dossierAction : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierAction);
		}
	}

	/**
	 * Returns the number of dossier actions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dossierAction.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dossierAction.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dossierAction.uuid IS NULL OR dossierAction.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dossierAction.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DID_DPG = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByDID_DPG",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			DossierActionModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierActionModelImpl.PENDING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_DPG = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_DPG",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns the dossier action where dossierId = &#63; and pending = &#63; or throws a {@link NoSuchDossierActionException} if it could not be found.
	 *
	 * @param dossierId the dossier ID
	 * @param pending the pending
	 * @return the matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_DPG(long dossierId, boolean pending)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_DPG(dossierId, pending);

		if (dossierAction == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossierId=");
			msg.append(dossierId);

			msg.append(", pending=");
			msg.append(pending);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierActionException(msg.toString());
		}

		return dossierAction;
	}

	/**
	 * Returns the dossier action where dossierId = &#63; and pending = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param pending the pending
	 * @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_DPG(long dossierId, boolean pending) {
		return fetchByDID_DPG(dossierId, pending, true);
	}

	/**
	 * Returns the dossier action where dossierId = &#63; and pending = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param pending the pending
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_DPG(long dossierId, boolean pending,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossierId, pending };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DID_DPG,
					finderArgs, this);
		}

		if (result instanceof DossierAction) {
			DossierAction dossierAction = (DossierAction)result;

			if ((dossierId != dossierAction.getDossierId()) ||
					(pending != dossierAction.isPending())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_DPG_DOSSIERID_2);

			query.append(_FINDER_COLUMN_DID_DPG_PENDING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				qPos.add(pending);

				List<DossierAction> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DID_DPG,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierActionPersistenceImpl.fetchByDID_DPG(long, boolean, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierAction dossierAction = list.get(0);

					result = dossierAction;

					cacheResult(dossierAction);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_DPG,
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
			return (DossierAction)result;
		}
	}

	/**
	 * Removes the dossier action where dossierId = &#63; and pending = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param pending the pending
	 * @return the dossier action that was removed
	 */
	@Override
	public DossierAction removeByDID_DPG(long dossierId, boolean pending)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByDID_DPG(dossierId, pending);

		return remove(dossierAction);
	}

	/**
	 * Returns the number of dossier actions where dossierId = &#63; and pending = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param pending the pending
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByDID_DPG(long dossierId, boolean pending) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_DPG;

		Object[] finderArgs = new Object[] { dossierId, pending };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_DPG_DOSSIERID_2);

			query.append(_FINDER_COLUMN_DID_DPG_PENDING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				qPos.add(pending);

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

	private static final String _FINDER_COLUMN_DID_DPG_DOSSIERID_2 = "dossierAction.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_DPG_PENDING_2 = "dossierAction.pending = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DID_NACTID = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByDID_NACTID",
			new String[] { Long.class.getName(), Long.class.getName() },
			DossierActionModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierActionModelImpl.NEXTACTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_NACTID = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_NACTID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier action where dossierId = &#63; and nextActionId = &#63; or throws a {@link NoSuchDossierActionException} if it could not be found.
	 *
	 * @param dossierId the dossier ID
	 * @param nextActionId the next action ID
	 * @return the matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_NACTID(long dossierId, long nextActionId)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_NACTID(dossierId, nextActionId);

		if (dossierAction == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossierId=");
			msg.append(dossierId);

			msg.append(", nextActionId=");
			msg.append(nextActionId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierActionException(msg.toString());
		}

		return dossierAction;
	}

	/**
	 * Returns the dossier action where dossierId = &#63; and nextActionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param nextActionId the next action ID
	 * @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_NACTID(long dossierId, long nextActionId) {
		return fetchByDID_NACTID(dossierId, nextActionId, true);
	}

	/**
	 * Returns the dossier action where dossierId = &#63; and nextActionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param nextActionId the next action ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_NACTID(long dossierId, long nextActionId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossierId, nextActionId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DID_NACTID,
					finderArgs, this);
		}

		if (result instanceof DossierAction) {
			DossierAction dossierAction = (DossierAction)result;

			if ((dossierId != dossierAction.getDossierId()) ||
					(nextActionId != dossierAction.getNextActionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_NACTID_DOSSIERID_2);

			query.append(_FINDER_COLUMN_DID_NACTID_NEXTACTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				qPos.add(nextActionId);

				List<DossierAction> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DID_NACTID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierActionPersistenceImpl.fetchByDID_NACTID(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierAction dossierAction = list.get(0);

					result = dossierAction;

					cacheResult(dossierAction);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_NACTID,
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
			return (DossierAction)result;
		}
	}

	/**
	 * Removes the dossier action where dossierId = &#63; and nextActionId = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param nextActionId the next action ID
	 * @return the dossier action that was removed
	 */
	@Override
	public DossierAction removeByDID_NACTID(long dossierId, long nextActionId)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByDID_NACTID(dossierId, nextActionId);

		return remove(dossierAction);
	}

	/**
	 * Returns the number of dossier actions where dossierId = &#63; and nextActionId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param nextActionId the next action ID
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByDID_NACTID(long dossierId, long nextActionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_NACTID;

		Object[] finderArgs = new Object[] { dossierId, nextActionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_NACTID_DOSSIERID_2);

			query.append(_FINDER_COLUMN_DID_NACTID_NEXTACTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				qPos.add(nextActionId);

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

	private static final String _FINDER_COLUMN_DID_NACTID_DOSSIERID_2 = "dossierAction.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_NACTID_NEXTACTIONID_2 = "dossierAction.nextActionId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DID_ACTC = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByDID_ACTC",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierActionModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierActionModelImpl.ACTIONCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_ACTC = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_ACTC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the dossier action where dossierId = &#63; and actionCode = &#63; or throws a {@link NoSuchDossierActionException} if it could not be found.
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @return the matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_ACTC(long dossierId, String actionCode)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_ACTC(dossierId, actionCode);

		if (dossierAction == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossierId=");
			msg.append(dossierId);

			msg.append(", actionCode=");
			msg.append(actionCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierActionException(msg.toString());
		}

		return dossierAction;
	}

	/**
	 * Returns the dossier action where dossierId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_ACTC(long dossierId, String actionCode) {
		return fetchByDID_ACTC(dossierId, actionCode, true);
	}

	/**
	 * Returns the dossier action where dossierId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_ACTC(long dossierId, String actionCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossierId, actionCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DID_ACTC,
					finderArgs, this);
		}

		if (result instanceof DossierAction) {
			DossierAction dossierAction = (DossierAction)result;

			if ((dossierId != dossierAction.getDossierId()) ||
					!Objects.equals(actionCode, dossierAction.getActionCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_ACTC_DOSSIERID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_DID_ACTC_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_DID_ACTC_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_DID_ACTC_ACTIONCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindActionCode) {
					qPos.add(actionCode);
				}

				List<DossierAction> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DID_ACTC,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierActionPersistenceImpl.fetchByDID_ACTC(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierAction dossierAction = list.get(0);

					result = dossierAction;

					cacheResult(dossierAction);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_ACTC,
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
			return (DossierAction)result;
		}
	}

	/**
	 * Removes the dossier action where dossierId = &#63; and actionCode = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @return the dossier action that was removed
	 */
	@Override
	public DossierAction removeByDID_ACTC(long dossierId, String actionCode)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByDID_ACTC(dossierId, actionCode);

		return remove(dossierAction);
	}

	/**
	 * Returns the number of dossier actions where dossierId = &#63; and actionCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByDID_ACTC(long dossierId, String actionCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_ACTC;

		Object[] finderArgs = new Object[] { dossierId, actionCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_ACTC_DOSSIERID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_DID_ACTC_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_DID_ACTC_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_DID_ACTC_ACTIONCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindActionCode) {
					qPos.add(actionCode);
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

	private static final String _FINDER_COLUMN_DID_ACTC_DOSSIERID_2 = "dossierAction.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_ACTC_ACTIONCODE_1 = "dossierAction.actionCode IS NULL";
	private static final String _FINDER_COLUMN_DID_ACTC_ACTIONCODE_2 = "dossierAction.actionCode = ?";
	private static final String _FINDER_COLUMN_DID_ACTC_ACTIONCODE_3 = "(dossierAction.actionCode IS NULL OR dossierAction.actionCode = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_DID_UID = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByDID_UID",
			new String[] { Long.class.getName(), Long.class.getName() },
			DossierActionModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierActionModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_UID = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_UID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier action where dossierId = &#63; and userId = &#63; or throws a {@link NoSuchDossierActionException} if it could not be found.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @return the matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_UID(long dossierId, long userId)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_UID(dossierId, userId);

		if (dossierAction == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossierId=");
			msg.append(dossierId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierActionException(msg.toString());
		}

		return dossierAction;
	}

	/**
	 * Returns the dossier action where dossierId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_UID(long dossierId, long userId) {
		return fetchByDID_UID(dossierId, userId, true);
	}

	/**
	 * Returns the dossier action where dossierId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_UID(long dossierId, long userId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossierId, userId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DID_UID,
					finderArgs, this);
		}

		if (result instanceof DossierAction) {
			DossierAction dossierAction = (DossierAction)result;

			if ((dossierId != dossierAction.getDossierId()) ||
					(userId != dossierAction.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_UID_DOSSIERID_2);

			query.append(_FINDER_COLUMN_DID_UID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				qPos.add(userId);

				List<DossierAction> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DID_UID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierActionPersistenceImpl.fetchByDID_UID(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierAction dossierAction = list.get(0);

					result = dossierAction;

					cacheResult(dossierAction);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_UID,
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
			return (DossierAction)result;
		}
	}

	/**
	 * Removes the dossier action where dossierId = &#63; and userId = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @return the dossier action that was removed
	 */
	@Override
	public DossierAction removeByDID_UID(long dossierId, long userId)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByDID_UID(dossierId, userId);

		return remove(dossierAction);
	}

	/**
	 * Returns the number of dossier actions where dossierId = &#63; and userId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByDID_UID(long dossierId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_UID;

		Object[] finderArgs = new Object[] { dossierId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_UID_DOSSIERID_2);

			query.append(_FINDER_COLUMN_DID_UID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

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

	private static final String _FINDER_COLUMN_DID_UID_DOSSIERID_2 = "dossierAction.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_UID_USERID_2 = "dossierAction.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByDID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByDID", new String[] { Long.class.getName() },
			DossierActionModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierActionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dossier actions where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @return the matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID(long dossierId) {
		return findByDID(dossierId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier actions where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @return the range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID(long dossierId, int start, int end) {
		return findByDID(dossierId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier actions where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID(long dossierId, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return findByDID(dossierId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier actions where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID(long dossierId, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID;
			finderArgs = new Object[] { dossierId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID;
			finderArgs = new Object[] { dossierId, start, end, orderByComparator };
		}

		List<DossierAction> list = null;

		if (retrieveFromCache) {
			list = (List<DossierAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierAction dossierAction : list) {
					if ((dossierId != dossierAction.getDossierId())) {
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

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_DOSSIERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (!pagination) {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier action in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_First(long dossierId,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_First(dossierId,
				orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the first dossier action in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_First(long dossierId,
		OrderByComparator<DossierAction> orderByComparator) {
		List<DossierAction> list = findByDID(dossierId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_Last(long dossierId,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_Last(dossierId,
				orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the last dossier action in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_Last(long dossierId,
		OrderByComparator<DossierAction> orderByComparator) {
		int count = countByDID(dossierId);

		if (count == 0) {
			return null;
		}

		List<DossierAction> list = findByDID(dossierId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier actions before and after the current dossier action in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierActionId the primary key of the current dossier action
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction[] findByDID_PrevAndNext(long dossierActionId,
		long dossierId, OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByPrimaryKey(dossierActionId);

		Session session = null;

		try {
			session = openSession();

			DossierAction[] array = new DossierActionImpl[3];

			array[0] = getByDID_PrevAndNext(session, dossierAction, dossierId,
					orderByComparator, true);

			array[1] = dossierAction;

			array[2] = getByDID_PrevAndNext(session, dossierAction, dossierId,
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

	protected DossierAction getByDID_PrevAndNext(Session session,
		DossierAction dossierAction, long dossierId,
		OrderByComparator<DossierAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

		query.append(_FINDER_COLUMN_DID_DOSSIERID_2);

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
			query.append(DossierActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier actions where dossierId = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 */
	@Override
	public void removeByDID(long dossierId) {
		for (DossierAction dossierAction : findByDID(dossierId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierAction);
		}
	}

	/**
	 * Returns the number of dossier actions where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByDID(long dossierId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID;

		Object[] finderArgs = new Object[] { dossierId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_DOSSIERID_2);

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

	private static final String _FINDER_COLUMN_DID_DOSSIERID_2 = "dossierAction.dossierId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PENDING =
		new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_PENDING",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PENDING =
		new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByG_PENDING",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			DossierActionModelImpl.GROUPID_COLUMN_BITMASK |
			DossierActionModelImpl.PENDING_COLUMN_BITMASK |
			DossierActionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_PENDING = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_PENDING",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the dossier actions where groupId = &#63; and pending = &#63;.
	 *
	 * @param groupId the group ID
	 * @param pending the pending
	 * @return the matching dossier actions
	 */
	@Override
	public List<DossierAction> findByG_PENDING(long groupId, boolean pending) {
		return findByG_PENDING(groupId, pending, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier actions where groupId = &#63; and pending = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param pending the pending
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @return the range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByG_PENDING(long groupId, boolean pending,
		int start, int end) {
		return findByG_PENDING(groupId, pending, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier actions where groupId = &#63; and pending = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param pending the pending
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByG_PENDING(long groupId, boolean pending,
		int start, int end, OrderByComparator<DossierAction> orderByComparator) {
		return findByG_PENDING(groupId, pending, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the dossier actions where groupId = &#63; and pending = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param pending the pending
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByG_PENDING(long groupId, boolean pending,
		int start, int end, OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PENDING;
			finderArgs = new Object[] { groupId, pending };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_PENDING;
			finderArgs = new Object[] {
					groupId, pending,
					
					start, end, orderByComparator
				};
		}

		List<DossierAction> list = null;

		if (retrieveFromCache) {
			list = (List<DossierAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierAction dossierAction : list) {
					if ((groupId != dossierAction.getGroupId()) ||
							(pending != dossierAction.isPending())) {
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

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_G_PENDING_GROUPID_2);

			query.append(_FINDER_COLUMN_G_PENDING_PENDING_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(pending);

				if (!pagination) {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier action in the ordered set where groupId = &#63; and pending = &#63;.
	 *
	 * @param groupId the group ID
	 * @param pending the pending
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByG_PENDING_First(long groupId, boolean pending,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByG_PENDING_First(groupId, pending,
				orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", pending=");
		msg.append(pending);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the first dossier action in the ordered set where groupId = &#63; and pending = &#63;.
	 *
	 * @param groupId the group ID
	 * @param pending the pending
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByG_PENDING_First(long groupId, boolean pending,
		OrderByComparator<DossierAction> orderByComparator) {
		List<DossierAction> list = findByG_PENDING(groupId, pending, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action in the ordered set where groupId = &#63; and pending = &#63;.
	 *
	 * @param groupId the group ID
	 * @param pending the pending
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByG_PENDING_Last(long groupId, boolean pending,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByG_PENDING_Last(groupId, pending,
				orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", pending=");
		msg.append(pending);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the last dossier action in the ordered set where groupId = &#63; and pending = &#63;.
	 *
	 * @param groupId the group ID
	 * @param pending the pending
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByG_PENDING_Last(long groupId, boolean pending,
		OrderByComparator<DossierAction> orderByComparator) {
		int count = countByG_PENDING(groupId, pending);

		if (count == 0) {
			return null;
		}

		List<DossierAction> list = findByG_PENDING(groupId, pending, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier actions before and after the current dossier action in the ordered set where groupId = &#63; and pending = &#63;.
	 *
	 * @param dossierActionId the primary key of the current dossier action
	 * @param groupId the group ID
	 * @param pending the pending
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction[] findByG_PENDING_PrevAndNext(long dossierActionId,
		long groupId, boolean pending,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByPrimaryKey(dossierActionId);

		Session session = null;

		try {
			session = openSession();

			DossierAction[] array = new DossierActionImpl[3];

			array[0] = getByG_PENDING_PrevAndNext(session, dossierAction,
					groupId, pending, orderByComparator, true);

			array[1] = dossierAction;

			array[2] = getByG_PENDING_PrevAndNext(session, dossierAction,
					groupId, pending, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierAction getByG_PENDING_PrevAndNext(Session session,
		DossierAction dossierAction, long groupId, boolean pending,
		OrderByComparator<DossierAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

		query.append(_FINDER_COLUMN_G_PENDING_GROUPID_2);

		query.append(_FINDER_COLUMN_G_PENDING_PENDING_2);

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
			query.append(DossierActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(pending);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier actions where groupId = &#63; and pending = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param pending the pending
	 */
	@Override
	public void removeByG_PENDING(long groupId, boolean pending) {
		for (DossierAction dossierAction : findByG_PENDING(groupId, pending,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierAction);
		}
	}

	/**
	 * Returns the number of dossier actions where groupId = &#63; and pending = &#63;.
	 *
	 * @param groupId the group ID
	 * @param pending the pending
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByG_PENDING(long groupId, boolean pending) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_PENDING;

		Object[] finderArgs = new Object[] { groupId, pending };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_G_PENDING_GROUPID_2);

			query.append(_FINDER_COLUMN_G_PENDING_PENDING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(pending);

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

	private static final String _FINDER_COLUMN_G_PENDING_GROUPID_2 = "dossierAction.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_PENDING_PENDING_2 = "dossierAction.pending = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_FSN = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByDID_FSN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FSN =
		new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByDID_FSN",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierActionModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierActionModelImpl.FROMSEQUENCENO_COLUMN_BITMASK |
			DossierActionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_FSN = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_FSN",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossier actions where dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @return the matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_FSN(long dossierId,
		String fromSequenceNo) {
		return findByDID_FSN(dossierId, fromSequenceNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier actions where dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @return the range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_FSN(long dossierId,
		String fromSequenceNo, int start, int end) {
		return findByDID_FSN(dossierId, fromSequenceNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier actions where dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_FSN(long dossierId,
		String fromSequenceNo, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return findByDID_FSN(dossierId, fromSequenceNo, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier actions where dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_FSN(long dossierId,
		String fromSequenceNo, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FSN;
			finderArgs = new Object[] { dossierId, fromSequenceNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_FSN;
			finderArgs = new Object[] {
					dossierId, fromSequenceNo,
					
					start, end, orderByComparator
				};
		}

		List<DossierAction> list = null;

		if (retrieveFromCache) {
			list = (List<DossierAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierAction dossierAction : list) {
					if ((dossierId != dossierAction.getDossierId()) ||
							!Objects.equals(fromSequenceNo,
								dossierAction.getFromSequenceNo())) {
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

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_FSN_DOSSIERID_2);

			boolean bindFromSequenceNo = false;

			if (fromSequenceNo == null) {
				query.append(_FINDER_COLUMN_DID_FSN_FROMSEQUENCENO_1);
			}
			else if (fromSequenceNo.equals("")) {
				query.append(_FINDER_COLUMN_DID_FSN_FROMSEQUENCENO_3);
			}
			else {
				bindFromSequenceNo = true;

				query.append(_FINDER_COLUMN_DID_FSN_FROMSEQUENCENO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindFromSequenceNo) {
					qPos.add(fromSequenceNo);
				}

				if (!pagination) {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier action in the ordered set where dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_FSN_First(long dossierId,
		String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_FSN_First(dossierId,
				fromSequenceNo, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", fromSequenceNo=");
		msg.append(fromSequenceNo);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the first dossier action in the ordered set where dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_FSN_First(long dossierId,
		String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator) {
		List<DossierAction> list = findByDID_FSN(dossierId, fromSequenceNo, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action in the ordered set where dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_FSN_Last(long dossierId,
		String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_FSN_Last(dossierId,
				fromSequenceNo, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", fromSequenceNo=");
		msg.append(fromSequenceNo);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the last dossier action in the ordered set where dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_FSN_Last(long dossierId,
		String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator) {
		int count = countByDID_FSN(dossierId, fromSequenceNo);

		if (count == 0) {
			return null;
		}

		List<DossierAction> list = findByDID_FSN(dossierId, fromSequenceNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier actions before and after the current dossier action in the ordered set where dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * @param dossierActionId the primary key of the current dossier action
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction[] findByDID_FSN_PrevAndNext(long dossierActionId,
		long dossierId, String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByPrimaryKey(dossierActionId);

		Session session = null;

		try {
			session = openSession();

			DossierAction[] array = new DossierActionImpl[3];

			array[0] = getByDID_FSN_PrevAndNext(session, dossierAction,
					dossierId, fromSequenceNo, orderByComparator, true);

			array[1] = dossierAction;

			array[2] = getByDID_FSN_PrevAndNext(session, dossierAction,
					dossierId, fromSequenceNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierAction getByDID_FSN_PrevAndNext(Session session,
		DossierAction dossierAction, long dossierId, String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

		query.append(_FINDER_COLUMN_DID_FSN_DOSSIERID_2);

		boolean bindFromSequenceNo = false;

		if (fromSequenceNo == null) {
			query.append(_FINDER_COLUMN_DID_FSN_FROMSEQUENCENO_1);
		}
		else if (fromSequenceNo.equals("")) {
			query.append(_FINDER_COLUMN_DID_FSN_FROMSEQUENCENO_3);
		}
		else {
			bindFromSequenceNo = true;

			query.append(_FINDER_COLUMN_DID_FSN_FROMSEQUENCENO_2);
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
			query.append(DossierActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (bindFromSequenceNo) {
			qPos.add(fromSequenceNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier actions where dossierId = &#63; and fromSequenceNo = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 */
	@Override
	public void removeByDID_FSN(long dossierId, String fromSequenceNo) {
		for (DossierAction dossierAction : findByDID_FSN(dossierId,
				fromSequenceNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierAction);
		}
	}

	/**
	 * Returns the number of dossier actions where dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByDID_FSN(long dossierId, String fromSequenceNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_FSN;

		Object[] finderArgs = new Object[] { dossierId, fromSequenceNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_FSN_DOSSIERID_2);

			boolean bindFromSequenceNo = false;

			if (fromSequenceNo == null) {
				query.append(_FINDER_COLUMN_DID_FSN_FROMSEQUENCENO_1);
			}
			else if (fromSequenceNo.equals("")) {
				query.append(_FINDER_COLUMN_DID_FSN_FROMSEQUENCENO_3);
			}
			else {
				bindFromSequenceNo = true;

				query.append(_FINDER_COLUMN_DID_FSN_FROMSEQUENCENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindFromSequenceNo) {
					qPos.add(fromSequenceNo);
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

	private static final String _FINDER_COLUMN_DID_FSN_DOSSIERID_2 = "dossierAction.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_FSN_FROMSEQUENCENO_1 = "dossierAction.fromSequenceNo IS NULL";
	private static final String _FINDER_COLUMN_DID_FSN_FROMSEQUENCENO_2 = "dossierAction.fromSequenceNo = ?";
	private static final String _FINDER_COLUMN_DID_FSN_FROMSEQUENCENO_3 = "(dossierAction.fromSequenceNo IS NULL OR dossierAction.fromSequenceNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_DID_SN = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_DID_SN",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID_SN =
		new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByG_DID_SN",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			DossierActionModelImpl.GROUPID_COLUMN_BITMASK |
			DossierActionModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierActionModelImpl.SEQUENCENO_COLUMN_BITMASK |
			DossierActionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_DID_SN = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_DID_SN",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the dossier actions where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param sequenceNo the sequence no
	 * @return the matching dossier actions
	 */
	@Override
	public List<DossierAction> findByG_DID_SN(long groupId, long dossierId,
		String sequenceNo) {
		return findByG_DID_SN(groupId, dossierId, sequenceNo,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier actions where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param sequenceNo the sequence no
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @return the range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByG_DID_SN(long groupId, long dossierId,
		String sequenceNo, int start, int end) {
		return findByG_DID_SN(groupId, dossierId, sequenceNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier actions where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param sequenceNo the sequence no
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByG_DID_SN(long groupId, long dossierId,
		String sequenceNo, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return findByG_DID_SN(groupId, dossierId, sequenceNo, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier actions where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param sequenceNo the sequence no
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByG_DID_SN(long groupId, long dossierId,
		String sequenceNo, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID_SN;
			finderArgs = new Object[] { groupId, dossierId, sequenceNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_DID_SN;
			finderArgs = new Object[] {
					groupId, dossierId, sequenceNo,
					
					start, end, orderByComparator
				};
		}

		List<DossierAction> list = null;

		if (retrieveFromCache) {
			list = (List<DossierAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierAction dossierAction : list) {
					if ((groupId != dossierAction.getGroupId()) ||
							(dossierId != dossierAction.getDossierId()) ||
							!Objects.equals(sequenceNo,
								dossierAction.getSequenceNo())) {
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

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_G_DID_SN_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_SN_DOSSIERID_2);

			boolean bindSequenceNo = false;

			if (sequenceNo == null) {
				query.append(_FINDER_COLUMN_G_DID_SN_SEQUENCENO_1);
			}
			else if (sequenceNo.equals("")) {
				query.append(_FINDER_COLUMN_G_DID_SN_SEQUENCENO_3);
			}
			else {
				bindSequenceNo = true;

				query.append(_FINDER_COLUMN_G_DID_SN_SEQUENCENO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				if (bindSequenceNo) {
					qPos.add(sequenceNo);
				}

				if (!pagination) {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByG_DID_SN_First(long groupId, long dossierId,
		String sequenceNo, OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByG_DID_SN_First(groupId, dossierId,
				sequenceNo, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierId=");
		msg.append(dossierId);

		msg.append(", sequenceNo=");
		msg.append(sequenceNo);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the first dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByG_DID_SN_First(long groupId, long dossierId,
		String sequenceNo, OrderByComparator<DossierAction> orderByComparator) {
		List<DossierAction> list = findByG_DID_SN(groupId, dossierId,
				sequenceNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByG_DID_SN_Last(long groupId, long dossierId,
		String sequenceNo, OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByG_DID_SN_Last(groupId, dossierId,
				sequenceNo, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierId=");
		msg.append(dossierId);

		msg.append(", sequenceNo=");
		msg.append(sequenceNo);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the last dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByG_DID_SN_Last(long groupId, long dossierId,
		String sequenceNo, OrderByComparator<DossierAction> orderByComparator) {
		int count = countByG_DID_SN(groupId, dossierId, sequenceNo);

		if (count == 0) {
			return null;
		}

		List<DossierAction> list = findByG_DID_SN(groupId, dossierId,
				sequenceNo, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier actions before and after the current dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	 *
	 * @param dossierActionId the primary key of the current dossier action
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction[] findByG_DID_SN_PrevAndNext(long dossierActionId,
		long groupId, long dossierId, String sequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByPrimaryKey(dossierActionId);

		Session session = null;

		try {
			session = openSession();

			DossierAction[] array = new DossierActionImpl[3];

			array[0] = getByG_DID_SN_PrevAndNext(session, dossierAction,
					groupId, dossierId, sequenceNo, orderByComparator, true);

			array[1] = dossierAction;

			array[2] = getByG_DID_SN_PrevAndNext(session, dossierAction,
					groupId, dossierId, sequenceNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierAction getByG_DID_SN_PrevAndNext(Session session,
		DossierAction dossierAction, long groupId, long dossierId,
		String sequenceNo, OrderByComparator<DossierAction> orderByComparator,
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

		query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

		query.append(_FINDER_COLUMN_G_DID_SN_GROUPID_2);

		query.append(_FINDER_COLUMN_G_DID_SN_DOSSIERID_2);

		boolean bindSequenceNo = false;

		if (sequenceNo == null) {
			query.append(_FINDER_COLUMN_G_DID_SN_SEQUENCENO_1);
		}
		else if (sequenceNo.equals("")) {
			query.append(_FINDER_COLUMN_G_DID_SN_SEQUENCENO_3);
		}
		else {
			bindSequenceNo = true;

			query.append(_FINDER_COLUMN_G_DID_SN_SEQUENCENO_2);
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
			query.append(DossierActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(dossierId);

		if (bindSequenceNo) {
			qPos.add(sequenceNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier actions where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param sequenceNo the sequence no
	 */
	@Override
	public void removeByG_DID_SN(long groupId, long dossierId, String sequenceNo) {
		for (DossierAction dossierAction : findByG_DID_SN(groupId, dossierId,
				sequenceNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierAction);
		}
	}

	/**
	 * Returns the number of dossier actions where groupId = &#63; and dossierId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param sequenceNo the sequence no
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByG_DID_SN(long groupId, long dossierId, String sequenceNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_DID_SN;

		Object[] finderArgs = new Object[] { groupId, dossierId, sequenceNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_G_DID_SN_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_SN_DOSSIERID_2);

			boolean bindSequenceNo = false;

			if (sequenceNo == null) {
				query.append(_FINDER_COLUMN_G_DID_SN_SEQUENCENO_1);
			}
			else if (sequenceNo.equals("")) {
				query.append(_FINDER_COLUMN_G_DID_SN_SEQUENCENO_3);
			}
			else {
				bindSequenceNo = true;

				query.append(_FINDER_COLUMN_G_DID_SN_SEQUENCENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				if (bindSequenceNo) {
					qPos.add(sequenceNo);
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

	private static final String _FINDER_COLUMN_G_DID_SN_GROUPID_2 = "dossierAction.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_DID_SN_DOSSIERID_2 = "dossierAction.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_G_DID_SN_SEQUENCENO_1 = "dossierAction.sequenceNo IS NULL";
	private static final String _FINDER_COLUMN_G_DID_SN_SEQUENCENO_2 = "dossierAction.sequenceNo = ?";
	private static final String _FINDER_COLUMN_G_DID_SN_SEQUENCENO_3 = "(dossierAction.sequenceNo IS NULL OR dossierAction.sequenceNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_DID_FSN =
		new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_DID_FSN",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID_FSN =
		new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByG_DID_FSN",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			DossierActionModelImpl.GROUPID_COLUMN_BITMASK |
			DossierActionModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierActionModelImpl.FROMSEQUENCENO_COLUMN_BITMASK |
			DossierActionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_DID_FSN = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_DID_FSN",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the dossier actions where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @return the matching dossier actions
	 */
	@Override
	public List<DossierAction> findByG_DID_FSN(long groupId, long dossierId,
		String fromSequenceNo) {
		return findByG_DID_FSN(groupId, dossierId, fromSequenceNo,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier actions where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @return the range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByG_DID_FSN(long groupId, long dossierId,
		String fromSequenceNo, int start, int end) {
		return findByG_DID_FSN(groupId, dossierId, fromSequenceNo, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the dossier actions where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByG_DID_FSN(long groupId, long dossierId,
		String fromSequenceNo, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return findByG_DID_FSN(groupId, dossierId, fromSequenceNo, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier actions where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByG_DID_FSN(long groupId, long dossierId,
		String fromSequenceNo, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID_FSN;
			finderArgs = new Object[] { groupId, dossierId, fromSequenceNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_DID_FSN;
			finderArgs = new Object[] {
					groupId, dossierId, fromSequenceNo,
					
					start, end, orderByComparator
				};
		}

		List<DossierAction> list = null;

		if (retrieveFromCache) {
			list = (List<DossierAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierAction dossierAction : list) {
					if ((groupId != dossierAction.getGroupId()) ||
							(dossierId != dossierAction.getDossierId()) ||
							!Objects.equals(fromSequenceNo,
								dossierAction.getFromSequenceNo())) {
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

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_G_DID_FSN_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_FSN_DOSSIERID_2);

			boolean bindFromSequenceNo = false;

			if (fromSequenceNo == null) {
				query.append(_FINDER_COLUMN_G_DID_FSN_FROMSEQUENCENO_1);
			}
			else if (fromSequenceNo.equals("")) {
				query.append(_FINDER_COLUMN_G_DID_FSN_FROMSEQUENCENO_3);
			}
			else {
				bindFromSequenceNo = true;

				query.append(_FINDER_COLUMN_G_DID_FSN_FROMSEQUENCENO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				if (bindFromSequenceNo) {
					qPos.add(fromSequenceNo);
				}

				if (!pagination) {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByG_DID_FSN_First(long groupId, long dossierId,
		String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByG_DID_FSN_First(groupId,
				dossierId, fromSequenceNo, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierId=");
		msg.append(dossierId);

		msg.append(", fromSequenceNo=");
		msg.append(fromSequenceNo);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the first dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByG_DID_FSN_First(long groupId, long dossierId,
		String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator) {
		List<DossierAction> list = findByG_DID_FSN(groupId, dossierId,
				fromSequenceNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByG_DID_FSN_Last(long groupId, long dossierId,
		String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByG_DID_FSN_Last(groupId, dossierId,
				fromSequenceNo, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierId=");
		msg.append(dossierId);

		msg.append(", fromSequenceNo=");
		msg.append(fromSequenceNo);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the last dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByG_DID_FSN_Last(long groupId, long dossierId,
		String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator) {
		int count = countByG_DID_FSN(groupId, dossierId, fromSequenceNo);

		if (count == 0) {
			return null;
		}

		List<DossierAction> list = findByG_DID_FSN(groupId, dossierId,
				fromSequenceNo, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier actions before and after the current dossier action in the ordered set where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * @param dossierActionId the primary key of the current dossier action
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction[] findByG_DID_FSN_PrevAndNext(long dossierActionId,
		long groupId, long dossierId, String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByPrimaryKey(dossierActionId);

		Session session = null;

		try {
			session = openSession();

			DossierAction[] array = new DossierActionImpl[3];

			array[0] = getByG_DID_FSN_PrevAndNext(session, dossierAction,
					groupId, dossierId, fromSequenceNo, orderByComparator, true);

			array[1] = dossierAction;

			array[2] = getByG_DID_FSN_PrevAndNext(session, dossierAction,
					groupId, dossierId, fromSequenceNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierAction getByG_DID_FSN_PrevAndNext(Session session,
		DossierAction dossierAction, long groupId, long dossierId,
		String fromSequenceNo,
		OrderByComparator<DossierAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

		query.append(_FINDER_COLUMN_G_DID_FSN_GROUPID_2);

		query.append(_FINDER_COLUMN_G_DID_FSN_DOSSIERID_2);

		boolean bindFromSequenceNo = false;

		if (fromSequenceNo == null) {
			query.append(_FINDER_COLUMN_G_DID_FSN_FROMSEQUENCENO_1);
		}
		else if (fromSequenceNo.equals("")) {
			query.append(_FINDER_COLUMN_G_DID_FSN_FROMSEQUENCENO_3);
		}
		else {
			bindFromSequenceNo = true;

			query.append(_FINDER_COLUMN_G_DID_FSN_FROMSEQUENCENO_2);
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
			query.append(DossierActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(dossierId);

		if (bindFromSequenceNo) {
			qPos.add(fromSequenceNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier actions where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 */
	@Override
	public void removeByG_DID_FSN(long groupId, long dossierId,
		String fromSequenceNo) {
		for (DossierAction dossierAction : findByG_DID_FSN(groupId, dossierId,
				fromSequenceNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierAction);
		}
	}

	/**
	 * Returns the number of dossier actions where groupId = &#63; and dossierId = &#63; and fromSequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param fromSequenceNo the from sequence no
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByG_DID_FSN(long groupId, long dossierId,
		String fromSequenceNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_DID_FSN;

		Object[] finderArgs = new Object[] { groupId, dossierId, fromSequenceNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_G_DID_FSN_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_FSN_DOSSIERID_2);

			boolean bindFromSequenceNo = false;

			if (fromSequenceNo == null) {
				query.append(_FINDER_COLUMN_G_DID_FSN_FROMSEQUENCENO_1);
			}
			else if (fromSequenceNo.equals("")) {
				query.append(_FINDER_COLUMN_G_DID_FSN_FROMSEQUENCENO_3);
			}
			else {
				bindFromSequenceNo = true;

				query.append(_FINDER_COLUMN_G_DID_FSN_FROMSEQUENCENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				if (bindFromSequenceNo) {
					qPos.add(fromSequenceNo);
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

	private static final String _FINDER_COLUMN_G_DID_FSN_GROUPID_2 = "dossierAction.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_DID_FSN_DOSSIERID_2 = "dossierAction.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_G_DID_FSN_FROMSEQUENCENO_1 = "dossierAction.fromSequenceNo IS NULL";
	private static final String _FINDER_COLUMN_G_DID_FSN_FROMSEQUENCENO_2 = "dossierAction.fromSequenceNo = ?";
	private static final String _FINDER_COLUMN_G_DID_FSN_FROMSEQUENCENO_3 = "(dossierAction.fromSequenceNo IS NULL OR dossierAction.fromSequenceNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_STEP = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByDID_STEP",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_STEP =
		new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByDID_STEP",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierActionModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierActionModelImpl.FROMSTEPCODE_COLUMN_BITMASK |
			DossierActionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_STEP = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_STEP",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossier actions where dossierId = &#63; and fromStepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fromStepCode the from step code
	 * @return the matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_STEP(long dossierId,
		String fromStepCode) {
		return findByDID_STEP(dossierId, fromStepCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier actions where dossierId = &#63; and fromStepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fromStepCode the from step code
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @return the range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_STEP(long dossierId,
		String fromStepCode, int start, int end) {
		return findByDID_STEP(dossierId, fromStepCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier actions where dossierId = &#63; and fromStepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fromStepCode the from step code
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_STEP(long dossierId,
		String fromStepCode, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return findByDID_STEP(dossierId, fromStepCode, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier actions where dossierId = &#63; and fromStepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fromStepCode the from step code
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_STEP(long dossierId,
		String fromStepCode, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_STEP;
			finderArgs = new Object[] { dossierId, fromStepCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_STEP;
			finderArgs = new Object[] {
					dossierId, fromStepCode,
					
					start, end, orderByComparator
				};
		}

		List<DossierAction> list = null;

		if (retrieveFromCache) {
			list = (List<DossierAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierAction dossierAction : list) {
					if ((dossierId != dossierAction.getDossierId()) ||
							!Objects.equals(fromStepCode,
								dossierAction.getFromStepCode())) {
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

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_STEP_DOSSIERID_2);

			boolean bindFromStepCode = false;

			if (fromStepCode == null) {
				query.append(_FINDER_COLUMN_DID_STEP_FROMSTEPCODE_1);
			}
			else if (fromStepCode.equals("")) {
				query.append(_FINDER_COLUMN_DID_STEP_FROMSTEPCODE_3);
			}
			else {
				bindFromStepCode = true;

				query.append(_FINDER_COLUMN_DID_STEP_FROMSTEPCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindFromStepCode) {
					qPos.add(fromStepCode);
				}

				if (!pagination) {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier action in the ordered set where dossierId = &#63; and fromStepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fromStepCode the from step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_STEP_First(long dossierId,
		String fromStepCode, OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_STEP_First(dossierId,
				fromStepCode, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", fromStepCode=");
		msg.append(fromStepCode);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the first dossier action in the ordered set where dossierId = &#63; and fromStepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fromStepCode the from step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_STEP_First(long dossierId,
		String fromStepCode, OrderByComparator<DossierAction> orderByComparator) {
		List<DossierAction> list = findByDID_STEP(dossierId, fromStepCode, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action in the ordered set where dossierId = &#63; and fromStepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fromStepCode the from step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_STEP_Last(long dossierId,
		String fromStepCode, OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_STEP_Last(dossierId,
				fromStepCode, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", fromStepCode=");
		msg.append(fromStepCode);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the last dossier action in the ordered set where dossierId = &#63; and fromStepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fromStepCode the from step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_STEP_Last(long dossierId,
		String fromStepCode, OrderByComparator<DossierAction> orderByComparator) {
		int count = countByDID_STEP(dossierId, fromStepCode);

		if (count == 0) {
			return null;
		}

		List<DossierAction> list = findByDID_STEP(dossierId, fromStepCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier actions before and after the current dossier action in the ordered set where dossierId = &#63; and fromStepCode = &#63;.
	 *
	 * @param dossierActionId the primary key of the current dossier action
	 * @param dossierId the dossier ID
	 * @param fromStepCode the from step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction[] findByDID_STEP_PrevAndNext(long dossierActionId,
		long dossierId, String fromStepCode,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByPrimaryKey(dossierActionId);

		Session session = null;

		try {
			session = openSession();

			DossierAction[] array = new DossierActionImpl[3];

			array[0] = getByDID_STEP_PrevAndNext(session, dossierAction,
					dossierId, fromStepCode, orderByComparator, true);

			array[1] = dossierAction;

			array[2] = getByDID_STEP_PrevAndNext(session, dossierAction,
					dossierId, fromStepCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierAction getByDID_STEP_PrevAndNext(Session session,
		DossierAction dossierAction, long dossierId, String fromStepCode,
		OrderByComparator<DossierAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

		query.append(_FINDER_COLUMN_DID_STEP_DOSSIERID_2);

		boolean bindFromStepCode = false;

		if (fromStepCode == null) {
			query.append(_FINDER_COLUMN_DID_STEP_FROMSTEPCODE_1);
		}
		else if (fromStepCode.equals("")) {
			query.append(_FINDER_COLUMN_DID_STEP_FROMSTEPCODE_3);
		}
		else {
			bindFromStepCode = true;

			query.append(_FINDER_COLUMN_DID_STEP_FROMSTEPCODE_2);
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
			query.append(DossierActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (bindFromStepCode) {
			qPos.add(fromStepCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier actions where dossierId = &#63; and fromStepCode = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param fromStepCode the from step code
	 */
	@Override
	public void removeByDID_STEP(long dossierId, String fromStepCode) {
		for (DossierAction dossierAction : findByDID_STEP(dossierId,
				fromStepCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierAction);
		}
	}

	/**
	 * Returns the number of dossier actions where dossierId = &#63; and fromStepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fromStepCode the from step code
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByDID_STEP(long dossierId, String fromStepCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_STEP;

		Object[] finderArgs = new Object[] { dossierId, fromStepCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_STEP_DOSSIERID_2);

			boolean bindFromStepCode = false;

			if (fromStepCode == null) {
				query.append(_FINDER_COLUMN_DID_STEP_FROMSTEPCODE_1);
			}
			else if (fromStepCode.equals("")) {
				query.append(_FINDER_COLUMN_DID_STEP_FROMSTEPCODE_3);
			}
			else {
				bindFromStepCode = true;

				query.append(_FINDER_COLUMN_DID_STEP_FROMSTEPCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindFromStepCode) {
					qPos.add(fromStepCode);
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

	private static final String _FINDER_COLUMN_DID_STEP_DOSSIERID_2 = "dossierAction.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_STEP_FROMSTEPCODE_1 = "dossierAction.fromStepCode IS NULL";
	private static final String _FINDER_COLUMN_DID_STEP_FROMSTEPCODE_2 = "dossierAction.fromStepCode = ?";
	private static final String _FINDER_COLUMN_DID_STEP_FROMSTEPCODE_3 = "(dossierAction.fromStepCode IS NULL OR dossierAction.fromStepCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_SC = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByDID_SC",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_SC =
		new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByDID_SC",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierActionModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierActionModelImpl.STEPCODE_COLUMN_BITMASK |
			DossierActionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_SC = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_SC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossier actions where dossierId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @return the matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_SC(long dossierId, String stepCode) {
		return findByDID_SC(dossierId, stepCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier actions where dossierId = &#63; and stepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @return the range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_SC(long dossierId, String stepCode,
		int start, int end) {
		return findByDID_SC(dossierId, stepCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier actions where dossierId = &#63; and stepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_SC(long dossierId, String stepCode,
		int start, int end, OrderByComparator<DossierAction> orderByComparator) {
		return findByDID_SC(dossierId, stepCode, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the dossier actions where dossierId = &#63; and stepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_SC(long dossierId, String stepCode,
		int start, int end, OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_SC;
			finderArgs = new Object[] { dossierId, stepCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_SC;
			finderArgs = new Object[] {
					dossierId, stepCode,
					
					start, end, orderByComparator
				};
		}

		List<DossierAction> list = null;

		if (retrieveFromCache) {
			list = (List<DossierAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierAction dossierAction : list) {
					if ((dossierId != dossierAction.getDossierId()) ||
							!Objects.equals(stepCode,
								dossierAction.getStepCode())) {
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

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_SC_DOSSIERID_2);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_DID_SC_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_DID_SC_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_DID_SC_STEPCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindStepCode) {
					qPos.add(stepCode);
				}

				if (!pagination) {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier action in the ordered set where dossierId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_SC_First(long dossierId, String stepCode,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_SC_First(dossierId, stepCode,
				orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", stepCode=");
		msg.append(stepCode);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the first dossier action in the ordered set where dossierId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_SC_First(long dossierId, String stepCode,
		OrderByComparator<DossierAction> orderByComparator) {
		List<DossierAction> list = findByDID_SC(dossierId, stepCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action in the ordered set where dossierId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_SC_Last(long dossierId, String stepCode,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_SC_Last(dossierId, stepCode,
				orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", stepCode=");
		msg.append(stepCode);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the last dossier action in the ordered set where dossierId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_SC_Last(long dossierId, String stepCode,
		OrderByComparator<DossierAction> orderByComparator) {
		int count = countByDID_SC(dossierId, stepCode);

		if (count == 0) {
			return null;
		}

		List<DossierAction> list = findByDID_SC(dossierId, stepCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier actions before and after the current dossier action in the ordered set where dossierId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierActionId the primary key of the current dossier action
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction[] findByDID_SC_PrevAndNext(long dossierActionId,
		long dossierId, String stepCode,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByPrimaryKey(dossierActionId);

		Session session = null;

		try {
			session = openSession();

			DossierAction[] array = new DossierActionImpl[3];

			array[0] = getByDID_SC_PrevAndNext(session, dossierAction,
					dossierId, stepCode, orderByComparator, true);

			array[1] = dossierAction;

			array[2] = getByDID_SC_PrevAndNext(session, dossierAction,
					dossierId, stepCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierAction getByDID_SC_PrevAndNext(Session session,
		DossierAction dossierAction, long dossierId, String stepCode,
		OrderByComparator<DossierAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

		query.append(_FINDER_COLUMN_DID_SC_DOSSIERID_2);

		boolean bindStepCode = false;

		if (stepCode == null) {
			query.append(_FINDER_COLUMN_DID_SC_STEPCODE_1);
		}
		else if (stepCode.equals("")) {
			query.append(_FINDER_COLUMN_DID_SC_STEPCODE_3);
		}
		else {
			bindStepCode = true;

			query.append(_FINDER_COLUMN_DID_SC_STEPCODE_2);
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
			query.append(DossierActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (bindStepCode) {
			qPos.add(stepCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier actions where dossierId = &#63; and stepCode = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 */
	@Override
	public void removeByDID_SC(long dossierId, String stepCode) {
		for (DossierAction dossierAction : findByDID_SC(dossierId, stepCode,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierAction);
		}
	}

	/**
	 * Returns the number of dossier actions where dossierId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByDID_SC(long dossierId, String stepCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_SC;

		Object[] finderArgs = new Object[] { dossierId, stepCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_SC_DOSSIERID_2);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_DID_SC_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_DID_SC_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_DID_SC_STEPCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindStepCode) {
					qPos.add(stepCode);
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

	private static final String _FINDER_COLUMN_DID_SC_DOSSIERID_2 = "dossierAction.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_SC_STEPCODE_1 = "dossierAction.stepCode IS NULL";
	private static final String _FINDER_COLUMN_DID_SC_STEPCODE_2 = "dossierAction.stepCode = ?";
	private static final String _FINDER_COLUMN_DID_SC_STEPCODE_3 = "(dossierAction.stepCode IS NULL OR dossierAction.stepCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_CODE = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByDID_CODE",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_CODE =
		new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByDID_CODE",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierActionModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierActionModelImpl.ACTIONCODE_COLUMN_BITMASK |
			DossierActionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_CODE = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_CODE",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossier actions where dossierId = &#63; and actionCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @return the matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_CODE(long dossierId, String actionCode) {
		return findByDID_CODE(dossierId, actionCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier actions where dossierId = &#63; and actionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @return the range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_CODE(long dossierId,
		String actionCode, int start, int end) {
		return findByDID_CODE(dossierId, actionCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier actions where dossierId = &#63; and actionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_CODE(long dossierId,
		String actionCode, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return findByDID_CODE(dossierId, actionCode, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier actions where dossierId = &#63; and actionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_CODE(long dossierId,
		String actionCode, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_CODE;
			finderArgs = new Object[] { dossierId, actionCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_CODE;
			finderArgs = new Object[] {
					dossierId, actionCode,
					
					start, end, orderByComparator
				};
		}

		List<DossierAction> list = null;

		if (retrieveFromCache) {
			list = (List<DossierAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierAction dossierAction : list) {
					if ((dossierId != dossierAction.getDossierId()) ||
							!Objects.equals(actionCode,
								dossierAction.getActionCode())) {
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

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_CODE_DOSSIERID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_DID_CODE_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_DID_CODE_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_DID_CODE_ACTIONCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindActionCode) {
					qPos.add(actionCode);
				}

				if (!pagination) {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier action in the ordered set where dossierId = &#63; and actionCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_CODE_First(long dossierId,
		String actionCode, OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_CODE_First(dossierId,
				actionCode, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", actionCode=");
		msg.append(actionCode);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the first dossier action in the ordered set where dossierId = &#63; and actionCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_CODE_First(long dossierId,
		String actionCode, OrderByComparator<DossierAction> orderByComparator) {
		List<DossierAction> list = findByDID_CODE(dossierId, actionCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action in the ordered set where dossierId = &#63; and actionCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_CODE_Last(long dossierId, String actionCode,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_CODE_Last(dossierId,
				actionCode, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", actionCode=");
		msg.append(actionCode);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the last dossier action in the ordered set where dossierId = &#63; and actionCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_CODE_Last(long dossierId,
		String actionCode, OrderByComparator<DossierAction> orderByComparator) {
		int count = countByDID_CODE(dossierId, actionCode);

		if (count == 0) {
			return null;
		}

		List<DossierAction> list = findByDID_CODE(dossierId, actionCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier actions before and after the current dossier action in the ordered set where dossierId = &#63; and actionCode = &#63;.
	 *
	 * @param dossierActionId the primary key of the current dossier action
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction[] findByDID_CODE_PrevAndNext(long dossierActionId,
		long dossierId, String actionCode,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByPrimaryKey(dossierActionId);

		Session session = null;

		try {
			session = openSession();

			DossierAction[] array = new DossierActionImpl[3];

			array[0] = getByDID_CODE_PrevAndNext(session, dossierAction,
					dossierId, actionCode, orderByComparator, true);

			array[1] = dossierAction;

			array[2] = getByDID_CODE_PrevAndNext(session, dossierAction,
					dossierId, actionCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierAction getByDID_CODE_PrevAndNext(Session session,
		DossierAction dossierAction, long dossierId, String actionCode,
		OrderByComparator<DossierAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

		query.append(_FINDER_COLUMN_DID_CODE_DOSSIERID_2);

		boolean bindActionCode = false;

		if (actionCode == null) {
			query.append(_FINDER_COLUMN_DID_CODE_ACTIONCODE_1);
		}
		else if (actionCode.equals("")) {
			query.append(_FINDER_COLUMN_DID_CODE_ACTIONCODE_3);
		}
		else {
			bindActionCode = true;

			query.append(_FINDER_COLUMN_DID_CODE_ACTIONCODE_2);
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
			query.append(DossierActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (bindActionCode) {
			qPos.add(actionCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier actions where dossierId = &#63; and actionCode = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 */
	@Override
	public void removeByDID_CODE(long dossierId, String actionCode) {
		for (DossierAction dossierAction : findByDID_CODE(dossierId,
				actionCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierAction);
		}
	}

	/**
	 * Returns the number of dossier actions where dossierId = &#63; and actionCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param actionCode the action code
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByDID_CODE(long dossierId, String actionCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_CODE;

		Object[] finderArgs = new Object[] { dossierId, actionCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_CODE_DOSSIERID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_DID_CODE_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_DID_CODE_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_DID_CODE_ACTIONCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindActionCode) {
					qPos.add(actionCode);
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

	private static final String _FINDER_COLUMN_DID_CODE_DOSSIERID_2 = "dossierAction.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_CODE_ACTIONCODE_1 = "dossierAction.actionCode IS NULL";
	private static final String _FINDER_COLUMN_DID_CODE_ACTIONCODE_2 = "dossierAction.actionCode = ?";
	private static final String _FINDER_COLUMN_DID_CODE_ACTIONCODE_3 = "(dossierAction.actionCode IS NULL OR dossierAction.actionCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_U_SC = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByDID_U_SC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_U_SC =
		new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByDID_U_SC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			DossierActionModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierActionModelImpl.USERID_COLUMN_BITMASK |
			DossierActionModelImpl.STEPCODE_COLUMN_BITMASK |
			DossierActionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_U_SC = new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_U_SC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the dossier actions where dossierId = &#63; and userId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @param stepCode the step code
	 * @return the matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_U_SC(long dossierId, long userId,
		String stepCode) {
		return findByDID_U_SC(dossierId, userId, stepCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier actions where dossierId = &#63; and userId = &#63; and stepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @param stepCode the step code
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @return the range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_U_SC(long dossierId, long userId,
		String stepCode, int start, int end) {
		return findByDID_U_SC(dossierId, userId, stepCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier actions where dossierId = &#63; and userId = &#63; and stepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @param stepCode the step code
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_U_SC(long dossierId, long userId,
		String stepCode, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return findByDID_U_SC(dossierId, userId, stepCode, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier actions where dossierId = &#63; and userId = &#63; and stepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @param stepCode the step code
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_U_SC(long dossierId, long userId,
		String stepCode, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_U_SC;
			finderArgs = new Object[] { dossierId, userId, stepCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_U_SC;
			finderArgs = new Object[] {
					dossierId, userId, stepCode,
					
					start, end, orderByComparator
				};
		}

		List<DossierAction> list = null;

		if (retrieveFromCache) {
			list = (List<DossierAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierAction dossierAction : list) {
					if ((dossierId != dossierAction.getDossierId()) ||
							(userId != dossierAction.getUserId()) ||
							!Objects.equals(stepCode,
								dossierAction.getStepCode())) {
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

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_U_SC_DOSSIERID_2);

			query.append(_FINDER_COLUMN_DID_U_SC_USERID_2);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_DID_U_SC_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_DID_U_SC_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_DID_U_SC_STEPCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				qPos.add(userId);

				if (bindStepCode) {
					qPos.add(stepCode);
				}

				if (!pagination) {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier action in the ordered set where dossierId = &#63; and userId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_U_SC_First(long dossierId, long userId,
		String stepCode, OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_U_SC_First(dossierId, userId,
				stepCode, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", stepCode=");
		msg.append(stepCode);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the first dossier action in the ordered set where dossierId = &#63; and userId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_U_SC_First(long dossierId, long userId,
		String stepCode, OrderByComparator<DossierAction> orderByComparator) {
		List<DossierAction> list = findByDID_U_SC(dossierId, userId, stepCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action in the ordered set where dossierId = &#63; and userId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_U_SC_Last(long dossierId, long userId,
		String stepCode, OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_U_SC_Last(dossierId, userId,
				stepCode, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", stepCode=");
		msg.append(stepCode);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the last dossier action in the ordered set where dossierId = &#63; and userId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_U_SC_Last(long dossierId, long userId,
		String stepCode, OrderByComparator<DossierAction> orderByComparator) {
		int count = countByDID_U_SC(dossierId, userId, stepCode);

		if (count == 0) {
			return null;
		}

		List<DossierAction> list = findByDID_U_SC(dossierId, userId, stepCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier actions before and after the current dossier action in the ordered set where dossierId = &#63; and userId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierActionId the primary key of the current dossier action
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction[] findByDID_U_SC_PrevAndNext(long dossierActionId,
		long dossierId, long userId, String stepCode,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = findByPrimaryKey(dossierActionId);

		Session session = null;

		try {
			session = openSession();

			DossierAction[] array = new DossierActionImpl[3];

			array[0] = getByDID_U_SC_PrevAndNext(session, dossierAction,
					dossierId, userId, stepCode, orderByComparator, true);

			array[1] = dossierAction;

			array[2] = getByDID_U_SC_PrevAndNext(session, dossierAction,
					dossierId, userId, stepCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierAction getByDID_U_SC_PrevAndNext(Session session,
		DossierAction dossierAction, long dossierId, long userId,
		String stepCode, OrderByComparator<DossierAction> orderByComparator,
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

		query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

		query.append(_FINDER_COLUMN_DID_U_SC_DOSSIERID_2);

		query.append(_FINDER_COLUMN_DID_U_SC_USERID_2);

		boolean bindStepCode = false;

		if (stepCode == null) {
			query.append(_FINDER_COLUMN_DID_U_SC_STEPCODE_1);
		}
		else if (stepCode.equals("")) {
			query.append(_FINDER_COLUMN_DID_U_SC_STEPCODE_3);
		}
		else {
			bindStepCode = true;

			query.append(_FINDER_COLUMN_DID_U_SC_STEPCODE_2);
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
			query.append(DossierActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		qPos.add(userId);

		if (bindStepCode) {
			qPos.add(stepCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier actions where dossierId = &#63; and userId = &#63; and stepCode = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @param stepCode the step code
	 */
	@Override
	public void removeByDID_U_SC(long dossierId, long userId, String stepCode) {
		for (DossierAction dossierAction : findByDID_U_SC(dossierId, userId,
				stepCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierAction);
		}
	}

	/**
	 * Returns the number of dossier actions where dossierId = &#63; and userId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @param stepCode the step code
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByDID_U_SC(long dossierId, long userId, String stepCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_U_SC;

		Object[] finderArgs = new Object[] { dossierId, userId, stepCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_U_SC_DOSSIERID_2);

			query.append(_FINDER_COLUMN_DID_U_SC_USERID_2);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_DID_U_SC_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_DID_U_SC_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_DID_U_SC_STEPCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				qPos.add(userId);

				if (bindStepCode) {
					qPos.add(stepCode);
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

	private static final String _FINDER_COLUMN_DID_U_SC_DOSSIERID_2 = "dossierAction.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_U_SC_USERID_2 = "dossierAction.userId = ? AND ";
	private static final String _FINDER_COLUMN_DID_U_SC_STEPCODE_1 = "dossierAction.stepCode IS NULL";
	private static final String _FINDER_COLUMN_DID_U_SC_STEPCODE_2 = "dossierAction.stepCode = ?";
	private static final String _FINDER_COLUMN_DID_U_SC_STEPCODE_3 = "(dossierAction.stepCode IS NULL OR dossierAction.stepCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_SC_NOT_DAI =
		new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED,
			DossierActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByDID_SC_NOT_DAI",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_DID_SC_NOT_DAI =
		new FinderPath(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByDID_SC_NOT_DAI",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the dossier actions where dossierId = &#63; and stepCode = &#63; and dossierActionId &ne; &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param dossierActionId the dossier action ID
	 * @return the matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_SC_NOT_DAI(long dossierId,
		String stepCode, long dossierActionId) {
		return findByDID_SC_NOT_DAI(dossierId, stepCode, dossierActionId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier actions where dossierId = &#63; and stepCode = &#63; and dossierActionId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param dossierActionId the dossier action ID
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @return the range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_SC_NOT_DAI(long dossierId,
		String stepCode, long dossierActionId, int start, int end) {
		return findByDID_SC_NOT_DAI(dossierId, stepCode, dossierActionId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier actions where dossierId = &#63; and stepCode = &#63; and dossierActionId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param dossierActionId the dossier action ID
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_SC_NOT_DAI(long dossierId,
		String stepCode, long dossierActionId, int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return findByDID_SC_NOT_DAI(dossierId, stepCode, dossierActionId,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier actions where dossierId = &#63; and stepCode = &#63; and dossierActionId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param dossierActionId the dossier action ID
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier actions
	 */
	@Override
	public List<DossierAction> findByDID_SC_NOT_DAI(long dossierId,
		String stepCode, long dossierActionId, int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_SC_NOT_DAI;
		finderArgs = new Object[] {
				dossierId, stepCode, dossierActionId,
				
				start, end, orderByComparator
			};

		List<DossierAction> list = null;

		if (retrieveFromCache) {
			list = (List<DossierAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierAction dossierAction : list) {
					if ((dossierId != dossierAction.getDossierId()) ||
							!Objects.equals(stepCode,
								dossierAction.getStepCode()) ||
							(dossierActionId == dossierAction.getDossierActionId())) {
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

			query.append(_SQL_SELECT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_SC_NOT_DAI_DOSSIERID_2);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_DID_SC_NOT_DAI_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_DID_SC_NOT_DAI_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_DID_SC_NOT_DAI_STEPCODE_2);
			}

			query.append(_FINDER_COLUMN_DID_SC_NOT_DAI_DOSSIERACTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindStepCode) {
					qPos.add(stepCode);
				}

				qPos.add(dossierActionId);

				if (!pagination) {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier action in the ordered set where dossierId = &#63; and stepCode = &#63; and dossierActionId &ne; &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param dossierActionId the dossier action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_SC_NOT_DAI_First(long dossierId,
		String stepCode, long dossierActionId,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_SC_NOT_DAI_First(dossierId,
				stepCode, dossierActionId, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", stepCode=");
		msg.append(stepCode);

		msg.append(", dossierActionId=");
		msg.append(dossierActionId);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the first dossier action in the ordered set where dossierId = &#63; and stepCode = &#63; and dossierActionId &ne; &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param dossierActionId the dossier action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_SC_NOT_DAI_First(long dossierId,
		String stepCode, long dossierActionId,
		OrderByComparator<DossierAction> orderByComparator) {
		List<DossierAction> list = findByDID_SC_NOT_DAI(dossierId, stepCode,
				dossierActionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action in the ordered set where dossierId = &#63; and stepCode = &#63; and dossierActionId &ne; &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param dossierActionId the dossier action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action
	 * @throws NoSuchDossierActionException if a matching dossier action could not be found
	 */
	@Override
	public DossierAction findByDID_SC_NOT_DAI_Last(long dossierId,
		String stepCode, long dossierActionId,
		OrderByComparator<DossierAction> orderByComparator)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByDID_SC_NOT_DAI_Last(dossierId,
				stepCode, dossierActionId, orderByComparator);

		if (dossierAction != null) {
			return dossierAction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", stepCode=");
		msg.append(stepCode);

		msg.append(", dossierActionId=");
		msg.append(dossierActionId);

		msg.append("}");

		throw new NoSuchDossierActionException(msg.toString());
	}

	/**
	 * Returns the last dossier action in the ordered set where dossierId = &#63; and stepCode = &#63; and dossierActionId &ne; &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param dossierActionId the dossier action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action, or <code>null</code> if a matching dossier action could not be found
	 */
	@Override
	public DossierAction fetchByDID_SC_NOT_DAI_Last(long dossierId,
		String stepCode, long dossierActionId,
		OrderByComparator<DossierAction> orderByComparator) {
		int count = countByDID_SC_NOT_DAI(dossierId, stepCode, dossierActionId);

		if (count == 0) {
			return null;
		}

		List<DossierAction> list = findByDID_SC_NOT_DAI(dossierId, stepCode,
				dossierActionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the dossier actions where dossierId = &#63; and stepCode = &#63; and dossierActionId &ne; &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param dossierActionId the dossier action ID
	 */
	@Override
	public void removeByDID_SC_NOT_DAI(long dossierId, String stepCode,
		long dossierActionId) {
		for (DossierAction dossierAction : findByDID_SC_NOT_DAI(dossierId,
				stepCode, dossierActionId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossierAction);
		}
	}

	/**
	 * Returns the number of dossier actions where dossierId = &#63; and stepCode = &#63; and dossierActionId &ne; &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param dossierActionId the dossier action ID
	 * @return the number of matching dossier actions
	 */
	@Override
	public int countByDID_SC_NOT_DAI(long dossierId, String stepCode,
		long dossierActionId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_DID_SC_NOT_DAI;

		Object[] finderArgs = new Object[] { dossierId, stepCode, dossierActionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERACTION_WHERE);

			query.append(_FINDER_COLUMN_DID_SC_NOT_DAI_DOSSIERID_2);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_DID_SC_NOT_DAI_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_DID_SC_NOT_DAI_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_DID_SC_NOT_DAI_STEPCODE_2);
			}

			query.append(_FINDER_COLUMN_DID_SC_NOT_DAI_DOSSIERACTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindStepCode) {
					qPos.add(stepCode);
				}

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

	private static final String _FINDER_COLUMN_DID_SC_NOT_DAI_DOSSIERID_2 = "dossierAction.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_SC_NOT_DAI_STEPCODE_1 = "dossierAction.stepCode IS NULL AND ";
	private static final String _FINDER_COLUMN_DID_SC_NOT_DAI_STEPCODE_2 = "dossierAction.stepCode = ? AND ";
	private static final String _FINDER_COLUMN_DID_SC_NOT_DAI_STEPCODE_3 = "(dossierAction.stepCode IS NULL OR dossierAction.stepCode = '') AND ";
	private static final String _FINDER_COLUMN_DID_SC_NOT_DAI_DOSSIERACTIONID_2 = "dossierAction.dossierActionId != ?";

	public DossierActionPersistenceImpl() {
		setModelClass(DossierAction.class);

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
	 * Caches the dossier action in the entity cache if it is enabled.
	 *
	 * @param dossierAction the dossier action
	 */
	@Override
	public void cacheResult(DossierAction dossierAction) {
		entityCache.putResult(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionImpl.class, dossierAction.getPrimaryKey(),
			dossierAction);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dossierAction.getUuid(), dossierAction.getGroupId() },
			dossierAction);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DID_DPG,
			new Object[] { dossierAction.getDossierId(), dossierAction.isPending() },
			dossierAction);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DID_NACTID,
			new Object[] {
				dossierAction.getDossierId(), dossierAction.getNextActionId()
			}, dossierAction);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DID_ACTC,
			new Object[] {
				dossierAction.getDossierId(), dossierAction.getActionCode()
			}, dossierAction);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DID_UID,
			new Object[] { dossierAction.getDossierId(), dossierAction.getUserId() },
			dossierAction);

		dossierAction.resetOriginalValues();
	}

	/**
	 * Caches the dossier actions in the entity cache if it is enabled.
	 *
	 * @param dossierActions the dossier actions
	 */
	@Override
	public void cacheResult(List<DossierAction> dossierActions) {
		for (DossierAction dossierAction : dossierActions) {
			if (entityCache.getResult(
						DossierActionModelImpl.ENTITY_CACHE_ENABLED,
						DossierActionImpl.class, dossierAction.getPrimaryKey()) == null) {
				cacheResult(dossierAction);
			}
			else {
				dossierAction.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dossier actions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DossierActionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dossier action.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DossierAction dossierAction) {
		entityCache.removeResult(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionImpl.class, dossierAction.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DossierActionModelImpl)dossierAction, true);
	}

	@Override
	public void clearCache(List<DossierAction> dossierActions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DossierAction dossierAction : dossierActions) {
			entityCache.removeResult(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
				DossierActionImpl.class, dossierAction.getPrimaryKey());

			clearUniqueFindersCache((DossierActionModelImpl)dossierAction, true);
		}
	}

	protected void cacheUniqueFindersCache(
		DossierActionModelImpl dossierActionModelImpl) {
		Object[] args = new Object[] {
				dossierActionModelImpl.getUuid(),
				dossierActionModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dossierActionModelImpl, false);

		args = new Object[] {
				dossierActionModelImpl.getDossierId(),
				dossierActionModelImpl.isPending()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_DID_DPG, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DID_DPG, args,
			dossierActionModelImpl, false);

		args = new Object[] {
				dossierActionModelImpl.getDossierId(),
				dossierActionModelImpl.getNextActionId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_DID_NACTID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DID_NACTID, args,
			dossierActionModelImpl, false);

		args = new Object[] {
				dossierActionModelImpl.getDossierId(),
				dossierActionModelImpl.getActionCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_DID_ACTC, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DID_ACTC, args,
			dossierActionModelImpl, false);

		args = new Object[] {
				dossierActionModelImpl.getDossierId(),
				dossierActionModelImpl.getUserId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_DID_UID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DID_UID, args,
			dossierActionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DossierActionModelImpl dossierActionModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierActionModelImpl.getUuid(),
					dossierActionModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dossierActionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierActionModelImpl.getOriginalUuid(),
					dossierActionModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierActionModelImpl.getDossierId(),
					dossierActionModelImpl.isPending()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_DPG, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_DPG, args);
		}

		if ((dossierActionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DID_DPG.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierActionModelImpl.getOriginalDossierId(),
					dossierActionModelImpl.getOriginalPending()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_DPG, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_DPG, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierActionModelImpl.getDossierId(),
					dossierActionModelImpl.getNextActionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_NACTID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_NACTID, args);
		}

		if ((dossierActionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DID_NACTID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierActionModelImpl.getOriginalDossierId(),
					dossierActionModelImpl.getOriginalNextActionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_NACTID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_NACTID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierActionModelImpl.getDossierId(),
					dossierActionModelImpl.getActionCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_ACTC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_ACTC, args);
		}

		if ((dossierActionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DID_ACTC.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierActionModelImpl.getOriginalDossierId(),
					dossierActionModelImpl.getOriginalActionCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_ACTC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_ACTC, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierActionModelImpl.getDossierId(),
					dossierActionModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_UID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_UID, args);
		}

		if ((dossierActionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DID_UID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierActionModelImpl.getOriginalDossierId(),
					dossierActionModelImpl.getOriginalUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_UID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_UID, args);
		}
	}

	/**
	 * Creates a new dossier action with the primary key. Does not add the dossier action to the database.
	 *
	 * @param dossierActionId the primary key for the new dossier action
	 * @return the new dossier action
	 */
	@Override
	public DossierAction create(long dossierActionId) {
		DossierAction dossierAction = new DossierActionImpl();

		dossierAction.setNew(true);
		dossierAction.setPrimaryKey(dossierActionId);

		String uuid = PortalUUIDUtil.generate();

		dossierAction.setUuid(uuid);

		dossierAction.setCompanyId(companyProvider.getCompanyId());

		return dossierAction;
	}

	/**
	 * Removes the dossier action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dossierActionId the primary key of the dossier action
	 * @return the dossier action that was removed
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction remove(long dossierActionId)
		throws NoSuchDossierActionException {
		return remove((Serializable)dossierActionId);
	}

	/**
	 * Removes the dossier action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dossier action
	 * @return the dossier action that was removed
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction remove(Serializable primaryKey)
		throws NoSuchDossierActionException {
		Session session = null;

		try {
			session = openSession();

			DossierAction dossierAction = (DossierAction)session.get(DossierActionImpl.class,
					primaryKey);

			if (dossierAction == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDossierActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dossierAction);
		}
		catch (NoSuchDossierActionException nsee) {
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
	protected DossierAction removeImpl(DossierAction dossierAction) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dossierAction)) {
				dossierAction = (DossierAction)session.get(DossierActionImpl.class,
						dossierAction.getPrimaryKeyObj());
			}

			if (dossierAction != null) {
				session.delete(dossierAction);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dossierAction != null) {
			clearCache(dossierAction);
		}

		return dossierAction;
	}

	@Override
	public DossierAction updateImpl(DossierAction dossierAction) {
		boolean isNew = dossierAction.isNew();

		if (!(dossierAction instanceof DossierActionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dossierAction.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dossierAction);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dossierAction proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DossierAction implementation " +
				dossierAction.getClass());
		}

		DossierActionModelImpl dossierActionModelImpl = (DossierActionModelImpl)dossierAction;

		if (Validator.isNull(dossierAction.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dossierAction.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dossierAction.getCreateDate() == null)) {
			if (serviceContext == null) {
				dossierAction.setCreateDate(now);
			}
			else {
				dossierAction.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dossierActionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dossierAction.setModifiedDate(now);
			}
			else {
				dossierAction.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dossierAction.isNew()) {
				session.save(dossierAction);

				dossierAction.setNew(false);
			}
			else {
				dossierAction = (DossierAction)session.merge(dossierAction);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DossierActionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dossierActionModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					dossierActionModelImpl.getUuid(),
					dossierActionModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { dossierActionModelImpl.getDossierId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID,
				args);

			args = new Object[] {
					dossierActionModelImpl.getGroupId(),
					dossierActionModelImpl.isPending()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PENDING, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PENDING,
				args);

			args = new Object[] {
					dossierActionModelImpl.getDossierId(),
					dossierActionModelImpl.getFromSequenceNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_FSN, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FSN,
				args);

			args = new Object[] {
					dossierActionModelImpl.getGroupId(),
					dossierActionModelImpl.getDossierId(),
					dossierActionModelImpl.getSequenceNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID_SN, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID_SN,
				args);

			args = new Object[] {
					dossierActionModelImpl.getGroupId(),
					dossierActionModelImpl.getDossierId(),
					dossierActionModelImpl.getFromSequenceNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID_FSN, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID_FSN,
				args);

			args = new Object[] {
					dossierActionModelImpl.getDossierId(),
					dossierActionModelImpl.getFromStepCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_STEP, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_STEP,
				args);

			args = new Object[] {
					dossierActionModelImpl.getDossierId(),
					dossierActionModelImpl.getStepCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_SC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_SC,
				args);

			args = new Object[] {
					dossierActionModelImpl.getDossierId(),
					dossierActionModelImpl.getActionCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_CODE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_CODE,
				args);

			args = new Object[] {
					dossierActionModelImpl.getDossierId(),
					dossierActionModelImpl.getUserId(),
					dossierActionModelImpl.getStepCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_U_SC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_U_SC,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dossierActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dossierActionModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dossierActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionModelImpl.getOriginalUuid(),
						dossierActionModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dossierActionModelImpl.getUuid(),
						dossierActionModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((dossierActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionModelImpl.getOriginalDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID,
					args);

				args = new Object[] { dossierActionModelImpl.getDossierId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID,
					args);
			}

			if ((dossierActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PENDING.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionModelImpl.getOriginalGroupId(),
						dossierActionModelImpl.getOriginalPending()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PENDING, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PENDING,
					args);

				args = new Object[] {
						dossierActionModelImpl.getGroupId(),
						dossierActionModelImpl.isPending()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_PENDING, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_PENDING,
					args);
			}

			if ((dossierActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FSN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionModelImpl.getOriginalDossierId(),
						dossierActionModelImpl.getOriginalFromSequenceNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_FSN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FSN,
					args);

				args = new Object[] {
						dossierActionModelImpl.getDossierId(),
						dossierActionModelImpl.getFromSequenceNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_FSN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FSN,
					args);
			}

			if ((dossierActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID_SN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionModelImpl.getOriginalGroupId(),
						dossierActionModelImpl.getOriginalDossierId(),
						dossierActionModelImpl.getOriginalSequenceNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID_SN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID_SN,
					args);

				args = new Object[] {
						dossierActionModelImpl.getGroupId(),
						dossierActionModelImpl.getDossierId(),
						dossierActionModelImpl.getSequenceNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID_SN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID_SN,
					args);
			}

			if ((dossierActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID_FSN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionModelImpl.getOriginalGroupId(),
						dossierActionModelImpl.getOriginalDossierId(),
						dossierActionModelImpl.getOriginalFromSequenceNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID_FSN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID_FSN,
					args);

				args = new Object[] {
						dossierActionModelImpl.getGroupId(),
						dossierActionModelImpl.getDossierId(),
						dossierActionModelImpl.getFromSequenceNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID_FSN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DID_FSN,
					args);
			}

			if ((dossierActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_STEP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionModelImpl.getOriginalDossierId(),
						dossierActionModelImpl.getOriginalFromStepCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_STEP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_STEP,
					args);

				args = new Object[] {
						dossierActionModelImpl.getDossierId(),
						dossierActionModelImpl.getFromStepCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_STEP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_STEP,
					args);
			}

			if ((dossierActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_SC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionModelImpl.getOriginalDossierId(),
						dossierActionModelImpl.getOriginalStepCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_SC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_SC,
					args);

				args = new Object[] {
						dossierActionModelImpl.getDossierId(),
						dossierActionModelImpl.getStepCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_SC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_SC,
					args);
			}

			if ((dossierActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_CODE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionModelImpl.getOriginalDossierId(),
						dossierActionModelImpl.getOriginalActionCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_CODE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_CODE,
					args);

				args = new Object[] {
						dossierActionModelImpl.getDossierId(),
						dossierActionModelImpl.getActionCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_CODE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_CODE,
					args);
			}

			if ((dossierActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_U_SC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionModelImpl.getOriginalDossierId(),
						dossierActionModelImpl.getOriginalUserId(),
						dossierActionModelImpl.getOriginalStepCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_U_SC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_U_SC,
					args);

				args = new Object[] {
						dossierActionModelImpl.getDossierId(),
						dossierActionModelImpl.getUserId(),
						dossierActionModelImpl.getStepCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_U_SC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_U_SC,
					args);
			}
		}

		entityCache.putResult(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionImpl.class, dossierAction.getPrimaryKey(),
			dossierAction, false);

		clearUniqueFindersCache(dossierActionModelImpl, false);
		cacheUniqueFindersCache(dossierActionModelImpl);

		dossierAction.resetOriginalValues();

		return dossierAction;
	}

	/**
	 * Returns the dossier action with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier action
	 * @return the dossier action
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDossierActionException {
		DossierAction dossierAction = fetchByPrimaryKey(primaryKey);

		if (dossierAction == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDossierActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dossierAction;
	}

	/**
	 * Returns the dossier action with the primary key or throws a {@link NoSuchDossierActionException} if it could not be found.
	 *
	 * @param dossierActionId the primary key of the dossier action
	 * @return the dossier action
	 * @throws NoSuchDossierActionException if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction findByPrimaryKey(long dossierActionId)
		throws NoSuchDossierActionException {
		return findByPrimaryKey((Serializable)dossierActionId);
	}

	/**
	 * Returns the dossier action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier action
	 * @return the dossier action, or <code>null</code> if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
				DossierActionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DossierAction dossierAction = (DossierAction)serializable;

		if (dossierAction == null) {
			Session session = null;

			try {
				session = openSession();

				dossierAction = (DossierAction)session.get(DossierActionImpl.class,
						primaryKey);

				if (dossierAction != null) {
					cacheResult(dossierAction);
				}
				else {
					entityCache.putResult(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
						DossierActionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
					DossierActionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dossierAction;
	}

	/**
	 * Returns the dossier action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dossierActionId the primary key of the dossier action
	 * @return the dossier action, or <code>null</code> if a dossier action with the primary key could not be found
	 */
	@Override
	public DossierAction fetchByPrimaryKey(long dossierActionId) {
		return fetchByPrimaryKey((Serializable)dossierActionId);
	}

	@Override
	public Map<Serializable, DossierAction> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DossierAction> map = new HashMap<Serializable, DossierAction>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DossierAction dossierAction = fetchByPrimaryKey(primaryKey);

			if (dossierAction != null) {
				map.put(primaryKey, dossierAction);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
					DossierActionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DossierAction)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DOSSIERACTION_WHERE_PKS_IN);

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

			for (DossierAction dossierAction : (List<DossierAction>)q.list()) {
				map.put(dossierAction.getPrimaryKeyObj(), dossierAction);

				cacheResult(dossierAction);

				uncachedPrimaryKeys.remove(dossierAction.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DossierActionModelImpl.ENTITY_CACHE_ENABLED,
					DossierActionImpl.class, primaryKey, nullModel);
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
	 * Returns all the dossier actions.
	 *
	 * @return the dossier actions
	 */
	@Override
	public List<DossierAction> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @return the range of dossier actions
	 */
	@Override
	public List<DossierAction> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dossier actions
	 */
	@Override
	public List<DossierAction> findAll(int start, int end,
		OrderByComparator<DossierAction> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier actions
	 * @param end the upper bound of the range of dossier actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dossier actions
	 */
	@Override
	public List<DossierAction> findAll(int start, int end,
		OrderByComparator<DossierAction> orderByComparator,
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

		List<DossierAction> list = null;

		if (retrieveFromCache) {
			list = (List<DossierAction>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOSSIERACTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOSSIERACTION;

				if (pagination) {
					sql = sql.concat(DossierActionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierAction>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the dossier actions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DossierAction dossierAction : findAll()) {
			remove(dossierAction);
		}
	}

	/**
	 * Returns the number of dossier actions.
	 *
	 * @return the number of dossier actions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOSSIERACTION);

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
		return DossierActionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dossier action persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DossierActionImpl.class.getName());
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
	private static final String _SQL_SELECT_DOSSIERACTION = "SELECT dossierAction FROM DossierAction dossierAction";
	private static final String _SQL_SELECT_DOSSIERACTION_WHERE_PKS_IN = "SELECT dossierAction FROM DossierAction dossierAction WHERE dossierActionId IN (";
	private static final String _SQL_SELECT_DOSSIERACTION_WHERE = "SELECT dossierAction FROM DossierAction dossierAction WHERE ";
	private static final String _SQL_COUNT_DOSSIERACTION = "SELECT COUNT(dossierAction) FROM DossierAction dossierAction";
	private static final String _SQL_COUNT_DOSSIERACTION_WHERE = "SELECT COUNT(dossierAction) FROM DossierAction dossierAction WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dossierAction.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DossierAction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DossierAction exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DossierActionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "state"
			});
}