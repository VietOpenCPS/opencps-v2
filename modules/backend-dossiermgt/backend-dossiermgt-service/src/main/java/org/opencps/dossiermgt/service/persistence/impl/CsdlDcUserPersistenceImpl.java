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

import org.opencps.dossiermgt.exception.NoSuchCsdlDcUserException;
import org.opencps.dossiermgt.model.CsdlDcUser;
import org.opencps.dossiermgt.model.impl.CsdlDcUserImpl;
import org.opencps.dossiermgt.model.impl.CsdlDcUserModelImpl;
import org.opencps.dossiermgt.service.persistence.CsdlDcUserPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the csdl dc user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see CsdlDcUserPersistence
 * @see org.opencps.dossiermgt.service.persistence.CsdlDcUserUtil
 * @generated
 */
@ProviderType
public class CsdlDcUserPersistenceImpl extends BasePersistenceImpl<CsdlDcUser>
	implements CsdlDcUserPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CsdlDcUserUtil} to access the csdl dc user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CsdlDcUserImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserModelImpl.FINDER_CACHE_ENABLED, CsdlDcUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserModelImpl.FINDER_CACHE_ENABLED, CsdlDcUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserModelImpl.FINDER_CACHE_ENABLED, CsdlDcUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserModelImpl.FINDER_CACHE_ENABLED, CsdlDcUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CsdlDcUserModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the csdl dc users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching csdl dc users
	 */
	@Override
	public List<CsdlDcUser> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the csdl dc users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csdl dc users
	 * @param end the upper bound of the range of csdl dc users (not inclusive)
	 * @return the range of matching csdl dc users
	 */
	@Override
	public List<CsdlDcUser> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the csdl dc users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csdl dc users
	 * @param end the upper bound of the range of csdl dc users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csdl dc users
	 */
	@Override
	public List<CsdlDcUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<CsdlDcUser> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the csdl dc users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csdl dc users
	 * @param end the upper bound of the range of csdl dc users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csdl dc users
	 */
	@Override
	public List<CsdlDcUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<CsdlDcUser> orderByComparator,
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

		List<CsdlDcUser> list = null;

		if (retrieveFromCache) {
			list = (List<CsdlDcUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CsdlDcUser csdlDcUser : list) {
					if (!Objects.equals(uuid, csdlDcUser.getUuid())) {
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

			query.append(_SQL_SELECT_CSDLDCUSER_WHERE);

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
				query.append(CsdlDcUserModelImpl.ORDER_BY_JPQL);
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
					list = (List<CsdlDcUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CsdlDcUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first csdl dc user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csdl dc user
	 * @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	 */
	@Override
	public CsdlDcUser findByUuid_First(String uuid,
		OrderByComparator<CsdlDcUser> orderByComparator)
		throws NoSuchCsdlDcUserException {
		CsdlDcUser csdlDcUser = fetchByUuid_First(uuid, orderByComparator);

		if (csdlDcUser != null) {
			return csdlDcUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCsdlDcUserException(msg.toString());
	}

	/**
	 * Returns the first csdl dc user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	 */
	@Override
	public CsdlDcUser fetchByUuid_First(String uuid,
		OrderByComparator<CsdlDcUser> orderByComparator) {
		List<CsdlDcUser> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last csdl dc user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csdl dc user
	 * @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	 */
	@Override
	public CsdlDcUser findByUuid_Last(String uuid,
		OrderByComparator<CsdlDcUser> orderByComparator)
		throws NoSuchCsdlDcUserException {
		CsdlDcUser csdlDcUser = fetchByUuid_Last(uuid, orderByComparator);

		if (csdlDcUser != null) {
			return csdlDcUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCsdlDcUserException(msg.toString());
	}

	/**
	 * Returns the last csdl dc user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	 */
	@Override
	public CsdlDcUser fetchByUuid_Last(String uuid,
		OrderByComparator<CsdlDcUser> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CsdlDcUser> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the csdl dc users before and after the current csdl dc user in the ordered set where uuid = &#63;.
	 *
	 * @param idDcUser the primary key of the current csdl dc user
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csdl dc user
	 * @throws NoSuchCsdlDcUserException if a csdl dc user with the primary key could not be found
	 */
	@Override
	public CsdlDcUser[] findByUuid_PrevAndNext(long idDcUser, String uuid,
		OrderByComparator<CsdlDcUser> orderByComparator)
		throws NoSuchCsdlDcUserException {
		CsdlDcUser csdlDcUser = findByPrimaryKey(idDcUser);

		Session session = null;

		try {
			session = openSession();

			CsdlDcUser[] array = new CsdlDcUserImpl[3];

			array[0] = getByUuid_PrevAndNext(session, csdlDcUser, uuid,
					orderByComparator, true);

			array[1] = csdlDcUser;

			array[2] = getByUuid_PrevAndNext(session, csdlDcUser, uuid,
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

	protected CsdlDcUser getByUuid_PrevAndNext(Session session,
		CsdlDcUser csdlDcUser, String uuid,
		OrderByComparator<CsdlDcUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CSDLDCUSER_WHERE);

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
			query.append(CsdlDcUserModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(csdlDcUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CsdlDcUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the csdl dc users where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CsdlDcUser csdlDcUser : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(csdlDcUser);
		}
	}

	/**
	 * Returns the number of csdl dc users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching csdl dc users
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CSDLDCUSER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "csdlDcUser.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "csdlDcUser.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(csdlDcUser.uuid IS NULL OR csdlDcUser.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserModelImpl.FINDER_CACHE_ENABLED, CsdlDcUserImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			CsdlDcUserModelImpl.UUID_COLUMN_BITMASK |
			CsdlDcUserModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the csdl dc user where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCsdlDcUserException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching csdl dc user
	 * @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	 */
	@Override
	public CsdlDcUser findByUUID_G(String uuid, long groupId)
		throws NoSuchCsdlDcUserException {
		CsdlDcUser csdlDcUser = fetchByUUID_G(uuid, groupId);

		if (csdlDcUser == null) {
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

			throw new NoSuchCsdlDcUserException(msg.toString());
		}

		return csdlDcUser;
	}

	/**
	 * Returns the csdl dc user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	 */
	@Override
	public CsdlDcUser fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the csdl dc user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	 */
	@Override
	public CsdlDcUser fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof CsdlDcUser) {
			CsdlDcUser csdlDcUser = (CsdlDcUser)result;

			if (!Objects.equals(uuid, csdlDcUser.getUuid()) ||
					(groupId != csdlDcUser.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CSDLDCUSER_WHERE);

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

				List<CsdlDcUser> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					CsdlDcUser csdlDcUser = list.get(0);

					result = csdlDcUser;

					cacheResult(csdlDcUser);
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
			return (CsdlDcUser)result;
		}
	}

	/**
	 * Removes the csdl dc user where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the csdl dc user that was removed
	 */
	@Override
	public CsdlDcUser removeByUUID_G(String uuid, long groupId)
		throws NoSuchCsdlDcUserException {
		CsdlDcUser csdlDcUser = findByUUID_G(uuid, groupId);

		return remove(csdlDcUser);
	}

	/**
	 * Returns the number of csdl dc users where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching csdl dc users
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CSDLDCUSER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "csdlDcUser.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "csdlDcUser.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(csdlDcUser.uuid IS NULL OR csdlDcUser.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "csdlDcUser.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserModelImpl.FINDER_CACHE_ENABLED, CsdlDcUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserModelImpl.FINDER_CACHE_ENABLED, CsdlDcUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			CsdlDcUserModelImpl.UUID_COLUMN_BITMASK |
			CsdlDcUserModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the csdl dc users where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching csdl dc users
	 */
	@Override
	public List<CsdlDcUser> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the csdl dc users where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of csdl dc users
	 * @param end the upper bound of the range of csdl dc users (not inclusive)
	 * @return the range of matching csdl dc users
	 */
	@Override
	public List<CsdlDcUser> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the csdl dc users where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of csdl dc users
	 * @param end the upper bound of the range of csdl dc users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csdl dc users
	 */
	@Override
	public List<CsdlDcUser> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<CsdlDcUser> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the csdl dc users where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of csdl dc users
	 * @param end the upper bound of the range of csdl dc users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csdl dc users
	 */
	@Override
	public List<CsdlDcUser> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<CsdlDcUser> orderByComparator,
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

		List<CsdlDcUser> list = null;

		if (retrieveFromCache) {
			list = (List<CsdlDcUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CsdlDcUser csdlDcUser : list) {
					if (!Objects.equals(uuid, csdlDcUser.getUuid()) ||
							(companyId != csdlDcUser.getCompanyId())) {
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

			query.append(_SQL_SELECT_CSDLDCUSER_WHERE);

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
				query.append(CsdlDcUserModelImpl.ORDER_BY_JPQL);
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
					list = (List<CsdlDcUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CsdlDcUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first csdl dc user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csdl dc user
	 * @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	 */
	@Override
	public CsdlDcUser findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<CsdlDcUser> orderByComparator)
		throws NoSuchCsdlDcUserException {
		CsdlDcUser csdlDcUser = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (csdlDcUser != null) {
			return csdlDcUser;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCsdlDcUserException(msg.toString());
	}

	/**
	 * Returns the first csdl dc user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	 */
	@Override
	public CsdlDcUser fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<CsdlDcUser> orderByComparator) {
		List<CsdlDcUser> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last csdl dc user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csdl dc user
	 * @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	 */
	@Override
	public CsdlDcUser findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<CsdlDcUser> orderByComparator)
		throws NoSuchCsdlDcUserException {
		CsdlDcUser csdlDcUser = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (csdlDcUser != null) {
			return csdlDcUser;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCsdlDcUserException(msg.toString());
	}

	/**
	 * Returns the last csdl dc user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	 */
	@Override
	public CsdlDcUser fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<CsdlDcUser> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CsdlDcUser> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the csdl dc users before and after the current csdl dc user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param idDcUser the primary key of the current csdl dc user
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csdl dc user
	 * @throws NoSuchCsdlDcUserException if a csdl dc user with the primary key could not be found
	 */
	@Override
	public CsdlDcUser[] findByUuid_C_PrevAndNext(long idDcUser, String uuid,
		long companyId, OrderByComparator<CsdlDcUser> orderByComparator)
		throws NoSuchCsdlDcUserException {
		CsdlDcUser csdlDcUser = findByPrimaryKey(idDcUser);

		Session session = null;

		try {
			session = openSession();

			CsdlDcUser[] array = new CsdlDcUserImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, csdlDcUser, uuid,
					companyId, orderByComparator, true);

			array[1] = csdlDcUser;

			array[2] = getByUuid_C_PrevAndNext(session, csdlDcUser, uuid,
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

	protected CsdlDcUser getByUuid_C_PrevAndNext(Session session,
		CsdlDcUser csdlDcUser, String uuid, long companyId,
		OrderByComparator<CsdlDcUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CSDLDCUSER_WHERE);

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
			query.append(CsdlDcUserModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(csdlDcUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CsdlDcUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the csdl dc users where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CsdlDcUser csdlDcUser : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(csdlDcUser);
		}
	}

	/**
	 * Returns the number of csdl dc users where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching csdl dc users
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CSDLDCUSER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "csdlDcUser.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "csdlDcUser.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(csdlDcUser.uuid IS NULL OR csdlDcUser.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "csdlDcUser.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GOV_EMAIL_STATUS = new FinderPath(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserModelImpl.FINDER_CACHE_ENABLED, CsdlDcUserImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_GOV_EMAIL_STATUS",
			new String[] {
				String.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			CsdlDcUserModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			CsdlDcUserModelImpl.EMPLOYEEEMAIL_COLUMN_BITMASK |
			CsdlDcUserModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GOV_EMAIL_STATUS = new FinderPath(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_GOV_EMAIL_STATUS",
			new String[] {
				String.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns the csdl dc user where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63; or throws a {@link NoSuchCsdlDcUserException} if it could not be found.
	 *
	 * @param govAgencyCode the gov agency code
	 * @param employeeEmail the employee email
	 * @param status the status
	 * @return the matching csdl dc user
	 * @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	 */
	@Override
	public CsdlDcUser findByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status) throws NoSuchCsdlDcUserException {
		CsdlDcUser csdlDcUser = fetchByF_GOV_EMAIL_STATUS(govAgencyCode,
				employeeEmail, status);

		if (csdlDcUser == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("govAgencyCode=");
			msg.append(govAgencyCode);

			msg.append(", employeeEmail=");
			msg.append(employeeEmail);

			msg.append(", status=");
			msg.append(status);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCsdlDcUserException(msg.toString());
		}

		return csdlDcUser;
	}

	/**
	 * Returns the csdl dc user where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param govAgencyCode the gov agency code
	 * @param employeeEmail the employee email
	 * @param status the status
	 * @return the matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	 */
	@Override
	public CsdlDcUser fetchByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status) {
		return fetchByF_GOV_EMAIL_STATUS(govAgencyCode, employeeEmail, status,
			true);
	}

	/**
	 * Returns the csdl dc user where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param govAgencyCode the gov agency code
	 * @param employeeEmail the employee email
	 * @param status the status
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	 */
	@Override
	public CsdlDcUser fetchByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { govAgencyCode, employeeEmail, status };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GOV_EMAIL_STATUS,
					finderArgs, this);
		}

		if (result instanceof CsdlDcUser) {
			CsdlDcUser csdlDcUser = (CsdlDcUser)result;

			if (!Objects.equals(govAgencyCode, csdlDcUser.getGovAgencyCode()) ||
					!Objects.equals(employeeEmail, csdlDcUser.getEmployeeEmail()) ||
					(status != csdlDcUser.getStatus())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_CSDLDCUSER_WHERE);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_F_GOV_EMAIL_STATUS_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GOV_EMAIL_STATUS_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_F_GOV_EMAIL_STATUS_GOVAGENCYCODE_2);
			}

			boolean bindEmployeeEmail = false;

			if (employeeEmail == null) {
				query.append(_FINDER_COLUMN_F_GOV_EMAIL_STATUS_EMPLOYEEEMAIL_1);
			}
			else if (employeeEmail.equals("")) {
				query.append(_FINDER_COLUMN_F_GOV_EMAIL_STATUS_EMPLOYEEEMAIL_3);
			}
			else {
				bindEmployeeEmail = true;

				query.append(_FINDER_COLUMN_F_GOV_EMAIL_STATUS_EMPLOYEEEMAIL_2);
			}

			query.append(_FINDER_COLUMN_F_GOV_EMAIL_STATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindEmployeeEmail) {
					qPos.add(employeeEmail);
				}

				qPos.add(status);

				List<CsdlDcUser> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GOV_EMAIL_STATUS,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CsdlDcUserPersistenceImpl.fetchByF_GOV_EMAIL_STATUS(String, String, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CsdlDcUser csdlDcUser = list.get(0);

					result = csdlDcUser;

					cacheResult(csdlDcUser);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GOV_EMAIL_STATUS,
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
			return (CsdlDcUser)result;
		}
	}

	/**
	 * Removes the csdl dc user where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63; from the database.
	 *
	 * @param govAgencyCode the gov agency code
	 * @param employeeEmail the employee email
	 * @param status the status
	 * @return the csdl dc user that was removed
	 */
	@Override
	public CsdlDcUser removeByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status) throws NoSuchCsdlDcUserException {
		CsdlDcUser csdlDcUser = findByF_GOV_EMAIL_STATUS(govAgencyCode,
				employeeEmail, status);

		return remove(csdlDcUser);
	}

	/**
	 * Returns the number of csdl dc users where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63;.
	 *
	 * @param govAgencyCode the gov agency code
	 * @param employeeEmail the employee email
	 * @param status the status
	 * @return the number of matching csdl dc users
	 */
	@Override
	public int countByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GOV_EMAIL_STATUS;

		Object[] finderArgs = new Object[] { govAgencyCode, employeeEmail, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CSDLDCUSER_WHERE);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_F_GOV_EMAIL_STATUS_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GOV_EMAIL_STATUS_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_F_GOV_EMAIL_STATUS_GOVAGENCYCODE_2);
			}

			boolean bindEmployeeEmail = false;

			if (employeeEmail == null) {
				query.append(_FINDER_COLUMN_F_GOV_EMAIL_STATUS_EMPLOYEEEMAIL_1);
			}
			else if (employeeEmail.equals("")) {
				query.append(_FINDER_COLUMN_F_GOV_EMAIL_STATUS_EMPLOYEEEMAIL_3);
			}
			else {
				bindEmployeeEmail = true;

				query.append(_FINDER_COLUMN_F_GOV_EMAIL_STATUS_EMPLOYEEEMAIL_2);
			}

			query.append(_FINDER_COLUMN_F_GOV_EMAIL_STATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindEmployeeEmail) {
					qPos.add(employeeEmail);
				}

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_F_GOV_EMAIL_STATUS_GOVAGENCYCODE_1 =
		"csdlDcUser.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GOV_EMAIL_STATUS_GOVAGENCYCODE_2 =
		"csdlDcUser.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_F_GOV_EMAIL_STATUS_GOVAGENCYCODE_3 =
		"(csdlDcUser.govAgencyCode IS NULL OR csdlDcUser.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_F_GOV_EMAIL_STATUS_EMPLOYEEEMAIL_1 =
		"csdlDcUser.employeeEmail IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GOV_EMAIL_STATUS_EMPLOYEEEMAIL_2 =
		"csdlDcUser.employeeEmail = ? AND ";
	private static final String _FINDER_COLUMN_F_GOV_EMAIL_STATUS_EMPLOYEEEMAIL_3 =
		"(csdlDcUser.employeeEmail IS NULL OR csdlDcUser.employeeEmail = '') AND ";
	private static final String _FINDER_COLUMN_F_GOV_EMAIL_STATUS_STATUS_2 = "csdlDcUser.status = ?";

	public CsdlDcUserPersistenceImpl() {
		setModelClass(CsdlDcUser.class);

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
	 * Caches the csdl dc user in the entity cache if it is enabled.
	 *
	 * @param csdlDcUser the csdl dc user
	 */
	@Override
	public void cacheResult(CsdlDcUser csdlDcUser) {
		entityCache.putResult(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserImpl.class, csdlDcUser.getPrimaryKey(), csdlDcUser);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { csdlDcUser.getUuid(), csdlDcUser.getGroupId() },
			csdlDcUser);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GOV_EMAIL_STATUS,
			new Object[] {
				csdlDcUser.getGovAgencyCode(), csdlDcUser.getEmployeeEmail(),
				csdlDcUser.getStatus()
			}, csdlDcUser);

		csdlDcUser.resetOriginalValues();
	}

	/**
	 * Caches the csdl dc users in the entity cache if it is enabled.
	 *
	 * @param csdlDcUsers the csdl dc users
	 */
	@Override
	public void cacheResult(List<CsdlDcUser> csdlDcUsers) {
		for (CsdlDcUser csdlDcUser : csdlDcUsers) {
			if (entityCache.getResult(
						CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
						CsdlDcUserImpl.class, csdlDcUser.getPrimaryKey()) == null) {
				cacheResult(csdlDcUser);
			}
			else {
				csdlDcUser.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all csdl dc users.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CsdlDcUserImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the csdl dc user.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CsdlDcUser csdlDcUser) {
		entityCache.removeResult(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserImpl.class, csdlDcUser.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CsdlDcUserModelImpl)csdlDcUser, true);
	}

	@Override
	public void clearCache(List<CsdlDcUser> csdlDcUsers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CsdlDcUser csdlDcUser : csdlDcUsers) {
			entityCache.removeResult(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
				CsdlDcUserImpl.class, csdlDcUser.getPrimaryKey());

			clearUniqueFindersCache((CsdlDcUserModelImpl)csdlDcUser, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CsdlDcUserModelImpl csdlDcUserModelImpl) {
		Object[] args = new Object[] {
				csdlDcUserModelImpl.getUuid(), csdlDcUserModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			csdlDcUserModelImpl, false);

		args = new Object[] {
				csdlDcUserModelImpl.getGovAgencyCode(),
				csdlDcUserModelImpl.getEmployeeEmail(),
				csdlDcUserModelImpl.getStatus()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GOV_EMAIL_STATUS, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GOV_EMAIL_STATUS, args,
			csdlDcUserModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CsdlDcUserModelImpl csdlDcUserModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					csdlDcUserModelImpl.getUuid(),
					csdlDcUserModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((csdlDcUserModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					csdlDcUserModelImpl.getOriginalUuid(),
					csdlDcUserModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					csdlDcUserModelImpl.getGovAgencyCode(),
					csdlDcUserModelImpl.getEmployeeEmail(),
					csdlDcUserModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GOV_EMAIL_STATUS,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GOV_EMAIL_STATUS,
				args);
		}

		if ((csdlDcUserModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GOV_EMAIL_STATUS.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					csdlDcUserModelImpl.getOriginalGovAgencyCode(),
					csdlDcUserModelImpl.getOriginalEmployeeEmail(),
					csdlDcUserModelImpl.getOriginalStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GOV_EMAIL_STATUS,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GOV_EMAIL_STATUS,
				args);
		}
	}

	/**
	 * Creates a new csdl dc user with the primary key. Does not add the csdl dc user to the database.
	 *
	 * @param idDcUser the primary key for the new csdl dc user
	 * @return the new csdl dc user
	 */
	@Override
	public CsdlDcUser create(long idDcUser) {
		CsdlDcUser csdlDcUser = new CsdlDcUserImpl();

		csdlDcUser.setNew(true);
		csdlDcUser.setPrimaryKey(idDcUser);

		String uuid = PortalUUIDUtil.generate();

		csdlDcUser.setUuid(uuid);

		csdlDcUser.setCompanyId(companyProvider.getCompanyId());

		return csdlDcUser;
	}

	/**
	 * Removes the csdl dc user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param idDcUser the primary key of the csdl dc user
	 * @return the csdl dc user that was removed
	 * @throws NoSuchCsdlDcUserException if a csdl dc user with the primary key could not be found
	 */
	@Override
	public CsdlDcUser remove(long idDcUser) throws NoSuchCsdlDcUserException {
		return remove((Serializable)idDcUser);
	}

	/**
	 * Removes the csdl dc user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the csdl dc user
	 * @return the csdl dc user that was removed
	 * @throws NoSuchCsdlDcUserException if a csdl dc user with the primary key could not be found
	 */
	@Override
	public CsdlDcUser remove(Serializable primaryKey)
		throws NoSuchCsdlDcUserException {
		Session session = null;

		try {
			session = openSession();

			CsdlDcUser csdlDcUser = (CsdlDcUser)session.get(CsdlDcUserImpl.class,
					primaryKey);

			if (csdlDcUser == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCsdlDcUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(csdlDcUser);
		}
		catch (NoSuchCsdlDcUserException nsee) {
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
	protected CsdlDcUser removeImpl(CsdlDcUser csdlDcUser) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(csdlDcUser)) {
				csdlDcUser = (CsdlDcUser)session.get(CsdlDcUserImpl.class,
						csdlDcUser.getPrimaryKeyObj());
			}

			if (csdlDcUser != null) {
				session.delete(csdlDcUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (csdlDcUser != null) {
			clearCache(csdlDcUser);
		}

		return csdlDcUser;
	}

	@Override
	public CsdlDcUser updateImpl(CsdlDcUser csdlDcUser) {
		boolean isNew = csdlDcUser.isNew();

		if (!(csdlDcUser instanceof CsdlDcUserModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(csdlDcUser.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(csdlDcUser);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in csdlDcUser proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CsdlDcUser implementation " +
				csdlDcUser.getClass());
		}

		CsdlDcUserModelImpl csdlDcUserModelImpl = (CsdlDcUserModelImpl)csdlDcUser;

		if (Validator.isNull(csdlDcUser.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			csdlDcUser.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (csdlDcUser.isNew()) {
				session.save(csdlDcUser);

				csdlDcUser.setNew(false);
			}
			else {
				csdlDcUser = (CsdlDcUser)session.merge(csdlDcUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CsdlDcUserModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { csdlDcUserModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					csdlDcUserModelImpl.getUuid(),
					csdlDcUserModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((csdlDcUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						csdlDcUserModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { csdlDcUserModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((csdlDcUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						csdlDcUserModelImpl.getOriginalUuid(),
						csdlDcUserModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						csdlDcUserModelImpl.getUuid(),
						csdlDcUserModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
			CsdlDcUserImpl.class, csdlDcUser.getPrimaryKey(), csdlDcUser, false);

		clearUniqueFindersCache(csdlDcUserModelImpl, false);
		cacheUniqueFindersCache(csdlDcUserModelImpl);

		csdlDcUser.resetOriginalValues();

		return csdlDcUser;
	}

	/**
	 * Returns the csdl dc user with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the csdl dc user
	 * @return the csdl dc user
	 * @throws NoSuchCsdlDcUserException if a csdl dc user with the primary key could not be found
	 */
	@Override
	public CsdlDcUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCsdlDcUserException {
		CsdlDcUser csdlDcUser = fetchByPrimaryKey(primaryKey);

		if (csdlDcUser == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCsdlDcUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return csdlDcUser;
	}

	/**
	 * Returns the csdl dc user with the primary key or throws a {@link NoSuchCsdlDcUserException} if it could not be found.
	 *
	 * @param idDcUser the primary key of the csdl dc user
	 * @return the csdl dc user
	 * @throws NoSuchCsdlDcUserException if a csdl dc user with the primary key could not be found
	 */
	@Override
	public CsdlDcUser findByPrimaryKey(long idDcUser)
		throws NoSuchCsdlDcUserException {
		return findByPrimaryKey((Serializable)idDcUser);
	}

	/**
	 * Returns the csdl dc user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the csdl dc user
	 * @return the csdl dc user, or <code>null</code> if a csdl dc user with the primary key could not be found
	 */
	@Override
	public CsdlDcUser fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
				CsdlDcUserImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CsdlDcUser csdlDcUser = (CsdlDcUser)serializable;

		if (csdlDcUser == null) {
			Session session = null;

			try {
				session = openSession();

				csdlDcUser = (CsdlDcUser)session.get(CsdlDcUserImpl.class,
						primaryKey);

				if (csdlDcUser != null) {
					cacheResult(csdlDcUser);
				}
				else {
					entityCache.putResult(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
						CsdlDcUserImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
					CsdlDcUserImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return csdlDcUser;
	}

	/**
	 * Returns the csdl dc user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param idDcUser the primary key of the csdl dc user
	 * @return the csdl dc user, or <code>null</code> if a csdl dc user with the primary key could not be found
	 */
	@Override
	public CsdlDcUser fetchByPrimaryKey(long idDcUser) {
		return fetchByPrimaryKey((Serializable)idDcUser);
	}

	@Override
	public Map<Serializable, CsdlDcUser> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CsdlDcUser> map = new HashMap<Serializable, CsdlDcUser>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CsdlDcUser csdlDcUser = fetchByPrimaryKey(primaryKey);

			if (csdlDcUser != null) {
				map.put(primaryKey, csdlDcUser);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
					CsdlDcUserImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CsdlDcUser)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CSDLDCUSER_WHERE_PKS_IN);

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

			for (CsdlDcUser csdlDcUser : (List<CsdlDcUser>)q.list()) {
				map.put(csdlDcUser.getPrimaryKeyObj(), csdlDcUser);

				cacheResult(csdlDcUser);

				uncachedPrimaryKeys.remove(csdlDcUser.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CsdlDcUserModelImpl.ENTITY_CACHE_ENABLED,
					CsdlDcUserImpl.class, primaryKey, nullModel);
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
	 * Returns all the csdl dc users.
	 *
	 * @return the csdl dc users
	 */
	@Override
	public List<CsdlDcUser> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the csdl dc users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csdl dc users
	 * @param end the upper bound of the range of csdl dc users (not inclusive)
	 * @return the range of csdl dc users
	 */
	@Override
	public List<CsdlDcUser> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the csdl dc users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csdl dc users
	 * @param end the upper bound of the range of csdl dc users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of csdl dc users
	 */
	@Override
	public List<CsdlDcUser> findAll(int start, int end,
		OrderByComparator<CsdlDcUser> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the csdl dc users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csdl dc users
	 * @param end the upper bound of the range of csdl dc users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of csdl dc users
	 */
	@Override
	public List<CsdlDcUser> findAll(int start, int end,
		OrderByComparator<CsdlDcUser> orderByComparator,
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

		List<CsdlDcUser> list = null;

		if (retrieveFromCache) {
			list = (List<CsdlDcUser>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CSDLDCUSER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CSDLDCUSER;

				if (pagination) {
					sql = sql.concat(CsdlDcUserModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CsdlDcUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CsdlDcUser>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the csdl dc users from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CsdlDcUser csdlDcUser : findAll()) {
			remove(csdlDcUser);
		}
	}

	/**
	 * Returns the number of csdl dc users.
	 *
	 * @return the number of csdl dc users
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CSDLDCUSER);

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
		return CsdlDcUserModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the csdl dc user persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CsdlDcUserImpl.class.getName());
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
	private static final String _SQL_SELECT_CSDLDCUSER = "SELECT csdlDcUser FROM CsdlDcUser csdlDcUser";
	private static final String _SQL_SELECT_CSDLDCUSER_WHERE_PKS_IN = "SELECT csdlDcUser FROM CsdlDcUser csdlDcUser WHERE idDcUser IN (";
	private static final String _SQL_SELECT_CSDLDCUSER_WHERE = "SELECT csdlDcUser FROM CsdlDcUser csdlDcUser WHERE ";
	private static final String _SQL_COUNT_CSDLDCUSER = "SELECT COUNT(csdlDcUser) FROM CsdlDcUser csdlDcUser";
	private static final String _SQL_COUNT_CSDLDCUSER_WHERE = "SELECT COUNT(csdlDcUser) FROM CsdlDcUser csdlDcUser WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "csdlDcUser.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CsdlDcUser exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CsdlDcUser exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CsdlDcUserPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}