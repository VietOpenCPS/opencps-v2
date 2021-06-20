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

package org.opencps.synctracking.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.synctracking.exception.NoSuchDossierTaxException;
import org.opencps.synctracking.model.DossierTax;
import org.opencps.synctracking.model.impl.DossierTaxImpl;
import org.opencps.synctracking.model.impl.DossierTaxModelImpl;
import org.opencps.synctracking.service.persistence.DossierTaxPersistence;

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
 * The persistence implementation for the dossier tax service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author duongnt
 * @see DossierTaxPersistence
 * @see org.opencps.synctracking.service.persistence.DossierTaxUtil
 * @generated
 */
@ProviderType
public class DossierTaxPersistenceImpl extends BasePersistenceImpl<DossierTax>
	implements DossierTaxPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DossierTaxUtil} to access the dossier tax persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DossierTaxImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxModelImpl.FINDER_CACHE_ENABLED, DossierTaxImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxModelImpl.FINDER_CACHE_ENABLED, DossierTaxImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxModelImpl.FINDER_CACHE_ENABLED, DossierTaxImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxModelImpl.FINDER_CACHE_ENABLED, DossierTaxImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DossierTaxModelImpl.UUID_COLUMN_BITMASK |
			DossierTaxModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dossier taxs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dossier taxs
	 */
	@Override
	public List<DossierTax> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier taxs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier taxs
	 * @param end the upper bound of the range of dossier taxs (not inclusive)
	 * @return the range of matching dossier taxs
	 */
	@Override
	public List<DossierTax> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier taxs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier taxs
	 * @param end the upper bound of the range of dossier taxs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier taxs
	 */
	@Override
	public List<DossierTax> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierTax> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier taxs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier taxs
	 * @param end the upper bound of the range of dossier taxs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier taxs
	 */
	@Override
	public List<DossierTax> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierTax> orderByComparator,
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

		List<DossierTax> list = null;

		if (retrieveFromCache) {
			list = (List<DossierTax>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierTax dossierTax : list) {
					if (!Objects.equals(uuid, dossierTax.getUuid())) {
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

			query.append(_SQL_SELECT_DOSSIERTAX_WHERE);

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
				query.append(DossierTaxModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierTax>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierTax>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier tax in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier tax
	 * @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	 */
	@Override
	public DossierTax findByUuid_First(String uuid,
		OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException {
		DossierTax dossierTax = fetchByUuid_First(uuid, orderByComparator);

		if (dossierTax != null) {
			return dossierTax;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierTaxException(msg.toString());
	}

	/**
	 * Returns the first dossier tax in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	 */
	@Override
	public DossierTax fetchByUuid_First(String uuid,
		OrderByComparator<DossierTax> orderByComparator) {
		List<DossierTax> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier tax in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier tax
	 * @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	 */
	@Override
	public DossierTax findByUuid_Last(String uuid,
		OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException {
		DossierTax dossierTax = fetchByUuid_Last(uuid, orderByComparator);

		if (dossierTax != null) {
			return dossierTax;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierTaxException(msg.toString());
	}

	/**
	 * Returns the last dossier tax in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	 */
	@Override
	public DossierTax fetchByUuid_Last(String uuid,
		OrderByComparator<DossierTax> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DossierTax> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier taxs before and after the current dossier tax in the ordered set where uuid = &#63;.
	 *
	 * @param taxId the primary key of the current dossier tax
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier tax
	 * @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	 */
	@Override
	public DossierTax[] findByUuid_PrevAndNext(long taxId, String uuid,
		OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException {
		DossierTax dossierTax = findByPrimaryKey(taxId);

		Session session = null;

		try {
			session = openSession();

			DossierTax[] array = new DossierTaxImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dossierTax, uuid,
					orderByComparator, true);

			array[1] = dossierTax;

			array[2] = getByUuid_PrevAndNext(session, dossierTax, uuid,
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

	protected DossierTax getByUuid_PrevAndNext(Session session,
		DossierTax dossierTax, String uuid,
		OrderByComparator<DossierTax> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERTAX_WHERE);

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
			query.append(DossierTaxModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierTax);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierTax> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier taxs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DossierTax dossierTax : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossierTax);
		}
	}

	/**
	 * Returns the number of dossier taxs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dossier taxs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERTAX_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dossierTax.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dossierTax.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dossierTax.uuid IS NULL OR dossierTax.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxModelImpl.FINDER_CACHE_ENABLED, DossierTaxImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierTaxModelImpl.UUID_COLUMN_BITMASK |
			DossierTaxModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier tax where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierTaxException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier tax
	 * @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	 */
	@Override
	public DossierTax findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierTaxException {
		DossierTax dossierTax = fetchByUUID_G(uuid, groupId);

		if (dossierTax == null) {
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

			throw new NoSuchDossierTaxException(msg.toString());
		}

		return dossierTax;
	}

	/**
	 * Returns the dossier tax where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	 */
	@Override
	public DossierTax fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dossier tax where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	 */
	@Override
	public DossierTax fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DossierTax) {
			DossierTax dossierTax = (DossierTax)result;

			if (!Objects.equals(uuid, dossierTax.getUuid()) ||
					(groupId != dossierTax.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERTAX_WHERE);

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

				List<DossierTax> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DossierTax dossierTax = list.get(0);

					result = dossierTax;

					cacheResult(dossierTax);
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
			return (DossierTax)result;
		}
	}

	/**
	 * Removes the dossier tax where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dossier tax that was removed
	 */
	@Override
	public DossierTax removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierTaxException {
		DossierTax dossierTax = findByUUID_G(uuid, groupId);

		return remove(dossierTax);
	}

	/**
	 * Returns the number of dossier taxs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dossier taxs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERTAX_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dossierTax.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dossierTax.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dossierTax.uuid IS NULL OR dossierTax.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dossierTax.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxModelImpl.FINDER_CACHE_ENABLED, DossierTaxImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxModelImpl.FINDER_CACHE_ENABLED, DossierTaxImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierTaxModelImpl.UUID_COLUMN_BITMASK |
			DossierTaxModelImpl.COMPANYID_COLUMN_BITMASK |
			DossierTaxModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dossier taxs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dossier taxs
	 */
	@Override
	public List<DossierTax> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier taxs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier taxs
	 * @param end the upper bound of the range of dossier taxs (not inclusive)
	 * @return the range of matching dossier taxs
	 */
	@Override
	public List<DossierTax> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier taxs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier taxs
	 * @param end the upper bound of the range of dossier taxs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier taxs
	 */
	@Override
	public List<DossierTax> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierTax> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier taxs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier taxs
	 * @param end the upper bound of the range of dossier taxs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier taxs
	 */
	@Override
	public List<DossierTax> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierTax> orderByComparator,
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

		List<DossierTax> list = null;

		if (retrieveFromCache) {
			list = (List<DossierTax>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierTax dossierTax : list) {
					if (!Objects.equals(uuid, dossierTax.getUuid()) ||
							(companyId != dossierTax.getCompanyId())) {
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

			query.append(_SQL_SELECT_DOSSIERTAX_WHERE);

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
				query.append(DossierTaxModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierTax>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierTax>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier tax
	 * @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	 */
	@Override
	public DossierTax findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException {
		DossierTax dossierTax = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (dossierTax != null) {
			return dossierTax;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDossierTaxException(msg.toString());
	}

	/**
	 * Returns the first dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	 */
	@Override
	public DossierTax fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierTax> orderByComparator) {
		List<DossierTax> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier tax
	 * @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	 */
	@Override
	public DossierTax findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException {
		DossierTax dossierTax = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (dossierTax != null) {
			return dossierTax;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDossierTaxException(msg.toString());
	}

	/**
	 * Returns the last dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	 */
	@Override
	public DossierTax fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierTax> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DossierTax> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier taxs before and after the current dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param taxId the primary key of the current dossier tax
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier tax
	 * @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	 */
	@Override
	public DossierTax[] findByUuid_C_PrevAndNext(long taxId, String uuid,
		long companyId, OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException {
		DossierTax dossierTax = findByPrimaryKey(taxId);

		Session session = null;

		try {
			session = openSession();

			DossierTax[] array = new DossierTaxImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dossierTax, uuid,
					companyId, orderByComparator, true);

			array[1] = dossierTax;

			array[2] = getByUuid_C_PrevAndNext(session, dossierTax, uuid,
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

	protected DossierTax getByUuid_C_PrevAndNext(Session session,
		DossierTax dossierTax, String uuid, long companyId,
		OrderByComparator<DossierTax> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERTAX_WHERE);

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
			query.append(DossierTaxModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierTax);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierTax> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier taxs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DossierTax dossierTax : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierTax);
		}
	}

	/**
	 * Returns the number of dossier taxs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dossier taxs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERTAX_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dossierTax.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dossierTax.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dossierTax.uuid IS NULL OR dossierTax.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dossierTax.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_DMS = new FinderPath(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxModelImpl.FINDER_CACHE_ENABLED, DossierTaxImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_DMS",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName()
			},
			DossierTaxModelImpl.DOSSIERNO_COLUMN_BITMASK |
			DossierTaxModelImpl.MASOTHUE_COLUMN_BITMASK |
			DossierTaxModelImpl.SOQUYETDINH_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DMS = new FinderPath(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_DMS",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the dossier tax where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63; or throws a {@link NoSuchDossierTaxException} if it could not be found.
	 *
	 * @param dossierNo the dossier no
	 * @param maSoThue the ma so thue
	 * @param soQuyetDinh the so quyet dinh
	 * @return the matching dossier tax
	 * @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	 */
	@Override
	public DossierTax findByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh) throws NoSuchDossierTaxException {
		DossierTax dossierTax = fetchByF_DMS(dossierNo, maSoThue, soQuyetDinh);

		if (dossierTax == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossierNo=");
			msg.append(dossierNo);

			msg.append(", maSoThue=");
			msg.append(maSoThue);

			msg.append(", soQuyetDinh=");
			msg.append(soQuyetDinh);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierTaxException(msg.toString());
		}

		return dossierTax;
	}

	/**
	 * Returns the dossier tax where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossierNo the dossier no
	 * @param maSoThue the ma so thue
	 * @param soQuyetDinh the so quyet dinh
	 * @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	 */
	@Override
	public DossierTax fetchByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh) {
		return fetchByF_DMS(dossierNo, maSoThue, soQuyetDinh, true);
	}

	/**
	 * Returns the dossier tax where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossierNo the dossier no
	 * @param maSoThue the ma so thue
	 * @param soQuyetDinh the so quyet dinh
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	 */
	@Override
	public DossierTax fetchByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossierNo, maSoThue, soQuyetDinh };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_DMS,
					finderArgs, this);
		}

		if (result instanceof DossierTax) {
			DossierTax dossierTax = (DossierTax)result;

			if (!Objects.equals(dossierNo, dossierTax.getDossierNo()) ||
					!Objects.equals(maSoThue, dossierTax.getMaSoThue()) ||
					!Objects.equals(soQuyetDinh, dossierTax.getSoQuyetDinh())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DOSSIERTAX_WHERE);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_F_DMS_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_F_DMS_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_F_DMS_DOSSIERNO_2);
			}

			boolean bindMaSoThue = false;

			if (maSoThue == null) {
				query.append(_FINDER_COLUMN_F_DMS_MASOTHUE_1);
			}
			else if (maSoThue.equals("")) {
				query.append(_FINDER_COLUMN_F_DMS_MASOTHUE_3);
			}
			else {
				bindMaSoThue = true;

				query.append(_FINDER_COLUMN_F_DMS_MASOTHUE_2);
			}

			boolean bindSoQuyetDinh = false;

			if (soQuyetDinh == null) {
				query.append(_FINDER_COLUMN_F_DMS_SOQUYETDINH_1);
			}
			else if (soQuyetDinh.equals("")) {
				query.append(_FINDER_COLUMN_F_DMS_SOQUYETDINH_3);
			}
			else {
				bindSoQuyetDinh = true;

				query.append(_FINDER_COLUMN_F_DMS_SOQUYETDINH_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierNo) {
					qPos.add(dossierNo);
				}

				if (bindMaSoThue) {
					qPos.add(maSoThue);
				}

				if (bindSoQuyetDinh) {
					qPos.add(soQuyetDinh);
				}

				List<DossierTax> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_DMS,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierTaxPersistenceImpl.fetchByF_DMS(String, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierTax dossierTax = list.get(0);

					result = dossierTax;

					cacheResult(dossierTax);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DMS, finderArgs);

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
			return (DossierTax)result;
		}
	}

	/**
	 * Removes the dossier tax where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63; from the database.
	 *
	 * @param dossierNo the dossier no
	 * @param maSoThue the ma so thue
	 * @param soQuyetDinh the so quyet dinh
	 * @return the dossier tax that was removed
	 */
	@Override
	public DossierTax removeByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh) throws NoSuchDossierTaxException {
		DossierTax dossierTax = findByF_DMS(dossierNo, maSoThue, soQuyetDinh);

		return remove(dossierTax);
	}

	/**
	 * Returns the number of dossier taxs where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63;.
	 *
	 * @param dossierNo the dossier no
	 * @param maSoThue the ma so thue
	 * @param soQuyetDinh the so quyet dinh
	 * @return the number of matching dossier taxs
	 */
	@Override
	public int countByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DMS;

		Object[] finderArgs = new Object[] { dossierNo, maSoThue, soQuyetDinh };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERTAX_WHERE);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_F_DMS_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_F_DMS_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_F_DMS_DOSSIERNO_2);
			}

			boolean bindMaSoThue = false;

			if (maSoThue == null) {
				query.append(_FINDER_COLUMN_F_DMS_MASOTHUE_1);
			}
			else if (maSoThue.equals("")) {
				query.append(_FINDER_COLUMN_F_DMS_MASOTHUE_3);
			}
			else {
				bindMaSoThue = true;

				query.append(_FINDER_COLUMN_F_DMS_MASOTHUE_2);
			}

			boolean bindSoQuyetDinh = false;

			if (soQuyetDinh == null) {
				query.append(_FINDER_COLUMN_F_DMS_SOQUYETDINH_1);
			}
			else if (soQuyetDinh.equals("")) {
				query.append(_FINDER_COLUMN_F_DMS_SOQUYETDINH_3);
			}
			else {
				bindSoQuyetDinh = true;

				query.append(_FINDER_COLUMN_F_DMS_SOQUYETDINH_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossierNo) {
					qPos.add(dossierNo);
				}

				if (bindMaSoThue) {
					qPos.add(maSoThue);
				}

				if (bindSoQuyetDinh) {
					qPos.add(soQuyetDinh);
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

	private static final String _FINDER_COLUMN_F_DMS_DOSSIERNO_1 = "dossierTax.dossierNo IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DMS_DOSSIERNO_2 = "dossierTax.dossierNo = ? AND ";
	private static final String _FINDER_COLUMN_F_DMS_DOSSIERNO_3 = "(dossierTax.dossierNo IS NULL OR dossierTax.dossierNo = '') AND ";
	private static final String _FINDER_COLUMN_F_DMS_MASOTHUE_1 = "dossierTax.maSoThue IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DMS_MASOTHUE_2 = "dossierTax.maSoThue = ? AND ";
	private static final String _FINDER_COLUMN_F_DMS_MASOTHUE_3 = "(dossierTax.maSoThue IS NULL OR dossierTax.maSoThue = '') AND ";
	private static final String _FINDER_COLUMN_F_DMS_SOQUYETDINH_1 = "dossierTax.soQuyetDinh IS NULL";
	private static final String _FINDER_COLUMN_F_DMS_SOQUYETDINH_2 = "dossierTax.soQuyetDinh = ?";
	private static final String _FINDER_COLUMN_F_DMS_SOQUYETDINH_3 = "(dossierTax.soQuyetDinh IS NULL OR dossierTax.soQuyetDinh = '')";

	public DossierTaxPersistenceImpl() {
		setModelClass(DossierTax.class);

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
	 * Caches the dossier tax in the entity cache if it is enabled.
	 *
	 * @param dossierTax the dossier tax
	 */
	@Override
	public void cacheResult(DossierTax dossierTax) {
		entityCache.putResult(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxImpl.class, dossierTax.getPrimaryKey(), dossierTax);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dossierTax.getUuid(), dossierTax.getGroupId() },
			dossierTax);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DMS,
			new Object[] {
				dossierTax.getDossierNo(), dossierTax.getMaSoThue(),
				dossierTax.getSoQuyetDinh()
			}, dossierTax);

		dossierTax.resetOriginalValues();
	}

	/**
	 * Caches the dossier taxs in the entity cache if it is enabled.
	 *
	 * @param dossierTaxs the dossier taxs
	 */
	@Override
	public void cacheResult(List<DossierTax> dossierTaxs) {
		for (DossierTax dossierTax : dossierTaxs) {
			if (entityCache.getResult(
						DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
						DossierTaxImpl.class, dossierTax.getPrimaryKey()) == null) {
				cacheResult(dossierTax);
			}
			else {
				dossierTax.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dossier taxs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DossierTaxImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dossier tax.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DossierTax dossierTax) {
		entityCache.removeResult(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxImpl.class, dossierTax.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DossierTaxModelImpl)dossierTax, true);
	}

	@Override
	public void clearCache(List<DossierTax> dossierTaxs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DossierTax dossierTax : dossierTaxs) {
			entityCache.removeResult(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
				DossierTaxImpl.class, dossierTax.getPrimaryKey());

			clearUniqueFindersCache((DossierTaxModelImpl)dossierTax, true);
		}
	}

	protected void cacheUniqueFindersCache(
		DossierTaxModelImpl dossierTaxModelImpl) {
		Object[] args = new Object[] {
				dossierTaxModelImpl.getUuid(), dossierTaxModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dossierTaxModelImpl, false);

		args = new Object[] {
				dossierTaxModelImpl.getDossierNo(),
				dossierTaxModelImpl.getMaSoThue(),
				dossierTaxModelImpl.getSoQuyetDinh()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_DMS, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DMS, args,
			dossierTaxModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DossierTaxModelImpl dossierTaxModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierTaxModelImpl.getUuid(),
					dossierTaxModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dossierTaxModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierTaxModelImpl.getOriginalUuid(),
					dossierTaxModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierTaxModelImpl.getDossierNo(),
					dossierTaxModelImpl.getMaSoThue(),
					dossierTaxModelImpl.getSoQuyetDinh()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DMS, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DMS, args);
		}

		if ((dossierTaxModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_DMS.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierTaxModelImpl.getOriginalDossierNo(),
					dossierTaxModelImpl.getOriginalMaSoThue(),
					dossierTaxModelImpl.getOriginalSoQuyetDinh()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DMS, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DMS, args);
		}
	}

	/**
	 * Creates a new dossier tax with the primary key. Does not add the dossier tax to the database.
	 *
	 * @param taxId the primary key for the new dossier tax
	 * @return the new dossier tax
	 */
	@Override
	public DossierTax create(long taxId) {
		DossierTax dossierTax = new DossierTaxImpl();

		dossierTax.setNew(true);
		dossierTax.setPrimaryKey(taxId);

		String uuid = PortalUUIDUtil.generate();

		dossierTax.setUuid(uuid);

		dossierTax.setCompanyId(companyProvider.getCompanyId());

		return dossierTax;
	}

	/**
	 * Removes the dossier tax with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param taxId the primary key of the dossier tax
	 * @return the dossier tax that was removed
	 * @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	 */
	@Override
	public DossierTax remove(long taxId) throws NoSuchDossierTaxException {
		return remove((Serializable)taxId);
	}

	/**
	 * Removes the dossier tax with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dossier tax
	 * @return the dossier tax that was removed
	 * @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	 */
	@Override
	public DossierTax remove(Serializable primaryKey)
		throws NoSuchDossierTaxException {
		Session session = null;

		try {
			session = openSession();

			DossierTax dossierTax = (DossierTax)session.get(DossierTaxImpl.class,
					primaryKey);

			if (dossierTax == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDossierTaxException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dossierTax);
		}
		catch (NoSuchDossierTaxException nsee) {
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
	protected DossierTax removeImpl(DossierTax dossierTax) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dossierTax)) {
				dossierTax = (DossierTax)session.get(DossierTaxImpl.class,
						dossierTax.getPrimaryKeyObj());
			}

			if (dossierTax != null) {
				session.delete(dossierTax);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dossierTax != null) {
			clearCache(dossierTax);
		}

		return dossierTax;
	}

	@Override
	public DossierTax updateImpl(DossierTax dossierTax) {
		boolean isNew = dossierTax.isNew();

		if (!(dossierTax instanceof DossierTaxModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dossierTax.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dossierTax);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dossierTax proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DossierTax implementation " +
				dossierTax.getClass());
		}

		DossierTaxModelImpl dossierTaxModelImpl = (DossierTaxModelImpl)dossierTax;

		if (Validator.isNull(dossierTax.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dossierTax.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dossierTax.getCreateDate() == null)) {
			if (serviceContext == null) {
				dossierTax.setCreateDate(now);
			}
			else {
				dossierTax.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dossierTaxModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dossierTax.setModifiedDate(now);
			}
			else {
				dossierTax.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dossierTax.isNew()) {
				session.save(dossierTax);

				dossierTax.setNew(false);
			}
			else {
				dossierTax = (DossierTax)session.merge(dossierTax);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DossierTaxModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dossierTaxModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					dossierTaxModelImpl.getUuid(),
					dossierTaxModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dossierTaxModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierTaxModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dossierTaxModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dossierTaxModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierTaxModelImpl.getOriginalUuid(),
						dossierTaxModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dossierTaxModelImpl.getUuid(),
						dossierTaxModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
			DossierTaxImpl.class, dossierTax.getPrimaryKey(), dossierTax, false);

		clearUniqueFindersCache(dossierTaxModelImpl, false);
		cacheUniqueFindersCache(dossierTaxModelImpl);

		dossierTax.resetOriginalValues();

		return dossierTax;
	}

	/**
	 * Returns the dossier tax with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier tax
	 * @return the dossier tax
	 * @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	 */
	@Override
	public DossierTax findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDossierTaxException {
		DossierTax dossierTax = fetchByPrimaryKey(primaryKey);

		if (dossierTax == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDossierTaxException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dossierTax;
	}

	/**
	 * Returns the dossier tax with the primary key or throws a {@link NoSuchDossierTaxException} if it could not be found.
	 *
	 * @param taxId the primary key of the dossier tax
	 * @return the dossier tax
	 * @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	 */
	@Override
	public DossierTax findByPrimaryKey(long taxId)
		throws NoSuchDossierTaxException {
		return findByPrimaryKey((Serializable)taxId);
	}

	/**
	 * Returns the dossier tax with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier tax
	 * @return the dossier tax, or <code>null</code> if a dossier tax with the primary key could not be found
	 */
	@Override
	public DossierTax fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
				DossierTaxImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DossierTax dossierTax = (DossierTax)serializable;

		if (dossierTax == null) {
			Session session = null;

			try {
				session = openSession();

				dossierTax = (DossierTax)session.get(DossierTaxImpl.class,
						primaryKey);

				if (dossierTax != null) {
					cacheResult(dossierTax);
				}
				else {
					entityCache.putResult(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
						DossierTaxImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
					DossierTaxImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dossierTax;
	}

	/**
	 * Returns the dossier tax with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param taxId the primary key of the dossier tax
	 * @return the dossier tax, or <code>null</code> if a dossier tax with the primary key could not be found
	 */
	@Override
	public DossierTax fetchByPrimaryKey(long taxId) {
		return fetchByPrimaryKey((Serializable)taxId);
	}

	@Override
	public Map<Serializable, DossierTax> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DossierTax> map = new HashMap<Serializable, DossierTax>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DossierTax dossierTax = fetchByPrimaryKey(primaryKey);

			if (dossierTax != null) {
				map.put(primaryKey, dossierTax);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
					DossierTaxImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DossierTax)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DOSSIERTAX_WHERE_PKS_IN);

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

			for (DossierTax dossierTax : (List<DossierTax>)q.list()) {
				map.put(dossierTax.getPrimaryKeyObj(), dossierTax);

				cacheResult(dossierTax);

				uncachedPrimaryKeys.remove(dossierTax.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DossierTaxModelImpl.ENTITY_CACHE_ENABLED,
					DossierTaxImpl.class, primaryKey, nullModel);
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
	 * Returns all the dossier taxs.
	 *
	 * @return the dossier taxs
	 */
	@Override
	public List<DossierTax> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier taxs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier taxs
	 * @param end the upper bound of the range of dossier taxs (not inclusive)
	 * @return the range of dossier taxs
	 */
	@Override
	public List<DossierTax> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier taxs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier taxs
	 * @param end the upper bound of the range of dossier taxs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dossier taxs
	 */
	@Override
	public List<DossierTax> findAll(int start, int end,
		OrderByComparator<DossierTax> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier taxs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier taxs
	 * @param end the upper bound of the range of dossier taxs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dossier taxs
	 */
	@Override
	public List<DossierTax> findAll(int start, int end,
		OrderByComparator<DossierTax> orderByComparator,
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

		List<DossierTax> list = null;

		if (retrieveFromCache) {
			list = (List<DossierTax>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOSSIERTAX);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOSSIERTAX;

				if (pagination) {
					sql = sql.concat(DossierTaxModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DossierTax>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierTax>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the dossier taxs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DossierTax dossierTax : findAll()) {
			remove(dossierTax);
		}
	}

	/**
	 * Returns the number of dossier taxs.
	 *
	 * @return the number of dossier taxs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOSSIERTAX);

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
		return DossierTaxModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dossier tax persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DossierTaxImpl.class.getName());
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
	private static final String _SQL_SELECT_DOSSIERTAX = "SELECT dossierTax FROM DossierTax dossierTax";
	private static final String _SQL_SELECT_DOSSIERTAX_WHERE_PKS_IN = "SELECT dossierTax FROM DossierTax dossierTax WHERE taxId IN (";
	private static final String _SQL_SELECT_DOSSIERTAX_WHERE = "SELECT dossierTax FROM DossierTax dossierTax WHERE ";
	private static final String _SQL_COUNT_DOSSIERTAX = "SELECT COUNT(dossierTax) FROM DossierTax dossierTax";
	private static final String _SQL_COUNT_DOSSIERTAX_WHERE = "SELECT COUNT(dossierTax) FROM DossierTax dossierTax WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dossierTax.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DossierTax exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DossierTax exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DossierTaxPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}