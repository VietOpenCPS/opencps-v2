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

package org.opencps.usermgt.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
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

import org.opencps.usermgt.exception.NoSuchHmacAuthenException;
import org.opencps.usermgt.model.HmacAuthen;
import org.opencps.usermgt.model.impl.HmacAuthenImpl;
import org.opencps.usermgt.model.impl.HmacAuthenModelImpl;
import org.opencps.usermgt.service.persistence.HmacAuthenPersistence;

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
 * The persistence implementation for the hmac authen service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see HmacAuthenPersistence
 * @see org.opencps.usermgt.service.persistence.HmacAuthenUtil
 * @generated
 */
@ProviderType
public class HmacAuthenPersistenceImpl extends BasePersistenceImpl<HmacAuthen>
	implements HmacAuthenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HmacAuthenUtil} to access the hmac authen persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HmacAuthenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HmacAuthenModelImpl.ENTITY_CACHE_ENABLED,
			HmacAuthenModelImpl.FINDER_CACHE_ENABLED, HmacAuthenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HmacAuthenModelImpl.ENTITY_CACHE_ENABLED,
			HmacAuthenModelImpl.FINDER_CACHE_ENABLED, HmacAuthenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HmacAuthenModelImpl.ENTITY_CACHE_ENABLED,
			HmacAuthenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public HmacAuthenPersistenceImpl() {
		setModelClass(HmacAuthen.class);
	}

	/**
	 * Caches the hmac authen in the entity cache if it is enabled.
	 *
	 * @param hmacAuthen the hmac authen
	 */
	@Override
	public void cacheResult(HmacAuthen hmacAuthen) {
		entityCache.putResult(HmacAuthenModelImpl.ENTITY_CACHE_ENABLED,
			HmacAuthenImpl.class, hmacAuthen.getPrimaryKey(), hmacAuthen);

		hmacAuthen.resetOriginalValues();
	}

	/**
	 * Caches the hmac authens in the entity cache if it is enabled.
	 *
	 * @param hmacAuthens the hmac authens
	 */
	@Override
	public void cacheResult(List<HmacAuthen> hmacAuthens) {
		for (HmacAuthen hmacAuthen : hmacAuthens) {
			if (entityCache.getResult(
						HmacAuthenModelImpl.ENTITY_CACHE_ENABLED,
						HmacAuthenImpl.class, hmacAuthen.getPrimaryKey()) == null) {
				cacheResult(hmacAuthen);
			}
			else {
				hmacAuthen.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all hmac authens.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HmacAuthenImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the hmac authen.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HmacAuthen hmacAuthen) {
		entityCache.removeResult(HmacAuthenModelImpl.ENTITY_CACHE_ENABLED,
			HmacAuthenImpl.class, hmacAuthen.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<HmacAuthen> hmacAuthens) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HmacAuthen hmacAuthen : hmacAuthens) {
			entityCache.removeResult(HmacAuthenModelImpl.ENTITY_CACHE_ENABLED,
				HmacAuthenImpl.class, hmacAuthen.getPrimaryKey());
		}
	}

	/**
	 * Creates a new hmac authen with the primary key. Does not add the hmac authen to the database.
	 *
	 * @param hmacAuthId the primary key for the new hmac authen
	 * @return the new hmac authen
	 */
	@Override
	public HmacAuthen create(long hmacAuthId) {
		HmacAuthen hmacAuthen = new HmacAuthenImpl();

		hmacAuthen.setNew(true);
		hmacAuthen.setPrimaryKey(hmacAuthId);

		hmacAuthen.setCompanyId(companyProvider.getCompanyId());

		return hmacAuthen;
	}

	/**
	 * Removes the hmac authen with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hmacAuthId the primary key of the hmac authen
	 * @return the hmac authen that was removed
	 * @throws NoSuchHmacAuthenException if a hmac authen with the primary key could not be found
	 */
	@Override
	public HmacAuthen remove(long hmacAuthId) throws NoSuchHmacAuthenException {
		return remove((Serializable)hmacAuthId);
	}

	/**
	 * Removes the hmac authen with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hmac authen
	 * @return the hmac authen that was removed
	 * @throws NoSuchHmacAuthenException if a hmac authen with the primary key could not be found
	 */
	@Override
	public HmacAuthen remove(Serializable primaryKey)
		throws NoSuchHmacAuthenException {
		Session session = null;

		try {
			session = openSession();

			HmacAuthen hmacAuthen = (HmacAuthen)session.get(HmacAuthenImpl.class,
					primaryKey);

			if (hmacAuthen == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHmacAuthenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(hmacAuthen);
		}
		catch (NoSuchHmacAuthenException nsee) {
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
	protected HmacAuthen removeImpl(HmacAuthen hmacAuthen) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(hmacAuthen)) {
				hmacAuthen = (HmacAuthen)session.get(HmacAuthenImpl.class,
						hmacAuthen.getPrimaryKeyObj());
			}

			if (hmacAuthen != null) {
				session.delete(hmacAuthen);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (hmacAuthen != null) {
			clearCache(hmacAuthen);
		}

		return hmacAuthen;
	}

	@Override
	public HmacAuthen updateImpl(HmacAuthen hmacAuthen) {
		boolean isNew = hmacAuthen.isNew();

		if (!(hmacAuthen instanceof HmacAuthenModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(hmacAuthen.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(hmacAuthen);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in hmacAuthen proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom HmacAuthen implementation " +
				hmacAuthen.getClass());
		}

		HmacAuthenModelImpl hmacAuthenModelImpl = (HmacAuthenModelImpl)hmacAuthen;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (hmacAuthen.getCreateDate() == null)) {
			if (serviceContext == null) {
				hmacAuthen.setCreateDate(now);
			}
			else {
				hmacAuthen.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!hmacAuthenModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				hmacAuthen.setModifiedDate(now);
			}
			else {
				hmacAuthen.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (hmacAuthen.isNew()) {
				session.save(hmacAuthen);

				hmacAuthen.setNew(false);
			}
			else {
				hmacAuthen = (HmacAuthen)session.merge(hmacAuthen);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(HmacAuthenModelImpl.ENTITY_CACHE_ENABLED,
			HmacAuthenImpl.class, hmacAuthen.getPrimaryKey(), hmacAuthen, false);

		hmacAuthen.resetOriginalValues();

		return hmacAuthen;
	}

	/**
	 * Returns the hmac authen with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the hmac authen
	 * @return the hmac authen
	 * @throws NoSuchHmacAuthenException if a hmac authen with the primary key could not be found
	 */
	@Override
	public HmacAuthen findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHmacAuthenException {
		HmacAuthen hmacAuthen = fetchByPrimaryKey(primaryKey);

		if (hmacAuthen == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHmacAuthenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return hmacAuthen;
	}

	/**
	 * Returns the hmac authen with the primary key or throws a {@link NoSuchHmacAuthenException} if it could not be found.
	 *
	 * @param hmacAuthId the primary key of the hmac authen
	 * @return the hmac authen
	 * @throws NoSuchHmacAuthenException if a hmac authen with the primary key could not be found
	 */
	@Override
	public HmacAuthen findByPrimaryKey(long hmacAuthId)
		throws NoSuchHmacAuthenException {
		return findByPrimaryKey((Serializable)hmacAuthId);
	}

	/**
	 * Returns the hmac authen with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hmac authen
	 * @return the hmac authen, or <code>null</code> if a hmac authen with the primary key could not be found
	 */
	@Override
	public HmacAuthen fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HmacAuthenModelImpl.ENTITY_CACHE_ENABLED,
				HmacAuthenImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HmacAuthen hmacAuthen = (HmacAuthen)serializable;

		if (hmacAuthen == null) {
			Session session = null;

			try {
				session = openSession();

				hmacAuthen = (HmacAuthen)session.get(HmacAuthenImpl.class,
						primaryKey);

				if (hmacAuthen != null) {
					cacheResult(hmacAuthen);
				}
				else {
					entityCache.putResult(HmacAuthenModelImpl.ENTITY_CACHE_ENABLED,
						HmacAuthenImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HmacAuthenModelImpl.ENTITY_CACHE_ENABLED,
					HmacAuthenImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return hmacAuthen;
	}

	/**
	 * Returns the hmac authen with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hmacAuthId the primary key of the hmac authen
	 * @return the hmac authen, or <code>null</code> if a hmac authen with the primary key could not be found
	 */
	@Override
	public HmacAuthen fetchByPrimaryKey(long hmacAuthId) {
		return fetchByPrimaryKey((Serializable)hmacAuthId);
	}

	@Override
	public Map<Serializable, HmacAuthen> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HmacAuthen> map = new HashMap<Serializable, HmacAuthen>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			HmacAuthen hmacAuthen = fetchByPrimaryKey(primaryKey);

			if (hmacAuthen != null) {
				map.put(primaryKey, hmacAuthen);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(HmacAuthenModelImpl.ENTITY_CACHE_ENABLED,
					HmacAuthenImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (HmacAuthen)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_HMACAUTHEN_WHERE_PKS_IN);

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

			for (HmacAuthen hmacAuthen : (List<HmacAuthen>)q.list()) {
				map.put(hmacAuthen.getPrimaryKeyObj(), hmacAuthen);

				cacheResult(hmacAuthen);

				uncachedPrimaryKeys.remove(hmacAuthen.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(HmacAuthenModelImpl.ENTITY_CACHE_ENABLED,
					HmacAuthenImpl.class, primaryKey, nullModel);
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
	 * Returns all the hmac authens.
	 *
	 * @return the hmac authens
	 */
	@Override
	public List<HmacAuthen> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hmac authens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HmacAuthenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hmac authens
	 * @param end the upper bound of the range of hmac authens (not inclusive)
	 * @return the range of hmac authens
	 */
	@Override
	public List<HmacAuthen> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hmac authens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HmacAuthenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hmac authens
	 * @param end the upper bound of the range of hmac authens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hmac authens
	 */
	@Override
	public List<HmacAuthen> findAll(int start, int end,
		OrderByComparator<HmacAuthen> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hmac authens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HmacAuthenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hmac authens
	 * @param end the upper bound of the range of hmac authens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of hmac authens
	 */
	@Override
	public List<HmacAuthen> findAll(int start, int end,
		OrderByComparator<HmacAuthen> orderByComparator,
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

		List<HmacAuthen> list = null;

		if (retrieveFromCache) {
			list = (List<HmacAuthen>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HMACAUTHEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HMACAUTHEN;

				if (pagination) {
					sql = sql.concat(HmacAuthenModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HmacAuthen>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HmacAuthen>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the hmac authens from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HmacAuthen hmacAuthen : findAll()) {
			remove(hmacAuthen);
		}
	}

	/**
	 * Returns the number of hmac authens.
	 *
	 * @return the number of hmac authens
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HMACAUTHEN);

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
		return HmacAuthenModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hmac authen persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HmacAuthenImpl.class.getName());
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
	private static final String _SQL_SELECT_HMACAUTHEN = "SELECT hmacAuthen FROM HmacAuthen hmacAuthen";
	private static final String _SQL_SELECT_HMACAUTHEN_WHERE_PKS_IN = "SELECT hmacAuthen FROM HmacAuthen hmacAuthen WHERE hmacAuthId IN (";
	private static final String _SQL_COUNT_HMACAUTHEN = "SELECT COUNT(hmacAuthen) FROM HmacAuthen hmacAuthen";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hmacAuthen.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HmacAuthen exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HmacAuthenPersistenceImpl.class);
}