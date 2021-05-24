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

package org.opencps.datamgt.service.persistence.impl;

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

import org.opencps.datamgt.exception.NoSuchDictItemMappingException;
import org.opencps.datamgt.model.DictItemMapping;
import org.opencps.datamgt.model.impl.DictItemMappingImpl;
import org.opencps.datamgt.model.impl.DictItemMappingModelImpl;
import org.opencps.datamgt.service.persistence.DictItemMappingPersistence;

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
 * The persistence implementation for the dict item mapping service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see DictItemMappingPersistence
 * @see org.opencps.datamgt.service.persistence.DictItemMappingUtil
 * @generated
 */
@ProviderType
public class DictItemMappingPersistenceImpl extends BasePersistenceImpl<DictItemMapping>
	implements DictItemMappingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DictItemMappingUtil} to access the dict item mapping persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DictItemMappingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingModelImpl.FINDER_CACHE_ENABLED,
			DictItemMappingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingModelImpl.FINDER_CACHE_ENABLED,
			DictItemMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_IC_CID = new FinderPath(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingModelImpl.FINDER_CACHE_ENABLED,
			DictItemMappingImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_GID_IC_CID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName()
			},
			DictItemMappingModelImpl.GROUPID_COLUMN_BITMASK |
			DictItemMappingModelImpl.ITEMCODE_COLUMN_BITMASK |
			DictItemMappingModelImpl.COLLECTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_IC_CID = new FinderPath(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_IC_CID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns the dict item mapping where groupId = &#63; and itemCode = &#63; and collectionId = &#63; or throws a {@link NoSuchDictItemMappingException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param itemCode the item code
	 * @param collectionId the collection ID
	 * @return the matching dict item mapping
	 * @throws NoSuchDictItemMappingException if a matching dict item mapping could not be found
	 */
	@Override
	public DictItemMapping findByF_GID_IC_CID(long groupId, String itemCode,
		long collectionId) throws NoSuchDictItemMappingException {
		DictItemMapping dictItemMapping = fetchByF_GID_IC_CID(groupId,
				itemCode, collectionId);

		if (dictItemMapping == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", itemCode=");
			msg.append(itemCode);

			msg.append(", collectionId=");
			msg.append(collectionId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDictItemMappingException(msg.toString());
		}

		return dictItemMapping;
	}

	/**
	 * Returns the dict item mapping where groupId = &#63; and itemCode = &#63; and collectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param itemCode the item code
	 * @param collectionId the collection ID
	 * @return the matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	 */
	@Override
	public DictItemMapping fetchByF_GID_IC_CID(long groupId, String itemCode,
		long collectionId) {
		return fetchByF_GID_IC_CID(groupId, itemCode, collectionId, true);
	}

	/**
	 * Returns the dict item mapping where groupId = &#63; and itemCode = &#63; and collectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param itemCode the item code
	 * @param collectionId the collection ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	 */
	@Override
	public DictItemMapping fetchByF_GID_IC_CID(long groupId, String itemCode,
		long collectionId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, itemCode, collectionId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_IC_CID,
					finderArgs, this);
		}

		if (result instanceof DictItemMapping) {
			DictItemMapping dictItemMapping = (DictItemMapping)result;

			if ((groupId != dictItemMapping.getGroupId()) ||
					!Objects.equals(itemCode, dictItemMapping.getItemCode()) ||
					(collectionId != dictItemMapping.getCollectionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DICTITEMMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_IC_CID_GROUPID_2);

			boolean bindItemCode = false;

			if (itemCode == null) {
				query.append(_FINDER_COLUMN_F_GID_IC_CID_ITEMCODE_1);
			}
			else if (itemCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_IC_CID_ITEMCODE_3);
			}
			else {
				bindItemCode = true;

				query.append(_FINDER_COLUMN_F_GID_IC_CID_ITEMCODE_2);
			}

			query.append(_FINDER_COLUMN_F_GID_IC_CID_COLLECTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindItemCode) {
					qPos.add(itemCode);
				}

				qPos.add(collectionId);

				List<DictItemMapping> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_IC_CID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictItemMappingPersistenceImpl.fetchByF_GID_IC_CID(long, String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictItemMapping dictItemMapping = list.get(0);

					result = dictItemMapping;

					cacheResult(dictItemMapping);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_IC_CID,
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
			return (DictItemMapping)result;
		}
	}

	/**
	 * Removes the dict item mapping where groupId = &#63; and itemCode = &#63; and collectionId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param itemCode the item code
	 * @param collectionId the collection ID
	 * @return the dict item mapping that was removed
	 */
	@Override
	public DictItemMapping removeByF_GID_IC_CID(long groupId, String itemCode,
		long collectionId) throws NoSuchDictItemMappingException {
		DictItemMapping dictItemMapping = findByF_GID_IC_CID(groupId, itemCode,
				collectionId);

		return remove(dictItemMapping);
	}

	/**
	 * Returns the number of dict item mappings where groupId = &#63; and itemCode = &#63; and collectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param itemCode the item code
	 * @param collectionId the collection ID
	 * @return the number of matching dict item mappings
	 */
	@Override
	public int countByF_GID_IC_CID(long groupId, String itemCode,
		long collectionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_IC_CID;

		Object[] finderArgs = new Object[] { groupId, itemCode, collectionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DICTITEMMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_IC_CID_GROUPID_2);

			boolean bindItemCode = false;

			if (itemCode == null) {
				query.append(_FINDER_COLUMN_F_GID_IC_CID_ITEMCODE_1);
			}
			else if (itemCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_IC_CID_ITEMCODE_3);
			}
			else {
				bindItemCode = true;

				query.append(_FINDER_COLUMN_F_GID_IC_CID_ITEMCODE_2);
			}

			query.append(_FINDER_COLUMN_F_GID_IC_CID_COLLECTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindItemCode) {
					qPos.add(itemCode);
				}

				qPos.add(collectionId);

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

	private static final String _FINDER_COLUMN_F_GID_IC_CID_GROUPID_2 = "dictItemMapping.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_IC_CID_ITEMCODE_1 = "dictItemMapping.itemCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_IC_CID_ITEMCODE_2 = "dictItemMapping.itemCode = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_IC_CID_ITEMCODE_3 = "(dictItemMapping.itemCode IS NULL OR dictItemMapping.itemCode = '') AND ";
	private static final String _FINDER_COLUMN_F_GID_IC_CID_COLLECTIONID_2 = "dictItemMapping.collectionId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_IC = new FinderPath(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingModelImpl.FINDER_CACHE_ENABLED,
			DictItemMappingImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByF_IC",
			new String[] { String.class.getName() },
			DictItemMappingModelImpl.ITEMCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_IC = new FinderPath(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_IC",
			new String[] { String.class.getName() });

	/**
	 * Returns the dict item mapping where itemCode = &#63; or throws a {@link NoSuchDictItemMappingException} if it could not be found.
	 *
	 * @param itemCode the item code
	 * @return the matching dict item mapping
	 * @throws NoSuchDictItemMappingException if a matching dict item mapping could not be found
	 */
	@Override
	public DictItemMapping findByF_IC(String itemCode)
		throws NoSuchDictItemMappingException {
		DictItemMapping dictItemMapping = fetchByF_IC(itemCode);

		if (dictItemMapping == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itemCode=");
			msg.append(itemCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDictItemMappingException(msg.toString());
		}

		return dictItemMapping;
	}

	/**
	 * Returns the dict item mapping where itemCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itemCode the item code
	 * @return the matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	 */
	@Override
	public DictItemMapping fetchByF_IC(String itemCode) {
		return fetchByF_IC(itemCode, true);
	}

	/**
	 * Returns the dict item mapping where itemCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itemCode the item code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	 */
	@Override
	public DictItemMapping fetchByF_IC(String itemCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { itemCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_IC,
					finderArgs, this);
		}

		if (result instanceof DictItemMapping) {
			DictItemMapping dictItemMapping = (DictItemMapping)result;

			if (!Objects.equals(itemCode, dictItemMapping.getItemCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DICTITEMMAPPING_WHERE);

			boolean bindItemCode = false;

			if (itemCode == null) {
				query.append(_FINDER_COLUMN_F_IC_ITEMCODE_1);
			}
			else if (itemCode.equals("")) {
				query.append(_FINDER_COLUMN_F_IC_ITEMCODE_3);
			}
			else {
				bindItemCode = true;

				query.append(_FINDER_COLUMN_F_IC_ITEMCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemCode) {
					qPos.add(itemCode);
				}

				List<DictItemMapping> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_IC,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictItemMappingPersistenceImpl.fetchByF_IC(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictItemMapping dictItemMapping = list.get(0);

					result = dictItemMapping;

					cacheResult(dictItemMapping);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_IC, finderArgs);

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
			return (DictItemMapping)result;
		}
	}

	/**
	 * Removes the dict item mapping where itemCode = &#63; from the database.
	 *
	 * @param itemCode the item code
	 * @return the dict item mapping that was removed
	 */
	@Override
	public DictItemMapping removeByF_IC(String itemCode)
		throws NoSuchDictItemMappingException {
		DictItemMapping dictItemMapping = findByF_IC(itemCode);

		return remove(dictItemMapping);
	}

	/**
	 * Returns the number of dict item mappings where itemCode = &#63;.
	 *
	 * @param itemCode the item code
	 * @return the number of matching dict item mappings
	 */
	@Override
	public int countByF_IC(String itemCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_IC;

		Object[] finderArgs = new Object[] { itemCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DICTITEMMAPPING_WHERE);

			boolean bindItemCode = false;

			if (itemCode == null) {
				query.append(_FINDER_COLUMN_F_IC_ITEMCODE_1);
			}
			else if (itemCode.equals("")) {
				query.append(_FINDER_COLUMN_F_IC_ITEMCODE_3);
			}
			else {
				bindItemCode = true;

				query.append(_FINDER_COLUMN_F_IC_ITEMCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemCode) {
					qPos.add(itemCode);
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

	private static final String _FINDER_COLUMN_F_IC_ITEMCODE_1 = "dictItemMapping.itemCode IS NULL";
	private static final String _FINDER_COLUMN_F_IC_ITEMCODE_2 = "dictItemMapping.itemCode = ?";
	private static final String _FINDER_COLUMN_F_IC_ITEMCODE_3 = "(dictItemMapping.itemCode IS NULL OR dictItemMapping.itemCode = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_ICDVCQG_CID = new FinderPath(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingModelImpl.FINDER_CACHE_ENABLED,
			DictItemMappingImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_GID_ICDVCQG_CID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName()
			},
			DictItemMappingModelImpl.GROUPID_COLUMN_BITMASK |
			DictItemMappingModelImpl.ITEMCODEDVCQG_COLUMN_BITMASK |
			DictItemMappingModelImpl.COLLECTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_ICDVCQG_CID = new FinderPath(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_GID_ICDVCQG_CID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns the dict item mapping where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63; or throws a {@link NoSuchDictItemMappingException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param itemCodeDVCQG the item code dvcqg
	 * @param collectionId the collection ID
	 * @return the matching dict item mapping
	 * @throws NoSuchDictItemMappingException if a matching dict item mapping could not be found
	 */
	@Override
	public DictItemMapping findByF_GID_ICDVCQG_CID(long groupId,
		String itemCodeDVCQG, long collectionId)
		throws NoSuchDictItemMappingException {
		DictItemMapping dictItemMapping = fetchByF_GID_ICDVCQG_CID(groupId,
				itemCodeDVCQG, collectionId);

		if (dictItemMapping == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", itemCodeDVCQG=");
			msg.append(itemCodeDVCQG);

			msg.append(", collectionId=");
			msg.append(collectionId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDictItemMappingException(msg.toString());
		}

		return dictItemMapping;
	}

	/**
	 * Returns the dict item mapping where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param itemCodeDVCQG the item code dvcqg
	 * @param collectionId the collection ID
	 * @return the matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	 */
	@Override
	public DictItemMapping fetchByF_GID_ICDVCQG_CID(long groupId,
		String itemCodeDVCQG, long collectionId) {
		return fetchByF_GID_ICDVCQG_CID(groupId, itemCodeDVCQG, collectionId,
			true);
	}

	/**
	 * Returns the dict item mapping where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param itemCodeDVCQG the item code dvcqg
	 * @param collectionId the collection ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	 */
	@Override
	public DictItemMapping fetchByF_GID_ICDVCQG_CID(long groupId,
		String itemCodeDVCQG, long collectionId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, itemCodeDVCQG, collectionId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_ICDVCQG_CID,
					finderArgs, this);
		}

		if (result instanceof DictItemMapping) {
			DictItemMapping dictItemMapping = (DictItemMapping)result;

			if ((groupId != dictItemMapping.getGroupId()) ||
					!Objects.equals(itemCodeDVCQG,
						dictItemMapping.getItemCodeDVCQG()) ||
					(collectionId != dictItemMapping.getCollectionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DICTITEMMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_ICDVCQG_CID_GROUPID_2);

			boolean bindItemCodeDVCQG = false;

			if (itemCodeDVCQG == null) {
				query.append(_FINDER_COLUMN_F_GID_ICDVCQG_CID_ITEMCODEDVCQG_1);
			}
			else if (itemCodeDVCQG.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_ICDVCQG_CID_ITEMCODEDVCQG_3);
			}
			else {
				bindItemCodeDVCQG = true;

				query.append(_FINDER_COLUMN_F_GID_ICDVCQG_CID_ITEMCODEDVCQG_2);
			}

			query.append(_FINDER_COLUMN_F_GID_ICDVCQG_CID_COLLECTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindItemCodeDVCQG) {
					qPos.add(itemCodeDVCQG);
				}

				qPos.add(collectionId);

				List<DictItemMapping> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_ICDVCQG_CID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DictItemMappingPersistenceImpl.fetchByF_GID_ICDVCQG_CID(long, String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DictItemMapping dictItemMapping = list.get(0);

					result = dictItemMapping;

					cacheResult(dictItemMapping);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_ICDVCQG_CID,
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
			return (DictItemMapping)result;
		}
	}

	/**
	 * Removes the dict item mapping where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param itemCodeDVCQG the item code dvcqg
	 * @param collectionId the collection ID
	 * @return the dict item mapping that was removed
	 */
	@Override
	public DictItemMapping removeByF_GID_ICDVCQG_CID(long groupId,
		String itemCodeDVCQG, long collectionId)
		throws NoSuchDictItemMappingException {
		DictItemMapping dictItemMapping = findByF_GID_ICDVCQG_CID(groupId,
				itemCodeDVCQG, collectionId);

		return remove(dictItemMapping);
	}

	/**
	 * Returns the number of dict item mappings where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param itemCodeDVCQG the item code dvcqg
	 * @param collectionId the collection ID
	 * @return the number of matching dict item mappings
	 */
	@Override
	public int countByF_GID_ICDVCQG_CID(long groupId, String itemCodeDVCQG,
		long collectionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_ICDVCQG_CID;

		Object[] finderArgs = new Object[] { groupId, itemCodeDVCQG, collectionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DICTITEMMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_ICDVCQG_CID_GROUPID_2);

			boolean bindItemCodeDVCQG = false;

			if (itemCodeDVCQG == null) {
				query.append(_FINDER_COLUMN_F_GID_ICDVCQG_CID_ITEMCODEDVCQG_1);
			}
			else if (itemCodeDVCQG.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_ICDVCQG_CID_ITEMCODEDVCQG_3);
			}
			else {
				bindItemCodeDVCQG = true;

				query.append(_FINDER_COLUMN_F_GID_ICDVCQG_CID_ITEMCODEDVCQG_2);
			}

			query.append(_FINDER_COLUMN_F_GID_ICDVCQG_CID_COLLECTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindItemCodeDVCQG) {
					qPos.add(itemCodeDVCQG);
				}

				qPos.add(collectionId);

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

	private static final String _FINDER_COLUMN_F_GID_ICDVCQG_CID_GROUPID_2 = "dictItemMapping.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_ICDVCQG_CID_ITEMCODEDVCQG_1 =
		"dictItemMapping.itemCodeDVCQG IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_ICDVCQG_CID_ITEMCODEDVCQG_2 =
		"dictItemMapping.itemCodeDVCQG = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_ICDVCQG_CID_ITEMCODEDVCQG_3 =
		"(dictItemMapping.itemCodeDVCQG IS NULL OR dictItemMapping.itemCodeDVCQG = '') AND ";
	private static final String _FINDER_COLUMN_F_GID_ICDVCQG_CID_COLLECTIONID_2 = "dictItemMapping.collectionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_CID =
		new FinderPath(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingModelImpl.FINDER_CACHE_ENABLED,
			DictItemMappingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_GID_CID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_CID =
		new FinderPath(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingModelImpl.FINDER_CACHE_ENABLED,
			DictItemMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_GID_CID",
			new String[] { Long.class.getName(), Long.class.getName() },
			DictItemMappingModelImpl.GROUPID_COLUMN_BITMASK |
			DictItemMappingModelImpl.COLLECTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_CID = new FinderPath(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_CID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dict item mappings where groupId = &#63; and collectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param collectionId the collection ID
	 * @return the matching dict item mappings
	 */
	@Override
	public List<DictItemMapping> findByF_GID_CID(long groupId, long collectionId) {
		return findByF_GID_CID(groupId, collectionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item mappings where groupId = &#63; and collectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param collectionId the collection ID
	 * @param start the lower bound of the range of dict item mappings
	 * @param end the upper bound of the range of dict item mappings (not inclusive)
	 * @return the range of matching dict item mappings
	 */
	@Override
	public List<DictItemMapping> findByF_GID_CID(long groupId,
		long collectionId, int start, int end) {
		return findByF_GID_CID(groupId, collectionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item mappings where groupId = &#63; and collectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param collectionId the collection ID
	 * @param start the lower bound of the range of dict item mappings
	 * @param end the upper bound of the range of dict item mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dict item mappings
	 */
	@Override
	public List<DictItemMapping> findByF_GID_CID(long groupId,
		long collectionId, int start, int end,
		OrderByComparator<DictItemMapping> orderByComparator) {
		return findByF_GID_CID(groupId, collectionId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item mappings where groupId = &#63; and collectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param collectionId the collection ID
	 * @param start the lower bound of the range of dict item mappings
	 * @param end the upper bound of the range of dict item mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dict item mappings
	 */
	@Override
	public List<DictItemMapping> findByF_GID_CID(long groupId,
		long collectionId, int start, int end,
		OrderByComparator<DictItemMapping> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_CID;
			finderArgs = new Object[] { groupId, collectionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_CID;
			finderArgs = new Object[] {
					groupId, collectionId,
					
					start, end, orderByComparator
				};
		}

		List<DictItemMapping> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemMapping>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DictItemMapping dictItemMapping : list) {
					if ((groupId != dictItemMapping.getGroupId()) ||
							(collectionId != dictItemMapping.getCollectionId())) {
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

			query.append(_SQL_SELECT_DICTITEMMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_CID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_CID_COLLECTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DictItemMappingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(collectionId);

				if (!pagination) {
					list = (List<DictItemMapping>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemMapping>)QueryUtil.list(q,
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
	 * Returns the first dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param collectionId the collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item mapping
	 * @throws NoSuchDictItemMappingException if a matching dict item mapping could not be found
	 */
	@Override
	public DictItemMapping findByF_GID_CID_First(long groupId,
		long collectionId, OrderByComparator<DictItemMapping> orderByComparator)
		throws NoSuchDictItemMappingException {
		DictItemMapping dictItemMapping = fetchByF_GID_CID_First(groupId,
				collectionId, orderByComparator);

		if (dictItemMapping != null) {
			return dictItemMapping;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", collectionId=");
		msg.append(collectionId);

		msg.append("}");

		throw new NoSuchDictItemMappingException(msg.toString());
	}

	/**
	 * Returns the first dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param collectionId the collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	 */
	@Override
	public DictItemMapping fetchByF_GID_CID_First(long groupId,
		long collectionId, OrderByComparator<DictItemMapping> orderByComparator) {
		List<DictItemMapping> list = findByF_GID_CID(groupId, collectionId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param collectionId the collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item mapping
	 * @throws NoSuchDictItemMappingException if a matching dict item mapping could not be found
	 */
	@Override
	public DictItemMapping findByF_GID_CID_Last(long groupId,
		long collectionId, OrderByComparator<DictItemMapping> orderByComparator)
		throws NoSuchDictItemMappingException {
		DictItemMapping dictItemMapping = fetchByF_GID_CID_Last(groupId,
				collectionId, orderByComparator);

		if (dictItemMapping != null) {
			return dictItemMapping;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", collectionId=");
		msg.append(collectionId);

		msg.append("}");

		throw new NoSuchDictItemMappingException(msg.toString());
	}

	/**
	 * Returns the last dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param collectionId the collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	 */
	@Override
	public DictItemMapping fetchByF_GID_CID_Last(long groupId,
		long collectionId, OrderByComparator<DictItemMapping> orderByComparator) {
		int count = countByF_GID_CID(groupId, collectionId);

		if (count == 0) {
			return null;
		}

		List<DictItemMapping> list = findByF_GID_CID(groupId, collectionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dict item mappings before and after the current dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	 *
	 * @param mappingId the primary key of the current dict item mapping
	 * @param groupId the group ID
	 * @param collectionId the collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dict item mapping
	 * @throws NoSuchDictItemMappingException if a dict item mapping with the primary key could not be found
	 */
	@Override
	public DictItemMapping[] findByF_GID_CID_PrevAndNext(long mappingId,
		long groupId, long collectionId,
		OrderByComparator<DictItemMapping> orderByComparator)
		throws NoSuchDictItemMappingException {
		DictItemMapping dictItemMapping = findByPrimaryKey(mappingId);

		Session session = null;

		try {
			session = openSession();

			DictItemMapping[] array = new DictItemMappingImpl[3];

			array[0] = getByF_GID_CID_PrevAndNext(session, dictItemMapping,
					groupId, collectionId, orderByComparator, true);

			array[1] = dictItemMapping;

			array[2] = getByF_GID_CID_PrevAndNext(session, dictItemMapping,
					groupId, collectionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DictItemMapping getByF_GID_CID_PrevAndNext(Session session,
		DictItemMapping dictItemMapping, long groupId, long collectionId,
		OrderByComparator<DictItemMapping> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DICTITEMMAPPING_WHERE);

		query.append(_FINDER_COLUMN_F_GID_CID_GROUPID_2);

		query.append(_FINDER_COLUMN_F_GID_CID_COLLECTIONID_2);

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
			query.append(DictItemMappingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(collectionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dictItemMapping);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DictItemMapping> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dict item mappings where groupId = &#63; and collectionId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param collectionId the collection ID
	 */
	@Override
	public void removeByF_GID_CID(long groupId, long collectionId) {
		for (DictItemMapping dictItemMapping : findByF_GID_CID(groupId,
				collectionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dictItemMapping);
		}
	}

	/**
	 * Returns the number of dict item mappings where groupId = &#63; and collectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param collectionId the collection ID
	 * @return the number of matching dict item mappings
	 */
	@Override
	public int countByF_GID_CID(long groupId, long collectionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_CID;

		Object[] finderArgs = new Object[] { groupId, collectionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DICTITEMMAPPING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_CID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_CID_COLLECTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(collectionId);

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

	private static final String _FINDER_COLUMN_F_GID_CID_GROUPID_2 = "dictItemMapping.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_CID_COLLECTIONID_2 = "dictItemMapping.collectionId = ?";

	public DictItemMappingPersistenceImpl() {
		setModelClass(DictItemMapping.class);
	}

	/**
	 * Caches the dict item mapping in the entity cache if it is enabled.
	 *
	 * @param dictItemMapping the dict item mapping
	 */
	@Override
	public void cacheResult(DictItemMapping dictItemMapping) {
		entityCache.putResult(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingImpl.class, dictItemMapping.getPrimaryKey(),
			dictItemMapping);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_IC_CID,
			new Object[] {
				dictItemMapping.getGroupId(), dictItemMapping.getItemCode(),
				dictItemMapping.getCollectionId()
			}, dictItemMapping);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_IC,
			new Object[] { dictItemMapping.getItemCode() }, dictItemMapping);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_ICDVCQG_CID,
			new Object[] {
				dictItemMapping.getGroupId(), dictItemMapping.getItemCodeDVCQG(),
				dictItemMapping.getCollectionId()
			}, dictItemMapping);

		dictItemMapping.resetOriginalValues();
	}

	/**
	 * Caches the dict item mappings in the entity cache if it is enabled.
	 *
	 * @param dictItemMappings the dict item mappings
	 */
	@Override
	public void cacheResult(List<DictItemMapping> dictItemMappings) {
		for (DictItemMapping dictItemMapping : dictItemMappings) {
			if (entityCache.getResult(
						DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
						DictItemMappingImpl.class,
						dictItemMapping.getPrimaryKey()) == null) {
				cacheResult(dictItemMapping);
			}
			else {
				dictItemMapping.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dict item mappings.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DictItemMappingImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dict item mapping.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DictItemMapping dictItemMapping) {
		entityCache.removeResult(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingImpl.class, dictItemMapping.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DictItemMappingModelImpl)dictItemMapping, true);
	}

	@Override
	public void clearCache(List<DictItemMapping> dictItemMappings) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DictItemMapping dictItemMapping : dictItemMappings) {
			entityCache.removeResult(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
				DictItemMappingImpl.class, dictItemMapping.getPrimaryKey());

			clearUniqueFindersCache((DictItemMappingModelImpl)dictItemMapping,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		DictItemMappingModelImpl dictItemMappingModelImpl) {
		Object[] args = new Object[] {
				dictItemMappingModelImpl.getGroupId(),
				dictItemMappingModelImpl.getItemCode(),
				dictItemMappingModelImpl.getCollectionId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_IC_CID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_IC_CID, args,
			dictItemMappingModelImpl, false);

		args = new Object[] { dictItemMappingModelImpl.getItemCode() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_IC, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_IC, args,
			dictItemMappingModelImpl, false);

		args = new Object[] {
				dictItemMappingModelImpl.getGroupId(),
				dictItemMappingModelImpl.getItemCodeDVCQG(),
				dictItemMappingModelImpl.getCollectionId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_ICDVCQG_CID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_ICDVCQG_CID, args,
			dictItemMappingModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DictItemMappingModelImpl dictItemMappingModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dictItemMappingModelImpl.getGroupId(),
					dictItemMappingModelImpl.getItemCode(),
					dictItemMappingModelImpl.getCollectionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_IC_CID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_IC_CID, args);
		}

		if ((dictItemMappingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_IC_CID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictItemMappingModelImpl.getOriginalGroupId(),
					dictItemMappingModelImpl.getOriginalItemCode(),
					dictItemMappingModelImpl.getOriginalCollectionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_IC_CID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_IC_CID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { dictItemMappingModelImpl.getItemCode() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_IC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_IC, args);
		}

		if ((dictItemMappingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_IC.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictItemMappingModelImpl.getOriginalItemCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_IC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_IC, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dictItemMappingModelImpl.getGroupId(),
					dictItemMappingModelImpl.getItemCodeDVCQG(),
					dictItemMappingModelImpl.getCollectionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_ICDVCQG_CID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_ICDVCQG_CID,
				args);
		}

		if ((dictItemMappingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_ICDVCQG_CID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dictItemMappingModelImpl.getOriginalGroupId(),
					dictItemMappingModelImpl.getOriginalItemCodeDVCQG(),
					dictItemMappingModelImpl.getOriginalCollectionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_ICDVCQG_CID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_ICDVCQG_CID,
				args);
		}
	}

	/**
	 * Creates a new dict item mapping with the primary key. Does not add the dict item mapping to the database.
	 *
	 * @param mappingId the primary key for the new dict item mapping
	 * @return the new dict item mapping
	 */
	@Override
	public DictItemMapping create(long mappingId) {
		DictItemMapping dictItemMapping = new DictItemMappingImpl();

		dictItemMapping.setNew(true);
		dictItemMapping.setPrimaryKey(mappingId);

		dictItemMapping.setCompanyId(companyProvider.getCompanyId());

		return dictItemMapping;
	}

	/**
	 * Removes the dict item mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mappingId the primary key of the dict item mapping
	 * @return the dict item mapping that was removed
	 * @throws NoSuchDictItemMappingException if a dict item mapping with the primary key could not be found
	 */
	@Override
	public DictItemMapping remove(long mappingId)
		throws NoSuchDictItemMappingException {
		return remove((Serializable)mappingId);
	}

	/**
	 * Removes the dict item mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dict item mapping
	 * @return the dict item mapping that was removed
	 * @throws NoSuchDictItemMappingException if a dict item mapping with the primary key could not be found
	 */
	@Override
	public DictItemMapping remove(Serializable primaryKey)
		throws NoSuchDictItemMappingException {
		Session session = null;

		try {
			session = openSession();

			DictItemMapping dictItemMapping = (DictItemMapping)session.get(DictItemMappingImpl.class,
					primaryKey);

			if (dictItemMapping == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDictItemMappingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dictItemMapping);
		}
		catch (NoSuchDictItemMappingException nsee) {
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
	protected DictItemMapping removeImpl(DictItemMapping dictItemMapping) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dictItemMapping)) {
				dictItemMapping = (DictItemMapping)session.get(DictItemMappingImpl.class,
						dictItemMapping.getPrimaryKeyObj());
			}

			if (dictItemMapping != null) {
				session.delete(dictItemMapping);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dictItemMapping != null) {
			clearCache(dictItemMapping);
		}

		return dictItemMapping;
	}

	@Override
	public DictItemMapping updateImpl(DictItemMapping dictItemMapping) {
		boolean isNew = dictItemMapping.isNew();

		if (!(dictItemMapping instanceof DictItemMappingModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dictItemMapping.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dictItemMapping);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dictItemMapping proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DictItemMapping implementation " +
				dictItemMapping.getClass());
		}

		DictItemMappingModelImpl dictItemMappingModelImpl = (DictItemMappingModelImpl)dictItemMapping;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dictItemMapping.getCreateDate() == null)) {
			if (serviceContext == null) {
				dictItemMapping.setCreateDate(now);
			}
			else {
				dictItemMapping.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dictItemMappingModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dictItemMapping.setModifiedDate(now);
			}
			else {
				dictItemMapping.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dictItemMapping.isNew()) {
				session.save(dictItemMapping);

				dictItemMapping.setNew(false);
			}
			else {
				dictItemMapping = (DictItemMapping)session.merge(dictItemMapping);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DictItemMappingModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					dictItemMappingModelImpl.getGroupId(),
					dictItemMappingModelImpl.getCollectionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_CID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_CID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dictItemMappingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_CID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dictItemMappingModelImpl.getOriginalGroupId(),
						dictItemMappingModelImpl.getOriginalCollectionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_CID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_CID,
					args);

				args = new Object[] {
						dictItemMappingModelImpl.getGroupId(),
						dictItemMappingModelImpl.getCollectionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_CID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_CID,
					args);
			}
		}

		entityCache.putResult(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
			DictItemMappingImpl.class, dictItemMapping.getPrimaryKey(),
			dictItemMapping, false);

		clearUniqueFindersCache(dictItemMappingModelImpl, false);
		cacheUniqueFindersCache(dictItemMappingModelImpl);

		dictItemMapping.resetOriginalValues();

		return dictItemMapping;
	}

	/**
	 * Returns the dict item mapping with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict item mapping
	 * @return the dict item mapping
	 * @throws NoSuchDictItemMappingException if a dict item mapping with the primary key could not be found
	 */
	@Override
	public DictItemMapping findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDictItemMappingException {
		DictItemMapping dictItemMapping = fetchByPrimaryKey(primaryKey);

		if (dictItemMapping == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDictItemMappingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dictItemMapping;
	}

	/**
	 * Returns the dict item mapping with the primary key or throws a {@link NoSuchDictItemMappingException} if it could not be found.
	 *
	 * @param mappingId the primary key of the dict item mapping
	 * @return the dict item mapping
	 * @throws NoSuchDictItemMappingException if a dict item mapping with the primary key could not be found
	 */
	@Override
	public DictItemMapping findByPrimaryKey(long mappingId)
		throws NoSuchDictItemMappingException {
		return findByPrimaryKey((Serializable)mappingId);
	}

	/**
	 * Returns the dict item mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dict item mapping
	 * @return the dict item mapping, or <code>null</code> if a dict item mapping with the primary key could not be found
	 */
	@Override
	public DictItemMapping fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
				DictItemMappingImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DictItemMapping dictItemMapping = (DictItemMapping)serializable;

		if (dictItemMapping == null) {
			Session session = null;

			try {
				session = openSession();

				dictItemMapping = (DictItemMapping)session.get(DictItemMappingImpl.class,
						primaryKey);

				if (dictItemMapping != null) {
					cacheResult(dictItemMapping);
				}
				else {
					entityCache.putResult(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
						DictItemMappingImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
					DictItemMappingImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dictItemMapping;
	}

	/**
	 * Returns the dict item mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mappingId the primary key of the dict item mapping
	 * @return the dict item mapping, or <code>null</code> if a dict item mapping with the primary key could not be found
	 */
	@Override
	public DictItemMapping fetchByPrimaryKey(long mappingId) {
		return fetchByPrimaryKey((Serializable)mappingId);
	}

	@Override
	public Map<Serializable, DictItemMapping> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DictItemMapping> map = new HashMap<Serializable, DictItemMapping>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DictItemMapping dictItemMapping = fetchByPrimaryKey(primaryKey);

			if (dictItemMapping != null) {
				map.put(primaryKey, dictItemMapping);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
					DictItemMappingImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DictItemMapping)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DICTITEMMAPPING_WHERE_PKS_IN);

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

			for (DictItemMapping dictItemMapping : (List<DictItemMapping>)q.list()) {
				map.put(dictItemMapping.getPrimaryKeyObj(), dictItemMapping);

				cacheResult(dictItemMapping);

				uncachedPrimaryKeys.remove(dictItemMapping.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DictItemMappingModelImpl.ENTITY_CACHE_ENABLED,
					DictItemMappingImpl.class, primaryKey, nullModel);
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
	 * Returns all the dict item mappings.
	 *
	 * @return the dict item mappings
	 */
	@Override
	public List<DictItemMapping> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dict item mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict item mappings
	 * @param end the upper bound of the range of dict item mappings (not inclusive)
	 * @return the range of dict item mappings
	 */
	@Override
	public List<DictItemMapping> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dict item mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict item mappings
	 * @param end the upper bound of the range of dict item mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dict item mappings
	 */
	@Override
	public List<DictItemMapping> findAll(int start, int end,
		OrderByComparator<DictItemMapping> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dict item mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dict item mappings
	 * @param end the upper bound of the range of dict item mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dict item mappings
	 */
	@Override
	public List<DictItemMapping> findAll(int start, int end,
		OrderByComparator<DictItemMapping> orderByComparator,
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

		List<DictItemMapping> list = null;

		if (retrieveFromCache) {
			list = (List<DictItemMapping>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DICTITEMMAPPING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DICTITEMMAPPING;

				if (pagination) {
					sql = sql.concat(DictItemMappingModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DictItemMapping>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DictItemMapping>)QueryUtil.list(q,
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
	 * Removes all the dict item mappings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DictItemMapping dictItemMapping : findAll()) {
			remove(dictItemMapping);
		}
	}

	/**
	 * Returns the number of dict item mappings.
	 *
	 * @return the number of dict item mappings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DICTITEMMAPPING);

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
		return DictItemMappingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dict item mapping persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DictItemMappingImpl.class.getName());
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
	private static final String _SQL_SELECT_DICTITEMMAPPING = "SELECT dictItemMapping FROM DictItemMapping dictItemMapping";
	private static final String _SQL_SELECT_DICTITEMMAPPING_WHERE_PKS_IN = "SELECT dictItemMapping FROM DictItemMapping dictItemMapping WHERE mappingId IN (";
	private static final String _SQL_SELECT_DICTITEMMAPPING_WHERE = "SELECT dictItemMapping FROM DictItemMapping dictItemMapping WHERE ";
	private static final String _SQL_COUNT_DICTITEMMAPPING = "SELECT COUNT(dictItemMapping) FROM DictItemMapping dictItemMapping";
	private static final String _SQL_COUNT_DICTITEMMAPPING_WHERE = "SELECT COUNT(dictItemMapping) FROM DictItemMapping dictItemMapping WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dictItemMapping.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DictItemMapping exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DictItemMapping exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DictItemMappingPersistenceImpl.class);
}