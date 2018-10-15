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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchRegistrationLogException;
import org.opencps.dossiermgt.model.RegistrationLog;
import org.opencps.dossiermgt.model.impl.RegistrationLogImpl;
import org.opencps.dossiermgt.model.impl.RegistrationLogModelImpl;
import org.opencps.dossiermgt.service.persistence.RegistrationLogPersistence;

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
 * The persistence implementation for the registration log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see RegistrationLogPersistence
 * @see org.opencps.dossiermgt.service.persistence.RegistrationLogUtil
 * @generated
 */
@ProviderType
public class RegistrationLogPersistenceImpl extends BasePersistenceImpl<RegistrationLog>
	implements RegistrationLogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RegistrationLogUtil} to access the registration log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RegistrationLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogModelImpl.FINDER_CACHE_ENABLED,
			RegistrationLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogModelImpl.FINDER_CACHE_ENABLED,
			RegistrationLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogModelImpl.FINDER_CACHE_ENABLED,
			RegistrationLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogModelImpl.FINDER_CACHE_ENABLED,
			RegistrationLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			RegistrationLogModelImpl.UUID_COLUMN_BITMASK |
			RegistrationLogModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the registration logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @return the range of matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator,
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

		List<RegistrationLog> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationLog registrationLog : list) {
					if (!Objects.equals(uuid, registrationLog.getUuid())) {
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

			query.append(_SQL_SELECT_REGISTRATIONLOG_WHERE);

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
				query.append(RegistrationLogModelImpl.ORDER_BY_JPQL);
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
					list = (List<RegistrationLog>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationLog>)QueryUtil.list(q,
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
	 * Returns the first registration log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration log
	 * @throws NoSuchRegistrationLogException if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog findByUuid_First(String uuid,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException {
		RegistrationLog registrationLog = fetchByUuid_First(uuid,
				orderByComparator);

		if (registrationLog != null) {
			return registrationLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchRegistrationLogException(msg.toString());
	}

	/**
	 * Returns the first registration log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration log, or <code>null</code> if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog fetchByUuid_First(String uuid,
		OrderByComparator<RegistrationLog> orderByComparator) {
		List<RegistrationLog> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration log
	 * @throws NoSuchRegistrationLogException if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog findByUuid_Last(String uuid,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException {
		RegistrationLog registrationLog = fetchByUuid_Last(uuid,
				orderByComparator);

		if (registrationLog != null) {
			return registrationLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchRegistrationLogException(msg.toString());
	}

	/**
	 * Returns the last registration log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration log, or <code>null</code> if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog fetchByUuid_Last(String uuid,
		OrderByComparator<RegistrationLog> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<RegistrationLog> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration logs before and after the current registration log in the ordered set where uuid = &#63;.
	 *
	 * @param registrationLogId the primary key of the current registration log
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration log
	 * @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog[] findByUuid_PrevAndNext(long registrationLogId,
		String uuid, OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException {
		RegistrationLog registrationLog = findByPrimaryKey(registrationLogId);

		Session session = null;

		try {
			session = openSession();

			RegistrationLog[] array = new RegistrationLogImpl[3];

			array[0] = getByUuid_PrevAndNext(session, registrationLog, uuid,
					orderByComparator, true);

			array[1] = registrationLog;

			array[2] = getByUuid_PrevAndNext(session, registrationLog, uuid,
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

	protected RegistrationLog getByUuid_PrevAndNext(Session session,
		RegistrationLog registrationLog, String uuid,
		OrderByComparator<RegistrationLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REGISTRATIONLOG_WHERE);

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
			query.append(RegistrationLogModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(registrationLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RegistrationLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration logs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (RegistrationLog registrationLog : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registrationLog);
		}
	}

	/**
	 * Returns the number of registration logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching registration logs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REGISTRATIONLOG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "registrationLog.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "registrationLog.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(registrationLog.uuid IS NULL OR registrationLog.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogModelImpl.FINDER_CACHE_ENABLED,
			RegistrationLogImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			RegistrationLogModelImpl.UUID_COLUMN_BITMASK |
			RegistrationLogModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the registration log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRegistrationLogException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching registration log
	 * @throws NoSuchRegistrationLogException if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog findByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationLogException {
		RegistrationLog registrationLog = fetchByUUID_G(uuid, groupId);

		if (registrationLog == null) {
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

			throw new NoSuchRegistrationLogException(msg.toString());
		}

		return registrationLog;
	}

	/**
	 * Returns the registration log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching registration log, or <code>null</code> if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the registration log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching registration log, or <code>null</code> if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof RegistrationLog) {
			RegistrationLog registrationLog = (RegistrationLog)result;

			if (!Objects.equals(uuid, registrationLog.getUuid()) ||
					(groupId != registrationLog.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_REGISTRATIONLOG_WHERE);

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

				List<RegistrationLog> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					RegistrationLog registrationLog = list.get(0);

					result = registrationLog;

					cacheResult(registrationLog);
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
			return (RegistrationLog)result;
		}
	}

	/**
	 * Removes the registration log where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the registration log that was removed
	 */
	@Override
	public RegistrationLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationLogException {
		RegistrationLog registrationLog = findByUUID_G(uuid, groupId);

		return remove(registrationLog);
	}

	/**
	 * Returns the number of registration logs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching registration logs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATIONLOG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "registrationLog.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "registrationLog.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(registrationLog.uuid IS NULL OR registrationLog.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "registrationLog.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogModelImpl.FINDER_CACHE_ENABLED,
			RegistrationLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogModelImpl.FINDER_CACHE_ENABLED,
			RegistrationLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			RegistrationLogModelImpl.UUID_COLUMN_BITMASK |
			RegistrationLogModelImpl.COMPANYID_COLUMN_BITMASK |
			RegistrationLogModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the registration logs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @return the range of matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<RegistrationLog> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator,
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

		List<RegistrationLog> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationLog registrationLog : list) {
					if (!Objects.equals(uuid, registrationLog.getUuid()) ||
							(companyId != registrationLog.getCompanyId())) {
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

			query.append(_SQL_SELECT_REGISTRATIONLOG_WHERE);

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
				query.append(RegistrationLogModelImpl.ORDER_BY_JPQL);
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
					list = (List<RegistrationLog>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationLog>)QueryUtil.list(q,
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
	 * Returns the first registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration log
	 * @throws NoSuchRegistrationLogException if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException {
		RegistrationLog registrationLog = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (registrationLog != null) {
			return registrationLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchRegistrationLogException(msg.toString());
	}

	/**
	 * Returns the first registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration log, or <code>null</code> if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<RegistrationLog> orderByComparator) {
		List<RegistrationLog> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration log
	 * @throws NoSuchRegistrationLogException if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException {
		RegistrationLog registrationLog = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (registrationLog != null) {
			return registrationLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchRegistrationLogException(msg.toString());
	}

	/**
	 * Returns the last registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration log, or <code>null</code> if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<RegistrationLog> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<RegistrationLog> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration logs before and after the current registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param registrationLogId the primary key of the current registration log
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration log
	 * @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog[] findByUuid_C_PrevAndNext(long registrationLogId,
		String uuid, long companyId,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException {
		RegistrationLog registrationLog = findByPrimaryKey(registrationLogId);

		Session session = null;

		try {
			session = openSession();

			RegistrationLog[] array = new RegistrationLogImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, registrationLog, uuid,
					companyId, orderByComparator, true);

			array[1] = registrationLog;

			array[2] = getByUuid_C_PrevAndNext(session, registrationLog, uuid,
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

	protected RegistrationLog getByUuid_C_PrevAndNext(Session session,
		RegistrationLog registrationLog, String uuid, long companyId,
		OrderByComparator<RegistrationLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REGISTRATIONLOG_WHERE);

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
			query.append(RegistrationLogModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(registrationLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RegistrationLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration logs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (RegistrationLog registrationLog : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registrationLog);
		}
	}

	/**
	 * Returns the number of registration logs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching registration logs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATIONLOG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "registrationLog.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "registrationLog.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(registrationLog.uuid IS NULL OR registrationLog.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "registrationLog.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_REGID = new FinderPath(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogModelImpl.FINDER_CACHE_ENABLED,
			RegistrationLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_REGID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID =
		new FinderPath(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogModelImpl.FINDER_CACHE_ENABLED,
			RegistrationLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_REGID",
			new String[] { Long.class.getName(), Long.class.getName() },
			RegistrationLogModelImpl.GROUPID_COLUMN_BITMASK |
			RegistrationLogModelImpl.REGISTRATIONID_COLUMN_BITMASK |
			RegistrationLogModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_REGID = new FinderPath(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_REGID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the registration logs where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @return the matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByG_REGID(long groupId, long registrationId) {
		return findByG_REGID(groupId, registrationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration logs where groupId = &#63; and registrationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @return the range of matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByG_REGID(long groupId,
		long registrationId, int start, int end) {
		return findByG_REGID(groupId, registrationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration logs where groupId = &#63; and registrationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByG_REGID(long groupId,
		long registrationId, int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator) {
		return findByG_REGID(groupId, registrationId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration logs where groupId = &#63; and registrationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registration logs
	 */
	@Override
	public List<RegistrationLog> findByG_REGID(long groupId,
		long registrationId, int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID;
			finderArgs = new Object[] { groupId, registrationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_REGID;
			finderArgs = new Object[] {
					groupId, registrationId,
					
					start, end, orderByComparator
				};
		}

		List<RegistrationLog> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationLog registrationLog : list) {
					if ((groupId != registrationLog.getGroupId()) ||
							(registrationId != registrationLog.getRegistrationId())) {
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

			query.append(_SQL_SELECT_REGISTRATIONLOG_WHERE);

			query.append(_FINDER_COLUMN_G_REGID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_REGID_REGISTRATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RegistrationLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(registrationId);

				if (!pagination) {
					list = (List<RegistrationLog>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationLog>)QueryUtil.list(q,
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
	 * Returns the first registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration log
	 * @throws NoSuchRegistrationLogException if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog findByG_REGID_First(long groupId,
		long registrationId,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException {
		RegistrationLog registrationLog = fetchByG_REGID_First(groupId,
				registrationId, orderByComparator);

		if (registrationLog != null) {
			return registrationLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", registrationId=");
		msg.append(registrationId);

		msg.append("}");

		throw new NoSuchRegistrationLogException(msg.toString());
	}

	/**
	 * Returns the first registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration log, or <code>null</code> if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog fetchByG_REGID_First(long groupId,
		long registrationId,
		OrderByComparator<RegistrationLog> orderByComparator) {
		List<RegistrationLog> list = findByG_REGID(groupId, registrationId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration log
	 * @throws NoSuchRegistrationLogException if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog findByG_REGID_Last(long groupId,
		long registrationId,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException {
		RegistrationLog registrationLog = fetchByG_REGID_Last(groupId,
				registrationId, orderByComparator);

		if (registrationLog != null) {
			return registrationLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", registrationId=");
		msg.append(registrationId);

		msg.append("}");

		throw new NoSuchRegistrationLogException(msg.toString());
	}

	/**
	 * Returns the last registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration log, or <code>null</code> if a matching registration log could not be found
	 */
	@Override
	public RegistrationLog fetchByG_REGID_Last(long groupId,
		long registrationId,
		OrderByComparator<RegistrationLog> orderByComparator) {
		int count = countByG_REGID(groupId, registrationId);

		if (count == 0) {
			return null;
		}

		List<RegistrationLog> list = findByG_REGID(groupId, registrationId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration logs before and after the current registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param registrationLogId the primary key of the current registration log
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration log
	 * @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog[] findByG_REGID_PrevAndNext(long registrationLogId,
		long groupId, long registrationId,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException {
		RegistrationLog registrationLog = findByPrimaryKey(registrationLogId);

		Session session = null;

		try {
			session = openSession();

			RegistrationLog[] array = new RegistrationLogImpl[3];

			array[0] = getByG_REGID_PrevAndNext(session, registrationLog,
					groupId, registrationId, orderByComparator, true);

			array[1] = registrationLog;

			array[2] = getByG_REGID_PrevAndNext(session, registrationLog,
					groupId, registrationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RegistrationLog getByG_REGID_PrevAndNext(Session session,
		RegistrationLog registrationLog, long groupId, long registrationId,
		OrderByComparator<RegistrationLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REGISTRATIONLOG_WHERE);

		query.append(_FINDER_COLUMN_G_REGID_GROUPID_2);

		query.append(_FINDER_COLUMN_G_REGID_REGISTRATIONID_2);

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
			query.append(RegistrationLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(registrationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(registrationLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RegistrationLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration logs where groupId = &#63; and registrationId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 */
	@Override
	public void removeByG_REGID(long groupId, long registrationId) {
		for (RegistrationLog registrationLog : findByG_REGID(groupId,
				registrationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registrationLog);
		}
	}

	/**
	 * Returns the number of registration logs where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @return the number of matching registration logs
	 */
	@Override
	public int countByG_REGID(long groupId, long registrationId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_REGID;

		Object[] finderArgs = new Object[] { groupId, registrationId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATIONLOG_WHERE);

			query.append(_FINDER_COLUMN_G_REGID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_REGID_REGISTRATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(registrationId);

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

	private static final String _FINDER_COLUMN_G_REGID_GROUPID_2 = "registrationLog.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_REGID_REGISTRATIONID_2 = "registrationLog.registrationId = ?";

	public RegistrationLogPersistenceImpl() {
		setModelClass(RegistrationLog.class);

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
	 * Caches the registration log in the entity cache if it is enabled.
	 *
	 * @param registrationLog the registration log
	 */
	@Override
	public void cacheResult(RegistrationLog registrationLog) {
		entityCache.putResult(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogImpl.class, registrationLog.getPrimaryKey(),
			registrationLog);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { registrationLog.getUuid(), registrationLog.getGroupId() },
			registrationLog);

		registrationLog.resetOriginalValues();
	}

	/**
	 * Caches the registration logs in the entity cache if it is enabled.
	 *
	 * @param registrationLogs the registration logs
	 */
	@Override
	public void cacheResult(List<RegistrationLog> registrationLogs) {
		for (RegistrationLog registrationLog : registrationLogs) {
			if (entityCache.getResult(
						RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
						RegistrationLogImpl.class,
						registrationLog.getPrimaryKey()) == null) {
				cacheResult(registrationLog);
			}
			else {
				registrationLog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all registration logs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RegistrationLogImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the registration log.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RegistrationLog registrationLog) {
		entityCache.removeResult(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogImpl.class, registrationLog.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((RegistrationLogModelImpl)registrationLog, true);
	}

	@Override
	public void clearCache(List<RegistrationLog> registrationLogs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RegistrationLog registrationLog : registrationLogs) {
			entityCache.removeResult(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
				RegistrationLogImpl.class, registrationLog.getPrimaryKey());

			clearUniqueFindersCache((RegistrationLogModelImpl)registrationLog,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		RegistrationLogModelImpl registrationLogModelImpl) {
		Object[] args = new Object[] {
				registrationLogModelImpl.getUuid(),
				registrationLogModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			registrationLogModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		RegistrationLogModelImpl registrationLogModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					registrationLogModelImpl.getUuid(),
					registrationLogModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((registrationLogModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					registrationLogModelImpl.getOriginalUuid(),
					registrationLogModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new registration log with the primary key. Does not add the registration log to the database.
	 *
	 * @param registrationLogId the primary key for the new registration log
	 * @return the new registration log
	 */
	@Override
	public RegistrationLog create(long registrationLogId) {
		RegistrationLog registrationLog = new RegistrationLogImpl();

		registrationLog.setNew(true);
		registrationLog.setPrimaryKey(registrationLogId);

		String uuid = PortalUUIDUtil.generate();

		registrationLog.setUuid(uuid);

		registrationLog.setCompanyId(companyProvider.getCompanyId());

		return registrationLog;
	}

	/**
	 * Removes the registration log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param registrationLogId the primary key of the registration log
	 * @return the registration log that was removed
	 * @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog remove(long registrationLogId)
		throws NoSuchRegistrationLogException {
		return remove((Serializable)registrationLogId);
	}

	/**
	 * Removes the registration log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the registration log
	 * @return the registration log that was removed
	 * @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog remove(Serializable primaryKey)
		throws NoSuchRegistrationLogException {
		Session session = null;

		try {
			session = openSession();

			RegistrationLog registrationLog = (RegistrationLog)session.get(RegistrationLogImpl.class,
					primaryKey);

			if (registrationLog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRegistrationLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(registrationLog);
		}
		catch (NoSuchRegistrationLogException nsee) {
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
	protected RegistrationLog removeImpl(RegistrationLog registrationLog) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(registrationLog)) {
				registrationLog = (RegistrationLog)session.get(RegistrationLogImpl.class,
						registrationLog.getPrimaryKeyObj());
			}

			if (registrationLog != null) {
				session.delete(registrationLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (registrationLog != null) {
			clearCache(registrationLog);
		}

		return registrationLog;
	}

	@Override
	public RegistrationLog updateImpl(RegistrationLog registrationLog) {
		boolean isNew = registrationLog.isNew();

		if (!(registrationLog instanceof RegistrationLogModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(registrationLog.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(registrationLog);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in registrationLog proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom RegistrationLog implementation " +
				registrationLog.getClass());
		}

		RegistrationLogModelImpl registrationLogModelImpl = (RegistrationLogModelImpl)registrationLog;

		if (Validator.isNull(registrationLog.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			registrationLog.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (registrationLog.getCreateDate() == null)) {
			if (serviceContext == null) {
				registrationLog.setCreateDate(now);
			}
			else {
				registrationLog.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!registrationLogModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				registrationLog.setModifiedDate(now);
			}
			else {
				registrationLog.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (registrationLog.isNew()) {
				session.save(registrationLog);

				registrationLog.setNew(false);
			}
			else {
				registrationLog = (RegistrationLog)session.merge(registrationLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!RegistrationLogModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { registrationLogModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					registrationLogModelImpl.getUuid(),
					registrationLogModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					registrationLogModelImpl.getGroupId(),
					registrationLogModelImpl.getRegistrationId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((registrationLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationLogModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { registrationLogModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((registrationLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationLogModelImpl.getOriginalUuid(),
						registrationLogModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						registrationLogModelImpl.getUuid(),
						registrationLogModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((registrationLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationLogModelImpl.getOriginalGroupId(),
						registrationLogModelImpl.getOriginalRegistrationId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID,
					args);

				args = new Object[] {
						registrationLogModelImpl.getGroupId(),
						registrationLogModelImpl.getRegistrationId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID,
					args);
			}
		}

		entityCache.putResult(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationLogImpl.class, registrationLog.getPrimaryKey(),
			registrationLog, false);

		clearUniqueFindersCache(registrationLogModelImpl, false);
		cacheUniqueFindersCache(registrationLogModelImpl);

		registrationLog.resetOriginalValues();

		return registrationLog;
	}

	/**
	 * Returns the registration log with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the registration log
	 * @return the registration log
	 * @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRegistrationLogException {
		RegistrationLog registrationLog = fetchByPrimaryKey(primaryKey);

		if (registrationLog == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRegistrationLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return registrationLog;
	}

	/**
	 * Returns the registration log with the primary key or throws a {@link NoSuchRegistrationLogException} if it could not be found.
	 *
	 * @param registrationLogId the primary key of the registration log
	 * @return the registration log
	 * @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog findByPrimaryKey(long registrationLogId)
		throws NoSuchRegistrationLogException {
		return findByPrimaryKey((Serializable)registrationLogId);
	}

	/**
	 * Returns the registration log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the registration log
	 * @return the registration log, or <code>null</code> if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
				RegistrationLogImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		RegistrationLog registrationLog = (RegistrationLog)serializable;

		if (registrationLog == null) {
			Session session = null;

			try {
				session = openSession();

				registrationLog = (RegistrationLog)session.get(RegistrationLogImpl.class,
						primaryKey);

				if (registrationLog != null) {
					cacheResult(registrationLog);
				}
				else {
					entityCache.putResult(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
						RegistrationLogImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
					RegistrationLogImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return registrationLog;
	}

	/**
	 * Returns the registration log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param registrationLogId the primary key of the registration log
	 * @return the registration log, or <code>null</code> if a registration log with the primary key could not be found
	 */
	@Override
	public RegistrationLog fetchByPrimaryKey(long registrationLogId) {
		return fetchByPrimaryKey((Serializable)registrationLogId);
	}

	@Override
	public Map<Serializable, RegistrationLog> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, RegistrationLog> map = new HashMap<Serializable, RegistrationLog>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			RegistrationLog registrationLog = fetchByPrimaryKey(primaryKey);

			if (registrationLog != null) {
				map.put(primaryKey, registrationLog);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
					RegistrationLogImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (RegistrationLog)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_REGISTRATIONLOG_WHERE_PKS_IN);

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

			for (RegistrationLog registrationLog : (List<RegistrationLog>)q.list()) {
				map.put(registrationLog.getPrimaryKeyObj(), registrationLog);

				cacheResult(registrationLog);

				uncachedPrimaryKeys.remove(registrationLog.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RegistrationLogModelImpl.ENTITY_CACHE_ENABLED,
					RegistrationLogImpl.class, primaryKey, nullModel);
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
	 * Returns all the registration logs.
	 *
	 * @return the registration logs
	 */
	@Override
	public List<RegistrationLog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @return the range of registration logs
	 */
	@Override
	public List<RegistrationLog> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of registration logs
	 */
	@Override
	public List<RegistrationLog> findAll(int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration logs
	 * @param end the upper bound of the range of registration logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of registration logs
	 */
	@Override
	public List<RegistrationLog> findAll(int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator,
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

		List<RegistrationLog> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationLog>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REGISTRATIONLOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REGISTRATIONLOG;

				if (pagination) {
					sql = sql.concat(RegistrationLogModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RegistrationLog>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationLog>)QueryUtil.list(q,
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
	 * Removes all the registration logs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RegistrationLog registrationLog : findAll()) {
			remove(registrationLog);
		}
	}

	/**
	 * Returns the number of registration logs.
	 *
	 * @return the number of registration logs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REGISTRATIONLOG);

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
		return RegistrationLogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the registration log persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RegistrationLogImpl.class.getName());
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
	private static final String _SQL_SELECT_REGISTRATIONLOG = "SELECT registrationLog FROM RegistrationLog registrationLog";
	private static final String _SQL_SELECT_REGISTRATIONLOG_WHERE_PKS_IN = "SELECT registrationLog FROM RegistrationLog registrationLog WHERE registrationLogId IN (";
	private static final String _SQL_SELECT_REGISTRATIONLOG_WHERE = "SELECT registrationLog FROM RegistrationLog registrationLog WHERE ";
	private static final String _SQL_COUNT_REGISTRATIONLOG = "SELECT COUNT(registrationLog) FROM RegistrationLog registrationLog";
	private static final String _SQL_COUNT_REGISTRATIONLOG_WHERE = "SELECT COUNT(registrationLog) FROM RegistrationLog registrationLog WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "registrationLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RegistrationLog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No RegistrationLog exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(RegistrationLogPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}