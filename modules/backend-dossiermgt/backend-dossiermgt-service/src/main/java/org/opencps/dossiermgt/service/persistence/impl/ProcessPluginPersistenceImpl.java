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

import org.opencps.dossiermgt.exception.NoSuchProcessPluginException;
import org.opencps.dossiermgt.model.ProcessPlugin;
import org.opencps.dossiermgt.model.impl.ProcessPluginImpl;
import org.opencps.dossiermgt.model.impl.ProcessPluginModelImpl;
import org.opencps.dossiermgt.service.persistence.ProcessPluginPersistence;

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
 * The persistence implementation for the process plugin service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ProcessPluginPersistence
 * @see org.opencps.dossiermgt.service.persistence.ProcessPluginUtil
 * @generated
 */
@ProviderType
public class ProcessPluginPersistenceImpl extends BasePersistenceImpl<ProcessPlugin>
	implements ProcessPluginPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProcessPluginUtil} to access the process plugin persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProcessPluginImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginModelImpl.FINDER_CACHE_ENABLED,
			ProcessPluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginModelImpl.FINDER_CACHE_ENABLED,
			ProcessPluginImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginModelImpl.FINDER_CACHE_ENABLED,
			ProcessPluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginModelImpl.FINDER_CACHE_ENABLED,
			ProcessPluginImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			ProcessPluginModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the process plugins where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching process plugins
	 */
	@Override
	public List<ProcessPlugin> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process plugins where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process plugins
	 * @param end the upper bound of the range of process plugins (not inclusive)
	 * @return the range of matching process plugins
	 */
	@Override
	public List<ProcessPlugin> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process plugins where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process plugins
	 * @param end the upper bound of the range of process plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process plugins
	 */
	@Override
	public List<ProcessPlugin> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process plugins where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of process plugins
	 * @param end the upper bound of the range of process plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process plugins
	 */
	@Override
	public List<ProcessPlugin> findByUuid(String uuid, int start, int end,
		OrderByComparator<ProcessPlugin> orderByComparator,
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

		List<ProcessPlugin> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessPlugin>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessPlugin processPlugin : list) {
					if (!Objects.equals(uuid, processPlugin.getUuid())) {
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

			query.append(_SQL_SELECT_PROCESSPLUGIN_WHERE);

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
				query.append(ProcessPluginModelImpl.ORDER_BY_JPQL);
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
					list = (List<ProcessPlugin>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessPlugin>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process plugin in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process plugin
	 * @throws NoSuchProcessPluginException if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin findByUuid_First(String uuid,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException {
		ProcessPlugin processPlugin = fetchByUuid_First(uuid, orderByComparator);

		if (processPlugin != null) {
			return processPlugin;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchProcessPluginException(msg.toString());
	}

	/**
	 * Returns the first process plugin in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process plugin, or <code>null</code> if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin fetchByUuid_First(String uuid,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		List<ProcessPlugin> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process plugin in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process plugin
	 * @throws NoSuchProcessPluginException if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin findByUuid_Last(String uuid,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException {
		ProcessPlugin processPlugin = fetchByUuid_Last(uuid, orderByComparator);

		if (processPlugin != null) {
			return processPlugin;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchProcessPluginException(msg.toString());
	}

	/**
	 * Returns the last process plugin in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process plugin, or <code>null</code> if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin fetchByUuid_Last(String uuid,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ProcessPlugin> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process plugins before and after the current process plugin in the ordered set where uuid = &#63;.
	 *
	 * @param processPluginId the primary key of the current process plugin
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process plugin
	 * @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	 */
	@Override
	public ProcessPlugin[] findByUuid_PrevAndNext(long processPluginId,
		String uuid, OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException {
		ProcessPlugin processPlugin = findByPrimaryKey(processPluginId);

		Session session = null;

		try {
			session = openSession();

			ProcessPlugin[] array = new ProcessPluginImpl[3];

			array[0] = getByUuid_PrevAndNext(session, processPlugin, uuid,
					orderByComparator, true);

			array[1] = processPlugin;

			array[2] = getByUuid_PrevAndNext(session, processPlugin, uuid,
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

	protected ProcessPlugin getByUuid_PrevAndNext(Session session,
		ProcessPlugin processPlugin, String uuid,
		OrderByComparator<ProcessPlugin> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PROCESSPLUGIN_WHERE);

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
			query.append(ProcessPluginModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(processPlugin);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessPlugin> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process plugins where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ProcessPlugin processPlugin : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(processPlugin);
		}
	}

	/**
	 * Returns the number of process plugins where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching process plugins
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSPLUGIN_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "processPlugin.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "processPlugin.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(processPlugin.uuid IS NULL OR processPlugin.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginModelImpl.FINDER_CACHE_ENABLED,
			ProcessPluginImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ProcessPluginModelImpl.UUID_COLUMN_BITMASK |
			ProcessPluginModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the process plugin where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessPluginException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching process plugin
	 * @throws NoSuchProcessPluginException if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin findByUUID_G(String uuid, long groupId)
		throws NoSuchProcessPluginException {
		ProcessPlugin processPlugin = fetchByUUID_G(uuid, groupId);

		if (processPlugin == null) {
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

			throw new NoSuchProcessPluginException(msg.toString());
		}

		return processPlugin;
	}

	/**
	 * Returns the process plugin where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching process plugin, or <code>null</code> if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the process plugin where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process plugin, or <code>null</code> if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ProcessPlugin) {
			ProcessPlugin processPlugin = (ProcessPlugin)result;

			if (!Objects.equals(uuid, processPlugin.getUuid()) ||
					(groupId != processPlugin.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PROCESSPLUGIN_WHERE);

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

				List<ProcessPlugin> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ProcessPlugin processPlugin = list.get(0);

					result = processPlugin;

					cacheResult(processPlugin);
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
			return (ProcessPlugin)result;
		}
	}

	/**
	 * Removes the process plugin where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the process plugin that was removed
	 */
	@Override
	public ProcessPlugin removeByUUID_G(String uuid, long groupId)
		throws NoSuchProcessPluginException {
		ProcessPlugin processPlugin = findByUUID_G(uuid, groupId);

		return remove(processPlugin);
	}

	/**
	 * Returns the number of process plugins where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching process plugins
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSPLUGIN_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "processPlugin.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "processPlugin.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(processPlugin.uuid IS NULL OR processPlugin.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "processPlugin.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginModelImpl.FINDER_CACHE_ENABLED,
			ProcessPluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginModelImpl.FINDER_CACHE_ENABLED,
			ProcessPluginImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ProcessPluginModelImpl.UUID_COLUMN_BITMASK |
			ProcessPluginModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the process plugins where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching process plugins
	 */
	@Override
	public List<ProcessPlugin> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process plugins where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process plugins
	 * @param end the upper bound of the range of process plugins (not inclusive)
	 * @return the range of matching process plugins
	 */
	@Override
	public List<ProcessPlugin> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process plugins where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process plugins
	 * @param end the upper bound of the range of process plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process plugins
	 */
	@Override
	public List<ProcessPlugin> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessPlugin> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process plugins where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of process plugins
	 * @param end the upper bound of the range of process plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process plugins
	 */
	@Override
	public List<ProcessPlugin> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ProcessPlugin> orderByComparator,
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

		List<ProcessPlugin> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessPlugin>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessPlugin processPlugin : list) {
					if (!Objects.equals(uuid, processPlugin.getUuid()) ||
							(companyId != processPlugin.getCompanyId())) {
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

			query.append(_SQL_SELECT_PROCESSPLUGIN_WHERE);

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
				query.append(ProcessPluginModelImpl.ORDER_BY_JPQL);
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
					list = (List<ProcessPlugin>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessPlugin>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process plugin in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process plugin
	 * @throws NoSuchProcessPluginException if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException {
		ProcessPlugin processPlugin = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (processPlugin != null) {
			return processPlugin;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchProcessPluginException(msg.toString());
	}

	/**
	 * Returns the first process plugin in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process plugin, or <code>null</code> if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		List<ProcessPlugin> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process plugin in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process plugin
	 * @throws NoSuchProcessPluginException if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException {
		ProcessPlugin processPlugin = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (processPlugin != null) {
			return processPlugin;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchProcessPluginException(msg.toString());
	}

	/**
	 * Returns the last process plugin in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process plugin, or <code>null</code> if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ProcessPlugin> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process plugins before and after the current process plugin in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param processPluginId the primary key of the current process plugin
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process plugin
	 * @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	 */
	@Override
	public ProcessPlugin[] findByUuid_C_PrevAndNext(long processPluginId,
		String uuid, long companyId,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException {
		ProcessPlugin processPlugin = findByPrimaryKey(processPluginId);

		Session session = null;

		try {
			session = openSession();

			ProcessPlugin[] array = new ProcessPluginImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, processPlugin, uuid,
					companyId, orderByComparator, true);

			array[1] = processPlugin;

			array[2] = getByUuid_C_PrevAndNext(session, processPlugin, uuid,
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

	protected ProcessPlugin getByUuid_C_PrevAndNext(Session session,
		ProcessPlugin processPlugin, String uuid, long companyId,
		OrderByComparator<ProcessPlugin> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PROCESSPLUGIN_WHERE);

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
			query.append(ProcessPluginModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(processPlugin);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessPlugin> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process plugins where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ProcessPlugin processPlugin : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processPlugin);
		}
	}

	/**
	 * Returns the number of process plugins where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching process plugins
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSPLUGIN_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "processPlugin.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "processPlugin.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(processPlugin.uuid IS NULL OR processPlugin.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "processPlugin.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SC_SPID = new FinderPath(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginModelImpl.FINDER_CACHE_ENABLED,
			ProcessPluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySC_SPID",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_SPID =
		new FinderPath(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginModelImpl.FINDER_CACHE_ENABLED,
			ProcessPluginImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySC_SPID",
			new String[] { String.class.getName(), Long.class.getName() },
			ProcessPluginModelImpl.STEPCODE_COLUMN_BITMASK |
			ProcessPluginModelImpl.SERVICEPROCESSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SC_SPID = new FinderPath(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySC_SPID",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the process plugins where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @return the matching process plugins
	 */
	@Override
	public List<ProcessPlugin> findBySC_SPID(String stepCode,
		long serviceProcessId) {
		return findBySC_SPID(stepCode, serviceProcessId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process plugins where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process plugins
	 * @param end the upper bound of the range of process plugins (not inclusive)
	 * @return the range of matching process plugins
	 */
	@Override
	public List<ProcessPlugin> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end) {
		return findBySC_SPID(stepCode, serviceProcessId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process plugins where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process plugins
	 * @param end the upper bound of the range of process plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process plugins
	 */
	@Override
	public List<ProcessPlugin> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		return findBySC_SPID(stepCode, serviceProcessId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process plugins where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of process plugins
	 * @param end the upper bound of the range of process plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process plugins
	 */
	@Override
	public List<ProcessPlugin> findBySC_SPID(String stepCode,
		long serviceProcessId, int start, int end,
		OrderByComparator<ProcessPlugin> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_SPID;
			finderArgs = new Object[] { stepCode, serviceProcessId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SC_SPID;
			finderArgs = new Object[] {
					stepCode, serviceProcessId,
					
					start, end, orderByComparator
				};
		}

		List<ProcessPlugin> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessPlugin>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessPlugin processPlugin : list) {
					if (!Objects.equals(stepCode, processPlugin.getStepCode()) ||
							(serviceProcessId != processPlugin.getServiceProcessId())) {
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

			query.append(_SQL_SELECT_PROCESSPLUGIN_WHERE);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_2);
			}

			query.append(_FINDER_COLUMN_SC_SPID_SERVICEPROCESSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessPluginModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStepCode) {
					qPos.add(stepCode);
				}

				qPos.add(serviceProcessId);

				if (!pagination) {
					list = (List<ProcessPlugin>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessPlugin>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first process plugin in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process plugin
	 * @throws NoSuchProcessPluginException if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin findBySC_SPID_First(String stepCode,
		long serviceProcessId,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException {
		ProcessPlugin processPlugin = fetchBySC_SPID_First(stepCode,
				serviceProcessId, orderByComparator);

		if (processPlugin != null) {
			return processPlugin;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stepCode=");
		msg.append(stepCode);

		msg.append(", serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append("}");

		throw new NoSuchProcessPluginException(msg.toString());
	}

	/**
	 * Returns the first process plugin in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process plugin, or <code>null</code> if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin fetchBySC_SPID_First(String stepCode,
		long serviceProcessId,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		List<ProcessPlugin> list = findBySC_SPID(stepCode, serviceProcessId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process plugin in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process plugin
	 * @throws NoSuchProcessPluginException if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin findBySC_SPID_Last(String stepCode,
		long serviceProcessId,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException {
		ProcessPlugin processPlugin = fetchBySC_SPID_Last(stepCode,
				serviceProcessId, orderByComparator);

		if (processPlugin != null) {
			return processPlugin;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stepCode=");
		msg.append(stepCode);

		msg.append(", serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append("}");

		throw new NoSuchProcessPluginException(msg.toString());
	}

	/**
	 * Returns the last process plugin in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process plugin, or <code>null</code> if a matching process plugin could not be found
	 */
	@Override
	public ProcessPlugin fetchBySC_SPID_Last(String stepCode,
		long serviceProcessId,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		int count = countBySC_SPID(stepCode, serviceProcessId);

		if (count == 0) {
			return null;
		}

		List<ProcessPlugin> list = findBySC_SPID(stepCode, serviceProcessId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process plugins before and after the current process plugin in the ordered set where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param processPluginId the primary key of the current process plugin
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process plugin
	 * @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	 */
	@Override
	public ProcessPlugin[] findBySC_SPID_PrevAndNext(long processPluginId,
		String stepCode, long serviceProcessId,
		OrderByComparator<ProcessPlugin> orderByComparator)
		throws NoSuchProcessPluginException {
		ProcessPlugin processPlugin = findByPrimaryKey(processPluginId);

		Session session = null;

		try {
			session = openSession();

			ProcessPlugin[] array = new ProcessPluginImpl[3];

			array[0] = getBySC_SPID_PrevAndNext(session, processPlugin,
					stepCode, serviceProcessId, orderByComparator, true);

			array[1] = processPlugin;

			array[2] = getBySC_SPID_PrevAndNext(session, processPlugin,
					stepCode, serviceProcessId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessPlugin getBySC_SPID_PrevAndNext(Session session,
		ProcessPlugin processPlugin, String stepCode, long serviceProcessId,
		OrderByComparator<ProcessPlugin> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PROCESSPLUGIN_WHERE);

		boolean bindStepCode = false;

		if (stepCode == null) {
			query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_1);
		}
		else if (stepCode.equals("")) {
			query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_3);
		}
		else {
			bindStepCode = true;

			query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_2);
		}

		query.append(_FINDER_COLUMN_SC_SPID_SERVICEPROCESSID_2);

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
			query.append(ProcessPluginModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindStepCode) {
			qPos.add(stepCode);
		}

		qPos.add(serviceProcessId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processPlugin);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessPlugin> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process plugins where stepCode = &#63; and serviceProcessId = &#63; from the database.
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 */
	@Override
	public void removeBySC_SPID(String stepCode, long serviceProcessId) {
		for (ProcessPlugin processPlugin : findBySC_SPID(stepCode,
				serviceProcessId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processPlugin);
		}
	}

	/**
	 * Returns the number of process plugins where stepCode = &#63; and serviceProcessId = &#63;.
	 *
	 * @param stepCode the step code
	 * @param serviceProcessId the service process ID
	 * @return the number of matching process plugins
	 */
	@Override
	public int countBySC_SPID(String stepCode, long serviceProcessId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SC_SPID;

		Object[] finderArgs = new Object[] { stepCode, serviceProcessId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PROCESSPLUGIN_WHERE);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_SC_SPID_STEPCODE_2);
			}

			query.append(_FINDER_COLUMN_SC_SPID_SERVICEPROCESSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStepCode) {
					qPos.add(stepCode);
				}

				qPos.add(serviceProcessId);

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

	private static final String _FINDER_COLUMN_SC_SPID_STEPCODE_1 = "processPlugin.stepCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SC_SPID_STEPCODE_2 = "processPlugin.stepCode = ? AND ";
	private static final String _FINDER_COLUMN_SC_SPID_STEPCODE_3 = "(processPlugin.stepCode IS NULL OR processPlugin.stepCode = '') AND ";
	private static final String _FINDER_COLUMN_SC_SPID_SERVICEPROCESSID_2 = "processPlugin.serviceProcessId = ?";

	public ProcessPluginPersistenceImpl() {
		setModelClass(ProcessPlugin.class);

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
	 * Caches the process plugin in the entity cache if it is enabled.
	 *
	 * @param processPlugin the process plugin
	 */
	@Override
	public void cacheResult(ProcessPlugin processPlugin) {
		entityCache.putResult(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginImpl.class, processPlugin.getPrimaryKey(),
			processPlugin);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { processPlugin.getUuid(), processPlugin.getGroupId() },
			processPlugin);

		processPlugin.resetOriginalValues();
	}

	/**
	 * Caches the process plugins in the entity cache if it is enabled.
	 *
	 * @param processPlugins the process plugins
	 */
	@Override
	public void cacheResult(List<ProcessPlugin> processPlugins) {
		for (ProcessPlugin processPlugin : processPlugins) {
			if (entityCache.getResult(
						ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
						ProcessPluginImpl.class, processPlugin.getPrimaryKey()) == null) {
				cacheResult(processPlugin);
			}
			else {
				processPlugin.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all process plugins.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProcessPluginImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the process plugin.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProcessPlugin processPlugin) {
		entityCache.removeResult(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginImpl.class, processPlugin.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ProcessPluginModelImpl)processPlugin, true);
	}

	@Override
	public void clearCache(List<ProcessPlugin> processPlugins) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProcessPlugin processPlugin : processPlugins) {
			entityCache.removeResult(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
				ProcessPluginImpl.class, processPlugin.getPrimaryKey());

			clearUniqueFindersCache((ProcessPluginModelImpl)processPlugin, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ProcessPluginModelImpl processPluginModelImpl) {
		Object[] args = new Object[] {
				processPluginModelImpl.getUuid(),
				processPluginModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			processPluginModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ProcessPluginModelImpl processPluginModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					processPluginModelImpl.getUuid(),
					processPluginModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((processPluginModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processPluginModelImpl.getOriginalUuid(),
					processPluginModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new process plugin with the primary key. Does not add the process plugin to the database.
	 *
	 * @param processPluginId the primary key for the new process plugin
	 * @return the new process plugin
	 */
	@Override
	public ProcessPlugin create(long processPluginId) {
		ProcessPlugin processPlugin = new ProcessPluginImpl();

		processPlugin.setNew(true);
		processPlugin.setPrimaryKey(processPluginId);

		String uuid = PortalUUIDUtil.generate();

		processPlugin.setUuid(uuid);

		processPlugin.setCompanyId(companyProvider.getCompanyId());

		return processPlugin;
	}

	/**
	 * Removes the process plugin with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param processPluginId the primary key of the process plugin
	 * @return the process plugin that was removed
	 * @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	 */
	@Override
	public ProcessPlugin remove(long processPluginId)
		throws NoSuchProcessPluginException {
		return remove((Serializable)processPluginId);
	}

	/**
	 * Removes the process plugin with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the process plugin
	 * @return the process plugin that was removed
	 * @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	 */
	@Override
	public ProcessPlugin remove(Serializable primaryKey)
		throws NoSuchProcessPluginException {
		Session session = null;

		try {
			session = openSession();

			ProcessPlugin processPlugin = (ProcessPlugin)session.get(ProcessPluginImpl.class,
					primaryKey);

			if (processPlugin == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProcessPluginException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(processPlugin);
		}
		catch (NoSuchProcessPluginException nsee) {
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
	protected ProcessPlugin removeImpl(ProcessPlugin processPlugin) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(processPlugin)) {
				processPlugin = (ProcessPlugin)session.get(ProcessPluginImpl.class,
						processPlugin.getPrimaryKeyObj());
			}

			if (processPlugin != null) {
				session.delete(processPlugin);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (processPlugin != null) {
			clearCache(processPlugin);
		}

		return processPlugin;
	}

	@Override
	public ProcessPlugin updateImpl(ProcessPlugin processPlugin) {
		boolean isNew = processPlugin.isNew();

		if (!(processPlugin instanceof ProcessPluginModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(processPlugin.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(processPlugin);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in processPlugin proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProcessPlugin implementation " +
				processPlugin.getClass());
		}

		ProcessPluginModelImpl processPluginModelImpl = (ProcessPluginModelImpl)processPlugin;

		if (Validator.isNull(processPlugin.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			processPlugin.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (processPlugin.getCreateDate() == null)) {
			if (serviceContext == null) {
				processPlugin.setCreateDate(now);
			}
			else {
				processPlugin.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!processPluginModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				processPlugin.setModifiedDate(now);
			}
			else {
				processPlugin.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (processPlugin.isNew()) {
				session.save(processPlugin);

				processPlugin.setNew(false);
			}
			else {
				processPlugin = (ProcessPlugin)session.merge(processPlugin);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ProcessPluginModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { processPluginModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					processPluginModelImpl.getUuid(),
					processPluginModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					processPluginModelImpl.getStepCode(),
					processPluginModelImpl.getServiceProcessId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_SPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_SPID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((processPluginModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processPluginModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { processPluginModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((processPluginModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processPluginModelImpl.getOriginalUuid(),
						processPluginModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						processPluginModelImpl.getUuid(),
						processPluginModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((processPluginModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_SPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processPluginModelImpl.getOriginalStepCode(),
						processPluginModelImpl.getOriginalServiceProcessId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_SPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_SPID,
					args);

				args = new Object[] {
						processPluginModelImpl.getStepCode(),
						processPluginModelImpl.getServiceProcessId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_SPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SC_SPID,
					args);
			}
		}

		entityCache.putResult(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
			ProcessPluginImpl.class, processPlugin.getPrimaryKey(),
			processPlugin, false);

		clearUniqueFindersCache(processPluginModelImpl, false);
		cacheUniqueFindersCache(processPluginModelImpl);

		processPlugin.resetOriginalValues();

		return processPlugin;
	}

	/**
	 * Returns the process plugin with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the process plugin
	 * @return the process plugin
	 * @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	 */
	@Override
	public ProcessPlugin findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProcessPluginException {
		ProcessPlugin processPlugin = fetchByPrimaryKey(primaryKey);

		if (processPlugin == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProcessPluginException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return processPlugin;
	}

	/**
	 * Returns the process plugin with the primary key or throws a {@link NoSuchProcessPluginException} if it could not be found.
	 *
	 * @param processPluginId the primary key of the process plugin
	 * @return the process plugin
	 * @throws NoSuchProcessPluginException if a process plugin with the primary key could not be found
	 */
	@Override
	public ProcessPlugin findByPrimaryKey(long processPluginId)
		throws NoSuchProcessPluginException {
		return findByPrimaryKey((Serializable)processPluginId);
	}

	/**
	 * Returns the process plugin with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the process plugin
	 * @return the process plugin, or <code>null</code> if a process plugin with the primary key could not be found
	 */
	@Override
	public ProcessPlugin fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
				ProcessPluginImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProcessPlugin processPlugin = (ProcessPlugin)serializable;

		if (processPlugin == null) {
			Session session = null;

			try {
				session = openSession();

				processPlugin = (ProcessPlugin)session.get(ProcessPluginImpl.class,
						primaryKey);

				if (processPlugin != null) {
					cacheResult(processPlugin);
				}
				else {
					entityCache.putResult(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
						ProcessPluginImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
					ProcessPluginImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return processPlugin;
	}

	/**
	 * Returns the process plugin with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param processPluginId the primary key of the process plugin
	 * @return the process plugin, or <code>null</code> if a process plugin with the primary key could not be found
	 */
	@Override
	public ProcessPlugin fetchByPrimaryKey(long processPluginId) {
		return fetchByPrimaryKey((Serializable)processPluginId);
	}

	@Override
	public Map<Serializable, ProcessPlugin> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProcessPlugin> map = new HashMap<Serializable, ProcessPlugin>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProcessPlugin processPlugin = fetchByPrimaryKey(primaryKey);

			if (processPlugin != null) {
				map.put(primaryKey, processPlugin);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
					ProcessPluginImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProcessPlugin)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROCESSPLUGIN_WHERE_PKS_IN);

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

			for (ProcessPlugin processPlugin : (List<ProcessPlugin>)q.list()) {
				map.put(processPlugin.getPrimaryKeyObj(), processPlugin);

				cacheResult(processPlugin);

				uncachedPrimaryKeys.remove(processPlugin.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProcessPluginModelImpl.ENTITY_CACHE_ENABLED,
					ProcessPluginImpl.class, primaryKey, nullModel);
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
	 * Returns all the process plugins.
	 *
	 * @return the process plugins
	 */
	@Override
	public List<ProcessPlugin> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process plugins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process plugins
	 * @param end the upper bound of the range of process plugins (not inclusive)
	 * @return the range of process plugins
	 */
	@Override
	public List<ProcessPlugin> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the process plugins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process plugins
	 * @param end the upper bound of the range of process plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of process plugins
	 */
	@Override
	public List<ProcessPlugin> findAll(int start, int end,
		OrderByComparator<ProcessPlugin> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process plugins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process plugins
	 * @param end the upper bound of the range of process plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of process plugins
	 */
	@Override
	public List<ProcessPlugin> findAll(int start, int end,
		OrderByComparator<ProcessPlugin> orderByComparator,
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

		List<ProcessPlugin> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessPlugin>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROCESSPLUGIN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROCESSPLUGIN;

				if (pagination) {
					sql = sql.concat(ProcessPluginModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProcessPlugin>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessPlugin>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the process plugins from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProcessPlugin processPlugin : findAll()) {
			remove(processPlugin);
		}
	}

	/**
	 * Returns the number of process plugins.
	 *
	 * @return the number of process plugins
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROCESSPLUGIN);

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
		return ProcessPluginModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the process plugin persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProcessPluginImpl.class.getName());
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
	private static final String _SQL_SELECT_PROCESSPLUGIN = "SELECT processPlugin FROM ProcessPlugin processPlugin";
	private static final String _SQL_SELECT_PROCESSPLUGIN_WHERE_PKS_IN = "SELECT processPlugin FROM ProcessPlugin processPlugin WHERE processPluginId IN (";
	private static final String _SQL_SELECT_PROCESSPLUGIN_WHERE = "SELECT processPlugin FROM ProcessPlugin processPlugin WHERE ";
	private static final String _SQL_COUNT_PROCESSPLUGIN = "SELECT COUNT(processPlugin) FROM ProcessPlugin processPlugin";
	private static final String _SQL_COUNT_PROCESSPLUGIN_WHERE = "SELECT COUNT(processPlugin) FROM ProcessPlugin processPlugin WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "processPlugin.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProcessPlugin exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProcessPlugin exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ProcessPluginPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}