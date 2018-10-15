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

import org.opencps.dossiermgt.exception.NoSuchProcessSequenceException;
import org.opencps.dossiermgt.model.ProcessSequence;
import org.opencps.dossiermgt.model.impl.ProcessSequenceImpl;
import org.opencps.dossiermgt.model.impl.ProcessSequenceModelImpl;
import org.opencps.dossiermgt.service.persistence.ProcessSequencePersistence;

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
 * The persistence implementation for the process sequence service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ProcessSequencePersistence
 * @see org.opencps.dossiermgt.service.persistence.ProcessSequenceUtil
 * @generated
 */
@ProviderType
public class ProcessSequencePersistenceImpl extends BasePersistenceImpl<ProcessSequence>
	implements ProcessSequencePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProcessSequenceUtil} to access the process sequence persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProcessSequenceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED,
			ProcessSequenceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED,
			ProcessSequenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED,
			ProcessSequenceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED,
			ProcessSequenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ProcessSequenceModelImpl.UUID_COLUMN_BITMASK |
			ProcessSequenceModelImpl.SEQUENCENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the process sequences where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process sequences where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @return the range of matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process sequences where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process sequences where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator,
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

		List<ProcessSequence> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessSequence>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessSequence processSequence : list) {
					if (!Objects.equals(uuid, processSequence.getUuid())) {
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

			query.append(_SQL_SELECT_PROCESSSEQUENCE_WHERE);

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
				query.append(ProcessSequenceModelImpl.ORDER_BY_JPQL);
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
					list = (List<ProcessSequence>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessSequence>)QueryUtil.list(q,
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
	 * Returns the first process sequence in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process sequence
	 * @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence findByUuid_First(String uuid,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = fetchByUuid_First(uuid,
				orderByComparator);

		if (processSequence != null) {
			return processSequence;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchProcessSequenceException(msg.toString());
	}

	/**
	 * Returns the first process sequence in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process sequence, or <code>null</code> if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence fetchByUuid_First(String uuid,
		OrderByComparator<ProcessSequence> orderByComparator) {
		List<ProcessSequence> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process sequence in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process sequence
	 * @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence findByUuid_Last(String uuid,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = fetchByUuid_Last(uuid,
				orderByComparator);

		if (processSequence != null) {
			return processSequence;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchProcessSequenceException(msg.toString());
	}

	/**
	 * Returns the last process sequence in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process sequence, or <code>null</code> if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence fetchByUuid_Last(String uuid,
		OrderByComparator<ProcessSequence> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ProcessSequence> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process sequences before and after the current process sequence in the ordered set where uuid = &#63;.
	 *
	 * @param processSequenceId the primary key of the current process sequence
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process sequence
	 * @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	 */
	@Override
	public ProcessSequence[] findByUuid_PrevAndNext(long processSequenceId,
		String uuid, OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = findByPrimaryKey(processSequenceId);

		Session session = null;

		try {
			session = openSession();

			ProcessSequence[] array = new ProcessSequenceImpl[3];

			array[0] = getByUuid_PrevAndNext(session, processSequence, uuid,
					orderByComparator, true);

			array[1] = processSequence;

			array[2] = getByUuid_PrevAndNext(session, processSequence, uuid,
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

	protected ProcessSequence getByUuid_PrevAndNext(Session session,
		ProcessSequence processSequence, String uuid,
		OrderByComparator<ProcessSequence> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PROCESSSEQUENCE_WHERE);

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
			query.append(ProcessSequenceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(processSequence);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessSequence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process sequences where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ProcessSequence processSequence : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processSequence);
		}
	}

	/**
	 * Returns the number of process sequences where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching process sequences
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSSEQUENCE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "processSequence.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "processSequence.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(processSequence.uuid IS NULL OR processSequence.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED,
			ProcessSequenceImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ProcessSequenceModelImpl.UUID_COLUMN_BITMASK |
			ProcessSequenceModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the process sequence where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessSequenceException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching process sequence
	 * @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence findByUUID_G(String uuid, long groupId)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = fetchByUUID_G(uuid, groupId);

		if (processSequence == null) {
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

			throw new NoSuchProcessSequenceException(msg.toString());
		}

		return processSequence;
	}

	/**
	 * Returns the process sequence where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching process sequence, or <code>null</code> if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the process sequence where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process sequence, or <code>null</code> if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ProcessSequence) {
			ProcessSequence processSequence = (ProcessSequence)result;

			if (!Objects.equals(uuid, processSequence.getUuid()) ||
					(groupId != processSequence.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PROCESSSEQUENCE_WHERE);

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

				List<ProcessSequence> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ProcessSequence processSequence = list.get(0);

					result = processSequence;

					cacheResult(processSequence);
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
			return (ProcessSequence)result;
		}
	}

	/**
	 * Removes the process sequence where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the process sequence that was removed
	 */
	@Override
	public ProcessSequence removeByUUID_G(String uuid, long groupId)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = findByUUID_G(uuid, groupId);

		return remove(processSequence);
	}

	/**
	 * Returns the number of process sequences where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching process sequences
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSSEQUENCE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "processSequence.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "processSequence.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(processSequence.uuid IS NULL OR processSequence.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "processSequence.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED,
			ProcessSequenceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED,
			ProcessSequenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ProcessSequenceModelImpl.UUID_COLUMN_BITMASK |
			ProcessSequenceModelImpl.COMPANYID_COLUMN_BITMASK |
			ProcessSequenceModelImpl.SEQUENCENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the process sequences where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process sequences where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @return the range of matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process sequences where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessSequence> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process sequences where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator,
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

		List<ProcessSequence> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessSequence>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessSequence processSequence : list) {
					if (!Objects.equals(uuid, processSequence.getUuid()) ||
							(companyId != processSequence.getCompanyId())) {
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

			query.append(_SQL_SELECT_PROCESSSEQUENCE_WHERE);

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
				query.append(ProcessSequenceModelImpl.ORDER_BY_JPQL);
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
					list = (List<ProcessSequence>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessSequence>)QueryUtil.list(q,
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
	 * Returns the first process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process sequence
	 * @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (processSequence != null) {
			return processSequence;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchProcessSequenceException(msg.toString());
	}

	/**
	 * Returns the first process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process sequence, or <code>null</code> if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessSequence> orderByComparator) {
		List<ProcessSequence> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process sequence
	 * @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (processSequence != null) {
			return processSequence;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchProcessSequenceException(msg.toString());
	}

	/**
	 * Returns the last process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process sequence, or <code>null</code> if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessSequence> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ProcessSequence> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process sequences before and after the current process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param processSequenceId the primary key of the current process sequence
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process sequence
	 * @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	 */
	@Override
	public ProcessSequence[] findByUuid_C_PrevAndNext(long processSequenceId,
		String uuid, long companyId,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = findByPrimaryKey(processSequenceId);

		Session session = null;

		try {
			session = openSession();

			ProcessSequence[] array = new ProcessSequenceImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, processSequence, uuid,
					companyId, orderByComparator, true);

			array[1] = processSequence;

			array[2] = getByUuid_C_PrevAndNext(session, processSequence, uuid,
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

	protected ProcessSequence getByUuid_C_PrevAndNext(Session session,
		ProcessSequence processSequence, String uuid, long companyId,
		OrderByComparator<ProcessSequence> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PROCESSSEQUENCE_WHERE);

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
			query.append(ProcessSequenceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(processSequence);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessSequence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process sequences where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ProcessSequence processSequence : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processSequence);
		}
	}

	/**
	 * Returns the number of process sequences where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching process sequences
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSSEQUENCE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "processSequence.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "processSequence.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(processSequence.uuid IS NULL OR processSequence.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "processSequence.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_SN = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED,
			ProcessSequenceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_SN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SN = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED,
			ProcessSequenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_SN",
			new String[] { Long.class.getName(), String.class.getName() },
			ProcessSequenceModelImpl.GROUPID_COLUMN_BITMASK |
			ProcessSequenceModelImpl.SEQUENCENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_SN = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_SN",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the process sequences where groupId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sequenceNo the sequence no
	 * @return the matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByG_SN(long groupId, String sequenceNo) {
		return findByG_SN(groupId, sequenceNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process sequences where groupId = &#63; and sequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sequenceNo the sequence no
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @return the range of matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByG_SN(long groupId, String sequenceNo,
		int start, int end) {
		return findByG_SN(groupId, sequenceNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process sequences where groupId = &#63; and sequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sequenceNo the sequence no
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByG_SN(long groupId, String sequenceNo,
		int start, int end, OrderByComparator<ProcessSequence> orderByComparator) {
		return findByG_SN(groupId, sequenceNo, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the process sequences where groupId = &#63; and sequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sequenceNo the sequence no
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByG_SN(long groupId, String sequenceNo,
		int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SN;
			finderArgs = new Object[] { groupId, sequenceNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_SN;
			finderArgs = new Object[] {
					groupId, sequenceNo,
					
					start, end, orderByComparator
				};
		}

		List<ProcessSequence> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessSequence>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessSequence processSequence : list) {
					if ((groupId != processSequence.getGroupId()) ||
							!Objects.equals(sequenceNo,
								processSequence.getSequenceNo())) {
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

			query.append(_SQL_SELECT_PROCESSSEQUENCE_WHERE);

			query.append(_FINDER_COLUMN_G_SN_GROUPID_2);

			boolean bindSequenceNo = false;

			if (sequenceNo == null) {
				query.append(_FINDER_COLUMN_G_SN_SEQUENCENO_1);
			}
			else if (sequenceNo.equals("")) {
				query.append(_FINDER_COLUMN_G_SN_SEQUENCENO_3);
			}
			else {
				bindSequenceNo = true;

				query.append(_FINDER_COLUMN_G_SN_SEQUENCENO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessSequenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindSequenceNo) {
					qPos.add(sequenceNo);
				}

				if (!pagination) {
					list = (List<ProcessSequence>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessSequence>)QueryUtil.list(q,
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
	 * Returns the first process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process sequence
	 * @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence findByG_SN_First(long groupId, String sequenceNo,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = fetchByG_SN_First(groupId,
				sequenceNo, orderByComparator);

		if (processSequence != null) {
			return processSequence;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", sequenceNo=");
		msg.append(sequenceNo);

		msg.append("}");

		throw new NoSuchProcessSequenceException(msg.toString());
	}

	/**
	 * Returns the first process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process sequence, or <code>null</code> if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence fetchByG_SN_First(long groupId, String sequenceNo,
		OrderByComparator<ProcessSequence> orderByComparator) {
		List<ProcessSequence> list = findByG_SN(groupId, sequenceNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process sequence
	 * @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence findByG_SN_Last(long groupId, String sequenceNo,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = fetchByG_SN_Last(groupId, sequenceNo,
				orderByComparator);

		if (processSequence != null) {
			return processSequence;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", sequenceNo=");
		msg.append(sequenceNo);

		msg.append("}");

		throw new NoSuchProcessSequenceException(msg.toString());
	}

	/**
	 * Returns the last process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process sequence, or <code>null</code> if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence fetchByG_SN_Last(long groupId, String sequenceNo,
		OrderByComparator<ProcessSequence> orderByComparator) {
		int count = countByG_SN(groupId, sequenceNo);

		if (count == 0) {
			return null;
		}

		List<ProcessSequence> list = findByG_SN(groupId, sequenceNo, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process sequences before and after the current process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	 *
	 * @param processSequenceId the primary key of the current process sequence
	 * @param groupId the group ID
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process sequence
	 * @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	 */
	@Override
	public ProcessSequence[] findByG_SN_PrevAndNext(long processSequenceId,
		long groupId, String sequenceNo,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = findByPrimaryKey(processSequenceId);

		Session session = null;

		try {
			session = openSession();

			ProcessSequence[] array = new ProcessSequenceImpl[3];

			array[0] = getByG_SN_PrevAndNext(session, processSequence, groupId,
					sequenceNo, orderByComparator, true);

			array[1] = processSequence;

			array[2] = getByG_SN_PrevAndNext(session, processSequence, groupId,
					sequenceNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessSequence getByG_SN_PrevAndNext(Session session,
		ProcessSequence processSequence, long groupId, String sequenceNo,
		OrderByComparator<ProcessSequence> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PROCESSSEQUENCE_WHERE);

		query.append(_FINDER_COLUMN_G_SN_GROUPID_2);

		boolean bindSequenceNo = false;

		if (sequenceNo == null) {
			query.append(_FINDER_COLUMN_G_SN_SEQUENCENO_1);
		}
		else if (sequenceNo.equals("")) {
			query.append(_FINDER_COLUMN_G_SN_SEQUENCENO_3);
		}
		else {
			bindSequenceNo = true;

			query.append(_FINDER_COLUMN_G_SN_SEQUENCENO_2);
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
			query.append(ProcessSequenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindSequenceNo) {
			qPos.add(sequenceNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processSequence);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessSequence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process sequences where groupId = &#63; and sequenceNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param sequenceNo the sequence no
	 */
	@Override
	public void removeByG_SN(long groupId, String sequenceNo) {
		for (ProcessSequence processSequence : findByG_SN(groupId, sequenceNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processSequence);
		}
	}

	/**
	 * Returns the number of process sequences where groupId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sequenceNo the sequence no
	 * @return the number of matching process sequences
	 */
	@Override
	public int countByG_SN(long groupId, String sequenceNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_SN;

		Object[] finderArgs = new Object[] { groupId, sequenceNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSSEQUENCE_WHERE);

			query.append(_FINDER_COLUMN_G_SN_GROUPID_2);

			boolean bindSequenceNo = false;

			if (sequenceNo == null) {
				query.append(_FINDER_COLUMN_G_SN_SEQUENCENO_1);
			}
			else if (sequenceNo.equals("")) {
				query.append(_FINDER_COLUMN_G_SN_SEQUENCENO_3);
			}
			else {
				bindSequenceNo = true;

				query.append(_FINDER_COLUMN_G_SN_SEQUENCENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_G_SN_GROUPID_2 = "processSequence.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_SN_SEQUENCENO_1 = "processSequence.sequenceNo IS NULL";
	private static final String _FINDER_COLUMN_G_SN_SEQUENCENO_2 = "processSequence.sequenceNo = ?";
	private static final String _FINDER_COLUMN_G_SN_SEQUENCENO_3 = "(processSequence.sequenceNo IS NULL OR processSequence.sequenceNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SID =
		new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED,
			ProcessSequenceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_GID_SID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID =
		new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED,
			ProcessSequenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_GID_SID",
			new String[] { Long.class.getName(), Long.class.getName() },
			ProcessSequenceModelImpl.GROUPID_COLUMN_BITMASK |
			ProcessSequenceModelImpl.SERVICEPROCESSID_COLUMN_BITMASK |
			ProcessSequenceModelImpl.SEQUENCENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_SID = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_SID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the process sequences where groupId = &#63; and serviceProcessId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @return the matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByF_GID_SID(long groupId,
		long serviceProcessId) {
		return findByF_GID_SID(groupId, serviceProcessId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process sequences where groupId = &#63; and serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @return the range of matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByF_GID_SID(long groupId,
		long serviceProcessId, int start, int end) {
		return findByF_GID_SID(groupId, serviceProcessId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process sequences where groupId = &#63; and serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByF_GID_SID(long groupId,
		long serviceProcessId, int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator) {
		return findByF_GID_SID(groupId, serviceProcessId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process sequences where groupId = &#63; and serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process sequences
	 */
	@Override
	public List<ProcessSequence> findByF_GID_SID(long groupId,
		long serviceProcessId, int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID;
			finderArgs = new Object[] { groupId, serviceProcessId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SID;
			finderArgs = new Object[] {
					groupId, serviceProcessId,
					
					start, end, orderByComparator
				};
		}

		List<ProcessSequence> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessSequence>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessSequence processSequence : list) {
					if ((groupId != processSequence.getGroupId()) ||
							(serviceProcessId != processSequence.getServiceProcessId())) {
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

			query.append(_SQL_SELECT_PROCESSSEQUENCE_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_SID_SERVICEPROCESSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessSequenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceProcessId);

				if (!pagination) {
					list = (List<ProcessSequence>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessSequence>)QueryUtil.list(q,
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
	 * Returns the first process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process sequence
	 * @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence findByF_GID_SID_First(long groupId,
		long serviceProcessId,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = fetchByF_GID_SID_First(groupId,
				serviceProcessId, orderByComparator);

		if (processSequence != null) {
			return processSequence;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append("}");

		throw new NoSuchProcessSequenceException(msg.toString());
	}

	/**
	 * Returns the first process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process sequence, or <code>null</code> if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence fetchByF_GID_SID_First(long groupId,
		long serviceProcessId,
		OrderByComparator<ProcessSequence> orderByComparator) {
		List<ProcessSequence> list = findByF_GID_SID(groupId, serviceProcessId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process sequence
	 * @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence findByF_GID_SID_Last(long groupId,
		long serviceProcessId,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = fetchByF_GID_SID_Last(groupId,
				serviceProcessId, orderByComparator);

		if (processSequence != null) {
			return processSequence;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append("}");

		throw new NoSuchProcessSequenceException(msg.toString());
	}

	/**
	 * Returns the last process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process sequence, or <code>null</code> if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence fetchByF_GID_SID_Last(long groupId,
		long serviceProcessId,
		OrderByComparator<ProcessSequence> orderByComparator) {
		int count = countByF_GID_SID(groupId, serviceProcessId);

		if (count == 0) {
			return null;
		}

		List<ProcessSequence> list = findByF_GID_SID(groupId, serviceProcessId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process sequences before and after the current process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	 *
	 * @param processSequenceId the primary key of the current process sequence
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process sequence
	 * @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	 */
	@Override
	public ProcessSequence[] findByF_GID_SID_PrevAndNext(
		long processSequenceId, long groupId, long serviceProcessId,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = findByPrimaryKey(processSequenceId);

		Session session = null;

		try {
			session = openSession();

			ProcessSequence[] array = new ProcessSequenceImpl[3];

			array[0] = getByF_GID_SID_PrevAndNext(session, processSequence,
					groupId, serviceProcessId, orderByComparator, true);

			array[1] = processSequence;

			array[2] = getByF_GID_SID_PrevAndNext(session, processSequence,
					groupId, serviceProcessId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessSequence getByF_GID_SID_PrevAndNext(Session session,
		ProcessSequence processSequence, long groupId, long serviceProcessId,
		OrderByComparator<ProcessSequence> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PROCESSSEQUENCE_WHERE);

		query.append(_FINDER_COLUMN_F_GID_SID_GROUPID_2);

		query.append(_FINDER_COLUMN_F_GID_SID_SERVICEPROCESSID_2);

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
			query.append(ProcessSequenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(serviceProcessId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processSequence);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessSequence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process sequences where groupId = &#63; and serviceProcessId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 */
	@Override
	public void removeByF_GID_SID(long groupId, long serviceProcessId) {
		for (ProcessSequence processSequence : findByF_GID_SID(groupId,
				serviceProcessId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processSequence);
		}
	}

	/**
	 * Returns the number of process sequences where groupId = &#63; and serviceProcessId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @return the number of matching process sequences
	 */
	@Override
	public int countByF_GID_SID(long groupId, long serviceProcessId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_SID;

		Object[] finderArgs = new Object[] { groupId, serviceProcessId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSSEQUENCE_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_SID_SERVICEPROCESSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_F_GID_SID_GROUPID_2 = "processSequence.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SID_SERVICEPROCESSID_2 = "processSequence.serviceProcessId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_SID_SNO = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED,
			ProcessSequenceImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_GID_SID_SNO",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			ProcessSequenceModelImpl.GROUPID_COLUMN_BITMASK |
			ProcessSequenceModelImpl.SERVICEPROCESSID_COLUMN_BITMASK |
			ProcessSequenceModelImpl.SEQUENCENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_SID_SNO = new FinderPath(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_SID_SNO",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the process sequence where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63; or throws a {@link NoSuchProcessSequenceException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @return the matching process sequence
	 * @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence findByF_GID_SID_SNO(long groupId,
		long serviceProcessId, String sequenceNo)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = fetchByF_GID_SID_SNO(groupId,
				serviceProcessId, sequenceNo);

		if (processSequence == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", serviceProcessId=");
			msg.append(serviceProcessId);

			msg.append(", sequenceNo=");
			msg.append(sequenceNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchProcessSequenceException(msg.toString());
		}

		return processSequence;
	}

	/**
	 * Returns the process sequence where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @return the matching process sequence, or <code>null</code> if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence fetchByF_GID_SID_SNO(long groupId,
		long serviceProcessId, String sequenceNo) {
		return fetchByF_GID_SID_SNO(groupId, serviceProcessId, sequenceNo, true);
	}

	/**
	 * Returns the process sequence where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process sequence, or <code>null</code> if a matching process sequence could not be found
	 */
	@Override
	public ProcessSequence fetchByF_GID_SID_SNO(long groupId,
		long serviceProcessId, String sequenceNo, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, serviceProcessId, sequenceNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_SID_SNO,
					finderArgs, this);
		}

		if (result instanceof ProcessSequence) {
			ProcessSequence processSequence = (ProcessSequence)result;

			if ((groupId != processSequence.getGroupId()) ||
					(serviceProcessId != processSequence.getServiceProcessId()) ||
					!Objects.equals(sequenceNo, processSequence.getSequenceNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_PROCESSSEQUENCE_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SID_SNO_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_SID_SNO_SERVICEPROCESSID_2);

			boolean bindSequenceNo = false;

			if (sequenceNo == null) {
				query.append(_FINDER_COLUMN_F_GID_SID_SNO_SEQUENCENO_1);
			}
			else if (sequenceNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SID_SNO_SEQUENCENO_3);
			}
			else {
				bindSequenceNo = true;

				query.append(_FINDER_COLUMN_F_GID_SID_SNO_SEQUENCENO_2);
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

				List<ProcessSequence> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_SID_SNO,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ProcessSequencePersistenceImpl.fetchByF_GID_SID_SNO(long, long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProcessSequence processSequence = list.get(0);

					result = processSequence;

					cacheResult(processSequence);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_SID_SNO,
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
			return (ProcessSequence)result;
		}
	}

	/**
	 * Removes the process sequence where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @return the process sequence that was removed
	 */
	@Override
	public ProcessSequence removeByF_GID_SID_SNO(long groupId,
		long serviceProcessId, String sequenceNo)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = findByF_GID_SID_SNO(groupId,
				serviceProcessId, sequenceNo);

		return remove(processSequence);
	}

	/**
	 * Returns the number of process sequences where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceProcessId the service process ID
	 * @param sequenceNo the sequence no
	 * @return the number of matching process sequences
	 */
	@Override
	public int countByF_GID_SID_SNO(long groupId, long serviceProcessId,
		String sequenceNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_SID_SNO;

		Object[] finderArgs = new Object[] { groupId, serviceProcessId, sequenceNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PROCESSSEQUENCE_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SID_SNO_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_SID_SNO_SERVICEPROCESSID_2);

			boolean bindSequenceNo = false;

			if (sequenceNo == null) {
				query.append(_FINDER_COLUMN_F_GID_SID_SNO_SEQUENCENO_1);
			}
			else if (sequenceNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SID_SNO_SEQUENCENO_3);
			}
			else {
				bindSequenceNo = true;

				query.append(_FINDER_COLUMN_F_GID_SID_SNO_SEQUENCENO_2);
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

	private static final String _FINDER_COLUMN_F_GID_SID_SNO_GROUPID_2 = "processSequence.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SID_SNO_SERVICEPROCESSID_2 = "processSequence.serviceProcessId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SID_SNO_SEQUENCENO_1 = "processSequence.sequenceNo IS NULL";
	private static final String _FINDER_COLUMN_F_GID_SID_SNO_SEQUENCENO_2 = "processSequence.sequenceNo = ?";
	private static final String _FINDER_COLUMN_F_GID_SID_SNO_SEQUENCENO_3 = "(processSequence.sequenceNo IS NULL OR processSequence.sequenceNo = '')";

	public ProcessSequencePersistenceImpl() {
		setModelClass(ProcessSequence.class);

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
	 * Caches the process sequence in the entity cache if it is enabled.
	 *
	 * @param processSequence the process sequence
	 */
	@Override
	public void cacheResult(ProcessSequence processSequence) {
		entityCache.putResult(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceImpl.class, processSequence.getPrimaryKey(),
			processSequence);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { processSequence.getUuid(), processSequence.getGroupId() },
			processSequence);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_SID_SNO,
			new Object[] {
				processSequence.getGroupId(),
				processSequence.getServiceProcessId(),
				processSequence.getSequenceNo()
			}, processSequence);

		processSequence.resetOriginalValues();
	}

	/**
	 * Caches the process sequences in the entity cache if it is enabled.
	 *
	 * @param processSequences the process sequences
	 */
	@Override
	public void cacheResult(List<ProcessSequence> processSequences) {
		for (ProcessSequence processSequence : processSequences) {
			if (entityCache.getResult(
						ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
						ProcessSequenceImpl.class,
						processSequence.getPrimaryKey()) == null) {
				cacheResult(processSequence);
			}
			else {
				processSequence.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all process sequences.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProcessSequenceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the process sequence.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProcessSequence processSequence) {
		entityCache.removeResult(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceImpl.class, processSequence.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ProcessSequenceModelImpl)processSequence, true);
	}

	@Override
	public void clearCache(List<ProcessSequence> processSequences) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProcessSequence processSequence : processSequences) {
			entityCache.removeResult(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
				ProcessSequenceImpl.class, processSequence.getPrimaryKey());

			clearUniqueFindersCache((ProcessSequenceModelImpl)processSequence,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ProcessSequenceModelImpl processSequenceModelImpl) {
		Object[] args = new Object[] {
				processSequenceModelImpl.getUuid(),
				processSequenceModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			processSequenceModelImpl, false);

		args = new Object[] {
				processSequenceModelImpl.getGroupId(),
				processSequenceModelImpl.getServiceProcessId(),
				processSequenceModelImpl.getSequenceNo()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_SID_SNO, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_SID_SNO, args,
			processSequenceModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ProcessSequenceModelImpl processSequenceModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					processSequenceModelImpl.getUuid(),
					processSequenceModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((processSequenceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processSequenceModelImpl.getOriginalUuid(),
					processSequenceModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					processSequenceModelImpl.getGroupId(),
					processSequenceModelImpl.getServiceProcessId(),
					processSequenceModelImpl.getSequenceNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SID_SNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_SID_SNO, args);
		}

		if ((processSequenceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_SID_SNO.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processSequenceModelImpl.getOriginalGroupId(),
					processSequenceModelImpl.getOriginalServiceProcessId(),
					processSequenceModelImpl.getOriginalSequenceNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SID_SNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_SID_SNO, args);
		}
	}

	/**
	 * Creates a new process sequence with the primary key. Does not add the process sequence to the database.
	 *
	 * @param processSequenceId the primary key for the new process sequence
	 * @return the new process sequence
	 */
	@Override
	public ProcessSequence create(long processSequenceId) {
		ProcessSequence processSequence = new ProcessSequenceImpl();

		processSequence.setNew(true);
		processSequence.setPrimaryKey(processSequenceId);

		String uuid = PortalUUIDUtil.generate();

		processSequence.setUuid(uuid);

		processSequence.setCompanyId(companyProvider.getCompanyId());

		return processSequence;
	}

	/**
	 * Removes the process sequence with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param processSequenceId the primary key of the process sequence
	 * @return the process sequence that was removed
	 * @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	 */
	@Override
	public ProcessSequence remove(long processSequenceId)
		throws NoSuchProcessSequenceException {
		return remove((Serializable)processSequenceId);
	}

	/**
	 * Removes the process sequence with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the process sequence
	 * @return the process sequence that was removed
	 * @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	 */
	@Override
	public ProcessSequence remove(Serializable primaryKey)
		throws NoSuchProcessSequenceException {
		Session session = null;

		try {
			session = openSession();

			ProcessSequence processSequence = (ProcessSequence)session.get(ProcessSequenceImpl.class,
					primaryKey);

			if (processSequence == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProcessSequenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(processSequence);
		}
		catch (NoSuchProcessSequenceException nsee) {
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
	protected ProcessSequence removeImpl(ProcessSequence processSequence) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(processSequence)) {
				processSequence = (ProcessSequence)session.get(ProcessSequenceImpl.class,
						processSequence.getPrimaryKeyObj());
			}

			if (processSequence != null) {
				session.delete(processSequence);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (processSequence != null) {
			clearCache(processSequence);
		}

		return processSequence;
	}

	@Override
	public ProcessSequence updateImpl(ProcessSequence processSequence) {
		boolean isNew = processSequence.isNew();

		if (!(processSequence instanceof ProcessSequenceModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(processSequence.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(processSequence);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in processSequence proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProcessSequence implementation " +
				processSequence.getClass());
		}

		ProcessSequenceModelImpl processSequenceModelImpl = (ProcessSequenceModelImpl)processSequence;

		if (Validator.isNull(processSequence.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			processSequence.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (processSequence.getCreateDate() == null)) {
			if (serviceContext == null) {
				processSequence.setCreateDate(now);
			}
			else {
				processSequence.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!processSequenceModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				processSequence.setModifiedDate(now);
			}
			else {
				processSequence.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (processSequence.isNew()) {
				session.save(processSequence);

				processSequence.setNew(false);
			}
			else {
				processSequence = (ProcessSequence)session.merge(processSequence);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ProcessSequenceModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { processSequenceModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					processSequenceModelImpl.getUuid(),
					processSequenceModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					processSequenceModelImpl.getGroupId(),
					processSequenceModelImpl.getSequenceNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SN, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SN,
				args);

			args = new Object[] {
					processSequenceModelImpl.getGroupId(),
					processSequenceModelImpl.getServiceProcessId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((processSequenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processSequenceModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { processSequenceModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((processSequenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processSequenceModelImpl.getOriginalUuid(),
						processSequenceModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						processSequenceModelImpl.getUuid(),
						processSequenceModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((processSequenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processSequenceModelImpl.getOriginalGroupId(),
						processSequenceModelImpl.getOriginalSequenceNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SN,
					args);

				args = new Object[] {
						processSequenceModelImpl.getGroupId(),
						processSequenceModelImpl.getSequenceNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SN,
					args);
			}

			if ((processSequenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processSequenceModelImpl.getOriginalGroupId(),
						processSequenceModelImpl.getOriginalServiceProcessId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID,
					args);

				args = new Object[] {
						processSequenceModelImpl.getGroupId(),
						processSequenceModelImpl.getServiceProcessId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID,
					args);
			}
		}

		entityCache.putResult(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
			ProcessSequenceImpl.class, processSequence.getPrimaryKey(),
			processSequence, false);

		clearUniqueFindersCache(processSequenceModelImpl, false);
		cacheUniqueFindersCache(processSequenceModelImpl);

		processSequence.resetOriginalValues();

		return processSequence;
	}

	/**
	 * Returns the process sequence with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the process sequence
	 * @return the process sequence
	 * @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	 */
	@Override
	public ProcessSequence findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProcessSequenceException {
		ProcessSequence processSequence = fetchByPrimaryKey(primaryKey);

		if (processSequence == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProcessSequenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return processSequence;
	}

	/**
	 * Returns the process sequence with the primary key or throws a {@link NoSuchProcessSequenceException} if it could not be found.
	 *
	 * @param processSequenceId the primary key of the process sequence
	 * @return the process sequence
	 * @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	 */
	@Override
	public ProcessSequence findByPrimaryKey(long processSequenceId)
		throws NoSuchProcessSequenceException {
		return findByPrimaryKey((Serializable)processSequenceId);
	}

	/**
	 * Returns the process sequence with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the process sequence
	 * @return the process sequence, or <code>null</code> if a process sequence with the primary key could not be found
	 */
	@Override
	public ProcessSequence fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
				ProcessSequenceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProcessSequence processSequence = (ProcessSequence)serializable;

		if (processSequence == null) {
			Session session = null;

			try {
				session = openSession();

				processSequence = (ProcessSequence)session.get(ProcessSequenceImpl.class,
						primaryKey);

				if (processSequence != null) {
					cacheResult(processSequence);
				}
				else {
					entityCache.putResult(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
						ProcessSequenceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
					ProcessSequenceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return processSequence;
	}

	/**
	 * Returns the process sequence with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param processSequenceId the primary key of the process sequence
	 * @return the process sequence, or <code>null</code> if a process sequence with the primary key could not be found
	 */
	@Override
	public ProcessSequence fetchByPrimaryKey(long processSequenceId) {
		return fetchByPrimaryKey((Serializable)processSequenceId);
	}

	@Override
	public Map<Serializable, ProcessSequence> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProcessSequence> map = new HashMap<Serializable, ProcessSequence>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProcessSequence processSequence = fetchByPrimaryKey(primaryKey);

			if (processSequence != null) {
				map.put(primaryKey, processSequence);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
					ProcessSequenceImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProcessSequence)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROCESSSEQUENCE_WHERE_PKS_IN);

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

			for (ProcessSequence processSequence : (List<ProcessSequence>)q.list()) {
				map.put(processSequence.getPrimaryKeyObj(), processSequence);

				cacheResult(processSequence);

				uncachedPrimaryKeys.remove(processSequence.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProcessSequenceModelImpl.ENTITY_CACHE_ENABLED,
					ProcessSequenceImpl.class, primaryKey, nullModel);
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
	 * Returns all the process sequences.
	 *
	 * @return the process sequences
	 */
	@Override
	public List<ProcessSequence> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process sequences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @return the range of process sequences
	 */
	@Override
	public List<ProcessSequence> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the process sequences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of process sequences
	 */
	@Override
	public List<ProcessSequence> findAll(int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process sequences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process sequences
	 * @param end the upper bound of the range of process sequences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of process sequences
	 */
	@Override
	public List<ProcessSequence> findAll(int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator,
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

		List<ProcessSequence> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessSequence>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROCESSSEQUENCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROCESSSEQUENCE;

				if (pagination) {
					sql = sql.concat(ProcessSequenceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProcessSequence>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessSequence>)QueryUtil.list(q,
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
	 * Removes all the process sequences from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProcessSequence processSequence : findAll()) {
			remove(processSequence);
		}
	}

	/**
	 * Returns the number of process sequences.
	 *
	 * @return the number of process sequences
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROCESSSEQUENCE);

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
		return ProcessSequenceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the process sequence persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProcessSequenceImpl.class.getName());
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
	private static final String _SQL_SELECT_PROCESSSEQUENCE = "SELECT processSequence FROM ProcessSequence processSequence";
	private static final String _SQL_SELECT_PROCESSSEQUENCE_WHERE_PKS_IN = "SELECT processSequence FROM ProcessSequence processSequence WHERE processSequenceId IN (";
	private static final String _SQL_SELECT_PROCESSSEQUENCE_WHERE = "SELECT processSequence FROM ProcessSequence processSequence WHERE ";
	private static final String _SQL_COUNT_PROCESSSEQUENCE = "SELECT COUNT(processSequence) FROM ProcessSequence processSequence";
	private static final String _SQL_COUNT_PROCESSSEQUENCE_WHERE = "SELECT COUNT(processSequence) FROM ProcessSequence processSequence WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "processSequence.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProcessSequence exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProcessSequence exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ProcessSequencePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}