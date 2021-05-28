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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchReportLandTaxException;
import org.opencps.dossiermgt.model.ReportLandTax;
import org.opencps.dossiermgt.model.impl.ReportLandTaxImpl;
import org.opencps.dossiermgt.model.impl.ReportLandTaxModelImpl;
import org.opencps.dossiermgt.service.persistence.ReportLandTaxPersistence;

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
 * The persistence implementation for the report land tax service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ReportLandTaxPersistence
 * @see org.opencps.dossiermgt.service.persistence.ReportLandTaxUtil
 * @generated
 */
@ProviderType
public class ReportLandTaxPersistenceImpl extends BasePersistenceImpl<ReportLandTax>
	implements ReportLandTaxPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ReportLandTaxUtil} to access the report land tax persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ReportLandTaxImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
			ReportLandTaxModelImpl.FINDER_CACHE_ENABLED,
			ReportLandTaxImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
			ReportLandTaxModelImpl.FINDER_CACHE_ENABLED,
			ReportLandTaxImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
			ReportLandTaxModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
			ReportLandTaxModelImpl.FINDER_CACHE_ENABLED,
			ReportLandTaxImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
			ReportLandTaxModelImpl.FINDER_CACHE_ENABLED,
			ReportLandTaxImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			ReportLandTaxModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
			ReportLandTaxModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the report land taxs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching report land taxs
	 */
	@Override
	public List<ReportLandTax> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the report land taxs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of report land taxs
	 * @param end the upper bound of the range of report land taxs (not inclusive)
	 * @return the range of matching report land taxs
	 */
	@Override
	public List<ReportLandTax> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the report land taxs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of report land taxs
	 * @param end the upper bound of the range of report land taxs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching report land taxs
	 */
	@Override
	public List<ReportLandTax> findByUuid(String uuid, int start, int end,
		OrderByComparator<ReportLandTax> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the report land taxs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of report land taxs
	 * @param end the upper bound of the range of report land taxs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching report land taxs
	 */
	@Override
	public List<ReportLandTax> findByUuid(String uuid, int start, int end,
		OrderByComparator<ReportLandTax> orderByComparator,
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

		List<ReportLandTax> list = null;

		if (retrieveFromCache) {
			list = (List<ReportLandTax>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReportLandTax reportLandTax : list) {
					if (!Objects.equals(uuid, reportLandTax.getUuid())) {
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

			query.append(_SQL_SELECT_REPORTLANDTAX_WHERE);

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
				query.append(ReportLandTaxModelImpl.ORDER_BY_JPQL);
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
					list = (List<ReportLandTax>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReportLandTax>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first report land tax in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching report land tax
	 * @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	 */
	@Override
	public ReportLandTax findByUuid_First(String uuid,
		OrderByComparator<ReportLandTax> orderByComparator)
		throws NoSuchReportLandTaxException {
		ReportLandTax reportLandTax = fetchByUuid_First(uuid, orderByComparator);

		if (reportLandTax != null) {
			return reportLandTax;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchReportLandTaxException(msg.toString());
	}

	/**
	 * Returns the first report land tax in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching report land tax, or <code>null</code> if a matching report land tax could not be found
	 */
	@Override
	public ReportLandTax fetchByUuid_First(String uuid,
		OrderByComparator<ReportLandTax> orderByComparator) {
		List<ReportLandTax> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last report land tax in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching report land tax
	 * @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	 */
	@Override
	public ReportLandTax findByUuid_Last(String uuid,
		OrderByComparator<ReportLandTax> orderByComparator)
		throws NoSuchReportLandTaxException {
		ReportLandTax reportLandTax = fetchByUuid_Last(uuid, orderByComparator);

		if (reportLandTax != null) {
			return reportLandTax;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchReportLandTaxException(msg.toString());
	}

	/**
	 * Returns the last report land tax in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching report land tax, or <code>null</code> if a matching report land tax could not be found
	 */
	@Override
	public ReportLandTax fetchByUuid_Last(String uuid,
		OrderByComparator<ReportLandTax> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ReportLandTax> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the report land taxs before and after the current report land tax in the ordered set where uuid = &#63;.
	 *
	 * @param reportId the primary key of the current report land tax
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next report land tax
	 * @throws NoSuchReportLandTaxException if a report land tax with the primary key could not be found
	 */
	@Override
	public ReportLandTax[] findByUuid_PrevAndNext(long reportId, String uuid,
		OrderByComparator<ReportLandTax> orderByComparator)
		throws NoSuchReportLandTaxException {
		ReportLandTax reportLandTax = findByPrimaryKey(reportId);

		Session session = null;

		try {
			session = openSession();

			ReportLandTax[] array = new ReportLandTaxImpl[3];

			array[0] = getByUuid_PrevAndNext(session, reportLandTax, uuid,
					orderByComparator, true);

			array[1] = reportLandTax;

			array[2] = getByUuid_PrevAndNext(session, reportLandTax, uuid,
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

	protected ReportLandTax getByUuid_PrevAndNext(Session session,
		ReportLandTax reportLandTax, String uuid,
		OrderByComparator<ReportLandTax> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REPORTLANDTAX_WHERE);

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
			query.append(ReportLandTaxModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(reportLandTax);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ReportLandTax> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the report land taxs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ReportLandTax reportLandTax : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(reportLandTax);
		}
	}

	/**
	 * Returns the number of report land taxs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching report land taxs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REPORTLANDTAX_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "reportLandTax.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "reportLandTax.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(reportLandTax.uuid IS NULL OR reportLandTax.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
			ReportLandTaxModelImpl.FINDER_CACHE_ENABLED,
			ReportLandTaxImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ReportLandTaxModelImpl.UUID_COLUMN_BITMASK |
			ReportLandTaxModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
			ReportLandTaxModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the report land tax where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchReportLandTaxException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching report land tax
	 * @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	 */
	@Override
	public ReportLandTax findByUUID_G(String uuid, long groupId)
		throws NoSuchReportLandTaxException {
		ReportLandTax reportLandTax = fetchByUUID_G(uuid, groupId);

		if (reportLandTax == null) {
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

			throw new NoSuchReportLandTaxException(msg.toString());
		}

		return reportLandTax;
	}

	/**
	 * Returns the report land tax where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching report land tax, or <code>null</code> if a matching report land tax could not be found
	 */
	@Override
	public ReportLandTax fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the report land tax where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching report land tax, or <code>null</code> if a matching report land tax could not be found
	 */
	@Override
	public ReportLandTax fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ReportLandTax) {
			ReportLandTax reportLandTax = (ReportLandTax)result;

			if (!Objects.equals(uuid, reportLandTax.getUuid()) ||
					(groupId != reportLandTax.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_REPORTLANDTAX_WHERE);

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

				List<ReportLandTax> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ReportLandTax reportLandTax = list.get(0);

					result = reportLandTax;

					cacheResult(reportLandTax);
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
			return (ReportLandTax)result;
		}
	}

	/**
	 * Removes the report land tax where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the report land tax that was removed
	 */
	@Override
	public ReportLandTax removeByUUID_G(String uuid, long groupId)
		throws NoSuchReportLandTaxException {
		ReportLandTax reportLandTax = findByUUID_G(uuid, groupId);

		return remove(reportLandTax);
	}

	/**
	 * Returns the number of report land taxs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching report land taxs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REPORTLANDTAX_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "reportLandTax.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "reportLandTax.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(reportLandTax.uuid IS NULL OR reportLandTax.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "reportLandTax.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
			ReportLandTaxModelImpl.FINDER_CACHE_ENABLED,
			ReportLandTaxImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
			ReportLandTaxModelImpl.FINDER_CACHE_ENABLED,
			ReportLandTaxImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ReportLandTaxModelImpl.UUID_COLUMN_BITMASK |
			ReportLandTaxModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
			ReportLandTaxModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the report land taxs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching report land taxs
	 */
	@Override
	public List<ReportLandTax> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the report land taxs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of report land taxs
	 * @param end the upper bound of the range of report land taxs (not inclusive)
	 * @return the range of matching report land taxs
	 */
	@Override
	public List<ReportLandTax> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the report land taxs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of report land taxs
	 * @param end the upper bound of the range of report land taxs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching report land taxs
	 */
	@Override
	public List<ReportLandTax> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ReportLandTax> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the report land taxs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of report land taxs
	 * @param end the upper bound of the range of report land taxs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching report land taxs
	 */
	@Override
	public List<ReportLandTax> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ReportLandTax> orderByComparator,
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

		List<ReportLandTax> list = null;

		if (retrieveFromCache) {
			list = (List<ReportLandTax>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReportLandTax reportLandTax : list) {
					if (!Objects.equals(uuid, reportLandTax.getUuid()) ||
							(companyId != reportLandTax.getCompanyId())) {
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

			query.append(_SQL_SELECT_REPORTLANDTAX_WHERE);

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
				query.append(ReportLandTaxModelImpl.ORDER_BY_JPQL);
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
					list = (List<ReportLandTax>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReportLandTax>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching report land tax
	 * @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	 */
	@Override
	public ReportLandTax findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ReportLandTax> orderByComparator)
		throws NoSuchReportLandTaxException {
		ReportLandTax reportLandTax = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (reportLandTax != null) {
			return reportLandTax;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchReportLandTaxException(msg.toString());
	}

	/**
	 * Returns the first report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching report land tax, or <code>null</code> if a matching report land tax could not be found
	 */
	@Override
	public ReportLandTax fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ReportLandTax> orderByComparator) {
		List<ReportLandTax> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching report land tax
	 * @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	 */
	@Override
	public ReportLandTax findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ReportLandTax> orderByComparator)
		throws NoSuchReportLandTaxException {
		ReportLandTax reportLandTax = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (reportLandTax != null) {
			return reportLandTax;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchReportLandTaxException(msg.toString());
	}

	/**
	 * Returns the last report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching report land tax, or <code>null</code> if a matching report land tax could not be found
	 */
	@Override
	public ReportLandTax fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ReportLandTax> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ReportLandTax> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the report land taxs before and after the current report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param reportId the primary key of the current report land tax
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next report land tax
	 * @throws NoSuchReportLandTaxException if a report land tax with the primary key could not be found
	 */
	@Override
	public ReportLandTax[] findByUuid_C_PrevAndNext(long reportId, String uuid,
		long companyId, OrderByComparator<ReportLandTax> orderByComparator)
		throws NoSuchReportLandTaxException {
		ReportLandTax reportLandTax = findByPrimaryKey(reportId);

		Session session = null;

		try {
			session = openSession();

			ReportLandTax[] array = new ReportLandTaxImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, reportLandTax, uuid,
					companyId, orderByComparator, true);

			array[1] = reportLandTax;

			array[2] = getByUuid_C_PrevAndNext(session, reportLandTax, uuid,
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

	protected ReportLandTax getByUuid_C_PrevAndNext(Session session,
		ReportLandTax reportLandTax, String uuid, long companyId,
		OrderByComparator<ReportLandTax> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REPORTLANDTAX_WHERE);

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
			query.append(ReportLandTaxModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(reportLandTax);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ReportLandTax> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the report land taxs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ReportLandTax reportLandTax : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reportLandTax);
		}
	}

	/**
	 * Returns the number of report land taxs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching report land taxs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REPORTLANDTAX_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "reportLandTax.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "reportLandTax.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(reportLandTax.uuid IS NULL OR reportLandTax.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "reportLandTax.companyId = ?";

	public ReportLandTaxPersistenceImpl() {
		setModelClass(ReportLandTax.class);

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
	 * Caches the report land tax in the entity cache if it is enabled.
	 *
	 * @param reportLandTax the report land tax
	 */
	@Override
	public void cacheResult(ReportLandTax reportLandTax) {
		entityCache.putResult(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
			ReportLandTaxImpl.class, reportLandTax.getPrimaryKey(),
			reportLandTax);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { reportLandTax.getUuid(), reportLandTax.getGroupId() },
			reportLandTax);

		reportLandTax.resetOriginalValues();
	}

	/**
	 * Caches the report land taxs in the entity cache if it is enabled.
	 *
	 * @param reportLandTaxs the report land taxs
	 */
	@Override
	public void cacheResult(List<ReportLandTax> reportLandTaxs) {
		for (ReportLandTax reportLandTax : reportLandTaxs) {
			if (entityCache.getResult(
						ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
						ReportLandTaxImpl.class, reportLandTax.getPrimaryKey()) == null) {
				cacheResult(reportLandTax);
			}
			else {
				reportLandTax.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all report land taxs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReportLandTaxImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the report land tax.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReportLandTax reportLandTax) {
		entityCache.removeResult(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
			ReportLandTaxImpl.class, reportLandTax.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ReportLandTaxModelImpl)reportLandTax, true);
	}

	@Override
	public void clearCache(List<ReportLandTax> reportLandTaxs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ReportLandTax reportLandTax : reportLandTaxs) {
			entityCache.removeResult(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
				ReportLandTaxImpl.class, reportLandTax.getPrimaryKey());

			clearUniqueFindersCache((ReportLandTaxModelImpl)reportLandTax, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ReportLandTaxModelImpl reportLandTaxModelImpl) {
		Object[] args = new Object[] {
				reportLandTaxModelImpl.getUuid(),
				reportLandTaxModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			reportLandTaxModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ReportLandTaxModelImpl reportLandTaxModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					reportLandTaxModelImpl.getUuid(),
					reportLandTaxModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((reportLandTaxModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					reportLandTaxModelImpl.getOriginalUuid(),
					reportLandTaxModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new report land tax with the primary key. Does not add the report land tax to the database.
	 *
	 * @param reportId the primary key for the new report land tax
	 * @return the new report land tax
	 */
	@Override
	public ReportLandTax create(long reportId) {
		ReportLandTax reportLandTax = new ReportLandTaxImpl();

		reportLandTax.setNew(true);
		reportLandTax.setPrimaryKey(reportId);

		String uuid = PortalUUIDUtil.generate();

		reportLandTax.setUuid(uuid);

		reportLandTax.setCompanyId(companyProvider.getCompanyId());

		return reportLandTax;
	}

	/**
	 * Removes the report land tax with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param reportId the primary key of the report land tax
	 * @return the report land tax that was removed
	 * @throws NoSuchReportLandTaxException if a report land tax with the primary key could not be found
	 */
	@Override
	public ReportLandTax remove(long reportId)
		throws NoSuchReportLandTaxException {
		return remove((Serializable)reportId);
	}

	/**
	 * Removes the report land tax with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the report land tax
	 * @return the report land tax that was removed
	 * @throws NoSuchReportLandTaxException if a report land tax with the primary key could not be found
	 */
	@Override
	public ReportLandTax remove(Serializable primaryKey)
		throws NoSuchReportLandTaxException {
		Session session = null;

		try {
			session = openSession();

			ReportLandTax reportLandTax = (ReportLandTax)session.get(ReportLandTaxImpl.class,
					primaryKey);

			if (reportLandTax == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReportLandTaxException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(reportLandTax);
		}
		catch (NoSuchReportLandTaxException nsee) {
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
	protected ReportLandTax removeImpl(ReportLandTax reportLandTax) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(reportLandTax)) {
				reportLandTax = (ReportLandTax)session.get(ReportLandTaxImpl.class,
						reportLandTax.getPrimaryKeyObj());
			}

			if (reportLandTax != null) {
				session.delete(reportLandTax);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (reportLandTax != null) {
			clearCache(reportLandTax);
		}

		return reportLandTax;
	}

	@Override
	public ReportLandTax updateImpl(ReportLandTax reportLandTax) {
		boolean isNew = reportLandTax.isNew();

		if (!(reportLandTax instanceof ReportLandTaxModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(reportLandTax.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(reportLandTax);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in reportLandTax proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ReportLandTax implementation " +
				reportLandTax.getClass());
		}

		ReportLandTaxModelImpl reportLandTaxModelImpl = (ReportLandTaxModelImpl)reportLandTax;

		if (Validator.isNull(reportLandTax.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			reportLandTax.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (reportLandTax.getCreateDate() == null)) {
			if (serviceContext == null) {
				reportLandTax.setCreateDate(now);
			}
			else {
				reportLandTax.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!reportLandTaxModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				reportLandTax.setModifiedDate(now);
			}
			else {
				reportLandTax.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (reportLandTax.isNew()) {
				session.save(reportLandTax);

				reportLandTax.setNew(false);
			}
			else {
				reportLandTax = (ReportLandTax)session.merge(reportLandTax);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ReportLandTaxModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { reportLandTaxModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					reportLandTaxModelImpl.getUuid(),
					reportLandTaxModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((reportLandTaxModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						reportLandTaxModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { reportLandTaxModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((reportLandTaxModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						reportLandTaxModelImpl.getOriginalUuid(),
						reportLandTaxModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						reportLandTaxModelImpl.getUuid(),
						reportLandTaxModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
			ReportLandTaxImpl.class, reportLandTax.getPrimaryKey(),
			reportLandTax, false);

		clearUniqueFindersCache(reportLandTaxModelImpl, false);
		cacheUniqueFindersCache(reportLandTaxModelImpl);

		reportLandTax.resetOriginalValues();

		return reportLandTax;
	}

	/**
	 * Returns the report land tax with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the report land tax
	 * @return the report land tax
	 * @throws NoSuchReportLandTaxException if a report land tax with the primary key could not be found
	 */
	@Override
	public ReportLandTax findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReportLandTaxException {
		ReportLandTax reportLandTax = fetchByPrimaryKey(primaryKey);

		if (reportLandTax == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReportLandTaxException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return reportLandTax;
	}

	/**
	 * Returns the report land tax with the primary key or throws a {@link NoSuchReportLandTaxException} if it could not be found.
	 *
	 * @param reportId the primary key of the report land tax
	 * @return the report land tax
	 * @throws NoSuchReportLandTaxException if a report land tax with the primary key could not be found
	 */
	@Override
	public ReportLandTax findByPrimaryKey(long reportId)
		throws NoSuchReportLandTaxException {
		return findByPrimaryKey((Serializable)reportId);
	}

	/**
	 * Returns the report land tax with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the report land tax
	 * @return the report land tax, or <code>null</code> if a report land tax with the primary key could not be found
	 */
	@Override
	public ReportLandTax fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
				ReportLandTaxImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ReportLandTax reportLandTax = (ReportLandTax)serializable;

		if (reportLandTax == null) {
			Session session = null;

			try {
				session = openSession();

				reportLandTax = (ReportLandTax)session.get(ReportLandTaxImpl.class,
						primaryKey);

				if (reportLandTax != null) {
					cacheResult(reportLandTax);
				}
				else {
					entityCache.putResult(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
						ReportLandTaxImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
					ReportLandTaxImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return reportLandTax;
	}

	/**
	 * Returns the report land tax with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param reportId the primary key of the report land tax
	 * @return the report land tax, or <code>null</code> if a report land tax with the primary key could not be found
	 */
	@Override
	public ReportLandTax fetchByPrimaryKey(long reportId) {
		return fetchByPrimaryKey((Serializable)reportId);
	}

	@Override
	public Map<Serializable, ReportLandTax> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ReportLandTax> map = new HashMap<Serializable, ReportLandTax>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ReportLandTax reportLandTax = fetchByPrimaryKey(primaryKey);

			if (reportLandTax != null) {
				map.put(primaryKey, reportLandTax);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
					ReportLandTaxImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ReportLandTax)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_REPORTLANDTAX_WHERE_PKS_IN);

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

			for (ReportLandTax reportLandTax : (List<ReportLandTax>)q.list()) {
				map.put(reportLandTax.getPrimaryKeyObj(), reportLandTax);

				cacheResult(reportLandTax);

				uncachedPrimaryKeys.remove(reportLandTax.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ReportLandTaxModelImpl.ENTITY_CACHE_ENABLED,
					ReportLandTaxImpl.class, primaryKey, nullModel);
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
	 * Returns all the report land taxs.
	 *
	 * @return the report land taxs
	 */
	@Override
	public List<ReportLandTax> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the report land taxs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of report land taxs
	 * @param end the upper bound of the range of report land taxs (not inclusive)
	 * @return the range of report land taxs
	 */
	@Override
	public List<ReportLandTax> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the report land taxs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of report land taxs
	 * @param end the upper bound of the range of report land taxs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of report land taxs
	 */
	@Override
	public List<ReportLandTax> findAll(int start, int end,
		OrderByComparator<ReportLandTax> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the report land taxs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of report land taxs
	 * @param end the upper bound of the range of report land taxs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of report land taxs
	 */
	@Override
	public List<ReportLandTax> findAll(int start, int end,
		OrderByComparator<ReportLandTax> orderByComparator,
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

		List<ReportLandTax> list = null;

		if (retrieveFromCache) {
			list = (List<ReportLandTax>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REPORTLANDTAX);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REPORTLANDTAX;

				if (pagination) {
					sql = sql.concat(ReportLandTaxModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ReportLandTax>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReportLandTax>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the report land taxs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ReportLandTax reportLandTax : findAll()) {
			remove(reportLandTax);
		}
	}

	/**
	 * Returns the number of report land taxs.
	 *
	 * @return the number of report land taxs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REPORTLANDTAX);

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
		return ReportLandTaxModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the report land tax persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ReportLandTaxImpl.class.getName());
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
	private static final String _SQL_SELECT_REPORTLANDTAX = "SELECT reportLandTax FROM ReportLandTax reportLandTax";
	private static final String _SQL_SELECT_REPORTLANDTAX_WHERE_PKS_IN = "SELECT reportLandTax FROM ReportLandTax reportLandTax WHERE reportId IN (";
	private static final String _SQL_SELECT_REPORTLANDTAX_WHERE = "SELECT reportLandTax FROM ReportLandTax reportLandTax WHERE ";
	private static final String _SQL_COUNT_REPORTLANDTAX = "SELECT COUNT(reportLandTax) FROM ReportLandTax reportLandTax";
	private static final String _SQL_COUNT_REPORTLANDTAX_WHERE = "SELECT COUNT(reportLandTax) FROM ReportLandTax reportLandTax WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "reportLandTax.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ReportLandTax exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ReportLandTax exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ReportLandTaxPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}