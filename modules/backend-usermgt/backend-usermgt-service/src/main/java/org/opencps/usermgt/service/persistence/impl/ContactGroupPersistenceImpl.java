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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.usermgt.exception.NoSuchContactGroupException;
import org.opencps.usermgt.model.ContactGroup;
import org.opencps.usermgt.model.impl.ContactGroupImpl;
import org.opencps.usermgt.model.impl.ContactGroupModelImpl;
import org.opencps.usermgt.service.persistence.ContactGroupPersistence;

import java.io.Serializable;

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
 * The persistence implementation for the contact group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see ContactGroupPersistence
 * @see org.opencps.usermgt.service.persistence.ContactGroupUtil
 * @generated
 */
@ProviderType
public class ContactGroupPersistenceImpl extends BasePersistenceImpl<ContactGroup>
	implements ContactGroupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ContactGroupUtil} to access the contact group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ContactGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
			ContactGroupModelImpl.FINDER_CACHE_ENABLED, ContactGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
			ContactGroupModelImpl.FINDER_CACHE_ENABLED, ContactGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
			ContactGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
			ContactGroupModelImpl.FINDER_CACHE_ENABLED, ContactGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
			ContactGroupModelImpl.FINDER_CACHE_ENABLED, ContactGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ContactGroupModelImpl.UUID_COLUMN_BITMASK |
			ContactGroupModelImpl.GROUPNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
			ContactGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the contact groups where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching contact groups
	 */
	@Override
	public List<ContactGroup> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contact groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contact groups
	 * @param end the upper bound of the range of contact groups (not inclusive)
	 * @return the range of matching contact groups
	 */
	@Override
	public List<ContactGroup> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contact groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contact groups
	 * @param end the upper bound of the range of contact groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact groups
	 */
	@Override
	public List<ContactGroup> findByUuid(String uuid, int start, int end,
		OrderByComparator<ContactGroup> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contact groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contact groups
	 * @param end the upper bound of the range of contact groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching contact groups
	 */
	@Override
	public List<ContactGroup> findByUuid(String uuid, int start, int end,
		OrderByComparator<ContactGroup> orderByComparator,
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

		List<ContactGroup> list = null;

		if (retrieveFromCache) {
			list = (List<ContactGroup>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContactGroup contactGroup : list) {
					if (!Objects.equals(uuid, contactGroup.getUuid())) {
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

			query.append(_SQL_SELECT_CONTACTGROUP_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
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
				query.append(ContactGroupModelImpl.ORDER_BY_JPQL);
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
					list = (List<ContactGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContactGroup>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first contact group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact group
	 * @throws NoSuchContactGroupException if a matching contact group could not be found
	 */
	@Override
	public ContactGroup findByUuid_First(String uuid,
		OrderByComparator<ContactGroup> orderByComparator)
		throws NoSuchContactGroupException {
		ContactGroup contactGroup = fetchByUuid_First(uuid, orderByComparator);

		if (contactGroup != null) {
			return contactGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContactGroupException(msg.toString());
	}

	/**
	 * Returns the first contact group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact group, or <code>null</code> if a matching contact group could not be found
	 */
	@Override
	public ContactGroup fetchByUuid_First(String uuid,
		OrderByComparator<ContactGroup> orderByComparator) {
		List<ContactGroup> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contact group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact group
	 * @throws NoSuchContactGroupException if a matching contact group could not be found
	 */
	@Override
	public ContactGroup findByUuid_Last(String uuid,
		OrderByComparator<ContactGroup> orderByComparator)
		throws NoSuchContactGroupException {
		ContactGroup contactGroup = fetchByUuid_Last(uuid, orderByComparator);

		if (contactGroup != null) {
			return contactGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContactGroupException(msg.toString());
	}

	/**
	 * Returns the last contact group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact group, or <code>null</code> if a matching contact group could not be found
	 */
	@Override
	public ContactGroup fetchByUuid_Last(String uuid,
		OrderByComparator<ContactGroup> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ContactGroup> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contact groups before and after the current contact group in the ordered set where uuid = &#63;.
	 *
	 * @param contactGroupId the primary key of the current contact group
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact group
	 * @throws NoSuchContactGroupException if a contact group with the primary key could not be found
	 */
	@Override
	public ContactGroup[] findByUuid_PrevAndNext(long contactGroupId,
		String uuid, OrderByComparator<ContactGroup> orderByComparator)
		throws NoSuchContactGroupException {
		ContactGroup contactGroup = findByPrimaryKey(contactGroupId);

		Session session = null;

		try {
			session = openSession();

			ContactGroup[] array = new ContactGroupImpl[3];

			array[0] = getByUuid_PrevAndNext(session, contactGroup, uuid,
					orderByComparator, true);

			array[1] = contactGroup;

			array[2] = getByUuid_PrevAndNext(session, contactGroup, uuid,
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

	protected ContactGroup getByUuid_PrevAndNext(Session session,
		ContactGroup contactGroup, String uuid,
		OrderByComparator<ContactGroup> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTACTGROUP_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
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
			query.append(ContactGroupModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(contactGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContactGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contact groups where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ContactGroup contactGroup : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(contactGroup);
		}
	}

	/**
	 * Returns the number of contact groups where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching contact groups
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTACTGROUP_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "contactGroup.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "contactGroup.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(contactGroup.uuid IS NULL OR contactGroup.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
			ContactGroupModelImpl.FINDER_CACHE_ENABLED, ContactGroupImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ContactGroupModelImpl.UUID_COLUMN_BITMASK |
			ContactGroupModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
			ContactGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the contact group where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchContactGroupException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching contact group
	 * @throws NoSuchContactGroupException if a matching contact group could not be found
	 */
	@Override
	public ContactGroup findByUUID_G(String uuid, long groupId)
		throws NoSuchContactGroupException {
		ContactGroup contactGroup = fetchByUUID_G(uuid, groupId);

		if (contactGroup == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchContactGroupException(msg.toString());
		}

		return contactGroup;
	}

	/**
	 * Returns the contact group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching contact group, or <code>null</code> if a matching contact group could not be found
	 */
	@Override
	public ContactGroup fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the contact group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching contact group, or <code>null</code> if a matching contact group could not be found
	 */
	@Override
	public ContactGroup fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ContactGroup) {
			ContactGroup contactGroup = (ContactGroup)result;

			if (!Objects.equals(uuid, contactGroup.getUuid()) ||
					(groupId != contactGroup.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CONTACTGROUP_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
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

				List<ContactGroup> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ContactGroup contactGroup = list.get(0);

					result = contactGroup;

					cacheResult(contactGroup);

					if ((contactGroup.getUuid() == null) ||
							!contactGroup.getUuid().equals(uuid) ||
							(contactGroup.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, contactGroup);
					}
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
			return (ContactGroup)result;
		}
	}

	/**
	 * Removes the contact group where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the contact group that was removed
	 */
	@Override
	public ContactGroup removeByUUID_G(String uuid, long groupId)
		throws NoSuchContactGroupException {
		ContactGroup contactGroup = findByUUID_G(uuid, groupId);

		return remove(contactGroup);
	}

	/**
	 * Returns the number of contact groups where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching contact groups
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CONTACTGROUP_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "contactGroup.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "contactGroup.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(contactGroup.uuid IS NULL OR contactGroup.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "contactGroup.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
			ContactGroupModelImpl.FINDER_CACHE_ENABLED, ContactGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
			ContactGroupModelImpl.FINDER_CACHE_ENABLED, ContactGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ContactGroupModelImpl.UUID_COLUMN_BITMASK |
			ContactGroupModelImpl.COMPANYID_COLUMN_BITMASK |
			ContactGroupModelImpl.GROUPNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
			ContactGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the contact groups where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching contact groups
	 */
	@Override
	public List<ContactGroup> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contact groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of contact groups
	 * @param end the upper bound of the range of contact groups (not inclusive)
	 * @return the range of matching contact groups
	 */
	@Override
	public List<ContactGroup> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contact groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of contact groups
	 * @param end the upper bound of the range of contact groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact groups
	 */
	@Override
	public List<ContactGroup> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ContactGroup> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contact groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of contact groups
	 * @param end the upper bound of the range of contact groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching contact groups
	 */
	@Override
	public List<ContactGroup> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ContactGroup> orderByComparator,
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

		List<ContactGroup> list = null;

		if (retrieveFromCache) {
			list = (List<ContactGroup>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContactGroup contactGroup : list) {
					if (!Objects.equals(uuid, contactGroup.getUuid()) ||
							(companyId != contactGroup.getCompanyId())) {
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

			query.append(_SQL_SELECT_CONTACTGROUP_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
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
				query.append(ContactGroupModelImpl.ORDER_BY_JPQL);
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
					list = (List<ContactGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContactGroup>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first contact group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact group
	 * @throws NoSuchContactGroupException if a matching contact group could not be found
	 */
	@Override
	public ContactGroup findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ContactGroup> orderByComparator)
		throws NoSuchContactGroupException {
		ContactGroup contactGroup = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (contactGroup != null) {
			return contactGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContactGroupException(msg.toString());
	}

	/**
	 * Returns the first contact group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact group, or <code>null</code> if a matching contact group could not be found
	 */
	@Override
	public ContactGroup fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ContactGroup> orderByComparator) {
		List<ContactGroup> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contact group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact group
	 * @throws NoSuchContactGroupException if a matching contact group could not be found
	 */
	@Override
	public ContactGroup findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ContactGroup> orderByComparator)
		throws NoSuchContactGroupException {
		ContactGroup contactGroup = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (contactGroup != null) {
			return contactGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContactGroupException(msg.toString());
	}

	/**
	 * Returns the last contact group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact group, or <code>null</code> if a matching contact group could not be found
	 */
	@Override
	public ContactGroup fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ContactGroup> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ContactGroup> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contact groups before and after the current contact group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param contactGroupId the primary key of the current contact group
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact group
	 * @throws NoSuchContactGroupException if a contact group with the primary key could not be found
	 */
	@Override
	public ContactGroup[] findByUuid_C_PrevAndNext(long contactGroupId,
		String uuid, long companyId,
		OrderByComparator<ContactGroup> orderByComparator)
		throws NoSuchContactGroupException {
		ContactGroup contactGroup = findByPrimaryKey(contactGroupId);

		Session session = null;

		try {
			session = openSession();

			ContactGroup[] array = new ContactGroupImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, contactGroup, uuid,
					companyId, orderByComparator, true);

			array[1] = contactGroup;

			array[2] = getByUuid_C_PrevAndNext(session, contactGroup, uuid,
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

	protected ContactGroup getByUuid_C_PrevAndNext(Session session,
		ContactGroup contactGroup, String uuid, long companyId,
		OrderByComparator<ContactGroup> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CONTACTGROUP_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
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
			query.append(ContactGroupModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(contactGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContactGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contact groups where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ContactGroup contactGroup : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(contactGroup);
		}
	}

	/**
	 * Returns the number of contact groups where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching contact groups
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CONTACTGROUP_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "contactGroup.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "contactGroup.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(contactGroup.uuid IS NULL OR contactGroup.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "contactGroup.companyId = ?";

	public ContactGroupPersistenceImpl() {
		setModelClass(ContactGroup.class);
	}

	/**
	 * Caches the contact group in the entity cache if it is enabled.
	 *
	 * @param contactGroup the contact group
	 */
	@Override
	public void cacheResult(ContactGroup contactGroup) {
		entityCache.putResult(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
			ContactGroupImpl.class, contactGroup.getPrimaryKey(), contactGroup);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { contactGroup.getUuid(), contactGroup.getGroupId() },
			contactGroup);

		contactGroup.resetOriginalValues();
	}

	/**
	 * Caches the contact groups in the entity cache if it is enabled.
	 *
	 * @param contactGroups the contact groups
	 */
	@Override
	public void cacheResult(List<ContactGroup> contactGroups) {
		for (ContactGroup contactGroup : contactGroups) {
			if (entityCache.getResult(
						ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
						ContactGroupImpl.class, contactGroup.getPrimaryKey()) == null) {
				cacheResult(contactGroup);
			}
			else {
				contactGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all contact groups.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ContactGroupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the contact group.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ContactGroup contactGroup) {
		entityCache.removeResult(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
			ContactGroupImpl.class, contactGroup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ContactGroupModelImpl)contactGroup, true);
	}

	@Override
	public void clearCache(List<ContactGroup> contactGroups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ContactGroup contactGroup : contactGroups) {
			entityCache.removeResult(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
				ContactGroupImpl.class, contactGroup.getPrimaryKey());

			clearUniqueFindersCache((ContactGroupModelImpl)contactGroup, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ContactGroupModelImpl contactGroupModelImpl) {
		Object[] args = new Object[] {
				contactGroupModelImpl.getUuid(),
				contactGroupModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			contactGroupModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ContactGroupModelImpl contactGroupModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					contactGroupModelImpl.getUuid(),
					contactGroupModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((contactGroupModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					contactGroupModelImpl.getOriginalUuid(),
					contactGroupModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new contact group with the primary key. Does not add the contact group to the database.
	 *
	 * @param contactGroupId the primary key for the new contact group
	 * @return the new contact group
	 */
	@Override
	public ContactGroup create(long contactGroupId) {
		ContactGroup contactGroup = new ContactGroupImpl();

		contactGroup.setNew(true);
		contactGroup.setPrimaryKey(contactGroupId);

		String uuid = PortalUUIDUtil.generate();

		contactGroup.setUuid(uuid);

		contactGroup.setCompanyId(companyProvider.getCompanyId());

		return contactGroup;
	}

	/**
	 * Removes the contact group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactGroupId the primary key of the contact group
	 * @return the contact group that was removed
	 * @throws NoSuchContactGroupException if a contact group with the primary key could not be found
	 */
	@Override
	public ContactGroup remove(long contactGroupId)
		throws NoSuchContactGroupException {
		return remove((Serializable)contactGroupId);
	}

	/**
	 * Removes the contact group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the contact group
	 * @return the contact group that was removed
	 * @throws NoSuchContactGroupException if a contact group with the primary key could not be found
	 */
	@Override
	public ContactGroup remove(Serializable primaryKey)
		throws NoSuchContactGroupException {
		Session session = null;

		try {
			session = openSession();

			ContactGroup contactGroup = (ContactGroup)session.get(ContactGroupImpl.class,
					primaryKey);

			if (contactGroup == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContactGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(contactGroup);
		}
		catch (NoSuchContactGroupException nsee) {
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
	protected ContactGroup removeImpl(ContactGroup contactGroup) {
		contactGroup = toUnwrappedModel(contactGroup);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(contactGroup)) {
				contactGroup = (ContactGroup)session.get(ContactGroupImpl.class,
						contactGroup.getPrimaryKeyObj());
			}

			if (contactGroup != null) {
				session.delete(contactGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (contactGroup != null) {
			clearCache(contactGroup);
		}

		return contactGroup;
	}

	@Override
	public ContactGroup updateImpl(ContactGroup contactGroup) {
		contactGroup = toUnwrappedModel(contactGroup);

		boolean isNew = contactGroup.isNew();

		ContactGroupModelImpl contactGroupModelImpl = (ContactGroupModelImpl)contactGroup;

		if (Validator.isNull(contactGroup.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			contactGroup.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (contactGroup.getCreateDate() == null)) {
			if (serviceContext == null) {
				contactGroup.setCreateDate(now);
			}
			else {
				contactGroup.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!contactGroupModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				contactGroup.setModifiedDate(now);
			}
			else {
				contactGroup.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (contactGroup.isNew()) {
				session.save(contactGroup);

				contactGroup.setNew(false);
			}
			else {
				contactGroup = (ContactGroup)session.merge(contactGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ContactGroupModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((contactGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contactGroupModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { contactGroupModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((contactGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contactGroupModelImpl.getOriginalUuid(),
						contactGroupModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						contactGroupModelImpl.getUuid(),
						contactGroupModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
			ContactGroupImpl.class, contactGroup.getPrimaryKey(), contactGroup,
			false);

		clearUniqueFindersCache(contactGroupModelImpl, false);
		cacheUniqueFindersCache(contactGroupModelImpl);

		contactGroup.resetOriginalValues();

		return contactGroup;
	}

	protected ContactGroup toUnwrappedModel(ContactGroup contactGroup) {
		if (contactGroup instanceof ContactGroupImpl) {
			return contactGroup;
		}

		ContactGroupImpl contactGroupImpl = new ContactGroupImpl();

		contactGroupImpl.setNew(contactGroup.isNew());
		contactGroupImpl.setPrimaryKey(contactGroup.getPrimaryKey());

		contactGroupImpl.setUuid(contactGroup.getUuid());
		contactGroupImpl.setContactGroupId(contactGroup.getContactGroupId());
		contactGroupImpl.setGroupId(contactGroup.getGroupId());
		contactGroupImpl.setCompanyId(contactGroup.getCompanyId());
		contactGroupImpl.setUserId(contactGroup.getUserId());
		contactGroupImpl.setUserName(contactGroup.getUserName());
		contactGroupImpl.setCreateDate(contactGroup.getCreateDate());
		contactGroupImpl.setModifiedDate(contactGroup.getModifiedDate());
		contactGroupImpl.setGroupName(contactGroup.getGroupName());
		contactGroupImpl.setContactList(contactGroup.getContactList());
		contactGroupImpl.setShared(contactGroup.getShared());

		return contactGroupImpl;
	}

	/**
	 * Returns the contact group with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the contact group
	 * @return the contact group
	 * @throws NoSuchContactGroupException if a contact group with the primary key could not be found
	 */
	@Override
	public ContactGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContactGroupException {
		ContactGroup contactGroup = fetchByPrimaryKey(primaryKey);

		if (contactGroup == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContactGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return contactGroup;
	}

	/**
	 * Returns the contact group with the primary key or throws a {@link NoSuchContactGroupException} if it could not be found.
	 *
	 * @param contactGroupId the primary key of the contact group
	 * @return the contact group
	 * @throws NoSuchContactGroupException if a contact group with the primary key could not be found
	 */
	@Override
	public ContactGroup findByPrimaryKey(long contactGroupId)
		throws NoSuchContactGroupException {
		return findByPrimaryKey((Serializable)contactGroupId);
	}

	/**
	 * Returns the contact group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the contact group
	 * @return the contact group, or <code>null</code> if a contact group with the primary key could not be found
	 */
	@Override
	public ContactGroup fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
				ContactGroupImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ContactGroup contactGroup = (ContactGroup)serializable;

		if (contactGroup == null) {
			Session session = null;

			try {
				session = openSession();

				contactGroup = (ContactGroup)session.get(ContactGroupImpl.class,
						primaryKey);

				if (contactGroup != null) {
					cacheResult(contactGroup);
				}
				else {
					entityCache.putResult(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
						ContactGroupImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
					ContactGroupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return contactGroup;
	}

	/**
	 * Returns the contact group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactGroupId the primary key of the contact group
	 * @return the contact group, or <code>null</code> if a contact group with the primary key could not be found
	 */
	@Override
	public ContactGroup fetchByPrimaryKey(long contactGroupId) {
		return fetchByPrimaryKey((Serializable)contactGroupId);
	}

	@Override
	public Map<Serializable, ContactGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ContactGroup> map = new HashMap<Serializable, ContactGroup>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ContactGroup contactGroup = fetchByPrimaryKey(primaryKey);

			if (contactGroup != null) {
				map.put(primaryKey, contactGroup);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
					ContactGroupImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ContactGroup)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CONTACTGROUP_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (ContactGroup contactGroup : (List<ContactGroup>)q.list()) {
				map.put(contactGroup.getPrimaryKeyObj(), contactGroup);

				cacheResult(contactGroup);

				uncachedPrimaryKeys.remove(contactGroup.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ContactGroupModelImpl.ENTITY_CACHE_ENABLED,
					ContactGroupImpl.class, primaryKey, nullModel);
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
	 * Returns all the contact groups.
	 *
	 * @return the contact groups
	 */
	@Override
	public List<ContactGroup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contact groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact groups
	 * @param end the upper bound of the range of contact groups (not inclusive)
	 * @return the range of contact groups
	 */
	@Override
	public List<ContactGroup> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the contact groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact groups
	 * @param end the upper bound of the range of contact groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contact groups
	 */
	@Override
	public List<ContactGroup> findAll(int start, int end,
		OrderByComparator<ContactGroup> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contact groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact groups
	 * @param end the upper bound of the range of contact groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of contact groups
	 */
	@Override
	public List<ContactGroup> findAll(int start, int end,
		OrderByComparator<ContactGroup> orderByComparator,
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

		List<ContactGroup> list = null;

		if (retrieveFromCache) {
			list = (List<ContactGroup>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CONTACTGROUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONTACTGROUP;

				if (pagination) {
					sql = sql.concat(ContactGroupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ContactGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContactGroup>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the contact groups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ContactGroup contactGroup : findAll()) {
			remove(contactGroup);
		}
	}

	/**
	 * Returns the number of contact groups.
	 *
	 * @return the number of contact groups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONTACTGROUP);

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
		return ContactGroupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the contact group persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ContactGroupImpl.class.getName());
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
	private static final String _SQL_SELECT_CONTACTGROUP = "SELECT contactGroup FROM ContactGroup contactGroup";
	private static final String _SQL_SELECT_CONTACTGROUP_WHERE_PKS_IN = "SELECT contactGroup FROM ContactGroup contactGroup WHERE contactGroupId IN (";
	private static final String _SQL_SELECT_CONTACTGROUP_WHERE = "SELECT contactGroup FROM ContactGroup contactGroup WHERE ";
	private static final String _SQL_COUNT_CONTACTGROUP = "SELECT COUNT(contactGroup) FROM ContactGroup contactGroup";
	private static final String _SQL_COUNT_CONTACTGROUP_WHERE = "SELECT COUNT(contactGroup) FROM ContactGroup contactGroup WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "contactGroup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContactGroup exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContactGroup exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ContactGroupPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}