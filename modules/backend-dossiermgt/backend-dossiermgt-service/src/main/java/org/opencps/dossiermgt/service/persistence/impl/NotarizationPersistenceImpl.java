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
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchNotarizationException;
import org.opencps.dossiermgt.model.Notarization;
import org.opencps.dossiermgt.model.impl.NotarizationImpl;
import org.opencps.dossiermgt.model.impl.NotarizationModelImpl;
import org.opencps.dossiermgt.service.persistence.NotarizationPersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the notarization service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see NotarizationPersistence
 * @see org.opencps.dossiermgt.service.persistence.NotarizationUtil
 * @generated
 */
@ProviderType
public class NotarizationPersistenceImpl extends BasePersistenceImpl<Notarization>
	implements NotarizationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NotarizationUtil} to access the notarization persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NotarizationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
			NotarizationModelImpl.FINDER_CACHE_ENABLED, NotarizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
			NotarizationModelImpl.FINDER_CACHE_ENABLED, NotarizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
			NotarizationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_G_DID = new FinderPath(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
			NotarizationModelImpl.FINDER_CACHE_ENABLED, NotarizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_G_DID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_DID =
		new FinderPath(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
			NotarizationModelImpl.FINDER_CACHE_ENABLED, NotarizationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_G_DID",
			new String[] { Long.class.getName(), Long.class.getName() },
			NotarizationModelImpl.GROUPID_COLUMN_BITMASK |
			NotarizationModelImpl.DOSSIERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_G_DID = new FinderPath(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
			NotarizationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_G_DID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the notarizations where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @return the matching notarizations
	 */
	@Override
	public List<Notarization> findByF_G_DID(long groupId, long dossierId) {
		return findByF_G_DID(groupId, dossierId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notarizations where groupId = &#63; and dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of notarizations
	 * @param end the upper bound of the range of notarizations (not inclusive)
	 * @return the range of matching notarizations
	 */
	@Override
	public List<Notarization> findByF_G_DID(long groupId, long dossierId,
		int start, int end) {
		return findByF_G_DID(groupId, dossierId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notarizations where groupId = &#63; and dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of notarizations
	 * @param end the upper bound of the range of notarizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notarizations
	 */
	@Override
	public List<Notarization> findByF_G_DID(long groupId, long dossierId,
		int start, int end, OrderByComparator<Notarization> orderByComparator) {
		return findByF_G_DID(groupId, dossierId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the notarizations where groupId = &#63; and dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of notarizations
	 * @param end the upper bound of the range of notarizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching notarizations
	 */
	@Override
	public List<Notarization> findByF_G_DID(long groupId, long dossierId,
		int start, int end, OrderByComparator<Notarization> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_DID;
			finderArgs = new Object[] { groupId, dossierId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_G_DID;
			finderArgs = new Object[] {
					groupId, dossierId,
					
					start, end, orderByComparator
				};
		}

		List<Notarization> list = null;

		if (retrieveFromCache) {
			list = (List<Notarization>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Notarization notarization : list) {
					if ((groupId != notarization.getGroupId()) ||
							(dossierId != notarization.getDossierId())) {
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

			query.append(_SQL_SELECT_NOTARIZATION_WHERE);

			query.append(_FINDER_COLUMN_F_G_DID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_G_DID_DOSSIERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(NotarizationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				if (!pagination) {
					list = (List<Notarization>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Notarization>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notarization
	 * @throws NoSuchNotarizationException if a matching notarization could not be found
	 */
	@Override
	public Notarization findByF_G_DID_First(long groupId, long dossierId,
		OrderByComparator<Notarization> orderByComparator)
		throws NoSuchNotarizationException {
		Notarization notarization = fetchByF_G_DID_First(groupId, dossierId,
				orderByComparator);

		if (notarization != null) {
			return notarization;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchNotarizationException(msg.toString());
	}

	/**
	 * Returns the first notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notarization, or <code>null</code> if a matching notarization could not be found
	 */
	@Override
	public Notarization fetchByF_G_DID_First(long groupId, long dossierId,
		OrderByComparator<Notarization> orderByComparator) {
		List<Notarization> list = findByF_G_DID(groupId, dossierId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notarization
	 * @throws NoSuchNotarizationException if a matching notarization could not be found
	 */
	@Override
	public Notarization findByF_G_DID_Last(long groupId, long dossierId,
		OrderByComparator<Notarization> orderByComparator)
		throws NoSuchNotarizationException {
		Notarization notarization = fetchByF_G_DID_Last(groupId, dossierId,
				orderByComparator);

		if (notarization != null) {
			return notarization;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchNotarizationException(msg.toString());
	}

	/**
	 * Returns the last notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notarization, or <code>null</code> if a matching notarization could not be found
	 */
	@Override
	public Notarization fetchByF_G_DID_Last(long groupId, long dossierId,
		OrderByComparator<Notarization> orderByComparator) {
		int count = countByF_G_DID(groupId, dossierId);

		if (count == 0) {
			return null;
		}

		List<Notarization> list = findByF_G_DID(groupId, dossierId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notarizations before and after the current notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param notarizationId the primary key of the current notarization
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notarization
	 * @throws NoSuchNotarizationException if a notarization with the primary key could not be found
	 */
	@Override
	public Notarization[] findByF_G_DID_PrevAndNext(long notarizationId,
		long groupId, long dossierId,
		OrderByComparator<Notarization> orderByComparator)
		throws NoSuchNotarizationException {
		Notarization notarization = findByPrimaryKey(notarizationId);

		Session session = null;

		try {
			session = openSession();

			Notarization[] array = new NotarizationImpl[3];

			array[0] = getByF_G_DID_PrevAndNext(session, notarization, groupId,
					dossierId, orderByComparator, true);

			array[1] = notarization;

			array[2] = getByF_G_DID_PrevAndNext(session, notarization, groupId,
					dossierId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Notarization getByF_G_DID_PrevAndNext(Session session,
		Notarization notarization, long groupId, long dossierId,
		OrderByComparator<Notarization> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_NOTARIZATION_WHERE);

		query.append(_FINDER_COLUMN_F_G_DID_GROUPID_2);

		query.append(_FINDER_COLUMN_F_G_DID_DOSSIERID_2);

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
			query.append(NotarizationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(dossierId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(notarization);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Notarization> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notarizations where groupId = &#63; and dossierId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 */
	@Override
	public void removeByF_G_DID(long groupId, long dossierId) {
		for (Notarization notarization : findByF_G_DID(groupId, dossierId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(notarization);
		}
	}

	/**
	 * Returns the number of notarizations where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @return the number of matching notarizations
	 */
	@Override
	public int countByF_G_DID(long groupId, long dossierId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_G_DID;

		Object[] finderArgs = new Object[] { groupId, dossierId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_NOTARIZATION_WHERE);

			query.append(_FINDER_COLUMN_F_G_DID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_G_DID_DOSSIERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_F_G_DID_GROUPID_2 = "notarization.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_G_DID_DOSSIERID_2 = "notarization.dossierId = ?";

	public NotarizationPersistenceImpl() {
		setModelClass(Notarization.class);
	}

	/**
	 * Caches the notarization in the entity cache if it is enabled.
	 *
	 * @param notarization the notarization
	 */
	@Override
	public void cacheResult(Notarization notarization) {
		entityCache.putResult(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
			NotarizationImpl.class, notarization.getPrimaryKey(), notarization);

		notarization.resetOriginalValues();
	}

	/**
	 * Caches the notarizations in the entity cache if it is enabled.
	 *
	 * @param notarizations the notarizations
	 */
	@Override
	public void cacheResult(List<Notarization> notarizations) {
		for (Notarization notarization : notarizations) {
			if (entityCache.getResult(
						NotarizationModelImpl.ENTITY_CACHE_ENABLED,
						NotarizationImpl.class, notarization.getPrimaryKey()) == null) {
				cacheResult(notarization);
			}
			else {
				notarization.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all notarizations.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NotarizationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the notarization.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Notarization notarization) {
		entityCache.removeResult(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
			NotarizationImpl.class, notarization.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Notarization> notarizations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Notarization notarization : notarizations) {
			entityCache.removeResult(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
				NotarizationImpl.class, notarization.getPrimaryKey());
		}
	}

	/**
	 * Creates a new notarization with the primary key. Does not add the notarization to the database.
	 *
	 * @param notarizationId the primary key for the new notarization
	 * @return the new notarization
	 */
	@Override
	public Notarization create(long notarizationId) {
		Notarization notarization = new NotarizationImpl();

		notarization.setNew(true);
		notarization.setPrimaryKey(notarizationId);

		notarization.setCompanyId(companyProvider.getCompanyId());

		return notarization;
	}

	/**
	 * Removes the notarization with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notarizationId the primary key of the notarization
	 * @return the notarization that was removed
	 * @throws NoSuchNotarizationException if a notarization with the primary key could not be found
	 */
	@Override
	public Notarization remove(long notarizationId)
		throws NoSuchNotarizationException {
		return remove((Serializable)notarizationId);
	}

	/**
	 * Removes the notarization with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the notarization
	 * @return the notarization that was removed
	 * @throws NoSuchNotarizationException if a notarization with the primary key could not be found
	 */
	@Override
	public Notarization remove(Serializable primaryKey)
		throws NoSuchNotarizationException {
		Session session = null;

		try {
			session = openSession();

			Notarization notarization = (Notarization)session.get(NotarizationImpl.class,
					primaryKey);

			if (notarization == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNotarizationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(notarization);
		}
		catch (NoSuchNotarizationException nsee) {
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
	protected Notarization removeImpl(Notarization notarization) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(notarization)) {
				notarization = (Notarization)session.get(NotarizationImpl.class,
						notarization.getPrimaryKeyObj());
			}

			if (notarization != null) {
				session.delete(notarization);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (notarization != null) {
			clearCache(notarization);
		}

		return notarization;
	}

	@Override
	public Notarization updateImpl(Notarization notarization) {
		boolean isNew = notarization.isNew();

		if (!(notarization instanceof NotarizationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(notarization.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(notarization);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in notarization proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Notarization implementation " +
				notarization.getClass());
		}

		NotarizationModelImpl notarizationModelImpl = (NotarizationModelImpl)notarization;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (notarization.getCreateDate() == null)) {
			if (serviceContext == null) {
				notarization.setCreateDate(now);
			}
			else {
				notarization.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!notarizationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				notarization.setModifiedDate(now);
			}
			else {
				notarization.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (notarization.isNew()) {
				session.save(notarization);

				notarization.setNew(false);
			}
			else {
				notarization = (Notarization)session.merge(notarization);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!NotarizationModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					notarizationModelImpl.getGroupId(),
					notarizationModelImpl.getDossierId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_G_DID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_DID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((notarizationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_DID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						notarizationModelImpl.getOriginalGroupId(),
						notarizationModelImpl.getOriginalDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_G_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_DID,
					args);

				args = new Object[] {
						notarizationModelImpl.getGroupId(),
						notarizationModelImpl.getDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_G_DID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_DID,
					args);
			}
		}

		entityCache.putResult(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
			NotarizationImpl.class, notarization.getPrimaryKey(), notarization,
			false);

		notarization.resetOriginalValues();

		return notarization;
	}

	/**
	 * Returns the notarization with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the notarization
	 * @return the notarization
	 * @throws NoSuchNotarizationException if a notarization with the primary key could not be found
	 */
	@Override
	public Notarization findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNotarizationException {
		Notarization notarization = fetchByPrimaryKey(primaryKey);

		if (notarization == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNotarizationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return notarization;
	}

	/**
	 * Returns the notarization with the primary key or throws a {@link NoSuchNotarizationException} if it could not be found.
	 *
	 * @param notarizationId the primary key of the notarization
	 * @return the notarization
	 * @throws NoSuchNotarizationException if a notarization with the primary key could not be found
	 */
	@Override
	public Notarization findByPrimaryKey(long notarizationId)
		throws NoSuchNotarizationException {
		return findByPrimaryKey((Serializable)notarizationId);
	}

	/**
	 * Returns the notarization with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the notarization
	 * @return the notarization, or <code>null</code> if a notarization with the primary key could not be found
	 */
	@Override
	public Notarization fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
				NotarizationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Notarization notarization = (Notarization)serializable;

		if (notarization == null) {
			Session session = null;

			try {
				session = openSession();

				notarization = (Notarization)session.get(NotarizationImpl.class,
						primaryKey);

				if (notarization != null) {
					cacheResult(notarization);
				}
				else {
					entityCache.putResult(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
						NotarizationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
					NotarizationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return notarization;
	}

	/**
	 * Returns the notarization with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notarizationId the primary key of the notarization
	 * @return the notarization, or <code>null</code> if a notarization with the primary key could not be found
	 */
	@Override
	public Notarization fetchByPrimaryKey(long notarizationId) {
		return fetchByPrimaryKey((Serializable)notarizationId);
	}

	@Override
	public Map<Serializable, Notarization> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Notarization> map = new HashMap<Serializable, Notarization>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Notarization notarization = fetchByPrimaryKey(primaryKey);

			if (notarization != null) {
				map.put(primaryKey, notarization);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
					NotarizationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Notarization)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_NOTARIZATION_WHERE_PKS_IN);

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

			for (Notarization notarization : (List<Notarization>)q.list()) {
				map.put(notarization.getPrimaryKeyObj(), notarization);

				cacheResult(notarization);

				uncachedPrimaryKeys.remove(notarization.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(NotarizationModelImpl.ENTITY_CACHE_ENABLED,
					NotarizationImpl.class, primaryKey, nullModel);
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
	 * Returns all the notarizations.
	 *
	 * @return the notarizations
	 */
	@Override
	public List<Notarization> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notarizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of notarizations
	 * @param end the upper bound of the range of notarizations (not inclusive)
	 * @return the range of notarizations
	 */
	@Override
	public List<Notarization> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the notarizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of notarizations
	 * @param end the upper bound of the range of notarizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of notarizations
	 */
	@Override
	public List<Notarization> findAll(int start, int end,
		OrderByComparator<Notarization> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notarizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of notarizations
	 * @param end the upper bound of the range of notarizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of notarizations
	 */
	@Override
	public List<Notarization> findAll(int start, int end,
		OrderByComparator<Notarization> orderByComparator,
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

		List<Notarization> list = null;

		if (retrieveFromCache) {
			list = (List<Notarization>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NOTARIZATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NOTARIZATION;

				if (pagination) {
					sql = sql.concat(NotarizationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Notarization>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Notarization>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the notarizations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Notarization notarization : findAll()) {
			remove(notarization);
		}
	}

	/**
	 * Returns the number of notarizations.
	 *
	 * @return the number of notarizations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NOTARIZATION);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return NotarizationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the notarization persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NotarizationImpl.class.getName());
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
	private static final String _SQL_SELECT_NOTARIZATION = "SELECT notarization FROM Notarization notarization";
	private static final String _SQL_SELECT_NOTARIZATION_WHERE_PKS_IN = "SELECT notarization FROM Notarization notarization WHERE notarizationId IN (";
	private static final String _SQL_SELECT_NOTARIZATION_WHERE = "SELECT notarization FROM Notarization notarization WHERE ";
	private static final String _SQL_COUNT_NOTARIZATION = "SELECT COUNT(notarization) FROM Notarization notarization";
	private static final String _SQL_COUNT_NOTARIZATION_WHERE = "SELECT COUNT(notarization) FROM Notarization notarization WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "notarization.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Notarization exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Notarization exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(NotarizationPersistenceImpl.class);
}