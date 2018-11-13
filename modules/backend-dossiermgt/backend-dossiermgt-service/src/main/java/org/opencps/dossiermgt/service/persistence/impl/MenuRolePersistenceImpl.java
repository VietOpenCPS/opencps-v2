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

import org.opencps.dossiermgt.exception.NoSuchMenuRoleException;
import org.opencps.dossiermgt.model.MenuRole;
import org.opencps.dossiermgt.model.impl.MenuRoleImpl;
import org.opencps.dossiermgt.model.impl.MenuRoleModelImpl;
import org.opencps.dossiermgt.service.persistence.MenuRolePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the menu role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see MenuRolePersistence
 * @see org.opencps.dossiermgt.service.persistence.MenuRoleUtil
 * @generated
 */
@ProviderType
public class MenuRolePersistenceImpl extends BasePersistenceImpl<MenuRole>
	implements MenuRolePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MenuRoleUtil} to access the menu role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MenuRoleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleModelImpl.FINDER_CACHE_ENABLED, MenuRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleModelImpl.FINDER_CACHE_ENABLED, MenuRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleModelImpl.FINDER_CACHE_ENABLED, MenuRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleModelImpl.FINDER_CACHE_ENABLED, MenuRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			MenuRoleModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the menu roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching menu roles
	 */
	@Override
	public List<MenuRole> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the menu roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of menu roles
	 * @param end the upper bound of the range of menu roles (not inclusive)
	 * @return the range of matching menu roles
	 */
	@Override
	public List<MenuRole> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the menu roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of menu roles
	 * @param end the upper bound of the range of menu roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching menu roles
	 */
	@Override
	public List<MenuRole> findByUuid(String uuid, int start, int end,
		OrderByComparator<MenuRole> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the menu roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of menu roles
	 * @param end the upper bound of the range of menu roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching menu roles
	 */
	@Override
	public List<MenuRole> findByUuid(String uuid, int start, int end,
		OrderByComparator<MenuRole> orderByComparator, boolean retrieveFromCache) {
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

		List<MenuRole> list = null;

		if (retrieveFromCache) {
			list = (List<MenuRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MenuRole menuRole : list) {
					if (!Objects.equals(uuid, menuRole.getUuid())) {
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

			query.append(_SQL_SELECT_MENUROLE_WHERE);

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
				query.append(MenuRoleModelImpl.ORDER_BY_JPQL);
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
					list = (List<MenuRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MenuRole>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first menu role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menu role
	 * @throws NoSuchMenuRoleException if a matching menu role could not be found
	 */
	@Override
	public MenuRole findByUuid_First(String uuid,
		OrderByComparator<MenuRole> orderByComparator)
		throws NoSuchMenuRoleException {
		MenuRole menuRole = fetchByUuid_First(uuid, orderByComparator);

		if (menuRole != null) {
			return menuRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchMenuRoleException(msg.toString());
	}

	/**
	 * Returns the first menu role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menu role, or <code>null</code> if a matching menu role could not be found
	 */
	@Override
	public MenuRole fetchByUuid_First(String uuid,
		OrderByComparator<MenuRole> orderByComparator) {
		List<MenuRole> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last menu role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menu role
	 * @throws NoSuchMenuRoleException if a matching menu role could not be found
	 */
	@Override
	public MenuRole findByUuid_Last(String uuid,
		OrderByComparator<MenuRole> orderByComparator)
		throws NoSuchMenuRoleException {
		MenuRole menuRole = fetchByUuid_Last(uuid, orderByComparator);

		if (menuRole != null) {
			return menuRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchMenuRoleException(msg.toString());
	}

	/**
	 * Returns the last menu role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menu role, or <code>null</code> if a matching menu role could not be found
	 */
	@Override
	public MenuRole fetchByUuid_Last(String uuid,
		OrderByComparator<MenuRole> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<MenuRole> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the menu roles before and after the current menu role in the ordered set where uuid = &#63;.
	 *
	 * @param menuRoleId the primary key of the current menu role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next menu role
	 * @throws NoSuchMenuRoleException if a menu role with the primary key could not be found
	 */
	@Override
	public MenuRole[] findByUuid_PrevAndNext(long menuRoleId, String uuid,
		OrderByComparator<MenuRole> orderByComparator)
		throws NoSuchMenuRoleException {
		MenuRole menuRole = findByPrimaryKey(menuRoleId);

		Session session = null;

		try {
			session = openSession();

			MenuRole[] array = new MenuRoleImpl[3];

			array[0] = getByUuid_PrevAndNext(session, menuRole, uuid,
					orderByComparator, true);

			array[1] = menuRole;

			array[2] = getByUuid_PrevAndNext(session, menuRole, uuid,
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

	protected MenuRole getByUuid_PrevAndNext(Session session,
		MenuRole menuRole, String uuid,
		OrderByComparator<MenuRole> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MENUROLE_WHERE);

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
			query.append(MenuRoleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(menuRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MenuRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the menu roles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (MenuRole menuRole : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(menuRole);
		}
	}

	/**
	 * Returns the number of menu roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching menu roles
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MENUROLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "menuRole.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "menuRole.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(menuRole.uuid IS NULL OR menuRole.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_RID = new FinderPath(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleModelImpl.FINDER_CACHE_ENABLED, MenuRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_RID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_RID = new FinderPath(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleModelImpl.FINDER_CACHE_ENABLED, MenuRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_RID",
			new String[] { Long.class.getName() },
			MenuRoleModelImpl.ROLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_RID = new FinderPath(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_RID",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_RID = new FinderPath(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByF_RID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the menu roles where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @return the matching menu roles
	 */
	@Override
	public List<MenuRole> findByF_RID(long roleId) {
		return findByF_RID(roleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the menu roles where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleId the role ID
	 * @param start the lower bound of the range of menu roles
	 * @param end the upper bound of the range of menu roles (not inclusive)
	 * @return the range of matching menu roles
	 */
	@Override
	public List<MenuRole> findByF_RID(long roleId, int start, int end) {
		return findByF_RID(roleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the menu roles where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleId the role ID
	 * @param start the lower bound of the range of menu roles
	 * @param end the upper bound of the range of menu roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching menu roles
	 */
	@Override
	public List<MenuRole> findByF_RID(long roleId, int start, int end,
		OrderByComparator<MenuRole> orderByComparator) {
		return findByF_RID(roleId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the menu roles where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleId the role ID
	 * @param start the lower bound of the range of menu roles
	 * @param end the upper bound of the range of menu roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching menu roles
	 */
	@Override
	public List<MenuRole> findByF_RID(long roleId, int start, int end,
		OrderByComparator<MenuRole> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_RID;
			finderArgs = new Object[] { roleId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_RID;
			finderArgs = new Object[] { roleId, start, end, orderByComparator };
		}

		List<MenuRole> list = null;

		if (retrieveFromCache) {
			list = (List<MenuRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MenuRole menuRole : list) {
					if ((roleId != menuRole.getRoleId())) {
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

			query.append(_SQL_SELECT_MENUROLE_WHERE);

			query.append(_FINDER_COLUMN_F_RID_ROLEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MenuRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(roleId);

				if (!pagination) {
					list = (List<MenuRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MenuRole>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first menu role in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menu role
	 * @throws NoSuchMenuRoleException if a matching menu role could not be found
	 */
	@Override
	public MenuRole findByF_RID_First(long roleId,
		OrderByComparator<MenuRole> orderByComparator)
		throws NoSuchMenuRoleException {
		MenuRole menuRole = fetchByF_RID_First(roleId, orderByComparator);

		if (menuRole != null) {
			return menuRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("roleId=");
		msg.append(roleId);

		msg.append("}");

		throw new NoSuchMenuRoleException(msg.toString());
	}

	/**
	 * Returns the first menu role in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menu role, or <code>null</code> if a matching menu role could not be found
	 */
	@Override
	public MenuRole fetchByF_RID_First(long roleId,
		OrderByComparator<MenuRole> orderByComparator) {
		List<MenuRole> list = findByF_RID(roleId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last menu role in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menu role
	 * @throws NoSuchMenuRoleException if a matching menu role could not be found
	 */
	@Override
	public MenuRole findByF_RID_Last(long roleId,
		OrderByComparator<MenuRole> orderByComparator)
		throws NoSuchMenuRoleException {
		MenuRole menuRole = fetchByF_RID_Last(roleId, orderByComparator);

		if (menuRole != null) {
			return menuRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("roleId=");
		msg.append(roleId);

		msg.append("}");

		throw new NoSuchMenuRoleException(msg.toString());
	}

	/**
	 * Returns the last menu role in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menu role, or <code>null</code> if a matching menu role could not be found
	 */
	@Override
	public MenuRole fetchByF_RID_Last(long roleId,
		OrderByComparator<MenuRole> orderByComparator) {
		int count = countByF_RID(roleId);

		if (count == 0) {
			return null;
		}

		List<MenuRole> list = findByF_RID(roleId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the menu roles before and after the current menu role in the ordered set where roleId = &#63;.
	 *
	 * @param menuRoleId the primary key of the current menu role
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next menu role
	 * @throws NoSuchMenuRoleException if a menu role with the primary key could not be found
	 */
	@Override
	public MenuRole[] findByF_RID_PrevAndNext(long menuRoleId, long roleId,
		OrderByComparator<MenuRole> orderByComparator)
		throws NoSuchMenuRoleException {
		MenuRole menuRole = findByPrimaryKey(menuRoleId);

		Session session = null;

		try {
			session = openSession();

			MenuRole[] array = new MenuRoleImpl[3];

			array[0] = getByF_RID_PrevAndNext(session, menuRole, roleId,
					orderByComparator, true);

			array[1] = menuRole;

			array[2] = getByF_RID_PrevAndNext(session, menuRole, roleId,
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

	protected MenuRole getByF_RID_PrevAndNext(Session session,
		MenuRole menuRole, long roleId,
		OrderByComparator<MenuRole> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MENUROLE_WHERE);

		query.append(_FINDER_COLUMN_F_RID_ROLEID_2);

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
			query.append(MenuRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(roleId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(menuRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MenuRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the menu roles where roleId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleIds the role IDs
	 * @return the matching menu roles
	 */
	@Override
	public List<MenuRole> findByF_RID(long[] roleIds) {
		return findByF_RID(roleIds, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the menu roles where roleId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleIds the role IDs
	 * @param start the lower bound of the range of menu roles
	 * @param end the upper bound of the range of menu roles (not inclusive)
	 * @return the range of matching menu roles
	 */
	@Override
	public List<MenuRole> findByF_RID(long[] roleIds, int start, int end) {
		return findByF_RID(roleIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the menu roles where roleId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleIds the role IDs
	 * @param start the lower bound of the range of menu roles
	 * @param end the upper bound of the range of menu roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching menu roles
	 */
	@Override
	public List<MenuRole> findByF_RID(long[] roleIds, int start, int end,
		OrderByComparator<MenuRole> orderByComparator) {
		return findByF_RID(roleIds, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the menu roles where roleId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleId the role ID
	 * @param start the lower bound of the range of menu roles
	 * @param end the upper bound of the range of menu roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching menu roles
	 */
	@Override
	public List<MenuRole> findByF_RID(long[] roleIds, int start, int end,
		OrderByComparator<MenuRole> orderByComparator, boolean retrieveFromCache) {
		if (roleIds == null) {
			roleIds = new long[0];
		}
		else if (roleIds.length > 1) {
			roleIds = ArrayUtil.unique(roleIds);

			Arrays.sort(roleIds);
		}

		if (roleIds.length == 1) {
			return findByF_RID(roleIds[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(roleIds) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(roleIds),
					
					start, end, orderByComparator
				};
		}

		List<MenuRole> list = null;

		if (retrieveFromCache) {
			list = (List<MenuRole>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_F_RID,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MenuRole menuRole : list) {
					if (!ArrayUtil.contains(roleIds, menuRole.getRoleId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_MENUROLE_WHERE);

			if (roleIds.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_F_RID_ROLEID_7);

				query.append(StringUtil.merge(roleIds));

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
				query.append(MenuRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MenuRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MenuRole>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_F_RID,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_F_RID,
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
	 * Removes all the menu roles where roleId = &#63; from the database.
	 *
	 * @param roleId the role ID
	 */
	@Override
	public void removeByF_RID(long roleId) {
		for (MenuRole menuRole : findByF_RID(roleId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(menuRole);
		}
	}

	/**
	 * Returns the number of menu roles where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @return the number of matching menu roles
	 */
	@Override
	public int countByF_RID(long roleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_RID;

		Object[] finderArgs = new Object[] { roleId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MENUROLE_WHERE);

			query.append(_FINDER_COLUMN_F_RID_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(roleId);

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
	 * Returns the number of menu roles where roleId = any &#63;.
	 *
	 * @param roleIds the role IDs
	 * @return the number of matching menu roles
	 */
	@Override
	public int countByF_RID(long[] roleIds) {
		if (roleIds == null) {
			roleIds = new long[0];
		}
		else if (roleIds.length > 1) {
			roleIds = ArrayUtil.unique(roleIds);

			Arrays.sort(roleIds);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(roleIds) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_RID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_MENUROLE_WHERE);

			if (roleIds.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_F_RID_ROLEID_7);

				query.append(StringUtil.merge(roleIds));

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

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_RID,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_RID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_F_RID_ROLEID_2 = "menuRole.roleId = ?";
	private static final String _FINDER_COLUMN_F_RID_ROLEID_7 = "menuRole.roleId IN (";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_MENU_ROLE = new FinderPath(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleModelImpl.FINDER_CACHE_ENABLED, MenuRoleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_MENU_ROLE",
			new String[] { Long.class.getName(), Long.class.getName() },
			MenuRoleModelImpl.MENUCONFIGID_COLUMN_BITMASK |
			MenuRoleModelImpl.ROLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_MENU_ROLE = new FinderPath(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_MENU_ROLE",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the menu role where menuConfigId = &#63; and roleId = &#63; or throws a {@link NoSuchMenuRoleException} if it could not be found.
	 *
	 * @param menuConfigId the menu config ID
	 * @param roleId the role ID
	 * @return the matching menu role
	 * @throws NoSuchMenuRoleException if a matching menu role could not be found
	 */
	@Override
	public MenuRole findByF_MENU_ROLE(long menuConfigId, long roleId)
		throws NoSuchMenuRoleException {
		MenuRole menuRole = fetchByF_MENU_ROLE(menuConfigId, roleId);

		if (menuRole == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("menuConfigId=");
			msg.append(menuConfigId);

			msg.append(", roleId=");
			msg.append(roleId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchMenuRoleException(msg.toString());
		}

		return menuRole;
	}

	/**
	 * Returns the menu role where menuConfigId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param menuConfigId the menu config ID
	 * @param roleId the role ID
	 * @return the matching menu role, or <code>null</code> if a matching menu role could not be found
	 */
	@Override
	public MenuRole fetchByF_MENU_ROLE(long menuConfigId, long roleId) {
		return fetchByF_MENU_ROLE(menuConfigId, roleId, true);
	}

	/**
	 * Returns the menu role where menuConfigId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param menuConfigId the menu config ID
	 * @param roleId the role ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching menu role, or <code>null</code> if a matching menu role could not be found
	 */
	@Override
	public MenuRole fetchByF_MENU_ROLE(long menuConfigId, long roleId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { menuConfigId, roleId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_MENU_ROLE,
					finderArgs, this);
		}

		if (result instanceof MenuRole) {
			MenuRole menuRole = (MenuRole)result;

			if ((menuConfigId != menuRole.getMenuConfigId()) ||
					(roleId != menuRole.getRoleId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MENUROLE_WHERE);

			query.append(_FINDER_COLUMN_F_MENU_ROLE_MENUCONFIGID_2);

			query.append(_FINDER_COLUMN_F_MENU_ROLE_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(menuConfigId);

				qPos.add(roleId);

				List<MenuRole> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_MENU_ROLE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"MenuRolePersistenceImpl.fetchByF_MENU_ROLE(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					MenuRole menuRole = list.get(0);

					result = menuRole;

					cacheResult(menuRole);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_MENU_ROLE,
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
			return (MenuRole)result;
		}
	}

	/**
	 * Removes the menu role where menuConfigId = &#63; and roleId = &#63; from the database.
	 *
	 * @param menuConfigId the menu config ID
	 * @param roleId the role ID
	 * @return the menu role that was removed
	 */
	@Override
	public MenuRole removeByF_MENU_ROLE(long menuConfigId, long roleId)
		throws NoSuchMenuRoleException {
		MenuRole menuRole = findByF_MENU_ROLE(menuConfigId, roleId);

		return remove(menuRole);
	}

	/**
	 * Returns the number of menu roles where menuConfigId = &#63; and roleId = &#63;.
	 *
	 * @param menuConfigId the menu config ID
	 * @param roleId the role ID
	 * @return the number of matching menu roles
	 */
	@Override
	public int countByF_MENU_ROLE(long menuConfigId, long roleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_MENU_ROLE;

		Object[] finderArgs = new Object[] { menuConfigId, roleId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MENUROLE_WHERE);

			query.append(_FINDER_COLUMN_F_MENU_ROLE_MENUCONFIGID_2);

			query.append(_FINDER_COLUMN_F_MENU_ROLE_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(menuConfigId);

				qPos.add(roleId);

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

	private static final String _FINDER_COLUMN_F_MENU_ROLE_MENUCONFIGID_2 = "menuRole.menuConfigId = ? AND ";
	private static final String _FINDER_COLUMN_F_MENU_ROLE_ROLEID_2 = "menuRole.roleId = ?";

	public MenuRolePersistenceImpl() {
		setModelClass(MenuRole.class);

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
	 * Caches the menu role in the entity cache if it is enabled.
	 *
	 * @param menuRole the menu role
	 */
	@Override
	public void cacheResult(MenuRole menuRole) {
		entityCache.putResult(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleImpl.class, menuRole.getPrimaryKey(), menuRole);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_MENU_ROLE,
			new Object[] { menuRole.getMenuConfigId(), menuRole.getRoleId() },
			menuRole);

		menuRole.resetOriginalValues();
	}

	/**
	 * Caches the menu roles in the entity cache if it is enabled.
	 *
	 * @param menuRoles the menu roles
	 */
	@Override
	public void cacheResult(List<MenuRole> menuRoles) {
		for (MenuRole menuRole : menuRoles) {
			if (entityCache.getResult(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
						MenuRoleImpl.class, menuRole.getPrimaryKey()) == null) {
				cacheResult(menuRole);
			}
			else {
				menuRole.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all menu roles.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MenuRoleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the menu role.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MenuRole menuRole) {
		entityCache.removeResult(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleImpl.class, menuRole.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((MenuRoleModelImpl)menuRole, true);
	}

	@Override
	public void clearCache(List<MenuRole> menuRoles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MenuRole menuRole : menuRoles) {
			entityCache.removeResult(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
				MenuRoleImpl.class, menuRole.getPrimaryKey());

			clearUniqueFindersCache((MenuRoleModelImpl)menuRole, true);
		}
	}

	protected void cacheUniqueFindersCache(MenuRoleModelImpl menuRoleModelImpl) {
		Object[] args = new Object[] {
				menuRoleModelImpl.getMenuConfigId(),
				menuRoleModelImpl.getRoleId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_MENU_ROLE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_MENU_ROLE, args,
			menuRoleModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		MenuRoleModelImpl menuRoleModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					menuRoleModelImpl.getMenuConfigId(),
					menuRoleModelImpl.getRoleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_MENU_ROLE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_MENU_ROLE, args);
		}

		if ((menuRoleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_MENU_ROLE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					menuRoleModelImpl.getOriginalMenuConfigId(),
					menuRoleModelImpl.getOriginalRoleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_MENU_ROLE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_MENU_ROLE, args);
		}
	}

	/**
	 * Creates a new menu role with the primary key. Does not add the menu role to the database.
	 *
	 * @param menuRoleId the primary key for the new menu role
	 * @return the new menu role
	 */
	@Override
	public MenuRole create(long menuRoleId) {
		MenuRole menuRole = new MenuRoleImpl();

		menuRole.setNew(true);
		menuRole.setPrimaryKey(menuRoleId);

		String uuid = PortalUUIDUtil.generate();

		menuRole.setUuid(uuid);

		return menuRole;
	}

	/**
	 * Removes the menu role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param menuRoleId the primary key of the menu role
	 * @return the menu role that was removed
	 * @throws NoSuchMenuRoleException if a menu role with the primary key could not be found
	 */
	@Override
	public MenuRole remove(long menuRoleId) throws NoSuchMenuRoleException {
		return remove((Serializable)menuRoleId);
	}

	/**
	 * Removes the menu role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the menu role
	 * @return the menu role that was removed
	 * @throws NoSuchMenuRoleException if a menu role with the primary key could not be found
	 */
	@Override
	public MenuRole remove(Serializable primaryKey)
		throws NoSuchMenuRoleException {
		Session session = null;

		try {
			session = openSession();

			MenuRole menuRole = (MenuRole)session.get(MenuRoleImpl.class,
					primaryKey);

			if (menuRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMenuRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(menuRole);
		}
		catch (NoSuchMenuRoleException nsee) {
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
	protected MenuRole removeImpl(MenuRole menuRole) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(menuRole)) {
				menuRole = (MenuRole)session.get(MenuRoleImpl.class,
						menuRole.getPrimaryKeyObj());
			}

			if (menuRole != null) {
				session.delete(menuRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (menuRole != null) {
			clearCache(menuRole);
		}

		return menuRole;
	}

	@Override
	public MenuRole updateImpl(MenuRole menuRole) {
		boolean isNew = menuRole.isNew();

		if (!(menuRole instanceof MenuRoleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(menuRole.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(menuRole);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in menuRole proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MenuRole implementation " +
				menuRole.getClass());
		}

		MenuRoleModelImpl menuRoleModelImpl = (MenuRoleModelImpl)menuRole;

		if (Validator.isNull(menuRole.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			menuRole.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (menuRole.isNew()) {
				session.save(menuRole);

				menuRole.setNew(false);
			}
			else {
				menuRole = (MenuRole)session.merge(menuRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!MenuRoleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { menuRoleModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { menuRoleModelImpl.getRoleId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_RID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_RID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((menuRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { menuRoleModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { menuRoleModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((menuRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_RID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						menuRoleModelImpl.getOriginalRoleId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_RID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_RID,
					args);

				args = new Object[] { menuRoleModelImpl.getRoleId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_RID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_RID,
					args);
			}
		}

		entityCache.putResult(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
			MenuRoleImpl.class, menuRole.getPrimaryKey(), menuRole, false);

		clearUniqueFindersCache(menuRoleModelImpl, false);
		cacheUniqueFindersCache(menuRoleModelImpl);

		menuRole.resetOriginalValues();

		return menuRole;
	}

	/**
	 * Returns the menu role with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the menu role
	 * @return the menu role
	 * @throws NoSuchMenuRoleException if a menu role with the primary key could not be found
	 */
	@Override
	public MenuRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMenuRoleException {
		MenuRole menuRole = fetchByPrimaryKey(primaryKey);

		if (menuRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMenuRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return menuRole;
	}

	/**
	 * Returns the menu role with the primary key or throws a {@link NoSuchMenuRoleException} if it could not be found.
	 *
	 * @param menuRoleId the primary key of the menu role
	 * @return the menu role
	 * @throws NoSuchMenuRoleException if a menu role with the primary key could not be found
	 */
	@Override
	public MenuRole findByPrimaryKey(long menuRoleId)
		throws NoSuchMenuRoleException {
		return findByPrimaryKey((Serializable)menuRoleId);
	}

	/**
	 * Returns the menu role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the menu role
	 * @return the menu role, or <code>null</code> if a menu role with the primary key could not be found
	 */
	@Override
	public MenuRole fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
				MenuRoleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MenuRole menuRole = (MenuRole)serializable;

		if (menuRole == null) {
			Session session = null;

			try {
				session = openSession();

				menuRole = (MenuRole)session.get(MenuRoleImpl.class, primaryKey);

				if (menuRole != null) {
					cacheResult(menuRole);
				}
				else {
					entityCache.putResult(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
						MenuRoleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
					MenuRoleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return menuRole;
	}

	/**
	 * Returns the menu role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param menuRoleId the primary key of the menu role
	 * @return the menu role, or <code>null</code> if a menu role with the primary key could not be found
	 */
	@Override
	public MenuRole fetchByPrimaryKey(long menuRoleId) {
		return fetchByPrimaryKey((Serializable)menuRoleId);
	}

	@Override
	public Map<Serializable, MenuRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MenuRole> map = new HashMap<Serializable, MenuRole>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MenuRole menuRole = fetchByPrimaryKey(primaryKey);

			if (menuRole != null) {
				map.put(primaryKey, menuRole);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
					MenuRoleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MenuRole)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MENUROLE_WHERE_PKS_IN);

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

			for (MenuRole menuRole : (List<MenuRole>)q.list()) {
				map.put(menuRole.getPrimaryKeyObj(), menuRole);

				cacheResult(menuRole);

				uncachedPrimaryKeys.remove(menuRole.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MenuRoleModelImpl.ENTITY_CACHE_ENABLED,
					MenuRoleImpl.class, primaryKey, nullModel);
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
	 * Returns all the menu roles.
	 *
	 * @return the menu roles
	 */
	@Override
	public List<MenuRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the menu roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of menu roles
	 * @param end the upper bound of the range of menu roles (not inclusive)
	 * @return the range of menu roles
	 */
	@Override
	public List<MenuRole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the menu roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of menu roles
	 * @param end the upper bound of the range of menu roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of menu roles
	 */
	@Override
	public List<MenuRole> findAll(int start, int end,
		OrderByComparator<MenuRole> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the menu roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MenuRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of menu roles
	 * @param end the upper bound of the range of menu roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of menu roles
	 */
	@Override
	public List<MenuRole> findAll(int start, int end,
		OrderByComparator<MenuRole> orderByComparator, boolean retrieveFromCache) {
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

		List<MenuRole> list = null;

		if (retrieveFromCache) {
			list = (List<MenuRole>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MENUROLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MENUROLE;

				if (pagination) {
					sql = sql.concat(MenuRoleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MenuRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MenuRole>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the menu roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MenuRole menuRole : findAll()) {
			remove(menuRole);
		}
	}

	/**
	 * Returns the number of menu roles.
	 *
	 * @return the number of menu roles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MENUROLE);

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
		return MenuRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the menu role persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MenuRoleImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MENUROLE = "SELECT menuRole FROM MenuRole menuRole";
	private static final String _SQL_SELECT_MENUROLE_WHERE_PKS_IN = "SELECT menuRole FROM MenuRole menuRole WHERE menuRoleId IN (";
	private static final String _SQL_SELECT_MENUROLE_WHERE = "SELECT menuRole FROM MenuRole menuRole WHERE ";
	private static final String _SQL_COUNT_MENUROLE = "SELECT COUNT(menuRole) FROM MenuRole menuRole";
	private static final String _SQL_COUNT_MENUROLE_WHERE = "SELECT COUNT(menuRole) FROM MenuRole menuRole WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "menuRole.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MenuRole exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MenuRole exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(MenuRolePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}