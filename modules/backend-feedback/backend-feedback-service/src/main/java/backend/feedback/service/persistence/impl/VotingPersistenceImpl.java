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

import backend.feedback.exception.NoSuchVotingException;

import backend.feedback.model.Voting;
import backend.feedback.model.impl.VotingImpl;
import backend.feedback.model.impl.VotingModelImpl;

import backend.feedback.service.persistence.VotingPersistence;

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
 * The persistence implementation for the voting service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sondt
 * @see VotingPersistence
 * @see backend.feedback.service.persistence.VotingUtil
 * @generated
 */
@ProviderType
public class VotingPersistenceImpl extends BasePersistenceImpl<Voting>
	implements VotingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VotingUtil} to access the voting persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VotingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, VotingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, VotingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, VotingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, VotingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			VotingModelImpl.UUID_COLUMN_BITMASK |
			VotingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the votings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching votings
	 */
	@Override
	public List<Voting> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the votings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @return the range of matching votings
	 */
	@Override
	public List<Voting> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the votings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching votings
	 */
	@Override
	public List<Voting> findByUuid(String uuid, int start, int end,
		OrderByComparator<Voting> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the votings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching votings
	 */
	@Override
	public List<Voting> findByUuid(String uuid, int start, int end,
		OrderByComparator<Voting> orderByComparator, boolean retrieveFromCache) {
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

		List<Voting> list = null;

		if (retrieveFromCache) {
			list = (List<Voting>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Voting voting : list) {
					if (!Objects.equals(uuid, voting.getUuid())) {
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

			query.append(_SQL_SELECT_VOTING_WHERE);

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
				query.append(VotingModelImpl.ORDER_BY_JPQL);
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
					list = (List<Voting>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Voting>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first voting in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching voting
	 * @throws NoSuchVotingException if a matching voting could not be found
	 */
	@Override
	public Voting findByUuid_First(String uuid,
		OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = fetchByUuid_First(uuid, orderByComparator);

		if (voting != null) {
			return voting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchVotingException(msg.toString());
	}

	/**
	 * Returns the first voting in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching voting, or <code>null</code> if a matching voting could not be found
	 */
	@Override
	public Voting fetchByUuid_First(String uuid,
		OrderByComparator<Voting> orderByComparator) {
		List<Voting> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last voting in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching voting
	 * @throws NoSuchVotingException if a matching voting could not be found
	 */
	@Override
	public Voting findByUuid_Last(String uuid,
		OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = fetchByUuid_Last(uuid, orderByComparator);

		if (voting != null) {
			return voting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchVotingException(msg.toString());
	}

	/**
	 * Returns the last voting in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching voting, or <code>null</code> if a matching voting could not be found
	 */
	@Override
	public Voting fetchByUuid_Last(String uuid,
		OrderByComparator<Voting> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Voting> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the votings before and after the current voting in the ordered set where uuid = &#63;.
	 *
	 * @param votingId the primary key of the current voting
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next voting
	 * @throws NoSuchVotingException if a voting with the primary key could not be found
	 */
	@Override
	public Voting[] findByUuid_PrevAndNext(long votingId, String uuid,
		OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = findByPrimaryKey(votingId);

		Session session = null;

		try {
			session = openSession();

			Voting[] array = new VotingImpl[3];

			array[0] = getByUuid_PrevAndNext(session, voting, uuid,
					orderByComparator, true);

			array[1] = voting;

			array[2] = getByUuid_PrevAndNext(session, voting, uuid,
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

	protected Voting getByUuid_PrevAndNext(Session session, Voting voting,
		String uuid, OrderByComparator<Voting> orderByComparator,
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

		query.append(_SQL_SELECT_VOTING_WHERE);

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
			query.append(VotingModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(voting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Voting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the votings where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Voting voting : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(voting);
		}
	}

	/**
	 * Returns the number of votings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching votings
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VOTING_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "voting.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "voting.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(voting.uuid IS NULL OR voting.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, VotingImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			VotingModelImpl.UUID_COLUMN_BITMASK |
			VotingModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the voting where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchVotingException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching voting
	 * @throws NoSuchVotingException if a matching voting could not be found
	 */
	@Override
	public Voting findByUUID_G(String uuid, long groupId)
		throws NoSuchVotingException {
		Voting voting = fetchByUUID_G(uuid, groupId);

		if (voting == null) {
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

			throw new NoSuchVotingException(msg.toString());
		}

		return voting;
	}

	/**
	 * Returns the voting where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching voting, or <code>null</code> if a matching voting could not be found
	 */
	@Override
	public Voting fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the voting where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching voting, or <code>null</code> if a matching voting could not be found
	 */
	@Override
	public Voting fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Voting) {
			Voting voting = (Voting)result;

			if (!Objects.equals(uuid, voting.getUuid()) ||
					(groupId != voting.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VOTING_WHERE);

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

				List<Voting> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Voting voting = list.get(0);

					result = voting;

					cacheResult(voting);
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
			return (Voting)result;
		}
	}

	/**
	 * Removes the voting where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the voting that was removed
	 */
	@Override
	public Voting removeByUUID_G(String uuid, long groupId)
		throws NoSuchVotingException {
		Voting voting = findByUUID_G(uuid, groupId);

		return remove(voting);
	}

	/**
	 * Returns the number of votings where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching votings
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VOTING_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "voting.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "voting.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(voting.uuid IS NULL OR voting.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "voting.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, VotingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, VotingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			VotingModelImpl.UUID_COLUMN_BITMASK |
			VotingModelImpl.COMPANYID_COLUMN_BITMASK |
			VotingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the votings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching votings
	 */
	@Override
	public List<Voting> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the votings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @return the range of matching votings
	 */
	@Override
	public List<Voting> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the votings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching votings
	 */
	@Override
	public List<Voting> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Voting> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the votings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching votings
	 */
	@Override
	public List<Voting> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Voting> orderByComparator,
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

		List<Voting> list = null;

		if (retrieveFromCache) {
			list = (List<Voting>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Voting voting : list) {
					if (!Objects.equals(uuid, voting.getUuid()) ||
							(companyId != voting.getCompanyId())) {
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

			query.append(_SQL_SELECT_VOTING_WHERE);

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
				query.append(VotingModelImpl.ORDER_BY_JPQL);
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
					list = (List<Voting>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Voting>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first voting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching voting
	 * @throws NoSuchVotingException if a matching voting could not be found
	 */
	@Override
	public Voting findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (voting != null) {
			return voting;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchVotingException(msg.toString());
	}

	/**
	 * Returns the first voting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching voting, or <code>null</code> if a matching voting could not be found
	 */
	@Override
	public Voting fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Voting> orderByComparator) {
		List<Voting> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last voting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching voting
	 * @throws NoSuchVotingException if a matching voting could not be found
	 */
	@Override
	public Voting findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (voting != null) {
			return voting;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchVotingException(msg.toString());
	}

	/**
	 * Returns the last voting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching voting, or <code>null</code> if a matching voting could not be found
	 */
	@Override
	public Voting fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Voting> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Voting> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the votings before and after the current voting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param votingId the primary key of the current voting
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next voting
	 * @throws NoSuchVotingException if a voting with the primary key could not be found
	 */
	@Override
	public Voting[] findByUuid_C_PrevAndNext(long votingId, String uuid,
		long companyId, OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = findByPrimaryKey(votingId);

		Session session = null;

		try {
			session = openSession();

			Voting[] array = new VotingImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, voting, uuid,
					companyId, orderByComparator, true);

			array[1] = voting;

			array[2] = getByUuid_C_PrevAndNext(session, voting, uuid,
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

	protected Voting getByUuid_C_PrevAndNext(Session session, Voting voting,
		String uuid, long companyId,
		OrderByComparator<Voting> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_VOTING_WHERE);

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
			query.append(VotingModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(voting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Voting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the votings where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Voting voting : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(voting);
		}
	}

	/**
	 * Returns the number of votings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching votings
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VOTING_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "voting.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "voting.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(voting.uuid IS NULL OR voting.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "voting.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_CLNAME_CLPK =
		new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, VotingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_CLNAME_CLPK",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLNAME_CLPK =
		new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, VotingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_CLNAME_CLPK",
			new String[] { String.class.getName(), String.class.getName() },
			VotingModelImpl.CLASSNAME_COLUMN_BITMASK |
			VotingModelImpl.CLASSPK_COLUMN_BITMASK |
			VotingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_CLNAME_CLPK = new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_CLNAME_CLPK",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns all the votings where className = &#63; and classPK = &#63;.
	 *
	 * @param className the class name
	 * @param classPK the class pk
	 * @return the matching votings
	 */
	@Override
	public List<Voting> findByF_CLNAME_CLPK(String className, String classPK) {
		return findByF_CLNAME_CLPK(className, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the votings where className = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param className the class name
	 * @param classPK the class pk
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @return the range of matching votings
	 */
	@Override
	public List<Voting> findByF_CLNAME_CLPK(String className, String classPK,
		int start, int end) {
		return findByF_CLNAME_CLPK(className, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the votings where className = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param className the class name
	 * @param classPK the class pk
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching votings
	 */
	@Override
	public List<Voting> findByF_CLNAME_CLPK(String className, String classPK,
		int start, int end, OrderByComparator<Voting> orderByComparator) {
		return findByF_CLNAME_CLPK(className, classPK, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the votings where className = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param className the class name
	 * @param classPK the class pk
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching votings
	 */
	@Override
	public List<Voting> findByF_CLNAME_CLPK(String className, String classPK,
		int start, int end, OrderByComparator<Voting> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLNAME_CLPK;
			finderArgs = new Object[] { className, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_CLNAME_CLPK;
			finderArgs = new Object[] {
					className, classPK,
					
					start, end, orderByComparator
				};
		}

		List<Voting> list = null;

		if (retrieveFromCache) {
			list = (List<Voting>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Voting voting : list) {
					if (!Objects.equals(className, voting.getClassName()) ||
							!Objects.equals(classPK, voting.getClassPK())) {
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

			query.append(_SQL_SELECT_VOTING_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSNAME_2);
			}

			boolean bindClassPK = false;

			if (classPK == null) {
				query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSPK_1);
			}
			else if (classPK.equals("")) {
				query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSPK_3);
			}
			else {
				bindClassPK = true;

				query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSPK_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(VotingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindClassPK) {
					qPos.add(classPK);
				}

				if (!pagination) {
					list = (List<Voting>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Voting>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first voting in the ordered set where className = &#63; and classPK = &#63;.
	 *
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching voting
	 * @throws NoSuchVotingException if a matching voting could not be found
	 */
	@Override
	public Voting findByF_CLNAME_CLPK_First(String className, String classPK,
		OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = fetchByF_CLNAME_CLPK_First(className, classPK,
				orderByComparator);

		if (voting != null) {
			return voting;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("className=");
		msg.append(className);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchVotingException(msg.toString());
	}

	/**
	 * Returns the first voting in the ordered set where className = &#63; and classPK = &#63;.
	 *
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching voting, or <code>null</code> if a matching voting could not be found
	 */
	@Override
	public Voting fetchByF_CLNAME_CLPK_First(String className, String classPK,
		OrderByComparator<Voting> orderByComparator) {
		List<Voting> list = findByF_CLNAME_CLPK(className, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last voting in the ordered set where className = &#63; and classPK = &#63;.
	 *
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching voting
	 * @throws NoSuchVotingException if a matching voting could not be found
	 */
	@Override
	public Voting findByF_CLNAME_CLPK_Last(String className, String classPK,
		OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = fetchByF_CLNAME_CLPK_Last(className, classPK,
				orderByComparator);

		if (voting != null) {
			return voting;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("className=");
		msg.append(className);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchVotingException(msg.toString());
	}

	/**
	 * Returns the last voting in the ordered set where className = &#63; and classPK = &#63;.
	 *
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching voting, or <code>null</code> if a matching voting could not be found
	 */
	@Override
	public Voting fetchByF_CLNAME_CLPK_Last(String className, String classPK,
		OrderByComparator<Voting> orderByComparator) {
		int count = countByF_CLNAME_CLPK(className, classPK);

		if (count == 0) {
			return null;
		}

		List<Voting> list = findByF_CLNAME_CLPK(className, classPK, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the votings before and after the current voting in the ordered set where className = &#63; and classPK = &#63;.
	 *
	 * @param votingId the primary key of the current voting
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next voting
	 * @throws NoSuchVotingException if a voting with the primary key could not be found
	 */
	@Override
	public Voting[] findByF_CLNAME_CLPK_PrevAndNext(long votingId,
		String className, String classPK,
		OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = findByPrimaryKey(votingId);

		Session session = null;

		try {
			session = openSession();

			Voting[] array = new VotingImpl[3];

			array[0] = getByF_CLNAME_CLPK_PrevAndNext(session, voting,
					className, classPK, orderByComparator, true);

			array[1] = voting;

			array[2] = getByF_CLNAME_CLPK_PrevAndNext(session, voting,
					className, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Voting getByF_CLNAME_CLPK_PrevAndNext(Session session,
		Voting voting, String className, String classPK,
		OrderByComparator<Voting> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_VOTING_WHERE);

		boolean bindClassName = false;

		if (className == null) {
			query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSNAME_1);
		}
		else if (className.equals("")) {
			query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSNAME_3);
		}
		else {
			bindClassName = true;

			query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSNAME_2);
		}

		boolean bindClassPK = false;

		if (classPK == null) {
			query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSPK_1);
		}
		else if (classPK.equals("")) {
			query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSPK_3);
		}
		else {
			bindClassPK = true;

			query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSPK_2);
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
			query.append(VotingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindClassName) {
			qPos.add(className);
		}

		if (bindClassPK) {
			qPos.add(classPK);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(voting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Voting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the votings where className = &#63; and classPK = &#63; from the database.
	 *
	 * @param className the class name
	 * @param classPK the class pk
	 */
	@Override
	public void removeByF_CLNAME_CLPK(String className, String classPK) {
		for (Voting voting : findByF_CLNAME_CLPK(className, classPK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(voting);
		}
	}

	/**
	 * Returns the number of votings where className = &#63; and classPK = &#63;.
	 *
	 * @param className the class name
	 * @param classPK the class pk
	 * @return the number of matching votings
	 */
	@Override
	public int countByF_CLNAME_CLPK(String className, String classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_CLNAME_CLPK;

		Object[] finderArgs = new Object[] { className, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VOTING_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSNAME_2);
			}

			boolean bindClassPK = false;

			if (classPK == null) {
				query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSPK_1);
			}
			else if (classPK.equals("")) {
				query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSPK_3);
			}
			else {
				bindClassPK = true;

				query.append(_FINDER_COLUMN_F_CLNAME_CLPK_CLASSPK_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

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

	private static final String _FINDER_COLUMN_F_CLNAME_CLPK_CLASSNAME_1 = "voting.className IS NULL AND ";
	private static final String _FINDER_COLUMN_F_CLNAME_CLPK_CLASSNAME_2 = "voting.className = ? AND ";
	private static final String _FINDER_COLUMN_F_CLNAME_CLPK_CLASSNAME_3 = "(voting.className IS NULL OR voting.className = '') AND ";
	private static final String _FINDER_COLUMN_F_CLNAME_CLPK_CLASSPK_1 = "voting.classPK IS NULL";
	private static final String _FINDER_COLUMN_F_CLNAME_CLPK_CLASSPK_2 = "voting.classPK = ?";
	private static final String _FINDER_COLUMN_F_CLNAME_CLPK_CLASSPK_3 = "(voting.classPK IS NULL OR voting.classPK = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_G_CLNAME_CLPK =
		new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, VotingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_G_CLNAME_CLPK",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_CLNAME_CLPK =
		new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, VotingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_G_CLNAME_CLPK",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			VotingModelImpl.GROUPID_COLUMN_BITMASK |
			VotingModelImpl.CLASSNAME_COLUMN_BITMASK |
			VotingModelImpl.CLASSPK_COLUMN_BITMASK |
			VotingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_G_CLNAME_CLPK = new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_G_CLNAME_CLPK",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the votings where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @return the matching votings
	 */
	@Override
	public List<Voting> findByF_G_CLNAME_CLPK(long groupId, String className,
		String classPK) {
		return findByF_G_CLNAME_CLPK(groupId, className, classPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the votings where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @return the range of matching votings
	 */
	@Override
	public List<Voting> findByF_G_CLNAME_CLPK(long groupId, String className,
		String classPK, int start, int end) {
		return findByF_G_CLNAME_CLPK(groupId, className, classPK, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the votings where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching votings
	 */
	@Override
	public List<Voting> findByF_G_CLNAME_CLPK(long groupId, String className,
		String classPK, int start, int end,
		OrderByComparator<Voting> orderByComparator) {
		return findByF_G_CLNAME_CLPK(groupId, className, classPK, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the votings where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching votings
	 */
	@Override
	public List<Voting> findByF_G_CLNAME_CLPK(long groupId, String className,
		String classPK, int start, int end,
		OrderByComparator<Voting> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_CLNAME_CLPK;
			finderArgs = new Object[] { groupId, className, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_G_CLNAME_CLPK;
			finderArgs = new Object[] {
					groupId, className, classPK,
					
					start, end, orderByComparator
				};
		}

		List<Voting> list = null;

		if (retrieveFromCache) {
			list = (List<Voting>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Voting voting : list) {
					if ((groupId != voting.getGroupId()) ||
							!Objects.equals(className, voting.getClassName()) ||
							!Objects.equals(classPK, voting.getClassPK())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_VOTING_WHERE);

			query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_GROUPID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSNAME_2);
			}

			boolean bindClassPK = false;

			if (classPK == null) {
				query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSPK_1);
			}
			else if (classPK.equals("")) {
				query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSPK_3);
			}
			else {
				bindClassPK = true;

				query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSPK_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(VotingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindClassPK) {
					qPos.add(classPK);
				}

				if (!pagination) {
					list = (List<Voting>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Voting>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first voting in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching voting
	 * @throws NoSuchVotingException if a matching voting could not be found
	 */
	@Override
	public Voting findByF_G_CLNAME_CLPK_First(long groupId, String className,
		String classPK, OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = fetchByF_G_CLNAME_CLPK_First(groupId, className,
				classPK, orderByComparator);

		if (voting != null) {
			return voting;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", className=");
		msg.append(className);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchVotingException(msg.toString());
	}

	/**
	 * Returns the first voting in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching voting, or <code>null</code> if a matching voting could not be found
	 */
	@Override
	public Voting fetchByF_G_CLNAME_CLPK_First(long groupId, String className,
		String classPK, OrderByComparator<Voting> orderByComparator) {
		List<Voting> list = findByF_G_CLNAME_CLPK(groupId, className, classPK,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last voting in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching voting
	 * @throws NoSuchVotingException if a matching voting could not be found
	 */
	@Override
	public Voting findByF_G_CLNAME_CLPK_Last(long groupId, String className,
		String classPK, OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = fetchByF_G_CLNAME_CLPK_Last(groupId, className,
				classPK, orderByComparator);

		if (voting != null) {
			return voting;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", className=");
		msg.append(className);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchVotingException(msg.toString());
	}

	/**
	 * Returns the last voting in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching voting, or <code>null</code> if a matching voting could not be found
	 */
	@Override
	public Voting fetchByF_G_CLNAME_CLPK_Last(long groupId, String className,
		String classPK, OrderByComparator<Voting> orderByComparator) {
		int count = countByF_G_CLNAME_CLPK(groupId, className, classPK);

		if (count == 0) {
			return null;
		}

		List<Voting> list = findByF_G_CLNAME_CLPK(groupId, className, classPK,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the votings before and after the current voting in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param votingId the primary key of the current voting
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next voting
	 * @throws NoSuchVotingException if a voting with the primary key could not be found
	 */
	@Override
	public Voting[] findByF_G_CLNAME_CLPK_PrevAndNext(long votingId,
		long groupId, String className, String classPK,
		OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = findByPrimaryKey(votingId);

		Session session = null;

		try {
			session = openSession();

			Voting[] array = new VotingImpl[3];

			array[0] = getByF_G_CLNAME_CLPK_PrevAndNext(session, voting,
					groupId, className, classPK, orderByComparator, true);

			array[1] = voting;

			array[2] = getByF_G_CLNAME_CLPK_PrevAndNext(session, voting,
					groupId, className, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Voting getByF_G_CLNAME_CLPK_PrevAndNext(Session session,
		Voting voting, long groupId, String className, String classPK,
		OrderByComparator<Voting> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_VOTING_WHERE);

		query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_GROUPID_2);

		boolean bindClassName = false;

		if (className == null) {
			query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSNAME_1);
		}
		else if (className.equals("")) {
			query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSNAME_3);
		}
		else {
			bindClassName = true;

			query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSNAME_2);
		}

		boolean bindClassPK = false;

		if (classPK == null) {
			query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSPK_1);
		}
		else if (classPK.equals("")) {
			query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSPK_3);
		}
		else {
			bindClassPK = true;

			query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSPK_2);
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
			query.append(VotingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindClassName) {
			qPos.add(className);
		}

		if (bindClassPK) {
			qPos.add(classPK);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(voting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Voting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the votings where groupId = &#63; and className = &#63; and classPK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 */
	@Override
	public void removeByF_G_CLNAME_CLPK(long groupId, String className,
		String classPK) {
		for (Voting voting : findByF_G_CLNAME_CLPK(groupId, className, classPK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(voting);
		}
	}

	/**
	 * Returns the number of votings where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @return the number of matching votings
	 */
	@Override
	public int countByF_G_CLNAME_CLPK(long groupId, String className,
		String classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_G_CLNAME_CLPK;

		Object[] finderArgs = new Object[] { groupId, className, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VOTING_WHERE);

			query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_GROUPID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSNAME_2);
			}

			boolean bindClassPK = false;

			if (classPK == null) {
				query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSPK_1);
			}
			else if (classPK.equals("")) {
				query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSPK_3);
			}
			else {
				bindClassPK = true;

				query.append(_FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSPK_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindClassName) {
					qPos.add(className);
				}

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

	private static final String _FINDER_COLUMN_F_G_CLNAME_CLPK_GROUPID_2 = "voting.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSNAME_1 = "voting.className IS NULL AND ";
	private static final String _FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSNAME_2 = "voting.className = ? AND ";
	private static final String _FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSNAME_3 = "(voting.className IS NULL OR voting.className = '') AND ";
	private static final String _FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSPK_1 = "voting.classPK IS NULL";
	private static final String _FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSPK_2 = "voting.classPK = ?";
	private static final String _FINDER_COLUMN_F_G_CLNAME_CLPK_CLASSPK_3 = "(voting.classPK IS NULL OR voting.classPK = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_CLNAME_VC =
		new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, VotingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_CLNAME_VC",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLNAME_VC =
		new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, VotingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_CLNAME_VC",
			new String[] { String.class.getName(), String.class.getName() },
			VotingModelImpl.CLASSNAME_COLUMN_BITMASK |
			VotingModelImpl.VOTINGCODE_COLUMN_BITMASK |
			VotingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_CLNAME_VC = new FinderPath(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_CLNAME_VC",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns all the votings where className = &#63; and votingCode = &#63;.
	 *
	 * @param className the class name
	 * @param votingCode the voting code
	 * @return the matching votings
	 */
	@Override
	public List<Voting> findByF_CLNAME_VC(String className, String votingCode) {
		return findByF_CLNAME_VC(className, votingCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the votings where className = &#63; and votingCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param className the class name
	 * @param votingCode the voting code
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @return the range of matching votings
	 */
	@Override
	public List<Voting> findByF_CLNAME_VC(String className, String votingCode,
		int start, int end) {
		return findByF_CLNAME_VC(className, votingCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the votings where className = &#63; and votingCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param className the class name
	 * @param votingCode the voting code
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching votings
	 */
	@Override
	public List<Voting> findByF_CLNAME_VC(String className, String votingCode,
		int start, int end, OrderByComparator<Voting> orderByComparator) {
		return findByF_CLNAME_VC(className, votingCode, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the votings where className = &#63; and votingCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param className the class name
	 * @param votingCode the voting code
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching votings
	 */
	@Override
	public List<Voting> findByF_CLNAME_VC(String className, String votingCode,
		int start, int end, OrderByComparator<Voting> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLNAME_VC;
			finderArgs = new Object[] { className, votingCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_CLNAME_VC;
			finderArgs = new Object[] {
					className, votingCode,
					
					start, end, orderByComparator
				};
		}

		List<Voting> list = null;

		if (retrieveFromCache) {
			list = (List<Voting>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Voting voting : list) {
					if (!Objects.equals(className, voting.getClassName()) ||
							!Objects.equals(votingCode, voting.getVotingCode())) {
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

			query.append(_SQL_SELECT_VOTING_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_CLNAME_VC_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_CLNAME_VC_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_CLNAME_VC_CLASSNAME_2);
			}

			boolean bindVotingCode = false;

			if (votingCode == null) {
				query.append(_FINDER_COLUMN_F_CLNAME_VC_VOTINGCODE_1);
			}
			else if (votingCode.equals("")) {
				query.append(_FINDER_COLUMN_F_CLNAME_VC_VOTINGCODE_3);
			}
			else {
				bindVotingCode = true;

				query.append(_FINDER_COLUMN_F_CLNAME_VC_VOTINGCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(VotingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindVotingCode) {
					qPos.add(votingCode);
				}

				if (!pagination) {
					list = (List<Voting>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Voting>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first voting in the ordered set where className = &#63; and votingCode = &#63;.
	 *
	 * @param className the class name
	 * @param votingCode the voting code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching voting
	 * @throws NoSuchVotingException if a matching voting could not be found
	 */
	@Override
	public Voting findByF_CLNAME_VC_First(String className, String votingCode,
		OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = fetchByF_CLNAME_VC_First(className, votingCode,
				orderByComparator);

		if (voting != null) {
			return voting;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("className=");
		msg.append(className);

		msg.append(", votingCode=");
		msg.append(votingCode);

		msg.append("}");

		throw new NoSuchVotingException(msg.toString());
	}

	/**
	 * Returns the first voting in the ordered set where className = &#63; and votingCode = &#63;.
	 *
	 * @param className the class name
	 * @param votingCode the voting code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching voting, or <code>null</code> if a matching voting could not be found
	 */
	@Override
	public Voting fetchByF_CLNAME_VC_First(String className, String votingCode,
		OrderByComparator<Voting> orderByComparator) {
		List<Voting> list = findByF_CLNAME_VC(className, votingCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last voting in the ordered set where className = &#63; and votingCode = &#63;.
	 *
	 * @param className the class name
	 * @param votingCode the voting code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching voting
	 * @throws NoSuchVotingException if a matching voting could not be found
	 */
	@Override
	public Voting findByF_CLNAME_VC_Last(String className, String votingCode,
		OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = fetchByF_CLNAME_VC_Last(className, votingCode,
				orderByComparator);

		if (voting != null) {
			return voting;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("className=");
		msg.append(className);

		msg.append(", votingCode=");
		msg.append(votingCode);

		msg.append("}");

		throw new NoSuchVotingException(msg.toString());
	}

	/**
	 * Returns the last voting in the ordered set where className = &#63; and votingCode = &#63;.
	 *
	 * @param className the class name
	 * @param votingCode the voting code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching voting, or <code>null</code> if a matching voting could not be found
	 */
	@Override
	public Voting fetchByF_CLNAME_VC_Last(String className, String votingCode,
		OrderByComparator<Voting> orderByComparator) {
		int count = countByF_CLNAME_VC(className, votingCode);

		if (count == 0) {
			return null;
		}

		List<Voting> list = findByF_CLNAME_VC(className, votingCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the votings before and after the current voting in the ordered set where className = &#63; and votingCode = &#63;.
	 *
	 * @param votingId the primary key of the current voting
	 * @param className the class name
	 * @param votingCode the voting code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next voting
	 * @throws NoSuchVotingException if a voting with the primary key could not be found
	 */
	@Override
	public Voting[] findByF_CLNAME_VC_PrevAndNext(long votingId,
		String className, String votingCode,
		OrderByComparator<Voting> orderByComparator)
		throws NoSuchVotingException {
		Voting voting = findByPrimaryKey(votingId);

		Session session = null;

		try {
			session = openSession();

			Voting[] array = new VotingImpl[3];

			array[0] = getByF_CLNAME_VC_PrevAndNext(session, voting, className,
					votingCode, orderByComparator, true);

			array[1] = voting;

			array[2] = getByF_CLNAME_VC_PrevAndNext(session, voting, className,
					votingCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Voting getByF_CLNAME_VC_PrevAndNext(Session session,
		Voting voting, String className, String votingCode,
		OrderByComparator<Voting> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_VOTING_WHERE);

		boolean bindClassName = false;

		if (className == null) {
			query.append(_FINDER_COLUMN_F_CLNAME_VC_CLASSNAME_1);
		}
		else if (className.equals("")) {
			query.append(_FINDER_COLUMN_F_CLNAME_VC_CLASSNAME_3);
		}
		else {
			bindClassName = true;

			query.append(_FINDER_COLUMN_F_CLNAME_VC_CLASSNAME_2);
		}

		boolean bindVotingCode = false;

		if (votingCode == null) {
			query.append(_FINDER_COLUMN_F_CLNAME_VC_VOTINGCODE_1);
		}
		else if (votingCode.equals("")) {
			query.append(_FINDER_COLUMN_F_CLNAME_VC_VOTINGCODE_3);
		}
		else {
			bindVotingCode = true;

			query.append(_FINDER_COLUMN_F_CLNAME_VC_VOTINGCODE_2);
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
			query.append(VotingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindClassName) {
			qPos.add(className);
		}

		if (bindVotingCode) {
			qPos.add(votingCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(voting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Voting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the votings where className = &#63; and votingCode = &#63; from the database.
	 *
	 * @param className the class name
	 * @param votingCode the voting code
	 */
	@Override
	public void removeByF_CLNAME_VC(String className, String votingCode) {
		for (Voting voting : findByF_CLNAME_VC(className, votingCode,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(voting);
		}
	}

	/**
	 * Returns the number of votings where className = &#63; and votingCode = &#63;.
	 *
	 * @param className the class name
	 * @param votingCode the voting code
	 * @return the number of matching votings
	 */
	@Override
	public int countByF_CLNAME_VC(String className, String votingCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_CLNAME_VC;

		Object[] finderArgs = new Object[] { className, votingCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VOTING_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_CLNAME_VC_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_CLNAME_VC_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_CLNAME_VC_CLASSNAME_2);
			}

			boolean bindVotingCode = false;

			if (votingCode == null) {
				query.append(_FINDER_COLUMN_F_CLNAME_VC_VOTINGCODE_1);
			}
			else if (votingCode.equals("")) {
				query.append(_FINDER_COLUMN_F_CLNAME_VC_VOTINGCODE_3);
			}
			else {
				bindVotingCode = true;

				query.append(_FINDER_COLUMN_F_CLNAME_VC_VOTINGCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindVotingCode) {
					qPos.add(votingCode);
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

	private static final String _FINDER_COLUMN_F_CLNAME_VC_CLASSNAME_1 = "voting.className IS NULL AND ";
	private static final String _FINDER_COLUMN_F_CLNAME_VC_CLASSNAME_2 = "voting.className = ? AND ";
	private static final String _FINDER_COLUMN_F_CLNAME_VC_CLASSNAME_3 = "(voting.className IS NULL OR voting.className = '') AND ";
	private static final String _FINDER_COLUMN_F_CLNAME_VC_VOTINGCODE_1 = "voting.votingCode IS NULL";
	private static final String _FINDER_COLUMN_F_CLNAME_VC_VOTINGCODE_2 = "voting.votingCode = ?";
	private static final String _FINDER_COLUMN_F_CLNAME_VC_VOTINGCODE_3 = "(voting.votingCode IS NULL OR voting.votingCode = '')";

	public VotingPersistenceImpl() {
		setModelClass(Voting.class);

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
	 * Caches the voting in the entity cache if it is enabled.
	 *
	 * @param voting the voting
	 */
	@Override
	public void cacheResult(Voting voting) {
		entityCache.putResult(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingImpl.class, voting.getPrimaryKey(), voting);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { voting.getUuid(), voting.getGroupId() }, voting);

		voting.resetOriginalValues();
	}

	/**
	 * Caches the votings in the entity cache if it is enabled.
	 *
	 * @param votings the votings
	 */
	@Override
	public void cacheResult(List<Voting> votings) {
		for (Voting voting : votings) {
			if (entityCache.getResult(VotingModelImpl.ENTITY_CACHE_ENABLED,
						VotingImpl.class, voting.getPrimaryKey()) == null) {
				cacheResult(voting);
			}
			else {
				voting.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all votings.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VotingImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the voting.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Voting voting) {
		entityCache.removeResult(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingImpl.class, voting.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((VotingModelImpl)voting, true);
	}

	@Override
	public void clearCache(List<Voting> votings) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Voting voting : votings) {
			entityCache.removeResult(VotingModelImpl.ENTITY_CACHE_ENABLED,
				VotingImpl.class, voting.getPrimaryKey());

			clearUniqueFindersCache((VotingModelImpl)voting, true);
		}
	}

	protected void cacheUniqueFindersCache(VotingModelImpl votingModelImpl) {
		Object[] args = new Object[] {
				votingModelImpl.getUuid(), votingModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			votingModelImpl, false);
	}

	protected void clearUniqueFindersCache(VotingModelImpl votingModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					votingModelImpl.getUuid(), votingModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((votingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					votingModelImpl.getOriginalUuid(),
					votingModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new voting with the primary key. Does not add the voting to the database.
	 *
	 * @param votingId the primary key for the new voting
	 * @return the new voting
	 */
	@Override
	public Voting create(long votingId) {
		Voting voting = new VotingImpl();

		voting.setNew(true);
		voting.setPrimaryKey(votingId);

		String uuid = PortalUUIDUtil.generate();

		voting.setUuid(uuid);

		voting.setCompanyId(companyProvider.getCompanyId());

		return voting;
	}

	/**
	 * Removes the voting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param votingId the primary key of the voting
	 * @return the voting that was removed
	 * @throws NoSuchVotingException if a voting with the primary key could not be found
	 */
	@Override
	public Voting remove(long votingId) throws NoSuchVotingException {
		return remove((Serializable)votingId);
	}

	/**
	 * Removes the voting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the voting
	 * @return the voting that was removed
	 * @throws NoSuchVotingException if a voting with the primary key could not be found
	 */
	@Override
	public Voting remove(Serializable primaryKey) throws NoSuchVotingException {
		Session session = null;

		try {
			session = openSession();

			Voting voting = (Voting)session.get(VotingImpl.class, primaryKey);

			if (voting == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVotingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(voting);
		}
		catch (NoSuchVotingException nsee) {
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
	protected Voting removeImpl(Voting voting) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(voting)) {
				voting = (Voting)session.get(VotingImpl.class,
						voting.getPrimaryKeyObj());
			}

			if (voting != null) {
				session.delete(voting);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (voting != null) {
			clearCache(voting);
		}

		return voting;
	}

	@Override
	public Voting updateImpl(Voting voting) {
		boolean isNew = voting.isNew();

		if (!(voting instanceof VotingModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(voting.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(voting);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in voting proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Voting implementation " +
				voting.getClass());
		}

		VotingModelImpl votingModelImpl = (VotingModelImpl)voting;

		if (Validator.isNull(voting.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			voting.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (voting.getCreateDate() == null)) {
			if (serviceContext == null) {
				voting.setCreateDate(now);
			}
			else {
				voting.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!votingModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				voting.setModifiedDate(now);
			}
			else {
				voting.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (voting.isNew()) {
				session.save(voting);

				voting.setNew(false);
			}
			else {
				voting = (Voting)session.merge(voting);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!VotingModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { votingModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					votingModelImpl.getUuid(), votingModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					votingModelImpl.getClassName(), votingModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLNAME_CLPK, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLNAME_CLPK,
				args);

			args = new Object[] {
					votingModelImpl.getGroupId(), votingModelImpl.getClassName(),
					votingModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_G_CLNAME_CLPK, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_CLNAME_CLPK,
				args);

			args = new Object[] {
					votingModelImpl.getClassName(),
					votingModelImpl.getVotingCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLNAME_VC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLNAME_VC,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((votingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { votingModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { votingModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((votingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						votingModelImpl.getOriginalUuid(),
						votingModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						votingModelImpl.getUuid(),
						votingModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((votingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLNAME_CLPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						votingModelImpl.getOriginalClassName(),
						votingModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLNAME_CLPK,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLNAME_CLPK,
					args);

				args = new Object[] {
						votingModelImpl.getClassName(),
						votingModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLNAME_CLPK,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLNAME_CLPK,
					args);
			}

			if ((votingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_CLNAME_CLPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						votingModelImpl.getOriginalGroupId(),
						votingModelImpl.getOriginalClassName(),
						votingModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_G_CLNAME_CLPK,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_CLNAME_CLPK,
					args);

				args = new Object[] {
						votingModelImpl.getGroupId(),
						votingModelImpl.getClassName(),
						votingModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_G_CLNAME_CLPK,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_CLNAME_CLPK,
					args);
			}

			if ((votingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLNAME_VC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						votingModelImpl.getOriginalClassName(),
						votingModelImpl.getOriginalVotingCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLNAME_VC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLNAME_VC,
					args);

				args = new Object[] {
						votingModelImpl.getClassName(),
						votingModelImpl.getVotingCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLNAME_VC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLNAME_VC,
					args);
			}
		}

		entityCache.putResult(VotingModelImpl.ENTITY_CACHE_ENABLED,
			VotingImpl.class, voting.getPrimaryKey(), voting, false);

		clearUniqueFindersCache(votingModelImpl, false);
		cacheUniqueFindersCache(votingModelImpl);

		voting.resetOriginalValues();

		return voting;
	}

	/**
	 * Returns the voting with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the voting
	 * @return the voting
	 * @throws NoSuchVotingException if a voting with the primary key could not be found
	 */
	@Override
	public Voting findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVotingException {
		Voting voting = fetchByPrimaryKey(primaryKey);

		if (voting == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVotingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return voting;
	}

	/**
	 * Returns the voting with the primary key or throws a {@link NoSuchVotingException} if it could not be found.
	 *
	 * @param votingId the primary key of the voting
	 * @return the voting
	 * @throws NoSuchVotingException if a voting with the primary key could not be found
	 */
	@Override
	public Voting findByPrimaryKey(long votingId) throws NoSuchVotingException {
		return findByPrimaryKey((Serializable)votingId);
	}

	/**
	 * Returns the voting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the voting
	 * @return the voting, or <code>null</code> if a voting with the primary key could not be found
	 */
	@Override
	public Voting fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VotingModelImpl.ENTITY_CACHE_ENABLED,
				VotingImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Voting voting = (Voting)serializable;

		if (voting == null) {
			Session session = null;

			try {
				session = openSession();

				voting = (Voting)session.get(VotingImpl.class, primaryKey);

				if (voting != null) {
					cacheResult(voting);
				}
				else {
					entityCache.putResult(VotingModelImpl.ENTITY_CACHE_ENABLED,
						VotingImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VotingModelImpl.ENTITY_CACHE_ENABLED,
					VotingImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return voting;
	}

	/**
	 * Returns the voting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param votingId the primary key of the voting
	 * @return the voting, or <code>null</code> if a voting with the primary key could not be found
	 */
	@Override
	public Voting fetchByPrimaryKey(long votingId) {
		return fetchByPrimaryKey((Serializable)votingId);
	}

	@Override
	public Map<Serializable, Voting> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Voting> map = new HashMap<Serializable, Voting>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Voting voting = fetchByPrimaryKey(primaryKey);

			if (voting != null) {
				map.put(primaryKey, voting);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VotingModelImpl.ENTITY_CACHE_ENABLED,
					VotingImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Voting)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VOTING_WHERE_PKS_IN);

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

			for (Voting voting : (List<Voting>)q.list()) {
				map.put(voting.getPrimaryKeyObj(), voting);

				cacheResult(voting);

				uncachedPrimaryKeys.remove(voting.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VotingModelImpl.ENTITY_CACHE_ENABLED,
					VotingImpl.class, primaryKey, nullModel);
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
	 * Returns all the votings.
	 *
	 * @return the votings
	 */
	@Override
	public List<Voting> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the votings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @return the range of votings
	 */
	@Override
	public List<Voting> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the votings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of votings
	 */
	@Override
	public List<Voting> findAll(int start, int end,
		OrderByComparator<Voting> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the votings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of votings
	 * @param end the upper bound of the range of votings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of votings
	 */
	@Override
	public List<Voting> findAll(int start, int end,
		OrderByComparator<Voting> orderByComparator, boolean retrieveFromCache) {
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

		List<Voting> list = null;

		if (retrieveFromCache) {
			list = (List<Voting>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VOTING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VOTING;

				if (pagination) {
					sql = sql.concat(VotingModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Voting>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Voting>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the votings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Voting voting : findAll()) {
			remove(voting);
		}
	}

	/**
	 * Returns the number of votings.
	 *
	 * @return the number of votings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VOTING);

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
		return VotingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the voting persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VotingImpl.class.getName());
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
	private static final String _SQL_SELECT_VOTING = "SELECT voting FROM Voting voting";
	private static final String _SQL_SELECT_VOTING_WHERE_PKS_IN = "SELECT voting FROM Voting voting WHERE votingId IN (";
	private static final String _SQL_SELECT_VOTING_WHERE = "SELECT voting FROM Voting voting WHERE ";
	private static final String _SQL_COUNT_VOTING = "SELECT COUNT(voting) FROM Voting voting";
	private static final String _SQL_COUNT_VOTING_WHERE = "SELECT COUNT(voting) FROM Voting voting WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "voting.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Voting exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Voting exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(VotingPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}