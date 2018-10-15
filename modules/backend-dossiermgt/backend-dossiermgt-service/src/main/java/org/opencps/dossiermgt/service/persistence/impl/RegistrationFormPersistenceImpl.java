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

import org.opencps.dossiermgt.exception.NoSuchRegistrationFormException;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.impl.RegistrationFormImpl;
import org.opencps.dossiermgt.model.impl.RegistrationFormModelImpl;
import org.opencps.dossiermgt.service.persistence.RegistrationFormPersistence;

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
 * The persistence implementation for the registration form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see RegistrationFormPersistence
 * @see org.opencps.dossiermgt.service.persistence.RegistrationFormUtil
 * @generated
 */
@ProviderType
public class RegistrationFormPersistenceImpl extends BasePersistenceImpl<RegistrationForm>
	implements RegistrationFormPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RegistrationFormUtil} to access the registration form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RegistrationFormImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			RegistrationFormModelImpl.UUID_COLUMN_BITMASK |
			RegistrationFormModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the registration forms where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration forms where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @return the range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration forms where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByUuid(String uuid, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration forms where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByUuid(String uuid, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator,
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

		List<RegistrationForm> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationForm>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationForm registrationForm : list) {
					if (!Objects.equals(uuid, registrationForm.getUuid())) {
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

			query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

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
				query.append(RegistrationFormModelImpl.ORDER_BY_JPQL);
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
					list = (List<RegistrationForm>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationForm>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first registration form in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByUuid_First(String uuid,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByUuid_First(uuid,
				orderByComparator);

		if (registrationForm != null) {
			return registrationForm;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchRegistrationFormException(msg.toString());
	}

	/**
	 * Returns the first registration form in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByUuid_First(String uuid,
		OrderByComparator<RegistrationForm> orderByComparator) {
		List<RegistrationForm> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration form in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByUuid_Last(String uuid,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByUuid_Last(uuid,
				orderByComparator);

		if (registrationForm != null) {
			return registrationForm;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchRegistrationFormException(msg.toString());
	}

	/**
	 * Returns the last registration form in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByUuid_Last(String uuid,
		OrderByComparator<RegistrationForm> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<RegistrationForm> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration forms before and after the current registration form in the ordered set where uuid = &#63;.
	 *
	 * @param registrationFormId the primary key of the current registration form
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration form
	 * @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	 */
	@Override
	public RegistrationForm[] findByUuid_PrevAndNext(long registrationFormId,
		String uuid, OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = findByPrimaryKey(registrationFormId);

		Session session = null;

		try {
			session = openSession();

			RegistrationForm[] array = new RegistrationFormImpl[3];

			array[0] = getByUuid_PrevAndNext(session, registrationForm, uuid,
					orderByComparator, true);

			array[1] = registrationForm;

			array[2] = getByUuid_PrevAndNext(session, registrationForm, uuid,
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

	protected RegistrationForm getByUuid_PrevAndNext(Session session,
		RegistrationForm registrationForm, String uuid,
		OrderByComparator<RegistrationForm> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

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
			query.append(RegistrationFormModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(registrationForm);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RegistrationForm> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration forms where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (RegistrationForm registrationForm : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registrationForm);
		}
	}

	/**
	 * Returns the number of registration forms where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching registration forms
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REGISTRATIONFORM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "registrationForm.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "registrationForm.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(registrationForm.uuid IS NULL OR registrationForm.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			RegistrationFormModelImpl.UUID_COLUMN_BITMASK |
			RegistrationFormModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the registration form where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRegistrationFormException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByUUID_G(uuid, groupId);

		if (registrationForm == null) {
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

			throw new NoSuchRegistrationFormException(msg.toString());
		}

		return registrationForm;
	}

	/**
	 * Returns the registration form where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the registration form where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof RegistrationForm) {
			RegistrationForm registrationForm = (RegistrationForm)result;

			if (!Objects.equals(uuid, registrationForm.getUuid()) ||
					(groupId != registrationForm.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

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

				List<RegistrationForm> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					RegistrationForm registrationForm = list.get(0);

					result = registrationForm;

					cacheResult(registrationForm);
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
			return (RegistrationForm)result;
		}
	}

	/**
	 * Removes the registration form where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the registration form that was removed
	 */
	@Override
	public RegistrationForm removeByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = findByUUID_G(uuid, groupId);

		return remove(registrationForm);
	}

	/**
	 * Returns the number of registration forms where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching registration forms
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATIONFORM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "registrationForm.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "registrationForm.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(registrationForm.uuid IS NULL OR registrationForm.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "registrationForm.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			RegistrationFormModelImpl.UUID_COLUMN_BITMASK |
			RegistrationFormModelImpl.COMPANYID_COLUMN_BITMASK |
			RegistrationFormModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the registration forms where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration forms where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @return the range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration forms where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration forms where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator,
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

		List<RegistrationForm> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationForm>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationForm registrationForm : list) {
					if (!Objects.equals(uuid, registrationForm.getUuid()) ||
							(companyId != registrationForm.getCompanyId())) {
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

			query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

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
				query.append(RegistrationFormModelImpl.ORDER_BY_JPQL);
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
					list = (List<RegistrationForm>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationForm>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first registration form in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (registrationForm != null) {
			return registrationForm;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchRegistrationFormException(msg.toString());
	}

	/**
	 * Returns the first registration form in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<RegistrationForm> orderByComparator) {
		List<RegistrationForm> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration form in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (registrationForm != null) {
			return registrationForm;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchRegistrationFormException(msg.toString());
	}

	/**
	 * Returns the last registration form in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<RegistrationForm> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<RegistrationForm> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration forms before and after the current registration form in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param registrationFormId the primary key of the current registration form
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration form
	 * @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	 */
	@Override
	public RegistrationForm[] findByUuid_C_PrevAndNext(
		long registrationFormId, String uuid, long companyId,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = findByPrimaryKey(registrationFormId);

		Session session = null;

		try {
			session = openSession();

			RegistrationForm[] array = new RegistrationFormImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, registrationForm, uuid,
					companyId, orderByComparator, true);

			array[1] = registrationForm;

			array[2] = getByUuid_C_PrevAndNext(session, registrationForm, uuid,
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

	protected RegistrationForm getByUuid_C_PrevAndNext(Session session,
		RegistrationForm registrationForm, String uuid, long companyId,
		OrderByComparator<RegistrationForm> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

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
			query.append(RegistrationFormModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(registrationForm);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RegistrationForm> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration forms where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (RegistrationForm registrationForm : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registrationForm);
		}
	}

	/**
	 * Returns the number of registration forms where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching registration forms
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATIONFORM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "registrationForm.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "registrationForm.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(registrationForm.uuid IS NULL OR registrationForm.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "registrationForm.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_REGID = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_REGID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID =
		new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_REGID",
			new String[] { Long.class.getName(), Long.class.getName() },
			RegistrationFormModelImpl.GROUPID_COLUMN_BITMASK |
			RegistrationFormModelImpl.REGISTRATIONID_COLUMN_BITMASK |
			RegistrationFormModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_REGID = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_REGID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the registration forms where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @return the matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByG_REGID(long groupId,
		long registrationId) {
		return findByG_REGID(groupId, registrationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration forms where groupId = &#63; and registrationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @return the range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByG_REGID(long groupId,
		long registrationId, int start, int end) {
		return findByG_REGID(groupId, registrationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration forms where groupId = &#63; and registrationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByG_REGID(long groupId,
		long registrationId, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return findByG_REGID(groupId, registrationId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration forms where groupId = &#63; and registrationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByG_REGID(long groupId,
		long registrationId, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID;
			finderArgs = new Object[] { groupId, registrationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_REGID;
			finderArgs = new Object[] {
					groupId, registrationId,
					
					start, end, orderByComparator
				};
		}

		List<RegistrationForm> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationForm>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationForm registrationForm : list) {
					if ((groupId != registrationForm.getGroupId()) ||
							(registrationId != registrationForm.getRegistrationId())) {
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

			query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

			query.append(_FINDER_COLUMN_G_REGID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_REGID_REGISTRATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RegistrationFormModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(registrationId);

				if (!pagination) {
					list = (List<RegistrationForm>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationForm>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first registration form in the ordered set where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByG_REGID_First(long groupId,
		long registrationId,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByG_REGID_First(groupId,
				registrationId, orderByComparator);

		if (registrationForm != null) {
			return registrationForm;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", registrationId=");
		msg.append(registrationId);

		msg.append("}");

		throw new NoSuchRegistrationFormException(msg.toString());
	}

	/**
	 * Returns the first registration form in the ordered set where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByG_REGID_First(long groupId,
		long registrationId,
		OrderByComparator<RegistrationForm> orderByComparator) {
		List<RegistrationForm> list = findByG_REGID(groupId, registrationId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration form in the ordered set where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByG_REGID_Last(long groupId,
		long registrationId,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByG_REGID_Last(groupId,
				registrationId, orderByComparator);

		if (registrationForm != null) {
			return registrationForm;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", registrationId=");
		msg.append(registrationId);

		msg.append("}");

		throw new NoSuchRegistrationFormException(msg.toString());
	}

	/**
	 * Returns the last registration form in the ordered set where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByG_REGID_Last(long groupId,
		long registrationId,
		OrderByComparator<RegistrationForm> orderByComparator) {
		int count = countByG_REGID(groupId, registrationId);

		if (count == 0) {
			return null;
		}

		List<RegistrationForm> list = findByG_REGID(groupId, registrationId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration forms before and after the current registration form in the ordered set where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param registrationFormId the primary key of the current registration form
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration form
	 * @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	 */
	@Override
	public RegistrationForm[] findByG_REGID_PrevAndNext(
		long registrationFormId, long groupId, long registrationId,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = findByPrimaryKey(registrationFormId);

		Session session = null;

		try {
			session = openSession();

			RegistrationForm[] array = new RegistrationFormImpl[3];

			array[0] = getByG_REGID_PrevAndNext(session, registrationForm,
					groupId, registrationId, orderByComparator, true);

			array[1] = registrationForm;

			array[2] = getByG_REGID_PrevAndNext(session, registrationForm,
					groupId, registrationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RegistrationForm getByG_REGID_PrevAndNext(Session session,
		RegistrationForm registrationForm, long groupId, long registrationId,
		OrderByComparator<RegistrationForm> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

		query.append(_FINDER_COLUMN_G_REGID_GROUPID_2);

		query.append(_FINDER_COLUMN_G_REGID_REGISTRATIONID_2);

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
			query.append(RegistrationFormModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(registrationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(registrationForm);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RegistrationForm> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration forms where groupId = &#63; and registrationId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 */
	@Override
	public void removeByG_REGID(long groupId, long registrationId) {
		for (RegistrationForm registrationForm : findByG_REGID(groupId,
				registrationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registrationForm);
		}
	}

	/**
	 * Returns the number of registration forms where groupId = &#63; and registrationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @return the number of matching registration forms
	 */
	@Override
	public int countByG_REGID(long groupId, long registrationId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_REGID;

		Object[] finderArgs = new Object[] { groupId, registrationId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATIONFORM_WHERE);

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

	private static final String _FINDER_COLUMN_G_REGID_GROUPID_2 = "registrationForm.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_REGID_REGISTRATIONID_2 = "registrationForm.registrationId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_REGID_REFID = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_REGID_REFID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			RegistrationFormModelImpl.GROUPID_COLUMN_BITMASK |
			RegistrationFormModelImpl.REGISTRATIONID_COLUMN_BITMASK |
			RegistrationFormModelImpl.REFERENCEUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_REGID_REFID = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_REGID_REFID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the registration form where groupId = &#63; and registrationId = &#63; and referenceUid = &#63; or throws a {@link NoSuchRegistrationFormException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param referenceUid the reference uid
	 * @return the matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByG_REGID_REFID(long groupId,
		long registrationId, String referenceUid)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByG_REGID_REFID(groupId,
				registrationId, referenceUid);

		if (registrationForm == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", registrationId=");
			msg.append(registrationId);

			msg.append(", referenceUid=");
			msg.append(referenceUid);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchRegistrationFormException(msg.toString());
		}

		return registrationForm;
	}

	/**
	 * Returns the registration form where groupId = &#63; and registrationId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param referenceUid the reference uid
	 * @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByG_REGID_REFID(long groupId,
		long registrationId, String referenceUid) {
		return fetchByG_REGID_REFID(groupId, registrationId, referenceUid, true);
	}

	/**
	 * Returns the registration form where groupId = &#63; and registrationId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param referenceUid the reference uid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByG_REGID_REFID(long groupId,
		long registrationId, String referenceUid, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, registrationId, referenceUid };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_REGID_REFID,
					finderArgs, this);
		}

		if (result instanceof RegistrationForm) {
			RegistrationForm registrationForm = (RegistrationForm)result;

			if ((groupId != registrationForm.getGroupId()) ||
					(registrationId != registrationForm.getRegistrationId()) ||
					!Objects.equals(referenceUid,
						registrationForm.getReferenceUid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

			query.append(_FINDER_COLUMN_G_REGID_REFID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_REGID_REFID_REGISTRATIONID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_G_REGID_REFID_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_G_REGID_REFID_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_G_REGID_REFID_REFERENCEUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(registrationId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
				}

				List<RegistrationForm> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_REGID_REFID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"RegistrationFormPersistenceImpl.fetchByG_REGID_REFID(long, long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					RegistrationForm registrationForm = list.get(0);

					result = registrationForm;

					cacheResult(registrationForm);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_REGID_REFID,
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
			return (RegistrationForm)result;
		}
	}

	/**
	 * Removes the registration form where groupId = &#63; and registrationId = &#63; and referenceUid = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param referenceUid the reference uid
	 * @return the registration form that was removed
	 */
	@Override
	public RegistrationForm removeByG_REGID_REFID(long groupId,
		long registrationId, String referenceUid)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = findByG_REGID_REFID(groupId,
				registrationId, referenceUid);

		return remove(registrationForm);
	}

	/**
	 * Returns the number of registration forms where groupId = &#63; and registrationId = &#63; and referenceUid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param referenceUid the reference uid
	 * @return the number of matching registration forms
	 */
	@Override
	public int countByG_REGID_REFID(long groupId, long registrationId,
		String referenceUid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_REGID_REFID;

		Object[] finderArgs = new Object[] { groupId, registrationId, referenceUid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_REGISTRATIONFORM_WHERE);

			query.append(_FINDER_COLUMN_G_REGID_REFID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_REGID_REFID_REGISTRATIONID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_G_REGID_REFID_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_G_REGID_REFID_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_G_REGID_REFID_REFERENCEUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(registrationId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
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

	private static final String _FINDER_COLUMN_G_REGID_REFID_GROUPID_2 = "registrationForm.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_REGID_REFID_REGISTRATIONID_2 = "registrationForm.registrationId = ? AND ";
	private static final String _FINDER_COLUMN_G_REGID_REFID_REFERENCEUID_1 = "registrationForm.referenceUid IS NULL";
	private static final String _FINDER_COLUMN_G_REGID_REFID_REFERENCEUID_2 = "registrationForm.referenceUid = ?";
	private static final String _FINDER_COLUMN_G_REGID_REFID_REFERENCEUID_3 = "(registrationForm.referenceUid IS NULL OR registrationForm.referenceUid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_REGID_ISNEW =
		new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_REGID_ISNEW",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID_ISNEW =
		new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_REGID_ISNEW",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			RegistrationFormModelImpl.REGISTRATIONID_COLUMN_BITMASK |
			RegistrationFormModelImpl.ISNEW_COLUMN_BITMASK |
			RegistrationFormModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_REGID_ISNEW = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_REGID_ISNEW",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the registration forms where registrationId = &#63; and isNew = &#63;.
	 *
	 * @param registrationId the registration ID
	 * @param isNew the is new
	 * @return the matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByG_REGID_ISNEW(long registrationId,
		boolean isNew) {
		return findByG_REGID_ISNEW(registrationId, isNew, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration forms where registrationId = &#63; and isNew = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param registrationId the registration ID
	 * @param isNew the is new
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @return the range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByG_REGID_ISNEW(long registrationId,
		boolean isNew, int start, int end) {
		return findByG_REGID_ISNEW(registrationId, isNew, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration forms where registrationId = &#63; and isNew = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param registrationId the registration ID
	 * @param isNew the is new
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByG_REGID_ISNEW(long registrationId,
		boolean isNew, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return findByG_REGID_ISNEW(registrationId, isNew, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration forms where registrationId = &#63; and isNew = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param registrationId the registration ID
	 * @param isNew the is new
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByG_REGID_ISNEW(long registrationId,
		boolean isNew, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID_ISNEW;
			finderArgs = new Object[] { registrationId, isNew };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_REGID_ISNEW;
			finderArgs = new Object[] {
					registrationId, isNew,
					
					start, end, orderByComparator
				};
		}

		List<RegistrationForm> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationForm>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationForm registrationForm : list) {
					if ((registrationId != registrationForm.getRegistrationId()) ||
							(isNew != registrationForm.isIsNew())) {
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

			query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

			query.append(_FINDER_COLUMN_G_REGID_ISNEW_REGISTRATIONID_2);

			query.append(_FINDER_COLUMN_G_REGID_ISNEW_ISNEW_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RegistrationFormModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(registrationId);

				qPos.add(isNew);

				if (!pagination) {
					list = (List<RegistrationForm>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationForm>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first registration form in the ordered set where registrationId = &#63; and isNew = &#63;.
	 *
	 * @param registrationId the registration ID
	 * @param isNew the is new
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByG_REGID_ISNEW_First(long registrationId,
		boolean isNew, OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByG_REGID_ISNEW_First(registrationId,
				isNew, orderByComparator);

		if (registrationForm != null) {
			return registrationForm;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("registrationId=");
		msg.append(registrationId);

		msg.append(", isNew=");
		msg.append(isNew);

		msg.append("}");

		throw new NoSuchRegistrationFormException(msg.toString());
	}

	/**
	 * Returns the first registration form in the ordered set where registrationId = &#63; and isNew = &#63;.
	 *
	 * @param registrationId the registration ID
	 * @param isNew the is new
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByG_REGID_ISNEW_First(long registrationId,
		boolean isNew, OrderByComparator<RegistrationForm> orderByComparator) {
		List<RegistrationForm> list = findByG_REGID_ISNEW(registrationId,
				isNew, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration form in the ordered set where registrationId = &#63; and isNew = &#63;.
	 *
	 * @param registrationId the registration ID
	 * @param isNew the is new
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByG_REGID_ISNEW_Last(long registrationId,
		boolean isNew, OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByG_REGID_ISNEW_Last(registrationId,
				isNew, orderByComparator);

		if (registrationForm != null) {
			return registrationForm;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("registrationId=");
		msg.append(registrationId);

		msg.append(", isNew=");
		msg.append(isNew);

		msg.append("}");

		throw new NoSuchRegistrationFormException(msg.toString());
	}

	/**
	 * Returns the last registration form in the ordered set where registrationId = &#63; and isNew = &#63;.
	 *
	 * @param registrationId the registration ID
	 * @param isNew the is new
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByG_REGID_ISNEW_Last(long registrationId,
		boolean isNew, OrderByComparator<RegistrationForm> orderByComparator) {
		int count = countByG_REGID_ISNEW(registrationId, isNew);

		if (count == 0) {
			return null;
		}

		List<RegistrationForm> list = findByG_REGID_ISNEW(registrationId,
				isNew, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration forms before and after the current registration form in the ordered set where registrationId = &#63; and isNew = &#63;.
	 *
	 * @param registrationFormId the primary key of the current registration form
	 * @param registrationId the registration ID
	 * @param isNew the is new
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration form
	 * @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	 */
	@Override
	public RegistrationForm[] findByG_REGID_ISNEW_PrevAndNext(
		long registrationFormId, long registrationId, boolean isNew,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = findByPrimaryKey(registrationFormId);

		Session session = null;

		try {
			session = openSession();

			RegistrationForm[] array = new RegistrationFormImpl[3];

			array[0] = getByG_REGID_ISNEW_PrevAndNext(session,
					registrationForm, registrationId, isNew, orderByComparator,
					true);

			array[1] = registrationForm;

			array[2] = getByG_REGID_ISNEW_PrevAndNext(session,
					registrationForm, registrationId, isNew, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RegistrationForm getByG_REGID_ISNEW_PrevAndNext(Session session,
		RegistrationForm registrationForm, long registrationId, boolean isNew,
		OrderByComparator<RegistrationForm> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

		query.append(_FINDER_COLUMN_G_REGID_ISNEW_REGISTRATIONID_2);

		query.append(_FINDER_COLUMN_G_REGID_ISNEW_ISNEW_2);

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
			query.append(RegistrationFormModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(registrationId);

		qPos.add(isNew);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(registrationForm);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RegistrationForm> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration forms where registrationId = &#63; and isNew = &#63; from the database.
	 *
	 * @param registrationId the registration ID
	 * @param isNew the is new
	 */
	@Override
	public void removeByG_REGID_ISNEW(long registrationId, boolean isNew) {
		for (RegistrationForm registrationForm : findByG_REGID_ISNEW(
				registrationId, isNew, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(registrationForm);
		}
	}

	/**
	 * Returns the number of registration forms where registrationId = &#63; and isNew = &#63;.
	 *
	 * @param registrationId the registration ID
	 * @param isNew the is new
	 * @return the number of matching registration forms
	 */
	@Override
	public int countByG_REGID_ISNEW(long registrationId, boolean isNew) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_REGID_ISNEW;

		Object[] finderArgs = new Object[] { registrationId, isNew };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATIONFORM_WHERE);

			query.append(_FINDER_COLUMN_G_REGID_ISNEW_REGISTRATIONID_2);

			query.append(_FINDER_COLUMN_G_REGID_ISNEW_ISNEW_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(registrationId);

				qPos.add(isNew);

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

	private static final String _FINDER_COLUMN_G_REGID_ISNEW_REGISTRATIONID_2 = "registrationForm.registrationId = ? AND ";
	private static final String _FINDER_COLUMN_G_REGID_ISNEW_ISNEW_2 = "registrationForm.isNew = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_REGID_FORMNO = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByREGID_FORMNO",
			new String[] { Long.class.getName(), String.class.getName() },
			RegistrationFormModelImpl.REGISTRATIONID_COLUMN_BITMASK |
			RegistrationFormModelImpl.FORMNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REGID_FORMNO = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByREGID_FORMNO",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the registration form where registrationId = &#63; and formNo = &#63; or throws a {@link NoSuchRegistrationFormException} if it could not be found.
	 *
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @return the matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByREGID_FORMNO(long registrationId,
		String formNo) throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByREGID_FORMNO(registrationId,
				formNo);

		if (registrationForm == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("registrationId=");
			msg.append(registrationId);

			msg.append(", formNo=");
			msg.append(formNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchRegistrationFormException(msg.toString());
		}

		return registrationForm;
	}

	/**
	 * Returns the registration form where registrationId = &#63; and formNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByREGID_FORMNO(long registrationId,
		String formNo) {
		return fetchByREGID_FORMNO(registrationId, formNo, true);
	}

	/**
	 * Returns the registration form where registrationId = &#63; and formNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByREGID_FORMNO(long registrationId,
		String formNo, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { registrationId, formNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_REGID_FORMNO,
					finderArgs, this);
		}

		if (result instanceof RegistrationForm) {
			RegistrationForm registrationForm = (RegistrationForm)result;

			if ((registrationId != registrationForm.getRegistrationId()) ||
					!Objects.equals(formNo, registrationForm.getFormNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

			query.append(_FINDER_COLUMN_REGID_FORMNO_REGISTRATIONID_2);

			boolean bindFormNo = false;

			if (formNo == null) {
				query.append(_FINDER_COLUMN_REGID_FORMNO_FORMNO_1);
			}
			else if (formNo.equals("")) {
				query.append(_FINDER_COLUMN_REGID_FORMNO_FORMNO_3);
			}
			else {
				bindFormNo = true;

				query.append(_FINDER_COLUMN_REGID_FORMNO_FORMNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(registrationId);

				if (bindFormNo) {
					qPos.add(formNo);
				}

				List<RegistrationForm> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_REGID_FORMNO,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"RegistrationFormPersistenceImpl.fetchByREGID_FORMNO(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					RegistrationForm registrationForm = list.get(0);

					result = registrationForm;

					cacheResult(registrationForm);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_REGID_FORMNO,
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
			return (RegistrationForm)result;
		}
	}

	/**
	 * Removes the registration form where registrationId = &#63; and formNo = &#63; from the database.
	 *
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @return the registration form that was removed
	 */
	@Override
	public RegistrationForm removeByREGID_FORMNO(long registrationId,
		String formNo) throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = findByREGID_FORMNO(registrationId,
				formNo);

		return remove(registrationForm);
	}

	/**
	 * Returns the number of registration forms where registrationId = &#63; and formNo = &#63;.
	 *
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @return the number of matching registration forms
	 */
	@Override
	public int countByREGID_FORMNO(long registrationId, String formNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_REGID_FORMNO;

		Object[] finderArgs = new Object[] { registrationId, formNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATIONFORM_WHERE);

			query.append(_FINDER_COLUMN_REGID_FORMNO_REGISTRATIONID_2);

			boolean bindFormNo = false;

			if (formNo == null) {
				query.append(_FINDER_COLUMN_REGID_FORMNO_FORMNO_1);
			}
			else if (formNo.equals("")) {
				query.append(_FINDER_COLUMN_REGID_FORMNO_FORMNO_3);
			}
			else {
				bindFormNo = true;

				query.append(_FINDER_COLUMN_REGID_FORMNO_FORMNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(registrationId);

				if (bindFormNo) {
					qPos.add(formNo);
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

	private static final String _FINDER_COLUMN_REGID_FORMNO_REGISTRATIONID_2 = "registrationForm.registrationId = ? AND ";
	private static final String _FINDER_COLUMN_REGID_FORMNO_FORMNO_1 = "registrationForm.formNo IS NULL";
	private static final String _FINDER_COLUMN_REGID_FORMNO_FORMNO_2 = "registrationForm.formNo = ?";
	private static final String _FINDER_COLUMN_REGID_FORMNO_FORMNO_3 = "(registrationForm.formNo IS NULL OR registrationForm.formNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_REGID_FORMNO =
		new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_REGID_FORMNO",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID_FORMNO =
		new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_REGID_FORMNO",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			RegistrationFormModelImpl.GROUPID_COLUMN_BITMASK |
			RegistrationFormModelImpl.REGISTRATIONID_COLUMN_BITMASK |
			RegistrationFormModelImpl.FORMNO_COLUMN_BITMASK |
			RegistrationFormModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_REGID_FORMNO = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_REGID_FORMNO",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @return the matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByG_REGID_FORMNO(long groupId,
		long registrationId, String formNo) {
		return findByG_REGID_FORMNO(groupId, registrationId, formNo,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @return the range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByG_REGID_FORMNO(long groupId,
		long registrationId, String formNo, int start, int end) {
		return findByG_REGID_FORMNO(groupId, registrationId, formNo, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByG_REGID_FORMNO(long groupId,
		long registrationId, String formNo, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return findByG_REGID_FORMNO(groupId, registrationId, formNo, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByG_REGID_FORMNO(long groupId,
		long registrationId, String formNo, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID_FORMNO;
			finderArgs = new Object[] { groupId, registrationId, formNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_REGID_FORMNO;
			finderArgs = new Object[] {
					groupId, registrationId, formNo,
					
					start, end, orderByComparator
				};
		}

		List<RegistrationForm> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationForm>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationForm registrationForm : list) {
					if ((groupId != registrationForm.getGroupId()) ||
							(registrationId != registrationForm.getRegistrationId()) ||
							!Objects.equals(formNo, registrationForm.getFormNo())) {
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

			query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

			query.append(_FINDER_COLUMN_G_REGID_FORMNO_GROUPID_2);

			query.append(_FINDER_COLUMN_G_REGID_FORMNO_REGISTRATIONID_2);

			boolean bindFormNo = false;

			if (formNo == null) {
				query.append(_FINDER_COLUMN_G_REGID_FORMNO_FORMNO_1);
			}
			else if (formNo.equals("")) {
				query.append(_FINDER_COLUMN_G_REGID_FORMNO_FORMNO_3);
			}
			else {
				bindFormNo = true;

				query.append(_FINDER_COLUMN_G_REGID_FORMNO_FORMNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RegistrationFormModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(registrationId);

				if (bindFormNo) {
					qPos.add(formNo);
				}

				if (!pagination) {
					list = (List<RegistrationForm>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationForm>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first registration form in the ordered set where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByG_REGID_FORMNO_First(long groupId,
		long registrationId, String formNo,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByG_REGID_FORMNO_First(groupId,
				registrationId, formNo, orderByComparator);

		if (registrationForm != null) {
			return registrationForm;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", registrationId=");
		msg.append(registrationId);

		msg.append(", formNo=");
		msg.append(formNo);

		msg.append("}");

		throw new NoSuchRegistrationFormException(msg.toString());
	}

	/**
	 * Returns the first registration form in the ordered set where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByG_REGID_FORMNO_First(long groupId,
		long registrationId, String formNo,
		OrderByComparator<RegistrationForm> orderByComparator) {
		List<RegistrationForm> list = findByG_REGID_FORMNO(groupId,
				registrationId, formNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration form in the ordered set where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByG_REGID_FORMNO_Last(long groupId,
		long registrationId, String formNo,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByG_REGID_FORMNO_Last(groupId,
				registrationId, formNo, orderByComparator);

		if (registrationForm != null) {
			return registrationForm;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", registrationId=");
		msg.append(registrationId);

		msg.append(", formNo=");
		msg.append(formNo);

		msg.append("}");

		throw new NoSuchRegistrationFormException(msg.toString());
	}

	/**
	 * Returns the last registration form in the ordered set where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByG_REGID_FORMNO_Last(long groupId,
		long registrationId, String formNo,
		OrderByComparator<RegistrationForm> orderByComparator) {
		int count = countByG_REGID_FORMNO(groupId, registrationId, formNo);

		if (count == 0) {
			return null;
		}

		List<RegistrationForm> list = findByG_REGID_FORMNO(groupId,
				registrationId, formNo, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration forms before and after the current registration form in the ordered set where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	 *
	 * @param registrationFormId the primary key of the current registration form
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration form
	 * @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	 */
	@Override
	public RegistrationForm[] findByG_REGID_FORMNO_PrevAndNext(
		long registrationFormId, long groupId, long registrationId,
		String formNo, OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = findByPrimaryKey(registrationFormId);

		Session session = null;

		try {
			session = openSession();

			RegistrationForm[] array = new RegistrationFormImpl[3];

			array[0] = getByG_REGID_FORMNO_PrevAndNext(session,
					registrationForm, groupId, registrationId, formNo,
					orderByComparator, true);

			array[1] = registrationForm;

			array[2] = getByG_REGID_FORMNO_PrevAndNext(session,
					registrationForm, groupId, registrationId, formNo,
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

	protected RegistrationForm getByG_REGID_FORMNO_PrevAndNext(
		Session session, RegistrationForm registrationForm, long groupId,
		long registrationId, String formNo,
		OrderByComparator<RegistrationForm> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

		query.append(_FINDER_COLUMN_G_REGID_FORMNO_GROUPID_2);

		query.append(_FINDER_COLUMN_G_REGID_FORMNO_REGISTRATIONID_2);

		boolean bindFormNo = false;

		if (formNo == null) {
			query.append(_FINDER_COLUMN_G_REGID_FORMNO_FORMNO_1);
		}
		else if (formNo.equals("")) {
			query.append(_FINDER_COLUMN_G_REGID_FORMNO_FORMNO_3);
		}
		else {
			bindFormNo = true;

			query.append(_FINDER_COLUMN_G_REGID_FORMNO_FORMNO_2);
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
			query.append(RegistrationFormModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(registrationId);

		if (bindFormNo) {
			qPos.add(formNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(registrationForm);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RegistrationForm> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 */
	@Override
	public void removeByG_REGID_FORMNO(long groupId, long registrationId,
		String formNo) {
		for (RegistrationForm registrationForm : findByG_REGID_FORMNO(groupId,
				registrationId, formNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(registrationForm);
		}
	}

	/**
	 * Returns the number of registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationId the registration ID
	 * @param formNo the form no
	 * @return the number of matching registration forms
	 */
	@Override
	public int countByG_REGID_FORMNO(long groupId, long registrationId,
		String formNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_REGID_FORMNO;

		Object[] finderArgs = new Object[] { groupId, registrationId, formNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_REGISTRATIONFORM_WHERE);

			query.append(_FINDER_COLUMN_G_REGID_FORMNO_GROUPID_2);

			query.append(_FINDER_COLUMN_G_REGID_FORMNO_REGISTRATIONID_2);

			boolean bindFormNo = false;

			if (formNo == null) {
				query.append(_FINDER_COLUMN_G_REGID_FORMNO_FORMNO_1);
			}
			else if (formNo.equals("")) {
				query.append(_FINDER_COLUMN_G_REGID_FORMNO_FORMNO_3);
			}
			else {
				bindFormNo = true;

				query.append(_FINDER_COLUMN_G_REGID_FORMNO_FORMNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(registrationId);

				if (bindFormNo) {
					qPos.add(formNo);
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

	private static final String _FINDER_COLUMN_G_REGID_FORMNO_GROUPID_2 = "registrationForm.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_REGID_FORMNO_REGISTRATIONID_2 = "registrationForm.registrationId = ? AND ";
	private static final String _FINDER_COLUMN_G_REGID_FORMNO_FORMNO_1 = "registrationForm.formNo IS NULL";
	private static final String _FINDER_COLUMN_G_REGID_FORMNO_FORMNO_2 = "registrationForm.formNo = ?";
	private static final String _FINDER_COLUMN_G_REGID_FORMNO_FORMNO_3 = "(registrationForm.formNo IS NULL OR registrationForm.formNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_REFID = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_REFID",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REFID =
		new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED,
			RegistrationFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_REFID",
			new String[] { String.class.getName() },
			RegistrationFormModelImpl.REFERENCEUID_COLUMN_BITMASK |
			RegistrationFormModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_REFID = new FinderPath(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_REFID",
			new String[] { String.class.getName() });

	/**
	 * Returns all the registration forms where referenceUid = &#63;.
	 *
	 * @param referenceUid the reference uid
	 * @return the matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByF_REFID(String referenceUid) {
		return findByF_REFID(referenceUid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration forms where referenceUid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param referenceUid the reference uid
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @return the range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByF_REFID(String referenceUid, int start,
		int end) {
		return findByF_REFID(referenceUid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration forms where referenceUid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param referenceUid the reference uid
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByF_REFID(String referenceUid, int start,
		int end, OrderByComparator<RegistrationForm> orderByComparator) {
		return findByF_REFID(referenceUid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration forms where referenceUid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param referenceUid the reference uid
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registration forms
	 */
	@Override
	public List<RegistrationForm> findByF_REFID(String referenceUid, int start,
		int end, OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REFID;
			finderArgs = new Object[] { referenceUid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_REFID;
			finderArgs = new Object[] {
					referenceUid,
					
					start, end, orderByComparator
				};
		}

		List<RegistrationForm> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationForm>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationForm registrationForm : list) {
					if (!Objects.equals(referenceUid,
								registrationForm.getReferenceUid())) {
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

			query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_F_REFID_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_F_REFID_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_F_REFID_REFERENCEUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RegistrationFormModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
				}

				if (!pagination) {
					list = (List<RegistrationForm>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationForm>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first registration form in the ordered set where referenceUid = &#63;.
	 *
	 * @param referenceUid the reference uid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByF_REFID_First(String referenceUid,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByF_REFID_First(referenceUid,
				orderByComparator);

		if (registrationForm != null) {
			return registrationForm;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("referenceUid=");
		msg.append(referenceUid);

		msg.append("}");

		throw new NoSuchRegistrationFormException(msg.toString());
	}

	/**
	 * Returns the first registration form in the ordered set where referenceUid = &#63;.
	 *
	 * @param referenceUid the reference uid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByF_REFID_First(String referenceUid,
		OrderByComparator<RegistrationForm> orderByComparator) {
		List<RegistrationForm> list = findByF_REFID(referenceUid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration form in the ordered set where referenceUid = &#63;.
	 *
	 * @param referenceUid the reference uid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration form
	 * @throws NoSuchRegistrationFormException if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm findByF_REFID_Last(String referenceUid,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByF_REFID_Last(referenceUid,
				orderByComparator);

		if (registrationForm != null) {
			return registrationForm;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("referenceUid=");
		msg.append(referenceUid);

		msg.append("}");

		throw new NoSuchRegistrationFormException(msg.toString());
	}

	/**
	 * Returns the last registration form in the ordered set where referenceUid = &#63;.
	 *
	 * @param referenceUid the reference uid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	 */
	@Override
	public RegistrationForm fetchByF_REFID_Last(String referenceUid,
		OrderByComparator<RegistrationForm> orderByComparator) {
		int count = countByF_REFID(referenceUid);

		if (count == 0) {
			return null;
		}

		List<RegistrationForm> list = findByF_REFID(referenceUid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration forms before and after the current registration form in the ordered set where referenceUid = &#63;.
	 *
	 * @param registrationFormId the primary key of the current registration form
	 * @param referenceUid the reference uid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration form
	 * @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	 */
	@Override
	public RegistrationForm[] findByF_REFID_PrevAndNext(
		long registrationFormId, String referenceUid,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = findByPrimaryKey(registrationFormId);

		Session session = null;

		try {
			session = openSession();

			RegistrationForm[] array = new RegistrationFormImpl[3];

			array[0] = getByF_REFID_PrevAndNext(session, registrationForm,
					referenceUid, orderByComparator, true);

			array[1] = registrationForm;

			array[2] = getByF_REFID_PrevAndNext(session, registrationForm,
					referenceUid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RegistrationForm getByF_REFID_PrevAndNext(Session session,
		RegistrationForm registrationForm, String referenceUid,
		OrderByComparator<RegistrationForm> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE);

		boolean bindReferenceUid = false;

		if (referenceUid == null) {
			query.append(_FINDER_COLUMN_F_REFID_REFERENCEUID_1);
		}
		else if (referenceUid.equals("")) {
			query.append(_FINDER_COLUMN_F_REFID_REFERENCEUID_3);
		}
		else {
			bindReferenceUid = true;

			query.append(_FINDER_COLUMN_F_REFID_REFERENCEUID_2);
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
			query.append(RegistrationFormModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindReferenceUid) {
			qPos.add(referenceUid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(registrationForm);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RegistrationForm> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration forms where referenceUid = &#63; from the database.
	 *
	 * @param referenceUid the reference uid
	 */
	@Override
	public void removeByF_REFID(String referenceUid) {
		for (RegistrationForm registrationForm : findByF_REFID(referenceUid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registrationForm);
		}
	}

	/**
	 * Returns the number of registration forms where referenceUid = &#63;.
	 *
	 * @param referenceUid the reference uid
	 * @return the number of matching registration forms
	 */
	@Override
	public int countByF_REFID(String referenceUid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_REFID;

		Object[] finderArgs = new Object[] { referenceUid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REGISTRATIONFORM_WHERE);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_F_REFID_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_F_REFID_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_F_REFID_REFERENCEUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
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

	private static final String _FINDER_COLUMN_F_REFID_REFERENCEUID_1 = "registrationForm.referenceUid IS NULL";
	private static final String _FINDER_COLUMN_F_REFID_REFERENCEUID_2 = "registrationForm.referenceUid = ?";
	private static final String _FINDER_COLUMN_F_REFID_REFERENCEUID_3 = "(registrationForm.referenceUid IS NULL OR registrationForm.referenceUid = '')";

	public RegistrationFormPersistenceImpl() {
		setModelClass(RegistrationForm.class);

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
	 * Caches the registration form in the entity cache if it is enabled.
	 *
	 * @param registrationForm the registration form
	 */
	@Override
	public void cacheResult(RegistrationForm registrationForm) {
		entityCache.putResult(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormImpl.class, registrationForm.getPrimaryKey(),
			registrationForm);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				registrationForm.getUuid(), registrationForm.getGroupId()
			}, registrationForm);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_REGID_REFID,
			new Object[] {
				registrationForm.getGroupId(),
				registrationForm.getRegistrationId(),
				registrationForm.getReferenceUid()
			}, registrationForm);

		finderCache.putResult(FINDER_PATH_FETCH_BY_REGID_FORMNO,
			new Object[] {
				registrationForm.getRegistrationId(),
				registrationForm.getFormNo()
			}, registrationForm);

		registrationForm.resetOriginalValues();
	}

	/**
	 * Caches the registration forms in the entity cache if it is enabled.
	 *
	 * @param registrationForms the registration forms
	 */
	@Override
	public void cacheResult(List<RegistrationForm> registrationForms) {
		for (RegistrationForm registrationForm : registrationForms) {
			if (entityCache.getResult(
						RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
						RegistrationFormImpl.class,
						registrationForm.getPrimaryKey()) == null) {
				cacheResult(registrationForm);
			}
			else {
				registrationForm.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all registration forms.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RegistrationFormImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the registration form.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RegistrationForm registrationForm) {
		entityCache.removeResult(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormImpl.class, registrationForm.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((RegistrationFormModelImpl)registrationForm,
			true);
	}

	@Override
	public void clearCache(List<RegistrationForm> registrationForms) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RegistrationForm registrationForm : registrationForms) {
			entityCache.removeResult(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
				RegistrationFormImpl.class, registrationForm.getPrimaryKey());

			clearUniqueFindersCache((RegistrationFormModelImpl)registrationForm,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		RegistrationFormModelImpl registrationFormModelImpl) {
		Object[] args = new Object[] {
				registrationFormModelImpl.getUuid(),
				registrationFormModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			registrationFormModelImpl, false);

		args = new Object[] {
				registrationFormModelImpl.getGroupId(),
				registrationFormModelImpl.getRegistrationId(),
				registrationFormModelImpl.getReferenceUid()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_REGID_REFID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_REGID_REFID, args,
			registrationFormModelImpl, false);

		args = new Object[] {
				registrationFormModelImpl.getRegistrationId(),
				registrationFormModelImpl.getFormNo()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_REGID_FORMNO, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_REGID_FORMNO, args,
			registrationFormModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		RegistrationFormModelImpl registrationFormModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					registrationFormModelImpl.getUuid(),
					registrationFormModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((registrationFormModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					registrationFormModelImpl.getOriginalUuid(),
					registrationFormModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					registrationFormModelImpl.getGroupId(),
					registrationFormModelImpl.getRegistrationId(),
					registrationFormModelImpl.getReferenceUid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID_REFID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_REGID_REFID, args);
		}

		if ((registrationFormModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_REGID_REFID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					registrationFormModelImpl.getOriginalGroupId(),
					registrationFormModelImpl.getOriginalRegistrationId(),
					registrationFormModelImpl.getOriginalReferenceUid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID_REFID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_REGID_REFID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					registrationFormModelImpl.getRegistrationId(),
					registrationFormModelImpl.getFormNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_REGID_FORMNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_REGID_FORMNO, args);
		}

		if ((registrationFormModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_REGID_FORMNO.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					registrationFormModelImpl.getOriginalRegistrationId(),
					registrationFormModelImpl.getOriginalFormNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_REGID_FORMNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_REGID_FORMNO, args);
		}
	}

	/**
	 * Creates a new registration form with the primary key. Does not add the registration form to the database.
	 *
	 * @param registrationFormId the primary key for the new registration form
	 * @return the new registration form
	 */
	@Override
	public RegistrationForm create(long registrationFormId) {
		RegistrationForm registrationForm = new RegistrationFormImpl();

		registrationForm.setNew(true);
		registrationForm.setPrimaryKey(registrationFormId);

		String uuid = PortalUUIDUtil.generate();

		registrationForm.setUuid(uuid);

		registrationForm.setCompanyId(companyProvider.getCompanyId());

		return registrationForm;
	}

	/**
	 * Removes the registration form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param registrationFormId the primary key of the registration form
	 * @return the registration form that was removed
	 * @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	 */
	@Override
	public RegistrationForm remove(long registrationFormId)
		throws NoSuchRegistrationFormException {
		return remove((Serializable)registrationFormId);
	}

	/**
	 * Removes the registration form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the registration form
	 * @return the registration form that was removed
	 * @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	 */
	@Override
	public RegistrationForm remove(Serializable primaryKey)
		throws NoSuchRegistrationFormException {
		Session session = null;

		try {
			session = openSession();

			RegistrationForm registrationForm = (RegistrationForm)session.get(RegistrationFormImpl.class,
					primaryKey);

			if (registrationForm == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRegistrationFormException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(registrationForm);
		}
		catch (NoSuchRegistrationFormException nsee) {
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
	protected RegistrationForm removeImpl(RegistrationForm registrationForm) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(registrationForm)) {
				registrationForm = (RegistrationForm)session.get(RegistrationFormImpl.class,
						registrationForm.getPrimaryKeyObj());
			}

			if (registrationForm != null) {
				session.delete(registrationForm);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (registrationForm != null) {
			clearCache(registrationForm);
		}

		return registrationForm;
	}

	@Override
	public RegistrationForm updateImpl(RegistrationForm registrationForm) {
		boolean isNew = registrationForm.isNew();

		if (!(registrationForm instanceof RegistrationFormModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(registrationForm.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(registrationForm);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in registrationForm proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom RegistrationForm implementation " +
				registrationForm.getClass());
		}

		RegistrationFormModelImpl registrationFormModelImpl = (RegistrationFormModelImpl)registrationForm;

		if (Validator.isNull(registrationForm.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			registrationForm.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (registrationForm.getCreateDate() == null)) {
			if (serviceContext == null) {
				registrationForm.setCreateDate(now);
			}
			else {
				registrationForm.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!registrationFormModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				registrationForm.setModifiedDate(now);
			}
			else {
				registrationForm.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (registrationForm.isNew()) {
				session.save(registrationForm);

				registrationForm.setNew(false);
			}
			else {
				registrationForm = (RegistrationForm)session.merge(registrationForm);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!RegistrationFormModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { registrationFormModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					registrationFormModelImpl.getUuid(),
					registrationFormModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					registrationFormModelImpl.getGroupId(),
					registrationFormModelImpl.getRegistrationId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID,
				args);

			args = new Object[] {
					registrationFormModelImpl.getRegistrationId(),
					registrationFormModelImpl.isIsNew()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID_ISNEW, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID_ISNEW,
				args);

			args = new Object[] {
					registrationFormModelImpl.getGroupId(),
					registrationFormModelImpl.getRegistrationId(),
					registrationFormModelImpl.getFormNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID_FORMNO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID_FORMNO,
				args);

			args = new Object[] { registrationFormModelImpl.getReferenceUid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_REFID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REFID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((registrationFormModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationFormModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { registrationFormModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((registrationFormModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationFormModelImpl.getOriginalUuid(),
						registrationFormModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						registrationFormModelImpl.getUuid(),
						registrationFormModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((registrationFormModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationFormModelImpl.getOriginalGroupId(),
						registrationFormModelImpl.getOriginalRegistrationId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID,
					args);

				args = new Object[] {
						registrationFormModelImpl.getGroupId(),
						registrationFormModelImpl.getRegistrationId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID,
					args);
			}

			if ((registrationFormModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID_ISNEW.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationFormModelImpl.getOriginalRegistrationId(),
						registrationFormModelImpl.getOriginalIsNew()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID_ISNEW,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID_ISNEW,
					args);

				args = new Object[] {
						registrationFormModelImpl.getRegistrationId(),
						registrationFormModelImpl.isIsNew()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID_ISNEW,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID_ISNEW,
					args);
			}

			if ((registrationFormModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID_FORMNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationFormModelImpl.getOriginalGroupId(),
						registrationFormModelImpl.getOriginalRegistrationId(),
						registrationFormModelImpl.getOriginalFormNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID_FORMNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID_FORMNO,
					args);

				args = new Object[] {
						registrationFormModelImpl.getGroupId(),
						registrationFormModelImpl.getRegistrationId(),
						registrationFormModelImpl.getFormNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID_FORMNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_REGID_FORMNO,
					args);
			}

			if ((registrationFormModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REFID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationFormModelImpl.getOriginalReferenceUid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_REFID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REFID,
					args);

				args = new Object[] { registrationFormModelImpl.getReferenceUid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_REFID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REFID,
					args);
			}
		}

		entityCache.putResult(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationFormImpl.class, registrationForm.getPrimaryKey(),
			registrationForm, false);

		clearUniqueFindersCache(registrationFormModelImpl, false);
		cacheUniqueFindersCache(registrationFormModelImpl);

		registrationForm.resetOriginalValues();

		return registrationForm;
	}

	/**
	 * Returns the registration form with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the registration form
	 * @return the registration form
	 * @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	 */
	@Override
	public RegistrationForm findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRegistrationFormException {
		RegistrationForm registrationForm = fetchByPrimaryKey(primaryKey);

		if (registrationForm == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRegistrationFormException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return registrationForm;
	}

	/**
	 * Returns the registration form with the primary key or throws a {@link NoSuchRegistrationFormException} if it could not be found.
	 *
	 * @param registrationFormId the primary key of the registration form
	 * @return the registration form
	 * @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	 */
	@Override
	public RegistrationForm findByPrimaryKey(long registrationFormId)
		throws NoSuchRegistrationFormException {
		return findByPrimaryKey((Serializable)registrationFormId);
	}

	/**
	 * Returns the registration form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the registration form
	 * @return the registration form, or <code>null</code> if a registration form with the primary key could not be found
	 */
	@Override
	public RegistrationForm fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
				RegistrationFormImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		RegistrationForm registrationForm = (RegistrationForm)serializable;

		if (registrationForm == null) {
			Session session = null;

			try {
				session = openSession();

				registrationForm = (RegistrationForm)session.get(RegistrationFormImpl.class,
						primaryKey);

				if (registrationForm != null) {
					cacheResult(registrationForm);
				}
				else {
					entityCache.putResult(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
						RegistrationFormImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
					RegistrationFormImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return registrationForm;
	}

	/**
	 * Returns the registration form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param registrationFormId the primary key of the registration form
	 * @return the registration form, or <code>null</code> if a registration form with the primary key could not be found
	 */
	@Override
	public RegistrationForm fetchByPrimaryKey(long registrationFormId) {
		return fetchByPrimaryKey((Serializable)registrationFormId);
	}

	@Override
	public Map<Serializable, RegistrationForm> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, RegistrationForm> map = new HashMap<Serializable, RegistrationForm>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			RegistrationForm registrationForm = fetchByPrimaryKey(primaryKey);

			if (registrationForm != null) {
				map.put(primaryKey, registrationForm);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
					RegistrationFormImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (RegistrationForm)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_REGISTRATIONFORM_WHERE_PKS_IN);

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

			for (RegistrationForm registrationForm : (List<RegistrationForm>)q.list()) {
				map.put(registrationForm.getPrimaryKeyObj(), registrationForm);

				cacheResult(registrationForm);

				uncachedPrimaryKeys.remove(registrationForm.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RegistrationFormModelImpl.ENTITY_CACHE_ENABLED,
					RegistrationFormImpl.class, primaryKey, nullModel);
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
	 * Returns all the registration forms.
	 *
	 * @return the registration forms
	 */
	@Override
	public List<RegistrationForm> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @return the range of registration forms
	 */
	@Override
	public List<RegistrationForm> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of registration forms
	 */
	@Override
	public List<RegistrationForm> findAll(int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration forms
	 * @param end the upper bound of the range of registration forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of registration forms
	 */
	@Override
	public List<RegistrationForm> findAll(int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator,
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

		List<RegistrationForm> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationForm>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REGISTRATIONFORM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REGISTRATIONFORM;

				if (pagination) {
					sql = sql.concat(RegistrationFormModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RegistrationForm>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationForm>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the registration forms from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RegistrationForm registrationForm : findAll()) {
			remove(registrationForm);
		}
	}

	/**
	 * Returns the number of registration forms.
	 *
	 * @return the number of registration forms
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REGISTRATIONFORM);

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
		return RegistrationFormModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the registration form persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RegistrationFormImpl.class.getName());
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
	private static final String _SQL_SELECT_REGISTRATIONFORM = "SELECT registrationForm FROM RegistrationForm registrationForm";
	private static final String _SQL_SELECT_REGISTRATIONFORM_WHERE_PKS_IN = "SELECT registrationForm FROM RegistrationForm registrationForm WHERE registrationFormId IN (";
	private static final String _SQL_SELECT_REGISTRATIONFORM_WHERE = "SELECT registrationForm FROM RegistrationForm registrationForm WHERE ";
	private static final String _SQL_COUNT_REGISTRATIONFORM = "SELECT COUNT(registrationForm) FROM RegistrationForm registrationForm";
	private static final String _SQL_COUNT_REGISTRATIONFORM_WHERE = "SELECT COUNT(registrationForm) FROM RegistrationForm registrationForm WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "registrationForm.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RegistrationForm exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No RegistrationForm exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(RegistrationFormPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}