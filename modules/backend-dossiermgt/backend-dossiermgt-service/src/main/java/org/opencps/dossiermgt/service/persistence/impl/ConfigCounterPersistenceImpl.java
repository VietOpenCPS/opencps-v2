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

import org.opencps.dossiermgt.exception.NoSuchConfigCounterException;
import org.opencps.dossiermgt.model.ConfigCounter;
import org.opencps.dossiermgt.model.impl.ConfigCounterImpl;
import org.opencps.dossiermgt.model.impl.ConfigCounterModelImpl;
import org.opencps.dossiermgt.service.persistence.ConfigCounterPersistence;

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
 * The persistence implementation for the config counter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ConfigCounterPersistence
 * @see org.opencps.dossiermgt.service.persistence.ConfigCounterUtil
 * @generated
 */
@ProviderType
public class ConfigCounterPersistenceImpl extends BasePersistenceImpl<ConfigCounter>
	implements ConfigCounterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ConfigCounterUtil} to access the config counter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ConfigCounterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED,
			ConfigCounterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED,
			ConfigCounterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED,
			ConfigCounterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED,
			ConfigCounterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			ConfigCounterModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the config counters where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching config counters
	 */
	@Override
	public List<ConfigCounter> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the config counters where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of config counters
	 * @param end the upper bound of the range of config counters (not inclusive)
	 * @return the range of matching config counters
	 */
	@Override
	public List<ConfigCounter> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the config counters where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of config counters
	 * @param end the upper bound of the range of config counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching config counters
	 */
	@Override
	public List<ConfigCounter> findByUuid(String uuid, int start, int end,
		OrderByComparator<ConfigCounter> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the config counters where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of config counters
	 * @param end the upper bound of the range of config counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching config counters
	 */
	@Override
	public List<ConfigCounter> findByUuid(String uuid, int start, int end,
		OrderByComparator<ConfigCounter> orderByComparator,
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

		List<ConfigCounter> list = null;

		if (retrieveFromCache) {
			list = (List<ConfigCounter>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ConfigCounter configCounter : list) {
					if (!Objects.equals(uuid, configCounter.getUuid())) {
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

			query.append(_SQL_SELECT_CONFIGCOUNTER_WHERE);

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
				query.append(ConfigCounterModelImpl.ORDER_BY_JPQL);
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
					list = (List<ConfigCounter>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ConfigCounter>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first config counter in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching config counter
	 * @throws NoSuchConfigCounterException if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter findByUuid_First(String uuid,
		OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException {
		ConfigCounter configCounter = fetchByUuid_First(uuid, orderByComparator);

		if (configCounter != null) {
			return configCounter;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchConfigCounterException(msg.toString());
	}

	/**
	 * Returns the first config counter in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching config counter, or <code>null</code> if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter fetchByUuid_First(String uuid,
		OrderByComparator<ConfigCounter> orderByComparator) {
		List<ConfigCounter> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last config counter in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching config counter
	 * @throws NoSuchConfigCounterException if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter findByUuid_Last(String uuid,
		OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException {
		ConfigCounter configCounter = fetchByUuid_Last(uuid, orderByComparator);

		if (configCounter != null) {
			return configCounter;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchConfigCounterException(msg.toString());
	}

	/**
	 * Returns the last config counter in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching config counter, or <code>null</code> if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter fetchByUuid_Last(String uuid,
		OrderByComparator<ConfigCounter> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ConfigCounter> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the config counters before and after the current config counter in the ordered set where uuid = &#63;.
	 *
	 * @param configCounterId the primary key of the current config counter
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next config counter
	 * @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	 */
	@Override
	public ConfigCounter[] findByUuid_PrevAndNext(long configCounterId,
		String uuid, OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException {
		ConfigCounter configCounter = findByPrimaryKey(configCounterId);

		Session session = null;

		try {
			session = openSession();

			ConfigCounter[] array = new ConfigCounterImpl[3];

			array[0] = getByUuid_PrevAndNext(session, configCounter, uuid,
					orderByComparator, true);

			array[1] = configCounter;

			array[2] = getByUuid_PrevAndNext(session, configCounter, uuid,
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

	protected ConfigCounter getByUuid_PrevAndNext(Session session,
		ConfigCounter configCounter, String uuid,
		OrderByComparator<ConfigCounter> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONFIGCOUNTER_WHERE);

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
			query.append(ConfigCounterModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(configCounter);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ConfigCounter> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the config counters where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ConfigCounter configCounter : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(configCounter);
		}
	}

	/**
	 * Returns the number of config counters where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching config counters
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONFIGCOUNTER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "configCounter.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "configCounter.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(configCounter.uuid IS NULL OR configCounter.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED,
			ConfigCounterImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ConfigCounterModelImpl.UUID_COLUMN_BITMASK |
			ConfigCounterModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the config counter where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchConfigCounterException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching config counter
	 * @throws NoSuchConfigCounterException if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter findByUUID_G(String uuid, long groupId)
		throws NoSuchConfigCounterException {
		ConfigCounter configCounter = fetchByUUID_G(uuid, groupId);

		if (configCounter == null) {
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

			throw new NoSuchConfigCounterException(msg.toString());
		}

		return configCounter;
	}

	/**
	 * Returns the config counter where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the config counter where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ConfigCounter) {
			ConfigCounter configCounter = (ConfigCounter)result;

			if (!Objects.equals(uuid, configCounter.getUuid()) ||
					(groupId != configCounter.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CONFIGCOUNTER_WHERE);

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

				List<ConfigCounter> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ConfigCounter configCounter = list.get(0);

					result = configCounter;

					cacheResult(configCounter);
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
			return (ConfigCounter)result;
		}
	}

	/**
	 * Removes the config counter where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the config counter that was removed
	 */
	@Override
	public ConfigCounter removeByUUID_G(String uuid, long groupId)
		throws NoSuchConfigCounterException {
		ConfigCounter configCounter = findByUUID_G(uuid, groupId);

		return remove(configCounter);
	}

	/**
	 * Returns the number of config counters where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching config counters
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CONFIGCOUNTER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "configCounter.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "configCounter.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(configCounter.uuid IS NULL OR configCounter.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "configCounter.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED,
			ConfigCounterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED,
			ConfigCounterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ConfigCounterModelImpl.UUID_COLUMN_BITMASK |
			ConfigCounterModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the config counters where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching config counters
	 */
	@Override
	public List<ConfigCounter> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the config counters where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of config counters
	 * @param end the upper bound of the range of config counters (not inclusive)
	 * @return the range of matching config counters
	 */
	@Override
	public List<ConfigCounter> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the config counters where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of config counters
	 * @param end the upper bound of the range of config counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching config counters
	 */
	@Override
	public List<ConfigCounter> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ConfigCounter> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the config counters where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of config counters
	 * @param end the upper bound of the range of config counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching config counters
	 */
	@Override
	public List<ConfigCounter> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ConfigCounter> orderByComparator,
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

		List<ConfigCounter> list = null;

		if (retrieveFromCache) {
			list = (List<ConfigCounter>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ConfigCounter configCounter : list) {
					if (!Objects.equals(uuid, configCounter.getUuid()) ||
							(companyId != configCounter.getCompanyId())) {
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

			query.append(_SQL_SELECT_CONFIGCOUNTER_WHERE);

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
				query.append(ConfigCounterModelImpl.ORDER_BY_JPQL);
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
					list = (List<ConfigCounter>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ConfigCounter>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching config counter
	 * @throws NoSuchConfigCounterException if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException {
		ConfigCounter configCounter = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (configCounter != null) {
			return configCounter;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchConfigCounterException(msg.toString());
	}

	/**
	 * Returns the first config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching config counter, or <code>null</code> if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ConfigCounter> orderByComparator) {
		List<ConfigCounter> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching config counter
	 * @throws NoSuchConfigCounterException if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException {
		ConfigCounter configCounter = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (configCounter != null) {
			return configCounter;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchConfigCounterException(msg.toString());
	}

	/**
	 * Returns the last config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching config counter, or <code>null</code> if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ConfigCounter> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ConfigCounter> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the config counters before and after the current config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param configCounterId the primary key of the current config counter
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next config counter
	 * @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	 */
	@Override
	public ConfigCounter[] findByUuid_C_PrevAndNext(long configCounterId,
		String uuid, long companyId,
		OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException {
		ConfigCounter configCounter = findByPrimaryKey(configCounterId);

		Session session = null;

		try {
			session = openSession();

			ConfigCounter[] array = new ConfigCounterImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, configCounter, uuid,
					companyId, orderByComparator, true);

			array[1] = configCounter;

			array[2] = getByUuid_C_PrevAndNext(session, configCounter, uuid,
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

	protected ConfigCounter getByUuid_C_PrevAndNext(Session session,
		ConfigCounter configCounter, String uuid, long companyId,
		OrderByComparator<ConfigCounter> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CONFIGCOUNTER_WHERE);

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
			query.append(ConfigCounterModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(configCounter);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ConfigCounter> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the config counters where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ConfigCounter configCounter : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(configCounter);
		}
	}

	/**
	 * Returns the number of config counters where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching config counters
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CONFIGCOUNTER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "configCounter.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "configCounter.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(configCounter.uuid IS NULL OR configCounter.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "configCounter.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_ID = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED,
			ConfigCounterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_ID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED,
			ConfigCounterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByG_ID", new String[] { Long.class.getName() },
			ConfigCounterModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_ID = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the config counters where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching config counters
	 */
	@Override
	public List<ConfigCounter> findByG_ID(long groupId) {
		return findByG_ID(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the config counters where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of config counters
	 * @param end the upper bound of the range of config counters (not inclusive)
	 * @return the range of matching config counters
	 */
	@Override
	public List<ConfigCounter> findByG_ID(long groupId, int start, int end) {
		return findByG_ID(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the config counters where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of config counters
	 * @param end the upper bound of the range of config counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching config counters
	 */
	@Override
	public List<ConfigCounter> findByG_ID(long groupId, int start, int end,
		OrderByComparator<ConfigCounter> orderByComparator) {
		return findByG_ID(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the config counters where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of config counters
	 * @param end the upper bound of the range of config counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching config counters
	 */
	@Override
	public List<ConfigCounter> findByG_ID(long groupId, int start, int end,
		OrderByComparator<ConfigCounter> orderByComparator,
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

		List<ConfigCounter> list = null;

		if (retrieveFromCache) {
			list = (List<ConfigCounter>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ConfigCounter configCounter : list) {
					if ((groupId != configCounter.getGroupId())) {
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

			query.append(_SQL_SELECT_CONFIGCOUNTER_WHERE);

			query.append(_FINDER_COLUMN_G_ID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ConfigCounterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ConfigCounter>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ConfigCounter>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first config counter in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching config counter
	 * @throws NoSuchConfigCounterException if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter findByG_ID_First(long groupId,
		OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException {
		ConfigCounter configCounter = fetchByG_ID_First(groupId,
				orderByComparator);

		if (configCounter != null) {
			return configCounter;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchConfigCounterException(msg.toString());
	}

	/**
	 * Returns the first config counter in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching config counter, or <code>null</code> if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter fetchByG_ID_First(long groupId,
		OrderByComparator<ConfigCounter> orderByComparator) {
		List<ConfigCounter> list = findByG_ID(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last config counter in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching config counter
	 * @throws NoSuchConfigCounterException if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter findByG_ID_Last(long groupId,
		OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException {
		ConfigCounter configCounter = fetchByG_ID_Last(groupId,
				orderByComparator);

		if (configCounter != null) {
			return configCounter;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchConfigCounterException(msg.toString());
	}

	/**
	 * Returns the last config counter in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching config counter, or <code>null</code> if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter fetchByG_ID_Last(long groupId,
		OrderByComparator<ConfigCounter> orderByComparator) {
		int count = countByG_ID(groupId);

		if (count == 0) {
			return null;
		}

		List<ConfigCounter> list = findByG_ID(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the config counters before and after the current config counter in the ordered set where groupId = &#63;.
	 *
	 * @param configCounterId the primary key of the current config counter
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next config counter
	 * @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	 */
	@Override
	public ConfigCounter[] findByG_ID_PrevAndNext(long configCounterId,
		long groupId, OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException {
		ConfigCounter configCounter = findByPrimaryKey(configCounterId);

		Session session = null;

		try {
			session = openSession();

			ConfigCounter[] array = new ConfigCounterImpl[3];

			array[0] = getByG_ID_PrevAndNext(session, configCounter, groupId,
					orderByComparator, true);

			array[1] = configCounter;

			array[2] = getByG_ID_PrevAndNext(session, configCounter, groupId,
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

	protected ConfigCounter getByG_ID_PrevAndNext(Session session,
		ConfigCounter configCounter, long groupId,
		OrderByComparator<ConfigCounter> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONFIGCOUNTER_WHERE);

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
			query.append(ConfigCounterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(configCounter);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ConfigCounter> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the config counters where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByG_ID(long groupId) {
		for (ConfigCounter configCounter : findByG_ID(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(configCounter);
		}
	}

	/**
	 * Returns the number of config counters where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching config counters
	 */
	@Override
	public int countByG_ID(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_ID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONFIGCOUNTER_WHERE);

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

	private static final String _FINDER_COLUMN_G_ID_GROUPID_2 = "configCounter.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_GID_CODE = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED,
			ConfigCounterImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByGID_CODE",
			new String[] { Long.class.getName(), String.class.getName() },
			ConfigCounterModelImpl.GROUPID_COLUMN_BITMASK |
			ConfigCounterModelImpl.COUNTERCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_CODE = new FinderPath(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGID_CODE",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the config counter where groupId = &#63; and counterCode = &#63; or throws a {@link NoSuchConfigCounterException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param counterCode the counter code
	 * @return the matching config counter
	 * @throws NoSuchConfigCounterException if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter findByGID_CODE(long groupId, String counterCode)
		throws NoSuchConfigCounterException {
		ConfigCounter configCounter = fetchByGID_CODE(groupId, counterCode);

		if (configCounter == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", counterCode=");
			msg.append(counterCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchConfigCounterException(msg.toString());
		}

		return configCounter;
	}

	/**
	 * Returns the config counter where groupId = &#63; and counterCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param counterCode the counter code
	 * @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter fetchByGID_CODE(long groupId, String counterCode) {
		return fetchByGID_CODE(groupId, counterCode, true);
	}

	/**
	 * Returns the config counter where groupId = &#63; and counterCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param counterCode the counter code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	 */
	@Override
	public ConfigCounter fetchByGID_CODE(long groupId, String counterCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, counterCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_GID_CODE,
					finderArgs, this);
		}

		if (result instanceof ConfigCounter) {
			ConfigCounter configCounter = (ConfigCounter)result;

			if ((groupId != configCounter.getGroupId()) ||
					!Objects.equals(counterCode, configCounter.getCounterCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CONFIGCOUNTER_WHERE);

			query.append(_FINDER_COLUMN_GID_CODE_GROUPID_2);

			boolean bindCounterCode = false;

			if (counterCode == null) {
				query.append(_FINDER_COLUMN_GID_CODE_COUNTERCODE_1);
			}
			else if (counterCode.equals("")) {
				query.append(_FINDER_COLUMN_GID_CODE_COUNTERCODE_3);
			}
			else {
				bindCounterCode = true;

				query.append(_FINDER_COLUMN_GID_CODE_COUNTERCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindCounterCode) {
					qPos.add(counterCode);
				}

				List<ConfigCounter> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_GID_CODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ConfigCounterPersistenceImpl.fetchByGID_CODE(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ConfigCounter configCounter = list.get(0);

					result = configCounter;

					cacheResult(configCounter);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_CODE,
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
			return (ConfigCounter)result;
		}
	}

	/**
	 * Removes the config counter where groupId = &#63; and counterCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param counterCode the counter code
	 * @return the config counter that was removed
	 */
	@Override
	public ConfigCounter removeByGID_CODE(long groupId, String counterCode)
		throws NoSuchConfigCounterException {
		ConfigCounter configCounter = findByGID_CODE(groupId, counterCode);

		return remove(configCounter);
	}

	/**
	 * Returns the number of config counters where groupId = &#63; and counterCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param counterCode the counter code
	 * @return the number of matching config counters
	 */
	@Override
	public int countByGID_CODE(long groupId, String counterCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_CODE;

		Object[] finderArgs = new Object[] { groupId, counterCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CONFIGCOUNTER_WHERE);

			query.append(_FINDER_COLUMN_GID_CODE_GROUPID_2);

			boolean bindCounterCode = false;

			if (counterCode == null) {
				query.append(_FINDER_COLUMN_GID_CODE_COUNTERCODE_1);
			}
			else if (counterCode.equals("")) {
				query.append(_FINDER_COLUMN_GID_CODE_COUNTERCODE_3);
			}
			else {
				bindCounterCode = true;

				query.append(_FINDER_COLUMN_GID_CODE_COUNTERCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindCounterCode) {
					qPos.add(counterCode);
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

	private static final String _FINDER_COLUMN_GID_CODE_GROUPID_2 = "configCounter.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GID_CODE_COUNTERCODE_1 = "configCounter.counterCode IS NULL";
	private static final String _FINDER_COLUMN_GID_CODE_COUNTERCODE_2 = "configCounter.counterCode = ?";
	private static final String _FINDER_COLUMN_GID_CODE_COUNTERCODE_3 = "(configCounter.counterCode IS NULL OR configCounter.counterCode = '')";

	public ConfigCounterPersistenceImpl() {
		setModelClass(ConfigCounter.class);

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
	 * Caches the config counter in the entity cache if it is enabled.
	 *
	 * @param configCounter the config counter
	 */
	@Override
	public void cacheResult(ConfigCounter configCounter) {
		entityCache.putResult(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterImpl.class, configCounter.getPrimaryKey(),
			configCounter);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { configCounter.getUuid(), configCounter.getGroupId() },
			configCounter);

		finderCache.putResult(FINDER_PATH_FETCH_BY_GID_CODE,
			new Object[] {
				configCounter.getGroupId(), configCounter.getCounterCode()
			}, configCounter);

		configCounter.resetOriginalValues();
	}

	/**
	 * Caches the config counters in the entity cache if it is enabled.
	 *
	 * @param configCounters the config counters
	 */
	@Override
	public void cacheResult(List<ConfigCounter> configCounters) {
		for (ConfigCounter configCounter : configCounters) {
			if (entityCache.getResult(
						ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
						ConfigCounterImpl.class, configCounter.getPrimaryKey()) == null) {
				cacheResult(configCounter);
			}
			else {
				configCounter.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all config counters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ConfigCounterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the config counter.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ConfigCounter configCounter) {
		entityCache.removeResult(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterImpl.class, configCounter.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ConfigCounterModelImpl)configCounter, true);
	}

	@Override
	public void clearCache(List<ConfigCounter> configCounters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ConfigCounter configCounter : configCounters) {
			entityCache.removeResult(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
				ConfigCounterImpl.class, configCounter.getPrimaryKey());

			clearUniqueFindersCache((ConfigCounterModelImpl)configCounter, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ConfigCounterModelImpl configCounterModelImpl) {
		Object[] args = new Object[] {
				configCounterModelImpl.getUuid(),
				configCounterModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			configCounterModelImpl, false);

		args = new Object[] {
				configCounterModelImpl.getGroupId(),
				configCounterModelImpl.getCounterCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_GID_CODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_GID_CODE, args,
			configCounterModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ConfigCounterModelImpl configCounterModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					configCounterModelImpl.getUuid(),
					configCounterModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((configCounterModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					configCounterModelImpl.getOriginalUuid(),
					configCounterModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					configCounterModelImpl.getGroupId(),
					configCounterModelImpl.getCounterCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_CODE, args);
		}

		if ((configCounterModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GID_CODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					configCounterModelImpl.getOriginalGroupId(),
					configCounterModelImpl.getOriginalCounterCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_CODE, args);
		}
	}

	/**
	 * Creates a new config counter with the primary key. Does not add the config counter to the database.
	 *
	 * @param configCounterId the primary key for the new config counter
	 * @return the new config counter
	 */
	@Override
	public ConfigCounter create(long configCounterId) {
		ConfigCounter configCounter = new ConfigCounterImpl();

		configCounter.setNew(true);
		configCounter.setPrimaryKey(configCounterId);

		String uuid = PortalUUIDUtil.generate();

		configCounter.setUuid(uuid);

		configCounter.setCompanyId(companyProvider.getCompanyId());

		return configCounter;
	}

	/**
	 * Removes the config counter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param configCounterId the primary key of the config counter
	 * @return the config counter that was removed
	 * @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	 */
	@Override
	public ConfigCounter remove(long configCounterId)
		throws NoSuchConfigCounterException {
		return remove((Serializable)configCounterId);
	}

	/**
	 * Removes the config counter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the config counter
	 * @return the config counter that was removed
	 * @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	 */
	@Override
	public ConfigCounter remove(Serializable primaryKey)
		throws NoSuchConfigCounterException {
		Session session = null;

		try {
			session = openSession();

			ConfigCounter configCounter = (ConfigCounter)session.get(ConfigCounterImpl.class,
					primaryKey);

			if (configCounter == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchConfigCounterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(configCounter);
		}
		catch (NoSuchConfigCounterException nsee) {
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
	protected ConfigCounter removeImpl(ConfigCounter configCounter) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(configCounter)) {
				configCounter = (ConfigCounter)session.get(ConfigCounterImpl.class,
						configCounter.getPrimaryKeyObj());
			}

			if (configCounter != null) {
				session.delete(configCounter);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (configCounter != null) {
			clearCache(configCounter);
		}

		return configCounter;
	}

	@Override
	public ConfigCounter updateImpl(ConfigCounter configCounter) {
		boolean isNew = configCounter.isNew();

		if (!(configCounter instanceof ConfigCounterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(configCounter.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(configCounter);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in configCounter proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ConfigCounter implementation " +
				configCounter.getClass());
		}

		ConfigCounterModelImpl configCounterModelImpl = (ConfigCounterModelImpl)configCounter;

		if (Validator.isNull(configCounter.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			configCounter.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (configCounter.getCreateDate() == null)) {
			if (serviceContext == null) {
				configCounter.setCreateDate(now);
			}
			else {
				configCounter.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!configCounterModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				configCounter.setModifiedDate(now);
			}
			else {
				configCounter.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (configCounter.isNew()) {
				session.save(configCounter);

				configCounter.setNew(false);
			}
			else {
				configCounter = (ConfigCounter)session.merge(configCounter);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ConfigCounterModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { configCounterModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					configCounterModelImpl.getUuid(),
					configCounterModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { configCounterModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_ID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((configCounterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						configCounterModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { configCounterModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((configCounterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						configCounterModelImpl.getOriginalUuid(),
						configCounterModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						configCounterModelImpl.getUuid(),
						configCounterModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((configCounterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						configCounterModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID,
					args);

				args = new Object[] { configCounterModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID,
					args);
			}
		}

		entityCache.putResult(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
			ConfigCounterImpl.class, configCounter.getPrimaryKey(),
			configCounter, false);

		clearUniqueFindersCache(configCounterModelImpl, false);
		cacheUniqueFindersCache(configCounterModelImpl);

		configCounter.resetOriginalValues();

		return configCounter;
	}

	/**
	 * Returns the config counter with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the config counter
	 * @return the config counter
	 * @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	 */
	@Override
	public ConfigCounter findByPrimaryKey(Serializable primaryKey)
		throws NoSuchConfigCounterException {
		ConfigCounter configCounter = fetchByPrimaryKey(primaryKey);

		if (configCounter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchConfigCounterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return configCounter;
	}

	/**
	 * Returns the config counter with the primary key or throws a {@link NoSuchConfigCounterException} if it could not be found.
	 *
	 * @param configCounterId the primary key of the config counter
	 * @return the config counter
	 * @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	 */
	@Override
	public ConfigCounter findByPrimaryKey(long configCounterId)
		throws NoSuchConfigCounterException {
		return findByPrimaryKey((Serializable)configCounterId);
	}

	/**
	 * Returns the config counter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the config counter
	 * @return the config counter, or <code>null</code> if a config counter with the primary key could not be found
	 */
	@Override
	public ConfigCounter fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
				ConfigCounterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ConfigCounter configCounter = (ConfigCounter)serializable;

		if (configCounter == null) {
			Session session = null;

			try {
				session = openSession();

				configCounter = (ConfigCounter)session.get(ConfigCounterImpl.class,
						primaryKey);

				if (configCounter != null) {
					cacheResult(configCounter);
				}
				else {
					entityCache.putResult(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
						ConfigCounterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
					ConfigCounterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return configCounter;
	}

	/**
	 * Returns the config counter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param configCounterId the primary key of the config counter
	 * @return the config counter, or <code>null</code> if a config counter with the primary key could not be found
	 */
	@Override
	public ConfigCounter fetchByPrimaryKey(long configCounterId) {
		return fetchByPrimaryKey((Serializable)configCounterId);
	}

	@Override
	public Map<Serializable, ConfigCounter> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ConfigCounter> map = new HashMap<Serializable, ConfigCounter>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ConfigCounter configCounter = fetchByPrimaryKey(primaryKey);

			if (configCounter != null) {
				map.put(primaryKey, configCounter);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
					ConfigCounterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ConfigCounter)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CONFIGCOUNTER_WHERE_PKS_IN);

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

			for (ConfigCounter configCounter : (List<ConfigCounter>)q.list()) {
				map.put(configCounter.getPrimaryKeyObj(), configCounter);

				cacheResult(configCounter);

				uncachedPrimaryKeys.remove(configCounter.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ConfigCounterModelImpl.ENTITY_CACHE_ENABLED,
					ConfigCounterImpl.class, primaryKey, nullModel);
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
	 * Returns all the config counters.
	 *
	 * @return the config counters
	 */
	@Override
	public List<ConfigCounter> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the config counters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of config counters
	 * @param end the upper bound of the range of config counters (not inclusive)
	 * @return the range of config counters
	 */
	@Override
	public List<ConfigCounter> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the config counters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of config counters
	 * @param end the upper bound of the range of config counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of config counters
	 */
	@Override
	public List<ConfigCounter> findAll(int start, int end,
		OrderByComparator<ConfigCounter> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the config counters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of config counters
	 * @param end the upper bound of the range of config counters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of config counters
	 */
	@Override
	public List<ConfigCounter> findAll(int start, int end,
		OrderByComparator<ConfigCounter> orderByComparator,
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

		List<ConfigCounter> list = null;

		if (retrieveFromCache) {
			list = (List<ConfigCounter>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CONFIGCOUNTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONFIGCOUNTER;

				if (pagination) {
					sql = sql.concat(ConfigCounterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ConfigCounter>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ConfigCounter>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the config counters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ConfigCounter configCounter : findAll()) {
			remove(configCounter);
		}
	}

	/**
	 * Returns the number of config counters.
	 *
	 * @return the number of config counters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONFIGCOUNTER);

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
		return ConfigCounterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the config counter persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ConfigCounterImpl.class.getName());
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
	private static final String _SQL_SELECT_CONFIGCOUNTER = "SELECT configCounter FROM ConfigCounter configCounter";
	private static final String _SQL_SELECT_CONFIGCOUNTER_WHERE_PKS_IN = "SELECT configCounter FROM ConfigCounter configCounter WHERE configCounterId IN (";
	private static final String _SQL_SELECT_CONFIGCOUNTER_WHERE = "SELECT configCounter FROM ConfigCounter configCounter WHERE ";
	private static final String _SQL_COUNT_CONFIGCOUNTER = "SELECT COUNT(configCounter) FROM ConfigCounter configCounter";
	private static final String _SQL_COUNT_CONFIGCOUNTER_WHERE = "SELECT COUNT(configCounter) FROM ConfigCounter configCounter WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "configCounter.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ConfigCounter exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ConfigCounter exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ConfigCounterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}