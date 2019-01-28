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

package org.opencps.systemmgt.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.systemmgt.exception.NoSuchSchedulerRecordException;
import org.opencps.systemmgt.model.SchedulerRecord;
import org.opencps.systemmgt.model.impl.SchedulerRecordImpl;
import org.opencps.systemmgt.model.impl.SchedulerRecordModelImpl;
import org.opencps.systemmgt.service.persistence.SchedulerRecordPersistence;

import java.io.Serializable;

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
 * The persistence implementation for the scheduler record service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchedulerRecordPersistence
 * @see org.opencps.systemmgt.service.persistence.SchedulerRecordUtil
 * @generated
 */
@ProviderType
public class SchedulerRecordPersistenceImpl extends BasePersistenceImpl<SchedulerRecord>
	implements SchedulerRecordPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SchedulerRecordUtil} to access the scheduler record persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SchedulerRecordImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
			SchedulerRecordModelImpl.FINDER_CACHE_ENABLED,
			SchedulerRecordImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
			SchedulerRecordModelImpl.FINDER_CACHE_ENABLED,
			SchedulerRecordImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
			SchedulerRecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_ST = new FinderPath(SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
			SchedulerRecordModelImpl.FINDER_CACHE_ENABLED,
			SchedulerRecordImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByST",
			new String[] { String.class.getName() },
			SchedulerRecordModelImpl.SCHEDULERTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ST = new FinderPath(SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
			SchedulerRecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByST",
			new String[] { String.class.getName() });

	/**
	 * Returns the scheduler record where schedulerType = &#63; or throws a {@link NoSuchSchedulerRecordException} if it could not be found.
	 *
	 * @param schedulerType the scheduler type
	 * @return the matching scheduler record
	 * @throws NoSuchSchedulerRecordException if a matching scheduler record could not be found
	 */
	@Override
	public SchedulerRecord findByST(String schedulerType)
		throws NoSuchSchedulerRecordException {
		SchedulerRecord schedulerRecord = fetchByST(schedulerType);

		if (schedulerRecord == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("schedulerType=");
			msg.append(schedulerType);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSchedulerRecordException(msg.toString());
		}

		return schedulerRecord;
	}

	/**
	 * Returns the scheduler record where schedulerType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param schedulerType the scheduler type
	 * @return the matching scheduler record, or <code>null</code> if a matching scheduler record could not be found
	 */
	@Override
	public SchedulerRecord fetchByST(String schedulerType) {
		return fetchByST(schedulerType, true);
	}

	/**
	 * Returns the scheduler record where schedulerType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param schedulerType the scheduler type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching scheduler record, or <code>null</code> if a matching scheduler record could not be found
	 */
	@Override
	public SchedulerRecord fetchByST(String schedulerType,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { schedulerType };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_ST, finderArgs,
					this);
		}

		if (result instanceof SchedulerRecord) {
			SchedulerRecord schedulerRecord = (SchedulerRecord)result;

			if (!Objects.equals(schedulerType,
						schedulerRecord.getSchedulerType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SCHEDULERRECORD_WHERE);

			boolean bindSchedulerType = false;

			if (schedulerType == null) {
				query.append(_FINDER_COLUMN_ST_SCHEDULERTYPE_1);
			}
			else if (schedulerType.equals("")) {
				query.append(_FINDER_COLUMN_ST_SCHEDULERTYPE_3);
			}
			else {
				bindSchedulerType = true;

				query.append(_FINDER_COLUMN_ST_SCHEDULERTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSchedulerType) {
					qPos.add(schedulerType);
				}

				List<SchedulerRecord> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_ST, finderArgs,
						list);
				}
				else {
					SchedulerRecord schedulerRecord = list.get(0);

					result = schedulerRecord;

					cacheResult(schedulerRecord);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_ST, finderArgs);

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
			return (SchedulerRecord)result;
		}
	}

	/**
	 * Removes the scheduler record where schedulerType = &#63; from the database.
	 *
	 * @param schedulerType the scheduler type
	 * @return the scheduler record that was removed
	 */
	@Override
	public SchedulerRecord removeByST(String schedulerType)
		throws NoSuchSchedulerRecordException {
		SchedulerRecord schedulerRecord = findByST(schedulerType);

		return remove(schedulerRecord);
	}

	/**
	 * Returns the number of scheduler records where schedulerType = &#63;.
	 *
	 * @param schedulerType the scheduler type
	 * @return the number of matching scheduler records
	 */
	@Override
	public int countByST(String schedulerType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ST;

		Object[] finderArgs = new Object[] { schedulerType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCHEDULERRECORD_WHERE);

			boolean bindSchedulerType = false;

			if (schedulerType == null) {
				query.append(_FINDER_COLUMN_ST_SCHEDULERTYPE_1);
			}
			else if (schedulerType.equals("")) {
				query.append(_FINDER_COLUMN_ST_SCHEDULERTYPE_3);
			}
			else {
				bindSchedulerType = true;

				query.append(_FINDER_COLUMN_ST_SCHEDULERTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSchedulerType) {
					qPos.add(schedulerType);
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

	private static final String _FINDER_COLUMN_ST_SCHEDULERTYPE_1 = "schedulerRecord.schedulerType IS NULL";
	private static final String _FINDER_COLUMN_ST_SCHEDULERTYPE_2 = "schedulerRecord.schedulerType = ?";
	private static final String _FINDER_COLUMN_ST_SCHEDULERTYPE_3 = "(schedulerRecord.schedulerType IS NULL OR schedulerRecord.schedulerType = '')";

	public SchedulerRecordPersistenceImpl() {
		setModelClass(SchedulerRecord.class);
	}

	/**
	 * Caches the scheduler record in the entity cache if it is enabled.
	 *
	 * @param schedulerRecord the scheduler record
	 */
	@Override
	public void cacheResult(SchedulerRecord schedulerRecord) {
		entityCache.putResult(SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
			SchedulerRecordImpl.class, schedulerRecord.getPrimaryKey(),
			schedulerRecord);

		finderCache.putResult(FINDER_PATH_FETCH_BY_ST,
			new Object[] { schedulerRecord.getSchedulerType() }, schedulerRecord);

		schedulerRecord.resetOriginalValues();
	}

	/**
	 * Caches the scheduler records in the entity cache if it is enabled.
	 *
	 * @param schedulerRecords the scheduler records
	 */
	@Override
	public void cacheResult(List<SchedulerRecord> schedulerRecords) {
		for (SchedulerRecord schedulerRecord : schedulerRecords) {
			if (entityCache.getResult(
						SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
						SchedulerRecordImpl.class,
						schedulerRecord.getPrimaryKey()) == null) {
				cacheResult(schedulerRecord);
			}
			else {
				schedulerRecord.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all scheduler records.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SchedulerRecordImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the scheduler record.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SchedulerRecord schedulerRecord) {
		entityCache.removeResult(SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
			SchedulerRecordImpl.class, schedulerRecord.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SchedulerRecordModelImpl)schedulerRecord, true);
	}

	@Override
	public void clearCache(List<SchedulerRecord> schedulerRecords) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SchedulerRecord schedulerRecord : schedulerRecords) {
			entityCache.removeResult(SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
				SchedulerRecordImpl.class, schedulerRecord.getPrimaryKey());

			clearUniqueFindersCache((SchedulerRecordModelImpl)schedulerRecord,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		SchedulerRecordModelImpl schedulerRecordModelImpl) {
		Object[] args = new Object[] { schedulerRecordModelImpl.getSchedulerType() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_ST, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_ST, args,
			schedulerRecordModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SchedulerRecordModelImpl schedulerRecordModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					schedulerRecordModelImpl.getSchedulerType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ST, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ST, args);
		}

		if ((schedulerRecordModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ST.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					schedulerRecordModelImpl.getOriginalSchedulerType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ST, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ST, args);
		}
	}

	/**
	 * Creates a new scheduler record with the primary key. Does not add the scheduler record to the database.
	 *
	 * @param schedulerId the primary key for the new scheduler record
	 * @return the new scheduler record
	 */
	@Override
	public SchedulerRecord create(long schedulerId) {
		SchedulerRecord schedulerRecord = new SchedulerRecordImpl();

		schedulerRecord.setNew(true);
		schedulerRecord.setPrimaryKey(schedulerId);

		return schedulerRecord;
	}

	/**
	 * Removes the scheduler record with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schedulerId the primary key of the scheduler record
	 * @return the scheduler record that was removed
	 * @throws NoSuchSchedulerRecordException if a scheduler record with the primary key could not be found
	 */
	@Override
	public SchedulerRecord remove(long schedulerId)
		throws NoSuchSchedulerRecordException {
		return remove((Serializable)schedulerId);
	}

	/**
	 * Removes the scheduler record with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the scheduler record
	 * @return the scheduler record that was removed
	 * @throws NoSuchSchedulerRecordException if a scheduler record with the primary key could not be found
	 */
	@Override
	public SchedulerRecord remove(Serializable primaryKey)
		throws NoSuchSchedulerRecordException {
		Session session = null;

		try {
			session = openSession();

			SchedulerRecord schedulerRecord = (SchedulerRecord)session.get(SchedulerRecordImpl.class,
					primaryKey);

			if (schedulerRecord == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSchedulerRecordException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(schedulerRecord);
		}
		catch (NoSuchSchedulerRecordException nsee) {
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
	protected SchedulerRecord removeImpl(SchedulerRecord schedulerRecord) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(schedulerRecord)) {
				schedulerRecord = (SchedulerRecord)session.get(SchedulerRecordImpl.class,
						schedulerRecord.getPrimaryKeyObj());
			}

			if (schedulerRecord != null) {
				session.delete(schedulerRecord);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (schedulerRecord != null) {
			clearCache(schedulerRecord);
		}

		return schedulerRecord;
	}

	@Override
	public SchedulerRecord updateImpl(SchedulerRecord schedulerRecord) {
		boolean isNew = schedulerRecord.isNew();

		if (!(schedulerRecord instanceof SchedulerRecordModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(schedulerRecord.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(schedulerRecord);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in schedulerRecord proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SchedulerRecord implementation " +
				schedulerRecord.getClass());
		}

		SchedulerRecordModelImpl schedulerRecordModelImpl = (SchedulerRecordModelImpl)schedulerRecord;

		Session session = null;

		try {
			session = openSession();

			if (schedulerRecord.isNew()) {
				session.save(schedulerRecord);

				schedulerRecord.setNew(false);
			}
			else {
				schedulerRecord = (SchedulerRecord)session.merge(schedulerRecord);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SchedulerRecordModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
			SchedulerRecordImpl.class, schedulerRecord.getPrimaryKey(),
			schedulerRecord, false);

		clearUniqueFindersCache(schedulerRecordModelImpl, false);
		cacheUniqueFindersCache(schedulerRecordModelImpl);

		schedulerRecord.resetOriginalValues();

		return schedulerRecord;
	}

	/**
	 * Returns the scheduler record with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the scheduler record
	 * @return the scheduler record
	 * @throws NoSuchSchedulerRecordException if a scheduler record with the primary key could not be found
	 */
	@Override
	public SchedulerRecord findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSchedulerRecordException {
		SchedulerRecord schedulerRecord = fetchByPrimaryKey(primaryKey);

		if (schedulerRecord == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSchedulerRecordException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return schedulerRecord;
	}

	/**
	 * Returns the scheduler record with the primary key or throws a {@link NoSuchSchedulerRecordException} if it could not be found.
	 *
	 * @param schedulerId the primary key of the scheduler record
	 * @return the scheduler record
	 * @throws NoSuchSchedulerRecordException if a scheduler record with the primary key could not be found
	 */
	@Override
	public SchedulerRecord findByPrimaryKey(long schedulerId)
		throws NoSuchSchedulerRecordException {
		return findByPrimaryKey((Serializable)schedulerId);
	}

	/**
	 * Returns the scheduler record with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the scheduler record
	 * @return the scheduler record, or <code>null</code> if a scheduler record with the primary key could not be found
	 */
	@Override
	public SchedulerRecord fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
				SchedulerRecordImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SchedulerRecord schedulerRecord = (SchedulerRecord)serializable;

		if (schedulerRecord == null) {
			Session session = null;

			try {
				session = openSession();

				schedulerRecord = (SchedulerRecord)session.get(SchedulerRecordImpl.class,
						primaryKey);

				if (schedulerRecord != null) {
					cacheResult(schedulerRecord);
				}
				else {
					entityCache.putResult(SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
						SchedulerRecordImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
					SchedulerRecordImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return schedulerRecord;
	}

	/**
	 * Returns the scheduler record with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schedulerId the primary key of the scheduler record
	 * @return the scheduler record, or <code>null</code> if a scheduler record with the primary key could not be found
	 */
	@Override
	public SchedulerRecord fetchByPrimaryKey(long schedulerId) {
		return fetchByPrimaryKey((Serializable)schedulerId);
	}

	@Override
	public Map<Serializable, SchedulerRecord> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SchedulerRecord> map = new HashMap<Serializable, SchedulerRecord>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SchedulerRecord schedulerRecord = fetchByPrimaryKey(primaryKey);

			if (schedulerRecord != null) {
				map.put(primaryKey, schedulerRecord);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
					SchedulerRecordImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SchedulerRecord)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SCHEDULERRECORD_WHERE_PKS_IN);

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

			for (SchedulerRecord schedulerRecord : (List<SchedulerRecord>)q.list()) {
				map.put(schedulerRecord.getPrimaryKeyObj(), schedulerRecord);

				cacheResult(schedulerRecord);

				uncachedPrimaryKeys.remove(schedulerRecord.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SchedulerRecordModelImpl.ENTITY_CACHE_ENABLED,
					SchedulerRecordImpl.class, primaryKey, nullModel);
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
	 * Returns all the scheduler records.
	 *
	 * @return the scheduler records
	 */
	@Override
	public List<SchedulerRecord> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheduler records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SchedulerRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of scheduler records
	 * @param end the upper bound of the range of scheduler records (not inclusive)
	 * @return the range of scheduler records
	 */
	@Override
	public List<SchedulerRecord> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheduler records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SchedulerRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of scheduler records
	 * @param end the upper bound of the range of scheduler records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of scheduler records
	 */
	@Override
	public List<SchedulerRecord> findAll(int start, int end,
		OrderByComparator<SchedulerRecord> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the scheduler records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SchedulerRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of scheduler records
	 * @param end the upper bound of the range of scheduler records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of scheduler records
	 */
	@Override
	public List<SchedulerRecord> findAll(int start, int end,
		OrderByComparator<SchedulerRecord> orderByComparator,
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

		List<SchedulerRecord> list = null;

		if (retrieveFromCache) {
			list = (List<SchedulerRecord>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SCHEDULERRECORD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SCHEDULERRECORD;

				if (pagination) {
					sql = sql.concat(SchedulerRecordModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SchedulerRecord>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SchedulerRecord>)QueryUtil.list(q,
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
	 * Removes all the scheduler records from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SchedulerRecord schedulerRecord : findAll()) {
			remove(schedulerRecord);
		}
	}

	/**
	 * Returns the number of scheduler records.
	 *
	 * @return the number of scheduler records
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SCHEDULERRECORD);

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
		return SchedulerRecordModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the scheduler record persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SchedulerRecordImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SCHEDULERRECORD = "SELECT schedulerRecord FROM SchedulerRecord schedulerRecord";
	private static final String _SQL_SELECT_SCHEDULERRECORD_WHERE_PKS_IN = "SELECT schedulerRecord FROM SchedulerRecord schedulerRecord WHERE schedulerId IN (";
	private static final String _SQL_SELECT_SCHEDULERRECORD_WHERE = "SELECT schedulerRecord FROM SchedulerRecord schedulerRecord WHERE ";
	private static final String _SQL_COUNT_SCHEDULERRECORD = "SELECT COUNT(schedulerRecord) FROM SchedulerRecord schedulerRecord";
	private static final String _SQL_COUNT_SCHEDULERRECORD_WHERE = "SELECT COUNT(schedulerRecord) FROM SchedulerRecord schedulerRecord WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "schedulerRecord.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SchedulerRecord exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SchedulerRecord exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SchedulerRecordPersistenceImpl.class);
}