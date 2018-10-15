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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.usermgt.exception.NoSuchPreferencesException;
import org.opencps.usermgt.model.Preferences;
import org.opencps.usermgt.model.impl.PreferencesImpl;
import org.opencps.usermgt.model.impl.PreferencesModelImpl;
import org.opencps.usermgt.service.persistence.PreferencesPersistence;

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
 * The persistence implementation for the preferences service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see PreferencesPersistence
 * @see org.opencps.usermgt.service.persistence.PreferencesUtil
 * @generated
 */
@ProviderType
public class PreferencesPersistenceImpl extends BasePersistenceImpl<Preferences>
	implements PreferencesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PreferencesUtil} to access the preferences persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PreferencesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesModelImpl.FINDER_CACHE_ENABLED, PreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesModelImpl.FINDER_CACHE_ENABLED, PreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesModelImpl.FINDER_CACHE_ENABLED, PreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesModelImpl.FINDER_CACHE_ENABLED, PreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PreferencesModelImpl.UUID_COLUMN_BITMASK |
			PreferencesModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the preferenceses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching preferenceses
	 */
	@Override
	public List<Preferences> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the preferenceses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of preferenceses
	 * @param end the upper bound of the range of preferenceses (not inclusive)
	 * @return the range of matching preferenceses
	 */
	@Override
	public List<Preferences> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the preferenceses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of preferenceses
	 * @param end the upper bound of the range of preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching preferenceses
	 */
	@Override
	public List<Preferences> findByUuid(String uuid, int start, int end,
		OrderByComparator<Preferences> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the preferenceses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of preferenceses
	 * @param end the upper bound of the range of preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching preferenceses
	 */
	@Override
	public List<Preferences> findByUuid(String uuid, int start, int end,
		OrderByComparator<Preferences> orderByComparator,
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

		List<Preferences> list = null;

		if (retrieveFromCache) {
			list = (List<Preferences>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Preferences preferences : list) {
					if (!Objects.equals(uuid, preferences.getUuid())) {
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

			query.append(_SQL_SELECT_PREFERENCES_WHERE);

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
				query.append(PreferencesModelImpl.ORDER_BY_JPQL);
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
					list = (List<Preferences>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Preferences>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first preferences in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching preferences
	 * @throws NoSuchPreferencesException if a matching preferences could not be found
	 */
	@Override
	public Preferences findByUuid_First(String uuid,
		OrderByComparator<Preferences> orderByComparator)
		throws NoSuchPreferencesException {
		Preferences preferences = fetchByUuid_First(uuid, orderByComparator);

		if (preferences != null) {
			return preferences;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPreferencesException(msg.toString());
	}

	/**
	 * Returns the first preferences in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching preferences, or <code>null</code> if a matching preferences could not be found
	 */
	@Override
	public Preferences fetchByUuid_First(String uuid,
		OrderByComparator<Preferences> orderByComparator) {
		List<Preferences> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last preferences in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching preferences
	 * @throws NoSuchPreferencesException if a matching preferences could not be found
	 */
	@Override
	public Preferences findByUuid_Last(String uuid,
		OrderByComparator<Preferences> orderByComparator)
		throws NoSuchPreferencesException {
		Preferences preferences = fetchByUuid_Last(uuid, orderByComparator);

		if (preferences != null) {
			return preferences;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPreferencesException(msg.toString());
	}

	/**
	 * Returns the last preferences in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching preferences, or <code>null</code> if a matching preferences could not be found
	 */
	@Override
	public Preferences fetchByUuid_Last(String uuid,
		OrderByComparator<Preferences> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Preferences> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the preferenceses before and after the current preferences in the ordered set where uuid = &#63;.
	 *
	 * @param preferencesId the primary key of the current preferences
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next preferences
	 * @throws NoSuchPreferencesException if a preferences with the primary key could not be found
	 */
	@Override
	public Preferences[] findByUuid_PrevAndNext(long preferencesId,
		String uuid, OrderByComparator<Preferences> orderByComparator)
		throws NoSuchPreferencesException {
		Preferences preferences = findByPrimaryKey(preferencesId);

		Session session = null;

		try {
			session = openSession();

			Preferences[] array = new PreferencesImpl[3];

			array[0] = getByUuid_PrevAndNext(session, preferences, uuid,
					orderByComparator, true);

			array[1] = preferences;

			array[2] = getByUuid_PrevAndNext(session, preferences, uuid,
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

	protected Preferences getByUuid_PrevAndNext(Session session,
		Preferences preferences, String uuid,
		OrderByComparator<Preferences> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PREFERENCES_WHERE);

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
			query.append(PreferencesModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(preferences);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Preferences> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the preferenceses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Preferences preferences : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(preferences);
		}
	}

	/**
	 * Returns the number of preferenceses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching preferenceses
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PREFERENCES_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "preferences.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "preferences.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(preferences.uuid IS NULL OR preferences.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesModelImpl.FINDER_CACHE_ENABLED, PreferencesImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PreferencesModelImpl.UUID_COLUMN_BITMASK |
			PreferencesModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the preferences where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPreferencesException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching preferences
	 * @throws NoSuchPreferencesException if a matching preferences could not be found
	 */
	@Override
	public Preferences findByUUID_G(String uuid, long groupId)
		throws NoSuchPreferencesException {
		Preferences preferences = fetchByUUID_G(uuid, groupId);

		if (preferences == null) {
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

			throw new NoSuchPreferencesException(msg.toString());
		}

		return preferences;
	}

	/**
	 * Returns the preferences where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching preferences, or <code>null</code> if a matching preferences could not be found
	 */
	@Override
	public Preferences fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the preferences where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching preferences, or <code>null</code> if a matching preferences could not be found
	 */
	@Override
	public Preferences fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Preferences) {
			Preferences preferences = (Preferences)result;

			if (!Objects.equals(uuid, preferences.getUuid()) ||
					(groupId != preferences.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PREFERENCES_WHERE);

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

				List<Preferences> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Preferences preferences = list.get(0);

					result = preferences;

					cacheResult(preferences);
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
			return (Preferences)result;
		}
	}

	/**
	 * Removes the preferences where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the preferences that was removed
	 */
	@Override
	public Preferences removeByUUID_G(String uuid, long groupId)
		throws NoSuchPreferencesException {
		Preferences preferences = findByUUID_G(uuid, groupId);

		return remove(preferences);
	}

	/**
	 * Returns the number of preferenceses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching preferenceses
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PREFERENCES_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "preferences.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "preferences.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(preferences.uuid IS NULL OR preferences.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "preferences.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesModelImpl.FINDER_CACHE_ENABLED, PreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesModelImpl.FINDER_CACHE_ENABLED, PreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PreferencesModelImpl.UUID_COLUMN_BITMASK |
			PreferencesModelImpl.COMPANYID_COLUMN_BITMASK |
			PreferencesModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the preferenceses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching preferenceses
	 */
	@Override
	public List<Preferences> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the preferenceses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of preferenceses
	 * @param end the upper bound of the range of preferenceses (not inclusive)
	 * @return the range of matching preferenceses
	 */
	@Override
	public List<Preferences> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the preferenceses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of preferenceses
	 * @param end the upper bound of the range of preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching preferenceses
	 */
	@Override
	public List<Preferences> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Preferences> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the preferenceses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of preferenceses
	 * @param end the upper bound of the range of preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching preferenceses
	 */
	@Override
	public List<Preferences> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Preferences> orderByComparator,
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

		List<Preferences> list = null;

		if (retrieveFromCache) {
			list = (List<Preferences>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Preferences preferences : list) {
					if (!Objects.equals(uuid, preferences.getUuid()) ||
							(companyId != preferences.getCompanyId())) {
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

			query.append(_SQL_SELECT_PREFERENCES_WHERE);

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
				query.append(PreferencesModelImpl.ORDER_BY_JPQL);
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
					list = (List<Preferences>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Preferences>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching preferences
	 * @throws NoSuchPreferencesException if a matching preferences could not be found
	 */
	@Override
	public Preferences findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Preferences> orderByComparator)
		throws NoSuchPreferencesException {
		Preferences preferences = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (preferences != null) {
			return preferences;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPreferencesException(msg.toString());
	}

	/**
	 * Returns the first preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching preferences, or <code>null</code> if a matching preferences could not be found
	 */
	@Override
	public Preferences fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Preferences> orderByComparator) {
		List<Preferences> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching preferences
	 * @throws NoSuchPreferencesException if a matching preferences could not be found
	 */
	@Override
	public Preferences findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Preferences> orderByComparator)
		throws NoSuchPreferencesException {
		Preferences preferences = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (preferences != null) {
			return preferences;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPreferencesException(msg.toString());
	}

	/**
	 * Returns the last preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching preferences, or <code>null</code> if a matching preferences could not be found
	 */
	@Override
	public Preferences fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Preferences> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Preferences> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the preferenceses before and after the current preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param preferencesId the primary key of the current preferences
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next preferences
	 * @throws NoSuchPreferencesException if a preferences with the primary key could not be found
	 */
	@Override
	public Preferences[] findByUuid_C_PrevAndNext(long preferencesId,
		String uuid, long companyId,
		OrderByComparator<Preferences> orderByComparator)
		throws NoSuchPreferencesException {
		Preferences preferences = findByPrimaryKey(preferencesId);

		Session session = null;

		try {
			session = openSession();

			Preferences[] array = new PreferencesImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, preferences, uuid,
					companyId, orderByComparator, true);

			array[1] = preferences;

			array[2] = getByUuid_C_PrevAndNext(session, preferences, uuid,
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

	protected Preferences getByUuid_C_PrevAndNext(Session session,
		Preferences preferences, String uuid, long companyId,
		OrderByComparator<Preferences> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PREFERENCES_WHERE);

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
			query.append(PreferencesModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(preferences);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Preferences> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the preferenceses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Preferences preferences : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(preferences);
		}
	}

	/**
	 * Returns the number of preferenceses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching preferenceses
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PREFERENCES_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "preferences.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "preferences.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(preferences.uuid IS NULL OR preferences.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "preferences.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_USERID = new FinderPath(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesModelImpl.FINDER_CACHE_ENABLED, PreferencesImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_userId",
			new String[] { Long.class.getName(), Long.class.getName() },
			PreferencesModelImpl.GROUPID_COLUMN_BITMASK |
			PreferencesModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_USERID = new FinderPath(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_userId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the preferences where groupId = &#63; and userId = &#63; or throws a {@link NoSuchPreferencesException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching preferences
	 * @throws NoSuchPreferencesException if a matching preferences could not be found
	 */
	@Override
	public Preferences findByF_userId(long groupId, long userId)
		throws NoSuchPreferencesException {
		Preferences preferences = fetchByF_userId(groupId, userId);

		if (preferences == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPreferencesException(msg.toString());
		}

		return preferences;
	}

	/**
	 * Returns the preferences where groupId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching preferences, or <code>null</code> if a matching preferences could not be found
	 */
	@Override
	public Preferences fetchByF_userId(long groupId, long userId) {
		return fetchByF_userId(groupId, userId, true);
	}

	/**
	 * Returns the preferences where groupId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching preferences, or <code>null</code> if a matching preferences could not be found
	 */
	@Override
	public Preferences fetchByF_userId(long groupId, long userId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, userId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_USERID,
					finderArgs, this);
		}

		if (result instanceof Preferences) {
			Preferences preferences = (Preferences)result;

			if ((groupId != preferences.getGroupId()) ||
					(userId != preferences.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PREFERENCES_WHERE);

			query.append(_FINDER_COLUMN_F_USERID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				List<Preferences> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_USERID,
						finderArgs, list);
				}
				else {
					Preferences preferences = list.get(0);

					result = preferences;

					cacheResult(preferences);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_USERID,
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
			return (Preferences)result;
		}
	}

	/**
	 * Removes the preferences where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the preferences that was removed
	 */
	@Override
	public Preferences removeByF_userId(long groupId, long userId)
		throws NoSuchPreferencesException {
		Preferences preferences = findByF_userId(groupId, userId);

		return remove(preferences);
	}

	/**
	 * Returns the number of preferenceses where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching preferenceses
	 */
	@Override
	public int countByF_userId(long groupId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_USERID;

		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PREFERENCES_WHERE);

			query.append(_FINDER_COLUMN_F_USERID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_F_USERID_GROUPID_2 = "preferences.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_USERID_USERID_2 = "preferences.userId = ?";

	public PreferencesPersistenceImpl() {
		setModelClass(Preferences.class);

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
	 * Caches the preferences in the entity cache if it is enabled.
	 *
	 * @param preferences the preferences
	 */
	@Override
	public void cacheResult(Preferences preferences) {
		entityCache.putResult(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesImpl.class, preferences.getPrimaryKey(), preferences);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { preferences.getUuid(), preferences.getGroupId() },
			preferences);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_USERID,
			new Object[] { preferences.getGroupId(), preferences.getUserId() },
			preferences);

		preferences.resetOriginalValues();
	}

	/**
	 * Caches the preferenceses in the entity cache if it is enabled.
	 *
	 * @param preferenceses the preferenceses
	 */
	@Override
	public void cacheResult(List<Preferences> preferenceses) {
		for (Preferences preferences : preferenceses) {
			if (entityCache.getResult(
						PreferencesModelImpl.ENTITY_CACHE_ENABLED,
						PreferencesImpl.class, preferences.getPrimaryKey()) == null) {
				cacheResult(preferences);
			}
			else {
				preferences.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all preferenceses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PreferencesImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the preferences.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Preferences preferences) {
		entityCache.removeResult(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesImpl.class, preferences.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PreferencesModelImpl)preferences, true);
	}

	@Override
	public void clearCache(List<Preferences> preferenceses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Preferences preferences : preferenceses) {
			entityCache.removeResult(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
				PreferencesImpl.class, preferences.getPrimaryKey());

			clearUniqueFindersCache((PreferencesModelImpl)preferences, true);
		}
	}

	protected void cacheUniqueFindersCache(
		PreferencesModelImpl preferencesModelImpl) {
		Object[] args = new Object[] {
				preferencesModelImpl.getUuid(),
				preferencesModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			preferencesModelImpl, false);

		args = new Object[] {
				preferencesModelImpl.getGroupId(),
				preferencesModelImpl.getUserId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_USERID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_USERID, args,
			preferencesModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PreferencesModelImpl preferencesModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					preferencesModelImpl.getUuid(),
					preferencesModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((preferencesModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					preferencesModelImpl.getOriginalUuid(),
					preferencesModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					preferencesModelImpl.getGroupId(),
					preferencesModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_USERID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_USERID, args);
		}

		if ((preferencesModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_USERID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					preferencesModelImpl.getOriginalGroupId(),
					preferencesModelImpl.getOriginalUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_USERID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_USERID, args);
		}
	}

	/**
	 * Creates a new preferences with the primary key. Does not add the preferences to the database.
	 *
	 * @param preferencesId the primary key for the new preferences
	 * @return the new preferences
	 */
	@Override
	public Preferences create(long preferencesId) {
		Preferences preferences = new PreferencesImpl();

		preferences.setNew(true);
		preferences.setPrimaryKey(preferencesId);

		String uuid = PortalUUIDUtil.generate();

		preferences.setUuid(uuid);

		preferences.setCompanyId(companyProvider.getCompanyId());

		return preferences;
	}

	/**
	 * Removes the preferences with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param preferencesId the primary key of the preferences
	 * @return the preferences that was removed
	 * @throws NoSuchPreferencesException if a preferences with the primary key could not be found
	 */
	@Override
	public Preferences remove(long preferencesId)
		throws NoSuchPreferencesException {
		return remove((Serializable)preferencesId);
	}

	/**
	 * Removes the preferences with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the preferences
	 * @return the preferences that was removed
	 * @throws NoSuchPreferencesException if a preferences with the primary key could not be found
	 */
	@Override
	public Preferences remove(Serializable primaryKey)
		throws NoSuchPreferencesException {
		Session session = null;

		try {
			session = openSession();

			Preferences preferences = (Preferences)session.get(PreferencesImpl.class,
					primaryKey);

			if (preferences == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPreferencesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(preferences);
		}
		catch (NoSuchPreferencesException nsee) {
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
	protected Preferences removeImpl(Preferences preferences) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(preferences)) {
				preferences = (Preferences)session.get(PreferencesImpl.class,
						preferences.getPrimaryKeyObj());
			}

			if (preferences != null) {
				session.delete(preferences);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (preferences != null) {
			clearCache(preferences);
		}

		return preferences;
	}

	@Override
	public Preferences updateImpl(Preferences preferences) {
		boolean isNew = preferences.isNew();

		if (!(preferences instanceof PreferencesModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(preferences.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(preferences);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in preferences proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Preferences implementation " +
				preferences.getClass());
		}

		PreferencesModelImpl preferencesModelImpl = (PreferencesModelImpl)preferences;

		if (Validator.isNull(preferences.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			preferences.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (preferences.getCreateDate() == null)) {
			if (serviceContext == null) {
				preferences.setCreateDate(now);
			}
			else {
				preferences.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!preferencesModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				preferences.setModifiedDate(now);
			}
			else {
				preferences.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (preferences.isNew()) {
				session.save(preferences);

				preferences.setNew(false);
			}
			else {
				preferences = (Preferences)session.merge(preferences);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PreferencesModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { preferencesModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					preferencesModelImpl.getUuid(),
					preferencesModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((preferencesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						preferencesModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { preferencesModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((preferencesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						preferencesModelImpl.getOriginalUuid(),
						preferencesModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						preferencesModelImpl.getUuid(),
						preferencesModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PreferencesImpl.class, preferences.getPrimaryKey(), preferences,
			false);

		clearUniqueFindersCache(preferencesModelImpl, false);
		cacheUniqueFindersCache(preferencesModelImpl);

		preferences.resetOriginalValues();

		return preferences;
	}

	/**
	 * Returns the preferences with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the preferences
	 * @return the preferences
	 * @throws NoSuchPreferencesException if a preferences with the primary key could not be found
	 */
	@Override
	public Preferences findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPreferencesException {
		Preferences preferences = fetchByPrimaryKey(primaryKey);

		if (preferences == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPreferencesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return preferences;
	}

	/**
	 * Returns the preferences with the primary key or throws a {@link NoSuchPreferencesException} if it could not be found.
	 *
	 * @param preferencesId the primary key of the preferences
	 * @return the preferences
	 * @throws NoSuchPreferencesException if a preferences with the primary key could not be found
	 */
	@Override
	public Preferences findByPrimaryKey(long preferencesId)
		throws NoSuchPreferencesException {
		return findByPrimaryKey((Serializable)preferencesId);
	}

	/**
	 * Returns the preferences with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the preferences
	 * @return the preferences, or <code>null</code> if a preferences with the primary key could not be found
	 */
	@Override
	public Preferences fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
				PreferencesImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Preferences preferences = (Preferences)serializable;

		if (preferences == null) {
			Session session = null;

			try {
				session = openSession();

				preferences = (Preferences)session.get(PreferencesImpl.class,
						primaryKey);

				if (preferences != null) {
					cacheResult(preferences);
				}
				else {
					entityCache.putResult(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
						PreferencesImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
					PreferencesImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return preferences;
	}

	/**
	 * Returns the preferences with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param preferencesId the primary key of the preferences
	 * @return the preferences, or <code>null</code> if a preferences with the primary key could not be found
	 */
	@Override
	public Preferences fetchByPrimaryKey(long preferencesId) {
		return fetchByPrimaryKey((Serializable)preferencesId);
	}

	@Override
	public Map<Serializable, Preferences> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Preferences> map = new HashMap<Serializable, Preferences>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Preferences preferences = fetchByPrimaryKey(primaryKey);

			if (preferences != null) {
				map.put(primaryKey, preferences);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
					PreferencesImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Preferences)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PREFERENCES_WHERE_PKS_IN);

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

			for (Preferences preferences : (List<Preferences>)q.list()) {
				map.put(preferences.getPrimaryKeyObj(), preferences);

				cacheResult(preferences);

				uncachedPrimaryKeys.remove(preferences.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PreferencesModelImpl.ENTITY_CACHE_ENABLED,
					PreferencesImpl.class, primaryKey, nullModel);
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
	 * Returns all the preferenceses.
	 *
	 * @return the preferenceses
	 */
	@Override
	public List<Preferences> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the preferenceses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of preferenceses
	 * @param end the upper bound of the range of preferenceses (not inclusive)
	 * @return the range of preferenceses
	 */
	@Override
	public List<Preferences> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the preferenceses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of preferenceses
	 * @param end the upper bound of the range of preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of preferenceses
	 */
	@Override
	public List<Preferences> findAll(int start, int end,
		OrderByComparator<Preferences> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the preferenceses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of preferenceses
	 * @param end the upper bound of the range of preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of preferenceses
	 */
	@Override
	public List<Preferences> findAll(int start, int end,
		OrderByComparator<Preferences> orderByComparator,
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

		List<Preferences> list = null;

		if (retrieveFromCache) {
			list = (List<Preferences>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PREFERENCES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PREFERENCES;

				if (pagination) {
					sql = sql.concat(PreferencesModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Preferences>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Preferences>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the preferenceses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Preferences preferences : findAll()) {
			remove(preferences);
		}
	}

	/**
	 * Returns the number of preferenceses.
	 *
	 * @return the number of preferenceses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PREFERENCES);

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
		return PreferencesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the preferences persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PreferencesImpl.class.getName());
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
	private static final String _SQL_SELECT_PREFERENCES = "SELECT preferences FROM Preferences preferences";
	private static final String _SQL_SELECT_PREFERENCES_WHERE_PKS_IN = "SELECT preferences FROM Preferences preferences WHERE preferencesId IN (";
	private static final String _SQL_SELECT_PREFERENCES_WHERE = "SELECT preferences FROM Preferences preferences WHERE ";
	private static final String _SQL_COUNT_PREFERENCES = "SELECT COUNT(preferences) FROM Preferences preferences";
	private static final String _SQL_COUNT_PREFERENCES_WHERE = "SELECT COUNT(preferences) FROM Preferences preferences WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "preferences.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Preferences exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Preferences exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PreferencesPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}