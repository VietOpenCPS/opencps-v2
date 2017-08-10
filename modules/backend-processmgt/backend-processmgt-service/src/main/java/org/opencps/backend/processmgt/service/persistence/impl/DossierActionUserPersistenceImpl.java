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

package org.opencps.backend.processmgt.service.persistence.impl;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.backend.processmgt.exception.NoSuchDossierActionUserException;
import org.opencps.backend.processmgt.model.DossierActionUser;
import org.opencps.backend.processmgt.model.impl.DossierActionUserImpl;
import org.opencps.backend.processmgt.model.impl.DossierActionUserModelImpl;
import org.opencps.backend.processmgt.service.persistence.DossierActionUserPK;
import org.opencps.backend.processmgt.service.persistence.DossierActionUserPersistence;

import java.io.Serializable;

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
 * @author khoavu
 * @see DossierActionUserPersistence
 * @see org.opencps.backend.processmgt.service.persistence.DossierActionUserUtil
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

		msg.append(StringPool.CLOSE_CURLY_BRACE);

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

		msg.append(StringPool.CLOSE_CURLY_BRACE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dossierActionUser.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dossierActionUser.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dossierActionUser.uuid IS NULL OR dossierActionUser.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_D_A_ID = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED,
			DossierActionUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByD_A_ID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_A_ID =
		new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED,
			DossierActionUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByD_A_ID",
			new String[] { Long.class.getName() },
			DossierActionUserModelImpl.DOSSIERACTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_D_A_ID = new FinderPath(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByD_A_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dossier action users where dossierActionId = &#63;.
	 *
	 * @param dossierActionId the dossier action ID
	 * @return the matching dossier action users
	 */
	@Override
	public List<DossierActionUser> findByD_A_ID(long dossierActionId) {
		return findByD_A_ID(dossierActionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	public List<DossierActionUser> findByD_A_ID(long dossierActionId,
		int start, int end) {
		return findByD_A_ID(dossierActionId, start, end, null);
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
	public List<DossierActionUser> findByD_A_ID(long dossierActionId,
		int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator) {
		return findByD_A_ID(dossierActionId, start, end, orderByComparator, true);
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
	public List<DossierActionUser> findByD_A_ID(long dossierActionId,
		int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_A_ID;
			finderArgs = new Object[] { dossierActionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_D_A_ID;
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

			query.append(_FINDER_COLUMN_D_A_ID_DOSSIERACTIONID_2);

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
	public DossierActionUser findByD_A_ID_First(long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = fetchByD_A_ID_First(dossierActionId,
				orderByComparator);

		if (dossierActionUser != null) {
			return dossierActionUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierActionId=");
		msg.append(dossierActionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

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
	public DossierActionUser fetchByD_A_ID_First(long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator) {
		List<DossierActionUser> list = findByD_A_ID(dossierActionId, 0, 1,
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
	public DossierActionUser findByD_A_ID_Last(long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = fetchByD_A_ID_Last(dossierActionId,
				orderByComparator);

		if (dossierActionUser != null) {
			return dossierActionUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierActionId=");
		msg.append(dossierActionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

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
	public DossierActionUser fetchByD_A_ID_Last(long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator) {
		int count = countByD_A_ID(dossierActionId);

		if (count == 0) {
			return null;
		}

		List<DossierActionUser> list = findByD_A_ID(dossierActionId, count - 1,
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
	public DossierActionUser[] findByD_A_ID_PrevAndNext(
		DossierActionUserPK dossierActionUserPK, long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws NoSuchDossierActionUserException {
		DossierActionUser dossierActionUser = findByPrimaryKey(dossierActionUserPK);

		Session session = null;

		try {
			session = openSession();

			DossierActionUser[] array = new DossierActionUserImpl[3];

			array[0] = getByD_A_ID_PrevAndNext(session, dossierActionUser,
					dossierActionId, orderByComparator, true);

			array[1] = dossierActionUser;

			array[2] = getByD_A_ID_PrevAndNext(session, dossierActionUser,
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

	protected DossierActionUser getByD_A_ID_PrevAndNext(Session session,
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

		query.append(_FINDER_COLUMN_D_A_ID_DOSSIERACTIONID_2);

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
	public void removeByD_A_ID(long dossierActionId) {
		for (DossierActionUser dossierActionUser : findByD_A_ID(
				dossierActionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
	public int countByD_A_ID(long dossierActionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_D_A_ID;

		Object[] finderArgs = new Object[] { dossierActionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERACTIONUSER_WHERE);

			query.append(_FINDER_COLUMN_D_A_ID_DOSSIERACTIONID_2);

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

	private static final String _FINDER_COLUMN_D_A_ID_DOSSIERACTIONID_2 = "dossierActionUser.id.dossierActionId = ?";

	public DossierActionUserPersistenceImpl() {
		setModelClass(DossierActionUser.class);
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
	}

	@Override
	public void clearCache(List<DossierActionUser> dossierActionUsers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DossierActionUser dossierActionUser : dossierActionUsers) {
			entityCache.removeResult(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
				DossierActionUserImpl.class, dossierActionUser.getPrimaryKey());
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
		dossierActionUser = toUnwrappedModel(dossierActionUser);

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
		dossierActionUser = toUnwrappedModel(dossierActionUser);

		boolean isNew = dossierActionUser.isNew();

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

		if (isNew || !DossierActionUserModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
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
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_A_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierActionUserModelImpl.getOriginalDossierActionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_D_A_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_A_ID,
					args);

				args = new Object[] {
						dossierActionUserModelImpl.getDossierActionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_D_A_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_A_ID,
					args);
			}
		}

		entityCache.putResult(DossierActionUserModelImpl.ENTITY_CACHE_ENABLED,
			DossierActionUserImpl.class, dossierActionUser.getPrimaryKey(),
			dossierActionUser, false);

		dossierActionUser.resetOriginalValues();

		return dossierActionUser;
	}

	protected DossierActionUser toUnwrappedModel(
		DossierActionUser dossierActionUser) {
		if (dossierActionUser instanceof DossierActionUserImpl) {
			return dossierActionUser;
		}

		DossierActionUserImpl dossierActionUserImpl = new DossierActionUserImpl();

		dossierActionUserImpl.setNew(dossierActionUser.isNew());
		dossierActionUserImpl.setPrimaryKey(dossierActionUser.getPrimaryKey());

		dossierActionUserImpl.setUuid(dossierActionUser.getUuid());
		dossierActionUserImpl.setDossierActionId(dossierActionUser.getDossierActionId());
		dossierActionUserImpl.setUserId(dossierActionUser.getUserId());
		dossierActionUserImpl.setModerator(dossierActionUser.getModerator());

		return dossierActionUserImpl;
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
}