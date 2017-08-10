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

import org.opencps.backend.processmgt.exception.NoSuchServiceProcessException;
import org.opencps.backend.processmgt.model.ServiceProcess;
import org.opencps.backend.processmgt.model.impl.ServiceProcessImpl;
import org.opencps.backend.processmgt.model.impl.ServiceProcessModelImpl;
import org.opencps.backend.processmgt.service.persistence.ServiceProcessPersistence;

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
 * The persistence implementation for the service process service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see ServiceProcessPersistence
 * @see org.opencps.backend.processmgt.service.persistence.ServiceProcessUtil
 * @generated
 */
@ProviderType
public class ServiceProcessPersistenceImpl extends BasePersistenceImpl<ServiceProcess>
	implements ServiceProcessPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ServiceProcessUtil} to access the service process persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ServiceProcessImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ServiceProcessModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the service processes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching service processes
	 */
	@Override
	public List<ServiceProcess> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service processes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service processes
	 * @param end the upper bound of the range of service processes (not inclusive)
	 * @return the range of matching service processes
	 */
	@Override
	public List<ServiceProcess> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service processes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service processes
	 * @param end the upper bound of the range of service processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service processes
	 */
	@Override
	public List<ServiceProcess> findByUuid(String uuid, int start, int end,
		OrderByComparator<ServiceProcess> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service processes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service processes
	 * @param end the upper bound of the range of service processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service processes
	 */
	@Override
	public List<ServiceProcess> findByUuid(String uuid, int start, int end,
		OrderByComparator<ServiceProcess> orderByComparator,
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

		List<ServiceProcess> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceProcess>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceProcess serviceProcess : list) {
					if (!Objects.equals(uuid, serviceProcess.getUuid())) {
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

			query.append(_SQL_SELECT_SERVICEPROCESS_WHERE);

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
				query.append(ServiceProcessModelImpl.ORDER_BY_JPQL);
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
					list = (List<ServiceProcess>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceProcess>)QueryUtil.list(q,
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
	 * Returns the first service process in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service process
	 * @throws NoSuchServiceProcessException if a matching service process could not be found
	 */
	@Override
	public ServiceProcess findByUuid_First(String uuid,
		OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException {
		ServiceProcess serviceProcess = fetchByUuid_First(uuid,
				orderByComparator);

		if (serviceProcess != null) {
			return serviceProcess;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchServiceProcessException(msg.toString());
	}

	/**
	 * Returns the first service process in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service process, or <code>null</code> if a matching service process could not be found
	 */
	@Override
	public ServiceProcess fetchByUuid_First(String uuid,
		OrderByComparator<ServiceProcess> orderByComparator) {
		List<ServiceProcess> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service process in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service process
	 * @throws NoSuchServiceProcessException if a matching service process could not be found
	 */
	@Override
	public ServiceProcess findByUuid_Last(String uuid,
		OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException {
		ServiceProcess serviceProcess = fetchByUuid_Last(uuid, orderByComparator);

		if (serviceProcess != null) {
			return serviceProcess;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchServiceProcessException(msg.toString());
	}

	/**
	 * Returns the last service process in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service process, or <code>null</code> if a matching service process could not be found
	 */
	@Override
	public ServiceProcess fetchByUuid_Last(String uuid,
		OrderByComparator<ServiceProcess> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ServiceProcess> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service processes before and after the current service process in the ordered set where uuid = &#63;.
	 *
	 * @param serviceProcessId the primary key of the current service process
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service process
	 * @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	 */
	@Override
	public ServiceProcess[] findByUuid_PrevAndNext(long serviceProcessId,
		String uuid, OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException {
		ServiceProcess serviceProcess = findByPrimaryKey(serviceProcessId);

		Session session = null;

		try {
			session = openSession();

			ServiceProcess[] array = new ServiceProcessImpl[3];

			array[0] = getByUuid_PrevAndNext(session, serviceProcess, uuid,
					orderByComparator, true);

			array[1] = serviceProcess;

			array[2] = getByUuid_PrevAndNext(session, serviceProcess, uuid,
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

	protected ServiceProcess getByUuid_PrevAndNext(Session session,
		ServiceProcess serviceProcess, String uuid,
		OrderByComparator<ServiceProcess> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SERVICEPROCESS_WHERE);

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
			query.append(ServiceProcessModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(serviceProcess);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceProcess> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service processes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ServiceProcess serviceProcess : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceProcess);
		}
	}

	/**
	 * Returns the number of service processes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching service processes
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVICEPROCESS_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "serviceProcess.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "serviceProcess.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(serviceProcess.uuid IS NULL OR serviceProcess.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ServiceProcessModelImpl.UUID_COLUMN_BITMASK |
			ServiceProcessModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the service process where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchServiceProcessException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching service process
	 * @throws NoSuchServiceProcessException if a matching service process could not be found
	 */
	@Override
	public ServiceProcess findByUUID_G(String uuid, long groupId)
		throws NoSuchServiceProcessException {
		ServiceProcess serviceProcess = fetchByUUID_G(uuid, groupId);

		if (serviceProcess == null) {
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

			throw new NoSuchServiceProcessException(msg.toString());
		}

		return serviceProcess;
	}

	/**
	 * Returns the service process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching service process, or <code>null</code> if a matching service process could not be found
	 */
	@Override
	public ServiceProcess fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the service process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching service process, or <code>null</code> if a matching service process could not be found
	 */
	@Override
	public ServiceProcess fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ServiceProcess) {
			ServiceProcess serviceProcess = (ServiceProcess)result;

			if (!Objects.equals(uuid, serviceProcess.getUuid()) ||
					(groupId != serviceProcess.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SERVICEPROCESS_WHERE);

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

				List<ServiceProcess> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ServiceProcess serviceProcess = list.get(0);

					result = serviceProcess;

					cacheResult(serviceProcess);

					if ((serviceProcess.getUuid() == null) ||
							!serviceProcess.getUuid().equals(uuid) ||
							(serviceProcess.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, serviceProcess);
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
			return (ServiceProcess)result;
		}
	}

	/**
	 * Removes the service process where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the service process that was removed
	 */
	@Override
	public ServiceProcess removeByUUID_G(String uuid, long groupId)
		throws NoSuchServiceProcessException {
		ServiceProcess serviceProcess = findByUUID_G(uuid, groupId);

		return remove(serviceProcess);
	}

	/**
	 * Returns the number of service processes where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching service processes
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICEPROCESS_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "serviceProcess.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "serviceProcess.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(serviceProcess.uuid IS NULL OR serviceProcess.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "serviceProcess.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ServiceProcessModelImpl.UUID_COLUMN_BITMASK |
			ServiceProcessModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the service processes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching service processes
	 */
	@Override
	public List<ServiceProcess> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service processes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service processes
	 * @param end the upper bound of the range of service processes (not inclusive)
	 * @return the range of matching service processes
	 */
	@Override
	public List<ServiceProcess> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service processes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service processes
	 * @param end the upper bound of the range of service processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service processes
	 */
	@Override
	public List<ServiceProcess> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ServiceProcess> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service processes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service processes
	 * @param end the upper bound of the range of service processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service processes
	 */
	@Override
	public List<ServiceProcess> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<ServiceProcess> orderByComparator,
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

		List<ServiceProcess> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceProcess>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceProcess serviceProcess : list) {
					if (!Objects.equals(uuid, serviceProcess.getUuid()) ||
							(companyId != serviceProcess.getCompanyId())) {
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

			query.append(_SQL_SELECT_SERVICEPROCESS_WHERE);

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
				query.append(ServiceProcessModelImpl.ORDER_BY_JPQL);
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
					list = (List<ServiceProcess>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceProcess>)QueryUtil.list(q,
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
	 * Returns the first service process in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service process
	 * @throws NoSuchServiceProcessException if a matching service process could not be found
	 */
	@Override
	public ServiceProcess findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException {
		ServiceProcess serviceProcess = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (serviceProcess != null) {
			return serviceProcess;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchServiceProcessException(msg.toString());
	}

	/**
	 * Returns the first service process in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service process, or <code>null</code> if a matching service process could not be found
	 */
	@Override
	public ServiceProcess fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ServiceProcess> orderByComparator) {
		List<ServiceProcess> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service process in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service process
	 * @throws NoSuchServiceProcessException if a matching service process could not be found
	 */
	@Override
	public ServiceProcess findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException {
		ServiceProcess serviceProcess = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (serviceProcess != null) {
			return serviceProcess;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchServiceProcessException(msg.toString());
	}

	/**
	 * Returns the last service process in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service process, or <code>null</code> if a matching service process could not be found
	 */
	@Override
	public ServiceProcess fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ServiceProcess> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ServiceProcess> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service processes before and after the current service process in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param serviceProcessId the primary key of the current service process
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service process
	 * @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	 */
	@Override
	public ServiceProcess[] findByUuid_C_PrevAndNext(long serviceProcessId,
		String uuid, long companyId,
		OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException {
		ServiceProcess serviceProcess = findByPrimaryKey(serviceProcessId);

		Session session = null;

		try {
			session = openSession();

			ServiceProcess[] array = new ServiceProcessImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, serviceProcess, uuid,
					companyId, orderByComparator, true);

			array[1] = serviceProcess;

			array[2] = getByUuid_C_PrevAndNext(session, serviceProcess, uuid,
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

	protected ServiceProcess getByUuid_C_PrevAndNext(Session session,
		ServiceProcess serviceProcess, String uuid, long companyId,
		OrderByComparator<ServiceProcess> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SERVICEPROCESS_WHERE);

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
			query.append(ServiceProcessModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(serviceProcess);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceProcess> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service processes where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ServiceProcess serviceProcess : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceProcess);
		}
	}

	/**
	 * Returns the number of service processes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching service processes
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICEPROCESS_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "serviceProcess.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "serviceProcess.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(serviceProcess.uuid IS NULL OR serviceProcess.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "serviceProcess.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_ID = new FinderPath(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_ID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID = new FinderPath(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_ID",
			new String[] { Long.class.getName() },
			ServiceProcessModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_ID = new FinderPath(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the service processes where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching service processes
	 */
	@Override
	public List<ServiceProcess> findByG_ID(long groupId) {
		return findByG_ID(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service processes where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of service processes
	 * @param end the upper bound of the range of service processes (not inclusive)
	 * @return the range of matching service processes
	 */
	@Override
	public List<ServiceProcess> findByG_ID(long groupId, int start, int end) {
		return findByG_ID(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service processes where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of service processes
	 * @param end the upper bound of the range of service processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service processes
	 */
	@Override
	public List<ServiceProcess> findByG_ID(long groupId, int start, int end,
		OrderByComparator<ServiceProcess> orderByComparator) {
		return findByG_ID(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service processes where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of service processes
	 * @param end the upper bound of the range of service processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service processes
	 */
	@Override
	public List<ServiceProcess> findByG_ID(long groupId, int start, int end,
		OrderByComparator<ServiceProcess> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_ID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<ServiceProcess> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceProcess>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceProcess serviceProcess : list) {
					if ((groupId != serviceProcess.getGroupId())) {
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

			query.append(_SQL_SELECT_SERVICEPROCESS_WHERE);

			query.append(_FINDER_COLUMN_G_ID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ServiceProcessModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ServiceProcess>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceProcess>)QueryUtil.list(q,
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
	 * Returns the first service process in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service process
	 * @throws NoSuchServiceProcessException if a matching service process could not be found
	 */
	@Override
	public ServiceProcess findByG_ID_First(long groupId,
		OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException {
		ServiceProcess serviceProcess = fetchByG_ID_First(groupId,
				orderByComparator);

		if (serviceProcess != null) {
			return serviceProcess;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchServiceProcessException(msg.toString());
	}

	/**
	 * Returns the first service process in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service process, or <code>null</code> if a matching service process could not be found
	 */
	@Override
	public ServiceProcess fetchByG_ID_First(long groupId,
		OrderByComparator<ServiceProcess> orderByComparator) {
		List<ServiceProcess> list = findByG_ID(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service process in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service process
	 * @throws NoSuchServiceProcessException if a matching service process could not be found
	 */
	@Override
	public ServiceProcess findByG_ID_Last(long groupId,
		OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException {
		ServiceProcess serviceProcess = fetchByG_ID_Last(groupId,
				orderByComparator);

		if (serviceProcess != null) {
			return serviceProcess;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchServiceProcessException(msg.toString());
	}

	/**
	 * Returns the last service process in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service process, or <code>null</code> if a matching service process could not be found
	 */
	@Override
	public ServiceProcess fetchByG_ID_Last(long groupId,
		OrderByComparator<ServiceProcess> orderByComparator) {
		int count = countByG_ID(groupId);

		if (count == 0) {
			return null;
		}

		List<ServiceProcess> list = findByG_ID(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service processes before and after the current service process in the ordered set where groupId = &#63;.
	 *
	 * @param serviceProcessId the primary key of the current service process
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service process
	 * @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	 */
	@Override
	public ServiceProcess[] findByG_ID_PrevAndNext(long serviceProcessId,
		long groupId, OrderByComparator<ServiceProcess> orderByComparator)
		throws NoSuchServiceProcessException {
		ServiceProcess serviceProcess = findByPrimaryKey(serviceProcessId);

		Session session = null;

		try {
			session = openSession();

			ServiceProcess[] array = new ServiceProcessImpl[3];

			array[0] = getByG_ID_PrevAndNext(session, serviceProcess, groupId,
					orderByComparator, true);

			array[1] = serviceProcess;

			array[2] = getByG_ID_PrevAndNext(session, serviceProcess, groupId,
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

	protected ServiceProcess getByG_ID_PrevAndNext(Session session,
		ServiceProcess serviceProcess, long groupId,
		OrderByComparator<ServiceProcess> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SERVICEPROCESS_WHERE);

		query.append(_FINDER_COLUMN_G_ID_GROUPID_2);

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
			query.append(ServiceProcessModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(serviceProcess);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceProcess> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service processes where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByG_ID(long groupId) {
		for (ServiceProcess serviceProcess : findByG_ID(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceProcess);
		}
	}

	/**
	 * Returns the number of service processes where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching service processes
	 */
	@Override
	public int countByG_ID(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_ID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVICEPROCESS_WHERE);

			query.append(_FINDER_COLUMN_G_ID_GROUPID_2);

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

	private static final String _FINDER_COLUMN_G_ID_GROUPID_2 = "serviceProcess.groupId = ?";

	public ServiceProcessPersistenceImpl() {
		setModelClass(ServiceProcess.class);
	}

	/**
	 * Caches the service process in the entity cache if it is enabled.
	 *
	 * @param serviceProcess the service process
	 */
	@Override
	public void cacheResult(ServiceProcess serviceProcess) {
		entityCache.putResult(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessImpl.class, serviceProcess.getPrimaryKey(),
			serviceProcess);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { serviceProcess.getUuid(), serviceProcess.getGroupId() },
			serviceProcess);

		serviceProcess.resetOriginalValues();
	}

	/**
	 * Caches the service processes in the entity cache if it is enabled.
	 *
	 * @param serviceProcesses the service processes
	 */
	@Override
	public void cacheResult(List<ServiceProcess> serviceProcesses) {
		for (ServiceProcess serviceProcess : serviceProcesses) {
			if (entityCache.getResult(
						ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
						ServiceProcessImpl.class, serviceProcess.getPrimaryKey()) == null) {
				cacheResult(serviceProcess);
			}
			else {
				serviceProcess.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all service processes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ServiceProcessImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the service process.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ServiceProcess serviceProcess) {
		entityCache.removeResult(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessImpl.class, serviceProcess.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ServiceProcessModelImpl)serviceProcess, true);
	}

	@Override
	public void clearCache(List<ServiceProcess> serviceProcesses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ServiceProcess serviceProcess : serviceProcesses) {
			entityCache.removeResult(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
				ServiceProcessImpl.class, serviceProcess.getPrimaryKey());

			clearUniqueFindersCache((ServiceProcessModelImpl)serviceProcess,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ServiceProcessModelImpl serviceProcessModelImpl) {
		Object[] args = new Object[] {
				serviceProcessModelImpl.getUuid(),
				serviceProcessModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			serviceProcessModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ServiceProcessModelImpl serviceProcessModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					serviceProcessModelImpl.getUuid(),
					serviceProcessModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((serviceProcessModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					serviceProcessModelImpl.getOriginalUuid(),
					serviceProcessModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new service process with the primary key. Does not add the service process to the database.
	 *
	 * @param serviceProcessId the primary key for the new service process
	 * @return the new service process
	 */
	@Override
	public ServiceProcess create(long serviceProcessId) {
		ServiceProcess serviceProcess = new ServiceProcessImpl();

		serviceProcess.setNew(true);
		serviceProcess.setPrimaryKey(serviceProcessId);

		String uuid = PortalUUIDUtil.generate();

		serviceProcess.setUuid(uuid);

		serviceProcess.setCompanyId(companyProvider.getCompanyId());

		return serviceProcess;
	}

	/**
	 * Removes the service process with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceProcessId the primary key of the service process
	 * @return the service process that was removed
	 * @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	 */
	@Override
	public ServiceProcess remove(long serviceProcessId)
		throws NoSuchServiceProcessException {
		return remove((Serializable)serviceProcessId);
	}

	/**
	 * Removes the service process with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the service process
	 * @return the service process that was removed
	 * @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	 */
	@Override
	public ServiceProcess remove(Serializable primaryKey)
		throws NoSuchServiceProcessException {
		Session session = null;

		try {
			session = openSession();

			ServiceProcess serviceProcess = (ServiceProcess)session.get(ServiceProcessImpl.class,
					primaryKey);

			if (serviceProcess == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchServiceProcessException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(serviceProcess);
		}
		catch (NoSuchServiceProcessException nsee) {
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
	protected ServiceProcess removeImpl(ServiceProcess serviceProcess) {
		serviceProcess = toUnwrappedModel(serviceProcess);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(serviceProcess)) {
				serviceProcess = (ServiceProcess)session.get(ServiceProcessImpl.class,
						serviceProcess.getPrimaryKeyObj());
			}

			if (serviceProcess != null) {
				session.delete(serviceProcess);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (serviceProcess != null) {
			clearCache(serviceProcess);
		}

		return serviceProcess;
	}

	@Override
	public ServiceProcess updateImpl(ServiceProcess serviceProcess) {
		serviceProcess = toUnwrappedModel(serviceProcess);

		boolean isNew = serviceProcess.isNew();

		ServiceProcessModelImpl serviceProcessModelImpl = (ServiceProcessModelImpl)serviceProcess;

		if (Validator.isNull(serviceProcess.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			serviceProcess.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (serviceProcess.getCreateDate() == null)) {
			if (serviceContext == null) {
				serviceProcess.setCreateDate(now);
			}
			else {
				serviceProcess.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!serviceProcessModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				serviceProcess.setModifiedDate(now);
			}
			else {
				serviceProcess.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (serviceProcess.isNew()) {
				session.save(serviceProcess);

				serviceProcess.setNew(false);
			}
			else {
				serviceProcess = (ServiceProcess)session.merge(serviceProcess);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ServiceProcessModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((serviceProcessModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceProcessModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { serviceProcessModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((serviceProcessModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceProcessModelImpl.getOriginalUuid(),
						serviceProcessModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						serviceProcessModelImpl.getUuid(),
						serviceProcessModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((serviceProcessModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceProcessModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID,
					args);

				args = new Object[] { serviceProcessModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID,
					args);
			}
		}

		entityCache.putResult(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessImpl.class, serviceProcess.getPrimaryKey(),
			serviceProcess, false);

		clearUniqueFindersCache(serviceProcessModelImpl, false);
		cacheUniqueFindersCache(serviceProcessModelImpl);

		serviceProcess.resetOriginalValues();

		return serviceProcess;
	}

	protected ServiceProcess toUnwrappedModel(ServiceProcess serviceProcess) {
		if (serviceProcess instanceof ServiceProcessImpl) {
			return serviceProcess;
		}

		ServiceProcessImpl serviceProcessImpl = new ServiceProcessImpl();

		serviceProcessImpl.setNew(serviceProcess.isNew());
		serviceProcessImpl.setPrimaryKey(serviceProcess.getPrimaryKey());

		serviceProcessImpl.setUuid(serviceProcess.getUuid());
		serviceProcessImpl.setServiceProcessId(serviceProcess.getServiceProcessId());
		serviceProcessImpl.setCompanyId(serviceProcess.getCompanyId());
		serviceProcessImpl.setGroupId(serviceProcess.getGroupId());
		serviceProcessImpl.setUserId(serviceProcess.getUserId());
		serviceProcessImpl.setUserName(serviceProcess.getUserName());
		serviceProcessImpl.setCreateDate(serviceProcess.getCreateDate());
		serviceProcessImpl.setModifiedDate(serviceProcess.getModifiedDate());
		serviceProcessImpl.setProcessNo(serviceProcess.getProcessNo());
		serviceProcessImpl.setDescription(serviceProcess.getDescription());
		serviceProcessImpl.setDurationCount(serviceProcess.getDurationCount());
		serviceProcessImpl.setDurationUnit(serviceProcess.getDurationUnit());
		serviceProcessImpl.setCounter(serviceProcess.getCounter());
		serviceProcessImpl.setGenerateDossierNo(serviceProcess.getGenerateDossierNo());
		serviceProcessImpl.setDossierNoPattern(serviceProcess.getDossierNoPattern());
		serviceProcessImpl.setGenerateDueDate(serviceProcess.getGenerateDueDate());
		serviceProcessImpl.setDueDatePattern(serviceProcess.getDueDatePattern());

		return serviceProcessImpl;
	}

	/**
	 * Returns the service process with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the service process
	 * @return the service process
	 * @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	 */
	@Override
	public ServiceProcess findByPrimaryKey(Serializable primaryKey)
		throws NoSuchServiceProcessException {
		ServiceProcess serviceProcess = fetchByPrimaryKey(primaryKey);

		if (serviceProcess == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchServiceProcessException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return serviceProcess;
	}

	/**
	 * Returns the service process with the primary key or throws a {@link NoSuchServiceProcessException} if it could not be found.
	 *
	 * @param serviceProcessId the primary key of the service process
	 * @return the service process
	 * @throws NoSuchServiceProcessException if a service process with the primary key could not be found
	 */
	@Override
	public ServiceProcess findByPrimaryKey(long serviceProcessId)
		throws NoSuchServiceProcessException {
		return findByPrimaryKey((Serializable)serviceProcessId);
	}

	/**
	 * Returns the service process with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the service process
	 * @return the service process, or <code>null</code> if a service process with the primary key could not be found
	 */
	@Override
	public ServiceProcess fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
				ServiceProcessImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ServiceProcess serviceProcess = (ServiceProcess)serializable;

		if (serviceProcess == null) {
			Session session = null;

			try {
				session = openSession();

				serviceProcess = (ServiceProcess)session.get(ServiceProcessImpl.class,
						primaryKey);

				if (serviceProcess != null) {
					cacheResult(serviceProcess);
				}
				else {
					entityCache.putResult(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
						ServiceProcessImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
					ServiceProcessImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return serviceProcess;
	}

	/**
	 * Returns the service process with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param serviceProcessId the primary key of the service process
	 * @return the service process, or <code>null</code> if a service process with the primary key could not be found
	 */
	@Override
	public ServiceProcess fetchByPrimaryKey(long serviceProcessId) {
		return fetchByPrimaryKey((Serializable)serviceProcessId);
	}

	@Override
	public Map<Serializable, ServiceProcess> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ServiceProcess> map = new HashMap<Serializable, ServiceProcess>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ServiceProcess serviceProcess = fetchByPrimaryKey(primaryKey);

			if (serviceProcess != null) {
				map.put(primaryKey, serviceProcess);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
					ServiceProcessImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ServiceProcess)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SERVICEPROCESS_WHERE_PKS_IN);

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

			for (ServiceProcess serviceProcess : (List<ServiceProcess>)q.list()) {
				map.put(serviceProcess.getPrimaryKeyObj(), serviceProcess);

				cacheResult(serviceProcess);

				uncachedPrimaryKeys.remove(serviceProcess.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ServiceProcessModelImpl.ENTITY_CACHE_ENABLED,
					ServiceProcessImpl.class, primaryKey, nullModel);
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
	 * Returns all the service processes.
	 *
	 * @return the service processes
	 */
	@Override
	public List<ServiceProcess> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service processes
	 * @param end the upper bound of the range of service processes (not inclusive)
	 * @return the range of service processes
	 */
	@Override
	public List<ServiceProcess> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the service processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service processes
	 * @param end the upper bound of the range of service processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of service processes
	 */
	@Override
	public List<ServiceProcess> findAll(int start, int end,
		OrderByComparator<ServiceProcess> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service processes
	 * @param end the upper bound of the range of service processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of service processes
	 */
	@Override
	public List<ServiceProcess> findAll(int start, int end,
		OrderByComparator<ServiceProcess> orderByComparator,
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

		List<ServiceProcess> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceProcess>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SERVICEPROCESS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SERVICEPROCESS;

				if (pagination) {
					sql = sql.concat(ServiceProcessModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ServiceProcess>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceProcess>)QueryUtil.list(q,
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
	 * Removes all the service processes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ServiceProcess serviceProcess : findAll()) {
			remove(serviceProcess);
		}
	}

	/**
	 * Returns the number of service processes.
	 *
	 * @return the number of service processes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SERVICEPROCESS);

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
		return ServiceProcessModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the service process persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ServiceProcessImpl.class.getName());
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
	private static final String _SQL_SELECT_SERVICEPROCESS = "SELECT serviceProcess FROM ServiceProcess serviceProcess";
	private static final String _SQL_SELECT_SERVICEPROCESS_WHERE_PKS_IN = "SELECT serviceProcess FROM ServiceProcess serviceProcess WHERE serviceProcessId IN (";
	private static final String _SQL_SELECT_SERVICEPROCESS_WHERE = "SELECT serviceProcess FROM ServiceProcess serviceProcess WHERE ";
	private static final String _SQL_COUNT_SERVICEPROCESS = "SELECT COUNT(serviceProcess) FROM ServiceProcess serviceProcess";
	private static final String _SQL_COUNT_SERVICEPROCESS_WHERE = "SELECT COUNT(serviceProcess) FROM ServiceProcess serviceProcess WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "serviceProcess.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ServiceProcess exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ServiceProcess exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ServiceProcessPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}