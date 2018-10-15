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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchDossierUserException;
import org.opencps.dossiermgt.model.DossierUser;
import org.opencps.dossiermgt.model.impl.DossierUserImpl;
import org.opencps.dossiermgt.model.impl.DossierUserModelImpl;
import org.opencps.dossiermgt.service.persistence.DossierUserPK;
import org.opencps.dossiermgt.service.persistence.DossierUserPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the dossier user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierUserPersistence
 * @see org.opencps.dossiermgt.service.persistence.DossierUserUtil
 * @generated
 */
@ProviderType
public class DossierUserPersistenceImpl extends BasePersistenceImpl<DossierUser>
	implements DossierUserPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DossierUserUtil} to access the dossier user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DossierUserImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserModelImpl.FINDER_CACHE_ENABLED, DossierUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserModelImpl.FINDER_CACHE_ENABLED, DossierUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserModelImpl.FINDER_CACHE_ENABLED, DossierUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserModelImpl.FINDER_CACHE_ENABLED, DossierUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DossierUserModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dossier users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dossier users
	 */
	@Override
	public List<DossierUser> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier users
	 * @param end the upper bound of the range of dossier users (not inclusive)
	 * @return the range of matching dossier users
	 */
	@Override
	public List<DossierUser> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier users
	 * @param end the upper bound of the range of dossier users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier users
	 */
	@Override
	public List<DossierUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierUser> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier users
	 * @param end the upper bound of the range of dossier users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier users
	 */
	@Override
	public List<DossierUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierUser> orderByComparator,
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

		List<DossierUser> list = null;

		if (retrieveFromCache) {
			list = (List<DossierUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierUser dossierUser : list) {
					if (!Objects.equals(uuid, dossierUser.getUuid())) {
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

			query.append(_SQL_SELECT_DOSSIERUSER_WHERE);

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
				query.append(DossierUserModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier user
	 * @throws NoSuchDossierUserException if a matching dossier user could not be found
	 */
	@Override
	public DossierUser findByUuid_First(String uuid,
		OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException {
		DossierUser dossierUser = fetchByUuid_First(uuid, orderByComparator);

		if (dossierUser != null) {
			return dossierUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierUserException(msg.toString());
	}

	/**
	 * Returns the first dossier user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier user, or <code>null</code> if a matching dossier user could not be found
	 */
	@Override
	public DossierUser fetchByUuid_First(String uuid,
		OrderByComparator<DossierUser> orderByComparator) {
		List<DossierUser> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier user
	 * @throws NoSuchDossierUserException if a matching dossier user could not be found
	 */
	@Override
	public DossierUser findByUuid_Last(String uuid,
		OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException {
		DossierUser dossierUser = fetchByUuid_Last(uuid, orderByComparator);

		if (dossierUser != null) {
			return dossierUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierUserException(msg.toString());
	}

	/**
	 * Returns the last dossier user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier user, or <code>null</code> if a matching dossier user could not be found
	 */
	@Override
	public DossierUser fetchByUuid_Last(String uuid,
		OrderByComparator<DossierUser> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DossierUser> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier users before and after the current dossier user in the ordered set where uuid = &#63;.
	 *
	 * @param dossierUserPK the primary key of the current dossier user
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier user
	 * @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	 */
	@Override
	public DossierUser[] findByUuid_PrevAndNext(DossierUserPK dossierUserPK,
		String uuid, OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException {
		DossierUser dossierUser = findByPrimaryKey(dossierUserPK);

		Session session = null;

		try {
			session = openSession();

			DossierUser[] array = new DossierUserImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dossierUser, uuid,
					orderByComparator, true);

			array[1] = dossierUser;

			array[2] = getByUuid_PrevAndNext(session, dossierUser, uuid,
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

	protected DossierUser getByUuid_PrevAndNext(Session session,
		DossierUser dossierUser, String uuid,
		OrderByComparator<DossierUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERUSER_WHERE);

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
			query.append(DossierUserModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier users where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DossierUser dossierUser : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossierUser);
		}
	}

	/**
	 * Returns the number of dossier users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dossier users
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERUSER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dossierUser.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dossierUser.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dossierUser.uuid IS NULL OR dossierUser.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID = new FinderPath(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserModelImpl.FINDER_CACHE_ENABLED, DossierUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID = new FinderPath(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserModelImpl.FINDER_CACHE_ENABLED, DossierUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDID",
			new String[] { Long.class.getName() },
			DossierUserModelImpl.DOSSIERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID = new FinderPath(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dossier users where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @return the matching dossier users
	 */
	@Override
	public List<DossierUser> findByDID(long dossierId) {
		return findByDID(dossierId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier users where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier users
	 * @param end the upper bound of the range of dossier users (not inclusive)
	 * @return the range of matching dossier users
	 */
	@Override
	public List<DossierUser> findByDID(long dossierId, int start, int end) {
		return findByDID(dossierId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier users where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier users
	 * @param end the upper bound of the range of dossier users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier users
	 */
	@Override
	public List<DossierUser> findByDID(long dossierId, int start, int end,
		OrderByComparator<DossierUser> orderByComparator) {
		return findByDID(dossierId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier users where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier users
	 * @param end the upper bound of the range of dossier users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier users
	 */
	@Override
	public List<DossierUser> findByDID(long dossierId, int start, int end,
		OrderByComparator<DossierUser> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID;
			finderArgs = new Object[] { dossierId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID;
			finderArgs = new Object[] { dossierId, start, end, orderByComparator };
		}

		List<DossierUser> list = null;

		if (retrieveFromCache) {
			list = (List<DossierUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierUser dossierUser : list) {
					if ((dossierId != dossierUser.getDossierId())) {
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

			query.append(_SQL_SELECT_DOSSIERUSER_WHERE);

			query.append(_FINDER_COLUMN_DID_DOSSIERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierUserModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (!pagination) {
					list = (List<DossierUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier user in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier user
	 * @throws NoSuchDossierUserException if a matching dossier user could not be found
	 */
	@Override
	public DossierUser findByDID_First(long dossierId,
		OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException {
		DossierUser dossierUser = fetchByDID_First(dossierId, orderByComparator);

		if (dossierUser != null) {
			return dossierUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchDossierUserException(msg.toString());
	}

	/**
	 * Returns the first dossier user in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier user, or <code>null</code> if a matching dossier user could not be found
	 */
	@Override
	public DossierUser fetchByDID_First(long dossierId,
		OrderByComparator<DossierUser> orderByComparator) {
		List<DossierUser> list = findByDID(dossierId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier user in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier user
	 * @throws NoSuchDossierUserException if a matching dossier user could not be found
	 */
	@Override
	public DossierUser findByDID_Last(long dossierId,
		OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException {
		DossierUser dossierUser = fetchByDID_Last(dossierId, orderByComparator);

		if (dossierUser != null) {
			return dossierUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchDossierUserException(msg.toString());
	}

	/**
	 * Returns the last dossier user in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier user, or <code>null</code> if a matching dossier user could not be found
	 */
	@Override
	public DossierUser fetchByDID_Last(long dossierId,
		OrderByComparator<DossierUser> orderByComparator) {
		int count = countByDID(dossierId);

		if (count == 0) {
			return null;
		}

		List<DossierUser> list = findByDID(dossierId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier users before and after the current dossier user in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierUserPK the primary key of the current dossier user
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier user
	 * @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	 */
	@Override
	public DossierUser[] findByDID_PrevAndNext(DossierUserPK dossierUserPK,
		long dossierId, OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException {
		DossierUser dossierUser = findByPrimaryKey(dossierUserPK);

		Session session = null;

		try {
			session = openSession();

			DossierUser[] array = new DossierUserImpl[3];

			array[0] = getByDID_PrevAndNext(session, dossierUser, dossierId,
					orderByComparator, true);

			array[1] = dossierUser;

			array[2] = getByDID_PrevAndNext(session, dossierUser, dossierId,
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

	protected DossierUser getByDID_PrevAndNext(Session session,
		DossierUser dossierUser, long dossierId,
		OrderByComparator<DossierUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERUSER_WHERE);

		query.append(_FINDER_COLUMN_DID_DOSSIERID_2);

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
			query.append(DossierUserModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier users where dossierId = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 */
	@Override
	public void removeByDID(long dossierId) {
		for (DossierUser dossierUser : findByDID(dossierId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossierUser);
		}
	}

	/**
	 * Returns the number of dossier users where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @return the number of matching dossier users
	 */
	@Override
	public int countByDID(long dossierId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID;

		Object[] finderArgs = new Object[] { dossierId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERUSER_WHERE);

			query.append(_FINDER_COLUMN_DID_DOSSIERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

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

	private static final String _FINDER_COLUMN_DID_DOSSIERID_2 = "dossierUser.id.dossierId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DID_UID = new FinderPath(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserModelImpl.FINDER_CACHE_ENABLED, DossierUserImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDID_UID",
			new String[] { Long.class.getName(), Long.class.getName() },
			DossierUserModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierUserModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_UID = new FinderPath(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_UID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier user where dossierId = &#63; and userId = &#63; or throws a {@link NoSuchDossierUserException} if it could not be found.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @return the matching dossier user
	 * @throws NoSuchDossierUserException if a matching dossier user could not be found
	 */
	@Override
	public DossierUser findByDID_UID(long dossierId, long userId)
		throws NoSuchDossierUserException {
		DossierUser dossierUser = fetchByDID_UID(dossierId, userId);

		if (dossierUser == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossierId=");
			msg.append(dossierId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierUserException(msg.toString());
		}

		return dossierUser;
	}

	/**
	 * Returns the dossier user where dossierId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @return the matching dossier user, or <code>null</code> if a matching dossier user could not be found
	 */
	@Override
	public DossierUser fetchByDID_UID(long dossierId, long userId) {
		return fetchByDID_UID(dossierId, userId, true);
	}

	/**
	 * Returns the dossier user where dossierId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier user, or <code>null</code> if a matching dossier user could not be found
	 */
	@Override
	public DossierUser fetchByDID_UID(long dossierId, long userId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossierId, userId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DID_UID,
					finderArgs, this);
		}

		if (result instanceof DossierUser) {
			DossierUser dossierUser = (DossierUser)result;

			if ((dossierId != dossierUser.getDossierId()) ||
					(userId != dossierUser.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERUSER_WHERE);

			query.append(_FINDER_COLUMN_DID_UID_DOSSIERID_2);

			query.append(_FINDER_COLUMN_DID_UID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				qPos.add(userId);

				List<DossierUser> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DID_UID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierUserPersistenceImpl.fetchByDID_UID(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierUser dossierUser = list.get(0);

					result = dossierUser;

					cacheResult(dossierUser);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_UID,
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
			return (DossierUser)result;
		}
	}

	/**
	 * Removes the dossier user where dossierId = &#63; and userId = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @return the dossier user that was removed
	 */
	@Override
	public DossierUser removeByDID_UID(long dossierId, long userId)
		throws NoSuchDossierUserException {
		DossierUser dossierUser = findByDID_UID(dossierId, userId);

		return remove(dossierUser);
	}

	/**
	 * Returns the number of dossier users where dossierId = &#63; and userId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param userId the user ID
	 * @return the number of matching dossier users
	 */
	@Override
	public int countByDID_UID(long dossierId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_UID;

		Object[] finderArgs = new Object[] { dossierId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERUSER_WHERE);

			query.append(_FINDER_COLUMN_DID_UID_DOSSIERID_2);

			query.append(_FINDER_COLUMN_DID_UID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

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

	private static final String _FINDER_COLUMN_DID_UID_DOSSIERID_2 = "dossierUser.id.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_UID_USERID_2 = "dossierUser.id.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UID = new FinderPath(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserModelImpl.FINDER_CACHE_ENABLED, DossierUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UID = new FinderPath(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserModelImpl.FINDER_CACHE_ENABLED, DossierUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUID",
			new String[] { Long.class.getName() },
			DossierUserModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UID = new FinderPath(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dossier users where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching dossier users
	 */
	@Override
	public List<DossierUser> findByUID(long userId) {
		return findByUID(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier users where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of dossier users
	 * @param end the upper bound of the range of dossier users (not inclusive)
	 * @return the range of matching dossier users
	 */
	@Override
	public List<DossierUser> findByUID(long userId, int start, int end) {
		return findByUID(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier users where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of dossier users
	 * @param end the upper bound of the range of dossier users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier users
	 */
	@Override
	public List<DossierUser> findByUID(long userId, int start, int end,
		OrderByComparator<DossierUser> orderByComparator) {
		return findByUID(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier users where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of dossier users
	 * @param end the upper bound of the range of dossier users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier users
	 */
	@Override
	public List<DossierUser> findByUID(long userId, int start, int end,
		OrderByComparator<DossierUser> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<DossierUser> list = null;

		if (retrieveFromCache) {
			list = (List<DossierUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierUser dossierUser : list) {
					if ((userId != dossierUser.getUserId())) {
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

			query.append(_SQL_SELECT_DOSSIERUSER_WHERE);

			query.append(_FINDER_COLUMN_UID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierUserModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<DossierUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier user
	 * @throws NoSuchDossierUserException if a matching dossier user could not be found
	 */
	@Override
	public DossierUser findByUID_First(long userId,
		OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException {
		DossierUser dossierUser = fetchByUID_First(userId, orderByComparator);

		if (dossierUser != null) {
			return dossierUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchDossierUserException(msg.toString());
	}

	/**
	 * Returns the first dossier user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier user, or <code>null</code> if a matching dossier user could not be found
	 */
	@Override
	public DossierUser fetchByUID_First(long userId,
		OrderByComparator<DossierUser> orderByComparator) {
		List<DossierUser> list = findByUID(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier user
	 * @throws NoSuchDossierUserException if a matching dossier user could not be found
	 */
	@Override
	public DossierUser findByUID_Last(long userId,
		OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException {
		DossierUser dossierUser = fetchByUID_Last(userId, orderByComparator);

		if (dossierUser != null) {
			return dossierUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchDossierUserException(msg.toString());
	}

	/**
	 * Returns the last dossier user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier user, or <code>null</code> if a matching dossier user could not be found
	 */
	@Override
	public DossierUser fetchByUID_Last(long userId,
		OrderByComparator<DossierUser> orderByComparator) {
		int count = countByUID(userId);

		if (count == 0) {
			return null;
		}

		List<DossierUser> list = findByUID(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier users before and after the current dossier user in the ordered set where userId = &#63;.
	 *
	 * @param dossierUserPK the primary key of the current dossier user
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier user
	 * @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	 */
	@Override
	public DossierUser[] findByUID_PrevAndNext(DossierUserPK dossierUserPK,
		long userId, OrderByComparator<DossierUser> orderByComparator)
		throws NoSuchDossierUserException {
		DossierUser dossierUser = findByPrimaryKey(dossierUserPK);

		Session session = null;

		try {
			session = openSession();

			DossierUser[] array = new DossierUserImpl[3];

			array[0] = getByUID_PrevAndNext(session, dossierUser, userId,
					orderByComparator, true);

			array[1] = dossierUser;

			array[2] = getByUID_PrevAndNext(session, dossierUser, userId,
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

	protected DossierUser getByUID_PrevAndNext(Session session,
		DossierUser dossierUser, long userId,
		OrderByComparator<DossierUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERUSER_WHERE);

		query.append(_FINDER_COLUMN_UID_USERID_2);

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
			query.append(DossierUserModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier users where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUID(long userId) {
		for (DossierUser dossierUser : findByUID(userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossierUser);
		}
	}

	/**
	 * Returns the number of dossier users where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching dossier users
	 */
	@Override
	public int countByUID(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERUSER_WHERE);

			query.append(_FINDER_COLUMN_UID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_UID_USERID_2 = "dossierUser.id.userId = ?";

	public DossierUserPersistenceImpl() {
		setModelClass(DossierUser.class);

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
	 * Caches the dossier user in the entity cache if it is enabled.
	 *
	 * @param dossierUser the dossier user
	 */
	@Override
	public void cacheResult(DossierUser dossierUser) {
		entityCache.putResult(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserImpl.class, dossierUser.getPrimaryKey(), dossierUser);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DID_UID,
			new Object[] { dossierUser.getDossierId(), dossierUser.getUserId() },
			dossierUser);

		dossierUser.resetOriginalValues();
	}

	/**
	 * Caches the dossier users in the entity cache if it is enabled.
	 *
	 * @param dossierUsers the dossier users
	 */
	@Override
	public void cacheResult(List<DossierUser> dossierUsers) {
		for (DossierUser dossierUser : dossierUsers) {
			if (entityCache.getResult(
						DossierUserModelImpl.ENTITY_CACHE_ENABLED,
						DossierUserImpl.class, dossierUser.getPrimaryKey()) == null) {
				cacheResult(dossierUser);
			}
			else {
				dossierUser.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dossier users.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DossierUserImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dossier user.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DossierUser dossierUser) {
		entityCache.removeResult(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserImpl.class, dossierUser.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DossierUserModelImpl)dossierUser, true);
	}

	@Override
	public void clearCache(List<DossierUser> dossierUsers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DossierUser dossierUser : dossierUsers) {
			entityCache.removeResult(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
				DossierUserImpl.class, dossierUser.getPrimaryKey());

			clearUniqueFindersCache((DossierUserModelImpl)dossierUser, true);
		}
	}

	protected void cacheUniqueFindersCache(
		DossierUserModelImpl dossierUserModelImpl) {
		Object[] args = new Object[] {
				dossierUserModelImpl.getDossierId(),
				dossierUserModelImpl.getUserId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_DID_UID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DID_UID, args,
			dossierUserModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DossierUserModelImpl dossierUserModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierUserModelImpl.getDossierId(),
					dossierUserModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_UID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_UID, args);
		}

		if ((dossierUserModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DID_UID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierUserModelImpl.getOriginalDossierId(),
					dossierUserModelImpl.getOriginalUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_UID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_UID, args);
		}
	}

	/**
	 * Creates a new dossier user with the primary key. Does not add the dossier user to the database.
	 *
	 * @param dossierUserPK the primary key for the new dossier user
	 * @return the new dossier user
	 */
	@Override
	public DossierUser create(DossierUserPK dossierUserPK) {
		DossierUser dossierUser = new DossierUserImpl();

		dossierUser.setNew(true);
		dossierUser.setPrimaryKey(dossierUserPK);

		String uuid = PortalUUIDUtil.generate();

		dossierUser.setUuid(uuid);

		return dossierUser;
	}

	/**
	 * Removes the dossier user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dossierUserPK the primary key of the dossier user
	 * @return the dossier user that was removed
	 * @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	 */
	@Override
	public DossierUser remove(DossierUserPK dossierUserPK)
		throws NoSuchDossierUserException {
		return remove((Serializable)dossierUserPK);
	}

	/**
	 * Removes the dossier user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dossier user
	 * @return the dossier user that was removed
	 * @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	 */
	@Override
	public DossierUser remove(Serializable primaryKey)
		throws NoSuchDossierUserException {
		Session session = null;

		try {
			session = openSession();

			DossierUser dossierUser = (DossierUser)session.get(DossierUserImpl.class,
					primaryKey);

			if (dossierUser == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDossierUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dossierUser);
		}
		catch (NoSuchDossierUserException nsee) {
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
	protected DossierUser removeImpl(DossierUser dossierUser) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dossierUser)) {
				dossierUser = (DossierUser)session.get(DossierUserImpl.class,
						dossierUser.getPrimaryKeyObj());
			}

			if (dossierUser != null) {
				session.delete(dossierUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dossierUser != null) {
			clearCache(dossierUser);
		}

		return dossierUser;
	}

	@Override
	public DossierUser updateImpl(DossierUser dossierUser) {
		boolean isNew = dossierUser.isNew();

		if (!(dossierUser instanceof DossierUserModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dossierUser.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dossierUser);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dossierUser proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DossierUser implementation " +
				dossierUser.getClass());
		}

		DossierUserModelImpl dossierUserModelImpl = (DossierUserModelImpl)dossierUser;

		if (Validator.isNull(dossierUser.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dossierUser.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (dossierUser.isNew()) {
				session.save(dossierUser);

				dossierUser.setNew(false);
			}
			else {
				dossierUser = (DossierUser)session.merge(dossierUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DossierUserModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dossierUserModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { dossierUserModelImpl.getDossierId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID,
				args);

			args = new Object[] { dossierUserModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dossierUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierUserModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dossierUserModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dossierUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierUserModelImpl.getOriginalDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID,
					args);

				args = new Object[] { dossierUserModelImpl.getDossierId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID,
					args);
			}

			if ((dossierUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierUserModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UID,
					args);

				args = new Object[] { dossierUserModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UID,
					args);
			}
		}

		entityCache.putResult(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierUserImpl.class, dossierUser.getPrimaryKey(), dossierUser,
			false);

		clearUniqueFindersCache(dossierUserModelImpl, false);
		cacheUniqueFindersCache(dossierUserModelImpl);

		dossierUser.resetOriginalValues();

		return dossierUser;
	}

	/**
	 * Returns the dossier user with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier user
	 * @return the dossier user
	 * @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	 */
	@Override
	public DossierUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDossierUserException {
		DossierUser dossierUser = fetchByPrimaryKey(primaryKey);

		if (dossierUser == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDossierUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dossierUser;
	}

	/**
	 * Returns the dossier user with the primary key or throws a {@link NoSuchDossierUserException} if it could not be found.
	 *
	 * @param dossierUserPK the primary key of the dossier user
	 * @return the dossier user
	 * @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	 */
	@Override
	public DossierUser findByPrimaryKey(DossierUserPK dossierUserPK)
		throws NoSuchDossierUserException {
		return findByPrimaryKey((Serializable)dossierUserPK);
	}

	/**
	 * Returns the dossier user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier user
	 * @return the dossier user, or <code>null</code> if a dossier user with the primary key could not be found
	 */
	@Override
	public DossierUser fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
				DossierUserImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DossierUser dossierUser = (DossierUser)serializable;

		if (dossierUser == null) {
			Session session = null;

			try {
				session = openSession();

				dossierUser = (DossierUser)session.get(DossierUserImpl.class,
						primaryKey);

				if (dossierUser != null) {
					cacheResult(dossierUser);
				}
				else {
					entityCache.putResult(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
						DossierUserImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DossierUserModelImpl.ENTITY_CACHE_ENABLED,
					DossierUserImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dossierUser;
	}

	/**
	 * Returns the dossier user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dossierUserPK the primary key of the dossier user
	 * @return the dossier user, or <code>null</code> if a dossier user with the primary key could not be found
	 */
	@Override
	public DossierUser fetchByPrimaryKey(DossierUserPK dossierUserPK) {
		return fetchByPrimaryKey((Serializable)dossierUserPK);
	}

	@Override
	public Map<Serializable, DossierUser> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DossierUser> map = new HashMap<Serializable, DossierUser>();

		for (Serializable primaryKey : primaryKeys) {
			DossierUser dossierUser = fetchByPrimaryKey(primaryKey);

			if (dossierUser != null) {
				map.put(primaryKey, dossierUser);
			}
		}

		return map;
	}

	/**
	 * Returns all the dossier users.
	 *
	 * @return the dossier users
	 */
	@Override
	public List<DossierUser> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier users
	 * @param end the upper bound of the range of dossier users (not inclusive)
	 * @return the range of dossier users
	 */
	@Override
	public List<DossierUser> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier users
	 * @param end the upper bound of the range of dossier users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dossier users
	 */
	@Override
	public List<DossierUser> findAll(int start, int end,
		OrderByComparator<DossierUser> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier users
	 * @param end the upper bound of the range of dossier users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dossier users
	 */
	@Override
	public List<DossierUser> findAll(int start, int end,
		OrderByComparator<DossierUser> orderByComparator,
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

		List<DossierUser> list = null;

		if (retrieveFromCache) {
			list = (List<DossierUser>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOSSIERUSER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOSSIERUSER;

				if (pagination) {
					sql = sql.concat(DossierUserModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DossierUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierUser>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the dossier users from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DossierUser dossierUser : findAll()) {
			remove(dossierUser);
		}
	}

	/**
	 * Returns the number of dossier users.
	 *
	 * @return the number of dossier users
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOSSIERUSER);

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
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DossierUserModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dossier user persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DossierUserImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_DOSSIERUSER = "SELECT dossierUser FROM DossierUser dossierUser";
	private static final String _SQL_SELECT_DOSSIERUSER_WHERE = "SELECT dossierUser FROM DossierUser dossierUser WHERE ";
	private static final String _SQL_COUNT_DOSSIERUSER = "SELECT COUNT(dossierUser) FROM DossierUser dossierUser";
	private static final String _SQL_COUNT_DOSSIERUSER_WHERE = "SELECT COUNT(dossierUser) FROM DossierUser dossierUser WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dossierUser.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DossierUser exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DossierUser exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DossierUserPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(new String[] {
				"dossierId", "userId"
			});
}