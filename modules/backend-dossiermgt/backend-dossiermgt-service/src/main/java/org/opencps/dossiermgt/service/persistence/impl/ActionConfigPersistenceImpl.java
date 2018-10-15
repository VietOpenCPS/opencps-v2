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

import org.opencps.dossiermgt.exception.NoSuchActionConfigException;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.impl.ActionConfigImpl;
import org.opencps.dossiermgt.model.impl.ActionConfigModelImpl;
import org.opencps.dossiermgt.service.persistence.ActionConfigPersistence;

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
 * The persistence implementation for the action config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ActionConfigPersistence
 * @see org.opencps.dossiermgt.service.persistence.ActionConfigUtil
 * @generated
 */
@ProviderType
public class ActionConfigPersistenceImpl extends BasePersistenceImpl<ActionConfig>
	implements ActionConfigPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ActionConfigUtil} to access the action config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ActionConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, ActionConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, ActionConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, ActionConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, ActionConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ActionConfigModelImpl.UUID_COLUMN_BITMASK |
			ActionConfigModelImpl.ACTIONCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the action configs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching action configs
	 */
	@Override
	public List<ActionConfig> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the action configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of action configs
	 * @param end the upper bound of the range of action configs (not inclusive)
	 * @return the range of matching action configs
	 */
	@Override
	public List<ActionConfig> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the action configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of action configs
	 * @param end the upper bound of the range of action configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching action configs
	 */
	@Override
	public List<ActionConfig> findByUuid(String uuid, int start, int end,
		OrderByComparator<ActionConfig> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the action configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of action configs
	 * @param end the upper bound of the range of action configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching action configs
	 */
	@Override
	public List<ActionConfig> findByUuid(String uuid, int start, int end,
		OrderByComparator<ActionConfig> orderByComparator,
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

		List<ActionConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ActionConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActionConfig actionConfig : list) {
					if (!Objects.equals(uuid, actionConfig.getUuid())) {
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

			query.append(_SQL_SELECT_ACTIONCONFIG_WHERE);

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
				query.append(ActionConfigModelImpl.ORDER_BY_JPQL);
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
					list = (List<ActionConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActionConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first action config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action config
	 * @throws NoSuchActionConfigException if a matching action config could not be found
	 */
	@Override
	public ActionConfig findByUuid_First(String uuid,
		OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException {
		ActionConfig actionConfig = fetchByUuid_First(uuid, orderByComparator);

		if (actionConfig != null) {
			return actionConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchActionConfigException(msg.toString());
	}

	/**
	 * Returns the first action config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action config, or <code>null</code> if a matching action config could not be found
	 */
	@Override
	public ActionConfig fetchByUuid_First(String uuid,
		OrderByComparator<ActionConfig> orderByComparator) {
		List<ActionConfig> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last action config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action config
	 * @throws NoSuchActionConfigException if a matching action config could not be found
	 */
	@Override
	public ActionConfig findByUuid_Last(String uuid,
		OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException {
		ActionConfig actionConfig = fetchByUuid_Last(uuid, orderByComparator);

		if (actionConfig != null) {
			return actionConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchActionConfigException(msg.toString());
	}

	/**
	 * Returns the last action config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action config, or <code>null</code> if a matching action config could not be found
	 */
	@Override
	public ActionConfig fetchByUuid_Last(String uuid,
		OrderByComparator<ActionConfig> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ActionConfig> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the action configs before and after the current action config in the ordered set where uuid = &#63;.
	 *
	 * @param actionConfigId the primary key of the current action config
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next action config
	 * @throws NoSuchActionConfigException if a action config with the primary key could not be found
	 */
	@Override
	public ActionConfig[] findByUuid_PrevAndNext(long actionConfigId,
		String uuid, OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException {
		ActionConfig actionConfig = findByPrimaryKey(actionConfigId);

		Session session = null;

		try {
			session = openSession();

			ActionConfig[] array = new ActionConfigImpl[3];

			array[0] = getByUuid_PrevAndNext(session, actionConfig, uuid,
					orderByComparator, true);

			array[1] = actionConfig;

			array[2] = getByUuid_PrevAndNext(session, actionConfig, uuid,
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

	protected ActionConfig getByUuid_PrevAndNext(Session session,
		ActionConfig actionConfig, String uuid,
		OrderByComparator<ActionConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTIONCONFIG_WHERE);

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
			query.append(ActionConfigModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(actionConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActionConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the action configs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ActionConfig actionConfig : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(actionConfig);
		}
	}

	/**
	 * Returns the number of action configs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching action configs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIONCONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "actionConfig.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "actionConfig.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(actionConfig.uuid IS NULL OR actionConfig.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, ActionConfigImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ActionConfigModelImpl.UUID_COLUMN_BITMASK |
			ActionConfigModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the action config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchActionConfigException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching action config
	 * @throws NoSuchActionConfigException if a matching action config could not be found
	 */
	@Override
	public ActionConfig findByUUID_G(String uuid, long groupId)
		throws NoSuchActionConfigException {
		ActionConfig actionConfig = fetchByUUID_G(uuid, groupId);

		if (actionConfig == null) {
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

			throw new NoSuchActionConfigException(msg.toString());
		}

		return actionConfig;
	}

	/**
	 * Returns the action config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching action config, or <code>null</code> if a matching action config could not be found
	 */
	@Override
	public ActionConfig fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the action config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching action config, or <code>null</code> if a matching action config could not be found
	 */
	@Override
	public ActionConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ActionConfig) {
			ActionConfig actionConfig = (ActionConfig)result;

			if (!Objects.equals(uuid, actionConfig.getUuid()) ||
					(groupId != actionConfig.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ACTIONCONFIG_WHERE);

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

				List<ActionConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ActionConfig actionConfig = list.get(0);

					result = actionConfig;

					cacheResult(actionConfig);
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
			return (ActionConfig)result;
		}
	}

	/**
	 * Removes the action config where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the action config that was removed
	 */
	@Override
	public ActionConfig removeByUUID_G(String uuid, long groupId)
		throws NoSuchActionConfigException {
		ActionConfig actionConfig = findByUUID_G(uuid, groupId);

		return remove(actionConfig);
	}

	/**
	 * Returns the number of action configs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching action configs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACTIONCONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "actionConfig.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "actionConfig.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(actionConfig.uuid IS NULL OR actionConfig.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "actionConfig.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, ActionConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, ActionConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ActionConfigModelImpl.UUID_COLUMN_BITMASK |
			ActionConfigModelImpl.COMPANYID_COLUMN_BITMASK |
			ActionConfigModelImpl.ACTIONCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the action configs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching action configs
	 */
	@Override
	public List<ActionConfig> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the action configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of action configs
	 * @param end the upper bound of the range of action configs (not inclusive)
	 * @return the range of matching action configs
	 */
	@Override
	public List<ActionConfig> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the action configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of action configs
	 * @param end the upper bound of the range of action configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching action configs
	 */
	@Override
	public List<ActionConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ActionConfig> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the action configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of action configs
	 * @param end the upper bound of the range of action configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching action configs
	 */
	@Override
	public List<ActionConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ActionConfig> orderByComparator,
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

		List<ActionConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ActionConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActionConfig actionConfig : list) {
					if (!Objects.equals(uuid, actionConfig.getUuid()) ||
							(companyId != actionConfig.getCompanyId())) {
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

			query.append(_SQL_SELECT_ACTIONCONFIG_WHERE);

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
				query.append(ActionConfigModelImpl.ORDER_BY_JPQL);
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
					list = (List<ActionConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActionConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first action config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action config
	 * @throws NoSuchActionConfigException if a matching action config could not be found
	 */
	@Override
	public ActionConfig findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException {
		ActionConfig actionConfig = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (actionConfig != null) {
			return actionConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchActionConfigException(msg.toString());
	}

	/**
	 * Returns the first action config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action config, or <code>null</code> if a matching action config could not be found
	 */
	@Override
	public ActionConfig fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ActionConfig> orderByComparator) {
		List<ActionConfig> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last action config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action config
	 * @throws NoSuchActionConfigException if a matching action config could not be found
	 */
	@Override
	public ActionConfig findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException {
		ActionConfig actionConfig = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (actionConfig != null) {
			return actionConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchActionConfigException(msg.toString());
	}

	/**
	 * Returns the last action config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action config, or <code>null</code> if a matching action config could not be found
	 */
	@Override
	public ActionConfig fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ActionConfig> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ActionConfig> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the action configs before and after the current action config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param actionConfigId the primary key of the current action config
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next action config
	 * @throws NoSuchActionConfigException if a action config with the primary key could not be found
	 */
	@Override
	public ActionConfig[] findByUuid_C_PrevAndNext(long actionConfigId,
		String uuid, long companyId,
		OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException {
		ActionConfig actionConfig = findByPrimaryKey(actionConfigId);

		Session session = null;

		try {
			session = openSession();

			ActionConfig[] array = new ActionConfigImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, actionConfig, uuid,
					companyId, orderByComparator, true);

			array[1] = actionConfig;

			array[2] = getByUuid_C_PrevAndNext(session, actionConfig, uuid,
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

	protected ActionConfig getByUuid_C_PrevAndNext(Session session,
		ActionConfig actionConfig, String uuid, long companyId,
		OrderByComparator<ActionConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ACTIONCONFIG_WHERE);

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
			query.append(ActionConfigModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(actionConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActionConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the action configs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ActionConfig actionConfig : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(actionConfig);
		}
	}

	/**
	 * Returns the number of action configs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching action configs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACTIONCONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "actionConfig.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "actionConfig.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(actionConfig.uuid IS NULL OR actionConfig.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "actionConfig.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_BY_GID = new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, ActionConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_BY_GID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_BY_GID =
		new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, ActionConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_BY_GID",
			new String[] { Long.class.getName() },
			ActionConfigModelImpl.GROUPID_COLUMN_BITMASK |
			ActionConfigModelImpl.ACTIONCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_BY_GID = new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_BY_GID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the action configs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching action configs
	 */
	@Override
	public List<ActionConfig> findByF_BY_GID(long groupId) {
		return findByF_BY_GID(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the action configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of action configs
	 * @param end the upper bound of the range of action configs (not inclusive)
	 * @return the range of matching action configs
	 */
	@Override
	public List<ActionConfig> findByF_BY_GID(long groupId, int start, int end) {
		return findByF_BY_GID(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the action configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of action configs
	 * @param end the upper bound of the range of action configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching action configs
	 */
	@Override
	public List<ActionConfig> findByF_BY_GID(long groupId, int start, int end,
		OrderByComparator<ActionConfig> orderByComparator) {
		return findByF_BY_GID(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the action configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of action configs
	 * @param end the upper bound of the range of action configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching action configs
	 */
	@Override
	public List<ActionConfig> findByF_BY_GID(long groupId, int start, int end,
		OrderByComparator<ActionConfig> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_BY_GID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_BY_GID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<ActionConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ActionConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActionConfig actionConfig : list) {
					if ((groupId != actionConfig.getGroupId())) {
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

			query.append(_SQL_SELECT_ACTIONCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_BY_GID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActionConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ActionConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActionConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first action config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action config
	 * @throws NoSuchActionConfigException if a matching action config could not be found
	 */
	@Override
	public ActionConfig findByF_BY_GID_First(long groupId,
		OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException {
		ActionConfig actionConfig = fetchByF_BY_GID_First(groupId,
				orderByComparator);

		if (actionConfig != null) {
			return actionConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchActionConfigException(msg.toString());
	}

	/**
	 * Returns the first action config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching action config, or <code>null</code> if a matching action config could not be found
	 */
	@Override
	public ActionConfig fetchByF_BY_GID_First(long groupId,
		OrderByComparator<ActionConfig> orderByComparator) {
		List<ActionConfig> list = findByF_BY_GID(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last action config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action config
	 * @throws NoSuchActionConfigException if a matching action config could not be found
	 */
	@Override
	public ActionConfig findByF_BY_GID_Last(long groupId,
		OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException {
		ActionConfig actionConfig = fetchByF_BY_GID_Last(groupId,
				orderByComparator);

		if (actionConfig != null) {
			return actionConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchActionConfigException(msg.toString());
	}

	/**
	 * Returns the last action config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching action config, or <code>null</code> if a matching action config could not be found
	 */
	@Override
	public ActionConfig fetchByF_BY_GID_Last(long groupId,
		OrderByComparator<ActionConfig> orderByComparator) {
		int count = countByF_BY_GID(groupId);

		if (count == 0) {
			return null;
		}

		List<ActionConfig> list = findByF_BY_GID(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the action configs before and after the current action config in the ordered set where groupId = &#63;.
	 *
	 * @param actionConfigId the primary key of the current action config
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next action config
	 * @throws NoSuchActionConfigException if a action config with the primary key could not be found
	 */
	@Override
	public ActionConfig[] findByF_BY_GID_PrevAndNext(long actionConfigId,
		long groupId, OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException {
		ActionConfig actionConfig = findByPrimaryKey(actionConfigId);

		Session session = null;

		try {
			session = openSession();

			ActionConfig[] array = new ActionConfigImpl[3];

			array[0] = getByF_BY_GID_PrevAndNext(session, actionConfig,
					groupId, orderByComparator, true);

			array[1] = actionConfig;

			array[2] = getByF_BY_GID_PrevAndNext(session, actionConfig,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActionConfig getByF_BY_GID_PrevAndNext(Session session,
		ActionConfig actionConfig, long groupId,
		OrderByComparator<ActionConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTIONCONFIG_WHERE);

		query.append(_FINDER_COLUMN_F_BY_GID_GROUPID_2);

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
			query.append(ActionConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actionConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActionConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the action configs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_BY_GID(long groupId) {
		for (ActionConfig actionConfig : findByF_BY_GID(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(actionConfig);
		}
	}

	/**
	 * Returns the number of action configs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching action configs
	 */
	@Override
	public int countByF_BY_GID(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_BY_GID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTIONCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_BY_GID_GROUPID_2);

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

	private static final String _FINDER_COLUMN_F_BY_GID_GROUPID_2 = "actionConfig.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_BY_ACTIONCODE = new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, ActionConfigImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_BY_ActionCode",
			new String[] { Long.class.getName(), String.class.getName() },
			ActionConfigModelImpl.GROUPID_COLUMN_BITMASK |
			ActionConfigModelImpl.ACTIONCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_BY_ACTIONCODE = new FinderPath(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_BY_ActionCode",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the action config where groupId = &#63; and actionCode = &#63; or throws a {@link NoSuchActionConfigException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @return the matching action config
	 * @throws NoSuchActionConfigException if a matching action config could not be found
	 */
	@Override
	public ActionConfig findByF_BY_ActionCode(long groupId, String actionCode)
		throws NoSuchActionConfigException {
		ActionConfig actionConfig = fetchByF_BY_ActionCode(groupId, actionCode);

		if (actionConfig == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", actionCode=");
			msg.append(actionCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchActionConfigException(msg.toString());
		}

		return actionConfig;
	}

	/**
	 * Returns the action config where groupId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @return the matching action config, or <code>null</code> if a matching action config could not be found
	 */
	@Override
	public ActionConfig fetchByF_BY_ActionCode(long groupId, String actionCode) {
		return fetchByF_BY_ActionCode(groupId, actionCode, true);
	}

	/**
	 * Returns the action config where groupId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching action config, or <code>null</code> if a matching action config could not be found
	 */
	@Override
	public ActionConfig fetchByF_BY_ActionCode(long groupId, String actionCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, actionCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_BY_ACTIONCODE,
					finderArgs, this);
		}

		if (result instanceof ActionConfig) {
			ActionConfig actionConfig = (ActionConfig)result;

			if ((groupId != actionConfig.getGroupId()) ||
					!Objects.equals(actionCode, actionConfig.getActionCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ACTIONCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_BY_ACTIONCODE_GROUPID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_F_BY_ACTIONCODE_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_F_BY_ACTIONCODE_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_F_BY_ACTIONCODE_ACTIONCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindActionCode) {
					qPos.add(actionCode);
				}

				List<ActionConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_BY_ACTIONCODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ActionConfigPersistenceImpl.fetchByF_BY_ActionCode(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ActionConfig actionConfig = list.get(0);

					result = actionConfig;

					cacheResult(actionConfig);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_BY_ACTIONCODE,
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
			return (ActionConfig)result;
		}
	}

	/**
	 * Removes the action config where groupId = &#63; and actionCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @return the action config that was removed
	 */
	@Override
	public ActionConfig removeByF_BY_ActionCode(long groupId, String actionCode)
		throws NoSuchActionConfigException {
		ActionConfig actionConfig = findByF_BY_ActionCode(groupId, actionCode);

		return remove(actionConfig);
	}

	/**
	 * Returns the number of action configs where groupId = &#63; and actionCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param actionCode the action code
	 * @return the number of matching action configs
	 */
	@Override
	public int countByF_BY_ActionCode(long groupId, String actionCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_BY_ACTIONCODE;

		Object[] finderArgs = new Object[] { groupId, actionCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACTIONCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_BY_ACTIONCODE_GROUPID_2);

			boolean bindActionCode = false;

			if (actionCode == null) {
				query.append(_FINDER_COLUMN_F_BY_ACTIONCODE_ACTIONCODE_1);
			}
			else if (actionCode.equals("")) {
				query.append(_FINDER_COLUMN_F_BY_ACTIONCODE_ACTIONCODE_3);
			}
			else {
				bindActionCode = true;

				query.append(_FINDER_COLUMN_F_BY_ACTIONCODE_ACTIONCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindActionCode) {
					qPos.add(actionCode);
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

	private static final String _FINDER_COLUMN_F_BY_ACTIONCODE_GROUPID_2 = "actionConfig.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_BY_ACTIONCODE_ACTIONCODE_1 = "actionConfig.actionCode IS NULL";
	private static final String _FINDER_COLUMN_F_BY_ACTIONCODE_ACTIONCODE_2 = "actionConfig.actionCode = ?";
	private static final String _FINDER_COLUMN_F_BY_ACTIONCODE_ACTIONCODE_3 = "(actionConfig.actionCode IS NULL OR actionConfig.actionCode = '')";

	public ActionConfigPersistenceImpl() {
		setModelClass(ActionConfig.class);

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
	 * Caches the action config in the entity cache if it is enabled.
	 *
	 * @param actionConfig the action config
	 */
	@Override
	public void cacheResult(ActionConfig actionConfig) {
		entityCache.putResult(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigImpl.class, actionConfig.getPrimaryKey(), actionConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { actionConfig.getUuid(), actionConfig.getGroupId() },
			actionConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_BY_ACTIONCODE,
			new Object[] { actionConfig.getGroupId(), actionConfig.getActionCode() },
			actionConfig);

		actionConfig.resetOriginalValues();
	}

	/**
	 * Caches the action configs in the entity cache if it is enabled.
	 *
	 * @param actionConfigs the action configs
	 */
	@Override
	public void cacheResult(List<ActionConfig> actionConfigs) {
		for (ActionConfig actionConfig : actionConfigs) {
			if (entityCache.getResult(
						ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
						ActionConfigImpl.class, actionConfig.getPrimaryKey()) == null) {
				cacheResult(actionConfig);
			}
			else {
				actionConfig.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all action configs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ActionConfigImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the action config.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ActionConfig actionConfig) {
		entityCache.removeResult(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigImpl.class, actionConfig.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ActionConfigModelImpl)actionConfig, true);
	}

	@Override
	public void clearCache(List<ActionConfig> actionConfigs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ActionConfig actionConfig : actionConfigs) {
			entityCache.removeResult(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
				ActionConfigImpl.class, actionConfig.getPrimaryKey());

			clearUniqueFindersCache((ActionConfigModelImpl)actionConfig, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ActionConfigModelImpl actionConfigModelImpl) {
		Object[] args = new Object[] {
				actionConfigModelImpl.getUuid(),
				actionConfigModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			actionConfigModelImpl, false);

		args = new Object[] {
				actionConfigModelImpl.getGroupId(),
				actionConfigModelImpl.getActionCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_BY_ACTIONCODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_BY_ACTIONCODE, args,
			actionConfigModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ActionConfigModelImpl actionConfigModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					actionConfigModelImpl.getUuid(),
					actionConfigModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((actionConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					actionConfigModelImpl.getOriginalUuid(),
					actionConfigModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					actionConfigModelImpl.getGroupId(),
					actionConfigModelImpl.getActionCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_BY_ACTIONCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_BY_ACTIONCODE, args);
		}

		if ((actionConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_BY_ACTIONCODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					actionConfigModelImpl.getOriginalGroupId(),
					actionConfigModelImpl.getOriginalActionCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_BY_ACTIONCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_BY_ACTIONCODE, args);
		}
	}

	/**
	 * Creates a new action config with the primary key. Does not add the action config to the database.
	 *
	 * @param actionConfigId the primary key for the new action config
	 * @return the new action config
	 */
	@Override
	public ActionConfig create(long actionConfigId) {
		ActionConfig actionConfig = new ActionConfigImpl();

		actionConfig.setNew(true);
		actionConfig.setPrimaryKey(actionConfigId);

		String uuid = PortalUUIDUtil.generate();

		actionConfig.setUuid(uuid);

		actionConfig.setCompanyId(companyProvider.getCompanyId());

		return actionConfig;
	}

	/**
	 * Removes the action config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param actionConfigId the primary key of the action config
	 * @return the action config that was removed
	 * @throws NoSuchActionConfigException if a action config with the primary key could not be found
	 */
	@Override
	public ActionConfig remove(long actionConfigId)
		throws NoSuchActionConfigException {
		return remove((Serializable)actionConfigId);
	}

	/**
	 * Removes the action config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the action config
	 * @return the action config that was removed
	 * @throws NoSuchActionConfigException if a action config with the primary key could not be found
	 */
	@Override
	public ActionConfig remove(Serializable primaryKey)
		throws NoSuchActionConfigException {
		Session session = null;

		try {
			session = openSession();

			ActionConfig actionConfig = (ActionConfig)session.get(ActionConfigImpl.class,
					primaryKey);

			if (actionConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchActionConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(actionConfig);
		}
		catch (NoSuchActionConfigException nsee) {
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
	protected ActionConfig removeImpl(ActionConfig actionConfig) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(actionConfig)) {
				actionConfig = (ActionConfig)session.get(ActionConfigImpl.class,
						actionConfig.getPrimaryKeyObj());
			}

			if (actionConfig != null) {
				session.delete(actionConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (actionConfig != null) {
			clearCache(actionConfig);
		}

		return actionConfig;
	}

	@Override
	public ActionConfig updateImpl(ActionConfig actionConfig) {
		boolean isNew = actionConfig.isNew();

		if (!(actionConfig instanceof ActionConfigModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(actionConfig.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(actionConfig);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in actionConfig proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ActionConfig implementation " +
				actionConfig.getClass());
		}

		ActionConfigModelImpl actionConfigModelImpl = (ActionConfigModelImpl)actionConfig;

		if (Validator.isNull(actionConfig.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			actionConfig.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (actionConfig.getCreateDate() == null)) {
			if (serviceContext == null) {
				actionConfig.setCreateDate(now);
			}
			else {
				actionConfig.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!actionConfigModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				actionConfig.setModifiedDate(now);
			}
			else {
				actionConfig.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (actionConfig.isNew()) {
				session.save(actionConfig);

				actionConfig.setNew(false);
			}
			else {
				actionConfig = (ActionConfig)session.merge(actionConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ActionConfigModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { actionConfigModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					actionConfigModelImpl.getUuid(),
					actionConfigModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { actionConfigModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_BY_GID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_BY_GID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((actionConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actionConfigModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { actionConfigModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((actionConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actionConfigModelImpl.getOriginalUuid(),
						actionConfigModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						actionConfigModelImpl.getUuid(),
						actionConfigModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((actionConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_BY_GID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actionConfigModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_BY_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_BY_GID,
					args);

				args = new Object[] { actionConfigModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_BY_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_BY_GID,
					args);
			}
		}

		entityCache.putResult(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
			ActionConfigImpl.class, actionConfig.getPrimaryKey(), actionConfig,
			false);

		clearUniqueFindersCache(actionConfigModelImpl, false);
		cacheUniqueFindersCache(actionConfigModelImpl);

		actionConfig.resetOriginalValues();

		return actionConfig;
	}

	/**
	 * Returns the action config with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the action config
	 * @return the action config
	 * @throws NoSuchActionConfigException if a action config with the primary key could not be found
	 */
	@Override
	public ActionConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchActionConfigException {
		ActionConfig actionConfig = fetchByPrimaryKey(primaryKey);

		if (actionConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchActionConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return actionConfig;
	}

	/**
	 * Returns the action config with the primary key or throws a {@link NoSuchActionConfigException} if it could not be found.
	 *
	 * @param actionConfigId the primary key of the action config
	 * @return the action config
	 * @throws NoSuchActionConfigException if a action config with the primary key could not be found
	 */
	@Override
	public ActionConfig findByPrimaryKey(long actionConfigId)
		throws NoSuchActionConfigException {
		return findByPrimaryKey((Serializable)actionConfigId);
	}

	/**
	 * Returns the action config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the action config
	 * @return the action config, or <code>null</code> if a action config with the primary key could not be found
	 */
	@Override
	public ActionConfig fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
				ActionConfigImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ActionConfig actionConfig = (ActionConfig)serializable;

		if (actionConfig == null) {
			Session session = null;

			try {
				session = openSession();

				actionConfig = (ActionConfig)session.get(ActionConfigImpl.class,
						primaryKey);

				if (actionConfig != null) {
					cacheResult(actionConfig);
				}
				else {
					entityCache.putResult(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
						ActionConfigImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
					ActionConfigImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return actionConfig;
	}

	/**
	 * Returns the action config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param actionConfigId the primary key of the action config
	 * @return the action config, or <code>null</code> if a action config with the primary key could not be found
	 */
	@Override
	public ActionConfig fetchByPrimaryKey(long actionConfigId) {
		return fetchByPrimaryKey((Serializable)actionConfigId);
	}

	@Override
	public Map<Serializable, ActionConfig> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ActionConfig> map = new HashMap<Serializable, ActionConfig>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ActionConfig actionConfig = fetchByPrimaryKey(primaryKey);

			if (actionConfig != null) {
				map.put(primaryKey, actionConfig);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
					ActionConfigImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ActionConfig)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACTIONCONFIG_WHERE_PKS_IN);

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

			for (ActionConfig actionConfig : (List<ActionConfig>)q.list()) {
				map.put(actionConfig.getPrimaryKeyObj(), actionConfig);

				cacheResult(actionConfig);

				uncachedPrimaryKeys.remove(actionConfig.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ActionConfigModelImpl.ENTITY_CACHE_ENABLED,
					ActionConfigImpl.class, primaryKey, nullModel);
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
	 * Returns all the action configs.
	 *
	 * @return the action configs
	 */
	@Override
	public List<ActionConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the action configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of action configs
	 * @param end the upper bound of the range of action configs (not inclusive)
	 * @return the range of action configs
	 */
	@Override
	public List<ActionConfig> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the action configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of action configs
	 * @param end the upper bound of the range of action configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of action configs
	 */
	@Override
	public List<ActionConfig> findAll(int start, int end,
		OrderByComparator<ActionConfig> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the action configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of action configs
	 * @param end the upper bound of the range of action configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of action configs
	 */
	@Override
	public List<ActionConfig> findAll(int start, int end,
		OrderByComparator<ActionConfig> orderByComparator,
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

		List<ActionConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ActionConfig>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACTIONCONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACTIONCONFIG;

				if (pagination) {
					sql = sql.concat(ActionConfigModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ActionConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActionConfig>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the action configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ActionConfig actionConfig : findAll()) {
			remove(actionConfig);
		}
	}

	/**
	 * Returns the number of action configs.
	 *
	 * @return the number of action configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACTIONCONFIG);

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
		return ActionConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the action config persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ActionConfigImpl.class.getName());
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
	private static final String _SQL_SELECT_ACTIONCONFIG = "SELECT actionConfig FROM ActionConfig actionConfig";
	private static final String _SQL_SELECT_ACTIONCONFIG_WHERE_PKS_IN = "SELECT actionConfig FROM ActionConfig actionConfig WHERE actionConfigId IN (";
	private static final String _SQL_SELECT_ACTIONCONFIG_WHERE = "SELECT actionConfig FROM ActionConfig actionConfig WHERE ";
	private static final String _SQL_COUNT_ACTIONCONFIG = "SELECT COUNT(actionConfig) FROM ActionConfig actionConfig";
	private static final String _SQL_COUNT_ACTIONCONFIG_WHERE = "SELECT COUNT(actionConfig) FROM ActionConfig actionConfig WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "actionConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ActionConfig exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ActionConfig exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ActionConfigPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}