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

import org.opencps.dossiermgt.exception.NoSuchPaymentFileException;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.impl.PaymentFileImpl;
import org.opencps.dossiermgt.model.impl.PaymentFileModelImpl;
import org.opencps.dossiermgt.service.persistence.PaymentFilePersistence;

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
 * The persistence implementation for the payment file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see PaymentFilePersistence
 * @see org.opencps.dossiermgt.service.persistence.PaymentFileUtil
 * @generated
 */
@ProviderType
public class PaymentFilePersistenceImpl extends BasePersistenceImpl<PaymentFile>
	implements PaymentFilePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PaymentFileUtil} to access the payment file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PaymentFileImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, PaymentFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, PaymentFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, PaymentFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, PaymentFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PaymentFileModelImpl.UUID_COLUMN_BITMASK |
			PaymentFileModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the payment files where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching payment files
	 */
	@Override
	public List<PaymentFile> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment files where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment files
	 * @param end the upper bound of the range of payment files (not inclusive)
	 * @return the range of matching payment files
	 */
	@Override
	public List<PaymentFile> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment files where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment files
	 * @param end the upper bound of the range of payment files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment files
	 */
	@Override
	public List<PaymentFile> findByUuid(String uuid, int start, int end,
		OrderByComparator<PaymentFile> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the payment files where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment files
	 * @param end the upper bound of the range of payment files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching payment files
	 */
	@Override
	public List<PaymentFile> findByUuid(String uuid, int start, int end,
		OrderByComparator<PaymentFile> orderByComparator,
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

		List<PaymentFile> list = null;

		if (retrieveFromCache) {
			list = (List<PaymentFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PaymentFile paymentFile : list) {
					if (!Objects.equals(uuid, paymentFile.getUuid())) {
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

			query.append(_SQL_SELECT_PAYMENTFILE_WHERE);

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
				query.append(PaymentFileModelImpl.ORDER_BY_JPQL);
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
					list = (List<PaymentFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PaymentFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first payment file in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment file
	 * @throws NoSuchPaymentFileException if a matching payment file could not be found
	 */
	@Override
	public PaymentFile findByUuid_First(String uuid,
		OrderByComparator<PaymentFile> orderByComparator)
		throws NoSuchPaymentFileException {
		PaymentFile paymentFile = fetchByUuid_First(uuid, orderByComparator);

		if (paymentFile != null) {
			return paymentFile;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPaymentFileException(msg.toString());
	}

	/**
	 * Returns the first payment file in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment file, or <code>null</code> if a matching payment file could not be found
	 */
	@Override
	public PaymentFile fetchByUuid_First(String uuid,
		OrderByComparator<PaymentFile> orderByComparator) {
		List<PaymentFile> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment file in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment file
	 * @throws NoSuchPaymentFileException if a matching payment file could not be found
	 */
	@Override
	public PaymentFile findByUuid_Last(String uuid,
		OrderByComparator<PaymentFile> orderByComparator)
		throws NoSuchPaymentFileException {
		PaymentFile paymentFile = fetchByUuid_Last(uuid, orderByComparator);

		if (paymentFile != null) {
			return paymentFile;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPaymentFileException(msg.toString());
	}

	/**
	 * Returns the last payment file in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment file, or <code>null</code> if a matching payment file could not be found
	 */
	@Override
	public PaymentFile fetchByUuid_Last(String uuid,
		OrderByComparator<PaymentFile> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PaymentFile> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment files before and after the current payment file in the ordered set where uuid = &#63;.
	 *
	 * @param paymentFileId the primary key of the current payment file
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment file
	 * @throws NoSuchPaymentFileException if a payment file with the primary key could not be found
	 */
	@Override
	public PaymentFile[] findByUuid_PrevAndNext(long paymentFileId,
		String uuid, OrderByComparator<PaymentFile> orderByComparator)
		throws NoSuchPaymentFileException {
		PaymentFile paymentFile = findByPrimaryKey(paymentFileId);

		Session session = null;

		try {
			session = openSession();

			PaymentFile[] array = new PaymentFileImpl[3];

			array[0] = getByUuid_PrevAndNext(session, paymentFile, uuid,
					orderByComparator, true);

			array[1] = paymentFile;

			array[2] = getByUuid_PrevAndNext(session, paymentFile, uuid,
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

	protected PaymentFile getByUuid_PrevAndNext(Session session,
		PaymentFile paymentFile, String uuid,
		OrderByComparator<PaymentFile> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTFILE_WHERE);

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
			query.append(PaymentFileModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(paymentFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the payment files where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PaymentFile paymentFile : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(paymentFile);
		}
	}

	/**
	 * Returns the number of payment files where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching payment files
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTFILE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "paymentFile.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "paymentFile.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(paymentFile.uuid IS NULL OR paymentFile.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, PaymentFileImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PaymentFileModelImpl.UUID_COLUMN_BITMASK |
			PaymentFileModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the payment file where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPaymentFileException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching payment file
	 * @throws NoSuchPaymentFileException if a matching payment file could not be found
	 */
	@Override
	public PaymentFile findByUUID_G(String uuid, long groupId)
		throws NoSuchPaymentFileException {
		PaymentFile paymentFile = fetchByUUID_G(uuid, groupId);

		if (paymentFile == null) {
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

			throw new NoSuchPaymentFileException(msg.toString());
		}

		return paymentFile;
	}

	/**
	 * Returns the payment file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching payment file, or <code>null</code> if a matching payment file could not be found
	 */
	@Override
	public PaymentFile fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the payment file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching payment file, or <code>null</code> if a matching payment file could not be found
	 */
	@Override
	public PaymentFile fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PaymentFile) {
			PaymentFile paymentFile = (PaymentFile)result;

			if (!Objects.equals(uuid, paymentFile.getUuid()) ||
					(groupId != paymentFile.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PAYMENTFILE_WHERE);

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

				List<PaymentFile> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					PaymentFile paymentFile = list.get(0);

					result = paymentFile;

					cacheResult(paymentFile);
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
			return (PaymentFile)result;
		}
	}

	/**
	 * Removes the payment file where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the payment file that was removed
	 */
	@Override
	public PaymentFile removeByUUID_G(String uuid, long groupId)
		throws NoSuchPaymentFileException {
		PaymentFile paymentFile = findByUUID_G(uuid, groupId);

		return remove(paymentFile);
	}

	/**
	 * Returns the number of payment files where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching payment files
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTFILE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "paymentFile.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "paymentFile.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(paymentFile.uuid IS NULL OR paymentFile.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "paymentFile.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, PaymentFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, PaymentFileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PaymentFileModelImpl.UUID_COLUMN_BITMASK |
			PaymentFileModelImpl.COMPANYID_COLUMN_BITMASK |
			PaymentFileModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the payment files where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching payment files
	 */
	@Override
	public List<PaymentFile> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment files where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment files
	 * @param end the upper bound of the range of payment files (not inclusive)
	 * @return the range of matching payment files
	 */
	@Override
	public List<PaymentFile> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment files where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment files
	 * @param end the upper bound of the range of payment files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment files
	 */
	@Override
	public List<PaymentFile> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PaymentFile> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the payment files where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment files
	 * @param end the upper bound of the range of payment files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching payment files
	 */
	@Override
	public List<PaymentFile> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PaymentFile> orderByComparator,
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

		List<PaymentFile> list = null;

		if (retrieveFromCache) {
			list = (List<PaymentFile>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PaymentFile paymentFile : list) {
					if (!Objects.equals(uuid, paymentFile.getUuid()) ||
							(companyId != paymentFile.getCompanyId())) {
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

			query.append(_SQL_SELECT_PAYMENTFILE_WHERE);

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
				query.append(PaymentFileModelImpl.ORDER_BY_JPQL);
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
					list = (List<PaymentFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PaymentFile>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first payment file in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment file
	 * @throws NoSuchPaymentFileException if a matching payment file could not be found
	 */
	@Override
	public PaymentFile findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PaymentFile> orderByComparator)
		throws NoSuchPaymentFileException {
		PaymentFile paymentFile = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (paymentFile != null) {
			return paymentFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPaymentFileException(msg.toString());
	}

	/**
	 * Returns the first payment file in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment file, or <code>null</code> if a matching payment file could not be found
	 */
	@Override
	public PaymentFile fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PaymentFile> orderByComparator) {
		List<PaymentFile> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment file in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment file
	 * @throws NoSuchPaymentFileException if a matching payment file could not be found
	 */
	@Override
	public PaymentFile findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PaymentFile> orderByComparator)
		throws NoSuchPaymentFileException {
		PaymentFile paymentFile = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (paymentFile != null) {
			return paymentFile;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPaymentFileException(msg.toString());
	}

	/**
	 * Returns the last payment file in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment file, or <code>null</code> if a matching payment file could not be found
	 */
	@Override
	public PaymentFile fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PaymentFile> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<PaymentFile> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment files before and after the current payment file in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param paymentFileId the primary key of the current payment file
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment file
	 * @throws NoSuchPaymentFileException if a payment file with the primary key could not be found
	 */
	@Override
	public PaymentFile[] findByUuid_C_PrevAndNext(long paymentFileId,
		String uuid, long companyId,
		OrderByComparator<PaymentFile> orderByComparator)
		throws NoSuchPaymentFileException {
		PaymentFile paymentFile = findByPrimaryKey(paymentFileId);

		Session session = null;

		try {
			session = openSession();

			PaymentFile[] array = new PaymentFileImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, paymentFile, uuid,
					companyId, orderByComparator, true);

			array[1] = paymentFile;

			array[2] = getByUuid_C_PrevAndNext(session, paymentFile, uuid,
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

	protected PaymentFile getByUuid_C_PrevAndNext(Session session,
		PaymentFile paymentFile, String uuid, long companyId,
		OrderByComparator<PaymentFile> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PAYMENTFILE_WHERE);

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
			query.append(PaymentFileModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(paymentFile);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentFile> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the payment files where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (PaymentFile paymentFile : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(paymentFile);
		}
	}

	/**
	 * Returns the number of payment files where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching payment files
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTFILE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "paymentFile.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "paymentFile.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(paymentFile.uuid IS NULL OR paymentFile.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "paymentFile.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_D_RUID = new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, PaymentFileImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByD_RUID",
			new String[] { Long.class.getName(), String.class.getName() },
			PaymentFileModelImpl.DOSSIERID_COLUMN_BITMASK |
			PaymentFileModelImpl.REFERENCEUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_D_RUID = new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByD_RUID",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the payment file where dossierId = &#63; and referenceUid = &#63; or throws a {@link NoSuchPaymentFileException} if it could not be found.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @return the matching payment file
	 * @throws NoSuchPaymentFileException if a matching payment file could not be found
	 */
	@Override
	public PaymentFile findByD_RUID(long dossierId, String referenceUid)
		throws NoSuchPaymentFileException {
		PaymentFile paymentFile = fetchByD_RUID(dossierId, referenceUid);

		if (paymentFile == null) {
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

			throw new NoSuchPaymentFileException(msg.toString());
		}

		return paymentFile;
	}

	/**
	 * Returns the payment file where dossierId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @return the matching payment file, or <code>null</code> if a matching payment file could not be found
	 */
	@Override
	public PaymentFile fetchByD_RUID(long dossierId, String referenceUid) {
		return fetchByD_RUID(dossierId, referenceUid, true);
	}

	/**
	 * Returns the payment file where dossierId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching payment file, or <code>null</code> if a matching payment file could not be found
	 */
	@Override
	public PaymentFile fetchByD_RUID(long dossierId, String referenceUid,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossierId, referenceUid };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_D_RUID,
					finderArgs, this);
		}

		if (result instanceof PaymentFile) {
			PaymentFile paymentFile = (PaymentFile)result;

			if ((dossierId != paymentFile.getDossierId()) ||
					!Objects.equals(referenceUid, paymentFile.getReferenceUid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PAYMENTFILE_WHERE);

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

				List<PaymentFile> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_D_RUID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"PaymentFilePersistenceImpl.fetchByD_RUID(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PaymentFile paymentFile = list.get(0);

					result = paymentFile;

					cacheResult(paymentFile);
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
			return (PaymentFile)result;
		}
	}

	/**
	 * Removes the payment file where dossierId = &#63; and referenceUid = &#63; from the database.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @return the payment file that was removed
	 */
	@Override
	public PaymentFile removeByD_RUID(long dossierId, String referenceUid)
		throws NoSuchPaymentFileException {
		PaymentFile paymentFile = findByD_RUID(dossierId, referenceUid);

		return remove(paymentFile);
	}

	/**
	 * Returns the number of payment files where dossierId = &#63; and referenceUid = &#63;.
	 *
	 * @param dossierId the dossier ID
	 * @param referenceUid the reference uid
	 * @return the number of matching payment files
	 */
	@Override
	public int countByD_RUID(long dossierId, String referenceUid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_D_RUID;

		Object[] finderArgs = new Object[] { dossierId, referenceUid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTFILE_WHERE);

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

	private static final String _FINDER_COLUMN_D_RUID_DOSSIERID_2 = "paymentFile.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_D_RUID_REFERENCEUID_1 = "paymentFile.referenceUid IS NULL";
	private static final String _FINDER_COLUMN_D_RUID_REFERENCEUID_2 = "paymentFile.referenceUid = ?";
	private static final String _FINDER_COLUMN_D_RUID_REFERENCEUID_3 = "(paymentFile.referenceUid IS NULL OR paymentFile.referenceUid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_DOSSIERID = new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, PaymentFileImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDossierId",
			new String[] { Long.class.getName(), Long.class.getName() },
			PaymentFileModelImpl.GROUPID_COLUMN_BITMASK |
			PaymentFileModelImpl.DOSSIERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DOSSIERID = new FinderPath(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDossierId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the payment file where groupId = &#63; and dossierId = &#63; or throws a {@link NoSuchPaymentFileException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @return the matching payment file
	 * @throws NoSuchPaymentFileException if a matching payment file could not be found
	 */
	@Override
	public PaymentFile findByDossierId(long groupId, long dossierId)
		throws NoSuchPaymentFileException {
		PaymentFile paymentFile = fetchByDossierId(groupId, dossierId);

		if (paymentFile == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", dossierId=");
			msg.append(dossierId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPaymentFileException(msg.toString());
		}

		return paymentFile;
	}

	/**
	 * Returns the payment file where groupId = &#63; and dossierId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @return the matching payment file, or <code>null</code> if a matching payment file could not be found
	 */
	@Override
	public PaymentFile fetchByDossierId(long groupId, long dossierId) {
		return fetchByDossierId(groupId, dossierId, true);
	}

	/**
	 * Returns the payment file where groupId = &#63; and dossierId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching payment file, or <code>null</code> if a matching payment file could not be found
	 */
	@Override
	public PaymentFile fetchByDossierId(long groupId, long dossierId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, dossierId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DOSSIERID,
					finderArgs, this);
		}

		if (result instanceof PaymentFile) {
			PaymentFile paymentFile = (PaymentFile)result;

			if ((groupId != paymentFile.getGroupId()) ||
					(dossierId != paymentFile.getDossierId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PAYMENTFILE_WHERE);

			query.append(_FINDER_COLUMN_DOSSIERID_GROUPID_2);

			query.append(_FINDER_COLUMN_DOSSIERID_DOSSIERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				List<PaymentFile> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DOSSIERID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"PaymentFilePersistenceImpl.fetchByDossierId(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PaymentFile paymentFile = list.get(0);

					result = paymentFile;

					cacheResult(paymentFile);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DOSSIERID,
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
			return (PaymentFile)result;
		}
	}

	/**
	 * Removes the payment file where groupId = &#63; and dossierId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @return the payment file that was removed
	 */
	@Override
	public PaymentFile removeByDossierId(long groupId, long dossierId)
		throws NoSuchPaymentFileException {
		PaymentFile paymentFile = findByDossierId(groupId, dossierId);

		return remove(paymentFile);
	}

	/**
	 * Returns the number of payment files where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @return the number of matching payment files
	 */
	@Override
	public int countByDossierId(long groupId, long dossierId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DOSSIERID;

		Object[] finderArgs = new Object[] { groupId, dossierId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTFILE_WHERE);

			query.append(_FINDER_COLUMN_DOSSIERID_GROUPID_2);

			query.append(_FINDER_COLUMN_DOSSIERID_DOSSIERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_DOSSIERID_GROUPID_2 = "paymentFile.groupId = ? AND ";
	private static final String _FINDER_COLUMN_DOSSIERID_DOSSIERID_2 = "paymentFile.dossierId = ?";

	public PaymentFilePersistenceImpl() {
		setModelClass(PaymentFile.class);

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
	 * Caches the payment file in the entity cache if it is enabled.
	 *
	 * @param paymentFile the payment file
	 */
	@Override
	public void cacheResult(PaymentFile paymentFile) {
		entityCache.putResult(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileImpl.class, paymentFile.getPrimaryKey(), paymentFile);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { paymentFile.getUuid(), paymentFile.getGroupId() },
			paymentFile);

		finderCache.putResult(FINDER_PATH_FETCH_BY_D_RUID,
			new Object[] {
				paymentFile.getDossierId(), paymentFile.getReferenceUid()
			}, paymentFile);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DOSSIERID,
			new Object[] { paymentFile.getGroupId(), paymentFile.getDossierId() },
			paymentFile);

		paymentFile.resetOriginalValues();
	}

	/**
	 * Caches the payment files in the entity cache if it is enabled.
	 *
	 * @param paymentFiles the payment files
	 */
	@Override
	public void cacheResult(List<PaymentFile> paymentFiles) {
		for (PaymentFile paymentFile : paymentFiles) {
			if (entityCache.getResult(
						PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
						PaymentFileImpl.class, paymentFile.getPrimaryKey()) == null) {
				cacheResult(paymentFile);
			}
			else {
				paymentFile.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all payment files.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PaymentFileImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the payment file.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PaymentFile paymentFile) {
		entityCache.removeResult(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileImpl.class, paymentFile.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PaymentFileModelImpl)paymentFile, true);
	}

	@Override
	public void clearCache(List<PaymentFile> paymentFiles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PaymentFile paymentFile : paymentFiles) {
			entityCache.removeResult(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
				PaymentFileImpl.class, paymentFile.getPrimaryKey());

			clearUniqueFindersCache((PaymentFileModelImpl)paymentFile, true);
		}
	}

	protected void cacheUniqueFindersCache(
		PaymentFileModelImpl paymentFileModelImpl) {
		Object[] args = new Object[] {
				paymentFileModelImpl.getUuid(),
				paymentFileModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			paymentFileModelImpl, false);

		args = new Object[] {
				paymentFileModelImpl.getDossierId(),
				paymentFileModelImpl.getReferenceUid()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_D_RUID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_D_RUID, args,
			paymentFileModelImpl, false);

		args = new Object[] {
				paymentFileModelImpl.getGroupId(),
				paymentFileModelImpl.getDossierId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_DOSSIERID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DOSSIERID, args,
			paymentFileModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PaymentFileModelImpl paymentFileModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					paymentFileModelImpl.getUuid(),
					paymentFileModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((paymentFileModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					paymentFileModelImpl.getOriginalUuid(),
					paymentFileModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					paymentFileModelImpl.getDossierId(),
					paymentFileModelImpl.getReferenceUid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_D_RUID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_D_RUID, args);
		}

		if ((paymentFileModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_D_RUID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					paymentFileModelImpl.getOriginalDossierId(),
					paymentFileModelImpl.getOriginalReferenceUid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_D_RUID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_D_RUID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					paymentFileModelImpl.getGroupId(),
					paymentFileModelImpl.getDossierId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DOSSIERID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DOSSIERID, args);
		}

		if ((paymentFileModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DOSSIERID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					paymentFileModelImpl.getOriginalGroupId(),
					paymentFileModelImpl.getOriginalDossierId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DOSSIERID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DOSSIERID, args);
		}
	}

	/**
	 * Creates a new payment file with the primary key. Does not add the payment file to the database.
	 *
	 * @param paymentFileId the primary key for the new payment file
	 * @return the new payment file
	 */
	@Override
	public PaymentFile create(long paymentFileId) {
		PaymentFile paymentFile = new PaymentFileImpl();

		paymentFile.setNew(true);
		paymentFile.setPrimaryKey(paymentFileId);

		String uuid = PortalUUIDUtil.generate();

		paymentFile.setUuid(uuid);

		paymentFile.setCompanyId(companyProvider.getCompanyId());

		return paymentFile;
	}

	/**
	 * Removes the payment file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param paymentFileId the primary key of the payment file
	 * @return the payment file that was removed
	 * @throws NoSuchPaymentFileException if a payment file with the primary key could not be found
	 */
	@Override
	public PaymentFile remove(long paymentFileId)
		throws NoSuchPaymentFileException {
		return remove((Serializable)paymentFileId);
	}

	/**
	 * Removes the payment file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the payment file
	 * @return the payment file that was removed
	 * @throws NoSuchPaymentFileException if a payment file with the primary key could not be found
	 */
	@Override
	public PaymentFile remove(Serializable primaryKey)
		throws NoSuchPaymentFileException {
		Session session = null;

		try {
			session = openSession();

			PaymentFile paymentFile = (PaymentFile)session.get(PaymentFileImpl.class,
					primaryKey);

			if (paymentFile == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPaymentFileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(paymentFile);
		}
		catch (NoSuchPaymentFileException nsee) {
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
	protected PaymentFile removeImpl(PaymentFile paymentFile) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(paymentFile)) {
				paymentFile = (PaymentFile)session.get(PaymentFileImpl.class,
						paymentFile.getPrimaryKeyObj());
			}

			if (paymentFile != null) {
				session.delete(paymentFile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (paymentFile != null) {
			clearCache(paymentFile);
		}

		return paymentFile;
	}

	@Override
	public PaymentFile updateImpl(PaymentFile paymentFile) {
		boolean isNew = paymentFile.isNew();

		if (!(paymentFile instanceof PaymentFileModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(paymentFile.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(paymentFile);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in paymentFile proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PaymentFile implementation " +
				paymentFile.getClass());
		}

		PaymentFileModelImpl paymentFileModelImpl = (PaymentFileModelImpl)paymentFile;

		if (Validator.isNull(paymentFile.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			paymentFile.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (paymentFile.getCreateDate() == null)) {
			if (serviceContext == null) {
				paymentFile.setCreateDate(now);
			}
			else {
				paymentFile.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!paymentFileModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				paymentFile.setModifiedDate(now);
			}
			else {
				paymentFile.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (paymentFile.isNew()) {
				session.save(paymentFile);

				paymentFile.setNew(false);
			}
			else {
				paymentFile = (PaymentFile)session.merge(paymentFile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PaymentFileModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { paymentFileModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					paymentFileModelImpl.getUuid(),
					paymentFileModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((paymentFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentFileModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { paymentFileModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((paymentFileModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentFileModelImpl.getOriginalUuid(),
						paymentFileModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						paymentFileModelImpl.getUuid(),
						paymentFileModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFileImpl.class, paymentFile.getPrimaryKey(), paymentFile,
			false);

		clearUniqueFindersCache(paymentFileModelImpl, false);
		cacheUniqueFindersCache(paymentFileModelImpl);

		paymentFile.resetOriginalValues();

		return paymentFile;
	}

	/**
	 * Returns the payment file with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment file
	 * @return the payment file
	 * @throws NoSuchPaymentFileException if a payment file with the primary key could not be found
	 */
	@Override
	public PaymentFile findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPaymentFileException {
		PaymentFile paymentFile = fetchByPrimaryKey(primaryKey);

		if (paymentFile == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPaymentFileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return paymentFile;
	}

	/**
	 * Returns the payment file with the primary key or throws a {@link NoSuchPaymentFileException} if it could not be found.
	 *
	 * @param paymentFileId the primary key of the payment file
	 * @return the payment file
	 * @throws NoSuchPaymentFileException if a payment file with the primary key could not be found
	 */
	@Override
	public PaymentFile findByPrimaryKey(long paymentFileId)
		throws NoSuchPaymentFileException {
		return findByPrimaryKey((Serializable)paymentFileId);
	}

	/**
	 * Returns the payment file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment file
	 * @return the payment file, or <code>null</code> if a payment file with the primary key could not be found
	 */
	@Override
	public PaymentFile fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
				PaymentFileImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PaymentFile paymentFile = (PaymentFile)serializable;

		if (paymentFile == null) {
			Session session = null;

			try {
				session = openSession();

				paymentFile = (PaymentFile)session.get(PaymentFileImpl.class,
						primaryKey);

				if (paymentFile != null) {
					cacheResult(paymentFile);
				}
				else {
					entityCache.putResult(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
						PaymentFileImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
					PaymentFileImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return paymentFile;
	}

	/**
	 * Returns the payment file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param paymentFileId the primary key of the payment file
	 * @return the payment file, or <code>null</code> if a payment file with the primary key could not be found
	 */
	@Override
	public PaymentFile fetchByPrimaryKey(long paymentFileId) {
		return fetchByPrimaryKey((Serializable)paymentFileId);
	}

	@Override
	public Map<Serializable, PaymentFile> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PaymentFile> map = new HashMap<Serializable, PaymentFile>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PaymentFile paymentFile = fetchByPrimaryKey(primaryKey);

			if (paymentFile != null) {
				map.put(primaryKey, paymentFile);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
					PaymentFileImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PaymentFile)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PAYMENTFILE_WHERE_PKS_IN);

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

			for (PaymentFile paymentFile : (List<PaymentFile>)q.list()) {
				map.put(paymentFile.getPrimaryKeyObj(), paymentFile);

				cacheResult(paymentFile);

				uncachedPrimaryKeys.remove(paymentFile.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PaymentFileModelImpl.ENTITY_CACHE_ENABLED,
					PaymentFileImpl.class, primaryKey, nullModel);
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
	 * Returns all the payment files.
	 *
	 * @return the payment files
	 */
	@Override
	public List<PaymentFile> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment files
	 * @param end the upper bound of the range of payment files (not inclusive)
	 * @return the range of payment files
	 */
	@Override
	public List<PaymentFile> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment files
	 * @param end the upper bound of the range of payment files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of payment files
	 */
	@Override
	public List<PaymentFile> findAll(int start, int end,
		OrderByComparator<PaymentFile> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the payment files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment files
	 * @param end the upper bound of the range of payment files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of payment files
	 */
	@Override
	public List<PaymentFile> findAll(int start, int end,
		OrderByComparator<PaymentFile> orderByComparator,
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

		List<PaymentFile> list = null;

		if (retrieveFromCache) {
			list = (List<PaymentFile>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PAYMENTFILE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PAYMENTFILE;

				if (pagination) {
					sql = sql.concat(PaymentFileModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PaymentFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PaymentFile>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the payment files from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PaymentFile paymentFile : findAll()) {
			remove(paymentFile);
		}
	}

	/**
	 * Returns the number of payment files.
	 *
	 * @return the number of payment files
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PAYMENTFILE);

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
		return PaymentFileModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the payment file persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PaymentFileImpl.class.getName());
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
	private static final String _SQL_SELECT_PAYMENTFILE = "SELECT paymentFile FROM PaymentFile paymentFile";
	private static final String _SQL_SELECT_PAYMENTFILE_WHERE_PKS_IN = "SELECT paymentFile FROM PaymentFile paymentFile WHERE paymentFileId IN (";
	private static final String _SQL_SELECT_PAYMENTFILE_WHERE = "SELECT paymentFile FROM PaymentFile paymentFile WHERE ";
	private static final String _SQL_COUNT_PAYMENTFILE = "SELECT COUNT(paymentFile) FROM PaymentFile paymentFile";
	private static final String _SQL_COUNT_PAYMENTFILE_WHERE = "SELECT COUNT(paymentFile) FROM PaymentFile paymentFile WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "paymentFile.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PaymentFile exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PaymentFile exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PaymentFilePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}