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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.usermgt.exception.NoSuchSavePickFieldException;
import org.opencps.usermgt.model.SavePickField;
import org.opencps.usermgt.model.impl.SavePickFieldImpl;
import org.opencps.usermgt.model.impl.SavePickFieldModelImpl;
import org.opencps.usermgt.service.persistence.SavePickFieldPersistence;

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
 * The persistence implementation for the save pick field service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see SavePickFieldPersistence
 * @see org.opencps.usermgt.service.persistence.SavePickFieldUtil
 * @generated
 */
@ProviderType
public class SavePickFieldPersistenceImpl extends BasePersistenceImpl<SavePickField>
	implements SavePickFieldPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SavePickFieldUtil} to access the save pick field persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SavePickFieldImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
			SavePickFieldModelImpl.FINDER_CACHE_ENABLED,
			SavePickFieldImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
			SavePickFieldModelImpl.FINDER_CACHE_ENABLED,
			SavePickFieldImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
			SavePickFieldModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
			SavePickFieldModelImpl.FINDER_CACHE_ENABLED,
			SavePickFieldImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
			SavePickFieldModelImpl.FINDER_CACHE_ENABLED,
			SavePickFieldImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			SavePickFieldModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
			SavePickFieldModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the save pick fields where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching save pick fields
	 */
	@Override
	public List<SavePickField> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the save pick fields where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of save pick fields
	 * @param end the upper bound of the range of save pick fields (not inclusive)
	 * @return the range of matching save pick fields
	 */
	@Override
	public List<SavePickField> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the save pick fields where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of save pick fields
	 * @param end the upper bound of the range of save pick fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching save pick fields
	 */
	@Override
	public List<SavePickField> findByUuid(String uuid, int start, int end,
		OrderByComparator<SavePickField> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the save pick fields where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of save pick fields
	 * @param end the upper bound of the range of save pick fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching save pick fields
	 */
	@Override
	public List<SavePickField> findByUuid(String uuid, int start, int end,
		OrderByComparator<SavePickField> orderByComparator,
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

		List<SavePickField> list = null;

		if (retrieveFromCache) {
			list = (List<SavePickField>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SavePickField savePickField : list) {
					if (!Objects.equals(uuid, savePickField.getUuid())) {
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

			query.append(_SQL_SELECT_SAVEPICKFIELD_WHERE);

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
				query.append(SavePickFieldModelImpl.ORDER_BY_JPQL);
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
					list = (List<SavePickField>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SavePickField>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first save pick field in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching save pick field
	 * @throws NoSuchSavePickFieldException if a matching save pick field could not be found
	 */
	@Override
	public SavePickField findByUuid_First(String uuid,
		OrderByComparator<SavePickField> orderByComparator)
		throws NoSuchSavePickFieldException {
		SavePickField savePickField = fetchByUuid_First(uuid, orderByComparator);

		if (savePickField != null) {
			return savePickField;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSavePickFieldException(msg.toString());
	}

	/**
	 * Returns the first save pick field in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching save pick field, or <code>null</code> if a matching save pick field could not be found
	 */
	@Override
	public SavePickField fetchByUuid_First(String uuid,
		OrderByComparator<SavePickField> orderByComparator) {
		List<SavePickField> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last save pick field in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching save pick field
	 * @throws NoSuchSavePickFieldException if a matching save pick field could not be found
	 */
	@Override
	public SavePickField findByUuid_Last(String uuid,
		OrderByComparator<SavePickField> orderByComparator)
		throws NoSuchSavePickFieldException {
		SavePickField savePickField = fetchByUuid_Last(uuid, orderByComparator);

		if (savePickField != null) {
			return savePickField;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSavePickFieldException(msg.toString());
	}

	/**
	 * Returns the last save pick field in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching save pick field, or <code>null</code> if a matching save pick field could not be found
	 */
	@Override
	public SavePickField fetchByUuid_Last(String uuid,
		OrderByComparator<SavePickField> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SavePickField> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the save pick fields before and after the current save pick field in the ordered set where uuid = &#63;.
	 *
	 * @param fieldPickId the primary key of the current save pick field
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next save pick field
	 * @throws NoSuchSavePickFieldException if a save pick field with the primary key could not be found
	 */
	@Override
	public SavePickField[] findByUuid_PrevAndNext(long fieldPickId,
		String uuid, OrderByComparator<SavePickField> orderByComparator)
		throws NoSuchSavePickFieldException {
		SavePickField savePickField = findByPrimaryKey(fieldPickId);

		Session session = null;

		try {
			session = openSession();

			SavePickField[] array = new SavePickFieldImpl[3];

			array[0] = getByUuid_PrevAndNext(session, savePickField, uuid,
					orderByComparator, true);

			array[1] = savePickField;

			array[2] = getByUuid_PrevAndNext(session, savePickField, uuid,
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

	protected SavePickField getByUuid_PrevAndNext(Session session,
		SavePickField savePickField, String uuid,
		OrderByComparator<SavePickField> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SAVEPICKFIELD_WHERE);

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
			query.append(SavePickFieldModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(savePickField);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SavePickField> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the save pick fields where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (SavePickField savePickField : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(savePickField);
		}
	}

	/**
	 * Returns the number of save pick fields where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching save pick fields
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SAVEPICKFIELD_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "savePickField.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "savePickField.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(savePickField.uuid IS NULL OR savePickField.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
			SavePickFieldModelImpl.FINDER_CACHE_ENABLED,
			SavePickFieldImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			SavePickFieldModelImpl.UUID_COLUMN_BITMASK |
			SavePickFieldModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
			SavePickFieldModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the save pick field where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSavePickFieldException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching save pick field
	 * @throws NoSuchSavePickFieldException if a matching save pick field could not be found
	 */
	@Override
	public SavePickField findByUUID_G(String uuid, long groupId)
		throws NoSuchSavePickFieldException {
		SavePickField savePickField = fetchByUUID_G(uuid, groupId);

		if (savePickField == null) {
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

			throw new NoSuchSavePickFieldException(msg.toString());
		}

		return savePickField;
	}

	/**
	 * Returns the save pick field where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching save pick field, or <code>null</code> if a matching save pick field could not be found
	 */
	@Override
	public SavePickField fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the save pick field where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching save pick field, or <code>null</code> if a matching save pick field could not be found
	 */
	@Override
	public SavePickField fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof SavePickField) {
			SavePickField savePickField = (SavePickField)result;

			if (!Objects.equals(uuid, savePickField.getUuid()) ||
					(groupId != savePickField.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SAVEPICKFIELD_WHERE);

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

				List<SavePickField> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					SavePickField savePickField = list.get(0);

					result = savePickField;

					cacheResult(savePickField);
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
			return (SavePickField)result;
		}
	}

	/**
	 * Removes the save pick field where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the save pick field that was removed
	 */
	@Override
	public SavePickField removeByUUID_G(String uuid, long groupId)
		throws NoSuchSavePickFieldException {
		SavePickField savePickField = findByUUID_G(uuid, groupId);

		return remove(savePickField);
	}

	/**
	 * Returns the number of save pick fields where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching save pick fields
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SAVEPICKFIELD_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "savePickField.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "savePickField.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(savePickField.uuid IS NULL OR savePickField.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "savePickField.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_U_CLASSPK = new FinderPath(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
			SavePickFieldModelImpl.FINDER_CACHE_ENABLED,
			SavePickFieldImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_U_ClassPK",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			SavePickFieldModelImpl.GROUPID_COLUMN_BITMASK |
			SavePickFieldModelImpl.USERID_COLUMN_BITMASK |
			SavePickFieldModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U_CLASSPK = new FinderPath(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
			SavePickFieldModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U_ClassPK",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the save pick field where groupId = &#63; and userId = &#63; and classPK = &#63; or throws a {@link NoSuchSavePickFieldException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classPK the class pk
	 * @return the matching save pick field
	 * @throws NoSuchSavePickFieldException if a matching save pick field could not be found
	 */
	@Override
	public SavePickField findByG_U_ClassPK(long groupId, long userId,
		String classPK) throws NoSuchSavePickFieldException {
		SavePickField savePickField = fetchByG_U_ClassPK(groupId, userId,
				classPK);

		if (savePickField == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSavePickFieldException(msg.toString());
		}

		return savePickField;
	}

	/**
	 * Returns the save pick field where groupId = &#63; and userId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classPK the class pk
	 * @return the matching save pick field, or <code>null</code> if a matching save pick field could not be found
	 */
	@Override
	public SavePickField fetchByG_U_ClassPK(long groupId, long userId,
		String classPK) {
		return fetchByG_U_ClassPK(groupId, userId, classPK, true);
	}

	/**
	 * Returns the save pick field where groupId = &#63; and userId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classPK the class pk
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching save pick field, or <code>null</code> if a matching save pick field could not be found
	 */
	@Override
	public SavePickField fetchByG_U_ClassPK(long groupId, long userId,
		String classPK, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, userId, classPK };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_U_CLASSPK,
					finderArgs, this);
		}

		if (result instanceof SavePickField) {
			SavePickField savePickField = (SavePickField)result;

			if ((groupId != savePickField.getGroupId()) ||
					(userId != savePickField.getUserId()) ||
					!Objects.equals(classPK, savePickField.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_SAVEPICKFIELD_WHERE);

			query.append(_FINDER_COLUMN_G_U_CLASSPK_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_CLASSPK_USERID_2);

			boolean bindClassPK = false;

			if (classPK == null) {
				query.append(_FINDER_COLUMN_G_U_CLASSPK_CLASSPK_1);
			}
			else if (classPK.equals("")) {
				query.append(_FINDER_COLUMN_G_U_CLASSPK_CLASSPK_3);
			}
			else {
				bindClassPK = true;

				query.append(_FINDER_COLUMN_G_U_CLASSPK_CLASSPK_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (bindClassPK) {
					qPos.add(classPK);
				}

				List<SavePickField> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_U_CLASSPK,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"SavePickFieldPersistenceImpl.fetchByG_U_ClassPK(long, long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SavePickField savePickField = list.get(0);

					result = savePickField;

					cacheResult(savePickField);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_U_CLASSPK,
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
			return (SavePickField)result;
		}
	}

	/**
	 * Removes the save pick field where groupId = &#63; and userId = &#63; and classPK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classPK the class pk
	 * @return the save pick field that was removed
	 */
	@Override
	public SavePickField removeByG_U_ClassPK(long groupId, long userId,
		String classPK) throws NoSuchSavePickFieldException {
		SavePickField savePickField = findByG_U_ClassPK(groupId, userId, classPK);

		return remove(savePickField);
	}

	/**
	 * Returns the number of save pick fields where groupId = &#63; and userId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classPK the class pk
	 * @return the number of matching save pick fields
	 */
	@Override
	public int countByG_U_ClassPK(long groupId, long userId, String classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_U_CLASSPK;

		Object[] finderArgs = new Object[] { groupId, userId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SAVEPICKFIELD_WHERE);

			query.append(_FINDER_COLUMN_G_U_CLASSPK_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_CLASSPK_USERID_2);

			boolean bindClassPK = false;

			if (classPK == null) {
				query.append(_FINDER_COLUMN_G_U_CLASSPK_CLASSPK_1);
			}
			else if (classPK.equals("")) {
				query.append(_FINDER_COLUMN_G_U_CLASSPK_CLASSPK_3);
			}
			else {
				bindClassPK = true;

				query.append(_FINDER_COLUMN_G_U_CLASSPK_CLASSPK_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (bindClassPK) {
					qPos.add(classPK);
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

	private static final String _FINDER_COLUMN_G_U_CLASSPK_GROUPID_2 = "savePickField.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_CLASSPK_USERID_2 = "savePickField.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_CLASSPK_CLASSPK_1 = "savePickField.classPK IS NULL";
	private static final String _FINDER_COLUMN_G_U_CLASSPK_CLASSPK_2 = "savePickField.classPK = ?";
	private static final String _FINDER_COLUMN_G_U_CLASSPK_CLASSPK_3 = "(savePickField.classPK IS NULL OR savePickField.classPK = '')";

	public SavePickFieldPersistenceImpl() {
		setModelClass(SavePickField.class);

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
	 * Caches the save pick field in the entity cache if it is enabled.
	 *
	 * @param savePickField the save pick field
	 */
	@Override
	public void cacheResult(SavePickField savePickField) {
		entityCache.putResult(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
			SavePickFieldImpl.class, savePickField.getPrimaryKey(),
			savePickField);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { savePickField.getUuid(), savePickField.getGroupId() },
			savePickField);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_U_CLASSPK,
			new Object[] {
				savePickField.getGroupId(), savePickField.getUserId(),
				savePickField.getClassPK()
			}, savePickField);

		savePickField.resetOriginalValues();
	}

	/**
	 * Caches the save pick fields in the entity cache if it is enabled.
	 *
	 * @param savePickFields the save pick fields
	 */
	@Override
	public void cacheResult(List<SavePickField> savePickFields) {
		for (SavePickField savePickField : savePickFields) {
			if (entityCache.getResult(
						SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
						SavePickFieldImpl.class, savePickField.getPrimaryKey()) == null) {
				cacheResult(savePickField);
			}
			else {
				savePickField.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all save pick fields.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SavePickFieldImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the save pick field.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SavePickField savePickField) {
		entityCache.removeResult(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
			SavePickFieldImpl.class, savePickField.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SavePickFieldModelImpl)savePickField, true);
	}

	@Override
	public void clearCache(List<SavePickField> savePickFields) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SavePickField savePickField : savePickFields) {
			entityCache.removeResult(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
				SavePickFieldImpl.class, savePickField.getPrimaryKey());

			clearUniqueFindersCache((SavePickFieldModelImpl)savePickField, true);
		}
	}

	protected void cacheUniqueFindersCache(
		SavePickFieldModelImpl savePickFieldModelImpl) {
		Object[] args = new Object[] {
				savePickFieldModelImpl.getUuid(),
				savePickFieldModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			savePickFieldModelImpl, false);

		args = new Object[] {
				savePickFieldModelImpl.getGroupId(),
				savePickFieldModelImpl.getUserId(),
				savePickFieldModelImpl.getClassPK()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_U_CLASSPK, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_U_CLASSPK, args,
			savePickFieldModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SavePickFieldModelImpl savePickFieldModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					savePickFieldModelImpl.getUuid(),
					savePickFieldModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((savePickFieldModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					savePickFieldModelImpl.getOriginalUuid(),
					savePickFieldModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					savePickFieldModelImpl.getGroupId(),
					savePickFieldModelImpl.getUserId(),
					savePickFieldModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U_CLASSPK, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_U_CLASSPK, args);
		}

		if ((savePickFieldModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_U_CLASSPK.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					savePickFieldModelImpl.getOriginalGroupId(),
					savePickFieldModelImpl.getOriginalUserId(),
					savePickFieldModelImpl.getOriginalClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U_CLASSPK, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_U_CLASSPK, args);
		}
	}

	/**
	 * Creates a new save pick field with the primary key. Does not add the save pick field to the database.
	 *
	 * @param fieldPickId the primary key for the new save pick field
	 * @return the new save pick field
	 */
	@Override
	public SavePickField create(long fieldPickId) {
		SavePickField savePickField = new SavePickFieldImpl();

		savePickField.setNew(true);
		savePickField.setPrimaryKey(fieldPickId);

		String uuid = PortalUUIDUtil.generate();

		savePickField.setUuid(uuid);

		return savePickField;
	}

	/**
	 * Removes the save pick field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fieldPickId the primary key of the save pick field
	 * @return the save pick field that was removed
	 * @throws NoSuchSavePickFieldException if a save pick field with the primary key could not be found
	 */
	@Override
	public SavePickField remove(long fieldPickId)
		throws NoSuchSavePickFieldException {
		return remove((Serializable)fieldPickId);
	}

	/**
	 * Removes the save pick field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the save pick field
	 * @return the save pick field that was removed
	 * @throws NoSuchSavePickFieldException if a save pick field with the primary key could not be found
	 */
	@Override
	public SavePickField remove(Serializable primaryKey)
		throws NoSuchSavePickFieldException {
		Session session = null;

		try {
			session = openSession();

			SavePickField savePickField = (SavePickField)session.get(SavePickFieldImpl.class,
					primaryKey);

			if (savePickField == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSavePickFieldException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(savePickField);
		}
		catch (NoSuchSavePickFieldException nsee) {
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
	protected SavePickField removeImpl(SavePickField savePickField) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(savePickField)) {
				savePickField = (SavePickField)session.get(SavePickFieldImpl.class,
						savePickField.getPrimaryKeyObj());
			}

			if (savePickField != null) {
				session.delete(savePickField);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (savePickField != null) {
			clearCache(savePickField);
		}

		return savePickField;
	}

	@Override
	public SavePickField updateImpl(SavePickField savePickField) {
		boolean isNew = savePickField.isNew();

		if (!(savePickField instanceof SavePickFieldModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(savePickField.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(savePickField);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in savePickField proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SavePickField implementation " +
				savePickField.getClass());
		}

		SavePickFieldModelImpl savePickFieldModelImpl = (SavePickFieldModelImpl)savePickField;

		if (Validator.isNull(savePickField.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			savePickField.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (savePickField.getCreateDate() == null)) {
			if (serviceContext == null) {
				savePickField.setCreateDate(now);
			}
			else {
				savePickField.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!savePickFieldModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				savePickField.setModifiedDate(now);
			}
			else {
				savePickField.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (savePickField.isNew()) {
				session.save(savePickField);

				savePickField.setNew(false);
			}
			else {
				savePickField = (SavePickField)session.merge(savePickField);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SavePickFieldModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { savePickFieldModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((savePickFieldModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						savePickFieldModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { savePickFieldModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		entityCache.putResult(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
			SavePickFieldImpl.class, savePickField.getPrimaryKey(),
			savePickField, false);

		clearUniqueFindersCache(savePickFieldModelImpl, false);
		cacheUniqueFindersCache(savePickFieldModelImpl);

		savePickField.resetOriginalValues();

		return savePickField;
	}

	/**
	 * Returns the save pick field with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the save pick field
	 * @return the save pick field
	 * @throws NoSuchSavePickFieldException if a save pick field with the primary key could not be found
	 */
	@Override
	public SavePickField findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSavePickFieldException {
		SavePickField savePickField = fetchByPrimaryKey(primaryKey);

		if (savePickField == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSavePickFieldException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return savePickField;
	}

	/**
	 * Returns the save pick field with the primary key or throws a {@link NoSuchSavePickFieldException} if it could not be found.
	 *
	 * @param fieldPickId the primary key of the save pick field
	 * @return the save pick field
	 * @throws NoSuchSavePickFieldException if a save pick field with the primary key could not be found
	 */
	@Override
	public SavePickField findByPrimaryKey(long fieldPickId)
		throws NoSuchSavePickFieldException {
		return findByPrimaryKey((Serializable)fieldPickId);
	}

	/**
	 * Returns the save pick field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the save pick field
	 * @return the save pick field, or <code>null</code> if a save pick field with the primary key could not be found
	 */
	@Override
	public SavePickField fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
				SavePickFieldImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SavePickField savePickField = (SavePickField)serializable;

		if (savePickField == null) {
			Session session = null;

			try {
				session = openSession();

				savePickField = (SavePickField)session.get(SavePickFieldImpl.class,
						primaryKey);

				if (savePickField != null) {
					cacheResult(savePickField);
				}
				else {
					entityCache.putResult(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
						SavePickFieldImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
					SavePickFieldImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return savePickField;
	}

	/**
	 * Returns the save pick field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fieldPickId the primary key of the save pick field
	 * @return the save pick field, or <code>null</code> if a save pick field with the primary key could not be found
	 */
	@Override
	public SavePickField fetchByPrimaryKey(long fieldPickId) {
		return fetchByPrimaryKey((Serializable)fieldPickId);
	}

	@Override
	public Map<Serializable, SavePickField> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SavePickField> map = new HashMap<Serializable, SavePickField>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SavePickField savePickField = fetchByPrimaryKey(primaryKey);

			if (savePickField != null) {
				map.put(primaryKey, savePickField);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
					SavePickFieldImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SavePickField)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SAVEPICKFIELD_WHERE_PKS_IN);

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

			for (SavePickField savePickField : (List<SavePickField>)q.list()) {
				map.put(savePickField.getPrimaryKeyObj(), savePickField);

				cacheResult(savePickField);

				uncachedPrimaryKeys.remove(savePickField.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SavePickFieldModelImpl.ENTITY_CACHE_ENABLED,
					SavePickFieldImpl.class, primaryKey, nullModel);
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
	 * Returns all the save pick fields.
	 *
	 * @return the save pick fields
	 */
	@Override
	public List<SavePickField> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the save pick fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of save pick fields
	 * @param end the upper bound of the range of save pick fields (not inclusive)
	 * @return the range of save pick fields
	 */
	@Override
	public List<SavePickField> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the save pick fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of save pick fields
	 * @param end the upper bound of the range of save pick fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of save pick fields
	 */
	@Override
	public List<SavePickField> findAll(int start, int end,
		OrderByComparator<SavePickField> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the save pick fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of save pick fields
	 * @param end the upper bound of the range of save pick fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of save pick fields
	 */
	@Override
	public List<SavePickField> findAll(int start, int end,
		OrderByComparator<SavePickField> orderByComparator,
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

		List<SavePickField> list = null;

		if (retrieveFromCache) {
			list = (List<SavePickField>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SAVEPICKFIELD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SAVEPICKFIELD;

				if (pagination) {
					sql = sql.concat(SavePickFieldModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SavePickField>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SavePickField>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the save pick fields from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SavePickField savePickField : findAll()) {
			remove(savePickField);
		}
	}

	/**
	 * Returns the number of save pick fields.
	 *
	 * @return the number of save pick fields
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SAVEPICKFIELD);

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
		return SavePickFieldModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the save pick field persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SavePickFieldImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SAVEPICKFIELD = "SELECT savePickField FROM SavePickField savePickField";
	private static final String _SQL_SELECT_SAVEPICKFIELD_WHERE_PKS_IN = "SELECT savePickField FROM SavePickField savePickField WHERE fieldPickId IN (";
	private static final String _SQL_SELECT_SAVEPICKFIELD_WHERE = "SELECT savePickField FROM SavePickField savePickField WHERE ";
	private static final String _SQL_COUNT_SAVEPICKFIELD = "SELECT COUNT(savePickField) FROM SavePickField savePickField";
	private static final String _SQL_COUNT_SAVEPICKFIELD_WHERE = "SELECT COUNT(savePickField) FROM SavePickField savePickField WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "savePickField.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SavePickField exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SavePickField exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SavePickFieldPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}