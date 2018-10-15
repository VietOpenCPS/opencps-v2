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

package org.opencps.usermgt.service.persistence.impl;

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

import org.opencps.usermgt.exception.NoSuchJobPosException;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.model.impl.JobPosImpl;
import org.opencps.usermgt.model.impl.JobPosModelImpl;
import org.opencps.usermgt.service.persistence.JobPosPersistence;

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
 * The persistence implementation for the job pos service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see JobPosPersistence
 * @see org.opencps.usermgt.service.persistence.JobPosUtil
 * @generated
 */
@ProviderType
public class JobPosPersistenceImpl extends BasePersistenceImpl<JobPos>
	implements JobPosPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JobPosUtil} to access the job pos persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JobPosImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, JobPosImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, JobPosImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, JobPosImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, JobPosImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			JobPosModelImpl.UUID_COLUMN_BITMASK |
			JobPosModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the job poses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching job poses
	 */
	@Override
	public List<JobPos> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the job poses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of job poses
	 * @param end the upper bound of the range of job poses (not inclusive)
	 * @return the range of matching job poses
	 */
	@Override
	public List<JobPos> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the job poses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of job poses
	 * @param end the upper bound of the range of job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching job poses
	 */
	@Override
	public List<JobPos> findByUuid(String uuid, int start, int end,
		OrderByComparator<JobPos> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the job poses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of job poses
	 * @param end the upper bound of the range of job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching job poses
	 */
	@Override
	public List<JobPos> findByUuid(String uuid, int start, int end,
		OrderByComparator<JobPos> orderByComparator, boolean retrieveFromCache) {
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

		List<JobPos> list = null;

		if (retrieveFromCache) {
			list = (List<JobPos>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (JobPos jobPos : list) {
					if (!Objects.equals(uuid, jobPos.getUuid())) {
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

			query.append(_SQL_SELECT_JOBPOS_WHERE);

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
				query.append(JobPosModelImpl.ORDER_BY_JPQL);
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
					list = (List<JobPos>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JobPos>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first job pos in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job pos
	 * @throws NoSuchJobPosException if a matching job pos could not be found
	 */
	@Override
	public JobPos findByUuid_First(String uuid,
		OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException {
		JobPos jobPos = fetchByUuid_First(uuid, orderByComparator);

		if (jobPos != null) {
			return jobPos;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchJobPosException(msg.toString());
	}

	/**
	 * Returns the first job pos in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job pos, or <code>null</code> if a matching job pos could not be found
	 */
	@Override
	public JobPos fetchByUuid_First(String uuid,
		OrderByComparator<JobPos> orderByComparator) {
		List<JobPos> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job pos in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job pos
	 * @throws NoSuchJobPosException if a matching job pos could not be found
	 */
	@Override
	public JobPos findByUuid_Last(String uuid,
		OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException {
		JobPos jobPos = fetchByUuid_Last(uuid, orderByComparator);

		if (jobPos != null) {
			return jobPos;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchJobPosException(msg.toString());
	}

	/**
	 * Returns the last job pos in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job pos, or <code>null</code> if a matching job pos could not be found
	 */
	@Override
	public JobPos fetchByUuid_Last(String uuid,
		OrderByComparator<JobPos> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<JobPos> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the job poses before and after the current job pos in the ordered set where uuid = &#63;.
	 *
	 * @param jobPosId the primary key of the current job pos
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job pos
	 * @throws NoSuchJobPosException if a job pos with the primary key could not be found
	 */
	@Override
	public JobPos[] findByUuid_PrevAndNext(long jobPosId, String uuid,
		OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException {
		JobPos jobPos = findByPrimaryKey(jobPosId);

		Session session = null;

		try {
			session = openSession();

			JobPos[] array = new JobPosImpl[3];

			array[0] = getByUuid_PrevAndNext(session, jobPos, uuid,
					orderByComparator, true);

			array[1] = jobPos;

			array[2] = getByUuid_PrevAndNext(session, jobPos, uuid,
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

	protected JobPos getByUuid_PrevAndNext(Session session, JobPos jobPos,
		String uuid, OrderByComparator<JobPos> orderByComparator,
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

		query.append(_SQL_SELECT_JOBPOS_WHERE);

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
			query.append(JobPosModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(jobPos);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JobPos> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the job poses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (JobPos jobPos : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(jobPos);
		}
	}

	/**
	 * Returns the number of job poses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching job poses
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOBPOS_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "jobPos.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "jobPos.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(jobPos.uuid IS NULL OR jobPos.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, JobPosImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			JobPosModelImpl.UUID_COLUMN_BITMASK |
			JobPosModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the job pos where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchJobPosException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching job pos
	 * @throws NoSuchJobPosException if a matching job pos could not be found
	 */
	@Override
	public JobPos findByUUID_G(String uuid, long groupId)
		throws NoSuchJobPosException {
		JobPos jobPos = fetchByUUID_G(uuid, groupId);

		if (jobPos == null) {
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

			throw new NoSuchJobPosException(msg.toString());
		}

		return jobPos;
	}

	/**
	 * Returns the job pos where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	 */
	@Override
	public JobPos fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the job pos where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	 */
	@Override
	public JobPos fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof JobPos) {
			JobPos jobPos = (JobPos)result;

			if (!Objects.equals(uuid, jobPos.getUuid()) ||
					(groupId != jobPos.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_JOBPOS_WHERE);

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

				List<JobPos> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					JobPos jobPos = list.get(0);

					result = jobPos;

					cacheResult(jobPos);
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
			return (JobPos)result;
		}
	}

	/**
	 * Removes the job pos where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the job pos that was removed
	 */
	@Override
	public JobPos removeByUUID_G(String uuid, long groupId)
		throws NoSuchJobPosException {
		JobPos jobPos = findByUUID_G(uuid, groupId);

		return remove(jobPos);
	}

	/**
	 * Returns the number of job poses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching job poses
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOBPOS_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "jobPos.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "jobPos.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(jobPos.uuid IS NULL OR jobPos.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "jobPos.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, JobPosImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, JobPosImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			JobPosModelImpl.UUID_COLUMN_BITMASK |
			JobPosModelImpl.COMPANYID_COLUMN_BITMASK |
			JobPosModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the job poses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching job poses
	 */
	@Override
	public List<JobPos> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the job poses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of job poses
	 * @param end the upper bound of the range of job poses (not inclusive)
	 * @return the range of matching job poses
	 */
	@Override
	public List<JobPos> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the job poses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of job poses
	 * @param end the upper bound of the range of job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching job poses
	 */
	@Override
	public List<JobPos> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<JobPos> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the job poses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of job poses
	 * @param end the upper bound of the range of job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching job poses
	 */
	@Override
	public List<JobPos> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<JobPos> orderByComparator,
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

		List<JobPos> list = null;

		if (retrieveFromCache) {
			list = (List<JobPos>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (JobPos jobPos : list) {
					if (!Objects.equals(uuid, jobPos.getUuid()) ||
							(companyId != jobPos.getCompanyId())) {
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

			query.append(_SQL_SELECT_JOBPOS_WHERE);

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
				query.append(JobPosModelImpl.ORDER_BY_JPQL);
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
					list = (List<JobPos>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JobPos>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job pos
	 * @throws NoSuchJobPosException if a matching job pos could not be found
	 */
	@Override
	public JobPos findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException {
		JobPos jobPos = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (jobPos != null) {
			return jobPos;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchJobPosException(msg.toString());
	}

	/**
	 * Returns the first job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job pos, or <code>null</code> if a matching job pos could not be found
	 */
	@Override
	public JobPos fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<JobPos> orderByComparator) {
		List<JobPos> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job pos
	 * @throws NoSuchJobPosException if a matching job pos could not be found
	 */
	@Override
	public JobPos findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException {
		JobPos jobPos = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (jobPos != null) {
			return jobPos;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchJobPosException(msg.toString());
	}

	/**
	 * Returns the last job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job pos, or <code>null</code> if a matching job pos could not be found
	 */
	@Override
	public JobPos fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<JobPos> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<JobPos> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the job poses before and after the current job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param jobPosId the primary key of the current job pos
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job pos
	 * @throws NoSuchJobPosException if a job pos with the primary key could not be found
	 */
	@Override
	public JobPos[] findByUuid_C_PrevAndNext(long jobPosId, String uuid,
		long companyId, OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException {
		JobPos jobPos = findByPrimaryKey(jobPosId);

		Session session = null;

		try {
			session = openSession();

			JobPos[] array = new JobPosImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, jobPos, uuid,
					companyId, orderByComparator, true);

			array[1] = jobPos;

			array[2] = getByUuid_C_PrevAndNext(session, jobPos, uuid,
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

	protected JobPos getByUuid_C_PrevAndNext(Session session, JobPos jobPos,
		String uuid, long companyId,
		OrderByComparator<JobPos> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOBPOS_WHERE);

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
			query.append(JobPosModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(jobPos);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JobPos> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the job poses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (JobPos jobPos : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(jobPos);
		}
	}

	/**
	 * Returns the number of job poses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching job poses
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOBPOS_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "jobPos.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "jobPos.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(jobPos.uuid IS NULL OR jobPos.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "jobPos.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_TITLE = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, JobPosImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_title",
			new String[] { Long.class.getName(), String.class.getName() },
			JobPosModelImpl.GROUPID_COLUMN_BITMASK |
			JobPosModelImpl.TITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_TITLE = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_title",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the job pos where groupId = &#63; and title = &#63; or throws a {@link NoSuchJobPosException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the matching job pos
	 * @throws NoSuchJobPosException if a matching job pos could not be found
	 */
	@Override
	public JobPos findByF_title(long groupId, String title)
		throws NoSuchJobPosException {
		JobPos jobPos = fetchByF_title(groupId, title);

		if (jobPos == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", title=");
			msg.append(title);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchJobPosException(msg.toString());
		}

		return jobPos;
	}

	/**
	 * Returns the job pos where groupId = &#63; and title = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	 */
	@Override
	public JobPos fetchByF_title(long groupId, String title) {
		return fetchByF_title(groupId, title, true);
	}

	/**
	 * Returns the job pos where groupId = &#63; and title = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	 */
	@Override
	public JobPos fetchByF_title(long groupId, String title,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, title };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_TITLE,
					finderArgs, this);
		}

		if (result instanceof JobPos) {
			JobPos jobPos = (JobPos)result;

			if ((groupId != jobPos.getGroupId()) ||
					!Objects.equals(title, jobPos.getTitle())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_JOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_TITLE_GROUPID_2);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_F_TITLE_TITLE_1);
			}
			else if (title.equals("")) {
				query.append(_FINDER_COLUMN_F_TITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_F_TITLE_TITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTitle) {
					qPos.add(title);
				}

				List<JobPos> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_TITLE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"JobPosPersistenceImpl.fetchByF_title(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					JobPos jobPos = list.get(0);

					result = jobPos;

					cacheResult(jobPos);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_TITLE,
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
			return (JobPos)result;
		}
	}

	/**
	 * Removes the job pos where groupId = &#63; and title = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the job pos that was removed
	 */
	@Override
	public JobPos removeByF_title(long groupId, String title)
		throws NoSuchJobPosException {
		JobPos jobPos = findByF_title(groupId, title);

		return remove(jobPos);
	}

	/**
	 * Returns the number of job poses where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the number of matching job poses
	 */
	@Override
	public int countByF_title(long groupId, String title) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_TITLE;

		Object[] finderArgs = new Object[] { groupId, title };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_TITLE_GROUPID_2);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_F_TITLE_TITLE_1);
			}
			else if (title.equals("")) {
				query.append(_FINDER_COLUMN_F_TITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_F_TITLE_TITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTitle) {
					qPos.add(title);
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

	private static final String _FINDER_COLUMN_F_TITLE_GROUPID_2 = "jobPos.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_TITLE_TITLE_1 = "jobPos.title IS NULL";
	private static final String _FINDER_COLUMN_F_TITLE_TITLE_2 = "jobPos.title = ?";
	private static final String _FINDER_COLUMN_F_TITLE_TITLE_3 = "(jobPos.title IS NULL OR jobPos.title = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_MAPPINGROLEID = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, JobPosImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_mappingRoleId",
			new String[] { Long.class.getName(), Long.class.getName() },
			JobPosModelImpl.GROUPID_COLUMN_BITMASK |
			JobPosModelImpl.MAPPINGROLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_MAPPINGROLEID = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_mappingRoleId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the job pos where groupId = &#63; and mappingRoleId = &#63; or throws a {@link NoSuchJobPosException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param mappingRoleId the mapping role ID
	 * @return the matching job pos
	 * @throws NoSuchJobPosException if a matching job pos could not be found
	 */
	@Override
	public JobPos findByF_mappingRoleId(long groupId, long mappingRoleId)
		throws NoSuchJobPosException {
		JobPos jobPos = fetchByF_mappingRoleId(groupId, mappingRoleId);

		if (jobPos == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", mappingRoleId=");
			msg.append(mappingRoleId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchJobPosException(msg.toString());
		}

		return jobPos;
	}

	/**
	 * Returns the job pos where groupId = &#63; and mappingRoleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param mappingRoleId the mapping role ID
	 * @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	 */
	@Override
	public JobPos fetchByF_mappingRoleId(long groupId, long mappingRoleId) {
		return fetchByF_mappingRoleId(groupId, mappingRoleId, true);
	}

	/**
	 * Returns the job pos where groupId = &#63; and mappingRoleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param mappingRoleId the mapping role ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	 */
	@Override
	public JobPos fetchByF_mappingRoleId(long groupId, long mappingRoleId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, mappingRoleId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_MAPPINGROLEID,
					finderArgs, this);
		}

		if (result instanceof JobPos) {
			JobPos jobPos = (JobPos)result;

			if ((groupId != jobPos.getGroupId()) ||
					(mappingRoleId != jobPos.getMappingRoleId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_JOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_MAPPINGROLEID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_MAPPINGROLEID_MAPPINGROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(mappingRoleId);

				List<JobPos> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_MAPPINGROLEID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"JobPosPersistenceImpl.fetchByF_mappingRoleId(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					JobPos jobPos = list.get(0);

					result = jobPos;

					cacheResult(jobPos);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_MAPPINGROLEID,
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
			return (JobPos)result;
		}
	}

	/**
	 * Removes the job pos where groupId = &#63; and mappingRoleId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param mappingRoleId the mapping role ID
	 * @return the job pos that was removed
	 */
	@Override
	public JobPos removeByF_mappingRoleId(long groupId, long mappingRoleId)
		throws NoSuchJobPosException {
		JobPos jobPos = findByF_mappingRoleId(groupId, mappingRoleId);

		return remove(jobPos);
	}

	/**
	 * Returns the number of job poses where groupId = &#63; and mappingRoleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param mappingRoleId the mapping role ID
	 * @return the number of matching job poses
	 */
	@Override
	public int countByF_mappingRoleId(long groupId, long mappingRoleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_MAPPINGROLEID;

		Object[] finderArgs = new Object[] { groupId, mappingRoleId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_MAPPINGROLEID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_MAPPINGROLEID_MAPPINGROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(mappingRoleId);

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

	private static final String _FINDER_COLUMN_F_MAPPINGROLEID_GROUPID_2 = "jobPos.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_MAPPINGROLEID_MAPPINGROLEID_2 = "jobPos.mappingRoleId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_CODE = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, JobPosImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_CODE",
			new String[] { Long.class.getName(), String.class.getName() },
			JobPosModelImpl.GROUPID_COLUMN_BITMASK |
			JobPosModelImpl.JOBPOSCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_CODE = new FinderPath(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_CODE",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the job pos where groupId = &#63; and jobPosCode = &#63; or throws a {@link NoSuchJobPosException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param jobPosCode the job pos code
	 * @return the matching job pos
	 * @throws NoSuchJobPosException if a matching job pos could not be found
	 */
	@Override
	public JobPos findByF_CODE(long groupId, String jobPosCode)
		throws NoSuchJobPosException {
		JobPos jobPos = fetchByF_CODE(groupId, jobPosCode);

		if (jobPos == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", jobPosCode=");
			msg.append(jobPosCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchJobPosException(msg.toString());
		}

		return jobPos;
	}

	/**
	 * Returns the job pos where groupId = &#63; and jobPosCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param jobPosCode the job pos code
	 * @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	 */
	@Override
	public JobPos fetchByF_CODE(long groupId, String jobPosCode) {
		return fetchByF_CODE(groupId, jobPosCode, true);
	}

	/**
	 * Returns the job pos where groupId = &#63; and jobPosCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param jobPosCode the job pos code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	 */
	@Override
	public JobPos fetchByF_CODE(long groupId, String jobPosCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, jobPosCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_CODE,
					finderArgs, this);
		}

		if (result instanceof JobPos) {
			JobPos jobPos = (JobPos)result;

			if ((groupId != jobPos.getGroupId()) ||
					!Objects.equals(jobPosCode, jobPos.getJobPosCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_JOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_CODE_GROUPID_2);

			boolean bindJobPosCode = false;

			if (jobPosCode == null) {
				query.append(_FINDER_COLUMN_F_CODE_JOBPOSCODE_1);
			}
			else if (jobPosCode.equals("")) {
				query.append(_FINDER_COLUMN_F_CODE_JOBPOSCODE_3);
			}
			else {
				bindJobPosCode = true;

				query.append(_FINDER_COLUMN_F_CODE_JOBPOSCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindJobPosCode) {
					qPos.add(jobPosCode);
				}

				List<JobPos> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"JobPosPersistenceImpl.fetchByF_CODE(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					JobPos jobPos = list.get(0);

					result = jobPos;

					cacheResult(jobPos);
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
			return (JobPos)result;
		}
	}

	/**
	 * Removes the job pos where groupId = &#63; and jobPosCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param jobPosCode the job pos code
	 * @return the job pos that was removed
	 */
	@Override
	public JobPos removeByF_CODE(long groupId, String jobPosCode)
		throws NoSuchJobPosException {
		JobPos jobPos = findByF_CODE(groupId, jobPosCode);

		return remove(jobPos);
	}

	/**
	 * Returns the number of job poses where groupId = &#63; and jobPosCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobPosCode the job pos code
	 * @return the number of matching job poses
	 */
	@Override
	public int countByF_CODE(long groupId, String jobPosCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_CODE;

		Object[] finderArgs = new Object[] { groupId, jobPosCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_CODE_GROUPID_2);

			boolean bindJobPosCode = false;

			if (jobPosCode == null) {
				query.append(_FINDER_COLUMN_F_CODE_JOBPOSCODE_1);
			}
			else if (jobPosCode.equals("")) {
				query.append(_FINDER_COLUMN_F_CODE_JOBPOSCODE_3);
			}
			else {
				bindJobPosCode = true;

				query.append(_FINDER_COLUMN_F_CODE_JOBPOSCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindJobPosCode) {
					qPos.add(jobPosCode);
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

	private static final String _FINDER_COLUMN_F_CODE_GROUPID_2 = "jobPos.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_CODE_JOBPOSCODE_1 = "jobPos.jobPosCode IS NULL";
	private static final String _FINDER_COLUMN_F_CODE_JOBPOSCODE_2 = "jobPos.jobPosCode = ?";
	private static final String _FINDER_COLUMN_F_CODE_JOBPOSCODE_3 = "(jobPos.jobPosCode IS NULL OR jobPos.jobPosCode = '')";

	public JobPosPersistenceImpl() {
		setModelClass(JobPos.class);

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
	 * Caches the job pos in the entity cache if it is enabled.
	 *
	 * @param jobPos the job pos
	 */
	@Override
	public void cacheResult(JobPos jobPos) {
		entityCache.putResult(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosImpl.class, jobPos.getPrimaryKey(), jobPos);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { jobPos.getUuid(), jobPos.getGroupId() }, jobPos);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_TITLE,
			new Object[] { jobPos.getGroupId(), jobPos.getTitle() }, jobPos);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_MAPPINGROLEID,
			new Object[] { jobPos.getGroupId(), jobPos.getMappingRoleId() },
			jobPos);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE,
			new Object[] { jobPos.getGroupId(), jobPos.getJobPosCode() }, jobPos);

		jobPos.resetOriginalValues();
	}

	/**
	 * Caches the job poses in the entity cache if it is enabled.
	 *
	 * @param jobPoses the job poses
	 */
	@Override
	public void cacheResult(List<JobPos> jobPoses) {
		for (JobPos jobPos : jobPoses) {
			if (entityCache.getResult(JobPosModelImpl.ENTITY_CACHE_ENABLED,
						JobPosImpl.class, jobPos.getPrimaryKey()) == null) {
				cacheResult(jobPos);
			}
			else {
				jobPos.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all job poses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(JobPosImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the job pos.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JobPos jobPos) {
		entityCache.removeResult(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosImpl.class, jobPos.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((JobPosModelImpl)jobPos, true);
	}

	@Override
	public void clearCache(List<JobPos> jobPoses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (JobPos jobPos : jobPoses) {
			entityCache.removeResult(JobPosModelImpl.ENTITY_CACHE_ENABLED,
				JobPosImpl.class, jobPos.getPrimaryKey());

			clearUniqueFindersCache((JobPosModelImpl)jobPos, true);
		}
	}

	protected void cacheUniqueFindersCache(JobPosModelImpl jobPosModelImpl) {
		Object[] args = new Object[] {
				jobPosModelImpl.getUuid(), jobPosModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			jobPosModelImpl, false);

		args = new Object[] {
				jobPosModelImpl.getGroupId(), jobPosModelImpl.getTitle()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_TITLE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_TITLE, args,
			jobPosModelImpl, false);

		args = new Object[] {
				jobPosModelImpl.getGroupId(), jobPosModelImpl.getMappingRoleId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_MAPPINGROLEID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_MAPPINGROLEID, args,
			jobPosModelImpl, false);

		args = new Object[] {
				jobPosModelImpl.getGroupId(), jobPosModelImpl.getJobPosCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_CODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE, args,
			jobPosModelImpl, false);
	}

	protected void clearUniqueFindersCache(JobPosModelImpl jobPosModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					jobPosModelImpl.getUuid(), jobPosModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((jobPosModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					jobPosModelImpl.getOriginalUuid(),
					jobPosModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					jobPosModelImpl.getGroupId(), jobPosModelImpl.getTitle()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_TITLE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_TITLE, args);
		}

		if ((jobPosModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_TITLE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					jobPosModelImpl.getOriginalGroupId(),
					jobPosModelImpl.getOriginalTitle()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_TITLE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_TITLE, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					jobPosModelImpl.getGroupId(),
					jobPosModelImpl.getMappingRoleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_MAPPINGROLEID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_MAPPINGROLEID, args);
		}

		if ((jobPosModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_MAPPINGROLEID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					jobPosModelImpl.getOriginalGroupId(),
					jobPosModelImpl.getOriginalMappingRoleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_MAPPINGROLEID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_MAPPINGROLEID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					jobPosModelImpl.getGroupId(),
					jobPosModelImpl.getJobPosCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CODE, args);
		}

		if ((jobPosModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_CODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					jobPosModelImpl.getOriginalGroupId(),
					jobPosModelImpl.getOriginalJobPosCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CODE, args);
		}
	}

	/**
	 * Creates a new job pos with the primary key. Does not add the job pos to the database.
	 *
	 * @param jobPosId the primary key for the new job pos
	 * @return the new job pos
	 */
	@Override
	public JobPos create(long jobPosId) {
		JobPos jobPos = new JobPosImpl();

		jobPos.setNew(true);
		jobPos.setPrimaryKey(jobPosId);

		String uuid = PortalUUIDUtil.generate();

		jobPos.setUuid(uuid);

		jobPos.setCompanyId(companyProvider.getCompanyId());

		return jobPos;
	}

	/**
	 * Removes the job pos with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jobPosId the primary key of the job pos
	 * @return the job pos that was removed
	 * @throws NoSuchJobPosException if a job pos with the primary key could not be found
	 */
	@Override
	public JobPos remove(long jobPosId) throws NoSuchJobPosException {
		return remove((Serializable)jobPosId);
	}

	/**
	 * Removes the job pos with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the job pos
	 * @return the job pos that was removed
	 * @throws NoSuchJobPosException if a job pos with the primary key could not be found
	 */
	@Override
	public JobPos remove(Serializable primaryKey) throws NoSuchJobPosException {
		Session session = null;

		try {
			session = openSession();

			JobPos jobPos = (JobPos)session.get(JobPosImpl.class, primaryKey);

			if (jobPos == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJobPosException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(jobPos);
		}
		catch (NoSuchJobPosException nsee) {
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
	protected JobPos removeImpl(JobPos jobPos) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jobPos)) {
				jobPos = (JobPos)session.get(JobPosImpl.class,
						jobPos.getPrimaryKeyObj());
			}

			if (jobPos != null) {
				session.delete(jobPos);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (jobPos != null) {
			clearCache(jobPos);
		}

		return jobPos;
	}

	@Override
	public JobPos updateImpl(JobPos jobPos) {
		boolean isNew = jobPos.isNew();

		if (!(jobPos instanceof JobPosModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(jobPos.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(jobPos);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in jobPos proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom JobPos implementation " +
				jobPos.getClass());
		}

		JobPosModelImpl jobPosModelImpl = (JobPosModelImpl)jobPos;

		if (Validator.isNull(jobPos.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			jobPos.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (jobPos.getCreateDate() == null)) {
			if (serviceContext == null) {
				jobPos.setCreateDate(now);
			}
			else {
				jobPos.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!jobPosModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				jobPos.setModifiedDate(now);
			}
			else {
				jobPos.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (jobPos.isNew()) {
				session.save(jobPos);

				jobPos.setNew(false);
			}
			else {
				jobPos = (JobPos)session.merge(jobPos);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!JobPosModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { jobPosModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					jobPosModelImpl.getUuid(), jobPosModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((jobPosModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { jobPosModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { jobPosModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((jobPosModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobPosModelImpl.getOriginalUuid(),
						jobPosModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						jobPosModelImpl.getUuid(),
						jobPosModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(JobPosModelImpl.ENTITY_CACHE_ENABLED,
			JobPosImpl.class, jobPos.getPrimaryKey(), jobPos, false);

		clearUniqueFindersCache(jobPosModelImpl, false);
		cacheUniqueFindersCache(jobPosModelImpl);

		jobPos.resetOriginalValues();

		return jobPos;
	}

	/**
	 * Returns the job pos with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the job pos
	 * @return the job pos
	 * @throws NoSuchJobPosException if a job pos with the primary key could not be found
	 */
	@Override
	public JobPos findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJobPosException {
		JobPos jobPos = fetchByPrimaryKey(primaryKey);

		if (jobPos == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJobPosException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return jobPos;
	}

	/**
	 * Returns the job pos with the primary key or throws a {@link NoSuchJobPosException} if it could not be found.
	 *
	 * @param jobPosId the primary key of the job pos
	 * @return the job pos
	 * @throws NoSuchJobPosException if a job pos with the primary key could not be found
	 */
	@Override
	public JobPos findByPrimaryKey(long jobPosId) throws NoSuchJobPosException {
		return findByPrimaryKey((Serializable)jobPosId);
	}

	/**
	 * Returns the job pos with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the job pos
	 * @return the job pos, or <code>null</code> if a job pos with the primary key could not be found
	 */
	@Override
	public JobPos fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(JobPosModelImpl.ENTITY_CACHE_ENABLED,
				JobPosImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		JobPos jobPos = (JobPos)serializable;

		if (jobPos == null) {
			Session session = null;

			try {
				session = openSession();

				jobPos = (JobPos)session.get(JobPosImpl.class, primaryKey);

				if (jobPos != null) {
					cacheResult(jobPos);
				}
				else {
					entityCache.putResult(JobPosModelImpl.ENTITY_CACHE_ENABLED,
						JobPosImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(JobPosModelImpl.ENTITY_CACHE_ENABLED,
					JobPosImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return jobPos;
	}

	/**
	 * Returns the job pos with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jobPosId the primary key of the job pos
	 * @return the job pos, or <code>null</code> if a job pos with the primary key could not be found
	 */
	@Override
	public JobPos fetchByPrimaryKey(long jobPosId) {
		return fetchByPrimaryKey((Serializable)jobPosId);
	}

	@Override
	public Map<Serializable, JobPos> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, JobPos> map = new HashMap<Serializable, JobPos>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			JobPos jobPos = fetchByPrimaryKey(primaryKey);

			if (jobPos != null) {
				map.put(primaryKey, jobPos);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(JobPosModelImpl.ENTITY_CACHE_ENABLED,
					JobPosImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (JobPos)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_JOBPOS_WHERE_PKS_IN);

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

			for (JobPos jobPos : (List<JobPos>)q.list()) {
				map.put(jobPos.getPrimaryKeyObj(), jobPos);

				cacheResult(jobPos);

				uncachedPrimaryKeys.remove(jobPos.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(JobPosModelImpl.ENTITY_CACHE_ENABLED,
					JobPosImpl.class, primaryKey, nullModel);
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
	 * Returns all the job poses.
	 *
	 * @return the job poses
	 */
	@Override
	public List<JobPos> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the job poses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of job poses
	 * @param end the upper bound of the range of job poses (not inclusive)
	 * @return the range of job poses
	 */
	@Override
	public List<JobPos> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the job poses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of job poses
	 * @param end the upper bound of the range of job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of job poses
	 */
	@Override
	public List<JobPos> findAll(int start, int end,
		OrderByComparator<JobPos> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the job poses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of job poses
	 * @param end the upper bound of the range of job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of job poses
	 */
	@Override
	public List<JobPos> findAll(int start, int end,
		OrderByComparator<JobPos> orderByComparator, boolean retrieveFromCache) {
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

		List<JobPos> list = null;

		if (retrieveFromCache) {
			list = (List<JobPos>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_JOBPOS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JOBPOS;

				if (pagination) {
					sql = sql.concat(JobPosModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<JobPos>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JobPos>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the job poses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (JobPos jobPos : findAll()) {
			remove(jobPos);
		}
	}

	/**
	 * Returns the number of job poses.
	 *
	 * @return the number of job poses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JOBPOS);

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
		return JobPosModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the job pos persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(JobPosImpl.class.getName());
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
	private static final String _SQL_SELECT_JOBPOS = "SELECT jobPos FROM JobPos jobPos";
	private static final String _SQL_SELECT_JOBPOS_WHERE_PKS_IN = "SELECT jobPos FROM JobPos jobPos WHERE jobPosId IN (";
	private static final String _SQL_SELECT_JOBPOS_WHERE = "SELECT jobPos FROM JobPos jobPos WHERE ";
	private static final String _SQL_COUNT_JOBPOS = "SELECT COUNT(jobPos) FROM JobPos jobPos";
	private static final String _SQL_COUNT_JOBPOS_WHERE = "SELECT COUNT(jobPos) FROM JobPos jobPos WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jobPos.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JobPos exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No JobPos exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(JobPosPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}