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

package backend.feedback.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import backend.feedback.exception.NoSuchEvaluationException;

import backend.feedback.model.Evaluation;
import backend.feedback.model.impl.EvaluationImpl;
import backend.feedback.model.impl.EvaluationModelImpl;

import backend.feedback.service.persistence.EvaluationPersistence;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

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
 * The persistence implementation for the evaluation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sondt
 * @see EvaluationPersistence
 * @see backend.feedback.service.persistence.EvaluationUtil
 * @generated
 */
@ProviderType
public class EvaluationPersistenceImpl extends BasePersistenceImpl<Evaluation>
	implements EvaluationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EvaluationUtil} to access the evaluation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EvaluationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, EvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, EvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, EvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, EvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			EvaluationModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the evaluations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching evaluations
	 */
	@Override
	public List<Evaluation> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the evaluations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @return the range of matching evaluations
	 */
	@Override
	public List<Evaluation> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the evaluations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching evaluations
	 */
	@Override
	public List<Evaluation> findByUuid(String uuid, int start, int end,
		OrderByComparator<Evaluation> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the evaluations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching evaluations
	 */
	@Override
	public List<Evaluation> findByUuid(String uuid, int start, int end,
		OrderByComparator<Evaluation> orderByComparator,
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

		List<Evaluation> list = null;

		if (retrieveFromCache) {
			list = (List<Evaluation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Evaluation evaluation : list) {
					if (!Objects.equals(uuid, evaluation.getUuid())) {
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

			query.append(_SQL_SELECT_EVALUATION_WHERE);

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
				query.append(EvaluationModelImpl.ORDER_BY_JPQL);
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
					list = (List<Evaluation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Evaluation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first evaluation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evaluation
	 * @throws NoSuchEvaluationException if a matching evaluation could not be found
	 */
	@Override
	public Evaluation findByUuid_First(String uuid,
		OrderByComparator<Evaluation> orderByComparator)
		throws NoSuchEvaluationException {
		Evaluation evaluation = fetchByUuid_First(uuid, orderByComparator);

		if (evaluation != null) {
			return evaluation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchEvaluationException(msg.toString());
	}

	/**
	 * Returns the first evaluation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evaluation, or <code>null</code> if a matching evaluation could not be found
	 */
	@Override
	public Evaluation fetchByUuid_First(String uuid,
		OrderByComparator<Evaluation> orderByComparator) {
		List<Evaluation> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last evaluation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evaluation
	 * @throws NoSuchEvaluationException if a matching evaluation could not be found
	 */
	@Override
	public Evaluation findByUuid_Last(String uuid,
		OrderByComparator<Evaluation> orderByComparator)
		throws NoSuchEvaluationException {
		Evaluation evaluation = fetchByUuid_Last(uuid, orderByComparator);

		if (evaluation != null) {
			return evaluation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchEvaluationException(msg.toString());
	}

	/**
	 * Returns the last evaluation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evaluation, or <code>null</code> if a matching evaluation could not be found
	 */
	@Override
	public Evaluation fetchByUuid_Last(String uuid,
		OrderByComparator<Evaluation> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Evaluation> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the evaluations before and after the current evaluation in the ordered set where uuid = &#63;.
	 *
	 * @param evaluationId the primary key of the current evaluation
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next evaluation
	 * @throws NoSuchEvaluationException if a evaluation with the primary key could not be found
	 */
	@Override
	public Evaluation[] findByUuid_PrevAndNext(long evaluationId, String uuid,
		OrderByComparator<Evaluation> orderByComparator)
		throws NoSuchEvaluationException {
		Evaluation evaluation = findByPrimaryKey(evaluationId);

		Session session = null;

		try {
			session = openSession();

			Evaluation[] array = new EvaluationImpl[3];

			array[0] = getByUuid_PrevAndNext(session, evaluation, uuid,
					orderByComparator, true);

			array[1] = evaluation;

			array[2] = getByUuid_PrevAndNext(session, evaluation, uuid,
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

	protected Evaluation getByUuid_PrevAndNext(Session session,
		Evaluation evaluation, String uuid,
		OrderByComparator<Evaluation> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVALUATION_WHERE);

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
			query.append(EvaluationModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(evaluation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Evaluation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the evaluations where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Evaluation evaluation : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(evaluation);
		}
	}

	/**
	 * Returns the number of evaluations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching evaluations
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EVALUATION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "evaluation.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "evaluation.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(evaluation.uuid IS NULL OR evaluation.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, EvaluationImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			EvaluationModelImpl.UUID_COLUMN_BITMASK |
			EvaluationModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the evaluation where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEvaluationException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching evaluation
	 * @throws NoSuchEvaluationException if a matching evaluation could not be found
	 */
	@Override
	public Evaluation findByUUID_G(String uuid, long groupId)
		throws NoSuchEvaluationException {
		Evaluation evaluation = fetchByUUID_G(uuid, groupId);

		if (evaluation == null) {
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

			throw new NoSuchEvaluationException(msg.toString());
		}

		return evaluation;
	}

	/**
	 * Returns the evaluation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching evaluation, or <code>null</code> if a matching evaluation could not be found
	 */
	@Override
	public Evaluation fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the evaluation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching evaluation, or <code>null</code> if a matching evaluation could not be found
	 */
	@Override
	public Evaluation fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Evaluation) {
			Evaluation evaluation = (Evaluation)result;

			if (!Objects.equals(uuid, evaluation.getUuid()) ||
					(groupId != evaluation.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_EVALUATION_WHERE);

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

				List<Evaluation> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Evaluation evaluation = list.get(0);

					result = evaluation;

					cacheResult(evaluation);
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
			return (Evaluation)result;
		}
	}

	/**
	 * Removes the evaluation where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the evaluation that was removed
	 */
	@Override
	public Evaluation removeByUUID_G(String uuid, long groupId)
		throws NoSuchEvaluationException {
		Evaluation evaluation = findByUUID_G(uuid, groupId);

		return remove(evaluation);
	}

	/**
	 * Returns the number of evaluations where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching evaluations
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EVALUATION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "evaluation.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "evaluation.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(evaluation.uuid IS NULL OR evaluation.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "evaluation.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, EvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, EvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			EvaluationModelImpl.UUID_COLUMN_BITMASK |
			EvaluationModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the evaluations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching evaluations
	 */
	@Override
	public List<Evaluation> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the evaluations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @return the range of matching evaluations
	 */
	@Override
	public List<Evaluation> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the evaluations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching evaluations
	 */
	@Override
	public List<Evaluation> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Evaluation> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the evaluations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching evaluations
	 */
	@Override
	public List<Evaluation> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Evaluation> orderByComparator,
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

		List<Evaluation> list = null;

		if (retrieveFromCache) {
			list = (List<Evaluation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Evaluation evaluation : list) {
					if (!Objects.equals(uuid, evaluation.getUuid()) ||
							(companyId != evaluation.getCompanyId())) {
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

			query.append(_SQL_SELECT_EVALUATION_WHERE);

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
				query.append(EvaluationModelImpl.ORDER_BY_JPQL);
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
					list = (List<Evaluation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Evaluation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evaluation
	 * @throws NoSuchEvaluationException if a matching evaluation could not be found
	 */
	@Override
	public Evaluation findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Evaluation> orderByComparator)
		throws NoSuchEvaluationException {
		Evaluation evaluation = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (evaluation != null) {
			return evaluation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchEvaluationException(msg.toString());
	}

	/**
	 * Returns the first evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evaluation, or <code>null</code> if a matching evaluation could not be found
	 */
	@Override
	public Evaluation fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Evaluation> orderByComparator) {
		List<Evaluation> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evaluation
	 * @throws NoSuchEvaluationException if a matching evaluation could not be found
	 */
	@Override
	public Evaluation findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Evaluation> orderByComparator)
		throws NoSuchEvaluationException {
		Evaluation evaluation = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (evaluation != null) {
			return evaluation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchEvaluationException(msg.toString());
	}

	/**
	 * Returns the last evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evaluation, or <code>null</code> if a matching evaluation could not be found
	 */
	@Override
	public Evaluation fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Evaluation> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Evaluation> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the evaluations before and after the current evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param evaluationId the primary key of the current evaluation
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next evaluation
	 * @throws NoSuchEvaluationException if a evaluation with the primary key could not be found
	 */
	@Override
	public Evaluation[] findByUuid_C_PrevAndNext(long evaluationId,
		String uuid, long companyId,
		OrderByComparator<Evaluation> orderByComparator)
		throws NoSuchEvaluationException {
		Evaluation evaluation = findByPrimaryKey(evaluationId);

		Session session = null;

		try {
			session = openSession();

			Evaluation[] array = new EvaluationImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, evaluation, uuid,
					companyId, orderByComparator, true);

			array[1] = evaluation;

			array[2] = getByUuid_C_PrevAndNext(session, evaluation, uuid,
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

	protected Evaluation getByUuid_C_PrevAndNext(Session session,
		Evaluation evaluation, String uuid, long companyId,
		OrderByComparator<Evaluation> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EVALUATION_WHERE);

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
			query.append(EvaluationModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(evaluation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Evaluation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the evaluations where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Evaluation evaluation : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(evaluation);
		}
	}

	/**
	 * Returns the number of evaluations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching evaluations
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EVALUATION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "evaluation.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "evaluation.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(evaluation.uuid IS NULL OR evaluation.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "evaluation.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EM_ID = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, EvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEM_ID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EM_ID = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, EvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEM_ID",
			new String[] { Long.class.getName() },
			EvaluationModelImpl.EMPLOYEEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EM_ID = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEM_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the evaluations where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @return the matching evaluations
	 */
	@Override
	public List<Evaluation> findByEM_ID(long employeeId) {
		return findByEM_ID(employeeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the evaluations where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @return the range of matching evaluations
	 */
	@Override
	public List<Evaluation> findByEM_ID(long employeeId, int start, int end) {
		return findByEM_ID(employeeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the evaluations where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching evaluations
	 */
	@Override
	public List<Evaluation> findByEM_ID(long employeeId, int start, int end,
		OrderByComparator<Evaluation> orderByComparator) {
		return findByEM_ID(employeeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the evaluations where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching evaluations
	 */
	@Override
	public List<Evaluation> findByEM_ID(long employeeId, int start, int end,
		OrderByComparator<Evaluation> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EM_ID;
			finderArgs = new Object[] { employeeId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EM_ID;
			finderArgs = new Object[] { employeeId, start, end, orderByComparator };
		}

		List<Evaluation> list = null;

		if (retrieveFromCache) {
			list = (List<Evaluation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Evaluation evaluation : list) {
					if ((employeeId != evaluation.getEmployeeId())) {
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

			query.append(_SQL_SELECT_EVALUATION_WHERE);

			query.append(_FINDER_COLUMN_EM_ID_EMPLOYEEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EvaluationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(employeeId);

				if (!pagination) {
					list = (List<Evaluation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Evaluation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first evaluation in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evaluation
	 * @throws NoSuchEvaluationException if a matching evaluation could not be found
	 */
	@Override
	public Evaluation findByEM_ID_First(long employeeId,
		OrderByComparator<Evaluation> orderByComparator)
		throws NoSuchEvaluationException {
		Evaluation evaluation = fetchByEM_ID_First(employeeId, orderByComparator);

		if (evaluation != null) {
			return evaluation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("employeeId=");
		msg.append(employeeId);

		msg.append("}");

		throw new NoSuchEvaluationException(msg.toString());
	}

	/**
	 * Returns the first evaluation in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evaluation, or <code>null</code> if a matching evaluation could not be found
	 */
	@Override
	public Evaluation fetchByEM_ID_First(long employeeId,
		OrderByComparator<Evaluation> orderByComparator) {
		List<Evaluation> list = findByEM_ID(employeeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last evaluation in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evaluation
	 * @throws NoSuchEvaluationException if a matching evaluation could not be found
	 */
	@Override
	public Evaluation findByEM_ID_Last(long employeeId,
		OrderByComparator<Evaluation> orderByComparator)
		throws NoSuchEvaluationException {
		Evaluation evaluation = fetchByEM_ID_Last(employeeId, orderByComparator);

		if (evaluation != null) {
			return evaluation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("employeeId=");
		msg.append(employeeId);

		msg.append("}");

		throw new NoSuchEvaluationException(msg.toString());
	}

	/**
	 * Returns the last evaluation in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evaluation, or <code>null</code> if a matching evaluation could not be found
	 */
	@Override
	public Evaluation fetchByEM_ID_Last(long employeeId,
		OrderByComparator<Evaluation> orderByComparator) {
		int count = countByEM_ID(employeeId);

		if (count == 0) {
			return null;
		}

		List<Evaluation> list = findByEM_ID(employeeId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the evaluations before and after the current evaluation in the ordered set where employeeId = &#63;.
	 *
	 * @param evaluationId the primary key of the current evaluation
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next evaluation
	 * @throws NoSuchEvaluationException if a evaluation with the primary key could not be found
	 */
	@Override
	public Evaluation[] findByEM_ID_PrevAndNext(long evaluationId,
		long employeeId, OrderByComparator<Evaluation> orderByComparator)
		throws NoSuchEvaluationException {
		Evaluation evaluation = findByPrimaryKey(evaluationId);

		Session session = null;

		try {
			session = openSession();

			Evaluation[] array = new EvaluationImpl[3];

			array[0] = getByEM_ID_PrevAndNext(session, evaluation, employeeId,
					orderByComparator, true);

			array[1] = evaluation;

			array[2] = getByEM_ID_PrevAndNext(session, evaluation, employeeId,
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

	protected Evaluation getByEM_ID_PrevAndNext(Session session,
		Evaluation evaluation, long employeeId,
		OrderByComparator<Evaluation> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVALUATION_WHERE);

		query.append(_FINDER_COLUMN_EM_ID_EMPLOYEEID_2);

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
			query.append(EvaluationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(employeeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(evaluation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Evaluation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the evaluations where employeeId = &#63; from the database.
	 *
	 * @param employeeId the employee ID
	 */
	@Override
	public void removeByEM_ID(long employeeId) {
		for (Evaluation evaluation : findByEM_ID(employeeId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(evaluation);
		}
	}

	/**
	 * Returns the number of evaluations where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @return the number of matching evaluations
	 */
	@Override
	public int countByEM_ID(long employeeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_EM_ID;

		Object[] finderArgs = new Object[] { employeeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EVALUATION_WHERE);

			query.append(_FINDER_COLUMN_EM_ID_EMPLOYEEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(employeeId);

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

	private static final String _FINDER_COLUMN_EM_ID_EMPLOYEEID_2 = "evaluation.employeeId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EM_ID_S = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, EvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEM_ID_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EM_ID_S =
		new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, EvaluationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEM_ID_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			EvaluationModelImpl.EMPLOYEEID_COLUMN_BITMASK |
			EvaluationModelImpl.SCORE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EM_ID_S = new FinderPath(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEM_ID_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the evaluations where employeeId = &#63; and score = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param score the score
	 * @return the matching evaluations
	 */
	@Override
	public List<Evaluation> findByEM_ID_S(long employeeId, int score) {
		return findByEM_ID_S(employeeId, score, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the evaluations where employeeId = &#63; and score = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param score the score
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @return the range of matching evaluations
	 */
	@Override
	public List<Evaluation> findByEM_ID_S(long employeeId, int score,
		int start, int end) {
		return findByEM_ID_S(employeeId, score, start, end, null);
	}

	/**
	 * Returns an ordered range of all the evaluations where employeeId = &#63; and score = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param score the score
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching evaluations
	 */
	@Override
	public List<Evaluation> findByEM_ID_S(long employeeId, int score,
		int start, int end, OrderByComparator<Evaluation> orderByComparator) {
		return findByEM_ID_S(employeeId, score, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the evaluations where employeeId = &#63; and score = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param score the score
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching evaluations
	 */
	@Override
	public List<Evaluation> findByEM_ID_S(long employeeId, int score,
		int start, int end, OrderByComparator<Evaluation> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EM_ID_S;
			finderArgs = new Object[] { employeeId, score };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EM_ID_S;
			finderArgs = new Object[] {
					employeeId, score,
					
					start, end, orderByComparator
				};
		}

		List<Evaluation> list = null;

		if (retrieveFromCache) {
			list = (List<Evaluation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Evaluation evaluation : list) {
					if ((employeeId != evaluation.getEmployeeId()) ||
							(score != evaluation.getScore())) {
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

			query.append(_SQL_SELECT_EVALUATION_WHERE);

			query.append(_FINDER_COLUMN_EM_ID_S_EMPLOYEEID_2);

			query.append(_FINDER_COLUMN_EM_ID_S_SCORE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EvaluationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(employeeId);

				qPos.add(score);

				if (!pagination) {
					list = (List<Evaluation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Evaluation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first evaluation in the ordered set where employeeId = &#63; and score = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param score the score
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evaluation
	 * @throws NoSuchEvaluationException if a matching evaluation could not be found
	 */
	@Override
	public Evaluation findByEM_ID_S_First(long employeeId, int score,
		OrderByComparator<Evaluation> orderByComparator)
		throws NoSuchEvaluationException {
		Evaluation evaluation = fetchByEM_ID_S_First(employeeId, score,
				orderByComparator);

		if (evaluation != null) {
			return evaluation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("employeeId=");
		msg.append(employeeId);

		msg.append(", score=");
		msg.append(score);

		msg.append("}");

		throw new NoSuchEvaluationException(msg.toString());
	}

	/**
	 * Returns the first evaluation in the ordered set where employeeId = &#63; and score = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param score the score
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching evaluation, or <code>null</code> if a matching evaluation could not be found
	 */
	@Override
	public Evaluation fetchByEM_ID_S_First(long employeeId, int score,
		OrderByComparator<Evaluation> orderByComparator) {
		List<Evaluation> list = findByEM_ID_S(employeeId, score, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last evaluation in the ordered set where employeeId = &#63; and score = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param score the score
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evaluation
	 * @throws NoSuchEvaluationException if a matching evaluation could not be found
	 */
	@Override
	public Evaluation findByEM_ID_S_Last(long employeeId, int score,
		OrderByComparator<Evaluation> orderByComparator)
		throws NoSuchEvaluationException {
		Evaluation evaluation = fetchByEM_ID_S_Last(employeeId, score,
				orderByComparator);

		if (evaluation != null) {
			return evaluation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("employeeId=");
		msg.append(employeeId);

		msg.append(", score=");
		msg.append(score);

		msg.append("}");

		throw new NoSuchEvaluationException(msg.toString());
	}

	/**
	 * Returns the last evaluation in the ordered set where employeeId = &#63; and score = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param score the score
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching evaluation, or <code>null</code> if a matching evaluation could not be found
	 */
	@Override
	public Evaluation fetchByEM_ID_S_Last(long employeeId, int score,
		OrderByComparator<Evaluation> orderByComparator) {
		int count = countByEM_ID_S(employeeId, score);

		if (count == 0) {
			return null;
		}

		List<Evaluation> list = findByEM_ID_S(employeeId, score, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the evaluations before and after the current evaluation in the ordered set where employeeId = &#63; and score = &#63;.
	 *
	 * @param evaluationId the primary key of the current evaluation
	 * @param employeeId the employee ID
	 * @param score the score
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next evaluation
	 * @throws NoSuchEvaluationException if a evaluation with the primary key could not be found
	 */
	@Override
	public Evaluation[] findByEM_ID_S_PrevAndNext(long evaluationId,
		long employeeId, int score,
		OrderByComparator<Evaluation> orderByComparator)
		throws NoSuchEvaluationException {
		Evaluation evaluation = findByPrimaryKey(evaluationId);

		Session session = null;

		try {
			session = openSession();

			Evaluation[] array = new EvaluationImpl[3];

			array[0] = getByEM_ID_S_PrevAndNext(session, evaluation,
					employeeId, score, orderByComparator, true);

			array[1] = evaluation;

			array[2] = getByEM_ID_S_PrevAndNext(session, evaluation,
					employeeId, score, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Evaluation getByEM_ID_S_PrevAndNext(Session session,
		Evaluation evaluation, long employeeId, int score,
		OrderByComparator<Evaluation> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EVALUATION_WHERE);

		query.append(_FINDER_COLUMN_EM_ID_S_EMPLOYEEID_2);

		query.append(_FINDER_COLUMN_EM_ID_S_SCORE_2);

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
			query.append(EvaluationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(employeeId);

		qPos.add(score);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(evaluation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Evaluation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the evaluations where employeeId = &#63; and score = &#63; from the database.
	 *
	 * @param employeeId the employee ID
	 * @param score the score
	 */
	@Override
	public void removeByEM_ID_S(long employeeId, int score) {
		for (Evaluation evaluation : findByEM_ID_S(employeeId, score,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(evaluation);
		}
	}

	/**
	 * Returns the number of evaluations where employeeId = &#63; and score = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param score the score
	 * @return the number of matching evaluations
	 */
	@Override
	public int countByEM_ID_S(long employeeId, int score) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_EM_ID_S;

		Object[] finderArgs = new Object[] { employeeId, score };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EVALUATION_WHERE);

			query.append(_FINDER_COLUMN_EM_ID_S_EMPLOYEEID_2);

			query.append(_FINDER_COLUMN_EM_ID_S_SCORE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(employeeId);

				qPos.add(score);

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

	private static final String _FINDER_COLUMN_EM_ID_S_EMPLOYEEID_2 = "evaluation.employeeId = ? AND ";
	private static final String _FINDER_COLUMN_EM_ID_S_SCORE_2 = "evaluation.score = ?";

	public EvaluationPersistenceImpl() {
		setModelClass(Evaluation.class);

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
	 * Caches the evaluation in the entity cache if it is enabled.
	 *
	 * @param evaluation the evaluation
	 */
	@Override
	public void cacheResult(Evaluation evaluation) {
		entityCache.putResult(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationImpl.class, evaluation.getPrimaryKey(), evaluation);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { evaluation.getUuid(), evaluation.getGroupId() },
			evaluation);

		evaluation.resetOriginalValues();
	}

	/**
	 * Caches the evaluations in the entity cache if it is enabled.
	 *
	 * @param evaluations the evaluations
	 */
	@Override
	public void cacheResult(List<Evaluation> evaluations) {
		for (Evaluation evaluation : evaluations) {
			if (entityCache.getResult(
						EvaluationModelImpl.ENTITY_CACHE_ENABLED,
						EvaluationImpl.class, evaluation.getPrimaryKey()) == null) {
				cacheResult(evaluation);
			}
			else {
				evaluation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all evaluations.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EvaluationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the evaluation.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Evaluation evaluation) {
		entityCache.removeResult(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationImpl.class, evaluation.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((EvaluationModelImpl)evaluation, true);
	}

	@Override
	public void clearCache(List<Evaluation> evaluations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Evaluation evaluation : evaluations) {
			entityCache.removeResult(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
				EvaluationImpl.class, evaluation.getPrimaryKey());

			clearUniqueFindersCache((EvaluationModelImpl)evaluation, true);
		}
	}

	protected void cacheUniqueFindersCache(
		EvaluationModelImpl evaluationModelImpl) {
		Object[] args = new Object[] {
				evaluationModelImpl.getUuid(), evaluationModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			evaluationModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		EvaluationModelImpl evaluationModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					evaluationModelImpl.getUuid(),
					evaluationModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((evaluationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					evaluationModelImpl.getOriginalUuid(),
					evaluationModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new evaluation with the primary key. Does not add the evaluation to the database.
	 *
	 * @param evaluationId the primary key for the new evaluation
	 * @return the new evaluation
	 */
	@Override
	public Evaluation create(long evaluationId) {
		Evaluation evaluation = new EvaluationImpl();

		evaluation.setNew(true);
		evaluation.setPrimaryKey(evaluationId);

		String uuid = PortalUUIDUtil.generate();

		evaluation.setUuid(uuid);

		evaluation.setCompanyId(companyProvider.getCompanyId());

		return evaluation;
	}

	/**
	 * Removes the evaluation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param evaluationId the primary key of the evaluation
	 * @return the evaluation that was removed
	 * @throws NoSuchEvaluationException if a evaluation with the primary key could not be found
	 */
	@Override
	public Evaluation remove(long evaluationId)
		throws NoSuchEvaluationException {
		return remove((Serializable)evaluationId);
	}

	/**
	 * Removes the evaluation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the evaluation
	 * @return the evaluation that was removed
	 * @throws NoSuchEvaluationException if a evaluation with the primary key could not be found
	 */
	@Override
	public Evaluation remove(Serializable primaryKey)
		throws NoSuchEvaluationException {
		Session session = null;

		try {
			session = openSession();

			Evaluation evaluation = (Evaluation)session.get(EvaluationImpl.class,
					primaryKey);

			if (evaluation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEvaluationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(evaluation);
		}
		catch (NoSuchEvaluationException nsee) {
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
	protected Evaluation removeImpl(Evaluation evaluation) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(evaluation)) {
				evaluation = (Evaluation)session.get(EvaluationImpl.class,
						evaluation.getPrimaryKeyObj());
			}

			if (evaluation != null) {
				session.delete(evaluation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (evaluation != null) {
			clearCache(evaluation);
		}

		return evaluation;
	}

	@Override
	public Evaluation updateImpl(Evaluation evaluation) {
		boolean isNew = evaluation.isNew();

		if (!(evaluation instanceof EvaluationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(evaluation.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(evaluation);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in evaluation proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Evaluation implementation " +
				evaluation.getClass());
		}

		EvaluationModelImpl evaluationModelImpl = (EvaluationModelImpl)evaluation;

		if (Validator.isNull(evaluation.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			evaluation.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (evaluation.getCreateDate() == null)) {
			if (serviceContext == null) {
				evaluation.setCreateDate(now);
			}
			else {
				evaluation.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!evaluationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				evaluation.setModifiedDate(now);
			}
			else {
				evaluation.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (evaluation.isNew()) {
				session.save(evaluation);

				evaluation.setNew(false);
			}
			else {
				evaluation = (Evaluation)session.merge(evaluation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!EvaluationModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { evaluationModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					evaluationModelImpl.getUuid(),
					evaluationModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { evaluationModelImpl.getEmployeeId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_EM_ID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EM_ID,
				args);

			args = new Object[] {
					evaluationModelImpl.getEmployeeId(),
					evaluationModelImpl.getScore()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_EM_ID_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EM_ID_S,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((evaluationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						evaluationModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { evaluationModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((evaluationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						evaluationModelImpl.getOriginalUuid(),
						evaluationModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						evaluationModelImpl.getUuid(),
						evaluationModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((evaluationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EM_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						evaluationModelImpl.getOriginalEmployeeId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EM_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EM_ID,
					args);

				args = new Object[] { evaluationModelImpl.getEmployeeId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EM_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EM_ID,
					args);
			}

			if ((evaluationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EM_ID_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						evaluationModelImpl.getOriginalEmployeeId(),
						evaluationModelImpl.getOriginalScore()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EM_ID_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EM_ID_S,
					args);

				args = new Object[] {
						evaluationModelImpl.getEmployeeId(),
						evaluationModelImpl.getScore()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EM_ID_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EM_ID_S,
					args);
			}
		}

		entityCache.putResult(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
			EvaluationImpl.class, evaluation.getPrimaryKey(), evaluation, false);

		clearUniqueFindersCache(evaluationModelImpl, false);
		cacheUniqueFindersCache(evaluationModelImpl);

		evaluation.resetOriginalValues();

		return evaluation;
	}

	/**
	 * Returns the evaluation with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the evaluation
	 * @return the evaluation
	 * @throws NoSuchEvaluationException if a evaluation with the primary key could not be found
	 */
	@Override
	public Evaluation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEvaluationException {
		Evaluation evaluation = fetchByPrimaryKey(primaryKey);

		if (evaluation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEvaluationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return evaluation;
	}

	/**
	 * Returns the evaluation with the primary key or throws a {@link NoSuchEvaluationException} if it could not be found.
	 *
	 * @param evaluationId the primary key of the evaluation
	 * @return the evaluation
	 * @throws NoSuchEvaluationException if a evaluation with the primary key could not be found
	 */
	@Override
	public Evaluation findByPrimaryKey(long evaluationId)
		throws NoSuchEvaluationException {
		return findByPrimaryKey((Serializable)evaluationId);
	}

	/**
	 * Returns the evaluation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the evaluation
	 * @return the evaluation, or <code>null</code> if a evaluation with the primary key could not be found
	 */
	@Override
	public Evaluation fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
				EvaluationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Evaluation evaluation = (Evaluation)serializable;

		if (evaluation == null) {
			Session session = null;

			try {
				session = openSession();

				evaluation = (Evaluation)session.get(EvaluationImpl.class,
						primaryKey);

				if (evaluation != null) {
					cacheResult(evaluation);
				}
				else {
					entityCache.putResult(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
						EvaluationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
					EvaluationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return evaluation;
	}

	/**
	 * Returns the evaluation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param evaluationId the primary key of the evaluation
	 * @return the evaluation, or <code>null</code> if a evaluation with the primary key could not be found
	 */
	@Override
	public Evaluation fetchByPrimaryKey(long evaluationId) {
		return fetchByPrimaryKey((Serializable)evaluationId);
	}

	@Override
	public Map<Serializable, Evaluation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Evaluation> map = new HashMap<Serializable, Evaluation>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Evaluation evaluation = fetchByPrimaryKey(primaryKey);

			if (evaluation != null) {
				map.put(primaryKey, evaluation);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
					EvaluationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Evaluation)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_EVALUATION_WHERE_PKS_IN);

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

			for (Evaluation evaluation : (List<Evaluation>)q.list()) {
				map.put(evaluation.getPrimaryKeyObj(), evaluation);

				cacheResult(evaluation);

				uncachedPrimaryKeys.remove(evaluation.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EvaluationModelImpl.ENTITY_CACHE_ENABLED,
					EvaluationImpl.class, primaryKey, nullModel);
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
	 * Returns all the evaluations.
	 *
	 * @return the evaluations
	 */
	@Override
	public List<Evaluation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the evaluations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @return the range of evaluations
	 */
	@Override
	public List<Evaluation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the evaluations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of evaluations
	 */
	@Override
	public List<Evaluation> findAll(int start, int end,
		OrderByComparator<Evaluation> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the evaluations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of evaluations
	 * @param end the upper bound of the range of evaluations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of evaluations
	 */
	@Override
	public List<Evaluation> findAll(int start, int end,
		OrderByComparator<Evaluation> orderByComparator,
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

		List<Evaluation> list = null;

		if (retrieveFromCache) {
			list = (List<Evaluation>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_EVALUATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EVALUATION;

				if (pagination) {
					sql = sql.concat(EvaluationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Evaluation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Evaluation>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the evaluations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Evaluation evaluation : findAll()) {
			remove(evaluation);
		}
	}

	/**
	 * Returns the number of evaluations.
	 *
	 * @return the number of evaluations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EVALUATION);

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
		return EvaluationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the evaluation persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(EvaluationImpl.class.getName());
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
	private static final String _SQL_SELECT_EVALUATION = "SELECT evaluation FROM Evaluation evaluation";
	private static final String _SQL_SELECT_EVALUATION_WHERE_PKS_IN = "SELECT evaluation FROM Evaluation evaluation WHERE evaluationId IN (";
	private static final String _SQL_SELECT_EVALUATION_WHERE = "SELECT evaluation FROM Evaluation evaluation WHERE ";
	private static final String _SQL_COUNT_EVALUATION = "SELECT COUNT(evaluation) FROM Evaluation evaluation";
	private static final String _SQL_COUNT_EVALUATION_WHERE = "SELECT COUNT(evaluation) FROM Evaluation evaluation WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "evaluation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Evaluation exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Evaluation exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(EvaluationPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}