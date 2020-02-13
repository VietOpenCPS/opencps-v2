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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.adminconfig.exception.NoSuchReportRoleException;
import org.opencps.adminconfig.model.ReportRole;
import org.opencps.adminconfig.model.impl.ReportRoleImpl;
import org.opencps.adminconfig.model.impl.ReportRoleModelImpl;
import org.opencps.adminconfig.service.persistence.ReportRolePersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the report role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see ReportRolePersistence
 * @see org.opencps.adminconfig.service.persistence.ReportRoleUtil
 * @generated
 */
@ProviderType
public class ReportRolePersistenceImpl extends BasePersistenceImpl<ReportRole>
	implements ReportRolePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ReportRoleUtil} to access the report role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ReportRoleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleModelImpl.FINDER_CACHE_ENABLED, ReportRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleModelImpl.FINDER_CACHE_ENABLED, ReportRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DRID = new FinderPath(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleModelImpl.FINDER_CACHE_ENABLED, ReportRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_DRID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DRID =
		new FinderPath(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleModelImpl.FINDER_CACHE_ENABLED, ReportRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_DRID",
			new String[] { Long.class.getName() },
			ReportRoleModelImpl.DYNAMICREPORTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DRID = new FinderPath(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_DRID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the report roles where dynamicReportId = &#63;.
	 *
	 * @param dynamicReportId the dynamic report ID
	 * @return the matching report roles
	 */
	@Override
	public List<ReportRole> findByF_DRID(long dynamicReportId) {
		return findByF_DRID(dynamicReportId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the report roles where dynamicReportId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicReportId the dynamic report ID
	 * @param start the lower bound of the range of report roles
	 * @param end the upper bound of the range of report roles (not inclusive)
	 * @return the range of matching report roles
	 */
	@Override
	public List<ReportRole> findByF_DRID(long dynamicReportId, int start,
		int end) {
		return findByF_DRID(dynamicReportId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the report roles where dynamicReportId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicReportId the dynamic report ID
	 * @param start the lower bound of the range of report roles
	 * @param end the upper bound of the range of report roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching report roles
	 */
	@Override
	public List<ReportRole> findByF_DRID(long dynamicReportId, int start,
		int end, OrderByComparator<ReportRole> orderByComparator) {
		return findByF_DRID(dynamicReportId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the report roles where dynamicReportId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicReportId the dynamic report ID
	 * @param start the lower bound of the range of report roles
	 * @param end the upper bound of the range of report roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching report roles
	 */
	@Override
	public List<ReportRole> findByF_DRID(long dynamicReportId, int start,
		int end, OrderByComparator<ReportRole> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DRID;
			finderArgs = new Object[] { dynamicReportId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DRID;
			finderArgs = new Object[] {
					dynamicReportId,
					
					start, end, orderByComparator
				};
		}

		List<ReportRole> list = null;

		if (retrieveFromCache) {
			list = (List<ReportRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReportRole reportRole : list) {
					if ((dynamicReportId != reportRole.getDynamicReportId())) {
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

			query.append(_SQL_SELECT_REPORTROLE_WHERE);

			query.append(_FINDER_COLUMN_F_DRID_DYNAMICREPORTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReportRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dynamicReportId);

				if (!pagination) {
					list = (List<ReportRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReportRole>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first report role in the ordered set where dynamicReportId = &#63;.
	 *
	 * @param dynamicReportId the dynamic report ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching report role
	 * @throws NoSuchReportRoleException if a matching report role could not be found
	 */
	@Override
	public ReportRole findByF_DRID_First(long dynamicReportId,
		OrderByComparator<ReportRole> orderByComparator)
		throws NoSuchReportRoleException {
		ReportRole reportRole = fetchByF_DRID_First(dynamicReportId,
				orderByComparator);

		if (reportRole != null) {
			return reportRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dynamicReportId=");
		msg.append(dynamicReportId);

		msg.append("}");

		throw new NoSuchReportRoleException(msg.toString());
	}

	/**
	 * Returns the first report role in the ordered set where dynamicReportId = &#63;.
	 *
	 * @param dynamicReportId the dynamic report ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching report role, or <code>null</code> if a matching report role could not be found
	 */
	@Override
	public ReportRole fetchByF_DRID_First(long dynamicReportId,
		OrderByComparator<ReportRole> orderByComparator) {
		List<ReportRole> list = findByF_DRID(dynamicReportId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last report role in the ordered set where dynamicReportId = &#63;.
	 *
	 * @param dynamicReportId the dynamic report ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching report role
	 * @throws NoSuchReportRoleException if a matching report role could not be found
	 */
	@Override
	public ReportRole findByF_DRID_Last(long dynamicReportId,
		OrderByComparator<ReportRole> orderByComparator)
		throws NoSuchReportRoleException {
		ReportRole reportRole = fetchByF_DRID_Last(dynamicReportId,
				orderByComparator);

		if (reportRole != null) {
			return reportRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dynamicReportId=");
		msg.append(dynamicReportId);

		msg.append("}");

		throw new NoSuchReportRoleException(msg.toString());
	}

	/**
	 * Returns the last report role in the ordered set where dynamicReportId = &#63;.
	 *
	 * @param dynamicReportId the dynamic report ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching report role, or <code>null</code> if a matching report role could not be found
	 */
	@Override
	public ReportRole fetchByF_DRID_Last(long dynamicReportId,
		OrderByComparator<ReportRole> orderByComparator) {
		int count = countByF_DRID(dynamicReportId);

		if (count == 0) {
			return null;
		}

		List<ReportRole> list = findByF_DRID(dynamicReportId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the report roles before and after the current report role in the ordered set where dynamicReportId = &#63;.
	 *
	 * @param reportRoleId the primary key of the current report role
	 * @param dynamicReportId the dynamic report ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next report role
	 * @throws NoSuchReportRoleException if a report role with the primary key could not be found
	 */
	@Override
	public ReportRole[] findByF_DRID_PrevAndNext(long reportRoleId,
		long dynamicReportId, OrderByComparator<ReportRole> orderByComparator)
		throws NoSuchReportRoleException {
		ReportRole reportRole = findByPrimaryKey(reportRoleId);

		Session session = null;

		try {
			session = openSession();

			ReportRole[] array = new ReportRoleImpl[3];

			array[0] = getByF_DRID_PrevAndNext(session, reportRole,
					dynamicReportId, orderByComparator, true);

			array[1] = reportRole;

			array[2] = getByF_DRID_PrevAndNext(session, reportRole,
					dynamicReportId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ReportRole getByF_DRID_PrevAndNext(Session session,
		ReportRole reportRole, long dynamicReportId,
		OrderByComparator<ReportRole> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REPORTROLE_WHERE);

		query.append(_FINDER_COLUMN_F_DRID_DYNAMICREPORTID_2);

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
			query.append(ReportRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dynamicReportId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reportRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ReportRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the report roles where dynamicReportId = &#63; from the database.
	 *
	 * @param dynamicReportId the dynamic report ID
	 */
	@Override
	public void removeByF_DRID(long dynamicReportId) {
		for (ReportRole reportRole : findByF_DRID(dynamicReportId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reportRole);
		}
	}

	/**
	 * Returns the number of report roles where dynamicReportId = &#63;.
	 *
	 * @param dynamicReportId the dynamic report ID
	 * @return the number of matching report roles
	 */
	@Override
	public int countByF_DRID(long dynamicReportId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DRID;

		Object[] finderArgs = new Object[] { dynamicReportId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REPORTROLE_WHERE);

			query.append(_FINDER_COLUMN_F_DRID_DYNAMICREPORTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dynamicReportId);

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

	private static final String _FINDER_COLUMN_F_DRID_DYNAMICREPORTID_2 = "reportRole.dynamicReportId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_RIDS = new FinderPath(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleModelImpl.FINDER_CACHE_ENABLED, ReportRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_RIDS",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_RIDS =
		new FinderPath(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleModelImpl.FINDER_CACHE_ENABLED, ReportRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_RIDS",
			new String[] { Long.class.getName() },
			ReportRoleModelImpl.ROLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_RIDS = new FinderPath(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_RIDS",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_RIDS = new FinderPath(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByF_RIDS",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the report roles where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @return the matching report roles
	 */
	@Override
	public List<ReportRole> findByF_RIDS(long roleId) {
		return findByF_RIDS(roleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the report roles where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleId the role ID
	 * @param start the lower bound of the range of report roles
	 * @param end the upper bound of the range of report roles (not inclusive)
	 * @return the range of matching report roles
	 */
	@Override
	public List<ReportRole> findByF_RIDS(long roleId, int start, int end) {
		return findByF_RIDS(roleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the report roles where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleId the role ID
	 * @param start the lower bound of the range of report roles
	 * @param end the upper bound of the range of report roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching report roles
	 */
	@Override
	public List<ReportRole> findByF_RIDS(long roleId, int start, int end,
		OrderByComparator<ReportRole> orderByComparator) {
		return findByF_RIDS(roleId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the report roles where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleId the role ID
	 * @param start the lower bound of the range of report roles
	 * @param end the upper bound of the range of report roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching report roles
	 */
	@Override
	public List<ReportRole> findByF_RIDS(long roleId, int start, int end,
		OrderByComparator<ReportRole> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_RIDS;
			finderArgs = new Object[] { roleId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_RIDS;
			finderArgs = new Object[] { roleId, start, end, orderByComparator };
		}

		List<ReportRole> list = null;

		if (retrieveFromCache) {
			list = (List<ReportRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReportRole reportRole : list) {
					if ((roleId != reportRole.getRoleId())) {
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

			query.append(_SQL_SELECT_REPORTROLE_WHERE);

			query.append(_FINDER_COLUMN_F_RIDS_ROLEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReportRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(roleId);

				if (!pagination) {
					list = (List<ReportRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReportRole>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first report role in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching report role
	 * @throws NoSuchReportRoleException if a matching report role could not be found
	 */
	@Override
	public ReportRole findByF_RIDS_First(long roleId,
		OrderByComparator<ReportRole> orderByComparator)
		throws NoSuchReportRoleException {
		ReportRole reportRole = fetchByF_RIDS_First(roleId, orderByComparator);

		if (reportRole != null) {
			return reportRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("roleId=");
		msg.append(roleId);

		msg.append("}");

		throw new NoSuchReportRoleException(msg.toString());
	}

	/**
	 * Returns the first report role in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching report role, or <code>null</code> if a matching report role could not be found
	 */
	@Override
	public ReportRole fetchByF_RIDS_First(long roleId,
		OrderByComparator<ReportRole> orderByComparator) {
		List<ReportRole> list = findByF_RIDS(roleId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last report role in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching report role
	 * @throws NoSuchReportRoleException if a matching report role could not be found
	 */
	@Override
	public ReportRole findByF_RIDS_Last(long roleId,
		OrderByComparator<ReportRole> orderByComparator)
		throws NoSuchReportRoleException {
		ReportRole reportRole = fetchByF_RIDS_Last(roleId, orderByComparator);

		if (reportRole != null) {
			return reportRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("roleId=");
		msg.append(roleId);

		msg.append("}");

		throw new NoSuchReportRoleException(msg.toString());
	}

	/**
	 * Returns the last report role in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching report role, or <code>null</code> if a matching report role could not be found
	 */
	@Override
	public ReportRole fetchByF_RIDS_Last(long roleId,
		OrderByComparator<ReportRole> orderByComparator) {
		int count = countByF_RIDS(roleId);

		if (count == 0) {
			return null;
		}

		List<ReportRole> list = findByF_RIDS(roleId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the report roles before and after the current report role in the ordered set where roleId = &#63;.
	 *
	 * @param reportRoleId the primary key of the current report role
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next report role
	 * @throws NoSuchReportRoleException if a report role with the primary key could not be found
	 */
	@Override
	public ReportRole[] findByF_RIDS_PrevAndNext(long reportRoleId,
		long roleId, OrderByComparator<ReportRole> orderByComparator)
		throws NoSuchReportRoleException {
		ReportRole reportRole = findByPrimaryKey(reportRoleId);

		Session session = null;

		try {
			session = openSession();

			ReportRole[] array = new ReportRoleImpl[3];

			array[0] = getByF_RIDS_PrevAndNext(session, reportRole, roleId,
					orderByComparator, true);

			array[1] = reportRole;

			array[2] = getByF_RIDS_PrevAndNext(session, reportRole, roleId,
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

	protected ReportRole getByF_RIDS_PrevAndNext(Session session,
		ReportRole reportRole, long roleId,
		OrderByComparator<ReportRole> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REPORTROLE_WHERE);

		query.append(_FINDER_COLUMN_F_RIDS_ROLEID_2);

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
			query.append(ReportRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(roleId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reportRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ReportRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the report roles where roleId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleIds the role IDs
	 * @return the matching report roles
	 */
	@Override
	public List<ReportRole> findByF_RIDS(long[] roleIds) {
		return findByF_RIDS(roleIds, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the report roles where roleId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleIds the role IDs
	 * @param start the lower bound of the range of report roles
	 * @param end the upper bound of the range of report roles (not inclusive)
	 * @return the range of matching report roles
	 */
	@Override
	public List<ReportRole> findByF_RIDS(long[] roleIds, int start, int end) {
		return findByF_RIDS(roleIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the report roles where roleId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleIds the role IDs
	 * @param start the lower bound of the range of report roles
	 * @param end the upper bound of the range of report roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching report roles
	 */
	@Override
	public List<ReportRole> findByF_RIDS(long[] roleIds, int start, int end,
		OrderByComparator<ReportRole> orderByComparator) {
		return findByF_RIDS(roleIds, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the report roles where roleId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleId the role ID
	 * @param start the lower bound of the range of report roles
	 * @param end the upper bound of the range of report roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching report roles
	 */
	@Override
	public List<ReportRole> findByF_RIDS(long[] roleIds, int start, int end,
		OrderByComparator<ReportRole> orderByComparator,
		boolean retrieveFromCache) {
		if (roleIds == null) {
			roleIds = new long[0];
		}
		else if (roleIds.length > 1) {
			roleIds = ArrayUtil.unique(roleIds);

			Arrays.sort(roleIds);
		}

		if (roleIds.length == 1) {
			return findByF_RIDS(roleIds[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(roleIds) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(roleIds),
					
					start, end, orderByComparator
				};
		}

		List<ReportRole> list = null;

		if (retrieveFromCache) {
			list = (List<ReportRole>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_F_RIDS,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReportRole reportRole : list) {
					if (!ArrayUtil.contains(roleIds, reportRole.getRoleId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_REPORTROLE_WHERE);

			if (roleIds.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_F_RIDS_ROLEID_7);

				query.append(StringUtil.merge(roleIds));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReportRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ReportRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReportRole>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_F_RIDS,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_F_RIDS,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the report roles where roleId = &#63; from the database.
	 *
	 * @param roleId the role ID
	 */
	@Override
	public void removeByF_RIDS(long roleId) {
		for (ReportRole reportRole : findByF_RIDS(roleId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(reportRole);
		}
	}

	/**
	 * Returns the number of report roles where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @return the number of matching report roles
	 */
	@Override
	public int countByF_RIDS(long roleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_RIDS;

		Object[] finderArgs = new Object[] { roleId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REPORTROLE_WHERE);

			query.append(_FINDER_COLUMN_F_RIDS_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(roleId);

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

	/**
	 * Returns the number of report roles where roleId = any &#63;.
	 *
	 * @param roleIds the role IDs
	 * @return the number of matching report roles
	 */
	@Override
	public int countByF_RIDS(long[] roleIds) {
		if (roleIds == null) {
			roleIds = new long[0];
		}
		else if (roleIds.length > 1) {
			roleIds = ArrayUtil.unique(roleIds);

			Arrays.sort(roleIds);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(roleIds) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_RIDS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_REPORTROLE_WHERE);

			if (roleIds.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_F_RIDS_ROLEID_7);

				query.append(StringUtil.merge(roleIds));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_RIDS,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_RIDS,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_F_RIDS_ROLEID_2 = "reportRole.roleId = ?";
	private static final String _FINDER_COLUMN_F_RIDS_ROLEID_7 = "reportRole.roleId IN (";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_DRID_RID = new FinderPath(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleModelImpl.FINDER_CACHE_ENABLED, ReportRoleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_DRID_RID",
			new String[] { Long.class.getName(), Long.class.getName() },
			ReportRoleModelImpl.DYNAMICREPORTID_COLUMN_BITMASK |
			ReportRoleModelImpl.ROLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DRID_RID = new FinderPath(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_DRID_RID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the report role where dynamicReportId = &#63; and roleId = &#63; or throws a {@link NoSuchReportRoleException} if it could not be found.
	 *
	 * @param dynamicReportId the dynamic report ID
	 * @param roleId the role ID
	 * @return the matching report role
	 * @throws NoSuchReportRoleException if a matching report role could not be found
	 */
	@Override
	public ReportRole findByF_DRID_RID(long dynamicReportId, long roleId)
		throws NoSuchReportRoleException {
		ReportRole reportRole = fetchByF_DRID_RID(dynamicReportId, roleId);

		if (reportRole == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dynamicReportId=");
			msg.append(dynamicReportId);

			msg.append(", roleId=");
			msg.append(roleId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchReportRoleException(msg.toString());
		}

		return reportRole;
	}

	/**
	 * Returns the report role where dynamicReportId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dynamicReportId the dynamic report ID
	 * @param roleId the role ID
	 * @return the matching report role, or <code>null</code> if a matching report role could not be found
	 */
	@Override
	public ReportRole fetchByF_DRID_RID(long dynamicReportId, long roleId) {
		return fetchByF_DRID_RID(dynamicReportId, roleId, true);
	}

	/**
	 * Returns the report role where dynamicReportId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dynamicReportId the dynamic report ID
	 * @param roleId the role ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching report role, or <code>null</code> if a matching report role could not be found
	 */
	@Override
	public ReportRole fetchByF_DRID_RID(long dynamicReportId, long roleId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dynamicReportId, roleId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_DRID_RID,
					finderArgs, this);
		}

		if (result instanceof ReportRole) {
			ReportRole reportRole = (ReportRole)result;

			if ((dynamicReportId != reportRole.getDynamicReportId()) ||
					(roleId != reportRole.getRoleId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_REPORTROLE_WHERE);

			query.append(_FINDER_COLUMN_F_DRID_RID_DYNAMICREPORTID_2);

			query.append(_FINDER_COLUMN_F_DRID_RID_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dynamicReportId);

				qPos.add(roleId);

				List<ReportRole> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_DRID_RID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ReportRolePersistenceImpl.fetchByF_DRID_RID(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ReportRole reportRole = list.get(0);

					result = reportRole;

					cacheResult(reportRole);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DRID_RID,
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
			return (ReportRole)result;
		}
	}

	/**
	 * Removes the report role where dynamicReportId = &#63; and roleId = &#63; from the database.
	 *
	 * @param dynamicReportId the dynamic report ID
	 * @param roleId the role ID
	 * @return the report role that was removed
	 */
	@Override
	public ReportRole removeByF_DRID_RID(long dynamicReportId, long roleId)
		throws NoSuchReportRoleException {
		ReportRole reportRole = findByF_DRID_RID(dynamicReportId, roleId);

		return remove(reportRole);
	}

	/**
	 * Returns the number of report roles where dynamicReportId = &#63; and roleId = &#63;.
	 *
	 * @param dynamicReportId the dynamic report ID
	 * @param roleId the role ID
	 * @return the number of matching report roles
	 */
	@Override
	public int countByF_DRID_RID(long dynamicReportId, long roleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DRID_RID;

		Object[] finderArgs = new Object[] { dynamicReportId, roleId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REPORTROLE_WHERE);

			query.append(_FINDER_COLUMN_F_DRID_RID_DYNAMICREPORTID_2);

			query.append(_FINDER_COLUMN_F_DRID_RID_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dynamicReportId);

				qPos.add(roleId);

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

	private static final String _FINDER_COLUMN_F_DRID_RID_DYNAMICREPORTID_2 = "reportRole.dynamicReportId = ? AND ";
	private static final String _FINDER_COLUMN_F_DRID_RID_ROLEID_2 = "reportRole.roleId = ?";

	public ReportRolePersistenceImpl() {
		setModelClass(ReportRole.class);
	}

	/**
	 * Caches the report role in the entity cache if it is enabled.
	 *
	 * @param reportRole the report role
	 */
	@Override
	public void cacheResult(ReportRole reportRole) {
		entityCache.putResult(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleImpl.class, reportRole.getPrimaryKey(), reportRole);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DRID_RID,
			new Object[] { reportRole.getDynamicReportId(), reportRole.getRoleId() },
			reportRole);

		reportRole.resetOriginalValues();
	}

	/**
	 * Caches the report roles in the entity cache if it is enabled.
	 *
	 * @param reportRoles the report roles
	 */
	@Override
	public void cacheResult(List<ReportRole> reportRoles) {
		for (ReportRole reportRole : reportRoles) {
			if (entityCache.getResult(
						ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
						ReportRoleImpl.class, reportRole.getPrimaryKey()) == null) {
				cacheResult(reportRole);
			}
			else {
				reportRole.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all report roles.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReportRoleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the report role.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReportRole reportRole) {
		entityCache.removeResult(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleImpl.class, reportRole.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ReportRoleModelImpl)reportRole, true);
	}

	@Override
	public void clearCache(List<ReportRole> reportRoles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ReportRole reportRole : reportRoles) {
			entityCache.removeResult(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
				ReportRoleImpl.class, reportRole.getPrimaryKey());

			clearUniqueFindersCache((ReportRoleModelImpl)reportRole, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ReportRoleModelImpl reportRoleModelImpl) {
		Object[] args = new Object[] {
				reportRoleModelImpl.getDynamicReportId(),
				reportRoleModelImpl.getRoleId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_DRID_RID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DRID_RID, args,
			reportRoleModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ReportRoleModelImpl reportRoleModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					reportRoleModelImpl.getDynamicReportId(),
					reportRoleModelImpl.getRoleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DRID_RID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DRID_RID, args);
		}

		if ((reportRoleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_DRID_RID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					reportRoleModelImpl.getOriginalDynamicReportId(),
					reportRoleModelImpl.getOriginalRoleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DRID_RID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DRID_RID, args);
		}
	}

	/**
	 * Creates a new report role with the primary key. Does not add the report role to the database.
	 *
	 * @param reportRoleId the primary key for the new report role
	 * @return the new report role
	 */
	@Override
	public ReportRole create(long reportRoleId) {
		ReportRole reportRole = new ReportRoleImpl();

		reportRole.setNew(true);
		reportRole.setPrimaryKey(reportRoleId);

		return reportRole;
	}

	/**
	 * Removes the report role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param reportRoleId the primary key of the report role
	 * @return the report role that was removed
	 * @throws NoSuchReportRoleException if a report role with the primary key could not be found
	 */
	@Override
	public ReportRole remove(long reportRoleId)
		throws NoSuchReportRoleException {
		return remove((Serializable)reportRoleId);
	}

	/**
	 * Removes the report role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the report role
	 * @return the report role that was removed
	 * @throws NoSuchReportRoleException if a report role with the primary key could not be found
	 */
	@Override
	public ReportRole remove(Serializable primaryKey)
		throws NoSuchReportRoleException {
		Session session = null;

		try {
			session = openSession();

			ReportRole reportRole = (ReportRole)session.get(ReportRoleImpl.class,
					primaryKey);

			if (reportRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReportRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(reportRole);
		}
		catch (NoSuchReportRoleException nsee) {
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
	protected ReportRole removeImpl(ReportRole reportRole) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(reportRole)) {
				reportRole = (ReportRole)session.get(ReportRoleImpl.class,
						reportRole.getPrimaryKeyObj());
			}

			if (reportRole != null) {
				session.delete(reportRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (reportRole != null) {
			clearCache(reportRole);
		}

		return reportRole;
	}

	@Override
	public ReportRole updateImpl(ReportRole reportRole) {
		boolean isNew = reportRole.isNew();

		if (!(reportRole instanceof ReportRoleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(reportRole.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(reportRole);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in reportRole proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ReportRole implementation " +
				reportRole.getClass());
		}

		ReportRoleModelImpl reportRoleModelImpl = (ReportRoleModelImpl)reportRole;

		Session session = null;

		try {
			session = openSession();

			if (reportRole.isNew()) {
				session.save(reportRole);

				reportRole.setNew(false);
			}
			else {
				reportRole = (ReportRole)session.merge(reportRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ReportRoleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					reportRoleModelImpl.getDynamicReportId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DRID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DRID,
				args);

			args = new Object[] { reportRoleModelImpl.getRoleId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_RIDS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_RIDS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((reportRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DRID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						reportRoleModelImpl.getOriginalDynamicReportId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DRID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DRID,
					args);

				args = new Object[] { reportRoleModelImpl.getDynamicReportId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DRID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DRID,
					args);
			}

			if ((reportRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_RIDS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						reportRoleModelImpl.getOriginalRoleId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_RIDS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_RIDS,
					args);

				args = new Object[] { reportRoleModelImpl.getRoleId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_RIDS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_RIDS,
					args);
			}
		}

		entityCache.putResult(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
			ReportRoleImpl.class, reportRole.getPrimaryKey(), reportRole, false);

		clearUniqueFindersCache(reportRoleModelImpl, false);
		cacheUniqueFindersCache(reportRoleModelImpl);

		reportRole.resetOriginalValues();

		return reportRole;
	}

	/**
	 * Returns the report role with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the report role
	 * @return the report role
	 * @throws NoSuchReportRoleException if a report role with the primary key could not be found
	 */
	@Override
	public ReportRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReportRoleException {
		ReportRole reportRole = fetchByPrimaryKey(primaryKey);

		if (reportRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReportRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return reportRole;
	}

	/**
	 * Returns the report role with the primary key or throws a {@link NoSuchReportRoleException} if it could not be found.
	 *
	 * @param reportRoleId the primary key of the report role
	 * @return the report role
	 * @throws NoSuchReportRoleException if a report role with the primary key could not be found
	 */
	@Override
	public ReportRole findByPrimaryKey(long reportRoleId)
		throws NoSuchReportRoleException {
		return findByPrimaryKey((Serializable)reportRoleId);
	}

	/**
	 * Returns the report role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the report role
	 * @return the report role, or <code>null</code> if a report role with the primary key could not be found
	 */
	@Override
	public ReportRole fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
				ReportRoleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ReportRole reportRole = (ReportRole)serializable;

		if (reportRole == null) {
			Session session = null;

			try {
				session = openSession();

				reportRole = (ReportRole)session.get(ReportRoleImpl.class,
						primaryKey);

				if (reportRole != null) {
					cacheResult(reportRole);
				}
				else {
					entityCache.putResult(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
						ReportRoleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
					ReportRoleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return reportRole;
	}

	/**
	 * Returns the report role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param reportRoleId the primary key of the report role
	 * @return the report role, or <code>null</code> if a report role with the primary key could not be found
	 */
	@Override
	public ReportRole fetchByPrimaryKey(long reportRoleId) {
		return fetchByPrimaryKey((Serializable)reportRoleId);
	}

	@Override
	public Map<Serializable, ReportRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ReportRole> map = new HashMap<Serializable, ReportRole>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ReportRole reportRole = fetchByPrimaryKey(primaryKey);

			if (reportRole != null) {
				map.put(primaryKey, reportRole);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
					ReportRoleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ReportRole)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_REPORTROLE_WHERE_PKS_IN);

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

			for (ReportRole reportRole : (List<ReportRole>)q.list()) {
				map.put(reportRole.getPrimaryKeyObj(), reportRole);

				cacheResult(reportRole);

				uncachedPrimaryKeys.remove(reportRole.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ReportRoleModelImpl.ENTITY_CACHE_ENABLED,
					ReportRoleImpl.class, primaryKey, nullModel);
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
	 * Returns all the report roles.
	 *
	 * @return the report roles
	 */
	@Override
	public List<ReportRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the report roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of report roles
	 * @param end the upper bound of the range of report roles (not inclusive)
	 * @return the range of report roles
	 */
	@Override
	public List<ReportRole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the report roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of report roles
	 * @param end the upper bound of the range of report roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of report roles
	 */
	@Override
	public List<ReportRole> findAll(int start, int end,
		OrderByComparator<ReportRole> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the report roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of report roles
	 * @param end the upper bound of the range of report roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of report roles
	 */
	@Override
	public List<ReportRole> findAll(int start, int end,
		OrderByComparator<ReportRole> orderByComparator,
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

		List<ReportRole> list = null;

		if (retrieveFromCache) {
			list = (List<ReportRole>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REPORTROLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REPORTROLE;

				if (pagination) {
					sql = sql.concat(ReportRoleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ReportRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReportRole>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the report roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ReportRole reportRole : findAll()) {
			remove(reportRole);
		}
	}

	/**
	 * Returns the number of report roles.
	 *
	 * @return the number of report roles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REPORTROLE);

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
		return ReportRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the report role persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ReportRoleImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_REPORTROLE = "SELECT reportRole FROM ReportRole reportRole";
	private static final String _SQL_SELECT_REPORTROLE_WHERE_PKS_IN = "SELECT reportRole FROM ReportRole reportRole WHERE reportRoleId IN (";
	private static final String _SQL_SELECT_REPORTROLE_WHERE = "SELECT reportRole FROM ReportRole reportRole WHERE ";
	private static final String _SQL_COUNT_REPORTROLE = "SELECT COUNT(reportRole) FROM ReportRole reportRole";
	private static final String _SQL_COUNT_REPORTROLE_WHERE = "SELECT COUNT(reportRole) FROM ReportRole reportRole WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "reportRole.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ReportRole exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ReportRole exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ReportRolePersistenceImpl.class);
}