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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.model.impl.RegistrationTemplatesImpl;
import org.opencps.dossiermgt.model.impl.RegistrationTemplatesModelImpl;
import org.opencps.dossiermgt.service.persistence.RegistrationTemplatesPersistence;

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
 * The persistence implementation for the registration templates service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see RegistrationTemplatesPersistence
 * @see org.opencps.dossiermgt.service.persistence.RegistrationTemplatesUtil
 * @generated
 */
@ProviderType
public class RegistrationTemplatesPersistenceImpl extends BasePersistenceImpl<RegistrationTemplates>
	implements RegistrationTemplatesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RegistrationTemplatesUtil} to access the registration templates persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RegistrationTemplatesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED,
			RegistrationTemplatesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED,
			RegistrationTemplatesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED,
			RegistrationTemplatesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED,
			RegistrationTemplatesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			RegistrationTemplatesModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the registration templateses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration templateses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @return the range of matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration templateses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByUuid(String uuid, int start,
		int end, OrderByComparator<RegistrationTemplates> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration templateses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByUuid(String uuid, int start,
		int end, OrderByComparator<RegistrationTemplates> orderByComparator,
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

		List<RegistrationTemplates> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationTemplates>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationTemplates registrationTemplates : list) {
					if (!Objects.equals(uuid, registrationTemplates.getUuid())) {
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

			query.append(_SQL_SELECT_REGISTRATIONTEMPLATES_WHERE);

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
				query.append(RegistrationTemplatesModelImpl.ORDER_BY_JPQL);
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
					list = (List<RegistrationTemplates>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationTemplates>)QueryUtil.list(q,
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
	 * Returns the first registration templates in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration templates
	 * @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates findByUuid_First(String uuid,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = fetchByUuid_First(uuid,
				orderByComparator);

		if (registrationTemplates != null) {
			return registrationTemplates;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchRegistrationTemplatesException(msg.toString());
	}

	/**
	 * Returns the first registration templates in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration templates, or <code>null</code> if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates fetchByUuid_First(String uuid,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		List<RegistrationTemplates> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration templates in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration templates
	 * @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates findByUuid_Last(String uuid,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = fetchByUuid_Last(uuid,
				orderByComparator);

		if (registrationTemplates != null) {
			return registrationTemplates;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchRegistrationTemplatesException(msg.toString());
	}

	/**
	 * Returns the last registration templates in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration templates, or <code>null</code> if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates fetchByUuid_Last(String uuid,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<RegistrationTemplates> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration templateses before and after the current registration templates in the ordered set where uuid = &#63;.
	 *
	 * @param registrationTemplateId the primary key of the current registration templates
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration templates
	 * @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	 */
	@Override
	public RegistrationTemplates[] findByUuid_PrevAndNext(
		long registrationTemplateId, String uuid,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = findByPrimaryKey(registrationTemplateId);

		Session session = null;

		try {
			session = openSession();

			RegistrationTemplates[] array = new RegistrationTemplatesImpl[3];

			array[0] = getByUuid_PrevAndNext(session, registrationTemplates,
					uuid, orderByComparator, true);

			array[1] = registrationTemplates;

			array[2] = getByUuid_PrevAndNext(session, registrationTemplates,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RegistrationTemplates getByUuid_PrevAndNext(Session session,
		RegistrationTemplates registrationTemplates, String uuid,
		OrderByComparator<RegistrationTemplates> orderByComparator,
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

		query.append(_SQL_SELECT_REGISTRATIONTEMPLATES_WHERE);

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
			query.append(RegistrationTemplatesModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(registrationTemplates);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RegistrationTemplates> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration templateses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (RegistrationTemplates registrationTemplates : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registrationTemplates);
		}
	}

	/**
	 * Returns the number of registration templateses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching registration templateses
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REGISTRATIONTEMPLATES_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "registrationTemplates.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "registrationTemplates.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(registrationTemplates.uuid IS NULL OR registrationTemplates.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED,
			RegistrationTemplatesImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			RegistrationTemplatesModelImpl.UUID_COLUMN_BITMASK |
			RegistrationTemplatesModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the registration templates where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRegistrationTemplatesException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching registration templates
	 * @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates findByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = fetchByUUID_G(uuid,
				groupId);

		if (registrationTemplates == null) {
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

			throw new NoSuchRegistrationTemplatesException(msg.toString());
		}

		return registrationTemplates;
	}

	/**
	 * Returns the registration templates where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the registration templates where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof RegistrationTemplates) {
			RegistrationTemplates registrationTemplates = (RegistrationTemplates)result;

			if (!Objects.equals(uuid, registrationTemplates.getUuid()) ||
					(groupId != registrationTemplates.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_REGISTRATIONTEMPLATES_WHERE);

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

				List<RegistrationTemplates> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					RegistrationTemplates registrationTemplates = list.get(0);

					result = registrationTemplates;

					cacheResult(registrationTemplates);
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
			return (RegistrationTemplates)result;
		}
	}

	/**
	 * Removes the registration templates where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the registration templates that was removed
	 */
	@Override
	public RegistrationTemplates removeByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = findByUUID_G(uuid, groupId);

		return remove(registrationTemplates);
	}

	/**
	 * Returns the number of registration templateses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching registration templateses
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATIONTEMPLATES_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "registrationTemplates.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "registrationTemplates.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(registrationTemplates.uuid IS NULL OR registrationTemplates.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "registrationTemplates.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_REGID = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED,
			RegistrationTemplatesImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_REGID",
			new String[] { Long.class.getName(), Long.class.getName() },
			RegistrationTemplatesModelImpl.GROUPID_COLUMN_BITMASK |
			RegistrationTemplatesModelImpl.REGISTRATIONTEMPLATEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_REGID = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_REGID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the registration templates where groupId = &#63; and registrationTemplateId = &#63; or throws a {@link NoSuchRegistrationTemplatesException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param registrationTemplateId the registration template ID
	 * @return the matching registration templates
	 * @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates findByG_REGID(long groupId,
		long registrationTemplateId)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = fetchByG_REGID(groupId,
				registrationTemplateId);

		if (registrationTemplates == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", registrationTemplateId=");
			msg.append(registrationTemplateId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchRegistrationTemplatesException(msg.toString());
		}

		return registrationTemplates;
	}

	/**
	 * Returns the registration templates where groupId = &#63; and registrationTemplateId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param registrationTemplateId the registration template ID
	 * @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates fetchByG_REGID(long groupId,
		long registrationTemplateId) {
		return fetchByG_REGID(groupId, registrationTemplateId, true);
	}

	/**
	 * Returns the registration templates where groupId = &#63; and registrationTemplateId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param registrationTemplateId the registration template ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates fetchByG_REGID(long groupId,
		long registrationTemplateId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, registrationTemplateId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_REGID,
					finderArgs, this);
		}

		if (result instanceof RegistrationTemplates) {
			RegistrationTemplates registrationTemplates = (RegistrationTemplates)result;

			if ((groupId != registrationTemplates.getGroupId()) ||
					(registrationTemplateId != registrationTemplates.getRegistrationTemplateId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_REGISTRATIONTEMPLATES_WHERE);

			query.append(_FINDER_COLUMN_G_REGID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_REGID_REGISTRATIONTEMPLATEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(registrationTemplateId);

				List<RegistrationTemplates> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_REGID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"RegistrationTemplatesPersistenceImpl.fetchByG_REGID(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					RegistrationTemplates registrationTemplates = list.get(0);

					result = registrationTemplates;

					cacheResult(registrationTemplates);
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
			return (RegistrationTemplates)result;
		}
	}

	/**
	 * Removes the registration templates where groupId = &#63; and registrationTemplateId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param registrationTemplateId the registration template ID
	 * @return the registration templates that was removed
	 */
	@Override
	public RegistrationTemplates removeByG_REGID(long groupId,
		long registrationTemplateId)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = findByG_REGID(groupId,
				registrationTemplateId);

		return remove(registrationTemplates);
	}

	/**
	 * Returns the number of registration templateses where groupId = &#63; and registrationTemplateId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param registrationTemplateId the registration template ID
	 * @return the number of matching registration templateses
	 */
	@Override
	public int countByG_REGID(long groupId, long registrationTemplateId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_REGID;

		Object[] finderArgs = new Object[] { groupId, registrationTemplateId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATIONTEMPLATES_WHERE);

			query.append(_FINDER_COLUMN_G_REGID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_REGID_REGISTRATIONTEMPLATEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(registrationTemplateId);

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

	private static final String _FINDER_COLUMN_G_REGID_GROUPID_2 = "registrationTemplates.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_REGID_REGISTRATIONTEMPLATEID_2 = "registrationTemplates.registrationTemplateId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FNO = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED,
			RegistrationTemplatesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFNO",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FNO = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED,
			RegistrationTemplatesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFNO",
			new String[] { Long.class.getName(), String.class.getName() },
			RegistrationTemplatesModelImpl.GROUPID_COLUMN_BITMASK |
			RegistrationTemplatesModelImpl.FORMNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FNO = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFNO",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the registration templateses where groupId = &#63; and formNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @return the matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByFNO(long groupId, String formNo) {
		return findByFNO(groupId, formNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the registration templateses where groupId = &#63; and formNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @return the range of matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByFNO(long groupId, String formNo,
		int start, int end) {
		return findByFNO(groupId, formNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration templateses where groupId = &#63; and formNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByFNO(long groupId, String formNo,
		int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return findByFNO(groupId, formNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration templateses where groupId = &#63; and formNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByFNO(long groupId, String formNo,
		int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FNO;
			finderArgs = new Object[] { groupId, formNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FNO;
			finderArgs = new Object[] {
					groupId, formNo,
					
					start, end, orderByComparator
				};
		}

		List<RegistrationTemplates> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationTemplates>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationTemplates registrationTemplates : list) {
					if ((groupId != registrationTemplates.getGroupId()) ||
							!Objects.equals(formNo,
								registrationTemplates.getFormNo())) {
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

			query.append(_SQL_SELECT_REGISTRATIONTEMPLATES_WHERE);

			query.append(_FINDER_COLUMN_FNO_GROUPID_2);

			boolean bindFormNo = false;

			if (formNo == null) {
				query.append(_FINDER_COLUMN_FNO_FORMNO_1);
			}
			else if (formNo.equals("")) {
				query.append(_FINDER_COLUMN_FNO_FORMNO_3);
			}
			else {
				bindFormNo = true;

				query.append(_FINDER_COLUMN_FNO_FORMNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RegistrationTemplatesModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindFormNo) {
					qPos.add(formNo);
				}

				if (!pagination) {
					list = (List<RegistrationTemplates>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationTemplates>)QueryUtil.list(q,
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
	 * Returns the first registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration templates
	 * @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates findByFNO_First(long groupId, String formNo,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = fetchByFNO_First(groupId,
				formNo, orderByComparator);

		if (registrationTemplates != null) {
			return registrationTemplates;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", formNo=");
		msg.append(formNo);

		msg.append("}");

		throw new NoSuchRegistrationTemplatesException(msg.toString());
	}

	/**
	 * Returns the first registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration templates, or <code>null</code> if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates fetchByFNO_First(long groupId, String formNo,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		List<RegistrationTemplates> list = findByFNO(groupId, formNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration templates
	 * @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates findByFNO_Last(long groupId, String formNo,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = fetchByFNO_Last(groupId,
				formNo, orderByComparator);

		if (registrationTemplates != null) {
			return registrationTemplates;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", formNo=");
		msg.append(formNo);

		msg.append("}");

		throw new NoSuchRegistrationTemplatesException(msg.toString());
	}

	/**
	 * Returns the last registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration templates, or <code>null</code> if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates fetchByFNO_Last(long groupId, String formNo,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		int count = countByFNO(groupId, formNo);

		if (count == 0) {
			return null;
		}

		List<RegistrationTemplates> list = findByFNO(groupId, formNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration templateses before and after the current registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	 *
	 * @param registrationTemplateId the primary key of the current registration templates
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration templates
	 * @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	 */
	@Override
	public RegistrationTemplates[] findByFNO_PrevAndNext(
		long registrationTemplateId, long groupId, String formNo,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = findByPrimaryKey(registrationTemplateId);

		Session session = null;

		try {
			session = openSession();

			RegistrationTemplates[] array = new RegistrationTemplatesImpl[3];

			array[0] = getByFNO_PrevAndNext(session, registrationTemplates,
					groupId, formNo, orderByComparator, true);

			array[1] = registrationTemplates;

			array[2] = getByFNO_PrevAndNext(session, registrationTemplates,
					groupId, formNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RegistrationTemplates getByFNO_PrevAndNext(Session session,
		RegistrationTemplates registrationTemplates, long groupId,
		String formNo,
		OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REGISTRATIONTEMPLATES_WHERE);

		query.append(_FINDER_COLUMN_FNO_GROUPID_2);

		boolean bindFormNo = false;

		if (formNo == null) {
			query.append(_FINDER_COLUMN_FNO_FORMNO_1);
		}
		else if (formNo.equals("")) {
			query.append(_FINDER_COLUMN_FNO_FORMNO_3);
		}
		else {
			bindFormNo = true;

			query.append(_FINDER_COLUMN_FNO_FORMNO_2);
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
			query.append(RegistrationTemplatesModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindFormNo) {
			qPos.add(formNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(registrationTemplates);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RegistrationTemplates> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration templateses where groupId = &#63; and formNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 */
	@Override
	public void removeByFNO(long groupId, String formNo) {
		for (RegistrationTemplates registrationTemplates : findByFNO(groupId,
				formNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registrationTemplates);
		}
	}

	/**
	 * Returns the number of registration templateses where groupId = &#63; and formNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @return the number of matching registration templateses
	 */
	@Override
	public int countByFNO(long groupId, String formNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FNO;

		Object[] finderArgs = new Object[] { groupId, formNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATIONTEMPLATES_WHERE);

			query.append(_FINDER_COLUMN_FNO_GROUPID_2);

			boolean bindFormNo = false;

			if (formNo == null) {
				query.append(_FINDER_COLUMN_FNO_FORMNO_1);
			}
			else if (formNo.equals("")) {
				query.append(_FINDER_COLUMN_FNO_FORMNO_3);
			}
			else {
				bindFormNo = true;

				query.append(_FINDER_COLUMN_FNO_FORMNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_FNO_GROUPID_2 = "registrationTemplates.groupId = ? AND ";
	private static final String _FINDER_COLUMN_FNO_FORMNO_1 = "registrationTemplates.formNo IS NULL";
	private static final String _FINDER_COLUMN_FNO_FORMNO_2 = "registrationTemplates.formNo = ?";
	private static final String _FINDER_COLUMN_FNO_FORMNO_3 = "(registrationTemplates.formNo IS NULL OR registrationTemplates.formNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GOVCODE = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED,
			RegistrationTemplatesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGOVCODE",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GOVCODE =
		new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED,
			RegistrationTemplatesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGOVCODE",
			new String[] { Long.class.getName(), String.class.getName() },
			RegistrationTemplatesModelImpl.GROUPID_COLUMN_BITMASK |
			RegistrationTemplatesModelImpl.GOVAGENCYCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GOVCODE = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGOVCODE",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @return the matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByGOVCODE(long groupId,
		String govAgencyCode) {
		return findByGOVCODE(groupId, govAgencyCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @return the range of matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByGOVCODE(long groupId,
		String govAgencyCode, int start, int end) {
		return findByGOVCODE(groupId, govAgencyCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByGOVCODE(long groupId,
		String govAgencyCode, int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return findByGOVCODE(groupId, govAgencyCode, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByGOVCODE(long groupId,
		String govAgencyCode, int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GOVCODE;
			finderArgs = new Object[] { groupId, govAgencyCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GOVCODE;
			finderArgs = new Object[] {
					groupId, govAgencyCode,
					
					start, end, orderByComparator
				};
		}

		List<RegistrationTemplates> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationTemplates>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationTemplates registrationTemplates : list) {
					if ((groupId != registrationTemplates.getGroupId()) ||
							!Objects.equals(govAgencyCode,
								registrationTemplates.getGovAgencyCode())) {
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

			query.append(_SQL_SELECT_REGISTRATIONTEMPLATES_WHERE);

			query.append(_FINDER_COLUMN_GOVCODE_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_GOVCODE_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_GOVCODE_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_GOVCODE_GOVAGENCYCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RegistrationTemplatesModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (!pagination) {
					list = (List<RegistrationTemplates>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationTemplates>)QueryUtil.list(q,
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
	 * Returns the first registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration templates
	 * @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates findByGOVCODE_First(long groupId,
		String govAgencyCode,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = fetchByGOVCODE_First(groupId,
				govAgencyCode, orderByComparator);

		if (registrationTemplates != null) {
			return registrationTemplates;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append("}");

		throw new NoSuchRegistrationTemplatesException(msg.toString());
	}

	/**
	 * Returns the first registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration templates, or <code>null</code> if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates fetchByGOVCODE_First(long groupId,
		String govAgencyCode,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		List<RegistrationTemplates> list = findByGOVCODE(groupId,
				govAgencyCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration templates
	 * @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates findByGOVCODE_Last(long groupId,
		String govAgencyCode,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = fetchByGOVCODE_Last(groupId,
				govAgencyCode, orderByComparator);

		if (registrationTemplates != null) {
			return registrationTemplates;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append("}");

		throw new NoSuchRegistrationTemplatesException(msg.toString());
	}

	/**
	 * Returns the last registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration templates, or <code>null</code> if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates fetchByGOVCODE_Last(long groupId,
		String govAgencyCode,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		int count = countByGOVCODE(groupId, govAgencyCode);

		if (count == 0) {
			return null;
		}

		List<RegistrationTemplates> list = findByGOVCODE(groupId,
				govAgencyCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration templateses before and after the current registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	 *
	 * @param registrationTemplateId the primary key of the current registration templates
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration templates
	 * @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	 */
	@Override
	public RegistrationTemplates[] findByGOVCODE_PrevAndNext(
		long registrationTemplateId, long groupId, String govAgencyCode,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = findByPrimaryKey(registrationTemplateId);

		Session session = null;

		try {
			session = openSession();

			RegistrationTemplates[] array = new RegistrationTemplatesImpl[3];

			array[0] = getByGOVCODE_PrevAndNext(session, registrationTemplates,
					groupId, govAgencyCode, orderByComparator, true);

			array[1] = registrationTemplates;

			array[2] = getByGOVCODE_PrevAndNext(session, registrationTemplates,
					groupId, govAgencyCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RegistrationTemplates getByGOVCODE_PrevAndNext(Session session,
		RegistrationTemplates registrationTemplates, long groupId,
		String govAgencyCode,
		OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REGISTRATIONTEMPLATES_WHERE);

		query.append(_FINDER_COLUMN_GOVCODE_GROUPID_2);

		boolean bindGovAgencyCode = false;

		if (govAgencyCode == null) {
			query.append(_FINDER_COLUMN_GOVCODE_GOVAGENCYCODE_1);
		}
		else if (govAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_GOVCODE_GOVAGENCYCODE_3);
		}
		else {
			bindGovAgencyCode = true;

			query.append(_FINDER_COLUMN_GOVCODE_GOVAGENCYCODE_2);
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
			query.append(RegistrationTemplatesModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindGovAgencyCode) {
			qPos.add(govAgencyCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(registrationTemplates);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RegistrationTemplates> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration templateses where groupId = &#63; and govAgencyCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 */
	@Override
	public void removeByGOVCODE(long groupId, String govAgencyCode) {
		for (RegistrationTemplates registrationTemplates : findByGOVCODE(
				groupId, govAgencyCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(registrationTemplates);
		}
	}

	/**
	 * Returns the number of registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @return the number of matching registration templateses
	 */
	@Override
	public int countByGOVCODE(long groupId, String govAgencyCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GOVCODE;

		Object[] finderArgs = new Object[] { groupId, govAgencyCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REGISTRATIONTEMPLATES_WHERE);

			query.append(_FINDER_COLUMN_GOVCODE_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_GOVCODE_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_GOVCODE_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_GOVCODE_GOVAGENCYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
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

	private static final String _FINDER_COLUMN_GOVCODE_GROUPID_2 = "registrationTemplates.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GOVCODE_GOVAGENCYCODE_1 = "registrationTemplates.govAgencyCode IS NULL";
	private static final String _FINDER_COLUMN_GOVCODE_GOVAGENCYCODE_2 = "registrationTemplates.govAgencyCode = ?";
	private static final String _FINDER_COLUMN_GOVCODE_GOVAGENCYCODE_3 = "(registrationTemplates.govAgencyCode IS NULL OR registrationTemplates.govAgencyCode = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_GOVCODE_FORMNO = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED,
			RegistrationTemplatesImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByGOVCODE_FORMNO",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			RegistrationTemplatesModelImpl.GROUPID_COLUMN_BITMASK |
			RegistrationTemplatesModelImpl.FORMNO_COLUMN_BITMASK |
			RegistrationTemplatesModelImpl.GOVAGENCYCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GOVCODE_FORMNO = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGOVCODE_FORMNO",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the registration templates where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63; or throws a {@link NoSuchRegistrationTemplatesException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @param govAgencyCode the gov agency code
	 * @return the matching registration templates
	 * @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates findByGOVCODE_FORMNO(long groupId,
		String formNo, String govAgencyCode)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = fetchByGOVCODE_FORMNO(groupId,
				formNo, govAgencyCode);

		if (registrationTemplates == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", formNo=");
			msg.append(formNo);

			msg.append(", govAgencyCode=");
			msg.append(govAgencyCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchRegistrationTemplatesException(msg.toString());
		}

		return registrationTemplates;
	}

	/**
	 * Returns the registration templates where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @param govAgencyCode the gov agency code
	 * @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates fetchByGOVCODE_FORMNO(long groupId,
		String formNo, String govAgencyCode) {
		return fetchByGOVCODE_FORMNO(groupId, formNo, govAgencyCode, true);
	}

	/**
	 * Returns the registration templates where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @param govAgencyCode the gov agency code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates fetchByGOVCODE_FORMNO(long groupId,
		String formNo, String govAgencyCode, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, formNo, govAgencyCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_GOVCODE_FORMNO,
					finderArgs, this);
		}

		if (result instanceof RegistrationTemplates) {
			RegistrationTemplates registrationTemplates = (RegistrationTemplates)result;

			if ((groupId != registrationTemplates.getGroupId()) ||
					!Objects.equals(formNo, registrationTemplates.getFormNo()) ||
					!Objects.equals(govAgencyCode,
						registrationTemplates.getGovAgencyCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_REGISTRATIONTEMPLATES_WHERE);

			query.append(_FINDER_COLUMN_GOVCODE_FORMNO_GROUPID_2);

			boolean bindFormNo = false;

			if (formNo == null) {
				query.append(_FINDER_COLUMN_GOVCODE_FORMNO_FORMNO_1);
			}
			else if (formNo.equals("")) {
				query.append(_FINDER_COLUMN_GOVCODE_FORMNO_FORMNO_3);
			}
			else {
				bindFormNo = true;

				query.append(_FINDER_COLUMN_GOVCODE_FORMNO_FORMNO_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_GOVCODE_FORMNO_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_GOVCODE_FORMNO_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_GOVCODE_FORMNO_GOVAGENCYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindFormNo) {
					qPos.add(formNo);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				List<RegistrationTemplates> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_GOVCODE_FORMNO,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"RegistrationTemplatesPersistenceImpl.fetchByGOVCODE_FORMNO(long, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					RegistrationTemplates registrationTemplates = list.get(0);

					result = registrationTemplates;

					cacheResult(registrationTemplates);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_GOVCODE_FORMNO,
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
			return (RegistrationTemplates)result;
		}
	}

	/**
	 * Removes the registration templates where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @param govAgencyCode the gov agency code
	 * @return the registration templates that was removed
	 */
	@Override
	public RegistrationTemplates removeByGOVCODE_FORMNO(long groupId,
		String formNo, String govAgencyCode)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = findByGOVCODE_FORMNO(groupId,
				formNo, govAgencyCode);

		return remove(registrationTemplates);
	}

	/**
	 * Returns the number of registration templateses where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param formNo the form no
	 * @param govAgencyCode the gov agency code
	 * @return the number of matching registration templateses
	 */
	@Override
	public int countByGOVCODE_FORMNO(long groupId, String formNo,
		String govAgencyCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GOVCODE_FORMNO;

		Object[] finderArgs = new Object[] { groupId, formNo, govAgencyCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_REGISTRATIONTEMPLATES_WHERE);

			query.append(_FINDER_COLUMN_GOVCODE_FORMNO_GROUPID_2);

			boolean bindFormNo = false;

			if (formNo == null) {
				query.append(_FINDER_COLUMN_GOVCODE_FORMNO_FORMNO_1);
			}
			else if (formNo.equals("")) {
				query.append(_FINDER_COLUMN_GOVCODE_FORMNO_FORMNO_3);
			}
			else {
				bindFormNo = true;

				query.append(_FINDER_COLUMN_GOVCODE_FORMNO_FORMNO_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_GOVCODE_FORMNO_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_GOVCODE_FORMNO_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_GOVCODE_FORMNO_GOVAGENCYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindFormNo) {
					qPos.add(formNo);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
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

	private static final String _FINDER_COLUMN_GOVCODE_FORMNO_GROUPID_2 = "registrationTemplates.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GOVCODE_FORMNO_FORMNO_1 = "registrationTemplates.formNo IS NULL AND ";
	private static final String _FINDER_COLUMN_GOVCODE_FORMNO_FORMNO_2 = "registrationTemplates.formNo = ? AND ";
	private static final String _FINDER_COLUMN_GOVCODE_FORMNO_FORMNO_3 = "(registrationTemplates.formNo IS NULL OR registrationTemplates.formNo = '') AND ";
	private static final String _FINDER_COLUMN_GOVCODE_FORMNO_GOVAGENCYCODE_1 = "registrationTemplates.govAgencyCode IS NULL";
	private static final String _FINDER_COLUMN_GOVCODE_FORMNO_GOVAGENCYCODE_2 = "registrationTemplates.govAgencyCode = ?";
	private static final String _FINDER_COLUMN_GOVCODE_FORMNO_GOVAGENCYCODE_3 = "(registrationTemplates.govAgencyCode IS NULL OR registrationTemplates.govAgencyCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED,
			RegistrationTemplatesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGROUPID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED,
			RegistrationTemplatesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGROUPID",
			new String[] { Long.class.getName() },
			RegistrationTemplatesModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGROUPID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the registration templateses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByGROUPID(long groupId) {
		return findByGROUPID(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration templateses where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @return the range of matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByGROUPID(long groupId, int start,
		int end) {
		return findByGROUPID(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration templateses where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByGROUPID(long groupId, int start,
		int end, OrderByComparator<RegistrationTemplates> orderByComparator) {
		return findByGROUPID(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration templateses where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findByGROUPID(long groupId, int start,
		int end, OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<RegistrationTemplates> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationTemplates>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationTemplates registrationTemplates : list) {
					if ((groupId != registrationTemplates.getGroupId())) {
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

			query.append(_SQL_SELECT_REGISTRATIONTEMPLATES_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RegistrationTemplatesModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<RegistrationTemplates>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationTemplates>)QueryUtil.list(q,
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
	 * Returns the first registration templates in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration templates
	 * @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates findByGROUPID_First(long groupId,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = fetchByGROUPID_First(groupId,
				orderByComparator);

		if (registrationTemplates != null) {
			return registrationTemplates;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchRegistrationTemplatesException(msg.toString());
	}

	/**
	 * Returns the first registration templates in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration templates, or <code>null</code> if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates fetchByGROUPID_First(long groupId,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		List<RegistrationTemplates> list = findByGROUPID(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration templates in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration templates
	 * @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates findByGROUPID_Last(long groupId,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = fetchByGROUPID_Last(groupId,
				orderByComparator);

		if (registrationTemplates != null) {
			return registrationTemplates;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchRegistrationTemplatesException(msg.toString());
	}

	/**
	 * Returns the last registration templates in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration templates, or <code>null</code> if a matching registration templates could not be found
	 */
	@Override
	public RegistrationTemplates fetchByGROUPID_Last(long groupId,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		int count = countByGROUPID(groupId);

		if (count == 0) {
			return null;
		}

		List<RegistrationTemplates> list = findByGROUPID(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration templateses before and after the current registration templates in the ordered set where groupId = &#63;.
	 *
	 * @param registrationTemplateId the primary key of the current registration templates
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration templates
	 * @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	 */
	@Override
	public RegistrationTemplates[] findByGROUPID_PrevAndNext(
		long registrationTemplateId, long groupId,
		OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = findByPrimaryKey(registrationTemplateId);

		Session session = null;

		try {
			session = openSession();

			RegistrationTemplates[] array = new RegistrationTemplatesImpl[3];

			array[0] = getByGROUPID_PrevAndNext(session, registrationTemplates,
					groupId, orderByComparator, true);

			array[1] = registrationTemplates;

			array[2] = getByGROUPID_PrevAndNext(session, registrationTemplates,
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

	protected RegistrationTemplates getByGROUPID_PrevAndNext(Session session,
		RegistrationTemplates registrationTemplates, long groupId,
		OrderByComparator<RegistrationTemplates> orderByComparator,
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

		query.append(_SQL_SELECT_REGISTRATIONTEMPLATES_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(RegistrationTemplatesModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(registrationTemplates);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RegistrationTemplates> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration templateses where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGROUPID(long groupId) {
		for (RegistrationTemplates registrationTemplates : findByGROUPID(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(registrationTemplates);
		}
	}

	/**
	 * Returns the number of registration templateses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching registration templateses
	 */
	@Override
	public int countByGROUPID(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REGISTRATIONTEMPLATES_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "registrationTemplates.groupId = ?";

	public RegistrationTemplatesPersistenceImpl() {
		setModelClass(RegistrationTemplates.class);

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
	 * Caches the registration templates in the entity cache if it is enabled.
	 *
	 * @param registrationTemplates the registration templates
	 */
	@Override
	public void cacheResult(RegistrationTemplates registrationTemplates) {
		entityCache.putResult(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesImpl.class,
			registrationTemplates.getPrimaryKey(), registrationTemplates);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				registrationTemplates.getUuid(),
				registrationTemplates.getGroupId()
			}, registrationTemplates);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_REGID,
			new Object[] {
				registrationTemplates.getGroupId(),
				registrationTemplates.getRegistrationTemplateId()
			}, registrationTemplates);

		finderCache.putResult(FINDER_PATH_FETCH_BY_GOVCODE_FORMNO,
			new Object[] {
				registrationTemplates.getGroupId(),
				registrationTemplates.getFormNo(),
				registrationTemplates.getGovAgencyCode()
			}, registrationTemplates);

		registrationTemplates.resetOriginalValues();
	}

	/**
	 * Caches the registration templateses in the entity cache if it is enabled.
	 *
	 * @param registrationTemplateses the registration templateses
	 */
	@Override
	public void cacheResult(List<RegistrationTemplates> registrationTemplateses) {
		for (RegistrationTemplates registrationTemplates : registrationTemplateses) {
			if (entityCache.getResult(
						RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
						RegistrationTemplatesImpl.class,
						registrationTemplates.getPrimaryKey()) == null) {
				cacheResult(registrationTemplates);
			}
			else {
				registrationTemplates.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all registration templateses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RegistrationTemplatesImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the registration templates.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RegistrationTemplates registrationTemplates) {
		entityCache.removeResult(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesImpl.class,
			registrationTemplates.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((RegistrationTemplatesModelImpl)registrationTemplates,
			true);
	}

	@Override
	public void clearCache(List<RegistrationTemplates> registrationTemplateses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RegistrationTemplates registrationTemplates : registrationTemplateses) {
			entityCache.removeResult(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
				RegistrationTemplatesImpl.class,
				registrationTemplates.getPrimaryKey());

			clearUniqueFindersCache((RegistrationTemplatesModelImpl)registrationTemplates,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		RegistrationTemplatesModelImpl registrationTemplatesModelImpl) {
		Object[] args = new Object[] {
				registrationTemplatesModelImpl.getUuid(),
				registrationTemplatesModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			registrationTemplatesModelImpl, false);

		args = new Object[] {
				registrationTemplatesModelImpl.getGroupId(),
				registrationTemplatesModelImpl.getRegistrationTemplateId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_REGID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_REGID, args,
			registrationTemplatesModelImpl, false);

		args = new Object[] {
				registrationTemplatesModelImpl.getGroupId(),
				registrationTemplatesModelImpl.getFormNo(),
				registrationTemplatesModelImpl.getGovAgencyCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_GOVCODE_FORMNO, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_GOVCODE_FORMNO, args,
			registrationTemplatesModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		RegistrationTemplatesModelImpl registrationTemplatesModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					registrationTemplatesModelImpl.getUuid(),
					registrationTemplatesModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((registrationTemplatesModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					registrationTemplatesModelImpl.getOriginalUuid(),
					registrationTemplatesModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					registrationTemplatesModelImpl.getGroupId(),
					registrationTemplatesModelImpl.getRegistrationTemplateId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_REGID, args);
		}

		if ((registrationTemplatesModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_REGID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					registrationTemplatesModelImpl.getOriginalGroupId(),
					registrationTemplatesModelImpl.getOriginalRegistrationTemplateId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_REGID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_REGID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					registrationTemplatesModelImpl.getGroupId(),
					registrationTemplatesModelImpl.getFormNo(),
					registrationTemplatesModelImpl.getGovAgencyCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GOVCODE_FORMNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GOVCODE_FORMNO, args);
		}

		if ((registrationTemplatesModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GOVCODE_FORMNO.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					registrationTemplatesModelImpl.getOriginalGroupId(),
					registrationTemplatesModelImpl.getOriginalFormNo(),
					registrationTemplatesModelImpl.getOriginalGovAgencyCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GOVCODE_FORMNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GOVCODE_FORMNO, args);
		}
	}

	/**
	 * Creates a new registration templates with the primary key. Does not add the registration templates to the database.
	 *
	 * @param registrationTemplateId the primary key for the new registration templates
	 * @return the new registration templates
	 */
	@Override
	public RegistrationTemplates create(long registrationTemplateId) {
		RegistrationTemplates registrationTemplates = new RegistrationTemplatesImpl();

		registrationTemplates.setNew(true);
		registrationTemplates.setPrimaryKey(registrationTemplateId);

		String uuid = PortalUUIDUtil.generate();

		registrationTemplates.setUuid(uuid);

		return registrationTemplates;
	}

	/**
	 * Removes the registration templates with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param registrationTemplateId the primary key of the registration templates
	 * @return the registration templates that was removed
	 * @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	 */
	@Override
	public RegistrationTemplates remove(long registrationTemplateId)
		throws NoSuchRegistrationTemplatesException {
		return remove((Serializable)registrationTemplateId);
	}

	/**
	 * Removes the registration templates with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the registration templates
	 * @return the registration templates that was removed
	 * @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	 */
	@Override
	public RegistrationTemplates remove(Serializable primaryKey)
		throws NoSuchRegistrationTemplatesException {
		Session session = null;

		try {
			session = openSession();

			RegistrationTemplates registrationTemplates = (RegistrationTemplates)session.get(RegistrationTemplatesImpl.class,
					primaryKey);

			if (registrationTemplates == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRegistrationTemplatesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(registrationTemplates);
		}
		catch (NoSuchRegistrationTemplatesException nsee) {
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
	protected RegistrationTemplates removeImpl(
		RegistrationTemplates registrationTemplates) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(registrationTemplates)) {
				registrationTemplates = (RegistrationTemplates)session.get(RegistrationTemplatesImpl.class,
						registrationTemplates.getPrimaryKeyObj());
			}

			if (registrationTemplates != null) {
				session.delete(registrationTemplates);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (registrationTemplates != null) {
			clearCache(registrationTemplates);
		}

		return registrationTemplates;
	}

	@Override
	public RegistrationTemplates updateImpl(
		RegistrationTemplates registrationTemplates) {
		boolean isNew = registrationTemplates.isNew();

		if (!(registrationTemplates instanceof RegistrationTemplatesModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(registrationTemplates.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(registrationTemplates);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in registrationTemplates proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom RegistrationTemplates implementation " +
				registrationTemplates.getClass());
		}

		RegistrationTemplatesModelImpl registrationTemplatesModelImpl = (RegistrationTemplatesModelImpl)registrationTemplates;

		if (Validator.isNull(registrationTemplates.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			registrationTemplates.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (registrationTemplates.getCreateDate() == null)) {
			if (serviceContext == null) {
				registrationTemplates.setCreateDate(now);
			}
			else {
				registrationTemplates.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!registrationTemplatesModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				registrationTemplates.setModifiedDate(now);
			}
			else {
				registrationTemplates.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (registrationTemplates.isNew()) {
				session.save(registrationTemplates);

				registrationTemplates.setNew(false);
			}
			else {
				registrationTemplates = (RegistrationTemplates)session.merge(registrationTemplates);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!RegistrationTemplatesModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					registrationTemplatesModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					registrationTemplatesModelImpl.getGroupId(),
					registrationTemplatesModelImpl.getFormNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FNO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FNO,
				args);

			args = new Object[] {
					registrationTemplatesModelImpl.getGroupId(),
					registrationTemplatesModelImpl.getGovAgencyCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GOVCODE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GOVCODE,
				args);

			args = new Object[] { registrationTemplatesModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((registrationTemplatesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationTemplatesModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { registrationTemplatesModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((registrationTemplatesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationTemplatesModelImpl.getOriginalGroupId(),
						registrationTemplatesModelImpl.getOriginalFormNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FNO,
					args);

				args = new Object[] {
						registrationTemplatesModelImpl.getGroupId(),
						registrationTemplatesModelImpl.getFormNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FNO,
					args);
			}

			if ((registrationTemplatesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GOVCODE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationTemplatesModelImpl.getOriginalGroupId(),
						registrationTemplatesModelImpl.getOriginalGovAgencyCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GOVCODE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GOVCODE,
					args);

				args = new Object[] {
						registrationTemplatesModelImpl.getGroupId(),
						registrationTemplatesModelImpl.getGovAgencyCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GOVCODE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GOVCODE,
					args);
			}

			if ((registrationTemplatesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						registrationTemplatesModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { registrationTemplatesModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
			RegistrationTemplatesImpl.class,
			registrationTemplates.getPrimaryKey(), registrationTemplates, false);

		clearUniqueFindersCache(registrationTemplatesModelImpl, false);
		cacheUniqueFindersCache(registrationTemplatesModelImpl);

		registrationTemplates.resetOriginalValues();

		return registrationTemplates;
	}

	/**
	 * Returns the registration templates with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the registration templates
	 * @return the registration templates
	 * @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	 */
	@Override
	public RegistrationTemplates findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRegistrationTemplatesException {
		RegistrationTemplates registrationTemplates = fetchByPrimaryKey(primaryKey);

		if (registrationTemplates == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRegistrationTemplatesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return registrationTemplates;
	}

	/**
	 * Returns the registration templates with the primary key or throws a {@link NoSuchRegistrationTemplatesException} if it could not be found.
	 *
	 * @param registrationTemplateId the primary key of the registration templates
	 * @return the registration templates
	 * @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	 */
	@Override
	public RegistrationTemplates findByPrimaryKey(long registrationTemplateId)
		throws NoSuchRegistrationTemplatesException {
		return findByPrimaryKey((Serializable)registrationTemplateId);
	}

	/**
	 * Returns the registration templates with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the registration templates
	 * @return the registration templates, or <code>null</code> if a registration templates with the primary key could not be found
	 */
	@Override
	public RegistrationTemplates fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
				RegistrationTemplatesImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		RegistrationTemplates registrationTemplates = (RegistrationTemplates)serializable;

		if (registrationTemplates == null) {
			Session session = null;

			try {
				session = openSession();

				registrationTemplates = (RegistrationTemplates)session.get(RegistrationTemplatesImpl.class,
						primaryKey);

				if (registrationTemplates != null) {
					cacheResult(registrationTemplates);
				}
				else {
					entityCache.putResult(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
						RegistrationTemplatesImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
					RegistrationTemplatesImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return registrationTemplates;
	}

	/**
	 * Returns the registration templates with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param registrationTemplateId the primary key of the registration templates
	 * @return the registration templates, or <code>null</code> if a registration templates with the primary key could not be found
	 */
	@Override
	public RegistrationTemplates fetchByPrimaryKey(long registrationTemplateId) {
		return fetchByPrimaryKey((Serializable)registrationTemplateId);
	}

	@Override
	public Map<Serializable, RegistrationTemplates> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, RegistrationTemplates> map = new HashMap<Serializable, RegistrationTemplates>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			RegistrationTemplates registrationTemplates = fetchByPrimaryKey(primaryKey);

			if (registrationTemplates != null) {
				map.put(primaryKey, registrationTemplates);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
					RegistrationTemplatesImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (RegistrationTemplates)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_REGISTRATIONTEMPLATES_WHERE_PKS_IN);

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

			for (RegistrationTemplates registrationTemplates : (List<RegistrationTemplates>)q.list()) {
				map.put(registrationTemplates.getPrimaryKeyObj(),
					registrationTemplates);

				cacheResult(registrationTemplates);

				uncachedPrimaryKeys.remove(registrationTemplates.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RegistrationTemplatesModelImpl.ENTITY_CACHE_ENABLED,
					RegistrationTemplatesImpl.class, primaryKey, nullModel);
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
	 * Returns all the registration templateses.
	 *
	 * @return the registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration templateses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @return the range of registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration templateses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findAll(int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration templateses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration templateses
	 * @param end the upper bound of the range of registration templateses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of registration templateses
	 */
	@Override
	public List<RegistrationTemplates> findAll(int start, int end,
		OrderByComparator<RegistrationTemplates> orderByComparator,
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

		List<RegistrationTemplates> list = null;

		if (retrieveFromCache) {
			list = (List<RegistrationTemplates>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REGISTRATIONTEMPLATES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REGISTRATIONTEMPLATES;

				if (pagination) {
					sql = sql.concat(RegistrationTemplatesModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RegistrationTemplates>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RegistrationTemplates>)QueryUtil.list(q,
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
	 * Removes all the registration templateses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RegistrationTemplates registrationTemplates : findAll()) {
			remove(registrationTemplates);
		}
	}

	/**
	 * Returns the number of registration templateses.
	 *
	 * @return the number of registration templateses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REGISTRATIONTEMPLATES);

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
		return RegistrationTemplatesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the registration templates persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RegistrationTemplatesImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_REGISTRATIONTEMPLATES = "SELECT registrationTemplates FROM RegistrationTemplates registrationTemplates";
	private static final String _SQL_SELECT_REGISTRATIONTEMPLATES_WHERE_PKS_IN = "SELECT registrationTemplates FROM RegistrationTemplates registrationTemplates WHERE registrationTemplateId IN (";
	private static final String _SQL_SELECT_REGISTRATIONTEMPLATES_WHERE = "SELECT registrationTemplates FROM RegistrationTemplates registrationTemplates WHERE ";
	private static final String _SQL_COUNT_REGISTRATIONTEMPLATES = "SELECT COUNT(registrationTemplates) FROM RegistrationTemplates registrationTemplates";
	private static final String _SQL_COUNT_REGISTRATIONTEMPLATES_WHERE = "SELECT COUNT(registrationTemplates) FROM RegistrationTemplates registrationTemplates WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "registrationTemplates.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RegistrationTemplates exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No RegistrationTemplates exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(RegistrationTemplatesPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}