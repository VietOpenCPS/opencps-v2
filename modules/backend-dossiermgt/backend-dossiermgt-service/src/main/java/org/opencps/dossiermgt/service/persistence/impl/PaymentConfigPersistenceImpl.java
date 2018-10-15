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

import org.opencps.dossiermgt.exception.NoSuchPaymentConfigException;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.impl.PaymentConfigImpl;
import org.opencps.dossiermgt.model.impl.PaymentConfigModelImpl;
import org.opencps.dossiermgt.service.persistence.PaymentConfigPersistence;

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
 * The persistence implementation for the payment config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see PaymentConfigPersistence
 * @see org.opencps.dossiermgt.service.persistence.PaymentConfigUtil
 * @generated
 */
@ProviderType
public class PaymentConfigPersistenceImpl extends BasePersistenceImpl<PaymentConfig>
	implements PaymentConfigPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PaymentConfigUtil} to access the payment config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PaymentConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentConfigImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentConfigImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentConfigImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentConfigImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			PaymentConfigModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the payment configs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching payment configs
	 */
	@Override
	public List<PaymentConfig> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment configs
	 * @param end the upper bound of the range of payment configs (not inclusive)
	 * @return the range of matching payment configs
	 */
	@Override
	public List<PaymentConfig> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment configs
	 * @param end the upper bound of the range of payment configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment configs
	 */
	@Override
	public List<PaymentConfig> findByUuid(String uuid, int start, int end,
		OrderByComparator<PaymentConfig> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the payment configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment configs
	 * @param end the upper bound of the range of payment configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching payment configs
	 */
	@Override
	public List<PaymentConfig> findByUuid(String uuid, int start, int end,
		OrderByComparator<PaymentConfig> orderByComparator,
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

		List<PaymentConfig> list = null;

		if (retrieveFromCache) {
			list = (List<PaymentConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PaymentConfig paymentConfig : list) {
					if (!Objects.equals(uuid, paymentConfig.getUuid())) {
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

			query.append(_SQL_SELECT_PAYMENTCONFIG_WHERE);

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
				query.append(PaymentConfigModelImpl.ORDER_BY_JPQL);
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
					list = (List<PaymentConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PaymentConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first payment config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment config
	 * @throws NoSuchPaymentConfigException if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig findByUuid_First(String uuid,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException {
		PaymentConfig paymentConfig = fetchByUuid_First(uuid, orderByComparator);

		if (paymentConfig != null) {
			return paymentConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPaymentConfigException(msg.toString());
	}

	/**
	 * Returns the first payment config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment config, or <code>null</code> if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig fetchByUuid_First(String uuid,
		OrderByComparator<PaymentConfig> orderByComparator) {
		List<PaymentConfig> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment config
	 * @throws NoSuchPaymentConfigException if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig findByUuid_Last(String uuid,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException {
		PaymentConfig paymentConfig = fetchByUuid_Last(uuid, orderByComparator);

		if (paymentConfig != null) {
			return paymentConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPaymentConfigException(msg.toString());
	}

	/**
	 * Returns the last payment config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment config, or <code>null</code> if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig fetchByUuid_Last(String uuid,
		OrderByComparator<PaymentConfig> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PaymentConfig> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment configs before and after the current payment config in the ordered set where uuid = &#63;.
	 *
	 * @param paymentConfigId the primary key of the current payment config
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment config
	 * @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	 */
	@Override
	public PaymentConfig[] findByUuid_PrevAndNext(long paymentConfigId,
		String uuid, OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException {
		PaymentConfig paymentConfig = findByPrimaryKey(paymentConfigId);

		Session session = null;

		try {
			session = openSession();

			PaymentConfig[] array = new PaymentConfigImpl[3];

			array[0] = getByUuid_PrevAndNext(session, paymentConfig, uuid,
					orderByComparator, true);

			array[1] = paymentConfig;

			array[2] = getByUuid_PrevAndNext(session, paymentConfig, uuid,
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

	protected PaymentConfig getByUuid_PrevAndNext(Session session,
		PaymentConfig paymentConfig, String uuid,
		OrderByComparator<PaymentConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTCONFIG_WHERE);

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
			query.append(PaymentConfigModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(paymentConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the payment configs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PaymentConfig paymentConfig : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(paymentConfig);
		}
	}

	/**
	 * Returns the number of payment configs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching payment configs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTCONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "paymentConfig.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "paymentConfig.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(paymentConfig.uuid IS NULL OR paymentConfig.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentConfigImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PaymentConfigModelImpl.UUID_COLUMN_BITMASK |
			PaymentConfigModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the payment config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPaymentConfigException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching payment config
	 * @throws NoSuchPaymentConfigException if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig findByUUID_G(String uuid, long groupId)
		throws NoSuchPaymentConfigException {
		PaymentConfig paymentConfig = fetchByUUID_G(uuid, groupId);

		if (paymentConfig == null) {
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

			throw new NoSuchPaymentConfigException(msg.toString());
		}

		return paymentConfig;
	}

	/**
	 * Returns the payment config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching payment config, or <code>null</code> if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the payment config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching payment config, or <code>null</code> if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PaymentConfig) {
			PaymentConfig paymentConfig = (PaymentConfig)result;

			if (!Objects.equals(uuid, paymentConfig.getUuid()) ||
					(groupId != paymentConfig.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PAYMENTCONFIG_WHERE);

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

				List<PaymentConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					PaymentConfig paymentConfig = list.get(0);

					result = paymentConfig;

					cacheResult(paymentConfig);
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
			return (PaymentConfig)result;
		}
	}

	/**
	 * Removes the payment config where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the payment config that was removed
	 */
	@Override
	public PaymentConfig removeByUUID_G(String uuid, long groupId)
		throws NoSuchPaymentConfigException {
		PaymentConfig paymentConfig = findByUUID_G(uuid, groupId);

		return remove(paymentConfig);
	}

	/**
	 * Returns the number of payment configs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching payment configs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTCONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "paymentConfig.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "paymentConfig.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(paymentConfig.uuid IS NULL OR paymentConfig.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "paymentConfig.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentConfigImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentConfigImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PaymentConfigModelImpl.UUID_COLUMN_BITMASK |
			PaymentConfigModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the payment configs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching payment configs
	 */
	@Override
	public List<PaymentConfig> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment configs
	 * @param end the upper bound of the range of payment configs (not inclusive)
	 * @return the range of matching payment configs
	 */
	@Override
	public List<PaymentConfig> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment configs
	 * @param end the upper bound of the range of payment configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment configs
	 */
	@Override
	public List<PaymentConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PaymentConfig> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the payment configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment configs
	 * @param end the upper bound of the range of payment configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching payment configs
	 */
	@Override
	public List<PaymentConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PaymentConfig> orderByComparator,
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

		List<PaymentConfig> list = null;

		if (retrieveFromCache) {
			list = (List<PaymentConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PaymentConfig paymentConfig : list) {
					if (!Objects.equals(uuid, paymentConfig.getUuid()) ||
							(companyId != paymentConfig.getCompanyId())) {
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

			query.append(_SQL_SELECT_PAYMENTCONFIG_WHERE);

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
				query.append(PaymentConfigModelImpl.ORDER_BY_JPQL);
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
					list = (List<PaymentConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PaymentConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first payment config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment config
	 * @throws NoSuchPaymentConfigException if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException {
		PaymentConfig paymentConfig = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (paymentConfig != null) {
			return paymentConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPaymentConfigException(msg.toString());
	}

	/**
	 * Returns the first payment config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment config, or <code>null</code> if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PaymentConfig> orderByComparator) {
		List<PaymentConfig> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment config
	 * @throws NoSuchPaymentConfigException if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException {
		PaymentConfig paymentConfig = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (paymentConfig != null) {
			return paymentConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPaymentConfigException(msg.toString());
	}

	/**
	 * Returns the last payment config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment config, or <code>null</code> if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PaymentConfig> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<PaymentConfig> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment configs before and after the current payment config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param paymentConfigId the primary key of the current payment config
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment config
	 * @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	 */
	@Override
	public PaymentConfig[] findByUuid_C_PrevAndNext(long paymentConfigId,
		String uuid, long companyId,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException {
		PaymentConfig paymentConfig = findByPrimaryKey(paymentConfigId);

		Session session = null;

		try {
			session = openSession();

			PaymentConfig[] array = new PaymentConfigImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, paymentConfig, uuid,
					companyId, orderByComparator, true);

			array[1] = paymentConfig;

			array[2] = getByUuid_C_PrevAndNext(session, paymentConfig, uuid,
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

	protected PaymentConfig getByUuid_C_PrevAndNext(Session session,
		PaymentConfig paymentConfig, String uuid, long companyId,
		OrderByComparator<PaymentConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PAYMENTCONFIG_WHERE);

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
			query.append(PaymentConfigModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(paymentConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the payment configs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (PaymentConfig paymentConfig : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(paymentConfig);
		}
	}

	/**
	 * Returns the number of payment configs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching payment configs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTCONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "paymentConfig.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "paymentConfig.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(paymentConfig.uuid IS NULL OR paymentConfig.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "paymentConfig.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FB_GID = new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentConfigImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByFB_GID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FB_GID =
		new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentConfigImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByFB_GID", new String[] { Long.class.getName() },
			PaymentConfigModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FB_GID = new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFB_GID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the payment configs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching payment configs
	 */
	@Override
	public List<PaymentConfig> findByFB_GID(long groupId) {
		return findByFB_GID(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of payment configs
	 * @param end the upper bound of the range of payment configs (not inclusive)
	 * @return the range of matching payment configs
	 */
	@Override
	public List<PaymentConfig> findByFB_GID(long groupId, int start, int end) {
		return findByFB_GID(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of payment configs
	 * @param end the upper bound of the range of payment configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment configs
	 */
	@Override
	public List<PaymentConfig> findByFB_GID(long groupId, int start, int end,
		OrderByComparator<PaymentConfig> orderByComparator) {
		return findByFB_GID(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the payment configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of payment configs
	 * @param end the upper bound of the range of payment configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching payment configs
	 */
	@Override
	public List<PaymentConfig> findByFB_GID(long groupId, int start, int end,
		OrderByComparator<PaymentConfig> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FB_GID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FB_GID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<PaymentConfig> list = null;

		if (retrieveFromCache) {
			list = (List<PaymentConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PaymentConfig paymentConfig : list) {
					if ((groupId != paymentConfig.getGroupId())) {
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

			query.append(_SQL_SELECT_PAYMENTCONFIG_WHERE);

			query.append(_FINDER_COLUMN_FB_GID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PaymentConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<PaymentConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PaymentConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first payment config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment config
	 * @throws NoSuchPaymentConfigException if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig findByFB_GID_First(long groupId,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException {
		PaymentConfig paymentConfig = fetchByFB_GID_First(groupId,
				orderByComparator);

		if (paymentConfig != null) {
			return paymentConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchPaymentConfigException(msg.toString());
	}

	/**
	 * Returns the first payment config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment config, or <code>null</code> if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig fetchByFB_GID_First(long groupId,
		OrderByComparator<PaymentConfig> orderByComparator) {
		List<PaymentConfig> list = findByFB_GID(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment config
	 * @throws NoSuchPaymentConfigException if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig findByFB_GID_Last(long groupId,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException {
		PaymentConfig paymentConfig = fetchByFB_GID_Last(groupId,
				orderByComparator);

		if (paymentConfig != null) {
			return paymentConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchPaymentConfigException(msg.toString());
	}

	/**
	 * Returns the last payment config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment config, or <code>null</code> if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig fetchByFB_GID_Last(long groupId,
		OrderByComparator<PaymentConfig> orderByComparator) {
		int count = countByFB_GID(groupId);

		if (count == 0) {
			return null;
		}

		List<PaymentConfig> list = findByFB_GID(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment configs before and after the current payment config in the ordered set where groupId = &#63;.
	 *
	 * @param paymentConfigId the primary key of the current payment config
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment config
	 * @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	 */
	@Override
	public PaymentConfig[] findByFB_GID_PrevAndNext(long paymentConfigId,
		long groupId, OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException {
		PaymentConfig paymentConfig = findByPrimaryKey(paymentConfigId);

		Session session = null;

		try {
			session = openSession();

			PaymentConfig[] array = new PaymentConfigImpl[3];

			array[0] = getByFB_GID_PrevAndNext(session, paymentConfig, groupId,
					orderByComparator, true);

			array[1] = paymentConfig;

			array[2] = getByFB_GID_PrevAndNext(session, paymentConfig, groupId,
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

	protected PaymentConfig getByFB_GID_PrevAndNext(Session session,
		PaymentConfig paymentConfig, long groupId,
		OrderByComparator<PaymentConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTCONFIG_WHERE);

		query.append(_FINDER_COLUMN_FB_GID_GROUPID_2);

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
			query.append(PaymentConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(paymentConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the payment configs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByFB_GID(long groupId) {
		for (PaymentConfig paymentConfig : findByFB_GID(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(paymentConfig);
		}
	}

	/**
	 * Returns the number of payment configs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching payment configs
	 */
	@Override
	public int countByFB_GID(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FB_GID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTCONFIG_WHERE);

			query.append(_FINDER_COLUMN_FB_GID_GROUPID_2);

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

	private static final String _FINDER_COLUMN_FB_GID_GROUPID_2 = "paymentConfig.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_FB_GID_GOVAGENCYCODE = new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentConfigImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByFB_GID_govAgencyCode",
			new String[] { Long.class.getName(), String.class.getName() },
			PaymentConfigModelImpl.GROUPID_COLUMN_BITMASK |
			PaymentConfigModelImpl.GOVAGENCYCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FB_GID_GOVAGENCYCODE = new FinderPath(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByFB_GID_govAgencyCode",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the payment config where groupId = &#63; and govAgencyCode = &#63; or throws a {@link NoSuchPaymentConfigException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @return the matching payment config
	 * @throws NoSuchPaymentConfigException if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig findByFB_GID_govAgencyCode(long groupId,
		String govAgencyCode) throws NoSuchPaymentConfigException {
		PaymentConfig paymentConfig = fetchByFB_GID_govAgencyCode(groupId,
				govAgencyCode);

		if (paymentConfig == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", govAgencyCode=");
			msg.append(govAgencyCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPaymentConfigException(msg.toString());
		}

		return paymentConfig;
	}

	/**
	 * Returns the payment config where groupId = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @return the matching payment config, or <code>null</code> if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig fetchByFB_GID_govAgencyCode(long groupId,
		String govAgencyCode) {
		return fetchByFB_GID_govAgencyCode(groupId, govAgencyCode, true);
	}

	/**
	 * Returns the payment config where groupId = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching payment config, or <code>null</code> if a matching payment config could not be found
	 */
	@Override
	public PaymentConfig fetchByFB_GID_govAgencyCode(long groupId,
		String govAgencyCode, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, govAgencyCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_FB_GID_GOVAGENCYCODE,
					finderArgs, this);
		}

		if (result instanceof PaymentConfig) {
			PaymentConfig paymentConfig = (PaymentConfig)result;

			if ((groupId != paymentConfig.getGroupId()) ||
					!Objects.equals(govAgencyCode,
						paymentConfig.getGovAgencyCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PAYMENTCONFIG_WHERE);

			query.append(_FINDER_COLUMN_FB_GID_GOVAGENCYCODE_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_FB_GID_GOVAGENCYCODE_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_FB_GID_GOVAGENCYCODE_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_FB_GID_GOVAGENCYCODE_GOVAGENCYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				List<PaymentConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_FB_GID_GOVAGENCYCODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"PaymentConfigPersistenceImpl.fetchByFB_GID_govAgencyCode(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PaymentConfig paymentConfig = list.get(0);

					result = paymentConfig;

					cacheResult(paymentConfig);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_FB_GID_GOVAGENCYCODE,
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
			return (PaymentConfig)result;
		}
	}

	/**
	 * Removes the payment config where groupId = &#63; and govAgencyCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @return the payment config that was removed
	 */
	@Override
	public PaymentConfig removeByFB_GID_govAgencyCode(long groupId,
		String govAgencyCode) throws NoSuchPaymentConfigException {
		PaymentConfig paymentConfig = findByFB_GID_govAgencyCode(groupId,
				govAgencyCode);

		return remove(paymentConfig);
	}

	/**
	 * Returns the number of payment configs where groupId = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @return the number of matching payment configs
	 */
	@Override
	public int countByFB_GID_govAgencyCode(long groupId, String govAgencyCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FB_GID_GOVAGENCYCODE;

		Object[] finderArgs = new Object[] { groupId, govAgencyCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTCONFIG_WHERE);

			query.append(_FINDER_COLUMN_FB_GID_GOVAGENCYCODE_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_FB_GID_GOVAGENCYCODE_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_FB_GID_GOVAGENCYCODE_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_FB_GID_GOVAGENCYCODE_GOVAGENCYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
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

	private static final String _FINDER_COLUMN_FB_GID_GOVAGENCYCODE_GROUPID_2 = "paymentConfig.groupId = ? AND ";
	private static final String _FINDER_COLUMN_FB_GID_GOVAGENCYCODE_GOVAGENCYCODE_1 =
		"paymentConfig.govAgencyCode IS NULL";
	private static final String _FINDER_COLUMN_FB_GID_GOVAGENCYCODE_GOVAGENCYCODE_2 =
		"paymentConfig.govAgencyCode = ?";
	private static final String _FINDER_COLUMN_FB_GID_GOVAGENCYCODE_GOVAGENCYCODE_3 =
		"(paymentConfig.govAgencyCode IS NULL OR paymentConfig.govAgencyCode = '')";

	public PaymentConfigPersistenceImpl() {
		setModelClass(PaymentConfig.class);

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
	 * Caches the payment config in the entity cache if it is enabled.
	 *
	 * @param paymentConfig the payment config
	 */
	@Override
	public void cacheResult(PaymentConfig paymentConfig) {
		entityCache.putResult(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigImpl.class, paymentConfig.getPrimaryKey(),
			paymentConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { paymentConfig.getUuid(), paymentConfig.getGroupId() },
			paymentConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_FB_GID_GOVAGENCYCODE,
			new Object[] {
				paymentConfig.getGroupId(), paymentConfig.getGovAgencyCode()
			}, paymentConfig);

		paymentConfig.resetOriginalValues();
	}

	/**
	 * Caches the payment configs in the entity cache if it is enabled.
	 *
	 * @param paymentConfigs the payment configs
	 */
	@Override
	public void cacheResult(List<PaymentConfig> paymentConfigs) {
		for (PaymentConfig paymentConfig : paymentConfigs) {
			if (entityCache.getResult(
						PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
						PaymentConfigImpl.class, paymentConfig.getPrimaryKey()) == null) {
				cacheResult(paymentConfig);
			}
			else {
				paymentConfig.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all payment configs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PaymentConfigImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the payment config.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PaymentConfig paymentConfig) {
		entityCache.removeResult(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigImpl.class, paymentConfig.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PaymentConfigModelImpl)paymentConfig, true);
	}

	@Override
	public void clearCache(List<PaymentConfig> paymentConfigs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PaymentConfig paymentConfig : paymentConfigs) {
			entityCache.removeResult(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
				PaymentConfigImpl.class, paymentConfig.getPrimaryKey());

			clearUniqueFindersCache((PaymentConfigModelImpl)paymentConfig, true);
		}
	}

	protected void cacheUniqueFindersCache(
		PaymentConfigModelImpl paymentConfigModelImpl) {
		Object[] args = new Object[] {
				paymentConfigModelImpl.getUuid(),
				paymentConfigModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			paymentConfigModelImpl, false);

		args = new Object[] {
				paymentConfigModelImpl.getGroupId(),
				paymentConfigModelImpl.getGovAgencyCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_FB_GID_GOVAGENCYCODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_FB_GID_GOVAGENCYCODE, args,
			paymentConfigModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PaymentConfigModelImpl paymentConfigModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					paymentConfigModelImpl.getUuid(),
					paymentConfigModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((paymentConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					paymentConfigModelImpl.getOriginalUuid(),
					paymentConfigModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					paymentConfigModelImpl.getGroupId(),
					paymentConfigModelImpl.getGovAgencyCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FB_GID_GOVAGENCYCODE,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_FB_GID_GOVAGENCYCODE,
				args);
		}

		if ((paymentConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_FB_GID_GOVAGENCYCODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					paymentConfigModelImpl.getOriginalGroupId(),
					paymentConfigModelImpl.getOriginalGovAgencyCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FB_GID_GOVAGENCYCODE,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_FB_GID_GOVAGENCYCODE,
				args);
		}
	}

	/**
	 * Creates a new payment config with the primary key. Does not add the payment config to the database.
	 *
	 * @param paymentConfigId the primary key for the new payment config
	 * @return the new payment config
	 */
	@Override
	public PaymentConfig create(long paymentConfigId) {
		PaymentConfig paymentConfig = new PaymentConfigImpl();

		paymentConfig.setNew(true);
		paymentConfig.setPrimaryKey(paymentConfigId);

		String uuid = PortalUUIDUtil.generate();

		paymentConfig.setUuid(uuid);

		paymentConfig.setCompanyId(companyProvider.getCompanyId());

		return paymentConfig;
	}

	/**
	 * Removes the payment config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param paymentConfigId the primary key of the payment config
	 * @return the payment config that was removed
	 * @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	 */
	@Override
	public PaymentConfig remove(long paymentConfigId)
		throws NoSuchPaymentConfigException {
		return remove((Serializable)paymentConfigId);
	}

	/**
	 * Removes the payment config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the payment config
	 * @return the payment config that was removed
	 * @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	 */
	@Override
	public PaymentConfig remove(Serializable primaryKey)
		throws NoSuchPaymentConfigException {
		Session session = null;

		try {
			session = openSession();

			PaymentConfig paymentConfig = (PaymentConfig)session.get(PaymentConfigImpl.class,
					primaryKey);

			if (paymentConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPaymentConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(paymentConfig);
		}
		catch (NoSuchPaymentConfigException nsee) {
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
	protected PaymentConfig removeImpl(PaymentConfig paymentConfig) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(paymentConfig)) {
				paymentConfig = (PaymentConfig)session.get(PaymentConfigImpl.class,
						paymentConfig.getPrimaryKeyObj());
			}

			if (paymentConfig != null) {
				session.delete(paymentConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (paymentConfig != null) {
			clearCache(paymentConfig);
		}

		return paymentConfig;
	}

	@Override
	public PaymentConfig updateImpl(PaymentConfig paymentConfig) {
		boolean isNew = paymentConfig.isNew();

		if (!(paymentConfig instanceof PaymentConfigModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(paymentConfig.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(paymentConfig);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in paymentConfig proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PaymentConfig implementation " +
				paymentConfig.getClass());
		}

		PaymentConfigModelImpl paymentConfigModelImpl = (PaymentConfigModelImpl)paymentConfig;

		if (Validator.isNull(paymentConfig.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			paymentConfig.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (paymentConfig.getCreateDate() == null)) {
			if (serviceContext == null) {
				paymentConfig.setCreateDate(now);
			}
			else {
				paymentConfig.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!paymentConfigModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				paymentConfig.setModifiedDate(now);
			}
			else {
				paymentConfig.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (paymentConfig.isNew()) {
				session.save(paymentConfig);

				paymentConfig.setNew(false);
			}
			else {
				paymentConfig = (PaymentConfig)session.merge(paymentConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PaymentConfigModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { paymentConfigModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					paymentConfigModelImpl.getUuid(),
					paymentConfigModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { paymentConfigModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FB_GID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FB_GID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((paymentConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentConfigModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { paymentConfigModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((paymentConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentConfigModelImpl.getOriginalUuid(),
						paymentConfigModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						paymentConfigModelImpl.getUuid(),
						paymentConfigModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((paymentConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FB_GID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentConfigModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FB_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FB_GID,
					args);

				args = new Object[] { paymentConfigModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FB_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FB_GID,
					args);
			}
		}

		entityCache.putResult(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentConfigImpl.class, paymentConfig.getPrimaryKey(),
			paymentConfig, false);

		clearUniqueFindersCache(paymentConfigModelImpl, false);
		cacheUniqueFindersCache(paymentConfigModelImpl);

		paymentConfig.resetOriginalValues();

		return paymentConfig;
	}

	/**
	 * Returns the payment config with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment config
	 * @return the payment config
	 * @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	 */
	@Override
	public PaymentConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPaymentConfigException {
		PaymentConfig paymentConfig = fetchByPrimaryKey(primaryKey);

		if (paymentConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPaymentConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return paymentConfig;
	}

	/**
	 * Returns the payment config with the primary key or throws a {@link NoSuchPaymentConfigException} if it could not be found.
	 *
	 * @param paymentConfigId the primary key of the payment config
	 * @return the payment config
	 * @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	 */
	@Override
	public PaymentConfig findByPrimaryKey(long paymentConfigId)
		throws NoSuchPaymentConfigException {
		return findByPrimaryKey((Serializable)paymentConfigId);
	}

	/**
	 * Returns the payment config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment config
	 * @return the payment config, or <code>null</code> if a payment config with the primary key could not be found
	 */
	@Override
	public PaymentConfig fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
				PaymentConfigImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PaymentConfig paymentConfig = (PaymentConfig)serializable;

		if (paymentConfig == null) {
			Session session = null;

			try {
				session = openSession();

				paymentConfig = (PaymentConfig)session.get(PaymentConfigImpl.class,
						primaryKey);

				if (paymentConfig != null) {
					cacheResult(paymentConfig);
				}
				else {
					entityCache.putResult(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
						PaymentConfigImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
					PaymentConfigImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return paymentConfig;
	}

	/**
	 * Returns the payment config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param paymentConfigId the primary key of the payment config
	 * @return the payment config, or <code>null</code> if a payment config with the primary key could not be found
	 */
	@Override
	public PaymentConfig fetchByPrimaryKey(long paymentConfigId) {
		return fetchByPrimaryKey((Serializable)paymentConfigId);
	}

	@Override
	public Map<Serializable, PaymentConfig> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PaymentConfig> map = new HashMap<Serializable, PaymentConfig>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PaymentConfig paymentConfig = fetchByPrimaryKey(primaryKey);

			if (paymentConfig != null) {
				map.put(primaryKey, paymentConfig);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
					PaymentConfigImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PaymentConfig)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PAYMENTCONFIG_WHERE_PKS_IN);

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

			for (PaymentConfig paymentConfig : (List<PaymentConfig>)q.list()) {
				map.put(paymentConfig.getPrimaryKeyObj(), paymentConfig);

				cacheResult(paymentConfig);

				uncachedPrimaryKeys.remove(paymentConfig.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PaymentConfigModelImpl.ENTITY_CACHE_ENABLED,
					PaymentConfigImpl.class, primaryKey, nullModel);
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
	 * Returns all the payment configs.
	 *
	 * @return the payment configs
	 */
	@Override
	public List<PaymentConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment configs
	 * @param end the upper bound of the range of payment configs (not inclusive)
	 * @return the range of payment configs
	 */
	@Override
	public List<PaymentConfig> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment configs
	 * @param end the upper bound of the range of payment configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of payment configs
	 */
	@Override
	public List<PaymentConfig> findAll(int start, int end,
		OrderByComparator<PaymentConfig> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the payment configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment configs
	 * @param end the upper bound of the range of payment configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of payment configs
	 */
	@Override
	public List<PaymentConfig> findAll(int start, int end,
		OrderByComparator<PaymentConfig> orderByComparator,
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

		List<PaymentConfig> list = null;

		if (retrieveFromCache) {
			list = (List<PaymentConfig>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PAYMENTCONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PAYMENTCONFIG;

				if (pagination) {
					sql = sql.concat(PaymentConfigModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PaymentConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PaymentConfig>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the payment configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PaymentConfig paymentConfig : findAll()) {
			remove(paymentConfig);
		}
	}

	/**
	 * Returns the number of payment configs.
	 *
	 * @return the number of payment configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PAYMENTCONFIG);

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
		return PaymentConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the payment config persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PaymentConfigImpl.class.getName());
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
	private static final String _SQL_SELECT_PAYMENTCONFIG = "SELECT paymentConfig FROM PaymentConfig paymentConfig";
	private static final String _SQL_SELECT_PAYMENTCONFIG_WHERE_PKS_IN = "SELECT paymentConfig FROM PaymentConfig paymentConfig WHERE paymentConfigId IN (";
	private static final String _SQL_SELECT_PAYMENTCONFIG_WHERE = "SELECT paymentConfig FROM PaymentConfig paymentConfig WHERE ";
	private static final String _SQL_COUNT_PAYMENTCONFIG = "SELECT COUNT(paymentConfig) FROM PaymentConfig paymentConfig";
	private static final String _SQL_COUNT_PAYMENTCONFIG_WHERE = "SELECT COUNT(paymentConfig) FROM PaymentConfig paymentConfig WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "paymentConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PaymentConfig exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PaymentConfig exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PaymentConfigPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}