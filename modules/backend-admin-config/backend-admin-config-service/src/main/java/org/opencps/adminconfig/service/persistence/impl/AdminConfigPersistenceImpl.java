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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.adminconfig.exception.NoSuchAdminConfigException;
import org.opencps.adminconfig.model.AdminConfig;
import org.opencps.adminconfig.model.impl.AdminConfigImpl;
import org.opencps.adminconfig.model.impl.AdminConfigModelImpl;
import org.opencps.adminconfig.service.persistence.AdminConfigPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the admin config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see AdminConfigPersistence
 * @see org.opencps.adminconfig.service.persistence.AdminConfigUtil
 * @generated
 */
@ProviderType
public class AdminConfigPersistenceImpl extends BasePersistenceImpl<AdminConfig>
	implements AdminConfigPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AdminConfigUtil} to access the admin config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AdminConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
			AdminConfigModelImpl.FINDER_CACHE_ENABLED, AdminConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
			AdminConfigModelImpl.FINDER_CACHE_ENABLED, AdminConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
			AdminConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_F_CODE = new FinderPath(AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
			AdminConfigModelImpl.FINDER_CACHE_ENABLED, AdminConfigImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_Code",
			new String[] { String.class.getName() },
			AdminConfigModelImpl.CODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_CODE = new FinderPath(AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
			AdminConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_Code",
			new String[] { String.class.getName() });

	/**
	 * Returns the admin config where code = &#63; or throws a {@link NoSuchAdminConfigException} if it could not be found.
	 *
	 * @param code the code
	 * @return the matching admin config
	 * @throws NoSuchAdminConfigException if a matching admin config could not be found
	 */
	@Override
	public AdminConfig findByF_Code(String code)
		throws NoSuchAdminConfigException {
		AdminConfig adminConfig = fetchByF_Code(code);

		if (adminConfig == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("code=");
			msg.append(code);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAdminConfigException(msg.toString());
		}

		return adminConfig;
	}

	/**
	 * Returns the admin config where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param code the code
	 * @return the matching admin config, or <code>null</code> if a matching admin config could not be found
	 */
	@Override
	public AdminConfig fetchByF_Code(String code) {
		return fetchByF_Code(code, true);
	}

	/**
	 * Returns the admin config where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param code the code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching admin config, or <code>null</code> if a matching admin config could not be found
	 */
	@Override
	public AdminConfig fetchByF_Code(String code, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { code };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_CODE,
					finderArgs, this);
		}

		if (result instanceof AdminConfig) {
			AdminConfig adminConfig = (AdminConfig)result;

			if (!Objects.equals(code, adminConfig.getCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ADMINCONFIG_WHERE);

			boolean bindCode = false;

			if (code == null) {
				query.append(_FINDER_COLUMN_F_CODE_CODE_1);
			}
			else if (code.equals("")) {
				query.append(_FINDER_COLUMN_F_CODE_CODE_3);
			}
			else {
				bindCode = true;

				query.append(_FINDER_COLUMN_F_CODE_CODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCode) {
					qPos.add(code);
				}

				List<AdminConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"AdminConfigPersistenceImpl.fetchByF_Code(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AdminConfig adminConfig = list.get(0);

					result = adminConfig;

					cacheResult(adminConfig);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CODE, finderArgs);

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
			return (AdminConfig)result;
		}
	}

	/**
	 * Removes the admin config where code = &#63; from the database.
	 *
	 * @param code the code
	 * @return the admin config that was removed
	 */
	@Override
	public AdminConfig removeByF_Code(String code)
		throws NoSuchAdminConfigException {
		AdminConfig adminConfig = findByF_Code(code);

		return remove(adminConfig);
	}

	/**
	 * Returns the number of admin configs where code = &#63;.
	 *
	 * @param code the code
	 * @return the number of matching admin configs
	 */
	@Override
	public int countByF_Code(String code) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_CODE;

		Object[] finderArgs = new Object[] { code };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ADMINCONFIG_WHERE);

			boolean bindCode = false;

			if (code == null) {
				query.append(_FINDER_COLUMN_F_CODE_CODE_1);
			}
			else if (code.equals("")) {
				query.append(_FINDER_COLUMN_F_CODE_CODE_3);
			}
			else {
				bindCode = true;

				query.append(_FINDER_COLUMN_F_CODE_CODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCode) {
					qPos.add(code);
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

	private static final String _FINDER_COLUMN_F_CODE_CODE_1 = "adminConfig.code IS NULL";
	private static final String _FINDER_COLUMN_F_CODE_CODE_2 = "adminConfig.code = ?";
	private static final String _FINDER_COLUMN_F_CODE_CODE_3 = "(adminConfig.code IS NULL OR adminConfig.code = '')";

	public AdminConfigPersistenceImpl() {
		setModelClass(AdminConfig.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("id", "id_");
			dbColumnNames.put("code", "code_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the admin config in the entity cache if it is enabled.
	 *
	 * @param adminConfig the admin config
	 */
	@Override
	public void cacheResult(AdminConfig adminConfig) {
		entityCache.putResult(AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
			AdminConfigImpl.class, adminConfig.getPrimaryKey(), adminConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE,
			new Object[] { adminConfig.getCode() }, adminConfig);

		adminConfig.resetOriginalValues();
	}

	/**
	 * Caches the admin configs in the entity cache if it is enabled.
	 *
	 * @param adminConfigs the admin configs
	 */
	@Override
	public void cacheResult(List<AdminConfig> adminConfigs) {
		for (AdminConfig adminConfig : adminConfigs) {
			if (entityCache.getResult(
						AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
						AdminConfigImpl.class, adminConfig.getPrimaryKey()) == null) {
				cacheResult(adminConfig);
			}
			else {
				adminConfig.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all admin configs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AdminConfigImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the admin config.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AdminConfig adminConfig) {
		entityCache.removeResult(AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
			AdminConfigImpl.class, adminConfig.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AdminConfigModelImpl)adminConfig, true);
	}

	@Override
	public void clearCache(List<AdminConfig> adminConfigs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AdminConfig adminConfig : adminConfigs) {
			entityCache.removeResult(AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
				AdminConfigImpl.class, adminConfig.getPrimaryKey());

			clearUniqueFindersCache((AdminConfigModelImpl)adminConfig, true);
		}
	}

	protected void cacheUniqueFindersCache(
		AdminConfigModelImpl adminConfigModelImpl) {
		Object[] args = new Object[] { adminConfigModelImpl.getCode() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_CODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE, args,
			adminConfigModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AdminConfigModelImpl adminConfigModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { adminConfigModelImpl.getCode() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CODE, args);
		}

		if ((adminConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_CODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { adminConfigModelImpl.getOriginalCode() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CODE, args);
		}
	}

	/**
	 * Creates a new admin config with the primary key. Does not add the admin config to the database.
	 *
	 * @param id the primary key for the new admin config
	 * @return the new admin config
	 */
	@Override
	public AdminConfig create(long id) {
		AdminConfig adminConfig = new AdminConfigImpl();

		adminConfig.setNew(true);
		adminConfig.setPrimaryKey(id);

		return adminConfig;
	}

	/**
	 * Removes the admin config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the admin config
	 * @return the admin config that was removed
	 * @throws NoSuchAdminConfigException if a admin config with the primary key could not be found
	 */
	@Override
	public AdminConfig remove(long id) throws NoSuchAdminConfigException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the admin config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the admin config
	 * @return the admin config that was removed
	 * @throws NoSuchAdminConfigException if a admin config with the primary key could not be found
	 */
	@Override
	public AdminConfig remove(Serializable primaryKey)
		throws NoSuchAdminConfigException {
		Session session = null;

		try {
			session = openSession();

			AdminConfig adminConfig = (AdminConfig)session.get(AdminConfigImpl.class,
					primaryKey);

			if (adminConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAdminConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(adminConfig);
		}
		catch (NoSuchAdminConfigException nsee) {
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
	protected AdminConfig removeImpl(AdminConfig adminConfig) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(adminConfig)) {
				adminConfig = (AdminConfig)session.get(AdminConfigImpl.class,
						adminConfig.getPrimaryKeyObj());
			}

			if (adminConfig != null) {
				session.delete(adminConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (adminConfig != null) {
			clearCache(adminConfig);
		}

		return adminConfig;
	}

	@Override
	public AdminConfig updateImpl(AdminConfig adminConfig) {
		boolean isNew = adminConfig.isNew();

		if (!(adminConfig instanceof AdminConfigModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(adminConfig.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(adminConfig);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in adminConfig proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AdminConfig implementation " +
				adminConfig.getClass());
		}

		AdminConfigModelImpl adminConfigModelImpl = (AdminConfigModelImpl)adminConfig;

		Session session = null;

		try {
			session = openSession();

			if (adminConfig.isNew()) {
				session.save(adminConfig);

				adminConfig.setNew(false);
			}
			else {
				adminConfig = (AdminConfig)session.merge(adminConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AdminConfigModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
			AdminConfigImpl.class, adminConfig.getPrimaryKey(), adminConfig,
			false);

		clearUniqueFindersCache(adminConfigModelImpl, false);
		cacheUniqueFindersCache(adminConfigModelImpl);

		adminConfig.resetOriginalValues();

		return adminConfig;
	}

	/**
	 * Returns the admin config with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the admin config
	 * @return the admin config
	 * @throws NoSuchAdminConfigException if a admin config with the primary key could not be found
	 */
	@Override
	public AdminConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAdminConfigException {
		AdminConfig adminConfig = fetchByPrimaryKey(primaryKey);

		if (adminConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAdminConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return adminConfig;
	}

	/**
	 * Returns the admin config with the primary key or throws a {@link NoSuchAdminConfigException} if it could not be found.
	 *
	 * @param id the primary key of the admin config
	 * @return the admin config
	 * @throws NoSuchAdminConfigException if a admin config with the primary key could not be found
	 */
	@Override
	public AdminConfig findByPrimaryKey(long id)
		throws NoSuchAdminConfigException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the admin config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the admin config
	 * @return the admin config, or <code>null</code> if a admin config with the primary key could not be found
	 */
	@Override
	public AdminConfig fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
				AdminConfigImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AdminConfig adminConfig = (AdminConfig)serializable;

		if (adminConfig == null) {
			Session session = null;

			try {
				session = openSession();

				adminConfig = (AdminConfig)session.get(AdminConfigImpl.class,
						primaryKey);

				if (adminConfig != null) {
					cacheResult(adminConfig);
				}
				else {
					entityCache.putResult(AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
						AdminConfigImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
					AdminConfigImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return adminConfig;
	}

	/**
	 * Returns the admin config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the admin config
	 * @return the admin config, or <code>null</code> if a admin config with the primary key could not be found
	 */
	@Override
	public AdminConfig fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, AdminConfig> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AdminConfig> map = new HashMap<Serializable, AdminConfig>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AdminConfig adminConfig = fetchByPrimaryKey(primaryKey);

			if (adminConfig != null) {
				map.put(primaryKey, adminConfig);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
					AdminConfigImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AdminConfig)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ADMINCONFIG_WHERE_PKS_IN);

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

			for (AdminConfig adminConfig : (List<AdminConfig>)q.list()) {
				map.put(adminConfig.getPrimaryKeyObj(), adminConfig);

				cacheResult(adminConfig);

				uncachedPrimaryKeys.remove(adminConfig.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AdminConfigModelImpl.ENTITY_CACHE_ENABLED,
					AdminConfigImpl.class, primaryKey, nullModel);
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
	 * Returns all the admin configs.
	 *
	 * @return the admin configs
	 */
	@Override
	public List<AdminConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the admin configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdminConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of admin configs
	 * @param end the upper bound of the range of admin configs (not inclusive)
	 * @return the range of admin configs
	 */
	@Override
	public List<AdminConfig> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the admin configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdminConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of admin configs
	 * @param end the upper bound of the range of admin configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of admin configs
	 */
	@Override
	public List<AdminConfig> findAll(int start, int end,
		OrderByComparator<AdminConfig> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the admin configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdminConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of admin configs
	 * @param end the upper bound of the range of admin configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of admin configs
	 */
	@Override
	public List<AdminConfig> findAll(int start, int end,
		OrderByComparator<AdminConfig> orderByComparator,
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

		List<AdminConfig> list = null;

		if (retrieveFromCache) {
			list = (List<AdminConfig>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ADMINCONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ADMINCONFIG;

				if (pagination) {
					sql = sql.concat(AdminConfigModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AdminConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AdminConfig>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the admin configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AdminConfig adminConfig : findAll()) {
			remove(adminConfig);
		}
	}

	/**
	 * Returns the number of admin configs.
	 *
	 * @return the number of admin configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ADMINCONFIG);

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
		return AdminConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the admin config persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AdminConfigImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ADMINCONFIG = "SELECT adminConfig FROM AdminConfig adminConfig";
	private static final String _SQL_SELECT_ADMINCONFIG_WHERE_PKS_IN = "SELECT adminConfig FROM AdminConfig adminConfig WHERE id_ IN (";
	private static final String _SQL_SELECT_ADMINCONFIG_WHERE = "SELECT adminConfig FROM AdminConfig adminConfig WHERE ";
	private static final String _SQL_COUNT_ADMINCONFIG = "SELECT COUNT(adminConfig) FROM AdminConfig adminConfig";
	private static final String _SQL_COUNT_ADMINCONFIG_WHERE = "SELECT COUNT(adminConfig) FROM AdminConfig adminConfig WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "adminConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AdminConfig exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AdminConfig exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AdminConfigPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"id", "code"
			});
}