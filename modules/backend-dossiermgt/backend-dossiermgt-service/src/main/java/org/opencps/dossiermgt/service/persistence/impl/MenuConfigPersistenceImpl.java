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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchMenuConfigException;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.model.impl.MenuConfigImpl;
import org.opencps.dossiermgt.model.impl.MenuConfigModelImpl;
import org.opencps.dossiermgt.service.persistence.MenuConfigPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Arrays;
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
 * The persistence implementation for the menu config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see MenuConfigPersistence
 * @see org.opencps.dossiermgt.service.persistence.MenuConfigUtil
 * @generated
 */
@ProviderType
public class MenuConfigPersistenceImpl extends BasePersistenceImpl<MenuConfig>
	implements MenuConfigPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MenuConfigUtil} to access the menu config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MenuConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, MenuConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, MenuConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, MenuConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, MenuConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			MenuConfigModelImpl.UUID_COLUMN_BITMASK |
			MenuConfigModelImpl.ORDER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the menu configs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching menu configs
	 */
	@Override
	public List<MenuConfig> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the menu configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @return the range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the menu configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByUuid(String uuid, int start, int end,
		OrderByComparator<MenuConfig> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the menu configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByUuid(String uuid, int start, int end,
		OrderByComparator<MenuConfig> orderByComparator,
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

		List<MenuConfig> list = null;

		if (retrieveFromCache) {
			list = (List<MenuConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MenuConfig menuConfig : list) {
					if (!Objects.equals(uuid, menuConfig.getUuid())) {
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

			query.append(_SQL_SELECT_MENUCONFIG_WHERE);

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
				query.append(MenuConfigModelImpl.ORDER_BY_JPQL);
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
					list = (List<MenuConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MenuConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first menu config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menu config
	 * @throws NoSuchMenuConfigException if a matching menu config could not be found
	 */
	@Override
	public MenuConfig findByUuid_First(String uuid,
		OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = fetchByUuid_First(uuid, orderByComparator);

		if (menuConfig != null) {
			return menuConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchMenuConfigException(msg.toString());
	}

	/**
	 * Returns the first menu config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menu config, or <code>null</code> if a matching menu config could not be found
	 */
	@Override
	public MenuConfig fetchByUuid_First(String uuid,
		OrderByComparator<MenuConfig> orderByComparator) {
		List<MenuConfig> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last menu config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menu config
	 * @throws NoSuchMenuConfigException if a matching menu config could not be found
	 */
	@Override
	public MenuConfig findByUuid_Last(String uuid,
		OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = fetchByUuid_Last(uuid, orderByComparator);

		if (menuConfig != null) {
			return menuConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchMenuConfigException(msg.toString());
	}

	/**
	 * Returns the last menu config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menu config, or <code>null</code> if a matching menu config could not be found
	 */
	@Override
	public MenuConfig fetchByUuid_Last(String uuid,
		OrderByComparator<MenuConfig> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<MenuConfig> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the menu configs before and after the current menu config in the ordered set where uuid = &#63;.
	 *
	 * @param menuConfigId the primary key of the current menu config
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next menu config
	 * @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	 */
	@Override
	public MenuConfig[] findByUuid_PrevAndNext(long menuConfigId, String uuid,
		OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = findByPrimaryKey(menuConfigId);

		Session session = null;

		try {
			session = openSession();

			MenuConfig[] array = new MenuConfigImpl[3];

			array[0] = getByUuid_PrevAndNext(session, menuConfig, uuid,
					orderByComparator, true);

			array[1] = menuConfig;

			array[2] = getByUuid_PrevAndNext(session, menuConfig, uuid,
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

	protected MenuConfig getByUuid_PrevAndNext(Session session,
		MenuConfig menuConfig, String uuid,
		OrderByComparator<MenuConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MENUCONFIG_WHERE);

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
			query.append(MenuConfigModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(menuConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MenuConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the menu configs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (MenuConfig menuConfig : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(menuConfig);
		}
	}

	/**
	 * Returns the number of menu configs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching menu configs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MENUCONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "menuConfig.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "menuConfig.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(menuConfig.uuid IS NULL OR menuConfig.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, MenuConfigImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			MenuConfigModelImpl.UUID_COLUMN_BITMASK |
			MenuConfigModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the menu config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchMenuConfigException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching menu config
	 * @throws NoSuchMenuConfigException if a matching menu config could not be found
	 */
	@Override
	public MenuConfig findByUUID_G(String uuid, long groupId)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = fetchByUUID_G(uuid, groupId);

		if (menuConfig == null) {
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

			throw new NoSuchMenuConfigException(msg.toString());
		}

		return menuConfig;
	}

	/**
	 * Returns the menu config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching menu config, or <code>null</code> if a matching menu config could not be found
	 */
	@Override
	public MenuConfig fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the menu config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching menu config, or <code>null</code> if a matching menu config could not be found
	 */
	@Override
	public MenuConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof MenuConfig) {
			MenuConfig menuConfig = (MenuConfig)result;

			if (!Objects.equals(uuid, menuConfig.getUuid()) ||
					(groupId != menuConfig.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MENUCONFIG_WHERE);

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

				List<MenuConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					MenuConfig menuConfig = list.get(0);

					result = menuConfig;

					cacheResult(menuConfig);
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
			return (MenuConfig)result;
		}
	}

	/**
	 * Removes the menu config where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the menu config that was removed
	 */
	@Override
	public MenuConfig removeByUUID_G(String uuid, long groupId)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = findByUUID_G(uuid, groupId);

		return remove(menuConfig);
	}

	/**
	 * Returns the number of menu configs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching menu configs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MENUCONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "menuConfig.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "menuConfig.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(menuConfig.uuid IS NULL OR menuConfig.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "menuConfig.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, MenuConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, MenuConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			MenuConfigModelImpl.UUID_COLUMN_BITMASK |
			MenuConfigModelImpl.COMPANYID_COLUMN_BITMASK |
			MenuConfigModelImpl.ORDER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the menu configs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching menu configs
	 */
	@Override
	public List<MenuConfig> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the menu configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @return the range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the menu configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<MenuConfig> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the menu configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<MenuConfig> orderByComparator,
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

		List<MenuConfig> list = null;

		if (retrieveFromCache) {
			list = (List<MenuConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MenuConfig menuConfig : list) {
					if (!Objects.equals(uuid, menuConfig.getUuid()) ||
							(companyId != menuConfig.getCompanyId())) {
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

			query.append(_SQL_SELECT_MENUCONFIG_WHERE);

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
				query.append(MenuConfigModelImpl.ORDER_BY_JPQL);
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
					list = (List<MenuConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MenuConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first menu config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menu config
	 * @throws NoSuchMenuConfigException if a matching menu config could not be found
	 */
	@Override
	public MenuConfig findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (menuConfig != null) {
			return menuConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchMenuConfigException(msg.toString());
	}

	/**
	 * Returns the first menu config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menu config, or <code>null</code> if a matching menu config could not be found
	 */
	@Override
	public MenuConfig fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<MenuConfig> orderByComparator) {
		List<MenuConfig> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last menu config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menu config
	 * @throws NoSuchMenuConfigException if a matching menu config could not be found
	 */
	@Override
	public MenuConfig findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (menuConfig != null) {
			return menuConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchMenuConfigException(msg.toString());
	}

	/**
	 * Returns the last menu config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menu config, or <code>null</code> if a matching menu config could not be found
	 */
	@Override
	public MenuConfig fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<MenuConfig> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<MenuConfig> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the menu configs before and after the current menu config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param menuConfigId the primary key of the current menu config
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next menu config
	 * @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	 */
	@Override
	public MenuConfig[] findByUuid_C_PrevAndNext(long menuConfigId,
		String uuid, long companyId,
		OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = findByPrimaryKey(menuConfigId);

		Session session = null;

		try {
			session = openSession();

			MenuConfig[] array = new MenuConfigImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, menuConfig, uuid,
					companyId, orderByComparator, true);

			array[1] = menuConfig;

			array[2] = getByUuid_C_PrevAndNext(session, menuConfig, uuid,
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

	protected MenuConfig getByUuid_C_PrevAndNext(Session session,
		MenuConfig menuConfig, String uuid, long companyId,
		OrderByComparator<MenuConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MENUCONFIG_WHERE);

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
			query.append(MenuConfigModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(menuConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MenuConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the menu configs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (MenuConfig menuConfig : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(menuConfig);
		}
	}

	/**
	 * Returns the number of menu configs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching menu configs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MENUCONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "menuConfig.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "menuConfig.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(menuConfig.uuid IS NULL OR menuConfig.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "menuConfig.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_BY_GID = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, MenuConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_BY_GID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_BY_GID =
		new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, MenuConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_BY_GID",
			new String[] { Long.class.getName() },
			MenuConfigModelImpl.GROUPID_COLUMN_BITMASK |
			MenuConfigModelImpl.ORDER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_BY_GID = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_BY_GID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the menu configs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching menu configs
	 */
	@Override
	public List<MenuConfig> findByF_BY_GID(long groupId) {
		return findByF_BY_GID(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the menu configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @return the range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByF_BY_GID(long groupId, int start, int end) {
		return findByF_BY_GID(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the menu configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByF_BY_GID(long groupId, int start, int end,
		OrderByComparator<MenuConfig> orderByComparator) {
		return findByF_BY_GID(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the menu configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByF_BY_GID(long groupId, int start, int end,
		OrderByComparator<MenuConfig> orderByComparator,
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

		List<MenuConfig> list = null;

		if (retrieveFromCache) {
			list = (List<MenuConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MenuConfig menuConfig : list) {
					if ((groupId != menuConfig.getGroupId())) {
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

			query.append(_SQL_SELECT_MENUCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_BY_GID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MenuConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<MenuConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MenuConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first menu config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menu config
	 * @throws NoSuchMenuConfigException if a matching menu config could not be found
	 */
	@Override
	public MenuConfig findByF_BY_GID_First(long groupId,
		OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = fetchByF_BY_GID_First(groupId, orderByComparator);

		if (menuConfig != null) {
			return menuConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchMenuConfigException(msg.toString());
	}

	/**
	 * Returns the first menu config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menu config, or <code>null</code> if a matching menu config could not be found
	 */
	@Override
	public MenuConfig fetchByF_BY_GID_First(long groupId,
		OrderByComparator<MenuConfig> orderByComparator) {
		List<MenuConfig> list = findByF_BY_GID(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last menu config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menu config
	 * @throws NoSuchMenuConfigException if a matching menu config could not be found
	 */
	@Override
	public MenuConfig findByF_BY_GID_Last(long groupId,
		OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = fetchByF_BY_GID_Last(groupId, orderByComparator);

		if (menuConfig != null) {
			return menuConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchMenuConfigException(msg.toString());
	}

	/**
	 * Returns the last menu config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menu config, or <code>null</code> if a matching menu config could not be found
	 */
	@Override
	public MenuConfig fetchByF_BY_GID_Last(long groupId,
		OrderByComparator<MenuConfig> orderByComparator) {
		int count = countByF_BY_GID(groupId);

		if (count == 0) {
			return null;
		}

		List<MenuConfig> list = findByF_BY_GID(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the menu configs before and after the current menu config in the ordered set where groupId = &#63;.
	 *
	 * @param menuConfigId the primary key of the current menu config
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next menu config
	 * @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	 */
	@Override
	public MenuConfig[] findByF_BY_GID_PrevAndNext(long menuConfigId,
		long groupId, OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = findByPrimaryKey(menuConfigId);

		Session session = null;

		try {
			session = openSession();

			MenuConfig[] array = new MenuConfigImpl[3];

			array[0] = getByF_BY_GID_PrevAndNext(session, menuConfig, groupId,
					orderByComparator, true);

			array[1] = menuConfig;

			array[2] = getByF_BY_GID_PrevAndNext(session, menuConfig, groupId,
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

	protected MenuConfig getByF_BY_GID_PrevAndNext(Session session,
		MenuConfig menuConfig, long groupId,
		OrderByComparator<MenuConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MENUCONFIG_WHERE);

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
			query.append(MenuConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(menuConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MenuConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the menu configs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_BY_GID(long groupId) {
		for (MenuConfig menuConfig : findByF_BY_GID(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(menuConfig);
		}
	}

	/**
	 * Returns the number of menu configs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching menu configs
	 */
	@Override
	public int countByF_BY_GID(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_BY_GID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MENUCONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_F_BY_GID_GROUPID_2 = "menuConfig.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_BY_MENUGROUP = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, MenuConfigImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_BY_menuGroup",
			new String[] { String.class.getName() },
			MenuConfigModelImpl.MENUGROUP_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_BY_MENUGROUP = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_BY_menuGroup",
			new String[] { String.class.getName() });

	/**
	 * Returns the menu config where menuGroup = &#63; or throws a {@link NoSuchMenuConfigException} if it could not be found.
	 *
	 * @param menuGroup the menu group
	 * @return the matching menu config
	 * @throws NoSuchMenuConfigException if a matching menu config could not be found
	 */
	@Override
	public MenuConfig findByF_BY_menuGroup(String menuGroup)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = fetchByF_BY_menuGroup(menuGroup);

		if (menuConfig == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("menuGroup=");
			msg.append(menuGroup);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchMenuConfigException(msg.toString());
		}

		return menuConfig;
	}

	/**
	 * Returns the menu config where menuGroup = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param menuGroup the menu group
	 * @return the matching menu config, or <code>null</code> if a matching menu config could not be found
	 */
	@Override
	public MenuConfig fetchByF_BY_menuGroup(String menuGroup) {
		return fetchByF_BY_menuGroup(menuGroup, true);
	}

	/**
	 * Returns the menu config where menuGroup = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param menuGroup the menu group
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching menu config, or <code>null</code> if a matching menu config could not be found
	 */
	@Override
	public MenuConfig fetchByF_BY_menuGroup(String menuGroup,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { menuGroup };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_BY_MENUGROUP,
					finderArgs, this);
		}

		if (result instanceof MenuConfig) {
			MenuConfig menuConfig = (MenuConfig)result;

			if (!Objects.equals(menuGroup, menuConfig.getMenuGroup())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_MENUCONFIG_WHERE);

			boolean bindMenuGroup = false;

			if (menuGroup == null) {
				query.append(_FINDER_COLUMN_F_BY_MENUGROUP_MENUGROUP_1);
			}
			else if (menuGroup.equals("")) {
				query.append(_FINDER_COLUMN_F_BY_MENUGROUP_MENUGROUP_3);
			}
			else {
				bindMenuGroup = true;

				query.append(_FINDER_COLUMN_F_BY_MENUGROUP_MENUGROUP_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMenuGroup) {
					qPos.add(menuGroup);
				}

				List<MenuConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_BY_MENUGROUP,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"MenuConfigPersistenceImpl.fetchByF_BY_menuGroup(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					MenuConfig menuConfig = list.get(0);

					result = menuConfig;

					cacheResult(menuConfig);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_BY_MENUGROUP,
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
			return (MenuConfig)result;
		}
	}

	/**
	 * Removes the menu config where menuGroup = &#63; from the database.
	 *
	 * @param menuGroup the menu group
	 * @return the menu config that was removed
	 */
	@Override
	public MenuConfig removeByF_BY_menuGroup(String menuGroup)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = findByF_BY_menuGroup(menuGroup);

		return remove(menuConfig);
	}

	/**
	 * Returns the number of menu configs where menuGroup = &#63;.
	 *
	 * @param menuGroup the menu group
	 * @return the number of matching menu configs
	 */
	@Override
	public int countByF_BY_menuGroup(String menuGroup) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_BY_MENUGROUP;

		Object[] finderArgs = new Object[] { menuGroup };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MENUCONFIG_WHERE);

			boolean bindMenuGroup = false;

			if (menuGroup == null) {
				query.append(_FINDER_COLUMN_F_BY_MENUGROUP_MENUGROUP_1);
			}
			else if (menuGroup.equals("")) {
				query.append(_FINDER_COLUMN_F_BY_MENUGROUP_MENUGROUP_3);
			}
			else {
				bindMenuGroup = true;

				query.append(_FINDER_COLUMN_F_BY_MENUGROUP_MENUGROUP_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMenuGroup) {
					qPos.add(menuGroup);
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

	private static final String _FINDER_COLUMN_F_BY_MENUGROUP_MENUGROUP_1 = "menuConfig.menuGroup IS NULL";
	private static final String _FINDER_COLUMN_F_BY_MENUGROUP_MENUGROUP_2 = "menuConfig.menuGroup = ?";
	private static final String _FINDER_COLUMN_F_BY_MENUGROUP_MENUGROUP_3 = "(menuConfig.menuGroup IS NULL OR menuConfig.menuGroup = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_A_MID = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, MenuConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_A_MID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_A_MID =
		new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, MenuConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_A_MID",
			new String[] { Long.class.getName() },
			MenuConfigModelImpl.MENUCONFIGID_COLUMN_BITMASK |
			MenuConfigModelImpl.ORDER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_A_MID = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_A_MID",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_A_MID = new FinderPath(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByF_A_MID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the menu configs where menuConfigId = &#63;.
	 *
	 * @param menuConfigId the menu config ID
	 * @return the matching menu configs
	 */
	@Override
	public List<MenuConfig> findByF_A_MID(long menuConfigId) {
		return findByF_A_MID(menuConfigId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the menu configs where menuConfigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param menuConfigId the menu config ID
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @return the range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByF_A_MID(long menuConfigId, int start, int end) {
		return findByF_A_MID(menuConfigId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the menu configs where menuConfigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param menuConfigId the menu config ID
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByF_A_MID(long menuConfigId, int start,
		int end, OrderByComparator<MenuConfig> orderByComparator) {
		return findByF_A_MID(menuConfigId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the menu configs where menuConfigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param menuConfigId the menu config ID
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByF_A_MID(long menuConfigId, int start,
		int end, OrderByComparator<MenuConfig> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_A_MID;
			finderArgs = new Object[] { menuConfigId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_A_MID;
			finderArgs = new Object[] {
					menuConfigId,
					
					start, end, orderByComparator
				};
		}

		List<MenuConfig> list = null;

		if (retrieveFromCache) {
			list = (List<MenuConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MenuConfig menuConfig : list) {
					if ((menuConfigId != menuConfig.getMenuConfigId())) {
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

			query.append(_SQL_SELECT_MENUCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_A_MID_MENUCONFIGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MenuConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(menuConfigId);

				if (!pagination) {
					list = (List<MenuConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MenuConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first menu config in the ordered set where menuConfigId = &#63;.
	 *
	 * @param menuConfigId the menu config ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menu config
	 * @throws NoSuchMenuConfigException if a matching menu config could not be found
	 */
	@Override
	public MenuConfig findByF_A_MID_First(long menuConfigId,
		OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = fetchByF_A_MID_First(menuConfigId,
				orderByComparator);

		if (menuConfig != null) {
			return menuConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("menuConfigId=");
		msg.append(menuConfigId);

		msg.append("}");

		throw new NoSuchMenuConfigException(msg.toString());
	}

	/**
	 * Returns the first menu config in the ordered set where menuConfigId = &#63;.
	 *
	 * @param menuConfigId the menu config ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menu config, or <code>null</code> if a matching menu config could not be found
	 */
	@Override
	public MenuConfig fetchByF_A_MID_First(long menuConfigId,
		OrderByComparator<MenuConfig> orderByComparator) {
		List<MenuConfig> list = findByF_A_MID(menuConfigId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last menu config in the ordered set where menuConfigId = &#63;.
	 *
	 * @param menuConfigId the menu config ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menu config
	 * @throws NoSuchMenuConfigException if a matching menu config could not be found
	 */
	@Override
	public MenuConfig findByF_A_MID_Last(long menuConfigId,
		OrderByComparator<MenuConfig> orderByComparator)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = fetchByF_A_MID_Last(menuConfigId,
				orderByComparator);

		if (menuConfig != null) {
			return menuConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("menuConfigId=");
		msg.append(menuConfigId);

		msg.append("}");

		throw new NoSuchMenuConfigException(msg.toString());
	}

	/**
	 * Returns the last menu config in the ordered set where menuConfigId = &#63;.
	 *
	 * @param menuConfigId the menu config ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menu config, or <code>null</code> if a matching menu config could not be found
	 */
	@Override
	public MenuConfig fetchByF_A_MID_Last(long menuConfigId,
		OrderByComparator<MenuConfig> orderByComparator) {
		int count = countByF_A_MID(menuConfigId);

		if (count == 0) {
			return null;
		}

		List<MenuConfig> list = findByF_A_MID(menuConfigId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns all the menu configs where menuConfigId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param menuConfigIds the menu config IDs
	 * @return the matching menu configs
	 */
	@Override
	public List<MenuConfig> findByF_A_MID(long[] menuConfigIds) {
		return findByF_A_MID(menuConfigIds, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the menu configs where menuConfigId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param menuConfigIds the menu config IDs
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @return the range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByF_A_MID(long[] menuConfigIds, int start,
		int end) {
		return findByF_A_MID(menuConfigIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the menu configs where menuConfigId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param menuConfigIds the menu config IDs
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByF_A_MID(long[] menuConfigIds, int start,
		int end, OrderByComparator<MenuConfig> orderByComparator) {
		return findByF_A_MID(menuConfigIds, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the menu configs where menuConfigId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param menuConfigId the menu config ID
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching menu configs
	 */
	@Override
	public List<MenuConfig> findByF_A_MID(long[] menuConfigIds, int start,
		int end, OrderByComparator<MenuConfig> orderByComparator,
		boolean retrieveFromCache) {
		if (menuConfigIds == null) {
			menuConfigIds = new long[0];
		}
		else if (menuConfigIds.length > 1) {
			menuConfigIds = ArrayUtil.unique(menuConfigIds);

			Arrays.sort(menuConfigIds);
		}

		if (menuConfigIds.length == 1) {
			return findByF_A_MID(menuConfigIds[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(menuConfigIds) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(menuConfigIds),
					
					start, end, orderByComparator
				};
		}

		List<MenuConfig> list = null;

		if (retrieveFromCache) {
			list = (List<MenuConfig>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_F_A_MID,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MenuConfig menuConfig : list) {
					if (!ArrayUtil.contains(menuConfigIds,
								menuConfig.getMenuConfigId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_MENUCONFIG_WHERE);

			if (menuConfigIds.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_F_A_MID_MENUCONFIGID_7);

				query.append(StringUtil.merge(menuConfigIds));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MenuConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MenuConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MenuConfig>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_F_A_MID,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_F_A_MID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the menu configs where menuConfigId = &#63; from the database.
	 *
	 * @param menuConfigId the menu config ID
	 */
	@Override
	public void removeByF_A_MID(long menuConfigId) {
		for (MenuConfig menuConfig : findByF_A_MID(menuConfigId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(menuConfig);
		}
	}

	/**
	 * Returns the number of menu configs where menuConfigId = &#63;.
	 *
	 * @param menuConfigId the menu config ID
	 * @return the number of matching menu configs
	 */
	@Override
	public int countByF_A_MID(long menuConfigId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_A_MID;

		Object[] finderArgs = new Object[] { menuConfigId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MENUCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_A_MID_MENUCONFIGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(menuConfigId);

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

	/**
	 * Returns the number of menu configs where menuConfigId = any &#63;.
	 *
	 * @param menuConfigIds the menu config IDs
	 * @return the number of matching menu configs
	 */
	@Override
	public int countByF_A_MID(long[] menuConfigIds) {
		if (menuConfigIds == null) {
			menuConfigIds = new long[0];
		}
		else if (menuConfigIds.length > 1) {
			menuConfigIds = ArrayUtil.unique(menuConfigIds);

			Arrays.sort(menuConfigIds);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(menuConfigIds) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_A_MID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_MENUCONFIG_WHERE);

			if (menuConfigIds.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_F_A_MID_MENUCONFIGID_7);

				query.append(StringUtil.merge(menuConfigIds));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_A_MID,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_A_MID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_F_A_MID_MENUCONFIGID_2 = "menuConfig.menuConfigId = ?";
	private static final String _FINDER_COLUMN_F_A_MID_MENUCONFIGID_7 = "menuConfig.menuConfigId IN (";

	public MenuConfigPersistenceImpl() {
		setModelClass(MenuConfig.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("order", "order_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the menu config in the entity cache if it is enabled.
	 *
	 * @param menuConfig the menu config
	 */
	@Override
	public void cacheResult(MenuConfig menuConfig) {
		entityCache.putResult(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigImpl.class, menuConfig.getPrimaryKey(), menuConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { menuConfig.getUuid(), menuConfig.getGroupId() },
			menuConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_BY_MENUGROUP,
			new Object[] { menuConfig.getMenuGroup() }, menuConfig);

		menuConfig.resetOriginalValues();
	}

	/**
	 * Caches the menu configs in the entity cache if it is enabled.
	 *
	 * @param menuConfigs the menu configs
	 */
	@Override
	public void cacheResult(List<MenuConfig> menuConfigs) {
		for (MenuConfig menuConfig : menuConfigs) {
			if (entityCache.getResult(
						MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
						MenuConfigImpl.class, menuConfig.getPrimaryKey()) == null) {
				cacheResult(menuConfig);
			}
			else {
				menuConfig.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all menu configs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MenuConfigImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the menu config.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MenuConfig menuConfig) {
		entityCache.removeResult(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigImpl.class, menuConfig.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((MenuConfigModelImpl)menuConfig, true);
	}

	@Override
	public void clearCache(List<MenuConfig> menuConfigs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MenuConfig menuConfig : menuConfigs) {
			entityCache.removeResult(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
				MenuConfigImpl.class, menuConfig.getPrimaryKey());

			clearUniqueFindersCache((MenuConfigModelImpl)menuConfig, true);
		}
	}

	protected void cacheUniqueFindersCache(
		MenuConfigModelImpl menuConfigModelImpl) {
		Object[] args = new Object[] {
				menuConfigModelImpl.getUuid(), menuConfigModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			menuConfigModelImpl, false);

		args = new Object[] { menuConfigModelImpl.getMenuGroup() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_BY_MENUGROUP, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_BY_MENUGROUP, args,
			menuConfigModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		MenuConfigModelImpl menuConfigModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					menuConfigModelImpl.getUuid(),
					menuConfigModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((menuConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					menuConfigModelImpl.getOriginalUuid(),
					menuConfigModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { menuConfigModelImpl.getMenuGroup() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_BY_MENUGROUP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_BY_MENUGROUP, args);
		}

		if ((menuConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_BY_MENUGROUP.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					menuConfigModelImpl.getOriginalMenuGroup()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_BY_MENUGROUP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_BY_MENUGROUP, args);
		}
	}

	/**
	 * Creates a new menu config with the primary key. Does not add the menu config to the database.
	 *
	 * @param menuConfigId the primary key for the new menu config
	 * @return the new menu config
	 */
	@Override
	public MenuConfig create(long menuConfigId) {
		MenuConfig menuConfig = new MenuConfigImpl();

		menuConfig.setNew(true);
		menuConfig.setPrimaryKey(menuConfigId);

		String uuid = PortalUUIDUtil.generate();

		menuConfig.setUuid(uuid);

		menuConfig.setCompanyId(companyProvider.getCompanyId());

		return menuConfig;
	}

	/**
	 * Removes the menu config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param menuConfigId the primary key of the menu config
	 * @return the menu config that was removed
	 * @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	 */
	@Override
	public MenuConfig remove(long menuConfigId)
		throws NoSuchMenuConfigException {
		return remove((Serializable)menuConfigId);
	}

	/**
	 * Removes the menu config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the menu config
	 * @return the menu config that was removed
	 * @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	 */
	@Override
	public MenuConfig remove(Serializable primaryKey)
		throws NoSuchMenuConfigException {
		Session session = null;

		try {
			session = openSession();

			MenuConfig menuConfig = (MenuConfig)session.get(MenuConfigImpl.class,
					primaryKey);

			if (menuConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMenuConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(menuConfig);
		}
		catch (NoSuchMenuConfigException nsee) {
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
	protected MenuConfig removeImpl(MenuConfig menuConfig) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(menuConfig)) {
				menuConfig = (MenuConfig)session.get(MenuConfigImpl.class,
						menuConfig.getPrimaryKeyObj());
			}

			if (menuConfig != null) {
				session.delete(menuConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (menuConfig != null) {
			clearCache(menuConfig);
		}

		return menuConfig;
	}

	@Override
	public MenuConfig updateImpl(MenuConfig menuConfig) {
		boolean isNew = menuConfig.isNew();

		if (!(menuConfig instanceof MenuConfigModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(menuConfig.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(menuConfig);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in menuConfig proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MenuConfig implementation " +
				menuConfig.getClass());
		}

		MenuConfigModelImpl menuConfigModelImpl = (MenuConfigModelImpl)menuConfig;

		if (Validator.isNull(menuConfig.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			menuConfig.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (menuConfig.getCreateDate() == null)) {
			if (serviceContext == null) {
				menuConfig.setCreateDate(now);
			}
			else {
				menuConfig.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!menuConfigModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				menuConfig.setModifiedDate(now);
			}
			else {
				menuConfig.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (menuConfig.isNew()) {
				session.save(menuConfig);

				menuConfig.setNew(false);
			}
			else {
				menuConfig = (MenuConfig)session.merge(menuConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!MenuConfigModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { menuConfigModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					menuConfigModelImpl.getUuid(),
					menuConfigModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { menuConfigModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_BY_GID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_BY_GID,
				args);

			args = new Object[] { menuConfigModelImpl.getMenuConfigId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_A_MID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_A_MID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((menuConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						menuConfigModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { menuConfigModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((menuConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						menuConfigModelImpl.getOriginalUuid(),
						menuConfigModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						menuConfigModelImpl.getUuid(),
						menuConfigModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((menuConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_BY_GID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						menuConfigModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_BY_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_BY_GID,
					args);

				args = new Object[] { menuConfigModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_BY_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_BY_GID,
					args);
			}

			if ((menuConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_A_MID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						menuConfigModelImpl.getOriginalMenuConfigId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_A_MID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_A_MID,
					args);

				args = new Object[] { menuConfigModelImpl.getMenuConfigId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_A_MID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_A_MID,
					args);
			}
		}

		entityCache.putResult(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
			MenuConfigImpl.class, menuConfig.getPrimaryKey(), menuConfig, false);

		clearUniqueFindersCache(menuConfigModelImpl, false);
		cacheUniqueFindersCache(menuConfigModelImpl);

		menuConfig.resetOriginalValues();

		return menuConfig;
	}

	/**
	 * Returns the menu config with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the menu config
	 * @return the menu config
	 * @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	 */
	@Override
	public MenuConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMenuConfigException {
		MenuConfig menuConfig = fetchByPrimaryKey(primaryKey);

		if (menuConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMenuConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return menuConfig;
	}

	/**
	 * Returns the menu config with the primary key or throws a {@link NoSuchMenuConfigException} if it could not be found.
	 *
	 * @param menuConfigId the primary key of the menu config
	 * @return the menu config
	 * @throws NoSuchMenuConfigException if a menu config with the primary key could not be found
	 */
	@Override
	public MenuConfig findByPrimaryKey(long menuConfigId)
		throws NoSuchMenuConfigException {
		return findByPrimaryKey((Serializable)menuConfigId);
	}

	/**
	 * Returns the menu config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the menu config
	 * @return the menu config, or <code>null</code> if a menu config with the primary key could not be found
	 */
	@Override
	public MenuConfig fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
				MenuConfigImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MenuConfig menuConfig = (MenuConfig)serializable;

		if (menuConfig == null) {
			Session session = null;

			try {
				session = openSession();

				menuConfig = (MenuConfig)session.get(MenuConfigImpl.class,
						primaryKey);

				if (menuConfig != null) {
					cacheResult(menuConfig);
				}
				else {
					entityCache.putResult(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
						MenuConfigImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
					MenuConfigImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return menuConfig;
	}

	/**
	 * Returns the menu config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param menuConfigId the primary key of the menu config
	 * @return the menu config, or <code>null</code> if a menu config with the primary key could not be found
	 */
	@Override
	public MenuConfig fetchByPrimaryKey(long menuConfigId) {
		return fetchByPrimaryKey((Serializable)menuConfigId);
	}

	@Override
	public Map<Serializable, MenuConfig> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MenuConfig> map = new HashMap<Serializable, MenuConfig>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MenuConfig menuConfig = fetchByPrimaryKey(primaryKey);

			if (menuConfig != null) {
				map.put(primaryKey, menuConfig);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
					MenuConfigImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MenuConfig)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MENUCONFIG_WHERE_PKS_IN);

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

			for (MenuConfig menuConfig : (List<MenuConfig>)q.list()) {
				map.put(menuConfig.getPrimaryKeyObj(), menuConfig);

				cacheResult(menuConfig);

				uncachedPrimaryKeys.remove(menuConfig.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MenuConfigModelImpl.ENTITY_CACHE_ENABLED,
					MenuConfigImpl.class, primaryKey, nullModel);
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
	 * Returns all the menu configs.
	 *
	 * @return the menu configs
	 */
	@Override
	public List<MenuConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the menu configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @return the range of menu configs
	 */
	@Override
	public List<MenuConfig> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the menu configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of menu configs
	 */
	@Override
	public List<MenuConfig> findAll(int start, int end,
		OrderByComparator<MenuConfig> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the menu configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of menu configs
	 * @param end the upper bound of the range of menu configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of menu configs
	 */
	@Override
	public List<MenuConfig> findAll(int start, int end,
		OrderByComparator<MenuConfig> orderByComparator,
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

		List<MenuConfig> list = null;

		if (retrieveFromCache) {
			list = (List<MenuConfig>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MENUCONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MENUCONFIG;

				if (pagination) {
					sql = sql.concat(MenuConfigModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MenuConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MenuConfig>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the menu configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MenuConfig menuConfig : findAll()) {
			remove(menuConfig);
		}
	}

	/**
	 * Returns the number of menu configs.
	 *
	 * @return the number of menu configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MENUCONFIG);

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
		return MenuConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the menu config persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MenuConfigImpl.class.getName());
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
	private static final String _SQL_SELECT_MENUCONFIG = "SELECT menuConfig FROM MenuConfig menuConfig";
	private static final String _SQL_SELECT_MENUCONFIG_WHERE_PKS_IN = "SELECT menuConfig FROM MenuConfig menuConfig WHERE menuConfigId IN (";
	private static final String _SQL_SELECT_MENUCONFIG_WHERE = "SELECT menuConfig FROM MenuConfig menuConfig WHERE ";
	private static final String _SQL_COUNT_MENUCONFIG = "SELECT COUNT(menuConfig) FROM MenuConfig menuConfig";
	private static final String _SQL_COUNT_MENUCONFIG_WHERE = "SELECT COUNT(menuConfig) FROM MenuConfig menuConfig WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "menuConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MenuConfig exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MenuConfig exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(MenuConfigPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "order"
			});
}