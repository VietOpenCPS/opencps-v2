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

import org.opencps.backend.processmgt.exception.NoSuchStepAllowanceException;
import org.opencps.backend.processmgt.model.StepAllowance;
import org.opencps.backend.processmgt.model.impl.StepAllowanceImpl;
import org.opencps.backend.processmgt.model.impl.StepAllowanceModelImpl;
import org.opencps.backend.processmgt.service.persistence.StepAllowancePK;
import org.opencps.backend.processmgt.service.persistence.StepAllowancePersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the step allowance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see StepAllowancePersistence
 * @see org.opencps.backend.processmgt.service.persistence.StepAllowanceUtil
 * @generated
 */
@ProviderType
public class StepAllowancePersistenceImpl extends BasePersistenceImpl<StepAllowance>
	implements StepAllowancePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StepAllowanceUtil} to access the step allowance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StepAllowanceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceModelImpl.FINDER_CACHE_ENABLED,
			StepAllowanceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceModelImpl.FINDER_CACHE_ENABLED,
			StepAllowanceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceModelImpl.FINDER_CACHE_ENABLED,
			StepAllowanceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceModelImpl.FINDER_CACHE_ENABLED,
			StepAllowanceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			StepAllowanceModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the step allowances where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching step allowances
	 */
	@Override
	public List<StepAllowance> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the step allowances where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of step allowances
	 * @param end the upper bound of the range of step allowances (not inclusive)
	 * @return the range of matching step allowances
	 */
	@Override
	public List<StepAllowance> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the step allowances where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of step allowances
	 * @param end the upper bound of the range of step allowances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching step allowances
	 */
	@Override
	public List<StepAllowance> findByUuid(String uuid, int start, int end,
		OrderByComparator<StepAllowance> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the step allowances where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of step allowances
	 * @param end the upper bound of the range of step allowances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching step allowances
	 */
	@Override
	public List<StepAllowance> findByUuid(String uuid, int start, int end,
		OrderByComparator<StepAllowance> orderByComparator,
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

		List<StepAllowance> list = null;

		if (retrieveFromCache) {
			list = (List<StepAllowance>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StepAllowance stepAllowance : list) {
					if (!Objects.equals(uuid, stepAllowance.getUuid())) {
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

			query.append(_SQL_SELECT_STEPALLOWANCE_WHERE);

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
				query.append(StepAllowanceModelImpl.ORDER_BY_JPQL);
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
					list = (List<StepAllowance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StepAllowance>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first step allowance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching step allowance
	 * @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance findByUuid_First(String uuid,
		OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException {
		StepAllowance stepAllowance = fetchByUuid_First(uuid, orderByComparator);

		if (stepAllowance != null) {
			return stepAllowance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStepAllowanceException(msg.toString());
	}

	/**
	 * Returns the first step allowance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching step allowance, or <code>null</code> if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance fetchByUuid_First(String uuid,
		OrderByComparator<StepAllowance> orderByComparator) {
		List<StepAllowance> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last step allowance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching step allowance
	 * @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance findByUuid_Last(String uuid,
		OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException {
		StepAllowance stepAllowance = fetchByUuid_Last(uuid, orderByComparator);

		if (stepAllowance != null) {
			return stepAllowance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStepAllowanceException(msg.toString());
	}

	/**
	 * Returns the last step allowance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching step allowance, or <code>null</code> if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance fetchByUuid_Last(String uuid,
		OrderByComparator<StepAllowance> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<StepAllowance> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the step allowances before and after the current step allowance in the ordered set where uuid = &#63;.
	 *
	 * @param stepAllowancePK the primary key of the current step allowance
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next step allowance
	 * @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	 */
	@Override
	public StepAllowance[] findByUuid_PrevAndNext(
		StepAllowancePK stepAllowancePK, String uuid,
		OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException {
		StepAllowance stepAllowance = findByPrimaryKey(stepAllowancePK);

		Session session = null;

		try {
			session = openSession();

			StepAllowance[] array = new StepAllowanceImpl[3];

			array[0] = getByUuid_PrevAndNext(session, stepAllowance, uuid,
					orderByComparator, true);

			array[1] = stepAllowance;

			array[2] = getByUuid_PrevAndNext(session, stepAllowance, uuid,
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

	protected StepAllowance getByUuid_PrevAndNext(Session session,
		StepAllowance stepAllowance, String uuid,
		OrderByComparator<StepAllowance> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_STEPALLOWANCE_WHERE);

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
			query.append(StepAllowanceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(stepAllowance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<StepAllowance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the step allowances where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (StepAllowance stepAllowance : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(stepAllowance);
		}
	}

	/**
	 * Returns the number of step allowances where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching step allowances
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_STEPALLOWANCE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "stepAllowance.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "stepAllowance.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(stepAllowance.uuid IS NULL OR stepAllowance.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceModelImpl.FINDER_CACHE_ENABLED,
			StepAllowanceImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			StepAllowanceModelImpl.UUID_COLUMN_BITMASK |
			StepAllowanceModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the step allowance where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchStepAllowanceException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching step allowance
	 * @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance findByUUID_G(String uuid, long groupId)
		throws NoSuchStepAllowanceException {
		StepAllowance stepAllowance = fetchByUUID_G(uuid, groupId);

		if (stepAllowance == null) {
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

			throw new NoSuchStepAllowanceException(msg.toString());
		}

		return stepAllowance;
	}

	/**
	 * Returns the step allowance where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching step allowance, or <code>null</code> if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the step allowance where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching step allowance, or <code>null</code> if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof StepAllowance) {
			StepAllowance stepAllowance = (StepAllowance)result;

			if (!Objects.equals(uuid, stepAllowance.getUuid()) ||
					(groupId != stepAllowance.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_STEPALLOWANCE_WHERE);

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

				List<StepAllowance> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					StepAllowance stepAllowance = list.get(0);

					result = stepAllowance;

					cacheResult(stepAllowance);

					if ((stepAllowance.getUuid() == null) ||
							!stepAllowance.getUuid().equals(uuid) ||
							(stepAllowance.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, stepAllowance);
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
			return (StepAllowance)result;
		}
	}

	/**
	 * Removes the step allowance where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the step allowance that was removed
	 */
	@Override
	public StepAllowance removeByUUID_G(String uuid, long groupId)
		throws NoSuchStepAllowanceException {
		StepAllowance stepAllowance = findByUUID_G(uuid, groupId);

		return remove(stepAllowance);
	}

	/**
	 * Returns the number of step allowances where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching step allowances
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_STEPALLOWANCE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "stepAllowance.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "stepAllowance.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(stepAllowance.uuid IS NULL OR stepAllowance.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "stepAllowance.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceModelImpl.FINDER_CACHE_ENABLED,
			StepAllowanceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceModelImpl.FINDER_CACHE_ENABLED,
			StepAllowanceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			StepAllowanceModelImpl.UUID_COLUMN_BITMASK |
			StepAllowanceModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the step allowances where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching step allowances
	 */
	@Override
	public List<StepAllowance> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the step allowances where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of step allowances
	 * @param end the upper bound of the range of step allowances (not inclusive)
	 * @return the range of matching step allowances
	 */
	@Override
	public List<StepAllowance> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the step allowances where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of step allowances
	 * @param end the upper bound of the range of step allowances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching step allowances
	 */
	@Override
	public List<StepAllowance> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<StepAllowance> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the step allowances where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of step allowances
	 * @param end the upper bound of the range of step allowances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching step allowances
	 */
	@Override
	public List<StepAllowance> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<StepAllowance> orderByComparator,
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

		List<StepAllowance> list = null;

		if (retrieveFromCache) {
			list = (List<StepAllowance>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StepAllowance stepAllowance : list) {
					if (!Objects.equals(uuid, stepAllowance.getUuid()) ||
							(companyId != stepAllowance.getCompanyId())) {
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

			query.append(_SQL_SELECT_STEPALLOWANCE_WHERE);

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
				query.append(StepAllowanceModelImpl.ORDER_BY_JPQL);
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
					list = (List<StepAllowance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StepAllowance>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first step allowance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching step allowance
	 * @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException {
		StepAllowance stepAllowance = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (stepAllowance != null) {
			return stepAllowance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStepAllowanceException(msg.toString());
	}

	/**
	 * Returns the first step allowance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching step allowance, or <code>null</code> if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<StepAllowance> orderByComparator) {
		List<StepAllowance> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last step allowance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching step allowance
	 * @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException {
		StepAllowance stepAllowance = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (stepAllowance != null) {
			return stepAllowance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStepAllowanceException(msg.toString());
	}

	/**
	 * Returns the last step allowance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching step allowance, or <code>null</code> if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<StepAllowance> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<StepAllowance> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the step allowances before and after the current step allowance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param stepAllowancePK the primary key of the current step allowance
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next step allowance
	 * @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	 */
	@Override
	public StepAllowance[] findByUuid_C_PrevAndNext(
		StepAllowancePK stepAllowancePK, String uuid, long companyId,
		OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException {
		StepAllowance stepAllowance = findByPrimaryKey(stepAllowancePK);

		Session session = null;

		try {
			session = openSession();

			StepAllowance[] array = new StepAllowanceImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, stepAllowance, uuid,
					companyId, orderByComparator, true);

			array[1] = stepAllowance;

			array[2] = getByUuid_C_PrevAndNext(session, stepAllowance, uuid,
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

	protected StepAllowance getByUuid_C_PrevAndNext(Session session,
		StepAllowance stepAllowance, String uuid, long companyId,
		OrderByComparator<StepAllowance> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_STEPALLOWANCE_WHERE);

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
			query.append(StepAllowanceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(stepAllowance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<StepAllowance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the step allowances where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (StepAllowance stepAllowance : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(stepAllowance);
		}
	}

	/**
	 * Returns the number of step allowances where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching step allowances
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_STEPALLOWANCE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "stepAllowance.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "stepAllowance.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(stepAllowance.uuid IS NULL OR stepAllowance.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "stepAllowance.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S_ID = new FinderPath(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceModelImpl.FINDER_CACHE_ENABLED,
			StepAllowanceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByP_S_ID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID =
		new FinderPath(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceModelImpl.FINDER_CACHE_ENABLED,
			StepAllowanceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByP_S_ID", new String[] { Long.class.getName() },
			StepAllowanceModelImpl.PROCESSSTEPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_S_ID = new FinderPath(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_S_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the step allowances where processStepId = &#63;.
	 *
	 * @param processStepId the process step ID
	 * @return the matching step allowances
	 */
	@Override
	public List<StepAllowance> findByP_S_ID(long processStepId) {
		return findByP_S_ID(processStepId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the step allowances where processStepId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param processStepId the process step ID
	 * @param start the lower bound of the range of step allowances
	 * @param end the upper bound of the range of step allowances (not inclusive)
	 * @return the range of matching step allowances
	 */
	@Override
	public List<StepAllowance> findByP_S_ID(long processStepId, int start,
		int end) {
		return findByP_S_ID(processStepId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the step allowances where processStepId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param processStepId the process step ID
	 * @param start the lower bound of the range of step allowances
	 * @param end the upper bound of the range of step allowances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching step allowances
	 */
	@Override
	public List<StepAllowance> findByP_S_ID(long processStepId, int start,
		int end, OrderByComparator<StepAllowance> orderByComparator) {
		return findByP_S_ID(processStepId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the step allowances where processStepId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param processStepId the process step ID
	 * @param start the lower bound of the range of step allowances
	 * @param end the upper bound of the range of step allowances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching step allowances
	 */
	@Override
	public List<StepAllowance> findByP_S_ID(long processStepId, int start,
		int end, OrderByComparator<StepAllowance> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID;
			finderArgs = new Object[] { processStepId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S_ID;
			finderArgs = new Object[] {
					processStepId,
					
					start, end, orderByComparator
				};
		}

		List<StepAllowance> list = null;

		if (retrieveFromCache) {
			list = (List<StepAllowance>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StepAllowance stepAllowance : list) {
					if ((processStepId != stepAllowance.getProcessStepId())) {
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

			query.append(_SQL_SELECT_STEPALLOWANCE_WHERE);

			query.append(_FINDER_COLUMN_P_S_ID_PROCESSSTEPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(StepAllowanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(processStepId);

				if (!pagination) {
					list = (List<StepAllowance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StepAllowance>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first step allowance in the ordered set where processStepId = &#63;.
	 *
	 * @param processStepId the process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching step allowance
	 * @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance findByP_S_ID_First(long processStepId,
		OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException {
		StepAllowance stepAllowance = fetchByP_S_ID_First(processStepId,
				orderByComparator);

		if (stepAllowance != null) {
			return stepAllowance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("processStepId=");
		msg.append(processStepId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStepAllowanceException(msg.toString());
	}

	/**
	 * Returns the first step allowance in the ordered set where processStepId = &#63;.
	 *
	 * @param processStepId the process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching step allowance, or <code>null</code> if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance fetchByP_S_ID_First(long processStepId,
		OrderByComparator<StepAllowance> orderByComparator) {
		List<StepAllowance> list = findByP_S_ID(processStepId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last step allowance in the ordered set where processStepId = &#63;.
	 *
	 * @param processStepId the process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching step allowance
	 * @throws NoSuchStepAllowanceException if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance findByP_S_ID_Last(long processStepId,
		OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException {
		StepAllowance stepAllowance = fetchByP_S_ID_Last(processStepId,
				orderByComparator);

		if (stepAllowance != null) {
			return stepAllowance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("processStepId=");
		msg.append(processStepId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStepAllowanceException(msg.toString());
	}

	/**
	 * Returns the last step allowance in the ordered set where processStepId = &#63;.
	 *
	 * @param processStepId the process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching step allowance, or <code>null</code> if a matching step allowance could not be found
	 */
	@Override
	public StepAllowance fetchByP_S_ID_Last(long processStepId,
		OrderByComparator<StepAllowance> orderByComparator) {
		int count = countByP_S_ID(processStepId);

		if (count == 0) {
			return null;
		}

		List<StepAllowance> list = findByP_S_ID(processStepId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the step allowances before and after the current step allowance in the ordered set where processStepId = &#63;.
	 *
	 * @param stepAllowancePK the primary key of the current step allowance
	 * @param processStepId the process step ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next step allowance
	 * @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	 */
	@Override
	public StepAllowance[] findByP_S_ID_PrevAndNext(
		StepAllowancePK stepAllowancePK, long processStepId,
		OrderByComparator<StepAllowance> orderByComparator)
		throws NoSuchStepAllowanceException {
		StepAllowance stepAllowance = findByPrimaryKey(stepAllowancePK);

		Session session = null;

		try {
			session = openSession();

			StepAllowance[] array = new StepAllowanceImpl[3];

			array[0] = getByP_S_ID_PrevAndNext(session, stepAllowance,
					processStepId, orderByComparator, true);

			array[1] = stepAllowance;

			array[2] = getByP_S_ID_PrevAndNext(session, stepAllowance,
					processStepId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected StepAllowance getByP_S_ID_PrevAndNext(Session session,
		StepAllowance stepAllowance, long processStepId,
		OrderByComparator<StepAllowance> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_STEPALLOWANCE_WHERE);

		query.append(_FINDER_COLUMN_P_S_ID_PROCESSSTEPID_2);

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
			query.append(StepAllowanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(processStepId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(stepAllowance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<StepAllowance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the step allowances where processStepId = &#63; from the database.
	 *
	 * @param processStepId the process step ID
	 */
	@Override
	public void removeByP_S_ID(long processStepId) {
		for (StepAllowance stepAllowance : findByP_S_ID(processStepId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(stepAllowance);
		}
	}

	/**
	 * Returns the number of step allowances where processStepId = &#63;.
	 *
	 * @param processStepId the process step ID
	 * @return the number of matching step allowances
	 */
	@Override
	public int countByP_S_ID(long processStepId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P_S_ID;

		Object[] finderArgs = new Object[] { processStepId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_STEPALLOWANCE_WHERE);

			query.append(_FINDER_COLUMN_P_S_ID_PROCESSSTEPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(processStepId);

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

	private static final String _FINDER_COLUMN_P_S_ID_PROCESSSTEPID_2 = "stepAllowance.id.processStepId = ?";

	public StepAllowancePersistenceImpl() {
		setModelClass(StepAllowance.class);
	}

	/**
	 * Caches the step allowance in the entity cache if it is enabled.
	 *
	 * @param stepAllowance the step allowance
	 */
	@Override
	public void cacheResult(StepAllowance stepAllowance) {
		entityCache.putResult(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceImpl.class, stepAllowance.getPrimaryKey(),
			stepAllowance);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { stepAllowance.getUuid(), stepAllowance.getGroupId() },
			stepAllowance);

		stepAllowance.resetOriginalValues();
	}

	/**
	 * Caches the step allowances in the entity cache if it is enabled.
	 *
	 * @param stepAllowances the step allowances
	 */
	@Override
	public void cacheResult(List<StepAllowance> stepAllowances) {
		for (StepAllowance stepAllowance : stepAllowances) {
			if (entityCache.getResult(
						StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
						StepAllowanceImpl.class, stepAllowance.getPrimaryKey()) == null) {
				cacheResult(stepAllowance);
			}
			else {
				stepAllowance.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all step allowances.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StepAllowanceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the step allowance.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StepAllowance stepAllowance) {
		entityCache.removeResult(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceImpl.class, stepAllowance.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((StepAllowanceModelImpl)stepAllowance, true);
	}

	@Override
	public void clearCache(List<StepAllowance> stepAllowances) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StepAllowance stepAllowance : stepAllowances) {
			entityCache.removeResult(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
				StepAllowanceImpl.class, stepAllowance.getPrimaryKey());

			clearUniqueFindersCache((StepAllowanceModelImpl)stepAllowance, true);
		}
	}

	protected void cacheUniqueFindersCache(
		StepAllowanceModelImpl stepAllowanceModelImpl) {
		Object[] args = new Object[] {
				stepAllowanceModelImpl.getUuid(),
				stepAllowanceModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			stepAllowanceModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		StepAllowanceModelImpl stepAllowanceModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					stepAllowanceModelImpl.getUuid(),
					stepAllowanceModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((stepAllowanceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					stepAllowanceModelImpl.getOriginalUuid(),
					stepAllowanceModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new step allowance with the primary key. Does not add the step allowance to the database.
	 *
	 * @param stepAllowancePK the primary key for the new step allowance
	 * @return the new step allowance
	 */
	@Override
	public StepAllowance create(StepAllowancePK stepAllowancePK) {
		StepAllowance stepAllowance = new StepAllowanceImpl();

		stepAllowance.setNew(true);
		stepAllowance.setPrimaryKey(stepAllowancePK);

		String uuid = PortalUUIDUtil.generate();

		stepAllowance.setUuid(uuid);

		stepAllowance.setCompanyId(companyProvider.getCompanyId());

		return stepAllowance;
	}

	/**
	 * Removes the step allowance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stepAllowancePK the primary key of the step allowance
	 * @return the step allowance that was removed
	 * @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	 */
	@Override
	public StepAllowance remove(StepAllowancePK stepAllowancePK)
		throws NoSuchStepAllowanceException {
		return remove((Serializable)stepAllowancePK);
	}

	/**
	 * Removes the step allowance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the step allowance
	 * @return the step allowance that was removed
	 * @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	 */
	@Override
	public StepAllowance remove(Serializable primaryKey)
		throws NoSuchStepAllowanceException {
		Session session = null;

		try {
			session = openSession();

			StepAllowance stepAllowance = (StepAllowance)session.get(StepAllowanceImpl.class,
					primaryKey);

			if (stepAllowance == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStepAllowanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stepAllowance);
		}
		catch (NoSuchStepAllowanceException nsee) {
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
	protected StepAllowance removeImpl(StepAllowance stepAllowance) {
		stepAllowance = toUnwrappedModel(stepAllowance);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stepAllowance)) {
				stepAllowance = (StepAllowance)session.get(StepAllowanceImpl.class,
						stepAllowance.getPrimaryKeyObj());
			}

			if (stepAllowance != null) {
				session.delete(stepAllowance);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stepAllowance != null) {
			clearCache(stepAllowance);
		}

		return stepAllowance;
	}

	@Override
	public StepAllowance updateImpl(StepAllowance stepAllowance) {
		stepAllowance = toUnwrappedModel(stepAllowance);

		boolean isNew = stepAllowance.isNew();

		StepAllowanceModelImpl stepAllowanceModelImpl = (StepAllowanceModelImpl)stepAllowance;

		if (Validator.isNull(stepAllowance.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			stepAllowance.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (stepAllowance.getCreateDate() == null)) {
			if (serviceContext == null) {
				stepAllowance.setCreateDate(now);
			}
			else {
				stepAllowance.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!stepAllowanceModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				stepAllowance.setModifiedDate(now);
			}
			else {
				stepAllowance.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (stepAllowance.isNew()) {
				session.save(stepAllowance);

				stepAllowance.setNew(false);
			}
			else {
				stepAllowance = (StepAllowance)session.merge(stepAllowance);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !StepAllowanceModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((stepAllowanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						stepAllowanceModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { stepAllowanceModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((stepAllowanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						stepAllowanceModelImpl.getOriginalUuid(),
						stepAllowanceModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						stepAllowanceModelImpl.getUuid(),
						stepAllowanceModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((stepAllowanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						stepAllowanceModelImpl.getOriginalProcessStepId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_P_S_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID,
					args);

				args = new Object[] { stepAllowanceModelImpl.getProcessStepId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_P_S_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID,
					args);
			}
		}

		entityCache.putResult(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
			StepAllowanceImpl.class, stepAllowance.getPrimaryKey(),
			stepAllowance, false);

		clearUniqueFindersCache(stepAllowanceModelImpl, false);
		cacheUniqueFindersCache(stepAllowanceModelImpl);

		stepAllowance.resetOriginalValues();

		return stepAllowance;
	}

	protected StepAllowance toUnwrappedModel(StepAllowance stepAllowance) {
		if (stepAllowance instanceof StepAllowanceImpl) {
			return stepAllowance;
		}

		StepAllowanceImpl stepAllowanceImpl = new StepAllowanceImpl();

		stepAllowanceImpl.setNew(stepAllowance.isNew());
		stepAllowanceImpl.setPrimaryKey(stepAllowance.getPrimaryKey());

		stepAllowanceImpl.setUuid(stepAllowance.getUuid());
		stepAllowanceImpl.setProcessStepId(stepAllowance.getProcessStepId());
		stepAllowanceImpl.setRoleId(stepAllowance.getRoleId());
		stepAllowanceImpl.setCompanyId(stepAllowance.getCompanyId());
		stepAllowanceImpl.setGroupId(stepAllowance.getGroupId());
		stepAllowanceImpl.setUserId(stepAllowance.getUserId());
		stepAllowanceImpl.setUserName(stepAllowance.getUserName());
		stepAllowanceImpl.setCreateDate(stepAllowance.getCreateDate());
		stepAllowanceImpl.setModifiedDate(stepAllowance.getModifiedDate());
		stepAllowanceImpl.setModerator(stepAllowance.getModerator());

		return stepAllowanceImpl;
	}

	/**
	 * Returns the step allowance with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the step allowance
	 * @return the step allowance
	 * @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	 */
	@Override
	public StepAllowance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStepAllowanceException {
		StepAllowance stepAllowance = fetchByPrimaryKey(primaryKey);

		if (stepAllowance == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStepAllowanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stepAllowance;
	}

	/**
	 * Returns the step allowance with the primary key or throws a {@link NoSuchStepAllowanceException} if it could not be found.
	 *
	 * @param stepAllowancePK the primary key of the step allowance
	 * @return the step allowance
	 * @throws NoSuchStepAllowanceException if a step allowance with the primary key could not be found
	 */
	@Override
	public StepAllowance findByPrimaryKey(StepAllowancePK stepAllowancePK)
		throws NoSuchStepAllowanceException {
		return findByPrimaryKey((Serializable)stepAllowancePK);
	}

	/**
	 * Returns the step allowance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the step allowance
	 * @return the step allowance, or <code>null</code> if a step allowance with the primary key could not be found
	 */
	@Override
	public StepAllowance fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
				StepAllowanceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StepAllowance stepAllowance = (StepAllowance)serializable;

		if (stepAllowance == null) {
			Session session = null;

			try {
				session = openSession();

				stepAllowance = (StepAllowance)session.get(StepAllowanceImpl.class,
						primaryKey);

				if (stepAllowance != null) {
					cacheResult(stepAllowance);
				}
				else {
					entityCache.putResult(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
						StepAllowanceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StepAllowanceModelImpl.ENTITY_CACHE_ENABLED,
					StepAllowanceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stepAllowance;
	}

	/**
	 * Returns the step allowance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stepAllowancePK the primary key of the step allowance
	 * @return the step allowance, or <code>null</code> if a step allowance with the primary key could not be found
	 */
	@Override
	public StepAllowance fetchByPrimaryKey(StepAllowancePK stepAllowancePK) {
		return fetchByPrimaryKey((Serializable)stepAllowancePK);
	}

	@Override
	public Map<Serializable, StepAllowance> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StepAllowance> map = new HashMap<Serializable, StepAllowance>();

		for (Serializable primaryKey : primaryKeys) {
			StepAllowance stepAllowance = fetchByPrimaryKey(primaryKey);

			if (stepAllowance != null) {
				map.put(primaryKey, stepAllowance);
			}
		}

		return map;
	}

	/**
	 * Returns all the step allowances.
	 *
	 * @return the step allowances
	 */
	@Override
	public List<StepAllowance> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the step allowances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of step allowances
	 * @param end the upper bound of the range of step allowances (not inclusive)
	 * @return the range of step allowances
	 */
	@Override
	public List<StepAllowance> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the step allowances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of step allowances
	 * @param end the upper bound of the range of step allowances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of step allowances
	 */
	@Override
	public List<StepAllowance> findAll(int start, int end,
		OrderByComparator<StepAllowance> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the step allowances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of step allowances
	 * @param end the upper bound of the range of step allowances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of step allowances
	 */
	@Override
	public List<StepAllowance> findAll(int start, int end,
		OrderByComparator<StepAllowance> orderByComparator,
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

		List<StepAllowance> list = null;

		if (retrieveFromCache) {
			list = (List<StepAllowance>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STEPALLOWANCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STEPALLOWANCE;

				if (pagination) {
					sql = sql.concat(StepAllowanceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StepAllowance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StepAllowance>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the step allowances from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StepAllowance stepAllowance : findAll()) {
			remove(stepAllowance);
		}
	}

	/**
	 * Returns the number of step allowances.
	 *
	 * @return the number of step allowances
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STEPALLOWANCE);

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
		return StepAllowanceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the step allowance persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StepAllowanceImpl.class.getName());
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
	private static final String _SQL_SELECT_STEPALLOWANCE = "SELECT stepAllowance FROM StepAllowance stepAllowance";
	private static final String _SQL_SELECT_STEPALLOWANCE_WHERE = "SELECT stepAllowance FROM StepAllowance stepAllowance WHERE ";
	private static final String _SQL_COUNT_STEPALLOWANCE = "SELECT COUNT(stepAllowance) FROM StepAllowance stepAllowance";
	private static final String _SQL_COUNT_STEPALLOWANCE_WHERE = "SELECT COUNT(stepAllowance) FROM StepAllowance stepAllowance WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stepAllowance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StepAllowance exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No StepAllowance exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(StepAllowancePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}