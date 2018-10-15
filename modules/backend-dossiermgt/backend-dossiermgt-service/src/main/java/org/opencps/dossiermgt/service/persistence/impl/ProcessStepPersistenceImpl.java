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

import org.opencps.dossiermgt.exception.NoSuchProcessStepException;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.impl.ProcessStepImpl;
import org.opencps.dossiermgt.model.impl.ProcessStepModelImpl;
import org.opencps.dossiermgt.service.persistence.ProcessStepPersistence;

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
 * The persistence implementation for the process step service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ProcessStepPersistence
 * @see org.opencps.dossiermgt.service.persistence.ProcessStepUtil
 * @generated
 */
@ProviderType
public class ProcessStepPersistenceImpl extends BasePersistenceImpl<ProcessStep>
	implements ProcessStepPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProcessStepUtil} to access the process step persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProcessStepImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ProcessStepModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the process steps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching process steps
	 */
	@Override
	public List<ProcessStep> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process steps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @return the range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process steps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process steps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator,
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

		List<ProcessStep> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessStep>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessStep processStep : list) {
					if (!Objects.equals(uuid, processStep.getUuid())) {
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

			query.append(_SQL_SELECT_PROCESSSTEP_WHERE);

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
				query.append(ProcessStepModelImpl.ORDER_BY_JPQL);
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
					list = (List<ProcessStep>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessStep>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process step in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step
	 * @throws NoSuchProcessStepException if a matching process step could not be found
	 */
	@Override
	public ProcessStep findByUuid_First(String uuid,
		OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = fetchByUuid_First(uuid, orderByComparator);

		if (processStep != null) {
			return processStep;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchProcessStepException(msg.toString());
	}

	/**
	 * Returns the first process step in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchByUuid_First(String uuid,
		OrderByComparator<ProcessStep> orderByComparator) {
		List<ProcessStep> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process step in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step
	 * @throws NoSuchProcessStepException if a matching process step could not be found
	 */
	@Override
	public ProcessStep findByUuid_Last(String uuid,
		OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = fetchByUuid_Last(uuid, orderByComparator);

		if (processStep != null) {
			return processStep;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchProcessStepException(msg.toString());
	}

	/**
	 * Returns the last process step in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchByUuid_Last(String uuid,
		OrderByComparator<ProcessStep> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ProcessStep> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process steps before and after the current process step in the ordered set where uuid = &#63;.
	 *
	 * @param processStepId the primary key of the current process step
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process step
	 * @throws NoSuchProcessStepException if a process step with the primary key could not be found
	 */
	@Override
	public ProcessStep[] findByUuid_PrevAndNext(long processStepId,
		String uuid, OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = findByPrimaryKey(processStepId);

		Session session = null;

		try {
			session = openSession();

			ProcessStep[] array = new ProcessStepImpl[3];

			array[0] = getByUuid_PrevAndNext(session, processStep, uuid,
					orderByComparator, true);

			array[1] = processStep;

			array[2] = getByUuid_PrevAndNext(session, processStep, uuid,
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

	protected ProcessStep getByUuid_PrevAndNext(Session session,
		ProcessStep processStep, String uuid,
		OrderByComparator<ProcessStep> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PROCESSSTEP_WHERE);

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
			query.append(ProcessStepModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(processStep);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessStep> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process steps where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ProcessStep processStep : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(processStep);
		}
	}

	/**
	 * Returns the number of process steps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching process steps
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSSTEP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "processStep.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "processStep.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(processStep.uuid IS NULL OR processStep.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ProcessStepModelImpl.UUID_COLUMN_BITMASK |
			ProcessStepModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the process step where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessStepException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching process step
	 * @throws NoSuchProcessStepException if a matching process step could not be found
	 */
	@Override
	public ProcessStep findByUUID_G(String uuid, long groupId)
		throws NoSuchProcessStepException {
		ProcessStep processStep = fetchByUUID_G(uuid, groupId);

		if (processStep == null) {
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

			throw new NoSuchProcessStepException(msg.toString());
		}

		return processStep;
	}

	/**
	 * Returns the process step where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the process step where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ProcessStep) {
			ProcessStep processStep = (ProcessStep)result;

			if (!Objects.equals(uuid, processStep.getUuid()) ||
					(groupId != processStep.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PROCESSSTEP_WHERE);

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

				List<ProcessStep> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ProcessStep processStep = list.get(0);

					result = processStep;

					cacheResult(processStep);
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
			return (ProcessStep)result;
		}
	}

	/**
	 * Removes the process step where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the process step that was removed
	 */
	@Override
	public ProcessStep removeByUUID_G(String uuid, long groupId)
		throws NoSuchProcessStepException {
		ProcessStep processStep = findByUUID_G(uuid, groupId);

		return remove(processStep);
	}

	/**
	 * Returns the number of process steps where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching process steps
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSSTEP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "processStep.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "processStep.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(processStep.uuid IS NULL OR processStep.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "processStep.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ProcessStepModelImpl.UUID_COLUMN_BITMASK |
			ProcessStepModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the process steps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching process steps
	 */
	@Override
	public List<ProcessStep> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process steps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @return the range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process steps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessStep> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process steps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessStep> orderByComparator,
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

		List<ProcessStep> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessStep>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessStep processStep : list) {
					if (!Objects.equals(uuid, processStep.getUuid()) ||
							(companyId != processStep.getCompanyId())) {
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

			query.append(_SQL_SELECT_PROCESSSTEP_WHERE);

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
				query.append(ProcessStepModelImpl.ORDER_BY_JPQL);
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
					list = (List<ProcessStep>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessStep>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process step in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step
	 * @throws NoSuchProcessStepException if a matching process step could not be found
	 */
	@Override
	public ProcessStep findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (processStep != null) {
			return processStep;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchProcessStepException(msg.toString());
	}

	/**
	 * Returns the first process step in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessStep> orderByComparator) {
		List<ProcessStep> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process step in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step
	 * @throws NoSuchProcessStepException if a matching process step could not be found
	 */
	@Override
	public ProcessStep findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (processStep != null) {
			return processStep;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchProcessStepException(msg.toString());
	}

	/**
	 * Returns the last process step in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessStep> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ProcessStep> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process steps before and after the current process step in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param processStepId the primary key of the current process step
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process step
	 * @throws NoSuchProcessStepException if a process step with the primary key could not be found
	 */
	@Override
	public ProcessStep[] findByUuid_C_PrevAndNext(long processStepId,
		String uuid, long companyId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = findByPrimaryKey(processStepId);

		Session session = null;

		try {
			session = openSession();

			ProcessStep[] array = new ProcessStepImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, processStep, uuid,
					companyId, orderByComparator, true);

			array[1] = processStep;

			array[2] = getByUuid_C_PrevAndNext(session, processStep, uuid,
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

	protected ProcessStep getByUuid_C_PrevAndNext(Session session,
		ProcessStep processStep, String uuid, long companyId,
		OrderByComparator<ProcessStep> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PROCESSSTEP_WHERE);

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
			query.append(ProcessStepModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(processStep);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessStep> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process steps where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ProcessStep processStep : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processStep);
		}
	}

	/**
	 * Returns the number of process steps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching process steps
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSSTEP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "processStep.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "processStep.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(processStep.uuid IS NULL OR processStep.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "processStep.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_S_P_ID = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS_P_ID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_P_ID =
		new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByS_P_ID",
			new String[] { Long.class.getName() },
			ProcessStepModelImpl.SERVICEPROCESSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_S_P_ID = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS_P_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the process steps where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @return the matching process steps
	 */
	@Override
	public List<ProcessStep> findByS_P_ID(long serviceProcessId) {
		return findByS_P_ID(serviceProcessId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process steps where serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @return the range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByS_P_ID(long serviceProcessId, int start,
		int end) {
		return findByS_P_ID(serviceProcessId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process steps where serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByS_P_ID(long serviceProcessId, int start,
		int end, OrderByComparator<ProcessStep> orderByComparator) {
		return findByS_P_ID(serviceProcessId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the process steps where serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByS_P_ID(long serviceProcessId, int start,
		int end, OrderByComparator<ProcessStep> orderByComparator,
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

		List<ProcessStep> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessStep>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessStep processStep : list) {
					if ((serviceProcessId != processStep.getServiceProcessId())) {
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

			query.append(_SQL_SELECT_PROCESSSTEP_WHERE);

			query.append(_FINDER_COLUMN_S_P_ID_SERVICEPROCESSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessStepModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceProcessId);

				if (!pagination) {
					list = (List<ProcessStep>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessStep>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process step in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step
	 * @throws NoSuchProcessStepException if a matching process step could not be found
	 */
	@Override
	public ProcessStep findByS_P_ID_First(long serviceProcessId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = fetchByS_P_ID_First(serviceProcessId,
				orderByComparator);

		if (processStep != null) {
			return processStep;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append("}");

		throw new NoSuchProcessStepException(msg.toString());
	}

	/**
	 * Returns the first process step in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchByS_P_ID_First(long serviceProcessId,
		OrderByComparator<ProcessStep> orderByComparator) {
		List<ProcessStep> list = findByS_P_ID(serviceProcessId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process step in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step
	 * @throws NoSuchProcessStepException if a matching process step could not be found
	 */
	@Override
	public ProcessStep findByS_P_ID_Last(long serviceProcessId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = fetchByS_P_ID_Last(serviceProcessId,
				orderByComparator);

		if (processStep != null) {
			return processStep;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append("}");

		throw new NoSuchProcessStepException(msg.toString());
	}

	/**
	 * Returns the last process step in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchByS_P_ID_Last(long serviceProcessId,
		OrderByComparator<ProcessStep> orderByComparator) {
		int count = countByS_P_ID(serviceProcessId);

		if (count == 0) {
			return null;
		}

		List<ProcessStep> list = findByS_P_ID(serviceProcessId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process steps before and after the current process step in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param processStepId the primary key of the current process step
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process step
	 * @throws NoSuchProcessStepException if a process step with the primary key could not be found
	 */
	@Override
	public ProcessStep[] findByS_P_ID_PrevAndNext(long processStepId,
		long serviceProcessId, OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = findByPrimaryKey(processStepId);

		Session session = null;

		try {
			session = openSession();

			ProcessStep[] array = new ProcessStepImpl[3];

			array[0] = getByS_P_ID_PrevAndNext(session, processStep,
					serviceProcessId, orderByComparator, true);

			array[1] = processStep;

			array[2] = getByS_P_ID_PrevAndNext(session, processStep,
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

	protected ProcessStep getByS_P_ID_PrevAndNext(Session session,
		ProcessStep processStep, long serviceProcessId,
		OrderByComparator<ProcessStep> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PROCESSSTEP_WHERE);

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
			query.append(ProcessStepModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(serviceProcessId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processStep);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessStep> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process steps where serviceProcessId = &#63; from the database.
	 *
	 * @param serviceProcessId the service process ID
	 */
	@Override
	public void removeByS_P_ID(long serviceProcessId) {
		for (ProcessStep processStep : findByS_P_ID(serviceProcessId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processStep);
		}
	}

	/**
	 * Returns the number of process steps where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @return the number of matching process steps
	 */
	@Override
	public int countByS_P_ID(long serviceProcessId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_S_P_ID;

		Object[] finderArgs = new Object[] { serviceProcessId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSSTEP_WHERE);

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

	private static final String _FINDER_COLUMN_S_P_ID_SERVICEPROCESSID_2 = "processStep.serviceProcessId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_SC_GID = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchBySC_GID",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			ProcessStepModelImpl.STEPCODE_COLUMN_BITMASK |
			ProcessStepModelImpl.GROUPID_COLUMN_BITMASK |
			ProcessStepModelImpl.SERVICEPROCESSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SC_GID = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySC_GID",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns the process step where stepCode = &#63; and groupId = &#63; and serviceProcessId = &#63; or throws a {@link NoSuchProcessStepException} if it could not be found.
	 *
	 * @param stepCode the step code
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @return the matching process step
	 * @throws NoSuchProcessStepException if a matching process step could not be found
	 */
	@Override
	public ProcessStep findBySC_GID(String stepCode, long groupId,
		long serviceProcessId) throws NoSuchProcessStepException {
		ProcessStep processStep = fetchBySC_GID(stepCode, groupId,
				serviceProcessId);

		if (processStep == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("stepCode=");
			msg.append(stepCode);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(", serviceProcessId=");
			msg.append(serviceProcessId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchProcessStepException(msg.toString());
		}

		return processStep;
	}

	/**
	 * Returns the process step where stepCode = &#63; and groupId = &#63; and serviceProcessId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param stepCode the step code
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @return the matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchBySC_GID(String stepCode, long groupId,
		long serviceProcessId) {
		return fetchBySC_GID(stepCode, groupId, serviceProcessId, true);
	}

	/**
	 * Returns the process step where stepCode = &#63; and groupId = &#63; and serviceProcessId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param stepCode the step code
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchBySC_GID(String stepCode, long groupId,
		long serviceProcessId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { stepCode, groupId, serviceProcessId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SC_GID,
					finderArgs, this);
		}

		if (result instanceof ProcessStep) {
			ProcessStep processStep = (ProcessStep)result;

			if (!Objects.equals(stepCode, processStep.getStepCode()) ||
					(groupId != processStep.getGroupId()) ||
					(serviceProcessId != processStep.getServiceProcessId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_PROCESSSTEP_WHERE);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_SC_GID_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_SC_GID_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_SC_GID_STEPCODE_2);
			}

			query.append(_FINDER_COLUMN_SC_GID_GROUPID_2);

			query.append(_FINDER_COLUMN_SC_GID_SERVICEPROCESSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStepCode) {
					qPos.add(stepCode);
				}

				qPos.add(groupId);

				qPos.add(serviceProcessId);

				List<ProcessStep> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SC_GID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ProcessStepPersistenceImpl.fetchBySC_GID(String, long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProcessStep processStep = list.get(0);

					result = processStep;

					cacheResult(processStep);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SC_GID, finderArgs);

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
			return (ProcessStep)result;
		}
	}

	/**
	 * Removes the process step where stepCode = &#63; and groupId = &#63; and serviceProcessId = &#63; from the database.
	 *
	 * @param stepCode the step code
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @return the process step that was removed
	 */
	@Override
	public ProcessStep removeBySC_GID(String stepCode, long groupId,
		long serviceProcessId) throws NoSuchProcessStepException {
		ProcessStep processStep = findBySC_GID(stepCode, groupId,
				serviceProcessId);

		return remove(processStep);
	}

	/**
	 * Returns the number of process steps where stepCode = &#63; and groupId = &#63; and serviceProcessId = &#63;.
	 *
	 * @param stepCode the step code
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @return the number of matching process steps
	 */
	@Override
	public int countBySC_GID(String stepCode, long groupId,
		long serviceProcessId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SC_GID;

		Object[] finderArgs = new Object[] { stepCode, groupId, serviceProcessId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PROCESSSTEP_WHERE);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_SC_GID_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_SC_GID_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_SC_GID_STEPCODE_2);
			}

			query.append(_FINDER_COLUMN_SC_GID_GROUPID_2);

			query.append(_FINDER_COLUMN_SC_GID_SERVICEPROCESSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStepCode) {
					qPos.add(stepCode);
				}

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_SC_GID_STEPCODE_1 = "processStep.stepCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SC_GID_STEPCODE_2 = "processStep.stepCode = ? AND ";
	private static final String _FINDER_COLUMN_SC_GID_STEPCODE_3 = "(processStep.stepCode IS NULL OR processStep.stepCode = '') AND ";
	private static final String _FINDER_COLUMN_SC_GID_GROUPID_2 = "processStep.groupId = ? AND ";
	private static final String _FINDER_COLUMN_SC_GID_SERVICEPROCESSID_2 = "processStep.serviceProcessId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SC_SPID = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySC_SPID",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_SPID =
		new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySC_SPID",
			new String[] { String.class.getName(), Long.class.getName() },
			ProcessStepModelImpl.STEPCODE_COLUMN_BITMASK |
			ProcessStepModelImpl.SERVICEPROCESSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SC_SPID = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySC_SPID",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the process steps where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @return the matching process steps
	 */
	@Override
	public List<ProcessStep> findBySC_SPID(String stepCode,
		long serviceProcessId) {
		return findBySC_SPID(stepCode, serviceProcessId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process steps where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @return the range of matching process steps
	 */
	@Override
	public List<ProcessStep> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end) {
		return findBySC_SPID(stepCode, serviceProcessId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process steps where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process steps
	 */
	@Override
	public List<ProcessStep> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator) {
		return findBySC_SPID(stepCode, serviceProcessId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process steps where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process steps
	 */
	@Override
	public List<ProcessStep> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_SPID;
			finderArgs = new Object[] { stepCode, serviceProcessId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SC_SPID;
			finderArgs = new Object[] {
					stepCode, serviceProcessId,
					
					start, end, orderByComparator
				};
		}

		List<ProcessStep> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessStep>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessStep processStep : list) {
					if (!Objects.equals(stepCode, processStep.getStepCode()) ||
							(serviceProcessId != processStep.getServiceProcessId())) {
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

			query.append(_SQL_SELECT_PROCESSSTEP_WHERE);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_2);
			}

			query.append(_FINDER_COLUMN_SC_SPID_SERVICEPROCESSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessStepModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStepCode) {
					qPos.add(stepCode);
				}

				qPos.add(serviceProcessId);

				if (!pagination) {
					list = (List<ProcessStep>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessStep>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process step in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step
	 * @throws NoSuchProcessStepException if a matching process step could not be found
	 */
	@Override
	public ProcessStep findBySC_SPID_First(String stepCode,
		long serviceProcessId, OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = fetchBySC_SPID_First(stepCode,
				serviceProcessId, orderByComparator);

		if (processStep != null) {
			return processStep;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stepCode=");
		msg.append(stepCode);

		msg.append(", serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append("}");

		throw new NoSuchProcessStepException(msg.toString());
	}

	/**
	 * Returns the first process step in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchBySC_SPID_First(String stepCode,
		long serviceProcessId, OrderByComparator<ProcessStep> orderByComparator) {
		List<ProcessStep> list = findBySC_SPID(stepCode, serviceProcessId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process step in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step
	 * @throws NoSuchProcessStepException if a matching process step could not be found
	 */
	@Override
	public ProcessStep findBySC_SPID_Last(String stepCode,
		long serviceProcessId, OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = fetchBySC_SPID_Last(stepCode,
				serviceProcessId, orderByComparator);

		if (processStep != null) {
			return processStep;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stepCode=");
		msg.append(stepCode);

		msg.append(", serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append("}");

		throw new NoSuchProcessStepException(msg.toString());
	}

	/**
	 * Returns the last process step in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchBySC_SPID_Last(String stepCode,
		long serviceProcessId, OrderByComparator<ProcessStep> orderByComparator) {
		int count = countBySC_SPID(stepCode, serviceProcessId);

		if (count == 0) {
			return null;
		}

		List<ProcessStep> list = findBySC_SPID(stepCode, serviceProcessId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process steps before and after the current process step in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param processStepId the primary key of the current process step
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process step
	 * @throws NoSuchProcessStepException if a process step with the primary key could not be found
	 */
	@Override
	public ProcessStep[] findBySC_SPID_PrevAndNext(long processStepId,
		String stepCode, long serviceProcessId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = findByPrimaryKey(processStepId);

		Session session = null;

		try {
			session = openSession();

			ProcessStep[] array = new ProcessStepImpl[3];

			array[0] = getBySC_SPID_PrevAndNext(session, processStep, stepCode,
					serviceProcessId, orderByComparator, true);

			array[1] = processStep;

			array[2] = getBySC_SPID_PrevAndNext(session, processStep, stepCode,
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

	protected ProcessStep getBySC_SPID_PrevAndNext(Session session,
		ProcessStep processStep, String stepCode, long serviceProcessId,
		OrderByComparator<ProcessStep> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PROCESSSTEP_WHERE);

		boolean bindStepCode = false;

		if (stepCode == null) {
			query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_1);
		}
		else if (stepCode.equals("")) {
			query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_3);
		}
		else {
			bindStepCode = true;

			query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_2);
		}

		query.append(_FINDER_COLUMN_SC_SPID_SERVICEPROCESSID_2);

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
			query.append(ProcessStepModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindStepCode) {
			qPos.add(stepCode);
		}

		qPos.add(serviceProcessId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processStep);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessStep> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process steps where stepCode = &#63; and serviceProcessId = &#63; from the database.
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 */
	@Override
	public void removeBySC_SPID(String stepCode, long serviceProcessId) {
		for (ProcessStep processStep : findBySC_SPID(stepCode,
				serviceProcessId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processStep);
		}
	}

	/**
	 * Returns the number of process steps where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @return the number of matching process steps
	 */
	@Override
	public int countBySC_SPID(String stepCode, long serviceProcessId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SC_SPID;

		Object[] finderArgs = new Object[] { stepCode, serviceProcessId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSSTEP_WHERE);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_2);
			}

			query.append(_FINDER_COLUMN_SC_SPID_SERVICEPROCESSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStepCode) {
					qPos.add(stepCode);
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

	private static final String _FINDER_COLUMN_SC_SPID_STEPCODE_1 = "processStep.stepCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SC_SPID_STEPCODE_2 = "processStep.stepCode = ? AND ";
	private static final String _FINDER_COLUMN_SC_SPID_STEPCODE_3 = "(processStep.stepCode IS NULL OR processStep.stepCode = '') AND ";
	private static final String _FINDER_COLUMN_SC_SPID_SERVICEPROCESSID_2 = "processStep.serviceProcessId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DST_DSST = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDST_DSST",
			new String[] {
				String.class.getName(), String.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DST_DSST =
		new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDST_DSST",
			new String[] {
				String.class.getName(), String.class.getName(),
				Long.class.getName()
			},
			ProcessStepModelImpl.DOSSIERSTATUS_COLUMN_BITMASK |
			ProcessStepModelImpl.DOSSIERSUBSTATUS_COLUMN_BITMASK |
			ProcessStepModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DST_DSST = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDST_DSST",
			new String[] {
				String.class.getName(), String.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the process steps where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	 *
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param groupId the group ID
	 * @return the matching process steps
	 */
	@Override
	public List<ProcessStep> findByDST_DSST(String dossierStatus,
		String dossierSubStatus, long groupId) {
		return findByDST_DSST(dossierStatus, dossierSubStatus, groupId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process steps where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @return the range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByDST_DSST(String dossierStatus,
		String dossierSubStatus, long groupId, int start, int end) {
		return findByDST_DSST(dossierStatus, dossierSubStatus, groupId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the process steps where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByDST_DSST(String dossierStatus,
		String dossierSubStatus, long groupId, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator) {
		return findByDST_DSST(dossierStatus, dossierSubStatus, groupId, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process steps where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByDST_DSST(String dossierStatus,
		String dossierSubStatus, long groupId, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DST_DSST;
			finderArgs = new Object[] { dossierStatus, dossierSubStatus, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DST_DSST;
			finderArgs = new Object[] {
					dossierStatus, dossierSubStatus, groupId,
					
					start, end, orderByComparator
				};
		}

		List<ProcessStep> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessStep>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessStep processStep : list) {
					if (!Objects.equals(dossierStatus,
								processStep.getDossierStatus()) ||
							!Objects.equals(dossierSubStatus,
								processStep.getDossierSubStatus()) ||
							(groupId != processStep.getGroupId())) {
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

			query.append(_SQL_SELECT_PROCESSSTEP_WHERE);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSTATUS_2);
			}

			boolean bindDossierSubStatus = false;

			if (dossierSubStatus == null) {
				query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSUBSTATUS_1);
			}
			else if (dossierSubStatus.equals("")) {
				query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSUBSTATUS_3);
			}
			else {
				bindDossierSubStatus = true;

				query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSUBSTATUS_2);
			}

			query.append(_FINDER_COLUMN_DST_DSST_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessStepModelImpl.ORDER_BY_JPQL);
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

				if (bindDossierSubStatus) {
					qPos.add(dossierSubStatus);
				}

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ProcessStep>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessStep>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process step in the ordered set where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	 *
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step
	 * @throws NoSuchProcessStepException if a matching process step could not be found
	 */
	@Override
	public ProcessStep findByDST_DSST_First(String dossierStatus,
		String dossierSubStatus, long groupId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = fetchByDST_DSST_First(dossierStatus,
				dossierSubStatus, groupId, orderByComparator);

		if (processStep != null) {
			return processStep;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", dossierSubStatus=");
		msg.append(dossierSubStatus);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchProcessStepException(msg.toString());
	}

	/**
	 * Returns the first process step in the ordered set where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	 *
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchByDST_DSST_First(String dossierStatus,
		String dossierSubStatus, long groupId,
		OrderByComparator<ProcessStep> orderByComparator) {
		List<ProcessStep> list = findByDST_DSST(dossierStatus,
				dossierSubStatus, groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process step in the ordered set where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	 *
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step
	 * @throws NoSuchProcessStepException if a matching process step could not be found
	 */
	@Override
	public ProcessStep findByDST_DSST_Last(String dossierStatus,
		String dossierSubStatus, long groupId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = fetchByDST_DSST_Last(dossierStatus,
				dossierSubStatus, groupId, orderByComparator);

		if (processStep != null) {
			return processStep;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", dossierSubStatus=");
		msg.append(dossierSubStatus);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchProcessStepException(msg.toString());
	}

	/**
	 * Returns the last process step in the ordered set where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	 *
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchByDST_DSST_Last(String dossierStatus,
		String dossierSubStatus, long groupId,
		OrderByComparator<ProcessStep> orderByComparator) {
		int count = countByDST_DSST(dossierStatus, dossierSubStatus, groupId);

		if (count == 0) {
			return null;
		}

		List<ProcessStep> list = findByDST_DSST(dossierStatus,
				dossierSubStatus, groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process steps before and after the current process step in the ordered set where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	 *
	 * @param processStepId the primary key of the current process step
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process step
	 * @throws NoSuchProcessStepException if a process step with the primary key could not be found
	 */
	@Override
	public ProcessStep[] findByDST_DSST_PrevAndNext(long processStepId,
		String dossierStatus, String dossierSubStatus, long groupId,
		OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = findByPrimaryKey(processStepId);

		Session session = null;

		try {
			session = openSession();

			ProcessStep[] array = new ProcessStepImpl[3];

			array[0] = getByDST_DSST_PrevAndNext(session, processStep,
					dossierStatus, dossierSubStatus, groupId,
					orderByComparator, true);

			array[1] = processStep;

			array[2] = getByDST_DSST_PrevAndNext(session, processStep,
					dossierStatus, dossierSubStatus, groupId,
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

	protected ProcessStep getByDST_DSST_PrevAndNext(Session session,
		ProcessStep processStep, String dossierStatus, String dossierSubStatus,
		long groupId, OrderByComparator<ProcessStep> orderByComparator,
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

		query.append(_SQL_SELECT_PROCESSSTEP_WHERE);

		boolean bindDossierStatus = false;

		if (dossierStatus == null) {
			query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSTATUS_1);
		}
		else if (dossierStatus.equals("")) {
			query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSTATUS_3);
		}
		else {
			bindDossierStatus = true;

			query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSTATUS_2);
		}

		boolean bindDossierSubStatus = false;

		if (dossierSubStatus == null) {
			query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSUBSTATUS_1);
		}
		else if (dossierSubStatus.equals("")) {
			query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSUBSTATUS_3);
		}
		else {
			bindDossierSubStatus = true;

			query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSUBSTATUS_2);
		}

		query.append(_FINDER_COLUMN_DST_DSST_GROUPID_2);

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
			query.append(ProcessStepModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindDossierStatus) {
			qPos.add(dossierStatus);
		}

		if (bindDossierSubStatus) {
			qPos.add(dossierSubStatus);
		}

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processStep);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessStep> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process steps where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63; from the database.
	 *
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param groupId the group ID
	 */
	@Override
	public void removeByDST_DSST(String dossierStatus, String dossierSubStatus,
		long groupId) {
		for (ProcessStep processStep : findByDST_DSST(dossierStatus,
				dossierSubStatus, groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(processStep);
		}
	}

	/**
	 * Returns the number of process steps where dossierStatus = &#63; and dossierSubStatus = &#63; and groupId = &#63;.
	 *
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param groupId the group ID
	 * @return the number of matching process steps
	 */
	@Override
	public int countByDST_DSST(String dossierStatus, String dossierSubStatus,
		long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DST_DSST;

		Object[] finderArgs = new Object[] {
				dossierStatus, dossierSubStatus, groupId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PROCESSSTEP_WHERE);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSTATUS_2);
			}

			boolean bindDossierSubStatus = false;

			if (dossierSubStatus == null) {
				query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSUBSTATUS_1);
			}
			else if (dossierSubStatus.equals("")) {
				query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSUBSTATUS_3);
			}
			else {
				bindDossierSubStatus = true;

				query.append(_FINDER_COLUMN_DST_DSST_DOSSIERSUBSTATUS_2);
			}

			query.append(_FINDER_COLUMN_DST_DSST_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (bindDossierSubStatus) {
					qPos.add(dossierSubStatus);
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

	private static final String _FINDER_COLUMN_DST_DSST_DOSSIERSTATUS_1 = "processStep.dossierStatus IS NULL AND ";
	private static final String _FINDER_COLUMN_DST_DSST_DOSSIERSTATUS_2 = "processStep.dossierStatus = ? AND ";
	private static final String _FINDER_COLUMN_DST_DSST_DOSSIERSTATUS_3 = "(processStep.dossierStatus IS NULL OR processStep.dossierStatus = '') AND ";
	private static final String _FINDER_COLUMN_DST_DSST_DOSSIERSUBSTATUS_1 = "processStep.dossierSubStatus IS NULL AND ";
	private static final String _FINDER_COLUMN_DST_DSST_DOSSIERSUBSTATUS_2 = "processStep.dossierSubStatus = ? AND ";
	private static final String _FINDER_COLUMN_DST_DSST_DOSSIERSUBSTATUS_3 = "(processStep.dossierSubStatus IS NULL OR processStep.dossierSubStatus = '') AND ";
	private static final String _FINDER_COLUMN_DST_DSST_GROUPID_2 = "processStep.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_SP_SNO = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_SP_SNO",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SP_SNO =
		new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, ProcessStepImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_SP_SNO",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			ProcessStepModelImpl.GROUPID_COLUMN_BITMASK |
			ProcessStepModelImpl.SERVICEPROCESSID_COLUMN_BITMASK |
			ProcessStepModelImpl.SEQUENCENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_SP_SNO = new FinderPath(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_SP_SNO",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the process steps where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @return the matching process steps
	 */
	@Override
	public List<ProcessStep> findByG_SP_SNO(long groupId,
		long serviceProcessId, String sequenceNo) {
		return findByG_SP_SNO(groupId, serviceProcessId, sequenceNo,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process steps where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @return the range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByG_SP_SNO(long groupId,
		long serviceProcessId, String sequenceNo, int start, int end) {
		return findByG_SP_SNO(groupId, serviceProcessId, sequenceNo, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the process steps where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByG_SP_SNO(long groupId,
		long serviceProcessId, String sequenceNo, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator) {
		return findByG_SP_SNO(groupId, serviceProcessId, sequenceNo, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process steps where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process steps
	 */
	@Override
	public List<ProcessStep> findByG_SP_SNO(long groupId,
		long serviceProcessId, String sequenceNo, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SP_SNO;
			finderArgs = new Object[] { groupId, serviceProcessId, sequenceNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_SP_SNO;
			finderArgs = new Object[] {
					groupId, serviceProcessId, sequenceNo,
					
					start, end, orderByComparator
				};
		}

		List<ProcessStep> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessStep>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessStep processStep : list) {
					if ((groupId != processStep.getGroupId()) ||
							(serviceProcessId != processStep.getServiceProcessId()) ||
							!Objects.equals(sequenceNo,
								processStep.getSequenceNo())) {
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

			query.append(_SQL_SELECT_PROCESSSTEP_WHERE);

			query.append(_FINDER_COLUMN_G_SP_SNO_GROUPID_2);

			query.append(_FINDER_COLUMN_G_SP_SNO_SERVICEPROCESSID_2);

			boolean bindSequenceNo = false;

			if (sequenceNo == null) {
				query.append(_FINDER_COLUMN_G_SP_SNO_SEQUENCENO_1);
			}
			else if (sequenceNo.equals("")) {
				query.append(_FINDER_COLUMN_G_SP_SNO_SEQUENCENO_3);
			}
			else {
				bindSequenceNo = true;

				query.append(_FINDER_COLUMN_G_SP_SNO_SEQUENCENO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessStepModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceProcessId);

				if (bindSequenceNo) {
					qPos.add(sequenceNo);
				}

				if (!pagination) {
					list = (List<ProcessStep>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessStep>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process step in the ordered set where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step
	 * @throws NoSuchProcessStepException if a matching process step could not be found
	 */
	@Override
	public ProcessStep findByG_SP_SNO_First(long groupId,
		long serviceProcessId, String sequenceNo,
		OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = fetchByG_SP_SNO_First(groupId,
				serviceProcessId, sequenceNo, orderByComparator);

		if (processStep != null) {
			return processStep;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append(", sequenceNo=");
		msg.append(sequenceNo);

		msg.append("}");

		throw new NoSuchProcessStepException(msg.toString());
	}

	/**
	 * Returns the first process step in the ordered set where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchByG_SP_SNO_First(long groupId,
		long serviceProcessId, String sequenceNo,
		OrderByComparator<ProcessStep> orderByComparator) {
		List<ProcessStep> list = findByG_SP_SNO(groupId, serviceProcessId,
				sequenceNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process step in the ordered set where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step
	 * @throws NoSuchProcessStepException if a matching process step could not be found
	 */
	@Override
	public ProcessStep findByG_SP_SNO_Last(long groupId, long serviceProcessId,
		String sequenceNo, OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = fetchByG_SP_SNO_Last(groupId,
				serviceProcessId, sequenceNo, orderByComparator);

		if (processStep != null) {
			return processStep;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append(", sequenceNo=");
		msg.append(sequenceNo);

		msg.append("}");

		throw new NoSuchProcessStepException(msg.toString());
	}

	/**
	 * Returns the last process step in the ordered set where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step, or <code>null</code> if a matching process step could not be found
	 */
	@Override
	public ProcessStep fetchByG_SP_SNO_Last(long groupId,
		long serviceProcessId, String sequenceNo,
		OrderByComparator<ProcessStep> orderByComparator) {
		int count = countByG_SP_SNO(groupId, serviceProcessId, sequenceNo);

		if (count == 0) {
			return null;
		}

		List<ProcessStep> list = findByG_SP_SNO(groupId, serviceProcessId,
				sequenceNo, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process steps before and after the current process step in the ordered set where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	 *
	 * @param processStepId the primary key of the current process step
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process step
	 * @throws NoSuchProcessStepException if a process step with the primary key could not be found
	 */
	@Override
	public ProcessStep[] findByG_SP_SNO_PrevAndNext(long processStepId,
		long groupId, long serviceProcessId, String sequenceNo,
		OrderByComparator<ProcessStep> orderByComparator)
		throws NoSuchProcessStepException {
		ProcessStep processStep = findByPrimaryKey(processStepId);

		Session session = null;

		try {
			session = openSession();

			ProcessStep[] array = new ProcessStepImpl[3];

			array[0] = getByG_SP_SNO_PrevAndNext(session, processStep, groupId,
					serviceProcessId, sequenceNo, orderByComparator, true);

			array[1] = processStep;

			array[2] = getByG_SP_SNO_PrevAndNext(session, processStep, groupId,
					serviceProcessId, sequenceNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessStep getByG_SP_SNO_PrevAndNext(Session session,
		ProcessStep processStep, long groupId, long serviceProcessId,
		String sequenceNo, OrderByComparator<ProcessStep> orderByComparator,
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

		query.append(_SQL_SELECT_PROCESSSTEP_WHERE);

		query.append(_FINDER_COLUMN_G_SP_SNO_GROUPID_2);

		query.append(_FINDER_COLUMN_G_SP_SNO_SERVICEPROCESSID_2);

		boolean bindSequenceNo = false;

		if (sequenceNo == null) {
			query.append(_FINDER_COLUMN_G_SP_SNO_SEQUENCENO_1);
		}
		else if (sequenceNo.equals("")) {
			query.append(_FINDER_COLUMN_G_SP_SNO_SEQUENCENO_3);
		}
		else {
			bindSequenceNo = true;

			query.append(_FINDER_COLUMN_G_SP_SNO_SEQUENCENO_2);
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
			query.append(ProcessStepModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(serviceProcessId);

		if (bindSequenceNo) {
			qPos.add(sequenceNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processStep);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessStep> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process steps where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 */
	@Override
	public void removeByG_SP_SNO(long groupId, long serviceProcessId,
		String sequenceNo) {
		for (ProcessStep processStep : findByG_SP_SNO(groupId,
				serviceProcessId, sequenceNo, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(processStep);
		}
	}

	/**
	 * Returns the number of process steps where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @return the number of matching process steps
	 */
	@Override
	public int countByG_SP_SNO(long groupId, long serviceProcessId,
		String sequenceNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_SP_SNO;

		Object[] finderArgs = new Object[] { groupId, serviceProcessId, sequenceNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PROCESSSTEP_WHERE);

			query.append(_FINDER_COLUMN_G_SP_SNO_GROUPID_2);

			query.append(_FINDER_COLUMN_G_SP_SNO_SERVICEPROCESSID_2);

			boolean bindSequenceNo = false;

			if (sequenceNo == null) {
				query.append(_FINDER_COLUMN_G_SP_SNO_SEQUENCENO_1);
			}
			else if (sequenceNo.equals("")) {
				query.append(_FINDER_COLUMN_G_SP_SNO_SEQUENCENO_3);
			}
			else {
				bindSequenceNo = true;

				query.append(_FINDER_COLUMN_G_SP_SNO_SEQUENCENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceProcessId);

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

	private static final String _FINDER_COLUMN_G_SP_SNO_GROUPID_2 = "processStep.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_SP_SNO_SERVICEPROCESSID_2 = "processStep.serviceProcessId = ? AND ";
	private static final String _FINDER_COLUMN_G_SP_SNO_SEQUENCENO_1 = "processStep.sequenceNo IS NULL";
	private static final String _FINDER_COLUMN_G_SP_SNO_SEQUENCENO_2 = "processStep.sequenceNo = ?";
	private static final String _FINDER_COLUMN_G_SP_SNO_SEQUENCENO_3 = "(processStep.sequenceNo IS NULL OR processStep.sequenceNo = '')";

	public ProcessStepPersistenceImpl() {
		setModelClass(ProcessStep.class);

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
	 * Caches the process step in the entity cache if it is enabled.
	 *
	 * @param processStep the process step
	 */
	@Override
	public void cacheResult(ProcessStep processStep) {
		entityCache.putResult(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepImpl.class, processStep.getPrimaryKey(), processStep);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { processStep.getUuid(), processStep.getGroupId() },
			processStep);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SC_GID,
			new Object[] {
				processStep.getStepCode(), processStep.getGroupId(),
				processStep.getServiceProcessId()
			}, processStep);

		processStep.resetOriginalValues();
	}

	/**
	 * Caches the process steps in the entity cache if it is enabled.
	 *
	 * @param processSteps the process steps
	 */
	@Override
	public void cacheResult(List<ProcessStep> processSteps) {
		for (ProcessStep processStep : processSteps) {
			if (entityCache.getResult(
						ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
						ProcessStepImpl.class, processStep.getPrimaryKey()) == null) {
				cacheResult(processStep);
			}
			else {
				processStep.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all process steps.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProcessStepImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the process step.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProcessStep processStep) {
		entityCache.removeResult(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepImpl.class, processStep.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ProcessStepModelImpl)processStep, true);
	}

	@Override
	public void clearCache(List<ProcessStep> processSteps) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProcessStep processStep : processSteps) {
			entityCache.removeResult(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
				ProcessStepImpl.class, processStep.getPrimaryKey());

			clearUniqueFindersCache((ProcessStepModelImpl)processStep, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ProcessStepModelImpl processStepModelImpl) {
		Object[] args = new Object[] {
				processStepModelImpl.getUuid(),
				processStepModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			processStepModelImpl, false);

		args = new Object[] {
				processStepModelImpl.getStepCode(),
				processStepModelImpl.getGroupId(),
				processStepModelImpl.getServiceProcessId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_SC_GID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_SC_GID, args,
			processStepModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ProcessStepModelImpl processStepModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					processStepModelImpl.getUuid(),
					processStepModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((processStepModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processStepModelImpl.getOriginalUuid(),
					processStepModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					processStepModelImpl.getStepCode(),
					processStepModelImpl.getGroupId(),
					processStepModelImpl.getServiceProcessId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_GID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SC_GID, args);
		}

		if ((processStepModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SC_GID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processStepModelImpl.getOriginalStepCode(),
					processStepModelImpl.getOriginalGroupId(),
					processStepModelImpl.getOriginalServiceProcessId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_GID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SC_GID, args);
		}
	}

	/**
	 * Creates a new process step with the primary key. Does not add the process step to the database.
	 *
	 * @param processStepId the primary key for the new process step
	 * @return the new process step
	 */
	@Override
	public ProcessStep create(long processStepId) {
		ProcessStep processStep = new ProcessStepImpl();

		processStep.setNew(true);
		processStep.setPrimaryKey(processStepId);

		String uuid = PortalUUIDUtil.generate();

		processStep.setUuid(uuid);

		processStep.setCompanyId(companyProvider.getCompanyId());

		return processStep;
	}

	/**
	 * Removes the process step with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param processStepId the primary key of the process step
	 * @return the process step that was removed
	 * @throws NoSuchProcessStepException if a process step with the primary key could not be found
	 */
	@Override
	public ProcessStep remove(long processStepId)
		throws NoSuchProcessStepException {
		return remove((Serializable)processStepId);
	}

	/**
	 * Removes the process step with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the process step
	 * @return the process step that was removed
	 * @throws NoSuchProcessStepException if a process step with the primary key could not be found
	 */
	@Override
	public ProcessStep remove(Serializable primaryKey)
		throws NoSuchProcessStepException {
		Session session = null;

		try {
			session = openSession();

			ProcessStep processStep = (ProcessStep)session.get(ProcessStepImpl.class,
					primaryKey);

			if (processStep == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProcessStepException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(processStep);
		}
		catch (NoSuchProcessStepException nsee) {
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
	protected ProcessStep removeImpl(ProcessStep processStep) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(processStep)) {
				processStep = (ProcessStep)session.get(ProcessStepImpl.class,
						processStep.getPrimaryKeyObj());
			}

			if (processStep != null) {
				session.delete(processStep);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (processStep != null) {
			clearCache(processStep);
		}

		return processStep;
	}

	@Override
	public ProcessStep updateImpl(ProcessStep processStep) {
		boolean isNew = processStep.isNew();

		if (!(processStep instanceof ProcessStepModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(processStep.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(processStep);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in processStep proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProcessStep implementation " +
				processStep.getClass());
		}

		ProcessStepModelImpl processStepModelImpl = (ProcessStepModelImpl)processStep;

		if (Validator.isNull(processStep.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			processStep.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (processStep.getCreateDate() == null)) {
			if (serviceContext == null) {
				processStep.setCreateDate(now);
			}
			else {
				processStep.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!processStepModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				processStep.setModifiedDate(now);
			}
			else {
				processStep.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (processStep.isNew()) {
				session.save(processStep);

				processStep.setNew(false);
			}
			else {
				processStep = (ProcessStep)session.merge(processStep);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ProcessStepModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { processStepModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					processStepModelImpl.getUuid(),
					processStepModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { processStepModelImpl.getServiceProcessId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_S_P_ID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_P_ID,
				args);

			args = new Object[] {
					processStepModelImpl.getStepCode(),
					processStepModelImpl.getServiceProcessId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_SPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_SPID,
				args);

			args = new Object[] {
					processStepModelImpl.getDossierStatus(),
					processStepModelImpl.getDossierSubStatus(),
					processStepModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DST_DSST, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DST_DSST,
				args);

			args = new Object[] {
					processStepModelImpl.getGroupId(),
					processStepModelImpl.getServiceProcessId(),
					processStepModelImpl.getSequenceNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SP_SNO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SP_SNO,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((processStepModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processStepModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { processStepModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((processStepModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processStepModelImpl.getOriginalUuid(),
						processStepModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						processStepModelImpl.getUuid(),
						processStepModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((processStepModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_P_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processStepModelImpl.getOriginalServiceProcessId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S_P_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_P_ID,
					args);

				args = new Object[] { processStepModelImpl.getServiceProcessId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S_P_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_P_ID,
					args);
			}

			if ((processStepModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_SPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processStepModelImpl.getOriginalStepCode(),
						processStepModelImpl.getOriginalServiceProcessId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_SPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_SPID,
					args);

				args = new Object[] {
						processStepModelImpl.getStepCode(),
						processStepModelImpl.getServiceProcessId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_SPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_SPID,
					args);
			}

			if ((processStepModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DST_DSST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processStepModelImpl.getOriginalDossierStatus(),
						processStepModelImpl.getOriginalDossierSubStatus(),
						processStepModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DST_DSST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DST_DSST,
					args);

				args = new Object[] {
						processStepModelImpl.getDossierStatus(),
						processStepModelImpl.getDossierSubStatus(),
						processStepModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DST_DSST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DST_DSST,
					args);
			}

			if ((processStepModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SP_SNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processStepModelImpl.getOriginalGroupId(),
						processStepModelImpl.getOriginalServiceProcessId(),
						processStepModelImpl.getOriginalSequenceNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SP_SNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SP_SNO,
					args);

				args = new Object[] {
						processStepModelImpl.getGroupId(),
						processStepModelImpl.getServiceProcessId(),
						processStepModelImpl.getSequenceNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SP_SNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SP_SNO,
					args);
			}
		}

		entityCache.putResult(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepImpl.class, processStep.getPrimaryKey(), processStep,
			false);

		clearUniqueFindersCache(processStepModelImpl, false);
		cacheUniqueFindersCache(processStepModelImpl);

		processStep.resetOriginalValues();

		return processStep;
	}

	/**
	 * Returns the process step with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the process step
	 * @return the process step
	 * @throws NoSuchProcessStepException if a process step with the primary key could not be found
	 */
	@Override
	public ProcessStep findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProcessStepException {
		ProcessStep processStep = fetchByPrimaryKey(primaryKey);

		if (processStep == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProcessStepException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return processStep;
	}

	/**
	 * Returns the process step with the primary key or throws a {@link NoSuchProcessStepException} if it could not be found.
	 *
	 * @param processStepId the primary key of the process step
	 * @return the process step
	 * @throws NoSuchProcessStepException if a process step with the primary key could not be found
	 */
	@Override
	public ProcessStep findByPrimaryKey(long processStepId)
		throws NoSuchProcessStepException {
		return findByPrimaryKey((Serializable)processStepId);
	}

	/**
	 * Returns the process step with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the process step
	 * @return the process step, or <code>null</code> if a process step with the primary key could not be found
	 */
	@Override
	public ProcessStep fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
				ProcessStepImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProcessStep processStep = (ProcessStep)serializable;

		if (processStep == null) {
			Session session = null;

			try {
				session = openSession();

				processStep = (ProcessStep)session.get(ProcessStepImpl.class,
						primaryKey);

				if (processStep != null) {
					cacheResult(processStep);
				}
				else {
					entityCache.putResult(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
						ProcessStepImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
					ProcessStepImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return processStep;
	}

	/**
	 * Returns the process step with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param processStepId the primary key of the process step
	 * @return the process step, or <code>null</code> if a process step with the primary key could not be found
	 */
	@Override
	public ProcessStep fetchByPrimaryKey(long processStepId) {
		return fetchByPrimaryKey((Serializable)processStepId);
	}

	@Override
	public Map<Serializable, ProcessStep> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProcessStep> map = new HashMap<Serializable, ProcessStep>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProcessStep processStep = fetchByPrimaryKey(primaryKey);

			if (processStep != null) {
				map.put(primaryKey, processStep);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
					ProcessStepImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProcessStep)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROCESSSTEP_WHERE_PKS_IN);

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

			for (ProcessStep processStep : (List<ProcessStep>)q.list()) {
				map.put(processStep.getPrimaryKeyObj(), processStep);

				cacheResult(processStep);

				uncachedPrimaryKeys.remove(processStep.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProcessStepModelImpl.ENTITY_CACHE_ENABLED,
					ProcessStepImpl.class, primaryKey, nullModel);
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
	 * Returns all the process steps.
	 *
	 * @return the process steps
	 */
	@Override
	public List<ProcessStep> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process steps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @return the range of process steps
	 */
	@Override
	public List<ProcessStep> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the process steps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of process steps
	 */
	@Override
	public List<ProcessStep> findAll(int start, int end,
		OrderByComparator<ProcessStep> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process steps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process steps
	 * @param end the upper bound of the range of process steps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of process steps
	 */
	@Override
	public List<ProcessStep> findAll(int start, int end,
		OrderByComparator<ProcessStep> orderByComparator,
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

		List<ProcessStep> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessStep>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROCESSSTEP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROCESSSTEP;

				if (pagination) {
					sql = sql.concat(ProcessStepModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProcessStep>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessStep>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the process steps from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProcessStep processStep : findAll()) {
			remove(processStep);
		}
	}

	/**
	 * Returns the number of process steps.
	 *
	 * @return the number of process steps
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROCESSSTEP);

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
		return ProcessStepModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the process step persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProcessStepImpl.class.getName());
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
	private static final String _SQL_SELECT_PROCESSSTEP = "SELECT processStep FROM ProcessStep processStep";
	private static final String _SQL_SELECT_PROCESSSTEP_WHERE_PKS_IN = "SELECT processStep FROM ProcessStep processStep WHERE processStepId IN (";
	private static final String _SQL_SELECT_PROCESSSTEP_WHERE = "SELECT processStep FROM ProcessStep processStep WHERE ";
	private static final String _SQL_COUNT_PROCESSSTEP = "SELECT COUNT(processStep) FROM ProcessStep processStep";
	private static final String _SQL_COUNT_PROCESSSTEP_WHERE = "SELECT COUNT(processStep) FROM ProcessStep processStep WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "processStep.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProcessStep exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProcessStep exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ProcessStepPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}