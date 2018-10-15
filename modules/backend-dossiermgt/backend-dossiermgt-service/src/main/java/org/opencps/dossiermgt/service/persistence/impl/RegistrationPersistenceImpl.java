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

import org.opencps.dossiermgt.exception.NoSuchRegistrationException;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.impl.RegistrationImpl;
import org.opencps.dossiermgt.model.impl.RegistrationModelImpl;
import org.opencps.dossiermgt.service.persistence.RegistrationPersistence;

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
 * The persistence implementation for the registration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see RegistrationPersistence
 * @see org.opencps.dossiermgt.service.persistence.RegistrationUtil
 * @generated
 */
@ProviderType
public class RegistrationPersistenceImpl extends BasePersistenceImpl<Registration>
	implements RegistrationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RegistrationUtil} to access the registration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RegistrationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			RegistrationModelImpl.UUID_COLUMN_BITMASK |
			RegistrationModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the registrations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching registrations
	 */
	@Override
	public List<Registration> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registrations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @return the range of matching registrations
	 */
	@Override
	public List<Registration> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registrations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registrations
	 */
	@Override
	public List<Registration> findByUuid(String uuid, int start, int end,
		OrderByComparator<Registration> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registrations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registrations
	 */
	@Override
	public List<Registration> findByUuid(String uuid, int start, int end,
		OrderByComparator<Registration> orderByComparator,
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

		List<Registration> list = null;

		if (retrieveFromCache) {
			list = (List<Registration>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Registration registration : list) {
					if (!Objects.equals(uuid, registration.getUuid())) {
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

			query.append(_SQL_SELECT_REGISTRATION_WHERE);

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
				query.append(RegistrationModelImpl.ORDER_BY_JPQL);
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
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first registration in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByUuid_First(String uuid,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = fetchByUuid_First(uuid, orderByComparator);

		if (registration != null) {
			return registration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the first registration in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByUuid_First(String uuid,
		OrderByComparator<Registration> orderByComparator) {
		List<Registration> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByUuid_Last(String uuid,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = fetchByUuid_Last(uuid, orderByComparator);

		if (registration != null) {
			return registration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the last registration in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByUuid_Last(String uuid,
		OrderByComparator<Registration> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Registration> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registrations before and after the current registration in the ordered set where uuid = &#63;.
	 *
	 * @param registrationId the primary key of the current registration
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration
	 * @throws NoSuchRegistrationException if a registration with the primary key could not be found
	 */
	@Override
	public Registration[] findByUuid_PrevAndNext(long registrationId,
		String uuid, OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			Registration[] array = new RegistrationImpl[3];

			array[0] = getByUuid_PrevAndNext(session, registration, uuid,
					orderByComparator, true);

			array[1] = registration;

			array[2] = getByUuid_PrevAndNext(session, registration, uuid,
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

	protected Registration getByUuid_PrevAndNext(Session session,
		Registration registration, String uuid,
		OrderByComparator<Registration> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REGISTRATION_WHERE);

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
			query.append(RegistrationModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(registration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Registration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registrations where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Registration registration : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(registration);
		}
	}

	/**
	 * Returns the number of registrations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching registrations
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REGISTRATION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "registration.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "registration.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(registration.uuid IS NULL OR registration.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			RegistrationModelImpl.UUID_COLUMN_BITMASK |
			RegistrationModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the registration where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRegistrationException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationException {
		Registration registration = fetchByUUID_G(uuid, groupId);

		if (registration == null) {
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

			throw new NoSuchRegistrationException(msg.toString());
		}

		return registration;
	}

	/**
	 * Returns the registration where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the registration where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Registration) {
			Registration registration = (Registration)result;

			if (!Objects.equals(uuid, registration.getUuid()) ||
					(groupId != registration.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_REGISTRATION_WHERE);

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

				List<Registration> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Registration registration = list.get(0);

					result = registration;

					cacheResult(registration);
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
			return (Registration)result;
		}
	}

	/**
	 * Removes the registration where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the registration that was removed
	 */
	@Override
	public Registration removeByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationException {
		Registration registration = findByUUID_G(uuid, groupId);

		return remove(registration);
	}

	/**
	 * Returns the number of registrations where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching registrations
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "registration.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "registration.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(registration.uuid IS NULL OR registration.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "registration.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			RegistrationModelImpl.UUID_COLUMN_BITMASK |
			RegistrationModelImpl.COMPANYID_COLUMN_BITMASK |
			RegistrationModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the registrations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching registrations
	 */
	@Override
	public List<Registration> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registrations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @return the range of matching registrations
	 */
	@Override
	public List<Registration> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registrations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registrations
	 */
	@Override
	public List<Registration> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Registration> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registrations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registrations
	 */
	@Override
	public List<Registration> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Registration> orderByComparator,
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

		List<Registration> list = null;

		if (retrieveFromCache) {
			list = (List<Registration>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Registration registration : list) {
					if (!Objects.equals(uuid, registration.getUuid()) ||
							(companyId != registration.getCompanyId())) {
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

			query.append(_SQL_SELECT_REGISTRATION_WHERE);

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
				query.append(RegistrationModelImpl.ORDER_BY_JPQL);
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
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first registration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (registration != null) {
			return registration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the first registration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Registration> orderByComparator) {
		List<Registration> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (registration != null) {
			return registration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the last registration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Registration> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Registration> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registrations before and after the current registration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param registrationId the primary key of the current registration
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration
	 * @throws NoSuchRegistrationException if a registration with the primary key could not be found
	 */
	@Override
	public Registration[] findByUuid_C_PrevAndNext(long registrationId,
		String uuid, long companyId,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			Registration[] array = new RegistrationImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, registration, uuid,
					companyId, orderByComparator, true);

			array[1] = registration;

			array[2] = getByUuid_C_PrevAndNext(session, registration, uuid,
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

	protected Registration getByUuid_C_PrevAndNext(Session session,
		Registration registration, String uuid, long companyId,
		OrderByComparator<Registration> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REGISTRATION_WHERE);

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
			query.append(RegistrationModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(registration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Registration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registrations where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Registration registration : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registration);
		}
	}

	/**
	 * Returns the number of registrations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching registrations
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "registration.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "registration.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(registration.uuid IS NULL OR registration.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "registration.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_SUBMITTING =
		new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_submitting",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_SUBMITTING =
		new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_submitting",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			RegistrationModelImpl.GROUPID_COLUMN_BITMASK |
			RegistrationModelImpl.SUBMITTING_COLUMN_BITMASK |
			RegistrationModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_SUBMITTING = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_submitting",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the registrations where groupId = &#63; and submitting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param submitting the submitting
	 * @return the matching registrations
	 */
	@Override
	public List<Registration> findByF_submitting(long groupId,
		boolean submitting) {
		return findByF_submitting(groupId, submitting, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registrations where groupId = &#63; and submitting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param submitting the submitting
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @return the range of matching registrations
	 */
	@Override
	public List<Registration> findByF_submitting(long groupId,
		boolean submitting, int start, int end) {
		return findByF_submitting(groupId, submitting, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registrations where groupId = &#63; and submitting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param submitting the submitting
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registrations
	 */
	@Override
	public List<Registration> findByF_submitting(long groupId,
		boolean submitting, int start, int end,
		OrderByComparator<Registration> orderByComparator) {
		return findByF_submitting(groupId, submitting, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registrations where groupId = &#63; and submitting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param submitting the submitting
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registrations
	 */
	@Override
	public List<Registration> findByF_submitting(long groupId,
		boolean submitting, int start, int end,
		OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_SUBMITTING;
			finderArgs = new Object[] { groupId, submitting };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_SUBMITTING;
			finderArgs = new Object[] {
					groupId, submitting,
					
					start, end, orderByComparator
				};
		}

		List<Registration> list = null;

		if (retrieveFromCache) {
			list = (List<Registration>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Registration registration : list) {
					if ((groupId != registration.getGroupId()) ||
							(submitting != registration.isSubmitting())) {
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

			query.append(_SQL_SELECT_REGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_F_SUBMITTING_GROUPID_2);

			query.append(_FINDER_COLUMN_F_SUBMITTING_SUBMITTING_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RegistrationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(submitting);

				if (!pagination) {
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first registration in the ordered set where groupId = &#63; and submitting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param submitting the submitting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByF_submitting_First(long groupId,
		boolean submitting, OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = fetchByF_submitting_First(groupId,
				submitting, orderByComparator);

		if (registration != null) {
			return registration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", submitting=");
		msg.append(submitting);

		msg.append("}");

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the first registration in the ordered set where groupId = &#63; and submitting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param submitting the submitting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByF_submitting_First(long groupId,
		boolean submitting, OrderByComparator<Registration> orderByComparator) {
		List<Registration> list = findByF_submitting(groupId, submitting, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration in the ordered set where groupId = &#63; and submitting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param submitting the submitting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByF_submitting_Last(long groupId,
		boolean submitting, OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = fetchByF_submitting_Last(groupId,
				submitting, orderByComparator);

		if (registration != null) {
			return registration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", submitting=");
		msg.append(submitting);

		msg.append("}");

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the last registration in the ordered set where groupId = &#63; and submitting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param submitting the submitting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByF_submitting_Last(long groupId,
		boolean submitting, OrderByComparator<Registration> orderByComparator) {
		int count = countByF_submitting(groupId, submitting);

		if (count == 0) {
			return null;
		}

		List<Registration> list = findByF_submitting(groupId, submitting,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registrations before and after the current registration in the ordered set where groupId = &#63; and submitting = &#63;.
	 *
	 * @param registrationId the primary key of the current registration
	 * @param groupId the group ID
	 * @param submitting the submitting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration
	 * @throws NoSuchRegistrationException if a registration with the primary key could not be found
	 */
	@Override
	public Registration[] findByF_submitting_PrevAndNext(long registrationId,
		long groupId, boolean submitting,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			Registration[] array = new RegistrationImpl[3];

			array[0] = getByF_submitting_PrevAndNext(session, registration,
					groupId, submitting, orderByComparator, true);

			array[1] = registration;

			array[2] = getByF_submitting_PrevAndNext(session, registration,
					groupId, submitting, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Registration getByF_submitting_PrevAndNext(Session session,
		Registration registration, long groupId, boolean submitting,
		OrderByComparator<Registration> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_F_SUBMITTING_GROUPID_2);

		query.append(_FINDER_COLUMN_F_SUBMITTING_SUBMITTING_2);

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
			query.append(RegistrationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(submitting);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(registration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Registration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registrations where groupId = &#63; and submitting = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param submitting the submitting
	 */
	@Override
	public void removeByF_submitting(long groupId, boolean submitting) {
		for (Registration registration : findByF_submitting(groupId,
				submitting, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registration);
		}
	}

	/**
	 * Returns the number of registrations where groupId = &#63; and submitting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param submitting the submitting
	 * @return the number of matching registrations
	 */
	@Override
	public int countByF_submitting(long groupId, boolean submitting) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_SUBMITTING;

		Object[] finderArgs = new Object[] { groupId, submitting };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_F_SUBMITTING_GROUPID_2);

			query.append(_FINDER_COLUMN_F_SUBMITTING_SUBMITTING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(submitting);

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

	private static final String _FINDER_COLUMN_F_SUBMITTING_GROUPID_2 = "registration.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_SUBMITTING_SUBMITTING_2 = "registration.submitting = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_UID = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGID_UID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_UID =
		new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGID_UID",
			new String[] { Long.class.getName(), Long.class.getName() },
			RegistrationModelImpl.GROUPID_COLUMN_BITMASK |
			RegistrationModelImpl.USERID_COLUMN_BITMASK |
			RegistrationModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_UID = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGID_UID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the registrations where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching registrations
	 */
	@Override
	public List<Registration> findByGID_UID(long groupId, long userId) {
		return findByGID_UID(groupId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registrations where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @return the range of matching registrations
	 */
	@Override
	public List<Registration> findByGID_UID(long groupId, long userId,
		int start, int end) {
		return findByGID_UID(groupId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registrations where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registrations
	 */
	@Override
	public List<Registration> findByGID_UID(long groupId, long userId,
		int start, int end, OrderByComparator<Registration> orderByComparator) {
		return findByGID_UID(groupId, userId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the registrations where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registrations
	 */
	@Override
	public List<Registration> findByGID_UID(long groupId, long userId,
		int start, int end, OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_UID;
			finderArgs = new Object[] { groupId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_UID;
			finderArgs = new Object[] {
					groupId, userId,
					
					start, end, orderByComparator
				};
		}

		List<Registration> list = null;

		if (retrieveFromCache) {
			list = (List<Registration>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Registration registration : list) {
					if ((groupId != registration.getGroupId()) ||
							(userId != registration.getUserId())) {
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

			query.append(_SQL_SELECT_REGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_GID_UID_GROUPID_2);

			query.append(_FINDER_COLUMN_GID_UID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RegistrationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first registration in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByGID_UID_First(long groupId, long userId,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = fetchByGID_UID_First(groupId, userId,
				orderByComparator);

		if (registration != null) {
			return registration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the first registration in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByGID_UID_First(long groupId, long userId,
		OrderByComparator<Registration> orderByComparator) {
		List<Registration> list = findByGID_UID(groupId, userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByGID_UID_Last(long groupId, long userId,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = fetchByGID_UID_Last(groupId, userId,
				orderByComparator);

		if (registration != null) {
			return registration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the last registration in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByGID_UID_Last(long groupId, long userId,
		OrderByComparator<Registration> orderByComparator) {
		int count = countByGID_UID(groupId, userId);

		if (count == 0) {
			return null;
		}

		List<Registration> list = findByGID_UID(groupId, userId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registrations before and after the current registration in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param registrationId the primary key of the current registration
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration
	 * @throws NoSuchRegistrationException if a registration with the primary key could not be found
	 */
	@Override
	public Registration[] findByGID_UID_PrevAndNext(long registrationId,
		long groupId, long userId,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			Registration[] array = new RegistrationImpl[3];

			array[0] = getByGID_UID_PrevAndNext(session, registration, groupId,
					userId, orderByComparator, true);

			array[1] = registration;

			array[2] = getByGID_UID_PrevAndNext(session, registration, groupId,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Registration getByGID_UID_PrevAndNext(Session session,
		Registration registration, long groupId, long userId,
		OrderByComparator<Registration> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_GID_UID_GROUPID_2);

		query.append(_FINDER_COLUMN_GID_UID_USERID_2);

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
			query.append(RegistrationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(registration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Registration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registrations where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByGID_UID(long groupId, long userId) {
		for (Registration registration : findByGID_UID(groupId, userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registration);
		}
	}

	/**
	 * Returns the number of registrations where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching registrations
	 */
	@Override
	public int countByGID_UID(long groupId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_UID;

		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_GID_UID_GROUPID_2);

			query.append(_FINDER_COLUMN_GID_UID_USERID_2);

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

	private static final String _FINDER_COLUMN_GID_UID_GROUPID_2 = "registration.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GID_UID_USERID_2 = "registration.userId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_REGID = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_REGID",
			new String[] { Long.class.getName(), Long.class.getName() },
			RegistrationModelImpl.GROUPID_COLUMN_BITMASK |
			RegistrationModelImpl.REGISTRATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_REGID = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_REGID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the registration where groupId = &#63; and registrationId = &#63; or throws a {@link NoSuchRegistrationException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @return the matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByG_REGID(long groupId, long registrationId)
		throws NoSuchRegistrationException {
		Registration registration = fetchByG_REGID(groupId, registrationId);

		if (registration == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", registrationId=");
			msg.append(registrationId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchRegistrationException(msg.toString());
		}

		return registration;
	}

	/**
	 * Returns the registration where groupId = &#63; and registrationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @return the matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByG_REGID(long groupId, long registrationId) {
		return fetchByG_REGID(groupId, registrationId, true);
	}

	/**
	 * Returns the registration where groupId = &#63; and registrationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByG_REGID(long groupId, long registrationId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, registrationId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_REGID,
					finderArgs, this);
		}

		if (result instanceof Registration) {
			Registration registration = (Registration)result;

			if ((groupId != registration.getGroupId()) ||
					(registrationId != registration.getRegistrationId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_REGISTRATION_WHERE);

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

				List<Registration> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_REGID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"RegistrationPersistenceImpl.fetchByG_REGID(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Registration registration = list.get(0);

					result = registration;

					cacheResult(registration);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_REGID,
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
			return (Registration)result;
		}
	}

	/**
	 * Removes the registration where groupId = &#63; and registrationId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @return the registration that was removed
	 */
	@Override
	public Registration removeByG_REGID(long groupId, long registrationId)
		throws NoSuchRegistrationException {
		Registration registration = findByG_REGID(groupId, registrationId);

		return remove(registration);
	}

	/**
	 * Returns the number of registrations where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @return the number of matching registrations
	 */
	@Override
	public int countByG_REGID(long groupId, long registrationId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_REGID;

		Object[] finderArgs = new Object[] { groupId, registrationId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATION_WHERE);

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

	private static final String _FINDER_COLUMN_G_REGID_GROUPID_2 = "registration.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_REGID_REGISTRATIONID_2 = "registration.registrationId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_USERID_SUBMITTING =
		new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_USERID_SUBMITTING",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_USERID_SUBMITTING =
		new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_USERID_SUBMITTING",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			RegistrationModelImpl.GROUPID_COLUMN_BITMASK |
			RegistrationModelImpl.USERID_COLUMN_BITMASK |
			RegistrationModelImpl.SUBMITTING_COLUMN_BITMASK |
			RegistrationModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_USERID_SUBMITTING = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_USERID_SUBMITTING",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the registrations where groupId = &#63; and userId = &#63; and submitting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param submitting the submitting
	 * @return the matching registrations
	 */
	@Override
	public List<Registration> findByF_USERID_SUBMITTING(long groupId,
		long userId, boolean submitting) {
		return findByF_USERID_SUBMITTING(groupId, userId, submitting,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registrations where groupId = &#63; and userId = &#63; and submitting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param submitting the submitting
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @return the range of matching registrations
	 */
	@Override
	public List<Registration> findByF_USERID_SUBMITTING(long groupId,
		long userId, boolean submitting, int start, int end) {
		return findByF_USERID_SUBMITTING(groupId, userId, submitting, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the registrations where groupId = &#63; and userId = &#63; and submitting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param submitting the submitting
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registrations
	 */
	@Override
	public List<Registration> findByF_USERID_SUBMITTING(long groupId,
		long userId, boolean submitting, int start, int end,
		OrderByComparator<Registration> orderByComparator) {
		return findByF_USERID_SUBMITTING(groupId, userId, submitting, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registrations where groupId = &#63; and userId = &#63; and submitting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param submitting the submitting
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registrations
	 */
	@Override
	public List<Registration> findByF_USERID_SUBMITTING(long groupId,
		long userId, boolean submitting, int start, int end,
		OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_USERID_SUBMITTING;
			finderArgs = new Object[] { groupId, userId, submitting };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_USERID_SUBMITTING;
			finderArgs = new Object[] {
					groupId, userId, submitting,
					
					start, end, orderByComparator
				};
		}

		List<Registration> list = null;

		if (retrieveFromCache) {
			list = (List<Registration>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Registration registration : list) {
					if ((groupId != registration.getGroupId()) ||
							(userId != registration.getUserId()) ||
							(submitting != registration.isSubmitting())) {
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

			query.append(_SQL_SELECT_REGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_F_USERID_SUBMITTING_GROUPID_2);

			query.append(_FINDER_COLUMN_F_USERID_SUBMITTING_USERID_2);

			query.append(_FINDER_COLUMN_F_USERID_SUBMITTING_SUBMITTING_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RegistrationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(submitting);

				if (!pagination) {
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first registration in the ordered set where groupId = &#63; and userId = &#63; and submitting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param submitting the submitting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByF_USERID_SUBMITTING_First(long groupId,
		long userId, boolean submitting,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = fetchByF_USERID_SUBMITTING_First(groupId,
				userId, submitting, orderByComparator);

		if (registration != null) {
			return registration;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", submitting=");
		msg.append(submitting);

		msg.append("}");

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the first registration in the ordered set where groupId = &#63; and userId = &#63; and submitting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param submitting the submitting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByF_USERID_SUBMITTING_First(long groupId,
		long userId, boolean submitting,
		OrderByComparator<Registration> orderByComparator) {
		List<Registration> list = findByF_USERID_SUBMITTING(groupId, userId,
				submitting, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration in the ordered set where groupId = &#63; and userId = &#63; and submitting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param submitting the submitting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByF_USERID_SUBMITTING_Last(long groupId,
		long userId, boolean submitting,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = fetchByF_USERID_SUBMITTING_Last(groupId,
				userId, submitting, orderByComparator);

		if (registration != null) {
			return registration;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", submitting=");
		msg.append(submitting);

		msg.append("}");

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the last registration in the ordered set where groupId = &#63; and userId = &#63; and submitting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param submitting the submitting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByF_USERID_SUBMITTING_Last(long groupId,
		long userId, boolean submitting,
		OrderByComparator<Registration> orderByComparator) {
		int count = countByF_USERID_SUBMITTING(groupId, userId, submitting);

		if (count == 0) {
			return null;
		}

		List<Registration> list = findByF_USERID_SUBMITTING(groupId, userId,
				submitting, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registrations before and after the current registration in the ordered set where groupId = &#63; and userId = &#63; and submitting = &#63;.
	 *
	 * @param registrationId the primary key of the current registration
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param submitting the submitting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration
	 * @throws NoSuchRegistrationException if a registration with the primary key could not be found
	 */
	@Override
	public Registration[] findByF_USERID_SUBMITTING_PrevAndNext(
		long registrationId, long groupId, long userId, boolean submitting,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			Registration[] array = new RegistrationImpl[3];

			array[0] = getByF_USERID_SUBMITTING_PrevAndNext(session,
					registration, groupId, userId, submitting,
					orderByComparator, true);

			array[1] = registration;

			array[2] = getByF_USERID_SUBMITTING_PrevAndNext(session,
					registration, groupId, userId, submitting,
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

	protected Registration getByF_USERID_SUBMITTING_PrevAndNext(
		Session session, Registration registration, long groupId, long userId,
		boolean submitting, OrderByComparator<Registration> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_REGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_F_USERID_SUBMITTING_GROUPID_2);

		query.append(_FINDER_COLUMN_F_USERID_SUBMITTING_USERID_2);

		query.append(_FINDER_COLUMN_F_USERID_SUBMITTING_SUBMITTING_2);

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
			query.append(RegistrationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(submitting);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(registration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Registration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registrations where groupId = &#63; and userId = &#63; and submitting = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param submitting the submitting
	 */
	@Override
	public void removeByF_USERID_SUBMITTING(long groupId, long userId,
		boolean submitting) {
		for (Registration registration : findByF_USERID_SUBMITTING(groupId,
				userId, submitting, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registration);
		}
	}

	/**
	 * Returns the number of registrations where groupId = &#63; and userId = &#63; and submitting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param submitting the submitting
	 * @return the number of matching registrations
	 */
	@Override
	public int countByF_USERID_SUBMITTING(long groupId, long userId,
		boolean submitting) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_USERID_SUBMITTING;

		Object[] finderArgs = new Object[] { groupId, userId, submitting };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_REGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_F_USERID_SUBMITTING_GROUPID_2);

			query.append(_FINDER_COLUMN_F_USERID_SUBMITTING_USERID_2);

			query.append(_FINDER_COLUMN_F_USERID_SUBMITTING_SUBMITTING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(submitting);

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

	private static final String _FINDER_COLUMN_F_USERID_SUBMITTING_GROUPID_2 = "registration.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_USERID_SUBMITTING_USERID_2 = "registration.userId = ? AND ";
	private static final String _FINDER_COLUMN_F_USERID_SUBMITTING_SUBMITTING_2 = "registration.submitting = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_APPNO_GOVCODE =
		new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_APPNO_GOVCODE",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_APPNO_GOVCODE =
		new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_APPNO_GOVCODE",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Integer.class.getName()
			},
			RegistrationModelImpl.GROUPID_COLUMN_BITMASK |
			RegistrationModelImpl.APPLICANTIDNO_COLUMN_BITMASK |
			RegistrationModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			RegistrationModelImpl.REGISTRATIONSTATE_COLUMN_BITMASK |
			RegistrationModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_APPNO_GOVCODE = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_APPNO_GOVCODE",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param govAgencyCode the gov agency code
	 * @param registrationState the registration state
	 * @return the matching registrations
	 */
	@Override
	public List<Registration> findByG_APPNO_GOVCODE(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState) {
		return findByG_APPNO_GOVCODE(groupId, applicantIdNo, govAgencyCode,
			registrationState, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param govAgencyCode the gov agency code
	 * @param registrationState the registration state
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @return the range of matching registrations
	 */
	@Override
	public List<Registration> findByG_APPNO_GOVCODE(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		int start, int end) {
		return findByG_APPNO_GOVCODE(groupId, applicantIdNo, govAgencyCode,
			registrationState, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param govAgencyCode the gov agency code
	 * @param registrationState the registration state
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registrations
	 */
	@Override
	public List<Registration> findByG_APPNO_GOVCODE(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		int start, int end, OrderByComparator<Registration> orderByComparator) {
		return findByG_APPNO_GOVCODE(groupId, applicantIdNo, govAgencyCode,
			registrationState, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param govAgencyCode the gov agency code
	 * @param registrationState the registration state
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registrations
	 */
	@Override
	public List<Registration> findByG_APPNO_GOVCODE(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		int start, int end, OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_APPNO_GOVCODE;
			finderArgs = new Object[] {
					groupId, applicantIdNo, govAgencyCode, registrationState
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_APPNO_GOVCODE;
			finderArgs = new Object[] {
					groupId, applicantIdNo, govAgencyCode, registrationState,
					
					start, end, orderByComparator
				};
		}

		List<Registration> list = null;

		if (retrieveFromCache) {
			list = (List<Registration>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Registration registration : list) {
					if ((groupId != registration.getGroupId()) ||
							!Objects.equals(applicantIdNo,
								registration.getApplicantIdNo()) ||
							!Objects.equals(govAgencyCode,
								registration.getGovAgencyCode()) ||
							(registrationState != registration.getRegistrationState())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_REGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_GROUPID_2);

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_APPLICANTIDNO_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_GOVAGENCYCODE_2);
			}

			query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_REGISTRATIONSTATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RegistrationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				qPos.add(registrationState);

				if (!pagination) {
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first registration in the ordered set where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param govAgencyCode the gov agency code
	 * @param registrationState the registration state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByG_APPNO_GOVCODE_First(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = fetchByG_APPNO_GOVCODE_First(groupId,
				applicantIdNo, govAgencyCode, registrationState,
				orderByComparator);

		if (registration != null) {
			return registration;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", applicantIdNo=");
		msg.append(applicantIdNo);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", registrationState=");
		msg.append(registrationState);

		msg.append("}");

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the first registration in the ordered set where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param govAgencyCode the gov agency code
	 * @param registrationState the registration state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByG_APPNO_GOVCODE_First(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		OrderByComparator<Registration> orderByComparator) {
		List<Registration> list = findByG_APPNO_GOVCODE(groupId, applicantIdNo,
				govAgencyCode, registrationState, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration in the ordered set where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param govAgencyCode the gov agency code
	 * @param registrationState the registration state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByG_APPNO_GOVCODE_Last(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = fetchByG_APPNO_GOVCODE_Last(groupId,
				applicantIdNo, govAgencyCode, registrationState,
				orderByComparator);

		if (registration != null) {
			return registration;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", applicantIdNo=");
		msg.append(applicantIdNo);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", registrationState=");
		msg.append(registrationState);

		msg.append("}");

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the last registration in the ordered set where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param govAgencyCode the gov agency code
	 * @param registrationState the registration state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByG_APPNO_GOVCODE_Last(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		OrderByComparator<Registration> orderByComparator) {
		int count = countByG_APPNO_GOVCODE(groupId, applicantIdNo,
				govAgencyCode, registrationState);

		if (count == 0) {
			return null;
		}

		List<Registration> list = findByG_APPNO_GOVCODE(groupId, applicantIdNo,
				govAgencyCode, registrationState, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registrations before and after the current registration in the ordered set where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	 *
	 * @param registrationId the primary key of the current registration
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param govAgencyCode the gov agency code
	 * @param registrationState the registration state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration
	 * @throws NoSuchRegistrationException if a registration with the primary key could not be found
	 */
	@Override
	public Registration[] findByG_APPNO_GOVCODE_PrevAndNext(
		long registrationId, long groupId, String applicantIdNo,
		String govAgencyCode, int registrationState,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			Registration[] array = new RegistrationImpl[3];

			array[0] = getByG_APPNO_GOVCODE_PrevAndNext(session, registration,
					groupId, applicantIdNo, govAgencyCode, registrationState,
					orderByComparator, true);

			array[1] = registration;

			array[2] = getByG_APPNO_GOVCODE_PrevAndNext(session, registration,
					groupId, applicantIdNo, govAgencyCode, registrationState,
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

	protected Registration getByG_APPNO_GOVCODE_PrevAndNext(Session session,
		Registration registration, long groupId, String applicantIdNo,
		String govAgencyCode, int registrationState,
		OrderByComparator<Registration> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_REGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_GROUPID_2);

		boolean bindApplicantIdNo = false;

		if (applicantIdNo == null) {
			query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_APPLICANTIDNO_1);
		}
		else if (applicantIdNo.equals("")) {
			query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_APPLICANTIDNO_3);
		}
		else {
			bindApplicantIdNo = true;

			query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_APPLICANTIDNO_2);
		}

		boolean bindGovAgencyCode = false;

		if (govAgencyCode == null) {
			query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_GOVAGENCYCODE_1);
		}
		else if (govAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_GOVAGENCYCODE_3);
		}
		else {
			bindGovAgencyCode = true;

			query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_GOVAGENCYCODE_2);
		}

		query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_REGISTRATIONSTATE_2);

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
			query.append(RegistrationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindApplicantIdNo) {
			qPos.add(applicantIdNo);
		}

		if (bindGovAgencyCode) {
			qPos.add(govAgencyCode);
		}

		qPos.add(registrationState);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(registration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Registration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param govAgencyCode the gov agency code
	 * @param registrationState the registration state
	 */
	@Override
	public void removeByG_APPNO_GOVCODE(long groupId, String applicantIdNo,
		String govAgencyCode, int registrationState) {
		for (Registration registration : findByG_APPNO_GOVCODE(groupId,
				applicantIdNo, govAgencyCode, registrationState,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registration);
		}
	}

	/**
	 * Returns the number of registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param applicantIdNo the applicant ID no
	 * @param govAgencyCode the gov agency code
	 * @param registrationState the registration state
	 * @return the number of matching registrations
	 */
	@Override
	public int countByG_APPNO_GOVCODE(long groupId, String applicantIdNo,
		String govAgencyCode, int registrationState) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_APPNO_GOVCODE;

		Object[] finderArgs = new Object[] {
				groupId, applicantIdNo, govAgencyCode, registrationState
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_REGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_GROUPID_2);

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_APPLICANTIDNO_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_GOVAGENCYCODE_2);
			}

			query.append(_FINDER_COLUMN_G_APPNO_GOVCODE_REGISTRATIONSTATE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				qPos.add(registrationState);

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

	private static final String _FINDER_COLUMN_G_APPNO_GOVCODE_GROUPID_2 = "registration.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_APPNO_GOVCODE_APPLICANTIDNO_1 = "registration.applicantIdNo IS NULL AND ";
	private static final String _FINDER_COLUMN_G_APPNO_GOVCODE_APPLICANTIDNO_2 = "registration.applicantIdNo = ? AND ";
	private static final String _FINDER_COLUMN_G_APPNO_GOVCODE_APPLICANTIDNO_3 = "(registration.applicantIdNo IS NULL OR registration.applicantIdNo = '') AND ";
	private static final String _FINDER_COLUMN_G_APPNO_GOVCODE_GOVAGENCYCODE_1 = "registration.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_APPNO_GOVCODE_GOVAGENCYCODE_2 = "registration.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_APPNO_GOVCODE_GOVAGENCYCODE_3 = "(registration.govAgencyCode IS NULL OR registration.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_APPNO_GOVCODE_REGISTRATIONSTATE_2 =
		"registration.registrationState = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_USER_STATE =
		new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_USER_STATE",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_USER_STATE =
		new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_USER_STATE",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			RegistrationModelImpl.GROUPID_COLUMN_BITMASK |
			RegistrationModelImpl.USERID_COLUMN_BITMASK |
			RegistrationModelImpl.REGISTRATIONSTATE_COLUMN_BITMASK |
			RegistrationModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_USER_STATE = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_USER_STATE",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the registrations where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param registrationState the registration state
	 * @return the matching registrations
	 */
	@Override
	public List<Registration> findByG_USER_STATE(long groupId, long userId,
		int registrationState) {
		return findByG_USER_STATE(groupId, userId, registrationState,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registrations where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param registrationState the registration state
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @return the range of matching registrations
	 */
	@Override
	public List<Registration> findByG_USER_STATE(long groupId, long userId,
		int registrationState, int start, int end) {
		return findByG_USER_STATE(groupId, userId, registrationState, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the registrations where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param registrationState the registration state
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registrations
	 */
	@Override
	public List<Registration> findByG_USER_STATE(long groupId, long userId,
		int registrationState, int start, int end,
		OrderByComparator<Registration> orderByComparator) {
		return findByG_USER_STATE(groupId, userId, registrationState, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registrations where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param registrationState the registration state
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registrations
	 */
	@Override
	public List<Registration> findByG_USER_STATE(long groupId, long userId,
		int registrationState, int start, int end,
		OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_USER_STATE;
			finderArgs = new Object[] { groupId, userId, registrationState };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_USER_STATE;
			finderArgs = new Object[] {
					groupId, userId, registrationState,
					
					start, end, orderByComparator
				};
		}

		List<Registration> list = null;

		if (retrieveFromCache) {
			list = (List<Registration>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Registration registration : list) {
					if ((groupId != registration.getGroupId()) ||
							(userId != registration.getUserId()) ||
							(registrationState != registration.getRegistrationState())) {
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

			query.append(_SQL_SELECT_REGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_G_USER_STATE_GROUPID_2);

			query.append(_FINDER_COLUMN_G_USER_STATE_USERID_2);

			query.append(_FINDER_COLUMN_G_USER_STATE_REGISTRATIONSTATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RegistrationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(registrationState);

				if (!pagination) {
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first registration in the ordered set where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param registrationState the registration state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByG_USER_STATE_First(long groupId, long userId,
		int registrationState, OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = fetchByG_USER_STATE_First(groupId, userId,
				registrationState, orderByComparator);

		if (registration != null) {
			return registration;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", registrationState=");
		msg.append(registrationState);

		msg.append("}");

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the first registration in the ordered set where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param registrationState the registration state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByG_USER_STATE_First(long groupId, long userId,
		int registrationState, OrderByComparator<Registration> orderByComparator) {
		List<Registration> list = findByG_USER_STATE(groupId, userId,
				registrationState, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration in the ordered set where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param registrationState the registration state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByG_USER_STATE_Last(long groupId, long userId,
		int registrationState, OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = fetchByG_USER_STATE_Last(groupId, userId,
				registrationState, orderByComparator);

		if (registration != null) {
			return registration;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", registrationState=");
		msg.append(registrationState);

		msg.append("}");

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the last registration in the ordered set where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param registrationState the registration state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByG_USER_STATE_Last(long groupId, long userId,
		int registrationState, OrderByComparator<Registration> orderByComparator) {
		int count = countByG_USER_STATE(groupId, userId, registrationState);

		if (count == 0) {
			return null;
		}

		List<Registration> list = findByG_USER_STATE(groupId, userId,
				registrationState, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registrations before and after the current registration in the ordered set where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	 *
	 * @param registrationId the primary key of the current registration
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param registrationState the registration state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration
	 * @throws NoSuchRegistrationException if a registration with the primary key could not be found
	 */
	@Override
	public Registration[] findByG_USER_STATE_PrevAndNext(long registrationId,
		long groupId, long userId, int registrationState,
		OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException {
		Registration registration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			Registration[] array = new RegistrationImpl[3];

			array[0] = getByG_USER_STATE_PrevAndNext(session, registration,
					groupId, userId, registrationState, orderByComparator, true);

			array[1] = registration;

			array[2] = getByG_USER_STATE_PrevAndNext(session, registration,
					groupId, userId, registrationState, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Registration getByG_USER_STATE_PrevAndNext(Session session,
		Registration registration, long groupId, long userId,
		int registrationState,
		OrderByComparator<Registration> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_REGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_G_USER_STATE_GROUPID_2);

		query.append(_FINDER_COLUMN_G_USER_STATE_USERID_2);

		query.append(_FINDER_COLUMN_G_USER_STATE_REGISTRATIONSTATE_2);

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
			query.append(RegistrationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(registrationState);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(registration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Registration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registrations where groupId = &#63; and userId = &#63; and registrationState = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param registrationState the registration state
	 */
	@Override
	public void removeByG_USER_STATE(long groupId, long userId,
		int registrationState) {
		for (Registration registration : findByG_USER_STATE(groupId, userId,
				registrationState, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registration);
		}
	}

	/**
	 * Returns the number of registrations where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param registrationState the registration state
	 * @return the number of matching registrations
	 */
	@Override
	public int countByG_USER_STATE(long groupId, long userId,
		int registrationState) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_USER_STATE;

		Object[] finderArgs = new Object[] { groupId, userId, registrationState };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_REGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_G_USER_STATE_GROUPID_2);

			query.append(_FINDER_COLUMN_G_USER_STATE_USERID_2);

			query.append(_FINDER_COLUMN_G_USER_STATE_REGISTRATIONSTATE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(registrationState);

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

	private static final String _FINDER_COLUMN_G_USER_STATE_GROUPID_2 = "registration.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_USER_STATE_USERID_2 = "registration.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_USER_STATE_REGISTRATIONSTATE_2 = "registration.registrationState = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_REG_APPNO = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, RegistrationImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByREG_APPNO",
			new String[] { String.class.getName() },
			RegistrationModelImpl.APPLICANTIDNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REG_APPNO = new FinderPath(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByREG_APPNO",
			new String[] { String.class.getName() });

	/**
	 * Returns the registration where applicantIdNo = &#63; or throws a {@link NoSuchRegistrationException} if it could not be found.
	 *
	 * @param applicantIdNo the applicant ID no
	 * @return the matching registration
	 * @throws NoSuchRegistrationException if a matching registration could not be found
	 */
	@Override
	public Registration findByREG_APPNO(String applicantIdNo)
		throws NoSuchRegistrationException {
		Registration registration = fetchByREG_APPNO(applicantIdNo);

		if (registration == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("applicantIdNo=");
			msg.append(applicantIdNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchRegistrationException(msg.toString());
		}

		return registration;
	}

	/**
	 * Returns the registration where applicantIdNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param applicantIdNo the applicant ID no
	 * @return the matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByREG_APPNO(String applicantIdNo) {
		return fetchByREG_APPNO(applicantIdNo, true);
	}

	/**
	 * Returns the registration where applicantIdNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param applicantIdNo the applicant ID no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching registration, or <code>null</code> if a matching registration could not be found
	 */
	@Override
	public Registration fetchByREG_APPNO(String applicantIdNo,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { applicantIdNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_REG_APPNO,
					finderArgs, this);
		}

		if (result instanceof Registration) {
			Registration registration = (Registration)result;

			if (!Objects.equals(applicantIdNo, registration.getApplicantIdNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_REGISTRATION_WHERE);

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_REG_APPNO_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_REG_APPNO_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_REG_APPNO_APPLICANTIDNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
				}

				List<Registration> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_REG_APPNO,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"RegistrationPersistenceImpl.fetchByREG_APPNO(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Registration registration = list.get(0);

					result = registration;

					cacheResult(registration);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_REG_APPNO,
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
			return (Registration)result;
		}
	}

	/**
	 * Removes the registration where applicantIdNo = &#63; from the database.
	 *
	 * @param applicantIdNo the applicant ID no
	 * @return the registration that was removed
	 */
	@Override
	public Registration removeByREG_APPNO(String applicantIdNo)
		throws NoSuchRegistrationException {
		Registration registration = findByREG_APPNO(applicantIdNo);

		return remove(registration);
	}

	/**
	 * Returns the number of registrations where applicantIdNo = &#63;.
	 *
	 * @param applicantIdNo the applicant ID no
	 * @return the number of matching registrations
	 */
	@Override
	public int countByREG_APPNO(String applicantIdNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_REG_APPNO;

		Object[] finderArgs = new Object[] { applicantIdNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REGISTRATION_WHERE);

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_REG_APPNO_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_REG_APPNO_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_REG_APPNO_APPLICANTIDNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
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

	private static final String _FINDER_COLUMN_REG_APPNO_APPLICANTIDNO_1 = "registration.applicantIdNo IS NULL";
	private static final String _FINDER_COLUMN_REG_APPNO_APPLICANTIDNO_2 = "registration.applicantIdNo = ?";
	private static final String _FINDER_COLUMN_REG_APPNO_APPLICANTIDNO_3 = "(registration.applicantIdNo IS NULL OR registration.applicantIdNo = '')";

	public RegistrationPersistenceImpl() {
		setModelClass(Registration.class);

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
	 * Caches the registration in the entity cache if it is enabled.
	 *
	 * @param registration the registration
	 */
	@Override
	public void cacheResult(Registration registration) {
		entityCache.putResult(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationImpl.class, registration.getPrimaryKey(), registration);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { registration.getUuid(), registration.getGroupId() },
			registration);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_REGID,
			new Object[] {
				registration.getGroupId(), registration.getRegistrationId()
			}, registration);

		finderCache.putResult(FINDER_PATH_FETCH_BY_REG_APPNO,
			new Object[] { registration.getApplicantIdNo() }, registration);

		registration.resetOriginalValues();
	}

	/**
	 * Caches the registrations in the entity cache if it is enabled.
	 *
	 * @param registrations the registrations
	 */
	@Override
	public void cacheResult(List<Registration> registrations) {
		for (Registration registration : registrations) {
			if (entityCache.getResult(
						RegistrationModelImpl.ENTITY_CACHE_ENABLED,
						RegistrationImpl.class, registration.getPrimaryKey()) == null) {
				cacheResult(registration);
			}
			else {
				registration.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all registrations.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RegistrationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the registration.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Registration registration) {
		entityCache.removeResult(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationImpl.class, registration.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((RegistrationModelImpl)registration, true);
	}

	@Override
	public void clearCache(List<Registration> registrations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Registration registration : registrations) {
			entityCache.removeResult(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
				RegistrationImpl.class, registration.getPrimaryKey());

			clearUniqueFindersCache((RegistrationModelImpl)registration, true);
		}
	}

	protected void cacheUniqueFindersCache(
		RegistrationModelImpl registrationModelImpl) {
		Object[] args = new Object[] {
				registrationModelImpl.getUuid(),
				registrationModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			registrationModelImpl, false);

		args = new Object[] {
				registrationModelImpl.getGroupId(),
				registrationModelImpl.getRegistrationId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_REGID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_REGID, args,
			registrationModelImpl, false);

		args = new Object[] { registrationModelImpl.getApplicantIdNo() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_REG_APPNO, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_REG_APPNO, args,
			registrationModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		RegistrationModelImpl registrationModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					registrationModelImpl.getUuid(),
					registrationModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((registrationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					registrationModelImpl.getOriginalUuid(),
					registrationModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					registrationModelImpl.getGroupId(),
					registrationModelImpl.getRegistrationId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_REGID, args);
		}

		if ((registrationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_REGID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					registrationModelImpl.getOriginalGroupId(),
					registrationModelImpl.getOriginalRegistrationId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_REGID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					registrationModelImpl.getApplicantIdNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_REG_APPNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_REG_APPNO, args);
		}

		if ((registrationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_REG_APPNO.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					registrationModelImpl.getOriginalApplicantIdNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_REG_APPNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_REG_APPNO, args);
		}
	}

	/**
	 * Creates a new registration with the primary key. Does not add the registration to the database.
	 *
	 * @param registrationId the primary key for the new registration
	 * @return the new registration
	 */
	@Override
	public Registration create(long registrationId) {
		Registration registration = new RegistrationImpl();

		registration.setNew(true);
		registration.setPrimaryKey(registrationId);

		String uuid = PortalUUIDUtil.generate();

		registration.setUuid(uuid);

		registration.setCompanyId(companyProvider.getCompanyId());

		return registration;
	}

	/**
	 * Removes the registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param registrationId the primary key of the registration
	 * @return the registration that was removed
	 * @throws NoSuchRegistrationException if a registration with the primary key could not be found
	 */
	@Override
	public Registration remove(long registrationId)
		throws NoSuchRegistrationException {
		return remove((Serializable)registrationId);
	}

	/**
	 * Removes the registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the registration
	 * @return the registration that was removed
	 * @throws NoSuchRegistrationException if a registration with the primary key could not be found
	 */
	@Override
	public Registration remove(Serializable primaryKey)
		throws NoSuchRegistrationException {
		Session session = null;

		try {
			session = openSession();

			Registration registration = (Registration)session.get(RegistrationImpl.class,
					primaryKey);

			if (registration == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRegistrationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(registration);
		}
		catch (NoSuchRegistrationException nsee) {
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
	protected Registration removeImpl(Registration registration) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(registration)) {
				registration = (Registration)session.get(RegistrationImpl.class,
						registration.getPrimaryKeyObj());
			}

			if (registration != null) {
				session.delete(registration);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (registration != null) {
			clearCache(registration);
		}

		return registration;
	}

	@Override
	public Registration updateImpl(Registration registration) {
		boolean isNew = registration.isNew();

		if (!(registration instanceof RegistrationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(registration.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(registration);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in registration proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Registration implementation " +
				registration.getClass());
		}

		RegistrationModelImpl registrationModelImpl = (RegistrationModelImpl)registration;

		if (Validator.isNull(registration.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			registration.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (registration.getCreateDate() == null)) {
			if (serviceContext == null) {
				registration.setCreateDate(now);
			}
			else {
				registration.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!registrationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				registration.setModifiedDate(now);
			}
			else {
				registration.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (registration.isNew()) {
				session.save(registration);

				registration.setNew(false);
			}
			else {
				registration = (Registration)session.merge(registration);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!RegistrationModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { registrationModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					registrationModelImpl.getUuid(),
					registrationModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					registrationModelImpl.getGroupId(),
					registrationModelImpl.isSubmitting()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_SUBMITTING, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_SUBMITTING,
				args);

			args = new Object[] {
					registrationModelImpl.getGroupId(),
					registrationModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_UID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_UID,
				args);

			args = new Object[] {
					registrationModelImpl.getGroupId(),
					registrationModelImpl.getUserId(),
					registrationModelImpl.isSubmitting()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_USERID_SUBMITTING,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_USERID_SUBMITTING,
				args);

			args = new Object[] {
					registrationModelImpl.getGroupId(),
					registrationModelImpl.getApplicantIdNo(),
					registrationModelImpl.getGovAgencyCode(),
					registrationModelImpl.getRegistrationState()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_APPNO_GOVCODE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_APPNO_GOVCODE,
				args);

			args = new Object[] {
					registrationModelImpl.getGroupId(),
					registrationModelImpl.getUserId(),
					registrationModelImpl.getRegistrationState()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_USER_STATE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_USER_STATE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((registrationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { registrationModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((registrationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationModelImpl.getOriginalUuid(),
						registrationModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						registrationModelImpl.getUuid(),
						registrationModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((registrationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_SUBMITTING.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationModelImpl.getOriginalGroupId(),
						registrationModelImpl.getOriginalSubmitting()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_SUBMITTING, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_SUBMITTING,
					args);

				args = new Object[] {
						registrationModelImpl.getGroupId(),
						registrationModelImpl.isSubmitting()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_SUBMITTING, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_SUBMITTING,
					args);
			}

			if ((registrationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_UID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationModelImpl.getOriginalGroupId(),
						registrationModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_UID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_UID,
					args);

				args = new Object[] {
						registrationModelImpl.getGroupId(),
						registrationModelImpl.getUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_UID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_UID,
					args);
			}

			if ((registrationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_USERID_SUBMITTING.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationModelImpl.getOriginalGroupId(),
						registrationModelImpl.getOriginalUserId(),
						registrationModelImpl.getOriginalSubmitting()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_USERID_SUBMITTING,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_USERID_SUBMITTING,
					args);

				args = new Object[] {
						registrationModelImpl.getGroupId(),
						registrationModelImpl.getUserId(),
						registrationModelImpl.isSubmitting()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_USERID_SUBMITTING,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_USERID_SUBMITTING,
					args);
			}

			if ((registrationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_APPNO_GOVCODE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationModelImpl.getOriginalGroupId(),
						registrationModelImpl.getOriginalApplicantIdNo(),
						registrationModelImpl.getOriginalGovAgencyCode(),
						registrationModelImpl.getOriginalRegistrationState()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_APPNO_GOVCODE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_APPNO_GOVCODE,
					args);

				args = new Object[] {
						registrationModelImpl.getGroupId(),
						registrationModelImpl.getApplicantIdNo(),
						registrationModelImpl.getGovAgencyCode(),
						registrationModelImpl.getRegistrationState()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_APPNO_GOVCODE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_APPNO_GOVCODE,
					args);
			}

			if ((registrationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_USER_STATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationModelImpl.getOriginalGroupId(),
						registrationModelImpl.getOriginalUserId(),
						registrationModelImpl.getOriginalRegistrationState()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_USER_STATE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_USER_STATE,
					args);

				args = new Object[] {
						registrationModelImpl.getGroupId(),
						registrationModelImpl.getUserId(),
						registrationModelImpl.getRegistrationState()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_USER_STATE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_USER_STATE,
					args);
			}
		}

		entityCache.putResult(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationImpl.class, registration.getPrimaryKey(), registration,
			false);

		clearUniqueFindersCache(registrationModelImpl, false);
		cacheUniqueFindersCache(registrationModelImpl);

		registration.resetOriginalValues();

		return registration;
	}

	/**
	 * Returns the registration with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the registration
	 * @return the registration
	 * @throws NoSuchRegistrationException if a registration with the primary key could not be found
	 */
	@Override
	public Registration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRegistrationException {
		Registration registration = fetchByPrimaryKey(primaryKey);

		if (registration == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRegistrationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return registration;
	}

	/**
	 * Returns the registration with the primary key or throws a {@link NoSuchRegistrationException} if it could not be found.
	 *
	 * @param registrationId the primary key of the registration
	 * @return the registration
	 * @throws NoSuchRegistrationException if a registration with the primary key could not be found
	 */
	@Override
	public Registration findByPrimaryKey(long registrationId)
		throws NoSuchRegistrationException {
		return findByPrimaryKey((Serializable)registrationId);
	}

	/**
	 * Returns the registration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the registration
	 * @return the registration, or <code>null</code> if a registration with the primary key could not be found
	 */
	@Override
	public Registration fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
				RegistrationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Registration registration = (Registration)serializable;

		if (registration == null) {
			Session session = null;

			try {
				session = openSession();

				registration = (Registration)session.get(RegistrationImpl.class,
						primaryKey);

				if (registration != null) {
					cacheResult(registration);
				}
				else {
					entityCache.putResult(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
						RegistrationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
					RegistrationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return registration;
	}

	/**
	 * Returns the registration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param registrationId the primary key of the registration
	 * @return the registration, or <code>null</code> if a registration with the primary key could not be found
	 */
	@Override
	public Registration fetchByPrimaryKey(long registrationId) {
		return fetchByPrimaryKey((Serializable)registrationId);
	}

	@Override
	public Map<Serializable, Registration> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Registration> map = new HashMap<Serializable, Registration>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Registration registration = fetchByPrimaryKey(primaryKey);

			if (registration != null) {
				map.put(primaryKey, registration);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
					RegistrationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Registration)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_REGISTRATION_WHERE_PKS_IN);

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

			for (Registration registration : (List<Registration>)q.list()) {
				map.put(registration.getPrimaryKeyObj(), registration);

				cacheResult(registration);

				uncachedPrimaryKeys.remove(registration.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RegistrationModelImpl.ENTITY_CACHE_ENABLED,
					RegistrationImpl.class, primaryKey, nullModel);
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
	 * Returns all the registrations.
	 *
	 * @return the registrations
	 */
	@Override
	public List<Registration> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @return the range of registrations
	 */
	@Override
	public List<Registration> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of registrations
	 */
	@Override
	public List<Registration> findAll(int start, int end,
		OrderByComparator<Registration> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of registrations
	 * @param end the upper bound of the range of registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of registrations
	 */
	@Override
	public List<Registration> findAll(int start, int end,
		OrderByComparator<Registration> orderByComparator,
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

		List<Registration> list = null;

		if (retrieveFromCache) {
			list = (List<Registration>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REGISTRATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REGISTRATION;

				if (pagination) {
					sql = sql.concat(RegistrationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Registration>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the registrations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Registration registration : findAll()) {
			remove(registration);
		}
	}

	/**
	 * Returns the number of registrations.
	 *
	 * @return the number of registrations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REGISTRATION);

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
		return RegistrationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the registration persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RegistrationImpl.class.getName());
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
	private static final String _SQL_SELECT_REGISTRATION = "SELECT registration FROM Registration registration";
	private static final String _SQL_SELECT_REGISTRATION_WHERE_PKS_IN = "SELECT registration FROM Registration registration WHERE registrationId IN (";
	private static final String _SQL_SELECT_REGISTRATION_WHERE = "SELECT registration FROM Registration registration WHERE ";
	private static final String _SQL_COUNT_REGISTRATION = "SELECT COUNT(registration) FROM Registration registration";
	private static final String _SQL_COUNT_REGISTRATION_WHERE = "SELECT COUNT(registration) FROM Registration registration WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "registration.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Registration exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Registration exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(RegistrationPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}