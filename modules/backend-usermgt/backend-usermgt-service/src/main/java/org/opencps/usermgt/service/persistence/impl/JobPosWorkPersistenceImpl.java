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

import org.opencps.usermgt.exception.NoSuchJobPosWorkException;
import org.opencps.usermgt.model.JobPosWork;
import org.opencps.usermgt.model.impl.JobPosWorkImpl;
import org.opencps.usermgt.model.impl.JobPosWorkModelImpl;
import org.opencps.usermgt.service.persistence.JobPosWorkPersistence;

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
 * The persistence implementation for the job pos work service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see JobPosWorkPersistence
 * @see org.opencps.usermgt.service.persistence.JobPosWorkUtil
 * @generated
 */
@ProviderType
public class JobPosWorkPersistenceImpl extends BasePersistenceImpl<JobPosWork>
	implements JobPosWorkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JobPosWorkUtil} to access the job pos work persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JobPosWorkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, JobPosWorkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, JobPosWorkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, JobPosWorkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, JobPosWorkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			JobPosWorkModelImpl.UUID_COLUMN_BITMASK |
			JobPosWorkModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the job pos works where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching job pos works
	 */
	@Override
	public List<JobPosWork> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the job pos works where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of job pos works
	 * @param end the upper bound of the range of job pos works (not inclusive)
	 * @return the range of matching job pos works
	 */
	@Override
	public List<JobPosWork> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the job pos works where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of job pos works
	 * @param end the upper bound of the range of job pos works (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching job pos works
	 */
	@Override
	public List<JobPosWork> findByUuid(String uuid, int start, int end,
		OrderByComparator<JobPosWork> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the job pos works where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of job pos works
	 * @param end the upper bound of the range of job pos works (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching job pos works
	 */
	@Override
	public List<JobPosWork> findByUuid(String uuid, int start, int end,
		OrderByComparator<JobPosWork> orderByComparator,
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

		List<JobPosWork> list = null;

		if (retrieveFromCache) {
			list = (List<JobPosWork>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (JobPosWork jobPosWork : list) {
					if (!Objects.equals(uuid, jobPosWork.getUuid())) {
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

			query.append(_SQL_SELECT_JOBPOSWORK_WHERE);

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
				query.append(JobPosWorkModelImpl.ORDER_BY_JPQL);
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
					list = (List<JobPosWork>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JobPosWork>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first job pos work in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job pos work
	 * @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork findByUuid_First(String uuid,
		OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException {
		JobPosWork jobPosWork = fetchByUuid_First(uuid, orderByComparator);

		if (jobPosWork != null) {
			return jobPosWork;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchJobPosWorkException(msg.toString());
	}

	/**
	 * Returns the first job pos work in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job pos work, or <code>null</code> if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork fetchByUuid_First(String uuid,
		OrderByComparator<JobPosWork> orderByComparator) {
		List<JobPosWork> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job pos work in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job pos work
	 * @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork findByUuid_Last(String uuid,
		OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException {
		JobPosWork jobPosWork = fetchByUuid_Last(uuid, orderByComparator);

		if (jobPosWork != null) {
			return jobPosWork;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchJobPosWorkException(msg.toString());
	}

	/**
	 * Returns the last job pos work in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job pos work, or <code>null</code> if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork fetchByUuid_Last(String uuid,
		OrderByComparator<JobPosWork> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<JobPosWork> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the job pos works before and after the current job pos work in the ordered set where uuid = &#63;.
	 *
	 * @param jobPosWorkId the primary key of the current job pos work
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job pos work
	 * @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	 */
	@Override
	public JobPosWork[] findByUuid_PrevAndNext(long jobPosWorkId, String uuid,
		OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException {
		JobPosWork jobPosWork = findByPrimaryKey(jobPosWorkId);

		Session session = null;

		try {
			session = openSession();

			JobPosWork[] array = new JobPosWorkImpl[3];

			array[0] = getByUuid_PrevAndNext(session, jobPosWork, uuid,
					orderByComparator, true);

			array[1] = jobPosWork;

			array[2] = getByUuid_PrevAndNext(session, jobPosWork, uuid,
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

	protected JobPosWork getByUuid_PrevAndNext(Session session,
		JobPosWork jobPosWork, String uuid,
		OrderByComparator<JobPosWork> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOBPOSWORK_WHERE);

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
			query.append(JobPosWorkModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(jobPosWork);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JobPosWork> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the job pos works where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (JobPosWork jobPosWork : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(jobPosWork);
		}
	}

	/**
	 * Returns the number of job pos works where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching job pos works
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOBPOSWORK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "jobPosWork.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "jobPosWork.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(jobPosWork.uuid IS NULL OR jobPosWork.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, JobPosWorkImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			JobPosWorkModelImpl.UUID_COLUMN_BITMASK |
			JobPosWorkModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the job pos work where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchJobPosWorkException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching job pos work
	 * @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork findByUUID_G(String uuid, long groupId)
		throws NoSuchJobPosWorkException {
		JobPosWork jobPosWork = fetchByUUID_G(uuid, groupId);

		if (jobPosWork == null) {
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

			throw new NoSuchJobPosWorkException(msg.toString());
		}

		return jobPosWork;
	}

	/**
	 * Returns the job pos work where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching job pos work, or <code>null</code> if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the job pos work where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching job pos work, or <code>null</code> if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof JobPosWork) {
			JobPosWork jobPosWork = (JobPosWork)result;

			if (!Objects.equals(uuid, jobPosWork.getUuid()) ||
					(groupId != jobPosWork.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_JOBPOSWORK_WHERE);

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

				List<JobPosWork> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					JobPosWork jobPosWork = list.get(0);

					result = jobPosWork;

					cacheResult(jobPosWork);
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
			return (JobPosWork)result;
		}
	}

	/**
	 * Removes the job pos work where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the job pos work that was removed
	 */
	@Override
	public JobPosWork removeByUUID_G(String uuid, long groupId)
		throws NoSuchJobPosWorkException {
		JobPosWork jobPosWork = findByUUID_G(uuid, groupId);

		return remove(jobPosWork);
	}

	/**
	 * Returns the number of job pos works where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching job pos works
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOBPOSWORK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "jobPosWork.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "jobPosWork.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(jobPosWork.uuid IS NULL OR jobPosWork.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "jobPosWork.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, JobPosWorkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, JobPosWorkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			JobPosWorkModelImpl.UUID_COLUMN_BITMASK |
			JobPosWorkModelImpl.COMPANYID_COLUMN_BITMASK |
			JobPosWorkModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the job pos works where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching job pos works
	 */
	@Override
	public List<JobPosWork> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the job pos works where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of job pos works
	 * @param end the upper bound of the range of job pos works (not inclusive)
	 * @return the range of matching job pos works
	 */
	@Override
	public List<JobPosWork> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the job pos works where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of job pos works
	 * @param end the upper bound of the range of job pos works (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching job pos works
	 */
	@Override
	public List<JobPosWork> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<JobPosWork> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the job pos works where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of job pos works
	 * @param end the upper bound of the range of job pos works (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching job pos works
	 */
	@Override
	public List<JobPosWork> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<JobPosWork> orderByComparator,
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

		List<JobPosWork> list = null;

		if (retrieveFromCache) {
			list = (List<JobPosWork>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (JobPosWork jobPosWork : list) {
					if (!Objects.equals(uuid, jobPosWork.getUuid()) ||
							(companyId != jobPosWork.getCompanyId())) {
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

			query.append(_SQL_SELECT_JOBPOSWORK_WHERE);

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
				query.append(JobPosWorkModelImpl.ORDER_BY_JPQL);
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
					list = (List<JobPosWork>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JobPosWork>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first job pos work in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job pos work
	 * @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException {
		JobPosWork jobPosWork = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (jobPosWork != null) {
			return jobPosWork;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchJobPosWorkException(msg.toString());
	}

	/**
	 * Returns the first job pos work in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job pos work, or <code>null</code> if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<JobPosWork> orderByComparator) {
		List<JobPosWork> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job pos work in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job pos work
	 * @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException {
		JobPosWork jobPosWork = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (jobPosWork != null) {
			return jobPosWork;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchJobPosWorkException(msg.toString());
	}

	/**
	 * Returns the last job pos work in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job pos work, or <code>null</code> if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<JobPosWork> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<JobPosWork> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the job pos works before and after the current job pos work in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param jobPosWorkId the primary key of the current job pos work
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job pos work
	 * @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	 */
	@Override
	public JobPosWork[] findByUuid_C_PrevAndNext(long jobPosWorkId,
		String uuid, long companyId,
		OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException {
		JobPosWork jobPosWork = findByPrimaryKey(jobPosWorkId);

		Session session = null;

		try {
			session = openSession();

			JobPosWork[] array = new JobPosWorkImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, jobPosWork, uuid,
					companyId, orderByComparator, true);

			array[1] = jobPosWork;

			array[2] = getByUuid_C_PrevAndNext(session, jobPosWork, uuid,
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

	protected JobPosWork getByUuid_C_PrevAndNext(Session session,
		JobPosWork jobPosWork, String uuid, long companyId,
		OrderByComparator<JobPosWork> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOBPOSWORK_WHERE);

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
			query.append(JobPosWorkModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(jobPosWork);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JobPosWork> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the job pos works where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (JobPosWork jobPosWork : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jobPosWork);
		}
	}

	/**
	 * Returns the number of job pos works where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching job pos works
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOBPOSWORK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "jobPosWork.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "jobPosWork.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(jobPosWork.uuid IS NULL OR jobPosWork.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "jobPosWork.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_CHECKLISTCAT = new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, JobPosWorkImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_ChecklistCat",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			JobPosWorkModelImpl.GROUPID_COLUMN_BITMASK |
			JobPosWorkModelImpl.JOBPOSTID_COLUMN_BITMASK |
			JobPosWorkModelImpl.CHECKLISTCAT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_CHECKLISTCAT = new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_ChecklistCat",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the job pos work where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63; or throws a {@link NoSuchJobPosWorkException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param checklistCat the checklist cat
	 * @return the matching job pos work
	 * @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork findByF_ChecklistCat(long groupId, long jobPostId,
		String checklistCat) throws NoSuchJobPosWorkException {
		JobPosWork jobPosWork = fetchByF_ChecklistCat(groupId, jobPostId,
				checklistCat);

		if (jobPosWork == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", jobPostId=");
			msg.append(jobPostId);

			msg.append(", checklistCat=");
			msg.append(checklistCat);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchJobPosWorkException(msg.toString());
		}

		return jobPosWork;
	}

	/**
	 * Returns the job pos work where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param checklistCat the checklist cat
	 * @return the matching job pos work, or <code>null</code> if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork fetchByF_ChecklistCat(long groupId, long jobPostId,
		String checklistCat) {
		return fetchByF_ChecklistCat(groupId, jobPostId, checklistCat, true);
	}

	/**
	 * Returns the job pos work where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param checklistCat the checklist cat
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching job pos work, or <code>null</code> if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork fetchByF_ChecklistCat(long groupId, long jobPostId,
		String checklistCat, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, jobPostId, checklistCat };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_CHECKLISTCAT,
					finderArgs, this);
		}

		if (result instanceof JobPosWork) {
			JobPosWork jobPosWork = (JobPosWork)result;

			if ((groupId != jobPosWork.getGroupId()) ||
					(jobPostId != jobPosWork.getJobPostId()) ||
					!Objects.equals(checklistCat, jobPosWork.getChecklistCat())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_JOBPOSWORK_WHERE);

			query.append(_FINDER_COLUMN_F_CHECKLISTCAT_GROUPID_2);

			query.append(_FINDER_COLUMN_F_CHECKLISTCAT_JOBPOSTID_2);

			boolean bindChecklistCat = false;

			if (checklistCat == null) {
				query.append(_FINDER_COLUMN_F_CHECKLISTCAT_CHECKLISTCAT_1);
			}
			else if (checklistCat.equals("")) {
				query.append(_FINDER_COLUMN_F_CHECKLISTCAT_CHECKLISTCAT_3);
			}
			else {
				bindChecklistCat = true;

				query.append(_FINDER_COLUMN_F_CHECKLISTCAT_CHECKLISTCAT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(jobPostId);

				if (bindChecklistCat) {
					qPos.add(checklistCat);
				}

				List<JobPosWork> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_CHECKLISTCAT,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"JobPosWorkPersistenceImpl.fetchByF_ChecklistCat(long, long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					JobPosWork jobPosWork = list.get(0);

					result = jobPosWork;

					cacheResult(jobPosWork);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CHECKLISTCAT,
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
			return (JobPosWork)result;
		}
	}

	/**
	 * Removes the job pos work where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param checklistCat the checklist cat
	 * @return the job pos work that was removed
	 */
	@Override
	public JobPosWork removeByF_ChecklistCat(long groupId, long jobPostId,
		String checklistCat) throws NoSuchJobPosWorkException {
		JobPosWork jobPosWork = findByF_ChecklistCat(groupId, jobPostId,
				checklistCat);

		return remove(jobPosWork);
	}

	/**
	 * Returns the number of job pos works where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param checklistCat the checklist cat
	 * @return the number of matching job pos works
	 */
	@Override
	public int countByF_ChecklistCat(long groupId, long jobPostId,
		String checklistCat) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_CHECKLISTCAT;

		Object[] finderArgs = new Object[] { groupId, jobPostId, checklistCat };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JOBPOSWORK_WHERE);

			query.append(_FINDER_COLUMN_F_CHECKLISTCAT_GROUPID_2);

			query.append(_FINDER_COLUMN_F_CHECKLISTCAT_JOBPOSTID_2);

			boolean bindChecklistCat = false;

			if (checklistCat == null) {
				query.append(_FINDER_COLUMN_F_CHECKLISTCAT_CHECKLISTCAT_1);
			}
			else if (checklistCat.equals("")) {
				query.append(_FINDER_COLUMN_F_CHECKLISTCAT_CHECKLISTCAT_3);
			}
			else {
				bindChecklistCat = true;

				query.append(_FINDER_COLUMN_F_CHECKLISTCAT_CHECKLISTCAT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(jobPostId);

				if (bindChecklistCat) {
					qPos.add(checklistCat);
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

	private static final String _FINDER_COLUMN_F_CHECKLISTCAT_GROUPID_2 = "jobPosWork.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_CHECKLISTCAT_JOBPOSTID_2 = "jobPosWork.jobPostId = ? AND ";
	private static final String _FINDER_COLUMN_F_CHECKLISTCAT_CHECKLISTCAT_1 = "jobPosWork.checklistCat IS NULL";
	private static final String _FINDER_COLUMN_F_CHECKLISTCAT_CHECKLISTCAT_2 = "jobPosWork.checklistCat = ?";
	private static final String _FINDER_COLUMN_F_CHECKLISTCAT_CHECKLISTCAT_3 = "(jobPosWork.checklistCat IS NULL OR jobPosWork.checklistCat = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_JOBPOSTID =
		new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, JobPosWorkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_JobPostId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_JOBPOSTID =
		new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, JobPosWorkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_JobPostId",
			new String[] { Long.class.getName(), Long.class.getName() },
			JobPosWorkModelImpl.GROUPID_COLUMN_BITMASK |
			JobPosWorkModelImpl.JOBPOSTID_COLUMN_BITMASK |
			JobPosWorkModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_JOBPOSTID = new FinderPath(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_JobPostId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the job pos works where groupId = &#63; and jobPostId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @return the matching job pos works
	 */
	@Override
	public List<JobPosWork> findByF_JobPostId(long groupId, long jobPostId) {
		return findByF_JobPostId(groupId, jobPostId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the job pos works where groupId = &#63; and jobPostId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param start the lower bound of the range of job pos works
	 * @param end the upper bound of the range of job pos works (not inclusive)
	 * @return the range of matching job pos works
	 */
	@Override
	public List<JobPosWork> findByF_JobPostId(long groupId, long jobPostId,
		int start, int end) {
		return findByF_JobPostId(groupId, jobPostId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the job pos works where groupId = &#63; and jobPostId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param start the lower bound of the range of job pos works
	 * @param end the upper bound of the range of job pos works (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching job pos works
	 */
	@Override
	public List<JobPosWork> findByF_JobPostId(long groupId, long jobPostId,
		int start, int end, OrderByComparator<JobPosWork> orderByComparator) {
		return findByF_JobPostId(groupId, jobPostId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the job pos works where groupId = &#63; and jobPostId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param start the lower bound of the range of job pos works
	 * @param end the upper bound of the range of job pos works (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching job pos works
	 */
	@Override
	public List<JobPosWork> findByF_JobPostId(long groupId, long jobPostId,
		int start, int end, OrderByComparator<JobPosWork> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_JOBPOSTID;
			finderArgs = new Object[] { groupId, jobPostId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_JOBPOSTID;
			finderArgs = new Object[] {
					groupId, jobPostId,
					
					start, end, orderByComparator
				};
		}

		List<JobPosWork> list = null;

		if (retrieveFromCache) {
			list = (List<JobPosWork>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (JobPosWork jobPosWork : list) {
					if ((groupId != jobPosWork.getGroupId()) ||
							(jobPostId != jobPosWork.getJobPostId())) {
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

			query.append(_SQL_SELECT_JOBPOSWORK_WHERE);

			query.append(_FINDER_COLUMN_F_JOBPOSTID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_JOBPOSTID_JOBPOSTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobPosWorkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(jobPostId);

				if (!pagination) {
					list = (List<JobPosWork>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JobPosWork>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first job pos work in the ordered set where groupId = &#63; and jobPostId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job pos work
	 * @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork findByF_JobPostId_First(long groupId, long jobPostId,
		OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException {
		JobPosWork jobPosWork = fetchByF_JobPostId_First(groupId, jobPostId,
				orderByComparator);

		if (jobPosWork != null) {
			return jobPosWork;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", jobPostId=");
		msg.append(jobPostId);

		msg.append("}");

		throw new NoSuchJobPosWorkException(msg.toString());
	}

	/**
	 * Returns the first job pos work in the ordered set where groupId = &#63; and jobPostId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job pos work, or <code>null</code> if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork fetchByF_JobPostId_First(long groupId, long jobPostId,
		OrderByComparator<JobPosWork> orderByComparator) {
		List<JobPosWork> list = findByF_JobPostId(groupId, jobPostId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job pos work in the ordered set where groupId = &#63; and jobPostId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job pos work
	 * @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork findByF_JobPostId_Last(long groupId, long jobPostId,
		OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException {
		JobPosWork jobPosWork = fetchByF_JobPostId_Last(groupId, jobPostId,
				orderByComparator);

		if (jobPosWork != null) {
			return jobPosWork;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", jobPostId=");
		msg.append(jobPostId);

		msg.append("}");

		throw new NoSuchJobPosWorkException(msg.toString());
	}

	/**
	 * Returns the last job pos work in the ordered set where groupId = &#63; and jobPostId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job pos work, or <code>null</code> if a matching job pos work could not be found
	 */
	@Override
	public JobPosWork fetchByF_JobPostId_Last(long groupId, long jobPostId,
		OrderByComparator<JobPosWork> orderByComparator) {
		int count = countByF_JobPostId(groupId, jobPostId);

		if (count == 0) {
			return null;
		}

		List<JobPosWork> list = findByF_JobPostId(groupId, jobPostId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the job pos works before and after the current job pos work in the ordered set where groupId = &#63; and jobPostId = &#63;.
	 *
	 * @param jobPosWorkId the primary key of the current job pos work
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job pos work
	 * @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	 */
	@Override
	public JobPosWork[] findByF_JobPostId_PrevAndNext(long jobPosWorkId,
		long groupId, long jobPostId,
		OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException {
		JobPosWork jobPosWork = findByPrimaryKey(jobPosWorkId);

		Session session = null;

		try {
			session = openSession();

			JobPosWork[] array = new JobPosWorkImpl[3];

			array[0] = getByF_JobPostId_PrevAndNext(session, jobPosWork,
					groupId, jobPostId, orderByComparator, true);

			array[1] = jobPosWork;

			array[2] = getByF_JobPostId_PrevAndNext(session, jobPosWork,
					groupId, jobPostId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JobPosWork getByF_JobPostId_PrevAndNext(Session session,
		JobPosWork jobPosWork, long groupId, long jobPostId,
		OrderByComparator<JobPosWork> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOBPOSWORK_WHERE);

		query.append(_FINDER_COLUMN_F_JOBPOSTID_GROUPID_2);

		query.append(_FINDER_COLUMN_F_JOBPOSTID_JOBPOSTID_2);

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
			query.append(JobPosWorkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(jobPostId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jobPosWork);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JobPosWork> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the job pos works where groupId = &#63; and jobPostId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 */
	@Override
	public void removeByF_JobPostId(long groupId, long jobPostId) {
		for (JobPosWork jobPosWork : findByF_JobPostId(groupId, jobPostId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jobPosWork);
		}
	}

	/**
	 * Returns the number of job pos works where groupId = &#63; and jobPostId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @return the number of matching job pos works
	 */
	@Override
	public int countByF_JobPostId(long groupId, long jobPostId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_JOBPOSTID;

		Object[] finderArgs = new Object[] { groupId, jobPostId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOBPOSWORK_WHERE);

			query.append(_FINDER_COLUMN_F_JOBPOSTID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_JOBPOSTID_JOBPOSTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(jobPostId);

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

	private static final String _FINDER_COLUMN_F_JOBPOSTID_GROUPID_2 = "jobPosWork.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_JOBPOSTID_JOBPOSTID_2 = "jobPosWork.jobPostId = ?";

	public JobPosWorkPersistenceImpl() {
		setModelClass(JobPosWork.class);

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
	 * Caches the job pos work in the entity cache if it is enabled.
	 *
	 * @param jobPosWork the job pos work
	 */
	@Override
	public void cacheResult(JobPosWork jobPosWork) {
		entityCache.putResult(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkImpl.class, jobPosWork.getPrimaryKey(), jobPosWork);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { jobPosWork.getUuid(), jobPosWork.getGroupId() },
			jobPosWork);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CHECKLISTCAT,
			new Object[] {
				jobPosWork.getGroupId(), jobPosWork.getJobPostId(),
				jobPosWork.getChecklistCat()
			}, jobPosWork);

		jobPosWork.resetOriginalValues();
	}

	/**
	 * Caches the job pos works in the entity cache if it is enabled.
	 *
	 * @param jobPosWorks the job pos works
	 */
	@Override
	public void cacheResult(List<JobPosWork> jobPosWorks) {
		for (JobPosWork jobPosWork : jobPosWorks) {
			if (entityCache.getResult(
						JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
						JobPosWorkImpl.class, jobPosWork.getPrimaryKey()) == null) {
				cacheResult(jobPosWork);
			}
			else {
				jobPosWork.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all job pos works.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(JobPosWorkImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the job pos work.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JobPosWork jobPosWork) {
		entityCache.removeResult(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkImpl.class, jobPosWork.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((JobPosWorkModelImpl)jobPosWork, true);
	}

	@Override
	public void clearCache(List<JobPosWork> jobPosWorks) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (JobPosWork jobPosWork : jobPosWorks) {
			entityCache.removeResult(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
				JobPosWorkImpl.class, jobPosWork.getPrimaryKey());

			clearUniqueFindersCache((JobPosWorkModelImpl)jobPosWork, true);
		}
	}

	protected void cacheUniqueFindersCache(
		JobPosWorkModelImpl jobPosWorkModelImpl) {
		Object[] args = new Object[] {
				jobPosWorkModelImpl.getUuid(), jobPosWorkModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			jobPosWorkModelImpl, false);

		args = new Object[] {
				jobPosWorkModelImpl.getGroupId(),
				jobPosWorkModelImpl.getJobPostId(),
				jobPosWorkModelImpl.getChecklistCat()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_CHECKLISTCAT, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CHECKLISTCAT, args,
			jobPosWorkModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		JobPosWorkModelImpl jobPosWorkModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					jobPosWorkModelImpl.getUuid(),
					jobPosWorkModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((jobPosWorkModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					jobPosWorkModelImpl.getOriginalUuid(),
					jobPosWorkModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					jobPosWorkModelImpl.getGroupId(),
					jobPosWorkModelImpl.getJobPostId(),
					jobPosWorkModelImpl.getChecklistCat()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CHECKLISTCAT, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CHECKLISTCAT, args);
		}

		if ((jobPosWorkModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_CHECKLISTCAT.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					jobPosWorkModelImpl.getOriginalGroupId(),
					jobPosWorkModelImpl.getOriginalJobPostId(),
					jobPosWorkModelImpl.getOriginalChecklistCat()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CHECKLISTCAT, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CHECKLISTCAT, args);
		}
	}

	/**
	 * Creates a new job pos work with the primary key. Does not add the job pos work to the database.
	 *
	 * @param jobPosWorkId the primary key for the new job pos work
	 * @return the new job pos work
	 */
	@Override
	public JobPosWork create(long jobPosWorkId) {
		JobPosWork jobPosWork = new JobPosWorkImpl();

		jobPosWork.setNew(true);
		jobPosWork.setPrimaryKey(jobPosWorkId);

		String uuid = PortalUUIDUtil.generate();

		jobPosWork.setUuid(uuid);

		jobPosWork.setCompanyId(companyProvider.getCompanyId());

		return jobPosWork;
	}

	/**
	 * Removes the job pos work with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jobPosWorkId the primary key of the job pos work
	 * @return the job pos work that was removed
	 * @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	 */
	@Override
	public JobPosWork remove(long jobPosWorkId)
		throws NoSuchJobPosWorkException {
		return remove((Serializable)jobPosWorkId);
	}

	/**
	 * Removes the job pos work with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the job pos work
	 * @return the job pos work that was removed
	 * @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	 */
	@Override
	public JobPosWork remove(Serializable primaryKey)
		throws NoSuchJobPosWorkException {
		Session session = null;

		try {
			session = openSession();

			JobPosWork jobPosWork = (JobPosWork)session.get(JobPosWorkImpl.class,
					primaryKey);

			if (jobPosWork == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJobPosWorkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(jobPosWork);
		}
		catch (NoSuchJobPosWorkException nsee) {
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
	protected JobPosWork removeImpl(JobPosWork jobPosWork) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jobPosWork)) {
				jobPosWork = (JobPosWork)session.get(JobPosWorkImpl.class,
						jobPosWork.getPrimaryKeyObj());
			}

			if (jobPosWork != null) {
				session.delete(jobPosWork);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (jobPosWork != null) {
			clearCache(jobPosWork);
		}

		return jobPosWork;
	}

	@Override
	public JobPosWork updateImpl(JobPosWork jobPosWork) {
		boolean isNew = jobPosWork.isNew();

		if (!(jobPosWork instanceof JobPosWorkModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(jobPosWork.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(jobPosWork);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in jobPosWork proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom JobPosWork implementation " +
				jobPosWork.getClass());
		}

		JobPosWorkModelImpl jobPosWorkModelImpl = (JobPosWorkModelImpl)jobPosWork;

		if (Validator.isNull(jobPosWork.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			jobPosWork.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (jobPosWork.getCreateDate() == null)) {
			if (serviceContext == null) {
				jobPosWork.setCreateDate(now);
			}
			else {
				jobPosWork.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!jobPosWorkModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				jobPosWork.setModifiedDate(now);
			}
			else {
				jobPosWork.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (jobPosWork.isNew()) {
				session.save(jobPosWork);

				jobPosWork.setNew(false);
			}
			else {
				jobPosWork = (JobPosWork)session.merge(jobPosWork);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!JobPosWorkModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { jobPosWorkModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					jobPosWorkModelImpl.getUuid(),
					jobPosWorkModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					jobPosWorkModelImpl.getGroupId(),
					jobPosWorkModelImpl.getJobPostId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_JOBPOSTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_JOBPOSTID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((jobPosWorkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobPosWorkModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { jobPosWorkModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((jobPosWorkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobPosWorkModelImpl.getOriginalUuid(),
						jobPosWorkModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						jobPosWorkModelImpl.getUuid(),
						jobPosWorkModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((jobPosWorkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_JOBPOSTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobPosWorkModelImpl.getOriginalGroupId(),
						jobPosWorkModelImpl.getOriginalJobPostId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_JOBPOSTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_JOBPOSTID,
					args);

				args = new Object[] {
						jobPosWorkModelImpl.getGroupId(),
						jobPosWorkModelImpl.getJobPostId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_JOBPOSTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_JOBPOSTID,
					args);
			}
		}

		entityCache.putResult(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
			JobPosWorkImpl.class, jobPosWork.getPrimaryKey(), jobPosWork, false);

		clearUniqueFindersCache(jobPosWorkModelImpl, false);
		cacheUniqueFindersCache(jobPosWorkModelImpl);

		jobPosWork.resetOriginalValues();

		return jobPosWork;
	}

	/**
	 * Returns the job pos work with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the job pos work
	 * @return the job pos work
	 * @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	 */
	@Override
	public JobPosWork findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJobPosWorkException {
		JobPosWork jobPosWork = fetchByPrimaryKey(primaryKey);

		if (jobPosWork == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJobPosWorkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return jobPosWork;
	}

	/**
	 * Returns the job pos work with the primary key or throws a {@link NoSuchJobPosWorkException} if it could not be found.
	 *
	 * @param jobPosWorkId the primary key of the job pos work
	 * @return the job pos work
	 * @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	 */
	@Override
	public JobPosWork findByPrimaryKey(long jobPosWorkId)
		throws NoSuchJobPosWorkException {
		return findByPrimaryKey((Serializable)jobPosWorkId);
	}

	/**
	 * Returns the job pos work with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the job pos work
	 * @return the job pos work, or <code>null</code> if a job pos work with the primary key could not be found
	 */
	@Override
	public JobPosWork fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
				JobPosWorkImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		JobPosWork jobPosWork = (JobPosWork)serializable;

		if (jobPosWork == null) {
			Session session = null;

			try {
				session = openSession();

				jobPosWork = (JobPosWork)session.get(JobPosWorkImpl.class,
						primaryKey);

				if (jobPosWork != null) {
					cacheResult(jobPosWork);
				}
				else {
					entityCache.putResult(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
						JobPosWorkImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
					JobPosWorkImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return jobPosWork;
	}

	/**
	 * Returns the job pos work with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jobPosWorkId the primary key of the job pos work
	 * @return the job pos work, or <code>null</code> if a job pos work with the primary key could not be found
	 */
	@Override
	public JobPosWork fetchByPrimaryKey(long jobPosWorkId) {
		return fetchByPrimaryKey((Serializable)jobPosWorkId);
	}

	@Override
	public Map<Serializable, JobPosWork> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, JobPosWork> map = new HashMap<Serializable, JobPosWork>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			JobPosWork jobPosWork = fetchByPrimaryKey(primaryKey);

			if (jobPosWork != null) {
				map.put(primaryKey, jobPosWork);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
					JobPosWorkImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (JobPosWork)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_JOBPOSWORK_WHERE_PKS_IN);

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

			for (JobPosWork jobPosWork : (List<JobPosWork>)q.list()) {
				map.put(jobPosWork.getPrimaryKeyObj(), jobPosWork);

				cacheResult(jobPosWork);

				uncachedPrimaryKeys.remove(jobPosWork.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(JobPosWorkModelImpl.ENTITY_CACHE_ENABLED,
					JobPosWorkImpl.class, primaryKey, nullModel);
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
	 * Returns all the job pos works.
	 *
	 * @return the job pos works
	 */
	@Override
	public List<JobPosWork> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the job pos works.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of job pos works
	 * @param end the upper bound of the range of job pos works (not inclusive)
	 * @return the range of job pos works
	 */
	@Override
	public List<JobPosWork> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the job pos works.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of job pos works
	 * @param end the upper bound of the range of job pos works (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of job pos works
	 */
	@Override
	public List<JobPosWork> findAll(int start, int end,
		OrderByComparator<JobPosWork> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the job pos works.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of job pos works
	 * @param end the upper bound of the range of job pos works (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of job pos works
	 */
	@Override
	public List<JobPosWork> findAll(int start, int end,
		OrderByComparator<JobPosWork> orderByComparator,
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

		List<JobPosWork> list = null;

		if (retrieveFromCache) {
			list = (List<JobPosWork>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_JOBPOSWORK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JOBPOSWORK;

				if (pagination) {
					sql = sql.concat(JobPosWorkModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<JobPosWork>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JobPosWork>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the job pos works from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (JobPosWork jobPosWork : findAll()) {
			remove(jobPosWork);
		}
	}

	/**
	 * Returns the number of job pos works.
	 *
	 * @return the number of job pos works
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JOBPOSWORK);

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
		return JobPosWorkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the job pos work persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(JobPosWorkImpl.class.getName());
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
	private static final String _SQL_SELECT_JOBPOSWORK = "SELECT jobPosWork FROM JobPosWork jobPosWork";
	private static final String _SQL_SELECT_JOBPOSWORK_WHERE_PKS_IN = "SELECT jobPosWork FROM JobPosWork jobPosWork WHERE jobPosWorkId IN (";
	private static final String _SQL_SELECT_JOBPOSWORK_WHERE = "SELECT jobPosWork FROM JobPosWork jobPosWork WHERE ";
	private static final String _SQL_COUNT_JOBPOSWORK = "SELECT COUNT(jobPosWork) FROM JobPosWork jobPosWork";
	private static final String _SQL_COUNT_JOBPOSWORK_WHERE = "SELECT COUNT(jobPosWork) FROM JobPosWork jobPosWork WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jobPosWork.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JobPosWork exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No JobPosWork exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(JobPosWorkPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}