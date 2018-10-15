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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchProcessStepRoleException;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.impl.ProcessStepRoleImpl;
import org.opencps.dossiermgt.model.impl.ProcessStepRoleModelImpl;
import org.opencps.dossiermgt.service.persistence.ProcessStepRolePK;
import org.opencps.dossiermgt.service.persistence.ProcessStepRolePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the process step role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ProcessStepRolePersistence
 * @see org.opencps.dossiermgt.service.persistence.ProcessStepRoleUtil
 * @generated
 */
@ProviderType
public class ProcessStepRolePersistenceImpl extends BasePersistenceImpl<ProcessStepRole>
	implements ProcessStepRolePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProcessStepRoleUtil} to access the process step role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProcessStepRoleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleModelImpl.FINDER_CACHE_ENABLED,
			ProcessStepRoleImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleModelImpl.FINDER_CACHE_ENABLED,
			ProcessStepRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleModelImpl.FINDER_CACHE_ENABLED,
			ProcessStepRoleImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleModelImpl.FINDER_CACHE_ENABLED,
			ProcessStepRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ProcessStepRoleModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the process step roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching process step roles
	 */
	@Override
	public List<ProcessStepRole> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process step roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process step roles
	 * @param end the upper bound of the range of process step roles (not inclusive)
	 * @return the range of matching process step roles
	 */
	@Override
	public List<ProcessStepRole> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process step roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process step roles
	 * @param end the upper bound of the range of process step roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process step roles
	 */
	@Override
	public List<ProcessStepRole> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessStepRole> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process step roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process step roles
	 * @param end the upper bound of the range of process step roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process step roles
	 */
	@Override
	public List<ProcessStepRole> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessStepRole> orderByComparator,
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

		List<ProcessStepRole> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessStepRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessStepRole processStepRole : list) {
					if (!Objects.equals(uuid, processStepRole.getUuid())) {
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

			query.append(_SQL_SELECT_PROCESSSTEPROLE_WHERE);

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
				query.append(ProcessStepRoleModelImpl.ORDER_BY_JPQL);
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
					list = (List<ProcessStepRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessStepRole>)QueryUtil.list(q,
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
	 * Returns the first process step role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step role
	 * @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	 */
	@Override
	public ProcessStepRole findByUuid_First(String uuid,
		OrderByComparator<ProcessStepRole> orderByComparator)
		throws NoSuchProcessStepRoleException {
		ProcessStepRole processStepRole = fetchByUuid_First(uuid,
				orderByComparator);

		if (processStepRole != null) {
			return processStepRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchProcessStepRoleException(msg.toString());
	}

	/**
	 * Returns the first process step role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step role, or <code>null</code> if a matching process step role could not be found
	 */
	@Override
	public ProcessStepRole fetchByUuid_First(String uuid,
		OrderByComparator<ProcessStepRole> orderByComparator) {
		List<ProcessStepRole> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process step role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step role
	 * @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	 */
	@Override
	public ProcessStepRole findByUuid_Last(String uuid,
		OrderByComparator<ProcessStepRole> orderByComparator)
		throws NoSuchProcessStepRoleException {
		ProcessStepRole processStepRole = fetchByUuid_Last(uuid,
				orderByComparator);

		if (processStepRole != null) {
			return processStepRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchProcessStepRoleException(msg.toString());
	}

	/**
	 * Returns the last process step role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step role, or <code>null</code> if a matching process step role could not be found
	 */
	@Override
	public ProcessStepRole fetchByUuid_Last(String uuid,
		OrderByComparator<ProcessStepRole> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ProcessStepRole> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process step roles before and after the current process step role in the ordered set where uuid = &#63;.
	 *
	 * @param processStepRolePK the primary key of the current process step role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process step role
	 * @throws NoSuchProcessStepRoleException if a process step role with the primary key could not be found
	 */
	@Override
	public ProcessStepRole[] findByUuid_PrevAndNext(
		ProcessStepRolePK processStepRolePK, String uuid,
		OrderByComparator<ProcessStepRole> orderByComparator)
		throws NoSuchProcessStepRoleException {
		ProcessStepRole processStepRole = findByPrimaryKey(processStepRolePK);

		Session session = null;

		try {
			session = openSession();

			ProcessStepRole[] array = new ProcessStepRoleImpl[3];

			array[0] = getByUuid_PrevAndNext(session, processStepRole, uuid,
					orderByComparator, true);

			array[1] = processStepRole;

			array[2] = getByUuid_PrevAndNext(session, processStepRole, uuid,
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

	protected ProcessStepRole getByUuid_PrevAndNext(Session session,
		ProcessStepRole processStepRole, String uuid,
		OrderByComparator<ProcessStepRole> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PROCESSSTEPROLE_WHERE);

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
			query.append(ProcessStepRoleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(processStepRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessStepRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process step roles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ProcessStepRole processStepRole : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processStepRole);
		}
	}

	/**
	 * Returns the number of process step roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching process step roles
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSSTEPROLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "processStepRole.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "processStepRole.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(processStepRole.uuid IS NULL OR processStepRole.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S_ID = new FinderPath(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleModelImpl.FINDER_CACHE_ENABLED,
			ProcessStepRoleImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByP_S_ID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID =
		new FinderPath(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleModelImpl.FINDER_CACHE_ENABLED,
			ProcessStepRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_S_ID",
			new String[] { Long.class.getName() },
			ProcessStepRoleModelImpl.PROCESSSTEPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_S_ID = new FinderPath(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_S_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the process step roles where processStepId = &#63;.
	 *
	 * @param processStepId the process step ID
	 * @return the matching process step roles
	 */
	@Override
	public List<ProcessStepRole> findByP_S_ID(long processStepId) {
		return findByP_S_ID(processStepId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process step roles where processStepId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param processStepId the process step ID
	 * @param start the lower bound of the range of process step roles
	 * @param end the upper bound of the range of process step roles (not inclusive)
	 * @return the range of matching process step roles
	 */
	@Override
	public List<ProcessStepRole> findByP_S_ID(long processStepId, int start,
		int end) {
		return findByP_S_ID(processStepId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process step roles where processStepId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param processStepId the process step ID
	 * @param start the lower bound of the range of process step roles
	 * @param end the upper bound of the range of process step roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process step roles
	 */
	@Override
	public List<ProcessStepRole> findByP_S_ID(long processStepId, int start,
		int end, OrderByComparator<ProcessStepRole> orderByComparator) {
		return findByP_S_ID(processStepId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process step roles where processStepId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param processStepId the process step ID
	 * @param start the lower bound of the range of process step roles
	 * @param end the upper bound of the range of process step roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process step roles
	 */
	@Override
	public List<ProcessStepRole> findByP_S_ID(long processStepId, int start,
		int end, OrderByComparator<ProcessStepRole> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID;
			finderArgs = new Object[] { processStepId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S_ID;
			finderArgs = new Object[] {
					processStepId,
					
					start, end, orderByComparator
				};
		}

		List<ProcessStepRole> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessStepRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessStepRole processStepRole : list) {
					if ((processStepId != processStepRole.getProcessStepId())) {
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

			query.append(_SQL_SELECT_PROCESSSTEPROLE_WHERE);

			query.append(_FINDER_COLUMN_P_S_ID_PROCESSSTEPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessStepRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(processStepId);

				if (!pagination) {
					list = (List<ProcessStepRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessStepRole>)QueryUtil.list(q,
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
	 * Returns the first process step role in the ordered set where processStepId = &#63;.
	 *
	 * @param processStepId the process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step role
	 * @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	 */
	@Override
	public ProcessStepRole findByP_S_ID_First(long processStepId,
		OrderByComparator<ProcessStepRole> orderByComparator)
		throws NoSuchProcessStepRoleException {
		ProcessStepRole processStepRole = fetchByP_S_ID_First(processStepId,
				orderByComparator);

		if (processStepRole != null) {
			return processStepRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("processStepId=");
		msg.append(processStepId);

		msg.append("}");

		throw new NoSuchProcessStepRoleException(msg.toString());
	}

	/**
	 * Returns the first process step role in the ordered set where processStepId = &#63;.
	 *
	 * @param processStepId the process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process step role, or <code>null</code> if a matching process step role could not be found
	 */
	@Override
	public ProcessStepRole fetchByP_S_ID_First(long processStepId,
		OrderByComparator<ProcessStepRole> orderByComparator) {
		List<ProcessStepRole> list = findByP_S_ID(processStepId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process step role in the ordered set where processStepId = &#63;.
	 *
	 * @param processStepId the process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step role
	 * @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	 */
	@Override
	public ProcessStepRole findByP_S_ID_Last(long processStepId,
		OrderByComparator<ProcessStepRole> orderByComparator)
		throws NoSuchProcessStepRoleException {
		ProcessStepRole processStepRole = fetchByP_S_ID_Last(processStepId,
				orderByComparator);

		if (processStepRole != null) {
			return processStepRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("processStepId=");
		msg.append(processStepId);

		msg.append("}");

		throw new NoSuchProcessStepRoleException(msg.toString());
	}

	/**
	 * Returns the last process step role in the ordered set where processStepId = &#63;.
	 *
	 * @param processStepId the process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process step role, or <code>null</code> if a matching process step role could not be found
	 */
	@Override
	public ProcessStepRole fetchByP_S_ID_Last(long processStepId,
		OrderByComparator<ProcessStepRole> orderByComparator) {
		int count = countByP_S_ID(processStepId);

		if (count == 0) {
			return null;
		}

		List<ProcessStepRole> list = findByP_S_ID(processStepId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process step roles before and after the current process step role in the ordered set where processStepId = &#63;.
	 *
	 * @param processStepRolePK the primary key of the current process step role
	 * @param processStepId the process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process step role
	 * @throws NoSuchProcessStepRoleException if a process step role with the primary key could not be found
	 */
	@Override
	public ProcessStepRole[] findByP_S_ID_PrevAndNext(
		ProcessStepRolePK processStepRolePK, long processStepId,
		OrderByComparator<ProcessStepRole> orderByComparator)
		throws NoSuchProcessStepRoleException {
		ProcessStepRole processStepRole = findByPrimaryKey(processStepRolePK);

		Session session = null;

		try {
			session = openSession();

			ProcessStepRole[] array = new ProcessStepRoleImpl[3];

			array[0] = getByP_S_ID_PrevAndNext(session, processStepRole,
					processStepId, orderByComparator, true);

			array[1] = processStepRole;

			array[2] = getByP_S_ID_PrevAndNext(session, processStepRole,
					processStepId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessStepRole getByP_S_ID_PrevAndNext(Session session,
		ProcessStepRole processStepRole, long processStepId,
		OrderByComparator<ProcessStepRole> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PROCESSSTEPROLE_WHERE);

		query.append(_FINDER_COLUMN_P_S_ID_PROCESSSTEPID_2);

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
			query.append(ProcessStepRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(processStepId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processStepRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessStepRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process step roles where processStepId = &#63; from the database.
	 *
	 * @param processStepId the process step ID
	 */
	@Override
	public void removeByP_S_ID(long processStepId) {
		for (ProcessStepRole processStepRole : findByP_S_ID(processStepId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processStepRole);
		}
	}

	/**
	 * Returns the number of process step roles where processStepId = &#63;.
	 *
	 * @param processStepId the process step ID
	 * @return the number of matching process step roles
	 */
	@Override
	public int countByP_S_ID(long processStepId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P_S_ID;

		Object[] finderArgs = new Object[] { processStepId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSSTEPROLE_WHERE);

			query.append(_FINDER_COLUMN_P_S_ID_PROCESSSTEPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(processStepId);

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

	private static final String _FINDER_COLUMN_P_S_ID_PROCESSSTEPID_2 = "processStepRole.id.processStepId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_STEP_ROLE = new FinderPath(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleModelImpl.FINDER_CACHE_ENABLED,
			ProcessStepRoleImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_STEP_ROLE",
			new String[] { Long.class.getName(), Long.class.getName() },
			ProcessStepRoleModelImpl.PROCESSSTEPID_COLUMN_BITMASK |
			ProcessStepRoleModelImpl.ROLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_STEP_ROLE = new FinderPath(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_STEP_ROLE",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the process step role where processStepId = &#63; and roleId = &#63; or throws a {@link NoSuchProcessStepRoleException} if it could not be found.
	 *
	 * @param processStepId the process step ID
	 * @param roleId the role ID
	 * @return the matching process step role
	 * @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	 */
	@Override
	public ProcessStepRole findByF_STEP_ROLE(long processStepId, long roleId)
		throws NoSuchProcessStepRoleException {
		ProcessStepRole processStepRole = fetchByF_STEP_ROLE(processStepId,
				roleId);

		if (processStepRole == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("processStepId=");
			msg.append(processStepId);

			msg.append(", roleId=");
			msg.append(roleId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchProcessStepRoleException(msg.toString());
		}

		return processStepRole;
	}

	/**
	 * Returns the process step role where processStepId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param processStepId the process step ID
	 * @param roleId the role ID
	 * @return the matching process step role, or <code>null</code> if a matching process step role could not be found
	 */
	@Override
	public ProcessStepRole fetchByF_STEP_ROLE(long processStepId, long roleId) {
		return fetchByF_STEP_ROLE(processStepId, roleId, true);
	}

	/**
	 * Returns the process step role where processStepId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param processStepId the process step ID
	 * @param roleId the role ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process step role, or <code>null</code> if a matching process step role could not be found
	 */
	@Override
	public ProcessStepRole fetchByF_STEP_ROLE(long processStepId, long roleId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { processStepId, roleId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_STEP_ROLE,
					finderArgs, this);
		}

		if (result instanceof ProcessStepRole) {
			ProcessStepRole processStepRole = (ProcessStepRole)result;

			if ((processStepId != processStepRole.getProcessStepId()) ||
					(roleId != processStepRole.getRoleId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PROCESSSTEPROLE_WHERE);

			query.append(_FINDER_COLUMN_F_STEP_ROLE_PROCESSSTEPID_2);

			query.append(_FINDER_COLUMN_F_STEP_ROLE_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(processStepId);

				qPos.add(roleId);

				List<ProcessStepRole> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_STEP_ROLE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ProcessStepRolePersistenceImpl.fetchByF_STEP_ROLE(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProcessStepRole processStepRole = list.get(0);

					result = processStepRole;

					cacheResult(processStepRole);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_STEP_ROLE,
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
			return (ProcessStepRole)result;
		}
	}

	/**
	 * Removes the process step role where processStepId = &#63; and roleId = &#63; from the database.
	 *
	 * @param processStepId the process step ID
	 * @param roleId the role ID
	 * @return the process step role that was removed
	 */
	@Override
	public ProcessStepRole removeByF_STEP_ROLE(long processStepId, long roleId)
		throws NoSuchProcessStepRoleException {
		ProcessStepRole processStepRole = findByF_STEP_ROLE(processStepId,
				roleId);

		return remove(processStepRole);
	}

	/**
	 * Returns the number of process step roles where processStepId = &#63; and roleId = &#63;.
	 *
	 * @param processStepId the process step ID
	 * @param roleId the role ID
	 * @return the number of matching process step roles
	 */
	@Override
	public int countByF_STEP_ROLE(long processStepId, long roleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_STEP_ROLE;

		Object[] finderArgs = new Object[] { processStepId, roleId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSSTEPROLE_WHERE);

			query.append(_FINDER_COLUMN_F_STEP_ROLE_PROCESSSTEPID_2);

			query.append(_FINDER_COLUMN_F_STEP_ROLE_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(processStepId);

				qPos.add(roleId);

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

	private static final String _FINDER_COLUMN_F_STEP_ROLE_PROCESSSTEPID_2 = "processStepRole.id.processStepId = ? AND ";
	private static final String _FINDER_COLUMN_F_STEP_ROLE_ROLEID_2 = "processStepRole.id.roleId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_CODE = new FinderPath(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleModelImpl.FINDER_CACHE_ENABLED,
			ProcessStepRoleImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_CODE", new String[] { String.class.getName() },
			ProcessStepRoleModelImpl.ROLECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_CODE = new FinderPath(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_CODE",
			new String[] { String.class.getName() });

	/**
	 * Returns the process step role where roleCode = &#63; or throws a {@link NoSuchProcessStepRoleException} if it could not be found.
	 *
	 * @param roleCode the role code
	 * @return the matching process step role
	 * @throws NoSuchProcessStepRoleException if a matching process step role could not be found
	 */
	@Override
	public ProcessStepRole findByF_CODE(String roleCode)
		throws NoSuchProcessStepRoleException {
		ProcessStepRole processStepRole = fetchByF_CODE(roleCode);

		if (processStepRole == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("roleCode=");
			msg.append(roleCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchProcessStepRoleException(msg.toString());
		}

		return processStepRole;
	}

	/**
	 * Returns the process step role where roleCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param roleCode the role code
	 * @return the matching process step role, or <code>null</code> if a matching process step role could not be found
	 */
	@Override
	public ProcessStepRole fetchByF_CODE(String roleCode) {
		return fetchByF_CODE(roleCode, true);
	}

	/**
	 * Returns the process step role where roleCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param roleCode the role code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process step role, or <code>null</code> if a matching process step role could not be found
	 */
	@Override
	public ProcessStepRole fetchByF_CODE(String roleCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { roleCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_CODE,
					finderArgs, this);
		}

		if (result instanceof ProcessStepRole) {
			ProcessStepRole processStepRole = (ProcessStepRole)result;

			if (!Objects.equals(roleCode, processStepRole.getRoleCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PROCESSSTEPROLE_WHERE);

			boolean bindRoleCode = false;

			if (roleCode == null) {
				query.append(_FINDER_COLUMN_F_CODE_ROLECODE_1);
			}
			else if (roleCode.equals("")) {
				query.append(_FINDER_COLUMN_F_CODE_ROLECODE_3);
			}
			else {
				bindRoleCode = true;

				query.append(_FINDER_COLUMN_F_CODE_ROLECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRoleCode) {
					qPos.add(roleCode);
				}

				List<ProcessStepRole> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ProcessStepRolePersistenceImpl.fetchByF_CODE(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProcessStepRole processStepRole = list.get(0);

					result = processStepRole;

					cacheResult(processStepRole);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CODE, finderArgs);

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
			return (ProcessStepRole)result;
		}
	}

	/**
	 * Removes the process step role where roleCode = &#63; from the database.
	 *
	 * @param roleCode the role code
	 * @return the process step role that was removed
	 */
	@Override
	public ProcessStepRole removeByF_CODE(String roleCode)
		throws NoSuchProcessStepRoleException {
		ProcessStepRole processStepRole = findByF_CODE(roleCode);

		return remove(processStepRole);
	}

	/**
	 * Returns the number of process step roles where roleCode = &#63;.
	 *
	 * @param roleCode the role code
	 * @return the number of matching process step roles
	 */
	@Override
	public int countByF_CODE(String roleCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_CODE;

		Object[] finderArgs = new Object[] { roleCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSSTEPROLE_WHERE);

			boolean bindRoleCode = false;

			if (roleCode == null) {
				query.append(_FINDER_COLUMN_F_CODE_ROLECODE_1);
			}
			else if (roleCode.equals("")) {
				query.append(_FINDER_COLUMN_F_CODE_ROLECODE_3);
			}
			else {
				bindRoleCode = true;

				query.append(_FINDER_COLUMN_F_CODE_ROLECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRoleCode) {
					qPos.add(roleCode);
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

	private static final String _FINDER_COLUMN_F_CODE_ROLECODE_1 = "processStepRole.roleCode IS NULL";
	private static final String _FINDER_COLUMN_F_CODE_ROLECODE_2 = "processStepRole.roleCode = ?";
	private static final String _FINDER_COLUMN_F_CODE_ROLECODE_3 = "(processStepRole.roleCode IS NULL OR processStepRole.roleCode = '')";

	public ProcessStepRolePersistenceImpl() {
		setModelClass(ProcessStepRole.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("condition", "condition_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the process step role in the entity cache if it is enabled.
	 *
	 * @param processStepRole the process step role
	 */
	@Override
	public void cacheResult(ProcessStepRole processStepRole) {
		entityCache.putResult(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleImpl.class, processStepRole.getPrimaryKey(),
			processStepRole);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_STEP_ROLE,
			new Object[] {
				processStepRole.getProcessStepId(), processStepRole.getRoleId()
			}, processStepRole);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE,
			new Object[] { processStepRole.getRoleCode() }, processStepRole);

		processStepRole.resetOriginalValues();
	}

	/**
	 * Caches the process step roles in the entity cache if it is enabled.
	 *
	 * @param processStepRoles the process step roles
	 */
	@Override
	public void cacheResult(List<ProcessStepRole> processStepRoles) {
		for (ProcessStepRole processStepRole : processStepRoles) {
			if (entityCache.getResult(
						ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
						ProcessStepRoleImpl.class,
						processStepRole.getPrimaryKey()) == null) {
				cacheResult(processStepRole);
			}
			else {
				processStepRole.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all process step roles.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProcessStepRoleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the process step role.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProcessStepRole processStepRole) {
		entityCache.removeResult(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleImpl.class, processStepRole.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ProcessStepRoleModelImpl)processStepRole, true);
	}

	@Override
	public void clearCache(List<ProcessStepRole> processStepRoles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProcessStepRole processStepRole : processStepRoles) {
			entityCache.removeResult(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
				ProcessStepRoleImpl.class, processStepRole.getPrimaryKey());

			clearUniqueFindersCache((ProcessStepRoleModelImpl)processStepRole,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ProcessStepRoleModelImpl processStepRoleModelImpl) {
		Object[] args = new Object[] {
				processStepRoleModelImpl.getProcessStepId(),
				processStepRoleModelImpl.getRoleId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_STEP_ROLE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_STEP_ROLE, args,
			processStepRoleModelImpl, false);

		args = new Object[] { processStepRoleModelImpl.getRoleCode() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_CODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE, args,
			processStepRoleModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ProcessStepRoleModelImpl processStepRoleModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					processStepRoleModelImpl.getProcessStepId(),
					processStepRoleModelImpl.getRoleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_STEP_ROLE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_STEP_ROLE, args);
		}

		if ((processStepRoleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_STEP_ROLE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processStepRoleModelImpl.getOriginalProcessStepId(),
					processStepRoleModelImpl.getOriginalRoleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_STEP_ROLE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_STEP_ROLE, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { processStepRoleModelImpl.getRoleCode() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CODE, args);
		}

		if ((processStepRoleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_CODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processStepRoleModelImpl.getOriginalRoleCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CODE, args);
		}
	}

	/**
	 * Creates a new process step role with the primary key. Does not add the process step role to the database.
	 *
	 * @param processStepRolePK the primary key for the new process step role
	 * @return the new process step role
	 */
	@Override
	public ProcessStepRole create(ProcessStepRolePK processStepRolePK) {
		ProcessStepRole processStepRole = new ProcessStepRoleImpl();

		processStepRole.setNew(true);
		processStepRole.setPrimaryKey(processStepRolePK);

		String uuid = PortalUUIDUtil.generate();

		processStepRole.setUuid(uuid);

		return processStepRole;
	}

	/**
	 * Removes the process step role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param processStepRolePK the primary key of the process step role
	 * @return the process step role that was removed
	 * @throws NoSuchProcessStepRoleException if a process step role with the primary key could not be found
	 */
	@Override
	public ProcessStepRole remove(ProcessStepRolePK processStepRolePK)
		throws NoSuchProcessStepRoleException {
		return remove((Serializable)processStepRolePK);
	}

	/**
	 * Removes the process step role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the process step role
	 * @return the process step role that was removed
	 * @throws NoSuchProcessStepRoleException if a process step role with the primary key could not be found
	 */
	@Override
	public ProcessStepRole remove(Serializable primaryKey)
		throws NoSuchProcessStepRoleException {
		Session session = null;

		try {
			session = openSession();

			ProcessStepRole processStepRole = (ProcessStepRole)session.get(ProcessStepRoleImpl.class,
					primaryKey);

			if (processStepRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProcessStepRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(processStepRole);
		}
		catch (NoSuchProcessStepRoleException nsee) {
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
	protected ProcessStepRole removeImpl(ProcessStepRole processStepRole) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(processStepRole)) {
				processStepRole = (ProcessStepRole)session.get(ProcessStepRoleImpl.class,
						processStepRole.getPrimaryKeyObj());
			}

			if (processStepRole != null) {
				session.delete(processStepRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (processStepRole != null) {
			clearCache(processStepRole);
		}

		return processStepRole;
	}

	@Override
	public ProcessStepRole updateImpl(ProcessStepRole processStepRole) {
		boolean isNew = processStepRole.isNew();

		if (!(processStepRole instanceof ProcessStepRoleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(processStepRole.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(processStepRole);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in processStepRole proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProcessStepRole implementation " +
				processStepRole.getClass());
		}

		ProcessStepRoleModelImpl processStepRoleModelImpl = (ProcessStepRoleModelImpl)processStepRole;

		if (Validator.isNull(processStepRole.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			processStepRole.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (processStepRole.isNew()) {
				session.save(processStepRole);

				processStepRole.setNew(false);
			}
			else {
				processStepRole = (ProcessStepRole)session.merge(processStepRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ProcessStepRoleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { processStepRoleModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { processStepRoleModelImpl.getProcessStepId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_P_S_ID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((processStepRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processStepRoleModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { processStepRoleModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((processStepRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processStepRoleModelImpl.getOriginalProcessStepId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_P_S_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID,
					args);

				args = new Object[] { processStepRoleModelImpl.getProcessStepId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_P_S_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID,
					args);
			}
		}

		entityCache.putResult(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
			ProcessStepRoleImpl.class, processStepRole.getPrimaryKey(),
			processStepRole, false);

		clearUniqueFindersCache(processStepRoleModelImpl, false);
		cacheUniqueFindersCache(processStepRoleModelImpl);

		processStepRole.resetOriginalValues();

		return processStepRole;
	}

	/**
	 * Returns the process step role with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the process step role
	 * @return the process step role
	 * @throws NoSuchProcessStepRoleException if a process step role with the primary key could not be found
	 */
	@Override
	public ProcessStepRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProcessStepRoleException {
		ProcessStepRole processStepRole = fetchByPrimaryKey(primaryKey);

		if (processStepRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProcessStepRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return processStepRole;
	}

	/**
	 * Returns the process step role with the primary key or throws a {@link NoSuchProcessStepRoleException} if it could not be found.
	 *
	 * @param processStepRolePK the primary key of the process step role
	 * @return the process step role
	 * @throws NoSuchProcessStepRoleException if a process step role with the primary key could not be found
	 */
	@Override
	public ProcessStepRole findByPrimaryKey(ProcessStepRolePK processStepRolePK)
		throws NoSuchProcessStepRoleException {
		return findByPrimaryKey((Serializable)processStepRolePK);
	}

	/**
	 * Returns the process step role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the process step role
	 * @return the process step role, or <code>null</code> if a process step role with the primary key could not be found
	 */
	@Override
	public ProcessStepRole fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
				ProcessStepRoleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProcessStepRole processStepRole = (ProcessStepRole)serializable;

		if (processStepRole == null) {
			Session session = null;

			try {
				session = openSession();

				processStepRole = (ProcessStepRole)session.get(ProcessStepRoleImpl.class,
						primaryKey);

				if (processStepRole != null) {
					cacheResult(processStepRole);
				}
				else {
					entityCache.putResult(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
						ProcessStepRoleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProcessStepRoleModelImpl.ENTITY_CACHE_ENABLED,
					ProcessStepRoleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return processStepRole;
	}

	/**
	 * Returns the process step role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param processStepRolePK the primary key of the process step role
	 * @return the process step role, or <code>null</code> if a process step role with the primary key could not be found
	 */
	@Override
	public ProcessStepRole fetchByPrimaryKey(
		ProcessStepRolePK processStepRolePK) {
		return fetchByPrimaryKey((Serializable)processStepRolePK);
	}

	@Override
	public Map<Serializable, ProcessStepRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProcessStepRole> map = new HashMap<Serializable, ProcessStepRole>();

		for (Serializable primaryKey : primaryKeys) {
			ProcessStepRole processStepRole = fetchByPrimaryKey(primaryKey);

			if (processStepRole != null) {
				map.put(primaryKey, processStepRole);
			}
		}

		return map;
	}

	/**
	 * Returns all the process step roles.
	 *
	 * @return the process step roles
	 */
	@Override
	public List<ProcessStepRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process step roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process step roles
	 * @param end the upper bound of the range of process step roles (not inclusive)
	 * @return the range of process step roles
	 */
	@Override
	public List<ProcessStepRole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the process step roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process step roles
	 * @param end the upper bound of the range of process step roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of process step roles
	 */
	@Override
	public List<ProcessStepRole> findAll(int start, int end,
		OrderByComparator<ProcessStepRole> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process step roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process step roles
	 * @param end the upper bound of the range of process step roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of process step roles
	 */
	@Override
	public List<ProcessStepRole> findAll(int start, int end,
		OrderByComparator<ProcessStepRole> orderByComparator,
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

		List<ProcessStepRole> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessStepRole>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROCESSSTEPROLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROCESSSTEPROLE;

				if (pagination) {
					sql = sql.concat(ProcessStepRoleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProcessStepRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessStepRole>)QueryUtil.list(q,
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
	 * Removes all the process step roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProcessStepRole processStepRole : findAll()) {
			remove(processStepRole);
		}
	}

	/**
	 * Returns the number of process step roles.
	 *
	 * @return the number of process step roles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROCESSSTEPROLE);

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
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ProcessStepRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the process step role persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProcessStepRoleImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PROCESSSTEPROLE = "SELECT processStepRole FROM ProcessStepRole processStepRole";
	private static final String _SQL_SELECT_PROCESSSTEPROLE_WHERE = "SELECT processStepRole FROM ProcessStepRole processStepRole WHERE ";
	private static final String _SQL_COUNT_PROCESSSTEPROLE = "SELECT COUNT(processStepRole) FROM ProcessStepRole processStepRole";
	private static final String _SQL_COUNT_PROCESSSTEPROLE_WHERE = "SELECT COUNT(processStepRole) FROM ProcessStepRole processStepRole WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "processStepRole.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProcessStepRole exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProcessStepRole exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ProcessStepRolePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "condition"
			});
	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(new String[] {
				"processStepId", "roleId"
			});
}