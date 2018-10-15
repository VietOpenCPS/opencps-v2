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

import org.opencps.dossiermgt.exception.NoSuchProcessOptionException;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.impl.ProcessOptionImpl;
import org.opencps.dossiermgt.model.impl.ProcessOptionModelImpl;
import org.opencps.dossiermgt.service.persistence.ProcessOptionPersistence;

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
 * The persistence implementation for the process option service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ProcessOptionPersistence
 * @see org.opencps.dossiermgt.service.persistence.ProcessOptionUtil
 * @generated
 */
@ProviderType
public class ProcessOptionPersistenceImpl extends BasePersistenceImpl<ProcessOption>
	implements ProcessOptionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProcessOptionUtil} to access the process option persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProcessOptionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED,
			ProcessOptionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED,
			ProcessOptionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED,
			ProcessOptionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED,
			ProcessOptionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			ProcessOptionModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the process options where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching process options
	 */
	@Override
	public List<ProcessOption> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process options where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process options
	 * @param end the upper bound of the range of process options (not inclusive)
	 * @return the range of matching process options
	 */
	@Override
	public List<ProcessOption> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process options where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process options
	 * @param end the upper bound of the range of process options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process options
	 */
	@Override
	public List<ProcessOption> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessOption> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process options where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process options
	 * @param end the upper bound of the range of process options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process options
	 */
	@Override
	public List<ProcessOption> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessOption> orderByComparator,
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

		List<ProcessOption> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessOption>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessOption processOption : list) {
					if (!Objects.equals(uuid, processOption.getUuid())) {
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

			query.append(_SQL_SELECT_PROCESSOPTION_WHERE);

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
				query.append(ProcessOptionModelImpl.ORDER_BY_JPQL);
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
					list = (List<ProcessOption>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessOption>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process option in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process option
	 * @throws NoSuchProcessOptionException if a matching process option could not be found
	 */
	@Override
	public ProcessOption findByUuid_First(String uuid,
		OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException {
		ProcessOption processOption = fetchByUuid_First(uuid, orderByComparator);

		if (processOption != null) {
			return processOption;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchProcessOptionException(msg.toString());
	}

	/**
	 * Returns the first process option in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process option, or <code>null</code> if a matching process option could not be found
	 */
	@Override
	public ProcessOption fetchByUuid_First(String uuid,
		OrderByComparator<ProcessOption> orderByComparator) {
		List<ProcessOption> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process option in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process option
	 * @throws NoSuchProcessOptionException if a matching process option could not be found
	 */
	@Override
	public ProcessOption findByUuid_Last(String uuid,
		OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException {
		ProcessOption processOption = fetchByUuid_Last(uuid, orderByComparator);

		if (processOption != null) {
			return processOption;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchProcessOptionException(msg.toString());
	}

	/**
	 * Returns the last process option in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process option, or <code>null</code> if a matching process option could not be found
	 */
	@Override
	public ProcessOption fetchByUuid_Last(String uuid,
		OrderByComparator<ProcessOption> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ProcessOption> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process options before and after the current process option in the ordered set where uuid = &#63;.
	 *
	 * @param processOptionId the primary key of the current process option
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process option
	 * @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	 */
	@Override
	public ProcessOption[] findByUuid_PrevAndNext(long processOptionId,
		String uuid, OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException {
		ProcessOption processOption = findByPrimaryKey(processOptionId);

		Session session = null;

		try {
			session = openSession();

			ProcessOption[] array = new ProcessOptionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, processOption, uuid,
					orderByComparator, true);

			array[1] = processOption;

			array[2] = getByUuid_PrevAndNext(session, processOption, uuid,
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

	protected ProcessOption getByUuid_PrevAndNext(Session session,
		ProcessOption processOption, String uuid,
		OrderByComparator<ProcessOption> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PROCESSOPTION_WHERE);

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
			query.append(ProcessOptionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(processOption);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process options where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ProcessOption processOption : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(processOption);
		}
	}

	/**
	 * Returns the number of process options where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching process options
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSOPTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "processOption.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "processOption.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(processOption.uuid IS NULL OR processOption.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED,
			ProcessOptionImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ProcessOptionModelImpl.UUID_COLUMN_BITMASK |
			ProcessOptionModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the process option where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessOptionException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching process option
	 * @throws NoSuchProcessOptionException if a matching process option could not be found
	 */
	@Override
	public ProcessOption findByUUID_G(String uuid, long groupId)
		throws NoSuchProcessOptionException {
		ProcessOption processOption = fetchByUUID_G(uuid, groupId);

		if (processOption == null) {
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

			throw new NoSuchProcessOptionException(msg.toString());
		}

		return processOption;
	}

	/**
	 * Returns the process option where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching process option, or <code>null</code> if a matching process option could not be found
	 */
	@Override
	public ProcessOption fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the process option where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process option, or <code>null</code> if a matching process option could not be found
	 */
	@Override
	public ProcessOption fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ProcessOption) {
			ProcessOption processOption = (ProcessOption)result;

			if (!Objects.equals(uuid, processOption.getUuid()) ||
					(groupId != processOption.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PROCESSOPTION_WHERE);

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

				List<ProcessOption> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ProcessOption processOption = list.get(0);

					result = processOption;

					cacheResult(processOption);
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
			return (ProcessOption)result;
		}
	}

	/**
	 * Removes the process option where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the process option that was removed
	 */
	@Override
	public ProcessOption removeByUUID_G(String uuid, long groupId)
		throws NoSuchProcessOptionException {
		ProcessOption processOption = findByUUID_G(uuid, groupId);

		return remove(processOption);
	}

	/**
	 * Returns the number of process options where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching process options
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSOPTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "processOption.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "processOption.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(processOption.uuid IS NULL OR processOption.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "processOption.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED,
			ProcessOptionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED,
			ProcessOptionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ProcessOptionModelImpl.UUID_COLUMN_BITMASK |
			ProcessOptionModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the process options where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching process options
	 */
	@Override
	public List<ProcessOption> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process options where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process options
	 * @param end the upper bound of the range of process options (not inclusive)
	 * @return the range of matching process options
	 */
	@Override
	public List<ProcessOption> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process options where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process options
	 * @param end the upper bound of the range of process options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process options
	 */
	@Override
	public List<ProcessOption> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessOption> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process options where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process options
	 * @param end the upper bound of the range of process options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process options
	 */
	@Override
	public List<ProcessOption> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessOption> orderByComparator,
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

		List<ProcessOption> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessOption>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessOption processOption : list) {
					if (!Objects.equals(uuid, processOption.getUuid()) ||
							(companyId != processOption.getCompanyId())) {
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

			query.append(_SQL_SELECT_PROCESSOPTION_WHERE);

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
				query.append(ProcessOptionModelImpl.ORDER_BY_JPQL);
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
					list = (List<ProcessOption>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessOption>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process option
	 * @throws NoSuchProcessOptionException if a matching process option could not be found
	 */
	@Override
	public ProcessOption findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException {
		ProcessOption processOption = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (processOption != null) {
			return processOption;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchProcessOptionException(msg.toString());
	}

	/**
	 * Returns the first process option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process option, or <code>null</code> if a matching process option could not be found
	 */
	@Override
	public ProcessOption fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessOption> orderByComparator) {
		List<ProcessOption> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process option
	 * @throws NoSuchProcessOptionException if a matching process option could not be found
	 */
	@Override
	public ProcessOption findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException {
		ProcessOption processOption = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (processOption != null) {
			return processOption;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchProcessOptionException(msg.toString());
	}

	/**
	 * Returns the last process option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process option, or <code>null</code> if a matching process option could not be found
	 */
	@Override
	public ProcessOption fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessOption> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ProcessOption> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process options before and after the current process option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param processOptionId the primary key of the current process option
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process option
	 * @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	 */
	@Override
	public ProcessOption[] findByUuid_C_PrevAndNext(long processOptionId,
		String uuid, long companyId,
		OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException {
		ProcessOption processOption = findByPrimaryKey(processOptionId);

		Session session = null;

		try {
			session = openSession();

			ProcessOption[] array = new ProcessOptionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, processOption, uuid,
					companyId, orderByComparator, true);

			array[1] = processOption;

			array[2] = getByUuid_C_PrevAndNext(session, processOption, uuid,
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

	protected ProcessOption getByUuid_C_PrevAndNext(Session session,
		ProcessOption processOption, String uuid, long companyId,
		OrderByComparator<ProcessOption> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PROCESSOPTION_WHERE);

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
			query.append(ProcessOptionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(processOption);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process options where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ProcessOption processOption : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processOption);
		}
	}

	/**
	 * Returns the number of process options where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching process options
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSOPTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "processOption.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "processOption.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(processOption.uuid IS NULL OR processOption.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "processOption.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SC_ID = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED,
			ProcessOptionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySC_ID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_ID = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED,
			ProcessOptionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySC_ID", new String[] { Long.class.getName() },
			ProcessOptionModelImpl.SERVICECONFIGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SC_ID = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySC_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the process options where serviceConfigId = &#63;.
	 *
	 * @param serviceConfigId the service config ID
	 * @return the matching process options
	 */
	@Override
	public List<ProcessOption> findBySC_ID(long serviceConfigId) {
		return findBySC_ID(serviceConfigId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process options where serviceConfigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceConfigId the service config ID
	 * @param start the lower bound of the range of process options
	 * @param end the upper bound of the range of process options (not inclusive)
	 * @return the range of matching process options
	 */
	@Override
	public List<ProcessOption> findBySC_ID(long serviceConfigId, int start,
		int end) {
		return findBySC_ID(serviceConfigId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process options where serviceConfigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceConfigId the service config ID
	 * @param start the lower bound of the range of process options
	 * @param end the upper bound of the range of process options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process options
	 */
	@Override
	public List<ProcessOption> findBySC_ID(long serviceConfigId, int start,
		int end, OrderByComparator<ProcessOption> orderByComparator) {
		return findBySC_ID(serviceConfigId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process options where serviceConfigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceConfigId the service config ID
	 * @param start the lower bound of the range of process options
	 * @param end the upper bound of the range of process options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process options
	 */
	@Override
	public List<ProcessOption> findBySC_ID(long serviceConfigId, int start,
		int end, OrderByComparator<ProcessOption> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_ID;
			finderArgs = new Object[] { serviceConfigId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SC_ID;
			finderArgs = new Object[] {
					serviceConfigId,
					
					start, end, orderByComparator
				};
		}

		List<ProcessOption> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessOption>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessOption processOption : list) {
					if ((serviceConfigId != processOption.getServiceConfigId())) {
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

			query.append(_SQL_SELECT_PROCESSOPTION_WHERE);

			query.append(_FINDER_COLUMN_SC_ID_SERVICECONFIGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessOptionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceConfigId);

				if (!pagination) {
					list = (List<ProcessOption>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessOption>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process option in the ordered set where serviceConfigId = &#63;.
	 *
	 * @param serviceConfigId the service config ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process option
	 * @throws NoSuchProcessOptionException if a matching process option could not be found
	 */
	@Override
	public ProcessOption findBySC_ID_First(long serviceConfigId,
		OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException {
		ProcessOption processOption = fetchBySC_ID_First(serviceConfigId,
				orderByComparator);

		if (processOption != null) {
			return processOption;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("serviceConfigId=");
		msg.append(serviceConfigId);

		msg.append("}");

		throw new NoSuchProcessOptionException(msg.toString());
	}

	/**
	 * Returns the first process option in the ordered set where serviceConfigId = &#63;.
	 *
	 * @param serviceConfigId the service config ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process option, or <code>null</code> if a matching process option could not be found
	 */
	@Override
	public ProcessOption fetchBySC_ID_First(long serviceConfigId,
		OrderByComparator<ProcessOption> orderByComparator) {
		List<ProcessOption> list = findBySC_ID(serviceConfigId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process option in the ordered set where serviceConfigId = &#63;.
	 *
	 * @param serviceConfigId the service config ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process option
	 * @throws NoSuchProcessOptionException if a matching process option could not be found
	 */
	@Override
	public ProcessOption findBySC_ID_Last(long serviceConfigId,
		OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException {
		ProcessOption processOption = fetchBySC_ID_Last(serviceConfigId,
				orderByComparator);

		if (processOption != null) {
			return processOption;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("serviceConfigId=");
		msg.append(serviceConfigId);

		msg.append("}");

		throw new NoSuchProcessOptionException(msg.toString());
	}

	/**
	 * Returns the last process option in the ordered set where serviceConfigId = &#63;.
	 *
	 * @param serviceConfigId the service config ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process option, or <code>null</code> if a matching process option could not be found
	 */
	@Override
	public ProcessOption fetchBySC_ID_Last(long serviceConfigId,
		OrderByComparator<ProcessOption> orderByComparator) {
		int count = countBySC_ID(serviceConfigId);

		if (count == 0) {
			return null;
		}

		List<ProcessOption> list = findBySC_ID(serviceConfigId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process options before and after the current process option in the ordered set where serviceConfigId = &#63;.
	 *
	 * @param processOptionId the primary key of the current process option
	 * @param serviceConfigId the service config ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process option
	 * @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	 */
	@Override
	public ProcessOption[] findBySC_ID_PrevAndNext(long processOptionId,
		long serviceConfigId, OrderByComparator<ProcessOption> orderByComparator)
		throws NoSuchProcessOptionException {
		ProcessOption processOption = findByPrimaryKey(processOptionId);

		Session session = null;

		try {
			session = openSession();

			ProcessOption[] array = new ProcessOptionImpl[3];

			array[0] = getBySC_ID_PrevAndNext(session, processOption,
					serviceConfigId, orderByComparator, true);

			array[1] = processOption;

			array[2] = getBySC_ID_PrevAndNext(session, processOption,
					serviceConfigId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessOption getBySC_ID_PrevAndNext(Session session,
		ProcessOption processOption, long serviceConfigId,
		OrderByComparator<ProcessOption> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PROCESSOPTION_WHERE);

		query.append(_FINDER_COLUMN_SC_ID_SERVICECONFIGID_2);

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
			query.append(ProcessOptionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(serviceConfigId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processOption);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process options where serviceConfigId = &#63; from the database.
	 *
	 * @param serviceConfigId the service config ID
	 */
	@Override
	public void removeBySC_ID(long serviceConfigId) {
		for (ProcessOption processOption : findBySC_ID(serviceConfigId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processOption);
		}
	}

	/**
	 * Returns the number of process options where serviceConfigId = &#63;.
	 *
	 * @param serviceConfigId the service config ID
	 * @return the number of matching process options
	 */
	@Override
	public int countBySC_ID(long serviceConfigId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SC_ID;

		Object[] finderArgs = new Object[] { serviceConfigId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSOPTION_WHERE);

			query.append(_FINDER_COLUMN_SC_ID_SERVICECONFIGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceConfigId);

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

	private static final String _FINDER_COLUMN_SC_ID_SERVICECONFIGID_2 = "processOption.serviceConfigId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_SC_ID_OP = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED,
			ProcessOptionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySC_ID_OP",
			new String[] { Long.class.getName(), Integer.class.getName() },
			ProcessOptionModelImpl.SERVICECONFIGID_COLUMN_BITMASK |
			ProcessOptionModelImpl.OPTIONORDER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SC_ID_OP = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySC_ID_OP",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns the process option where serviceConfigId = &#63; and optionOrder = &#63; or throws a {@link NoSuchProcessOptionException} if it could not be found.
	 *
	 * @param serviceConfigId the service config ID
	 * @param optionOrder the option order
	 * @return the matching process option
	 * @throws NoSuchProcessOptionException if a matching process option could not be found
	 */
	@Override
	public ProcessOption findBySC_ID_OP(long serviceConfigId, int optionOrder)
		throws NoSuchProcessOptionException {
		ProcessOption processOption = fetchBySC_ID_OP(serviceConfigId,
				optionOrder);

		if (processOption == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("serviceConfigId=");
			msg.append(serviceConfigId);

			msg.append(", optionOrder=");
			msg.append(optionOrder);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchProcessOptionException(msg.toString());
		}

		return processOption;
	}

	/**
	 * Returns the process option where serviceConfigId = &#63; and optionOrder = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param serviceConfigId the service config ID
	 * @param optionOrder the option order
	 * @return the matching process option, or <code>null</code> if a matching process option could not be found
	 */
	@Override
	public ProcessOption fetchBySC_ID_OP(long serviceConfigId, int optionOrder) {
		return fetchBySC_ID_OP(serviceConfigId, optionOrder, true);
	}

	/**
	 * Returns the process option where serviceConfigId = &#63; and optionOrder = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param serviceConfigId the service config ID
	 * @param optionOrder the option order
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process option, or <code>null</code> if a matching process option could not be found
	 */
	@Override
	public ProcessOption fetchBySC_ID_OP(long serviceConfigId, int optionOrder,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { serviceConfigId, optionOrder };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SC_ID_OP,
					finderArgs, this);
		}

		if (result instanceof ProcessOption) {
			ProcessOption processOption = (ProcessOption)result;

			if ((serviceConfigId != processOption.getServiceConfigId()) ||
					(optionOrder != processOption.getOptionOrder())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PROCESSOPTION_WHERE);

			query.append(_FINDER_COLUMN_SC_ID_OP_SERVICECONFIGID_2);

			query.append(_FINDER_COLUMN_SC_ID_OP_OPTIONORDER_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceConfigId);

				qPos.add(optionOrder);

				List<ProcessOption> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SC_ID_OP,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ProcessOptionPersistenceImpl.fetchBySC_ID_OP(long, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProcessOption processOption = list.get(0);

					result = processOption;

					cacheResult(processOption);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SC_ID_OP,
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
			return (ProcessOption)result;
		}
	}

	/**
	 * Removes the process option where serviceConfigId = &#63; and optionOrder = &#63; from the database.
	 *
	 * @param serviceConfigId the service config ID
	 * @param optionOrder the option order
	 * @return the process option that was removed
	 */
	@Override
	public ProcessOption removeBySC_ID_OP(long serviceConfigId, int optionOrder)
		throws NoSuchProcessOptionException {
		ProcessOption processOption = findBySC_ID_OP(serviceConfigId,
				optionOrder);

		return remove(processOption);
	}

	/**
	 * Returns the number of process options where serviceConfigId = &#63; and optionOrder = &#63;.
	 *
	 * @param serviceConfigId the service config ID
	 * @param optionOrder the option order
	 * @return the number of matching process options
	 */
	@Override
	public int countBySC_ID_OP(long serviceConfigId, int optionOrder) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SC_ID_OP;

		Object[] finderArgs = new Object[] { serviceConfigId, optionOrder };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSOPTION_WHERE);

			query.append(_FINDER_COLUMN_SC_ID_OP_SERVICECONFIGID_2);

			query.append(_FINDER_COLUMN_SC_ID_OP_OPTIONORDER_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceConfigId);

				qPos.add(optionOrder);

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

	private static final String _FINDER_COLUMN_SC_ID_OP_SERVICECONFIGID_2 = "processOption.serviceConfigId = ? AND ";
	private static final String _FINDER_COLUMN_SC_ID_OP_OPTIONORDER_2 = "processOption.optionOrder = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_SC_DT = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED,
			ProcessOptionImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchBySC_DT",
			new String[] { Long.class.getName(), Long.class.getName() },
			ProcessOptionModelImpl.SERVICECONFIGID_COLUMN_BITMASK |
			ProcessOptionModelImpl.DOSSIERTEMPLATEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SC_DT = new FinderPath(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySC_DT",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the process option where serviceConfigId = &#63; and dossierTemplateId = &#63; or throws a {@link NoSuchProcessOptionException} if it could not be found.
	 *
	 * @param serviceConfigId the service config ID
	 * @param dossierTemplateId the dossier template ID
	 * @return the matching process option
	 * @throws NoSuchProcessOptionException if a matching process option could not be found
	 */
	@Override
	public ProcessOption findBySC_DT(long serviceConfigId,
		long dossierTemplateId) throws NoSuchProcessOptionException {
		ProcessOption processOption = fetchBySC_DT(serviceConfigId,
				dossierTemplateId);

		if (processOption == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("serviceConfigId=");
			msg.append(serviceConfigId);

			msg.append(", dossierTemplateId=");
			msg.append(dossierTemplateId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchProcessOptionException(msg.toString());
		}

		return processOption;
	}

	/**
	 * Returns the process option where serviceConfigId = &#63; and dossierTemplateId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param serviceConfigId the service config ID
	 * @param dossierTemplateId the dossier template ID
	 * @return the matching process option, or <code>null</code> if a matching process option could not be found
	 */
	@Override
	public ProcessOption fetchBySC_DT(long serviceConfigId,
		long dossierTemplateId) {
		return fetchBySC_DT(serviceConfigId, dossierTemplateId, true);
	}

	/**
	 * Returns the process option where serviceConfigId = &#63; and dossierTemplateId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param serviceConfigId the service config ID
	 * @param dossierTemplateId the dossier template ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process option, or <code>null</code> if a matching process option could not be found
	 */
	@Override
	public ProcessOption fetchBySC_DT(long serviceConfigId,
		long dossierTemplateId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { serviceConfigId, dossierTemplateId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SC_DT,
					finderArgs, this);
		}

		if (result instanceof ProcessOption) {
			ProcessOption processOption = (ProcessOption)result;

			if ((serviceConfigId != processOption.getServiceConfigId()) ||
					(dossierTemplateId != processOption.getDossierTemplateId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PROCESSOPTION_WHERE);

			query.append(_FINDER_COLUMN_SC_DT_SERVICECONFIGID_2);

			query.append(_FINDER_COLUMN_SC_DT_DOSSIERTEMPLATEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceConfigId);

				qPos.add(dossierTemplateId);

				List<ProcessOption> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SC_DT,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ProcessOptionPersistenceImpl.fetchBySC_DT(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProcessOption processOption = list.get(0);

					result = processOption;

					cacheResult(processOption);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SC_DT, finderArgs);

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
			return (ProcessOption)result;
		}
	}

	/**
	 * Removes the process option where serviceConfigId = &#63; and dossierTemplateId = &#63; from the database.
	 *
	 * @param serviceConfigId the service config ID
	 * @param dossierTemplateId the dossier template ID
	 * @return the process option that was removed
	 */
	@Override
	public ProcessOption removeBySC_DT(long serviceConfigId,
		long dossierTemplateId) throws NoSuchProcessOptionException {
		ProcessOption processOption = findBySC_DT(serviceConfigId,
				dossierTemplateId);

		return remove(processOption);
	}

	/**
	 * Returns the number of process options where serviceConfigId = &#63; and dossierTemplateId = &#63;.
	 *
	 * @param serviceConfigId the service config ID
	 * @param dossierTemplateId the dossier template ID
	 * @return the number of matching process options
	 */
	@Override
	public int countBySC_DT(long serviceConfigId, long dossierTemplateId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SC_DT;

		Object[] finderArgs = new Object[] { serviceConfigId, dossierTemplateId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSOPTION_WHERE);

			query.append(_FINDER_COLUMN_SC_DT_SERVICECONFIGID_2);

			query.append(_FINDER_COLUMN_SC_DT_DOSSIERTEMPLATEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceConfigId);

				qPos.add(dossierTemplateId);

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

	private static final String _FINDER_COLUMN_SC_DT_SERVICECONFIGID_2 = "processOption.serviceConfigId = ? AND ";
	private static final String _FINDER_COLUMN_SC_DT_DOSSIERTEMPLATEID_2 = "processOption.dossierTemplateId = ?";

	public ProcessOptionPersistenceImpl() {
		setModelClass(ProcessOption.class);

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
	 * Caches the process option in the entity cache if it is enabled.
	 *
	 * @param processOption the process option
	 */
	@Override
	public void cacheResult(ProcessOption processOption) {
		entityCache.putResult(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionImpl.class, processOption.getPrimaryKey(),
			processOption);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { processOption.getUuid(), processOption.getGroupId() },
			processOption);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SC_ID_OP,
			new Object[] {
				processOption.getServiceConfigId(),
				processOption.getOptionOrder()
			}, processOption);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SC_DT,
			new Object[] {
				processOption.getServiceConfigId(),
				processOption.getDossierTemplateId()
			}, processOption);

		processOption.resetOriginalValues();
	}

	/**
	 * Caches the process options in the entity cache if it is enabled.
	 *
	 * @param processOptions the process options
	 */
	@Override
	public void cacheResult(List<ProcessOption> processOptions) {
		for (ProcessOption processOption : processOptions) {
			if (entityCache.getResult(
						ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
						ProcessOptionImpl.class, processOption.getPrimaryKey()) == null) {
				cacheResult(processOption);
			}
			else {
				processOption.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all process options.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProcessOptionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the process option.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProcessOption processOption) {
		entityCache.removeResult(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionImpl.class, processOption.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ProcessOptionModelImpl)processOption, true);
	}

	@Override
	public void clearCache(List<ProcessOption> processOptions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProcessOption processOption : processOptions) {
			entityCache.removeResult(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
				ProcessOptionImpl.class, processOption.getPrimaryKey());

			clearUniqueFindersCache((ProcessOptionModelImpl)processOption, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ProcessOptionModelImpl processOptionModelImpl) {
		Object[] args = new Object[] {
				processOptionModelImpl.getUuid(),
				processOptionModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			processOptionModelImpl, false);

		args = new Object[] {
				processOptionModelImpl.getServiceConfigId(),
				processOptionModelImpl.getOptionOrder()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_SC_ID_OP, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_SC_ID_OP, args,
			processOptionModelImpl, false);

		args = new Object[] {
				processOptionModelImpl.getServiceConfigId(),
				processOptionModelImpl.getDossierTemplateId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_SC_DT, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_SC_DT, args,
			processOptionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ProcessOptionModelImpl processOptionModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					processOptionModelImpl.getUuid(),
					processOptionModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((processOptionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processOptionModelImpl.getOriginalUuid(),
					processOptionModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					processOptionModelImpl.getServiceConfigId(),
					processOptionModelImpl.getOptionOrder()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_ID_OP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SC_ID_OP, args);
		}

		if ((processOptionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SC_ID_OP.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processOptionModelImpl.getOriginalServiceConfigId(),
					processOptionModelImpl.getOriginalOptionOrder()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_ID_OP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SC_ID_OP, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					processOptionModelImpl.getServiceConfigId(),
					processOptionModelImpl.getDossierTemplateId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_DT, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SC_DT, args);
		}

		if ((processOptionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SC_DT.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processOptionModelImpl.getOriginalServiceConfigId(),
					processOptionModelImpl.getOriginalDossierTemplateId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_DT, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SC_DT, args);
		}
	}

	/**
	 * Creates a new process option with the primary key. Does not add the process option to the database.
	 *
	 * @param processOptionId the primary key for the new process option
	 * @return the new process option
	 */
	@Override
	public ProcessOption create(long processOptionId) {
		ProcessOption processOption = new ProcessOptionImpl();

		processOption.setNew(true);
		processOption.setPrimaryKey(processOptionId);

		String uuid = PortalUUIDUtil.generate();

		processOption.setUuid(uuid);

		processOption.setCompanyId(companyProvider.getCompanyId());

		return processOption;
	}

	/**
	 * Removes the process option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param processOptionId the primary key of the process option
	 * @return the process option that was removed
	 * @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	 */
	@Override
	public ProcessOption remove(long processOptionId)
		throws NoSuchProcessOptionException {
		return remove((Serializable)processOptionId);
	}

	/**
	 * Removes the process option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the process option
	 * @return the process option that was removed
	 * @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	 */
	@Override
	public ProcessOption remove(Serializable primaryKey)
		throws NoSuchProcessOptionException {
		Session session = null;

		try {
			session = openSession();

			ProcessOption processOption = (ProcessOption)session.get(ProcessOptionImpl.class,
					primaryKey);

			if (processOption == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProcessOptionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(processOption);
		}
		catch (NoSuchProcessOptionException nsee) {
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
	protected ProcessOption removeImpl(ProcessOption processOption) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(processOption)) {
				processOption = (ProcessOption)session.get(ProcessOptionImpl.class,
						processOption.getPrimaryKeyObj());
			}

			if (processOption != null) {
				session.delete(processOption);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (processOption != null) {
			clearCache(processOption);
		}

		return processOption;
	}

	@Override
	public ProcessOption updateImpl(ProcessOption processOption) {
		boolean isNew = processOption.isNew();

		if (!(processOption instanceof ProcessOptionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(processOption.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(processOption);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in processOption proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProcessOption implementation " +
				processOption.getClass());
		}

		ProcessOptionModelImpl processOptionModelImpl = (ProcessOptionModelImpl)processOption;

		if (Validator.isNull(processOption.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			processOption.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (processOption.getCreateDate() == null)) {
			if (serviceContext == null) {
				processOption.setCreateDate(now);
			}
			else {
				processOption.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!processOptionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				processOption.setModifiedDate(now);
			}
			else {
				processOption.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (processOption.isNew()) {
				session.save(processOption);

				processOption.setNew(false);
			}
			else {
				processOption = (ProcessOption)session.merge(processOption);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ProcessOptionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { processOptionModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					processOptionModelImpl.getUuid(),
					processOptionModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { processOptionModelImpl.getServiceConfigId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_ID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_ID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((processOptionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processOptionModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { processOptionModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((processOptionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processOptionModelImpl.getOriginalUuid(),
						processOptionModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						processOptionModelImpl.getUuid(),
						processOptionModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((processOptionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processOptionModelImpl.getOriginalServiceConfigId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_ID,
					args);

				args = new Object[] { processOptionModelImpl.getServiceConfigId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_ID,
					args);
			}
		}

		entityCache.putResult(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
			ProcessOptionImpl.class, processOption.getPrimaryKey(),
			processOption, false);

		clearUniqueFindersCache(processOptionModelImpl, false);
		cacheUniqueFindersCache(processOptionModelImpl);

		processOption.resetOriginalValues();

		return processOption;
	}

	/**
	 * Returns the process option with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the process option
	 * @return the process option
	 * @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	 */
	@Override
	public ProcessOption findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProcessOptionException {
		ProcessOption processOption = fetchByPrimaryKey(primaryKey);

		if (processOption == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProcessOptionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return processOption;
	}

	/**
	 * Returns the process option with the primary key or throws a {@link NoSuchProcessOptionException} if it could not be found.
	 *
	 * @param processOptionId the primary key of the process option
	 * @return the process option
	 * @throws NoSuchProcessOptionException if a process option with the primary key could not be found
	 */
	@Override
	public ProcessOption findByPrimaryKey(long processOptionId)
		throws NoSuchProcessOptionException {
		return findByPrimaryKey((Serializable)processOptionId);
	}

	/**
	 * Returns the process option with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the process option
	 * @return the process option, or <code>null</code> if a process option with the primary key could not be found
	 */
	@Override
	public ProcessOption fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
				ProcessOptionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProcessOption processOption = (ProcessOption)serializable;

		if (processOption == null) {
			Session session = null;

			try {
				session = openSession();

				processOption = (ProcessOption)session.get(ProcessOptionImpl.class,
						primaryKey);

				if (processOption != null) {
					cacheResult(processOption);
				}
				else {
					entityCache.putResult(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
						ProcessOptionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
					ProcessOptionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return processOption;
	}

	/**
	 * Returns the process option with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param processOptionId the primary key of the process option
	 * @return the process option, or <code>null</code> if a process option with the primary key could not be found
	 */
	@Override
	public ProcessOption fetchByPrimaryKey(long processOptionId) {
		return fetchByPrimaryKey((Serializable)processOptionId);
	}

	@Override
	public Map<Serializable, ProcessOption> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProcessOption> map = new HashMap<Serializable, ProcessOption>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProcessOption processOption = fetchByPrimaryKey(primaryKey);

			if (processOption != null) {
				map.put(primaryKey, processOption);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
					ProcessOptionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProcessOption)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROCESSOPTION_WHERE_PKS_IN);

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

			for (ProcessOption processOption : (List<ProcessOption>)q.list()) {
				map.put(processOption.getPrimaryKeyObj(), processOption);

				cacheResult(processOption);

				uncachedPrimaryKeys.remove(processOption.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProcessOptionModelImpl.ENTITY_CACHE_ENABLED,
					ProcessOptionImpl.class, primaryKey, nullModel);
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
	 * Returns all the process options.
	 *
	 * @return the process options
	 */
	@Override
	public List<ProcessOption> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process options
	 * @param end the upper bound of the range of process options (not inclusive)
	 * @return the range of process options
	 */
	@Override
	public List<ProcessOption> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the process options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process options
	 * @param end the upper bound of the range of process options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of process options
	 */
	@Override
	public List<ProcessOption> findAll(int start, int end,
		OrderByComparator<ProcessOption> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process options
	 * @param end the upper bound of the range of process options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of process options
	 */
	@Override
	public List<ProcessOption> findAll(int start, int end,
		OrderByComparator<ProcessOption> orderByComparator,
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

		List<ProcessOption> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessOption>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROCESSOPTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROCESSOPTION;

				if (pagination) {
					sql = sql.concat(ProcessOptionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProcessOption>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessOption>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the process options from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProcessOption processOption : findAll()) {
			remove(processOption);
		}
	}

	/**
	 * Returns the number of process options.
	 *
	 * @return the number of process options
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROCESSOPTION);

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
		return ProcessOptionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the process option persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProcessOptionImpl.class.getName());
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
	private static final String _SQL_SELECT_PROCESSOPTION = "SELECT processOption FROM ProcessOption processOption";
	private static final String _SQL_SELECT_PROCESSOPTION_WHERE_PKS_IN = "SELECT processOption FROM ProcessOption processOption WHERE processOptionId IN (";
	private static final String _SQL_SELECT_PROCESSOPTION_WHERE = "SELECT processOption FROM ProcessOption processOption WHERE ";
	private static final String _SQL_COUNT_PROCESSOPTION = "SELECT COUNT(processOption) FROM ProcessOption processOption";
	private static final String _SQL_COUNT_PROCESSOPTION_WHERE = "SELECT COUNT(processOption) FROM ProcessOption processOption WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "processOption.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProcessOption exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProcessOption exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ProcessOptionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}