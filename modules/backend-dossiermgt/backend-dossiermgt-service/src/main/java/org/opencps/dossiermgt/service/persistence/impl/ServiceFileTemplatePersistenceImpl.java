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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchServiceFileTemplateException;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.impl.ServiceFileTemplateImpl;
import org.opencps.dossiermgt.model.impl.ServiceFileTemplateModelImpl;
import org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK;
import org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the service file template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ServiceFileTemplatePersistence
 * @see org.opencps.dossiermgt.service.persistence.ServiceFileTemplateUtil
 * @generated
 */
@ProviderType
public class ServiceFileTemplatePersistenceImpl extends BasePersistenceImpl<ServiceFileTemplate>
	implements ServiceFileTemplatePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ServiceFileTemplateUtil} to access the service file template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ServiceFileTemplateImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			ServiceFileTemplateModelImpl.FINDER_CACHE_ENABLED,
			ServiceFileTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			ServiceFileTemplateModelImpl.FINDER_CACHE_ENABLED,
			ServiceFileTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			ServiceFileTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			ServiceFileTemplateModelImpl.FINDER_CACHE_ENABLED,
			ServiceFileTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			ServiceFileTemplateModelImpl.FINDER_CACHE_ENABLED,
			ServiceFileTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ServiceFileTemplateModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			ServiceFileTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the service file templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching service file templates
	 */
	@Override
	public List<ServiceFileTemplate> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service file templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service file templates
	 * @param end the upper bound of the range of service file templates (not inclusive)
	 * @return the range of matching service file templates
	 */
	@Override
	public List<ServiceFileTemplate> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service file templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service file templates
	 * @param end the upper bound of the range of service file templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service file templates
	 */
	@Override
	public List<ServiceFileTemplate> findByUuid(String uuid, int start,
		int end, OrderByComparator<ServiceFileTemplate> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service file templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service file templates
	 * @param end the upper bound of the range of service file templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service file templates
	 */
	@Override
	public List<ServiceFileTemplate> findByUuid(String uuid, int start,
		int end, OrderByComparator<ServiceFileTemplate> orderByComparator,
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

		List<ServiceFileTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceFileTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceFileTemplate serviceFileTemplate : list) {
					if (!Objects.equals(uuid, serviceFileTemplate.getUuid())) {
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

			query.append(_SQL_SELECT_SERVICEFILETEMPLATE_WHERE);

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
				query.append(ServiceFileTemplateModelImpl.ORDER_BY_JPQL);
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
					list = (List<ServiceFileTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceFileTemplate>)QueryUtil.list(q,
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
	 * Returns the first service file template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service file template
	 * @throws NoSuchServiceFileTemplateException if a matching service file template could not be found
	 */
	@Override
	public ServiceFileTemplate findByUuid_First(String uuid,
		OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws NoSuchServiceFileTemplateException {
		ServiceFileTemplate serviceFileTemplate = fetchByUuid_First(uuid,
				orderByComparator);

		if (serviceFileTemplate != null) {
			return serviceFileTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchServiceFileTemplateException(msg.toString());
	}

	/**
	 * Returns the first service file template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service file template, or <code>null</code> if a matching service file template could not be found
	 */
	@Override
	public ServiceFileTemplate fetchByUuid_First(String uuid,
		OrderByComparator<ServiceFileTemplate> orderByComparator) {
		List<ServiceFileTemplate> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service file template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service file template
	 * @throws NoSuchServiceFileTemplateException if a matching service file template could not be found
	 */
	@Override
	public ServiceFileTemplate findByUuid_Last(String uuid,
		OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws NoSuchServiceFileTemplateException {
		ServiceFileTemplate serviceFileTemplate = fetchByUuid_Last(uuid,
				orderByComparator);

		if (serviceFileTemplate != null) {
			return serviceFileTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchServiceFileTemplateException(msg.toString());
	}

	/**
	 * Returns the last service file template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service file template, or <code>null</code> if a matching service file template could not be found
	 */
	@Override
	public ServiceFileTemplate fetchByUuid_Last(String uuid,
		OrderByComparator<ServiceFileTemplate> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ServiceFileTemplate> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service file templates before and after the current service file template in the ordered set where uuid = &#63;.
	 *
	 * @param serviceFileTemplatePK the primary key of the current service file template
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service file template
	 * @throws NoSuchServiceFileTemplateException if a service file template with the primary key could not be found
	 */
	@Override
	public ServiceFileTemplate[] findByUuid_PrevAndNext(
		ServiceFileTemplatePK serviceFileTemplatePK, String uuid,
		OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws NoSuchServiceFileTemplateException {
		ServiceFileTemplate serviceFileTemplate = findByPrimaryKey(serviceFileTemplatePK);

		Session session = null;

		try {
			session = openSession();

			ServiceFileTemplate[] array = new ServiceFileTemplateImpl[3];

			array[0] = getByUuid_PrevAndNext(session, serviceFileTemplate,
					uuid, orderByComparator, true);

			array[1] = serviceFileTemplate;

			array[2] = getByUuid_PrevAndNext(session, serviceFileTemplate,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ServiceFileTemplate getByUuid_PrevAndNext(Session session,
		ServiceFileTemplate serviceFileTemplate, String uuid,
		OrderByComparator<ServiceFileTemplate> orderByComparator,
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

		query.append(_SQL_SELECT_SERVICEFILETEMPLATE_WHERE);

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
			query.append(ServiceFileTemplateModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(serviceFileTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceFileTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service file templates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ServiceFileTemplate serviceFileTemplate : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceFileTemplate);
		}
	}

	/**
	 * Returns the number of service file templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching service file templates
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVICEFILETEMPLATE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "serviceFileTemplate.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "serviceFileTemplate.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(serviceFileTemplate.uuid IS NULL OR serviceFileTemplate.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SERVICEINFOID =
		new FinderPath(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			ServiceFileTemplateModelImpl.FINDER_CACHE_ENABLED,
			ServiceFileTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByServiceInfoId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICEINFOID =
		new FinderPath(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			ServiceFileTemplateModelImpl.FINDER_CACHE_ENABLED,
			ServiceFileTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByServiceInfoId",
			new String[] { Long.class.getName() },
			ServiceFileTemplateModelImpl.SERVICEINFOID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SERVICEINFOID = new FinderPath(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			ServiceFileTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByServiceInfoId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the service file templates where serviceInfoId = &#63;.
	 *
	 * @param serviceInfoId the service info ID
	 * @return the matching service file templates
	 */
	@Override
	public List<ServiceFileTemplate> findByServiceInfoId(long serviceInfoId) {
		return findByServiceInfoId(serviceInfoId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service file templates where serviceInfoId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceInfoId the service info ID
	 * @param start the lower bound of the range of service file templates
	 * @param end the upper bound of the range of service file templates (not inclusive)
	 * @return the range of matching service file templates
	 */
	@Override
	public List<ServiceFileTemplate> findByServiceInfoId(long serviceInfoId,
		int start, int end) {
		return findByServiceInfoId(serviceInfoId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service file templates where serviceInfoId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceInfoId the service info ID
	 * @param start the lower bound of the range of service file templates
	 * @param end the upper bound of the range of service file templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service file templates
	 */
	@Override
	public List<ServiceFileTemplate> findByServiceInfoId(long serviceInfoId,
		int start, int end,
		OrderByComparator<ServiceFileTemplate> orderByComparator) {
		return findByServiceInfoId(serviceInfoId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service file templates where serviceInfoId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceInfoId the service info ID
	 * @param start the lower bound of the range of service file templates
	 * @param end the upper bound of the range of service file templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service file templates
	 */
	@Override
	public List<ServiceFileTemplate> findByServiceInfoId(long serviceInfoId,
		int start, int end,
		OrderByComparator<ServiceFileTemplate> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICEINFOID;
			finderArgs = new Object[] { serviceInfoId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SERVICEINFOID;
			finderArgs = new Object[] {
					serviceInfoId,
					
					start, end, orderByComparator
				};
		}

		List<ServiceFileTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceFileTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceFileTemplate serviceFileTemplate : list) {
					if ((serviceInfoId != serviceFileTemplate.getServiceInfoId())) {
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

			query.append(_SQL_SELECT_SERVICEFILETEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_SERVICEINFOID_SERVICEINFOID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ServiceFileTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceInfoId);

				if (!pagination) {
					list = (List<ServiceFileTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceFileTemplate>)QueryUtil.list(q,
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
	 * Returns the first service file template in the ordered set where serviceInfoId = &#63;.
	 *
	 * @param serviceInfoId the service info ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service file template
	 * @throws NoSuchServiceFileTemplateException if a matching service file template could not be found
	 */
	@Override
	public ServiceFileTemplate findByServiceInfoId_First(long serviceInfoId,
		OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws NoSuchServiceFileTemplateException {
		ServiceFileTemplate serviceFileTemplate = fetchByServiceInfoId_First(serviceInfoId,
				orderByComparator);

		if (serviceFileTemplate != null) {
			return serviceFileTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("serviceInfoId=");
		msg.append(serviceInfoId);

		msg.append("}");

		throw new NoSuchServiceFileTemplateException(msg.toString());
	}

	/**
	 * Returns the first service file template in the ordered set where serviceInfoId = &#63;.
	 *
	 * @param serviceInfoId the service info ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service file template, or <code>null</code> if a matching service file template could not be found
	 */
	@Override
	public ServiceFileTemplate fetchByServiceInfoId_First(long serviceInfoId,
		OrderByComparator<ServiceFileTemplate> orderByComparator) {
		List<ServiceFileTemplate> list = findByServiceInfoId(serviceInfoId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service file template in the ordered set where serviceInfoId = &#63;.
	 *
	 * @param serviceInfoId the service info ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service file template
	 * @throws NoSuchServiceFileTemplateException if a matching service file template could not be found
	 */
	@Override
	public ServiceFileTemplate findByServiceInfoId_Last(long serviceInfoId,
		OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws NoSuchServiceFileTemplateException {
		ServiceFileTemplate serviceFileTemplate = fetchByServiceInfoId_Last(serviceInfoId,
				orderByComparator);

		if (serviceFileTemplate != null) {
			return serviceFileTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("serviceInfoId=");
		msg.append(serviceInfoId);

		msg.append("}");

		throw new NoSuchServiceFileTemplateException(msg.toString());
	}

	/**
	 * Returns the last service file template in the ordered set where serviceInfoId = &#63;.
	 *
	 * @param serviceInfoId the service info ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service file template, or <code>null</code> if a matching service file template could not be found
	 */
	@Override
	public ServiceFileTemplate fetchByServiceInfoId_Last(long serviceInfoId,
		OrderByComparator<ServiceFileTemplate> orderByComparator) {
		int count = countByServiceInfoId(serviceInfoId);

		if (count == 0) {
			return null;
		}

		List<ServiceFileTemplate> list = findByServiceInfoId(serviceInfoId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service file templates before and after the current service file template in the ordered set where serviceInfoId = &#63;.
	 *
	 * @param serviceFileTemplatePK the primary key of the current service file template
	 * @param serviceInfoId the service info ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service file template
	 * @throws NoSuchServiceFileTemplateException if a service file template with the primary key could not be found
	 */
	@Override
	public ServiceFileTemplate[] findByServiceInfoId_PrevAndNext(
		ServiceFileTemplatePK serviceFileTemplatePK, long serviceInfoId,
		OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws NoSuchServiceFileTemplateException {
		ServiceFileTemplate serviceFileTemplate = findByPrimaryKey(serviceFileTemplatePK);

		Session session = null;

		try {
			session = openSession();

			ServiceFileTemplate[] array = new ServiceFileTemplateImpl[3];

			array[0] = getByServiceInfoId_PrevAndNext(session,
					serviceFileTemplate, serviceInfoId, orderByComparator, true);

			array[1] = serviceFileTemplate;

			array[2] = getByServiceInfoId_PrevAndNext(session,
					serviceFileTemplate, serviceInfoId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ServiceFileTemplate getByServiceInfoId_PrevAndNext(
		Session session, ServiceFileTemplate serviceFileTemplate,
		long serviceInfoId,
		OrderByComparator<ServiceFileTemplate> orderByComparator,
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

		query.append(_SQL_SELECT_SERVICEFILETEMPLATE_WHERE);

		query.append(_FINDER_COLUMN_SERVICEINFOID_SERVICEINFOID_2);

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
			query.append(ServiceFileTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(serviceInfoId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(serviceFileTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceFileTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service file templates where serviceInfoId = &#63; from the database.
	 *
	 * @param serviceInfoId the service info ID
	 */
	@Override
	public void removeByServiceInfoId(long serviceInfoId) {
		for (ServiceFileTemplate serviceFileTemplate : findByServiceInfoId(
				serviceInfoId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceFileTemplate);
		}
	}

	/**
	 * Returns the number of service file templates where serviceInfoId = &#63;.
	 *
	 * @param serviceInfoId the service info ID
	 * @return the number of matching service file templates
	 */
	@Override
	public int countByServiceInfoId(long serviceInfoId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SERVICEINFOID;

		Object[] finderArgs = new Object[] { serviceInfoId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVICEFILETEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_SERVICEINFOID_SERVICEINFOID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceInfoId);

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

	private static final String _FINDER_COLUMN_SERVICEINFOID_SERVICEINFOID_2 = "serviceFileTemplate.id.serviceInfoId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_SERVICEINFOID_FILETEMPLATENO =
		new FinderPath(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			ServiceFileTemplateModelImpl.FINDER_CACHE_ENABLED,
			ServiceFileTemplateImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_serviceInfoId_fileTemplateNo",
			new String[] { Long.class.getName(), String.class.getName() },
			ServiceFileTemplateModelImpl.SERVICEINFOID_COLUMN_BITMASK |
			ServiceFileTemplateModelImpl.FILETEMPLATENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_SERVICEINFOID_FILETEMPLATENO =
		new FinderPath(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			ServiceFileTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_serviceInfoId_fileTemplateNo",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the service file template where serviceInfoId = &#63; and fileTemplateNo = &#63; or throws a {@link NoSuchServiceFileTemplateException} if it could not be found.
	 *
	 * @param serviceInfoId the service info ID
	 * @param fileTemplateNo the file template no
	 * @return the matching service file template
	 * @throws NoSuchServiceFileTemplateException if a matching service file template could not be found
	 */
	@Override
	public ServiceFileTemplate findByF_serviceInfoId_fileTemplateNo(
		long serviceInfoId, String fileTemplateNo)
		throws NoSuchServiceFileTemplateException {
		ServiceFileTemplate serviceFileTemplate = fetchByF_serviceInfoId_fileTemplateNo(serviceInfoId,
				fileTemplateNo);

		if (serviceFileTemplate == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("serviceInfoId=");
			msg.append(serviceInfoId);

			msg.append(", fileTemplateNo=");
			msg.append(fileTemplateNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchServiceFileTemplateException(msg.toString());
		}

		return serviceFileTemplate;
	}

	/**
	 * Returns the service file template where serviceInfoId = &#63; and fileTemplateNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param serviceInfoId the service info ID
	 * @param fileTemplateNo the file template no
	 * @return the matching service file template, or <code>null</code> if a matching service file template could not be found
	 */
	@Override
	public ServiceFileTemplate fetchByF_serviceInfoId_fileTemplateNo(
		long serviceInfoId, String fileTemplateNo) {
		return fetchByF_serviceInfoId_fileTemplateNo(serviceInfoId,
			fileTemplateNo, true);
	}

	/**
	 * Returns the service file template where serviceInfoId = &#63; and fileTemplateNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param serviceInfoId the service info ID
	 * @param fileTemplateNo the file template no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching service file template, or <code>null</code> if a matching service file template could not be found
	 */
	@Override
	public ServiceFileTemplate fetchByF_serviceInfoId_fileTemplateNo(
		long serviceInfoId, String fileTemplateNo, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { serviceInfoId, fileTemplateNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_SERVICEINFOID_FILETEMPLATENO,
					finderArgs, this);
		}

		if (result instanceof ServiceFileTemplate) {
			ServiceFileTemplate serviceFileTemplate = (ServiceFileTemplate)result;

			if ((serviceInfoId != serviceFileTemplate.getServiceInfoId()) ||
					!Objects.equals(fileTemplateNo,
						serviceFileTemplate.getFileTemplateNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SERVICEFILETEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_F_SERVICEINFOID_FILETEMPLATENO_SERVICEINFOID_2);

			boolean bindFileTemplateNo = false;

			if (fileTemplateNo == null) {
				query.append(_FINDER_COLUMN_F_SERVICEINFOID_FILETEMPLATENO_FILETEMPLATENO_1);
			}
			else if (fileTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_F_SERVICEINFOID_FILETEMPLATENO_FILETEMPLATENO_3);
			}
			else {
				bindFileTemplateNo = true;

				query.append(_FINDER_COLUMN_F_SERVICEINFOID_FILETEMPLATENO_FILETEMPLATENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceInfoId);

				if (bindFileTemplateNo) {
					qPos.add(fileTemplateNo);
				}

				List<ServiceFileTemplate> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_SERVICEINFOID_FILETEMPLATENO,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ServiceFileTemplatePersistenceImpl.fetchByF_serviceInfoId_fileTemplateNo(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ServiceFileTemplate serviceFileTemplate = list.get(0);

					result = serviceFileTemplate;

					cacheResult(serviceFileTemplate);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_SERVICEINFOID_FILETEMPLATENO,
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
			return (ServiceFileTemplate)result;
		}
	}

	/**
	 * Removes the service file template where serviceInfoId = &#63; and fileTemplateNo = &#63; from the database.
	 *
	 * @param serviceInfoId the service info ID
	 * @param fileTemplateNo the file template no
	 * @return the service file template that was removed
	 */
	@Override
	public ServiceFileTemplate removeByF_serviceInfoId_fileTemplateNo(
		long serviceInfoId, String fileTemplateNo)
		throws NoSuchServiceFileTemplateException {
		ServiceFileTemplate serviceFileTemplate = findByF_serviceInfoId_fileTemplateNo(serviceInfoId,
				fileTemplateNo);

		return remove(serviceFileTemplate);
	}

	/**
	 * Returns the number of service file templates where serviceInfoId = &#63; and fileTemplateNo = &#63;.
	 *
	 * @param serviceInfoId the service info ID
	 * @param fileTemplateNo the file template no
	 * @return the number of matching service file templates
	 */
	@Override
	public int countByF_serviceInfoId_fileTemplateNo(long serviceInfoId,
		String fileTemplateNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_SERVICEINFOID_FILETEMPLATENO;

		Object[] finderArgs = new Object[] { serviceInfoId, fileTemplateNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICEFILETEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_F_SERVICEINFOID_FILETEMPLATENO_SERVICEINFOID_2);

			boolean bindFileTemplateNo = false;

			if (fileTemplateNo == null) {
				query.append(_FINDER_COLUMN_F_SERVICEINFOID_FILETEMPLATENO_FILETEMPLATENO_1);
			}
			else if (fileTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_F_SERVICEINFOID_FILETEMPLATENO_FILETEMPLATENO_3);
			}
			else {
				bindFileTemplateNo = true;

				query.append(_FINDER_COLUMN_F_SERVICEINFOID_FILETEMPLATENO_FILETEMPLATENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceInfoId);

				if (bindFileTemplateNo) {
					qPos.add(fileTemplateNo);
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

	private static final String _FINDER_COLUMN_F_SERVICEINFOID_FILETEMPLATENO_SERVICEINFOID_2 =
		"serviceFileTemplate.id.serviceInfoId = ? AND ";
	private static final String _FINDER_COLUMN_F_SERVICEINFOID_FILETEMPLATENO_FILETEMPLATENO_1 =
		"serviceFileTemplate.id.fileTemplateNo IS NULL";
	private static final String _FINDER_COLUMN_F_SERVICEINFOID_FILETEMPLATENO_FILETEMPLATENO_2 =
		"serviceFileTemplate.id.fileTemplateNo = ?";
	private static final String _FINDER_COLUMN_F_SERVICEINFOID_FILETEMPLATENO_FILETEMPLATENO_3 =
		"(serviceFileTemplate.id.fileTemplateNo IS NULL OR serviceFileTemplate.id.fileTemplateNo = '')";

	public ServiceFileTemplatePersistenceImpl() {
		setModelClass(ServiceFileTemplate.class);

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
	 * Caches the service file template in the entity cache if it is enabled.
	 *
	 * @param serviceFileTemplate the service file template
	 */
	@Override
	public void cacheResult(ServiceFileTemplate serviceFileTemplate) {
		entityCache.putResult(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			ServiceFileTemplateImpl.class, serviceFileTemplate.getPrimaryKey(),
			serviceFileTemplate);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_SERVICEINFOID_FILETEMPLATENO,
			new Object[] {
				serviceFileTemplate.getServiceInfoId(),
				serviceFileTemplate.getFileTemplateNo()
			}, serviceFileTemplate);

		serviceFileTemplate.resetOriginalValues();
	}

	/**
	 * Caches the service file templates in the entity cache if it is enabled.
	 *
	 * @param serviceFileTemplates the service file templates
	 */
	@Override
	public void cacheResult(List<ServiceFileTemplate> serviceFileTemplates) {
		for (ServiceFileTemplate serviceFileTemplate : serviceFileTemplates) {
			if (entityCache.getResult(
						ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
						ServiceFileTemplateImpl.class,
						serviceFileTemplate.getPrimaryKey()) == null) {
				cacheResult(serviceFileTemplate);
			}
			else {
				serviceFileTemplate.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all service file templates.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ServiceFileTemplateImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the service file template.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ServiceFileTemplate serviceFileTemplate) {
		entityCache.removeResult(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			ServiceFileTemplateImpl.class, serviceFileTemplate.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ServiceFileTemplateModelImpl)serviceFileTemplate,
			true);
	}

	@Override
	public void clearCache(List<ServiceFileTemplate> serviceFileTemplates) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ServiceFileTemplate serviceFileTemplate : serviceFileTemplates) {
			entityCache.removeResult(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
				ServiceFileTemplateImpl.class,
				serviceFileTemplate.getPrimaryKey());

			clearUniqueFindersCache((ServiceFileTemplateModelImpl)serviceFileTemplate,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ServiceFileTemplateModelImpl serviceFileTemplateModelImpl) {
		Object[] args = new Object[] {
				serviceFileTemplateModelImpl.getServiceInfoId(),
				serviceFileTemplateModelImpl.getFileTemplateNo()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_SERVICEINFOID_FILETEMPLATENO,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_SERVICEINFOID_FILETEMPLATENO,
			args, serviceFileTemplateModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ServiceFileTemplateModelImpl serviceFileTemplateModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					serviceFileTemplateModelImpl.getServiceInfoId(),
					serviceFileTemplateModelImpl.getFileTemplateNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_SERVICEINFOID_FILETEMPLATENO,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_SERVICEINFOID_FILETEMPLATENO,
				args);
		}

		if ((serviceFileTemplateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_SERVICEINFOID_FILETEMPLATENO.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					serviceFileTemplateModelImpl.getOriginalServiceInfoId(),
					serviceFileTemplateModelImpl.getOriginalFileTemplateNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_SERVICEINFOID_FILETEMPLATENO,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_SERVICEINFOID_FILETEMPLATENO,
				args);
		}
	}

	/**
	 * Creates a new service file template with the primary key. Does not add the service file template to the database.
	 *
	 * @param serviceFileTemplatePK the primary key for the new service file template
	 * @return the new service file template
	 */
	@Override
	public ServiceFileTemplate create(
		ServiceFileTemplatePK serviceFileTemplatePK) {
		ServiceFileTemplate serviceFileTemplate = new ServiceFileTemplateImpl();

		serviceFileTemplate.setNew(true);
		serviceFileTemplate.setPrimaryKey(serviceFileTemplatePK);

		String uuid = PortalUUIDUtil.generate();

		serviceFileTemplate.setUuid(uuid);

		return serviceFileTemplate;
	}

	/**
	 * Removes the service file template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceFileTemplatePK the primary key of the service file template
	 * @return the service file template that was removed
	 * @throws NoSuchServiceFileTemplateException if a service file template with the primary key could not be found
	 */
	@Override
	public ServiceFileTemplate remove(
		ServiceFileTemplatePK serviceFileTemplatePK)
		throws NoSuchServiceFileTemplateException {
		return remove((Serializable)serviceFileTemplatePK);
	}

	/**
	 * Removes the service file template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the service file template
	 * @return the service file template that was removed
	 * @throws NoSuchServiceFileTemplateException if a service file template with the primary key could not be found
	 */
	@Override
	public ServiceFileTemplate remove(Serializable primaryKey)
		throws NoSuchServiceFileTemplateException {
		Session session = null;

		try {
			session = openSession();

			ServiceFileTemplate serviceFileTemplate = (ServiceFileTemplate)session.get(ServiceFileTemplateImpl.class,
					primaryKey);

			if (serviceFileTemplate == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchServiceFileTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(serviceFileTemplate);
		}
		catch (NoSuchServiceFileTemplateException nsee) {
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
	protected ServiceFileTemplate removeImpl(
		ServiceFileTemplate serviceFileTemplate) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(serviceFileTemplate)) {
				serviceFileTemplate = (ServiceFileTemplate)session.get(ServiceFileTemplateImpl.class,
						serviceFileTemplate.getPrimaryKeyObj());
			}

			if (serviceFileTemplate != null) {
				session.delete(serviceFileTemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (serviceFileTemplate != null) {
			clearCache(serviceFileTemplate);
		}

		return serviceFileTemplate;
	}

	@Override
	public ServiceFileTemplate updateImpl(
		ServiceFileTemplate serviceFileTemplate) {
		boolean isNew = serviceFileTemplate.isNew();

		if (!(serviceFileTemplate instanceof ServiceFileTemplateModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(serviceFileTemplate.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(serviceFileTemplate);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in serviceFileTemplate proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ServiceFileTemplate implementation " +
				serviceFileTemplate.getClass());
		}

		ServiceFileTemplateModelImpl serviceFileTemplateModelImpl = (ServiceFileTemplateModelImpl)serviceFileTemplate;

		if (Validator.isNull(serviceFileTemplate.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			serviceFileTemplate.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (serviceFileTemplate.isNew()) {
				session.save(serviceFileTemplate);

				serviceFileTemplate.setNew(false);
			}
			else {
				serviceFileTemplate = (ServiceFileTemplate)session.merge(serviceFileTemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ServiceFileTemplateModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { serviceFileTemplateModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { serviceFileTemplateModelImpl.getServiceInfoId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SERVICEINFOID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICEINFOID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((serviceFileTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceFileTemplateModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { serviceFileTemplateModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((serviceFileTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICEINFOID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceFileTemplateModelImpl.getOriginalServiceInfoId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SERVICEINFOID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICEINFOID,
					args);

				args = new Object[] {
						serviceFileTemplateModelImpl.getServiceInfoId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SERVICEINFOID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICEINFOID,
					args);
			}
		}

		entityCache.putResult(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			ServiceFileTemplateImpl.class, serviceFileTemplate.getPrimaryKey(),
			serviceFileTemplate, false);

		clearUniqueFindersCache(serviceFileTemplateModelImpl, false);
		cacheUniqueFindersCache(serviceFileTemplateModelImpl);

		serviceFileTemplate.resetOriginalValues();

		return serviceFileTemplate;
	}

	/**
	 * Returns the service file template with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the service file template
	 * @return the service file template
	 * @throws NoSuchServiceFileTemplateException if a service file template with the primary key could not be found
	 */
	@Override
	public ServiceFileTemplate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchServiceFileTemplateException {
		ServiceFileTemplate serviceFileTemplate = fetchByPrimaryKey(primaryKey);

		if (serviceFileTemplate == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchServiceFileTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return serviceFileTemplate;
	}

	/**
	 * Returns the service file template with the primary key or throws a {@link NoSuchServiceFileTemplateException} if it could not be found.
	 *
	 * @param serviceFileTemplatePK the primary key of the service file template
	 * @return the service file template
	 * @throws NoSuchServiceFileTemplateException if a service file template with the primary key could not be found
	 */
	@Override
	public ServiceFileTemplate findByPrimaryKey(
		ServiceFileTemplatePK serviceFileTemplatePK)
		throws NoSuchServiceFileTemplateException {
		return findByPrimaryKey((Serializable)serviceFileTemplatePK);
	}

	/**
	 * Returns the service file template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the service file template
	 * @return the service file template, or <code>null</code> if a service file template with the primary key could not be found
	 */
	@Override
	public ServiceFileTemplate fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
				ServiceFileTemplateImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ServiceFileTemplate serviceFileTemplate = (ServiceFileTemplate)serializable;

		if (serviceFileTemplate == null) {
			Session session = null;

			try {
				session = openSession();

				serviceFileTemplate = (ServiceFileTemplate)session.get(ServiceFileTemplateImpl.class,
						primaryKey);

				if (serviceFileTemplate != null) {
					cacheResult(serviceFileTemplate);
				}
				else {
					entityCache.putResult(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
						ServiceFileTemplateImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ServiceFileTemplateModelImpl.ENTITY_CACHE_ENABLED,
					ServiceFileTemplateImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return serviceFileTemplate;
	}

	/**
	 * Returns the service file template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param serviceFileTemplatePK the primary key of the service file template
	 * @return the service file template, or <code>null</code> if a service file template with the primary key could not be found
	 */
	@Override
	public ServiceFileTemplate fetchByPrimaryKey(
		ServiceFileTemplatePK serviceFileTemplatePK) {
		return fetchByPrimaryKey((Serializable)serviceFileTemplatePK);
	}

	@Override
	public Map<Serializable, ServiceFileTemplate> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ServiceFileTemplate> map = new HashMap<Serializable, ServiceFileTemplate>();

		for (Serializable primaryKey : primaryKeys) {
			ServiceFileTemplate serviceFileTemplate = fetchByPrimaryKey(primaryKey);

			if (serviceFileTemplate != null) {
				map.put(primaryKey, serviceFileTemplate);
			}
		}

		return map;
	}

	/**
	 * Returns all the service file templates.
	 *
	 * @return the service file templates
	 */
	@Override
	public List<ServiceFileTemplate> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service file templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service file templates
	 * @param end the upper bound of the range of service file templates (not inclusive)
	 * @return the range of service file templates
	 */
	@Override
	public List<ServiceFileTemplate> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the service file templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service file templates
	 * @param end the upper bound of the range of service file templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of service file templates
	 */
	@Override
	public List<ServiceFileTemplate> findAll(int start, int end,
		OrderByComparator<ServiceFileTemplate> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service file templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service file templates
	 * @param end the upper bound of the range of service file templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of service file templates
	 */
	@Override
	public List<ServiceFileTemplate> findAll(int start, int end,
		OrderByComparator<ServiceFileTemplate> orderByComparator,
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

		List<ServiceFileTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceFileTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SERVICEFILETEMPLATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SERVICEFILETEMPLATE;

				if (pagination) {
					sql = sql.concat(ServiceFileTemplateModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ServiceFileTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceFileTemplate>)QueryUtil.list(q,
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
	 * Removes all the service file templates from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ServiceFileTemplate serviceFileTemplate : findAll()) {
			remove(serviceFileTemplate);
		}
	}

	/**
	 * Returns the number of service file templates.
	 *
	 * @return the number of service file templates
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SERVICEFILETEMPLATE);

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
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ServiceFileTemplateModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the service file template persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ServiceFileTemplateImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SERVICEFILETEMPLATE = "SELECT serviceFileTemplate FROM ServiceFileTemplate serviceFileTemplate";
	private static final String _SQL_SELECT_SERVICEFILETEMPLATE_WHERE = "SELECT serviceFileTemplate FROM ServiceFileTemplate serviceFileTemplate WHERE ";
	private static final String _SQL_COUNT_SERVICEFILETEMPLATE = "SELECT COUNT(serviceFileTemplate) FROM ServiceFileTemplate serviceFileTemplate";
	private static final String _SQL_COUNT_SERVICEFILETEMPLATE_WHERE = "SELECT COUNT(serviceFileTemplate) FROM ServiceFileTemplate serviceFileTemplate WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "serviceFileTemplate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ServiceFileTemplate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ServiceFileTemplate exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ServiceFileTemplatePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(new String[] {
				"serviceInfoId", "fileTemplateNo"
			});
}