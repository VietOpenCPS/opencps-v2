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

import org.opencps.dossiermgt.exception.NoSuchStepConfigException;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.model.impl.StepConfigImpl;
import org.opencps.dossiermgt.model.impl.StepConfigModelImpl;
import org.opencps.dossiermgt.service.persistence.StepConfigPersistence;

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
 * The persistence implementation for the step config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see StepConfigPersistence
 * @see org.opencps.dossiermgt.service.persistence.StepConfigUtil
 * @generated
 */
@ProviderType
public class StepConfigPersistenceImpl extends BasePersistenceImpl<StepConfig>
	implements StepConfigPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StepConfigUtil} to access the step config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StepConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, StepConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, StepConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, StepConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, StepConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			StepConfigModelImpl.UUID_COLUMN_BITMASK |
			StepConfigModelImpl.STEPCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the step configs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching step configs
	 */
	@Override
	public List<StepConfig> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the step configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @return the range of matching step configs
	 */
	@Override
	public List<StepConfig> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the step configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching step configs
	 */
	@Override
	public List<StepConfig> findByUuid(String uuid, int start, int end,
		OrderByComparator<StepConfig> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the step configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching step configs
	 */
	@Override
	public List<StepConfig> findByUuid(String uuid, int start, int end,
		OrderByComparator<StepConfig> orderByComparator,
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

		List<StepConfig> list = null;

		if (retrieveFromCache) {
			list = (List<StepConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StepConfig stepConfig : list) {
					if (!Objects.equals(uuid, stepConfig.getUuid())) {
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

			query.append(_SQL_SELECT_STEPCONFIG_WHERE);

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
				query.append(StepConfigModelImpl.ORDER_BY_JPQL);
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
					list = (List<StepConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StepConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first step config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching step config
	 * @throws NoSuchStepConfigException if a matching step config could not be found
	 */
	@Override
	public StepConfig findByUuid_First(String uuid,
		OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = fetchByUuid_First(uuid, orderByComparator);

		if (stepConfig != null) {
			return stepConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchStepConfigException(msg.toString());
	}

	/**
	 * Returns the first step config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching step config, or <code>null</code> if a matching step config could not be found
	 */
	@Override
	public StepConfig fetchByUuid_First(String uuid,
		OrderByComparator<StepConfig> orderByComparator) {
		List<StepConfig> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last step config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching step config
	 * @throws NoSuchStepConfigException if a matching step config could not be found
	 */
	@Override
	public StepConfig findByUuid_Last(String uuid,
		OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = fetchByUuid_Last(uuid, orderByComparator);

		if (stepConfig != null) {
			return stepConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchStepConfigException(msg.toString());
	}

	/**
	 * Returns the last step config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching step config, or <code>null</code> if a matching step config could not be found
	 */
	@Override
	public StepConfig fetchByUuid_Last(String uuid,
		OrderByComparator<StepConfig> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<StepConfig> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the step configs before and after the current step config in the ordered set where uuid = &#63;.
	 *
	 * @param stepConfigId the primary key of the current step config
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next step config
	 * @throws NoSuchStepConfigException if a step config with the primary key could not be found
	 */
	@Override
	public StepConfig[] findByUuid_PrevAndNext(long stepConfigId, String uuid,
		OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = findByPrimaryKey(stepConfigId);

		Session session = null;

		try {
			session = openSession();

			StepConfig[] array = new StepConfigImpl[3];

			array[0] = getByUuid_PrevAndNext(session, stepConfig, uuid,
					orderByComparator, true);

			array[1] = stepConfig;

			array[2] = getByUuid_PrevAndNext(session, stepConfig, uuid,
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

	protected StepConfig getByUuid_PrevAndNext(Session session,
		StepConfig stepConfig, String uuid,
		OrderByComparator<StepConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_STEPCONFIG_WHERE);

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
			query.append(StepConfigModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(stepConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<StepConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the step configs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (StepConfig stepConfig : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(stepConfig);
		}
	}

	/**
	 * Returns the number of step configs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching step configs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_STEPCONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "stepConfig.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "stepConfig.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(stepConfig.uuid IS NULL OR stepConfig.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, StepConfigImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			StepConfigModelImpl.UUID_COLUMN_BITMASK |
			StepConfigModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the step config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchStepConfigException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching step config
	 * @throws NoSuchStepConfigException if a matching step config could not be found
	 */
	@Override
	public StepConfig findByUUID_G(String uuid, long groupId)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = fetchByUUID_G(uuid, groupId);

		if (stepConfig == null) {
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

			throw new NoSuchStepConfigException(msg.toString());
		}

		return stepConfig;
	}

	/**
	 * Returns the step config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching step config, or <code>null</code> if a matching step config could not be found
	 */
	@Override
	public StepConfig fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the step config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching step config, or <code>null</code> if a matching step config could not be found
	 */
	@Override
	public StepConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof StepConfig) {
			StepConfig stepConfig = (StepConfig)result;

			if (!Objects.equals(uuid, stepConfig.getUuid()) ||
					(groupId != stepConfig.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_STEPCONFIG_WHERE);

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

				List<StepConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					StepConfig stepConfig = list.get(0);

					result = stepConfig;

					cacheResult(stepConfig);
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
			return (StepConfig)result;
		}
	}

	/**
	 * Removes the step config where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the step config that was removed
	 */
	@Override
	public StepConfig removeByUUID_G(String uuid, long groupId)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = findByUUID_G(uuid, groupId);

		return remove(stepConfig);
	}

	/**
	 * Returns the number of step configs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching step configs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_STEPCONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "stepConfig.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "stepConfig.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(stepConfig.uuid IS NULL OR stepConfig.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "stepConfig.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, StepConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, StepConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			StepConfigModelImpl.UUID_COLUMN_BITMASK |
			StepConfigModelImpl.COMPANYID_COLUMN_BITMASK |
			StepConfigModelImpl.STEPCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the step configs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching step configs
	 */
	@Override
	public List<StepConfig> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the step configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @return the range of matching step configs
	 */
	@Override
	public List<StepConfig> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the step configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching step configs
	 */
	@Override
	public List<StepConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<StepConfig> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the step configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching step configs
	 */
	@Override
	public List<StepConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<StepConfig> orderByComparator,
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

		List<StepConfig> list = null;

		if (retrieveFromCache) {
			list = (List<StepConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StepConfig stepConfig : list) {
					if (!Objects.equals(uuid, stepConfig.getUuid()) ||
							(companyId != stepConfig.getCompanyId())) {
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

			query.append(_SQL_SELECT_STEPCONFIG_WHERE);

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
				query.append(StepConfigModelImpl.ORDER_BY_JPQL);
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
					list = (List<StepConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StepConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first step config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching step config
	 * @throws NoSuchStepConfigException if a matching step config could not be found
	 */
	@Override
	public StepConfig findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (stepConfig != null) {
			return stepConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchStepConfigException(msg.toString());
	}

	/**
	 * Returns the first step config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching step config, or <code>null</code> if a matching step config could not be found
	 */
	@Override
	public StepConfig fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<StepConfig> orderByComparator) {
		List<StepConfig> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last step config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching step config
	 * @throws NoSuchStepConfigException if a matching step config could not be found
	 */
	@Override
	public StepConfig findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (stepConfig != null) {
			return stepConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchStepConfigException(msg.toString());
	}

	/**
	 * Returns the last step config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching step config, or <code>null</code> if a matching step config could not be found
	 */
	@Override
	public StepConfig fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<StepConfig> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<StepConfig> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the step configs before and after the current step config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param stepConfigId the primary key of the current step config
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next step config
	 * @throws NoSuchStepConfigException if a step config with the primary key could not be found
	 */
	@Override
	public StepConfig[] findByUuid_C_PrevAndNext(long stepConfigId,
		String uuid, long companyId,
		OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = findByPrimaryKey(stepConfigId);

		Session session = null;

		try {
			session = openSession();

			StepConfig[] array = new StepConfigImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, stepConfig, uuid,
					companyId, orderByComparator, true);

			array[1] = stepConfig;

			array[2] = getByUuid_C_PrevAndNext(session, stepConfig, uuid,
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

	protected StepConfig getByUuid_C_PrevAndNext(Session session,
		StepConfig stepConfig, String uuid, long companyId,
		OrderByComparator<StepConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_STEPCONFIG_WHERE);

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
			query.append(StepConfigModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(stepConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<StepConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the step configs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (StepConfig stepConfig : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(stepConfig);
		}
	}

	/**
	 * Returns the number of step configs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching step configs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_STEPCONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "stepConfig.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "stepConfig.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(stepConfig.uuid IS NULL OR stepConfig.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "stepConfig.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_BY_STEPCODE = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, StepConfigImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_BY_stepCode",
			new String[] { Long.class.getName(), String.class.getName() },
			StepConfigModelImpl.GROUPID_COLUMN_BITMASK |
			StepConfigModelImpl.STEPCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_BY_STEPCODE = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_BY_stepCode",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the step config where groupId = &#63; and stepCode = &#63; or throws a {@link NoSuchStepConfigException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param stepCode the step code
	 * @return the matching step config
	 * @throws NoSuchStepConfigException if a matching step config could not be found
	 */
	@Override
	public StepConfig findByF_BY_stepCode(long groupId, String stepCode)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = fetchByF_BY_stepCode(groupId, stepCode);

		if (stepConfig == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", stepCode=");
			msg.append(stepCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchStepConfigException(msg.toString());
		}

		return stepConfig;
	}

	/**
	 * Returns the step config where groupId = &#63; and stepCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param stepCode the step code
	 * @return the matching step config, or <code>null</code> if a matching step config could not be found
	 */
	@Override
	public StepConfig fetchByF_BY_stepCode(long groupId, String stepCode) {
		return fetchByF_BY_stepCode(groupId, stepCode, true);
	}

	/**
	 * Returns the step config where groupId = &#63; and stepCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param stepCode the step code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching step config, or <code>null</code> if a matching step config could not be found
	 */
	@Override
	public StepConfig fetchByF_BY_stepCode(long groupId, String stepCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, stepCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_BY_STEPCODE,
					finderArgs, this);
		}

		if (result instanceof StepConfig) {
			StepConfig stepConfig = (StepConfig)result;

			if ((groupId != stepConfig.getGroupId()) ||
					!Objects.equals(stepCode, stepConfig.getStepCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_STEPCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_BY_STEPCODE_GROUPID_2);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_F_BY_STEPCODE_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_F_BY_STEPCODE_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_F_BY_STEPCODE_STEPCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindStepCode) {
					qPos.add(stepCode);
				}

				List<StepConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_BY_STEPCODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"StepConfigPersistenceImpl.fetchByF_BY_stepCode(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					StepConfig stepConfig = list.get(0);

					result = stepConfig;

					cacheResult(stepConfig);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_BY_STEPCODE,
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
			return (StepConfig)result;
		}
	}

	/**
	 * Removes the step config where groupId = &#63; and stepCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param stepCode the step code
	 * @return the step config that was removed
	 */
	@Override
	public StepConfig removeByF_BY_stepCode(long groupId, String stepCode)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = findByF_BY_stepCode(groupId, stepCode);

		return remove(stepConfig);
	}

	/**
	 * Returns the number of step configs where groupId = &#63; and stepCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param stepCode the step code
	 * @return the number of matching step configs
	 */
	@Override
	public int countByF_BY_stepCode(long groupId, String stepCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_BY_STEPCODE;

		Object[] finderArgs = new Object[] { groupId, stepCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_STEPCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_BY_STEPCODE_GROUPID_2);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_F_BY_STEPCODE_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_F_BY_STEPCODE_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_F_BY_STEPCODE_STEPCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindStepCode) {
					qPos.add(stepCode);
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

	private static final String _FINDER_COLUMN_F_BY_STEPCODE_GROUPID_2 = "stepConfig.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_BY_STEPCODE_STEPCODE_1 = "stepConfig.stepCode IS NULL";
	private static final String _FINDER_COLUMN_F_BY_STEPCODE_STEPCODE_2 = "stepConfig.stepCode = ?";
	private static final String _FINDER_COLUMN_F_BY_STEPCODE_STEPCODE_3 = "(stepConfig.stepCode IS NULL OR stepConfig.stepCode = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GT_STEP = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, StepConfigImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_GT_STEP",
			new String[] { Long.class.getName(), String.class.getName() },
			StepConfigModelImpl.GROUPID_COLUMN_BITMASK |
			StepConfigModelImpl.STEPCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GT_STEP = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GT_STEP",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the step config where groupId = &#63; and stepCode = &#63; or throws a {@link NoSuchStepConfigException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param stepCode the step code
	 * @return the matching step config
	 * @throws NoSuchStepConfigException if a matching step config could not be found
	 */
	@Override
	public StepConfig findByF_GT_STEP(long groupId, String stepCode)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = fetchByF_GT_STEP(groupId, stepCode);

		if (stepConfig == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", stepCode=");
			msg.append(stepCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchStepConfigException(msg.toString());
		}

		return stepConfig;
	}

	/**
	 * Returns the step config where groupId = &#63; and stepCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param stepCode the step code
	 * @return the matching step config, or <code>null</code> if a matching step config could not be found
	 */
	@Override
	public StepConfig fetchByF_GT_STEP(long groupId, String stepCode) {
		return fetchByF_GT_STEP(groupId, stepCode, true);
	}

	/**
	 * Returns the step config where groupId = &#63; and stepCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param stepCode the step code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching step config, or <code>null</code> if a matching step config could not be found
	 */
	@Override
	public StepConfig fetchByF_GT_STEP(long groupId, String stepCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, stepCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GT_STEP,
					finderArgs, this);
		}

		if (result instanceof StepConfig) {
			StepConfig stepConfig = (StepConfig)result;

			if ((groupId != stepConfig.getGroupId()) ||
					!Objects.equals(stepCode, stepConfig.getStepCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_STEPCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_GT_STEP_GROUPID_2);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_F_GT_STEP_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GT_STEP_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_F_GT_STEP_STEPCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindStepCode) {
					qPos.add(stepCode);
				}

				List<StepConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GT_STEP,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"StepConfigPersistenceImpl.fetchByF_GT_STEP(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					StepConfig stepConfig = list.get(0);

					result = stepConfig;

					cacheResult(stepConfig);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GT_STEP,
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
			return (StepConfig)result;
		}
	}

	/**
	 * Removes the step config where groupId = &#63; and stepCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param stepCode the step code
	 * @return the step config that was removed
	 */
	@Override
	public StepConfig removeByF_GT_STEP(long groupId, String stepCode)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = findByF_GT_STEP(groupId, stepCode);

		return remove(stepConfig);
	}

	/**
	 * Returns the number of step configs where groupId = &#63; and stepCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param stepCode the step code
	 * @return the number of matching step configs
	 */
	@Override
	public int countByF_GT_STEP(long groupId, String stepCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GT_STEP;

		Object[] finderArgs = new Object[] { groupId, stepCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_STEPCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_GT_STEP_GROUPID_2);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_F_GT_STEP_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GT_STEP_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_F_GT_STEP_STEPCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindStepCode) {
					qPos.add(stepCode);
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

	private static final String _FINDER_COLUMN_F_GT_STEP_GROUPID_2 = "stepConfig.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GT_STEP_STEPCODE_1 = "stepConfig.stepCode IS NULL";
	private static final String _FINDER_COLUMN_F_GT_STEP_STEPCODE_2 = "stepConfig.stepCode = ?";
	private static final String _FINDER_COLUMN_F_GT_STEP_STEPCODE_3 = "(stepConfig.stepCode IS NULL OR stepConfig.stepCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, StepConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_GID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, StepConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_GID",
			new String[] { Long.class.getName() },
			StepConfigModelImpl.GROUPID_COLUMN_BITMASK |
			StepConfigModelImpl.STEPCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the step configs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching step configs
	 */
	@Override
	public List<StepConfig> findByF_GID(long groupId) {
		return findByF_GID(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the step configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @return the range of matching step configs
	 */
	@Override
	public List<StepConfig> findByF_GID(long groupId, int start, int end) {
		return findByF_GID(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the step configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching step configs
	 */
	@Override
	public List<StepConfig> findByF_GID(long groupId, int start, int end,
		OrderByComparator<StepConfig> orderByComparator) {
		return findByF_GID(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the step configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching step configs
	 */
	@Override
	public List<StepConfig> findByF_GID(long groupId, int start, int end,
		OrderByComparator<StepConfig> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<StepConfig> list = null;

		if (retrieveFromCache) {
			list = (List<StepConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StepConfig stepConfig : list) {
					if ((groupId != stepConfig.getGroupId())) {
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

			query.append(_SQL_SELECT_STEPCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_GID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(StepConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<StepConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StepConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first step config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching step config
	 * @throws NoSuchStepConfigException if a matching step config could not be found
	 */
	@Override
	public StepConfig findByF_GID_First(long groupId,
		OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = fetchByF_GID_First(groupId, orderByComparator);

		if (stepConfig != null) {
			return stepConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchStepConfigException(msg.toString());
	}

	/**
	 * Returns the first step config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching step config, or <code>null</code> if a matching step config could not be found
	 */
	@Override
	public StepConfig fetchByF_GID_First(long groupId,
		OrderByComparator<StepConfig> orderByComparator) {
		List<StepConfig> list = findByF_GID(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last step config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching step config
	 * @throws NoSuchStepConfigException if a matching step config could not be found
	 */
	@Override
	public StepConfig findByF_GID_Last(long groupId,
		OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = fetchByF_GID_Last(groupId, orderByComparator);

		if (stepConfig != null) {
			return stepConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchStepConfigException(msg.toString());
	}

	/**
	 * Returns the last step config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching step config, or <code>null</code> if a matching step config could not be found
	 */
	@Override
	public StepConfig fetchByF_GID_Last(long groupId,
		OrderByComparator<StepConfig> orderByComparator) {
		int count = countByF_GID(groupId);

		if (count == 0) {
			return null;
		}

		List<StepConfig> list = findByF_GID(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the step configs before and after the current step config in the ordered set where groupId = &#63;.
	 *
	 * @param stepConfigId the primary key of the current step config
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next step config
	 * @throws NoSuchStepConfigException if a step config with the primary key could not be found
	 */
	@Override
	public StepConfig[] findByF_GID_PrevAndNext(long stepConfigId,
		long groupId, OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = findByPrimaryKey(stepConfigId);

		Session session = null;

		try {
			session = openSession();

			StepConfig[] array = new StepConfigImpl[3];

			array[0] = getByF_GID_PrevAndNext(session, stepConfig, groupId,
					orderByComparator, true);

			array[1] = stepConfig;

			array[2] = getByF_GID_PrevAndNext(session, stepConfig, groupId,
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

	protected StepConfig getByF_GID_PrevAndNext(Session session,
		StepConfig stepConfig, long groupId,
		OrderByComparator<StepConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_STEPCONFIG_WHERE);

		query.append(_FINDER_COLUMN_F_GID_GROUPID_2);

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
			query.append(StepConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(stepConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<StepConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the step configs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_GID(long groupId) {
		for (StepConfig stepConfig : findByF_GID(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(stepConfig);
		}
	}

	/**
	 * Returns the number of step configs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching step configs
	 */
	@Override
	public int countByF_GID(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_STEPCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_GID_GROUPID_2);

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

	private static final String _FINDER_COLUMN_F_GID_GROUPID_2 = "stepConfig.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_MS_SS = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, StepConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_MS_SS",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_MS_SS =
		new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, StepConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_MS_SS",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			StepConfigModelImpl.GROUPID_COLUMN_BITMASK |
			StepConfigModelImpl.DOSSIERSTATUS_COLUMN_BITMASK |
			StepConfigModelImpl.DOSSIERSUBSTATUS_COLUMN_BITMASK |
			StepConfigModelImpl.STEPCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_MS_SS = new FinderPath(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_MS_SS",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @return the matching step configs
	 */
	@Override
	public List<StepConfig> findByF_MS_SS(long groupId, String dossierStatus,
		String dossierSubStatus) {
		return findByF_MS_SS(groupId, dossierStatus, dossierSubStatus,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @return the range of matching step configs
	 */
	@Override
	public List<StepConfig> findByF_MS_SS(long groupId, String dossierStatus,
		String dossierSubStatus, int start, int end) {
		return findByF_MS_SS(groupId, dossierStatus, dossierSubStatus, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching step configs
	 */
	@Override
	public List<StepConfig> findByF_MS_SS(long groupId, String dossierStatus,
		String dossierSubStatus, int start, int end,
		OrderByComparator<StepConfig> orderByComparator) {
		return findByF_MS_SS(groupId, dossierStatus, dossierSubStatus, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching step configs
	 */
	@Override
	public List<StepConfig> findByF_MS_SS(long groupId, String dossierStatus,
		String dossierSubStatus, int start, int end,
		OrderByComparator<StepConfig> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_MS_SS;
			finderArgs = new Object[] { groupId, dossierStatus, dossierSubStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_MS_SS;
			finderArgs = new Object[] {
					groupId, dossierStatus, dossierSubStatus,
					
					start, end, orderByComparator
				};
		}

		List<StepConfig> list = null;

		if (retrieveFromCache) {
			list = (List<StepConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StepConfig stepConfig : list) {
					if ((groupId != stepConfig.getGroupId()) ||
							!Objects.equals(dossierStatus,
								stepConfig.getDossierStatus()) ||
							!Objects.equals(dossierSubStatus,
								stepConfig.getDossierSubStatus())) {
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

			query.append(_SQL_SELECT_STEPCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_MS_SS_GROUPID_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSTATUS_2);
			}

			boolean bindDossierSubStatus = false;

			if (dossierSubStatus == null) {
				query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSUBSTATUS_1);
			}
			else if (dossierSubStatus.equals("")) {
				query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSUBSTATUS_3);
			}
			else {
				bindDossierSubStatus = true;

				query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSUBSTATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(StepConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (bindDossierSubStatus) {
					qPos.add(dossierSubStatus);
				}

				if (!pagination) {
					list = (List<StepConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StepConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching step config
	 * @throws NoSuchStepConfigException if a matching step config could not be found
	 */
	@Override
	public StepConfig findByF_MS_SS_First(long groupId, String dossierStatus,
		String dossierSubStatus, OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = fetchByF_MS_SS_First(groupId, dossierStatus,
				dossierSubStatus, orderByComparator);

		if (stepConfig != null) {
			return stepConfig;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", dossierSubStatus=");
		msg.append(dossierSubStatus);

		msg.append("}");

		throw new NoSuchStepConfigException(msg.toString());
	}

	/**
	 * Returns the first step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching step config, or <code>null</code> if a matching step config could not be found
	 */
	@Override
	public StepConfig fetchByF_MS_SS_First(long groupId, String dossierStatus,
		String dossierSubStatus, OrderByComparator<StepConfig> orderByComparator) {
		List<StepConfig> list = findByF_MS_SS(groupId, dossierStatus,
				dossierSubStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching step config
	 * @throws NoSuchStepConfigException if a matching step config could not be found
	 */
	@Override
	public StepConfig findByF_MS_SS_Last(long groupId, String dossierStatus,
		String dossierSubStatus, OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = fetchByF_MS_SS_Last(groupId, dossierStatus,
				dossierSubStatus, orderByComparator);

		if (stepConfig != null) {
			return stepConfig;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierStatus=");
		msg.append(dossierStatus);

		msg.append(", dossierSubStatus=");
		msg.append(dossierSubStatus);

		msg.append("}");

		throw new NoSuchStepConfigException(msg.toString());
	}

	/**
	 * Returns the last step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching step config, or <code>null</code> if a matching step config could not be found
	 */
	@Override
	public StepConfig fetchByF_MS_SS_Last(long groupId, String dossierStatus,
		String dossierSubStatus, OrderByComparator<StepConfig> orderByComparator) {
		int count = countByF_MS_SS(groupId, dossierStatus, dossierSubStatus);

		if (count == 0) {
			return null;
		}

		List<StepConfig> list = findByF_MS_SS(groupId, dossierStatus,
				dossierSubStatus, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the step configs before and after the current step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	 *
	 * @param stepConfigId the primary key of the current step config
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next step config
	 * @throws NoSuchStepConfigException if a step config with the primary key could not be found
	 */
	@Override
	public StepConfig[] findByF_MS_SS_PrevAndNext(long stepConfigId,
		long groupId, String dossierStatus, String dossierSubStatus,
		OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = findByPrimaryKey(stepConfigId);

		Session session = null;

		try {
			session = openSession();

			StepConfig[] array = new StepConfigImpl[3];

			array[0] = getByF_MS_SS_PrevAndNext(session, stepConfig, groupId,
					dossierStatus, dossierSubStatus, orderByComparator, true);

			array[1] = stepConfig;

			array[2] = getByF_MS_SS_PrevAndNext(session, stepConfig, groupId,
					dossierStatus, dossierSubStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected StepConfig getByF_MS_SS_PrevAndNext(Session session,
		StepConfig stepConfig, long groupId, String dossierStatus,
		String dossierSubStatus,
		OrderByComparator<StepConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_STEPCONFIG_WHERE);

		query.append(_FINDER_COLUMN_F_MS_SS_GROUPID_2);

		boolean bindDossierStatus = false;

		if (dossierStatus == null) {
			query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSTATUS_1);
		}
		else if (dossierStatus.equals("")) {
			query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSTATUS_3);
		}
		else {
			bindDossierStatus = true;

			query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSTATUS_2);
		}

		boolean bindDossierSubStatus = false;

		if (dossierSubStatus == null) {
			query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSUBSTATUS_1);
		}
		else if (dossierSubStatus.equals("")) {
			query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSUBSTATUS_3);
		}
		else {
			bindDossierSubStatus = true;

			query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSUBSTATUS_2);
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
			query.append(StepConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindDossierStatus) {
			qPos.add(dossierStatus);
		}

		if (bindDossierSubStatus) {
			qPos.add(dossierSubStatus);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(stepConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<StepConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 */
	@Override
	public void removeByF_MS_SS(long groupId, String dossierStatus,
		String dossierSubStatus) {
		for (StepConfig stepConfig : findByF_MS_SS(groupId, dossierStatus,
				dossierSubStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(stepConfig);
		}
	}

	/**
	 * Returns the number of step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierStatus the dossier status
	 * @param dossierSubStatus the dossier sub status
	 * @return the number of matching step configs
	 */
	@Override
	public int countByF_MS_SS(long groupId, String dossierStatus,
		String dossierSubStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_MS_SS;

		Object[] finderArgs = new Object[] {
				groupId, dossierStatus, dossierSubStatus
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_STEPCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_MS_SS_GROUPID_2);

			boolean bindDossierStatus = false;

			if (dossierStatus == null) {
				query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSTATUS_1);
			}
			else if (dossierStatus.equals("")) {
				query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSTATUS_3);
			}
			else {
				bindDossierStatus = true;

				query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSTATUS_2);
			}

			boolean bindDossierSubStatus = false;

			if (dossierSubStatus == null) {
				query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSUBSTATUS_1);
			}
			else if (dossierSubStatus.equals("")) {
				query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSUBSTATUS_3);
			}
			else {
				bindDossierSubStatus = true;

				query.append(_FINDER_COLUMN_F_MS_SS_DOSSIERSUBSTATUS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierStatus) {
					qPos.add(dossierStatus);
				}

				if (bindDossierSubStatus) {
					qPos.add(dossierSubStatus);
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

	private static final String _FINDER_COLUMN_F_MS_SS_GROUPID_2 = "stepConfig.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_MS_SS_DOSSIERSTATUS_1 = "stepConfig.dossierStatus IS NULL AND ";
	private static final String _FINDER_COLUMN_F_MS_SS_DOSSIERSTATUS_2 = "stepConfig.dossierStatus = ? AND ";
	private static final String _FINDER_COLUMN_F_MS_SS_DOSSIERSTATUS_3 = "(stepConfig.dossierStatus IS NULL OR stepConfig.dossierStatus = '') AND ";
	private static final String _FINDER_COLUMN_F_MS_SS_DOSSIERSUBSTATUS_1 = "stepConfig.dossierSubStatus IS NULL";
	private static final String _FINDER_COLUMN_F_MS_SS_DOSSIERSUBSTATUS_2 = "stepConfig.dossierSubStatus = ?";
	private static final String _FINDER_COLUMN_F_MS_SS_DOSSIERSUBSTATUS_3 = "(stepConfig.dossierSubStatus IS NULL OR stepConfig.dossierSubStatus = '')";

	public StepConfigPersistenceImpl() {
		setModelClass(StepConfig.class);

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
	 * Caches the step config in the entity cache if it is enabled.
	 *
	 * @param stepConfig the step config
	 */
	@Override
	public void cacheResult(StepConfig stepConfig) {
		entityCache.putResult(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigImpl.class, stepConfig.getPrimaryKey(), stepConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { stepConfig.getUuid(), stepConfig.getGroupId() },
			stepConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_BY_STEPCODE,
			new Object[] { stepConfig.getGroupId(), stepConfig.getStepCode() },
			stepConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GT_STEP,
			new Object[] { stepConfig.getGroupId(), stepConfig.getStepCode() },
			stepConfig);

		stepConfig.resetOriginalValues();
	}

	/**
	 * Caches the step configs in the entity cache if it is enabled.
	 *
	 * @param stepConfigs the step configs
	 */
	@Override
	public void cacheResult(List<StepConfig> stepConfigs) {
		for (StepConfig stepConfig : stepConfigs) {
			if (entityCache.getResult(
						StepConfigModelImpl.ENTITY_CACHE_ENABLED,
						StepConfigImpl.class, stepConfig.getPrimaryKey()) == null) {
				cacheResult(stepConfig);
			}
			else {
				stepConfig.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all step configs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StepConfigImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the step config.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StepConfig stepConfig) {
		entityCache.removeResult(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigImpl.class, stepConfig.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((StepConfigModelImpl)stepConfig, true);
	}

	@Override
	public void clearCache(List<StepConfig> stepConfigs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StepConfig stepConfig : stepConfigs) {
			entityCache.removeResult(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
				StepConfigImpl.class, stepConfig.getPrimaryKey());

			clearUniqueFindersCache((StepConfigModelImpl)stepConfig, true);
		}
	}

	protected void cacheUniqueFindersCache(
		StepConfigModelImpl stepConfigModelImpl) {
		Object[] args = new Object[] {
				stepConfigModelImpl.getUuid(), stepConfigModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			stepConfigModelImpl, false);

		args = new Object[] {
				stepConfigModelImpl.getGroupId(),
				stepConfigModelImpl.getStepCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_BY_STEPCODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_BY_STEPCODE, args,
			stepConfigModelImpl, false);

		args = new Object[] {
				stepConfigModelImpl.getGroupId(),
				stepConfigModelImpl.getStepCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GT_STEP, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GT_STEP, args,
			stepConfigModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		StepConfigModelImpl stepConfigModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					stepConfigModelImpl.getUuid(),
					stepConfigModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((stepConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					stepConfigModelImpl.getOriginalUuid(),
					stepConfigModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					stepConfigModelImpl.getGroupId(),
					stepConfigModelImpl.getStepCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_BY_STEPCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_BY_STEPCODE, args);
		}

		if ((stepConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_BY_STEPCODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					stepConfigModelImpl.getOriginalGroupId(),
					stepConfigModelImpl.getOriginalStepCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_BY_STEPCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_BY_STEPCODE, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					stepConfigModelImpl.getGroupId(),
					stepConfigModelImpl.getStepCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GT_STEP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GT_STEP, args);
		}

		if ((stepConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GT_STEP.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					stepConfigModelImpl.getOriginalGroupId(),
					stepConfigModelImpl.getOriginalStepCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GT_STEP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GT_STEP, args);
		}
	}

	/**
	 * Creates a new step config with the primary key. Does not add the step config to the database.
	 *
	 * @param stepConfigId the primary key for the new step config
	 * @return the new step config
	 */
	@Override
	public StepConfig create(long stepConfigId) {
		StepConfig stepConfig = new StepConfigImpl();

		stepConfig.setNew(true);
		stepConfig.setPrimaryKey(stepConfigId);

		String uuid = PortalUUIDUtil.generate();

		stepConfig.setUuid(uuid);

		stepConfig.setCompanyId(companyProvider.getCompanyId());

		return stepConfig;
	}

	/**
	 * Removes the step config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stepConfigId the primary key of the step config
	 * @return the step config that was removed
	 * @throws NoSuchStepConfigException if a step config with the primary key could not be found
	 */
	@Override
	public StepConfig remove(long stepConfigId)
		throws NoSuchStepConfigException {
		return remove((Serializable)stepConfigId);
	}

	/**
	 * Removes the step config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the step config
	 * @return the step config that was removed
	 * @throws NoSuchStepConfigException if a step config with the primary key could not be found
	 */
	@Override
	public StepConfig remove(Serializable primaryKey)
		throws NoSuchStepConfigException {
		Session session = null;

		try {
			session = openSession();

			StepConfig stepConfig = (StepConfig)session.get(StepConfigImpl.class,
					primaryKey);

			if (stepConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStepConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stepConfig);
		}
		catch (NoSuchStepConfigException nsee) {
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
	protected StepConfig removeImpl(StepConfig stepConfig) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stepConfig)) {
				stepConfig = (StepConfig)session.get(StepConfigImpl.class,
						stepConfig.getPrimaryKeyObj());
			}

			if (stepConfig != null) {
				session.delete(stepConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stepConfig != null) {
			clearCache(stepConfig);
		}

		return stepConfig;
	}

	@Override
	public StepConfig updateImpl(StepConfig stepConfig) {
		boolean isNew = stepConfig.isNew();

		if (!(stepConfig instanceof StepConfigModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(stepConfig.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(stepConfig);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in stepConfig proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom StepConfig implementation " +
				stepConfig.getClass());
		}

		StepConfigModelImpl stepConfigModelImpl = (StepConfigModelImpl)stepConfig;

		if (Validator.isNull(stepConfig.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			stepConfig.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (stepConfig.getCreateDate() == null)) {
			if (serviceContext == null) {
				stepConfig.setCreateDate(now);
			}
			else {
				stepConfig.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!stepConfigModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				stepConfig.setModifiedDate(now);
			}
			else {
				stepConfig.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (stepConfig.isNew()) {
				session.save(stepConfig);

				stepConfig.setNew(false);
			}
			else {
				stepConfig = (StepConfig)session.merge(stepConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!StepConfigModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { stepConfigModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					stepConfigModelImpl.getUuid(),
					stepConfigModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { stepConfigModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
				args);

			args = new Object[] {
					stepConfigModelImpl.getGroupId(),
					stepConfigModelImpl.getDossierStatus(),
					stepConfigModelImpl.getDossierSubStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_MS_SS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_MS_SS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((stepConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						stepConfigModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { stepConfigModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((stepConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						stepConfigModelImpl.getOriginalUuid(),
						stepConfigModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						stepConfigModelImpl.getUuid(),
						stepConfigModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((stepConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						stepConfigModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
					args);

				args = new Object[] { stepConfigModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
					args);
			}

			if ((stepConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_MS_SS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						stepConfigModelImpl.getOriginalGroupId(),
						stepConfigModelImpl.getOriginalDossierStatus(),
						stepConfigModelImpl.getOriginalDossierSubStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_MS_SS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_MS_SS,
					args);

				args = new Object[] {
						stepConfigModelImpl.getGroupId(),
						stepConfigModelImpl.getDossierStatus(),
						stepConfigModelImpl.getDossierSubStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_MS_SS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_MS_SS,
					args);
			}
		}

		entityCache.putResult(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
			StepConfigImpl.class, stepConfig.getPrimaryKey(), stepConfig, false);

		clearUniqueFindersCache(stepConfigModelImpl, false);
		cacheUniqueFindersCache(stepConfigModelImpl);

		stepConfig.resetOriginalValues();

		return stepConfig;
	}

	/**
	 * Returns the step config with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the step config
	 * @return the step config
	 * @throws NoSuchStepConfigException if a step config with the primary key could not be found
	 */
	@Override
	public StepConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStepConfigException {
		StepConfig stepConfig = fetchByPrimaryKey(primaryKey);

		if (stepConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStepConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stepConfig;
	}

	/**
	 * Returns the step config with the primary key or throws a {@link NoSuchStepConfigException} if it could not be found.
	 *
	 * @param stepConfigId the primary key of the step config
	 * @return the step config
	 * @throws NoSuchStepConfigException if a step config with the primary key could not be found
	 */
	@Override
	public StepConfig findByPrimaryKey(long stepConfigId)
		throws NoSuchStepConfigException {
		return findByPrimaryKey((Serializable)stepConfigId);
	}

	/**
	 * Returns the step config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the step config
	 * @return the step config, or <code>null</code> if a step config with the primary key could not be found
	 */
	@Override
	public StepConfig fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
				StepConfigImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StepConfig stepConfig = (StepConfig)serializable;

		if (stepConfig == null) {
			Session session = null;

			try {
				session = openSession();

				stepConfig = (StepConfig)session.get(StepConfigImpl.class,
						primaryKey);

				if (stepConfig != null) {
					cacheResult(stepConfig);
				}
				else {
					entityCache.putResult(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
						StepConfigImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
					StepConfigImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stepConfig;
	}

	/**
	 * Returns the step config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stepConfigId the primary key of the step config
	 * @return the step config, or <code>null</code> if a step config with the primary key could not be found
	 */
	@Override
	public StepConfig fetchByPrimaryKey(long stepConfigId) {
		return fetchByPrimaryKey((Serializable)stepConfigId);
	}

	@Override
	public Map<Serializable, StepConfig> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StepConfig> map = new HashMap<Serializable, StepConfig>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			StepConfig stepConfig = fetchByPrimaryKey(primaryKey);

			if (stepConfig != null) {
				map.put(primaryKey, stepConfig);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
					StepConfigImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (StepConfig)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_STEPCONFIG_WHERE_PKS_IN);

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

			for (StepConfig stepConfig : (List<StepConfig>)q.list()) {
				map.put(stepConfig.getPrimaryKeyObj(), stepConfig);

				cacheResult(stepConfig);

				uncachedPrimaryKeys.remove(stepConfig.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(StepConfigModelImpl.ENTITY_CACHE_ENABLED,
					StepConfigImpl.class, primaryKey, nullModel);
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
	 * Returns all the step configs.
	 *
	 * @return the step configs
	 */
	@Override
	public List<StepConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the step configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @return the range of step configs
	 */
	@Override
	public List<StepConfig> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the step configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of step configs
	 */
	@Override
	public List<StepConfig> findAll(int start, int end,
		OrderByComparator<StepConfig> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the step configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of step configs
	 * @param end the upper bound of the range of step configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of step configs
	 */
	@Override
	public List<StepConfig> findAll(int start, int end,
		OrderByComparator<StepConfig> orderByComparator,
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

		List<StepConfig> list = null;

		if (retrieveFromCache) {
			list = (List<StepConfig>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STEPCONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STEPCONFIG;

				if (pagination) {
					sql = sql.concat(StepConfigModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StepConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StepConfig>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the step configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StepConfig stepConfig : findAll()) {
			remove(stepConfig);
		}
	}

	/**
	 * Returns the number of step configs.
	 *
	 * @return the number of step configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STEPCONFIG);

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
		return StepConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the step config persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StepConfigImpl.class.getName());
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
	private static final String _SQL_SELECT_STEPCONFIG = "SELECT stepConfig FROM StepConfig stepConfig";
	private static final String _SQL_SELECT_STEPCONFIG_WHERE_PKS_IN = "SELECT stepConfig FROM StepConfig stepConfig WHERE stepConfigId IN (";
	private static final String _SQL_SELECT_STEPCONFIG_WHERE = "SELECT stepConfig FROM StepConfig stepConfig WHERE ";
	private static final String _SQL_COUNT_STEPCONFIG = "SELECT COUNT(stepConfig) FROM StepConfig stepConfig";
	private static final String _SQL_COUNT_STEPCONFIG_WHERE = "SELECT COUNT(stepConfig) FROM StepConfig stepConfig WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stepConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StepConfig exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No StepConfig exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(StepConfigPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}