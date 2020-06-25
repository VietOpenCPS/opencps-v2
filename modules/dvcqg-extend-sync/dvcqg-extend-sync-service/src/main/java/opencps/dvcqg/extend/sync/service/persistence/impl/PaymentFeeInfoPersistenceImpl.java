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

package opencps.dvcqg.extend.sync.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.petra.string.StringBundler;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException;
import opencps.dvcqg.extend.sync.model.PaymentFeeInfo;
import opencps.dvcqg.extend.sync.model.impl.PaymentFeeInfoImpl;
import opencps.dvcqg.extend.sync.model.impl.PaymentFeeInfoModelImpl;
import opencps.dvcqg.extend.sync.service.persistence.PaymentFeeInfoPersistence;

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
 * The persistence implementation for the payment fee info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PaymentFeeInfoPersistence
 * @see opencps.dvcqg.extend.sync.service.persistence.PaymentFeeInfoUtil
 * @generated
 */
@ProviderType
public class PaymentFeeInfoPersistenceImpl extends BasePersistenceImpl<PaymentFeeInfo>
	implements PaymentFeeInfoPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PaymentFeeInfoUtil} to access the payment fee info persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PaymentFeeInfoImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoModelImpl.FINDER_CACHE_ENABLED,
			PaymentFeeInfoImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoModelImpl.FINDER_CACHE_ENABLED,
			PaymentFeeInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoModelImpl.FINDER_CACHE_ENABLED,
			PaymentFeeInfoImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoModelImpl.FINDER_CACHE_ENABLED,
			PaymentFeeInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PaymentFeeInfoModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the payment fee infos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment fee infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment fee infos
	 * @param end the upper bound of the range of payment fee infos (not inclusive)
	 * @return the range of matching payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment fee infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment fee infos
	 * @param end the upper bound of the range of payment fee infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findByUuid(String uuid, int start, int end,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the payment fee infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment fee infos
	 * @param end the upper bound of the range of payment fee infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findByUuid(String uuid, int start, int end,
		OrderByComparator<PaymentFeeInfo> orderByComparator,
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

		List<PaymentFeeInfo> list = null;

		if (retrieveFromCache) {
			list = (List<PaymentFeeInfo>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PaymentFeeInfo paymentFeeInfo : list) {
					if (!Objects.equals(uuid, paymentFeeInfo.getUuid())) {
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

			query.append(_SQL_SELECT_PAYMENTFEEINFO_WHERE);

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
				query.append(PaymentFeeInfoModelImpl.ORDER_BY_JPQL);
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
					list = (List<PaymentFeeInfo>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PaymentFeeInfo>)QueryUtil.list(q,
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
	 * Returns the first payment fee info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment fee info
	 * @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo findByUuid_First(String uuid,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException {
		PaymentFeeInfo paymentFeeInfo = fetchByUuid_First(uuid,
				orderByComparator);

		if (paymentFeeInfo != null) {
			return paymentFeeInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPaymentFeeInfoException(msg.toString());
	}

	/**
	 * Returns the first payment fee info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo fetchByUuid_First(String uuid,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		List<PaymentFeeInfo> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment fee info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment fee info
	 * @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo findByUuid_Last(String uuid,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException {
		PaymentFeeInfo paymentFeeInfo = fetchByUuid_Last(uuid, orderByComparator);

		if (paymentFeeInfo != null) {
			return paymentFeeInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPaymentFeeInfoException(msg.toString());
	}

	/**
	 * Returns the last payment fee info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo fetchByUuid_Last(String uuid,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PaymentFeeInfo> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment fee infos before and after the current payment fee info in the ordered set where uuid = &#63;.
	 *
	 * @param paymentFeeInfoId the primary key of the current payment fee info
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment fee info
	 * @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	 */
	@Override
	public PaymentFeeInfo[] findByUuid_PrevAndNext(long paymentFeeInfoId,
		String uuid, OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException {
		PaymentFeeInfo paymentFeeInfo = findByPrimaryKey(paymentFeeInfoId);

		Session session = null;

		try {
			session = openSession();

			PaymentFeeInfo[] array = new PaymentFeeInfoImpl[3];

			array[0] = getByUuid_PrevAndNext(session, paymentFeeInfo, uuid,
					orderByComparator, true);

			array[1] = paymentFeeInfo;

			array[2] = getByUuid_PrevAndNext(session, paymentFeeInfo, uuid,
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

	protected PaymentFeeInfo getByUuid_PrevAndNext(Session session,
		PaymentFeeInfo paymentFeeInfo, String uuid,
		OrderByComparator<PaymentFeeInfo> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTFEEINFO_WHERE);

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
			query.append(PaymentFeeInfoModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(paymentFeeInfo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentFeeInfo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the payment fee infos where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PaymentFeeInfo paymentFeeInfo : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(paymentFeeInfo);
		}
	}

	/**
	 * Returns the number of payment fee infos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching payment fee infos
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTFEEINFO_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "paymentFeeInfo.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "paymentFeeInfo.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(paymentFeeInfo.uuid IS NULL OR paymentFeeInfo.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoModelImpl.FINDER_CACHE_ENABLED,
			PaymentFeeInfoImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PaymentFeeInfoModelImpl.UUID_COLUMN_BITMASK |
			PaymentFeeInfoModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the payment fee info where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPaymentFeeInfoException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching payment fee info
	 * @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo findByUUID_G(String uuid, long groupId)
		throws NoSuchPaymentFeeInfoException {
		PaymentFeeInfo paymentFeeInfo = fetchByUUID_G(uuid, groupId);

		if (paymentFeeInfo == null) {
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

			throw new NoSuchPaymentFeeInfoException(msg.toString());
		}

		return paymentFeeInfo;
	}

	/**
	 * Returns the payment fee info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the payment fee info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PaymentFeeInfo) {
			PaymentFeeInfo paymentFeeInfo = (PaymentFeeInfo)result;

			if (!Objects.equals(uuid, paymentFeeInfo.getUuid()) ||
					(groupId != paymentFeeInfo.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PAYMENTFEEINFO_WHERE);

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

				List<PaymentFeeInfo> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					PaymentFeeInfo paymentFeeInfo = list.get(0);

					result = paymentFeeInfo;

					cacheResult(paymentFeeInfo);
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
			return (PaymentFeeInfo)result;
		}
	}

	/**
	 * Removes the payment fee info where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the payment fee info that was removed
	 */
	@Override
	public PaymentFeeInfo removeByUUID_G(String uuid, long groupId)
		throws NoSuchPaymentFeeInfoException {
		PaymentFeeInfo paymentFeeInfo = findByUUID_G(uuid, groupId);

		return remove(paymentFeeInfo);
	}

	/**
	 * Returns the number of payment fee infos where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching payment fee infos
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTFEEINFO_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "paymentFeeInfo.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "paymentFeeInfo.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(paymentFeeInfo.uuid IS NULL OR paymentFeeInfo.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "paymentFeeInfo.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoModelImpl.FINDER_CACHE_ENABLED,
			PaymentFeeInfoImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoModelImpl.FINDER_CACHE_ENABLED,
			PaymentFeeInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PaymentFeeInfoModelImpl.UUID_COLUMN_BITMASK |
			PaymentFeeInfoModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the payment fee infos where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment fee infos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment fee infos
	 * @param end the upper bound of the range of payment fee infos (not inclusive)
	 * @return the range of matching payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment fee infos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment fee infos
	 * @param end the upper bound of the range of payment fee infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the payment fee infos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment fee infos
	 * @param end the upper bound of the range of payment fee infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<PaymentFeeInfo> orderByComparator,
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

		List<PaymentFeeInfo> list = null;

		if (retrieveFromCache) {
			list = (List<PaymentFeeInfo>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PaymentFeeInfo paymentFeeInfo : list) {
					if (!Objects.equals(uuid, paymentFeeInfo.getUuid()) ||
							(companyId != paymentFeeInfo.getCompanyId())) {
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

			query.append(_SQL_SELECT_PAYMENTFEEINFO_WHERE);

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
				query.append(PaymentFeeInfoModelImpl.ORDER_BY_JPQL);
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
					list = (List<PaymentFeeInfo>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PaymentFeeInfo>)QueryUtil.list(q,
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
	 * Returns the first payment fee info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment fee info
	 * @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException {
		PaymentFeeInfo paymentFeeInfo = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (paymentFeeInfo != null) {
			return paymentFeeInfo;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPaymentFeeInfoException(msg.toString());
	}

	/**
	 * Returns the first payment fee info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		List<PaymentFeeInfo> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment fee info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment fee info
	 * @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException {
		PaymentFeeInfo paymentFeeInfo = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (paymentFeeInfo != null) {
			return paymentFeeInfo;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPaymentFeeInfoException(msg.toString());
	}

	/**
	 * Returns the last payment fee info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<PaymentFeeInfo> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment fee infos before and after the current payment fee info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param paymentFeeInfoId the primary key of the current payment fee info
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment fee info
	 * @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	 */
	@Override
	public PaymentFeeInfo[] findByUuid_C_PrevAndNext(long paymentFeeInfoId,
		String uuid, long companyId,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException {
		PaymentFeeInfo paymentFeeInfo = findByPrimaryKey(paymentFeeInfoId);

		Session session = null;

		try {
			session = openSession();

			PaymentFeeInfo[] array = new PaymentFeeInfoImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, paymentFeeInfo, uuid,
					companyId, orderByComparator, true);

			array[1] = paymentFeeInfo;

			array[2] = getByUuid_C_PrevAndNext(session, paymentFeeInfo, uuid,
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

	protected PaymentFeeInfo getByUuid_C_PrevAndNext(Session session,
		PaymentFeeInfo paymentFeeInfo, String uuid, long companyId,
		OrderByComparator<PaymentFeeInfo> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PAYMENTFEEINFO_WHERE);

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
			query.append(PaymentFeeInfoModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(paymentFeeInfo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentFeeInfo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the payment fee infos where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (PaymentFeeInfo paymentFeeInfo : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(paymentFeeInfo);
		}
	}

	/**
	 * Returns the number of payment fee infos where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching payment fee infos
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTFEEINFO_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "paymentFeeInfo.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "paymentFeeInfo.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(paymentFeeInfo.uuid IS NULL OR paymentFeeInfo.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "paymentFeeInfo.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID =
		new FinderPath(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoModelImpl.FINDER_CACHE_ENABLED,
			PaymentFeeInfoImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByServiceConfigMappingId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID =
		new FinderPath(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoModelImpl.FINDER_CACHE_ENABLED,
			PaymentFeeInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByServiceConfigMappingId",
			new String[] { Long.class.getName() },
			PaymentFeeInfoModelImpl.SERVICECONFIGMAPPINGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SERVICECONFIGMAPPINGID = new FinderPath(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByServiceConfigMappingId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the payment fee infos where serviceConfigMappingId = &#63;.
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @return the matching payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId) {
		return findByServiceConfigMappingId(serviceConfigMappingId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment fee infos where serviceConfigMappingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param start the lower bound of the range of payment fee infos
	 * @param end the upper bound of the range of payment fee infos (not inclusive)
	 * @return the range of matching payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end) {
		return findByServiceConfigMappingId(serviceConfigMappingId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the payment fee infos where serviceConfigMappingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param start the lower bound of the range of payment fee infos
	 * @param end the upper bound of the range of payment fee infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return findByServiceConfigMappingId(serviceConfigMappingId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the payment fee infos where serviceConfigMappingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param start the lower bound of the range of payment fee infos
	 * @param end the upper bound of the range of payment fee infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end,
		OrderByComparator<PaymentFeeInfo> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID;
			finderArgs = new Object[] { serviceConfigMappingId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID;
			finderArgs = new Object[] {
					serviceConfigMappingId,
					
					start, end, orderByComparator
				};
		}

		List<PaymentFeeInfo> list = null;

		if (retrieveFromCache) {
			list = (List<PaymentFeeInfo>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PaymentFeeInfo paymentFeeInfo : list) {
					if ((serviceConfigMappingId != paymentFeeInfo.getServiceConfigMappingId())) {
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

			query.append(_SQL_SELECT_PAYMENTFEEINFO_WHERE);

			query.append(_FINDER_COLUMN_SERVICECONFIGMAPPINGID_SERVICECONFIGMAPPINGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PaymentFeeInfoModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceConfigMappingId);

				if (!pagination) {
					list = (List<PaymentFeeInfo>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PaymentFeeInfo>)QueryUtil.list(q,
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
	 * Returns the first payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment fee info
	 * @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo findByServiceConfigMappingId_First(
		long serviceConfigMappingId,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException {
		PaymentFeeInfo paymentFeeInfo = fetchByServiceConfigMappingId_First(serviceConfigMappingId,
				orderByComparator);

		if (paymentFeeInfo != null) {
			return paymentFeeInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("serviceConfigMappingId=");
		msg.append(serviceConfigMappingId);

		msg.append("}");

		throw new NoSuchPaymentFeeInfoException(msg.toString());
	}

	/**
	 * Returns the first payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo fetchByServiceConfigMappingId_First(
		long serviceConfigMappingId,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		List<PaymentFeeInfo> list = findByServiceConfigMappingId(serviceConfigMappingId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment fee info
	 * @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo findByServiceConfigMappingId_Last(
		long serviceConfigMappingId,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException {
		PaymentFeeInfo paymentFeeInfo = fetchByServiceConfigMappingId_Last(serviceConfigMappingId,
				orderByComparator);

		if (paymentFeeInfo != null) {
			return paymentFeeInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("serviceConfigMappingId=");
		msg.append(serviceConfigMappingId);

		msg.append("}");

		throw new NoSuchPaymentFeeInfoException(msg.toString());
	}

	/**
	 * Returns the last payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	 */
	@Override
	public PaymentFeeInfo fetchByServiceConfigMappingId_Last(
		long serviceConfigMappingId,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		int count = countByServiceConfigMappingId(serviceConfigMappingId);

		if (count == 0) {
			return null;
		}

		List<PaymentFeeInfo> list = findByServiceConfigMappingId(serviceConfigMappingId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment fee infos before and after the current payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	 *
	 * @param paymentFeeInfoId the primary key of the current payment fee info
	 * @param serviceConfigMappingId the service config mapping ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment fee info
	 * @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	 */
	@Override
	public PaymentFeeInfo[] findByServiceConfigMappingId_PrevAndNext(
		long paymentFeeInfoId, long serviceConfigMappingId,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException {
		PaymentFeeInfo paymentFeeInfo = findByPrimaryKey(paymentFeeInfoId);

		Session session = null;

		try {
			session = openSession();

			PaymentFeeInfo[] array = new PaymentFeeInfoImpl[3];

			array[0] = getByServiceConfigMappingId_PrevAndNext(session,
					paymentFeeInfo, serviceConfigMappingId, orderByComparator,
					true);

			array[1] = paymentFeeInfo;

			array[2] = getByServiceConfigMappingId_PrevAndNext(session,
					paymentFeeInfo, serviceConfigMappingId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PaymentFeeInfo getByServiceConfigMappingId_PrevAndNext(
		Session session, PaymentFeeInfo paymentFeeInfo,
		long serviceConfigMappingId,
		OrderByComparator<PaymentFeeInfo> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTFEEINFO_WHERE);

		query.append(_FINDER_COLUMN_SERVICECONFIGMAPPINGID_SERVICECONFIGMAPPINGID_2);

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
			query.append(PaymentFeeInfoModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(serviceConfigMappingId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(paymentFeeInfo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentFeeInfo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the payment fee infos where serviceConfigMappingId = &#63; from the database.
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 */
	@Override
	public void removeByServiceConfigMappingId(long serviceConfigMappingId) {
		for (PaymentFeeInfo paymentFeeInfo : findByServiceConfigMappingId(
				serviceConfigMappingId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(paymentFeeInfo);
		}
	}

	/**
	 * Returns the number of payment fee infos where serviceConfigMappingId = &#63;.
	 *
	 * @param serviceConfigMappingId the service config mapping ID
	 * @return the number of matching payment fee infos
	 */
	@Override
	public int countByServiceConfigMappingId(long serviceConfigMappingId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SERVICECONFIGMAPPINGID;

		Object[] finderArgs = new Object[] { serviceConfigMappingId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTFEEINFO_WHERE);

			query.append(_FINDER_COLUMN_SERVICECONFIGMAPPINGID_SERVICECONFIGMAPPINGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceConfigMappingId);

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

	private static final String _FINDER_COLUMN_SERVICECONFIGMAPPINGID_SERVICECONFIGMAPPINGID_2 =
		"paymentFeeInfo.serviceConfigMappingId = ?";

	public PaymentFeeInfoPersistenceImpl() {
		setModelClass(PaymentFeeInfo.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("type", "type_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the payment fee info in the entity cache if it is enabled.
	 *
	 * @param paymentFeeInfo the payment fee info
	 */
	@Override
	public void cacheResult(PaymentFeeInfo paymentFeeInfo) {
		entityCache.putResult(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoImpl.class, paymentFeeInfo.getPrimaryKey(),
			paymentFeeInfo);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { paymentFeeInfo.getUuid(), paymentFeeInfo.getGroupId() },
			paymentFeeInfo);

		paymentFeeInfo.resetOriginalValues();
	}

	/**
	 * Caches the payment fee infos in the entity cache if it is enabled.
	 *
	 * @param paymentFeeInfos the payment fee infos
	 */
	@Override
	public void cacheResult(List<PaymentFeeInfo> paymentFeeInfos) {
		for (PaymentFeeInfo paymentFeeInfo : paymentFeeInfos) {
			if (entityCache.getResult(
						PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
						PaymentFeeInfoImpl.class, paymentFeeInfo.getPrimaryKey()) == null) {
				cacheResult(paymentFeeInfo);
			}
			else {
				paymentFeeInfo.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all payment fee infos.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PaymentFeeInfoImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the payment fee info.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PaymentFeeInfo paymentFeeInfo) {
		entityCache.removeResult(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoImpl.class, paymentFeeInfo.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PaymentFeeInfoModelImpl)paymentFeeInfo, true);
	}

	@Override
	public void clearCache(List<PaymentFeeInfo> paymentFeeInfos) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PaymentFeeInfo paymentFeeInfo : paymentFeeInfos) {
			entityCache.removeResult(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
				PaymentFeeInfoImpl.class, paymentFeeInfo.getPrimaryKey());

			clearUniqueFindersCache((PaymentFeeInfoModelImpl)paymentFeeInfo,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		PaymentFeeInfoModelImpl paymentFeeInfoModelImpl) {
		Object[] args = new Object[] {
				paymentFeeInfoModelImpl.getUuid(),
				paymentFeeInfoModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			paymentFeeInfoModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PaymentFeeInfoModelImpl paymentFeeInfoModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					paymentFeeInfoModelImpl.getUuid(),
					paymentFeeInfoModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((paymentFeeInfoModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					paymentFeeInfoModelImpl.getOriginalUuid(),
					paymentFeeInfoModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new payment fee info with the primary key. Does not add the payment fee info to the database.
	 *
	 * @param paymentFeeInfoId the primary key for the new payment fee info
	 * @return the new payment fee info
	 */
	@Override
	public PaymentFeeInfo create(long paymentFeeInfoId) {
		PaymentFeeInfo paymentFeeInfo = new PaymentFeeInfoImpl();

		paymentFeeInfo.setNew(true);
		paymentFeeInfo.setPrimaryKey(paymentFeeInfoId);

		String uuid = PortalUUIDUtil.generate();

		paymentFeeInfo.setUuid(uuid);

		paymentFeeInfo.setCompanyId(companyProvider.getCompanyId());

		return paymentFeeInfo;
	}

	/**
	 * Removes the payment fee info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param paymentFeeInfoId the primary key of the payment fee info
	 * @return the payment fee info that was removed
	 * @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	 */
	@Override
	public PaymentFeeInfo remove(long paymentFeeInfoId)
		throws NoSuchPaymentFeeInfoException {
		return remove((Serializable)paymentFeeInfoId);
	}

	/**
	 * Removes the payment fee info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the payment fee info
	 * @return the payment fee info that was removed
	 * @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	 */
	@Override
	public PaymentFeeInfo remove(Serializable primaryKey)
		throws NoSuchPaymentFeeInfoException {
		Session session = null;

		try {
			session = openSession();

			PaymentFeeInfo paymentFeeInfo = (PaymentFeeInfo)session.get(PaymentFeeInfoImpl.class,
					primaryKey);

			if (paymentFeeInfo == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPaymentFeeInfoException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(paymentFeeInfo);
		}
		catch (NoSuchPaymentFeeInfoException nsee) {
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
	protected PaymentFeeInfo removeImpl(PaymentFeeInfo paymentFeeInfo) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(paymentFeeInfo)) {
				paymentFeeInfo = (PaymentFeeInfo)session.get(PaymentFeeInfoImpl.class,
						paymentFeeInfo.getPrimaryKeyObj());
			}

			if (paymentFeeInfo != null) {
				session.delete(paymentFeeInfo);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (paymentFeeInfo != null) {
			clearCache(paymentFeeInfo);
		}

		return paymentFeeInfo;
	}

	@Override
	public PaymentFeeInfo updateImpl(PaymentFeeInfo paymentFeeInfo) {
		boolean isNew = paymentFeeInfo.isNew();

		if (!(paymentFeeInfo instanceof PaymentFeeInfoModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(paymentFeeInfo.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(paymentFeeInfo);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in paymentFeeInfo proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PaymentFeeInfo implementation " +
				paymentFeeInfo.getClass());
		}

		PaymentFeeInfoModelImpl paymentFeeInfoModelImpl = (PaymentFeeInfoModelImpl)paymentFeeInfo;

		if (Validator.isNull(paymentFeeInfo.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			paymentFeeInfo.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (paymentFeeInfo.getCreateDate() == null)) {
			if (serviceContext == null) {
				paymentFeeInfo.setCreateDate(now);
			}
			else {
				paymentFeeInfo.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!paymentFeeInfoModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				paymentFeeInfo.setModifiedDate(now);
			}
			else {
				paymentFeeInfo.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (paymentFeeInfo.isNew()) {
				session.save(paymentFeeInfo);

				paymentFeeInfo.setNew(false);
			}
			else {
				paymentFeeInfo = (PaymentFeeInfo)session.merge(paymentFeeInfo);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PaymentFeeInfoModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { paymentFeeInfoModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					paymentFeeInfoModelImpl.getUuid(),
					paymentFeeInfoModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					paymentFeeInfoModelImpl.getServiceConfigMappingId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SERVICECONFIGMAPPINGID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((paymentFeeInfoModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentFeeInfoModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { paymentFeeInfoModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((paymentFeeInfoModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentFeeInfoModelImpl.getOriginalUuid(),
						paymentFeeInfoModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						paymentFeeInfoModelImpl.getUuid(),
						paymentFeeInfoModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((paymentFeeInfoModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentFeeInfoModelImpl.getOriginalServiceConfigMappingId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SERVICECONFIGMAPPINGID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID,
					args);

				args = new Object[] {
						paymentFeeInfoModelImpl.getServiceConfigMappingId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SERVICECONFIGMAPPINGID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICECONFIGMAPPINGID,
					args);
			}
		}

		entityCache.putResult(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
			PaymentFeeInfoImpl.class, paymentFeeInfo.getPrimaryKey(),
			paymentFeeInfo, false);

		clearUniqueFindersCache(paymentFeeInfoModelImpl, false);
		cacheUniqueFindersCache(paymentFeeInfoModelImpl);

		paymentFeeInfo.resetOriginalValues();

		return paymentFeeInfo;
	}

	/**
	 * Returns the payment fee info with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment fee info
	 * @return the payment fee info
	 * @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	 */
	@Override
	public PaymentFeeInfo findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPaymentFeeInfoException {
		PaymentFeeInfo paymentFeeInfo = fetchByPrimaryKey(primaryKey);

		if (paymentFeeInfo == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPaymentFeeInfoException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return paymentFeeInfo;
	}

	/**
	 * Returns the payment fee info with the primary key or throws a {@link NoSuchPaymentFeeInfoException} if it could not be found.
	 *
	 * @param paymentFeeInfoId the primary key of the payment fee info
	 * @return the payment fee info
	 * @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	 */
	@Override
	public PaymentFeeInfo findByPrimaryKey(long paymentFeeInfoId)
		throws NoSuchPaymentFeeInfoException {
		return findByPrimaryKey((Serializable)paymentFeeInfoId);
	}

	/**
	 * Returns the payment fee info with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment fee info
	 * @return the payment fee info, or <code>null</code> if a payment fee info with the primary key could not be found
	 */
	@Override
	public PaymentFeeInfo fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
				PaymentFeeInfoImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PaymentFeeInfo paymentFeeInfo = (PaymentFeeInfo)serializable;

		if (paymentFeeInfo == null) {
			Session session = null;

			try {
				session = openSession();

				paymentFeeInfo = (PaymentFeeInfo)session.get(PaymentFeeInfoImpl.class,
						primaryKey);

				if (paymentFeeInfo != null) {
					cacheResult(paymentFeeInfo);
				}
				else {
					entityCache.putResult(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
						PaymentFeeInfoImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
					PaymentFeeInfoImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return paymentFeeInfo;
	}

	/**
	 * Returns the payment fee info with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param paymentFeeInfoId the primary key of the payment fee info
	 * @return the payment fee info, or <code>null</code> if a payment fee info with the primary key could not be found
	 */
	@Override
	public PaymentFeeInfo fetchByPrimaryKey(long paymentFeeInfoId) {
		return fetchByPrimaryKey((Serializable)paymentFeeInfoId);
	}

	@Override
	public Map<Serializable, PaymentFeeInfo> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PaymentFeeInfo> map = new HashMap<Serializable, PaymentFeeInfo>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PaymentFeeInfo paymentFeeInfo = fetchByPrimaryKey(primaryKey);

			if (paymentFeeInfo != null) {
				map.put(primaryKey, paymentFeeInfo);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
					PaymentFeeInfoImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PaymentFeeInfo)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PAYMENTFEEINFO_WHERE_PKS_IN);

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

			for (PaymentFeeInfo paymentFeeInfo : (List<PaymentFeeInfo>)q.list()) {
				map.put(paymentFeeInfo.getPrimaryKeyObj(), paymentFeeInfo);

				cacheResult(paymentFeeInfo);

				uncachedPrimaryKeys.remove(paymentFeeInfo.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PaymentFeeInfoModelImpl.ENTITY_CACHE_ENABLED,
					PaymentFeeInfoImpl.class, primaryKey, nullModel);
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
	 * Returns all the payment fee infos.
	 *
	 * @return the payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment fee infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment fee infos
	 * @param end the upper bound of the range of payment fee infos (not inclusive)
	 * @return the range of payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment fee infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment fee infos
	 * @param end the upper bound of the range of payment fee infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findAll(int start, int end,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the payment fee infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment fee infos
	 * @param end the upper bound of the range of payment fee infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of payment fee infos
	 */
	@Override
	public List<PaymentFeeInfo> findAll(int start, int end,
		OrderByComparator<PaymentFeeInfo> orderByComparator,
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

		List<PaymentFeeInfo> list = null;

		if (retrieveFromCache) {
			list = (List<PaymentFeeInfo>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PAYMENTFEEINFO);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PAYMENTFEEINFO;

				if (pagination) {
					sql = sql.concat(PaymentFeeInfoModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PaymentFeeInfo>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PaymentFeeInfo>)QueryUtil.list(q,
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
	 * Removes all the payment fee infos from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PaymentFeeInfo paymentFeeInfo : findAll()) {
			remove(paymentFeeInfo);
		}
	}

	/**
	 * Returns the number of payment fee infos.
	 *
	 * @return the number of payment fee infos
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PAYMENTFEEINFO);

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
		return PaymentFeeInfoModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the payment fee info persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PaymentFeeInfoImpl.class.getName());
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
	private static final String _SQL_SELECT_PAYMENTFEEINFO = "SELECT paymentFeeInfo FROM PaymentFeeInfo paymentFeeInfo";
	private static final String _SQL_SELECT_PAYMENTFEEINFO_WHERE_PKS_IN = "SELECT paymentFeeInfo FROM PaymentFeeInfo paymentFeeInfo WHERE paymentFeeInfoId IN (";
	private static final String _SQL_SELECT_PAYMENTFEEINFO_WHERE = "SELECT paymentFeeInfo FROM PaymentFeeInfo paymentFeeInfo WHERE ";
	private static final String _SQL_COUNT_PAYMENTFEEINFO = "SELECT COUNT(paymentFeeInfo) FROM PaymentFeeInfo paymentFeeInfo";
	private static final String _SQL_COUNT_PAYMENTFEEINFO_WHERE = "SELECT COUNT(paymentFeeInfo) FROM PaymentFeeInfo paymentFeeInfo WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "paymentFeeInfo.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PaymentFeeInfo exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PaymentFeeInfo exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PaymentFeeInfoPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "type"
			});
}