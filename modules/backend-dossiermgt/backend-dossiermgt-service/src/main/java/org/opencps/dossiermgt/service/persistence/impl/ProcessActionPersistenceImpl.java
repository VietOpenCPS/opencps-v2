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

import org.opencps.dossiermgt.exception.NoSuchProcessActionException;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.impl.ProcessActionImpl;
import org.opencps.dossiermgt.model.impl.ProcessActionModelImpl;
import org.opencps.dossiermgt.service.persistence.ProcessActionPersistence;

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
 * The persistence implementation for the process action service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ProcessActionPersistence
 * @see org.opencps.dossiermgt.service.persistence.ProcessActionUtil
 * @generated
 */
@ProviderType
public class ProcessActionPersistenceImpl extends BasePersistenceImpl<ProcessAction>
	implements ProcessActionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProcessActionUtil} to access the process action persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProcessActionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			ProcessActionModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the process actions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching process actions
	 */
	@Override
	public List<ProcessAction> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process actions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @return the range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process actions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process actions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
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

		List<ProcessAction> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessAction processAction : list) {
					if (!Objects.equals(uuid, processAction.getUuid())) {
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

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

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
				query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
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
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process action in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByUuid_First(String uuid,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByUuid_First(uuid, orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the first process action in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByUuid_First(String uuid,
		OrderByComparator<ProcessAction> orderByComparator) {
		List<ProcessAction> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process action in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByUuid_Last(String uuid,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByUuid_Last(uuid, orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the last process action in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByUuid_Last(String uuid,
		OrderByComparator<ProcessAction> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ProcessAction> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process actions before and after the current process action in the ordered set where uuid = &#63;.
	 *
	 * @param processActionId the primary key of the current process action
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction[] findByUuid_PrevAndNext(long processActionId,
		String uuid, OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findByPrimaryKey(processActionId);

		Session session = null;

		try {
			session = openSession();

			ProcessAction[] array = new ProcessActionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, processAction, uuid,
					orderByComparator, true);

			array[1] = processAction;

			array[2] = getByUuid_PrevAndNext(session, processAction, uuid,
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

	protected ProcessAction getByUuid_PrevAndNext(Session session,
		ProcessAction processAction, String uuid,
		OrderByComparator<ProcessAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PROCESSACTION_WHERE);

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
			query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(processAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process actions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ProcessAction processAction : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(processAction);
		}
	}

	/**
	 * Returns the number of process actions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching process actions
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "processAction.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "processAction.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(processAction.uuid IS NULL OR processAction.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ProcessActionModelImpl.UUID_COLUMN_BITMASK |
			ProcessActionModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the process action where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessActionException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByUUID_G(String uuid, long groupId)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByUUID_G(uuid, groupId);

		if (processAction == null) {
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

			throw new NoSuchProcessActionException(msg.toString());
		}

		return processAction;
	}

	/**
	 * Returns the process action where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the process action where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ProcessAction) {
			ProcessAction processAction = (ProcessAction)result;

			if (!Objects.equals(uuid, processAction.getUuid()) ||
					(groupId != processAction.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

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

				List<ProcessAction> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ProcessAction processAction = list.get(0);

					result = processAction;

					cacheResult(processAction);
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
			return (ProcessAction)result;
		}
	}

	/**
	 * Removes the process action where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the process action that was removed
	 */
	@Override
	public ProcessAction removeByUUID_G(String uuid, long groupId)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findByUUID_G(uuid, groupId);

		return remove(processAction);
	}

	/**
	 * Returns the number of process actions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching process actions
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "processAction.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "processAction.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(processAction.uuid IS NULL OR processAction.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "processAction.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ProcessActionModelImpl.UUID_COLUMN_BITMASK |
			ProcessActionModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the process actions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching process actions
	 */
	@Override
	public List<ProcessAction> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process actions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @return the range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process actions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process actions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator,
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

		List<ProcessAction> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessAction processAction : list) {
					if (!Objects.equals(uuid, processAction.getUuid()) ||
							(companyId != processAction.getCompanyId())) {
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

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

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
				query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
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
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process action in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the first process action in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessAction> orderByComparator) {
		List<ProcessAction> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process action in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the last process action in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessAction> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ProcessAction> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process actions before and after the current process action in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param processActionId the primary key of the current process action
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction[] findByUuid_C_PrevAndNext(long processActionId,
		String uuid, long companyId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findByPrimaryKey(processActionId);

		Session session = null;

		try {
			session = openSession();

			ProcessAction[] array = new ProcessActionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, processAction, uuid,
					companyId, orderByComparator, true);

			array[1] = processAction;

			array[2] = getByUuid_C_PrevAndNext(session, processAction, uuid,
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

	protected ProcessAction getByUuid_C_PrevAndNext(Session session,
		ProcessAction processAction, String uuid, long companyId,
		OrderByComparator<ProcessAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PROCESSACTION_WHERE);

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
			query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(processAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process actions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ProcessAction processAction : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processAction);
		}
	}

	/**
	 * Returns the number of process actions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching process actions
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "processAction.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "processAction.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(processAction.uuid IS NULL OR processAction.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "processAction.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_S_P_ID = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByS_P_ID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_P_ID =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByS_P_ID", new String[] { Long.class.getName() },
			ProcessActionModelImpl.SERVICEPROCESSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_S_P_ID = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS_P_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the process actions where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @return the matching process actions
	 */
	@Override
	public List<ProcessAction> findByS_P_ID(long serviceProcessId) {
		return findByS_P_ID(serviceProcessId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process actions where serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @return the range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByS_P_ID(long serviceProcessId, int start,
		int end) {
		return findByS_P_ID(serviceProcessId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process actions where serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByS_P_ID(long serviceProcessId, int start,
		int end, OrderByComparator<ProcessAction> orderByComparator) {
		return findByS_P_ID(serviceProcessId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the process actions where serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByS_P_ID(long serviceProcessId, int start,
		int end, OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_P_ID;
			finderArgs = new Object[] { serviceProcessId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_S_P_ID;
			finderArgs = new Object[] {
					serviceProcessId,
					
					start, end, orderByComparator
				};
		}

		List<ProcessAction> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessAction processAction : list) {
					if ((serviceProcessId != processAction.getServiceProcessId())) {
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

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_S_P_ID_SERVICEPROCESSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceProcessId);

				if (!pagination) {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process action in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByS_P_ID_First(long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByS_P_ID_First(serviceProcessId,
				orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the first process action in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByS_P_ID_First(long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator) {
		List<ProcessAction> list = findByS_P_ID(serviceProcessId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process action in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByS_P_ID_Last(long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByS_P_ID_Last(serviceProcessId,
				orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the last process action in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByS_P_ID_Last(long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator) {
		int count = countByS_P_ID(serviceProcessId);

		if (count == 0) {
			return null;
		}

		List<ProcessAction> list = findByS_P_ID(serviceProcessId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process actions before and after the current process action in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param processActionId the primary key of the current process action
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction[] findByS_P_ID_PrevAndNext(long processActionId,
		long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findByPrimaryKey(processActionId);

		Session session = null;

		try {
			session = openSession();

			ProcessAction[] array = new ProcessActionImpl[3];

			array[0] = getByS_P_ID_PrevAndNext(session, processAction,
					serviceProcessId, orderByComparator, true);

			array[1] = processAction;

			array[2] = getByS_P_ID_PrevAndNext(session, processAction,
					serviceProcessId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessAction getByS_P_ID_PrevAndNext(Session session,
		ProcessAction processAction, long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PROCESSACTION_WHERE);

		query.append(_FINDER_COLUMN_S_P_ID_SERVICEPROCESSID_2);

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
			query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(serviceProcessId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process actions where serviceProcessId = &#63; from the database.
	 *
	 * @param serviceProcessId the service process ID
	 */
	@Override
	public void removeByS_P_ID(long serviceProcessId) {
		for (ProcessAction processAction : findByS_P_ID(serviceProcessId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processAction);
		}
	}

	/**
	 * Returns the number of process actions where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @return the number of matching process actions
	 */
	@Override
	public int countByS_P_ID(long serviceProcessId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_S_P_ID;

		Object[] finderArgs = new Object[] { serviceProcessId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_S_P_ID_SERVICEPROCESSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceProcessId);

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

	private static final String _FINDER_COLUMN_S_P_ID_SERVICEPROCESSID_2 = "processAction.serviceProcessId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GI_AC = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGI_AC",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_AC = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGI_AC",
			new String[] { Long.class.getName(), String.class.getName() },
			ProcessActionModelImpl.GROUPID_COLUMN_BITMASK |
			ProcessActionModelImpl.ACTIONCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GI_AC = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGI_AC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the process actions where groupId = &#63; and actionCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @return the matching process actions
	 */
	@Override
	public List<ProcessAction> findByGI_AC(long groupId, String actionCode) {
		return findByGI_AC(groupId, actionCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process actions where groupId = &#63; and actionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @return the range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByGI_AC(long groupId, String actionCode,
		int start, int end) {
		return findByGI_AC(groupId, actionCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process actions where groupId = &#63; and actionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByGI_AC(long groupId, String actionCode,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator) {
		return findByGI_AC(groupId, actionCode, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the process actions where groupId = &#63; and actionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByGI_AC(long groupId, String actionCode,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_AC;
			finderArgs = new Object[] { groupId, actionCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GI_AC;
			finderArgs = new Object[] {
					groupId, actionCode,
					
					start, end, orderByComparator
				};
		}

		List<ProcessAction> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessAction processAction : list) {
					if ((groupId != processAction.getGroupId()) ||
							!Objects.equals(actionCode,
								processAction.getActionCode())) {
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

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_GI_AC_GROUPID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_GI_AC_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_GI_AC_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_GI_AC_ACTIONCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
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

				if (!pagination) {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process action in the ordered set where groupId = &#63; and actionCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByGI_AC_First(long groupId, String actionCode,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByGI_AC_First(groupId, actionCode,
				orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", actionCode=");
		msg.append(actionCode);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the first process action in the ordered set where groupId = &#63; and actionCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByGI_AC_First(long groupId, String actionCode,
		OrderByComparator<ProcessAction> orderByComparator) {
		List<ProcessAction> list = findByGI_AC(groupId, actionCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process action in the ordered set where groupId = &#63; and actionCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByGI_AC_Last(long groupId, String actionCode,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByGI_AC_Last(groupId, actionCode,
				orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", actionCode=");
		msg.append(actionCode);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the last process action in the ordered set where groupId = &#63; and actionCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByGI_AC_Last(long groupId, String actionCode,
		OrderByComparator<ProcessAction> orderByComparator) {
		int count = countByGI_AC(groupId, actionCode);

		if (count == 0) {
			return null;
		}

		List<ProcessAction> list = findByGI_AC(groupId, actionCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process actions before and after the current process action in the ordered set where groupId = &#63; and actionCode = &#63;.
	 *
	 * @param processActionId the primary key of the current process action
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction[] findByGI_AC_PrevAndNext(long processActionId,
		long groupId, String actionCode,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findByPrimaryKey(processActionId);

		Session session = null;

		try {
			session = openSession();

			ProcessAction[] array = new ProcessActionImpl[3];

			array[0] = getByGI_AC_PrevAndNext(session, processAction, groupId,
					actionCode, orderByComparator, true);

			array[1] = processAction;

			array[2] = getByGI_AC_PrevAndNext(session, processAction, groupId,
					actionCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessAction getByGI_AC_PrevAndNext(Session session,
		ProcessAction processAction, long groupId, String actionCode,
		OrderByComparator<ProcessAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PROCESSACTION_WHERE);

		query.append(_FINDER_COLUMN_GI_AC_GROUPID_2);

		boolean bindActionCode = false;

		if (actionCode == null) {
			query.append(_FINDER_COLUMN_GI_AC_ACTIONCODE_1);
		}
		else if (actionCode.equals("")) {
			query.append(_FINDER_COLUMN_GI_AC_ACTIONCODE_3);
		}
		else {
			bindActionCode = true;

			query.append(_FINDER_COLUMN_GI_AC_ACTIONCODE_2);
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
			query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
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

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process actions where groupId = &#63; and actionCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 */
	@Override
	public void removeByGI_AC(long groupId, String actionCode) {
		for (ProcessAction processAction : findByGI_AC(groupId, actionCode,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processAction);
		}
	}

	/**
	 * Returns the number of process actions where groupId = &#63; and actionCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @return the number of matching process actions
	 */
	@Override
	public int countByGI_AC(long groupId, String actionCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GI_AC;

		Object[] finderArgs = new Object[] { groupId, actionCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_GI_AC_GROUPID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_GI_AC_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_GI_AC_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_GI_AC_ACTIONCODE_2);
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

	private static final String _FINDER_COLUMN_GI_AC_GROUPID_2 = "processAction.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GI_AC_ACTIONCODE_1 = "processAction.actionCode IS NULL";
	private static final String _FINDER_COLUMN_GI_AC_ACTIONCODE_2 = "processAction.actionCode = ?";
	private static final String _FINDER_COLUMN_GI_AC_ACTIONCODE_3 = "(processAction.actionCode IS NULL OR processAction.actionCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GI_AC_SP = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGI_AC_SP",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_AC_SP =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGI_AC_SP",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName()
			},
			ProcessActionModelImpl.GROUPID_COLUMN_BITMASK |
			ProcessActionModelImpl.ACTIONCODE_COLUMN_BITMASK |
			ProcessActionModelImpl.SERVICEPROCESSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GI_AC_SP = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGI_AC_SP",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param serviceProcessId the service process ID
	 * @return the matching process actions
	 */
	@Override
	public List<ProcessAction> findByGI_AC_SP(long groupId, String actionCode,
		long serviceProcessId) {
		return findByGI_AC_SP(groupId, actionCode, serviceProcessId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @return the range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByGI_AC_SP(long groupId, String actionCode,
		long serviceProcessId, int start, int end) {
		return findByGI_AC_SP(groupId, actionCode, serviceProcessId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByGI_AC_SP(long groupId, String actionCode,
		long serviceProcessId, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return findByGI_AC_SP(groupId, actionCode, serviceProcessId, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByGI_AC_SP(long groupId, String actionCode,
		long serviceProcessId, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_AC_SP;
			finderArgs = new Object[] { groupId, actionCode, serviceProcessId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GI_AC_SP;
			finderArgs = new Object[] {
					groupId, actionCode, serviceProcessId,
					
					start, end, orderByComparator
				};
		}

		List<ProcessAction> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessAction processAction : list) {
					if ((groupId != processAction.getGroupId()) ||
							!Objects.equals(actionCode,
								processAction.getActionCode()) ||
							(serviceProcessId != processAction.getServiceProcessId())) {
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

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_GI_AC_SP_GROUPID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_GI_AC_SP_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_GI_AC_SP_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_GI_AC_SP_ACTIONCODE_2);
			}

			query.append(_FINDER_COLUMN_GI_AC_SP_SERVICEPROCESSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
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

				qPos.add(serviceProcessId);

				if (!pagination) {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process action in the ordered set where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByGI_AC_SP_First(long groupId, String actionCode,
		long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByGI_AC_SP_First(groupId,
				actionCode, serviceProcessId, orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", actionCode=");
		msg.append(actionCode);

		msg.append(", serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the first process action in the ordered set where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByGI_AC_SP_First(long groupId, String actionCode,
		long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator) {
		List<ProcessAction> list = findByGI_AC_SP(groupId, actionCode,
				serviceProcessId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process action in the ordered set where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByGI_AC_SP_Last(long groupId, String actionCode,
		long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByGI_AC_SP_Last(groupId, actionCode,
				serviceProcessId, orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", actionCode=");
		msg.append(actionCode);

		msg.append(", serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the last process action in the ordered set where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByGI_AC_SP_Last(long groupId, String actionCode,
		long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator) {
		int count = countByGI_AC_SP(groupId, actionCode, serviceProcessId);

		if (count == 0) {
			return null;
		}

		List<ProcessAction> list = findByGI_AC_SP(groupId, actionCode,
				serviceProcessId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process actions before and after the current process action in the ordered set where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param processActionId the primary key of the current process action
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction[] findByGI_AC_SP_PrevAndNext(long processActionId,
		long groupId, String actionCode, long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findByPrimaryKey(processActionId);

		Session session = null;

		try {
			session = openSession();

			ProcessAction[] array = new ProcessActionImpl[3];

			array[0] = getByGI_AC_SP_PrevAndNext(session, processAction,
					groupId, actionCode, serviceProcessId, orderByComparator,
					true);

			array[1] = processAction;

			array[2] = getByGI_AC_SP_PrevAndNext(session, processAction,
					groupId, actionCode, serviceProcessId, orderByComparator,
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

	protected ProcessAction getByGI_AC_SP_PrevAndNext(Session session,
		ProcessAction processAction, long groupId, String actionCode,
		long serviceProcessId,
		OrderByComparator<ProcessAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_PROCESSACTION_WHERE);

		query.append(_FINDER_COLUMN_GI_AC_SP_GROUPID_2);

		boolean bindActionCode = false;

		if (actionCode == null) {
			query.append(_FINDER_COLUMN_GI_AC_SP_ACTIONCODE_1);
		}
		else if (actionCode.equals("")) {
			query.append(_FINDER_COLUMN_GI_AC_SP_ACTIONCODE_3);
		}
		else {
			bindActionCode = true;

			query.append(_FINDER_COLUMN_GI_AC_SP_ACTIONCODE_2);
		}

		query.append(_FINDER_COLUMN_GI_AC_SP_SERVICEPROCESSID_2);

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
			query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
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

		qPos.add(serviceProcessId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param serviceProcessId the service process ID
	 */
	@Override
	public void removeByGI_AC_SP(long groupId, String actionCode,
		long serviceProcessId) {
		for (ProcessAction processAction : findByGI_AC_SP(groupId, actionCode,
				serviceProcessId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processAction);
		}
	}

	/**
	 * Returns the number of process actions where groupId = &#63; and actionCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param serviceProcessId the service process ID
	 * @return the number of matching process actions
	 */
	@Override
	public int countByGI_AC_SP(long groupId, String actionCode,
		long serviceProcessId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GI_AC_SP;

		Object[] finderArgs = new Object[] { groupId, actionCode, serviceProcessId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_GI_AC_SP_GROUPID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_GI_AC_SP_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_GI_AC_SP_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_GI_AC_SP_ACTIONCODE_2);
			}

			query.append(_FINDER_COLUMN_GI_AC_SP_SERVICEPROCESSID_2);

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

				qPos.add(serviceProcessId);

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

	private static final String _FINDER_COLUMN_GI_AC_SP_GROUPID_2 = "processAction.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GI_AC_SP_ACTIONCODE_1 = "processAction.actionCode IS NULL AND ";
	private static final String _FINDER_COLUMN_GI_AC_SP_ACTIONCODE_2 = "processAction.actionCode = ? AND ";
	private static final String _FINDER_COLUMN_GI_AC_SP_ACTIONCODE_3 = "(processAction.actionCode IS NULL OR processAction.actionCode = '') AND ";
	private static final String _FINDER_COLUMN_GI_AC_SP_SERVICEPROCESSID_2 = "processAction.serviceProcessId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRE_CODE = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPRE_CODE",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRE_CODE =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPRE_CODE",
			new String[] { String.class.getName(), Long.class.getName() },
			ProcessActionModelImpl.PRESTEPCODE_COLUMN_BITMASK |
			ProcessActionModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRE_CODE = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPRE_CODE",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the process actions where preStepCode = &#63; and groupId = &#63;.
	 *
	 * @param preStepCode the pre step code
	 * @param groupId the group ID
	 * @return the matching process actions
	 */
	@Override
	public List<ProcessAction> findByPRE_CODE(String preStepCode, long groupId) {
		return findByPRE_CODE(preStepCode, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process actions where preStepCode = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param preStepCode the pre step code
	 * @param groupId the group ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @return the range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPRE_CODE(String preStepCode, long groupId,
		int start, int end) {
		return findByPRE_CODE(preStepCode, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process actions where preStepCode = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param preStepCode the pre step code
	 * @param groupId the group ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPRE_CODE(String preStepCode, long groupId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator) {
		return findByPRE_CODE(preStepCode, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process actions where preStepCode = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param preStepCode the pre step code
	 * @param groupId the group ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPRE_CODE(String preStepCode, long groupId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRE_CODE;
			finderArgs = new Object[] { preStepCode, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRE_CODE;
			finderArgs = new Object[] {
					preStepCode, groupId,
					
					start, end, orderByComparator
				};
		}

		List<ProcessAction> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessAction processAction : list) {
					if (!Objects.equals(preStepCode,
								processAction.getPreStepCode()) ||
							(groupId != processAction.getGroupId())) {
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

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

			boolean bindPreStepCode = false;

			if (preStepCode == null) {
				query.append(_FINDER_COLUMN_PRE_CODE_PRESTEPCODE_1);
			}
			else if (preStepCode.equals("")) {
				query.append(_FINDER_COLUMN_PRE_CODE_PRESTEPCODE_3);
			}
			else {
				bindPreStepCode = true;

				query.append(_FINDER_COLUMN_PRE_CODE_PRESTEPCODE_2);
			}

			query.append(_FINDER_COLUMN_PRE_CODE_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPreStepCode) {
					qPos.add(preStepCode);
				}

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process action in the ordered set where preStepCode = &#63; and groupId = &#63;.
	 *
	 * @param preStepCode the pre step code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByPRE_CODE_First(String preStepCode, long groupId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByPRE_CODE_First(preStepCode,
				groupId, orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("preStepCode=");
		msg.append(preStepCode);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the first process action in the ordered set where preStepCode = &#63; and groupId = &#63;.
	 *
	 * @param preStepCode the pre step code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByPRE_CODE_First(String preStepCode,
		long groupId, OrderByComparator<ProcessAction> orderByComparator) {
		List<ProcessAction> list = findByPRE_CODE(preStepCode, groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process action in the ordered set where preStepCode = &#63; and groupId = &#63;.
	 *
	 * @param preStepCode the pre step code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByPRE_CODE_Last(String preStepCode, long groupId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByPRE_CODE_Last(preStepCode,
				groupId, orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("preStepCode=");
		msg.append(preStepCode);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the last process action in the ordered set where preStepCode = &#63; and groupId = &#63;.
	 *
	 * @param preStepCode the pre step code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByPRE_CODE_Last(String preStepCode, long groupId,
		OrderByComparator<ProcessAction> orderByComparator) {
		int count = countByPRE_CODE(preStepCode, groupId);

		if (count == 0) {
			return null;
		}

		List<ProcessAction> list = findByPRE_CODE(preStepCode, groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process actions before and after the current process action in the ordered set where preStepCode = &#63; and groupId = &#63;.
	 *
	 * @param processActionId the primary key of the current process action
	 * @param preStepCode the pre step code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction[] findByPRE_CODE_PrevAndNext(long processActionId,
		String preStepCode, long groupId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findByPrimaryKey(processActionId);

		Session session = null;

		try {
			session = openSession();

			ProcessAction[] array = new ProcessActionImpl[3];

			array[0] = getByPRE_CODE_PrevAndNext(session, processAction,
					preStepCode, groupId, orderByComparator, true);

			array[1] = processAction;

			array[2] = getByPRE_CODE_PrevAndNext(session, processAction,
					preStepCode, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessAction getByPRE_CODE_PrevAndNext(Session session,
		ProcessAction processAction, String preStepCode, long groupId,
		OrderByComparator<ProcessAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PROCESSACTION_WHERE);

		boolean bindPreStepCode = false;

		if (preStepCode == null) {
			query.append(_FINDER_COLUMN_PRE_CODE_PRESTEPCODE_1);
		}
		else if (preStepCode.equals("")) {
			query.append(_FINDER_COLUMN_PRE_CODE_PRESTEPCODE_3);
		}
		else {
			bindPreStepCode = true;

			query.append(_FINDER_COLUMN_PRE_CODE_PRESTEPCODE_2);
		}

		query.append(_FINDER_COLUMN_PRE_CODE_GROUPID_2);

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
			query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPreStepCode) {
			qPos.add(preStepCode);
		}

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process actions where preStepCode = &#63; and groupId = &#63; from the database.
	 *
	 * @param preStepCode the pre step code
	 * @param groupId the group ID
	 */
	@Override
	public void removeByPRE_CODE(String preStepCode, long groupId) {
		for (ProcessAction processAction : findByPRE_CODE(preStepCode, groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processAction);
		}
	}

	/**
	 * Returns the number of process actions where preStepCode = &#63; and groupId = &#63;.
	 *
	 * @param preStepCode the pre step code
	 * @param groupId the group ID
	 * @return the number of matching process actions
	 */
	@Override
	public int countByPRE_CODE(String preStepCode, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PRE_CODE;

		Object[] finderArgs = new Object[] { preStepCode, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

			boolean bindPreStepCode = false;

			if (preStepCode == null) {
				query.append(_FINDER_COLUMN_PRE_CODE_PRESTEPCODE_1);
			}
			else if (preStepCode.equals("")) {
				query.append(_FINDER_COLUMN_PRE_CODE_PRESTEPCODE_3);
			}
			else {
				bindPreStepCode = true;

				query.append(_FINDER_COLUMN_PRE_CODE_PRESTEPCODE_2);
			}

			query.append(_FINDER_COLUMN_PRE_CODE_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPreStepCode) {
					qPos.add(preStepCode);
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

	private static final String _FINDER_COLUMN_PRE_CODE_PRESTEPCODE_1 = "processAction.preStepCode IS NULL AND ";
	private static final String _FINDER_COLUMN_PRE_CODE_PRESTEPCODE_2 = "processAction.preStepCode = ? AND ";
	private static final String _FINDER_COLUMN_PRE_CODE_PRESTEPCODE_3 = "(processAction.preStepCode IS NULL OR processAction.preStepCode = '') AND ";
	private static final String _FINDER_COLUMN_PRE_CODE_GROUPID_2 = "processAction.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_POST_CODE =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPOST_CODE",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POST_CODE =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPOST_CODE",
			new String[] { String.class.getName(), Long.class.getName() },
			ProcessActionModelImpl.POSTSTEPCODE_COLUMN_BITMASK |
			ProcessActionModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_POST_CODE = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPOST_CODE",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the process actions where postStepCode = &#63; and groupId = &#63;.
	 *
	 * @param postStepCode the post step code
	 * @param groupId the group ID
	 * @return the matching process actions
	 */
	@Override
	public List<ProcessAction> findByPOST_CODE(String postStepCode, long groupId) {
		return findByPOST_CODE(postStepCode, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process actions where postStepCode = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param postStepCode the post step code
	 * @param groupId the group ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @return the range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPOST_CODE(String postStepCode,
		long groupId, int start, int end) {
		return findByPOST_CODE(postStepCode, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process actions where postStepCode = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param postStepCode the post step code
	 * @param groupId the group ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPOST_CODE(String postStepCode,
		long groupId, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return findByPOST_CODE(postStepCode, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process actions where postStepCode = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param postStepCode the post step code
	 * @param groupId the group ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPOST_CODE(String postStepCode,
		long groupId, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POST_CODE;
			finderArgs = new Object[] { postStepCode, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_POST_CODE;
			finderArgs = new Object[] {
					postStepCode, groupId,
					
					start, end, orderByComparator
				};
		}

		List<ProcessAction> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessAction processAction : list) {
					if (!Objects.equals(postStepCode,
								processAction.getPostStepCode()) ||
							(groupId != processAction.getGroupId())) {
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

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

			boolean bindPostStepCode = false;

			if (postStepCode == null) {
				query.append(_FINDER_COLUMN_POST_CODE_POSTSTEPCODE_1);
			}
			else if (postStepCode.equals("")) {
				query.append(_FINDER_COLUMN_POST_CODE_POSTSTEPCODE_3);
			}
			else {
				bindPostStepCode = true;

				query.append(_FINDER_COLUMN_POST_CODE_POSTSTEPCODE_2);
			}

			query.append(_FINDER_COLUMN_POST_CODE_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPostStepCode) {
					qPos.add(postStepCode);
				}

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process action in the ordered set where postStepCode = &#63; and groupId = &#63;.
	 *
	 * @param postStepCode the post step code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByPOST_CODE_First(String postStepCode,
		long groupId, OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByPOST_CODE_First(postStepCode,
				groupId, orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("postStepCode=");
		msg.append(postStepCode);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the first process action in the ordered set where postStepCode = &#63; and groupId = &#63;.
	 *
	 * @param postStepCode the post step code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByPOST_CODE_First(String postStepCode,
		long groupId, OrderByComparator<ProcessAction> orderByComparator) {
		List<ProcessAction> list = findByPOST_CODE(postStepCode, groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process action in the ordered set where postStepCode = &#63; and groupId = &#63;.
	 *
	 * @param postStepCode the post step code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByPOST_CODE_Last(String postStepCode,
		long groupId, OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByPOST_CODE_Last(postStepCode,
				groupId, orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("postStepCode=");
		msg.append(postStepCode);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the last process action in the ordered set where postStepCode = &#63; and groupId = &#63;.
	 *
	 * @param postStepCode the post step code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByPOST_CODE_Last(String postStepCode,
		long groupId, OrderByComparator<ProcessAction> orderByComparator) {
		int count = countByPOST_CODE(postStepCode, groupId);

		if (count == 0) {
			return null;
		}

		List<ProcessAction> list = findByPOST_CODE(postStepCode, groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process actions before and after the current process action in the ordered set where postStepCode = &#63; and groupId = &#63;.
	 *
	 * @param processActionId the primary key of the current process action
	 * @param postStepCode the post step code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction[] findByPOST_CODE_PrevAndNext(long processActionId,
		String postStepCode, long groupId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findByPrimaryKey(processActionId);

		Session session = null;

		try {
			session = openSession();

			ProcessAction[] array = new ProcessActionImpl[3];

			array[0] = getByPOST_CODE_PrevAndNext(session, processAction,
					postStepCode, groupId, orderByComparator, true);

			array[1] = processAction;

			array[2] = getByPOST_CODE_PrevAndNext(session, processAction,
					postStepCode, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessAction getByPOST_CODE_PrevAndNext(Session session,
		ProcessAction processAction, String postStepCode, long groupId,
		OrderByComparator<ProcessAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PROCESSACTION_WHERE);

		boolean bindPostStepCode = false;

		if (postStepCode == null) {
			query.append(_FINDER_COLUMN_POST_CODE_POSTSTEPCODE_1);
		}
		else if (postStepCode.equals("")) {
			query.append(_FINDER_COLUMN_POST_CODE_POSTSTEPCODE_3);
		}
		else {
			bindPostStepCode = true;

			query.append(_FINDER_COLUMN_POST_CODE_POSTSTEPCODE_2);
		}

		query.append(_FINDER_COLUMN_POST_CODE_GROUPID_2);

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
			query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPostStepCode) {
			qPos.add(postStepCode);
		}

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process actions where postStepCode = &#63; and groupId = &#63; from the database.
	 *
	 * @param postStepCode the post step code
	 * @param groupId the group ID
	 */
	@Override
	public void removeByPOST_CODE(String postStepCode, long groupId) {
		for (ProcessAction processAction : findByPOST_CODE(postStepCode,
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processAction);
		}
	}

	/**
	 * Returns the number of process actions where postStepCode = &#63; and groupId = &#63;.
	 *
	 * @param postStepCode the post step code
	 * @param groupId the group ID
	 * @return the number of matching process actions
	 */
	@Override
	public int countByPOST_CODE(String postStepCode, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_POST_CODE;

		Object[] finderArgs = new Object[] { postStepCode, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

			boolean bindPostStepCode = false;

			if (postStepCode == null) {
				query.append(_FINDER_COLUMN_POST_CODE_POSTSTEPCODE_1);
			}
			else if (postStepCode.equals("")) {
				query.append(_FINDER_COLUMN_POST_CODE_POSTSTEPCODE_3);
			}
			else {
				bindPostStepCode = true;

				query.append(_FINDER_COLUMN_POST_CODE_POSTSTEPCODE_2);
			}

			query.append(_FINDER_COLUMN_POST_CODE_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPostStepCode) {
					qPos.add(postStepCode);
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

	private static final String _FINDER_COLUMN_POST_CODE_POSTSTEPCODE_1 = "processAction.postStepCode IS NULL AND ";
	private static final String _FINDER_COLUMN_POST_CODE_POSTSTEPCODE_2 = "processAction.postStepCode = ? AND ";
	private static final String _FINDER_COLUMN_POST_CODE_POSTSTEPCODE_3 = "(processAction.postStepCode IS NULL OR processAction.postStepCode = '') AND ";
	private static final String _FINDER_COLUMN_POST_CODE_GROUPID_2 = "processAction.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_SPI_PRESC_AEV = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySPI_PRESC_AEV",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			ProcessActionModelImpl.SERVICEPROCESSID_COLUMN_BITMASK |
			ProcessActionModelImpl.PRESTEPCODE_COLUMN_BITMASK |
			ProcessActionModelImpl.AUTOEVENT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SPI_PRESC_AEV = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySPI_PRESC_AEV",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the process action where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63; or throws a {@link NoSuchProcessActionException} if it could not be found.
	 *
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param autoEvent the auto event
	 * @return the matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findBySPI_PRESC_AEV(long serviceProcessId,
		String preStepCode, String autoEvent)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchBySPI_PRESC_AEV(serviceProcessId,
				preStepCode, autoEvent);

		if (processAction == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("serviceProcessId=");
			msg.append(serviceProcessId);

			msg.append(", preStepCode=");
			msg.append(preStepCode);

			msg.append(", autoEvent=");
			msg.append(autoEvent);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchProcessActionException(msg.toString());
		}

		return processAction;
	}

	/**
	 * Returns the process action where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param autoEvent the auto event
	 * @return the matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchBySPI_PRESC_AEV(long serviceProcessId,
		String preStepCode, String autoEvent) {
		return fetchBySPI_PRESC_AEV(serviceProcessId, preStepCode, autoEvent,
			true);
	}

	/**
	 * Returns the process action where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param autoEvent the auto event
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchBySPI_PRESC_AEV(long serviceProcessId,
		String preStepCode, String autoEvent, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				serviceProcessId, preStepCode, autoEvent
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SPI_PRESC_AEV,
					finderArgs, this);
		}

		if (result instanceof ProcessAction) {
			ProcessAction processAction = (ProcessAction)result;

			if ((serviceProcessId != processAction.getServiceProcessId()) ||
					!Objects.equals(preStepCode, processAction.getPreStepCode()) ||
					!Objects.equals(autoEvent, processAction.getAutoEvent())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_SPI_PRESC_AEV_SERVICEPROCESSID_2);

			boolean bindPreStepCode = false;

			if (preStepCode == null) {
				query.append(_FINDER_COLUMN_SPI_PRESC_AEV_PRESTEPCODE_1);
			}
			else if (preStepCode.equals("")) {
				query.append(_FINDER_COLUMN_SPI_PRESC_AEV_PRESTEPCODE_3);
			}
			else {
				bindPreStepCode = true;

				query.append(_FINDER_COLUMN_SPI_PRESC_AEV_PRESTEPCODE_2);
			}

			boolean bindAutoEvent = false;

			if (autoEvent == null) {
				query.append(_FINDER_COLUMN_SPI_PRESC_AEV_AUTOEVENT_1);
			}
			else if (autoEvent.equals("")) {
				query.append(_FINDER_COLUMN_SPI_PRESC_AEV_AUTOEVENT_3);
			}
			else {
				bindAutoEvent = true;

				query.append(_FINDER_COLUMN_SPI_PRESC_AEV_AUTOEVENT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceProcessId);

				if (bindPreStepCode) {
					qPos.add(preStepCode);
				}

				if (bindAutoEvent) {
					qPos.add(autoEvent);
				}

				List<ProcessAction> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SPI_PRESC_AEV,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ProcessActionPersistenceImpl.fetchBySPI_PRESC_AEV(long, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProcessAction processAction = list.get(0);

					result = processAction;

					cacheResult(processAction);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SPI_PRESC_AEV,
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
			return (ProcessAction)result;
		}
	}

	/**
	 * Removes the process action where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63; from the database.
	 *
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param autoEvent the auto event
	 * @return the process action that was removed
	 */
	@Override
	public ProcessAction removeBySPI_PRESC_AEV(long serviceProcessId,
		String preStepCode, String autoEvent)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findBySPI_PRESC_AEV(serviceProcessId,
				preStepCode, autoEvent);

		return remove(processAction);
	}

	/**
	 * Returns the number of process actions where serviceProcessId = &#63; and preStepCode = &#63; and autoEvent = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param autoEvent the auto event
	 * @return the number of matching process actions
	 */
	@Override
	public int countBySPI_PRESC_AEV(long serviceProcessId, String preStepCode,
		String autoEvent) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SPI_PRESC_AEV;

		Object[] finderArgs = new Object[] {
				serviceProcessId, preStepCode, autoEvent
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_SPI_PRESC_AEV_SERVICEPROCESSID_2);

			boolean bindPreStepCode = false;

			if (preStepCode == null) {
				query.append(_FINDER_COLUMN_SPI_PRESC_AEV_PRESTEPCODE_1);
			}
			else if (preStepCode.equals("")) {
				query.append(_FINDER_COLUMN_SPI_PRESC_AEV_PRESTEPCODE_3);
			}
			else {
				bindPreStepCode = true;

				query.append(_FINDER_COLUMN_SPI_PRESC_AEV_PRESTEPCODE_2);
			}

			boolean bindAutoEvent = false;

			if (autoEvent == null) {
				query.append(_FINDER_COLUMN_SPI_PRESC_AEV_AUTOEVENT_1);
			}
			else if (autoEvent.equals("")) {
				query.append(_FINDER_COLUMN_SPI_PRESC_AEV_AUTOEVENT_3);
			}
			else {
				bindAutoEvent = true;

				query.append(_FINDER_COLUMN_SPI_PRESC_AEV_AUTOEVENT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceProcessId);

				if (bindPreStepCode) {
					qPos.add(preStepCode);
				}

				if (bindAutoEvent) {
					qPos.add(autoEvent);
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

	private static final String _FINDER_COLUMN_SPI_PRESC_AEV_SERVICEPROCESSID_2 = "processAction.serviceProcessId = ? AND ";
	private static final String _FINDER_COLUMN_SPI_PRESC_AEV_PRESTEPCODE_1 = "processAction.preStepCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SPI_PRESC_AEV_PRESTEPCODE_2 = "processAction.preStepCode = ? AND ";
	private static final String _FINDER_COLUMN_SPI_PRESC_AEV_PRESTEPCODE_3 = "(processAction.preStepCode IS NULL OR processAction.preStepCode = '') AND ";
	private static final String _FINDER_COLUMN_SPI_PRESC_AEV_AUTOEVENT_1 = "processAction.autoEvent IS NULL";
	private static final String _FINDER_COLUMN_SPI_PRESC_AEV_AUTOEVENT_2 = "processAction.autoEvent = ?";
	private static final String _FINDER_COLUMN_SPI_PRESC_AEV_AUTOEVENT_3 = "(processAction.autoEvent IS NULL OR processAction.autoEvent = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_SPID_AC = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySPID_AC",
			new String[] { Long.class.getName(), String.class.getName() },
			ProcessActionModelImpl.SERVICEPROCESSID_COLUMN_BITMASK |
			ProcessActionModelImpl.ACTIONCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SPID_AC = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySPID_AC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the process action where serviceProcessId = &#63; and actionCode = &#63; or throws a {@link NoSuchProcessActionException} if it could not be found.
	 *
	 * @param serviceProcessId the service process ID
	 * @param actionCode the action code
	 * @return the matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findBySPID_AC(long serviceProcessId, String actionCode)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchBySPID_AC(serviceProcessId,
				actionCode);

		if (processAction == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("serviceProcessId=");
			msg.append(serviceProcessId);

			msg.append(", actionCode=");
			msg.append(actionCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchProcessActionException(msg.toString());
		}

		return processAction;
	}

	/**
	 * Returns the process action where serviceProcessId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param serviceProcessId the service process ID
	 * @param actionCode the action code
	 * @return the matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchBySPID_AC(long serviceProcessId, String actionCode) {
		return fetchBySPID_AC(serviceProcessId, actionCode, true);
	}

	/**
	 * Returns the process action where serviceProcessId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param serviceProcessId the service process ID
	 * @param actionCode the action code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchBySPID_AC(long serviceProcessId,
		String actionCode, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { serviceProcessId, actionCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SPID_AC,
					finderArgs, this);
		}

		if (result instanceof ProcessAction) {
			ProcessAction processAction = (ProcessAction)result;

			if ((serviceProcessId != processAction.getServiceProcessId()) ||
					!Objects.equals(actionCode, processAction.getActionCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_SPID_AC_SERVICEPROCESSID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_SPID_AC_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_SPID_AC_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_SPID_AC_ACTIONCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceProcessId);

				if (bindActionCode) {
					qPos.add(actionCode);
				}

				List<ProcessAction> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SPID_AC,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ProcessActionPersistenceImpl.fetchBySPID_AC(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProcessAction processAction = list.get(0);

					result = processAction;

					cacheResult(processAction);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SPID_AC,
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
			return (ProcessAction)result;
		}
	}

	/**
	 * Removes the process action where serviceProcessId = &#63; and actionCode = &#63; from the database.
	 *
	 * @param serviceProcessId the service process ID
	 * @param actionCode the action code
	 * @return the process action that was removed
	 */
	@Override
	public ProcessAction removeBySPID_AC(long serviceProcessId,
		String actionCode) throws NoSuchProcessActionException {
		ProcessAction processAction = findBySPID_AC(serviceProcessId, actionCode);

		return remove(processAction);
	}

	/**
	 * Returns the number of process actions where serviceProcessId = &#63; and actionCode = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param actionCode the action code
	 * @return the number of matching process actions
	 */
	@Override
	public int countBySPID_AC(long serviceProcessId, String actionCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SPID_AC;

		Object[] finderArgs = new Object[] { serviceProcessId, actionCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_SPID_AC_SERVICEPROCESSID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_SPID_AC_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_SPID_AC_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_SPID_AC_ACTIONCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceProcessId);

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

	private static final String _FINDER_COLUMN_SPID_AC_SERVICEPROCESSID_2 = "processAction.serviceProcessId = ? AND ";
	private static final String _FINDER_COLUMN_SPID_AC_ACTIONCODE_1 = "processAction.actionCode IS NULL";
	private static final String _FINDER_COLUMN_SPID_AC_ACTIONCODE_2 = "processAction.actionCode = ?";
	private static final String _FINDER_COLUMN_SPID_AC_ACTIONCODE_3 = "(processAction.actionCode IS NULL OR processAction.actionCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_SPID_PRESC =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_SPID_PRESC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SPID_PRESC =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByG_SPID_PRESC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			ProcessActionModelImpl.GROUPID_COLUMN_BITMASK |
			ProcessActionModelImpl.SERVICEPROCESSID_COLUMN_BITMASK |
			ProcessActionModelImpl.PRESTEPCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_SPID_PRESC = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_SPID_PRESC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @return the matching process actions
	 */
	@Override
	public List<ProcessAction> findByG_SPID_PRESC(long groupId,
		long serviceProcessId, String preStepCode) {
		return findByG_SPID_PRESC(groupId, serviceProcessId, preStepCode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @return the range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByG_SPID_PRESC(long groupId,
		long serviceProcessId, String preStepCode, int start, int end) {
		return findByG_SPID_PRESC(groupId, serviceProcessId, preStepCode,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByG_SPID_PRESC(long groupId,
		long serviceProcessId, String preStepCode, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return findByG_SPID_PRESC(groupId, serviceProcessId, preStepCode,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByG_SPID_PRESC(long groupId,
		long serviceProcessId, String preStepCode, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SPID_PRESC;
			finderArgs = new Object[] { groupId, serviceProcessId, preStepCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_SPID_PRESC;
			finderArgs = new Object[] {
					groupId, serviceProcessId, preStepCode,
					
					start, end, orderByComparator
				};
		}

		List<ProcessAction> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessAction processAction : list) {
					if ((groupId != processAction.getGroupId()) ||
							(serviceProcessId != processAction.getServiceProcessId()) ||
							!Objects.equals(preStepCode,
								processAction.getPreStepCode())) {
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

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_G_SPID_PRESC_GROUPID_2);

			query.append(_FINDER_COLUMN_G_SPID_PRESC_SERVICEPROCESSID_2);

			boolean bindPreStepCode = false;

			if (preStepCode == null) {
				query.append(_FINDER_COLUMN_G_SPID_PRESC_PRESTEPCODE_1);
			}
			else if (preStepCode.equals("")) {
				query.append(_FINDER_COLUMN_G_SPID_PRESC_PRESTEPCODE_3);
			}
			else {
				bindPreStepCode = true;

				query.append(_FINDER_COLUMN_G_SPID_PRESC_PRESTEPCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceProcessId);

				if (bindPreStepCode) {
					qPos.add(preStepCode);
				}

				if (!pagination) {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByG_SPID_PRESC_First(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByG_SPID_PRESC_First(groupId,
				serviceProcessId, preStepCode, orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append(", preStepCode=");
		msg.append(preStepCode);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the first process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByG_SPID_PRESC_First(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator) {
		List<ProcessAction> list = findByG_SPID_PRESC(groupId,
				serviceProcessId, preStepCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByG_SPID_PRESC_Last(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByG_SPID_PRESC_Last(groupId,
				serviceProcessId, preStepCode, orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append(", preStepCode=");
		msg.append(preStepCode);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the last process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByG_SPID_PRESC_Last(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator) {
		int count = countByG_SPID_PRESC(groupId, serviceProcessId, preStepCode);

		if (count == 0) {
			return null;
		}

		List<ProcessAction> list = findByG_SPID_PRESC(groupId,
				serviceProcessId, preStepCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process actions before and after the current process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * @param processActionId the primary key of the current process action
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction[] findByG_SPID_PRESC_PrevAndNext(
		long processActionId, long groupId, long serviceProcessId,
		String preStepCode, OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findByPrimaryKey(processActionId);

		Session session = null;

		try {
			session = openSession();

			ProcessAction[] array = new ProcessActionImpl[3];

			array[0] = getByG_SPID_PRESC_PrevAndNext(session, processAction,
					groupId, serviceProcessId, preStepCode, orderByComparator,
					true);

			array[1] = processAction;

			array[2] = getByG_SPID_PRESC_PrevAndNext(session, processAction,
					groupId, serviceProcessId, preStepCode, orderByComparator,
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

	protected ProcessAction getByG_SPID_PRESC_PrevAndNext(Session session,
		ProcessAction processAction, long groupId, long serviceProcessId,
		String preStepCode, OrderByComparator<ProcessAction> orderByComparator,
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

		query.append(_SQL_SELECT_PROCESSACTION_WHERE);

		query.append(_FINDER_COLUMN_G_SPID_PRESC_GROUPID_2);

		query.append(_FINDER_COLUMN_G_SPID_PRESC_SERVICEPROCESSID_2);

		boolean bindPreStepCode = false;

		if (preStepCode == null) {
			query.append(_FINDER_COLUMN_G_SPID_PRESC_PRESTEPCODE_1);
		}
		else if (preStepCode.equals("")) {
			query.append(_FINDER_COLUMN_G_SPID_PRESC_PRESTEPCODE_3);
		}
		else {
			bindPreStepCode = true;

			query.append(_FINDER_COLUMN_G_SPID_PRESC_PRESTEPCODE_2);
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
			query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(serviceProcessId);

		if (bindPreStepCode) {
			qPos.add(preStepCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 */
	@Override
	public void removeByG_SPID_PRESC(long groupId, long serviceProcessId,
		String preStepCode) {
		for (ProcessAction processAction : findByG_SPID_PRESC(groupId,
				serviceProcessId, preStepCode, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(processAction);
		}
	}

	/**
	 * Returns the number of process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @return the number of matching process actions
	 */
	@Override
	public int countByG_SPID_PRESC(long groupId, long serviceProcessId,
		String preStepCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_SPID_PRESC;

		Object[] finderArgs = new Object[] {
				groupId, serviceProcessId, preStepCode
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_G_SPID_PRESC_GROUPID_2);

			query.append(_FINDER_COLUMN_G_SPID_PRESC_SERVICEPROCESSID_2);

			boolean bindPreStepCode = false;

			if (preStepCode == null) {
				query.append(_FINDER_COLUMN_G_SPID_PRESC_PRESTEPCODE_1);
			}
			else if (preStepCode.equals("")) {
				query.append(_FINDER_COLUMN_G_SPID_PRESC_PRESTEPCODE_3);
			}
			else {
				bindPreStepCode = true;

				query.append(_FINDER_COLUMN_G_SPID_PRESC_PRESTEPCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceProcessId);

				if (bindPreStepCode) {
					qPos.add(preStepCode);
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

	private static final String _FINDER_COLUMN_G_SPID_PRESC_GROUPID_2 = "processAction.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_SPID_PRESC_SERVICEPROCESSID_2 = "processAction.serviceProcessId = ? AND ";
	private static final String _FINDER_COLUMN_G_SPID_PRESC_PRESTEPCODE_1 = "processAction.preStepCode IS NULL";
	private static final String _FINDER_COLUMN_G_SPID_PRESC_PRESTEPCODE_2 = "processAction.preStepCode = ?";
	private static final String _FINDER_COLUMN_G_SPID_PRESC_PRESTEPCODE_3 = "(processAction.preStepCode IS NULL OR processAction.preStepCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PSC_AEV = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPSC_AEV",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSC_AEV =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPSC_AEV", new String[] { String.class.getName() },
			ProcessActionModelImpl.AUTOEVENT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PSC_AEV = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPSC_AEV",
			new String[] { String.class.getName() });

	/**
	 * Returns all the process actions where autoEvent = &#63;.
	 *
	 * @param autoEvent the auto event
	 * @return the matching process actions
	 */
	@Override
	public List<ProcessAction> findByPSC_AEV(String autoEvent) {
		return findByPSC_AEV(autoEvent, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the process actions where autoEvent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param autoEvent the auto event
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @return the range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPSC_AEV(String autoEvent, int start,
		int end) {
		return findByPSC_AEV(autoEvent, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process actions where autoEvent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param autoEvent the auto event
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPSC_AEV(String autoEvent, int start,
		int end, OrderByComparator<ProcessAction> orderByComparator) {
		return findByPSC_AEV(autoEvent, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process actions where autoEvent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param autoEvent the auto event
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPSC_AEV(String autoEvent, int start,
		int end, OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSC_AEV;
			finderArgs = new Object[] { autoEvent };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PSC_AEV;
			finderArgs = new Object[] { autoEvent, start, end, orderByComparator };
		}

		List<ProcessAction> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessAction processAction : list) {
					if (!Objects.equals(autoEvent, processAction.getAutoEvent())) {
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

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

			boolean bindAutoEvent = false;

			if (autoEvent == null) {
				query.append(_FINDER_COLUMN_PSC_AEV_AUTOEVENT_1);
			}
			else if (autoEvent.equals("")) {
				query.append(_FINDER_COLUMN_PSC_AEV_AUTOEVENT_3);
			}
			else {
				bindAutoEvent = true;

				query.append(_FINDER_COLUMN_PSC_AEV_AUTOEVENT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAutoEvent) {
					qPos.add(autoEvent);
				}

				if (!pagination) {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process action in the ordered set where autoEvent = &#63;.
	 *
	 * @param autoEvent the auto event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByPSC_AEV_First(String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByPSC_AEV_First(autoEvent,
				orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("autoEvent=");
		msg.append(autoEvent);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the first process action in the ordered set where autoEvent = &#63;.
	 *
	 * @param autoEvent the auto event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByPSC_AEV_First(String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator) {
		List<ProcessAction> list = findByPSC_AEV(autoEvent, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process action in the ordered set where autoEvent = &#63;.
	 *
	 * @param autoEvent the auto event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByPSC_AEV_Last(String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByPSC_AEV_Last(autoEvent,
				orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("autoEvent=");
		msg.append(autoEvent);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the last process action in the ordered set where autoEvent = &#63;.
	 *
	 * @param autoEvent the auto event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByPSC_AEV_Last(String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator) {
		int count = countByPSC_AEV(autoEvent);

		if (count == 0) {
			return null;
		}

		List<ProcessAction> list = findByPSC_AEV(autoEvent, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process actions before and after the current process action in the ordered set where autoEvent = &#63;.
	 *
	 * @param processActionId the primary key of the current process action
	 * @param autoEvent the auto event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction[] findByPSC_AEV_PrevAndNext(long processActionId,
		String autoEvent, OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findByPrimaryKey(processActionId);

		Session session = null;

		try {
			session = openSession();

			ProcessAction[] array = new ProcessActionImpl[3];

			array[0] = getByPSC_AEV_PrevAndNext(session, processAction,
					autoEvent, orderByComparator, true);

			array[1] = processAction;

			array[2] = getByPSC_AEV_PrevAndNext(session, processAction,
					autoEvent, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessAction getByPSC_AEV_PrevAndNext(Session session,
		ProcessAction processAction, String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PROCESSACTION_WHERE);

		boolean bindAutoEvent = false;

		if (autoEvent == null) {
			query.append(_FINDER_COLUMN_PSC_AEV_AUTOEVENT_1);
		}
		else if (autoEvent.equals("")) {
			query.append(_FINDER_COLUMN_PSC_AEV_AUTOEVENT_3);
		}
		else {
			bindAutoEvent = true;

			query.append(_FINDER_COLUMN_PSC_AEV_AUTOEVENT_2);
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
			query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindAutoEvent) {
			qPos.add(autoEvent);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process actions where autoEvent = &#63; from the database.
	 *
	 * @param autoEvent the auto event
	 */
	@Override
	public void removeByPSC_AEV(String autoEvent) {
		for (ProcessAction processAction : findByPSC_AEV(autoEvent,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processAction);
		}
	}

	/**
	 * Returns the number of process actions where autoEvent = &#63;.
	 *
	 * @param autoEvent the auto event
	 * @return the number of matching process actions
	 */
	@Override
	public int countByPSC_AEV(String autoEvent) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PSC_AEV;

		Object[] finderArgs = new Object[] { autoEvent };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

			boolean bindAutoEvent = false;

			if (autoEvent == null) {
				query.append(_FINDER_COLUMN_PSC_AEV_AUTOEVENT_1);
			}
			else if (autoEvent.equals("")) {
				query.append(_FINDER_COLUMN_PSC_AEV_AUTOEVENT_3);
			}
			else {
				bindAutoEvent = true;

				query.append(_FINDER_COLUMN_PSC_AEV_AUTOEVENT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAutoEvent) {
					qPos.add(autoEvent);
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

	private static final String _FINDER_COLUMN_PSC_AEV_AUTOEVENT_1 = "processAction.autoEvent IS NULL";
	private static final String _FINDER_COLUMN_PSC_AEV_AUTOEVENT_2 = "processAction.autoEvent = ?";
	private static final String _FINDER_COLUMN_PSC_AEV_AUTOEVENT_3 = "(processAction.autoEvent IS NULL OR processAction.autoEvent = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PSC_AEV_GI =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPSC_AEV_GI",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSC_AEV_GI =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPSC_AEV_GI",
			new String[] { Long.class.getName(), String.class.getName() },
			ProcessActionModelImpl.GROUPID_COLUMN_BITMASK |
			ProcessActionModelImpl.AUTOEVENT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PSC_AEV_GI = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPSC_AEV_GI",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the process actions where groupId = &#63; and autoEvent = &#63;.
	 *
	 * @param groupId the group ID
	 * @param autoEvent the auto event
	 * @return the matching process actions
	 */
	@Override
	public List<ProcessAction> findByPSC_AEV_GI(long groupId, String autoEvent) {
		return findByPSC_AEV_GI(groupId, autoEvent, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process actions where groupId = &#63; and autoEvent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param autoEvent the auto event
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @return the range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPSC_AEV_GI(long groupId, String autoEvent,
		int start, int end) {
		return findByPSC_AEV_GI(groupId, autoEvent, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process actions where groupId = &#63; and autoEvent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param autoEvent the auto event
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPSC_AEV_GI(long groupId, String autoEvent,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator) {
		return findByPSC_AEV_GI(groupId, autoEvent, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process actions where groupId = &#63; and autoEvent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param autoEvent the auto event
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPSC_AEV_GI(long groupId, String autoEvent,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSC_AEV_GI;
			finderArgs = new Object[] { groupId, autoEvent };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PSC_AEV_GI;
			finderArgs = new Object[] {
					groupId, autoEvent,
					
					start, end, orderByComparator
				};
		}

		List<ProcessAction> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessAction processAction : list) {
					if ((groupId != processAction.getGroupId()) ||
							!Objects.equals(autoEvent,
								processAction.getAutoEvent())) {
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

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_PSC_AEV_GI_GROUPID_2);

			boolean bindAutoEvent = false;

			if (autoEvent == null) {
				query.append(_FINDER_COLUMN_PSC_AEV_GI_AUTOEVENT_1);
			}
			else if (autoEvent.equals("")) {
				query.append(_FINDER_COLUMN_PSC_AEV_GI_AUTOEVENT_3);
			}
			else {
				bindAutoEvent = true;

				query.append(_FINDER_COLUMN_PSC_AEV_GI_AUTOEVENT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindAutoEvent) {
					qPos.add(autoEvent);
				}

				if (!pagination) {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process action in the ordered set where groupId = &#63; and autoEvent = &#63;.
	 *
	 * @param groupId the group ID
	 * @param autoEvent the auto event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByPSC_AEV_GI_First(long groupId, String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByPSC_AEV_GI_First(groupId,
				autoEvent, orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", autoEvent=");
		msg.append(autoEvent);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the first process action in the ordered set where groupId = &#63; and autoEvent = &#63;.
	 *
	 * @param groupId the group ID
	 * @param autoEvent the auto event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByPSC_AEV_GI_First(long groupId,
		String autoEvent, OrderByComparator<ProcessAction> orderByComparator) {
		List<ProcessAction> list = findByPSC_AEV_GI(groupId, autoEvent, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process action in the ordered set where groupId = &#63; and autoEvent = &#63;.
	 *
	 * @param groupId the group ID
	 * @param autoEvent the auto event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByPSC_AEV_GI_Last(long groupId, String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByPSC_AEV_GI_Last(groupId,
				autoEvent, orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", autoEvent=");
		msg.append(autoEvent);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the last process action in the ordered set where groupId = &#63; and autoEvent = &#63;.
	 *
	 * @param groupId the group ID
	 * @param autoEvent the auto event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByPSC_AEV_GI_Last(long groupId, String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator) {
		int count = countByPSC_AEV_GI(groupId, autoEvent);

		if (count == 0) {
			return null;
		}

		List<ProcessAction> list = findByPSC_AEV_GI(groupId, autoEvent,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process actions before and after the current process action in the ordered set where groupId = &#63; and autoEvent = &#63;.
	 *
	 * @param processActionId the primary key of the current process action
	 * @param groupId the group ID
	 * @param autoEvent the auto event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction[] findByPSC_AEV_GI_PrevAndNext(long processActionId,
		long groupId, String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findByPrimaryKey(processActionId);

		Session session = null;

		try {
			session = openSession();

			ProcessAction[] array = new ProcessActionImpl[3];

			array[0] = getByPSC_AEV_GI_PrevAndNext(session, processAction,
					groupId, autoEvent, orderByComparator, true);

			array[1] = processAction;

			array[2] = getByPSC_AEV_GI_PrevAndNext(session, processAction,
					groupId, autoEvent, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessAction getByPSC_AEV_GI_PrevAndNext(Session session,
		ProcessAction processAction, long groupId, String autoEvent,
		OrderByComparator<ProcessAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PROCESSACTION_WHERE);

		query.append(_FINDER_COLUMN_PSC_AEV_GI_GROUPID_2);

		boolean bindAutoEvent = false;

		if (autoEvent == null) {
			query.append(_FINDER_COLUMN_PSC_AEV_GI_AUTOEVENT_1);
		}
		else if (autoEvent.equals("")) {
			query.append(_FINDER_COLUMN_PSC_AEV_GI_AUTOEVENT_3);
		}
		else {
			bindAutoEvent = true;

			query.append(_FINDER_COLUMN_PSC_AEV_GI_AUTOEVENT_2);
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
			query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindAutoEvent) {
			qPos.add(autoEvent);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process actions where groupId = &#63; and autoEvent = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param autoEvent the auto event
	 */
	@Override
	public void removeByPSC_AEV_GI(long groupId, String autoEvent) {
		for (ProcessAction processAction : findByPSC_AEV_GI(groupId, autoEvent,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processAction);
		}
	}

	/**
	 * Returns the number of process actions where groupId = &#63; and autoEvent = &#63;.
	 *
	 * @param groupId the group ID
	 * @param autoEvent the auto event
	 * @return the number of matching process actions
	 */
	@Override
	public int countByPSC_AEV_GI(long groupId, String autoEvent) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PSC_AEV_GI;

		Object[] finderArgs = new Object[] { groupId, autoEvent };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_PSC_AEV_GI_GROUPID_2);

			boolean bindAutoEvent = false;

			if (autoEvent == null) {
				query.append(_FINDER_COLUMN_PSC_AEV_GI_AUTOEVENT_1);
			}
			else if (autoEvent.equals("")) {
				query.append(_FINDER_COLUMN_PSC_AEV_GI_AUTOEVENT_3);
			}
			else {
				bindAutoEvent = true;

				query.append(_FINDER_COLUMN_PSC_AEV_GI_AUTOEVENT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindAutoEvent) {
					qPos.add(autoEvent);
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

	private static final String _FINDER_COLUMN_PSC_AEV_GI_GROUPID_2 = "processAction.groupId = ? AND ";
	private static final String _FINDER_COLUMN_PSC_AEV_GI_AUTOEVENT_1 = "processAction.autoEvent IS NULL";
	private static final String _FINDER_COLUMN_PSC_AEV_GI_AUTOEVENT_2 = "processAction.autoEvent = ?";
	private static final String _FINDER_COLUMN_PSC_AEV_GI_AUTOEVENT_3 = "(processAction.autoEvent IS NULL OR processAction.autoEvent = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_SPID_AC_AN = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySPID_AC_AN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			ProcessActionModelImpl.SERVICEPROCESSID_COLUMN_BITMASK |
			ProcessActionModelImpl.ACTIONCODE_COLUMN_BITMASK |
			ProcessActionModelImpl.ACTIONNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SPID_AC_AN = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySPID_AC_AN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the process action where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63; or throws a {@link NoSuchProcessActionException} if it could not be found.
	 *
	 * @param serviceProcessId the service process ID
	 * @param actionCode the action code
	 * @param actionName the action name
	 * @return the matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findBySPID_AC_AN(long serviceProcessId,
		String actionCode, String actionName)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchBySPID_AC_AN(serviceProcessId,
				actionCode, actionName);

		if (processAction == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("serviceProcessId=");
			msg.append(serviceProcessId);

			msg.append(", actionCode=");
			msg.append(actionCode);

			msg.append(", actionName=");
			msg.append(actionName);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchProcessActionException(msg.toString());
		}

		return processAction;
	}

	/**
	 * Returns the process action where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param serviceProcessId the service process ID
	 * @param actionCode the action code
	 * @param actionName the action name
	 * @return the matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchBySPID_AC_AN(long serviceProcessId,
		String actionCode, String actionName) {
		return fetchBySPID_AC_AN(serviceProcessId, actionCode, actionName, true);
	}

	/**
	 * Returns the process action where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param serviceProcessId the service process ID
	 * @param actionCode the action code
	 * @param actionName the action name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchBySPID_AC_AN(long serviceProcessId,
		String actionCode, String actionName, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				serviceProcessId, actionCode, actionName
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SPID_AC_AN,
					finderArgs, this);
		}

		if (result instanceof ProcessAction) {
			ProcessAction processAction = (ProcessAction)result;

			if ((serviceProcessId != processAction.getServiceProcessId()) ||
					!Objects.equals(actionCode, processAction.getActionCode()) ||
					!Objects.equals(actionName, processAction.getActionName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_SPID_AC_AN_SERVICEPROCESSID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_SPID_AC_AN_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_SPID_AC_AN_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_SPID_AC_AN_ACTIONCODE_2);
			}

			boolean bindActionName = false;

			if (actionName == null) {
				query.append(_FINDER_COLUMN_SPID_AC_AN_ACTIONNAME_1);
			}
			else if (actionName.equals("")) {
				query.append(_FINDER_COLUMN_SPID_AC_AN_ACTIONNAME_3);
			}
			else {
				bindActionName = true;

				query.append(_FINDER_COLUMN_SPID_AC_AN_ACTIONNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceProcessId);

				if (bindActionCode) {
					qPos.add(actionCode);
				}

				if (bindActionName) {
					qPos.add(actionName);
				}

				List<ProcessAction> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SPID_AC_AN,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ProcessActionPersistenceImpl.fetchBySPID_AC_AN(long, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProcessAction processAction = list.get(0);

					result = processAction;

					cacheResult(processAction);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SPID_AC_AN,
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
			return (ProcessAction)result;
		}
	}

	/**
	 * Removes the process action where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63; from the database.
	 *
	 * @param serviceProcessId the service process ID
	 * @param actionCode the action code
	 * @param actionName the action name
	 * @return the process action that was removed
	 */
	@Override
	public ProcessAction removeBySPID_AC_AN(long serviceProcessId,
		String actionCode, String actionName)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findBySPID_AC_AN(serviceProcessId,
				actionCode, actionName);

		return remove(processAction);
	}

	/**
	 * Returns the number of process actions where serviceProcessId = &#63; and actionCode = &#63; and actionName = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param actionCode the action code
	 * @param actionName the action name
	 * @return the number of matching process actions
	 */
	@Override
	public int countBySPID_AC_AN(long serviceProcessId, String actionCode,
		String actionName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SPID_AC_AN;

		Object[] finderArgs = new Object[] {
				serviceProcessId, actionCode, actionName
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_SPID_AC_AN_SERVICEPROCESSID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_SPID_AC_AN_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_SPID_AC_AN_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_SPID_AC_AN_ACTIONCODE_2);
			}

			boolean bindActionName = false;

			if (actionName == null) {
				query.append(_FINDER_COLUMN_SPID_AC_AN_ACTIONNAME_1);
			}
			else if (actionName.equals("")) {
				query.append(_FINDER_COLUMN_SPID_AC_AN_ACTIONNAME_3);
			}
			else {
				bindActionName = true;

				query.append(_FINDER_COLUMN_SPID_AC_AN_ACTIONNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceProcessId);

				if (bindActionCode) {
					qPos.add(actionCode);
				}

				if (bindActionName) {
					qPos.add(actionName);
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

	private static final String _FINDER_COLUMN_SPID_AC_AN_SERVICEPROCESSID_2 = "processAction.serviceProcessId = ? AND ";
	private static final String _FINDER_COLUMN_SPID_AC_AN_ACTIONCODE_1 = "processAction.actionCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SPID_AC_AN_ACTIONCODE_2 = "processAction.actionCode = ? AND ";
	private static final String _FINDER_COLUMN_SPID_AC_AN_ACTIONCODE_3 = "(processAction.actionCode IS NULL OR processAction.actionCode = '') AND ";
	private static final String _FINDER_COLUMN_SPID_AC_AN_ACTIONNAME_1 = "processAction.actionName IS NULL";
	private static final String _FINDER_COLUMN_SPID_AC_AN_ACTIONNAME_2 = "processAction.actionName = ?";
	private static final String _FINDER_COLUMN_SPID_AC_AN_ACTIONNAME_3 = "(processAction.actionName IS NULL OR processAction.actionName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SID_PRE =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_GID_SID_PRE",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID_PRE =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_GID_SID_PRE",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			ProcessActionModelImpl.GROUPID_COLUMN_BITMASK |
			ProcessActionModelImpl.SERVICEPROCESSID_COLUMN_BITMASK |
			ProcessActionModelImpl.PRESTEPCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_SID_PRE = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_SID_PRE",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @return the matching process actions
	 */
	@Override
	public List<ProcessAction> findByF_GID_SID_PRE(long groupId,
		long serviceProcessId, String preStepCode) {
		return findByF_GID_SID_PRE(groupId, serviceProcessId, preStepCode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @return the range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByF_GID_SID_PRE(long groupId,
		long serviceProcessId, String preStepCode, int start, int end) {
		return findByF_GID_SID_PRE(groupId, serviceProcessId, preStepCode,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByF_GID_SID_PRE(long groupId,
		long serviceProcessId, String preStepCode, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return findByF_GID_SID_PRE(groupId, serviceProcessId, preStepCode,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByF_GID_SID_PRE(long groupId,
		long serviceProcessId, String preStepCode, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID_PRE;
			finderArgs = new Object[] { groupId, serviceProcessId, preStepCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SID_PRE;
			finderArgs = new Object[] {
					groupId, serviceProcessId, preStepCode,
					
					start, end, orderByComparator
				};
		}

		List<ProcessAction> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessAction processAction : list) {
					if ((groupId != processAction.getGroupId()) ||
							(serviceProcessId != processAction.getServiceProcessId()) ||
							!Objects.equals(preStepCode,
								processAction.getPreStepCode())) {
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

			query.append(_SQL_SELECT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SID_PRE_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_SID_PRE_SERVICEPROCESSID_2);

			boolean bindPreStepCode = false;

			if (preStepCode == null) {
				query.append(_FINDER_COLUMN_F_GID_SID_PRE_PRESTEPCODE_1);
			}
			else if (preStepCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SID_PRE_PRESTEPCODE_3);
			}
			else {
				bindPreStepCode = true;

				query.append(_FINDER_COLUMN_F_GID_SID_PRE_PRESTEPCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceProcessId);

				if (bindPreStepCode) {
					qPos.add(preStepCode);
				}

				if (!pagination) {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByF_GID_SID_PRE_First(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByF_GID_SID_PRE_First(groupId,
				serviceProcessId, preStepCode, orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append(", preStepCode=");
		msg.append(preStepCode);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the first process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByF_GID_SID_PRE_First(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator) {
		List<ProcessAction> list = findByF_GID_SID_PRE(groupId,
				serviceProcessId, preStepCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByF_GID_SID_PRE_Last(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByF_GID_SID_PRE_Last(groupId,
				serviceProcessId, preStepCode, orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append(", preStepCode=");
		msg.append(preStepCode);

		msg.append("}");

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the last process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByF_GID_SID_PRE_Last(long groupId,
		long serviceProcessId, String preStepCode,
		OrderByComparator<ProcessAction> orderByComparator) {
		int count = countByF_GID_SID_PRE(groupId, serviceProcessId, preStepCode);

		if (count == 0) {
			return null;
		}

		List<ProcessAction> list = findByF_GID_SID_PRE(groupId,
				serviceProcessId, preStepCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process actions before and after the current process action in the ordered set where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * @param processActionId the primary key of the current process action
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction[] findByF_GID_SID_PRE_PrevAndNext(
		long processActionId, long groupId, long serviceProcessId,
		String preStepCode, OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findByPrimaryKey(processActionId);

		Session session = null;

		try {
			session = openSession();

			ProcessAction[] array = new ProcessActionImpl[3];

			array[0] = getByF_GID_SID_PRE_PrevAndNext(session, processAction,
					groupId, serviceProcessId, preStepCode, orderByComparator,
					true);

			array[1] = processAction;

			array[2] = getByF_GID_SID_PRE_PrevAndNext(session, processAction,
					groupId, serviceProcessId, preStepCode, orderByComparator,
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

	protected ProcessAction getByF_GID_SID_PRE_PrevAndNext(Session session,
		ProcessAction processAction, long groupId, long serviceProcessId,
		String preStepCode, OrderByComparator<ProcessAction> orderByComparator,
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

		query.append(_SQL_SELECT_PROCESSACTION_WHERE);

		query.append(_FINDER_COLUMN_F_GID_SID_PRE_GROUPID_2);

		query.append(_FINDER_COLUMN_F_GID_SID_PRE_SERVICEPROCESSID_2);

		boolean bindPreStepCode = false;

		if (preStepCode == null) {
			query.append(_FINDER_COLUMN_F_GID_SID_PRE_PRESTEPCODE_1);
		}
		else if (preStepCode.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_SID_PRE_PRESTEPCODE_3);
		}
		else {
			bindPreStepCode = true;

			query.append(_FINDER_COLUMN_F_GID_SID_PRE_PRESTEPCODE_2);
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
			query.append(ProcessActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(serviceProcessId);

		if (bindPreStepCode) {
			qPos.add(preStepCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 */
	@Override
	public void removeByF_GID_SID_PRE(long groupId, long serviceProcessId,
		String preStepCode) {
		for (ProcessAction processAction : findByF_GID_SID_PRE(groupId,
				serviceProcessId, preStepCode, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(processAction);
		}
	}

	/**
	 * Returns the number of process actions where groupId = &#63; and serviceProcessId = &#63; and preStepCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param preStepCode the pre step code
	 * @return the number of matching process actions
	 */
	@Override
	public int countByF_GID_SID_PRE(long groupId, long serviceProcessId,
		String preStepCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_SID_PRE;

		Object[] finderArgs = new Object[] {
				groupId, serviceProcessId, preStepCode
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SID_PRE_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_SID_PRE_SERVICEPROCESSID_2);

			boolean bindPreStepCode = false;

			if (preStepCode == null) {
				query.append(_FINDER_COLUMN_F_GID_SID_PRE_PRESTEPCODE_1);
			}
			else if (preStepCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SID_PRE_PRESTEPCODE_3);
			}
			else {
				bindPreStepCode = true;

				query.append(_FINDER_COLUMN_F_GID_SID_PRE_PRESTEPCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceProcessId);

				if (bindPreStepCode) {
					qPos.add(preStepCode);
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

	private static final String _FINDER_COLUMN_F_GID_SID_PRE_GROUPID_2 = "processAction.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SID_PRE_SERVICEPROCESSID_2 = "processAction.serviceProcessId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SID_PRE_PRESTEPCODE_1 = "processAction.preStepCode IS NULL";
	private static final String _FINDER_COLUMN_F_GID_SID_PRE_PRESTEPCODE_2 = "processAction.preStepCode = ?";
	private static final String _FINDER_COLUMN_F_GID_SID_PRE_PRESTEPCODE_3 = "(processAction.preStepCode IS NULL OR processAction.preStepCode = '')";

	public ProcessActionPersistenceImpl() {
		setModelClass(ProcessAction.class);

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
	 * Caches the process action in the entity cache if it is enabled.
	 *
	 * @param processAction the process action
	 */
	@Override
	public void cacheResult(ProcessAction processAction) {
		entityCache.putResult(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionImpl.class, processAction.getPrimaryKey(),
			processAction);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { processAction.getUuid(), processAction.getGroupId() },
			processAction);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SPI_PRESC_AEV,
			new Object[] {
				processAction.getServiceProcessId(),
				processAction.getPreStepCode(), processAction.getAutoEvent()
			}, processAction);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SPID_AC,
			new Object[] {
				processAction.getServiceProcessId(),
				processAction.getActionCode()
			}, processAction);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SPID_AC_AN,
			new Object[] {
				processAction.getServiceProcessId(),
				processAction.getActionCode(), processAction.getActionName()
			}, processAction);

		processAction.resetOriginalValues();
	}

	/**
	 * Caches the process actions in the entity cache if it is enabled.
	 *
	 * @param processActions the process actions
	 */
	@Override
	public void cacheResult(List<ProcessAction> processActions) {
		for (ProcessAction processAction : processActions) {
			if (entityCache.getResult(
						ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
						ProcessActionImpl.class, processAction.getPrimaryKey()) == null) {
				cacheResult(processAction);
			}
			else {
				processAction.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all process actions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProcessActionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the process action.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProcessAction processAction) {
		entityCache.removeResult(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionImpl.class, processAction.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ProcessActionModelImpl)processAction, true);
	}

	@Override
	public void clearCache(List<ProcessAction> processActions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProcessAction processAction : processActions) {
			entityCache.removeResult(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
				ProcessActionImpl.class, processAction.getPrimaryKey());

			clearUniqueFindersCache((ProcessActionModelImpl)processAction, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ProcessActionModelImpl processActionModelImpl) {
		Object[] args = new Object[] {
				processActionModelImpl.getUuid(),
				processActionModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			processActionModelImpl, false);

		args = new Object[] {
				processActionModelImpl.getServiceProcessId(),
				processActionModelImpl.getPreStepCode(),
				processActionModelImpl.getAutoEvent()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_SPI_PRESC_AEV, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_SPI_PRESC_AEV, args,
			processActionModelImpl, false);

		args = new Object[] {
				processActionModelImpl.getServiceProcessId(),
				processActionModelImpl.getActionCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_SPID_AC, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_SPID_AC, args,
			processActionModelImpl, false);

		args = new Object[] {
				processActionModelImpl.getServiceProcessId(),
				processActionModelImpl.getActionCode(),
				processActionModelImpl.getActionName()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_SPID_AC_AN, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_SPID_AC_AN, args,
			processActionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ProcessActionModelImpl processActionModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					processActionModelImpl.getUuid(),
					processActionModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((processActionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processActionModelImpl.getOriginalUuid(),
					processActionModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					processActionModelImpl.getServiceProcessId(),
					processActionModelImpl.getPreStepCode(),
					processActionModelImpl.getAutoEvent()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SPI_PRESC_AEV, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SPI_PRESC_AEV, args);
		}

		if ((processActionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SPI_PRESC_AEV.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processActionModelImpl.getOriginalServiceProcessId(),
					processActionModelImpl.getOriginalPreStepCode(),
					processActionModelImpl.getOriginalAutoEvent()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SPI_PRESC_AEV, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SPI_PRESC_AEV, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					processActionModelImpl.getServiceProcessId(),
					processActionModelImpl.getActionCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SPID_AC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SPID_AC, args);
		}

		if ((processActionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SPID_AC.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processActionModelImpl.getOriginalServiceProcessId(),
					processActionModelImpl.getOriginalActionCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SPID_AC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SPID_AC, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					processActionModelImpl.getServiceProcessId(),
					processActionModelImpl.getActionCode(),
					processActionModelImpl.getActionName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SPID_AC_AN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SPID_AC_AN, args);
		}

		if ((processActionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SPID_AC_AN.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processActionModelImpl.getOriginalServiceProcessId(),
					processActionModelImpl.getOriginalActionCode(),
					processActionModelImpl.getOriginalActionName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SPID_AC_AN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SPID_AC_AN, args);
		}
	}

	/**
	 * Creates a new process action with the primary key. Does not add the process action to the database.
	 *
	 * @param processActionId the primary key for the new process action
	 * @return the new process action
	 */
	@Override
	public ProcessAction create(long processActionId) {
		ProcessAction processAction = new ProcessActionImpl();

		processAction.setNew(true);
		processAction.setPrimaryKey(processActionId);

		String uuid = PortalUUIDUtil.generate();

		processAction.setUuid(uuid);

		processAction.setCompanyId(companyProvider.getCompanyId());

		return processAction;
	}

	/**
	 * Removes the process action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param processActionId the primary key of the process action
	 * @return the process action that was removed
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction remove(long processActionId)
		throws NoSuchProcessActionException {
		return remove((Serializable)processActionId);
	}

	/**
	 * Removes the process action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the process action
	 * @return the process action that was removed
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction remove(Serializable primaryKey)
		throws NoSuchProcessActionException {
		Session session = null;

		try {
			session = openSession();

			ProcessAction processAction = (ProcessAction)session.get(ProcessActionImpl.class,
					primaryKey);

			if (processAction == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProcessActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(processAction);
		}
		catch (NoSuchProcessActionException nsee) {
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
	protected ProcessAction removeImpl(ProcessAction processAction) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(processAction)) {
				processAction = (ProcessAction)session.get(ProcessActionImpl.class,
						processAction.getPrimaryKeyObj());
			}

			if (processAction != null) {
				session.delete(processAction);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (processAction != null) {
			clearCache(processAction);
		}

		return processAction;
	}

	@Override
	public ProcessAction updateImpl(ProcessAction processAction) {
		boolean isNew = processAction.isNew();

		if (!(processAction instanceof ProcessActionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(processAction.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(processAction);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in processAction proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProcessAction implementation " +
				processAction.getClass());
		}

		ProcessActionModelImpl processActionModelImpl = (ProcessActionModelImpl)processAction;

		if (Validator.isNull(processAction.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			processAction.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (processAction.getCreateDate() == null)) {
			if (serviceContext == null) {
				processAction.setCreateDate(now);
			}
			else {
				processAction.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!processActionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				processAction.setModifiedDate(now);
			}
			else {
				processAction.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (processAction.isNew()) {
				session.save(processAction);

				processAction.setNew(false);
			}
			else {
				processAction = (ProcessAction)session.merge(processAction);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ProcessActionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { processActionModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					processActionModelImpl.getUuid(),
					processActionModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { processActionModelImpl.getServiceProcessId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_S_P_ID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_P_ID,
				args);

			args = new Object[] {
					processActionModelImpl.getGroupId(),
					processActionModelImpl.getActionCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GI_AC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_AC,
				args);

			args = new Object[] {
					processActionModelImpl.getGroupId(),
					processActionModelImpl.getActionCode(),
					processActionModelImpl.getServiceProcessId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GI_AC_SP, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_AC_SP,
				args);

			args = new Object[] {
					processActionModelImpl.getPreStepCode(),
					processActionModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PRE_CODE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRE_CODE,
				args);

			args = new Object[] {
					processActionModelImpl.getPostStepCode(),
					processActionModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_POST_CODE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POST_CODE,
				args);

			args = new Object[] {
					processActionModelImpl.getGroupId(),
					processActionModelImpl.getServiceProcessId(),
					processActionModelImpl.getPreStepCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SPID_PRESC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SPID_PRESC,
				args);

			args = new Object[] { processActionModelImpl.getAutoEvent() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PSC_AEV, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSC_AEV,
				args);

			args = new Object[] {
					processActionModelImpl.getGroupId(),
					processActionModelImpl.getAutoEvent()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PSC_AEV_GI, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSC_AEV_GI,
				args);

			args = new Object[] {
					processActionModelImpl.getGroupId(),
					processActionModelImpl.getServiceProcessId(),
					processActionModelImpl.getPreStepCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SID_PRE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID_PRE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((processActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processActionModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { processActionModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((processActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processActionModelImpl.getOriginalUuid(),
						processActionModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						processActionModelImpl.getUuid(),
						processActionModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((processActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_P_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processActionModelImpl.getOriginalServiceProcessId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S_P_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_P_ID,
					args);

				args = new Object[] { processActionModelImpl.getServiceProcessId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S_P_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_P_ID,
					args);
			}

			if ((processActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_AC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processActionModelImpl.getOriginalGroupId(),
						processActionModelImpl.getOriginalActionCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GI_AC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_AC,
					args);

				args = new Object[] {
						processActionModelImpl.getGroupId(),
						processActionModelImpl.getActionCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GI_AC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_AC,
					args);
			}

			if ((processActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_AC_SP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processActionModelImpl.getOriginalGroupId(),
						processActionModelImpl.getOriginalActionCode(),
						processActionModelImpl.getOriginalServiceProcessId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GI_AC_SP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_AC_SP,
					args);

				args = new Object[] {
						processActionModelImpl.getGroupId(),
						processActionModelImpl.getActionCode(),
						processActionModelImpl.getServiceProcessId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GI_AC_SP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_AC_SP,
					args);
			}

			if ((processActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRE_CODE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processActionModelImpl.getOriginalPreStepCode(),
						processActionModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRE_CODE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRE_CODE,
					args);

				args = new Object[] {
						processActionModelImpl.getPreStepCode(),
						processActionModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRE_CODE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRE_CODE,
					args);
			}

			if ((processActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POST_CODE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processActionModelImpl.getOriginalPostStepCode(),
						processActionModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_POST_CODE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POST_CODE,
					args);

				args = new Object[] {
						processActionModelImpl.getPostStepCode(),
						processActionModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_POST_CODE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POST_CODE,
					args);
			}

			if ((processActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SPID_PRESC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processActionModelImpl.getOriginalGroupId(),
						processActionModelImpl.getOriginalServiceProcessId(),
						processActionModelImpl.getOriginalPreStepCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SPID_PRESC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SPID_PRESC,
					args);

				args = new Object[] {
						processActionModelImpl.getGroupId(),
						processActionModelImpl.getServiceProcessId(),
						processActionModelImpl.getPreStepCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SPID_PRESC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SPID_PRESC,
					args);
			}

			if ((processActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSC_AEV.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processActionModelImpl.getOriginalAutoEvent()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PSC_AEV, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSC_AEV,
					args);

				args = new Object[] { processActionModelImpl.getAutoEvent() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PSC_AEV, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSC_AEV,
					args);
			}

			if ((processActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSC_AEV_GI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processActionModelImpl.getOriginalGroupId(),
						processActionModelImpl.getOriginalAutoEvent()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PSC_AEV_GI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSC_AEV_GI,
					args);

				args = new Object[] {
						processActionModelImpl.getGroupId(),
						processActionModelImpl.getAutoEvent()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PSC_AEV_GI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSC_AEV_GI,
					args);
			}

			if ((processActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID_PRE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processActionModelImpl.getOriginalGroupId(),
						processActionModelImpl.getOriginalServiceProcessId(),
						processActionModelImpl.getOriginalPreStepCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SID_PRE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID_PRE,
					args);

				args = new Object[] {
						processActionModelImpl.getGroupId(),
						processActionModelImpl.getServiceProcessId(),
						processActionModelImpl.getPreStepCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SID_PRE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID_PRE,
					args);
			}
		}

		entityCache.putResult(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionImpl.class, processAction.getPrimaryKey(),
			processAction, false);

		clearUniqueFindersCache(processActionModelImpl, false);
		cacheUniqueFindersCache(processActionModelImpl);

		processAction.resetOriginalValues();

		return processAction;
	}

	/**
	 * Returns the process action with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the process action
	 * @return the process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByPrimaryKey(primaryKey);

		if (processAction == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProcessActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return processAction;
	}

	/**
	 * Returns the process action with the primary key or throws a {@link NoSuchProcessActionException} if it could not be found.
	 *
	 * @param processActionId the primary key of the process action
	 * @return the process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction findByPrimaryKey(long processActionId)
		throws NoSuchProcessActionException {
		return findByPrimaryKey((Serializable)processActionId);
	}

	/**
	 * Returns the process action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the process action
	 * @return the process action, or <code>null</code> if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
				ProcessActionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProcessAction processAction = (ProcessAction)serializable;

		if (processAction == null) {
			Session session = null;

			try {
				session = openSession();

				processAction = (ProcessAction)session.get(ProcessActionImpl.class,
						primaryKey);

				if (processAction != null) {
					cacheResult(processAction);
				}
				else {
					entityCache.putResult(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
						ProcessActionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
					ProcessActionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return processAction;
	}

	/**
	 * Returns the process action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param processActionId the primary key of the process action
	 * @return the process action, or <code>null</code> if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction fetchByPrimaryKey(long processActionId) {
		return fetchByPrimaryKey((Serializable)processActionId);
	}

	@Override
	public Map<Serializable, ProcessAction> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProcessAction> map = new HashMap<Serializable, ProcessAction>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProcessAction processAction = fetchByPrimaryKey(primaryKey);

			if (processAction != null) {
				map.put(primaryKey, processAction);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
					ProcessActionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProcessAction)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROCESSACTION_WHERE_PKS_IN);

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

			for (ProcessAction processAction : (List<ProcessAction>)q.list()) {
				map.put(processAction.getPrimaryKeyObj(), processAction);

				cacheResult(processAction);

				uncachedPrimaryKeys.remove(processAction.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
					ProcessActionImpl.class, primaryKey, nullModel);
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
	 * Returns all the process actions.
	 *
	 * @return the process actions
	 */
	@Override
	public List<ProcessAction> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @return the range of process actions
	 */
	@Override
	public List<ProcessAction> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the process actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of process actions
	 */
	@Override
	public List<ProcessAction> findAll(int start, int end,
		OrderByComparator<ProcessAction> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of process actions
	 */
	@Override
	public List<ProcessAction> findAll(int start, int end,
		OrderByComparator<ProcessAction> orderByComparator,
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

		List<ProcessAction> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessAction>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROCESSACTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROCESSACTION;

				if (pagination) {
					sql = sql.concat(ProcessActionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessAction>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the process actions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProcessAction processAction : findAll()) {
			remove(processAction);
		}
	}

	/**
	 * Returns the number of process actions.
	 *
	 * @return the number of process actions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROCESSACTION);

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
		return ProcessActionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the process action persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProcessActionImpl.class.getName());
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
	private static final String _SQL_SELECT_PROCESSACTION = "SELECT processAction FROM ProcessAction processAction";
	private static final String _SQL_SELECT_PROCESSACTION_WHERE_PKS_IN = "SELECT processAction FROM ProcessAction processAction WHERE processActionId IN (";
	private static final String _SQL_SELECT_PROCESSACTION_WHERE = "SELECT processAction FROM ProcessAction processAction WHERE ";
	private static final String _SQL_COUNT_PROCESSACTION = "SELECT COUNT(processAction) FROM ProcessAction processAction";
	private static final String _SQL_COUNT_PROCESSACTION_WHERE = "SELECT COUNT(processAction) FROM ProcessAction processAction WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "processAction.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProcessAction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProcessAction exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ProcessActionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}