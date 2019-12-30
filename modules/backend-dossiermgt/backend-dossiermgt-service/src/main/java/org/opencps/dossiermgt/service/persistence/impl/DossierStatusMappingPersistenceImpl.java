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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchDossierStatusMappingException;
import org.opencps.dossiermgt.model.DossierStatusMapping;
import org.opencps.dossiermgt.model.impl.DossierStatusMappingImpl;
import org.opencps.dossiermgt.model.impl.DossierStatusMappingModelImpl;
import org.opencps.dossiermgt.service.persistence.DossierStatusMappingPersistence;

import java.io.Serializable;

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
 * The persistence implementation for the dossier status mapping service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierStatusMappingPersistence
 * @see org.opencps.dossiermgt.service.persistence.DossierStatusMappingUtil
 * @generated
 */
@ProviderType
public class DossierStatusMappingPersistenceImpl extends BasePersistenceImpl<DossierStatusMapping>
	implements DossierStatusMappingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DossierStatusMappingUtil} to access the dossier status mapping persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DossierStatusMappingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
			DossierStatusMappingModelImpl.FINDER_CACHE_ENABLED,
			DossierStatusMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
			DossierStatusMappingModelImpl.FINDER_CACHE_ENABLED,
			DossierStatusMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
			DossierStatusMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SC = new FinderPath(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
			DossierStatusMappingModelImpl.FINDER_CACHE_ENABLED,
			DossierStatusMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_GID_SC",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SC =
		new FinderPath(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
			DossierStatusMappingModelImpl.FINDER_CACHE_ENABLED,
			DossierStatusMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_GID_SC",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierStatusMappingModelImpl.GROUPID_COLUMN_BITMASK |
			DossierStatusMappingModelImpl.STATUSCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_SC = new FinderPath(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
			DossierStatusMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_SC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossier status mappings where groupId = &#63; and statusCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusCode the status code
	 * @return the matching dossier status mappings
	 */
	@Override
	public List<DossierStatusMapping> findByF_GID_SC(long groupId,
		String statusCode) {
		return findByF_GID_SC(groupId, statusCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier status mappings where groupId = &#63; and statusCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param statusCode the status code
	 * @param start the lower bound of the range of dossier status mappings
	 * @param end the upper bound of the range of dossier status mappings (not inclusive)
	 * @return the range of matching dossier status mappings
	 */
	@Override
	public List<DossierStatusMapping> findByF_GID_SC(long groupId,
		String statusCode, int start, int end) {
		return findByF_GID_SC(groupId, statusCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier status mappings where groupId = &#63; and statusCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param statusCode the status code
	 * @param start the lower bound of the range of dossier status mappings
	 * @param end the upper bound of the range of dossier status mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier status mappings
	 */
	@Override
	public List<DossierStatusMapping> findByF_GID_SC(long groupId,
		String statusCode, int start, int end,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		return findByF_GID_SC(groupId, statusCode, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier status mappings where groupId = &#63; and statusCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param statusCode the status code
	 * @param start the lower bound of the range of dossier status mappings
	 * @param end the upper bound of the range of dossier status mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier status mappings
	 */
	@Override
	public List<DossierStatusMapping> findByF_GID_SC(long groupId,
		String statusCode, int start, int end,
		OrderByComparator<DossierStatusMapping> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SC;
			finderArgs = new Object[] { groupId, statusCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SC;
			finderArgs = new Object[] {
					groupId, statusCode,
					
					start, end, orderByComparator
				};
		}

		List<DossierStatusMapping> list = null;

		if (retrieveFromCache) {
			list = (List<DossierStatusMapping>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierStatusMapping dossierStatusMapping : list) {
					if ((groupId != dossierStatusMapping.getGroupId()) ||
							!Objects.equals(statusCode,
								dossierStatusMapping.getStatusCode())) {
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

			query.append(_SQL_SELECT_DOSSIERSTATUSMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SC_GROUPID_2);

			boolean bindStatusCode = false;

			if (statusCode == null) {
				query.append(_FINDER_COLUMN_F_GID_SC_STATUSCODE_1);
			}
			else if (statusCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SC_STATUSCODE_3);
			}
			else {
				bindStatusCode = true;

				query.append(_FINDER_COLUMN_F_GID_SC_STATUSCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierStatusMappingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindStatusCode) {
					qPos.add(statusCode);
				}

				if (!pagination) {
					list = (List<DossierStatusMapping>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierStatusMapping>)QueryUtil.list(q,
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
	 * Returns the first dossier status mapping in the ordered set where groupId = &#63; and statusCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusCode the status code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier status mapping
	 * @throws NoSuchDossierStatusMappingException if a matching dossier status mapping could not be found
	 */
	@Override
	public DossierStatusMapping findByF_GID_SC_First(long groupId,
		String statusCode,
		OrderByComparator<DossierStatusMapping> orderByComparator)
		throws NoSuchDossierStatusMappingException {
		DossierStatusMapping dossierStatusMapping = fetchByF_GID_SC_First(groupId,
				statusCode, orderByComparator);

		if (dossierStatusMapping != null) {
			return dossierStatusMapping;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", statusCode=");
		msg.append(statusCode);

		msg.append("}");

		throw new NoSuchDossierStatusMappingException(msg.toString());
	}

	/**
	 * Returns the first dossier status mapping in the ordered set where groupId = &#63; and statusCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusCode the status code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier status mapping, or <code>null</code> if a matching dossier status mapping could not be found
	 */
	@Override
	public DossierStatusMapping fetchByF_GID_SC_First(long groupId,
		String statusCode,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		List<DossierStatusMapping> list = findByF_GID_SC(groupId, statusCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier status mapping in the ordered set where groupId = &#63; and statusCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusCode the status code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier status mapping
	 * @throws NoSuchDossierStatusMappingException if a matching dossier status mapping could not be found
	 */
	@Override
	public DossierStatusMapping findByF_GID_SC_Last(long groupId,
		String statusCode,
		OrderByComparator<DossierStatusMapping> orderByComparator)
		throws NoSuchDossierStatusMappingException {
		DossierStatusMapping dossierStatusMapping = fetchByF_GID_SC_Last(groupId,
				statusCode, orderByComparator);

		if (dossierStatusMapping != null) {
			return dossierStatusMapping;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", statusCode=");
		msg.append(statusCode);

		msg.append("}");

		throw new NoSuchDossierStatusMappingException(msg.toString());
	}

	/**
	 * Returns the last dossier status mapping in the ordered set where groupId = &#63; and statusCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusCode the status code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier status mapping, or <code>null</code> if a matching dossier status mapping could not be found
	 */
	@Override
	public DossierStatusMapping fetchByF_GID_SC_Last(long groupId,
		String statusCode,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		int count = countByF_GID_SC(groupId, statusCode);

		if (count == 0) {
			return null;
		}

		List<DossierStatusMapping> list = findByF_GID_SC(groupId, statusCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier status mappings before and after the current dossier status mapping in the ordered set where groupId = &#63; and statusCode = &#63;.
	 *
	 * @param dossierStatusMappingId the primary key of the current dossier status mapping
	 * @param groupId the group ID
	 * @param statusCode the status code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier status mapping
	 * @throws NoSuchDossierStatusMappingException if a dossier status mapping with the primary key could not be found
	 */
	@Override
	public DossierStatusMapping[] findByF_GID_SC_PrevAndNext(
		long dossierStatusMappingId, long groupId, String statusCode,
		OrderByComparator<DossierStatusMapping> orderByComparator)
		throws NoSuchDossierStatusMappingException {
		DossierStatusMapping dossierStatusMapping = findByPrimaryKey(dossierStatusMappingId);

		Session session = null;

		try {
			session = openSession();

			DossierStatusMapping[] array = new DossierStatusMappingImpl[3];

			array[0] = getByF_GID_SC_PrevAndNext(session, dossierStatusMapping,
					groupId, statusCode, orderByComparator, true);

			array[1] = dossierStatusMapping;

			array[2] = getByF_GID_SC_PrevAndNext(session, dossierStatusMapping,
					groupId, statusCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierStatusMapping getByF_GID_SC_PrevAndNext(Session session,
		DossierStatusMapping dossierStatusMapping, long groupId,
		String statusCode,
		OrderByComparator<DossierStatusMapping> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERSTATUSMAPPING_WHERE);

		query.append(_FINDER_COLUMN_F_GID_SC_GROUPID_2);

		boolean bindStatusCode = false;

		if (statusCode == null) {
			query.append(_FINDER_COLUMN_F_GID_SC_STATUSCODE_1);
		}
		else if (statusCode.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_SC_STATUSCODE_3);
		}
		else {
			bindStatusCode = true;

			query.append(_FINDER_COLUMN_F_GID_SC_STATUSCODE_2);
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
			query.append(DossierStatusMappingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindStatusCode) {
			qPos.add(statusCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierStatusMapping);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierStatusMapping> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier status mappings where groupId = &#63; and statusCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param statusCode the status code
	 */
	@Override
	public void removeByF_GID_SC(long groupId, String statusCode) {
		for (DossierStatusMapping dossierStatusMapping : findByF_GID_SC(
				groupId, statusCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierStatusMapping);
		}
	}

	/**
	 * Returns the number of dossier status mappings where groupId = &#63; and statusCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusCode the status code
	 * @return the number of matching dossier status mappings
	 */
	@Override
	public int countByF_GID_SC(long groupId, String statusCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_SC;

		Object[] finderArgs = new Object[] { groupId, statusCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERSTATUSMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SC_GROUPID_2);

			boolean bindStatusCode = false;

			if (statusCode == null) {
				query.append(_FINDER_COLUMN_F_GID_SC_STATUSCODE_1);
			}
			else if (statusCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SC_STATUSCODE_3);
			}
			else {
				bindStatusCode = true;

				query.append(_FINDER_COLUMN_F_GID_SC_STATUSCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindStatusCode) {
					qPos.add(statusCode);
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

	private static final String _FINDER_COLUMN_F_GID_SC_GROUPID_2 = "dossierStatusMapping.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SC_STATUSCODE_1 = "dossierStatusMapping.statusCode IS NULL";
	private static final String _FINDER_COLUMN_F_GID_SC_STATUSCODE_2 = "dossierStatusMapping.statusCode = ?";
	private static final String _FINDER_COLUMN_F_GID_SC_STATUSCODE_3 = "(dossierStatusMapping.statusCode IS NULL OR dossierStatusMapping.statusCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SCDVCQG =
		new FinderPath(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
			DossierStatusMappingModelImpl.FINDER_CACHE_ENABLED,
			DossierStatusMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_GID_SCDVCQG",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SCDVCQG =
		new FinderPath(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
			DossierStatusMappingModelImpl.FINDER_CACHE_ENABLED,
			DossierStatusMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_GID_SCDVCQG",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierStatusMappingModelImpl.GROUPID_COLUMN_BITMASK |
			DossierStatusMappingModelImpl.STATUSCODEDVCQG_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_SCDVCQG = new FinderPath(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
			DossierStatusMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_SCDVCQG",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossier status mappings where groupId = &#63; and statusCodeDVCQG = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusCodeDVCQG the status code dvcqg
	 * @return the matching dossier status mappings
	 */
	@Override
	public List<DossierStatusMapping> findByF_GID_SCDVCQG(long groupId,
		String statusCodeDVCQG) {
		return findByF_GID_SCDVCQG(groupId, statusCodeDVCQG, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier status mappings where groupId = &#63; and statusCodeDVCQG = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param statusCodeDVCQG the status code dvcqg
	 * @param start the lower bound of the range of dossier status mappings
	 * @param end the upper bound of the range of dossier status mappings (not inclusive)
	 * @return the range of matching dossier status mappings
	 */
	@Override
	public List<DossierStatusMapping> findByF_GID_SCDVCQG(long groupId,
		String statusCodeDVCQG, int start, int end) {
		return findByF_GID_SCDVCQG(groupId, statusCodeDVCQG, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier status mappings where groupId = &#63; and statusCodeDVCQG = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param statusCodeDVCQG the status code dvcqg
	 * @param start the lower bound of the range of dossier status mappings
	 * @param end the upper bound of the range of dossier status mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier status mappings
	 */
	@Override
	public List<DossierStatusMapping> findByF_GID_SCDVCQG(long groupId,
		String statusCodeDVCQG, int start, int end,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		return findByF_GID_SCDVCQG(groupId, statusCodeDVCQG, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier status mappings where groupId = &#63; and statusCodeDVCQG = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param statusCodeDVCQG the status code dvcqg
	 * @param start the lower bound of the range of dossier status mappings
	 * @param end the upper bound of the range of dossier status mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier status mappings
	 */
	@Override
	public List<DossierStatusMapping> findByF_GID_SCDVCQG(long groupId,
		String statusCodeDVCQG, int start, int end,
		OrderByComparator<DossierStatusMapping> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SCDVCQG;
			finderArgs = new Object[] { groupId, statusCodeDVCQG };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SCDVCQG;
			finderArgs = new Object[] {
					groupId, statusCodeDVCQG,
					
					start, end, orderByComparator
				};
		}

		List<DossierStatusMapping> list = null;

		if (retrieveFromCache) {
			list = (List<DossierStatusMapping>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierStatusMapping dossierStatusMapping : list) {
					if ((groupId != dossierStatusMapping.getGroupId()) ||
							!Objects.equals(statusCodeDVCQG,
								dossierStatusMapping.getStatusCodeDVCQG())) {
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

			query.append(_SQL_SELECT_DOSSIERSTATUSMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SCDVCQG_GROUPID_2);

			boolean bindStatusCodeDVCQG = false;

			if (statusCodeDVCQG == null) {
				query.append(_FINDER_COLUMN_F_GID_SCDVCQG_STATUSCODEDVCQG_1);
			}
			else if (statusCodeDVCQG.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SCDVCQG_STATUSCODEDVCQG_3);
			}
			else {
				bindStatusCodeDVCQG = true;

				query.append(_FINDER_COLUMN_F_GID_SCDVCQG_STATUSCODEDVCQG_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierStatusMappingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindStatusCodeDVCQG) {
					qPos.add(statusCodeDVCQG);
				}

				if (!pagination) {
					list = (List<DossierStatusMapping>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierStatusMapping>)QueryUtil.list(q,
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
	 * Returns the first dossier status mapping in the ordered set where groupId = &#63; and statusCodeDVCQG = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusCodeDVCQG the status code dvcqg
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier status mapping
	 * @throws NoSuchDossierStatusMappingException if a matching dossier status mapping could not be found
	 */
	@Override
	public DossierStatusMapping findByF_GID_SCDVCQG_First(long groupId,
		String statusCodeDVCQG,
		OrderByComparator<DossierStatusMapping> orderByComparator)
		throws NoSuchDossierStatusMappingException {
		DossierStatusMapping dossierStatusMapping = fetchByF_GID_SCDVCQG_First(groupId,
				statusCodeDVCQG, orderByComparator);

		if (dossierStatusMapping != null) {
			return dossierStatusMapping;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", statusCodeDVCQG=");
		msg.append(statusCodeDVCQG);

		msg.append("}");

		throw new NoSuchDossierStatusMappingException(msg.toString());
	}

	/**
	 * Returns the first dossier status mapping in the ordered set where groupId = &#63; and statusCodeDVCQG = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusCodeDVCQG the status code dvcqg
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier status mapping, or <code>null</code> if a matching dossier status mapping could not be found
	 */
	@Override
	public DossierStatusMapping fetchByF_GID_SCDVCQG_First(long groupId,
		String statusCodeDVCQG,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		List<DossierStatusMapping> list = findByF_GID_SCDVCQG(groupId,
				statusCodeDVCQG, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier status mapping in the ordered set where groupId = &#63; and statusCodeDVCQG = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusCodeDVCQG the status code dvcqg
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier status mapping
	 * @throws NoSuchDossierStatusMappingException if a matching dossier status mapping could not be found
	 */
	@Override
	public DossierStatusMapping findByF_GID_SCDVCQG_Last(long groupId,
		String statusCodeDVCQG,
		OrderByComparator<DossierStatusMapping> orderByComparator)
		throws NoSuchDossierStatusMappingException {
		DossierStatusMapping dossierStatusMapping = fetchByF_GID_SCDVCQG_Last(groupId,
				statusCodeDVCQG, orderByComparator);

		if (dossierStatusMapping != null) {
			return dossierStatusMapping;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", statusCodeDVCQG=");
		msg.append(statusCodeDVCQG);

		msg.append("}");

		throw new NoSuchDossierStatusMappingException(msg.toString());
	}

	/**
	 * Returns the last dossier status mapping in the ordered set where groupId = &#63; and statusCodeDVCQG = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusCodeDVCQG the status code dvcqg
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier status mapping, or <code>null</code> if a matching dossier status mapping could not be found
	 */
	@Override
	public DossierStatusMapping fetchByF_GID_SCDVCQG_Last(long groupId,
		String statusCodeDVCQG,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		int count = countByF_GID_SCDVCQG(groupId, statusCodeDVCQG);

		if (count == 0) {
			return null;
		}

		List<DossierStatusMapping> list = findByF_GID_SCDVCQG(groupId,
				statusCodeDVCQG, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier status mappings before and after the current dossier status mapping in the ordered set where groupId = &#63; and statusCodeDVCQG = &#63;.
	 *
	 * @param dossierStatusMappingId the primary key of the current dossier status mapping
	 * @param groupId the group ID
	 * @param statusCodeDVCQG the status code dvcqg
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier status mapping
	 * @throws NoSuchDossierStatusMappingException if a dossier status mapping with the primary key could not be found
	 */
	@Override
	public DossierStatusMapping[] findByF_GID_SCDVCQG_PrevAndNext(
		long dossierStatusMappingId, long groupId, String statusCodeDVCQG,
		OrderByComparator<DossierStatusMapping> orderByComparator)
		throws NoSuchDossierStatusMappingException {
		DossierStatusMapping dossierStatusMapping = findByPrimaryKey(dossierStatusMappingId);

		Session session = null;

		try {
			session = openSession();

			DossierStatusMapping[] array = new DossierStatusMappingImpl[3];

			array[0] = getByF_GID_SCDVCQG_PrevAndNext(session,
					dossierStatusMapping, groupId, statusCodeDVCQG,
					orderByComparator, true);

			array[1] = dossierStatusMapping;

			array[2] = getByF_GID_SCDVCQG_PrevAndNext(session,
					dossierStatusMapping, groupId, statusCodeDVCQG,
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

	protected DossierStatusMapping getByF_GID_SCDVCQG_PrevAndNext(
		Session session, DossierStatusMapping dossierStatusMapping,
		long groupId, String statusCodeDVCQG,
		OrderByComparator<DossierStatusMapping> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERSTATUSMAPPING_WHERE);

		query.append(_FINDER_COLUMN_F_GID_SCDVCQG_GROUPID_2);

		boolean bindStatusCodeDVCQG = false;

		if (statusCodeDVCQG == null) {
			query.append(_FINDER_COLUMN_F_GID_SCDVCQG_STATUSCODEDVCQG_1);
		}
		else if (statusCodeDVCQG.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_SCDVCQG_STATUSCODEDVCQG_3);
		}
		else {
			bindStatusCodeDVCQG = true;

			query.append(_FINDER_COLUMN_F_GID_SCDVCQG_STATUSCODEDVCQG_2);
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
			query.append(DossierStatusMappingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindStatusCodeDVCQG) {
			qPos.add(statusCodeDVCQG);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierStatusMapping);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierStatusMapping> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier status mappings where groupId = &#63; and statusCodeDVCQG = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param statusCodeDVCQG the status code dvcqg
	 */
	@Override
	public void removeByF_GID_SCDVCQG(long groupId, String statusCodeDVCQG) {
		for (DossierStatusMapping dossierStatusMapping : findByF_GID_SCDVCQG(
				groupId, statusCodeDVCQG, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(dossierStatusMapping);
		}
	}

	/**
	 * Returns the number of dossier status mappings where groupId = &#63; and statusCodeDVCQG = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusCodeDVCQG the status code dvcqg
	 * @return the number of matching dossier status mappings
	 */
	@Override
	public int countByF_GID_SCDVCQG(long groupId, String statusCodeDVCQG) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_SCDVCQG;

		Object[] finderArgs = new Object[] { groupId, statusCodeDVCQG };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERSTATUSMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SCDVCQG_GROUPID_2);

			boolean bindStatusCodeDVCQG = false;

			if (statusCodeDVCQG == null) {
				query.append(_FINDER_COLUMN_F_GID_SCDVCQG_STATUSCODEDVCQG_1);
			}
			else if (statusCodeDVCQG.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SCDVCQG_STATUSCODEDVCQG_3);
			}
			else {
				bindStatusCodeDVCQG = true;

				query.append(_FINDER_COLUMN_F_GID_SCDVCQG_STATUSCODEDVCQG_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindStatusCodeDVCQG) {
					qPos.add(statusCodeDVCQG);
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

	private static final String _FINDER_COLUMN_F_GID_SCDVCQG_GROUPID_2 = "dossierStatusMapping.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SCDVCQG_STATUSCODEDVCQG_1 = "dossierStatusMapping.statusCodeDVCQG IS NULL";
	private static final String _FINDER_COLUMN_F_GID_SCDVCQG_STATUSCODEDVCQG_2 = "dossierStatusMapping.statusCodeDVCQG = ?";
	private static final String _FINDER_COLUMN_F_GID_SCDVCQG_STATUSCODEDVCQG_3 = "(dossierStatusMapping.statusCodeDVCQG IS NULL OR dossierStatusMapping.statusCodeDVCQG = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_SUBSC = new FinderPath(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
			DossierStatusMappingModelImpl.FINDER_CACHE_ENABLED,
			DossierStatusMappingImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_GID_SUBSC",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierStatusMappingModelImpl.GROUPID_COLUMN_BITMASK |
			DossierStatusMappingModelImpl.SUBSTATUSCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_SUBSC = new FinderPath(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
			DossierStatusMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_SUBSC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the dossier status mapping where groupId = &#63; and subStatusCode = &#63; or throws a {@link NoSuchDossierStatusMappingException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param subStatusCode the sub status code
	 * @return the matching dossier status mapping
	 * @throws NoSuchDossierStatusMappingException if a matching dossier status mapping could not be found
	 */
	@Override
	public DossierStatusMapping findByF_GID_SUBSC(long groupId,
		String subStatusCode) throws NoSuchDossierStatusMappingException {
		DossierStatusMapping dossierStatusMapping = fetchByF_GID_SUBSC(groupId,
				subStatusCode);

		if (dossierStatusMapping == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", subStatusCode=");
			msg.append(subStatusCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierStatusMappingException(msg.toString());
		}

		return dossierStatusMapping;
	}

	/**
	 * Returns the dossier status mapping where groupId = &#63; and subStatusCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param subStatusCode the sub status code
	 * @return the matching dossier status mapping, or <code>null</code> if a matching dossier status mapping could not be found
	 */
	@Override
	public DossierStatusMapping fetchByF_GID_SUBSC(long groupId,
		String subStatusCode) {
		return fetchByF_GID_SUBSC(groupId, subStatusCode, true);
	}

	/**
	 * Returns the dossier status mapping where groupId = &#63; and subStatusCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param subStatusCode the sub status code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier status mapping, or <code>null</code> if a matching dossier status mapping could not be found
	 */
	@Override
	public DossierStatusMapping fetchByF_GID_SUBSC(long groupId,
		String subStatusCode, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, subStatusCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_SUBSC,
					finderArgs, this);
		}

		if (result instanceof DossierStatusMapping) {
			DossierStatusMapping dossierStatusMapping = (DossierStatusMapping)result;

			if ((groupId != dossierStatusMapping.getGroupId()) ||
					!Objects.equals(subStatusCode,
						dossierStatusMapping.getSubStatusCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERSTATUSMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SUBSC_GROUPID_2);

			boolean bindSubStatusCode = false;

			if (subStatusCode == null) {
				query.append(_FINDER_COLUMN_F_GID_SUBSC_SUBSTATUSCODE_1);
			}
			else if (subStatusCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SUBSC_SUBSTATUSCODE_3);
			}
			else {
				bindSubStatusCode = true;

				query.append(_FINDER_COLUMN_F_GID_SUBSC_SUBSTATUSCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindSubStatusCode) {
					qPos.add(subStatusCode);
				}

				List<DossierStatusMapping> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_SUBSC,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierStatusMappingPersistenceImpl.fetchByF_GID_SUBSC(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierStatusMapping dossierStatusMapping = list.get(0);

					result = dossierStatusMapping;

					cacheResult(dossierStatusMapping);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_SUBSC,
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
			return (DossierStatusMapping)result;
		}
	}

	/**
	 * Removes the dossier status mapping where groupId = &#63; and subStatusCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param subStatusCode the sub status code
	 * @return the dossier status mapping that was removed
	 */
	@Override
	public DossierStatusMapping removeByF_GID_SUBSC(long groupId,
		String subStatusCode) throws NoSuchDossierStatusMappingException {
		DossierStatusMapping dossierStatusMapping = findByF_GID_SUBSC(groupId,
				subStatusCode);

		return remove(dossierStatusMapping);
	}

	/**
	 * Returns the number of dossier status mappings where groupId = &#63; and subStatusCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param subStatusCode the sub status code
	 * @return the number of matching dossier status mappings
	 */
	@Override
	public int countByF_GID_SUBSC(long groupId, String subStatusCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_SUBSC;

		Object[] finderArgs = new Object[] { groupId, subStatusCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERSTATUSMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SUBSC_GROUPID_2);

			boolean bindSubStatusCode = false;

			if (subStatusCode == null) {
				query.append(_FINDER_COLUMN_F_GID_SUBSC_SUBSTATUSCODE_1);
			}
			else if (subStatusCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SUBSC_SUBSTATUSCODE_3);
			}
			else {
				bindSubStatusCode = true;

				query.append(_FINDER_COLUMN_F_GID_SUBSC_SUBSTATUSCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindSubStatusCode) {
					qPos.add(subStatusCode);
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

	private static final String _FINDER_COLUMN_F_GID_SUBSC_GROUPID_2 = "dossierStatusMapping.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SUBSC_SUBSTATUSCODE_1 = "dossierStatusMapping.subStatusCode IS NULL";
	private static final String _FINDER_COLUMN_F_GID_SUBSC_SUBSTATUSCODE_2 = "dossierStatusMapping.subStatusCode = ?";
	private static final String _FINDER_COLUMN_F_GID_SUBSC_SUBSTATUSCODE_3 = "(dossierStatusMapping.subStatusCode IS NULL OR dossierStatusMapping.subStatusCode = '')";

	public DossierStatusMappingPersistenceImpl() {
		setModelClass(DossierStatusMapping.class);
	}

	/**
	 * Caches the dossier status mapping in the entity cache if it is enabled.
	 *
	 * @param dossierStatusMapping the dossier status mapping
	 */
	@Override
	public void cacheResult(DossierStatusMapping dossierStatusMapping) {
		entityCache.putResult(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
			DossierStatusMappingImpl.class,
			dossierStatusMapping.getPrimaryKey(), dossierStatusMapping);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_SUBSC,
			new Object[] {
				dossierStatusMapping.getGroupId(),
				dossierStatusMapping.getSubStatusCode()
			}, dossierStatusMapping);

		dossierStatusMapping.resetOriginalValues();
	}

	/**
	 * Caches the dossier status mappings in the entity cache if it is enabled.
	 *
	 * @param dossierStatusMappings the dossier status mappings
	 */
	@Override
	public void cacheResult(List<DossierStatusMapping> dossierStatusMappings) {
		for (DossierStatusMapping dossierStatusMapping : dossierStatusMappings) {
			if (entityCache.getResult(
						DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
						DossierStatusMappingImpl.class,
						dossierStatusMapping.getPrimaryKey()) == null) {
				cacheResult(dossierStatusMapping);
			}
			else {
				dossierStatusMapping.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dossier status mappings.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DossierStatusMappingImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dossier status mapping.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DossierStatusMapping dossierStatusMapping) {
		entityCache.removeResult(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
			DossierStatusMappingImpl.class, dossierStatusMapping.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DossierStatusMappingModelImpl)dossierStatusMapping,
			true);
	}

	@Override
	public void clearCache(List<DossierStatusMapping> dossierStatusMappings) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DossierStatusMapping dossierStatusMapping : dossierStatusMappings) {
			entityCache.removeResult(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
				DossierStatusMappingImpl.class,
				dossierStatusMapping.getPrimaryKey());

			clearUniqueFindersCache((DossierStatusMappingModelImpl)dossierStatusMapping,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		DossierStatusMappingModelImpl dossierStatusMappingModelImpl) {
		Object[] args = new Object[] {
				dossierStatusMappingModelImpl.getGroupId(),
				dossierStatusMappingModelImpl.getSubStatusCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_SUBSC, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_SUBSC, args,
			dossierStatusMappingModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DossierStatusMappingModelImpl dossierStatusMappingModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierStatusMappingModelImpl.getGroupId(),
					dossierStatusMappingModelImpl.getSubStatusCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SUBSC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_SUBSC, args);
		}

		if ((dossierStatusMappingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_SUBSC.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierStatusMappingModelImpl.getOriginalGroupId(),
					dossierStatusMappingModelImpl.getOriginalSubStatusCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SUBSC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_SUBSC, args);
		}
	}

	/**
	 * Creates a new dossier status mapping with the primary key. Does not add the dossier status mapping to the database.
	 *
	 * @param dossierStatusMappingId the primary key for the new dossier status mapping
	 * @return the new dossier status mapping
	 */
	@Override
	public DossierStatusMapping create(long dossierStatusMappingId) {
		DossierStatusMapping dossierStatusMapping = new DossierStatusMappingImpl();

		dossierStatusMapping.setNew(true);
		dossierStatusMapping.setPrimaryKey(dossierStatusMappingId);

		dossierStatusMapping.setCompanyId(companyProvider.getCompanyId());

		return dossierStatusMapping;
	}

	/**
	 * Removes the dossier status mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dossierStatusMappingId the primary key of the dossier status mapping
	 * @return the dossier status mapping that was removed
	 * @throws NoSuchDossierStatusMappingException if a dossier status mapping with the primary key could not be found
	 */
	@Override
	public DossierStatusMapping remove(long dossierStatusMappingId)
		throws NoSuchDossierStatusMappingException {
		return remove((Serializable)dossierStatusMappingId);
	}

	/**
	 * Removes the dossier status mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dossier status mapping
	 * @return the dossier status mapping that was removed
	 * @throws NoSuchDossierStatusMappingException if a dossier status mapping with the primary key could not be found
	 */
	@Override
	public DossierStatusMapping remove(Serializable primaryKey)
		throws NoSuchDossierStatusMappingException {
		Session session = null;

		try {
			session = openSession();

			DossierStatusMapping dossierStatusMapping = (DossierStatusMapping)session.get(DossierStatusMappingImpl.class,
					primaryKey);

			if (dossierStatusMapping == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDossierStatusMappingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dossierStatusMapping);
		}
		catch (NoSuchDossierStatusMappingException nsee) {
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
	protected DossierStatusMapping removeImpl(
		DossierStatusMapping dossierStatusMapping) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dossierStatusMapping)) {
				dossierStatusMapping = (DossierStatusMapping)session.get(DossierStatusMappingImpl.class,
						dossierStatusMapping.getPrimaryKeyObj());
			}

			if (dossierStatusMapping != null) {
				session.delete(dossierStatusMapping);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dossierStatusMapping != null) {
			clearCache(dossierStatusMapping);
		}

		return dossierStatusMapping;
	}

	@Override
	public DossierStatusMapping updateImpl(
		DossierStatusMapping dossierStatusMapping) {
		boolean isNew = dossierStatusMapping.isNew();

		if (!(dossierStatusMapping instanceof DossierStatusMappingModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dossierStatusMapping.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dossierStatusMapping);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dossierStatusMapping proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DossierStatusMapping implementation " +
				dossierStatusMapping.getClass());
		}

		DossierStatusMappingModelImpl dossierStatusMappingModelImpl = (DossierStatusMappingModelImpl)dossierStatusMapping;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dossierStatusMapping.getCreateDate() == null)) {
			if (serviceContext == null) {
				dossierStatusMapping.setCreateDate(now);
			}
			else {
				dossierStatusMapping.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!dossierStatusMappingModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dossierStatusMapping.setModifiedDate(now);
			}
			else {
				dossierStatusMapping.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dossierStatusMapping.isNew()) {
				session.save(dossierStatusMapping);

				dossierStatusMapping.setNew(false);
			}
			else {
				dossierStatusMapping = (DossierStatusMapping)session.merge(dossierStatusMapping);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DossierStatusMappingModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					dossierStatusMappingModelImpl.getGroupId(),
					dossierStatusMappingModelImpl.getStatusCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SC,
				args);

			args = new Object[] {
					dossierStatusMappingModelImpl.getGroupId(),
					dossierStatusMappingModelImpl.getStatusCodeDVCQG()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SCDVCQG, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SCDVCQG,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dossierStatusMappingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierStatusMappingModelImpl.getOriginalGroupId(),
						dossierStatusMappingModelImpl.getOriginalStatusCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SC,
					args);

				args = new Object[] {
						dossierStatusMappingModelImpl.getGroupId(),
						dossierStatusMappingModelImpl.getStatusCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SC,
					args);
			}

			if ((dossierStatusMappingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SCDVCQG.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierStatusMappingModelImpl.getOriginalGroupId(),
						dossierStatusMappingModelImpl.getOriginalStatusCodeDVCQG()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SCDVCQG,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SCDVCQG,
					args);

				args = new Object[] {
						dossierStatusMappingModelImpl.getGroupId(),
						dossierStatusMappingModelImpl.getStatusCodeDVCQG()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SCDVCQG,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SCDVCQG,
					args);
			}
		}

		entityCache.putResult(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
			DossierStatusMappingImpl.class,
			dossierStatusMapping.getPrimaryKey(), dossierStatusMapping, false);

		clearUniqueFindersCache(dossierStatusMappingModelImpl, false);
		cacheUniqueFindersCache(dossierStatusMappingModelImpl);

		dossierStatusMapping.resetOriginalValues();

		return dossierStatusMapping;
	}

	/**
	 * Returns the dossier status mapping with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier status mapping
	 * @return the dossier status mapping
	 * @throws NoSuchDossierStatusMappingException if a dossier status mapping with the primary key could not be found
	 */
	@Override
	public DossierStatusMapping findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDossierStatusMappingException {
		DossierStatusMapping dossierStatusMapping = fetchByPrimaryKey(primaryKey);

		if (dossierStatusMapping == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDossierStatusMappingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dossierStatusMapping;
	}

	/**
	 * Returns the dossier status mapping with the primary key or throws a {@link NoSuchDossierStatusMappingException} if it could not be found.
	 *
	 * @param dossierStatusMappingId the primary key of the dossier status mapping
	 * @return the dossier status mapping
	 * @throws NoSuchDossierStatusMappingException if a dossier status mapping with the primary key could not be found
	 */
	@Override
	public DossierStatusMapping findByPrimaryKey(long dossierStatusMappingId)
		throws NoSuchDossierStatusMappingException {
		return findByPrimaryKey((Serializable)dossierStatusMappingId);
	}

	/**
	 * Returns the dossier status mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier status mapping
	 * @return the dossier status mapping, or <code>null</code> if a dossier status mapping with the primary key could not be found
	 */
	@Override
	public DossierStatusMapping fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
				DossierStatusMappingImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DossierStatusMapping dossierStatusMapping = (DossierStatusMapping)serializable;

		if (dossierStatusMapping == null) {
			Session session = null;

			try {
				session = openSession();

				dossierStatusMapping = (DossierStatusMapping)session.get(DossierStatusMappingImpl.class,
						primaryKey);

				if (dossierStatusMapping != null) {
					cacheResult(dossierStatusMapping);
				}
				else {
					entityCache.putResult(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
						DossierStatusMappingImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
					DossierStatusMappingImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dossierStatusMapping;
	}

	/**
	 * Returns the dossier status mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dossierStatusMappingId the primary key of the dossier status mapping
	 * @return the dossier status mapping, or <code>null</code> if a dossier status mapping with the primary key could not be found
	 */
	@Override
	public DossierStatusMapping fetchByPrimaryKey(long dossierStatusMappingId) {
		return fetchByPrimaryKey((Serializable)dossierStatusMappingId);
	}

	@Override
	public Map<Serializable, DossierStatusMapping> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DossierStatusMapping> map = new HashMap<Serializable, DossierStatusMapping>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DossierStatusMapping dossierStatusMapping = fetchByPrimaryKey(primaryKey);

			if (dossierStatusMapping != null) {
				map.put(primaryKey, dossierStatusMapping);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
					DossierStatusMappingImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DossierStatusMapping)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DOSSIERSTATUSMAPPING_WHERE_PKS_IN);

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

			for (DossierStatusMapping dossierStatusMapping : (List<DossierStatusMapping>)q.list()) {
				map.put(dossierStatusMapping.getPrimaryKeyObj(),
					dossierStatusMapping);

				cacheResult(dossierStatusMapping);

				uncachedPrimaryKeys.remove(dossierStatusMapping.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DossierStatusMappingModelImpl.ENTITY_CACHE_ENABLED,
					DossierStatusMappingImpl.class, primaryKey, nullModel);
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
	 * Returns all the dossier status mappings.
	 *
	 * @return the dossier status mappings
	 */
	@Override
	public List<DossierStatusMapping> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier status mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier status mappings
	 * @param end the upper bound of the range of dossier status mappings (not inclusive)
	 * @return the range of dossier status mappings
	 */
	@Override
	public List<DossierStatusMapping> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier status mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier status mappings
	 * @param end the upper bound of the range of dossier status mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dossier status mappings
	 */
	@Override
	public List<DossierStatusMapping> findAll(int start, int end,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier status mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier status mappings
	 * @param end the upper bound of the range of dossier status mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dossier status mappings
	 */
	@Override
	public List<DossierStatusMapping> findAll(int start, int end,
		OrderByComparator<DossierStatusMapping> orderByComparator,
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

		List<DossierStatusMapping> list = null;

		if (retrieveFromCache) {
			list = (List<DossierStatusMapping>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOSSIERSTATUSMAPPING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOSSIERSTATUSMAPPING;

				if (pagination) {
					sql = sql.concat(DossierStatusMappingModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DossierStatusMapping>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierStatusMapping>)QueryUtil.list(q,
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
	 * Removes all the dossier status mappings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DossierStatusMapping dossierStatusMapping : findAll()) {
			remove(dossierStatusMapping);
		}
	}

	/**
	 * Returns the number of dossier status mappings.
	 *
	 * @return the number of dossier status mappings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOSSIERSTATUSMAPPING);

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
		return DossierStatusMappingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dossier status mapping persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DossierStatusMappingImpl.class.getName());
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
	private static final String _SQL_SELECT_DOSSIERSTATUSMAPPING = "SELECT dossierStatusMapping FROM DossierStatusMapping dossierStatusMapping";
	private static final String _SQL_SELECT_DOSSIERSTATUSMAPPING_WHERE_PKS_IN = "SELECT dossierStatusMapping FROM DossierStatusMapping dossierStatusMapping WHERE dossierStatusMappingId IN (";
	private static final String _SQL_SELECT_DOSSIERSTATUSMAPPING_WHERE = "SELECT dossierStatusMapping FROM DossierStatusMapping dossierStatusMapping WHERE ";
	private static final String _SQL_COUNT_DOSSIERSTATUSMAPPING = "SELECT COUNT(dossierStatusMapping) FROM DossierStatusMapping dossierStatusMapping";
	private static final String _SQL_COUNT_DOSSIERSTATUSMAPPING_WHERE = "SELECT COUNT(dossierStatusMapping) FROM DossierStatusMapping dossierStatusMapping WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dossierStatusMapping.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DossierStatusMapping exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DossierStatusMapping exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DossierStatusMappingPersistenceImpl.class);
}