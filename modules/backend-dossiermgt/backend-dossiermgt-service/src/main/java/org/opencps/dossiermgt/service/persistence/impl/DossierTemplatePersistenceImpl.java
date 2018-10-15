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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchDossierTemplateException;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.impl.DossierTemplateImpl;
import org.opencps.dossiermgt.model.impl.DossierTemplateModelImpl;
import org.opencps.dossiermgt.service.persistence.DossierTemplatePersistence;

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
 * The persistence implementation for the dossier template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierTemplatePersistence
 * @see org.opencps.dossiermgt.service.persistence.DossierTemplateUtil
 * @generated
 */
@ProviderType
public class DossierTemplatePersistenceImpl extends BasePersistenceImpl<DossierTemplate>
	implements DossierTemplatePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DossierTemplateUtil} to access the dossier template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DossierTemplateImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED,
			DossierTemplateImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED,
			DossierTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED,
			DossierTemplateImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED,
			DossierTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DossierTemplateModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dossier templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dossier templates
	 */
	@Override
	public List<DossierTemplate> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier templates
	 * @param end the upper bound of the range of dossier templates (not inclusive)
	 * @return the range of matching dossier templates
	 */
	@Override
	public List<DossierTemplate> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier templates
	 * @param end the upper bound of the range of dossier templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier templates
	 */
	@Override
	public List<DossierTemplate> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierTemplate> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier templates
	 * @param end the upper bound of the range of dossier templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier templates
	 */
	@Override
	public List<DossierTemplate> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierTemplate> orderByComparator,
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

		List<DossierTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<DossierTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierTemplate dossierTemplate : list) {
					if (!Objects.equals(uuid, dossierTemplate.getUuid())) {
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

			query.append(_SQL_SELECT_DOSSIERTEMPLATE_WHERE);

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
				query.append(DossierTemplateModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierTemplate>)QueryUtil.list(q,
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
	 * Returns the first dossier template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier template
	 * @throws NoSuchDossierTemplateException if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate findByUuid_First(String uuid,
		OrderByComparator<DossierTemplate> orderByComparator)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = fetchByUuid_First(uuid,
				orderByComparator);

		if (dossierTemplate != null) {
			return dossierTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierTemplateException(msg.toString());
	}

	/**
	 * Returns the first dossier template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier template, or <code>null</code> if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate fetchByUuid_First(String uuid,
		OrderByComparator<DossierTemplate> orderByComparator) {
		List<DossierTemplate> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier template
	 * @throws NoSuchDossierTemplateException if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate findByUuid_Last(String uuid,
		OrderByComparator<DossierTemplate> orderByComparator)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = fetchByUuid_Last(uuid,
				orderByComparator);

		if (dossierTemplate != null) {
			return dossierTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierTemplateException(msg.toString());
	}

	/**
	 * Returns the last dossier template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier template, or <code>null</code> if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate fetchByUuid_Last(String uuid,
		OrderByComparator<DossierTemplate> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DossierTemplate> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier templates before and after the current dossier template in the ordered set where uuid = &#63;.
	 *
	 * @param dossierTemplateId the primary key of the current dossier template
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier template
	 * @throws NoSuchDossierTemplateException if a dossier template with the primary key could not be found
	 */
	@Override
	public DossierTemplate[] findByUuid_PrevAndNext(long dossierTemplateId,
		String uuid, OrderByComparator<DossierTemplate> orderByComparator)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = findByPrimaryKey(dossierTemplateId);

		Session session = null;

		try {
			session = openSession();

			DossierTemplate[] array = new DossierTemplateImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dossierTemplate, uuid,
					orderByComparator, true);

			array[1] = dossierTemplate;

			array[2] = getByUuid_PrevAndNext(session, dossierTemplate, uuid,
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

	protected DossierTemplate getByUuid_PrevAndNext(Session session,
		DossierTemplate dossierTemplate, String uuid,
		OrderByComparator<DossierTemplate> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERTEMPLATE_WHERE);

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
			query.append(DossierTemplateModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier templates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DossierTemplate dossierTemplate : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierTemplate);
		}
	}

	/**
	 * Returns the number of dossier templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dossier templates
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERTEMPLATE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dossierTemplate.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dossierTemplate.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dossierTemplate.uuid IS NULL OR dossierTemplate.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED,
			DossierTemplateImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierTemplateModelImpl.UUID_COLUMN_BITMASK |
			DossierTemplateModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier template where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierTemplateException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier template
	 * @throws NoSuchDossierTemplateException if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = fetchByUUID_G(uuid, groupId);

		if (dossierTemplate == null) {
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

			throw new NoSuchDossierTemplateException(msg.toString());
		}

		return dossierTemplate;
	}

	/**
	 * Returns the dossier template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier template, or <code>null</code> if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dossier template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier template, or <code>null</code> if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DossierTemplate) {
			DossierTemplate dossierTemplate = (DossierTemplate)result;

			if (!Objects.equals(uuid, dossierTemplate.getUuid()) ||
					(groupId != dossierTemplate.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERTEMPLATE_WHERE);

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

				List<DossierTemplate> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DossierTemplate dossierTemplate = list.get(0);

					result = dossierTemplate;

					cacheResult(dossierTemplate);
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
			return (DossierTemplate)result;
		}
	}

	/**
	 * Removes the dossier template where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dossier template that was removed
	 */
	@Override
	public DossierTemplate removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = findByUUID_G(uuid, groupId);

		return remove(dossierTemplate);
	}

	/**
	 * Returns the number of dossier templates where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dossier templates
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERTEMPLATE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dossierTemplate.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dossierTemplate.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dossierTemplate.uuid IS NULL OR dossierTemplate.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dossierTemplate.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED,
			DossierTemplateImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED,
			DossierTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierTemplateModelImpl.UUID_COLUMN_BITMASK |
			DossierTemplateModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dossier templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dossier templates
	 */
	@Override
	public List<DossierTemplate> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier templates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier templates
	 * @param end the upper bound of the range of dossier templates (not inclusive)
	 * @return the range of matching dossier templates
	 */
	@Override
	public List<DossierTemplate> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier templates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier templates
	 * @param end the upper bound of the range of dossier templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier templates
	 */
	@Override
	public List<DossierTemplate> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierTemplate> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier templates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier templates
	 * @param end the upper bound of the range of dossier templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier templates
	 */
	@Override
	public List<DossierTemplate> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DossierTemplate> orderByComparator,
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

		List<DossierTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<DossierTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierTemplate dossierTemplate : list) {
					if (!Objects.equals(uuid, dossierTemplate.getUuid()) ||
							(companyId != dossierTemplate.getCompanyId())) {
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

			query.append(_SQL_SELECT_DOSSIERTEMPLATE_WHERE);

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
				query.append(DossierTemplateModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierTemplate>)QueryUtil.list(q,
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
	 * Returns the first dossier template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier template
	 * @throws NoSuchDossierTemplateException if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierTemplate> orderByComparator)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (dossierTemplate != null) {
			return dossierTemplate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDossierTemplateException(msg.toString());
	}

	/**
	 * Returns the first dossier template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier template, or <code>null</code> if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierTemplate> orderByComparator) {
		List<DossierTemplate> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier template
	 * @throws NoSuchDossierTemplateException if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierTemplate> orderByComparator)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (dossierTemplate != null) {
			return dossierTemplate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDossierTemplateException(msg.toString());
	}

	/**
	 * Returns the last dossier template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier template, or <code>null</code> if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierTemplate> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DossierTemplate> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier templates before and after the current dossier template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dossierTemplateId the primary key of the current dossier template
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier template
	 * @throws NoSuchDossierTemplateException if a dossier template with the primary key could not be found
	 */
	@Override
	public DossierTemplate[] findByUuid_C_PrevAndNext(long dossierTemplateId,
		String uuid, long companyId,
		OrderByComparator<DossierTemplate> orderByComparator)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = findByPrimaryKey(dossierTemplateId);

		Session session = null;

		try {
			session = openSession();

			DossierTemplate[] array = new DossierTemplateImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dossierTemplate, uuid,
					companyId, orderByComparator, true);

			array[1] = dossierTemplate;

			array[2] = getByUuid_C_PrevAndNext(session, dossierTemplate, uuid,
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

	protected DossierTemplate getByUuid_C_PrevAndNext(Session session,
		DossierTemplate dossierTemplate, String uuid, long companyId,
		OrderByComparator<DossierTemplate> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERTEMPLATE_WHERE);

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
			query.append(DossierTemplateModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier templates where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DossierTemplate dossierTemplate : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierTemplate);
		}
	}

	/**
	 * Returns the number of dossier templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dossier templates
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERTEMPLATE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dossierTemplate.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dossierTemplate.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dossierTemplate.uuid IS NULL OR dossierTemplate.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dossierTemplate.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_DT_NAME = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED,
			DossierTemplateImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_DT_NAME",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierTemplateModelImpl.GROUPID_COLUMN_BITMASK |
			DossierTemplateModelImpl.TEMPLATENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_DT_NAME = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_DT_NAME",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the dossier template where groupId = &#63; and templateName = &#63; or throws a {@link NoSuchDossierTemplateException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param templateName the template name
	 * @return the matching dossier template
	 * @throws NoSuchDossierTemplateException if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate findByG_DT_NAME(long groupId, String templateName)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = fetchByG_DT_NAME(groupId, templateName);

		if (dossierTemplate == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", templateName=");
			msg.append(templateName);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierTemplateException(msg.toString());
		}

		return dossierTemplate;
	}

	/**
	 * Returns the dossier template where groupId = &#63; and templateName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param templateName the template name
	 * @return the matching dossier template, or <code>null</code> if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate fetchByG_DT_NAME(long groupId, String templateName) {
		return fetchByG_DT_NAME(groupId, templateName, true);
	}

	/**
	 * Returns the dossier template where groupId = &#63; and templateName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param templateName the template name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier template, or <code>null</code> if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate fetchByG_DT_NAME(long groupId, String templateName,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, templateName };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_DT_NAME,
					finderArgs, this);
		}

		if (result instanceof DossierTemplate) {
			DossierTemplate dossierTemplate = (DossierTemplate)result;

			if ((groupId != dossierTemplate.getGroupId()) ||
					!Objects.equals(templateName,
						dossierTemplate.getTemplateName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_G_DT_NAME_GROUPID_2);

			boolean bindTemplateName = false;

			if (templateName == null) {
				query.append(_FINDER_COLUMN_G_DT_NAME_TEMPLATENAME_1);
			}
			else if (templateName.equals("")) {
				query.append(_FINDER_COLUMN_G_DT_NAME_TEMPLATENAME_3);
			}
			else {
				bindTemplateName = true;

				query.append(_FINDER_COLUMN_G_DT_NAME_TEMPLATENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTemplateName) {
					qPos.add(templateName);
				}

				List<DossierTemplate> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_DT_NAME,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierTemplatePersistenceImpl.fetchByG_DT_NAME(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierTemplate dossierTemplate = list.get(0);

					result = dossierTemplate;

					cacheResult(dossierTemplate);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DT_NAME,
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
			return (DossierTemplate)result;
		}
	}

	/**
	 * Removes the dossier template where groupId = &#63; and templateName = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param templateName the template name
	 * @return the dossier template that was removed
	 */
	@Override
	public DossierTemplate removeByG_DT_NAME(long groupId, String templateName)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = findByG_DT_NAME(groupId, templateName);

		return remove(dossierTemplate);
	}

	/**
	 * Returns the number of dossier templates where groupId = &#63; and templateName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param templateName the template name
	 * @return the number of matching dossier templates
	 */
	@Override
	public int countByG_DT_NAME(long groupId, String templateName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_DT_NAME;

		Object[] finderArgs = new Object[] { groupId, templateName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_G_DT_NAME_GROUPID_2);

			boolean bindTemplateName = false;

			if (templateName == null) {
				query.append(_FINDER_COLUMN_G_DT_NAME_TEMPLATENAME_1);
			}
			else if (templateName.equals("")) {
				query.append(_FINDER_COLUMN_G_DT_NAME_TEMPLATENAME_3);
			}
			else {
				bindTemplateName = true;

				query.append(_FINDER_COLUMN_G_DT_NAME_TEMPLATENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTemplateName) {
					qPos.add(templateName);
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

	private static final String _FINDER_COLUMN_G_DT_NAME_GROUPID_2 = "dossierTemplate.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_DT_NAME_TEMPLATENAME_1 = "dossierTemplate.templateName IS NULL";
	private static final String _FINDER_COLUMN_G_DT_NAME_TEMPLATENAME_2 = "dossierTemplate.templateName = ?";
	private static final String _FINDER_COLUMN_G_DT_NAME_TEMPLATENAME_3 = "(dossierTemplate.templateName IS NULL OR dossierTemplate.templateName = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_DT_TPLNO = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED,
			DossierTemplateImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_DT_TPLNO",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierTemplateModelImpl.GROUPID_COLUMN_BITMASK |
			DossierTemplateModelImpl.TEMPLATENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_DT_TPLNO = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_DT_TPLNO",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the dossier template where groupId = &#63; and templateNo = &#63; or throws a {@link NoSuchDossierTemplateException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param templateNo the template no
	 * @return the matching dossier template
	 * @throws NoSuchDossierTemplateException if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate findByG_DT_TPLNO(long groupId, String templateNo)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = fetchByG_DT_TPLNO(groupId, templateNo);

		if (dossierTemplate == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", templateNo=");
			msg.append(templateNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierTemplateException(msg.toString());
		}

		return dossierTemplate;
	}

	/**
	 * Returns the dossier template where groupId = &#63; and templateNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param templateNo the template no
	 * @return the matching dossier template, or <code>null</code> if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate fetchByG_DT_TPLNO(long groupId, String templateNo) {
		return fetchByG_DT_TPLNO(groupId, templateNo, true);
	}

	/**
	 * Returns the dossier template where groupId = &#63; and templateNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param templateNo the template no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier template, or <code>null</code> if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate fetchByG_DT_TPLNO(long groupId, String templateNo,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, templateNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_DT_TPLNO,
					finderArgs, this);
		}

		if (result instanceof DossierTemplate) {
			DossierTemplate dossierTemplate = (DossierTemplate)result;

			if ((groupId != dossierTemplate.getGroupId()) ||
					!Objects.equals(templateNo, dossierTemplate.getTemplateNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_G_DT_TPLNO_GROUPID_2);

			boolean bindTemplateNo = false;

			if (templateNo == null) {
				query.append(_FINDER_COLUMN_G_DT_TPLNO_TEMPLATENO_1);
			}
			else if (templateNo.equals("")) {
				query.append(_FINDER_COLUMN_G_DT_TPLNO_TEMPLATENO_3);
			}
			else {
				bindTemplateNo = true;

				query.append(_FINDER_COLUMN_G_DT_TPLNO_TEMPLATENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTemplateNo) {
					qPos.add(templateNo);
				}

				List<DossierTemplate> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_DT_TPLNO,
						finderArgs, list);
				}
				else {
					DossierTemplate dossierTemplate = list.get(0);

					result = dossierTemplate;

					cacheResult(dossierTemplate);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DT_TPLNO,
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
			return (DossierTemplate)result;
		}
	}

	/**
	 * Removes the dossier template where groupId = &#63; and templateNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param templateNo the template no
	 * @return the dossier template that was removed
	 */
	@Override
	public DossierTemplate removeByG_DT_TPLNO(long groupId, String templateNo)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = findByG_DT_TPLNO(groupId, templateNo);

		return remove(dossierTemplate);
	}

	/**
	 * Returns the number of dossier templates where groupId = &#63; and templateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param templateNo the template no
	 * @return the number of matching dossier templates
	 */
	@Override
	public int countByG_DT_TPLNO(long groupId, String templateNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_DT_TPLNO;

		Object[] finderArgs = new Object[] { groupId, templateNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_G_DT_TPLNO_GROUPID_2);

			boolean bindTemplateNo = false;

			if (templateNo == null) {
				query.append(_FINDER_COLUMN_G_DT_TPLNO_TEMPLATENO_1);
			}
			else if (templateNo.equals("")) {
				query.append(_FINDER_COLUMN_G_DT_TPLNO_TEMPLATENO_3);
			}
			else {
				bindTemplateNo = true;

				query.append(_FINDER_COLUMN_G_DT_TPLNO_TEMPLATENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTemplateNo) {
					qPos.add(templateNo);
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

	private static final String _FINDER_COLUMN_G_DT_TPLNO_GROUPID_2 = "dossierTemplate.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_DT_TPLNO_TEMPLATENO_1 = "dossierTemplate.templateNo IS NULL";
	private static final String _FINDER_COLUMN_G_DT_TPLNO_TEMPLATENO_2 = "dossierTemplate.templateNo = ?";
	private static final String _FINDER_COLUMN_G_DT_TPLNO_TEMPLATENO_3 = "(dossierTemplate.templateNo IS NULL OR dossierTemplate.templateNo = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_GID_DTPLNO = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED,
			DossierTemplateImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByGID_DTPLNO",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierTemplateModelImpl.GROUPID_COLUMN_BITMASK |
			DossierTemplateModelImpl.TEMPLATENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_DTPLNO = new FinderPath(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGID_DTPLNO",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the dossier template where groupId = &#63; and templateNo = &#63; or throws a {@link NoSuchDossierTemplateException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param templateNo the template no
	 * @return the matching dossier template
	 * @throws NoSuchDossierTemplateException if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate findByGID_DTPLNO(long groupId, String templateNo)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = fetchByGID_DTPLNO(groupId, templateNo);

		if (dossierTemplate == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", templateNo=");
			msg.append(templateNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierTemplateException(msg.toString());
		}

		return dossierTemplate;
	}

	/**
	 * Returns the dossier template where groupId = &#63; and templateNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param templateNo the template no
	 * @return the matching dossier template, or <code>null</code> if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate fetchByGID_DTPLNO(long groupId, String templateNo) {
		return fetchByGID_DTPLNO(groupId, templateNo, true);
	}

	/**
	 * Returns the dossier template where groupId = &#63; and templateNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param templateNo the template no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier template, or <code>null</code> if a matching dossier template could not be found
	 */
	@Override
	public DossierTemplate fetchByGID_DTPLNO(long groupId, String templateNo,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, templateNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_GID_DTPLNO,
					finderArgs, this);
		}

		if (result instanceof DossierTemplate) {
			DossierTemplate dossierTemplate = (DossierTemplate)result;

			if ((groupId != dossierTemplate.getGroupId()) ||
					!Objects.equals(templateNo, dossierTemplate.getTemplateNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_GID_DTPLNO_GROUPID_2);

			boolean bindTemplateNo = false;

			if (templateNo == null) {
				query.append(_FINDER_COLUMN_GID_DTPLNO_TEMPLATENO_1);
			}
			else if (templateNo.equals("")) {
				query.append(_FINDER_COLUMN_GID_DTPLNO_TEMPLATENO_3);
			}
			else {
				bindTemplateNo = true;

				query.append(_FINDER_COLUMN_GID_DTPLNO_TEMPLATENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTemplateNo) {
					qPos.add(templateNo);
				}

				List<DossierTemplate> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_GID_DTPLNO,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierTemplatePersistenceImpl.fetchByGID_DTPLNO(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierTemplate dossierTemplate = list.get(0);

					result = dossierTemplate;

					cacheResult(dossierTemplate);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_DTPLNO,
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
			return (DossierTemplate)result;
		}
	}

	/**
	 * Removes the dossier template where groupId = &#63; and templateNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param templateNo the template no
	 * @return the dossier template that was removed
	 */
	@Override
	public DossierTemplate removeByGID_DTPLNO(long groupId, String templateNo)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = findByGID_DTPLNO(groupId, templateNo);

		return remove(dossierTemplate);
	}

	/**
	 * Returns the number of dossier templates where groupId = &#63; and templateNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param templateNo the template no
	 * @return the number of matching dossier templates
	 */
	@Override
	public int countByGID_DTPLNO(long groupId, String templateNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_DTPLNO;

		Object[] finderArgs = new Object[] { groupId, templateNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_GID_DTPLNO_GROUPID_2);

			boolean bindTemplateNo = false;

			if (templateNo == null) {
				query.append(_FINDER_COLUMN_GID_DTPLNO_TEMPLATENO_1);
			}
			else if (templateNo.equals("")) {
				query.append(_FINDER_COLUMN_GID_DTPLNO_TEMPLATENO_3);
			}
			else {
				bindTemplateNo = true;

				query.append(_FINDER_COLUMN_GID_DTPLNO_TEMPLATENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTemplateNo) {
					qPos.add(templateNo);
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

	private static final String _FINDER_COLUMN_GID_DTPLNO_GROUPID_2 = "dossierTemplate.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GID_DTPLNO_TEMPLATENO_1 = "dossierTemplate.templateNo IS NULL";
	private static final String _FINDER_COLUMN_GID_DTPLNO_TEMPLATENO_2 = "dossierTemplate.templateNo = ?";
	private static final String _FINDER_COLUMN_GID_DTPLNO_TEMPLATENO_3 = "(dossierTemplate.templateNo IS NULL OR dossierTemplate.templateNo = '')";

	public DossierTemplatePersistenceImpl() {
		setModelClass(DossierTemplate.class);

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
	 * Caches the dossier template in the entity cache if it is enabled.
	 *
	 * @param dossierTemplate the dossier template
	 */
	@Override
	public void cacheResult(DossierTemplate dossierTemplate) {
		entityCache.putResult(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateImpl.class, dossierTemplate.getPrimaryKey(),
			dossierTemplate);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dossierTemplate.getUuid(), dossierTemplate.getGroupId() },
			dossierTemplate);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_DT_NAME,
			new Object[] {
				dossierTemplate.getGroupId(), dossierTemplate.getTemplateName()
			}, dossierTemplate);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_DT_TPLNO,
			new Object[] {
				dossierTemplate.getGroupId(), dossierTemplate.getTemplateNo()
			}, dossierTemplate);

		finderCache.putResult(FINDER_PATH_FETCH_BY_GID_DTPLNO,
			new Object[] {
				dossierTemplate.getGroupId(), dossierTemplate.getTemplateNo()
			}, dossierTemplate);

		dossierTemplate.resetOriginalValues();
	}

	/**
	 * Caches the dossier templates in the entity cache if it is enabled.
	 *
	 * @param dossierTemplates the dossier templates
	 */
	@Override
	public void cacheResult(List<DossierTemplate> dossierTemplates) {
		for (DossierTemplate dossierTemplate : dossierTemplates) {
			if (entityCache.getResult(
						DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
						DossierTemplateImpl.class,
						dossierTemplate.getPrimaryKey()) == null) {
				cacheResult(dossierTemplate);
			}
			else {
				dossierTemplate.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dossier templates.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DossierTemplateImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dossier template.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DossierTemplate dossierTemplate) {
		entityCache.removeResult(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateImpl.class, dossierTemplate.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DossierTemplateModelImpl)dossierTemplate, true);
	}

	@Override
	public void clearCache(List<DossierTemplate> dossierTemplates) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DossierTemplate dossierTemplate : dossierTemplates) {
			entityCache.removeResult(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
				DossierTemplateImpl.class, dossierTemplate.getPrimaryKey());

			clearUniqueFindersCache((DossierTemplateModelImpl)dossierTemplate,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		DossierTemplateModelImpl dossierTemplateModelImpl) {
		Object[] args = new Object[] {
				dossierTemplateModelImpl.getUuid(),
				dossierTemplateModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dossierTemplateModelImpl, false);

		args = new Object[] {
				dossierTemplateModelImpl.getGroupId(),
				dossierTemplateModelImpl.getTemplateName()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_DT_NAME, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_DT_NAME, args,
			dossierTemplateModelImpl, false);

		args = new Object[] {
				dossierTemplateModelImpl.getGroupId(),
				dossierTemplateModelImpl.getTemplateNo()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_DT_TPLNO, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_DT_TPLNO, args,
			dossierTemplateModelImpl, false);

		args = new Object[] {
				dossierTemplateModelImpl.getGroupId(),
				dossierTemplateModelImpl.getTemplateNo()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_GID_DTPLNO, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_GID_DTPLNO, args,
			dossierTemplateModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DossierTemplateModelImpl dossierTemplateModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierTemplateModelImpl.getUuid(),
					dossierTemplateModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dossierTemplateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierTemplateModelImpl.getOriginalUuid(),
					dossierTemplateModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierTemplateModelImpl.getGroupId(),
					dossierTemplateModelImpl.getTemplateName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DT_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DT_NAME, args);
		}

		if ((dossierTemplateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_DT_NAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierTemplateModelImpl.getOriginalGroupId(),
					dossierTemplateModelImpl.getOriginalTemplateName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DT_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DT_NAME, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierTemplateModelImpl.getGroupId(),
					dossierTemplateModelImpl.getTemplateNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DT_TPLNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DT_TPLNO, args);
		}

		if ((dossierTemplateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_DT_TPLNO.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierTemplateModelImpl.getOriginalGroupId(),
					dossierTemplateModelImpl.getOriginalTemplateNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DT_TPLNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DT_TPLNO, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierTemplateModelImpl.getGroupId(),
					dossierTemplateModelImpl.getTemplateNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_DTPLNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_DTPLNO, args);
		}

		if ((dossierTemplateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GID_DTPLNO.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierTemplateModelImpl.getOriginalGroupId(),
					dossierTemplateModelImpl.getOriginalTemplateNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_DTPLNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_DTPLNO, args);
		}
	}

	/**
	 * Creates a new dossier template with the primary key. Does not add the dossier template to the database.
	 *
	 * @param dossierTemplateId the primary key for the new dossier template
	 * @return the new dossier template
	 */
	@Override
	public DossierTemplate create(long dossierTemplateId) {
		DossierTemplate dossierTemplate = new DossierTemplateImpl();

		dossierTemplate.setNew(true);
		dossierTemplate.setPrimaryKey(dossierTemplateId);

		String uuid = PortalUUIDUtil.generate();

		dossierTemplate.setUuid(uuid);

		dossierTemplate.setCompanyId(companyProvider.getCompanyId());

		return dossierTemplate;
	}

	/**
	 * Removes the dossier template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dossierTemplateId the primary key of the dossier template
	 * @return the dossier template that was removed
	 * @throws NoSuchDossierTemplateException if a dossier template with the primary key could not be found
	 */
	@Override
	public DossierTemplate remove(long dossierTemplateId)
		throws NoSuchDossierTemplateException {
		return remove((Serializable)dossierTemplateId);
	}

	/**
	 * Removes the dossier template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dossier template
	 * @return the dossier template that was removed
	 * @throws NoSuchDossierTemplateException if a dossier template with the primary key could not be found
	 */
	@Override
	public DossierTemplate remove(Serializable primaryKey)
		throws NoSuchDossierTemplateException {
		Session session = null;

		try {
			session = openSession();

			DossierTemplate dossierTemplate = (DossierTemplate)session.get(DossierTemplateImpl.class,
					primaryKey);

			if (dossierTemplate == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDossierTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dossierTemplate);
		}
		catch (NoSuchDossierTemplateException nsee) {
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
	protected DossierTemplate removeImpl(DossierTemplate dossierTemplate) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dossierTemplate)) {
				dossierTemplate = (DossierTemplate)session.get(DossierTemplateImpl.class,
						dossierTemplate.getPrimaryKeyObj());
			}

			if (dossierTemplate != null) {
				session.delete(dossierTemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dossierTemplate != null) {
			clearCache(dossierTemplate);
		}

		return dossierTemplate;
	}

	@Override
	public DossierTemplate updateImpl(DossierTemplate dossierTemplate) {
		boolean isNew = dossierTemplate.isNew();

		if (!(dossierTemplate instanceof DossierTemplateModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dossierTemplate.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dossierTemplate);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dossierTemplate proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DossierTemplate implementation " +
				dossierTemplate.getClass());
		}

		DossierTemplateModelImpl dossierTemplateModelImpl = (DossierTemplateModelImpl)dossierTemplate;

		if (Validator.isNull(dossierTemplate.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dossierTemplate.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dossierTemplate.getCreateDate() == null)) {
			if (serviceContext == null) {
				dossierTemplate.setCreateDate(now);
			}
			else {
				dossierTemplate.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dossierTemplateModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dossierTemplate.setModifiedDate(now);
			}
			else {
				dossierTemplate.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dossierTemplate.isNew()) {
				session.save(dossierTemplate);

				dossierTemplate.setNew(false);
			}
			else {
				dossierTemplate = (DossierTemplate)session.merge(dossierTemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DossierTemplateModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dossierTemplateModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					dossierTemplateModelImpl.getUuid(),
					dossierTemplateModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dossierTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierTemplateModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dossierTemplateModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dossierTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierTemplateModelImpl.getOriginalUuid(),
						dossierTemplateModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dossierTemplateModelImpl.getUuid(),
						dossierTemplateModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
			DossierTemplateImpl.class, dossierTemplate.getPrimaryKey(),
			dossierTemplate, false);

		clearUniqueFindersCache(dossierTemplateModelImpl, false);
		cacheUniqueFindersCache(dossierTemplateModelImpl);

		dossierTemplate.resetOriginalValues();

		return dossierTemplate;
	}

	/**
	 * Returns the dossier template with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier template
	 * @return the dossier template
	 * @throws NoSuchDossierTemplateException if a dossier template with the primary key could not be found
	 */
	@Override
	public DossierTemplate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDossierTemplateException {
		DossierTemplate dossierTemplate = fetchByPrimaryKey(primaryKey);

		if (dossierTemplate == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDossierTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dossierTemplate;
	}

	/**
	 * Returns the dossier template with the primary key or throws a {@link NoSuchDossierTemplateException} if it could not be found.
	 *
	 * @param dossierTemplateId the primary key of the dossier template
	 * @return the dossier template
	 * @throws NoSuchDossierTemplateException if a dossier template with the primary key could not be found
	 */
	@Override
	public DossierTemplate findByPrimaryKey(long dossierTemplateId)
		throws NoSuchDossierTemplateException {
		return findByPrimaryKey((Serializable)dossierTemplateId);
	}

	/**
	 * Returns the dossier template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier template
	 * @return the dossier template, or <code>null</code> if a dossier template with the primary key could not be found
	 */
	@Override
	public DossierTemplate fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
				DossierTemplateImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DossierTemplate dossierTemplate = (DossierTemplate)serializable;

		if (dossierTemplate == null) {
			Session session = null;

			try {
				session = openSession();

				dossierTemplate = (DossierTemplate)session.get(DossierTemplateImpl.class,
						primaryKey);

				if (dossierTemplate != null) {
					cacheResult(dossierTemplate);
				}
				else {
					entityCache.putResult(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
						DossierTemplateImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
					DossierTemplateImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dossierTemplate;
	}

	/**
	 * Returns the dossier template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dossierTemplateId the primary key of the dossier template
	 * @return the dossier template, or <code>null</code> if a dossier template with the primary key could not be found
	 */
	@Override
	public DossierTemplate fetchByPrimaryKey(long dossierTemplateId) {
		return fetchByPrimaryKey((Serializable)dossierTemplateId);
	}

	@Override
	public Map<Serializable, DossierTemplate> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DossierTemplate> map = new HashMap<Serializable, DossierTemplate>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DossierTemplate dossierTemplate = fetchByPrimaryKey(primaryKey);

			if (dossierTemplate != null) {
				map.put(primaryKey, dossierTemplate);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
					DossierTemplateImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DossierTemplate)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DOSSIERTEMPLATE_WHERE_PKS_IN);

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

			for (DossierTemplate dossierTemplate : (List<DossierTemplate>)q.list()) {
				map.put(dossierTemplate.getPrimaryKeyObj(), dossierTemplate);

				cacheResult(dossierTemplate);

				uncachedPrimaryKeys.remove(dossierTemplate.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DossierTemplateModelImpl.ENTITY_CACHE_ENABLED,
					DossierTemplateImpl.class, primaryKey, nullModel);
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
	 * Returns all the dossier templates.
	 *
	 * @return the dossier templates
	 */
	@Override
	public List<DossierTemplate> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier templates
	 * @param end the upper bound of the range of dossier templates (not inclusive)
	 * @return the range of dossier templates
	 */
	@Override
	public List<DossierTemplate> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier templates
	 * @param end the upper bound of the range of dossier templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dossier templates
	 */
	@Override
	public List<DossierTemplate> findAll(int start, int end,
		OrderByComparator<DossierTemplate> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier templates
	 * @param end the upper bound of the range of dossier templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dossier templates
	 */
	@Override
	public List<DossierTemplate> findAll(int start, int end,
		OrderByComparator<DossierTemplate> orderByComparator,
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

		List<DossierTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<DossierTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOSSIERTEMPLATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOSSIERTEMPLATE;

				if (pagination) {
					sql = sql.concat(DossierTemplateModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DossierTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierTemplate>)QueryUtil.list(q,
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
	 * Removes all the dossier templates from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DossierTemplate dossierTemplate : findAll()) {
			remove(dossierTemplate);
		}
	}

	/**
	 * Returns the number of dossier templates.
	 *
	 * @return the number of dossier templates
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOSSIERTEMPLATE);

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
		return DossierTemplateModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dossier template persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DossierTemplateImpl.class.getName());
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
	private static final String _SQL_SELECT_DOSSIERTEMPLATE = "SELECT dossierTemplate FROM DossierTemplate dossierTemplate";
	private static final String _SQL_SELECT_DOSSIERTEMPLATE_WHERE_PKS_IN = "SELECT dossierTemplate FROM DossierTemplate dossierTemplate WHERE dossierTemplateId IN (";
	private static final String _SQL_SELECT_DOSSIERTEMPLATE_WHERE = "SELECT dossierTemplate FROM DossierTemplate dossierTemplate WHERE ";
	private static final String _SQL_COUNT_DOSSIERTEMPLATE = "SELECT COUNT(dossierTemplate) FROM DossierTemplate dossierTemplate";
	private static final String _SQL_COUNT_DOSSIERTEMPLATE_WHERE = "SELECT COUNT(dossierTemplate) FROM DossierTemplate dossierTemplate WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dossierTemplate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DossierTemplate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DossierTemplate exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DossierTemplatePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}