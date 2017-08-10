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

package org.opencps.backend.processmgt.service.persistence.impl;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.backend.processmgt.exception.NoSuchProcessActionException;
import org.opencps.backend.processmgt.model.ProcessAction;
import org.opencps.backend.processmgt.model.impl.ProcessActionImpl;
import org.opencps.backend.processmgt.model.impl.ProcessActionModelImpl;
import org.opencps.backend.processmgt.service.persistence.ProcessActionPersistence;

import java.io.Serializable;

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
 * @author khoavu
 * @see ProcessActionPersistence
 * @see org.opencps.backend.processmgt.service.persistence.ProcessActionUtil
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
			else if (uuid.equals(StringPool.BLANK)) {
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

		msg.append(StringPool.CLOSE_CURLY_BRACE);

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

		msg.append(StringPool.CLOSE_CURLY_BRACE);

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
		else if (uuid.equals(StringPool.BLANK)) {
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
			else if (uuid.equals(StringPool.BLANK)) {
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

			msg.append(StringPool.CLOSE_CURLY_BRACE);

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
			else if (uuid.equals(StringPool.BLANK)) {
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

					if ((processAction.getUuid() == null) ||
							!processAction.getUuid().equals(uuid) ||
							(processAction.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, processAction);
					}
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
			else if (uuid.equals(StringPool.BLANK)) {
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
			else if (uuid.equals(StringPool.BLANK)) {
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

		msg.append(StringPool.CLOSE_CURLY_BRACE);

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

		msg.append(StringPool.CLOSE_CURLY_BRACE);

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
		else if (uuid.equals(StringPool.BLANK)) {
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
			else if (uuid.equals(StringPool.BLANK)) {
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

		msg.append(StringPool.CLOSE_CURLY_BRACE);

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

		msg.append(StringPool.CLOSE_CURLY_BRACE);

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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PR_P_S_ID =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPR_P_S_ID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PR_P_S_ID =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPR_P_S_ID", new String[] { Long.class.getName() },
			ProcessActionModelImpl.PREPROCESSSTEPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PR_P_S_ID = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPR_P_S_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the process actions where preProcessStepId = &#63;.
	 *
	 * @param preProcessStepId the pre process step ID
	 * @return the matching process actions
	 */
	@Override
	public List<ProcessAction> findByPR_P_S_ID(long preProcessStepId) {
		return findByPR_P_S_ID(preProcessStepId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process actions where preProcessStepId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param preProcessStepId the pre process step ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @return the range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPR_P_S_ID(long preProcessStepId,
		int start, int end) {
		return findByPR_P_S_ID(preProcessStepId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process actions where preProcessStepId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param preProcessStepId the pre process step ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPR_P_S_ID(long preProcessStepId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator) {
		return findByPR_P_S_ID(preProcessStepId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the process actions where preProcessStepId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param preProcessStepId the pre process step ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPR_P_S_ID(long preProcessStepId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PR_P_S_ID;
			finderArgs = new Object[] { preProcessStepId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PR_P_S_ID;
			finderArgs = new Object[] {
					preProcessStepId,
					
					start, end, orderByComparator
				};
		}

		List<ProcessAction> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessAction processAction : list) {
					if ((preProcessStepId != processAction.getPreProcessStepId())) {
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

			query.append(_FINDER_COLUMN_PR_P_S_ID_PREPROCESSSTEPID_2);

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

				qPos.add(preProcessStepId);

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
	 * Returns the first process action in the ordered set where preProcessStepId = &#63;.
	 *
	 * @param preProcessStepId the pre process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByPR_P_S_ID_First(long preProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByPR_P_S_ID_First(preProcessStepId,
				orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("preProcessStepId=");
		msg.append(preProcessStepId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the first process action in the ordered set where preProcessStepId = &#63;.
	 *
	 * @param preProcessStepId the pre process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByPR_P_S_ID_First(long preProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator) {
		List<ProcessAction> list = findByPR_P_S_ID(preProcessStepId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process action in the ordered set where preProcessStepId = &#63;.
	 *
	 * @param preProcessStepId the pre process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByPR_P_S_ID_Last(long preProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByPR_P_S_ID_Last(preProcessStepId,
				orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("preProcessStepId=");
		msg.append(preProcessStepId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the last process action in the ordered set where preProcessStepId = &#63;.
	 *
	 * @param preProcessStepId the pre process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByPR_P_S_ID_Last(long preProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator) {
		int count = countByPR_P_S_ID(preProcessStepId);

		if (count == 0) {
			return null;
		}

		List<ProcessAction> list = findByPR_P_S_ID(preProcessStepId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process actions before and after the current process action in the ordered set where preProcessStepId = &#63;.
	 *
	 * @param processActionId the primary key of the current process action
	 * @param preProcessStepId the pre process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction[] findByPR_P_S_ID_PrevAndNext(long processActionId,
		long preProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findByPrimaryKey(processActionId);

		Session session = null;

		try {
			session = openSession();

			ProcessAction[] array = new ProcessActionImpl[3];

			array[0] = getByPR_P_S_ID_PrevAndNext(session, processAction,
					preProcessStepId, orderByComparator, true);

			array[1] = processAction;

			array[2] = getByPR_P_S_ID_PrevAndNext(session, processAction,
					preProcessStepId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessAction getByPR_P_S_ID_PrevAndNext(Session session,
		ProcessAction processAction, long preProcessStepId,
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

		query.append(_FINDER_COLUMN_PR_P_S_ID_PREPROCESSSTEPID_2);

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

		qPos.add(preProcessStepId);

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
	 * Removes all the process actions where preProcessStepId = &#63; from the database.
	 *
	 * @param preProcessStepId the pre process step ID
	 */
	@Override
	public void removeByPR_P_S_ID(long preProcessStepId) {
		for (ProcessAction processAction : findByPR_P_S_ID(preProcessStepId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processAction);
		}
	}

	/**
	 * Returns the number of process actions where preProcessStepId = &#63;.
	 *
	 * @param preProcessStepId the pre process step ID
	 * @return the number of matching process actions
	 */
	@Override
	public int countByPR_P_S_ID(long preProcessStepId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PR_P_S_ID;

		Object[] finderArgs = new Object[] { preProcessStepId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_PR_P_S_ID_PREPROCESSSTEPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(preProcessStepId);

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

	private static final String _FINDER_COLUMN_PR_P_S_ID_PREPROCESSSTEPID_2 = "processAction.preProcessStepId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PO_P_S_ID =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPO_P_S_ID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PO_P_S_ID =
		new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED,
			ProcessActionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPO_P_S_ID", new String[] { Long.class.getName() },
			ProcessActionModelImpl.POSTPROCESSSTEPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PO_P_S_ID = new FinderPath(ProcessActionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPO_P_S_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the process actions where postProcessStepId = &#63;.
	 *
	 * @param postProcessStepId the post process step ID
	 * @return the matching process actions
	 */
	@Override
	public List<ProcessAction> findByPO_P_S_ID(long postProcessStepId) {
		return findByPO_P_S_ID(postProcessStepId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process actions where postProcessStepId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param postProcessStepId the post process step ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @return the range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPO_P_S_ID(long postProcessStepId,
		int start, int end) {
		return findByPO_P_S_ID(postProcessStepId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process actions where postProcessStepId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param postProcessStepId the post process step ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPO_P_S_ID(long postProcessStepId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator) {
		return findByPO_P_S_ID(postProcessStepId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process actions where postProcessStepId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param postProcessStepId the post process step ID
	 * @param start the lower bound of the range of process actions
	 * @param end the upper bound of the range of process actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process actions
	 */
	@Override
	public List<ProcessAction> findByPO_P_S_ID(long postProcessStepId,
		int start, int end, OrderByComparator<ProcessAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PO_P_S_ID;
			finderArgs = new Object[] { postProcessStepId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PO_P_S_ID;
			finderArgs = new Object[] {
					postProcessStepId,
					
					start, end, orderByComparator
				};
		}

		List<ProcessAction> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessAction processAction : list) {
					if ((postProcessStepId != processAction.getPostProcessStepId())) {
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

			query.append(_FINDER_COLUMN_PO_P_S_ID_POSTPROCESSSTEPID_2);

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

				qPos.add(postProcessStepId);

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
	 * Returns the first process action in the ordered set where postProcessStepId = &#63;.
	 *
	 * @param postProcessStepId the post process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByPO_P_S_ID_First(long postProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByPO_P_S_ID_First(postProcessStepId,
				orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("postProcessStepId=");
		msg.append(postProcessStepId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the first process action in the ordered set where postProcessStepId = &#63;.
	 *
	 * @param postProcessStepId the post process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByPO_P_S_ID_First(long postProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator) {
		List<ProcessAction> list = findByPO_P_S_ID(postProcessStepId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process action in the ordered set where postProcessStepId = &#63;.
	 *
	 * @param postProcessStepId the post process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action
	 * @throws NoSuchProcessActionException if a matching process action could not be found
	 */
	@Override
	public ProcessAction findByPO_P_S_ID_Last(long postProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = fetchByPO_P_S_ID_Last(postProcessStepId,
				orderByComparator);

		if (processAction != null) {
			return processAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("postProcessStepId=");
		msg.append(postProcessStepId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProcessActionException(msg.toString());
	}

	/**
	 * Returns the last process action in the ordered set where postProcessStepId = &#63;.
	 *
	 * @param postProcessStepId the post process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process action, or <code>null</code> if a matching process action could not be found
	 */
	@Override
	public ProcessAction fetchByPO_P_S_ID_Last(long postProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator) {
		int count = countByPO_P_S_ID(postProcessStepId);

		if (count == 0) {
			return null;
		}

		List<ProcessAction> list = findByPO_P_S_ID(postProcessStepId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process actions before and after the current process action in the ordered set where postProcessStepId = &#63;.
	 *
	 * @param processActionId the primary key of the current process action
	 * @param postProcessStepId the post process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process action
	 * @throws NoSuchProcessActionException if a process action with the primary key could not be found
	 */
	@Override
	public ProcessAction[] findByPO_P_S_ID_PrevAndNext(long processActionId,
		long postProcessStepId,
		OrderByComparator<ProcessAction> orderByComparator)
		throws NoSuchProcessActionException {
		ProcessAction processAction = findByPrimaryKey(processActionId);

		Session session = null;

		try {
			session = openSession();

			ProcessAction[] array = new ProcessActionImpl[3];

			array[0] = getByPO_P_S_ID_PrevAndNext(session, processAction,
					postProcessStepId, orderByComparator, true);

			array[1] = processAction;

			array[2] = getByPO_P_S_ID_PrevAndNext(session, processAction,
					postProcessStepId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessAction getByPO_P_S_ID_PrevAndNext(Session session,
		ProcessAction processAction, long postProcessStepId,
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

		query.append(_FINDER_COLUMN_PO_P_S_ID_POSTPROCESSSTEPID_2);

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

		qPos.add(postProcessStepId);

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
	 * Removes all the process actions where postProcessStepId = &#63; from the database.
	 *
	 * @param postProcessStepId the post process step ID
	 */
	@Override
	public void removeByPO_P_S_ID(long postProcessStepId) {
		for (ProcessAction processAction : findByPO_P_S_ID(postProcessStepId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processAction);
		}
	}

	/**
	 * Returns the number of process actions where postProcessStepId = &#63;.
	 *
	 * @param postProcessStepId the post process step ID
	 * @return the number of matching process actions
	 */
	@Override
	public int countByPO_P_S_ID(long postProcessStepId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PO_P_S_ID;

		Object[] finderArgs = new Object[] { postProcessStepId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSACTION_WHERE);

			query.append(_FINDER_COLUMN_PO_P_S_ID_POSTPROCESSSTEPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(postProcessStepId);

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

	private static final String _FINDER_COLUMN_PO_P_S_ID_POSTPROCESSSTEPID_2 = "processAction.postProcessStepId = ?";

	public ProcessActionPersistenceImpl() {
		setModelClass(ProcessAction.class);
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
		processAction = toUnwrappedModel(processAction);

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
		processAction = toUnwrappedModel(processAction);

		boolean isNew = processAction.isNew();

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

		if (isNew || !ProcessActionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
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
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PR_P_S_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processActionModelImpl.getOriginalPreProcessStepId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PR_P_S_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PR_P_S_ID,
					args);

				args = new Object[] { processActionModelImpl.getPreProcessStepId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PR_P_S_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PR_P_S_ID,
					args);
			}

			if ((processActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PO_P_S_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processActionModelImpl.getOriginalPostProcessStepId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PO_P_S_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PO_P_S_ID,
					args);

				args = new Object[] {
						processActionModelImpl.getPostProcessStepId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PO_P_S_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PO_P_S_ID,
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

	protected ProcessAction toUnwrappedModel(ProcessAction processAction) {
		if (processAction instanceof ProcessActionImpl) {
			return processAction;
		}

		ProcessActionImpl processActionImpl = new ProcessActionImpl();

		processActionImpl.setNew(processAction.isNew());
		processActionImpl.setPrimaryKey(processAction.getPrimaryKey());

		processActionImpl.setUuid(processAction.getUuid());
		processActionImpl.setProcessActionId(processAction.getProcessActionId());
		processActionImpl.setCompanyId(processAction.getCompanyId());
		processActionImpl.setGroupId(processAction.getGroupId());
		processActionImpl.setUserId(processAction.getUserId());
		processActionImpl.setUserName(processAction.getUserName());
		processActionImpl.setCreateDate(processAction.getCreateDate());
		processActionImpl.setModifiedDate(processAction.getModifiedDate());
		processActionImpl.setServiceProcessId(processAction.getServiceProcessId());
		processActionImpl.setPreProcessStepId(processAction.getPreProcessStepId());
		processActionImpl.setPostProcessStepId(processAction.getPostProcessStepId());
		processActionImpl.setAutoEvent(processAction.getAutoEvent());
		processActionImpl.setPreCondition(processAction.getPreCondition());
		processActionImpl.setActionCode(processAction.getActionCode());
		processActionImpl.setActionName(processAction.getActionName());
		processActionImpl.setAllowAssignUser(processAction.getAllowAssignUser());
		processActionImpl.setAssignUserId(processAction.getAssignUserId());
		processActionImpl.setRequestPayment(processAction.getRequestPayment());
		processActionImpl.setPaymentFee(processAction.getPaymentFee());
		processActionImpl.setCreateDossierFiles(processAction.getCreateDossierFiles());
		processActionImpl.setReturnDossierFiles(processAction.getReturnDossierFiles());
		processActionImpl.setSyncActionCode(processAction.getSyncActionCode());
		processActionImpl.setRollback(processAction.getRollback());

		return processActionImpl;
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
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

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