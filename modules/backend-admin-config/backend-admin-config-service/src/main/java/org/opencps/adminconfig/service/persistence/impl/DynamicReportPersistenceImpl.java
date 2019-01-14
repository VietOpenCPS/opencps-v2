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

package org.opencps.adminconfig.service.persistence.impl;

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

import org.opencps.adminconfig.exception.NoSuchDynamicReportException;
import org.opencps.adminconfig.model.DynamicReport;
import org.opencps.adminconfig.model.impl.DynamicReportImpl;
import org.opencps.adminconfig.model.impl.DynamicReportModelImpl;
import org.opencps.adminconfig.service.persistence.DynamicReportPersistence;

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
 * The persistence implementation for the dynamic report service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see DynamicReportPersistence
 * @see org.opencps.adminconfig.service.persistence.DynamicReportUtil
 * @generated
 */
@ProviderType
public class DynamicReportPersistenceImpl extends BasePersistenceImpl<DynamicReport>
	implements DynamicReportPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DynamicReportUtil} to access the dynamic report persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DynamicReportImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
			DynamicReportModelImpl.FINDER_CACHE_ENABLED,
			DynamicReportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
			DynamicReportModelImpl.FINDER_CACHE_ENABLED,
			DynamicReportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
			DynamicReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GROUPID =
		new FinderPath(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
			DynamicReportModelImpl.FINDER_CACHE_ENABLED,
			DynamicReportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_GroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID =
		new FinderPath(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
			DynamicReportModelImpl.FINDER_CACHE_ENABLED,
			DynamicReportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_GroupId", new String[] { Long.class.getName() },
			DynamicReportModelImpl.GROUPID_COLUMN_BITMASK |
			DynamicReportModelImpl.REPORTCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GROUPID = new FinderPath(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
			DynamicReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dynamic reports where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching dynamic reports
	 */
	@Override
	public List<DynamicReport> findByF_GroupId(long groupId) {
		return findByF_GroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dynamic reports where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dynamic reports
	 * @param end the upper bound of the range of dynamic reports (not inclusive)
	 * @return the range of matching dynamic reports
	 */
	@Override
	public List<DynamicReport> findByF_GroupId(long groupId, int start, int end) {
		return findByF_GroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dynamic reports where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dynamic reports
	 * @param end the upper bound of the range of dynamic reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dynamic reports
	 */
	@Override
	public List<DynamicReport> findByF_GroupId(long groupId, int start,
		int end, OrderByComparator<DynamicReport> orderByComparator) {
		return findByF_GroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dynamic reports where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dynamic reports
	 * @param end the upper bound of the range of dynamic reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dynamic reports
	 */
	@Override
	public List<DynamicReport> findByF_GroupId(long groupId, int start,
		int end, OrderByComparator<DynamicReport> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<DynamicReport> list = null;

		if (retrieveFromCache) {
			list = (List<DynamicReport>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DynamicReport dynamicReport : list) {
					if ((groupId != dynamicReport.getGroupId())) {
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

			query.append(_SQL_SELECT_DYNAMICREPORT_WHERE);

			query.append(_FINDER_COLUMN_F_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DynamicReportModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<DynamicReport>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DynamicReport>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dynamic report in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dynamic report
	 * @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	 */
	@Override
	public DynamicReport findByF_GroupId_First(long groupId,
		OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException {
		DynamicReport dynamicReport = fetchByF_GroupId_First(groupId,
				orderByComparator);

		if (dynamicReport != null) {
			return dynamicReport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDynamicReportException(msg.toString());
	}

	/**
	 * Returns the first dynamic report in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	 */
	@Override
	public DynamicReport fetchByF_GroupId_First(long groupId,
		OrderByComparator<DynamicReport> orderByComparator) {
		List<DynamicReport> list = findByF_GroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dynamic report in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dynamic report
	 * @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	 */
	@Override
	public DynamicReport findByF_GroupId_Last(long groupId,
		OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException {
		DynamicReport dynamicReport = fetchByF_GroupId_Last(groupId,
				orderByComparator);

		if (dynamicReport != null) {
			return dynamicReport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDynamicReportException(msg.toString());
	}

	/**
	 * Returns the last dynamic report in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	 */
	@Override
	public DynamicReport fetchByF_GroupId_Last(long groupId,
		OrderByComparator<DynamicReport> orderByComparator) {
		int count = countByF_GroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<DynamicReport> list = findByF_GroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dynamic reports before and after the current dynamic report in the ordered set where groupId = &#63;.
	 *
	 * @param dynamicReportId the primary key of the current dynamic report
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dynamic report
	 * @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	 */
	@Override
	public DynamicReport[] findByF_GroupId_PrevAndNext(long dynamicReportId,
		long groupId, OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException {
		DynamicReport dynamicReport = findByPrimaryKey(dynamicReportId);

		Session session = null;

		try {
			session = openSession();

			DynamicReport[] array = new DynamicReportImpl[3];

			array[0] = getByF_GroupId_PrevAndNext(session, dynamicReport,
					groupId, orderByComparator, true);

			array[1] = dynamicReport;

			array[2] = getByF_GroupId_PrevAndNext(session, dynamicReport,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DynamicReport getByF_GroupId_PrevAndNext(Session session,
		DynamicReport dynamicReport, long groupId,
		OrderByComparator<DynamicReport> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DYNAMICREPORT_WHERE);

		query.append(_FINDER_COLUMN_F_GROUPID_GROUPID_2);

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
			query.append(DynamicReportModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dynamicReport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DynamicReport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dynamic reports where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_GroupId(long groupId) {
		for (DynamicReport dynamicReport : findByF_GroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dynamicReport);
		}
	}

	/**
	 * Returns the number of dynamic reports where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching dynamic reports
	 */
	@Override
	public int countByF_GroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DYNAMICREPORT_WHERE);

			query.append(_FINDER_COLUMN_F_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_F_GROUPID_GROUPID_2 = "dynamicReport.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_REPORTCODE = new FinderPath(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
			DynamicReportModelImpl.FINDER_CACHE_ENABLED,
			DynamicReportImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_reportCode",
			new String[] { Long.class.getName(), String.class.getName() },
			DynamicReportModelImpl.GROUPID_COLUMN_BITMASK |
			DynamicReportModelImpl.REPORTCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_REPORTCODE = new FinderPath(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
			DynamicReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_reportCode",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the dynamic report where groupId = &#63; and reportCode = &#63; or throws a {@link NoSuchDynamicReportException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param reportCode the report code
	 * @return the matching dynamic report
	 * @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	 */
	@Override
	public DynamicReport findByF_reportCode(long groupId, String reportCode)
		throws NoSuchDynamicReportException {
		DynamicReport dynamicReport = fetchByF_reportCode(groupId, reportCode);

		if (dynamicReport == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", reportCode=");
			msg.append(reportCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDynamicReportException(msg.toString());
		}

		return dynamicReport;
	}

	/**
	 * Returns the dynamic report where groupId = &#63; and reportCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param reportCode the report code
	 * @return the matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	 */
	@Override
	public DynamicReport fetchByF_reportCode(long groupId, String reportCode) {
		return fetchByF_reportCode(groupId, reportCode, true);
	}

	/**
	 * Returns the dynamic report where groupId = &#63; and reportCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param reportCode the report code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	 */
	@Override
	public DynamicReport fetchByF_reportCode(long groupId, String reportCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, reportCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_REPORTCODE,
					finderArgs, this);
		}

		if (result instanceof DynamicReport) {
			DynamicReport dynamicReport = (DynamicReport)result;

			if ((groupId != dynamicReport.getGroupId()) ||
					!Objects.equals(reportCode, dynamicReport.getReportCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DYNAMICREPORT_WHERE);

			query.append(_FINDER_COLUMN_F_REPORTCODE_GROUPID_2);

			boolean bindReportCode = false;

			if (reportCode == null) {
				query.append(_FINDER_COLUMN_F_REPORTCODE_REPORTCODE_1);
			}
			else if (reportCode.equals("")) {
				query.append(_FINDER_COLUMN_F_REPORTCODE_REPORTCODE_3);
			}
			else {
				bindReportCode = true;

				query.append(_FINDER_COLUMN_F_REPORTCODE_REPORTCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReportCode) {
					qPos.add(reportCode);
				}

				List<DynamicReport> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_REPORTCODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DynamicReportPersistenceImpl.fetchByF_reportCode(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DynamicReport dynamicReport = list.get(0);

					result = dynamicReport;

					cacheResult(dynamicReport);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_REPORTCODE,
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
			return (DynamicReport)result;
		}
	}

	/**
	 * Removes the dynamic report where groupId = &#63; and reportCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param reportCode the report code
	 * @return the dynamic report that was removed
	 */
	@Override
	public DynamicReport removeByF_reportCode(long groupId, String reportCode)
		throws NoSuchDynamicReportException {
		DynamicReport dynamicReport = findByF_reportCode(groupId, reportCode);

		return remove(dynamicReport);
	}

	/**
	 * Returns the number of dynamic reports where groupId = &#63; and reportCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param reportCode the report code
	 * @return the number of matching dynamic reports
	 */
	@Override
	public int countByF_reportCode(long groupId, String reportCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_REPORTCODE;

		Object[] finderArgs = new Object[] { groupId, reportCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DYNAMICREPORT_WHERE);

			query.append(_FINDER_COLUMN_F_REPORTCODE_GROUPID_2);

			boolean bindReportCode = false;

			if (reportCode == null) {
				query.append(_FINDER_COLUMN_F_REPORTCODE_REPORTCODE_1);
			}
			else if (reportCode.equals("")) {
				query.append(_FINDER_COLUMN_F_REPORTCODE_REPORTCODE_3);
			}
			else {
				bindReportCode = true;

				query.append(_FINDER_COLUMN_F_REPORTCODE_REPORTCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReportCode) {
					qPos.add(reportCode);
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

	private static final String _FINDER_COLUMN_F_REPORTCODE_GROUPID_2 = "dynamicReport.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_REPORTCODE_REPORTCODE_1 = "dynamicReport.reportCode IS NULL";
	private static final String _FINDER_COLUMN_F_REPORTCODE_REPORTCODE_2 = "dynamicReport.reportCode = ?";
	private static final String _FINDER_COLUMN_F_REPORTCODE_REPORTCODE_3 = "(dynamicReport.reportCode IS NULL OR dynamicReport.reportCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_REPORTTYPE =
		new FinderPath(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
			DynamicReportModelImpl.FINDER_CACHE_ENABLED,
			DynamicReportImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_reportType",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REPORTTYPE =
		new FinderPath(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
			DynamicReportModelImpl.FINDER_CACHE_ENABLED,
			DynamicReportImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_reportType",
			new String[] { Long.class.getName(), String.class.getName() },
			DynamicReportModelImpl.GROUPID_COLUMN_BITMASK |
			DynamicReportModelImpl.REPORTTYPE_COLUMN_BITMASK |
			DynamicReportModelImpl.REPORTCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_REPORTTYPE = new FinderPath(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
			DynamicReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_reportType",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dynamic reports where groupId = &#63; and reportType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param reportType the report type
	 * @return the matching dynamic reports
	 */
	@Override
	public List<DynamicReport> findByF_reportType(long groupId,
		String reportType) {
		return findByF_reportType(groupId, reportType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dynamic reports where groupId = &#63; and reportType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param reportType the report type
	 * @param start the lower bound of the range of dynamic reports
	 * @param end the upper bound of the range of dynamic reports (not inclusive)
	 * @return the range of matching dynamic reports
	 */
	@Override
	public List<DynamicReport> findByF_reportType(long groupId,
		String reportType, int start, int end) {
		return findByF_reportType(groupId, reportType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dynamic reports where groupId = &#63; and reportType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param reportType the report type
	 * @param start the lower bound of the range of dynamic reports
	 * @param end the upper bound of the range of dynamic reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dynamic reports
	 */
	@Override
	public List<DynamicReport> findByF_reportType(long groupId,
		String reportType, int start, int end,
		OrderByComparator<DynamicReport> orderByComparator) {
		return findByF_reportType(groupId, reportType, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dynamic reports where groupId = &#63; and reportType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param reportType the report type
	 * @param start the lower bound of the range of dynamic reports
	 * @param end the upper bound of the range of dynamic reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dynamic reports
	 */
	@Override
	public List<DynamicReport> findByF_reportType(long groupId,
		String reportType, int start, int end,
		OrderByComparator<DynamicReport> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REPORTTYPE;
			finderArgs = new Object[] { groupId, reportType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_REPORTTYPE;
			finderArgs = new Object[] {
					groupId, reportType,
					
					start, end, orderByComparator
				};
		}

		List<DynamicReport> list = null;

		if (retrieveFromCache) {
			list = (List<DynamicReport>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DynamicReport dynamicReport : list) {
					if ((groupId != dynamicReport.getGroupId()) ||
							!Objects.equals(reportType,
								dynamicReport.getReportType())) {
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

			query.append(_SQL_SELECT_DYNAMICREPORT_WHERE);

			query.append(_FINDER_COLUMN_F_REPORTTYPE_GROUPID_2);

			boolean bindReportType = false;

			if (reportType == null) {
				query.append(_FINDER_COLUMN_F_REPORTTYPE_REPORTTYPE_1);
			}
			else if (reportType.equals("")) {
				query.append(_FINDER_COLUMN_F_REPORTTYPE_REPORTTYPE_3);
			}
			else {
				bindReportType = true;

				query.append(_FINDER_COLUMN_F_REPORTTYPE_REPORTTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DynamicReportModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReportType) {
					qPos.add(reportType);
				}

				if (!pagination) {
					list = (List<DynamicReport>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DynamicReport>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param reportType the report type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dynamic report
	 * @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	 */
	@Override
	public DynamicReport findByF_reportType_First(long groupId,
		String reportType, OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException {
		DynamicReport dynamicReport = fetchByF_reportType_First(groupId,
				reportType, orderByComparator);

		if (dynamicReport != null) {
			return dynamicReport;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", reportType=");
		msg.append(reportType);

		msg.append("}");

		throw new NoSuchDynamicReportException(msg.toString());
	}

	/**
	 * Returns the first dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param reportType the report type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	 */
	@Override
	public DynamicReport fetchByF_reportType_First(long groupId,
		String reportType, OrderByComparator<DynamicReport> orderByComparator) {
		List<DynamicReport> list = findByF_reportType(groupId, reportType, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param reportType the report type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dynamic report
	 * @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	 */
	@Override
	public DynamicReport findByF_reportType_Last(long groupId,
		String reportType, OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException {
		DynamicReport dynamicReport = fetchByF_reportType_Last(groupId,
				reportType, orderByComparator);

		if (dynamicReport != null) {
			return dynamicReport;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", reportType=");
		msg.append(reportType);

		msg.append("}");

		throw new NoSuchDynamicReportException(msg.toString());
	}

	/**
	 * Returns the last dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param reportType the report type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	 */
	@Override
	public DynamicReport fetchByF_reportType_Last(long groupId,
		String reportType, OrderByComparator<DynamicReport> orderByComparator) {
		int count = countByF_reportType(groupId, reportType);

		if (count == 0) {
			return null;
		}

		List<DynamicReport> list = findByF_reportType(groupId, reportType,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dynamic reports before and after the current dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	 *
	 * @param dynamicReportId the primary key of the current dynamic report
	 * @param groupId the group ID
	 * @param reportType the report type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dynamic report
	 * @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	 */
	@Override
	public DynamicReport[] findByF_reportType_PrevAndNext(
		long dynamicReportId, long groupId, String reportType,
		OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException {
		DynamicReport dynamicReport = findByPrimaryKey(dynamicReportId);

		Session session = null;

		try {
			session = openSession();

			DynamicReport[] array = new DynamicReportImpl[3];

			array[0] = getByF_reportType_PrevAndNext(session, dynamicReport,
					groupId, reportType, orderByComparator, true);

			array[1] = dynamicReport;

			array[2] = getByF_reportType_PrevAndNext(session, dynamicReport,
					groupId, reportType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DynamicReport getByF_reportType_PrevAndNext(Session session,
		DynamicReport dynamicReport, long groupId, String reportType,
		OrderByComparator<DynamicReport> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DYNAMICREPORT_WHERE);

		query.append(_FINDER_COLUMN_F_REPORTTYPE_GROUPID_2);

		boolean bindReportType = false;

		if (reportType == null) {
			query.append(_FINDER_COLUMN_F_REPORTTYPE_REPORTTYPE_1);
		}
		else if (reportType.equals("")) {
			query.append(_FINDER_COLUMN_F_REPORTTYPE_REPORTTYPE_3);
		}
		else {
			bindReportType = true;

			query.append(_FINDER_COLUMN_F_REPORTTYPE_REPORTTYPE_2);
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
			query.append(DynamicReportModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindReportType) {
			qPos.add(reportType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dynamicReport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DynamicReport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dynamic reports where groupId = &#63; and reportType = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param reportType the report type
	 */
	@Override
	public void removeByF_reportType(long groupId, String reportType) {
		for (DynamicReport dynamicReport : findByF_reportType(groupId,
				reportType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dynamicReport);
		}
	}

	/**
	 * Returns the number of dynamic reports where groupId = &#63; and reportType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param reportType the report type
	 * @return the number of matching dynamic reports
	 */
	@Override
	public int countByF_reportType(long groupId, String reportType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_REPORTTYPE;

		Object[] finderArgs = new Object[] { groupId, reportType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DYNAMICREPORT_WHERE);

			query.append(_FINDER_COLUMN_F_REPORTTYPE_GROUPID_2);

			boolean bindReportType = false;

			if (reportType == null) {
				query.append(_FINDER_COLUMN_F_REPORTTYPE_REPORTTYPE_1);
			}
			else if (reportType.equals("")) {
				query.append(_FINDER_COLUMN_F_REPORTTYPE_REPORTTYPE_3);
			}
			else {
				bindReportType = true;

				query.append(_FINDER_COLUMN_F_REPORTTYPE_REPORTTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReportType) {
					qPos.add(reportType);
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

	private static final String _FINDER_COLUMN_F_REPORTTYPE_GROUPID_2 = "dynamicReport.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_REPORTTYPE_REPORTTYPE_1 = "dynamicReport.reportType IS NULL";
	private static final String _FINDER_COLUMN_F_REPORTTYPE_REPORTTYPE_2 = "dynamicReport.reportType = ?";
	private static final String _FINDER_COLUMN_F_REPORTTYPE_REPORTTYPE_3 = "(dynamicReport.reportType IS NULL OR dynamicReport.reportType = '')";

	public DynamicReportPersistenceImpl() {
		setModelClass(DynamicReport.class);
	}

	/**
	 * Caches the dynamic report in the entity cache if it is enabled.
	 *
	 * @param dynamicReport the dynamic report
	 */
	@Override
	public void cacheResult(DynamicReport dynamicReport) {
		entityCache.putResult(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
			DynamicReportImpl.class, dynamicReport.getPrimaryKey(),
			dynamicReport);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_REPORTCODE,
			new Object[] {
				dynamicReport.getGroupId(), dynamicReport.getReportCode()
			}, dynamicReport);

		dynamicReport.resetOriginalValues();
	}

	/**
	 * Caches the dynamic reports in the entity cache if it is enabled.
	 *
	 * @param dynamicReports the dynamic reports
	 */
	@Override
	public void cacheResult(List<DynamicReport> dynamicReports) {
		for (DynamicReport dynamicReport : dynamicReports) {
			if (entityCache.getResult(
						DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
						DynamicReportImpl.class, dynamicReport.getPrimaryKey()) == null) {
				cacheResult(dynamicReport);
			}
			else {
				dynamicReport.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dynamic reports.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DynamicReportImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dynamic report.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DynamicReport dynamicReport) {
		entityCache.removeResult(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
			DynamicReportImpl.class, dynamicReport.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DynamicReportModelImpl)dynamicReport, true);
	}

	@Override
	public void clearCache(List<DynamicReport> dynamicReports) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DynamicReport dynamicReport : dynamicReports) {
			entityCache.removeResult(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
				DynamicReportImpl.class, dynamicReport.getPrimaryKey());

			clearUniqueFindersCache((DynamicReportModelImpl)dynamicReport, true);
		}
	}

	protected void cacheUniqueFindersCache(
		DynamicReportModelImpl dynamicReportModelImpl) {
		Object[] args = new Object[] {
				dynamicReportModelImpl.getGroupId(),
				dynamicReportModelImpl.getReportCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_REPORTCODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_REPORTCODE, args,
			dynamicReportModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DynamicReportModelImpl dynamicReportModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dynamicReportModelImpl.getGroupId(),
					dynamicReportModelImpl.getReportCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_REPORTCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_REPORTCODE, args);
		}

		if ((dynamicReportModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_REPORTCODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dynamicReportModelImpl.getOriginalGroupId(),
					dynamicReportModelImpl.getOriginalReportCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_REPORTCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_REPORTCODE, args);
		}
	}

	/**
	 * Creates a new dynamic report with the primary key. Does not add the dynamic report to the database.
	 *
	 * @param dynamicReportId the primary key for the new dynamic report
	 * @return the new dynamic report
	 */
	@Override
	public DynamicReport create(long dynamicReportId) {
		DynamicReport dynamicReport = new DynamicReportImpl();

		dynamicReport.setNew(true);
		dynamicReport.setPrimaryKey(dynamicReportId);

		dynamicReport.setCompanyId(companyProvider.getCompanyId());

		return dynamicReport;
	}

	/**
	 * Removes the dynamic report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dynamicReportId the primary key of the dynamic report
	 * @return the dynamic report that was removed
	 * @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	 */
	@Override
	public DynamicReport remove(long dynamicReportId)
		throws NoSuchDynamicReportException {
		return remove((Serializable)dynamicReportId);
	}

	/**
	 * Removes the dynamic report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dynamic report
	 * @return the dynamic report that was removed
	 * @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	 */
	@Override
	public DynamicReport remove(Serializable primaryKey)
		throws NoSuchDynamicReportException {
		Session session = null;

		try {
			session = openSession();

			DynamicReport dynamicReport = (DynamicReport)session.get(DynamicReportImpl.class,
					primaryKey);

			if (dynamicReport == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDynamicReportException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dynamicReport);
		}
		catch (NoSuchDynamicReportException nsee) {
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
	protected DynamicReport removeImpl(DynamicReport dynamicReport) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dynamicReport)) {
				dynamicReport = (DynamicReport)session.get(DynamicReportImpl.class,
						dynamicReport.getPrimaryKeyObj());
			}

			if (dynamicReport != null) {
				session.delete(dynamicReport);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dynamicReport != null) {
			clearCache(dynamicReport);
		}

		return dynamicReport;
	}

	@Override
	public DynamicReport updateImpl(DynamicReport dynamicReport) {
		boolean isNew = dynamicReport.isNew();

		if (!(dynamicReport instanceof DynamicReportModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dynamicReport.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dynamicReport);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dynamicReport proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DynamicReport implementation " +
				dynamicReport.getClass());
		}

		DynamicReportModelImpl dynamicReportModelImpl = (DynamicReportModelImpl)dynamicReport;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dynamicReport.getCreateDate() == null)) {
			if (serviceContext == null) {
				dynamicReport.setCreateDate(now);
			}
			else {
				dynamicReport.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dynamicReportModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dynamicReport.setModifiedDate(now);
			}
			else {
				dynamicReport.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dynamicReport.isNew()) {
				session.save(dynamicReport);

				dynamicReport.setNew(false);
			}
			else {
				dynamicReport = (DynamicReport)session.merge(dynamicReport);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DynamicReportModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dynamicReportModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID,
				args);

			args = new Object[] {
					dynamicReportModelImpl.getGroupId(),
					dynamicReportModelImpl.getReportType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_REPORTTYPE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REPORTTYPE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dynamicReportModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dynamicReportModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID,
					args);

				args = new Object[] { dynamicReportModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID,
					args);
			}

			if ((dynamicReportModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REPORTTYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dynamicReportModelImpl.getOriginalGroupId(),
						dynamicReportModelImpl.getOriginalReportType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_REPORTTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REPORTTYPE,
					args);

				args = new Object[] {
						dynamicReportModelImpl.getGroupId(),
						dynamicReportModelImpl.getReportType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_REPORTTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REPORTTYPE,
					args);
			}
		}

		entityCache.putResult(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
			DynamicReportImpl.class, dynamicReport.getPrimaryKey(),
			dynamicReport, false);

		clearUniqueFindersCache(dynamicReportModelImpl, false);
		cacheUniqueFindersCache(dynamicReportModelImpl);

		dynamicReport.resetOriginalValues();

		return dynamicReport;
	}

	/**
	 * Returns the dynamic report with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dynamic report
	 * @return the dynamic report
	 * @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	 */
	@Override
	public DynamicReport findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDynamicReportException {
		DynamicReport dynamicReport = fetchByPrimaryKey(primaryKey);

		if (dynamicReport == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDynamicReportException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dynamicReport;
	}

	/**
	 * Returns the dynamic report with the primary key or throws a {@link NoSuchDynamicReportException} if it could not be found.
	 *
	 * @param dynamicReportId the primary key of the dynamic report
	 * @return the dynamic report
	 * @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	 */
	@Override
	public DynamicReport findByPrimaryKey(long dynamicReportId)
		throws NoSuchDynamicReportException {
		return findByPrimaryKey((Serializable)dynamicReportId);
	}

	/**
	 * Returns the dynamic report with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dynamic report
	 * @return the dynamic report, or <code>null</code> if a dynamic report with the primary key could not be found
	 */
	@Override
	public DynamicReport fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
				DynamicReportImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DynamicReport dynamicReport = (DynamicReport)serializable;

		if (dynamicReport == null) {
			Session session = null;

			try {
				session = openSession();

				dynamicReport = (DynamicReport)session.get(DynamicReportImpl.class,
						primaryKey);

				if (dynamicReport != null) {
					cacheResult(dynamicReport);
				}
				else {
					entityCache.putResult(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
						DynamicReportImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
					DynamicReportImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dynamicReport;
	}

	/**
	 * Returns the dynamic report with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dynamicReportId the primary key of the dynamic report
	 * @return the dynamic report, or <code>null</code> if a dynamic report with the primary key could not be found
	 */
	@Override
	public DynamicReport fetchByPrimaryKey(long dynamicReportId) {
		return fetchByPrimaryKey((Serializable)dynamicReportId);
	}

	@Override
	public Map<Serializable, DynamicReport> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DynamicReport> map = new HashMap<Serializable, DynamicReport>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DynamicReport dynamicReport = fetchByPrimaryKey(primaryKey);

			if (dynamicReport != null) {
				map.put(primaryKey, dynamicReport);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
					DynamicReportImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DynamicReport)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DYNAMICREPORT_WHERE_PKS_IN);

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

			for (DynamicReport dynamicReport : (List<DynamicReport>)q.list()) {
				map.put(dynamicReport.getPrimaryKeyObj(), dynamicReport);

				cacheResult(dynamicReport);

				uncachedPrimaryKeys.remove(dynamicReport.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DynamicReportModelImpl.ENTITY_CACHE_ENABLED,
					DynamicReportImpl.class, primaryKey, nullModel);
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
	 * Returns all the dynamic reports.
	 *
	 * @return the dynamic reports
	 */
	@Override
	public List<DynamicReport> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dynamic reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dynamic reports
	 * @param end the upper bound of the range of dynamic reports (not inclusive)
	 * @return the range of dynamic reports
	 */
	@Override
	public List<DynamicReport> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dynamic reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dynamic reports
	 * @param end the upper bound of the range of dynamic reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dynamic reports
	 */
	@Override
	public List<DynamicReport> findAll(int start, int end,
		OrderByComparator<DynamicReport> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dynamic reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dynamic reports
	 * @param end the upper bound of the range of dynamic reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dynamic reports
	 */
	@Override
	public List<DynamicReport> findAll(int start, int end,
		OrderByComparator<DynamicReport> orderByComparator,
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

		List<DynamicReport> list = null;

		if (retrieveFromCache) {
			list = (List<DynamicReport>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DYNAMICREPORT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DYNAMICREPORT;

				if (pagination) {
					sql = sql.concat(DynamicReportModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DynamicReport>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DynamicReport>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the dynamic reports from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DynamicReport dynamicReport : findAll()) {
			remove(dynamicReport);
		}
	}

	/**
	 * Returns the number of dynamic reports.
	 *
	 * @return the number of dynamic reports
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DYNAMICREPORT);

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
		return DynamicReportModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dynamic report persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DynamicReportImpl.class.getName());
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
	private static final String _SQL_SELECT_DYNAMICREPORT = "SELECT dynamicReport FROM DynamicReport dynamicReport";
	private static final String _SQL_SELECT_DYNAMICREPORT_WHERE_PKS_IN = "SELECT dynamicReport FROM DynamicReport dynamicReport WHERE dynamicReportId IN (";
	private static final String _SQL_SELECT_DYNAMICREPORT_WHERE = "SELECT dynamicReport FROM DynamicReport dynamicReport WHERE ";
	private static final String _SQL_COUNT_DYNAMICREPORT = "SELECT COUNT(dynamicReport) FROM DynamicReport dynamicReport";
	private static final String _SQL_COUNT_DYNAMICREPORT_WHERE = "SELECT COUNT(dynamicReport) FROM DynamicReport dynamicReport WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dynamicReport.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DynamicReport exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DynamicReport exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DynamicReportPersistenceImpl.class);
}