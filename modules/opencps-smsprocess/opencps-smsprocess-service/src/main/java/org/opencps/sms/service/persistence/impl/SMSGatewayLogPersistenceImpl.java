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

package org.opencps.sms.service.persistence.impl;

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

import org.opencps.sms.exception.NoSuchGatewayLogException;
import org.opencps.sms.model.SMSGatewayLog;
import org.opencps.sms.model.impl.SMSGatewayLogImpl;
import org.opencps.sms.model.impl.SMSGatewayLogModelImpl;
import org.opencps.sms.service.persistence.SMSGatewayLogPersistence;

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
 * The persistence implementation for the sms gateway log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoa
 * @see SMSGatewayLogPersistence
 * @see org.opencps.sms.service.persistence.SMSGatewayLogUtil
 * @generated
 */
@ProviderType
public class SMSGatewayLogPersistenceImpl extends BasePersistenceImpl<SMSGatewayLog>
	implements SMSGatewayLogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SMSGatewayLogUtil} to access the sms gateway log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SMSGatewayLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
			SMSGatewayLogModelImpl.FINDER_CACHE_ENABLED,
			SMSGatewayLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
			SMSGatewayLogModelImpl.FINDER_CACHE_ENABLED,
			SMSGatewayLogImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
			SMSGatewayLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
			SMSGatewayLogModelImpl.FINDER_CACHE_ENABLED,
			SMSGatewayLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
			SMSGatewayLogModelImpl.FINDER_CACHE_ENABLED,
			SMSGatewayLogImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			SMSGatewayLogModelImpl.UUID_COLUMN_BITMASK |
			SMSGatewayLogModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
			SMSGatewayLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the sms gateway logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching sms gateway logs
	 */
	@Override
	public List<SMSGatewayLog> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sms gateway logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sms gateway logs
	 * @param end the upper bound of the range of sms gateway logs (not inclusive)
	 * @return the range of matching sms gateway logs
	 */
	@Override
	public List<SMSGatewayLog> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sms gateway logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sms gateway logs
	 * @param end the upper bound of the range of sms gateway logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sms gateway logs
	 */
	@Override
	public List<SMSGatewayLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<SMSGatewayLog> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sms gateway logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sms gateway logs
	 * @param end the upper bound of the range of sms gateway logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sms gateway logs
	 */
	@Override
	public List<SMSGatewayLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<SMSGatewayLog> orderByComparator,
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

		List<SMSGatewayLog> list = null;

		if (retrieveFromCache) {
			list = (List<SMSGatewayLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SMSGatewayLog smsGatewayLog : list) {
					if (!Objects.equals(uuid, smsGatewayLog.getUuid())) {
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

			query.append(_SQL_SELECT_SMSGATEWAYLOG_WHERE);

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
				query.append(SMSGatewayLogModelImpl.ORDER_BY_JPQL);
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
					list = (List<SMSGatewayLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SMSGatewayLog>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sms gateway log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sms gateway log
	 * @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	 */
	@Override
	public SMSGatewayLog findByUuid_First(String uuid,
		OrderByComparator<SMSGatewayLog> orderByComparator)
		throws NoSuchGatewayLogException {
		SMSGatewayLog smsGatewayLog = fetchByUuid_First(uuid, orderByComparator);

		if (smsGatewayLog != null) {
			return smsGatewayLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchGatewayLogException(msg.toString());
	}

	/**
	 * Returns the first sms gateway log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	 */
	@Override
	public SMSGatewayLog fetchByUuid_First(String uuid,
		OrderByComparator<SMSGatewayLog> orderByComparator) {
		List<SMSGatewayLog> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sms gateway log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sms gateway log
	 * @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	 */
	@Override
	public SMSGatewayLog findByUuid_Last(String uuid,
		OrderByComparator<SMSGatewayLog> orderByComparator)
		throws NoSuchGatewayLogException {
		SMSGatewayLog smsGatewayLog = fetchByUuid_Last(uuid, orderByComparator);

		if (smsGatewayLog != null) {
			return smsGatewayLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchGatewayLogException(msg.toString());
	}

	/**
	 * Returns the last sms gateway log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	 */
	@Override
	public SMSGatewayLog fetchByUuid_Last(String uuid,
		OrderByComparator<SMSGatewayLog> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SMSGatewayLog> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sms gateway logs before and after the current sms gateway log in the ordered set where uuid = &#63;.
	 *
	 * @param smsId the primary key of the current sms gateway log
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sms gateway log
	 * @throws NoSuchGatewayLogException if a sms gateway log with the primary key could not be found
	 */
	@Override
	public SMSGatewayLog[] findByUuid_PrevAndNext(long smsId, String uuid,
		OrderByComparator<SMSGatewayLog> orderByComparator)
		throws NoSuchGatewayLogException {
		SMSGatewayLog smsGatewayLog = findByPrimaryKey(smsId);

		Session session = null;

		try {
			session = openSession();

			SMSGatewayLog[] array = new SMSGatewayLogImpl[3];

			array[0] = getByUuid_PrevAndNext(session, smsGatewayLog, uuid,
					orderByComparator, true);

			array[1] = smsGatewayLog;

			array[2] = getByUuid_PrevAndNext(session, smsGatewayLog, uuid,
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

	protected SMSGatewayLog getByUuid_PrevAndNext(Session session,
		SMSGatewayLog smsGatewayLog, String uuid,
		OrderByComparator<SMSGatewayLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SMSGATEWAYLOG_WHERE);

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
			query.append(SMSGatewayLogModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(smsGatewayLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SMSGatewayLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sms gateway logs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (SMSGatewayLog smsGatewayLog : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(smsGatewayLog);
		}
	}

	/**
	 * Returns the number of sms gateway logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching sms gateway logs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SMSGATEWAYLOG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "smsGatewayLog.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "smsGatewayLog.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(smsGatewayLog.uuid IS NULL OR smsGatewayLog.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
			SMSGatewayLogModelImpl.FINDER_CACHE_ENABLED,
			SMSGatewayLogImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			SMSGatewayLogModelImpl.UUID_COLUMN_BITMASK |
			SMSGatewayLogModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
			SMSGatewayLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the sms gateway log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchGatewayLogException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching sms gateway log
	 * @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	 */
	@Override
	public SMSGatewayLog findByUUID_G(String uuid, long groupId)
		throws NoSuchGatewayLogException {
		SMSGatewayLog smsGatewayLog = fetchByUUID_G(uuid, groupId);

		if (smsGatewayLog == null) {
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

			throw new NoSuchGatewayLogException(msg.toString());
		}

		return smsGatewayLog;
	}

	/**
	 * Returns the sms gateway log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	 */
	@Override
	public SMSGatewayLog fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the sms gateway log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	 */
	@Override
	public SMSGatewayLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof SMSGatewayLog) {
			SMSGatewayLog smsGatewayLog = (SMSGatewayLog)result;

			if (!Objects.equals(uuid, smsGatewayLog.getUuid()) ||
					(groupId != smsGatewayLog.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SMSGATEWAYLOG_WHERE);

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

				List<SMSGatewayLog> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					SMSGatewayLog smsGatewayLog = list.get(0);

					result = smsGatewayLog;

					cacheResult(smsGatewayLog);
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
			return (SMSGatewayLog)result;
		}
	}

	/**
	 * Removes the sms gateway log where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the sms gateway log that was removed
	 */
	@Override
	public SMSGatewayLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchGatewayLogException {
		SMSGatewayLog smsGatewayLog = findByUUID_G(uuid, groupId);

		return remove(smsGatewayLog);
	}

	/**
	 * Returns the number of sms gateway logs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching sms gateway logs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SMSGATEWAYLOG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "smsGatewayLog.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "smsGatewayLog.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(smsGatewayLog.uuid IS NULL OR smsGatewayLog.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "smsGatewayLog.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
			SMSGatewayLogModelImpl.FINDER_CACHE_ENABLED,
			SMSGatewayLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
			SMSGatewayLogModelImpl.FINDER_CACHE_ENABLED,
			SMSGatewayLogImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			SMSGatewayLogModelImpl.UUID_COLUMN_BITMASK |
			SMSGatewayLogModelImpl.COMPANYID_COLUMN_BITMASK |
			SMSGatewayLogModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
			SMSGatewayLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the sms gateway logs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching sms gateway logs
	 */
	@Override
	public List<SMSGatewayLog> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sms gateway logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sms gateway logs
	 * @param end the upper bound of the range of sms gateway logs (not inclusive)
	 * @return the range of matching sms gateway logs
	 */
	@Override
	public List<SMSGatewayLog> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sms gateway logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sms gateway logs
	 * @param end the upper bound of the range of sms gateway logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sms gateway logs
	 */
	@Override
	public List<SMSGatewayLog> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<SMSGatewayLog> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sms gateway logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sms gateway logs
	 * @param end the upper bound of the range of sms gateway logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sms gateway logs
	 */
	@Override
	public List<SMSGatewayLog> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<SMSGatewayLog> orderByComparator,
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

		List<SMSGatewayLog> list = null;

		if (retrieveFromCache) {
			list = (List<SMSGatewayLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SMSGatewayLog smsGatewayLog : list) {
					if (!Objects.equals(uuid, smsGatewayLog.getUuid()) ||
							(companyId != smsGatewayLog.getCompanyId())) {
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

			query.append(_SQL_SELECT_SMSGATEWAYLOG_WHERE);

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
				query.append(SMSGatewayLogModelImpl.ORDER_BY_JPQL);
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
					list = (List<SMSGatewayLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SMSGatewayLog>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sms gateway log
	 * @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	 */
	@Override
	public SMSGatewayLog findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SMSGatewayLog> orderByComparator)
		throws NoSuchGatewayLogException {
		SMSGatewayLog smsGatewayLog = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (smsGatewayLog != null) {
			return smsGatewayLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchGatewayLogException(msg.toString());
	}

	/**
	 * Returns the first sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	 */
	@Override
	public SMSGatewayLog fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SMSGatewayLog> orderByComparator) {
		List<SMSGatewayLog> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sms gateway log
	 * @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	 */
	@Override
	public SMSGatewayLog findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SMSGatewayLog> orderByComparator)
		throws NoSuchGatewayLogException {
		SMSGatewayLog smsGatewayLog = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (smsGatewayLog != null) {
			return smsGatewayLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchGatewayLogException(msg.toString());
	}

	/**
	 * Returns the last sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	 */
	@Override
	public SMSGatewayLog fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SMSGatewayLog> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<SMSGatewayLog> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sms gateway logs before and after the current sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param smsId the primary key of the current sms gateway log
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sms gateway log
	 * @throws NoSuchGatewayLogException if a sms gateway log with the primary key could not be found
	 */
	@Override
	public SMSGatewayLog[] findByUuid_C_PrevAndNext(long smsId, String uuid,
		long companyId, OrderByComparator<SMSGatewayLog> orderByComparator)
		throws NoSuchGatewayLogException {
		SMSGatewayLog smsGatewayLog = findByPrimaryKey(smsId);

		Session session = null;

		try {
			session = openSession();

			SMSGatewayLog[] array = new SMSGatewayLogImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, smsGatewayLog, uuid,
					companyId, orderByComparator, true);

			array[1] = smsGatewayLog;

			array[2] = getByUuid_C_PrevAndNext(session, smsGatewayLog, uuid,
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

	protected SMSGatewayLog getByUuid_C_PrevAndNext(Session session,
		SMSGatewayLog smsGatewayLog, String uuid, long companyId,
		OrderByComparator<SMSGatewayLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SMSGATEWAYLOG_WHERE);

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
			query.append(SMSGatewayLogModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(smsGatewayLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SMSGatewayLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sms gateway logs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (SMSGatewayLog smsGatewayLog : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(smsGatewayLog);
		}
	}

	/**
	 * Returns the number of sms gateway logs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching sms gateway logs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SMSGATEWAYLOG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "smsGatewayLog.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "smsGatewayLog.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(smsGatewayLog.uuid IS NULL OR smsGatewayLog.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "smsGatewayLog.companyId = ?";

	public SMSGatewayLogPersistenceImpl() {
		setModelClass(SMSGatewayLog.class);

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
	 * Caches the sms gateway log in the entity cache if it is enabled.
	 *
	 * @param smsGatewayLog the sms gateway log
	 */
	@Override
	public void cacheResult(SMSGatewayLog smsGatewayLog) {
		entityCache.putResult(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
			SMSGatewayLogImpl.class, smsGatewayLog.getPrimaryKey(),
			smsGatewayLog);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { smsGatewayLog.getUuid(), smsGatewayLog.getGroupId() },
			smsGatewayLog);

		smsGatewayLog.resetOriginalValues();
	}

	/**
	 * Caches the sms gateway logs in the entity cache if it is enabled.
	 *
	 * @param smsGatewayLogs the sms gateway logs
	 */
	@Override
	public void cacheResult(List<SMSGatewayLog> smsGatewayLogs) {
		for (SMSGatewayLog smsGatewayLog : smsGatewayLogs) {
			if (entityCache.getResult(
						SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
						SMSGatewayLogImpl.class, smsGatewayLog.getPrimaryKey()) == null) {
				cacheResult(smsGatewayLog);
			}
			else {
				smsGatewayLog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sms gateway logs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SMSGatewayLogImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sms gateway log.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SMSGatewayLog smsGatewayLog) {
		entityCache.removeResult(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
			SMSGatewayLogImpl.class, smsGatewayLog.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SMSGatewayLogModelImpl)smsGatewayLog, true);
	}

	@Override
	public void clearCache(List<SMSGatewayLog> smsGatewayLogs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SMSGatewayLog smsGatewayLog : smsGatewayLogs) {
			entityCache.removeResult(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
				SMSGatewayLogImpl.class, smsGatewayLog.getPrimaryKey());

			clearUniqueFindersCache((SMSGatewayLogModelImpl)smsGatewayLog, true);
		}
	}

	protected void cacheUniqueFindersCache(
		SMSGatewayLogModelImpl smsGatewayLogModelImpl) {
		Object[] args = new Object[] {
				smsGatewayLogModelImpl.getUuid(),
				smsGatewayLogModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			smsGatewayLogModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SMSGatewayLogModelImpl smsGatewayLogModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					smsGatewayLogModelImpl.getUuid(),
					smsGatewayLogModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((smsGatewayLogModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					smsGatewayLogModelImpl.getOriginalUuid(),
					smsGatewayLogModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new sms gateway log with the primary key. Does not add the sms gateway log to the database.
	 *
	 * @param smsId the primary key for the new sms gateway log
	 * @return the new sms gateway log
	 */
	@Override
	public SMSGatewayLog create(long smsId) {
		SMSGatewayLog smsGatewayLog = new SMSGatewayLogImpl();

		smsGatewayLog.setNew(true);
		smsGatewayLog.setPrimaryKey(smsId);

		String uuid = PortalUUIDUtil.generate();

		smsGatewayLog.setUuid(uuid);

		smsGatewayLog.setCompanyId(companyProvider.getCompanyId());

		return smsGatewayLog;
	}

	/**
	 * Removes the sms gateway log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param smsId the primary key of the sms gateway log
	 * @return the sms gateway log that was removed
	 * @throws NoSuchGatewayLogException if a sms gateway log with the primary key could not be found
	 */
	@Override
	public SMSGatewayLog remove(long smsId) throws NoSuchGatewayLogException {
		return remove((Serializable)smsId);
	}

	/**
	 * Removes the sms gateway log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sms gateway log
	 * @return the sms gateway log that was removed
	 * @throws NoSuchGatewayLogException if a sms gateway log with the primary key could not be found
	 */
	@Override
	public SMSGatewayLog remove(Serializable primaryKey)
		throws NoSuchGatewayLogException {
		Session session = null;

		try {
			session = openSession();

			SMSGatewayLog smsGatewayLog = (SMSGatewayLog)session.get(SMSGatewayLogImpl.class,
					primaryKey);

			if (smsGatewayLog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGatewayLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(smsGatewayLog);
		}
		catch (NoSuchGatewayLogException nsee) {
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
	protected SMSGatewayLog removeImpl(SMSGatewayLog smsGatewayLog) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(smsGatewayLog)) {
				smsGatewayLog = (SMSGatewayLog)session.get(SMSGatewayLogImpl.class,
						smsGatewayLog.getPrimaryKeyObj());
			}

			if (smsGatewayLog != null) {
				session.delete(smsGatewayLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (smsGatewayLog != null) {
			clearCache(smsGatewayLog);
		}

		return smsGatewayLog;
	}

	@Override
	public SMSGatewayLog updateImpl(SMSGatewayLog smsGatewayLog) {
		boolean isNew = smsGatewayLog.isNew();

		if (!(smsGatewayLog instanceof SMSGatewayLogModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(smsGatewayLog.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(smsGatewayLog);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in smsGatewayLog proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SMSGatewayLog implementation " +
				smsGatewayLog.getClass());
		}

		SMSGatewayLogModelImpl smsGatewayLogModelImpl = (SMSGatewayLogModelImpl)smsGatewayLog;

		if (Validator.isNull(smsGatewayLog.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			smsGatewayLog.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (smsGatewayLog.getCreateDate() == null)) {
			if (serviceContext == null) {
				smsGatewayLog.setCreateDate(now);
			}
			else {
				smsGatewayLog.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!smsGatewayLogModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				smsGatewayLog.setModifiedDate(now);
			}
			else {
				smsGatewayLog.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (smsGatewayLog.isNew()) {
				session.save(smsGatewayLog);

				smsGatewayLog.setNew(false);
			}
			else {
				smsGatewayLog = (SMSGatewayLog)session.merge(smsGatewayLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SMSGatewayLogModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { smsGatewayLogModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					smsGatewayLogModelImpl.getUuid(),
					smsGatewayLogModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((smsGatewayLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						smsGatewayLogModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { smsGatewayLogModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((smsGatewayLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						smsGatewayLogModelImpl.getOriginalUuid(),
						smsGatewayLogModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						smsGatewayLogModelImpl.getUuid(),
						smsGatewayLogModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
			SMSGatewayLogImpl.class, smsGatewayLog.getPrimaryKey(),
			smsGatewayLog, false);

		clearUniqueFindersCache(smsGatewayLogModelImpl, false);
		cacheUniqueFindersCache(smsGatewayLogModelImpl);

		smsGatewayLog.resetOriginalValues();

		return smsGatewayLog;
	}

	/**
	 * Returns the sms gateway log with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sms gateway log
	 * @return the sms gateway log
	 * @throws NoSuchGatewayLogException if a sms gateway log with the primary key could not be found
	 */
	@Override
	public SMSGatewayLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGatewayLogException {
		SMSGatewayLog smsGatewayLog = fetchByPrimaryKey(primaryKey);

		if (smsGatewayLog == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGatewayLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return smsGatewayLog;
	}

	/**
	 * Returns the sms gateway log with the primary key or throws a {@link NoSuchGatewayLogException} if it could not be found.
	 *
	 * @param smsId the primary key of the sms gateway log
	 * @return the sms gateway log
	 * @throws NoSuchGatewayLogException if a sms gateway log with the primary key could not be found
	 */
	@Override
	public SMSGatewayLog findByPrimaryKey(long smsId)
		throws NoSuchGatewayLogException {
		return findByPrimaryKey((Serializable)smsId);
	}

	/**
	 * Returns the sms gateway log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sms gateway log
	 * @return the sms gateway log, or <code>null</code> if a sms gateway log with the primary key could not be found
	 */
	@Override
	public SMSGatewayLog fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
				SMSGatewayLogImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SMSGatewayLog smsGatewayLog = (SMSGatewayLog)serializable;

		if (smsGatewayLog == null) {
			Session session = null;

			try {
				session = openSession();

				smsGatewayLog = (SMSGatewayLog)session.get(SMSGatewayLogImpl.class,
						primaryKey);

				if (smsGatewayLog != null) {
					cacheResult(smsGatewayLog);
				}
				else {
					entityCache.putResult(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
						SMSGatewayLogImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
					SMSGatewayLogImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return smsGatewayLog;
	}

	/**
	 * Returns the sms gateway log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param smsId the primary key of the sms gateway log
	 * @return the sms gateway log, or <code>null</code> if a sms gateway log with the primary key could not be found
	 */
	@Override
	public SMSGatewayLog fetchByPrimaryKey(long smsId) {
		return fetchByPrimaryKey((Serializable)smsId);
	}

	@Override
	public Map<Serializable, SMSGatewayLog> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SMSGatewayLog> map = new HashMap<Serializable, SMSGatewayLog>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SMSGatewayLog smsGatewayLog = fetchByPrimaryKey(primaryKey);

			if (smsGatewayLog != null) {
				map.put(primaryKey, smsGatewayLog);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
					SMSGatewayLogImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SMSGatewayLog)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SMSGATEWAYLOG_WHERE_PKS_IN);

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

			for (SMSGatewayLog smsGatewayLog : (List<SMSGatewayLog>)q.list()) {
				map.put(smsGatewayLog.getPrimaryKeyObj(), smsGatewayLog);

				cacheResult(smsGatewayLog);

				uncachedPrimaryKeys.remove(smsGatewayLog.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SMSGatewayLogModelImpl.ENTITY_CACHE_ENABLED,
					SMSGatewayLogImpl.class, primaryKey, nullModel);
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
	 * Returns all the sms gateway logs.
	 *
	 * @return the sms gateway logs
	 */
	@Override
	public List<SMSGatewayLog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sms gateway logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sms gateway logs
	 * @param end the upper bound of the range of sms gateway logs (not inclusive)
	 * @return the range of sms gateway logs
	 */
	@Override
	public List<SMSGatewayLog> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sms gateway logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sms gateway logs
	 * @param end the upper bound of the range of sms gateway logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sms gateway logs
	 */
	@Override
	public List<SMSGatewayLog> findAll(int start, int end,
		OrderByComparator<SMSGatewayLog> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sms gateway logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sms gateway logs
	 * @param end the upper bound of the range of sms gateway logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of sms gateway logs
	 */
	@Override
	public List<SMSGatewayLog> findAll(int start, int end,
		OrderByComparator<SMSGatewayLog> orderByComparator,
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

		List<SMSGatewayLog> list = null;

		if (retrieveFromCache) {
			list = (List<SMSGatewayLog>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SMSGATEWAYLOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SMSGATEWAYLOG;

				if (pagination) {
					sql = sql.concat(SMSGatewayLogModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SMSGatewayLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SMSGatewayLog>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the sms gateway logs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SMSGatewayLog smsGatewayLog : findAll()) {
			remove(smsGatewayLog);
		}
	}

	/**
	 * Returns the number of sms gateway logs.
	 *
	 * @return the number of sms gateway logs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SMSGATEWAYLOG);

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
		return SMSGatewayLogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sms gateway log persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SMSGatewayLogImpl.class.getName());
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
	private static final String _SQL_SELECT_SMSGATEWAYLOG = "SELECT smsGatewayLog FROM SMSGatewayLog smsGatewayLog";
	private static final String _SQL_SELECT_SMSGATEWAYLOG_WHERE_PKS_IN = "SELECT smsGatewayLog FROM SMSGatewayLog smsGatewayLog WHERE smsId IN (";
	private static final String _SQL_SELECT_SMSGATEWAYLOG_WHERE = "SELECT smsGatewayLog FROM SMSGatewayLog smsGatewayLog WHERE ";
	private static final String _SQL_COUNT_SMSGATEWAYLOG = "SELECT COUNT(smsGatewayLog) FROM SMSGatewayLog smsGatewayLog";
	private static final String _SQL_COUNT_SMSGATEWAYLOG_WHERE = "SELECT COUNT(smsGatewayLog) FROM SMSGatewayLog smsGatewayLog WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "smsGatewayLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SMSGatewayLog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SMSGatewayLog exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SMSGatewayLogPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}