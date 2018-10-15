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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchDossierDocumentException;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.model.impl.DossierDocumentImpl;
import org.opencps.dossiermgt.model.impl.DossierDocumentModelImpl;
import org.opencps.dossiermgt.service.persistence.DossierDocumentPersistence;

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
 * The persistence implementation for the dossier document service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierDocumentPersistence
 * @see org.opencps.dossiermgt.service.persistence.DossierDocumentUtil
 * @generated
 */
@ProviderType
public class DossierDocumentPersistenceImpl extends BasePersistenceImpl<DossierDocument>
	implements DossierDocumentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DossierDocumentUtil} to access the dossier document persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DossierDocumentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED,
			DossierDocumentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED,
			DossierDocumentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED,
			DossierDocumentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED,
			DossierDocumentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DossierDocumentModelImpl.UUID_COLUMN_BITMASK |
			DossierDocumentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the dossier documents where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dossier documents
	 */
	@Override
	public List<DossierDocument> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier documents where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier documents
	 * @param end the upper bound of the range of dossier documents (not inclusive)
	 * @return the range of matching dossier documents
	 */
	@Override
	public List<DossierDocument> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier documents where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier documents
	 * @param end the upper bound of the range of dossier documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier documents
	 */
	@Override
	public List<DossierDocument> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierDocument> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier documents where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dossier documents
	 * @param end the upper bound of the range of dossier documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier documents
	 */
	@Override
	public List<DossierDocument> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierDocument> orderByComparator,
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

		List<DossierDocument> list = null;

		if (retrieveFromCache) {
			list = (List<DossierDocument>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierDocument dossierDocument : list) {
					if (!Objects.equals(uuid, dossierDocument.getUuid())) {
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

			query.append(_SQL_SELECT_DOSSIERDOCUMENT_WHERE);

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
				query.append(DossierDocumentModelImpl.ORDER_BY_JPQL);
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
					list = (List<DossierDocument>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierDocument>)QueryUtil.list(q,
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
	 * Returns the first dossier document in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier document
	 * @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument findByUuid_First(String uuid,
		OrderByComparator<DossierDocument> orderByComparator)
		throws NoSuchDossierDocumentException {
		DossierDocument dossierDocument = fetchByUuid_First(uuid,
				orderByComparator);

		if (dossierDocument != null) {
			return dossierDocument;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierDocumentException(msg.toString());
	}

	/**
	 * Returns the first dossier document in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier document, or <code>null</code> if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument fetchByUuid_First(String uuid,
		OrderByComparator<DossierDocument> orderByComparator) {
		List<DossierDocument> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier document in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier document
	 * @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument findByUuid_Last(String uuid,
		OrderByComparator<DossierDocument> orderByComparator)
		throws NoSuchDossierDocumentException {
		DossierDocument dossierDocument = fetchByUuid_Last(uuid,
				orderByComparator);

		if (dossierDocument != null) {
			return dossierDocument;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDossierDocumentException(msg.toString());
	}

	/**
	 * Returns the last dossier document in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier document, or <code>null</code> if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument fetchByUuid_Last(String uuid,
		OrderByComparator<DossierDocument> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DossierDocument> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier documents before and after the current dossier document in the ordered set where uuid = &#63;.
	 *
	 * @param DossierDocumentId the primary key of the current dossier document
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier document
	 * @throws NoSuchDossierDocumentException if a dossier document with the primary key could not be found
	 */
	@Override
	public DossierDocument[] findByUuid_PrevAndNext(long DossierDocumentId,
		String uuid, OrderByComparator<DossierDocument> orderByComparator)
		throws NoSuchDossierDocumentException {
		DossierDocument dossierDocument = findByPrimaryKey(DossierDocumentId);

		Session session = null;

		try {
			session = openSession();

			DossierDocument[] array = new DossierDocumentImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dossierDocument, uuid,
					orderByComparator, true);

			array[1] = dossierDocument;

			array[2] = getByUuid_PrevAndNext(session, dossierDocument, uuid,
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

	protected DossierDocument getByUuid_PrevAndNext(Session session,
		DossierDocument dossierDocument, String uuid,
		OrderByComparator<DossierDocument> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERDOCUMENT_WHERE);

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
			query.append(DossierDocumentModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dossierDocument);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierDocument> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier documents where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DossierDocument dossierDocument : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierDocument);
		}
	}

	/**
	 * Returns the number of dossier documents where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dossier documents
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERDOCUMENT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dossierDocument.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dossierDocument.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dossierDocument.uuid IS NULL OR dossierDocument.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED,
			DossierDocumentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DossierDocumentModelImpl.UUID_COLUMN_BITMASK |
			DossierDocumentModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier document where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierDocumentException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier document
	 * @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierDocumentException {
		DossierDocument dossierDocument = fetchByUUID_G(uuid, groupId);

		if (dossierDocument == null) {
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

			throw new NoSuchDossierDocumentException(msg.toString());
		}

		return dossierDocument;
	}

	/**
	 * Returns the dossier document where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dossier document where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DossierDocument) {
			DossierDocument dossierDocument = (DossierDocument)result;

			if (!Objects.equals(uuid, dossierDocument.getUuid()) ||
					(groupId != dossierDocument.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERDOCUMENT_WHERE);

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

				List<DossierDocument> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DossierDocument dossierDocument = list.get(0);

					result = dossierDocument;

					cacheResult(dossierDocument);
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
			return (DossierDocument)result;
		}
	}

	/**
	 * Removes the dossier document where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dossier document that was removed
	 */
	@Override
	public DossierDocument removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierDocumentException {
		DossierDocument dossierDocument = findByUUID_G(uuid, groupId);

		return remove(dossierDocument);
	}

	/**
	 * Returns the number of dossier documents where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dossier documents
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERDOCUMENT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dossierDocument.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dossierDocument.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dossierDocument.uuid IS NULL OR dossierDocument.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dossierDocument.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DOSSIERID =
		new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED,
			DossierDocumentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_DOSSIERID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DOSSIERID =
		new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED,
			DossierDocumentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_DOSSIERID",
			new String[] { Long.class.getName() },
			DossierDocumentModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierDocumentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DOSSIERID = new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_DOSSIERID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the dossier documents where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @return the matching dossier documents
	 */
	@Override
	public List<DossierDocument> findByF_DOSSIERID(long dossierId) {
		return findByF_DOSSIERID(dossierId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier documents where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier documents
	 * @param end the upper bound of the range of dossier documents (not inclusive)
	 * @return the range of matching dossier documents
	 */
	@Override
	public List<DossierDocument> findByF_DOSSIERID(long dossierId, int start,
		int end) {
		return findByF_DOSSIERID(dossierId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier documents where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier documents
	 * @param end the upper bound of the range of dossier documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dossier documents
	 */
	@Override
	public List<DossierDocument> findByF_DOSSIERID(long dossierId, int start,
		int end, OrderByComparator<DossierDocument> orderByComparator) {
		return findByF_DOSSIERID(dossierId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier documents where dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of dossier documents
	 * @param end the upper bound of the range of dossier documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching dossier documents
	 */
	@Override
	public List<DossierDocument> findByF_DOSSIERID(long dossierId, int start,
		int end, OrderByComparator<DossierDocument> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DOSSIERID;
			finderArgs = new Object[] { dossierId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DOSSIERID;
			finderArgs = new Object[] { dossierId, start, end, orderByComparator };
		}

		List<DossierDocument> list = null;

		if (retrieveFromCache) {
			list = (List<DossierDocument>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DossierDocument dossierDocument : list) {
					if ((dossierId != dossierDocument.getDossierId())) {
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

			query.append(_SQL_SELECT_DOSSIERDOCUMENT_WHERE);

			query.append(_FINDER_COLUMN_F_DOSSIERID_DOSSIERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DossierDocumentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dossierId);

				if (!pagination) {
					list = (List<DossierDocument>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierDocument>)QueryUtil.list(q,
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
	 * Returns the first dossier document in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier document
	 * @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument findByF_DOSSIERID_First(long dossierId,
		OrderByComparator<DossierDocument> orderByComparator)
		throws NoSuchDossierDocumentException {
		DossierDocument dossierDocument = fetchByF_DOSSIERID_First(dossierId,
				orderByComparator);

		if (dossierDocument != null) {
			return dossierDocument;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchDossierDocumentException(msg.toString());
	}

	/**
	 * Returns the first dossier document in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dossier document, or <code>null</code> if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument fetchByF_DOSSIERID_First(long dossierId,
		OrderByComparator<DossierDocument> orderByComparator) {
		List<DossierDocument> list = findByF_DOSSIERID(dossierId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dossier document in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier document
	 * @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument findByF_DOSSIERID_Last(long dossierId,
		OrderByComparator<DossierDocument> orderByComparator)
		throws NoSuchDossierDocumentException {
		DossierDocument dossierDocument = fetchByF_DOSSIERID_Last(dossierId,
				orderByComparator);

		if (dossierDocument != null) {
			return dossierDocument;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchDossierDocumentException(msg.toString());
	}

	/**
	 * Returns the last dossier document in the ordered set where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dossier document, or <code>null</code> if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument fetchByF_DOSSIERID_Last(long dossierId,
		OrderByComparator<DossierDocument> orderByComparator) {
		int count = countByF_DOSSIERID(dossierId);

		if (count == 0) {
			return null;
		}

		List<DossierDocument> list = findByF_DOSSIERID(dossierId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dossier documents before and after the current dossier document in the ordered set where dossierId = &#63;.
	 *
	 * @param DossierDocumentId the primary key of the current dossier document
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dossier document
	 * @throws NoSuchDossierDocumentException if a dossier document with the primary key could not be found
	 */
	@Override
	public DossierDocument[] findByF_DOSSIERID_PrevAndNext(
		long DossierDocumentId, long dossierId,
		OrderByComparator<DossierDocument> orderByComparator)
		throws NoSuchDossierDocumentException {
		DossierDocument dossierDocument = findByPrimaryKey(DossierDocumentId);

		Session session = null;

		try {
			session = openSession();

			DossierDocument[] array = new DossierDocumentImpl[3];

			array[0] = getByF_DOSSIERID_PrevAndNext(session, dossierDocument,
					dossierId, orderByComparator, true);

			array[1] = dossierDocument;

			array[2] = getByF_DOSSIERID_PrevAndNext(session, dossierDocument,
					dossierId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DossierDocument getByF_DOSSIERID_PrevAndNext(Session session,
		DossierDocument dossierDocument, long dossierId,
		OrderByComparator<DossierDocument> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOSSIERDOCUMENT_WHERE);

		query.append(_FINDER_COLUMN_F_DOSSIERID_DOSSIERID_2);

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
			query.append(DossierDocumentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dossierId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dossierDocument);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DossierDocument> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dossier documents where dossierId = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 */
	@Override
	public void removeByF_DOSSIERID(long dossierId) {
		for (DossierDocument dossierDocument : findByF_DOSSIERID(dossierId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dossierDocument);
		}
	}

	/**
	 * Returns the number of dossier documents where dossierId = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @return the number of matching dossier documents
	 */
	@Override
	public int countByF_DOSSIERID(long dossierId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DOSSIERID;

		Object[] finderArgs = new Object[] { dossierId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOSSIERDOCUMENT_WHERE);

			query.append(_FINDER_COLUMN_F_DOSSIERID_DOSSIERID_2);

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

	private static final String _FINDER_COLUMN_F_DOSSIERID_DOSSIERID_2 = "dossierDocument.dossierId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_DID = new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED,
			DossierDocumentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_GID_DID",
			new String[] { Long.class.getName(), Long.class.getName() },
			DossierDocumentModelImpl.GROUPID_COLUMN_BITMASK |
			DossierDocumentModelImpl.DOSSIERACTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_DID = new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_DID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the dossier document where groupId = &#63; and dossierActionId = &#63; or throws a {@link NoSuchDossierDocumentException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param dossierActionId the dossier action ID
	 * @return the matching dossier document
	 * @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument findByF_GID_DID(long groupId, long dossierActionId)
		throws NoSuchDossierDocumentException {
		DossierDocument dossierDocument = fetchByF_GID_DID(groupId,
				dossierActionId);

		if (dossierDocument == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", dossierActionId=");
			msg.append(dossierActionId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierDocumentException(msg.toString());
		}

		return dossierDocument;
	}

	/**
	 * Returns the dossier document where groupId = &#63; and dossierActionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierActionId the dossier action ID
	 * @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument fetchByF_GID_DID(long groupId, long dossierActionId) {
		return fetchByF_GID_DID(groupId, dossierActionId, true);
	}

	/**
	 * Returns the dossier document where groupId = &#63; and dossierActionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierActionId the dossier action ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument fetchByF_GID_DID(long groupId, long dossierActionId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, dossierActionId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_DID,
					finderArgs, this);
		}

		if (result instanceof DossierDocument) {
			DossierDocument dossierDocument = (DossierDocument)result;

			if ((groupId != dossierDocument.getGroupId()) ||
					(dossierActionId != dossierDocument.getDossierActionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOSSIERDOCUMENT_WHERE);

			query.append(_FINDER_COLUMN_F_GID_DID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_DID_DOSSIERACTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierActionId);

				List<DossierDocument> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_DID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierDocumentPersistenceImpl.fetchByF_GID_DID(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierDocument dossierDocument = list.get(0);

					result = dossierDocument;

					cacheResult(dossierDocument);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_DID,
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
			return (DossierDocument)result;
		}
	}

	/**
	 * Removes the dossier document where groupId = &#63; and dossierActionId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierActionId the dossier action ID
	 * @return the dossier document that was removed
	 */
	@Override
	public DossierDocument removeByF_GID_DID(long groupId, long dossierActionId)
		throws NoSuchDossierDocumentException {
		DossierDocument dossierDocument = findByF_GID_DID(groupId,
				dossierActionId);

		return remove(dossierDocument);
	}

	/**
	 * Returns the number of dossier documents where groupId = &#63; and dossierActionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierActionId the dossier action ID
	 * @return the number of matching dossier documents
	 */
	@Override
	public int countByF_GID_DID(long groupId, long dossierActionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_DID;

		Object[] finderArgs = new Object[] { groupId, dossierActionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOSSIERDOCUMENT_WHERE);

			query.append(_FINDER_COLUMN_F_GID_DID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_DID_DOSSIERACTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierActionId);

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

	private static final String _FINDER_COLUMN_F_GID_DID_GROUPID_2 = "dossierDocument.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_DID_DOSSIERACTIONID_2 = "dossierDocument.dossierActionId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_DID_REF = new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED,
			DossierDocumentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_GID_DID_REF",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			DossierDocumentModelImpl.GROUPID_COLUMN_BITMASK |
			DossierDocumentModelImpl.DOSSIERID_COLUMN_BITMASK |
			DossierDocumentModelImpl.REFERENCEUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_DID_REF = new FinderPath(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_DID_REF",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the dossier document where groupId = &#63; and dossierId = &#63; and referenceUid = &#63; or throws a {@link NoSuchDossierDocumentException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @return the matching dossier document
	 * @throws NoSuchDossierDocumentException if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument findByF_GID_DID_REF(long groupId, long dossierId,
		String referenceUid) throws NoSuchDossierDocumentException {
		DossierDocument dossierDocument = fetchByF_GID_DID_REF(groupId,
				dossierId, referenceUid);

		if (dossierDocument == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", dossierId=");
			msg.append(dossierId);

			msg.append(", referenceUid=");
			msg.append(referenceUid);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDossierDocumentException(msg.toString());
		}

		return dossierDocument;
	}

	/**
	 * Returns the dossier document where groupId = &#63; and dossierId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument fetchByF_GID_DID_REF(long groupId, long dossierId,
		String referenceUid) {
		return fetchByF_GID_DID_REF(groupId, dossierId, referenceUid, true);
	}

	/**
	 * Returns the dossier document where groupId = &#63; and dossierId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching dossier document, or <code>null</code> if a matching dossier document could not be found
	 */
	@Override
	public DossierDocument fetchByF_GID_DID_REF(long groupId, long dossierId,
		String referenceUid, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, dossierId, referenceUid };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_DID_REF,
					finderArgs, this);
		}

		if (result instanceof DossierDocument) {
			DossierDocument dossierDocument = (DossierDocument)result;

			if ((groupId != dossierDocument.getGroupId()) ||
					(dossierId != dossierDocument.getDossierId()) ||
					!Objects.equals(referenceUid,
						dossierDocument.getReferenceUid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DOSSIERDOCUMENT_WHERE);

			query.append(_FINDER_COLUMN_F_GID_DID_REF_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_DID_REF_DOSSIERID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_F_GID_DID_REF_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_DID_REF_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_F_GID_DID_REF_REFERENCEUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
				}

				List<DossierDocument> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_DID_REF,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DossierDocumentPersistenceImpl.fetchByF_GID_DID_REF(long, long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DossierDocument dossierDocument = list.get(0);

					result = dossierDocument;

					cacheResult(dossierDocument);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_DID_REF,
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
			return (DossierDocument)result;
		}
	}

	/**
	 * Removes the dossier document where groupId = &#63; and dossierId = &#63; and referenceUid = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @return the dossier document that was removed
	 */
	@Override
	public DossierDocument removeByF_GID_DID_REF(long groupId, long dossierId,
		String referenceUid) throws NoSuchDossierDocumentException {
		DossierDocument dossierDocument = findByF_GID_DID_REF(groupId,
				dossierId, referenceUid);

		return remove(dossierDocument);
	}

	/**
	 * Returns the number of dossier documents where groupId = &#63; and dossierId = &#63; and referenceUid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @return the number of matching dossier documents
	 */
	@Override
	public int countByF_GID_DID_REF(long groupId, long dossierId,
		String referenceUid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_DID_REF;

		Object[] finderArgs = new Object[] { groupId, dossierId, referenceUid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DOSSIERDOCUMENT_WHERE);

			query.append(_FINDER_COLUMN_F_GID_DID_REF_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_DID_REF_DOSSIERID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_F_GID_DID_REF_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_DID_REF_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_F_GID_DID_REF_REFERENCEUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_F_GID_DID_REF_GROUPID_2 = "dossierDocument.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_DID_REF_DOSSIERID_2 = "dossierDocument.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_DID_REF_REFERENCEUID_1 = "dossierDocument.referenceUid IS NULL";
	private static final String _FINDER_COLUMN_F_GID_DID_REF_REFERENCEUID_2 = "dossierDocument.referenceUid = ?";
	private static final String _FINDER_COLUMN_F_GID_DID_REF_REFERENCEUID_3 = "(dossierDocument.referenceUid IS NULL OR dossierDocument.referenceUid = '')";

	public DossierDocumentPersistenceImpl() {
		setModelClass(DossierDocument.class);

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
	 * Caches the dossier document in the entity cache if it is enabled.
	 *
	 * @param dossierDocument the dossier document
	 */
	@Override
	public void cacheResult(DossierDocument dossierDocument) {
		entityCache.putResult(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentImpl.class, dossierDocument.getPrimaryKey(),
			dossierDocument);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dossierDocument.getUuid(), dossierDocument.getGroupId() },
			dossierDocument);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_DID,
			new Object[] {
				dossierDocument.getGroupId(),
				dossierDocument.getDossierActionId()
			}, dossierDocument);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_DID_REF,
			new Object[] {
				dossierDocument.getGroupId(), dossierDocument.getDossierId(),
				dossierDocument.getReferenceUid()
			}, dossierDocument);

		dossierDocument.resetOriginalValues();
	}

	/**
	 * Caches the dossier documents in the entity cache if it is enabled.
	 *
	 * @param dossierDocuments the dossier documents
	 */
	@Override
	public void cacheResult(List<DossierDocument> dossierDocuments) {
		for (DossierDocument dossierDocument : dossierDocuments) {
			if (entityCache.getResult(
						DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
						DossierDocumentImpl.class,
						dossierDocument.getPrimaryKey()) == null) {
				cacheResult(dossierDocument);
			}
			else {
				dossierDocument.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dossier documents.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DossierDocumentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dossier document.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DossierDocument dossierDocument) {
		entityCache.removeResult(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentImpl.class, dossierDocument.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DossierDocumentModelImpl)dossierDocument, true);
	}

	@Override
	public void clearCache(List<DossierDocument> dossierDocuments) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DossierDocument dossierDocument : dossierDocuments) {
			entityCache.removeResult(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
				DossierDocumentImpl.class, dossierDocument.getPrimaryKey());

			clearUniqueFindersCache((DossierDocumentModelImpl)dossierDocument,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		DossierDocumentModelImpl dossierDocumentModelImpl) {
		Object[] args = new Object[] {
				dossierDocumentModelImpl.getUuid(),
				dossierDocumentModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			dossierDocumentModelImpl, false);

		args = new Object[] {
				dossierDocumentModelImpl.getGroupId(),
				dossierDocumentModelImpl.getDossierActionId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_DID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_DID, args,
			dossierDocumentModelImpl, false);

		args = new Object[] {
				dossierDocumentModelImpl.getGroupId(),
				dossierDocumentModelImpl.getDossierId(),
				dossierDocumentModelImpl.getReferenceUid()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_DID_REF, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_DID_REF, args,
			dossierDocumentModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DossierDocumentModelImpl dossierDocumentModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierDocumentModelImpl.getUuid(),
					dossierDocumentModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((dossierDocumentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierDocumentModelImpl.getOriginalUuid(),
					dossierDocumentModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierDocumentModelImpl.getGroupId(),
					dossierDocumentModelImpl.getDossierActionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_DID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_DID, args);
		}

		if ((dossierDocumentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_DID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierDocumentModelImpl.getOriginalGroupId(),
					dossierDocumentModelImpl.getOriginalDossierActionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_DID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_DID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					dossierDocumentModelImpl.getGroupId(),
					dossierDocumentModelImpl.getDossierId(),
					dossierDocumentModelImpl.getReferenceUid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_DID_REF, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_DID_REF, args);
		}

		if ((dossierDocumentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_DID_REF.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					dossierDocumentModelImpl.getOriginalGroupId(),
					dossierDocumentModelImpl.getOriginalDossierId(),
					dossierDocumentModelImpl.getOriginalReferenceUid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_DID_REF, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_DID_REF, args);
		}
	}

	/**
	 * Creates a new dossier document with the primary key. Does not add the dossier document to the database.
	 *
	 * @param DossierDocumentId the primary key for the new dossier document
	 * @return the new dossier document
	 */
	@Override
	public DossierDocument create(long DossierDocumentId) {
		DossierDocument dossierDocument = new DossierDocumentImpl();

		dossierDocument.setNew(true);
		dossierDocument.setPrimaryKey(DossierDocumentId);

		String uuid = PortalUUIDUtil.generate();

		dossierDocument.setUuid(uuid);

		return dossierDocument;
	}

	/**
	 * Removes the dossier document with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param DossierDocumentId the primary key of the dossier document
	 * @return the dossier document that was removed
	 * @throws NoSuchDossierDocumentException if a dossier document with the primary key could not be found
	 */
	@Override
	public DossierDocument remove(long DossierDocumentId)
		throws NoSuchDossierDocumentException {
		return remove((Serializable)DossierDocumentId);
	}

	/**
	 * Removes the dossier document with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dossier document
	 * @return the dossier document that was removed
	 * @throws NoSuchDossierDocumentException if a dossier document with the primary key could not be found
	 */
	@Override
	public DossierDocument remove(Serializable primaryKey)
		throws NoSuchDossierDocumentException {
		Session session = null;

		try {
			session = openSession();

			DossierDocument dossierDocument = (DossierDocument)session.get(DossierDocumentImpl.class,
					primaryKey);

			if (dossierDocument == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDossierDocumentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dossierDocument);
		}
		catch (NoSuchDossierDocumentException nsee) {
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
	protected DossierDocument removeImpl(DossierDocument dossierDocument) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dossierDocument)) {
				dossierDocument = (DossierDocument)session.get(DossierDocumentImpl.class,
						dossierDocument.getPrimaryKeyObj());
			}

			if (dossierDocument != null) {
				session.delete(dossierDocument);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dossierDocument != null) {
			clearCache(dossierDocument);
		}

		return dossierDocument;
	}

	@Override
	public DossierDocument updateImpl(DossierDocument dossierDocument) {
		boolean isNew = dossierDocument.isNew();

		if (!(dossierDocument instanceof DossierDocumentModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dossierDocument.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dossierDocument);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dossierDocument proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DossierDocument implementation " +
				dossierDocument.getClass());
		}

		DossierDocumentModelImpl dossierDocumentModelImpl = (DossierDocumentModelImpl)dossierDocument;

		if (Validator.isNull(dossierDocument.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dossierDocument.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dossierDocument.getCreateDate() == null)) {
			if (serviceContext == null) {
				dossierDocument.setCreateDate(now);
			}
			else {
				dossierDocument.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dossierDocumentModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dossierDocument.setModifiedDate(now);
			}
			else {
				dossierDocument.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dossierDocument.isNew()) {
				session.save(dossierDocument);

				dossierDocument.setNew(false);
			}
			else {
				dossierDocument = (DossierDocument)session.merge(dossierDocument);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DossierDocumentModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { dossierDocumentModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { dossierDocumentModelImpl.getDossierId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DOSSIERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DOSSIERID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((dossierDocumentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierDocumentModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dossierDocumentModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dossierDocumentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DOSSIERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dossierDocumentModelImpl.getOriginalDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DOSSIERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DOSSIERID,
					args);

				args = new Object[] { dossierDocumentModelImpl.getDossierId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DOSSIERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DOSSIERID,
					args);
			}
		}

		entityCache.putResult(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
			DossierDocumentImpl.class, dossierDocument.getPrimaryKey(),
			dossierDocument, false);

		clearUniqueFindersCache(dossierDocumentModelImpl, false);
		cacheUniqueFindersCache(dossierDocumentModelImpl);

		dossierDocument.resetOriginalValues();

		return dossierDocument;
	}

	/**
	 * Returns the dossier document with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier document
	 * @return the dossier document
	 * @throws NoSuchDossierDocumentException if a dossier document with the primary key could not be found
	 */
	@Override
	public DossierDocument findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDossierDocumentException {
		DossierDocument dossierDocument = fetchByPrimaryKey(primaryKey);

		if (dossierDocument == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDossierDocumentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dossierDocument;
	}

	/**
	 * Returns the dossier document with the primary key or throws a {@link NoSuchDossierDocumentException} if it could not be found.
	 *
	 * @param DossierDocumentId the primary key of the dossier document
	 * @return the dossier document
	 * @throws NoSuchDossierDocumentException if a dossier document with the primary key could not be found
	 */
	@Override
	public DossierDocument findByPrimaryKey(long DossierDocumentId)
		throws NoSuchDossierDocumentException {
		return findByPrimaryKey((Serializable)DossierDocumentId);
	}

	/**
	 * Returns the dossier document with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dossier document
	 * @return the dossier document, or <code>null</code> if a dossier document with the primary key could not be found
	 */
	@Override
	public DossierDocument fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
				DossierDocumentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DossierDocument dossierDocument = (DossierDocument)serializable;

		if (dossierDocument == null) {
			Session session = null;

			try {
				session = openSession();

				dossierDocument = (DossierDocument)session.get(DossierDocumentImpl.class,
						primaryKey);

				if (dossierDocument != null) {
					cacheResult(dossierDocument);
				}
				else {
					entityCache.putResult(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
						DossierDocumentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
					DossierDocumentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dossierDocument;
	}

	/**
	 * Returns the dossier document with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param DossierDocumentId the primary key of the dossier document
	 * @return the dossier document, or <code>null</code> if a dossier document with the primary key could not be found
	 */
	@Override
	public DossierDocument fetchByPrimaryKey(long DossierDocumentId) {
		return fetchByPrimaryKey((Serializable)DossierDocumentId);
	}

	@Override
	public Map<Serializable, DossierDocument> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DossierDocument> map = new HashMap<Serializable, DossierDocument>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DossierDocument dossierDocument = fetchByPrimaryKey(primaryKey);

			if (dossierDocument != null) {
				map.put(primaryKey, dossierDocument);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
					DossierDocumentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DossierDocument)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DOSSIERDOCUMENT_WHERE_PKS_IN);

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

			for (DossierDocument dossierDocument : (List<DossierDocument>)q.list()) {
				map.put(dossierDocument.getPrimaryKeyObj(), dossierDocument);

				cacheResult(dossierDocument);

				uncachedPrimaryKeys.remove(dossierDocument.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DossierDocumentModelImpl.ENTITY_CACHE_ENABLED,
					DossierDocumentImpl.class, primaryKey, nullModel);
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
	 * Returns all the dossier documents.
	 *
	 * @return the dossier documents
	 */
	@Override
	public List<DossierDocument> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dossier documents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier documents
	 * @param end the upper bound of the range of dossier documents (not inclusive)
	 * @return the range of dossier documents
	 */
	@Override
	public List<DossierDocument> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dossier documents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier documents
	 * @param end the upper bound of the range of dossier documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dossier documents
	 */
	@Override
	public List<DossierDocument> findAll(int start, int end,
		OrderByComparator<DossierDocument> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dossier documents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dossier documents
	 * @param end the upper bound of the range of dossier documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of dossier documents
	 */
	@Override
	public List<DossierDocument> findAll(int start, int end,
		OrderByComparator<DossierDocument> orderByComparator,
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

		List<DossierDocument> list = null;

		if (retrieveFromCache) {
			list = (List<DossierDocument>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOSSIERDOCUMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOSSIERDOCUMENT;

				if (pagination) {
					sql = sql.concat(DossierDocumentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DossierDocument>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DossierDocument>)QueryUtil.list(q,
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
	 * Removes all the dossier documents from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DossierDocument dossierDocument : findAll()) {
			remove(dossierDocument);
		}
	}

	/**
	 * Returns the number of dossier documents.
	 *
	 * @return the number of dossier documents
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOSSIERDOCUMENT);

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
		return DossierDocumentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dossier document persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DossierDocumentImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_DOSSIERDOCUMENT = "SELECT dossierDocument FROM DossierDocument dossierDocument";
	private static final String _SQL_SELECT_DOSSIERDOCUMENT_WHERE_PKS_IN = "SELECT dossierDocument FROM DossierDocument dossierDocument WHERE DossierDocumentId IN (";
	private static final String _SQL_SELECT_DOSSIERDOCUMENT_WHERE = "SELECT dossierDocument FROM DossierDocument dossierDocument WHERE ";
	private static final String _SQL_COUNT_DOSSIERDOCUMENT = "SELECT COUNT(dossierDocument) FROM DossierDocument dossierDocument";
	private static final String _SQL_COUNT_DOSSIERDOCUMENT_WHERE = "SELECT COUNT(dossierDocument) FROM DossierDocument dossierDocument WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dossierDocument.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DossierDocument exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DossierDocument exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DossierDocumentPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}