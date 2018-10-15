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

import org.opencps.dossiermgt.exception.NoSuchDossierFileException;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.impl.DossierFileImpl;
import org.opencps.dossiermgt.model.impl.DossierFileModelImpl;
import org.opencps.dossiermgt.service.persistence.DossierFilePersistence;

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
 * The persistence implementation for the dossier file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierFilePersistence
 * @see org.opencps.dossiermgt.service.persistence.DossierFileUtil
 * @generated
 */
@ProviderType
public class DossierFilePersistenceImpl extends BasePersistenceImpl<DossierFile>
	implements DossierFilePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DossierFileUtil} to access the dossier file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DossierFileImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DossierFileModelImpl.UUID_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTNO_COLUMN_BITMASK |
			DossierFileModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dossier files where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dossier files
	 */
	@Override
	public List<DossierFile> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier files where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
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

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierFile dossierFile : list) {
					if (!Objects.equals(uuid, dossierFile.getUuid())) {
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

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

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
				query.append(DossierFileModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier file in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByUuid_First(String uuid,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByUuid_First(uuid, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the first dossier file in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByUuid_First(String uuid,
		OrderByComparator<DossierFile> orderByComparator) {
		List<DossierFile> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier file in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByUuid_Last(String uuid,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByUuid_Last(uuid, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the last dossier file in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByUuid_Last(String uuid,
		OrderByComparator<DossierFile> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DossierFile> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier files before and after the current dossier file in the ordered set where uuid = &#63;.
	 *
	 * @param dossierFileId the primary key of the current dossier file
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile[] findByUuid_PrevAndNext(long dossierFileId,
		String uuid, OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByPrimaryKey(dossierFileId);

		Session session = null;

		try {
			session = openSession();

			DossierFile[] array = new DossierFileImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dossierFile, uuid,
					orderByComparator, true);

			array[1] = dossierFile;

			array[2] = getByUuid_PrevAndNext(session, dossierFile, uuid,
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

	protected DossierFile getByUuid_PrevAndNext(Session session,
		DossierFile dossierFile, String uuid,
		OrderByComparator<DossierFile> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

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
			query.append(DossierFileModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier files where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DossierFile dossierFile : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dossierFile.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dossierFile.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dossierFile.uuid IS NULL OR dossierFile.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierFileModelImpl.UUID_COLUMN_BITMASK |
			DossierFileModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier file where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByUUID_G(uuid, groupId);

		if (dossierFile == null) {
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

			throw new NoSuchDossierFileException(msg.toString());
		}

		return dossierFile;
	}

	/**
	 * Returns the dossier file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dossier file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DossierFile) {
			DossierFile dossierFile = (DossierFile)result;

			if (!Objects.equals(uuid, dossierFile.getUuid()) ||
					(groupId != dossierFile.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

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

				List<DossierFile> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DossierFile dossierFile = list.get(0);

					result = dossierFile;

					cacheResult(dossierFile);
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
			return (DossierFile)result;
		}
	}

	/**
	 * Removes the dossier file where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dossier file that was removed
	 */
	@Override
	public DossierFile removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByUUID_G(uuid, groupId);

		return remove(dossierFile);
	}

	/**
	 * Returns the number of dossier files where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dossierFile.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dossierFile.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dossierFile.uuid IS NULL OR dossierFile.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dossierFile.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierFileModelImpl.UUID_COLUMN_BITMASK |
			DossierFileModelImpl.COMPANYID_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTNO_COLUMN_BITMASK |
			DossierFileModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the dossier files where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dossier files
	 */
	@Override
	public List<DossierFile> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier files where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierFile> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierFile> orderByComparator,
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

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierFile dossierFile : list) {
					if (!Objects.equals(uuid, dossierFile.getUuid()) ||
							(companyId != dossierFile.getCompanyId())) {
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

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

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
				query.append(DossierFileModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier file in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the first dossier file in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierFile> orderByComparator) {
		List<DossierFile> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier file in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the last dossier file in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierFile> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DossierFile> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier files before and after the current dossier file in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dossierFileId the primary key of the current dossier file
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile[] findByUuid_C_PrevAndNext(long dossierFileId,
		String uuid, long companyId,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByPrimaryKey(dossierFileId);

		Session session = null;

		try {
			session = openSession();

			DossierFile[] array = new DossierFileImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dossierFile, uuid,
					companyId, orderByComparator, true);

			array[1] = dossierFile;

			array[2] = getByUuid_C_PrevAndNext(session, dossierFile, uuid,
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

	protected DossierFile getByUuid_C_PrevAndNext(Session session,
		DossierFile dossierFile, String uuid, long companyId,
		OrderByComparator<DossierFile> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

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
			query.append(DossierFileModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier files where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DossierFile dossierFile : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dossierFile.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dossierFile.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dossierFile.uuid IS NULL OR dossierFile.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dossierFile.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_FTN = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDID_FTN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTN =
		new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDID_FTN",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierFileModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierFileModelImpl.FILETEMPLATENO_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTNO_COLUMN_BITMASK |
			DossierFileModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_FTN = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_FTN",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the dossier files where dossierId = &#63; and fileTemplateNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @return the matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTN(long dossierId, String fileTemplateNo) {
		return findByDID_FTN(dossierId, fileTemplateNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTN(long dossierId,
		String fileTemplateNo, int start, int end) {
		return findByDID_FTN(dossierId, fileTemplateNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTN(long dossierId,
		String fileTemplateNo, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return findByDID_FTN(dossierId, fileTemplateNo, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTN(long dossierId,
		String fileTemplateNo, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTN;
			finderArgs = new Object[] { dossierId, fileTemplateNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_FTN;
			finderArgs = new Object[] {
					dossierId, fileTemplateNo,
					
					start, end, orderByComparator
				};
		}

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierFile dossierFile : list) {
					if ((dossierId != dossierFile.getDossierId()) ||
							!Objects.equals(fileTemplateNo,
								dossierFile.getFileTemplateNo())) {
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

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID_FTN_DOSSIERID_2);

			boolean bindFileTemplateNo = false;

			if (fileTemplateNo == null) {
				query.append(_FINDER_COLUMN_DID_FTN_FILETEMPLATENO_1);
			}
			else if (fileTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_DID_FTN_FILETEMPLATENO_3);
			}
			else {
				bindFileTemplateNo = true;

				query.append(_FINDER_COLUMN_DID_FTN_FILETEMPLATENO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindFileTemplateNo) {
					qPos.add(fileTemplateNo);
				}

				if (!pagination) {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID_FTN_First(long dossierId,
		String fileTemplateNo, OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID_FTN_First(dossierId,
				fileTemplateNo, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", fileTemplateNo=");
		msg.append(fileTemplateNo);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID_FTN_First(long dossierId,
		String fileTemplateNo, OrderByComparator<DossierFile> orderByComparator) {
		List<DossierFile> list = findByDID_FTN(dossierId, fileTemplateNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID_FTN_Last(long dossierId,
		String fileTemplateNo, OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID_FTN_Last(dossierId,
				fileTemplateNo, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", fileTemplateNo=");
		msg.append(fileTemplateNo);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID_FTN_Last(long dossierId,
		String fileTemplateNo, OrderByComparator<DossierFile> orderByComparator) {
		int count = countByDID_FTN(dossierId, fileTemplateNo);

		if (count == 0) {
			return null;
		}

		List<DossierFile> list = findByDID_FTN(dossierId, fileTemplateNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63;.
	 *
	 * @param dossierFileId the primary key of the current dossier file
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile[] findByDID_FTN_PrevAndNext(long dossierFileId,
		long dossierId, String fileTemplateNo,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByPrimaryKey(dossierFileId);

		Session session = null;

		try {
			session = openSession();

			DossierFile[] array = new DossierFileImpl[3];

			array[0] = getByDID_FTN_PrevAndNext(session, dossierFile,
					dossierId, fileTemplateNo, orderByComparator, true);

			array[1] = dossierFile;

			array[2] = getByDID_FTN_PrevAndNext(session, dossierFile,
					dossierId, fileTemplateNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierFile getByDID_FTN_PrevAndNext(Session session,
		DossierFile dossierFile, long dossierId, String fileTemplateNo,
		OrderByComparator<DossierFile> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

		query.append(_FINDER_COLUMN_DID_FTN_DOSSIERID_2);

		boolean bindFileTemplateNo = false;

		if (fileTemplateNo == null) {
			query.append(_FINDER_COLUMN_DID_FTN_FILETEMPLATENO_1);
		}
		else if (fileTemplateNo.equals("")) {
			query.append(_FINDER_COLUMN_DID_FTN_FILETEMPLATENO_3);
		}
		else {
			bindFileTemplateNo = true;

			query.append(_FINDER_COLUMN_DID_FTN_FILETEMPLATENO_2);
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
			query.append(DossierFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (bindFileTemplateNo) {
			qPos.add(fileTemplateNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 */
	@Override
	public void removeByDID_FTN(long dossierId, String fileTemplateNo) {
		for (DossierFile dossierFile : findByDID_FTN(dossierId, fileTemplateNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files where dossierId = &#63; and fileTemplateNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByDID_FTN(long dossierId, String fileTemplateNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_FTN;

		Object[] finderArgs = new Object[] { dossierId, fileTemplateNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID_FTN_DOSSIERID_2);

			boolean bindFileTemplateNo = false;

			if (fileTemplateNo == null) {
				query.append(_FINDER_COLUMN_DID_FTN_FILETEMPLATENO_1);
			}
			else if (fileTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_DID_FTN_FILETEMPLATENO_3);
			}
			else {
				bindFileTemplateNo = true;

				query.append(_FINDER_COLUMN_DID_FTN_FILETEMPLATENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

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

	private static final String _FINDER_COLUMN_DID_FTN_DOSSIERID_2 = "dossierFile.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_FTN_FILETEMPLATENO_1 = "dossierFile.fileTemplateNo IS NULL";
	private static final String _FINDER_COLUMN_DID_FTN_FILETEMPLATENO_2 = "dossierFile.fileTemplateNo = ?";
	private static final String _FINDER_COLUMN_DID_FTN_FILETEMPLATENO_3 = "(dossierFile.fileTemplateNo IS NULL OR dossierFile.fileTemplateNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_ = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDID_",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_ = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDID_",
			new String[] { Long.class.getName() },
			DossierFileModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTNO_COLUMN_BITMASK |
			DossierFileModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_ = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dossier files where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @return the matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_(long dossierId) {
		return findByDID_(dossierId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_(long dossierId, int start, int end) {
		return findByDID_(dossierId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_(long dossierId, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return findByDID_(dossierId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_(long dossierId, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_;
			finderArgs = new Object[] { dossierId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_;
			finderArgs = new Object[] { dossierId, start, end, orderByComparator };
		}

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierFile dossierFile : list) {
					if ((dossierId != dossierFile.getDossierId())) {
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

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID__DOSSIERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (!pagination) {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier file in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID__First(long dossierId,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID__First(dossierId, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the first dossier file in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID__First(long dossierId,
		OrderByComparator<DossierFile> orderByComparator) {
		List<DossierFile> list = findByDID_(dossierId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID__Last(long dossierId,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID__Last(dossierId, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID__Last(long dossierId,
		OrderByComparator<DossierFile> orderByComparator) {
		int count = countByDID_(dossierId);

		if (count == 0) {
			return null;
		}

		List<DossierFile> list = findByDID_(dossierId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierFileId the primary key of the current dossier file
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile[] findByDID__PrevAndNext(long dossierFileId,
		long dossierId, OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByPrimaryKey(dossierFileId);

		Session session = null;

		try {
			session = openSession();

			DossierFile[] array = new DossierFileImpl[3];

			array[0] = getByDID__PrevAndNext(session, dossierFile, dossierId,
					orderByComparator, true);

			array[1] = dossierFile;

			array[2] = getByDID__PrevAndNext(session, dossierFile, dossierId,
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

	protected DossierFile getByDID__PrevAndNext(Session session,
		DossierFile dossierFile, long dossierId,
		OrderByComparator<DossierFile> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

		query.append(_FINDER_COLUMN_DID__DOSSIERID_2);

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
			query.append(DossierFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier files where dossierId = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 */
	@Override
	public void removeByDID_(long dossierId) {
		for (DossierFile dossierFile : findByDID_(dossierId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByDID_(long dossierId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_;

		Object[] finderArgs = new Object[] { dossierId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID__DOSSIERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

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

	private static final String _FINDER_COLUMN_DID__DOSSIERID_2 = "dossierFile.dossierId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DOSSIERID =
		new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDossierId",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOSSIERID =
		new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDossierId",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			DossierFileModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierFileModelImpl.REMOVED_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTNO_COLUMN_BITMASK |
			DossierFileModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DOSSIERID = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDossierId",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the dossier files where dossierId = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param removed the removed
	 * @return the matching dossier files
	 */
	@Override
	public List<DossierFile> findByDossierId(long dossierId, boolean removed) {
		return findByDossierId(dossierId, removed, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files where dossierId = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDossierId(long dossierId, boolean removed,
		int start, int end) {
		return findByDossierId(dossierId, removed, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDossierId(long dossierId, boolean removed,
		int start, int end, OrderByComparator<DossierFile> orderByComparator) {
		return findByDossierId(dossierId, removed, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDossierId(long dossierId, boolean removed,
		int start, int end, OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOSSIERID;
			finderArgs = new Object[] { dossierId, removed };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DOSSIERID;
			finderArgs = new Object[] {
					dossierId, removed,
					
					start, end, orderByComparator
				};
		}

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierFile dossierFile : list) {
					if ((dossierId != dossierFile.getDossierId()) ||
							(removed != dossierFile.isRemoved())) {
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

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DOSSIERID_DOSSIERID_2);

			query.append(_FINDER_COLUMN_DOSSIERID_REMOVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				qPos.add(removed);

				if (!pagination) {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDossierId_First(long dossierId, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDossierId_First(dossierId, removed,
				orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", removed=");
		msg.append(removed);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDossierId_First(long dossierId, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		List<DossierFile> list = findByDossierId(dossierId, removed, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDossierId_Last(long dossierId, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDossierId_Last(dossierId, removed,
				orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", removed=");
		msg.append(removed);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDossierId_Last(long dossierId, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		int count = countByDossierId(dossierId, removed);

		if (count == 0) {
			return null;
		}

		List<DossierFile> list = findByDossierId(dossierId, removed, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and removed = &#63;.
	 *
	 * @param dossierFileId the primary key of the current dossier file
	 * @param dossierId the dossier ID
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile[] findByDossierId_PrevAndNext(long dossierFileId,
		long dossierId, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByPrimaryKey(dossierFileId);

		Session session = null;

		try {
			session = openSession();

			DossierFile[] array = new DossierFileImpl[3];

			array[0] = getByDossierId_PrevAndNext(session, dossierFile,
					dossierId, removed, orderByComparator, true);

			array[1] = dossierFile;

			array[2] = getByDossierId_PrevAndNext(session, dossierFile,
					dossierId, removed, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierFile getByDossierId_PrevAndNext(Session session,
		DossierFile dossierFile, long dossierId, boolean removed,
		OrderByComparator<DossierFile> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

		query.append(_FINDER_COLUMN_DOSSIERID_DOSSIERID_2);

		query.append(_FINDER_COLUMN_DOSSIERID_REMOVED_2);

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
			query.append(DossierFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		qPos.add(removed);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier files where dossierId = &#63; and removed = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param removed the removed
	 */
	@Override
	public void removeByDossierId(long dossierId, boolean removed) {
		for (DossierFile dossierFile : findByDossierId(dossierId, removed,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files where dossierId = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param removed the removed
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByDossierId(long dossierId, boolean removed) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DOSSIERID;

		Object[] finderArgs = new Object[] { dossierId, removed };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DOSSIERID_DOSSIERID_2);

			query.append(_FINDER_COLUMN_DOSSIERID_REMOVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				qPos.add(removed);

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

	private static final String _FINDER_COLUMN_DOSSIERID_DOSSIERID_2 = "dossierFile.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DOSSIERID_REMOVED_2 = "dossierFile.removed = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_D_DPT = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByD_DPT",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_DPT = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByD_DPT",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Boolean.class.getName()
			},
			DossierFileModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTTYPE_COLUMN_BITMASK |
			DossierFileModelImpl.REMOVED_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTNO_COLUMN_BITMASK |
			DossierFileModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_D_DPT = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByD_DPT",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @return the matching dossier files
	 */
	@Override
	public List<DossierFile> findByD_DPT(long dossierId, int dossierPartType,
		boolean removed) {
		return findByD_DPT(dossierId, dossierPartType, removed,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByD_DPT(long dossierId, int dossierPartType,
		boolean removed, int start, int end) {
		return findByD_DPT(dossierId, dossierPartType, removed, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByD_DPT(long dossierId, int dossierPartType,
		boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return findByD_DPT(dossierId, dossierPartType, removed, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByD_DPT(long dossierId, int dossierPartType,
		boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_DPT;
			finderArgs = new Object[] { dossierId, dossierPartType, removed };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_D_DPT;
			finderArgs = new Object[] {
					dossierId, dossierPartType, removed,
					
					start, end, orderByComparator
				};
		}

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierFile dossierFile : list) {
					if ((dossierId != dossierFile.getDossierId()) ||
							(dossierPartType != dossierFile.getDossierPartType()) ||
							(removed != dossierFile.isRemoved())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_D_DPT_DOSSIERID_2);

			query.append(_FINDER_COLUMN_D_DPT_DOSSIERPARTTYPE_2);

			query.append(_FINDER_COLUMN_D_DPT_REMOVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				qPos.add(dossierPartType);

				qPos.add(removed);

				if (!pagination) {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByD_DPT_First(long dossierId, int dossierPartType,
		boolean removed, OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByD_DPT_First(dossierId,
				dossierPartType, removed, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", dossierPartType=");
		msg.append(dossierPartType);

		msg.append(", removed=");
		msg.append(removed);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByD_DPT_First(long dossierId, int dossierPartType,
		boolean removed, OrderByComparator<DossierFile> orderByComparator) {
		List<DossierFile> list = findByD_DPT(dossierId, dossierPartType,
				removed, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByD_DPT_Last(long dossierId, int dossierPartType,
		boolean removed, OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByD_DPT_Last(dossierId, dossierPartType,
				removed, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", dossierPartType=");
		msg.append(dossierPartType);

		msg.append(", removed=");
		msg.append(removed);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByD_DPT_Last(long dossierId, int dossierPartType,
		boolean removed, OrderByComparator<DossierFile> orderByComparator) {
		int count = countByD_DPT(dossierId, dossierPartType, removed);

		if (count == 0) {
			return null;
		}

		List<DossierFile> list = findByD_DPT(dossierId, dossierPartType,
				removed, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * @param dossierFileId the primary key of the current dossier file
	 * @param dossierId the dossier ID
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile[] findByD_DPT_PrevAndNext(long dossierFileId,
		long dossierId, int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByPrimaryKey(dossierFileId);

		Session session = null;

		try {
			session = openSession();

			DossierFile[] array = new DossierFileImpl[3];

			array[0] = getByD_DPT_PrevAndNext(session, dossierFile, dossierId,
					dossierPartType, removed, orderByComparator, true);

			array[1] = dossierFile;

			array[2] = getByD_DPT_PrevAndNext(session, dossierFile, dossierId,
					dossierPartType, removed, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierFile getByD_DPT_PrevAndNext(Session session,
		DossierFile dossierFile, long dossierId, int dossierPartType,
		boolean removed, OrderByComparator<DossierFile> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

		query.append(_FINDER_COLUMN_D_DPT_DOSSIERID_2);

		query.append(_FINDER_COLUMN_D_DPT_DOSSIERPARTTYPE_2);

		query.append(_FINDER_COLUMN_D_DPT_REMOVED_2);

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
			query.append(DossierFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		qPos.add(dossierPartType);

		qPos.add(removed);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 */
	@Override
	public void removeByD_DPT(long dossierId, int dossierPartType,
		boolean removed) {
		for (DossierFile dossierFile : findByD_DPT(dossierId, dossierPartType,
				removed, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByD_DPT(long dossierId, int dossierPartType, boolean removed) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_D_DPT;

		Object[] finderArgs = new Object[] { dossierId, dossierPartType, removed };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_D_DPT_DOSSIERID_2);

			query.append(_FINDER_COLUMN_D_DPT_DOSSIERPARTTYPE_2);

			query.append(_FINDER_COLUMN_D_DPT_REMOVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				qPos.add(dossierPartType);

				qPos.add(removed);

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

	private static final String _FINDER_COLUMN_D_DPT_DOSSIERID_2 = "dossierFile.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_D_DPT_DOSSIERPARTTYPE_2 = "dossierFile.dossierPartType = ? AND ";
	private static final String _FINDER_COLUMN_D_DPT_REMOVED_2 = "dossierFile.removed = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_D_RUID = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByD_RUID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			DossierFileModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierFileModelImpl.REFERENCEUID_COLUMN_BITMASK |
			DossierFileModelImpl.REMOVED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_D_RUID = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByD_RUID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns the dossier file where dossierId = &#63; and referenceUid = &#63; and removed = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @param removed the removed
	 * @return the matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByD_RUID(long dossierId, String referenceUid,
		boolean removed) throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByD_RUID(dossierId, referenceUid, removed);

		if (dossierFile == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossierId=");
			msg.append(dossierId);

			msg.append(", referenceUid=");
			msg.append(referenceUid);

			msg.append(", removed=");
			msg.append(removed);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierFileException(msg.toString());
		}

		return dossierFile;
	}

	/**
	 * Returns the dossier file where dossierId = &#63; and referenceUid = &#63; and removed = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @param removed the removed
	 * @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByD_RUID(long dossierId, String referenceUid,
		boolean removed) {
		return fetchByD_RUID(dossierId, referenceUid, removed, true);
	}

	/**
	 * Returns the dossier file where dossierId = &#63; and referenceUid = &#63; and removed = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @param removed the removed
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByD_RUID(long dossierId, String referenceUid,
		boolean removed, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossierId, referenceUid, removed };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_D_RUID,
					finderArgs, this);
		}

		if (result instanceof DossierFile) {
			DossierFile dossierFile = (DossierFile)result;

			if ((dossierId != dossierFile.getDossierId()) ||
					!Objects.equals(referenceUid, dossierFile.getReferenceUid()) ||
					(removed != dossierFile.isRemoved())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_D_RUID_DOSSIERID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_D_RUID_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_D_RUID_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_D_RUID_REFERENCEUID_2);
			}

			query.append(_FINDER_COLUMN_D_RUID_REMOVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
				}

				qPos.add(removed);

				List<DossierFile> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_D_RUID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierFilePersistenceImpl.fetchByD_RUID(long, String, boolean, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierFile dossierFile = list.get(0);

					result = dossierFile;

					cacheResult(dossierFile);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_D_RUID, finderArgs);

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
			return (DossierFile)result;
		}
	}

	/**
	 * Removes the dossier file where dossierId = &#63; and referenceUid = &#63; and removed = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @param removed the removed
	 * @return the dossier file that was removed
	 */
	@Override
	public DossierFile removeByD_RUID(long dossierId, String referenceUid,
		boolean removed) throws NoSuchDossierFileException {
		DossierFile dossierFile = findByD_RUID(dossierId, referenceUid, removed);

		return remove(dossierFile);
	}

	/**
	 * Returns the number of dossier files where dossierId = &#63; and referenceUid = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @param removed the removed
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByD_RUID(long dossierId, String referenceUid,
		boolean removed) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_D_RUID;

		Object[] finderArgs = new Object[] { dossierId, referenceUid, removed };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_D_RUID_DOSSIERID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_D_RUID_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_D_RUID_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_D_RUID_REFERENCEUID_2);
			}

			query.append(_FINDER_COLUMN_D_RUID_REMOVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
				}

				qPos.add(removed);

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

	private static final String _FINDER_COLUMN_D_RUID_DOSSIERID_2 = "dossierFile.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_D_RUID_REFERENCEUID_1 = "dossierFile.referenceUid IS NULL AND ";
	private static final String _FINDER_COLUMN_D_RUID_REFERENCEUID_2 = "dossierFile.referenceUid = ? AND ";
	private static final String _FINDER_COLUMN_D_RUID_REFERENCEUID_3 = "(dossierFile.referenceUid IS NULL OR dossierFile.referenceUid = '') AND ";
	private static final String _FINDER_COLUMN_D_RUID_REMOVED_2 = "dossierFile.removed = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DID_REF = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDID_REF",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierFileModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierFileModelImpl.REFERENCEUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_REF = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_REF",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the dossier file where dossierId = &#63; and referenceUid = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @return the matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID_REF(long dossierId, String referenceUid)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID_REF(dossierId, referenceUid);

		if (dossierFile == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossierId=");
			msg.append(dossierId);

			msg.append(", referenceUid=");
			msg.append(referenceUid);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierFileException(msg.toString());
		}

		return dossierFile;
	}

	/**
	 * Returns the dossier file where dossierId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID_REF(long dossierId, String referenceUid) {
		return fetchByDID_REF(dossierId, referenceUid, true);
	}

	/**
	 * Returns the dossier file where dossierId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID_REF(long dossierId, String referenceUid,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossierId, referenceUid };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DID_REF,
					finderArgs, this);
		}

		if (result instanceof DossierFile) {
			DossierFile dossierFile = (DossierFile)result;

			if ((dossierId != dossierFile.getDossierId()) ||
					!Objects.equals(referenceUid, dossierFile.getReferenceUid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID_REF_DOSSIERID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_DID_REF_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_DID_REF_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_DID_REF_REFERENCEUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
				}

				List<DossierFile> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DID_REF,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierFilePersistenceImpl.fetchByDID_REF(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierFile dossierFile = list.get(0);

					result = dossierFile;

					cacheResult(dossierFile);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_REF,
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
			return (DossierFile)result;
		}
	}

	/**
	 * Removes the dossier file where dossierId = &#63; and referenceUid = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @return the dossier file that was removed
	 */
	@Override
	public DossierFile removeByDID_REF(long dossierId, String referenceUid)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByDID_REF(dossierId, referenceUid);

		return remove(dossierFile);
	}

	/**
	 * Returns the number of dossier files where dossierId = &#63; and referenceUid = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByDID_REF(long dossierId, String referenceUid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_REF;

		Object[] finderArgs = new Object[] { dossierId, referenceUid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID_REF_DOSSIERID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_DID_REF_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_DID_REF_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_DID_REF_REFERENCEUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
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

	private static final String _FINDER_COLUMN_DID_REF_DOSSIERID_2 = "dossierFile.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_REF_REFERENCEUID_1 = "dossierFile.referenceUid IS NULL";
	private static final String _FINDER_COLUMN_DID_REF_REFERENCEUID_2 = "dossierFile.referenceUid = ?";
	private static final String _FINDER_COLUMN_DID_REF_REFERENCEUID_3 = "(dossierFile.referenceUid IS NULL OR dossierFile.referenceUid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_ISN = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDID_ISN",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_ISN =
		new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDID_ISN",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			DossierFileModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierFileModelImpl.ISNEW_COLUMN_BITMASK |
			DossierFileModelImpl.REMOVED_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTNO_COLUMN_BITMASK |
			DossierFileModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_ISN = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_ISN",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param isNew the is new
	 * @param removed the removed
	 * @return the matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_ISN(long dossierId, boolean isNew,
		boolean removed) {
		return findByDID_ISN(dossierId, isNew, removed, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param isNew the is new
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_ISN(long dossierId, boolean isNew,
		boolean removed, int start, int end) {
		return findByDID_ISN(dossierId, isNew, removed, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param isNew the is new
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_ISN(long dossierId, boolean isNew,
		boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return findByDID_ISN(dossierId, isNew, removed, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param isNew the is new
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_ISN(long dossierId, boolean isNew,
		boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_ISN;
			finderArgs = new Object[] { dossierId, isNew, removed };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_ISN;
			finderArgs = new Object[] {
					dossierId, isNew, removed,
					
					start, end, orderByComparator
				};
		}

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierFile dossierFile : list) {
					if ((dossierId != dossierFile.getDossierId()) ||
							(isNew != dossierFile.isIsNew()) ||
							(removed != dossierFile.isRemoved())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID_ISN_DOSSIERID_2);

			query.append(_FINDER_COLUMN_DID_ISN_ISNEW_2);

			query.append(_FINDER_COLUMN_DID_ISN_REMOVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				qPos.add(isNew);

				qPos.add(removed);

				if (!pagination) {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param isNew the is new
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID_ISN_First(long dossierId, boolean isNew,
		boolean removed, OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID_ISN_First(dossierId, isNew,
				removed, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", isNew=");
		msg.append(isNew);

		msg.append(", removed=");
		msg.append(removed);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param isNew the is new
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID_ISN_First(long dossierId, boolean isNew,
		boolean removed, OrderByComparator<DossierFile> orderByComparator) {
		List<DossierFile> list = findByDID_ISN(dossierId, isNew, removed, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param isNew the is new
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID_ISN_Last(long dossierId, boolean isNew,
		boolean removed, OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID_ISN_Last(dossierId, isNew,
				removed, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", isNew=");
		msg.append(isNew);

		msg.append(", removed=");
		msg.append(removed);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param isNew the is new
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID_ISN_Last(long dossierId, boolean isNew,
		boolean removed, OrderByComparator<DossierFile> orderByComparator) {
		int count = countByDID_ISN(dossierId, isNew, removed);

		if (count == 0) {
			return null;
		}

		List<DossierFile> list = findByDID_ISN(dossierId, isNew, removed,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	 *
	 * @param dossierFileId the primary key of the current dossier file
	 * @param dossierId the dossier ID
	 * @param isNew the is new
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile[] findByDID_ISN_PrevAndNext(long dossierFileId,
		long dossierId, boolean isNew, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByPrimaryKey(dossierFileId);

		Session session = null;

		try {
			session = openSession();

			DossierFile[] array = new DossierFileImpl[3];

			array[0] = getByDID_ISN_PrevAndNext(session, dossierFile,
					dossierId, isNew, removed, orderByComparator, true);

			array[1] = dossierFile;

			array[2] = getByDID_ISN_PrevAndNext(session, dossierFile,
					dossierId, isNew, removed, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierFile getByDID_ISN_PrevAndNext(Session session,
		DossierFile dossierFile, long dossierId, boolean isNew,
		boolean removed, OrderByComparator<DossierFile> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

		query.append(_FINDER_COLUMN_DID_ISN_DOSSIERID_2);

		query.append(_FINDER_COLUMN_DID_ISN_ISNEW_2);

		query.append(_FINDER_COLUMN_DID_ISN_REMOVED_2);

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
			query.append(DossierFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		qPos.add(isNew);

		qPos.add(removed);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param isNew the is new
	 * @param removed the removed
	 */
	@Override
	public void removeByDID_ISN(long dossierId, boolean isNew, boolean removed) {
		for (DossierFile dossierFile : findByDID_ISN(dossierId, isNew, removed,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param isNew the is new
	 * @param removed the removed
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByDID_ISN(long dossierId, boolean isNew, boolean removed) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_ISN;

		Object[] finderArgs = new Object[] { dossierId, isNew, removed };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID_ISN_DOSSIERID_2);

			query.append(_FINDER_COLUMN_DID_ISN_ISNEW_2);

			query.append(_FINDER_COLUMN_DID_ISN_REMOVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				qPos.add(isNew);

				qPos.add(removed);

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

	private static final String _FINDER_COLUMN_DID_ISN_DOSSIERID_2 = "dossierFile.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_ISN_ISNEW_2 = "dossierFile.isNew = ? AND ";
	private static final String _FINDER_COLUMN_DID_ISN_REMOVED_2 = "dossierFile.removed = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_FTNO = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDID_FTNO",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTNO =
		new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDID_FTNO",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			DossierFileModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierFileModelImpl.FILETEMPLATENO_COLUMN_BITMASK |
			DossierFileModelImpl.REMOVED_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTNO_COLUMN_BITMASK |
			DossierFileModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_FTNO = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_FTNO",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param removed the removed
	 * @return the matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTNO(long dossierId,
		String fileTemplateNo, boolean removed) {
		return findByDID_FTNO(dossierId, fileTemplateNo, removed,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTNO(long dossierId,
		String fileTemplateNo, boolean removed, int start, int end) {
		return findByDID_FTNO(dossierId, fileTemplateNo, removed, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTNO(long dossierId,
		String fileTemplateNo, boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return findByDID_FTNO(dossierId, fileTemplateNo, removed, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTNO(long dossierId,
		String fileTemplateNo, boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTNO;
			finderArgs = new Object[] { dossierId, fileTemplateNo, removed };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_FTNO;
			finderArgs = new Object[] {
					dossierId, fileTemplateNo, removed,
					
					start, end, orderByComparator
				};
		}

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierFile dossierFile : list) {
					if ((dossierId != dossierFile.getDossierId()) ||
							!Objects.equals(fileTemplateNo,
								dossierFile.getFileTemplateNo()) ||
							(removed != dossierFile.isRemoved())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID_FTNO_DOSSIERID_2);

			boolean bindFileTemplateNo = false;

			if (fileTemplateNo == null) {
				query.append(_FINDER_COLUMN_DID_FTNO_FILETEMPLATENO_1);
			}
			else if (fileTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_DID_FTNO_FILETEMPLATENO_3);
			}
			else {
				bindFileTemplateNo = true;

				query.append(_FINDER_COLUMN_DID_FTNO_FILETEMPLATENO_2);
			}

			query.append(_FINDER_COLUMN_DID_FTNO_REMOVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindFileTemplateNo) {
					qPos.add(fileTemplateNo);
				}

				qPos.add(removed);

				if (!pagination) {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID_FTNO_First(long dossierId,
		String fileTemplateNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID_FTNO_First(dossierId,
				fileTemplateNo, removed, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", fileTemplateNo=");
		msg.append(fileTemplateNo);

		msg.append(", removed=");
		msg.append(removed);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID_FTNO_First(long dossierId,
		String fileTemplateNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		List<DossierFile> list = findByDID_FTNO(dossierId, fileTemplateNo,
				removed, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID_FTNO_Last(long dossierId,
		String fileTemplateNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID_FTNO_Last(dossierId,
				fileTemplateNo, removed, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", fileTemplateNo=");
		msg.append(fileTemplateNo);

		msg.append(", removed=");
		msg.append(removed);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID_FTNO_Last(long dossierId,
		String fileTemplateNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		int count = countByDID_FTNO(dossierId, fileTemplateNo, removed);

		if (count == 0) {
			return null;
		}

		List<DossierFile> list = findByDID_FTNO(dossierId, fileTemplateNo,
				removed, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	 *
	 * @param dossierFileId the primary key of the current dossier file
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile[] findByDID_FTNO_PrevAndNext(long dossierFileId,
		long dossierId, String fileTemplateNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByPrimaryKey(dossierFileId);

		Session session = null;

		try {
			session = openSession();

			DossierFile[] array = new DossierFileImpl[3];

			array[0] = getByDID_FTNO_PrevAndNext(session, dossierFile,
					dossierId, fileTemplateNo, removed, orderByComparator, true);

			array[1] = dossierFile;

			array[2] = getByDID_FTNO_PrevAndNext(session, dossierFile,
					dossierId, fileTemplateNo, removed, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierFile getByDID_FTNO_PrevAndNext(Session session,
		DossierFile dossierFile, long dossierId, String fileTemplateNo,
		boolean removed, OrderByComparator<DossierFile> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

		query.append(_FINDER_COLUMN_DID_FTNO_DOSSIERID_2);

		boolean bindFileTemplateNo = false;

		if (fileTemplateNo == null) {
			query.append(_FINDER_COLUMN_DID_FTNO_FILETEMPLATENO_1);
		}
		else if (fileTemplateNo.equals("")) {
			query.append(_FINDER_COLUMN_DID_FTNO_FILETEMPLATENO_3);
		}
		else {
			bindFileTemplateNo = true;

			query.append(_FINDER_COLUMN_DID_FTNO_FILETEMPLATENO_2);
		}

		query.append(_FINDER_COLUMN_DID_FTNO_REMOVED_2);

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
			query.append(DossierFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (bindFileTemplateNo) {
			qPos.add(fileTemplateNo);
		}

		qPos.add(removed);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param removed the removed
	 */
	@Override
	public void removeByDID_FTNO(long dossierId, String fileTemplateNo,
		boolean removed) {
		for (DossierFile dossierFile : findByDID_FTNO(dossierId,
				fileTemplateNo, removed, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param removed the removed
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByDID_FTNO(long dossierId, String fileTemplateNo,
		boolean removed) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_FTNO;

		Object[] finderArgs = new Object[] { dossierId, fileTemplateNo, removed };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID_FTNO_DOSSIERID_2);

			boolean bindFileTemplateNo = false;

			if (fileTemplateNo == null) {
				query.append(_FINDER_COLUMN_DID_FTNO_FILETEMPLATENO_1);
			}
			else if (fileTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_DID_FTNO_FILETEMPLATENO_3);
			}
			else {
				bindFileTemplateNo = true;

				query.append(_FINDER_COLUMN_DID_FTNO_FILETEMPLATENO_2);
			}

			query.append(_FINDER_COLUMN_DID_FTNO_REMOVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindFileTemplateNo) {
					qPos.add(fileTemplateNo);
				}

				qPos.add(removed);

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

	private static final String _FINDER_COLUMN_DID_FTNO_DOSSIERID_2 = "dossierFile.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_FILETEMPLATENO_1 = "dossierFile.fileTemplateNo IS NULL AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_FILETEMPLATENO_2 = "dossierFile.fileTemplateNo = ? AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_FILETEMPLATENO_3 = "(dossierFile.fileTemplateNo IS NULL OR dossierFile.fileTemplateNo = '') AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_REMOVED_2 = "dossierFile.removed = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_DPNO = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDID_DPNO",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_DPNO =
		new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDID_DPNO",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			DossierFileModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTNO_COLUMN_BITMASK |
			DossierFileModelImpl.REMOVED_COLUMN_BITMASK |
			DossierFileModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_DPNO = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_DPNO",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @param removed the removed
	 * @return the matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_DPNO(long dossierId,
		String dossierPartNo, boolean removed) {
		return findByDID_DPNO(dossierId, dossierPartNo, removed,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_DPNO(long dossierId,
		String dossierPartNo, boolean removed, int start, int end) {
		return findByDID_DPNO(dossierId, dossierPartNo, removed, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_DPNO(long dossierId,
		String dossierPartNo, boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return findByDID_DPNO(dossierId, dossierPartNo, removed, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_DPNO(long dossierId,
		String dossierPartNo, boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_DPNO;
			finderArgs = new Object[] { dossierId, dossierPartNo, removed };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_DPNO;
			finderArgs = new Object[] {
					dossierId, dossierPartNo, removed,
					
					start, end, orderByComparator
				};
		}

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierFile dossierFile : list) {
					if ((dossierId != dossierFile.getDossierId()) ||
							!Objects.equals(dossierPartNo,
								dossierFile.getDossierPartNo()) ||
							(removed != dossierFile.isRemoved())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID_DPNO_DOSSIERID_2);

			boolean bindDossierPartNo = false;

			if (dossierPartNo == null) {
				query.append(_FINDER_COLUMN_DID_DPNO_DOSSIERPARTNO_1);
			}
			else if (dossierPartNo.equals("")) {
				query.append(_FINDER_COLUMN_DID_DPNO_DOSSIERPARTNO_3);
			}
			else {
				bindDossierPartNo = true;

				query.append(_FINDER_COLUMN_DID_DPNO_DOSSIERPARTNO_2);
			}

			query.append(_FINDER_COLUMN_DID_DPNO_REMOVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindDossierPartNo) {
					qPos.add(dossierPartNo);
				}

				qPos.add(removed);

				if (!pagination) {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID_DPNO_First(long dossierId,
		String dossierPartNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID_DPNO_First(dossierId,
				dossierPartNo, removed, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", dossierPartNo=");
		msg.append(dossierPartNo);

		msg.append(", removed=");
		msg.append(removed);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID_DPNO_First(long dossierId,
		String dossierPartNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		List<DossierFile> list = findByDID_DPNO(dossierId, dossierPartNo,
				removed, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID_DPNO_Last(long dossierId,
		String dossierPartNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID_DPNO_Last(dossierId,
				dossierPartNo, removed, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", dossierPartNo=");
		msg.append(dossierPartNo);

		msg.append(", removed=");
		msg.append(removed);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID_DPNO_Last(long dossierId,
		String dossierPartNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		int count = countByDID_DPNO(dossierId, dossierPartNo, removed);

		if (count == 0) {
			return null;
		}

		List<DossierFile> list = findByDID_DPNO(dossierId, dossierPartNo,
				removed, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	 *
	 * @param dossierFileId the primary key of the current dossier file
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile[] findByDID_DPNO_PrevAndNext(long dossierFileId,
		long dossierId, String dossierPartNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByPrimaryKey(dossierFileId);

		Session session = null;

		try {
			session = openSession();

			DossierFile[] array = new DossierFileImpl[3];

			array[0] = getByDID_DPNO_PrevAndNext(session, dossierFile,
					dossierId, dossierPartNo, removed, orderByComparator, true);

			array[1] = dossierFile;

			array[2] = getByDID_DPNO_PrevAndNext(session, dossierFile,
					dossierId, dossierPartNo, removed, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierFile getByDID_DPNO_PrevAndNext(Session session,
		DossierFile dossierFile, long dossierId, String dossierPartNo,
		boolean removed, OrderByComparator<DossierFile> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

		query.append(_FINDER_COLUMN_DID_DPNO_DOSSIERID_2);

		boolean bindDossierPartNo = false;

		if (dossierPartNo == null) {
			query.append(_FINDER_COLUMN_DID_DPNO_DOSSIERPARTNO_1);
		}
		else if (dossierPartNo.equals("")) {
			query.append(_FINDER_COLUMN_DID_DPNO_DOSSIERPARTNO_3);
		}
		else {
			bindDossierPartNo = true;

			query.append(_FINDER_COLUMN_DID_DPNO_DOSSIERPARTNO_2);
		}

		query.append(_FINDER_COLUMN_DID_DPNO_REMOVED_2);

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
			query.append(DossierFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (bindDossierPartNo) {
			qPos.add(dossierPartNo);
		}

		qPos.add(removed);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @param removed the removed
	 */
	@Override
	public void removeByDID_DPNO(long dossierId, String dossierPartNo,
		boolean removed) {
		for (DossierFile dossierFile : findByDID_DPNO(dossierId, dossierPartNo,
				removed, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param dossierPartNo the dossier part no
	 * @param removed the removed
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByDID_DPNO(long dossierId, String dossierPartNo,
		boolean removed) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_DPNO;

		Object[] finderArgs = new Object[] { dossierId, dossierPartNo, removed };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID_DPNO_DOSSIERID_2);

			boolean bindDossierPartNo = false;

			if (dossierPartNo == null) {
				query.append(_FINDER_COLUMN_DID_DPNO_DOSSIERPARTNO_1);
			}
			else if (dossierPartNo.equals("")) {
				query.append(_FINDER_COLUMN_DID_DPNO_DOSSIERPARTNO_3);
			}
			else {
				bindDossierPartNo = true;

				query.append(_FINDER_COLUMN_DID_DPNO_DOSSIERPARTNO_2);
			}

			query.append(_FINDER_COLUMN_DID_DPNO_REMOVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindDossierPartNo) {
					qPos.add(dossierPartNo);
				}

				qPos.add(removed);

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

	private static final String _FINDER_COLUMN_DID_DPNO_DOSSIERID_2 = "dossierFile.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_DPNO_DOSSIERPARTNO_1 = "dossierFile.dossierPartNo IS NULL AND ";
	private static final String _FINDER_COLUMN_DID_DPNO_DOSSIERPARTNO_2 = "dossierFile.dossierPartNo = ? AND ";
	private static final String _FINDER_COLUMN_DID_DPNO_DOSSIERPARTNO_3 = "(dossierFile.dossierPartNo IS NULL OR dossierFile.dossierPartNo = '') AND ";
	private static final String _FINDER_COLUMN_DID_DPNO_REMOVED_2 = "dossierFile.removed = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_FTNO_DPT =
		new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDID_FTNO_DPT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTNO_DPT =
		new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDID_FTNO_DPT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Boolean.class.getName()
			},
			DossierFileModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierFileModelImpl.FILETEMPLATENO_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTTYPE_COLUMN_BITMASK |
			DossierFileModelImpl.REMOVED_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTNO_COLUMN_BITMASK |
			DossierFileModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID_FTNO_DPT = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID_FTNO_DPT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Boolean.class.getName()
			});

	/**
	 * Returns all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @return the matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTNO_DPT(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed) {
		return findByDID_FTNO_DPT(dossierId, fileTemplateNo, dossierPartType,
			removed, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTNO_DPT(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed, int start,
		int end) {
		return findByDID_FTNO_DPT(dossierId, fileTemplateNo, dossierPartType,
			removed, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTNO_DPT(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed, int start,
		int end, OrderByComparator<DossierFile> orderByComparator) {
		return findByDID_FTNO_DPT(dossierId, fileTemplateNo, dossierPartType,
			removed, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTNO_DPT(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed, int start,
		int end, OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTNO_DPT;
			finderArgs = new Object[] {
					dossierId, fileTemplateNo, dossierPartType, removed
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_FTNO_DPT;
			finderArgs = new Object[] {
					dossierId, fileTemplateNo, dossierPartType, removed,
					
					start, end, orderByComparator
				};
		}

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierFile dossierFile : list) {
					if ((dossierId != dossierFile.getDossierId()) ||
							!Objects.equals(fileTemplateNo,
								dossierFile.getFileTemplateNo()) ||
							(dossierPartType != dossierFile.getDossierPartType()) ||
							(removed != dossierFile.isRemoved())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_DOSSIERID_2);

			boolean bindFileTemplateNo = false;

			if (fileTemplateNo == null) {
				query.append(_FINDER_COLUMN_DID_FTNO_DPT_FILETEMPLATENO_1);
			}
			else if (fileTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_DID_FTNO_DPT_FILETEMPLATENO_3);
			}
			else {
				bindFileTemplateNo = true;

				query.append(_FINDER_COLUMN_DID_FTNO_DPT_FILETEMPLATENO_2);
			}

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_DOSSIERPARTTYPE_2);

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_REMOVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindFileTemplateNo) {
					qPos.add(fileTemplateNo);
				}

				qPos.add(dossierPartType);

				qPos.add(removed);

				if (!pagination) {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID_FTNO_DPT_First(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID_FTNO_DPT_First(dossierId,
				fileTemplateNo, dossierPartType, removed, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", fileTemplateNo=");
		msg.append(fileTemplateNo);

		msg.append(", dossierPartType=");
		msg.append(dossierPartType);

		msg.append(", removed=");
		msg.append(removed);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID_FTNO_DPT_First(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		List<DossierFile> list = findByDID_FTNO_DPT(dossierId, fileTemplateNo,
				dossierPartType, removed, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID_FTNO_DPT_Last(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID_FTNO_DPT_Last(dossierId,
				fileTemplateNo, dossierPartType, removed, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", fileTemplateNo=");
		msg.append(fileTemplateNo);

		msg.append(", dossierPartType=");
		msg.append(dossierPartType);

		msg.append(", removed=");
		msg.append(removed);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID_FTNO_DPT_Last(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		int count = countByDID_FTNO_DPT(dossierId, fileTemplateNo,
				dossierPartType, removed);

		if (count == 0) {
			return null;
		}

		List<DossierFile> list = findByDID_FTNO_DPT(dossierId, fileTemplateNo,
				dossierPartType, removed, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * @param dossierFileId the primary key of the current dossier file
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile[] findByDID_FTNO_DPT_PrevAndNext(long dossierFileId,
		long dossierId, String fileTemplateNo, int dossierPartType,
		boolean removed, OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByPrimaryKey(dossierFileId);

		Session session = null;

		try {
			session = openSession();

			DossierFile[] array = new DossierFileImpl[3];

			array[0] = getByDID_FTNO_DPT_PrevAndNext(session, dossierFile,
					dossierId, fileTemplateNo, dossierPartType, removed,
					orderByComparator, true);

			array[1] = dossierFile;

			array[2] = getByDID_FTNO_DPT_PrevAndNext(session, dossierFile,
					dossierId, fileTemplateNo, dossierPartType, removed,
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

	protected DossierFile getByDID_FTNO_DPT_PrevAndNext(Session session,
		DossierFile dossierFile, long dossierId, String fileTemplateNo,
		int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

		query.append(_FINDER_COLUMN_DID_FTNO_DPT_DOSSIERID_2);

		boolean bindFileTemplateNo = false;

		if (fileTemplateNo == null) {
			query.append(_FINDER_COLUMN_DID_FTNO_DPT_FILETEMPLATENO_1);
		}
		else if (fileTemplateNo.equals("")) {
			query.append(_FINDER_COLUMN_DID_FTNO_DPT_FILETEMPLATENO_3);
		}
		else {
			bindFileTemplateNo = true;

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_FILETEMPLATENO_2);
		}

		query.append(_FINDER_COLUMN_DID_FTNO_DPT_DOSSIERPARTTYPE_2);

		query.append(_FINDER_COLUMN_DID_FTNO_DPT_REMOVED_2);

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
			query.append(DossierFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (bindFileTemplateNo) {
			qPos.add(fileTemplateNo);
		}

		qPos.add(dossierPartType);

		qPos.add(removed);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 */
	@Override
	public void removeByDID_FTNO_DPT(long dossierId, String fileTemplateNo,
		int dossierPartType, boolean removed) {
		for (DossierFile dossierFile : findByDID_FTNO_DPT(dossierId,
				fileTemplateNo, dossierPartType, removed, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param removed the removed
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByDID_FTNO_DPT(long dossierId, String fileTemplateNo,
		int dossierPartType, boolean removed) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID_FTNO_DPT;

		Object[] finderArgs = new Object[] {
				dossierId, fileTemplateNo, dossierPartType, removed
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_DOSSIERID_2);

			boolean bindFileTemplateNo = false;

			if (fileTemplateNo == null) {
				query.append(_FINDER_COLUMN_DID_FTNO_DPT_FILETEMPLATENO_1);
			}
			else if (fileTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_DID_FTNO_DPT_FILETEMPLATENO_3);
			}
			else {
				bindFileTemplateNo = true;

				query.append(_FINDER_COLUMN_DID_FTNO_DPT_FILETEMPLATENO_2);
			}

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_DOSSIERPARTTYPE_2);

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_REMOVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindFileTemplateNo) {
					qPos.add(fileTemplateNo);
				}

				qPos.add(dossierPartType);

				qPos.add(removed);

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

	private static final String _FINDER_COLUMN_DID_FTNO_DPT_DOSSIERID_2 = "dossierFile.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_DPT_FILETEMPLATENO_1 = "dossierFile.fileTemplateNo IS NULL AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_DPT_FILETEMPLATENO_2 = "dossierFile.fileTemplateNo = ? AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_DPT_FILETEMPLATENO_3 = "(dossierFile.fileTemplateNo IS NULL OR dossierFile.fileTemplateNo = '') AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_DPT_DOSSIERPARTTYPE_2 = "dossierFile.dossierPartType = ? AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_DPT_REMOVED_2 = "dossierFile.removed = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_FTNO_DPT_NOT_NULL_FID =
		new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByDID_FTNO_DPT_NOT_NULL_FID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_DID_FTNO_DPT_NOT_NULL_FID =
		new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByDID_FTNO_DPT_NOT_NULL_FID",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param fileEntryId the file entry ID
	 * @param removed the removed
	 * @return the matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTNO_DPT_NOT_NULL_FID(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed) {
		return findByDID_FTNO_DPT_NOT_NULL_FID(dossierId, fileTemplateNo,
			dossierPartType, fileEntryId, removed, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param fileEntryId the file entry ID
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTNO_DPT_NOT_NULL_FID(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed, int start, int end) {
		return findByDID_FTNO_DPT_NOT_NULL_FID(dossierId, fileTemplateNo,
			dossierPartType, fileEntryId, removed, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param fileEntryId the file entry ID
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTNO_DPT_NOT_NULL_FID(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return findByDID_FTNO_DPT_NOT_NULL_FID(dossierId, fileTemplateNo,
			dossierPartType, fileEntryId, removed, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param fileEntryId the file entry ID
	 * @param removed the removed
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByDID_FTNO_DPT_NOT_NULL_FID(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DID_FTNO_DPT_NOT_NULL_FID;
		finderArgs = new Object[] {
				dossierId, fileTemplateNo, dossierPartType, fileEntryId, removed,
				
				start, end, orderByComparator
			};

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierFile dossierFile : list) {
					if ((dossierId != dossierFile.getDossierId()) ||
							!Objects.equals(fileTemplateNo,
								dossierFile.getFileTemplateNo()) ||
							(dossierPartType != dossierFile.getDossierPartType()) ||
							(fileEntryId >= dossierFile.getFileEntryId()) ||
							(removed != dossierFile.isRemoved())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_DOSSIERID_2);

			boolean bindFileTemplateNo = false;

			if (fileTemplateNo == null) {
				query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILETEMPLATENO_1);
			}
			else if (fileTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILETEMPLATENO_3);
			}
			else {
				bindFileTemplateNo = true;

				query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILETEMPLATENO_2);
			}

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_DOSSIERPARTTYPE_2);

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILEENTRYID_2);

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_REMOVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindFileTemplateNo) {
					qPos.add(fileTemplateNo);
				}

				qPos.add(dossierPartType);

				qPos.add(fileEntryId);

				qPos.add(removed);

				if (!pagination) {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param fileEntryId the file entry ID
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID_FTNO_DPT_NOT_NULL_FID_First(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed, OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID_FTNO_DPT_NOT_NULL_FID_First(dossierId,
				fileTemplateNo, dossierPartType, fileEntryId, removed,
				orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", fileTemplateNo=");
		msg.append(fileTemplateNo);

		msg.append(", dossierPartType=");
		msg.append(dossierPartType);

		msg.append(", fileEntryId=");
		msg.append(fileEntryId);

		msg.append(", removed=");
		msg.append(removed);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param fileEntryId the file entry ID
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID_FTNO_DPT_NOT_NULL_FID_First(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed, OrderByComparator<DossierFile> orderByComparator) {
		List<DossierFile> list = findByDID_FTNO_DPT_NOT_NULL_FID(dossierId,
				fileTemplateNo, dossierPartType, fileEntryId, removed, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param fileEntryId the file entry ID
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDID_FTNO_DPT_NOT_NULL_FID_Last(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed, OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDID_FTNO_DPT_NOT_NULL_FID_Last(dossierId,
				fileTemplateNo, dossierPartType, fileEntryId, removed,
				orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", fileTemplateNo=");
		msg.append(fileTemplateNo);

		msg.append(", dossierPartType=");
		msg.append(dossierPartType);

		msg.append(", fileEntryId=");
		msg.append(fileEntryId);

		msg.append(", removed=");
		msg.append(removed);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param fileEntryId the file entry ID
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDID_FTNO_DPT_NOT_NULL_FID_Last(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed, OrderByComparator<DossierFile> orderByComparator) {
		int count = countByDID_FTNO_DPT_NOT_NULL_FID(dossierId, fileTemplateNo,
				dossierPartType, fileEntryId, removed);

		if (count == 0) {
			return null;
		}

		List<DossierFile> list = findByDID_FTNO_DPT_NOT_NULL_FID(dossierId,
				fileTemplateNo, dossierPartType, fileEntryId, removed,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	 *
	 * @param dossierFileId the primary key of the current dossier file
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param fileEntryId the file entry ID
	 * @param removed the removed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile[] findByDID_FTNO_DPT_NOT_NULL_FID_PrevAndNext(
		long dossierFileId, long dossierId, String fileTemplateNo,
		int dossierPartType, long fileEntryId, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByPrimaryKey(dossierFileId);

		Session session = null;

		try {
			session = openSession();

			DossierFile[] array = new DossierFileImpl[3];

			array[0] = getByDID_FTNO_DPT_NOT_NULL_FID_PrevAndNext(session,
					dossierFile, dossierId, fileTemplateNo, dossierPartType,
					fileEntryId, removed, orderByComparator, true);

			array[1] = dossierFile;

			array[2] = getByDID_FTNO_DPT_NOT_NULL_FID_PrevAndNext(session,
					dossierFile, dossierId, fileTemplateNo, dossierPartType,
					fileEntryId, removed, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierFile getByDID_FTNO_DPT_NOT_NULL_FID_PrevAndNext(
		Session session, DossierFile dossierFile, long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed, OrderByComparator<DossierFile> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(8 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

		query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_DOSSIERID_2);

		boolean bindFileTemplateNo = false;

		if (fileTemplateNo == null) {
			query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILETEMPLATENO_1);
		}
		else if (fileTemplateNo.equals("")) {
			query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILETEMPLATENO_3);
		}
		else {
			bindFileTemplateNo = true;

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILETEMPLATENO_2);
		}

		query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_DOSSIERPARTTYPE_2);

		query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILEENTRYID_2);

		query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_REMOVED_2);

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
			query.append(DossierFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (bindFileTemplateNo) {
			qPos.add(fileTemplateNo);
		}

		qPos.add(dossierPartType);

		qPos.add(fileEntryId);

		qPos.add(removed);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param fileEntryId the file entry ID
	 * @param removed the removed
	 */
	@Override
	public void removeByDID_FTNO_DPT_NOT_NULL_FID(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed) {
		for (DossierFile dossierFile : findByDID_FTNO_DPT_NOT_NULL_FID(
				dossierId, fileTemplateNo, dossierPartType, fileEntryId,
				removed, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierPartType the dossier part type
	 * @param fileEntryId the file entry ID
	 * @param removed the removed
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByDID_FTNO_DPT_NOT_NULL_FID(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_DID_FTNO_DPT_NOT_NULL_FID;

		Object[] finderArgs = new Object[] {
				dossierId, fileTemplateNo, dossierPartType, fileEntryId, removed
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_DOSSIERID_2);

			boolean bindFileTemplateNo = false;

			if (fileTemplateNo == null) {
				query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILETEMPLATENO_1);
			}
			else if (fileTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILETEMPLATENO_3);
			}
			else {
				bindFileTemplateNo = true;

				query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILETEMPLATENO_2);
			}

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_DOSSIERPARTTYPE_2);

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILEENTRYID_2);

			query.append(_FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_REMOVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindFileTemplateNo) {
					qPos.add(fileTemplateNo);
				}

				qPos.add(dossierPartType);

				qPos.add(fileEntryId);

				qPos.add(removed);

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

	private static final String _FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_DOSSIERID_2 =
		"dossierFile.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILETEMPLATENO_1 =
		"dossierFile.fileTemplateNo IS NULL AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILETEMPLATENO_2 =
		"dossierFile.fileTemplateNo = ? AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILETEMPLATENO_3 =
		"(dossierFile.fileTemplateNo IS NULL OR dossierFile.fileTemplateNo = '') AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_DOSSIERPARTTYPE_2 =
		"dossierFile.dossierPartType = ? AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_FILEENTRYID_2 =
		"dossierFile.fileEntryId > ? AND ";
	private static final String _FINDER_COLUMN_DID_FTNO_DPT_NOT_NULL_FID_REMOVED_2 =
		"dossierFile.removed = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DE_CODE = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDE_CODE",
			new String[] { String.class.getName() },
			DossierFileModelImpl.DELIVERABLECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DE_CODE = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDE_CODE",
			new String[] { String.class.getName() });

	/**
	 * Returns the dossier file where deliverableCode = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	 *
	 * @param deliverableCode the deliverable code
	 * @return the matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByDE_CODE(String deliverableCode)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByDE_CODE(deliverableCode);

		if (dossierFile == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("deliverableCode=");
			msg.append(deliverableCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierFileException(msg.toString());
		}

		return dossierFile;
	}

	/**
	 * Returns the dossier file where deliverableCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param deliverableCode the deliverable code
	 * @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDE_CODE(String deliverableCode) {
		return fetchByDE_CODE(deliverableCode, true);
	}

	/**
	 * Returns the dossier file where deliverableCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param deliverableCode the deliverable code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByDE_CODE(String deliverableCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { deliverableCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DE_CODE,
					finderArgs, this);
		}

		if (result instanceof DossierFile) {
			DossierFile dossierFile = (DossierFile)result;

			if (!Objects.equals(deliverableCode,
						dossierFile.getDeliverableCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			boolean bindDeliverableCode = false;

			if (deliverableCode == null) {
				query.append(_FINDER_COLUMN_DE_CODE_DELIVERABLECODE_1);
			}
			else if (deliverableCode.equals("")) {
				query.append(_FINDER_COLUMN_DE_CODE_DELIVERABLECODE_3);
			}
			else {
				bindDeliverableCode = true;

				query.append(_FINDER_COLUMN_DE_CODE_DELIVERABLECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDeliverableCode) {
					qPos.add(deliverableCode);
				}

				List<DossierFile> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DE_CODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierFilePersistenceImpl.fetchByDE_CODE(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierFile dossierFile = list.get(0);

					result = dossierFile;

					cacheResult(dossierFile);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DE_CODE,
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
			return (DossierFile)result;
		}
	}

	/**
	 * Removes the dossier file where deliverableCode = &#63; from the database.
	 *
	 * @param deliverableCode the deliverable code
	 * @return the dossier file that was removed
	 */
	@Override
	public DossierFile removeByDE_CODE(String deliverableCode)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByDE_CODE(deliverableCode);

		return remove(dossierFile);
	}

	/**
	 * Returns the number of dossier files where deliverableCode = &#63;.
	 *
	 * @param deliverableCode the deliverable code
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByDE_CODE(String deliverableCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DE_CODE;

		Object[] finderArgs = new Object[] { deliverableCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			boolean bindDeliverableCode = false;

			if (deliverableCode == null) {
				query.append(_FINDER_COLUMN_DE_CODE_DELIVERABLECODE_1);
			}
			else if (deliverableCode.equals("")) {
				query.append(_FINDER_COLUMN_DE_CODE_DELIVERABLECODE_3);
			}
			else {
				bindDeliverableCode = true;

				query.append(_FINDER_COLUMN_DE_CODE_DELIVERABLECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDeliverableCode) {
					qPos.add(deliverableCode);
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

	private static final String _FINDER_COLUMN_DE_CODE_DELIVERABLECODE_1 = "dossierFile.deliverableCode IS NULL";
	private static final String _FINDER_COLUMN_DE_CODE_DELIVERABLECODE_2 = "dossierFile.deliverableCode = ?";
	private static final String _FINDER_COLUMN_DE_CODE_DELIVERABLECODE_3 = "(dossierFile.deliverableCode IS NULL OR dossierFile.deliverableCode = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_GID_REF = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByGID_REF",
			new String[] { Long.class.getName(), String.class.getName() },
			DossierFileModelImpl.GROUPID_COLUMN_BITMASK |
			DossierFileModelImpl.REFERENCEUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_REF = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGID_REF",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the dossier file where groupId = &#63; and referenceUid = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @return the matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByGID_REF(long groupId, String referenceUid)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByGID_REF(groupId, referenceUid);

		if (dossierFile == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", referenceUid=");
			msg.append(referenceUid);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierFileException(msg.toString());
		}

		return dossierFile;
	}

	/**
	 * Returns the dossier file where groupId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByGID_REF(long groupId, String referenceUid) {
		return fetchByGID_REF(groupId, referenceUid, true);
	}

	/**
	 * Returns the dossier file where groupId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByGID_REF(long groupId, String referenceUid,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, referenceUid };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_GID_REF,
					finderArgs, this);
		}

		if (result instanceof DossierFile) {
			DossierFile dossierFile = (DossierFile)result;

			if ((groupId != dossierFile.getGroupId()) ||
					!Objects.equals(referenceUid, dossierFile.getReferenceUid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_GID_REF_GROUPID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_GID_REF_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_GID_REF_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_GID_REF_REFERENCEUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
				}

				List<DossierFile> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_GID_REF,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierFilePersistenceImpl.fetchByGID_REF(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierFile dossierFile = list.get(0);

					result = dossierFile;

					cacheResult(dossierFile);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_REF,
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
			return (DossierFile)result;
		}
	}

	/**
	 * Removes the dossier file where groupId = &#63; and referenceUid = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @return the dossier file that was removed
	 */
	@Override
	public DossierFile removeByGID_REF(long groupId, String referenceUid)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByGID_REF(groupId, referenceUid);

		return remove(dossierFile);
	}

	/**
	 * Returns the number of dossier files where groupId = &#63; and referenceUid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByGID_REF(long groupId, String referenceUid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_REF;

		Object[] finderArgs = new Object[] { groupId, referenceUid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_GID_REF_GROUPID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_GID_REF_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_GID_REF_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_GID_REF_REFERENCEUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
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

	private static final String _FINDER_COLUMN_GID_REF_GROUPID_2 = "dossierFile.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GID_REF_REFERENCEUID_1 = "dossierFile.referenceUid IS NULL";
	private static final String _FINDER_COLUMN_GID_REF_REFERENCEUID_2 = "dossierFile.referenceUid = ?";
	private static final String _FINDER_COLUMN_GID_REF_REFERENCEUID_3 = "(dossierFile.referenceUid IS NULL OR dossierFile.referenceUid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_FILE_ID = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByFILE_ID",
			new String[] { Long.class.getName() },
			DossierFileModelImpl.FILEENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FILE_ID = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFILE_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns the dossier file where fileEntryId = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByFILE_ID(long fileEntryId)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByFILE_ID(fileEntryId);

		if (dossierFile == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("fileEntryId=");
			msg.append(fileEntryId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierFileException(msg.toString());
		}

		return dossierFile;
	}

	/**
	 * Returns the dossier file where fileEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByFILE_ID(long fileEntryId) {
		return fetchByFILE_ID(fileEntryId, true);
	}

	/**
	 * Returns the dossier file where fileEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByFILE_ID(long fileEntryId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { fileEntryId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_FILE_ID,
					finderArgs, this);
		}

		if (result instanceof DossierFile) {
			DossierFile dossierFile = (DossierFile)result;

			if ((fileEntryId != dossierFile.getFileEntryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_FILE_ID_FILEENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileEntryId);

				List<DossierFile> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_FILE_ID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierFilePersistenceImpl.fetchByFILE_ID(long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierFile dossierFile = list.get(0);

					result = dossierFile;

					cacheResult(dossierFile);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_FILE_ID,
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
			return (DossierFile)result;
		}
	}

	/**
	 * Removes the dossier file where fileEntryId = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the dossier file that was removed
	 */
	@Override
	public DossierFile removeByFILE_ID(long fileEntryId)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByFILE_ID(fileEntryId);

		return remove(dossierFile);
	}

	/**
	 * Returns the number of dossier files where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByFILE_ID(long fileEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FILE_ID;

		Object[] finderArgs = new Object[] { fileEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_FILE_ID_FILEENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileEntryId);

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

	private static final String _FINDER_COLUMN_FILE_ID_FILEENTRYID_2 = "dossierFile.fileEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REF_UID = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByREF_UID",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REF_UID =
		new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByREF_UID",
			new String[] { String.class.getName() },
			DossierFileModelImpl.REFERENCEUID_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTNO_COLUMN_BITMASK |
			DossierFileModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REF_UID = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByREF_UID",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dossier files where referenceUid = &#63;.
	 *
	 * @param referenceUid the reference uid
	 * @return the matching dossier files
	 */
	@Override
	public List<DossierFile> findByREF_UID(String referenceUid) {
		return findByREF_UID(referenceUid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files where referenceUid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param referenceUid the reference uid
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByREF_UID(String referenceUid, int start,
		int end) {
		return findByREF_UID(referenceUid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier files where referenceUid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param referenceUid the reference uid
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByREF_UID(String referenceUid, int start,
		int end, OrderByComparator<DossierFile> orderByComparator) {
		return findByREF_UID(referenceUid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files where referenceUid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param referenceUid the reference uid
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByREF_UID(String referenceUid, int start,
		int end, OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REF_UID;
			finderArgs = new Object[] { referenceUid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REF_UID;
			finderArgs = new Object[] {
					referenceUid,
					
					start, end, orderByComparator
				};
		}

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierFile dossierFile : list) {
					if (!Objects.equals(referenceUid,
								dossierFile.getReferenceUid())) {
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

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_REF_UID_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_REF_UID_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_REF_UID_REFERENCEUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
				}

				if (!pagination) {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier file in the ordered set where referenceUid = &#63;.
	 *
	 * @param referenceUid the reference uid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByREF_UID_First(String referenceUid,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByREF_UID_First(referenceUid,
				orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("referenceUid=");
		msg.append(referenceUid);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the first dossier file in the ordered set where referenceUid = &#63;.
	 *
	 * @param referenceUid the reference uid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByREF_UID_First(String referenceUid,
		OrderByComparator<DossierFile> orderByComparator) {
		List<DossierFile> list = findByREF_UID(referenceUid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier file in the ordered set where referenceUid = &#63;.
	 *
	 * @param referenceUid the reference uid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByREF_UID_Last(String referenceUid,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByREF_UID_Last(referenceUid,
				orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("referenceUid=");
		msg.append(referenceUid);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the last dossier file in the ordered set where referenceUid = &#63;.
	 *
	 * @param referenceUid the reference uid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByREF_UID_Last(String referenceUid,
		OrderByComparator<DossierFile> orderByComparator) {
		int count = countByREF_UID(referenceUid);

		if (count == 0) {
			return null;
		}

		List<DossierFile> list = findByREF_UID(referenceUid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier files before and after the current dossier file in the ordered set where referenceUid = &#63;.
	 *
	 * @param dossierFileId the primary key of the current dossier file
	 * @param referenceUid the reference uid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile[] findByREF_UID_PrevAndNext(long dossierFileId,
		String referenceUid, OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByPrimaryKey(dossierFileId);

		Session session = null;

		try {
			session = openSession();

			DossierFile[] array = new DossierFileImpl[3];

			array[0] = getByREF_UID_PrevAndNext(session, dossierFile,
					referenceUid, orderByComparator, true);

			array[1] = dossierFile;

			array[2] = getByREF_UID_PrevAndNext(session, dossierFile,
					referenceUid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierFile getByREF_UID_PrevAndNext(Session session,
		DossierFile dossierFile, String referenceUid,
		OrderByComparator<DossierFile> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

		boolean bindReferenceUid = false;

		if (referenceUid == null) {
			query.append(_FINDER_COLUMN_REF_UID_REFERENCEUID_1);
		}
		else if (referenceUid.equals("")) {
			query.append(_FINDER_COLUMN_REF_UID_REFERENCEUID_3);
		}
		else {
			bindReferenceUid = true;

			query.append(_FINDER_COLUMN_REF_UID_REFERENCEUID_2);
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
			query.append(DossierFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindReferenceUid) {
			qPos.add(referenceUid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier files where referenceUid = &#63; from the database.
	 *
	 * @param referenceUid the reference uid
	 */
	@Override
	public void removeByREF_UID(String referenceUid) {
		for (DossierFile dossierFile : findByREF_UID(referenceUid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files where referenceUid = &#63;.
	 *
	 * @param referenceUid the reference uid
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByREF_UID(String referenceUid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_REF_UID;

		Object[] finderArgs = new Object[] { referenceUid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_REF_UID_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_REF_UID_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_REF_UID_REFERENCEUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
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

	private static final String _FINDER_COLUMN_REF_UID_REFERENCEUID_1 = "dossierFile.referenceUid IS NULL";
	private static final String _FINDER_COLUMN_REF_UID_REFERENCEUID_2 = "dossierFile.referenceUid = ?";
	private static final String _FINDER_COLUMN_REF_UID_REFERENCEUID_3 = "(dossierFile.referenceUid IS NULL OR dossierFile.referenceUid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG",
			new String[] { Long.class.getName() },
			DossierFileModelImpl.GROUPID_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTNO_COLUMN_BITMASK |
			DossierFileModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dossier files where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching dossier files
	 */
	@Override
	public List<DossierFile> findByG(long groupId) {
		return findByG(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByG(long groupId, int start, int end) {
		return findByG(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier files where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByG(long groupId, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return findByG(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByG(long groupId, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierFile dossierFile : list) {
					if ((groupId != dossierFile.getGroupId())) {
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

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_G_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier file in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByG_First(long groupId,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByG_First(groupId, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the first dossier file in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByG_First(long groupId,
		OrderByComparator<DossierFile> orderByComparator) {
		List<DossierFile> list = findByG(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier file in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByG_Last(long groupId,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByG_Last(groupId, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the last dossier file in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByG_Last(long groupId,
		OrderByComparator<DossierFile> orderByComparator) {
		int count = countByG(groupId);

		if (count == 0) {
			return null;
		}

		List<DossierFile> list = findByG(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier files before and after the current dossier file in the ordered set where groupId = &#63;.
	 *
	 * @param dossierFileId the primary key of the current dossier file
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile[] findByG_PrevAndNext(long dossierFileId, long groupId,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByPrimaryKey(dossierFileId);

		Session session = null;

		try {
			session = openSession();

			DossierFile[] array = new DossierFileImpl[3];

			array[0] = getByG_PrevAndNext(session, dossierFile, groupId,
					orderByComparator, true);

			array[1] = dossierFile;

			array[2] = getByG_PrevAndNext(session, dossierFile, groupId,
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

	protected DossierFile getByG_PrevAndNext(Session session,
		DossierFile dossierFile, long groupId,
		OrderByComparator<DossierFile> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

		query.append(_FINDER_COLUMN_G_GROUPID_2);

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
			query.append(DossierFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier files where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByG(long groupId) {
		for (DossierFile dossierFile : findByG(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByG(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_G_GROUPID_2 = "dossierFile.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_D_FTN_DTN =
		new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByD_FTN_DTN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_FTN_DTN =
		new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, DossierFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByD_FTN_DTN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			DossierFileModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierFileModelImpl.FILETEMPLATENO_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERTEMPLATENO_COLUMN_BITMASK |
			DossierFileModelImpl.DOSSIERPARTNO_COLUMN_BITMASK |
			DossierFileModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_D_FTN_DTN = new FinderPath(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByD_FTN_DTN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierTemplateNo the dossier template no
	 * @return the matching dossier files
	 */
	@Override
	public List<DossierFile> findByD_FTN_DTN(long dossierId,
		String fileTemplateNo, String dossierTemplateNo) {
		return findByD_FTN_DTN(dossierId, fileTemplateNo, dossierTemplateNo,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierTemplateNo the dossier template no
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByD_FTN_DTN(long dossierId,
		String fileTemplateNo, String dossierTemplateNo, int start, int end) {
		return findByD_FTN_DTN(dossierId, fileTemplateNo, dossierTemplateNo,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierTemplateNo the dossier template no
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByD_FTN_DTN(long dossierId,
		String fileTemplateNo, String dossierTemplateNo, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return findByD_FTN_DTN(dossierId, fileTemplateNo, dossierTemplateNo,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierTemplateNo the dossier template no
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier files
	 */
	@Override
	public List<DossierFile> findByD_FTN_DTN(long dossierId,
		String fileTemplateNo, String dossierTemplateNo, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_FTN_DTN;
			finderArgs = new Object[] {
					dossierId, fileTemplateNo, dossierTemplateNo
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_D_FTN_DTN;
			finderArgs = new Object[] {
					dossierId, fileTemplateNo, dossierTemplateNo,
					
					start, end, orderByComparator
				};
		}

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierFile dossierFile : list) {
					if ((dossierId != dossierFile.getDossierId()) ||
							!Objects.equals(fileTemplateNo,
								dossierFile.getFileTemplateNo()) ||
							!Objects.equals(dossierTemplateNo,
								dossierFile.getDossierTemplateNo())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_D_FTN_DTN_DOSSIERID_2);

			boolean bindFileTemplateNo = false;

			if (fileTemplateNo == null) {
				query.append(_FINDER_COLUMN_D_FTN_DTN_FILETEMPLATENO_1);
			}
			else if (fileTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_D_FTN_DTN_FILETEMPLATENO_3);
			}
			else {
				bindFileTemplateNo = true;

				query.append(_FINDER_COLUMN_D_FTN_DTN_FILETEMPLATENO_2);
			}

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_D_FTN_DTN_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_D_FTN_DTN_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_D_FTN_DTN_DOSSIERTEMPLATENO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierFileModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindFileTemplateNo) {
					qPos.add(fileTemplateNo);
				}

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
				}

				if (!pagination) {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByD_FTN_DTN_First(long dossierId,
		String fileTemplateNo, String dossierTemplateNo,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByD_FTN_DTN_First(dossierId,
				fileTemplateNo, dossierTemplateNo, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", fileTemplateNo=");
		msg.append(fileTemplateNo);

		msg.append(", dossierTemplateNo=");
		msg.append(dossierTemplateNo);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByD_FTN_DTN_First(long dossierId,
		String fileTemplateNo, String dossierTemplateNo,
		OrderByComparator<DossierFile> orderByComparator) {
		List<DossierFile> list = findByD_FTN_DTN(dossierId, fileTemplateNo,
				dossierTemplateNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file
	 * @throws NoSuchDossierFileException if a matching dossier file could not be found
	 */
	@Override
	public DossierFile findByD_FTN_DTN_Last(long dossierId,
		String fileTemplateNo, String dossierTemplateNo,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByD_FTN_DTN_Last(dossierId,
				fileTemplateNo, dossierTemplateNo, orderByComparator);

		if (dossierFile != null) {
			return dossierFile;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append(", fileTemplateNo=");
		msg.append(fileTemplateNo);

		msg.append(", dossierTemplateNo=");
		msg.append(dossierTemplateNo);

		msg.append("}");

		throw new NoSuchDossierFileException(msg.toString());
	}

	/**
	 * Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	 */
	@Override
	public DossierFile fetchByD_FTN_DTN_Last(long dossierId,
		String fileTemplateNo, String dossierTemplateNo,
		OrderByComparator<DossierFile> orderByComparator) {
		int count = countByD_FTN_DTN(dossierId, fileTemplateNo,
				dossierTemplateNo);

		if (count == 0) {
			return null;
		}

		List<DossierFile> list = findByD_FTN_DTN(dossierId, fileTemplateNo,
				dossierTemplateNo, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param dossierFileId the primary key of the current dossier file
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierTemplateNo the dossier template no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile[] findByD_FTN_DTN_PrevAndNext(long dossierFileId,
		long dossierId, String fileTemplateNo, String dossierTemplateNo,
		OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = findByPrimaryKey(dossierFileId);

		Session session = null;

		try {
			session = openSession();

			DossierFile[] array = new DossierFileImpl[3];

			array[0] = getByD_FTN_DTN_PrevAndNext(session, dossierFile,
					dossierId, fileTemplateNo, dossierTemplateNo,
					orderByComparator, true);

			array[1] = dossierFile;

			array[2] = getByD_FTN_DTN_PrevAndNext(session, dossierFile,
					dossierId, fileTemplateNo, dossierTemplateNo,
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

	protected DossierFile getByD_FTN_DTN_PrevAndNext(Session session,
		DossierFile dossierFile, long dossierId, String fileTemplateNo,
		String dossierTemplateNo,
		OrderByComparator<DossierFile> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE);

		query.append(_FINDER_COLUMN_D_FTN_DTN_DOSSIERID_2);

		boolean bindFileTemplateNo = false;

		if (fileTemplateNo == null) {
			query.append(_FINDER_COLUMN_D_FTN_DTN_FILETEMPLATENO_1);
		}
		else if (fileTemplateNo.equals("")) {
			query.append(_FINDER_COLUMN_D_FTN_DTN_FILETEMPLATENO_3);
		}
		else {
			bindFileTemplateNo = true;

			query.append(_FINDER_COLUMN_D_FTN_DTN_FILETEMPLATENO_2);
		}

		boolean bindDossierTemplateNo = false;

		if (dossierTemplateNo == null) {
			query.append(_FINDER_COLUMN_D_FTN_DTN_DOSSIERTEMPLATENO_1);
		}
		else if (dossierTemplateNo.equals("")) {
			query.append(_FINDER_COLUMN_D_FTN_DTN_DOSSIERTEMPLATENO_3);
		}
		else {
			bindDossierTemplateNo = true;

			query.append(_FINDER_COLUMN_D_FTN_DTN_DOSSIERTEMPLATENO_2);
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
			query.append(DossierFileModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (bindFileTemplateNo) {
			qPos.add(fileTemplateNo);
		}

		if (bindDossierTemplateNo) {
			qPos.add(dossierTemplateNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierTemplateNo the dossier template no
	 */
	@Override
	public void removeByD_FTN_DTN(long dossierId, String fileTemplateNo,
		String dossierTemplateNo) {
		for (DossierFile dossierFile : findByD_FTN_DTN(dossierId,
				fileTemplateNo, dossierTemplateNo, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param fileTemplateNo the file template no
	 * @param dossierTemplateNo the dossier template no
	 * @return the number of matching dossier files
	 */
	@Override
	public int countByD_FTN_DTN(long dossierId, String fileTemplateNo,
		String dossierTemplateNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_D_FTN_DTN;

		Object[] finderArgs = new Object[] {
				dossierId, fileTemplateNo, dossierTemplateNo
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERFILE_WHERE);

			query.append(_FINDER_COLUMN_D_FTN_DTN_DOSSIERID_2);

			boolean bindFileTemplateNo = false;

			if (fileTemplateNo == null) {
				query.append(_FINDER_COLUMN_D_FTN_DTN_FILETEMPLATENO_1);
			}
			else if (fileTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_D_FTN_DTN_FILETEMPLATENO_3);
			}
			else {
				bindFileTemplateNo = true;

				query.append(_FINDER_COLUMN_D_FTN_DTN_FILETEMPLATENO_2);
			}

			boolean bindDossierTemplateNo = false;

			if (dossierTemplateNo == null) {
				query.append(_FINDER_COLUMN_D_FTN_DTN_DOSSIERTEMPLATENO_1);
			}
			else if (dossierTemplateNo.equals("")) {
				query.append(_FINDER_COLUMN_D_FTN_DTN_DOSSIERTEMPLATENO_3);
			}
			else {
				bindDossierTemplateNo = true;

				query.append(_FINDER_COLUMN_D_FTN_DTN_DOSSIERTEMPLATENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (bindFileTemplateNo) {
					qPos.add(fileTemplateNo);
				}

				if (bindDossierTemplateNo) {
					qPos.add(dossierTemplateNo);
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

	private static final String _FINDER_COLUMN_D_FTN_DTN_DOSSIERID_2 = "dossierFile.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_D_FTN_DTN_FILETEMPLATENO_1 = "dossierFile.fileTemplateNo IS NULL AND ";
	private static final String _FINDER_COLUMN_D_FTN_DTN_FILETEMPLATENO_2 = "dossierFile.fileTemplateNo = ? AND ";
	private static final String _FINDER_COLUMN_D_FTN_DTN_FILETEMPLATENO_3 = "(dossierFile.fileTemplateNo IS NULL OR dossierFile.fileTemplateNo = '') AND ";
	private static final String _FINDER_COLUMN_D_FTN_DTN_DOSSIERTEMPLATENO_1 = "dossierFile.dossierTemplateNo IS NULL";
	private static final String _FINDER_COLUMN_D_FTN_DTN_DOSSIERTEMPLATENO_2 = "dossierFile.dossierTemplateNo = ?";
	private static final String _FINDER_COLUMN_D_FTN_DTN_DOSSIERTEMPLATENO_3 = "(dossierFile.dossierTemplateNo IS NULL OR dossierFile.dossierTemplateNo = '')";

	public DossierFilePersistenceImpl() {
		setModelClass(DossierFile.class);

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
	 * Caches the dossier file in the entity cache if it is enabled.
	 *
	 * @param dossierFile the dossier file
	 */
	@Override
	public void cacheResult(DossierFile dossierFile) {
		entityCache.putResult(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileImpl.class, dossierFile.getPrimaryKey(), dossierFile);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dossierFile.getUuid(), dossierFile.getGroupId() },
			dossierFile);

		finderCache.putResult(FINDER_PATH_FETCH_BY_D_RUID,
			new Object[] {
				dossierFile.getDossierId(), dossierFile.getReferenceUid(),
				dossierFile.isRemoved()
			}, dossierFile);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DID_REF,
			new Object[] {
				dossierFile.getDossierId(), dossierFile.getReferenceUid()
			}, dossierFile);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DE_CODE,
			new Object[] { dossierFile.getDeliverableCode() }, dossierFile);

		finderCache.putResult(FINDER_PATH_FETCH_BY_GID_REF,
			new Object[] { dossierFile.getGroupId(), dossierFile.getReferenceUid() },
			dossierFile);

		finderCache.putResult(FINDER_PATH_FETCH_BY_FILE_ID,
			new Object[] { dossierFile.getFileEntryId() }, dossierFile);

		dossierFile.resetOriginalValues();
	}

	/**
	 * Caches the dossier files in the entity cache if it is enabled.
	 *
	 * @param dossierFiles the dossier files
	 */
	@Override
	public void cacheResult(List<DossierFile> dossierFiles) {
		for (DossierFile dossierFile : dossierFiles) {
			if (entityCache.getResult(
						DossierFileModelImpl.ENTITY_CACHE_ENABLED,
						DossierFileImpl.class, dossierFile.getPrimaryKey()) == null) {
				cacheResult(dossierFile);
			}
			else {
				dossierFile.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dossier files.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DossierFileImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dossier file.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DossierFile dossierFile) {
		entityCache.removeResult(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileImpl.class, dossierFile.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DossierFileModelImpl)dossierFile, true);
	}

	@Override
	public void clearCache(List<DossierFile> dossierFiles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DossierFile dossierFile : dossierFiles) {
			entityCache.removeResult(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
				DossierFileImpl.class, dossierFile.getPrimaryKey());

			clearUniqueFindersCache((DossierFileModelImpl)dossierFile, true);
		}
	}

	protected void cacheUniqueFindersCache(
		DossierFileModelImpl dossierFileModelImpl) {
		Object[] args = new Object[] {
				dossierFileModelImpl.getUuid(),
				dossierFileModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dossierFileModelImpl, false);

		args = new Object[] {
				dossierFileModelImpl.getDossierId(),
				dossierFileModelImpl.getReferenceUid(),
				dossierFileModelImpl.isRemoved()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_D_RUID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_D_RUID, args,
			dossierFileModelImpl, false);

		args = new Object[] {
				dossierFileModelImpl.getDossierId(),
				dossierFileModelImpl.getReferenceUid()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_DID_REF, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DID_REF, args,
			dossierFileModelImpl, false);

		args = new Object[] { dossierFileModelImpl.getDeliverableCode() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_DE_CODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DE_CODE, args,
			dossierFileModelImpl, false);

		args = new Object[] {
				dossierFileModelImpl.getGroupId(),
				dossierFileModelImpl.getReferenceUid()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_GID_REF, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_GID_REF, args,
			dossierFileModelImpl, false);

		args = new Object[] { dossierFileModelImpl.getFileEntryId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_FILE_ID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_FILE_ID, args,
			dossierFileModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DossierFileModelImpl dossierFileModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierFileModelImpl.getUuid(),
					dossierFileModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dossierFileModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierFileModelImpl.getOriginalUuid(),
					dossierFileModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierFileModelImpl.getDossierId(),
					dossierFileModelImpl.getReferenceUid(),
					dossierFileModelImpl.isRemoved()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_D_RUID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_D_RUID, args);
		}

		if ((dossierFileModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_D_RUID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierFileModelImpl.getOriginalDossierId(),
					dossierFileModelImpl.getOriginalReferenceUid(),
					dossierFileModelImpl.getOriginalRemoved()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_D_RUID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_D_RUID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierFileModelImpl.getDossierId(),
					dossierFileModelImpl.getReferenceUid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_REF, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_REF, args);
		}

		if ((dossierFileModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DID_REF.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierFileModelImpl.getOriginalDossierId(),
					dossierFileModelImpl.getOriginalReferenceUid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_REF, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID_REF, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierFileModelImpl.getDeliverableCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DE_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DE_CODE, args);
		}

		if ((dossierFileModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DE_CODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierFileModelImpl.getOriginalDeliverableCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DE_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DE_CODE, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierFileModelImpl.getGroupId(),
					dossierFileModelImpl.getReferenceUid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_REF, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_REF, args);
		}

		if ((dossierFileModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GID_REF.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierFileModelImpl.getOriginalGroupId(),
					dossierFileModelImpl.getOriginalReferenceUid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_REF, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_REF, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { dossierFileModelImpl.getFileEntryId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FILE_ID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_FILE_ID, args);
		}

		if ((dossierFileModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_FILE_ID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierFileModelImpl.getOriginalFileEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FILE_ID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_FILE_ID, args);
		}
	}

	/**
	 * Creates a new dossier file with the primary key. Does not add the dossier file to the database.
	 *
	 * @param dossierFileId the primary key for the new dossier file
	 * @return the new dossier file
	 */
	@Override
	public DossierFile create(long dossierFileId) {
		DossierFile dossierFile = new DossierFileImpl();

		dossierFile.setNew(true);
		dossierFile.setPrimaryKey(dossierFileId);

		String uuid = PortalUUIDUtil.generate();

		dossierFile.setUuid(uuid);

		dossierFile.setCompanyId(companyProvider.getCompanyId());

		return dossierFile;
	}

	/**
	 * Removes the dossier file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dossierFileId the primary key of the dossier file
	 * @return the dossier file that was removed
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile remove(long dossierFileId)
		throws NoSuchDossierFileException {
		return remove((Serializable)dossierFileId);
	}

	/**
	 * Removes the dossier file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dossier file
	 * @return the dossier file that was removed
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile remove(Serializable primaryKey)
		throws NoSuchDossierFileException {
		Session session = null;

		try {
			session = openSession();

			DossierFile dossierFile = (DossierFile)session.get(DossierFileImpl.class,
					primaryKey);

			if (dossierFile == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDossierFileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dossierFile);
		}
		catch (NoSuchDossierFileException nsee) {
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
	protected DossierFile removeImpl(DossierFile dossierFile) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dossierFile)) {
				dossierFile = (DossierFile)session.get(DossierFileImpl.class,
						dossierFile.getPrimaryKeyObj());
			}

			if (dossierFile != null) {
				session.delete(dossierFile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dossierFile != null) {
			clearCache(dossierFile);
		}

		return dossierFile;
	}

	@Override
	public DossierFile updateImpl(DossierFile dossierFile) {
		boolean isNew = dossierFile.isNew();

		if (!(dossierFile instanceof DossierFileModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dossierFile.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dossierFile);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dossierFile proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DossierFile implementation " +
				dossierFile.getClass());
		}

		DossierFileModelImpl dossierFileModelImpl = (DossierFileModelImpl)dossierFile;

		if (Validator.isNull(dossierFile.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dossierFile.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dossierFile.getCreateDate() == null)) {
			if (serviceContext == null) {
				dossierFile.setCreateDate(now);
			}
			else {
				dossierFile.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dossierFileModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dossierFile.setModifiedDate(now);
			}
			else {
				dossierFile.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dossierFile.isNew()) {
				session.save(dossierFile);

				dossierFile.setNew(false);
			}
			else {
				dossierFile = (DossierFile)session.merge(dossierFile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DossierFileModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dossierFileModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					dossierFileModelImpl.getUuid(),
					dossierFileModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					dossierFileModelImpl.getDossierId(),
					dossierFileModelImpl.getFileTemplateNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_FTN, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTN,
				args);

			args = new Object[] { dossierFileModelImpl.getDossierId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_,
				args);

			args = new Object[] {
					dossierFileModelImpl.getDossierId(),
					dossierFileModelImpl.isRemoved()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DOSSIERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOSSIERID,
				args);

			args = new Object[] {
					dossierFileModelImpl.getDossierId(),
					dossierFileModelImpl.getDossierPartType(),
					dossierFileModelImpl.isRemoved()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_D_DPT, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_DPT,
				args);

			args = new Object[] {
					dossierFileModelImpl.getDossierId(),
					dossierFileModelImpl.isIsNew(),
					dossierFileModelImpl.isRemoved()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_ISN, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_ISN,
				args);

			args = new Object[] {
					dossierFileModelImpl.getDossierId(),
					dossierFileModelImpl.getFileTemplateNo(),
					dossierFileModelImpl.isRemoved()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_FTNO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTNO,
				args);

			args = new Object[] {
					dossierFileModelImpl.getDossierId(),
					dossierFileModelImpl.getDossierPartNo(),
					dossierFileModelImpl.isRemoved()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_DPNO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_DPNO,
				args);

			args = new Object[] {
					dossierFileModelImpl.getDossierId(),
					dossierFileModelImpl.getFileTemplateNo(),
					dossierFileModelImpl.getDossierPartType(),
					dossierFileModelImpl.isRemoved()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_FTNO_DPT, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTNO_DPT,
				args);

			args = new Object[] { dossierFileModelImpl.getReferenceUid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_REF_UID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REF_UID,
				args);

			args = new Object[] { dossierFileModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
				args);

			args = new Object[] {
					dossierFileModelImpl.getDossierId(),
					dossierFileModelImpl.getFileTemplateNo(),
					dossierFileModelImpl.getDossierTemplateNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_D_FTN_DTN, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_FTN_DTN,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dossierFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierFileModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dossierFileModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dossierFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierFileModelImpl.getOriginalUuid(),
						dossierFileModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dossierFileModelImpl.getUuid(),
						dossierFileModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((dossierFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierFileModelImpl.getOriginalDossierId(),
						dossierFileModelImpl.getOriginalFileTemplateNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_FTN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTN,
					args);

				args = new Object[] {
						dossierFileModelImpl.getDossierId(),
						dossierFileModelImpl.getFileTemplateNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_FTN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTN,
					args);
			}

			if ((dossierFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierFileModelImpl.getOriginalDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_,
					args);

				args = new Object[] { dossierFileModelImpl.getDossierId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_,
					args);
			}

			if ((dossierFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOSSIERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierFileModelImpl.getOriginalDossierId(),
						dossierFileModelImpl.getOriginalRemoved()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DOSSIERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOSSIERID,
					args);

				args = new Object[] {
						dossierFileModelImpl.getDossierId(),
						dossierFileModelImpl.isRemoved()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DOSSIERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOSSIERID,
					args);
			}

			if ((dossierFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_DPT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierFileModelImpl.getOriginalDossierId(),
						dossierFileModelImpl.getOriginalDossierPartType(),
						dossierFileModelImpl.getOriginalRemoved()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_D_DPT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_DPT,
					args);

				args = new Object[] {
						dossierFileModelImpl.getDossierId(),
						dossierFileModelImpl.getDossierPartType(),
						dossierFileModelImpl.isRemoved()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_D_DPT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_DPT,
					args);
			}

			if ((dossierFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_ISN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierFileModelImpl.getOriginalDossierId(),
						dossierFileModelImpl.getOriginalIsNew(),
						dossierFileModelImpl.getOriginalRemoved()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_ISN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_ISN,
					args);

				args = new Object[] {
						dossierFileModelImpl.getDossierId(),
						dossierFileModelImpl.isIsNew(),
						dossierFileModelImpl.isRemoved()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_ISN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_ISN,
					args);
			}

			if ((dossierFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierFileModelImpl.getOriginalDossierId(),
						dossierFileModelImpl.getOriginalFileTemplateNo(),
						dossierFileModelImpl.getOriginalRemoved()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_FTNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTNO,
					args);

				args = new Object[] {
						dossierFileModelImpl.getDossierId(),
						dossierFileModelImpl.getFileTemplateNo(),
						dossierFileModelImpl.isRemoved()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_FTNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTNO,
					args);
			}

			if ((dossierFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_DPNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierFileModelImpl.getOriginalDossierId(),
						dossierFileModelImpl.getOriginalDossierPartNo(),
						dossierFileModelImpl.getOriginalRemoved()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_DPNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_DPNO,
					args);

				args = new Object[] {
						dossierFileModelImpl.getDossierId(),
						dossierFileModelImpl.getDossierPartNo(),
						dossierFileModelImpl.isRemoved()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_DPNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_DPNO,
					args);
			}

			if ((dossierFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTNO_DPT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierFileModelImpl.getOriginalDossierId(),
						dossierFileModelImpl.getOriginalFileTemplateNo(),
						dossierFileModelImpl.getOriginalDossierPartType(),
						dossierFileModelImpl.getOriginalRemoved()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_FTNO_DPT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTNO_DPT,
					args);

				args = new Object[] {
						dossierFileModelImpl.getDossierId(),
						dossierFileModelImpl.getFileTemplateNo(),
						dossierFileModelImpl.getDossierPartType(),
						dossierFileModelImpl.isRemoved()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DID_FTNO_DPT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DID_FTNO_DPT,
					args);
			}

			if ((dossierFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REF_UID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierFileModelImpl.getOriginalReferenceUid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_REF_UID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REF_UID,
					args);

				args = new Object[] { dossierFileModelImpl.getReferenceUid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_REF_UID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REF_UID,
					args);
			}

			if ((dossierFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierFileModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
					args);

				args = new Object[] { dossierFileModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
					args);
			}

			if ((dossierFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_FTN_DTN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierFileModelImpl.getOriginalDossierId(),
						dossierFileModelImpl.getOriginalFileTemplateNo(),
						dossierFileModelImpl.getOriginalDossierTemplateNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_D_FTN_DTN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_FTN_DTN,
					args);

				args = new Object[] {
						dossierFileModelImpl.getDossierId(),
						dossierFileModelImpl.getFileTemplateNo(),
						dossierFileModelImpl.getDossierTemplateNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_D_FTN_DTN, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_FTN_DTN,
					args);
			}
		}

		entityCache.putResult(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
			DossierFileImpl.class, dossierFile.getPrimaryKey(), dossierFile,
			false);

		clearUniqueFindersCache(dossierFileModelImpl, false);
		cacheUniqueFindersCache(dossierFileModelImpl);

		dossierFile.resetOriginalValues();

		return dossierFile;
	}

	/**
	 * Returns the dossier file with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier file
	 * @return the dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDossierFileException {
		DossierFile dossierFile = fetchByPrimaryKey(primaryKey);

		if (dossierFile == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDossierFileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dossierFile;
	}

	/**
	 * Returns the dossier file with the primary key or throws a {@link NoSuchDossierFileException} if it could not be found.
	 *
	 * @param dossierFileId the primary key of the dossier file
	 * @return the dossier file
	 * @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile findByPrimaryKey(long dossierFileId)
		throws NoSuchDossierFileException {
		return findByPrimaryKey((Serializable)dossierFileId);
	}

	/**
	 * Returns the dossier file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier file
	 * @return the dossier file, or <code>null</code> if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
				DossierFileImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DossierFile dossierFile = (DossierFile)serializable;

		if (dossierFile == null) {
			Session session = null;

			try {
				session = openSession();

				dossierFile = (DossierFile)session.get(DossierFileImpl.class,
						primaryKey);

				if (dossierFile != null) {
					cacheResult(dossierFile);
				}
				else {
					entityCache.putResult(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
						DossierFileImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
					DossierFileImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dossierFile;
	}

	/**
	 * Returns the dossier file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dossierFileId the primary key of the dossier file
	 * @return the dossier file, or <code>null</code> if a dossier file with the primary key could not be found
	 */
	@Override
	public DossierFile fetchByPrimaryKey(long dossierFileId) {
		return fetchByPrimaryKey((Serializable)dossierFileId);
	}

	@Override
	public Map<Serializable, DossierFile> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DossierFile> map = new HashMap<Serializable, DossierFile>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DossierFile dossierFile = fetchByPrimaryKey(primaryKey);

			if (dossierFile != null) {
				map.put(primaryKey, dossierFile);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
					DossierFileImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DossierFile)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DOSSIERFILE_WHERE_PKS_IN);

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

			for (DossierFile dossierFile : (List<DossierFile>)q.list()) {
				map.put(dossierFile.getPrimaryKeyObj(), dossierFile);

				cacheResult(dossierFile);

				uncachedPrimaryKeys.remove(dossierFile.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DossierFileModelImpl.ENTITY_CACHE_ENABLED,
					DossierFileImpl.class, primaryKey, nullModel);
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
	 * Returns all the dossier files.
	 *
	 * @return the dossier files
	 */
	@Override
	public List<DossierFile> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @return the range of dossier files
	 */
	@Override
	public List<DossierFile> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dossier files
	 */
	@Override
	public List<DossierFile> findAll(int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier files
	 * @param end the upper bound of the range of dossier files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dossier files
	 */
	@Override
	public List<DossierFile> findAll(int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
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

		List<DossierFile> list = null;

		if (retrieveFromCache) {
			list = (List<DossierFile>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOSSIERFILE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOSSIERFILE;

				if (pagination) {
					sql = sql.concat(DossierFileModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierFile>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the dossier files from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DossierFile dossierFile : findAll()) {
			remove(dossierFile);
		}
	}

	/**
	 * Returns the number of dossier files.
	 *
	 * @return the number of dossier files
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOSSIERFILE);

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
		return DossierFileModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dossier file persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DossierFileImpl.class.getName());
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
	private static final String _SQL_SELECT_DOSSIERFILE = "SELECT dossierFile FROM DossierFile dossierFile";
	private static final String _SQL_SELECT_DOSSIERFILE_WHERE_PKS_IN = "SELECT dossierFile FROM DossierFile dossierFile WHERE dossierFileId IN (";
	private static final String _SQL_SELECT_DOSSIERFILE_WHERE = "SELECT dossierFile FROM DossierFile dossierFile WHERE ";
	private static final String _SQL_COUNT_DOSSIERFILE = "SELECT COUNT(dossierFile) FROM DossierFile dossierFile";
	private static final String _SQL_COUNT_DOSSIERFILE_WHERE = "SELECT COUNT(dossierFile) FROM DossierFile dossierFile WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dossierFile.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DossierFile exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DossierFile exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DossierFilePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}