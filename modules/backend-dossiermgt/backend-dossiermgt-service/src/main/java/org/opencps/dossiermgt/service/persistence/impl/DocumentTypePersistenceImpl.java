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

import org.opencps.dossiermgt.exception.NoSuchDocumentTypeException;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.model.impl.DocumentTypeImpl;
import org.opencps.dossiermgt.model.impl.DocumentTypeModelImpl;
import org.opencps.dossiermgt.service.persistence.DocumentTypePersistence;

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
 * The persistence implementation for the document type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DocumentTypePersistence
 * @see org.opencps.dossiermgt.service.persistence.DocumentTypeUtil
 * @generated
 */
@ProviderType
public class DocumentTypePersistenceImpl extends BasePersistenceImpl<DocumentType>
	implements DocumentTypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DocumentTypeUtil} to access the document type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DocumentTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
			DocumentTypeModelImpl.FINDER_CACHE_ENABLED, DocumentTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
			DocumentTypeModelImpl.FINDER_CACHE_ENABLED, DocumentTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
			DocumentTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
			DocumentTypeModelImpl.FINDER_CACHE_ENABLED, DocumentTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
			DocumentTypeModelImpl.FINDER_CACHE_ENABLED, DocumentTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DocumentTypeModelImpl.UUID_COLUMN_BITMASK |
			DocumentTypeModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
			DocumentTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the document types where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching document types
	 */
	@Override
	public List<DocumentType> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of document types
	 * @param end the upper bound of the range of document types (not inclusive)
	 * @return the range of matching document types
	 */
	@Override
	public List<DocumentType> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of document types
	 * @param end the upper bound of the range of document types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document types
	 */
	@Override
	public List<DocumentType> findByUuid(String uuid, int start, int end,
		OrderByComparator<DocumentType> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of document types
	 * @param end the upper bound of the range of document types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching document types
	 */
	@Override
	public List<DocumentType> findByUuid(String uuid, int start, int end,
		OrderByComparator<DocumentType> orderByComparator,
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

		List<DocumentType> list = null;

		if (retrieveFromCache) {
			list = (List<DocumentType>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DocumentType documentType : list) {
					if (!Objects.equals(uuid, documentType.getUuid())) {
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

			query.append(_SQL_SELECT_DOCUMENTTYPE_WHERE);

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
				query.append(DocumentTypeModelImpl.ORDER_BY_JPQL);
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
					list = (List<DocumentType>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DocumentType>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first document type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document type
	 * @throws NoSuchDocumentTypeException if a matching document type could not be found
	 */
	@Override
	public DocumentType findByUuid_First(String uuid,
		OrderByComparator<DocumentType> orderByComparator)
		throws NoSuchDocumentTypeException {
		DocumentType documentType = fetchByUuid_First(uuid, orderByComparator);

		if (documentType != null) {
			return documentType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDocumentTypeException(msg.toString());
	}

	/**
	 * Returns the first document type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document type, or <code>null</code> if a matching document type could not be found
	 */
	@Override
	public DocumentType fetchByUuid_First(String uuid,
		OrderByComparator<DocumentType> orderByComparator) {
		List<DocumentType> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document type
	 * @throws NoSuchDocumentTypeException if a matching document type could not be found
	 */
	@Override
	public DocumentType findByUuid_Last(String uuid,
		OrderByComparator<DocumentType> orderByComparator)
		throws NoSuchDocumentTypeException {
		DocumentType documentType = fetchByUuid_Last(uuid, orderByComparator);

		if (documentType != null) {
			return documentType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDocumentTypeException(msg.toString());
	}

	/**
	 * Returns the last document type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document type, or <code>null</code> if a matching document type could not be found
	 */
	@Override
	public DocumentType fetchByUuid_Last(String uuid,
		OrderByComparator<DocumentType> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DocumentType> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document types before and after the current document type in the ordered set where uuid = &#63;.
	 *
	 * @param DocumentTypeId the primary key of the current document type
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document type
	 * @throws NoSuchDocumentTypeException if a document type with the primary key could not be found
	 */
	@Override
	public DocumentType[] findByUuid_PrevAndNext(long DocumentTypeId,
		String uuid, OrderByComparator<DocumentType> orderByComparator)
		throws NoSuchDocumentTypeException {
		DocumentType documentType = findByPrimaryKey(DocumentTypeId);

		Session session = null;

		try {
			session = openSession();

			DocumentType[] array = new DocumentTypeImpl[3];

			array[0] = getByUuid_PrevAndNext(session, documentType, uuid,
					orderByComparator, true);

			array[1] = documentType;

			array[2] = getByUuid_PrevAndNext(session, documentType, uuid,
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

	protected DocumentType getByUuid_PrevAndNext(Session session,
		DocumentType documentType, String uuid,
		OrderByComparator<DocumentType> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOCUMENTTYPE_WHERE);

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
			query.append(DocumentTypeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(documentType);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DocumentType> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the document types where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DocumentType documentType : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(documentType);
		}
	}

	/**
	 * Returns the number of document types where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching document types
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOCUMENTTYPE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "documentType.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "documentType.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(documentType.uuid IS NULL OR documentType.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
			DocumentTypeModelImpl.FINDER_CACHE_ENABLED, DocumentTypeImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DocumentTypeModelImpl.UUID_COLUMN_BITMASK |
			DocumentTypeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
			DocumentTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the document type where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDocumentTypeException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching document type
	 * @throws NoSuchDocumentTypeException if a matching document type could not be found
	 */
	@Override
	public DocumentType findByUUID_G(String uuid, long groupId)
		throws NoSuchDocumentTypeException {
		DocumentType documentType = fetchByUUID_G(uuid, groupId);

		if (documentType == null) {
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

			throw new NoSuchDocumentTypeException(msg.toString());
		}

		return documentType;
	}

	/**
	 * Returns the document type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching document type, or <code>null</code> if a matching document type could not be found
	 */
	@Override
	public DocumentType fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the document type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching document type, or <code>null</code> if a matching document type could not be found
	 */
	@Override
	public DocumentType fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DocumentType) {
			DocumentType documentType = (DocumentType)result;

			if (!Objects.equals(uuid, documentType.getUuid()) ||
					(groupId != documentType.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOCUMENTTYPE_WHERE);

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

				List<DocumentType> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DocumentType documentType = list.get(0);

					result = documentType;

					cacheResult(documentType);
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
			return (DocumentType)result;
		}
	}

	/**
	 * Removes the document type where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the document type that was removed
	 */
	@Override
	public DocumentType removeByUUID_G(String uuid, long groupId)
		throws NoSuchDocumentTypeException {
		DocumentType documentType = findByUUID_G(uuid, groupId);

		return remove(documentType);
	}

	/**
	 * Returns the number of document types where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching document types
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOCUMENTTYPE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "documentType.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "documentType.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(documentType.uuid IS NULL OR documentType.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "documentType.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_CODE = new FinderPath(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
			DocumentTypeModelImpl.FINDER_CACHE_ENABLED, DocumentTypeImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_CODE",
			new String[] { Long.class.getName(), String.class.getName() },
			DocumentTypeModelImpl.GROUPID_COLUMN_BITMASK |
			DocumentTypeModelImpl.TYPECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_CODE = new FinderPath(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
			DocumentTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_CODE",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the document type where groupId = &#63; and typeCode = &#63; or throws a {@link NoSuchDocumentTypeException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param typeCode the type code
	 * @return the matching document type
	 * @throws NoSuchDocumentTypeException if a matching document type could not be found
	 */
	@Override
	public DocumentType findByF_CODE(long groupId, String typeCode)
		throws NoSuchDocumentTypeException {
		DocumentType documentType = fetchByF_CODE(groupId, typeCode);

		if (documentType == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", typeCode=");
			msg.append(typeCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDocumentTypeException(msg.toString());
		}

		return documentType;
	}

	/**
	 * Returns the document type where groupId = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param typeCode the type code
	 * @return the matching document type, or <code>null</code> if a matching document type could not be found
	 */
	@Override
	public DocumentType fetchByF_CODE(long groupId, String typeCode) {
		return fetchByF_CODE(groupId, typeCode, true);
	}

	/**
	 * Returns the document type where groupId = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param typeCode the type code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching document type, or <code>null</code> if a matching document type could not be found
	 */
	@Override
	public DocumentType fetchByF_CODE(long groupId, String typeCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, typeCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_CODE,
					finderArgs, this);
		}

		if (result instanceof DocumentType) {
			DocumentType documentType = (DocumentType)result;

			if ((groupId != documentType.getGroupId()) ||
					!Objects.equals(typeCode, documentType.getTypeCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOCUMENTTYPE_WHERE);

			query.append(_FINDER_COLUMN_F_CODE_GROUPID_2);

			boolean bindTypeCode = false;

			if (typeCode == null) {
				query.append(_FINDER_COLUMN_F_CODE_TYPECODE_1);
			}
			else if (typeCode.equals("")) {
				query.append(_FINDER_COLUMN_F_CODE_TYPECODE_3);
			}
			else {
				bindTypeCode = true;

				query.append(_FINDER_COLUMN_F_CODE_TYPECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTypeCode) {
					qPos.add(typeCode);
				}

				List<DocumentType> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DocumentTypePersistenceImpl.fetchByF_CODE(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DocumentType documentType = list.get(0);

					result = documentType;

					cacheResult(documentType);
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
			return (DocumentType)result;
		}
	}

	/**
	 * Removes the document type where groupId = &#63; and typeCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param typeCode the type code
	 * @return the document type that was removed
	 */
	@Override
	public DocumentType removeByF_CODE(long groupId, String typeCode)
		throws NoSuchDocumentTypeException {
		DocumentType documentType = findByF_CODE(groupId, typeCode);

		return remove(documentType);
	}

	/**
	 * Returns the number of document types where groupId = &#63; and typeCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param typeCode the type code
	 * @return the number of matching document types
	 */
	@Override
	public int countByF_CODE(long groupId, String typeCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_CODE;

		Object[] finderArgs = new Object[] { groupId, typeCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOCUMENTTYPE_WHERE);

			query.append(_FINDER_COLUMN_F_CODE_GROUPID_2);

			boolean bindTypeCode = false;

			if (typeCode == null) {
				query.append(_FINDER_COLUMN_F_CODE_TYPECODE_1);
			}
			else if (typeCode.equals("")) {
				query.append(_FINDER_COLUMN_F_CODE_TYPECODE_3);
			}
			else {
				bindTypeCode = true;

				query.append(_FINDER_COLUMN_F_CODE_TYPECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTypeCode) {
					qPos.add(typeCode);
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

	private static final String _FINDER_COLUMN_F_CODE_GROUPID_2 = "documentType.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_CODE_TYPECODE_1 = "documentType.typeCode IS NULL";
	private static final String _FINDER_COLUMN_F_CODE_TYPECODE_2 = "documentType.typeCode = ?";
	private static final String _FINDER_COLUMN_F_CODE_TYPECODE_3 = "(documentType.typeCode IS NULL OR documentType.typeCode = '')";

	public DocumentTypePersistenceImpl() {
		setModelClass(DocumentType.class);

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
	 * Caches the document type in the entity cache if it is enabled.
	 *
	 * @param documentType the document type
	 */
	@Override
	public void cacheResult(DocumentType documentType) {
		entityCache.putResult(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
			DocumentTypeImpl.class, documentType.getPrimaryKey(), documentType);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { documentType.getUuid(), documentType.getGroupId() },
			documentType);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE,
			new Object[] { documentType.getGroupId(), documentType.getTypeCode() },
			documentType);

		documentType.resetOriginalValues();
	}

	/**
	 * Caches the document types in the entity cache if it is enabled.
	 *
	 * @param documentTypes the document types
	 */
	@Override
	public void cacheResult(List<DocumentType> documentTypes) {
		for (DocumentType documentType : documentTypes) {
			if (entityCache.getResult(
						DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
						DocumentTypeImpl.class, documentType.getPrimaryKey()) == null) {
				cacheResult(documentType);
			}
			else {
				documentType.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all document types.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DocumentTypeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the document type.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DocumentType documentType) {
		entityCache.removeResult(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
			DocumentTypeImpl.class, documentType.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DocumentTypeModelImpl)documentType, true);
	}

	@Override
	public void clearCache(List<DocumentType> documentTypes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DocumentType documentType : documentTypes) {
			entityCache.removeResult(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
				DocumentTypeImpl.class, documentType.getPrimaryKey());

			clearUniqueFindersCache((DocumentTypeModelImpl)documentType, true);
		}
	}

	protected void cacheUniqueFindersCache(
		DocumentTypeModelImpl documentTypeModelImpl) {
		Object[] args = new Object[] {
				documentTypeModelImpl.getUuid(),
				documentTypeModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			documentTypeModelImpl, false);

		args = new Object[] {
				documentTypeModelImpl.getGroupId(),
				documentTypeModelImpl.getTypeCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_CODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE, args,
			documentTypeModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DocumentTypeModelImpl documentTypeModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					documentTypeModelImpl.getUuid(),
					documentTypeModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((documentTypeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					documentTypeModelImpl.getOriginalUuid(),
					documentTypeModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					documentTypeModelImpl.getGroupId(),
					documentTypeModelImpl.getTypeCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CODE, args);
		}

		if ((documentTypeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_CODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					documentTypeModelImpl.getOriginalGroupId(),
					documentTypeModelImpl.getOriginalTypeCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CODE, args);
		}
	}

	/**
	 * Creates a new document type with the primary key. Does not add the document type to the database.
	 *
	 * @param DocumentTypeId the primary key for the new document type
	 * @return the new document type
	 */
	@Override
	public DocumentType create(long DocumentTypeId) {
		DocumentType documentType = new DocumentTypeImpl();

		documentType.setNew(true);
		documentType.setPrimaryKey(DocumentTypeId);

		String uuid = PortalUUIDUtil.generate();

		documentType.setUuid(uuid);

		return documentType;
	}

	/**
	 * Removes the document type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param DocumentTypeId the primary key of the document type
	 * @return the document type that was removed
	 * @throws NoSuchDocumentTypeException if a document type with the primary key could not be found
	 */
	@Override
	public DocumentType remove(long DocumentTypeId)
		throws NoSuchDocumentTypeException {
		return remove((Serializable)DocumentTypeId);
	}

	/**
	 * Removes the document type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the document type
	 * @return the document type that was removed
	 * @throws NoSuchDocumentTypeException if a document type with the primary key could not be found
	 */
	@Override
	public DocumentType remove(Serializable primaryKey)
		throws NoSuchDocumentTypeException {
		Session session = null;

		try {
			session = openSession();

			DocumentType documentType = (DocumentType)session.get(DocumentTypeImpl.class,
					primaryKey);

			if (documentType == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDocumentTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(documentType);
		}
		catch (NoSuchDocumentTypeException nsee) {
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
	protected DocumentType removeImpl(DocumentType documentType) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(documentType)) {
				documentType = (DocumentType)session.get(DocumentTypeImpl.class,
						documentType.getPrimaryKeyObj());
			}

			if (documentType != null) {
				session.delete(documentType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (documentType != null) {
			clearCache(documentType);
		}

		return documentType;
	}

	@Override
	public DocumentType updateImpl(DocumentType documentType) {
		boolean isNew = documentType.isNew();

		if (!(documentType instanceof DocumentTypeModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(documentType.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(documentType);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in documentType proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DocumentType implementation " +
				documentType.getClass());
		}

		DocumentTypeModelImpl documentTypeModelImpl = (DocumentTypeModelImpl)documentType;

		if (Validator.isNull(documentType.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			documentType.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (documentType.getCreateDate() == null)) {
			if (serviceContext == null) {
				documentType.setCreateDate(now);
			}
			else {
				documentType.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!documentTypeModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				documentType.setModifiedDate(now);
			}
			else {
				documentType.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (documentType.isNew()) {
				session.save(documentType);

				documentType.setNew(false);
			}
			else {
				documentType = (DocumentType)session.merge(documentType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DocumentTypeModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { documentTypeModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((documentTypeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						documentTypeModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { documentTypeModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		entityCache.putResult(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
			DocumentTypeImpl.class, documentType.getPrimaryKey(), documentType,
			false);

		clearUniqueFindersCache(documentTypeModelImpl, false);
		cacheUniqueFindersCache(documentTypeModelImpl);

		documentType.resetOriginalValues();

		return documentType;
	}

	/**
	 * Returns the document type with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the document type
	 * @return the document type
	 * @throws NoSuchDocumentTypeException if a document type with the primary key could not be found
	 */
	@Override
	public DocumentType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDocumentTypeException {
		DocumentType documentType = fetchByPrimaryKey(primaryKey);

		if (documentType == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDocumentTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return documentType;
	}

	/**
	 * Returns the document type with the primary key or throws a {@link NoSuchDocumentTypeException} if it could not be found.
	 *
	 * @param DocumentTypeId the primary key of the document type
	 * @return the document type
	 * @throws NoSuchDocumentTypeException if a document type with the primary key could not be found
	 */
	@Override
	public DocumentType findByPrimaryKey(long DocumentTypeId)
		throws NoSuchDocumentTypeException {
		return findByPrimaryKey((Serializable)DocumentTypeId);
	}

	/**
	 * Returns the document type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the document type
	 * @return the document type, or <code>null</code> if a document type with the primary key could not be found
	 */
	@Override
	public DocumentType fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
				DocumentTypeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DocumentType documentType = (DocumentType)serializable;

		if (documentType == null) {
			Session session = null;

			try {
				session = openSession();

				documentType = (DocumentType)session.get(DocumentTypeImpl.class,
						primaryKey);

				if (documentType != null) {
					cacheResult(documentType);
				}
				else {
					entityCache.putResult(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
						DocumentTypeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
					DocumentTypeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return documentType;
	}

	/**
	 * Returns the document type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param DocumentTypeId the primary key of the document type
	 * @return the document type, or <code>null</code> if a document type with the primary key could not be found
	 */
	@Override
	public DocumentType fetchByPrimaryKey(long DocumentTypeId) {
		return fetchByPrimaryKey((Serializable)DocumentTypeId);
	}

	@Override
	public Map<Serializable, DocumentType> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DocumentType> map = new HashMap<Serializable, DocumentType>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DocumentType documentType = fetchByPrimaryKey(primaryKey);

			if (documentType != null) {
				map.put(primaryKey, documentType);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
					DocumentTypeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DocumentType)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DOCUMENTTYPE_WHERE_PKS_IN);

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

			for (DocumentType documentType : (List<DocumentType>)q.list()) {
				map.put(documentType.getPrimaryKeyObj(), documentType);

				cacheResult(documentType);

				uncachedPrimaryKeys.remove(documentType.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DocumentTypeModelImpl.ENTITY_CACHE_ENABLED,
					DocumentTypeImpl.class, primaryKey, nullModel);
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
	 * Returns all the document types.
	 *
	 * @return the document types
	 */
	@Override
	public List<DocumentType> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of document types
	 * @param end the upper bound of the range of document types (not inclusive)
	 * @return the range of document types
	 */
	@Override
	public List<DocumentType> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the document types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of document types
	 * @param end the upper bound of the range of document types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of document types
	 */
	@Override
	public List<DocumentType> findAll(int start, int end,
		OrderByComparator<DocumentType> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of document types
	 * @param end the upper bound of the range of document types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of document types
	 */
	@Override
	public List<DocumentType> findAll(int start, int end,
		OrderByComparator<DocumentType> orderByComparator,
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

		List<DocumentType> list = null;

		if (retrieveFromCache) {
			list = (List<DocumentType>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOCUMENTTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOCUMENTTYPE;

				if (pagination) {
					sql = sql.concat(DocumentTypeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DocumentType>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DocumentType>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the document types from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DocumentType documentType : findAll()) {
			remove(documentType);
		}
	}

	/**
	 * Returns the number of document types.
	 *
	 * @return the number of document types
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOCUMENTTYPE);

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
		return DocumentTypeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the document type persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DocumentTypeImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_DOCUMENTTYPE = "SELECT documentType FROM DocumentType documentType";
	private static final String _SQL_SELECT_DOCUMENTTYPE_WHERE_PKS_IN = "SELECT documentType FROM DocumentType documentType WHERE DocumentTypeId IN (";
	private static final String _SQL_SELECT_DOCUMENTTYPE_WHERE = "SELECT documentType FROM DocumentType documentType WHERE ";
	private static final String _SQL_COUNT_DOCUMENTTYPE = "SELECT COUNT(documentType) FROM DocumentType documentType";
	private static final String _SQL_COUNT_DOCUMENTTYPE_WHERE = "SELECT COUNT(documentType) FROM DocumentType documentType WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "documentType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DocumentType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DocumentType exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DocumentTypePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}