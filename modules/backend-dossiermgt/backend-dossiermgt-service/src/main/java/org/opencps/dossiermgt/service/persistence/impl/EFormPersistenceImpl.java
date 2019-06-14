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

import org.opencps.dossiermgt.exception.NoSuchEFormException;
import org.opencps.dossiermgt.model.EForm;
import org.opencps.dossiermgt.model.impl.EFormImpl;
import org.opencps.dossiermgt.model.impl.EFormModelImpl;
import org.opencps.dossiermgt.service.persistence.EFormPersistence;

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
 * The persistence implementation for the e form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see EFormPersistence
 * @see org.opencps.dossiermgt.service.persistence.EFormUtil
 * @generated
 */
@ProviderType
public class EFormPersistenceImpl extends BasePersistenceImpl<EForm>
	implements EFormPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EFormUtil} to access the e form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EFormImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, EFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, EFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, EFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, EFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			EFormModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the e forms where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching e forms
	 */
	@Override
	public List<EForm> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the e forms where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of e forms
	 * @param end the upper bound of the range of e forms (not inclusive)
	 * @return the range of matching e forms
	 */
	@Override
	public List<EForm> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the e forms where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of e forms
	 * @param end the upper bound of the range of e forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching e forms
	 */
	@Override
	public List<EForm> findByUuid(String uuid, int start, int end,
		OrderByComparator<EForm> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the e forms where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of e forms
	 * @param end the upper bound of the range of e forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching e forms
	 */
	@Override
	public List<EForm> findByUuid(String uuid, int start, int end,
		OrderByComparator<EForm> orderByComparator, boolean retrieveFromCache) {
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

		List<EForm> list = null;

		if (retrieveFromCache) {
			list = (List<EForm>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (EForm eForm : list) {
					if (!Objects.equals(uuid, eForm.getUuid())) {
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

			query.append(_SQL_SELECT_EFORM_WHERE);

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
				query.append(EFormModelImpl.ORDER_BY_JPQL);
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
					list = (List<EForm>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EForm>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first e form in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching e form
	 * @throws NoSuchEFormException if a matching e form could not be found
	 */
	@Override
	public EForm findByUuid_First(String uuid,
		OrderByComparator<EForm> orderByComparator) throws NoSuchEFormException {
		EForm eForm = fetchByUuid_First(uuid, orderByComparator);

		if (eForm != null) {
			return eForm;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchEFormException(msg.toString());
	}

	/**
	 * Returns the first e form in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching e form, or <code>null</code> if a matching e form could not be found
	 */
	@Override
	public EForm fetchByUuid_First(String uuid,
		OrderByComparator<EForm> orderByComparator) {
		List<EForm> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last e form in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching e form
	 * @throws NoSuchEFormException if a matching e form could not be found
	 */
	@Override
	public EForm findByUuid_Last(String uuid,
		OrderByComparator<EForm> orderByComparator) throws NoSuchEFormException {
		EForm eForm = fetchByUuid_Last(uuid, orderByComparator);

		if (eForm != null) {
			return eForm;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchEFormException(msg.toString());
	}

	/**
	 * Returns the last e form in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching e form, or <code>null</code> if a matching e form could not be found
	 */
	@Override
	public EForm fetchByUuid_Last(String uuid,
		OrderByComparator<EForm> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<EForm> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the e forms before and after the current e form in the ordered set where uuid = &#63;.
	 *
	 * @param eFormId the primary key of the current e form
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next e form
	 * @throws NoSuchEFormException if a e form with the primary key could not be found
	 */
	@Override
	public EForm[] findByUuid_PrevAndNext(long eFormId, String uuid,
		OrderByComparator<EForm> orderByComparator) throws NoSuchEFormException {
		EForm eForm = findByPrimaryKey(eFormId);

		Session session = null;

		try {
			session = openSession();

			EForm[] array = new EFormImpl[3];

			array[0] = getByUuid_PrevAndNext(session, eForm, uuid,
					orderByComparator, true);

			array[1] = eForm;

			array[2] = getByUuid_PrevAndNext(session, eForm, uuid,
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

	protected EForm getByUuid_PrevAndNext(Session session, EForm eForm,
		String uuid, OrderByComparator<EForm> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EFORM_WHERE);

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
			query.append(EFormModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(eForm);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EForm> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the e forms where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (EForm eForm : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(eForm);
		}
	}

	/**
	 * Returns the number of e forms where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching e forms
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EFORM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "eForm.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "eForm.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(eForm.uuid IS NULL OR eForm.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, EFormImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			EFormModelImpl.UUID_COLUMN_BITMASK |
			EFormModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the e form where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEFormException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching e form
	 * @throws NoSuchEFormException if a matching e form could not be found
	 */
	@Override
	public EForm findByUUID_G(String uuid, long groupId)
		throws NoSuchEFormException {
		EForm eForm = fetchByUUID_G(uuid, groupId);

		if (eForm == null) {
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

			throw new NoSuchEFormException(msg.toString());
		}

		return eForm;
	}

	/**
	 * Returns the e form where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching e form, or <code>null</code> if a matching e form could not be found
	 */
	@Override
	public EForm fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the e form where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching e form, or <code>null</code> if a matching e form could not be found
	 */
	@Override
	public EForm fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof EForm) {
			EForm eForm = (EForm)result;

			if (!Objects.equals(uuid, eForm.getUuid()) ||
					(groupId != eForm.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_EFORM_WHERE);

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

				List<EForm> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					EForm eForm = list.get(0);

					result = eForm;

					cacheResult(eForm);
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
			return (EForm)result;
		}
	}

	/**
	 * Removes the e form where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the e form that was removed
	 */
	@Override
	public EForm removeByUUID_G(String uuid, long groupId)
		throws NoSuchEFormException {
		EForm eForm = findByUUID_G(uuid, groupId);

		return remove(eForm);
	}

	/**
	 * Returns the number of e forms where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching e forms
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EFORM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "eForm.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "eForm.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(eForm.uuid IS NULL OR eForm.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "eForm.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, EFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, EFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			EFormModelImpl.UUID_COLUMN_BITMASK |
			EFormModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the e forms where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching e forms
	 */
	@Override
	public List<EForm> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the e forms where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of e forms
	 * @param end the upper bound of the range of e forms (not inclusive)
	 * @return the range of matching e forms
	 */
	@Override
	public List<EForm> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the e forms where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of e forms
	 * @param end the upper bound of the range of e forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching e forms
	 */
	@Override
	public List<EForm> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<EForm> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the e forms where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of e forms
	 * @param end the upper bound of the range of e forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching e forms
	 */
	@Override
	public List<EForm> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<EForm> orderByComparator,
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

		List<EForm> list = null;

		if (retrieveFromCache) {
			list = (List<EForm>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (EForm eForm : list) {
					if (!Objects.equals(uuid, eForm.getUuid()) ||
							(companyId != eForm.getCompanyId())) {
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

			query.append(_SQL_SELECT_EFORM_WHERE);

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
				query.append(EFormModelImpl.ORDER_BY_JPQL);
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
					list = (List<EForm>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EForm>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first e form in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching e form
	 * @throws NoSuchEFormException if a matching e form could not be found
	 */
	@Override
	public EForm findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<EForm> orderByComparator) throws NoSuchEFormException {
		EForm eForm = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (eForm != null) {
			return eForm;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchEFormException(msg.toString());
	}

	/**
	 * Returns the first e form in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching e form, or <code>null</code> if a matching e form could not be found
	 */
	@Override
	public EForm fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<EForm> orderByComparator) {
		List<EForm> list = findByUuid_C(uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last e form in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching e form
	 * @throws NoSuchEFormException if a matching e form could not be found
	 */
	@Override
	public EForm findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<EForm> orderByComparator) throws NoSuchEFormException {
		EForm eForm = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (eForm != null) {
			return eForm;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchEFormException(msg.toString());
	}

	/**
	 * Returns the last e form in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching e form, or <code>null</code> if a matching e form could not be found
	 */
	@Override
	public EForm fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<EForm> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<EForm> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the e forms before and after the current e form in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param eFormId the primary key of the current e form
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next e form
	 * @throws NoSuchEFormException if a e form with the primary key could not be found
	 */
	@Override
	public EForm[] findByUuid_C_PrevAndNext(long eFormId, String uuid,
		long companyId, OrderByComparator<EForm> orderByComparator)
		throws NoSuchEFormException {
		EForm eForm = findByPrimaryKey(eFormId);

		Session session = null;

		try {
			session = openSession();

			EForm[] array = new EFormImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, eForm, uuid, companyId,
					orderByComparator, true);

			array[1] = eForm;

			array[2] = getByUuid_C_PrevAndNext(session, eForm, uuid, companyId,
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

	protected EForm getByUuid_C_PrevAndNext(Session session, EForm eForm,
		String uuid, long companyId,
		OrderByComparator<EForm> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EFORM_WHERE);

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
			query.append(EFormModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(eForm);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EForm> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the e forms where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (EForm eForm : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(eForm);
		}
	}

	/**
	 * Returns the number of e forms where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching e forms
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EFORM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "eForm.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "eForm.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(eForm.uuid IS NULL OR eForm.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "eForm.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SC = new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, EFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_GID_SC",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SC =
		new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, EFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_GID_SC",
			new String[] { Long.class.getName(), String.class.getName() },
			EFormModelImpl.GROUPID_COLUMN_BITMASK |
			EFormModelImpl.SERVICECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_SC = new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_SC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the e forms where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @return the matching e forms
	 */
	@Override
	public List<EForm> findByF_GID_SC(long groupId, String serviceCode) {
		return findByF_GID_SC(groupId, serviceCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the e forms where groupId = &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of e forms
	 * @param end the upper bound of the range of e forms (not inclusive)
	 * @return the range of matching e forms
	 */
	@Override
	public List<EForm> findByF_GID_SC(long groupId, String serviceCode,
		int start, int end) {
		return findByF_GID_SC(groupId, serviceCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the e forms where groupId = &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of e forms
	 * @param end the upper bound of the range of e forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching e forms
	 */
	@Override
	public List<EForm> findByF_GID_SC(long groupId, String serviceCode,
		int start, int end, OrderByComparator<EForm> orderByComparator) {
		return findByF_GID_SC(groupId, serviceCode, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the e forms where groupId = &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of e forms
	 * @param end the upper bound of the range of e forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching e forms
	 */
	@Override
	public List<EForm> findByF_GID_SC(long groupId, String serviceCode,
		int start, int end, OrderByComparator<EForm> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SC;
			finderArgs = new Object[] { groupId, serviceCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SC;
			finderArgs = new Object[] {
					groupId, serviceCode,
					
					start, end, orderByComparator
				};
		}

		List<EForm> list = null;

		if (retrieveFromCache) {
			list = (List<EForm>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (EForm eForm : list) {
					if ((groupId != eForm.getGroupId()) ||
							!Objects.equals(serviceCode, eForm.getServiceCode())) {
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

			query.append(_SQL_SELECT_EFORM_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SC_GROUPID_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EFormModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (!pagination) {
					list = (List<EForm>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EForm>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first e form in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching e form
	 * @throws NoSuchEFormException if a matching e form could not be found
	 */
	@Override
	public EForm findByF_GID_SC_First(long groupId, String serviceCode,
		OrderByComparator<EForm> orderByComparator) throws NoSuchEFormException {
		EForm eForm = fetchByF_GID_SC_First(groupId, serviceCode,
				orderByComparator);

		if (eForm != null) {
			return eForm;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append("}");

		throw new NoSuchEFormException(msg.toString());
	}

	/**
	 * Returns the first e form in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching e form, or <code>null</code> if a matching e form could not be found
	 */
	@Override
	public EForm fetchByF_GID_SC_First(long groupId, String serviceCode,
		OrderByComparator<EForm> orderByComparator) {
		List<EForm> list = findByF_GID_SC(groupId, serviceCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last e form in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching e form
	 * @throws NoSuchEFormException if a matching e form could not be found
	 */
	@Override
	public EForm findByF_GID_SC_Last(long groupId, String serviceCode,
		OrderByComparator<EForm> orderByComparator) throws NoSuchEFormException {
		EForm eForm = fetchByF_GID_SC_Last(groupId, serviceCode,
				orderByComparator);

		if (eForm != null) {
			return eForm;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append("}");

		throw new NoSuchEFormException(msg.toString());
	}

	/**
	 * Returns the last e form in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching e form, or <code>null</code> if a matching e form could not be found
	 */
	@Override
	public EForm fetchByF_GID_SC_Last(long groupId, String serviceCode,
		OrderByComparator<EForm> orderByComparator) {
		int count = countByF_GID_SC(groupId, serviceCode);

		if (count == 0) {
			return null;
		}

		List<EForm> list = findByF_GID_SC(groupId, serviceCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the e forms before and after the current e form in the ordered set where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param eFormId the primary key of the current e form
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next e form
	 * @throws NoSuchEFormException if a e form with the primary key could not be found
	 */
	@Override
	public EForm[] findByF_GID_SC_PrevAndNext(long eFormId, long groupId,
		String serviceCode, OrderByComparator<EForm> orderByComparator)
		throws NoSuchEFormException {
		EForm eForm = findByPrimaryKey(eFormId);

		Session session = null;

		try {
			session = openSession();

			EForm[] array = new EFormImpl[3];

			array[0] = getByF_GID_SC_PrevAndNext(session, eForm, groupId,
					serviceCode, orderByComparator, true);

			array[1] = eForm;

			array[2] = getByF_GID_SC_PrevAndNext(session, eForm, groupId,
					serviceCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EForm getByF_GID_SC_PrevAndNext(Session session, EForm eForm,
		long groupId, String serviceCode,
		OrderByComparator<EForm> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EFORM_WHERE);

		query.append(_FINDER_COLUMN_F_GID_SC_GROUPID_2);

		boolean bindServiceCode = false;

		if (serviceCode == null) {
			query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_1);
		}
		else if (serviceCode.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_3);
		}
		else {
			bindServiceCode = true;

			query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_2);
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
			query.append(EFormModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindServiceCode) {
			qPos.add(serviceCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(eForm);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EForm> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the e forms where groupId = &#63; and serviceCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 */
	@Override
	public void removeByF_GID_SC(long groupId, String serviceCode) {
		for (EForm eForm : findByF_GID_SC(groupId, serviceCode,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(eForm);
		}
	}

	/**
	 * Returns the number of e forms where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @return the number of matching e forms
	 */
	@Override
	public int countByF_GID_SC(long groupId, String serviceCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_SC;

		Object[] finderArgs = new Object[] { groupId, serviceCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EFORM_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SC_GROUPID_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServiceCode) {
					qPos.add(serviceCode);
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

	private static final String _FINDER_COLUMN_F_GID_SC_GROUPID_2 = "eForm.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SC_SERVICECODE_1 = "eForm.serviceCode IS NULL";
	private static final String _FINDER_COLUMN_F_GID_SC_SERVICECODE_2 = "eForm.serviceCode = ?";
	private static final String _FINDER_COLUMN_F_GID_SC_SERVICECODE_3 = "(eForm.serviceCode IS NULL OR eForm.serviceCode = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_FORM = new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, EFormImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_GID_FORM",
			new String[] { Long.class.getName(), String.class.getName() },
			EFormModelImpl.GROUPID_COLUMN_BITMASK |
			EFormModelImpl.EFORMNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_FORM = new FinderPath(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_FORM",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the e form where groupId = &#63; and eFormNo = &#63; or throws a {@link NoSuchEFormException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param eFormNo the e form no
	 * @return the matching e form
	 * @throws NoSuchEFormException if a matching e form could not be found
	 */
	@Override
	public EForm findByF_GID_FORM(long groupId, String eFormNo)
		throws NoSuchEFormException {
		EForm eForm = fetchByF_GID_FORM(groupId, eFormNo);

		if (eForm == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", eFormNo=");
			msg.append(eFormNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchEFormException(msg.toString());
		}

		return eForm;
	}

	/**
	 * Returns the e form where groupId = &#63; and eFormNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param eFormNo the e form no
	 * @return the matching e form, or <code>null</code> if a matching e form could not be found
	 */
	@Override
	public EForm fetchByF_GID_FORM(long groupId, String eFormNo) {
		return fetchByF_GID_FORM(groupId, eFormNo, true);
	}

	/**
	 * Returns the e form where groupId = &#63; and eFormNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param eFormNo the e form no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching e form, or <code>null</code> if a matching e form could not be found
	 */
	@Override
	public EForm fetchByF_GID_FORM(long groupId, String eFormNo,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, eFormNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_FORM,
					finderArgs, this);
		}

		if (result instanceof EForm) {
			EForm eForm = (EForm)result;

			if ((groupId != eForm.getGroupId()) ||
					!Objects.equals(eFormNo, eForm.getEFormNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_EFORM_WHERE);

			query.append(_FINDER_COLUMN_F_GID_FORM_GROUPID_2);

			boolean bindEFormNo = false;

			if (eFormNo == null) {
				query.append(_FINDER_COLUMN_F_GID_FORM_EFORMNO_1);
			}
			else if (eFormNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_FORM_EFORMNO_3);
			}
			else {
				bindEFormNo = true;

				query.append(_FINDER_COLUMN_F_GID_FORM_EFORMNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindEFormNo) {
					qPos.add(eFormNo);
				}

				List<EForm> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_FORM,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"EFormPersistenceImpl.fetchByF_GID_FORM(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					EForm eForm = list.get(0);

					result = eForm;

					cacheResult(eForm);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_FORM,
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
			return (EForm)result;
		}
	}

	/**
	 * Removes the e form where groupId = &#63; and eFormNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param eFormNo the e form no
	 * @return the e form that was removed
	 */
	@Override
	public EForm removeByF_GID_FORM(long groupId, String eFormNo)
		throws NoSuchEFormException {
		EForm eForm = findByF_GID_FORM(groupId, eFormNo);

		return remove(eForm);
	}

	/**
	 * Returns the number of e forms where groupId = &#63; and eFormNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param eFormNo the e form no
	 * @return the number of matching e forms
	 */
	@Override
	public int countByF_GID_FORM(long groupId, String eFormNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_FORM;

		Object[] finderArgs = new Object[] { groupId, eFormNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EFORM_WHERE);

			query.append(_FINDER_COLUMN_F_GID_FORM_GROUPID_2);

			boolean bindEFormNo = false;

			if (eFormNo == null) {
				query.append(_FINDER_COLUMN_F_GID_FORM_EFORMNO_1);
			}
			else if (eFormNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_FORM_EFORMNO_3);
			}
			else {
				bindEFormNo = true;

				query.append(_FINDER_COLUMN_F_GID_FORM_EFORMNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindEFormNo) {
					qPos.add(eFormNo);
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

	private static final String _FINDER_COLUMN_F_GID_FORM_GROUPID_2 = "eForm.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_FORM_EFORMNO_1 = "eForm.eFormNo IS NULL";
	private static final String _FINDER_COLUMN_F_GID_FORM_EFORMNO_2 = "eForm.eFormNo = ?";
	private static final String _FINDER_COLUMN_F_GID_FORM_EFORMNO_3 = "(eForm.eFormNo IS NULL OR eForm.eFormNo = '')";

	public EFormPersistenceImpl() {
		setModelClass(EForm.class);

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
	 * Caches the e form in the entity cache if it is enabled.
	 *
	 * @param eForm the e form
	 */
	@Override
	public void cacheResult(EForm eForm) {
		entityCache.putResult(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormImpl.class, eForm.getPrimaryKey(), eForm);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { eForm.getUuid(), eForm.getGroupId() }, eForm);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_FORM,
			new Object[] { eForm.getGroupId(), eForm.getEFormNo() }, eForm);

		eForm.resetOriginalValues();
	}

	/**
	 * Caches the e forms in the entity cache if it is enabled.
	 *
	 * @param eForms the e forms
	 */
	@Override
	public void cacheResult(List<EForm> eForms) {
		for (EForm eForm : eForms) {
			if (entityCache.getResult(EFormModelImpl.ENTITY_CACHE_ENABLED,
						EFormImpl.class, eForm.getPrimaryKey()) == null) {
				cacheResult(eForm);
			}
			else {
				eForm.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all e forms.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EFormImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the e form.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EForm eForm) {
		entityCache.removeResult(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormImpl.class, eForm.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((EFormModelImpl)eForm, true);
	}

	@Override
	public void clearCache(List<EForm> eForms) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EForm eForm : eForms) {
			entityCache.removeResult(EFormModelImpl.ENTITY_CACHE_ENABLED,
				EFormImpl.class, eForm.getPrimaryKey());

			clearUniqueFindersCache((EFormModelImpl)eForm, true);
		}
	}

	protected void cacheUniqueFindersCache(EFormModelImpl eFormModelImpl) {
		Object[] args = new Object[] {
				eFormModelImpl.getUuid(), eFormModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			eFormModelImpl, false);

		args = new Object[] {
				eFormModelImpl.getGroupId(), eFormModelImpl.getEFormNo()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_FORM, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_FORM, args,
			eFormModelImpl, false);
	}

	protected void clearUniqueFindersCache(EFormModelImpl eFormModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					eFormModelImpl.getUuid(), eFormModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((eFormModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					eFormModelImpl.getOriginalUuid(),
					eFormModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					eFormModelImpl.getGroupId(), eFormModelImpl.getEFormNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_FORM, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_FORM, args);
		}

		if ((eFormModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_FORM.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					eFormModelImpl.getOriginalGroupId(),
					eFormModelImpl.getOriginalEFormNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_FORM, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_FORM, args);
		}
	}

	/**
	 * Creates a new e form with the primary key. Does not add the e form to the database.
	 *
	 * @param eFormId the primary key for the new e form
	 * @return the new e form
	 */
	@Override
	public EForm create(long eFormId) {
		EForm eForm = new EFormImpl();

		eForm.setNew(true);
		eForm.setPrimaryKey(eFormId);

		String uuid = PortalUUIDUtil.generate();

		eForm.setUuid(uuid);

		eForm.setCompanyId(companyProvider.getCompanyId());

		return eForm;
	}

	/**
	 * Removes the e form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eFormId the primary key of the e form
	 * @return the e form that was removed
	 * @throws NoSuchEFormException if a e form with the primary key could not be found
	 */
	@Override
	public EForm remove(long eFormId) throws NoSuchEFormException {
		return remove((Serializable)eFormId);
	}

	/**
	 * Removes the e form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the e form
	 * @return the e form that was removed
	 * @throws NoSuchEFormException if a e form with the primary key could not be found
	 */
	@Override
	public EForm remove(Serializable primaryKey) throws NoSuchEFormException {
		Session session = null;

		try {
			session = openSession();

			EForm eForm = (EForm)session.get(EFormImpl.class, primaryKey);

			if (eForm == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEFormException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(eForm);
		}
		catch (NoSuchEFormException nsee) {
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
	protected EForm removeImpl(EForm eForm) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(eForm)) {
				eForm = (EForm)session.get(EFormImpl.class,
						eForm.getPrimaryKeyObj());
			}

			if (eForm != null) {
				session.delete(eForm);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (eForm != null) {
			clearCache(eForm);
		}

		return eForm;
	}

	@Override
	public EForm updateImpl(EForm eForm) {
		boolean isNew = eForm.isNew();

		if (!(eForm instanceof EFormModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(eForm.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(eForm);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in eForm proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom EForm implementation " +
				eForm.getClass());
		}

		EFormModelImpl eFormModelImpl = (EFormModelImpl)eForm;

		if (Validator.isNull(eForm.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			eForm.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (eForm.getCreateDate() == null)) {
			if (serviceContext == null) {
				eForm.setCreateDate(now);
			}
			else {
				eForm.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!eFormModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				eForm.setModifiedDate(now);
			}
			else {
				eForm.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (eForm.isNew()) {
				session.save(eForm);

				eForm.setNew(false);
			}
			else {
				eForm = (EForm)session.merge(eForm);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!EFormModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { eFormModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					eFormModelImpl.getUuid(), eFormModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					eFormModelImpl.getGroupId(), eFormModelImpl.getServiceCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SC,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((eFormModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { eFormModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { eFormModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((eFormModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						eFormModelImpl.getOriginalUuid(),
						eFormModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						eFormModelImpl.getUuid(), eFormModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((eFormModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						eFormModelImpl.getOriginalGroupId(),
						eFormModelImpl.getOriginalServiceCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SC,
					args);

				args = new Object[] {
						eFormModelImpl.getGroupId(),
						eFormModelImpl.getServiceCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SC,
					args);
			}
		}

		entityCache.putResult(EFormModelImpl.ENTITY_CACHE_ENABLED,
			EFormImpl.class, eForm.getPrimaryKey(), eForm, false);

		clearUniqueFindersCache(eFormModelImpl, false);
		cacheUniqueFindersCache(eFormModelImpl);

		eForm.resetOriginalValues();

		return eForm;
	}

	/**
	 * Returns the e form with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the e form
	 * @return the e form
	 * @throws NoSuchEFormException if a e form with the primary key could not be found
	 */
	@Override
	public EForm findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEFormException {
		EForm eForm = fetchByPrimaryKey(primaryKey);

		if (eForm == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEFormException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return eForm;
	}

	/**
	 * Returns the e form with the primary key or throws a {@link NoSuchEFormException} if it could not be found.
	 *
	 * @param eFormId the primary key of the e form
	 * @return the e form
	 * @throws NoSuchEFormException if a e form with the primary key could not be found
	 */
	@Override
	public EForm findByPrimaryKey(long eFormId) throws NoSuchEFormException {
		return findByPrimaryKey((Serializable)eFormId);
	}

	/**
	 * Returns the e form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the e form
	 * @return the e form, or <code>null</code> if a e form with the primary key could not be found
	 */
	@Override
	public EForm fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EFormModelImpl.ENTITY_CACHE_ENABLED,
				EFormImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		EForm eForm = (EForm)serializable;

		if (eForm == null) {
			Session session = null;

			try {
				session = openSession();

				eForm = (EForm)session.get(EFormImpl.class, primaryKey);

				if (eForm != null) {
					cacheResult(eForm);
				}
				else {
					entityCache.putResult(EFormModelImpl.ENTITY_CACHE_ENABLED,
						EFormImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EFormModelImpl.ENTITY_CACHE_ENABLED,
					EFormImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return eForm;
	}

	/**
	 * Returns the e form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eFormId the primary key of the e form
	 * @return the e form, or <code>null</code> if a e form with the primary key could not be found
	 */
	@Override
	public EForm fetchByPrimaryKey(long eFormId) {
		return fetchByPrimaryKey((Serializable)eFormId);
	}

	@Override
	public Map<Serializable, EForm> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, EForm> map = new HashMap<Serializable, EForm>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			EForm eForm = fetchByPrimaryKey(primaryKey);

			if (eForm != null) {
				map.put(primaryKey, eForm);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EFormModelImpl.ENTITY_CACHE_ENABLED,
					EFormImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (EForm)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_EFORM_WHERE_PKS_IN);

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

			for (EForm eForm : (List<EForm>)q.list()) {
				map.put(eForm.getPrimaryKeyObj(), eForm);

				cacheResult(eForm);

				uncachedPrimaryKeys.remove(eForm.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EFormModelImpl.ENTITY_CACHE_ENABLED,
					EFormImpl.class, primaryKey, nullModel);
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
	 * Returns all the e forms.
	 *
	 * @return the e forms
	 */
	@Override
	public List<EForm> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the e forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of e forms
	 * @param end the upper bound of the range of e forms (not inclusive)
	 * @return the range of e forms
	 */
	@Override
	public List<EForm> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the e forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of e forms
	 * @param end the upper bound of the range of e forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of e forms
	 */
	@Override
	public List<EForm> findAll(int start, int end,
		OrderByComparator<EForm> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the e forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of e forms
	 * @param end the upper bound of the range of e forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of e forms
	 */
	@Override
	public List<EForm> findAll(int start, int end,
		OrderByComparator<EForm> orderByComparator, boolean retrieveFromCache) {
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

		List<EForm> list = null;

		if (retrieveFromCache) {
			list = (List<EForm>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_EFORM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EFORM;

				if (pagination) {
					sql = sql.concat(EFormModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<EForm>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EForm>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the e forms from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EForm eForm : findAll()) {
			remove(eForm);
		}
	}

	/**
	 * Returns the number of e forms.
	 *
	 * @return the number of e forms
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EFORM);

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
		return EFormModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the e form persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(EFormImpl.class.getName());
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
	private static final String _SQL_SELECT_EFORM = "SELECT eForm FROM EForm eForm";
	private static final String _SQL_SELECT_EFORM_WHERE_PKS_IN = "SELECT eForm FROM EForm eForm WHERE eFormId IN (";
	private static final String _SQL_SELECT_EFORM_WHERE = "SELECT eForm FROM EForm eForm WHERE ";
	private static final String _SQL_COUNT_EFORM = "SELECT COUNT(eForm) FROM EForm eForm";
	private static final String _SQL_COUNT_EFORM_WHERE = "SELECT COUNT(eForm) FROM EForm eForm WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "eForm.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EForm exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No EForm exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(EFormPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}