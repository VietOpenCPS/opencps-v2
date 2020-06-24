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

package pay.gate.integration.dvc.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.petra.string.StringBundler;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import pay.gate.integration.dvc.exception.NoSuchPhiLePhiException;
import pay.gate.integration.dvc.model.PhiLePhi;
import pay.gate.integration.dvc.model.impl.PhiLePhiImpl;
import pay.gate.integration.dvc.model.impl.PhiLePhiModelImpl;
import pay.gate.integration.dvc.service.persistence.PhiLePhiPersistence;

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
 * The persistence implementation for the phi le phi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PhiLePhiPersistence
 * @see pay.gate.integration.dvc.service.persistence.PhiLePhiUtil
 * @generated
 */
@ProviderType
public class PhiLePhiPersistenceImpl extends BasePersistenceImpl<PhiLePhi>
	implements PhiLePhiPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PhiLePhiUtil} to access the phi le phi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PhiLePhiImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiModelImpl.FINDER_CACHE_ENABLED, PhiLePhiImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiModelImpl.FINDER_CACHE_ENABLED, PhiLePhiImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiModelImpl.FINDER_CACHE_ENABLED, PhiLePhiImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiModelImpl.FINDER_CACHE_ENABLED, PhiLePhiImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PhiLePhiModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the phi le phis where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching phi le phis
	 */
	@Override
	public List<PhiLePhi> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the phi le phis where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of phi le phis
	 * @param end the upper bound of the range of phi le phis (not inclusive)
	 * @return the range of matching phi le phis
	 */
	@Override
	public List<PhiLePhi> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the phi le phis where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of phi le phis
	 * @param end the upper bound of the range of phi le phis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching phi le phis
	 */
	@Override
	public List<PhiLePhi> findByUuid(String uuid, int start, int end,
		OrderByComparator<PhiLePhi> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the phi le phis where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of phi le phis
	 * @param end the upper bound of the range of phi le phis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching phi le phis
	 */
	@Override
	public List<PhiLePhi> findByUuid(String uuid, int start, int end,
		OrderByComparator<PhiLePhi> orderByComparator, boolean retrieveFromCache) {
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

		List<PhiLePhi> list = null;

		if (retrieveFromCache) {
			list = (List<PhiLePhi>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PhiLePhi phiLePhi : list) {
					if (!Objects.equals(uuid, phiLePhi.getUuid())) {
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

			query.append(_SQL_SELECT_PHILEPHI_WHERE);

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
				query.append(PhiLePhiModelImpl.ORDER_BY_JPQL);
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
					list = (List<PhiLePhi>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PhiLePhi>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first phi le phi in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching phi le phi
	 * @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi findByUuid_First(String uuid,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException {
		PhiLePhi phiLePhi = fetchByUuid_First(uuid, orderByComparator);

		if (phiLePhi != null) {
			return phiLePhi;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPhiLePhiException(msg.toString());
	}

	/**
	 * Returns the first phi le phi in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi fetchByUuid_First(String uuid,
		OrderByComparator<PhiLePhi> orderByComparator) {
		List<PhiLePhi> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last phi le phi in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching phi le phi
	 * @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi findByUuid_Last(String uuid,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException {
		PhiLePhi phiLePhi = fetchByUuid_Last(uuid, orderByComparator);

		if (phiLePhi != null) {
			return phiLePhi;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPhiLePhiException(msg.toString());
	}

	/**
	 * Returns the last phi le phi in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi fetchByUuid_Last(String uuid,
		OrderByComparator<PhiLePhi> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PhiLePhi> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the phi le phis before and after the current phi le phi in the ordered set where uuid = &#63;.
	 *
	 * @param phiLePhiId the primary key of the current phi le phi
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next phi le phi
	 * @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	 */
	@Override
	public PhiLePhi[] findByUuid_PrevAndNext(long phiLePhiId, String uuid,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException {
		PhiLePhi phiLePhi = findByPrimaryKey(phiLePhiId);

		Session session = null;

		try {
			session = openSession();

			PhiLePhi[] array = new PhiLePhiImpl[3];

			array[0] = getByUuid_PrevAndNext(session, phiLePhi, uuid,
					orderByComparator, true);

			array[1] = phiLePhi;

			array[2] = getByUuid_PrevAndNext(session, phiLePhi, uuid,
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

	protected PhiLePhi getByUuid_PrevAndNext(Session session,
		PhiLePhi phiLePhi, String uuid,
		OrderByComparator<PhiLePhi> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PHILEPHI_WHERE);

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
			query.append(PhiLePhiModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(phiLePhi);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PhiLePhi> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the phi le phis where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PhiLePhi phiLePhi : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(phiLePhi);
		}
	}

	/**
	 * Returns the number of phi le phis where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching phi le phis
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PHILEPHI_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "phiLePhi.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "phiLePhi.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(phiLePhi.uuid IS NULL OR phiLePhi.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiModelImpl.FINDER_CACHE_ENABLED, PhiLePhiImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PhiLePhiModelImpl.UUID_COLUMN_BITMASK |
			PhiLePhiModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the phi le phi where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPhiLePhiException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching phi le phi
	 * @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi findByUUID_G(String uuid, long groupId)
		throws NoSuchPhiLePhiException {
		PhiLePhi phiLePhi = fetchByUUID_G(uuid, groupId);

		if (phiLePhi == null) {
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

			throw new NoSuchPhiLePhiException(msg.toString());
		}

		return phiLePhi;
	}

	/**
	 * Returns the phi le phi where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the phi le phi where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PhiLePhi) {
			PhiLePhi phiLePhi = (PhiLePhi)result;

			if (!Objects.equals(uuid, phiLePhi.getUuid()) ||
					(groupId != phiLePhi.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PHILEPHI_WHERE);

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

				List<PhiLePhi> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					PhiLePhi phiLePhi = list.get(0);

					result = phiLePhi;

					cacheResult(phiLePhi);
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
			return (PhiLePhi)result;
		}
	}

	/**
	 * Removes the phi le phi where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the phi le phi that was removed
	 */
	@Override
	public PhiLePhi removeByUUID_G(String uuid, long groupId)
		throws NoSuchPhiLePhiException {
		PhiLePhi phiLePhi = findByUUID_G(uuid, groupId);

		return remove(phiLePhi);
	}

	/**
	 * Returns the number of phi le phis where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching phi le phis
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PHILEPHI_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "phiLePhi.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "phiLePhi.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(phiLePhi.uuid IS NULL OR phiLePhi.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "phiLePhi.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiModelImpl.FINDER_CACHE_ENABLED, PhiLePhiImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiModelImpl.FINDER_CACHE_ENABLED, PhiLePhiImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PhiLePhiModelImpl.UUID_COLUMN_BITMASK |
			PhiLePhiModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the phi le phis where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching phi le phis
	 */
	@Override
	public List<PhiLePhi> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the phi le phis where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of phi le phis
	 * @param end the upper bound of the range of phi le phis (not inclusive)
	 * @return the range of matching phi le phis
	 */
	@Override
	public List<PhiLePhi> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the phi le phis where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of phi le phis
	 * @param end the upper bound of the range of phi le phis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching phi le phis
	 */
	@Override
	public List<PhiLePhi> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<PhiLePhi> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the phi le phis where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of phi le phis
	 * @param end the upper bound of the range of phi le phis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching phi le phis
	 */
	@Override
	public List<PhiLePhi> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<PhiLePhi> orderByComparator,
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

		List<PhiLePhi> list = null;

		if (retrieveFromCache) {
			list = (List<PhiLePhi>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PhiLePhi phiLePhi : list) {
					if (!Objects.equals(uuid, phiLePhi.getUuid()) ||
							(companyId != phiLePhi.getCompanyId())) {
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

			query.append(_SQL_SELECT_PHILEPHI_WHERE);

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
				query.append(PhiLePhiModelImpl.ORDER_BY_JPQL);
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
					list = (List<PhiLePhi>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PhiLePhi>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching phi le phi
	 * @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException {
		PhiLePhi phiLePhi = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (phiLePhi != null) {
			return phiLePhi;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPhiLePhiException(msg.toString());
	}

	/**
	 * Returns the first phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PhiLePhi> orderByComparator) {
		List<PhiLePhi> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching phi le phi
	 * @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException {
		PhiLePhi phiLePhi = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (phiLePhi != null) {
			return phiLePhi;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPhiLePhiException(msg.toString());
	}

	/**
	 * Returns the last phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PhiLePhi> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<PhiLePhi> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the phi le phis before and after the current phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param phiLePhiId the primary key of the current phi le phi
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next phi le phi
	 * @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	 */
	@Override
	public PhiLePhi[] findByUuid_C_PrevAndNext(long phiLePhiId, String uuid,
		long companyId, OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException {
		PhiLePhi phiLePhi = findByPrimaryKey(phiLePhiId);

		Session session = null;

		try {
			session = openSession();

			PhiLePhi[] array = new PhiLePhiImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, phiLePhi, uuid,
					companyId, orderByComparator, true);

			array[1] = phiLePhi;

			array[2] = getByUuid_C_PrevAndNext(session, phiLePhi, uuid,
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

	protected PhiLePhi getByUuid_C_PrevAndNext(Session session,
		PhiLePhi phiLePhi, String uuid, long companyId,
		OrderByComparator<PhiLePhi> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PHILEPHI_WHERE);

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
			query.append(PhiLePhiModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(phiLePhi);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PhiLePhi> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the phi le phis where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (PhiLePhi phiLePhi : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(phiLePhi);
		}
	}

	/**
	 * Returns the number of phi le phis where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching phi le phis
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PHILEPHI_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "phiLePhi.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "phiLePhi.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(phiLePhi.uuid IS NULL OR phiLePhi.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "phiLePhi.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_SCMID = new FinderPath(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiModelImpl.FINDER_CACHE_ENABLED, PhiLePhiImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_SCMID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SCMID =
		new FinderPath(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiModelImpl.FINDER_CACHE_ENABLED, PhiLePhiImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_SCMID",
			new String[] { Long.class.getName(), Long.class.getName() },
			PhiLePhiModelImpl.GROUPID_COLUMN_BITMASK |
			PhiLePhiModelImpl.SERVICECONFIGMAPPINGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_SCMID = new FinderPath(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_SCMID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceConfigMappingId the service config mapping ID
	 * @return the matching phi le phis
	 */
	@Override
	public List<PhiLePhi> findByG_SCMID(long groupId,
		long serviceConfigMappingId) {
		return findByG_SCMID(groupId, serviceConfigMappingId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param start the lower bound of the range of phi le phis
	 * @param end the upper bound of the range of phi le phis (not inclusive)
	 * @return the range of matching phi le phis
	 */
	@Override
	public List<PhiLePhi> findByG_SCMID(long groupId,
		long serviceConfigMappingId, int start, int end) {
		return findByG_SCMID(groupId, serviceConfigMappingId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param start the lower bound of the range of phi le phis
	 * @param end the upper bound of the range of phi le phis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching phi le phis
	 */
	@Override
	public List<PhiLePhi> findByG_SCMID(long groupId,
		long serviceConfigMappingId, int start, int end,
		OrderByComparator<PhiLePhi> orderByComparator) {
		return findByG_SCMID(groupId, serviceConfigMappingId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param start the lower bound of the range of phi le phis
	 * @param end the upper bound of the range of phi le phis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching phi le phis
	 */
	@Override
	public List<PhiLePhi> findByG_SCMID(long groupId,
		long serviceConfigMappingId, int start, int end,
		OrderByComparator<PhiLePhi> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SCMID;
			finderArgs = new Object[] { groupId, serviceConfigMappingId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_SCMID;
			finderArgs = new Object[] {
					groupId, serviceConfigMappingId,
					
					start, end, orderByComparator
				};
		}

		List<PhiLePhi> list = null;

		if (retrieveFromCache) {
			list = (List<PhiLePhi>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PhiLePhi phiLePhi : list) {
					if ((groupId != phiLePhi.getGroupId()) ||
							(serviceConfigMappingId != phiLePhi.getServiceConfigMappingId())) {
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

			query.append(_SQL_SELECT_PHILEPHI_WHERE);

			query.append(_FINDER_COLUMN_G_SCMID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_SCMID_SERVICECONFIGMAPPINGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PhiLePhiModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceConfigMappingId);

				if (!pagination) {
					list = (List<PhiLePhi>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PhiLePhi>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching phi le phi
	 * @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi findByG_SCMID_First(long groupId,
		long serviceConfigMappingId,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException {
		PhiLePhi phiLePhi = fetchByG_SCMID_First(groupId,
				serviceConfigMappingId, orderByComparator);

		if (phiLePhi != null) {
			return phiLePhi;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceConfigMappingId=");
		msg.append(serviceConfigMappingId);

		msg.append("}");

		throw new NoSuchPhiLePhiException(msg.toString());
	}

	/**
	 * Returns the first phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi fetchByG_SCMID_First(long groupId,
		long serviceConfigMappingId,
		OrderByComparator<PhiLePhi> orderByComparator) {
		List<PhiLePhi> list = findByG_SCMID(groupId, serviceConfigMappingId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching phi le phi
	 * @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi findByG_SCMID_Last(long groupId,
		long serviceConfigMappingId,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException {
		PhiLePhi phiLePhi = fetchByG_SCMID_Last(groupId,
				serviceConfigMappingId, orderByComparator);

		if (phiLePhi != null) {
			return phiLePhi;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceConfigMappingId=");
		msg.append(serviceConfigMappingId);

		msg.append("}");

		throw new NoSuchPhiLePhiException(msg.toString());
	}

	/**
	 * Returns the last phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	 */
	@Override
	public PhiLePhi fetchByG_SCMID_Last(long groupId,
		long serviceConfigMappingId,
		OrderByComparator<PhiLePhi> orderByComparator) {
		int count = countByG_SCMID(groupId, serviceConfigMappingId);

		if (count == 0) {
			return null;
		}

		List<PhiLePhi> list = findByG_SCMID(groupId, serviceConfigMappingId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the phi le phis before and after the current phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	 *
	 * @param phiLePhiId the primary key of the current phi le phi
	 * @param groupId the group ID
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next phi le phi
	 * @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	 */
	@Override
	public PhiLePhi[] findByG_SCMID_PrevAndNext(long phiLePhiId, long groupId,
		long serviceConfigMappingId,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException {
		PhiLePhi phiLePhi = findByPrimaryKey(phiLePhiId);

		Session session = null;

		try {
			session = openSession();

			PhiLePhi[] array = new PhiLePhiImpl[3];

			array[0] = getByG_SCMID_PrevAndNext(session, phiLePhi, groupId,
					serviceConfigMappingId, orderByComparator, true);

			array[1] = phiLePhi;

			array[2] = getByG_SCMID_PrevAndNext(session, phiLePhi, groupId,
					serviceConfigMappingId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PhiLePhi getByG_SCMID_PrevAndNext(Session session,
		PhiLePhi phiLePhi, long groupId, long serviceConfigMappingId,
		OrderByComparator<PhiLePhi> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PHILEPHI_WHERE);

		query.append(_FINDER_COLUMN_G_SCMID_GROUPID_2);

		query.append(_FINDER_COLUMN_G_SCMID_SERVICECONFIGMAPPINGID_2);

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
			query.append(PhiLePhiModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(serviceConfigMappingId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(phiLePhi);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PhiLePhi> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceConfigMappingId the service config mapping ID
	 */
	@Override
	public void removeByG_SCMID(long groupId, long serviceConfigMappingId) {
		for (PhiLePhi phiLePhi : findByG_SCMID(groupId, serviceConfigMappingId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(phiLePhi);
		}
	}

	/**
	 * Returns the number of phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceConfigMappingId the service config mapping ID
	 * @return the number of matching phi le phis
	 */
	@Override
	public int countByG_SCMID(long groupId, long serviceConfigMappingId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_SCMID;

		Object[] finderArgs = new Object[] { groupId, serviceConfigMappingId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PHILEPHI_WHERE);

			query.append(_FINDER_COLUMN_G_SCMID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_SCMID_SERVICECONFIGMAPPINGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceConfigMappingId);

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

	private static final String _FINDER_COLUMN_G_SCMID_GROUPID_2 = "phiLePhi.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_SCMID_SERVICECONFIGMAPPINGID_2 = "phiLePhi.serviceConfigMappingId = ?";

	public PhiLePhiPersistenceImpl() {
		setModelClass(PhiLePhi.class);

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
	 * Caches the phi le phi in the entity cache if it is enabled.
	 *
	 * @param phiLePhi the phi le phi
	 */
	@Override
	public void cacheResult(PhiLePhi phiLePhi) {
		entityCache.putResult(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiImpl.class, phiLePhi.getPrimaryKey(), phiLePhi);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { phiLePhi.getUuid(), phiLePhi.getGroupId() }, phiLePhi);

		phiLePhi.resetOriginalValues();
	}

	/**
	 * Caches the phi le phis in the entity cache if it is enabled.
	 *
	 * @param phiLePhis the phi le phis
	 */
	@Override
	public void cacheResult(List<PhiLePhi> phiLePhis) {
		for (PhiLePhi phiLePhi : phiLePhis) {
			if (entityCache.getResult(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
						PhiLePhiImpl.class, phiLePhi.getPrimaryKey()) == null) {
				cacheResult(phiLePhi);
			}
			else {
				phiLePhi.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all phi le phis.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PhiLePhiImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the phi le phi.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PhiLePhi phiLePhi) {
		entityCache.removeResult(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiImpl.class, phiLePhi.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PhiLePhiModelImpl)phiLePhi, true);
	}

	@Override
	public void clearCache(List<PhiLePhi> phiLePhis) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PhiLePhi phiLePhi : phiLePhis) {
			entityCache.removeResult(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
				PhiLePhiImpl.class, phiLePhi.getPrimaryKey());

			clearUniqueFindersCache((PhiLePhiModelImpl)phiLePhi, true);
		}
	}

	protected void cacheUniqueFindersCache(PhiLePhiModelImpl phiLePhiModelImpl) {
		Object[] args = new Object[] {
				phiLePhiModelImpl.getUuid(), phiLePhiModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			phiLePhiModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PhiLePhiModelImpl phiLePhiModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					phiLePhiModelImpl.getUuid(), phiLePhiModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((phiLePhiModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					phiLePhiModelImpl.getOriginalUuid(),
					phiLePhiModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new phi le phi with the primary key. Does not add the phi le phi to the database.
	 *
	 * @param phiLePhiId the primary key for the new phi le phi
	 * @return the new phi le phi
	 */
	@Override
	public PhiLePhi create(long phiLePhiId) {
		PhiLePhi phiLePhi = new PhiLePhiImpl();

		phiLePhi.setNew(true);
		phiLePhi.setPrimaryKey(phiLePhiId);

		String uuid = PortalUUIDUtil.generate();

		phiLePhi.setUuid(uuid);

		phiLePhi.setCompanyId(companyProvider.getCompanyId());

		return phiLePhi;
	}

	/**
	 * Removes the phi le phi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param phiLePhiId the primary key of the phi le phi
	 * @return the phi le phi that was removed
	 * @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	 */
	@Override
	public PhiLePhi remove(long phiLePhiId) throws NoSuchPhiLePhiException {
		return remove((Serializable)phiLePhiId);
	}

	/**
	 * Removes the phi le phi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the phi le phi
	 * @return the phi le phi that was removed
	 * @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	 */
	@Override
	public PhiLePhi remove(Serializable primaryKey)
		throws NoSuchPhiLePhiException {
		Session session = null;

		try {
			session = openSession();

			PhiLePhi phiLePhi = (PhiLePhi)session.get(PhiLePhiImpl.class,
					primaryKey);

			if (phiLePhi == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPhiLePhiException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(phiLePhi);
		}
		catch (NoSuchPhiLePhiException nsee) {
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
	protected PhiLePhi removeImpl(PhiLePhi phiLePhi) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(phiLePhi)) {
				phiLePhi = (PhiLePhi)session.get(PhiLePhiImpl.class,
						phiLePhi.getPrimaryKeyObj());
			}

			if (phiLePhi != null) {
				session.delete(phiLePhi);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (phiLePhi != null) {
			clearCache(phiLePhi);
		}

		return phiLePhi;
	}

	@Override
	public PhiLePhi updateImpl(PhiLePhi phiLePhi) {
		boolean isNew = phiLePhi.isNew();

		if (!(phiLePhi instanceof PhiLePhiModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(phiLePhi.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(phiLePhi);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in phiLePhi proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PhiLePhi implementation " +
				phiLePhi.getClass());
		}

		PhiLePhiModelImpl phiLePhiModelImpl = (PhiLePhiModelImpl)phiLePhi;

		if (Validator.isNull(phiLePhi.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			phiLePhi.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (phiLePhi.getCreateDate() == null)) {
			if (serviceContext == null) {
				phiLePhi.setCreateDate(now);
			}
			else {
				phiLePhi.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!phiLePhiModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				phiLePhi.setModifiedDate(now);
			}
			else {
				phiLePhi.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (phiLePhi.isNew()) {
				session.save(phiLePhi);

				phiLePhi.setNew(false);
			}
			else {
				phiLePhi = (PhiLePhi)session.merge(phiLePhi);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PhiLePhiModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { phiLePhiModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					phiLePhiModelImpl.getUuid(),
					phiLePhiModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					phiLePhiModelImpl.getGroupId(),
					phiLePhiModelImpl.getServiceConfigMappingId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SCMID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SCMID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((phiLePhiModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { phiLePhiModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { phiLePhiModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((phiLePhiModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						phiLePhiModelImpl.getOriginalUuid(),
						phiLePhiModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						phiLePhiModelImpl.getUuid(),
						phiLePhiModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((phiLePhiModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SCMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						phiLePhiModelImpl.getOriginalGroupId(),
						phiLePhiModelImpl.getOriginalServiceConfigMappingId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SCMID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SCMID,
					args);

				args = new Object[] {
						phiLePhiModelImpl.getGroupId(),
						phiLePhiModelImpl.getServiceConfigMappingId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_SCMID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SCMID,
					args);
			}
		}

		entityCache.putResult(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
			PhiLePhiImpl.class, phiLePhi.getPrimaryKey(), phiLePhi, false);

		clearUniqueFindersCache(phiLePhiModelImpl, false);
		cacheUniqueFindersCache(phiLePhiModelImpl);

		phiLePhi.resetOriginalValues();

		return phiLePhi;
	}

	/**
	 * Returns the phi le phi with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the phi le phi
	 * @return the phi le phi
	 * @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	 */
	@Override
	public PhiLePhi findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPhiLePhiException {
		PhiLePhi phiLePhi = fetchByPrimaryKey(primaryKey);

		if (phiLePhi == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPhiLePhiException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return phiLePhi;
	}

	/**
	 * Returns the phi le phi with the primary key or throws a {@link NoSuchPhiLePhiException} if it could not be found.
	 *
	 * @param phiLePhiId the primary key of the phi le phi
	 * @return the phi le phi
	 * @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	 */
	@Override
	public PhiLePhi findByPrimaryKey(long phiLePhiId)
		throws NoSuchPhiLePhiException {
		return findByPrimaryKey((Serializable)phiLePhiId);
	}

	/**
	 * Returns the phi le phi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the phi le phi
	 * @return the phi le phi, or <code>null</code> if a phi le phi with the primary key could not be found
	 */
	@Override
	public PhiLePhi fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
				PhiLePhiImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PhiLePhi phiLePhi = (PhiLePhi)serializable;

		if (phiLePhi == null) {
			Session session = null;

			try {
				session = openSession();

				phiLePhi = (PhiLePhi)session.get(PhiLePhiImpl.class, primaryKey);

				if (phiLePhi != null) {
					cacheResult(phiLePhi);
				}
				else {
					entityCache.putResult(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
						PhiLePhiImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
					PhiLePhiImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return phiLePhi;
	}

	/**
	 * Returns the phi le phi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param phiLePhiId the primary key of the phi le phi
	 * @return the phi le phi, or <code>null</code> if a phi le phi with the primary key could not be found
	 */
	@Override
	public PhiLePhi fetchByPrimaryKey(long phiLePhiId) {
		return fetchByPrimaryKey((Serializable)phiLePhiId);
	}

	@Override
	public Map<Serializable, PhiLePhi> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PhiLePhi> map = new HashMap<Serializable, PhiLePhi>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PhiLePhi phiLePhi = fetchByPrimaryKey(primaryKey);

			if (phiLePhi != null) {
				map.put(primaryKey, phiLePhi);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
					PhiLePhiImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PhiLePhi)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PHILEPHI_WHERE_PKS_IN);

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

			for (PhiLePhi phiLePhi : (List<PhiLePhi>)q.list()) {
				map.put(phiLePhi.getPrimaryKeyObj(), phiLePhi);

				cacheResult(phiLePhi);

				uncachedPrimaryKeys.remove(phiLePhi.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PhiLePhiModelImpl.ENTITY_CACHE_ENABLED,
					PhiLePhiImpl.class, primaryKey, nullModel);
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
	 * Returns all the phi le phis.
	 *
	 * @return the phi le phis
	 */
	@Override
	public List<PhiLePhi> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the phi le phis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of phi le phis
	 * @param end the upper bound of the range of phi le phis (not inclusive)
	 * @return the range of phi le phis
	 */
	@Override
	public List<PhiLePhi> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the phi le phis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of phi le phis
	 * @param end the upper bound of the range of phi le phis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of phi le phis
	 */
	@Override
	public List<PhiLePhi> findAll(int start, int end,
		OrderByComparator<PhiLePhi> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the phi le phis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of phi le phis
	 * @param end the upper bound of the range of phi le phis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of phi le phis
	 */
	@Override
	public List<PhiLePhi> findAll(int start, int end,
		OrderByComparator<PhiLePhi> orderByComparator, boolean retrieveFromCache) {
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

		List<PhiLePhi> list = null;

		if (retrieveFromCache) {
			list = (List<PhiLePhi>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PHILEPHI);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PHILEPHI;

				if (pagination) {
					sql = sql.concat(PhiLePhiModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PhiLePhi>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PhiLePhi>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the phi le phis from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PhiLePhi phiLePhi : findAll()) {
			remove(phiLePhi);
		}
	}

	/**
	 * Returns the number of phi le phis.
	 *
	 * @return the number of phi le phis
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PHILEPHI);

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
		return PhiLePhiModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the phi le phi persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PhiLePhiImpl.class.getName());
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
	private static final String _SQL_SELECT_PHILEPHI = "SELECT phiLePhi FROM PhiLePhi phiLePhi";
	private static final String _SQL_SELECT_PHILEPHI_WHERE_PKS_IN = "SELECT phiLePhi FROM PhiLePhi phiLePhi WHERE phiLePhiId IN (";
	private static final String _SQL_SELECT_PHILEPHI_WHERE = "SELECT phiLePhi FROM PhiLePhi phiLePhi WHERE ";
	private static final String _SQL_COUNT_PHILEPHI = "SELECT COUNT(phiLePhi) FROM PhiLePhi phiLePhi";
	private static final String _SQL_COUNT_PHILEPHI_WHERE = "SELECT COUNT(phiLePhi) FROM PhiLePhi phiLePhi WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "phiLePhi.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PhiLePhi exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PhiLePhi exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PhiLePhiPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}