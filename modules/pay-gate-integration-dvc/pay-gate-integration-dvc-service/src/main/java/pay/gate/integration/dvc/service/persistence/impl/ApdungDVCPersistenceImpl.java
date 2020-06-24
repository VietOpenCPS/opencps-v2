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

package pay.gate.integration.dvc.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import pay.gate.integration.dvc.exception.NoSuchApdungDVCException;
import pay.gate.integration.dvc.model.ApdungDVC;
import pay.gate.integration.dvc.model.impl.ApdungDVCImpl;
import pay.gate.integration.dvc.model.impl.ApdungDVCModelImpl;
import pay.gate.integration.dvc.service.persistence.ApdungDVCPersistence;

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
 * The persistence implementation for the apdung dvc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApdungDVCPersistence
 * @see pay.gate.integration.dvc.service.persistence.ApdungDVCUtil
 * @generated
 */
@ProviderType
public class ApdungDVCPersistenceImpl extends BasePersistenceImpl<ApdungDVC>
	implements ApdungDVCPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ApdungDVCUtil} to access the apdung dvc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ApdungDVCImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCModelImpl.FINDER_CACHE_ENABLED, ApdungDVCImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCModelImpl.FINDER_CACHE_ENABLED, ApdungDVCImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCModelImpl.FINDER_CACHE_ENABLED, ApdungDVCImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCModelImpl.FINDER_CACHE_ENABLED, ApdungDVCImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ApdungDVCModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the apdung dvcs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching apdung dvcs
	 */
	@Override
	public List<ApdungDVC> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the apdung dvcs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of apdung dvcs
	 * @param end the upper bound of the range of apdung dvcs (not inclusive)
	 * @return the range of matching apdung dvcs
	 */
	@Override
	public List<ApdungDVC> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the apdung dvcs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of apdung dvcs
	 * @param end the upper bound of the range of apdung dvcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching apdung dvcs
	 */
	@Override
	public List<ApdungDVC> findByUuid(String uuid, int start, int end,
		OrderByComparator<ApdungDVC> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the apdung dvcs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of apdung dvcs
	 * @param end the upper bound of the range of apdung dvcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching apdung dvcs
	 */
	@Override
	public List<ApdungDVC> findByUuid(String uuid, int start, int end,
		OrderByComparator<ApdungDVC> orderByComparator,
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

		List<ApdungDVC> list = null;

		if (retrieveFromCache) {
			list = (List<ApdungDVC>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ApdungDVC apdungDVC : list) {
					if (!Objects.equals(uuid, apdungDVC.getUuid())) {
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

			query.append(_SQL_SELECT_APDUNGDVC_WHERE);

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
				query.append(ApdungDVCModelImpl.ORDER_BY_JPQL);
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
					list = (List<ApdungDVC>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ApdungDVC>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first apdung dvc in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching apdung dvc
	 * @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	 */
	@Override
	public ApdungDVC findByUuid_First(String uuid,
		OrderByComparator<ApdungDVC> orderByComparator)
		throws NoSuchApdungDVCException {
		ApdungDVC apdungDVC = fetchByUuid_First(uuid, orderByComparator);

		if (apdungDVC != null) {
			return apdungDVC;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchApdungDVCException(msg.toString());
	}

	/**
	 * Returns the first apdung dvc in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	 */
	@Override
	public ApdungDVC fetchByUuid_First(String uuid,
		OrderByComparator<ApdungDVC> orderByComparator) {
		List<ApdungDVC> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last apdung dvc in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching apdung dvc
	 * @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	 */
	@Override
	public ApdungDVC findByUuid_Last(String uuid,
		OrderByComparator<ApdungDVC> orderByComparator)
		throws NoSuchApdungDVCException {
		ApdungDVC apdungDVC = fetchByUuid_Last(uuid, orderByComparator);

		if (apdungDVC != null) {
			return apdungDVC;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchApdungDVCException(msg.toString());
	}

	/**
	 * Returns the last apdung dvc in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	 */
	@Override
	public ApdungDVC fetchByUuid_Last(String uuid,
		OrderByComparator<ApdungDVC> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ApdungDVC> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the apdung dvcs before and after the current apdung dvc in the ordered set where uuid = &#63;.
	 *
	 * @param apdungDVCId the primary key of the current apdung dvc
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next apdung dvc
	 * @throws NoSuchApdungDVCException if a apdung dvc with the primary key could not be found
	 */
	@Override
	public ApdungDVC[] findByUuid_PrevAndNext(long apdungDVCId, String uuid,
		OrderByComparator<ApdungDVC> orderByComparator)
		throws NoSuchApdungDVCException {
		ApdungDVC apdungDVC = findByPrimaryKey(apdungDVCId);

		Session session = null;

		try {
			session = openSession();

			ApdungDVC[] array = new ApdungDVCImpl[3];

			array[0] = getByUuid_PrevAndNext(session, apdungDVC, uuid,
					orderByComparator, true);

			array[1] = apdungDVC;

			array[2] = getByUuid_PrevAndNext(session, apdungDVC, uuid,
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

	protected ApdungDVC getByUuid_PrevAndNext(Session session,
		ApdungDVC apdungDVC, String uuid,
		OrderByComparator<ApdungDVC> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APDUNGDVC_WHERE);

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
			query.append(ApdungDVCModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(apdungDVC);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ApdungDVC> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the apdung dvcs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ApdungDVC apdungDVC : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(apdungDVC);
		}
	}

	/**
	 * Returns the number of apdung dvcs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching apdung dvcs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APDUNGDVC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "apdungDVC.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "apdungDVC.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(apdungDVC.uuid IS NULL OR apdungDVC.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCModelImpl.FINDER_CACHE_ENABLED, ApdungDVCImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ApdungDVCModelImpl.UUID_COLUMN_BITMASK |
			ApdungDVCModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the apdung dvc where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchApdungDVCException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching apdung dvc
	 * @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	 */
	@Override
	public ApdungDVC findByUUID_G(String uuid, long groupId)
		throws NoSuchApdungDVCException {
		ApdungDVC apdungDVC = fetchByUUID_G(uuid, groupId);

		if (apdungDVC == null) {
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

			throw new NoSuchApdungDVCException(msg.toString());
		}

		return apdungDVC;
	}

	/**
	 * Returns the apdung dvc where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	 */
	@Override
	public ApdungDVC fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the apdung dvc where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	 */
	@Override
	public ApdungDVC fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ApdungDVC) {
			ApdungDVC apdungDVC = (ApdungDVC)result;

			if (!Objects.equals(uuid, apdungDVC.getUuid()) ||
					(groupId != apdungDVC.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_APDUNGDVC_WHERE);

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

				List<ApdungDVC> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ApdungDVC apdungDVC = list.get(0);

					result = apdungDVC;

					cacheResult(apdungDVC);
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
			return (ApdungDVC)result;
		}
	}

	/**
	 * Removes the apdung dvc where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the apdung dvc that was removed
	 */
	@Override
	public ApdungDVC removeByUUID_G(String uuid, long groupId)
		throws NoSuchApdungDVCException {
		ApdungDVC apdungDVC = findByUUID_G(uuid, groupId);

		return remove(apdungDVC);
	}

	/**
	 * Returns the number of apdung dvcs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching apdung dvcs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APDUNGDVC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "apdungDVC.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "apdungDVC.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(apdungDVC.uuid IS NULL OR apdungDVC.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "apdungDVC.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCModelImpl.FINDER_CACHE_ENABLED, ApdungDVCImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCModelImpl.FINDER_CACHE_ENABLED, ApdungDVCImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ApdungDVCModelImpl.UUID_COLUMN_BITMASK |
			ApdungDVCModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the apdung dvcs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching apdung dvcs
	 */
	@Override
	public List<ApdungDVC> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the apdung dvcs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of apdung dvcs
	 * @param end the upper bound of the range of apdung dvcs (not inclusive)
	 * @return the range of matching apdung dvcs
	 */
	@Override
	public List<ApdungDVC> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the apdung dvcs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of apdung dvcs
	 * @param end the upper bound of the range of apdung dvcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching apdung dvcs
	 */
	@Override
	public List<ApdungDVC> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<ApdungDVC> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the apdung dvcs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of apdung dvcs
	 * @param end the upper bound of the range of apdung dvcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching apdung dvcs
	 */
	@Override
	public List<ApdungDVC> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<ApdungDVC> orderByComparator,
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

		List<ApdungDVC> list = null;

		if (retrieveFromCache) {
			list = (List<ApdungDVC>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ApdungDVC apdungDVC : list) {
					if (!Objects.equals(uuid, apdungDVC.getUuid()) ||
							(companyId != apdungDVC.getCompanyId())) {
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

			query.append(_SQL_SELECT_APDUNGDVC_WHERE);

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
				query.append(ApdungDVCModelImpl.ORDER_BY_JPQL);
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
					list = (List<ApdungDVC>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ApdungDVC>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching apdung dvc
	 * @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	 */
	@Override
	public ApdungDVC findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ApdungDVC> orderByComparator)
		throws NoSuchApdungDVCException {
		ApdungDVC apdungDVC = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (apdungDVC != null) {
			return apdungDVC;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchApdungDVCException(msg.toString());
	}

	/**
	 * Returns the first apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	 */
	@Override
	public ApdungDVC fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ApdungDVC> orderByComparator) {
		List<ApdungDVC> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching apdung dvc
	 * @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	 */
	@Override
	public ApdungDVC findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ApdungDVC> orderByComparator)
		throws NoSuchApdungDVCException {
		ApdungDVC apdungDVC = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (apdungDVC != null) {
			return apdungDVC;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchApdungDVCException(msg.toString());
	}

	/**
	 * Returns the last apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	 */
	@Override
	public ApdungDVC fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ApdungDVC> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ApdungDVC> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the apdung dvcs before and after the current apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param apdungDVCId the primary key of the current apdung dvc
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next apdung dvc
	 * @throws NoSuchApdungDVCException if a apdung dvc with the primary key could not be found
	 */
	@Override
	public ApdungDVC[] findByUuid_C_PrevAndNext(long apdungDVCId, String uuid,
		long companyId, OrderByComparator<ApdungDVC> orderByComparator)
		throws NoSuchApdungDVCException {
		ApdungDVC apdungDVC = findByPrimaryKey(apdungDVCId);

		Session session = null;

		try {
			session = openSession();

			ApdungDVC[] array = new ApdungDVCImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, apdungDVC, uuid,
					companyId, orderByComparator, true);

			array[1] = apdungDVC;

			array[2] = getByUuid_C_PrevAndNext(session, apdungDVC, uuid,
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

	protected ApdungDVC getByUuid_C_PrevAndNext(Session session,
		ApdungDVC apdungDVC, String uuid, long companyId,
		OrderByComparator<ApdungDVC> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_APDUNGDVC_WHERE);

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
			query.append(ApdungDVCModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(apdungDVC);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ApdungDVC> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the apdung dvcs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ApdungDVC apdungDVC : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(apdungDVC);
		}
	}

	/**
	 * Returns the number of apdung dvcs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching apdung dvcs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APDUNGDVC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "apdungDVC.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "apdungDVC.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(apdungDVC.uuid IS NULL OR apdungDVC.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "apdungDVC.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_TTHC_CQTH_MD = new FinderPath(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCModelImpl.FINDER_CACHE_ENABLED, ApdungDVCImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_TTHC_CQTH_MD",
			new String[] {
				String.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			ApdungDVCModelImpl.MATTHC_COLUMN_BITMASK |
			ApdungDVCModelImpl.MACQTH_COLUMN_BITMASK |
			ApdungDVCModelImpl.MUCDO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_TTHC_CQTH_MD = new FinderPath(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_TTHC_CQTH_MD",
			new String[] {
				String.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns the apdung dvc where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63; or throws a {@link NoSuchApdungDVCException} if it could not be found.
	 *
	 * @param maTTHC the ma tthc
	 * @param maCQTH the ma cqth
	 * @param mucdo the mucdo
	 * @return the matching apdung dvc
	 * @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	 */
	@Override
	public ApdungDVC findByF_TTHC_CQTH_MD(String maTTHC, String maCQTH,
		int mucdo) throws NoSuchApdungDVCException {
		ApdungDVC apdungDVC = fetchByF_TTHC_CQTH_MD(maTTHC, maCQTH, mucdo);

		if (apdungDVC == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("maTTHC=");
			msg.append(maTTHC);

			msg.append(", maCQTH=");
			msg.append(maCQTH);

			msg.append(", mucdo=");
			msg.append(mucdo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchApdungDVCException(msg.toString());
		}

		return apdungDVC;
	}

	/**
	 * Returns the apdung dvc where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param maTTHC the ma tthc
	 * @param maCQTH the ma cqth
	 * @param mucdo the mucdo
	 * @return the matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	 */
	@Override
	public ApdungDVC fetchByF_TTHC_CQTH_MD(String maTTHC, String maCQTH,
		int mucdo) {
		return fetchByF_TTHC_CQTH_MD(maTTHC, maCQTH, mucdo, true);
	}

	/**
	 * Returns the apdung dvc where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param maTTHC the ma tthc
	 * @param maCQTH the ma cqth
	 * @param mucdo the mucdo
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	 */
	@Override
	public ApdungDVC fetchByF_TTHC_CQTH_MD(String maTTHC, String maCQTH,
		int mucdo, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { maTTHC, maCQTH, mucdo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_TTHC_CQTH_MD,
					finderArgs, this);
		}

		if (result instanceof ApdungDVC) {
			ApdungDVC apdungDVC = (ApdungDVC)result;

			if (!Objects.equals(maTTHC, apdungDVC.getMaTTHC()) ||
					!Objects.equals(maCQTH, apdungDVC.getMaCQTH()) ||
					(mucdo != apdungDVC.getMucdo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_APDUNGDVC_WHERE);

			boolean bindMaTTHC = false;

			if (maTTHC == null) {
				query.append(_FINDER_COLUMN_F_TTHC_CQTH_MD_MATTHC_1);
			}
			else if (maTTHC.equals("")) {
				query.append(_FINDER_COLUMN_F_TTHC_CQTH_MD_MATTHC_3);
			}
			else {
				bindMaTTHC = true;

				query.append(_FINDER_COLUMN_F_TTHC_CQTH_MD_MATTHC_2);
			}

			boolean bindMaCQTH = false;

			if (maCQTH == null) {
				query.append(_FINDER_COLUMN_F_TTHC_CQTH_MD_MACQTH_1);
			}
			else if (maCQTH.equals("")) {
				query.append(_FINDER_COLUMN_F_TTHC_CQTH_MD_MACQTH_3);
			}
			else {
				bindMaCQTH = true;

				query.append(_FINDER_COLUMN_F_TTHC_CQTH_MD_MACQTH_2);
			}

			query.append(_FINDER_COLUMN_F_TTHC_CQTH_MD_MUCDO_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMaTTHC) {
					qPos.add(maTTHC);
				}

				if (bindMaCQTH) {
					qPos.add(maCQTH);
				}

				qPos.add(mucdo);

				List<ApdungDVC> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_TTHC_CQTH_MD,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ApdungDVCPersistenceImpl.fetchByF_TTHC_CQTH_MD(String, String, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ApdungDVC apdungDVC = list.get(0);

					result = apdungDVC;

					cacheResult(apdungDVC);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_TTHC_CQTH_MD,
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
			return (ApdungDVC)result;
		}
	}

	/**
	 * Removes the apdung dvc where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63; from the database.
	 *
	 * @param maTTHC the ma tthc
	 * @param maCQTH the ma cqth
	 * @param mucdo the mucdo
	 * @return the apdung dvc that was removed
	 */
	@Override
	public ApdungDVC removeByF_TTHC_CQTH_MD(String maTTHC, String maCQTH,
		int mucdo) throws NoSuchApdungDVCException {
		ApdungDVC apdungDVC = findByF_TTHC_CQTH_MD(maTTHC, maCQTH, mucdo);

		return remove(apdungDVC);
	}

	/**
	 * Returns the number of apdung dvcs where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63;.
	 *
	 * @param maTTHC the ma tthc
	 * @param maCQTH the ma cqth
	 * @param mucdo the mucdo
	 * @return the number of matching apdung dvcs
	 */
	@Override
	public int countByF_TTHC_CQTH_MD(String maTTHC, String maCQTH, int mucdo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_TTHC_CQTH_MD;

		Object[] finderArgs = new Object[] { maTTHC, maCQTH, mucdo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_APDUNGDVC_WHERE);

			boolean bindMaTTHC = false;

			if (maTTHC == null) {
				query.append(_FINDER_COLUMN_F_TTHC_CQTH_MD_MATTHC_1);
			}
			else if (maTTHC.equals("")) {
				query.append(_FINDER_COLUMN_F_TTHC_CQTH_MD_MATTHC_3);
			}
			else {
				bindMaTTHC = true;

				query.append(_FINDER_COLUMN_F_TTHC_CQTH_MD_MATTHC_2);
			}

			boolean bindMaCQTH = false;

			if (maCQTH == null) {
				query.append(_FINDER_COLUMN_F_TTHC_CQTH_MD_MACQTH_1);
			}
			else if (maCQTH.equals("")) {
				query.append(_FINDER_COLUMN_F_TTHC_CQTH_MD_MACQTH_3);
			}
			else {
				bindMaCQTH = true;

				query.append(_FINDER_COLUMN_F_TTHC_CQTH_MD_MACQTH_2);
			}

			query.append(_FINDER_COLUMN_F_TTHC_CQTH_MD_MUCDO_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMaTTHC) {
					qPos.add(maTTHC);
				}

				if (bindMaCQTH) {
					qPos.add(maCQTH);
				}

				qPos.add(mucdo);

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

	private static final String _FINDER_COLUMN_F_TTHC_CQTH_MD_MATTHC_1 = "apdungDVC.maTTHC IS NULL AND ";
	private static final String _FINDER_COLUMN_F_TTHC_CQTH_MD_MATTHC_2 = "apdungDVC.maTTHC = ? AND ";
	private static final String _FINDER_COLUMN_F_TTHC_CQTH_MD_MATTHC_3 = "(apdungDVC.maTTHC IS NULL OR apdungDVC.maTTHC = '') AND ";
	private static final String _FINDER_COLUMN_F_TTHC_CQTH_MD_MACQTH_1 = "apdungDVC.maCQTH IS NULL AND ";
	private static final String _FINDER_COLUMN_F_TTHC_CQTH_MD_MACQTH_2 = "apdungDVC.maCQTH = ? AND ";
	private static final String _FINDER_COLUMN_F_TTHC_CQTH_MD_MACQTH_3 = "(apdungDVC.maCQTH IS NULL OR apdungDVC.maCQTH = '') AND ";
	private static final String _FINDER_COLUMN_F_TTHC_CQTH_MD_MUCDO_2 = "apdungDVC.mucdo = ?";

	public ApdungDVCPersistenceImpl() {
		setModelClass(ApdungDVC.class);

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
	 * Caches the apdung dvc in the entity cache if it is enabled.
	 *
	 * @param apdungDVC the apdung dvc
	 */
	@Override
	public void cacheResult(ApdungDVC apdungDVC) {
		entityCache.putResult(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCImpl.class, apdungDVC.getPrimaryKey(), apdungDVC);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { apdungDVC.getUuid(), apdungDVC.getGroupId() },
			apdungDVC);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_TTHC_CQTH_MD,
			new Object[] {
				apdungDVC.getMaTTHC(), apdungDVC.getMaCQTH(),
				apdungDVC.getMucdo()
			}, apdungDVC);

		apdungDVC.resetOriginalValues();
	}

	/**
	 * Caches the apdung dvcs in the entity cache if it is enabled.
	 *
	 * @param apdungDVCs the apdung dvcs
	 */
	@Override
	public void cacheResult(List<ApdungDVC> apdungDVCs) {
		for (ApdungDVC apdungDVC : apdungDVCs) {
			if (entityCache.getResult(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
						ApdungDVCImpl.class, apdungDVC.getPrimaryKey()) == null) {
				cacheResult(apdungDVC);
			}
			else {
				apdungDVC.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all apdung dvcs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ApdungDVCImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the apdung dvc.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ApdungDVC apdungDVC) {
		entityCache.removeResult(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCImpl.class, apdungDVC.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ApdungDVCModelImpl)apdungDVC, true);
	}

	@Override
	public void clearCache(List<ApdungDVC> apdungDVCs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ApdungDVC apdungDVC : apdungDVCs) {
			entityCache.removeResult(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
				ApdungDVCImpl.class, apdungDVC.getPrimaryKey());

			clearUniqueFindersCache((ApdungDVCModelImpl)apdungDVC, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ApdungDVCModelImpl apdungDVCModelImpl) {
		Object[] args = new Object[] {
				apdungDVCModelImpl.getUuid(), apdungDVCModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			apdungDVCModelImpl, false);

		args = new Object[] {
				apdungDVCModelImpl.getMaTTHC(), apdungDVCModelImpl.getMaCQTH(),
				apdungDVCModelImpl.getMucdo()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_TTHC_CQTH_MD, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_TTHC_CQTH_MD, args,
			apdungDVCModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ApdungDVCModelImpl apdungDVCModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					apdungDVCModelImpl.getUuid(),
					apdungDVCModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((apdungDVCModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					apdungDVCModelImpl.getOriginalUuid(),
					apdungDVCModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					apdungDVCModelImpl.getMaTTHC(),
					apdungDVCModelImpl.getMaCQTH(),
					apdungDVCModelImpl.getMucdo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_TTHC_CQTH_MD, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_TTHC_CQTH_MD, args);
		}

		if ((apdungDVCModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_TTHC_CQTH_MD.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					apdungDVCModelImpl.getOriginalMaTTHC(),
					apdungDVCModelImpl.getOriginalMaCQTH(),
					apdungDVCModelImpl.getOriginalMucdo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_TTHC_CQTH_MD, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_TTHC_CQTH_MD, args);
		}
	}

	/**
	 * Creates a new apdung dvc with the primary key. Does not add the apdung dvc to the database.
	 *
	 * @param apdungDVCId the primary key for the new apdung dvc
	 * @return the new apdung dvc
	 */
	@Override
	public ApdungDVC create(long apdungDVCId) {
		ApdungDVC apdungDVC = new ApdungDVCImpl();

		apdungDVC.setNew(true);
		apdungDVC.setPrimaryKey(apdungDVCId);

		String uuid = PortalUUIDUtil.generate();

		apdungDVC.setUuid(uuid);

		apdungDVC.setCompanyId(companyProvider.getCompanyId());

		return apdungDVC;
	}

	/**
	 * Removes the apdung dvc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param apdungDVCId the primary key of the apdung dvc
	 * @return the apdung dvc that was removed
	 * @throws NoSuchApdungDVCException if a apdung dvc with the primary key could not be found
	 */
	@Override
	public ApdungDVC remove(long apdungDVCId) throws NoSuchApdungDVCException {
		return remove((Serializable)apdungDVCId);
	}

	/**
	 * Removes the apdung dvc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the apdung dvc
	 * @return the apdung dvc that was removed
	 * @throws NoSuchApdungDVCException if a apdung dvc with the primary key could not be found
	 */
	@Override
	public ApdungDVC remove(Serializable primaryKey)
		throws NoSuchApdungDVCException {
		Session session = null;

		try {
			session = openSession();

			ApdungDVC apdungDVC = (ApdungDVC)session.get(ApdungDVCImpl.class,
					primaryKey);

			if (apdungDVC == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApdungDVCException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(apdungDVC);
		}
		catch (NoSuchApdungDVCException nsee) {
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
	protected ApdungDVC removeImpl(ApdungDVC apdungDVC) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(apdungDVC)) {
				apdungDVC = (ApdungDVC)session.get(ApdungDVCImpl.class,
						apdungDVC.getPrimaryKeyObj());
			}

			if (apdungDVC != null) {
				session.delete(apdungDVC);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (apdungDVC != null) {
			clearCache(apdungDVC);
		}

		return apdungDVC;
	}

	@Override
	public ApdungDVC updateImpl(ApdungDVC apdungDVC) {
		boolean isNew = apdungDVC.isNew();

		if (!(apdungDVC instanceof ApdungDVCModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(apdungDVC.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(apdungDVC);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in apdungDVC proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ApdungDVC implementation " +
				apdungDVC.getClass());
		}

		ApdungDVCModelImpl apdungDVCModelImpl = (ApdungDVCModelImpl)apdungDVC;

		if (Validator.isNull(apdungDVC.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			apdungDVC.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (apdungDVC.getCreateDate() == null)) {
			if (serviceContext == null) {
				apdungDVC.setCreateDate(now);
			}
			else {
				apdungDVC.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!apdungDVCModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				apdungDVC.setModifiedDate(now);
			}
			else {
				apdungDVC.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (apdungDVC.isNew()) {
				session.save(apdungDVC);

				apdungDVC.setNew(false);
			}
			else {
				apdungDVC = (ApdungDVC)session.merge(apdungDVC);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ApdungDVCModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { apdungDVCModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					apdungDVCModelImpl.getUuid(),
					apdungDVCModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((apdungDVCModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						apdungDVCModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { apdungDVCModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((apdungDVCModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						apdungDVCModelImpl.getOriginalUuid(),
						apdungDVCModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						apdungDVCModelImpl.getUuid(),
						apdungDVCModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
			ApdungDVCImpl.class, apdungDVC.getPrimaryKey(), apdungDVC, false);

		clearUniqueFindersCache(apdungDVCModelImpl, false);
		cacheUniqueFindersCache(apdungDVCModelImpl);

		apdungDVC.resetOriginalValues();

		return apdungDVC;
	}

	/**
	 * Returns the apdung dvc with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the apdung dvc
	 * @return the apdung dvc
	 * @throws NoSuchApdungDVCException if a apdung dvc with the primary key could not be found
	 */
	@Override
	public ApdungDVC findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApdungDVCException {
		ApdungDVC apdungDVC = fetchByPrimaryKey(primaryKey);

		if (apdungDVC == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApdungDVCException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return apdungDVC;
	}

	/**
	 * Returns the apdung dvc with the primary key or throws a {@link NoSuchApdungDVCException} if it could not be found.
	 *
	 * @param apdungDVCId the primary key of the apdung dvc
	 * @return the apdung dvc
	 * @throws NoSuchApdungDVCException if a apdung dvc with the primary key could not be found
	 */
	@Override
	public ApdungDVC findByPrimaryKey(long apdungDVCId)
		throws NoSuchApdungDVCException {
		return findByPrimaryKey((Serializable)apdungDVCId);
	}

	/**
	 * Returns the apdung dvc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the apdung dvc
	 * @return the apdung dvc, or <code>null</code> if a apdung dvc with the primary key could not be found
	 */
	@Override
	public ApdungDVC fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
				ApdungDVCImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ApdungDVC apdungDVC = (ApdungDVC)serializable;

		if (apdungDVC == null) {
			Session session = null;

			try {
				session = openSession();

				apdungDVC = (ApdungDVC)session.get(ApdungDVCImpl.class,
						primaryKey);

				if (apdungDVC != null) {
					cacheResult(apdungDVC);
				}
				else {
					entityCache.putResult(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
						ApdungDVCImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
					ApdungDVCImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return apdungDVC;
	}

	/**
	 * Returns the apdung dvc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param apdungDVCId the primary key of the apdung dvc
	 * @return the apdung dvc, or <code>null</code> if a apdung dvc with the primary key could not be found
	 */
	@Override
	public ApdungDVC fetchByPrimaryKey(long apdungDVCId) {
		return fetchByPrimaryKey((Serializable)apdungDVCId);
	}

	@Override
	public Map<Serializable, ApdungDVC> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ApdungDVC> map = new HashMap<Serializable, ApdungDVC>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ApdungDVC apdungDVC = fetchByPrimaryKey(primaryKey);

			if (apdungDVC != null) {
				map.put(primaryKey, apdungDVC);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
					ApdungDVCImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ApdungDVC)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_APDUNGDVC_WHERE_PKS_IN);

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

			for (ApdungDVC apdungDVC : (List<ApdungDVC>)q.list()) {
				map.put(apdungDVC.getPrimaryKeyObj(), apdungDVC);

				cacheResult(apdungDVC);

				uncachedPrimaryKeys.remove(apdungDVC.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ApdungDVCModelImpl.ENTITY_CACHE_ENABLED,
					ApdungDVCImpl.class, primaryKey, nullModel);
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
	 * Returns all the apdung dvcs.
	 *
	 * @return the apdung dvcs
	 */
	@Override
	public List<ApdungDVC> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the apdung dvcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of apdung dvcs
	 * @param end the upper bound of the range of apdung dvcs (not inclusive)
	 * @return the range of apdung dvcs
	 */
	@Override
	public List<ApdungDVC> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the apdung dvcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of apdung dvcs
	 * @param end the upper bound of the range of apdung dvcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of apdung dvcs
	 */
	@Override
	public List<ApdungDVC> findAll(int start, int end,
		OrderByComparator<ApdungDVC> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the apdung dvcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of apdung dvcs
	 * @param end the upper bound of the range of apdung dvcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of apdung dvcs
	 */
	@Override
	public List<ApdungDVC> findAll(int start, int end,
		OrderByComparator<ApdungDVC> orderByComparator,
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

		List<ApdungDVC> list = null;

		if (retrieveFromCache) {
			list = (List<ApdungDVC>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_APDUNGDVC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_APDUNGDVC;

				if (pagination) {
					sql = sql.concat(ApdungDVCModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ApdungDVC>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ApdungDVC>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the apdung dvcs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ApdungDVC apdungDVC : findAll()) {
			remove(apdungDVC);
		}
	}

	/**
	 * Returns the number of apdung dvcs.
	 *
	 * @return the number of apdung dvcs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_APDUNGDVC);

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
		return ApdungDVCModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the apdung dvc persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ApdungDVCImpl.class.getName());
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
	private static final String _SQL_SELECT_APDUNGDVC = "SELECT apdungDVC FROM ApdungDVC apdungDVC";
	private static final String _SQL_SELECT_APDUNGDVC_WHERE_PKS_IN = "SELECT apdungDVC FROM ApdungDVC apdungDVC WHERE apdungDVCId IN (";
	private static final String _SQL_SELECT_APDUNGDVC_WHERE = "SELECT apdungDVC FROM ApdungDVC apdungDVC WHERE ";
	private static final String _SQL_COUNT_APDUNGDVC = "SELECT COUNT(apdungDVC) FROM ApdungDVC apdungDVC";
	private static final String _SQL_COUNT_APDUNGDVC_WHERE = "SELECT COUNT(apdungDVC) FROM ApdungDVC apdungDVC WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "apdungDVC.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ApdungDVC exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ApdungDVC exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ApdungDVCPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}