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

import org.opencps.dossiermgt.exception.NoSuchServiceInfoMappingException;
import org.opencps.dossiermgt.model.ServiceInfoMapping;
import org.opencps.dossiermgt.model.impl.ServiceInfoMappingImpl;
import org.opencps.dossiermgt.model.impl.ServiceInfoMappingModelImpl;
import org.opencps.dossiermgt.service.persistence.ServiceInfoMappingPersistence;

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
 * The persistence implementation for the service info mapping service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ServiceInfoMappingPersistence
 * @see org.opencps.dossiermgt.service.persistence.ServiceInfoMappingUtil
 * @generated
 */
@ProviderType
public class ServiceInfoMappingPersistenceImpl extends BasePersistenceImpl<ServiceInfoMapping>
	implements ServiceInfoMappingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ServiceInfoMappingUtil} to access the service info mapping persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ServiceInfoMappingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoMappingModelImpl.FINDER_CACHE_ENABLED,
			ServiceInfoMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoMappingModelImpl.FINDER_CACHE_ENABLED,
			ServiceInfoMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_SC = new FinderPath(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoMappingModelImpl.FINDER_CACHE_ENABLED,
			ServiceInfoMappingImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_GID_SC",
			new String[] { Long.class.getName(), String.class.getName() },
			ServiceInfoMappingModelImpl.GROUPID_COLUMN_BITMASK |
			ServiceInfoMappingModelImpl.SERVICECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_SC = new FinderPath(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_SC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the service info mapping where groupId = &#63; and serviceCode = &#63; or throws a {@link NoSuchServiceInfoMappingException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @return the matching service info mapping
	 * @throws NoSuchServiceInfoMappingException if a matching service info mapping could not be found
	 */
	@Override
	public ServiceInfoMapping findByF_GID_SC(long groupId, String serviceCode)
		throws NoSuchServiceInfoMappingException {
		ServiceInfoMapping serviceInfoMapping = fetchByF_GID_SC(groupId,
				serviceCode);

		if (serviceInfoMapping == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", serviceCode=");
			msg.append(serviceCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchServiceInfoMappingException(msg.toString());
		}

		return serviceInfoMapping;
	}

	/**
	 * Returns the service info mapping where groupId = &#63; and serviceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @return the matching service info mapping, or <code>null</code> if a matching service info mapping could not be found
	 */
	@Override
	public ServiceInfoMapping fetchByF_GID_SC(long groupId, String serviceCode) {
		return fetchByF_GID_SC(groupId, serviceCode, true);
	}

	/**
	 * Returns the service info mapping where groupId = &#63; and serviceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching service info mapping, or <code>null</code> if a matching service info mapping could not be found
	 */
	@Override
	public ServiceInfoMapping fetchByF_GID_SC(long groupId, String serviceCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, serviceCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_SC,
					finderArgs, this);
		}

		if (result instanceof ServiceInfoMapping) {
			ServiceInfoMapping serviceInfoMapping = (ServiceInfoMapping)result;

			if ((groupId != serviceInfoMapping.getGroupId()) ||
					!Objects.equals(serviceCode,
						serviceInfoMapping.getServiceCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SERVICEINFOMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SC_GROUPID_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				List<ServiceInfoMapping> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_SC,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ServiceInfoMappingPersistenceImpl.fetchByF_GID_SC(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ServiceInfoMapping serviceInfoMapping = list.get(0);

					result = serviceInfoMapping;

					cacheResult(serviceInfoMapping);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_SC,
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
			return (ServiceInfoMapping)result;
		}
	}

	/**
	 * Removes the service info mapping where groupId = &#63; and serviceCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @return the service info mapping that was removed
	 */
	@Override
	public ServiceInfoMapping removeByF_GID_SC(long groupId, String serviceCode)
		throws NoSuchServiceInfoMappingException {
		ServiceInfoMapping serviceInfoMapping = findByF_GID_SC(groupId,
				serviceCode);

		return remove(serviceInfoMapping);
	}

	/**
	 * Returns the number of service info mappings where groupId = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @return the number of matching service info mappings
	 */
	@Override
	public int countByF_GID_SC(long groupId, String serviceCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_SC;

		Object[] finderArgs = new Object[] { groupId, serviceCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICEINFOMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SC_GROUPID_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_F_GID_SC_SERVICECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServiceCode) {
					qPos.add(serviceCode);
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

	private static final String _FINDER_COLUMN_F_GID_SC_GROUPID_2 = "serviceInfoMapping.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SC_SERVICECODE_1 = "serviceInfoMapping.serviceCode IS NULL";
	private static final String _FINDER_COLUMN_F_GID_SC_SERVICECODE_2 = "serviceInfoMapping.serviceCode = ?";
	private static final String _FINDER_COLUMN_F_GID_SC_SERVICECODE_3 = "(serviceInfoMapping.serviceCode IS NULL OR serviceInfoMapping.serviceCode = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_SCDVCQG = new FinderPath(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoMappingModelImpl.FINDER_CACHE_ENABLED,
			ServiceInfoMappingImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_GID_SCDVCQG",
			new String[] { Long.class.getName(), String.class.getName() },
			ServiceInfoMappingModelImpl.GROUPID_COLUMN_BITMASK |
			ServiceInfoMappingModelImpl.SERVICECODEDVCQG_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_SCDVCQG = new FinderPath(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_SCDVCQG",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the service info mapping where groupId = &#63; and serviceCodeDVCQG = &#63; or throws a {@link NoSuchServiceInfoMappingException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param serviceCodeDVCQG the service code dvcqg
	 * @return the matching service info mapping
	 * @throws NoSuchServiceInfoMappingException if a matching service info mapping could not be found
	 */
	@Override
	public ServiceInfoMapping findByF_GID_SCDVCQG(long groupId,
		String serviceCodeDVCQG) throws NoSuchServiceInfoMappingException {
		ServiceInfoMapping serviceInfoMapping = fetchByF_GID_SCDVCQG(groupId,
				serviceCodeDVCQG);

		if (serviceInfoMapping == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", serviceCodeDVCQG=");
			msg.append(serviceCodeDVCQG);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchServiceInfoMappingException(msg.toString());
		}

		return serviceInfoMapping;
	}

	/**
	 * Returns the service info mapping where groupId = &#63; and serviceCodeDVCQG = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param serviceCodeDVCQG the service code dvcqg
	 * @return the matching service info mapping, or <code>null</code> if a matching service info mapping could not be found
	 */
	@Override
	public ServiceInfoMapping fetchByF_GID_SCDVCQG(long groupId,
		String serviceCodeDVCQG) {
		return fetchByF_GID_SCDVCQG(groupId, serviceCodeDVCQG, true);
	}

	/**
	 * Returns the service info mapping where groupId = &#63; and serviceCodeDVCQG = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param serviceCodeDVCQG the service code dvcqg
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching service info mapping, or <code>null</code> if a matching service info mapping could not be found
	 */
	@Override
	public ServiceInfoMapping fetchByF_GID_SCDVCQG(long groupId,
		String serviceCodeDVCQG, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, serviceCodeDVCQG };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_SCDVCQG,
					finderArgs, this);
		}

		if (result instanceof ServiceInfoMapping) {
			ServiceInfoMapping serviceInfoMapping = (ServiceInfoMapping)result;

			if ((groupId != serviceInfoMapping.getGroupId()) ||
					!Objects.equals(serviceCodeDVCQG,
						serviceInfoMapping.getServiceCodeDVCQG())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SERVICEINFOMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SCDVCQG_GROUPID_2);

			boolean bindServiceCodeDVCQG = false;

			if (serviceCodeDVCQG == null) {
				query.append(_FINDER_COLUMN_F_GID_SCDVCQG_SERVICECODEDVCQG_1);
			}
			else if (serviceCodeDVCQG.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SCDVCQG_SERVICECODEDVCQG_3);
			}
			else {
				bindServiceCodeDVCQG = true;

				query.append(_FINDER_COLUMN_F_GID_SCDVCQG_SERVICECODEDVCQG_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServiceCodeDVCQG) {
					qPos.add(serviceCodeDVCQG);
				}

				List<ServiceInfoMapping> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_SCDVCQG,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ServiceInfoMappingPersistenceImpl.fetchByF_GID_SCDVCQG(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ServiceInfoMapping serviceInfoMapping = list.get(0);

					result = serviceInfoMapping;

					cacheResult(serviceInfoMapping);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_SCDVCQG,
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
			return (ServiceInfoMapping)result;
		}
	}

	/**
	 * Removes the service info mapping where groupId = &#63; and serviceCodeDVCQG = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceCodeDVCQG the service code dvcqg
	 * @return the service info mapping that was removed
	 */
	@Override
	public ServiceInfoMapping removeByF_GID_SCDVCQG(long groupId,
		String serviceCodeDVCQG) throws NoSuchServiceInfoMappingException {
		ServiceInfoMapping serviceInfoMapping = findByF_GID_SCDVCQG(groupId,
				serviceCodeDVCQG);

		return remove(serviceInfoMapping);
	}

	/**
	 * Returns the number of service info mappings where groupId = &#63; and serviceCodeDVCQG = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCodeDVCQG the service code dvcqg
	 * @return the number of matching service info mappings
	 */
	@Override
	public int countByF_GID_SCDVCQG(long groupId, String serviceCodeDVCQG) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_SCDVCQG;

		Object[] finderArgs = new Object[] { groupId, serviceCodeDVCQG };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICEINFOMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SCDVCQG_GROUPID_2);

			boolean bindServiceCodeDVCQG = false;

			if (serviceCodeDVCQG == null) {
				query.append(_FINDER_COLUMN_F_GID_SCDVCQG_SERVICECODEDVCQG_1);
			}
			else if (serviceCodeDVCQG.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SCDVCQG_SERVICECODEDVCQG_3);
			}
			else {
				bindServiceCodeDVCQG = true;

				query.append(_FINDER_COLUMN_F_GID_SCDVCQG_SERVICECODEDVCQG_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServiceCodeDVCQG) {
					qPos.add(serviceCodeDVCQG);
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

	private static final String _FINDER_COLUMN_F_GID_SCDVCQG_GROUPID_2 = "serviceInfoMapping.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SCDVCQG_SERVICECODEDVCQG_1 = "serviceInfoMapping.serviceCodeDVCQG IS NULL";
	private static final String _FINDER_COLUMN_F_GID_SCDVCQG_SERVICECODEDVCQG_2 = "serviceInfoMapping.serviceCodeDVCQG = ?";
	private static final String _FINDER_COLUMN_F_GID_SCDVCQG_SERVICECODEDVCQG_3 = "(serviceInfoMapping.serviceCodeDVCQG IS NULL OR serviceInfoMapping.serviceCodeDVCQG = '')";

	public ServiceInfoMappingPersistenceImpl() {
		setModelClass(ServiceInfoMapping.class);
	}

	/**
	 * Caches the service info mapping in the entity cache if it is enabled.
	 *
	 * @param serviceInfoMapping the service info mapping
	 */
	@Override
	public void cacheResult(ServiceInfoMapping serviceInfoMapping) {
		entityCache.putResult(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoMappingImpl.class, serviceInfoMapping.getPrimaryKey(),
			serviceInfoMapping);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_SC,
			new Object[] {
				serviceInfoMapping.getGroupId(),
				serviceInfoMapping.getServiceCode()
			}, serviceInfoMapping);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_SCDVCQG,
			new Object[] {
				serviceInfoMapping.getGroupId(),
				serviceInfoMapping.getServiceCodeDVCQG()
			}, serviceInfoMapping);

		serviceInfoMapping.resetOriginalValues();
	}

	/**
	 * Caches the service info mappings in the entity cache if it is enabled.
	 *
	 * @param serviceInfoMappings the service info mappings
	 */
	@Override
	public void cacheResult(List<ServiceInfoMapping> serviceInfoMappings) {
		for (ServiceInfoMapping serviceInfoMapping : serviceInfoMappings) {
			if (entityCache.getResult(
						ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
						ServiceInfoMappingImpl.class,
						serviceInfoMapping.getPrimaryKey()) == null) {
				cacheResult(serviceInfoMapping);
			}
			else {
				serviceInfoMapping.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all service info mappings.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ServiceInfoMappingImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the service info mapping.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ServiceInfoMapping serviceInfoMapping) {
		entityCache.removeResult(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoMappingImpl.class, serviceInfoMapping.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ServiceInfoMappingModelImpl)serviceInfoMapping,
			true);
	}

	@Override
	public void clearCache(List<ServiceInfoMapping> serviceInfoMappings) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ServiceInfoMapping serviceInfoMapping : serviceInfoMappings) {
			entityCache.removeResult(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
				ServiceInfoMappingImpl.class, serviceInfoMapping.getPrimaryKey());

			clearUniqueFindersCache((ServiceInfoMappingModelImpl)serviceInfoMapping,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ServiceInfoMappingModelImpl serviceInfoMappingModelImpl) {
		Object[] args = new Object[] {
				serviceInfoMappingModelImpl.getGroupId(),
				serviceInfoMappingModelImpl.getServiceCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_SC, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_SC, args,
			serviceInfoMappingModelImpl, false);

		args = new Object[] {
				serviceInfoMappingModelImpl.getGroupId(),
				serviceInfoMappingModelImpl.getServiceCodeDVCQG()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_SCDVCQG, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_SCDVCQG, args,
			serviceInfoMappingModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ServiceInfoMappingModelImpl serviceInfoMappingModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					serviceInfoMappingModelImpl.getGroupId(),
					serviceInfoMappingModelImpl.getServiceCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_SC, args);
		}

		if ((serviceInfoMappingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_SC.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					serviceInfoMappingModelImpl.getOriginalGroupId(),
					serviceInfoMappingModelImpl.getOriginalServiceCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_SC, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					serviceInfoMappingModelImpl.getGroupId(),
					serviceInfoMappingModelImpl.getServiceCodeDVCQG()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SCDVCQG, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_SCDVCQG, args);
		}

		if ((serviceInfoMappingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_SCDVCQG.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					serviceInfoMappingModelImpl.getOriginalGroupId(),
					serviceInfoMappingModelImpl.getOriginalServiceCodeDVCQG()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SCDVCQG, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_SCDVCQG, args);
		}
	}

	/**
	 * Creates a new service info mapping with the primary key. Does not add the service info mapping to the database.
	 *
	 * @param serviceInfoMappingId the primary key for the new service info mapping
	 * @return the new service info mapping
	 */
	@Override
	public ServiceInfoMapping create(long serviceInfoMappingId) {
		ServiceInfoMapping serviceInfoMapping = new ServiceInfoMappingImpl();

		serviceInfoMapping.setNew(true);
		serviceInfoMapping.setPrimaryKey(serviceInfoMappingId);

		serviceInfoMapping.setCompanyId(companyProvider.getCompanyId());

		return serviceInfoMapping;
	}

	/**
	 * Removes the service info mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceInfoMappingId the primary key of the service info mapping
	 * @return the service info mapping that was removed
	 * @throws NoSuchServiceInfoMappingException if a service info mapping with the primary key could not be found
	 */
	@Override
	public ServiceInfoMapping remove(long serviceInfoMappingId)
		throws NoSuchServiceInfoMappingException {
		return remove((Serializable)serviceInfoMappingId);
	}

	/**
	 * Removes the service info mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the service info mapping
	 * @return the service info mapping that was removed
	 * @throws NoSuchServiceInfoMappingException if a service info mapping with the primary key could not be found
	 */
	@Override
	public ServiceInfoMapping remove(Serializable primaryKey)
		throws NoSuchServiceInfoMappingException {
		Session session = null;

		try {
			session = openSession();

			ServiceInfoMapping serviceInfoMapping = (ServiceInfoMapping)session.get(ServiceInfoMappingImpl.class,
					primaryKey);

			if (serviceInfoMapping == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchServiceInfoMappingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(serviceInfoMapping);
		}
		catch (NoSuchServiceInfoMappingException nsee) {
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
	protected ServiceInfoMapping removeImpl(
		ServiceInfoMapping serviceInfoMapping) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(serviceInfoMapping)) {
				serviceInfoMapping = (ServiceInfoMapping)session.get(ServiceInfoMappingImpl.class,
						serviceInfoMapping.getPrimaryKeyObj());
			}

			if (serviceInfoMapping != null) {
				session.delete(serviceInfoMapping);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (serviceInfoMapping != null) {
			clearCache(serviceInfoMapping);
		}

		return serviceInfoMapping;
	}

	@Override
	public ServiceInfoMapping updateImpl(ServiceInfoMapping serviceInfoMapping) {
		boolean isNew = serviceInfoMapping.isNew();

		if (!(serviceInfoMapping instanceof ServiceInfoMappingModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(serviceInfoMapping.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(serviceInfoMapping);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in serviceInfoMapping proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ServiceInfoMapping implementation " +
				serviceInfoMapping.getClass());
		}

		ServiceInfoMappingModelImpl serviceInfoMappingModelImpl = (ServiceInfoMappingModelImpl)serviceInfoMapping;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (serviceInfoMapping.getCreateDate() == null)) {
			if (serviceContext == null) {
				serviceInfoMapping.setCreateDate(now);
			}
			else {
				serviceInfoMapping.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!serviceInfoMappingModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				serviceInfoMapping.setModifiedDate(now);
			}
			else {
				serviceInfoMapping.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (serviceInfoMapping.isNew()) {
				session.save(serviceInfoMapping);

				serviceInfoMapping.setNew(false);
			}
			else {
				serviceInfoMapping = (ServiceInfoMapping)session.merge(serviceInfoMapping);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ServiceInfoMappingModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoMappingImpl.class, serviceInfoMapping.getPrimaryKey(),
			serviceInfoMapping, false);

		clearUniqueFindersCache(serviceInfoMappingModelImpl, false);
		cacheUniqueFindersCache(serviceInfoMappingModelImpl);

		serviceInfoMapping.resetOriginalValues();

		return serviceInfoMapping;
	}

	/**
	 * Returns the service info mapping with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the service info mapping
	 * @return the service info mapping
	 * @throws NoSuchServiceInfoMappingException if a service info mapping with the primary key could not be found
	 */
	@Override
	public ServiceInfoMapping findByPrimaryKey(Serializable primaryKey)
		throws NoSuchServiceInfoMappingException {
		ServiceInfoMapping serviceInfoMapping = fetchByPrimaryKey(primaryKey);

		if (serviceInfoMapping == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchServiceInfoMappingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return serviceInfoMapping;
	}

	/**
	 * Returns the service info mapping with the primary key or throws a {@link NoSuchServiceInfoMappingException} if it could not be found.
	 *
	 * @param serviceInfoMappingId the primary key of the service info mapping
	 * @return the service info mapping
	 * @throws NoSuchServiceInfoMappingException if a service info mapping with the primary key could not be found
	 */
	@Override
	public ServiceInfoMapping findByPrimaryKey(long serviceInfoMappingId)
		throws NoSuchServiceInfoMappingException {
		return findByPrimaryKey((Serializable)serviceInfoMappingId);
	}

	/**
	 * Returns the service info mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the service info mapping
	 * @return the service info mapping, or <code>null</code> if a service info mapping with the primary key could not be found
	 */
	@Override
	public ServiceInfoMapping fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
				ServiceInfoMappingImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ServiceInfoMapping serviceInfoMapping = (ServiceInfoMapping)serializable;

		if (serviceInfoMapping == null) {
			Session session = null;

			try {
				session = openSession();

				serviceInfoMapping = (ServiceInfoMapping)session.get(ServiceInfoMappingImpl.class,
						primaryKey);

				if (serviceInfoMapping != null) {
					cacheResult(serviceInfoMapping);
				}
				else {
					entityCache.putResult(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
						ServiceInfoMappingImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
					ServiceInfoMappingImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return serviceInfoMapping;
	}

	/**
	 * Returns the service info mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param serviceInfoMappingId the primary key of the service info mapping
	 * @return the service info mapping, or <code>null</code> if a service info mapping with the primary key could not be found
	 */
	@Override
	public ServiceInfoMapping fetchByPrimaryKey(long serviceInfoMappingId) {
		return fetchByPrimaryKey((Serializable)serviceInfoMappingId);
	}

	@Override
	public Map<Serializable, ServiceInfoMapping> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ServiceInfoMapping> map = new HashMap<Serializable, ServiceInfoMapping>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ServiceInfoMapping serviceInfoMapping = fetchByPrimaryKey(primaryKey);

			if (serviceInfoMapping != null) {
				map.put(primaryKey, serviceInfoMapping);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
					ServiceInfoMappingImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ServiceInfoMapping)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SERVICEINFOMAPPING_WHERE_PKS_IN);

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

			for (ServiceInfoMapping serviceInfoMapping : (List<ServiceInfoMapping>)q.list()) {
				map.put(serviceInfoMapping.getPrimaryKeyObj(),
					serviceInfoMapping);

				cacheResult(serviceInfoMapping);

				uncachedPrimaryKeys.remove(serviceInfoMapping.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ServiceInfoMappingModelImpl.ENTITY_CACHE_ENABLED,
					ServiceInfoMappingImpl.class, primaryKey, nullModel);
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
	 * Returns all the service info mappings.
	 *
	 * @return the service info mappings
	 */
	@Override
	public List<ServiceInfoMapping> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service info mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service info mappings
	 * @param end the upper bound of the range of service info mappings (not inclusive)
	 * @return the range of service info mappings
	 */
	@Override
	public List<ServiceInfoMapping> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the service info mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service info mappings
	 * @param end the upper bound of the range of service info mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of service info mappings
	 */
	@Override
	public List<ServiceInfoMapping> findAll(int start, int end,
		OrderByComparator<ServiceInfoMapping> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service info mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service info mappings
	 * @param end the upper bound of the range of service info mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of service info mappings
	 */
	@Override
	public List<ServiceInfoMapping> findAll(int start, int end,
		OrderByComparator<ServiceInfoMapping> orderByComparator,
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

		List<ServiceInfoMapping> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceInfoMapping>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SERVICEINFOMAPPING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SERVICEINFOMAPPING;

				if (pagination) {
					sql = sql.concat(ServiceInfoMappingModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ServiceInfoMapping>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceInfoMapping>)QueryUtil.list(q,
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
	 * Removes all the service info mappings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ServiceInfoMapping serviceInfoMapping : findAll()) {
			remove(serviceInfoMapping);
		}
	}

	/**
	 * Returns the number of service info mappings.
	 *
	 * @return the number of service info mappings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SERVICEINFOMAPPING);

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
		return ServiceInfoMappingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the service info mapping persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ServiceInfoMappingImpl.class.getName());
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
	private static final String _SQL_SELECT_SERVICEINFOMAPPING = "SELECT serviceInfoMapping FROM ServiceInfoMapping serviceInfoMapping";
	private static final String _SQL_SELECT_SERVICEINFOMAPPING_WHERE_PKS_IN = "SELECT serviceInfoMapping FROM ServiceInfoMapping serviceInfoMapping WHERE serviceInfoMappingId IN (";
	private static final String _SQL_SELECT_SERVICEINFOMAPPING_WHERE = "SELECT serviceInfoMapping FROM ServiceInfoMapping serviceInfoMapping WHERE ";
	private static final String _SQL_COUNT_SERVICEINFOMAPPING = "SELECT COUNT(serviceInfoMapping) FROM ServiceInfoMapping serviceInfoMapping";
	private static final String _SQL_COUNT_SERVICEINFOMAPPING_WHERE = "SELECT COUNT(serviceInfoMapping) FROM ServiceInfoMapping serviceInfoMapping WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "serviceInfoMapping.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ServiceInfoMapping exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ServiceInfoMapping exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ServiceInfoMappingPersistenceImpl.class);
}