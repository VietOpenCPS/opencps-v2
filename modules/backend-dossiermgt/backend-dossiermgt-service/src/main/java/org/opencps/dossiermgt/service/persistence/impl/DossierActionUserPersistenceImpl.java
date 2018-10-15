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

import org.opencps.dossiermgt.exception.NoSuchDossierActionUserException;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.impl.DossierActionUserImpl;
import org.opencps.dossiermgt.model.impl.DossierActionUserModelImpl;
import org.opencps.dossiermgt.service.persistence.DossierActionUserPK;
import org.opencps.dossiermgt.service.persistence.DossierActionUserPersistence;

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
 * The persistence implementation for the dossier action user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierActionUserPersistence
 * @see org.opencps.dossiermgt.service.persistence.DossierActionUserUtil
 * @generated
 */
@ProviderType
public class DossierActionUserPersistenceImpl extends BasePersistenceImpl<DossierActionUser>
	implements DossierActionUserPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DossierActionUserUtil} to access the dossier action user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DossierActionUserImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED,
			DossierActionUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED,
			DossierActionUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED,
			DossierActionUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED,
			DossierActionUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DossierActionUserModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dossier action users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier action users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @return the range of matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier action users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier action users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator,
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

		List<DossierActionUser> list = null;

		if (retrieveFromCache) {
			list = (List<DossierActionUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierActionUser dossierActionUser : list) {
					if (!Objects.equals(uuid, dossierActionUser.getUuid())) {
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

			query.append(_SQL_SELECT_DOSSIERACTIONUSER_WHERE);

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
				query.append(DossierActionUserModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierActionUser>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierActionUser>)QueryUtil.list(q,
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
	 * Returns the first dossier action user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action user
	 * @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser findByUuid_First(String uuid,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = fetchByUuid_First(uuid,
				orderByComparator);

		if (dossierActionUser != null) {
			return dossierActionUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierActionUserException(msg.toString());
	}

	/**
	 * Returns the first dossier action user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser fetchByUuid_First(String uuid,
		OrderByComparator<DossierActionUser> orderByComparator) {
		List<DossierActionUser> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action user
	 * @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser findByUuid_Last(String uuid,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = fetchByUuid_Last(uuid,
				orderByComparator);

		if (dossierActionUser != null) {
			return dossierActionUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierActionUserException(msg.toString());
	}

	/**
	 * Returns the last dossier action user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser fetchByUuid_Last(String uuid,
		OrderByComparator<DossierActionUser> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DossierActionUser> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier action users before and after the current dossier action user in the ordered set where uuid = &#63;.
	 *
	 * @param dossierActionUserPK the primary key of the current dossier action user
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action user
	 * @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	 */
	@Override
	public DossierActionUser[] findByUuid_PrevAndNext(
		DossierActionUserPK dossierActionUserPK, String uuid,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = findByPrimaryKey(dossierActionUserPK);

		Session session = null;

		try {
			session = openSession();

			DossierActionUser[] array = new DossierActionUserImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dossierActionUser, uuid,
					orderByComparator, true);

			array[1] = dossierActionUser;

			array[2] = getByUuid_PrevAndNext(session, dossierActionUser, uuid,
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

	protected DossierActionUser getByUuid_PrevAndNext(Session session,
		DossierActionUser dossierActionUser, String uuid,
		OrderByComparator<DossierActionUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERACTIONUSER_WHERE);

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
			query.append(DossierActionUserModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierActionUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierActionUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier action users where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DossierActionUser dossierActionUser : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierActionUser);
		}
	}

	/**
	 * Returns the number of dossier action users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dossier action users
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERACTIONUSER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dossierActionUser.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dossierActionUser.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dossierActionUser.uuid IS NULL OR dossierActionUser.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED,
			DossierActionUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED,
			DossierActionUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDID",
			new String[] { Long.class.getName() },
			DossierActionUserModelImpl.DOSSIERACTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dossier action users where dossierActionId = &#63;.
	 *
	 * @param dossierActionId the dossier action ID
	 * @return the matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByDID(long dossierActionId) {
		return findByDID(dossierActionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dossier action users where dossierActionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierActionId the dossier action ID
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @return the range of matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByDID(long dossierActionId, int start,
		int end) {
		return findByDID(dossierActionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier action users where dossierActionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierActionId the dossier action ID
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByDID(long dossierActionId, int start,
		int end, OrderByComparator<DossierActionUser> orderByComparator) {
		return findByDID(dossierActionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier action users where dossierActionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierActionId the dossier action ID
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByDID(long dossierActionId, int start,
		int end, OrderByComparator<DossierActionUser> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID;
			finderArgs = new Object[] { dossierActionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID;
			finderArgs = new Object[] {
					dossierActionId,
					
					start, end, orderByComparator
				};
		}

		List<DossierActionUser> list = null;

		if (retrieveFromCache) {
			list = (List<DossierActionUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierActionUser dossierActionUser : list) {
					if ((dossierActionId != dossierActionUser.getDossierActionId())) {
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

			query.append(_SQL_SELECT_DOSSIERACTIONUSER_WHERE);

			query.append(_FINDER_COLUMN_DID_DOSSIERACTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierActionUserModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierActionId);

				if (!pagination) {
					list = (List<DossierActionUser>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierActionUser>)QueryUtil.list(q,
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
	 * Returns the first dossier action user in the ordered set where dossierActionId = &#63;.
	 *
	 * @param dossierActionId the dossier action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action user
	 * @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser findByDID_First(long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = fetchByDID_First(dossierActionId,
				orderByComparator);

		if (dossierActionUser != null) {
			return dossierActionUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierActionId=");
		msg.append(dossierActionId);

		msg.append("}");

		throw new NoSuchDossierActionUserException(msg.toString());
	}

	/**
	 * Returns the first dossier action user in the ordered set where dossierActionId = &#63;.
	 *
	 * @param dossierActionId the dossier action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser fetchByDID_First(long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator) {
		List<DossierActionUser> list = findByDID(dossierActionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action user in the ordered set where dossierActionId = &#63;.
	 *
	 * @param dossierActionId the dossier action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action user
	 * @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser findByDID_Last(long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = fetchByDID_Last(dossierActionId,
				orderByComparator);

		if (dossierActionUser != null) {
			return dossierActionUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierActionId=");
		msg.append(dossierActionId);

		msg.append("}");

		throw new NoSuchDossierActionUserException(msg.toString());
	}

	/**
	 * Returns the last dossier action user in the ordered set where dossierActionId = &#63;.
	 *
	 * @param dossierActionId the dossier action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser fetchByDID_Last(long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator) {
		int count = countByDID(dossierActionId);

		if (count == 0) {
			return null;
		}

		List<DossierActionUser> list = findByDID(dossierActionId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier action users before and after the current dossier action user in the ordered set where dossierActionId = &#63;.
	 *
	 * @param dossierActionUserPK the primary key of the current dossier action user
	 * @param dossierActionId the dossier action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action user
	 * @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	 */
	@Override
	public DossierActionUser[] findByDID_PrevAndNext(
		DossierActionUserPK dossierActionUserPK, long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = findByPrimaryKey(dossierActionUserPK);

		Session session = null;

		try {
			session = openSession();

			DossierActionUser[] array = new DossierActionUserImpl[3];

			array[0] = getByDID_PrevAndNext(session, dossierActionUser,
					dossierActionId, orderByComparator, true);

			array[1] = dossierActionUser;

			array[2] = getByDID_PrevAndNext(session, dossierActionUser,
					dossierActionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierActionUser getByDID_PrevAndNext(Session session,
		DossierActionUser dossierActionUser, long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERACTIONUSER_WHERE);

		query.append(_FINDER_COLUMN_DID_DOSSIERACTIONID_2);

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
			query.append(DossierActionUserModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierActionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierActionUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierActionUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier action users where dossierActionId = &#63; from the database.
	 *
	 * @param dossierActionId the dossier action ID
	 */
	@Override
	public void removeByDID(long dossierActionId) {
		for (DossierActionUser dossierActionUser : findByDID(dossierActionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierActionUser);
		}
	}

	/**
	 * Returns the number of dossier action users where dossierActionId = &#63;.
	 *
	 * @param dossierActionId the dossier action ID
	 * @return the number of matching dossier action users
	 */
	@Override
	public int countByDID(long dossierActionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID;

		Object[] finderArgs = new Object[] { dossierActionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERACTIONUSER_WHERE);

			query.append(_FINDER_COLUMN_DID_DOSSIERACTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierActionId);

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

	private static final String _FINDER_COLUMN_DID_DOSSIERACTIONID_2 = "dossierActionUser.id.dossierActionId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DID_UID = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED,
			DossierActionUserImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByDID_UID",
			new String[] { Long.class.getName(), Long.class.getName() },
			DossierActionUserModelImpl.DOSSIERACTIONID_COLUMN_BITMASK |
			DossierActionUserModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_UID = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_UID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier action user where dossierActionId = &#63; and userId = &#63; or throws a {@link NoSuchDossierActionUserException} if it could not be found.
	 *
	 * @param dossierActionId the dossier action ID
	 * @param userId the user ID
	 * @return the matching dossier action user
	 * @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser findByDID_UID(long dossierActionId, long userId)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = fetchByDID_UID(dossierActionId,
				userId);

		if (dossierActionUser == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossierActionId=");
			msg.append(dossierActionId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierActionUserException(msg.toString());
		}

		return dossierActionUser;
	}

	/**
	 * Returns the dossier action user where dossierActionId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossierActionId the dossier action ID
	 * @param userId the user ID
	 * @return the matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser fetchByDID_UID(long dossierActionId, long userId) {
		return fetchByDID_UID(dossierActionId, userId, true);
	}

	/**
	 * Returns the dossier action user where dossierActionId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossierActionId the dossier action ID
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser fetchByDID_UID(long dossierActionId, long userId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossierActionId, userId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DID_UID,
					finderArgs, this);
		}

		if (result instanceof DossierActionUser) {
			DossierActionUser dossierActionUser = (DossierActionUser)result;

			if ((dossierActionId != dossierActionUser.getDossierActionId()) ||
					(userId != dossierActionUser.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERACTIONUSER_WHERE);

			query.append(_FINDER_COLUMN_DID_UID_DOSSIERACTIONID_2);

			query.append(_FINDER_COLUMN_DID_UID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierActionId);

				qPos.add(userId);

				List<DossierActionUser> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DID_UID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierActionUserPersistenceImpl.fetchByDID_UID(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierActionUser dossierActionUser = list.get(0);

					result = dossierActionUser;

					cacheResult(dossierActionUser);
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
			return (DossierActionUser)result;
		}
	}

	/**
	 * Removes the dossier action user where dossierActionId = &#63; and userId = &#63; from the database.
	 *
	 * @param dossierActionId the dossier action ID
	 * @param userId the user ID
	 * @return the dossier action user that was removed
	 */
	@Override
	public DossierActionUser removeByDID_UID(long dossierActionId, long userId)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = findByDID_UID(dossierActionId,
				userId);

		return remove(dossierActionUser);
	}

	/**
	 * Returns the number of dossier action users where dossierActionId = &#63; and userId = &#63;.
	 *
	 * @param dossierActionId the dossier action ID
	 * @param userId the user ID
	 * @return the number of matching dossier action users
	 */
	@Override
	public int countByDID_UID(long dossierActionId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_UID;

		Object[] finderArgs = new Object[] { dossierActionId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTIONUSER_WHERE);

			query.append(_FINDER_COLUMN_DID_UID_DOSSIERACTIONID_2);

			query.append(_FINDER_COLUMN_DID_UID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierActionId);

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

	private static final String _FINDER_COLUMN_DID_UID_DOSSIERACTIONID_2 = "dossierActionUser.id.dossierActionId = ? AND ";
	private static final String _FINDER_COLUMN_DID_UID_USERID_2 = "dossierActionUser.id.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UID = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED,
			DossierActionUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UID = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED,
			DossierActionUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUID",
			new String[] { Long.class.getName() },
			DossierActionUserModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UID = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dossier action users where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByUID(long userId) {
		return findByUID(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier action users where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @return the range of matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByUID(long userId, int start, int end) {
		return findByUID(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier action users where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByUID(long userId, int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator) {
		return findByUID(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier action users where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByUID(long userId, int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator,
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

		List<DossierActionUser> list = null;

		if (retrieveFromCache) {
			list = (List<DossierActionUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierActionUser dossierActionUser : list) {
					if ((userId != dossierActionUser.getUserId())) {
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

			query.append(_SQL_SELECT_DOSSIERACTIONUSER_WHERE);

			query.append(_FINDER_COLUMN_UID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierActionUserModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<DossierActionUser>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierActionUser>)QueryUtil.list(q,
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
	 * Returns the first dossier action user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action user
	 * @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser findByUID_First(long userId,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = fetchByUID_First(userId,
				orderByComparator);

		if (dossierActionUser != null) {
			return dossierActionUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchDossierActionUserException(msg.toString());
	}

	/**
	 * Returns the first dossier action user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser fetchByUID_First(long userId,
		OrderByComparator<DossierActionUser> orderByComparator) {
		List<DossierActionUser> list = findByUID(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action user
	 * @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser findByUID_Last(long userId,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = fetchByUID_Last(userId,
				orderByComparator);

		if (dossierActionUser != null) {
			return dossierActionUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchDossierActionUserException(msg.toString());
	}

	/**
	 * Returns the last dossier action user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser fetchByUID_Last(long userId,
		OrderByComparator<DossierActionUser> orderByComparator) {
		int count = countByUID(userId);

		if (count == 0) {
			return null;
		}

		List<DossierActionUser> list = findByUID(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier action users before and after the current dossier action user in the ordered set where userId = &#63;.
	 *
	 * @param dossierActionUserPK the primary key of the current dossier action user
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action user
	 * @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	 */
	@Override
	public DossierActionUser[] findByUID_PrevAndNext(
		DossierActionUserPK dossierActionUserPK, long userId,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = findByPrimaryKey(dossierActionUserPK);

		Session session = null;

		try {
			session = openSession();

			DossierActionUser[] array = new DossierActionUserImpl[3];

			array[0] = getByUID_PrevAndNext(session, dossierActionUser, userId,
					orderByComparator, true);

			array[1] = dossierActionUser;

			array[2] = getByUID_PrevAndNext(session, dossierActionUser, userId,
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

	protected DossierActionUser getByUID_PrevAndNext(Session session,
		DossierActionUser dossierActionUser, long userId,
		OrderByComparator<DossierActionUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERACTIONUSER_WHERE);

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
			query.append(DossierActionUserModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierActionUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierActionUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier action users where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUID(long userId) {
		for (DossierActionUser dossierActionUser : findByUID(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierActionUser);
		}
	}

	/**
	 * Returns the number of dossier action users where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching dossier action users
	 */
	@Override
	public int countByUID(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERACTIONUSER_WHERE);

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

	private static final String _FINDER_COLUMN_UID_USERID_2 = "dossierActionUser.id.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_SC = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED,
			DossierActionUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDID_SC",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_SC =
		new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED,
			DossierActionUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDID_SC",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierActionUserModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierActionUserModelImpl.STEPCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_SC = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_SC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossier action users where dossierId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @return the matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByDID_SC(long dossierId, String stepCode) {
		return findByDID_SC(dossierId, stepCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier action users where dossierId = &#63; and stepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @return the range of matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByDID_SC(long dossierId,
		String stepCode, int start, int end) {
		return findByDID_SC(dossierId, stepCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier action users where dossierId = &#63; and stepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByDID_SC(long dossierId,
		String stepCode, int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator) {
		return findByDID_SC(dossierId, stepCode, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the dossier action users where dossierId = &#63; and stepCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByDID_SC(long dossierId,
		String stepCode, int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_SC;
			finderArgs = new Object[] { dossierId, stepCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_SC;
			finderArgs = new Object[] {
					dossierId, stepCode,
					
					start, end, orderByComparator
				};
		}

		List<DossierActionUser> list = null;

		if (retrieveFromCache) {
			list = (List<DossierActionUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierActionUser dossierActionUser : list) {
					if ((dossierId != dossierActionUser.getDossierId()) ||
							!Objects.equals(stepCode,
								dossierActionUser.getStepCode())) {
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

			query.append(_SQL_SELECT_DOSSIERACTIONUSER_WHERE);

			query.append(_FINDER_COLUMN_DID_SC_DOSSIERID_2);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_DID_SC_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_DID_SC_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_DID_SC_STEPCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierActionUserModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindStepCode) {
					qPos.add(stepCode);
				}

				if (!pagination) {
					list = (List<DossierActionUser>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierActionUser>)QueryUtil.list(q,
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
	 * Returns the first dossier action user in the ordered set where dossierId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action user
	 * @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser findByDID_SC_First(long dossierId,
		String stepCode, OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = fetchByDID_SC_First(dossierId,
				stepCode, orderByComparator);

		if (dossierActionUser != null) {
			return dossierActionUser;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", stepCode=");
		msg.append(stepCode);

		msg.append("}");

		throw new NoSuchDossierActionUserException(msg.toString());
	}

	/**
	 * Returns the first dossier action user in the ordered set where dossierId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser fetchByDID_SC_First(long dossierId,
		String stepCode, OrderByComparator<DossierActionUser> orderByComparator) {
		List<DossierActionUser> list = findByDID_SC(dossierId, stepCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier action user in the ordered set where dossierId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action user
	 * @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser findByDID_SC_Last(long dossierId, String stepCode,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = fetchByDID_SC_Last(dossierId,
				stepCode, orderByComparator);

		if (dossierActionUser != null) {
			return dossierActionUser;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", stepCode=");
		msg.append(stepCode);

		msg.append("}");

		throw new NoSuchDossierActionUserException(msg.toString());
	}

	/**
	 * Returns the last dossier action user in the ordered set where dossierId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	 */
	@Override
	public DossierActionUser fetchByDID_SC_Last(long dossierId,
		String stepCode, OrderByComparator<DossierActionUser> orderByComparator) {
		int count = countByDID_SC(dossierId, stepCode);

		if (count == 0) {
			return null;
		}

		List<DossierActionUser> list = findByDID_SC(dossierId, stepCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier action users before and after the current dossier action user in the ordered set where dossierId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierActionUserPK the primary key of the current dossier action user
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier action user
	 * @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	 */
	@Override
	public DossierActionUser[] findByDID_SC_PrevAndNext(
		DossierActionUserPK dossierActionUserPK, long dossierId,
		String stepCode, OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = findByPrimaryKey(dossierActionUserPK);

		Session session = null;

		try {
			session = openSession();

			DossierActionUser[] array = new DossierActionUserImpl[3];

			array[0] = getByDID_SC_PrevAndNext(session, dossierActionUser,
					dossierId, stepCode, orderByComparator, true);

			array[1] = dossierActionUser;

			array[2] = getByDID_SC_PrevAndNext(session, dossierActionUser,
					dossierId, stepCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierActionUser getByDID_SC_PrevAndNext(Session session,
		DossierActionUser dossierActionUser, long dossierId, String stepCode,
		OrderByComparator<DossierActionUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERACTIONUSER_WHERE);

		query.append(_FINDER_COLUMN_DID_SC_DOSSIERID_2);

		boolean bindStepCode = false;

		if (stepCode == null) {
			query.append(_FINDER_COLUMN_DID_SC_STEPCODE_1);
		}
		else if (stepCode.equals("")) {
			query.append(_FINDER_COLUMN_DID_SC_STEPCODE_3);
		}
		else {
			bindStepCode = true;

			query.append(_FINDER_COLUMN_DID_SC_STEPCODE_2);
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
			query.append(DossierActionUserModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (bindStepCode) {
			qPos.add(stepCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierActionUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierActionUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier action users where dossierId = &#63; and stepCode = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 */
	@Override
	public void removeByDID_SC(long dossierId, String stepCode) {
		for (DossierActionUser dossierActionUser : findByDID_SC(dossierId,
				stepCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierActionUser);
		}
	}

	/**
	 * Returns the number of dossier action users where dossierId = &#63; and stepCode = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param stepCode the step code
	 * @return the number of matching dossier action users
	 */
	@Override
	public int countByDID_SC(long dossierId, String stepCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_SC;

		Object[] finderArgs = new Object[] { dossierId, stepCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERACTIONUSER_WHERE);

			query.append(_FINDER_COLUMN_DID_SC_DOSSIERID_2);

			boolean bindStepCode = false;

			if (stepCode == null) {
				query.append(_FINDER_COLUMN_DID_SC_STEPCODE_1);
			}
			else if (stepCode.equals("")) {
				query.append(_FINDER_COLUMN_DID_SC_STEPCODE_3);
			}
			else {
				bindStepCode = true;

				query.append(_FINDER_COLUMN_DID_SC_STEPCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindStepCode) {
					qPos.add(stepCode);
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

	private static final String _FINDER_COLUMN_DID_SC_DOSSIERID_2 = "dossierActionUser.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_SC_STEPCODE_1 = "dossierActionUser.stepCode IS NULL";
	private static final String _FINDER_COLUMN_DID_SC_STEPCODE_2 = "dossierActionUser.stepCode = ?";
	private static final String _FINDER_COLUMN_DID_SC_STEPCODE_3 = "(dossierActionUser.stepCode IS NULL OR dossierActionUser.stepCode = '')";

	public DossierActionUserPersistenceImpl() {
		setModelClass(DossierActionUser.class);

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
	 * Caches the dossier action user in the entity cache if it is enabled.
	 *
	 * @param dossierActionUser the dossier action user
	 */
	@Override
	public void cacheResult(DossierActionUser dossierActionUser) {
		entityCache.putResult(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserImpl.class, dossierActionUser.getPrimaryKey(),
			dossierActionUser);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DID_UID,
			new Object[] {
				dossierActionUser.getDossierActionId(),
				dossierActionUser.getUserId()
			}, dossierActionUser);

		dossierActionUser.resetOriginalValues();
	}

	/**
	 * Caches the dossier action users in the entity cache if it is enabled.
	 *
	 * @param dossierActionUsers the dossier action users
	 */
	@Override
	public void cacheResult(List<DossierActionUser> dossierActionUsers) {
		for (DossierActionUser dossierActionUser : dossierActionUsers) {
			if (entityCache.getResult(
						DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
						DossierActionUserImpl.class,
						dossierActionUser.getPrimaryKey()) == null) {
				cacheResult(dossierActionUser);
			}
			else {
				dossierActionUser.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dossier action users.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DossierActionUserImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dossier action user.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DossierActionUser dossierActionUser) {
		entityCache.removeResult(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserImpl.class, dossierActionUser.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DossierActionUserModelImpl)dossierActionUser,
			true);
	}

	@Override
	public void clearCache(List<DossierActionUser> dossierActionUsers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DossierActionUser dossierActionUser : dossierActionUsers) {
			entityCache.removeResult(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
				DossierActionUserImpl.class, dossierActionUser.getPrimaryKey());

			clearUniqueFindersCache((DossierActionUserModelImpl)dossierActionUser,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		DossierActionUserModelImpl dossierActionUserModelImpl) {
		Object[] args = new Object[] {
				dossierActionUserModelImpl.getDossierActionId(),
				dossierActionUserModelImpl.getUserId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_DID_UID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DID_UID, args,
			dossierActionUserModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DossierActionUserModelImpl dossierActionUserModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierActionUserModelImpl.getDossierActionId(),
					dossierActionUserModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_UID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_UID, args);
		}

		if ((dossierActionUserModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DID_UID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierActionUserModelImpl.getOriginalDossierActionId(),
					dossierActionUserModelImpl.getOriginalUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_UID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_UID, args);
		}
	}

	/**
	 * Creates a new dossier action user with the primary key. Does not add the dossier action user to the database.
	 *
	 * @param dossierActionUserPK the primary key for the new dossier action user
	 * @return the new dossier action user
	 */
	@Override
	public DossierActionUser create(DossierActionUserPK dossierActionUserPK) {
		DossierActionUser dossierActionUser = new DossierActionUserImpl();

		dossierActionUser.setNew(true);
		dossierActionUser.setPrimaryKey(dossierActionUserPK);

		String uuid = PortalUUIDUtil.generate();

		dossierActionUser.setUuid(uuid);

		return dossierActionUser;
	}

	/**
	 * Removes the dossier action user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dossierActionUserPK the primary key of the dossier action user
	 * @return the dossier action user that was removed
	 * @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	 */
	@Override
	public DossierActionUser remove(DossierActionUserPK dossierActionUserPK)
		throws NoSuchDossierActionUserException {
		return remove((Serializable)dossierActionUserPK);
	}

	/**
	 * Removes the dossier action user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dossier action user
	 * @return the dossier action user that was removed
	 * @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	 */
	@Override
	public DossierActionUser remove(Serializable primaryKey)
		throws NoSuchDossierActionUserException {
		Session session = null;

		try {
			session = openSession();

			DossierActionUser dossierActionUser = (DossierActionUser)session.get(DossierActionUserImpl.class,
					primaryKey);

			if (dossierActionUser == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDossierActionUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dossierActionUser);
		}
		catch (NoSuchDossierActionUserException nsee) {
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
	protected DossierActionUser removeImpl(DossierActionUser dossierActionUser) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dossierActionUser)) {
				dossierActionUser = (DossierActionUser)session.get(DossierActionUserImpl.class,
						dossierActionUser.getPrimaryKeyObj());
			}

			if (dossierActionUser != null) {
				session.delete(dossierActionUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dossierActionUser != null) {
			clearCache(dossierActionUser);
		}

		return dossierActionUser;
	}

	@Override
	public DossierActionUser updateImpl(DossierActionUser dossierActionUser) {
		boolean isNew = dossierActionUser.isNew();

		if (!(dossierActionUser instanceof DossierActionUserModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dossierActionUser.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dossierActionUser);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dossierActionUser proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DossierActionUser implementation " +
				dossierActionUser.getClass());
		}

		DossierActionUserModelImpl dossierActionUserModelImpl = (DossierActionUserModelImpl)dossierActionUser;

		if (Validator.isNull(dossierActionUser.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dossierActionUser.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (dossierActionUser.isNew()) {
				session.save(dossierActionUser);

				dossierActionUser.setNew(false);
			}
			else {
				dossierActionUser = (DossierActionUser)session.merge(dossierActionUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DossierActionUserModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dossierActionUserModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { dossierActionUserModelImpl.getDossierActionId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID,
				args);

			args = new Object[] { dossierActionUserModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UID,
				args);

			args = new Object[] {
					dossierActionUserModelImpl.getDossierId(),
					dossierActionUserModelImpl.getStepCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_SC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_SC,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dossierActionUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionUserModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dossierActionUserModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dossierActionUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionUserModelImpl.getOriginalDossierActionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID,
					args);

				args = new Object[] {
						dossierActionUserModelImpl.getDossierActionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID,
					args);
			}

			if ((dossierActionUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionUserModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UID,
					args);

				args = new Object[] { dossierActionUserModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UID,
					args);
			}

			if ((dossierActionUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_SC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionUserModelImpl.getOriginalDossierId(),
						dossierActionUserModelImpl.getOriginalStepCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_SC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_SC,
					args);

				args = new Object[] {
						dossierActionUserModelImpl.getDossierId(),
						dossierActionUserModelImpl.getStepCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_SC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_SC,
					args);
			}
		}

		entityCache.putResult(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserImpl.class, dossierActionUser.getPrimaryKey(),
			dossierActionUser, false);

		clearUniqueFindersCache(dossierActionUserModelImpl, false);
		cacheUniqueFindersCache(dossierActionUserModelImpl);

		dossierActionUser.resetOriginalValues();

		return dossierActionUser;
	}

	/**
	 * Returns the dossier action user with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier action user
	 * @return the dossier action user
	 * @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	 */
	@Override
	public DossierActionUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = fetchByPrimaryKey(primaryKey);

		if (dossierActionUser == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDossierActionUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dossierActionUser;
	}

	/**
	 * Returns the dossier action user with the primary key or throws a {@link NoSuchDossierActionUserException} if it could not be found.
	 *
	 * @param dossierActionUserPK the primary key of the dossier action user
	 * @return the dossier action user
	 * @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	 */
	@Override
	public DossierActionUser findByPrimaryKey(
		DossierActionUserPK dossierActionUserPK)
		throws NoSuchDossierActionUserException {
		return findByPrimaryKey((Serializable)dossierActionUserPK);
	}

	/**
	 * Returns the dossier action user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier action user
	 * @return the dossier action user, or <code>null</code> if a dossier action user with the primary key could not be found
	 */
	@Override
	public DossierActionUser fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
				DossierActionUserImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DossierActionUser dossierActionUser = (DossierActionUser)serializable;

		if (dossierActionUser == null) {
			Session session = null;

			try {
				session = openSession();

				dossierActionUser = (DossierActionUser)session.get(DossierActionUserImpl.class,
						primaryKey);

				if (dossierActionUser != null) {
					cacheResult(dossierActionUser);
				}
				else {
					entityCache.putResult(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
						DossierActionUserImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
					DossierActionUserImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dossierActionUser;
	}

	/**
	 * Returns the dossier action user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dossierActionUserPK the primary key of the dossier action user
	 * @return the dossier action user, or <code>null</code> if a dossier action user with the primary key could not be found
	 */
	@Override
	public DossierActionUser fetchByPrimaryKey(
		DossierActionUserPK dossierActionUserPK) {
		return fetchByPrimaryKey((Serializable)dossierActionUserPK);
	}

	@Override
	public Map<Serializable, DossierActionUser> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DossierActionUser> map = new HashMap<Serializable, DossierActionUser>();

		for (Serializable primaryKey : primaryKeys) {
			DossierActionUser dossierActionUser = fetchByPrimaryKey(primaryKey);

			if (dossierActionUser != null) {
				map.put(primaryKey, dossierActionUser);
			}
		}

		return map;
	}

	/**
	 * Returns all the dossier action users.
	 *
	 * @return the dossier action users
	 */
	@Override
	public List<DossierActionUser> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier action users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @return the range of dossier action users
	 */
	@Override
	public List<DossierActionUser> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier action users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dossier action users
	 */
	@Override
	public List<DossierActionUser> findAll(int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier action users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier action users
	 * @param end the upper bound of the range of dossier action users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dossier action users
	 */
	@Override
	public List<DossierActionUser> findAll(int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator,
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

		List<DossierActionUser> list = null;

		if (retrieveFromCache) {
			list = (List<DossierActionUser>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOSSIERACTIONUSER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOSSIERACTIONUSER;

				if (pagination) {
					sql = sql.concat(DossierActionUserModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DossierActionUser>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierActionUser>)QueryUtil.list(q,
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
	 * Removes all the dossier action users from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DossierActionUser dossierActionUser : findAll()) {
			remove(dossierActionUser);
		}
	}

	/**
	 * Returns the number of dossier action users.
	 *
	 * @return the number of dossier action users
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOSSIERACTIONUSER);

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
		return DossierActionUserModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dossier action user persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DossierActionUserImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_DOSSIERACTIONUSER = "SELECT dossierActionUser FROM DossierActionUser dossierActionUser";
	private static final String _SQL_SELECT_DOSSIERACTIONUSER_WHERE = "SELECT dossierActionUser FROM DossierActionUser dossierActionUser WHERE ";
	private static final String _SQL_COUNT_DOSSIERACTIONUSER = "SELECT COUNT(dossierActionUser) FROM DossierActionUser dossierActionUser";
	private static final String _SQL_COUNT_DOSSIERACTIONUSER_WHERE = "SELECT COUNT(dossierActionUser) FROM DossierActionUser dossierActionUser WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dossierActionUser.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DossierActionUser exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DossierActionUser exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DossierActionUserPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(new String[] {
				"dossierActionId", "userId"
			});
}